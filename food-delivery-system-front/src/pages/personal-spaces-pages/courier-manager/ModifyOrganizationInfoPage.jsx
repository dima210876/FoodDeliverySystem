import React, {useState} from 'react';
import {Button, Col, Container, Form, Row} from 'react-bootstrap';
import { Formik } from 'formik';
import * as Yup from 'yup';

import PhoneInputField from "../../../components/inputFields/phoneInputField";
import Navbar from "../../../components/Navbar";
import Footer from "../../../components/footer"

import {useNavigate} from "react-router-dom";
import {useDispatch, useSelector} from "react-redux";
import {isValidPhoneNumber} from "react-phone-number-input";
import AddressInputField from "../../../components/inputFields/addressInputField";

const ModifyOrganizationInfoPage = () => {
    const dispatch = useDispatch();
    // const navigate = useNavigate();
    const organization = useSelector(state => state.userData.deliveryOrgManagerData.manager.organization);
    const [phone, setPhone] = useState("");
    const [address, setAddress] = useState("");
    const [submitClicked, setSubmitClicked] = useState(false);

    const changePhone = (dataFromPhoneInput) => {
        setPhone(dataFromPhoneInput);
    }
    const changeAddress = (dataFromAddressInput) => {
        setAddress(dataFromAddressInput);
    }
    const changeSubmit = () => {
        setSubmitClicked(!submitClicked);
    }

    const schema = Yup.object().shape({
        organizationName: Yup.string()
            .min(2, 'First name should contain minimum 2 characters.')
            .max(30, 'First name should contain maximum 30 characters.'),
        accountNumber: Yup.string()
            .min(12, 'Account number should contain of 14 characters.')
            .max(14, 'Account number should contain of 14 characters.')
            .matches(
                /^[A-Z0-9]*$/,
                "Account number should contain of capital latin letters or digits."
            ),
        officeAddress: Yup.string()
            .required('Address is required field'),
    });

    return (
        <Formik
            initialValues={{
                organizationName: organization.name,
                accountNumber: organization.accountNumber,
                officeAddress: organization.address
            }}
            enableReinitialize={true}
            validationSchema={schema}
            validateOnChange={false}
            validateOnBlur={false}
            onSubmit={(values) => {
                // if (phone && isValidPhoneNumber(phone)) {
                //
                // }
            }}
        >
            {({
                  handleSubmit,
                  handleChange,
                  errors,
                  values }) => (
                <>
                    <Navbar />
                    <Container className="personal-space-form-container">
                        <Col md={6} className="m-auto mt-5 full-width d-flex justify-content-center">
                            <Form id="sign-in-form" className="m-5 p-5 rounded w-75"  noValidate onSubmit={handleSubmit}>
                                <div className="text-center">
                                    <h1 className="fs-0 mb-4">Modify organization info</h1>
                                </div>
                                <Form.Group className="p-4 pt-2" controlId="modify-organization-name">
                                    <Form.Label>Organization name</Form.Label>
                                    <Form.Control type="textarea" name="organizationName" placeholder="Enter organization name" value={values.organizationName} onChange={handleChange} isInvalid={!!errors.organizationName}/>
                                    <Form.Control.Feedback type="invalid">
                                        {errors.organizationName}
                                    </Form.Control.Feedback>
                                </Form.Group>
                                <Form.Group className="p-4 pt-0" controlId="modify-account-number">
                                    <Form.Label>Account number</Form.Label>
                                    <Form.Control type="textarea" name="accountNumber"  placeholder="Enter account number" value={values.accountNumber} onChange={handleChange} isInvalid={!!errors.accountNumber}/>
                                    <Form.Control.Feedback type="invalid">
                                        {errors.accountNumber}
                                    </Form.Control.Feedback>
                                </Form.Group>
                                {/*<Form.Group className="p-4 pt-0" controlId="modify-office-address">*/}
                                {/*    <Form.Label>Office address</Form.Label>*/}
                                {/*    <Form.Control type="textarea" name="officeAddress"  placeholder="Enter main office address" value={values.officeAddress} onChange={handleChange} isInvalid={!!errors.officeAddress}/>*/}
                                {/*    <Form.Control.Feedback type="invalid">*/}
                                {/*        {errors.officeAddress}*/}
                                {/*    </Form.Control.Feedback>*/}
                                {/*</Form.Group>*/}
                                <AddressInputField changeAddress={changeAddress} submitClicked={submitClicked}/>
                                <PhoneInputField phone={phone} changePhone={changePhone} submitClicked={submitClicked} />
                                <div className="mt-3 text-center ">
                                    <Button type="submit" variant="danger" size="lg" onClick={changeSubmit}>Modify info</Button>
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

export {ModifyOrganizationInfoPage};