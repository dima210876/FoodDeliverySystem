import React, {useState} from 'react';
import {Button, Col, Container, Form, Row} from 'react-bootstrap';
import { Formik } from 'formik';
import * as Yup from 'yup';

import PhoneInputField from "../../../components/inputFields/phoneInputField";
import Navbar from "../../../components/navbar";
import Footer from "../../../components/footer"

import {useNavigate} from "react-router-dom";
import {useDispatch, useSelector} from "react-redux";
import {isValidPhoneNumber} from "react-phone-number-input";
import AddressInputField from "../../../components/inputFields/addressInputField";
import {changeDeliveryOrganizationInfo} from "../../../redux/actions/changeInfoActions";
import {geocodeByAddress} from "react-places-autocomplete";

const ModifyOrganizationInfoPage = () => {
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const organization = useSelector(state => state.userData.deliveryOrgManagerData.organization);
    const [phone, setPhone] = useState(organization.phoneNumber);
    const [address, setAddress] = useState(organization.address);
    const [coordinates, setCoordinates] = useState({
        lat: organization.latitude,
        lng: organization.longitude
    });
    const [submitClicked, setSubmitClicked] = useState(false);

    const changeSubmit = () => {
        setSubmitClicked(true);
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
            )
    });

    return (
        <Formik
            initialValues={{
                organizationName: organization.name,
                accountNumber: organization.accountNumber,
            }}
            enableReinitialize={true}
            validationSchema={schema}
            validateOnChange={false}
            validateOnBlur={false}
            onSubmit={(values) => {
                geocodeByAddress(address).then(() => {
                    if (!phone || (phone && isValidPhoneNumber(phone))) {
                        changeDeliveryOrganizationInfo(organization.id, values.organizationName, values.accountNumber, phone, address, coordinates.lat, coordinates.lng)(dispatch).then(() => {
                            navigate('/courier-manager');
                        });
                    }}
                )
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
                                <AddressInputField address={address} changeAddress={setAddress} changeCoordinates={setCoordinates} submitClicked={submitClicked}/>
                                <PhoneInputField phone={phone} changePhone={setPhone} submitClicked={submitClicked} required={false}/>
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