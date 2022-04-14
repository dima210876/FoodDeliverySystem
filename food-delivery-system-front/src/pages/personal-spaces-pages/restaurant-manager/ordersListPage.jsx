import {useDispatch, useSelector} from "react-redux";
import {Link, useNavigate} from "react-router-dom";
import React, {useState} from "react";
import Navbar from "../../../components/navbar";
import Footer from "../../../components/footer";
import {Form, Button, Modal, Table} from "react-bootstrap";
import {changeOrderStatus, getRestaurantOrders} from "../../../redux/actions/orderActions";

const OrdersListPage = () => {
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const orders = useSelector(state => state.userData.restaurantOrders);
    const restaurantId = useSelector(state => state.userData.restaurantManagerData.restaurant.id);
    const [showModal, setShowModal] = useState(false);

    const [idOrder, setIdOrder] = useState('');
    const [newStatus, setNewStatus] = useState('');

    function handleClose() {
        setShowModal(false);
    }
    function handleShow() {
        setShowModal(true);
    }
    const handleSelectChange = (e) => {
        setNewStatus(e.target.value);
    }

    function changeStatus() {
        changeOrderStatus(idOrder, newStatus)(dispatch).then(() => {
            getRestaurantOrders(restaurantId)(dispatch).then(() => {
                navigate("/restaurant-manager/orders");
                handleClose();
            }).catch((error) => {
                handleClose();
            });
        });
    }

    const OrdersData = orders.map(
        (restaurant) => {
            return (
                <tr>
                    <td>{restaurant.id}</td>
                    <td>
                        {restaurant.itemsInOrders.map(
                            (itemInOrder) => {
                                return (
                                    <p> {itemInOrder.item.name} - {itemInOrder.amount}<br/></p>
                                )
                            })
                        }
                        </td>
                    <td>{restaurant.restaurantStatus[0].toUpperCase() + restaurant.restaurantStatus.substring(1).toLowerCase()}</td>
                    <td>
                        <Button variant="secondary" onClick={() => {
                            handleShow();
                            setIdOrder(restaurant.id);
                        }}>
                            Change order status
                        </Button>
                    </td>
                </tr>
            )
        }
    )

    return (
        <>
            <Navbar/>
            <div className='box'>
                <Table striped bordered hover>
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Order items</th>
                        <th>Order status</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    {OrdersData}
                    </tbody>
                </Table>
            </div>
            <div className='link-back-div'>
                <Link className='link' to='/restaurant-manager'><h5>Back to personal space</h5></Link>
            </div>

            <Modal show={showModal} onHide={() => handleClose()}>
                <Modal.Header closeButton>
                    <Modal.Title>Changing status of an order #{idOrder}</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form.Select onChange={handleSelectChange}>
                        <option>Choose new status</option>
                        <option value="VERIFICATION">Verification</option>
                        <option value="COOKING">Cooking</option>
                        <option value="READY">Ready</option>
                    </Form.Select>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={() => handleClose()}>
                        Close
                    </Button>
                    <Button variant="danger" onClick={() => changeStatus()}>
                        Save Changes
                    </Button>
                </Modal.Footer>
            </Modal>
            <Footer/>
        </>

    );
};

export default OrdersListPage;