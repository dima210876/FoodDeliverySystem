import React, {useState} from "react";

import "react-phone-number-input/style.css";
import PhoneInput, {isValidPhoneNumber} from "react-phone-number-input";
import {Form} from "react-bootstrap";

const PhoneInputField = () => {
    const [inputValue, setInputValue] = useState("");

    return (
        <Form.Group className="p-4 pt-0" controlId="sign-up-password">
            <Form.Label>Phone number</Form.Label>
            <PhoneInput
                placeholder="Enter phone number"
                value={inputValue}
                onChange={inputValue => setInputValue(inputValue)}
                />
            <div class="form-control-feedback">
                {inputValue ? (isValidPhoneNumber(inputValue) ? " " : 'Invalid phone number') : 'Phone number is required field'}
            </div>
        </Form.Group>
    );
}

export default PhoneInputField;