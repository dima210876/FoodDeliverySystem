import React, {useState} from 'react';
import {Link, useNavigate} from "react-router-dom";
import {useDispatch, useSelector} from "react-redux";
import Navbar from "../../../components/navbar";
import Footer from "../../../components/footer";
import {Button, Form, Modal, Table} from "react-bootstrap";
import {
    changeCourierOrderStatus,
    changeOrderStatus,
    getCourierOrders,
    getRestaurantOrders
} from "../../../redux/actions/orderActions";

const CourierOrderList = () => {
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const orders = useSelector(state => state.userData.courierOrders);
    const courierId = useSelector(state => state.userData.courierData.userId);
    const [newStatus, setNewStatus] = useState('');
    const [idOrder, setIdOrder] = useState('');
    const [showModal, setShowModal] = useState(false);

    function handleShow() {
        setShowModal(true);
    }

    function handleClose() {
        setShowModal(false);
    }

    const handleSelectChange = (e) => {
        setNewStatus(e.target.value);
    }

    function changeStatus() {
        changeCourierOrderStatus(idOrder, newStatus)(dispatch).then(() => {
            getCourierOrders(courierId)(dispatch).then(() => {
                navigate("/courier/orders");
                handleClose();
            }).catch((error) => {
                handleClose();
            });
        });
    }

    const OrdersData = orders.map(
        (order) => {
            if (order.orderStatus.toUpperCase() === 'DELIVERING') {
                return (
                    <tr>
                        <td>{order.id}</td>
                        <td>
                            {order.restaurantAddresses.map(
                                (restaurantAddress) => {
                                    return (
                                        <p> {restaurantAddress}<br/></p>
                                    )
                                })
                            }
                        </td>
                        <td>{order.deliveryAddress}</td>
                        <td>{order.orderStatus[0].toUpperCase() + order.orderStatus.substring(1).toLowerCase()}</td>
                        <td>
                            <Button variant="secondary" onClick={() => {
                                handleShow();
                                setIdOrder(order.id);
                            }}>
                                Change order status
                            </Button>
                        </td>
                    </tr>
                )
            } else {
                return (
                    <tr>
                        <td>{order.id}</td>
                        <td>
                            {order.restaurantAddresses.map(
                                (restaurantAddress) => {
                                    return (
                                        <p> {restaurantAddress}<br/></p>
                                    )
                                })
                            }
                        </td>
                        <td>{order.deliveryAddress}</td>
                        <td>{order.orderStatus[0].toUpperCase() + order.orderStatus.substring(1).toLowerCase()}</td>
                        <td>Order is finished</td>
                    </tr>
                )
            }

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
                        <th>Restaurant Address</th>
                        <th>Delivery Address</th>
                        <th>Order status</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    {OrdersData}
                    </tbody>
                </Table>
            </div>

            <Modal show={showModal} onHide={() => handleClose()}>
                <Modal.Header closeButton>
                    <Modal.Title>Changing status of an order #{idOrder}</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form.Select onChange={handleSelectChange}>
                        <option>Choose new status</option>
                        <option value="DELIVERING">Delivering</option>
                        <option value="DELIVERED">Delivered</option>
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

            <div className='link-back-div'>
                <Link className='link' to='/courier'><h5>Back to personal space</h5></Link>
            </div>
            <Footer/>
        </>
    );
};

export default CourierOrderList;