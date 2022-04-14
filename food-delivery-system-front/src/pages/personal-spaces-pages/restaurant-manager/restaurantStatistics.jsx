import {useDispatch, useSelector} from "react-redux";
import {Link, useNavigate} from "react-router-dom";
import React, {useState} from "react";
import Navbar from "../../../components/navbar";
import Footer from "../../../components/footer";
import {Form, Button, Modal, Table} from "react-bootstrap";
import {changeOrderStatus, getRestaurantOrders} from "../../../redux/actions/orderActions";
import LineChart from "../../../components/personalSpaceComponents/lineChart";

const RstaurantStatisticsPage = () => {
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const orders = useSelector(state => state.userData.restaurantOrders);
    const restaurantId = useSelector(state => state.userData.restaurantManagerData.restaurant.restaurantId);

    return (
        <>
            <Navbar/>
            <div className='box-table'>
                <div className='title'>
                    <h1>Restaurant statistics</h1>
                </div>
                <div className="chart">
                    <LineChart />
                </div>
            </div>
            <div className='link-back-div'>
                <Link className='link' to='/restaurant-manager'><h5>Back to personal space</h5></Link>
            </div>
            <Footer/>
        </>
    );
};

export default RstaurantStatisticsPage;