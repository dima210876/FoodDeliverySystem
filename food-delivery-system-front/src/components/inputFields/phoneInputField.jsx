import React, {useEffect, useState} from "react";

import "react-phone-number-input/style.css";
import "./phoneInputField.css"
import PhoneInput, {isValidPhoneNumber} from "react-phone-number-input";
import {Form} from "react-bootstrap";

const PhoneInputField = ({ phone, changePhone, submitClicked }) => {
    const [inputPhone, setInputPhone] = useState("");
    const [showError, setShowError] = useState(false);

    useEffect(() => {
        if (submitClicked) {
            validatePhone(inputPhone);
        }
    }, [submitClicked]);

    function validatePhone(inputValue) {
        if (inputValue === phone) {
            setInputPhone(inputValue);
            setShowError(true);
            changePhone(inputValue);
        }
    }

    return (
        <Form.Group className="p-4 pt-0" controlId="phone-input">
            <Form.Label>Phone number</Form.Label>
            <PhoneInput
                name="phone"
                placeholder="Enter phone number"
                value={phone ? phone : inputPhone}
                onChange={inputValue => {
                    validatePhone(inputValue);
                }}
            />
            <div className="form-control-feedback" style={{display: showError ? 'block' : 'none' }}>
                {inputPhone ? (isValidPhoneNumber(inputPhone) ? " " : 'Invalid phone number') : 'Phone number is required field'}
            </div>
        </Form.Group>
    );
}

export default PhoneInputField;