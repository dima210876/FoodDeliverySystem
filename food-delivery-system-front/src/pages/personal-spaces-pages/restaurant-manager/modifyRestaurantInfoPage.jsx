import React, {useState} from 'react';
import {Button, Col, Container, Form, Row} from 'react-bootstrap';
import {Formik} from 'formik';
import * as Yup from "yup";
import './restaurantManagerPage.css';

import Navbar from "../../../components/navbar";
import {TimePickerComponent} from "@syncfusion/ej2-react-calendars";

import {useSelector} from "react-redux";
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
    const [phone, setPhone] = useState(restaurant.phoneNumber);
    const [address, setAddress] = useState(restaurant.address);
    const [coordinates, setCoordinates] = useState({
        lat: restaurant.latitude,
        lng: restaurant.longitude
    });
    const [submitClicked, setSubmitClicked] = useState(false);
    const [restaurantTypes, setRestaurantTypes] = useState(restaurant.restaurantTypes);

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
        setRestaurantTypes(restaurantTypes.filter((current, idCurrent) => idCurrent !== id));
    };

    const handleAdd = () => {
        setRestaurantTypes([...restaurantTypes, '']);
    };

    return (
        <Formik
            initialValues={{
                restaurantName: restaurant.name,
                description: restaurant.description,
                workingTime: restaurant.workingTime,
            }}
            enableReinitialize={true}
            validationSchema={schema}
            validateOnChange={false}
            validateOnBlur={false}
            onSubmit={(values) => {
                geocodeByAddress(address).then(() => {
                    if (phone === "" || isValidPhoneNumber(phone)) {
                        changeInfoActions.changeRestaurantInfo(restaurant.restaurantId, values.restaurantName, values.description, phone, address, coordinates.lat, coordinates.lng,
                            restaurant.workingTime, restaurant.restaurantTypes).then(() => {
                            navigate('/restaurant-manager');
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
                                        {values.workingTime.map(item => (
                                            <div key={item.id}>
                                                <Row>
                                                    <Col className="small col-4"
                                                         key={item.dayOfWeek}>{item.dayName}</Col>
                                                </Row>
                                                <TimePickerComponent placeholder="opening time"
                                                                     name="openingTime"
                                                                     format="HH:mm"
                                                                     step={15}
                                                                     value={item.openingTime}
                                                                     onChange={handleChange}/>
                                                <TimePickerComponent placeholder="closing time"
                                                                     name="closingTime"
                                                                     format="HH:mm"
                                                                     step={15}
                                                                     value={item.closingTime}
                                                                     onChange={handleChange}/>
                                            </div>
                                        ))}
                                    </>
                                </Form.Group>
                                <Form.Group className="p-4 pt-0" controlId="modify-restaurant-types">
                                    <Form.Label>Restaurant Types</Form.Label>
                                    {restaurantTypes.map((type, id) => (
                                        <div className="restaurantType" key={type.id}>
                                            <Form.Control type="textarea" name="restaurantType"
                                                          placeholder="Enter type" value={type}
                                                          onChange={handleChange} isInvalid={!!errors.restaurantTypes}/>
                                            <Form.Control.Feedback type="invalid">
                                                {errors.restaurantTypes}
                                            </Form.Control.Feedback>
                                            <Button type="button" variant="light" onClick={handleRemove(id)}
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