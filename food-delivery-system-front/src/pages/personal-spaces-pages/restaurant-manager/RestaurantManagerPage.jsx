import {Link} from "react-router-dom";
import React from 'react';
import {useSelector} from "react-redux";
import Navbar from "../../../components/Navbar";
import './RestaurantManagerPage.css';

const RestaurantManagerPage = () => {
    const restaurantManager = useSelector(state => state.userData.restaurantManagerData.manager);

    return (
        <>
            <Navbar/>
            <div className='box'>
                <div className='restaurant-manager-div'>
                    <img className='manager-image' src='restaurant-manager.png'/>
                </div>
            <div className='managers-space'>
                <div className='title'>
                    <h1>Restaurant manager's space</h1>
                </div>
                <div className='info'>
                    <div className='managers-block'>
                        <h4>Manager's info</h4>
                        <div className='user-info'>
                            <h6>First name: {restaurantManager.firstName ? restaurantManager.firstName : " – "} </h6>
                            <h6>Last name: {restaurantManager.lastName ? restaurantManager.lastName : " – "}</h6>
                            <h6>Email: {restaurantManager.email ? restaurantManager.email : " – "}</h6>
                            <h6>Phone number: {restaurantManager.phoneNumber ? restaurantManager.phoneNumber : " – "} </h6>
                        </div>
                    </div>
                    <div className='restaurant-block'>
                        <h4>Restaurant's info</h4>
                        <div className='restaurant-info'>
                            <h6>Restaurant name:  {restaurantManager.restaurant.name ? restaurantManager.restaurant.name : " – "}</h6>
                            <h6>Description:  {restaurantManager.restaurant.description ? restaurantManager.restaurant.description : " – "}</h6>
                            <h6>Address:  {restaurantManager.restaurant.address ? restaurantManager.restaurant.address : " – "}</h6>
                            <h6>Phone number:  {restaurantManager.restaurant.phoneNumber ? restaurantManager.restaurant.phoneNumber : " – "}</h6>
                        </div>
                        <div className='link-div'>
                            <Link className='link' to='/restaurant-manager/modify-restaurant-info'><h5>Modify</h5></Link>
                        </div>
                    </div>
                </div>
            </div>
            </div>
        </>
    );
};

export default RestaurantManagerPage;