import {Link} from "react-router-dom";
import './CourierManagerPage.css';
import Navbar from "../../../components/Navbar";
import {useSelector} from "react-redux";

const CourierManagerPage = () => {
    const user = useSelector(state => state.userData.managerData);

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
                            <h6>First name: {user.firstName ? user.firstName : " – "} </h6>
                            <h6>Last name: {user.lastName ? user.lastName : " – "}</h6>
                            <h6>Email: {user.email ? user.email : " – "}</h6>
                            <h6>Phone number: {user.phone ? user.phone : " – "} </h6>
                        </div>
                    </div>
                    <div className='delivery-organization-block'>
                        <h4>Organization's info</h4>
                        <div className='delivery-organization-info'>
                            <h6>Organization name: </h6>
                            <h6>Account number: </h6>
                            <h6>Office address: </h6>
                            <h6>Phone number: </h6>
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