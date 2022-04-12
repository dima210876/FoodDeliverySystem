import {useDispatch, useSelector} from "react-redux";
import React from "react";
import "./drawer.css"
import {Link} from "react-router-dom";
import SelectedItems from "./selectedItems";
import TotalCount from "./totalCount";
import {Button} from "react-bootstrap";

function Drawer() {
        return (
        <div className="overlay">
            <div
                className="drawer fixed-top col-md-2 col-xs-2 col-sm-2 col-lg-2 offset-xs-9 offset-sm-9 offset-md-9 offset-lg-9">
                <h5 className='drawer-name'>Cart</h5>
                <SelectedItems/>
                <div className='drawer-footer'>
                    <TotalCount/>
                    <div className="blk-place-an-order">
                        <Link className="place-an-order-btn" to="/order">Place an
                        order</Link>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Drawer;