import {useDispatch, useSelector} from "react-redux";
import {useNavigate} from "react-router-dom";
import React, {useState} from "react";
import * as Yup from "yup";
import {Formik} from "formik";
import addDishService from "../../../services/addDishService"
import Navbar from "../../../components/navbar";
import {Button, Col, Container, Form, Row} from "react-bootstrap";
import Footer from "../../../components/footer";

const NewDishRegPage = () => {
    const managerEmail = useSelector(state => state.auth.authData.user.email);

    const navigate = useNavigate();
    const dispatch = useDispatch();
    const [submitClicked, setSubmitClicked] = useState(false);
    const [image, setImage] = useState(null);

    const SUPPORTED_FORMATS = ['image/jpg', 'image/jpeg', 'image/png'];

    const changeSubmit = () => {
        console.log(image);
        setSubmitClicked(true);
    };

    const schema = Yup.object().shape({
        dishName: Yup.string()
            .min(2, 'Dish name should contain minimum 2 characters.')
            .max(100, 'Dish name should contain maximum 100 characters.')
            .required("Name is required"),
        description: Yup.string()
            .min(2, 'Description should contain minimum 2 characters.')
            .max(200, 'Description should contain maximum 200 characters'),
        price: Yup.number()
            .required("Price is required"),
        type: Yup.string()
            .min(2, 'Dish type should contain minimum 2 characters.')
            .max(100, 'Dish type should contain maximum 100 characters.')
            .required("Dish type is required"),
        feature: Yup.string()
            .min(2, 'Feature should contain minimum 2 characters.')
            .max(100, 'Feature should contain maximum 100 characters'),
        ingredientName: Yup.string()
            .min(2, 'Ingredient name should contain minimum 2 characters.')
            .max(100, 'Ingredient name should contain maximum 100 characters')
            .required("Ingredient name  is required"),
        ingredientAmount: Yup.number()
            .required("Ingredient amount is required"),
        ingredientUnit: Yup.string()
            .min(2, 'Ingredient unit should contain minimum 2 characters.')
            .max(20, 'Ingredient unit should contain maximum 20 characters.')
            .required("Ingredient unit is required"),
    });

    return (
        <Formik
            initialValues={{
            }}
            enableReinitialize={true}
            validationSchema={schema}
            validateOnChange={false}
            validateOnBlur={false}
            onSubmit={(values) => {
                addDishService.addNewDish(values.dishName, values.description, values.price, 0,
                    values.type, values.feature, image, [{name: values.ingredientName,
                        amount: values.ingredientAmount, unit: values.ingredientUnit}], managerEmail).then(() => {
                    navigate('/restaurant-manager');
                });
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
                            <Form encType="multipart/form-data" id="sign-in-form" className="m-5 p-5 rounded w-75" noValidate onSubmit={handleSubmit}>
                                <div className="text-center">
                                    <h1 className="fs-0 mb-4">Add new dish</h1>
                                </div>
                                <Form.Group className="p-4 pt-2" controlId="modify-restaurant-name">
                                    <Form.Label>Dish name</Form.Label>
                                    <Form.Control type="textarea" name="dishName"
                                                  placeholder="Enter dish name" value={values.dishName}
                                                  onChange={handleChange} isInvalid={!!errors.dishName}/>
                                    <Form.Control.Feedback type="invalid">
                                        {errors.dishName}
                                    </Form.Control.Feedback>
                                </Form.Group>
                                <Form.Group className="p-4 pt-0" controlId="dish-description">
                                    <Form.Label>Description</Form.Label>
                                    <Form.Control type="textarea" name="description"
                                                  placeholder="Enter description" value={values.description}
                                                  onChange={handleChange} isInvalid={!!errors.description}/>
                                    <Form.Control.Feedback type="invalid">
                                        {errors.description}
                                    </Form.Control.Feedback>
                                </Form.Group>
                                <Form.Group className="p-4 pt-0" controlId="dish-price">
                                    <Form.Label>Price</Form.Label>
                                    <Form.Control type="textarea" name="price"
                                                  placeholder="Enter price" value={values.price}
                                                  onChange={handleChange} isInvalid={!!errors.price}/>
                                    <Form.Control.Feedback type="invalid">
                                        {errors.price}
                                    </Form.Control.Feedback>
                                </Form.Group>
                                <Form.Group className="p-4 pt-0" controlId="dish-type">
                                    <Form.Label>Type</Form.Label>
                                    <Form.Control type="textarea" name="type"
                                                  placeholder="Enter type" value={values.type}
                                                  onChange={handleChange} isInvalid={!!errors.type}/>
                                    <Form.Control.Feedback type="invalid">
                                        {errors.type}
                                    </Form.Control.Feedback>
                                </Form.Group>
                                <Form.Group className="p-4 pt-0" controlId="dish-feature">
                                    <Form.Label>Feature</Form.Label>
                                    <Form.Control type="textarea" name="feature"
                                                  placeholder="Enter feature" value={values.feature}
                                                  onChange={handleChange} isInvalid={!!errors.feature}/>
                                    <Form.Control.Feedback type="invalid">
                                        {errors.feature}
                                    </Form.Control.Feedback>
                                </Form.Group>
                                <Form.Group className="p-4 pt-0" controlId="dish-image">
                                    <Form.Label>Image</Form.Label>
                                    <Form.Control type="file" multiple name="image"
                                                  placeholder="Enter image" value={values.image}
                                                  onChange={(event) => {
                                                      setImage(event.currentTarget.files[0]);
                                                  }} isInvalid={!!errors.image}/>
                                    <Form.Control.Feedback type="invalid">
                                        {errors.image}
                                    </Form.Control.Feedback>
                                </Form.Group>
                                <Form.Group className="p-4 pt-0" controlId="dish-ingredient-name">
                                    <Form.Label>Ingredient name</Form.Label>
                                    <Form.Control type="textarea" name="ingredientName"
                                                  placeholder="Enter ingredient name" value={values.ingredientName}
                                                  onChange={handleChange} isInvalid={!!errors.ingredientName}/>
                                    <Form.Control.Feedback type="invalid">
                                        {errors.ingredientName}
                                    </Form.Control.Feedback>
                                </Form.Group>
                                <Form.Group className="p-4 pt-0" controlId="dish-ingredient-amount">
                                    <Form.Label>Ingredient amount</Form.Label>
                                    <Form.Control type="textarea" name="ingredientAmount"
                                                  placeholder="Enter ingredient amount" value={values.ingredientAmount}
                                                  onChange={handleChange} isInvalid={!!errors.ingredientAmount}/>
                                    <Form.Control.Feedback type="invalid">
                                        {errors.ingredientAmount}
                                    </Form.Control.Feedback>
                                </Form.Group>
                                <Form.Group className="p-4 pt-0" controlId="dish-ingredient-unit">
                                    <Form.Label>Ingredient unit</Form.Label>
                                    <Form.Control type="textarea" name="ingredientUnit"
                                                  placeholder="Enter ingredient unit" value={values.ingredientUnit}
                                                  onChange={handleChange} isInvalid={!!errors.ingredientUnit}/>
                                    <Form.Control.Feedback type="invalid">
                                        {errors.ingredientUnit}
                                    </Form.Control.Feedback>
                                </Form.Group>
                                <div className="mt-3 text-center ">
                                    <Button type="submit" variant="danger" size="lg" onClick={changeSubmit}>Add
                                        dish</Button>
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

export default NewDishRegPage;