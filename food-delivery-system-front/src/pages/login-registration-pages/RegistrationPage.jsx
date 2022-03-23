import React, {useState} from 'react';

import {Formik, Field, useFormikContext} from 'formik';
import * as Yup from 'yup';
import {Button, ButtonGroup, Col, Form, Container, Row} from 'react-bootstrap';

import './loginRegistration.css';
import {Link, useNavigate } from "react-router-dom";
import PhoneInputField from "../../components/PhoneInputField";
import * as authActions from "../../redux/actions/AuthActions";
import {useDispatch} from "react-redux";
import {isValidPhoneNumber} from "react-phone-number-input";

const RegistrationPage = () => {
    const dispatch = useDispatch();
    // const navigate = useNavigate();
    const [phone, setPhone] = useState("");
    const [submitClicked, setSubmitClicked] = useState(false);

    const changePhone = (dataFromPhoneInput) => {
        setPhone(dataFromPhoneInput);
    }
    const changeSubmit = () => {
        setSubmitClicked(!submitClicked);
    }

    const schema = Yup.object().shape({
        firstName: Yup.string()
            .min(2, 'First name should contain minimum 2 characters.')
            .max(30, 'First name should contain maximum 30 characters.')
            .required('First name is required field'),
        lastName: Yup.string()
            .min(2, 'Last name should contain minimum 2 characters.')
            .max(30, 'Last name should contain maximum 30 characters.')
            .required('Last name is required field'),
        email: Yup.string()
            .email('Invalid email')
            .required('Email is required field'),
        password: Yup.string()
            .required('Password is required field')
            .matches(
                /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/,
                "Must minimum 8 characters: at least one uppercase letter, one lowercase letter and one number"
            ),
    });

    return (
        <Formik
            initialValues={{
                firstName: '',
                lastName: '',
                email: '',
                password: '',
            }}
            validationSchema={schema}
            validateOnChange={false}
            validateOnBlur={false}
            onSubmit={(values) => {
                if (phone && isValidPhoneNumber(phone)) {
                    authActions.registerUser(values.firstName, values.lastName, values.email, phone, values.password)(dispatch).then(() => {
                        // navigate('/main-page-link-from-another-ticket');
                    });
                }
            }}
        >
            {({
                  handleSubmit,
                  handleChange,
                  errors,
              }) => (
            <Container id="main-container">
                <Col lg={6} md={6} sm={12} className="m-auto shadow-sm full-width d-flex justify-content-center">
                    <Form id="sign-up-form" className="m-5 p-4 rounded w-75" noValidate onSubmit={handleSubmit}>
                        <div className="text-center">
                            <h1 className="fs-0">Registration</h1>
                            <h6 className="mb-0">Already have an account?</h6>
                            <Link className="text-decoration-none" to="/login">
                                <h6 className="mt-0 text-danger">Log in</h6>
                            </Link>
                        </div>
                        <Form.Group className="p-4 mt-2" controlId="sign-up-first-name">
                            <Form.Label>First name</Form.Label>
                            <Form.Control type="textarea" name="firstName" placeholder="Enter first name" onChange={handleChange} isInvalid={!!errors.firstName}/>
                            <Form.Control.Feedback type="invalid">
                                {errors.firstName}
                            </Form.Control.Feedback>
                        </Form.Group>
                        <Form.Group className="p-4 pt-0" controlId="sign-up-last-name">
                            <Form.Label>Last name</Form.Label>
                            <Form.Control type="textarea" name="lastName" placeholder="Enter last name" onChange={handleChange} isInvalid={!!errors.lastName}/>
                            <Form.Control.Feedback type="invalid">
                                {errors.lastName}
                            </Form.Control.Feedback>
                        </Form.Group>
                        <Form.Group className="p-4 pt-0" controlId="sign-up-email-address">
                            <Form.Label>Email</Form.Label>
                            <Form.Control type="email" name="email" placeholder="Enter email"  onChange={handleChange} isInvalid={!!errors.email}/>
                            <Form.Control.Feedback type="invalid">
                                {errors.email}
                            </Form.Control.Feedback>
                        </Form.Group>
                        <PhoneInputField changePhone={changePhone}  submitClicked={submitClicked}/>
                        <Form.Group className="p-4 pt-0" controlId="sign-up-password">
                            <Form.Label>Password</Form.Label>
                            <Form.Control type="password" name="password" placeholder="Password" onChange={handleChange} isInvalid={!!errors.password}/>
                            <Form.Control.Feedback type="invalid">
                                {errors.password}
                            </Form.Control.Feedback>
                        </Form.Group>
                        <div className="mt-3 text-center ">
                            <Button type="submit" variant="danger" size="lg" onClick={changeSubmit}>Create account</Button>
                        </div>
                    </Form>
                </Col>
            </Container>
            )}
        </Formik>
    );
};

export default RegistrationPage;