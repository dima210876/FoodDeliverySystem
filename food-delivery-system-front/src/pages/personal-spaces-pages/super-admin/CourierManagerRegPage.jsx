import React, {useState} from 'react';
import {Button, Col, Container, Form, Row} from 'react-bootstrap';
import { Formik } from 'formik';
import * as Yup from 'yup';

import PhoneInputField from "../../../components/PhoneInputField";
import Navbar from "../../../components/Navbar";
import Footer from "../../../components/footer"

import "./formsInPersonalSpace.css"
import * as authActions from "../../../redux/actions/AuthActions";
import {useNavigate} from "react-router-dom";
import {useDispatch} from "react-redux";
import {isValidPhoneNumber} from "react-phone-number-input";
import {registerOrganization} from "../../../redux/actions/AuthActions";

const CourierManagerRegPage = () => {
    const dispatch = useDispatch();
    // const navigate = useNavigate();
    const [phone, setPhone] = useState("");
    const [submitClicked, setSubmitClicked] = useState(false);
    const [stateOfAlert, setStateOfAlert] = useState(false);


    const changePhone = (dataFromPhoneInput) => {
        setPhone(dataFromPhoneInput);
    }
    const changeSubmit = () => {
        setSubmitClicked(!submitClicked);
    }

    const schema = Yup.object().shape({
        organizationName: Yup.string()
            .min(2, 'Organization name should contain minimum 2 characters.')
            .max(30, 'Organization name should contain maximum 30 characters.')
            .required('Organization name is required field'),
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
                organizationName: '',
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
                    registerOrganization(values.organizationName, values.firstName, values.lastName, values.email, phone, values.password)(dispatch).then(() => {
                        // navigate('/admin-space-link-from-another-ticket');
                    }).catch(() => {
                        setStateOfAlert(true);
                        setTimeout(() => {
                            setStateOfAlert(false);
                        }, 3000)
                    });
                }
            }}
        >
            {({
                  handleSubmit,
                  handleChange,
                  errors, }) => (
                <>
                    <Navbar />
                    <Container className="personal-space-form-container">
                        {stateOfAlert ? <div className="alert alert-danger" role='alert'>Error on the server</div> : null}
                        <Col md={6} className="m-auto mt-5 full-width d-flex justify-content-center">
                            <Form id="sign-in-form" className="m-5 p-5 rounded w-75"  noValidate onSubmit={handleSubmit}>
                                <div className="text-center">
                                    <h1 className="fs-0">Delivery organization registration</h1>
                                </div>

                                <h2 className="mt-5 pb-0 text-danger">Organization info</h2>
                                <Form.Group className="p-4 pt-2 " controlId="sign-up-restaurant-name">
                                    <Form.Label>Organization name</Form.Label>
                                    <Form.Control type="textarea" name="organizationName"  placeholder="Enter organization name" onChange={handleChange} isInvalid={!!errors.organizationName}/>
                                    <Form.Control.Feedback type="invalid">
                                        {errors.organizationName}
                                    </Form.Control.Feedback>
                                </Form.Group>
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
                                <PhoneInputField changePhone={changePhone} submitClicked={submitClicked} />
                                <Form.Group className="p-4 pt-0" controlId="sign-up-password">
                                    <Form.Label>Password</Form.Label>
                                    <Form.Control type="password" name="password"  placeholder="Password" onChange={handleChange} isInvalid={!!errors.password}/>
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
                    <Footer />
                </>

            )}
        </Formik>
    );
};

export default CourierManagerRegPage;