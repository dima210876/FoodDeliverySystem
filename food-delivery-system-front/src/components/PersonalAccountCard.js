import {useSelector} from "react-redux";
import React from "react";
import "./PersonalAccountCard.css";
import {Link} from "react-router-dom";

function PersonalAccountCard(){
    const user =  useSelector(state => state.auth.authData.user);

    function loginAndRegistrationItems(){
        return (
            <>
                <Link className='personal-account-link' to='/login'>
                    <div className='personal-account-item'>Login</div>
                </Link>
                <Link className='personal-account-link' to='/registration'>
                    <div className='personal-account-item'>Registration</div>
                </Link>
            </>
        )
    }

    function personalAccountItem(){
        return (
            <>
                <Link className='personal-account-link' to='/account'>
                    <div className='personal-account-item'>Personal account</div>
                </Link>
            </>
        )
    }

    return(
        <div className="personal-account-card fixed-top col-md-1 col-xs-1 col-sm-1 col-lg-1 offset-xs-10 offset-sm-10 offset-md-10 offset-lg-10">
            {(user.role === undefined || user.role === '') ? loginAndRegistrationItems() : personalAccountItem()}
        </div>
    );
}

export default PersonalAccountCard;