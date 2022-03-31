import 'bootstrap/dist/css/bootstrap.css'
import { FaCartArrowDown } from "react-icons/fa";
import { MdAccountCircle } from "react-icons/md";
import Drawer from './drawer';
import React from 'react';
import PersonalAccountCard from "./personalAccountCard";
import {Link} from "react-router-dom";

function Navbar(){

    const[cartOpened, setCartOpened] = React.useState(false);
    const[personalCard, setPersonalCard] = React.useState(false);

    const changeStateOfCart = () => {
        setCartOpened(!cartOpened);
        setPersonalCard(false);
    }

    const switchPersonalCardState = () => {
        setPersonalCard(!personalCard)
        setCartOpened(false);
    }

    return(
        <div className="navbar navbar-inverse fixed-top header">
            {personalCard ? <PersonalAccountCard /> : null}
            {cartOpened ? <Drawer /> : null}
            <div className="container-fluid ">
                <Link className="navbar-brand nav-link-style " to="/"><b>Food<br/>Delivery</b></Link>
                <div className="row">
                    <Link className="navbar-brand nav-link-style col" to="/">Main page</Link>
                </div>
                <div className="row">
                    <Link className='navbar-brand nav-link-style col' to="/restaurant">Restaurant</Link>
                </div>
                <div className="row">
                    <Link className="navbar-brand nav-link-style col" to="/order">Order</Link>
                </div>
                <div className="row">
                    <button className="navbar-brand navbar-right col  btn-on-navbar" onClick={() => switchPersonalCardState()}><MdAccountCircle className='icons'/></button>
                    <button className="navbar-brand col btn-on-navbar"  onClick={() => changeStateOfCart()}><FaCartArrowDown className='icons'/></button>
                </div>
            </div>
        </div>
    );
}

export default Navbar;