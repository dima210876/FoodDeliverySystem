import 'bootstrap/dist/css/bootstrap.css'
import { FaCartArrowDown } from "react-icons/fa";
import { MdAccountCircle } from "react-icons/md";
import Drawer from '../components/Drawer';
import React from 'react';
import {Link} from 'react-router-dom'

function Navbar(){

    const[cartOpened, setCartOpened] = React.useState(false);

    return(
        <div className="navbar navbar-inverse fixed-top header">
            {cartOpened ? <Drawer /> : null}
            <div className="container-fluid ">
                <Link className="navbar-brand nav-link-style " to="/main"><b>Food<br/>Delivery</b></Link>
                <div className="row">
                    <Link className="navbar-brand nav-link-style col" to="/main">Main page</Link>
                </div>
                <div className="row">
                    <Link className='navbar-brand nav-link-style col' to="/restaurant">Restaurant</Link>
                </div>
                <div className="row">
                    <Link className="navbar-brand nav-link-style col" to="/order">Order</Link>
                </div>
                <div className="row">
                    <button className="navbar-brand navbar-right col  btn-on-navbar"><MdAccountCircle className='icons'/></button>
                    <button className="navbar-brand col btn-on-navbar"  onClick={() => setCartOpened(!cartOpened)}><FaCartArrowDown className='icons'/></button>
                </div>
            </div>
        </div>
    );
}

export default Navbar;