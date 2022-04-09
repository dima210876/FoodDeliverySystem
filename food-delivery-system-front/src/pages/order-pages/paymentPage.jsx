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
    const [stateOfAlert, setStateOfAlert] = useState(false);
    const [electronic, setElectronic] = useState(false);
    const ELECTRONIC_PAYMENT = 'Electronic payment system';
    const PHYSICAL_PAYMENT = 'Physical payment system';

    const schema = Yup.object().shape({
        cardNumber: Yup.string()
            .max(50, 'Card number should contain maximum 30 characters.'),
        cardCode: Yup.string()
            .max(50, 'Card code should contain maximum 30 characters.')
    });

    return (
        <Formik
            initialValues={{
                cardNumber: '',
                cardCode: '',
                validityPeriod: '',
            }}
            enableReinitialize={true}
            validationSchema={schema}
            validateOnChange={false}
            validateOnBlur={false}
            onSubmit={(values) => {
                let paymentProvider = '';
                if (electronic) {
                    paymentProvider = ELECTRONIC_PAYMENT;
                } else {
                    paymentProvider = PHYSICAL_PAYMENT;
                }

                confirmPayment(order.orderId, paymentProvider, values.cardNumber, values.validityPeriod, values.cardCode)(dispatch).then(() => {
                    alert("Your order is confirmed");
                    navigate('/')
                }).catch((error) => {
                    setStateOfAlert(true);
                    setTimeout(() => {
                        setStateOfAlert(false);
                    }, 3000)
                });
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
                        {stateOfAlert ?
                            <div className="alert alert-danger" role='alert'>Payment is not approved</div> : null}
                        <Col md={6} className="m-auto mt-5 full-width d-flex justify-content-center">
                            <Form id="sign-in-form" className="m-5 p-5 rounded w-75" noValidate onSubmit={handleSubmit}>
                                <div className="text-center">
                                    <h1 className="fs-0 mb-4">Payment type</h1>
                                    <div className="row">
                                        <RadioGroup>
                                            <div className="col-xs-12 col-sm-6">
                                                <RadioButton value="PHYSICAL" name="PHYSICAL" defaultChecked
                                                             onChange={() => setElectronic(false)}>
                                                    Physical payment to courier</RadioButton>
                                                <RadioButton value="ELECTRONIC" name="ELECTRONIC"
                                                             onChange={() => setElectronic(true)}>
                                                    Online payment</RadioButton>
                                            </div>
                                        </RadioGroup>
                                    </div>
                                    <h5>Total price is {order.totalPrice}</h5>
                                    {electronic && (
                                        <>
                                            <Form.Group className="p-4 pt-2" controlId="card-number">
                                                <Form.Label>Card number</Form.Label>
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
                                        <Button type="submit" variant="danger" size="lg">Confirm</Button>
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