import React, {useEffect, useState} from 'react';
import {Button, Col, Container, Form} from 'react-bootstrap';
import {Formik} from 'formik';
import * as Yup from "yup";
import Footer from "../../components/footer";
import Navbar from "../../components/navbar";
import {useDispatch, useSelector} from "react-redux";
import SelectedItems from "../../components/selectedItems";
import TotalCount from "../../components/totalCount";
import {geocodeByAddress} from "react-places-autocomplete";
import AddressInputField from "../../components/inputFields/addressInputField";
import {confirmOrderDetails} from "../../redux/actions/orderConfirmationActions";
import {useNavigate} from "react-router-dom";

const OrderConfirmationPage = () => {
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const userId = useSelector(state => state.auth.authData.user.user_id);
    let totalPrice = useSelector(state => state.cart.totalPrice);
    const items = useSelector(state => state.cart.items);
    const [stateOfAlert, setStateOfAlert] = useState(false);
    const [address, setAddress] = useState('');
    const [coordinates, setCoordinates] = useState({
        lat: 0,
        lng: 0
    });
    const [submitClicked, setSubmitClicked] = useState( false);
    const schema = Yup.object().shape({});

    const countShippingPrice = () => {
        if (totalPrice > 50) {
            return 0;
        } else {
            return 5;
        }
    };
    const [shippingPrice, setShippingPrice] = useState(countShippingPrice());

    useEffect(() => {
        setShippingPrice(countShippingPrice);
    }, [totalPrice]);


    const changeSubmit = () => {
        setSubmitClicked(true);
    };

    return (
        <Formik
            initialValues={{}}
            enableReinitialize={true}
            validationSchema={schema}
            validateOnChange={false}
            validateOnBlur={false}
            onSubmit={(values) => {
                geocodeByAddress(address).then(() => {
                    const totalPrice = JSON.parse(localStorage.getItem('TOTAL_PRICE'));
                    if (address !== "") {
                        confirmOrderDetails(userId, items, address, coordinates.lat, coordinates.lng, totalPrice.price.toFixed(2),
                            shippingPrice)
                        (dispatch).then(() => {
                            navigate('/payment');
                        }).catch((error) => {
                            setStateOfAlert(true);
                            setTimeout(() => {
                                setStateOfAlert(false);
                            }, 3000)
                        });
                    }
                })
            }}
        >
            {({
                  handleSubmit
              }) => (
                <>
                    <Navbar/>
                    <Container className="personal-space-form-container">
                        {stateOfAlert ?
                            <div className="alert alert-danger" role='alert'>Couldn't confirm your order</div> : null}
                        <Col md={8} className="m-auto mt-5 full-width d-flex justify-content-center">
                            <Form id="sign-in-form" className="m-5 p-5 rounded w-75" noValidate onSubmit={handleSubmit}>
                                <div className="text-center">
                                    <h1 className="fs-0 mb-4">Confirm an order</h1>
                                </div>
                                <div>
                                    <SelectedItems/>
                                    <TotalCount/>
                                </div>
                                <h6>Shipping price is {shippingPrice}$</h6>
                                <div className="mt-3 text-center ">
                                    <AddressInputField address={address} changeAddress={setAddress}
                                                       changeCoordinates={setCoordinates}
                                                       submitClicked={submitClicked}/>
                                </div>
                                <div className="mt-3 text-center ">
                                    <Button type="submit" variant="danger" size="lg"
                                            onClick={changeSubmit}>Confirm</Button>
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

export default OrderConfirmationPage;