import 'bootstrap/dist/css/bootstrap.css'
import { FaCartArrowDown } from "react-icons/fa";
import { MdAccountCircle } from "react-icons/md";
import Drawer from '../components/Drawer';
import React from 'react';

function Navbar(){

    const[cartOpened, setCartOpened] = React.useState(false);

    return(
        <div className="navbar navbar-inverse fixed-top header">
            {cartOpened ? <Drawer /> : null}
            
            <div className="container-fluid">
                
                <a className="navbar-brand nav-link-style" href="/"><b>Food<br/>Delivery</b></a>
            
                <div className="row">
                    <a className="navbar-brand nav-link-style col" href="/">Main page</a>
                </div>

                <div className="row">
                    <a className='navbar-brand nav-link-style col' href="/restaurant">Restaurant</a>
                </div>

                <div className="row">
                    <a className="navbar-brand nav-link-style col" href="/order">Order</a>
                </div>
                
                <div className="row">
                    <a className="navbar-brand navbar-right col" href="#"><MdAccountCircle className='icons'/></a>
                    <a className="navbar-brand col" href="#" onClick={() => setCartOpened(!cartOpened)}><FaCartArrowDown className='icons'/></a>
                </div>
            </div>
        </div>
    );
}

export default Navbar;