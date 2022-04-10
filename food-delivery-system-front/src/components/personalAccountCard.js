import {useSelector} from "react-redux";
import React from "react";
import "./personalAccountCard.css";
import {Link} from "react-router-dom";

function PersonalAccountCard(){
    const user =  useSelector(state => state.auth.authData.user);

    const initialState = {
    authData: {
        user: {
            user_id:'',
            email: '',
            firstName: '',
            lastName: '',
            role: ''
        },
        token: ''
    }
};

    function logout() {
        this.setState({ ...initialState });
        //localStorage.clear();
    }

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
                <Link className='personal-account-link' to='/' onClick={logout}>
                    <div className='personal-account-item'>Logout</div>
                </Link>
            </>
        )
    }

    return(
        <div className="personal-account-card fixed-top col-md-1 col-xs-1 col-sm-1 col-lg-1 offset-xs-10 offset-sm-10 offset-md-10 offset-lg-10">
            {(!user.role) ? loginAndRegistrationItems() : personalAccountItem()}
        </div>
    );
}

export default PersonalAccountCard;