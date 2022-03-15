import React from 'react';
import {Button, ButtonGroup, Col, Container, Form, Row} from 'react-bootstrap';
import { Formik, Field } from 'formik';
import * as Yup from 'yup';

import '../login-registration-pages/loginRegistration.css';
import {Link} from "react-router-dom";
import PhoneInputField from "../../components/PhoneInputField";


const RegistrationManager = () => {
    const schema = Yup.object().shape({
        restaurantName: Yup.string()
            .min(2, 'First name should contain minimum 2 characters.')
            .max(30, 'First name should contain maximum 30 characters.')
            .required('First name is required field'),
        description: Yup.string()
            .min(100, 'First name should contain minimum 100 characters.')
            .max(300, 'First name should contain maximum 300 characters.')
            .required('First name is required field'),
        restaurantPhone: Yup.string()
            .required("Phone number is required")
            .matches(
                /^([0]{1}|\+?[234]{3})([7-9]{1})([0|1]{1})([\d]{1})([\d]{7})$/g,
                "Invalid phone number"
            ),
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
                restaurantName: '',
                description: '',
                restaurantPhone: '',
                firstName: '',
                lastName: '',
                email: '',
                password: '',
            }}
            validationSchema={schema}
            validateOnChange={false}
            validateOnBlur={false}
            onSubmit={(values, { validate }) => {
                validate(values);
            }}
        >
            {({
                  handleSubmit,
                  handleChange,
                  handleBlur,
                  values,
                  touched,
                  isValid,
                  errors, }) => (
                <Container id="main-container">
                    <Col lg={6} md={6} sm={12} className="m-auto shadow-sm full-width d-flex justify-content-center">
                        <Form id="sign-in-form" className="m-5 p-4 rounded w-75"  noValidate onSubmit={handleSubmit}>
                            <div className="text-center">
                                <h1 className="fs-0">Registration</h1>
                            </div>

                            <h2 className="mt-5 pb-0 text-danger">Restaurant info</h2>
                            <Form.Group className="p-4 pt-2 " controlId="sign-up-restaurant-name">
                                <Form.Label>Restaurant name</Form.Label>
                                <Form.Control type="textarea" name="restaurantName"  placeholder="Enter restaurants name" onChange={handleChange} isInvalid={!!errors.restaurantName}/>
                                <Form.Control.Feedback type="invalid">
                                    {errors.restaurantName}
                                </Form.Control.Feedback>
                            </Form.Group>
                            <Form.Group className="p-4 pt-0" controlId="sign-up-description">
                                <Form.Label>Description</Form.Label>
                                <Form.Control type="textarea" name="description"  placeholder="Describe your restaurant and its cuisine" onChange={handleChange} isInvalid={!!errors.description}/>
                                <Form.Control.Feedback type="invalid">
                                    {errors.description}
                                </Form.Control.Feedback>
                            </Form.Group>
                            <Form.Group className="p-4 pt-0" controlId="sign-up-address">
                                <Form.Label>Address</Form.Label>
                                <Form.Control type="textarea" placeholder="Enter address of the restaurant"/>
                            </Form.Group>
                            <PhoneInputField />
                            <h2 className="mt-4 text-danger">Manager info</h2>
                            <Form.Group className="p-4 pt-2" controlId="sign-up-first-name">
                                <Form.Label>First name</Form.Label>
                                <Form.Control type="textarea" name="firstName"  placeholder="Enter first name" onChange={handleChange} isInvalid={!!errors.firstName}/>
                                <Form.Control.Feedback type="invalid">
                                    {errors.firstName}
                                </Form.Control.Feedback>
                            </Form.Group>
                            <Form.Group className="p-4 pt-0" controlId="sign-up-last-name">
                                <Form.Label>Last name</Form.Label>
                                <Form.Control type="textarea" name="lastName"  placeholder="Enter last name" onChange={handleChange} isInvalid={!!errors.lastName}/>
                                <Form.Control.Feedback type="invalid">
                                    {errors.lastName}
                                </Form.Control.Feedback>
                            </Form.Group>
                            <Form.Group className="p-4 pt-0" controlId="sign-up-email-address">
                                <Form.Label>Email</Form.Label>
                                <Form.Control type="email" name="email"  placeholder="Enter email" onChange={handleChange} isInvalid={!!errors.email}/>
                                <Form.Control.Feedback type="invalid">
                                    {errors.email}
                                </Form.Control.Feedback>
                            </Form.Group>
                            <PhoneInputField />
                            <Form.Group className="p-4 pt-0" controlId="sign-up-password">
                                <Form.Label>Password</Form.Label>
                                <Form.Control type="password" name="password"  placeholder="Password" onChange={handleChange} isInvalid={!!errors.password}/>
                                <Form.Control.Feedback type="invalid">
                                    {errors.password}
                                </Form.Control.Feedback>
                            </Form.Group>
                            <div className="mt-3 text-center ">
                                <Button type="submit" variant="danger" size="lg">Create account</Button>
                            </div>
                        </Form>
                    </Col>
                </Container>
            )}
        </Formik>
    );
};

export default RegistrationManager;