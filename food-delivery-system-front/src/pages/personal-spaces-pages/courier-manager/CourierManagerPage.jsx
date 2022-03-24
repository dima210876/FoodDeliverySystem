import {Link} from "react-router-dom";
import './CourierManagerPage.css';
import Navbar from "../../../components/Navbar";

const CourierManagerPage = () => {
    return(
        <>
        <Navbar />
        <div className='box'>
            <div className='courier-image-div'>
                <img className='courier-image' src='courier-manager.png'/>
            </div>
            <div className='list-of-actions'>
                <div className='title'>
                    <h1>Courier manager's space</h1>
                </div>
                <div className='actions'>
                    <div className='link-div'>
                        <Link className='link' to='/courier-manager/courier-registration'><h4>New courier registration</h4></Link>
                    </div>
                </div>
            </div>
        </div>
        </>
    );
}

export {CourierManagerPage}