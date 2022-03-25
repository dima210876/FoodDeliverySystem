import {Link} from "react-router-dom";
import './CourierManagerPage.css';
import Navbar from "../../../components/Navbar";
import {useSelector} from "react-redux";

const CourierManagerPage = () => {
    const courierManager = useSelector(state => state.userData.deliveryOrgManagerData.manager);

    return(
        <>
        <Navbar />
        <div className='box'>
            <div className='courier-image-div'>
                <img className='courier-image' src='courier-manager.png'/>
            </div>
            <div className='managers-space'>
                <div className='title'>
                    <h1>Courier manager's space</h1>
                </div>
                <div className='info'>
                    <div className='managers-block'>
                        <h4>Manager's info</h4>
                        <div className='user-info'>
                            <h6>First name: {courierManager.firstName ? courierManager.firstName : " – "} </h6>
                            <h6>Last name: {courierManager.lastName ? courierManager.lastName : " – "}</h6>
                            <h6>Email: {courierManager.email ? courierManager.email : " – "}</h6>
                            <h6>Phone number: {courierManager.phone ? courierManager.phone : " – "} </h6>
                        </div>
                    </div>
                    <div className='delivery-organization-block'>
                        <h4>Organization's info</h4>
                        <div className='delivery-organization-info'>
                            <h6>Organization name:  {courierManager.organization.name ? courierManager.organization.name : " – "}</h6>
                            <h6>Account number:  {courierManager.organization.accountNumber ? courierManager.organization.accountNumber : " – "}</h6>
                            <h6>Office address:  {courierManager.organization.address ? courierManager.organization.address : " – "}</h6>
                            <h6>Phone number:  {courierManager.organization.phoneNumber ? courierManager.organization.phoneNumber : " – "}</h6>
                        </div>
                        <div className='link-div'>
                            <Link className='link' to='/courier-manager/courier-registration'><h5>Modify</h5></Link>
                        </div>
                    </div>
                </div>
                <div className='actions'>
                    <div className='link-div'>
                        <Link className='link' to='/courier-manager/courier-registration'><h5>NEW COURIER REGISTRATION</h5></Link>
                    </div>
                </div>
            </div>
        </div>
        </>
    );
}

export {CourierManagerPage}