import React, {useEffect, useState} from "react";
import PlacesAutocomplete, {
    geocodeByAddress,
    getLatLng
} from "react-places-autocomplete";
import {Form} from "react-bootstrap";

const AddressInputField = ({ changeAddress, submitClicked  }) => {
    const [inputAddress, setInputAddress] = useState("");
    const [coordinates, setCoordinates] = useState({
        lat: null,
        lng: null
    });
    const [error, setError] = useState("");

    useEffect(() => {
        if (submitClicked) {
            validateAddress(inputAddress);
        }
    }, [submitClicked]);

    function validateAddress(inputValue) {
        if (inputValue) {
            console.log('validating address:');

            geocodeByAddress(inputValue)
                .then(latLng => {
                    setCoordinates(latLng);
                    setError("");
                })
                .catch(error => {
                    setError(error);
                    setCoordinates(null);
                })
            setInputAddress(inputValue);
        } else {
            setError("");
        }
        changeAddress(inputValue);
    }

    const handleSelect = value => {
        validateAddress(value);
    };

    return (
            <PlacesAutocomplete
                value={inputAddress}
                onChange={setInputAddress}
                onSelect={handleSelect}
                // onSelect={setInputAddress}

            >
                {({ getInputProps, suggestions, getSuggestionItemProps, loading }) => (
                        <Form.Group className="p-4 pt-0" controlId="address-input">
                            <Form.Label>Office address</Form.Label>
                            <div>{inputAddress}</div>
                            <input {...getInputProps({ placeholder: "Enter address", class: "form-control"} )} />

                            <div className="form-control-feedback" style={{display: error !== '' ? 'block' : 'none' }}>
                                { error === 'ZERO_RESULTS' ? 'There is no such address. Please select it from the suggestions.' : 'Address error'}
                            </div>
                        <div className="address-suggestions">
                            {loading ? <div>...loading</div> : null}
                            {suggestions.map(suggestion => {
                                const style = {
                                    backgroundColor: suggestion.active ? "#c8c8c8" : "#fff"
                                };
                                return (
                                    <div {...getSuggestionItemProps(suggestion, { style })}>
                                        {suggestion.description}
                                    </div>
                                );
                            })}
                        </div>
                        </Form.Group>
                )}
            </PlacesAutocomplete>
    );
}

export default AddressInputField;