import React, {useEffect, useState} from 'react';
import '@trendmicro/react-radio/dist/react-radio.css';
import * as Yup from "yup";
import {Formik} from 'formik';
import {Button, Col, Container, Form} from 'react-bootstrap';
import {RadioButton, RadioGroup} from '@trendmicro/react-radio';
import {useDispatch, useSelector} from "react-redux";
import {useNavigate} from "react-router-dom";
import Footer from "../../components/footer";
import Navbar from "../../components/navbar";
import {DatePickerComponent} from "@syncfusion/ej2-react-calendars/src/datepicker/datepicker.component";
import {confirmPayment} from "../../redux/actions/orderConfirmationActions";

const PaymentPage = () => {
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const order = useSelector(state => state.order.orderData)
    const [submitClicked, setSubmitClicked] = useState(false);
    const [electronic, setElectronic] = useState(false);

    const schema = Yup.object().shape({
        paymentProviderName: Yup.string()
            .max(100, 'Payment provider name should contain maximum 100 characters.')
            .required('Payment provider name is required field'),
        cardNumber: Yup.string()
            .max(50, 'Card number should contain maximum 30 characters.')
            .required('Card number is required field'),
        cardCode: Yup.string()
            .max(50, 'Card code should contain maximum 30 characters.')
            .required('Card code is required field'),
        validityPeriod: Yup.string()
            .required('Validity period is required field'),
    });

    const changeSubmit = () => {
        setSubmitClicked(true);
    };

    return (
        <Formik
            initialValues={{
                paymentProviderName: '',
                cardNumber: '',
                cardCode: '',
                validityPeriod: '',
            }}
            enableReinitialize={true}
            validationSchema={schema}
            validateOnChange={false}
            validateOnBlur={false}
            onSubmit={(values) => {
                if (electronic) {
                    confirmPayment(order.orderId, values.paymentProviderName, values.cardNumber, values.validityPeriod, values.cardCode)(dispatch).then(() => {
                        navigate('/');
                    })
                }
            }}
        >
            {({
                  handleSubmit,
                  handleChange,
                  errors,
              }) => (
                <>
                    <Navbar/>
                    <Container className="personal-space-form-container">
                        <Col md={6} className="m-auto mt-5 full-width d-flex justify-content-center">
                            <Form id="sign-in-form" className="m-5 p-5 rounded w-75" noValidate onSubmit={handleSubmit}>
                                <div className="text-center">
                                    <h1 className="fs-0 mb-4">Payment type</h1>
                                    <div className="row">
                                        <RadioGroup>
                                            <div className="col-xs-12 col-sm-6">
                                                <RadioButton value="PHYSICAL" defaultChecked
                                                             onChange={() => setElectronic(false)}>
                                                    Physical payment to courier</RadioButton>
                                                <RadioButton value="ELECTRONIC"
                                                             onChange={() => setElectronic(true)}>
                                                    Online payment</RadioButton>
                                            </div>
                                        </RadioGroup>
                                    </div>
                                    <h5>Total price is {order.totalPrice}</h5>
                                    {electronic && (
                                        <>
                                            <Form.Group className="p-4 pt-2" controlId="payment-provider-name">
                                                <Form.Label>Payment provider name</Form.Label>
                                                <Form.Control type="textarea" name="paymentProviderName"
                                                              placeholder="Enter payment provider name"
                                                              onChange={handleChange}
                                                              isInvalid={!!errors.paymentProviderName}/>
                                                <Form.Control.Feedback type="invalid">
                                                    {errors.paymentProviderName}
                                                </Form.Control.Feedback>
                                            </Form.Group>
                                            <Form.Group className="p-4 pt-2" controlId="card-number">
                                                <Form.Label>Email</Form.Label>
                                                <Form.Control type="textarea" name="cardNumber"
                                                              placeholder="Enter card number"
                                                              onChange={handleChange} isInvalid={!!errors.cardNumber}/>
                                                <Form.Control.Feedback type="invalid">
                                                    {errors.cardNumber}
                                                </Form.Control.Feedback>
                                            </Form.Group>
                                            <Form.Group className="p-4 pt-2" controlId="validity-period">
                                                <Form.Label>Validity period</Form.Label>
                                                <DatePickerComponent placeholder="Enter validity period"
                                                                     name="validityPeriod"
                                                                     required={true}
                                                                     isInvalid={!!errors.validityPeriod}
                                                                     onChange={handleChange}/>
                                                <Form.Control.Feedback type="invalid">
                                                    {errors.validityPeriod}
                                                </Form.Control.Feedback>
                                            </Form.Group>
                                            <Form.Group className="p-4 pt-2" controlId="card-code">
                                                <Form.Label>Card code</Form.Label>
                                                <Form.Control type="textarea" name="cardCode"
                                                              placeholder="Enter card code"
                                                              onChange={handleChange} isInvalid={!!errors.cardCode}/>
                                                <Form.Control.Feedback type="invalid">
                                                    {errors.cardCode}
                                                </Form.Control.Feedback>
                                            </Form.Group>
                                        </>
                                    )}
                                    <div className="mt-3 text-center ">
                                        <Button type="submit" variant="danger" size="lg"
                                                onClick={changeSubmit}>Confirm</Button>
                                    </div>
                                </div>
                            </Form>
                        </Col>
                    </Container>
                    <Footer/>
                </>
            )}

        </Formik>
    );
};

export default PaymentPage;