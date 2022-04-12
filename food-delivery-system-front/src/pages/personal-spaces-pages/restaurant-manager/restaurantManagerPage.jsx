import {Link} from "react-router-dom";
import React from 'react';
import {useSelector} from "react-redux";
import Navbar from "../../../components/navbar";
import './restaurantManagerPage.css';

const RestaurantManagerPage = () => {
    const restaurantManagerData = useSelector(state => state.userData.restaurantManagerData);

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
                            <h6>First name: {restaurantManagerData.firstName ? restaurantManagerData.firstName : " – "} </h6>
                            <h6>Last name: {restaurantManagerData.lastName ? restaurantManagerData.lastName : " – "}</h6>
                            <h6>Email: {restaurantManagerData.email ? restaurantManagerData.email : " – "}</h6>
                            <h6>Phone number: {restaurantManagerData.phoneNumber ? restaurantManagerData.phoneNumber : " – "} </h6>
                        </div>
                        <div className='link-div'>
                            <Link className='link' to='/restaurant-manager/modify-restaurant-manager-info'><h5>Modify</h5></Link>
                        </div>
                    </div>
                    <div className='restaurant-block'>
                        <h4>Restaurant's info</h4>
                        <div className='restaurant-info'>
                            <h6>Restaurant name:  {restaurantManagerData.restaurant.name ? restaurantManagerData.restaurant.name : " – "}</h6>
                            <h6>Description:  {restaurantManagerData.restaurant.description ? restaurantManagerData.restaurant.description : " – "}</h6>
                            <h6>Address:  {restaurantManagerData.restaurant.restaurantAddress ? restaurantManagerData.restaurant.restaurantAddress : " – "}</h6>
                            <h6>Phone number:  {restaurantManagerData.restaurant.phoneNumber ? restaurantManagerData.restaurant.phoneNumber : " – "}</h6>
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