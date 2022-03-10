import React from 'react';
import {Button, ButtonGroup, Col, Container, Form, Row} from 'react-bootstrap';
import { Formik, Field } from 'formik';
import * as Yup from 'yup';

import '../loginReg.css';
import {Link} from "react-router-dom";


const LoginPage = () => {
    const SignupSchema = Yup.object().shape({
        email: Yup.string()
            .email('Invalid email')
            .required('Email is required field'),
        password: Yup.string()
            .required('Password is required field'),
    });

    return (
        <Formik
            initialValues={{
                email: '',
                password: '',
            }}
            validationSchema={SignupSchema}
            onSubmit={values => {
                // same shape as initial values
                console.log(values);
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
                    <Form id="sign-in-form" className="m-5 p-4 rounded w-75">
                        <div className="text-center">
                            <h1 className="fs-0">Login</h1>
                            <h6 className="mb-0">Don't have an account?</h6>
                            <Link className="text-decoration-none" to="/registration-user">
                                <h6 className="mt-0 text-danger">Register</h6>
                            </Link>
                            <h3 className="mt-4">Who are you?</h3>
                            <Button className="m-1" variant="danger">User</Button>
                            <Button className="m-1" variant="danger">Courier</Button>
                            <Button className="m-1" variant="danger">Manager</Button>
                        </div>

                        <Form.Group className="p-4 mt-2" controlId="sign-in-email-address">
                            <Form.Label>Email</Form.Label>
                            <Form.Control type="email" name="email" placeholder="Enter email" onChange={handleChange} isInvalid={!!errors.email}/>
                            <Form.Control.Feedback type="invalid">
                                {errors.email}
                            </Form.Control.Feedback>
                        </Form.Group>
                        <Form.Group className="p-4 pt-0" controlId="sign-in-password">
                            <Form.Label>Password</Form.Label>
                            <Form.Control type="password" name="password" placeholder="Password" onChange={handleChange} isInvalid={!!errors.password}/>
                            <Form.Control.Feedback type="invalid">
                                {errors.password}
                            </Form.Control.Feedback>
                        </Form.Group>
                        <div className="mt-3 text-center ">
                            <Button variant="danger" size="lg">Sign in</Button>
                        </div>
                    </Form>
                </Col>
            </Container>
            )}
        </Formik>
    );
};

export default LoginPage;