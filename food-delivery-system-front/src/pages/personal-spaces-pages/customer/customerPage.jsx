import {Link} from "react-router-dom";
import '../courier-manager/courierManagerPage.css';
import Navbar from "../../../components/navbar";
import {useSelector} from "react-redux";

const CustomerPage = () => {
    const customer = useSelector(state => state.userData.customerData);
    return(
        <>
            <Navbar />
            <div className='box'>
                <div className='courier-image-div'>
                    <img className='customer-image' src='man.png'/>
                </div>
                <div className='managers-space'>
                    <div className='title'>
                        <h1>Customer's space</h1>
                    </div>
                    <div className='info'>
                        <div className='info-block'>
                            <h4>Customer's info</h4>
                            <div className='user-info'>
                                <h6>First name: {customer.firstName ? customer.firstName : " – "} </h6>
                                <h6>Last name: {customer.lastName ? customer.lastName : " – "}</h6>
                                <h6>Email: {customer.email ? customer.email : " – "}</h6>
                                <h6>Phone number: {customer.phoneNumber ? customer.phoneNumber : " – "} </h6>
                            </div>
                            <div className='link-div'>
                                <Link className='link' to='/courier-manager/modify-courier-info'><h5>Modify</h5></Link>
                            </div>
                        </div>
                    </div>
                    <div className='actions'>
                        <div className='link-div'>
                            <Link className='link' to='/customer/confirmedOrderPage'><h5>ORDER INFO</h5></Link>
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
}

export {CustomerPage};