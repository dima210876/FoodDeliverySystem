import React, {useEffect, useState} from "react";

import "react-phone-number-input/style.css";
import "./phoneInputField.css"
import PhoneInput, {isValidPhoneNumber} from "react-phone-number-input";
import {Form} from "react-bootstrap";

const PhoneInputField = ({ changePhone, submitClicked }) => {
    const [inputValue, setInputValue] = useState("");
    const [showError, setShowError] = useState(false);

    useEffect(() => {
        if (submitClicked) {
            validatePhone(inputValue);
        }
    }, [submitClicked]);

    function validatePhone(inputValue) {
        setInputValue(inputValue);
        setShowError(true);
        changePhone(inputValue);
    }

    return (
        <Form.Group className="p-4 pt-0" controlId="sign-up-password">
            <Form.Label>Phone number</Form.Label>
            <PhoneInput
                name="phone"
                placeholder="Enter phone number"
                value={inputValue}
                onChange={inputValue => {
                    validatePhone(inputValue);
                }}
            />
            <div className="form-control-feedback" style={{display: showError ? 'block' : 'none' }}>
                {inputValue ? (isValidPhoneNumber(inputValue) ? " " : 'Invalid phone number') : 'Phone number is required field'}
            </div>
        </Form.Group>
    );
}

export default PhoneInputField;