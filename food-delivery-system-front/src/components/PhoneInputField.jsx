import React, {useState} from "react";

import "react-phone-number-input/style.css";
import PhoneInput, {isValidPhoneNumber} from "react-phone-number-input";
import {Form} from "react-bootstrap";

const PhoneInputField = () => {
    const [inputValue, setInputValue] = useState("");
    const [showError, setShowError] = useState(false);

    return (
        <Form.Group className="p-4 pt-0" controlId="sign-up-password">
            <Form.Label>Phone number</Form.Label>
            <PhoneInput
                placeholder="Enter phone number"
                value={inputValue}
                onChange={inputValue => {
                    setInputValue(inputValue);
                    setShowError(true);
                }}
            />
            <div className="form-control-feedback" style={{display: showError ? 'block' : 'none' }}>
                {inputValue ? (isValidPhoneNumber(inputValue) ? " " : 'Invalid phone number') : 'Phone number is required field'}
            </div>
        </Form.Group>
    );
}

export default PhoneInputField;