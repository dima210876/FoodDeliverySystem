import React, {useEffect, useState} from 'react';
import {Button, Col, Container, Form, Row} from 'react-bootstrap';
import {Formik} from 'formik';
import * as Yup from "yup";
import './restaurantManagerPage.css';

import Navbar from "../../../components/navbar";
import {TimePickerComponent} from "@syncfusion/ej2-react-calendars";

import {useDispatch, useSelector} from "react-redux";
import {geocodeByAddress} from "react-places-autocomplete";
import {isValidPhoneNumber} from "react-phone-number-input";
import * as changeInfoActions from "../../../redux/actions/changeInfoActions";
import AddressInputField from "../../../components/inputFields/addressInputField";
import PhoneInputField from "../../../components/inputFields/phoneInputField";
import Footer from "../../../components/footer";
import {useNavigate} from "react-router-dom";

const ModifyRestaurantInfoPage = () => {
    const restaurant = useSelector(state => state.userData.restaurantManagerData.restaurant);
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const [phone, setPhone] = useState(restaurant.phoneNumber);
    const [address, setAddress] = useState(restaurant.address);
    const [stateOfAlert, setStateOfAlert] = useState(false);
    const [coordinates, setCoordinates] = useState({
        lat: restaurant.latitude,
        lng: restaurant.longitude
    });
    const [submitClicked, setSubmitClicked] = useState(false);
    const [restaurantTypes, setRestaurantTypes] = useState(restaurant.restaurantTypes);
    const [workingTime, setWorkingTime] = useState(restaurant.workingTime);

    const changeSubmit = () => {
        setSubmitClicked(true);
    };

    const schema = Yup.object().shape({
        restaurantName: Yup.string()
            .min(2, 'First name should contain minimum 2 characters.')
            .max(30, 'First name should contain maximum 30 characters.'),
        description: Yup.string()
            .max(200, 'Description should contain maximum 200 characters')
    });

    const handleRemove = (id) => () => {
        setRestaurantTypes([...restaurantTypes.slice(0, id), ...restaurantTypes.slice(id + 1)]);
    };

    const handleAdd = () => {
        setRestaurantTypes([...restaurantTypes, '']);
    };

    function renderDay(day) {
        switch (day) {
            case '1':
                return 'Monday';
            case '2':
                return 'Tuesday';
            case '3':
                return 'Wednesday';
            case '4':
                return 'Thursday';
            case '5':
                return 'Friday';
            case '6':
                return 'Saturday';
            case '7':
                return 'Sunday';
        }
    }

    return (
        <Formik
            initialValues={{
                restaurantName: restaurant.name,
                description: restaurant.description,
                workingTime: workingTime,
                setWorkingTime: setWorkingTime,
                restaurantTypes: restaurantTypes,
                setRestaurantTypes: setRestaurantTypes,
            }}
            enableReinitialize={true}
            validationSchema={schema}
            validateOnChange={false}
            validateOnBlur={false}
            onSubmit={(values) => {
                geocodeByAddress(address).then(() => {
                    if (phone === "" || isValidPhoneNumber(phone)) {
                        changeInfoActions.changeRestaurantInfo(restaurant.restaurantId, values.restaurantName, values.description, phone, address, coordinates.lat, coordinates.lng,
                            values.workingTime, values.restaurantTypes)(dispatch).then(() => {
                            navigate('/restaurant-manager');
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
                  handleSubmit,
                  handleChange,
                  errors,
                  values
              }) => (
                <>
                    <Navbar/>
                    <Container className="personal-space-form-container">
                        {stateOfAlert ? <div className="alert alert-danger" role='alert'>Couldn't edit restaurant info</div> : null}
                        <Col md={6} className="m-auto mt-5 full-width d-flex justify-content-center">
                            <Form id="sign-in-form" className="m-5 p-5 rounded w-75" noValidate onSubmit={handleSubmit}>
                                <div className="text-center">
                                    <h1 className="fs-0 mb-4">Modify restaurant info</h1>
                                </div>
                                <Form.Group className="p-4 pt-2" controlId="modify-restaurant-name">
                                    <Form.Label>Restaurant name</Form.Label>
                                    <Form.Control type="textarea" name="restaurantName"
                                                  placeholder="Enter restaurant name" value={values.restaurantName}
                                                  onChange={handleChange} isInvalid={!!errors.restaurantName}/>
                                    <Form.Control.Feedback type="invalid">
                                        {errors.restaurantName}
                                    </Form.Control.Feedback>
                                </Form.Group>
                                <Form.Group className="p-4 pt-0" controlId="modify-description">
                                    <Form.Label>Description</Form.Label>
                                    <Form.Control type="textarea" name="description"
                                                  placeholder="Enter description" value={values.description}
                                                  onChange={handleChange} isInvalid={!!errors.description}/>
                                    <Form.Control.Feedback type="invalid">
                                        {errors.description}
                                    </Form.Control.Feedback>
                                </Form.Group>
                                <AddressInputField address={address} changeAddress={setAddress}
                                                   changeCoordinates={setCoordinates} submitClicked={submitClicked}/>
                                <PhoneInputField phone={phone} changePhone={setPhone} submitClicked={submitClicked}
                                                 required={false}/>
                                <Form.Group className="p-4 pt-0" controlId="modify-working-time">
                                    <Form.Label>Working time</Form.Label>
                                    <>
                                        {values.workingTime.map((item, id) => (
                                            <div key={item.id}>
                                                <Row>
                                                    <Col className="small col-4"
                                                         key={item.dayOfWeek}>{renderDay(item.dayOfWeek)}</Col>
                                                </Row>
                                                <TimePickerComponent placeholder="opening time"
                                                                     name="openingTime"
                                                                     format="HH:mm"
                                                                     step={15}
                                                                     defaultValue={item.openingTime}
                                                                     onChange={event => setWorkingTime([...workingTime.slice(0, id),
                                                                         {
                                                                             dayOfWeek: item.dayOfWeek,
                                                                             openingTime: event.target.value,
                                                                             closingTime: item.closingTime
                                                                         },
                                                                         ...workingTime.slice(id + 1)])}/>
                                                <TimePickerComponent placeholder="closing time"
                                                                     name="closingTime"
                                                                     format="HH:mm"
                                                                     step={15}
                                                                     defaultValue={item.closingTime}
                                                                     onChange={event => setWorkingTime([...workingTime.slice(0, id),
                                                                         {
                                                                             dayOfWeek: item.dayOfWeek,
                                                                             openingTime: item.openingTime,
                                                                             closingTime: event.target.value
                                                                         },
                                                                         ...workingTime.slice(id + 1)])}/>
                                            </div>
                                        ))}
                                    </>
                                </Form.Group>
                                <Form.Group className="p-4 pt-0" controlId="modify-restaurant-types"
                                            id="restaurant-types">
                                    <Form.Label>Restaurant Types</Form.Label>
                                    {values.restaurantTypes.map((type, id) => (
                                        <div className="restaurantType" key={type.id}>
                                            <Form.Control type="textarea" name="restaurantType"
                                                          placeholder="Enter type"
                                                          defaultValue={type}
                                                          onChange={event => setRestaurantTypes([...restaurantTypes.slice(0, id),
                                                              event.target.value, ...restaurantTypes.slice(id + 1)])}>
                                            </Form.Control>
                                            <Button type="button" variant="light"
                                                    onClick={handleRemove(id)}
                                                    className="small">
                                                -
                                            </Button>
                                        </div>
                                    ))}
                                    <div className="mt-3 text-center ">
                                        <Button type="button" variant="light" onClick={handleAdd} className="small">
                                            Add restaurant Type
                                        </Button>
                                    </div>
                                </Form.Group>
                                <div className="mt-3 text-center ">
                                    <Button type="submit" variant="danger" size="lg" onClick={changeSubmit}>Modify
                                        info</Button>
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

export default ModifyRestaurantInfoPage;