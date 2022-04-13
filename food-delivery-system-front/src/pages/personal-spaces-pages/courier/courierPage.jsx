import {Link} from "react-router-dom";
import '../courier-manager/courierManagerPage.css';
import Navbar from "../../../components/navbar";
import {useSelector} from "react-redux";

const CourierPage = () => {
    const courier = useSelector(state => state.userData.courierData);
    return(
        <>
            <Navbar />
            <div className='box'>
                <div className='courier-image-div'>
                    <img className='courier-image' src='delivery-man.png'/>
                </div>
                <div className='managers-space'>
                    <div className='title'>
                        <h1>Courier's space</h1>
                    </div>
                    <div className='info'>
                        <div className='info-block'>
                            <h4>Courier's info</h4>
                            <div className='user-info'>
                                <h6>First name: {courier.firstName ? courier.firstName : " – "} </h6>
                                <h6>Last name: {courier.lastName ? courier.lastName : " – "}</h6>
                                <h6>Email: {courier.email ? courier.email : " – "}</h6>
                                <h6>Phone number: {courier.phoneNumber ? courier.phoneNumber : " – "} </h6>
                            </div>
                            <div className='link-div'>
                                <Link className='link' to='/courier-manager/modify-courier-info'><h5>Modify</h5></Link>
                            </div>
                        </div>
                        <div className='delivery-organization-block'>
                            <h4>Organization's info</h4>
                            <div className='delivery-organization-info'>
                                <h6>Organization name:  {courier.organization.name ? courier.organization.name : " – "}</h6>
                                <h6>Account number:  {courier.organization.accountNumber ? courier.organization.accountNumber : " – "}</h6>
                                <h6>Office address:  {courier.organization.officeAddress && courier.organization.officeAddress !== "Unknown" ? courier.organization.officeAddress : " – "}</h6>
                                <h6>Phone number:  {courier.organization.phoneNumber ? courier.organization.phoneNumber : " – "}</h6>
                            </div>
                        </div>
                    </div>
                    <div className='actions'>
                        <div className='link-div'>
                            <Link className='link' to='/courier'><h5>ORDER INFO</h5></Link>
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
}

export {CourierPage};