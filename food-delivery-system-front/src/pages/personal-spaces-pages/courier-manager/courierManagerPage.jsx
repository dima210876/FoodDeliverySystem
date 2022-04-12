import {Link} from "react-router-dom";
import './courierManagerPage.css';
import Navbar from "../../../components/navbar";
import {useSelector} from "react-redux";

const CourierManagerPage = () => {
    const deliveryOrgManager = useSelector(state => state.userData.deliveryOrgManagerData);
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
                            <h6>First name: {deliveryOrgManager.firstName ? deliveryOrgManager.firstName : " – "} </h6>
                            <h6>Last name: {deliveryOrgManager.lastName ? deliveryOrgManager.lastName : " – "}</h6>
                            <h6>Email: {deliveryOrgManager.email ? deliveryOrgManager.email : " – "}</h6>
                            <h6>Phone number: {deliveryOrgManager.phoneNumber ? deliveryOrgManager.phoneNumber : " – "} </h6>
                        </div>
                        <div className='link-div'>
                            <Link className='link' to='/courier-manager/modify-courier-manager-info'><h5>Modify</h5></Link>
                        </div>
                    </div>
                    <div className='delivery-organization-block'>
                        <h4>Organization's info</h4>
                        <div className='delivery-organization-info'>
                            <h6>Organization name:  {deliveryOrgManager.organization.name ? deliveryOrgManager.organization.name : " – "}</h6>
                            <h6>Account number:  {deliveryOrgManager.organization.accountNumber ? deliveryOrgManager.organization.accountNumber : " – "}</h6>
                            <h6>Office address:  {deliveryOrgManager.organization.officeAddress && deliveryOrgManager.organization.officeAddress !== "Unknown" ? deliveryOrgManager.organization.officeAddress : " – "}</h6>
                            <h6>Phone number:  {deliveryOrgManager.organization.phoneNumber ? deliveryOrgManager.organization.phoneNumber : " – "}</h6>
                        </div>
                        <div className='link-div'>
                            <Link className='link' to='/courier-manager/modify-organization-info'><h5>Modify</h5></Link>
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

export {CourierManagerPage};