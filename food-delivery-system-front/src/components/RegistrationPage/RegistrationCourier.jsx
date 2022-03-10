import React, {useState} from 'react';
import {Button, ButtonGroup, Col, Container, Form, Row} from 'react-bootstrap';
import { Formik, Field } from 'formik';
import * as Yup from 'yup';

import options from '../../../public/testData/deliveryOrganizations';

import '../loginReg.css';
import {Typeahead} from "react-bootstrap-typeahead";
import {Link} from "react-router-dom";
import PhoneInputField from "./PhoneInputField";


const RegistrationCourier = () => {
    const [selectedOrganization, setSelectedOrganization] = useState([]);

    const schema = Yup.object().shape({
        firstName: Yup.string()
            .min(2, 'First name should contain minimum 2 characters.')
            .max(30, 'First name should contain maximum 30 characters.')
            .required('First name is required field'),
        lastName: Yup.string()
            .min(2, 'Last name should contain minimum 2 characters.')
            .max(30, 'Last name should contain maximum 30 characters.')
            .required('Last name is required field'),
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
                <Form id="sign-in-form" className="m-5 p-4 rounded w-75" noValidate onSubmit={handleSubmit}>
                    <div className="text-center">
                        <h1 className="fs-0">Registration</h1>
                        <h6 className="mb-0">Already have an account?</h6>
                        <Link className="text-decoration-none" to="/login">
                            <h6 className="mt-0 text-danger">Log in</h6>
                        </Link>
                        <h3 className="mt-4">Choose registration form</h3>
                        <ButtonGroup aria-label="buttons-type-of-user">
                            <Link to="/registration-user">
                                <Button className="m-1" variant="danger">User</Button>
                            </Link>
                            <Link to="/registration-courier">
                                <Button className="m-1" variant="danger" disabled>Courier</Button>
                            </Link>
                            <Link to="/registration-manager">
                                <Button className="m-1" variant="danger">Manager</Button>
                            </Link>
                        </ButtonGroup>
                    </div>

                    <Form.Group className="p-4 mt-2" controlId="delivery-company-select">
                            <Form.Label>Delivery organization</Form.Label>
                            <Typeahead
                                id="basic-typeahead-single"
                                labelKey="name"
                                onChange={setSelectedOrganization}
                                options={options}
                                placeholder="Choose your company..."
                                selected={selectedOrganization}
                            />
                    </Form.Group>
                    <Form.Group className="p-4 pt-0" controlId="sign-up-first-name">
                        <Form.Label>First name</Form.Label>
                        <Form.Control type="textarea" name="firstName"  placeholder="Enter first name" className="font" onChange={handleChange}  isInvalid={!!errors.firstName}/>
                        <Form.Control.Feedback type="invalid">
                            {errors.firstName}
                        </Form.Control.Feedback>
                    </Form.Group>
                    <Form.Group className="p-4 pt-0" controlId="sign-up-last-name">
                        <Form.Label>Last name</Form.Label>
                        <Form.Control type="textarea" name="lastName"  placeholder="Enter last name" onChange={handleChange}  isInvalid={!!errors.lastName}/>
                        <Form.Control.Feedback type="invalid">
                            {errors.lastName}
                        </Form.Control.Feedback>
                    </Form.Group>
                    <PhoneInputField />
                    <Form.Group className="p-4 pt-0" controlId="sign-up-password">
                        <Form.Label>Password</Form.Label>
                        <Form.Control type="password" name="password"  placeholder="Password" onChange={handleChange}  isInvalid={!!errors.password}/>
                        <Form.Control.Feedback type="invalid">
                            {errors.password}
                        </Form.Control.Feedback>
                    </Form.Group>
                    <div className="mt-3 text-center ">
                        <Button  type="submit" variant="danger" size="lg">Create account</Button>
                    </div>
                </Form>
            </Col>
        </Container>
    )}
</Formik>
    );
};

export default RegistrationCourier;