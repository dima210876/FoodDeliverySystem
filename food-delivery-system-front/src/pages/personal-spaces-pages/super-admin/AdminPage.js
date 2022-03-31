import {Link} from "react-router-dom";
import './AdminPage.css';
import Navbar from "../../../components/Navbar";

const AdminPage = () => {
    return(
        <>
        <Navbar />
        <div className='box'>
            <div className='admin-image-div'>
                <img className='admin-image' src='admin.png'/>
            </div>
            <div className='list-of-actions'>
                <div className='title'>
                    <h1>Administrator's space</h1>
                </div>
                <div className='actions'>
                    <div className='link-div'>
                        <Link className='link' to='/admin/restaurant-registration'><h4>Restaurant manager registration</h4></Link>
                    </div>
                    <div className='link-div'>
                        <Link className='link' to='/admin/delivery-registration'><h4>Courier organization manager registration</h4></Link>
                    </div>
                    <div className='link-div'>
                        <Link className='link' to=''><h4>List of recent orders</h4></Link>
                    </div>
                </div>
            </div>

        </div>
        </>
    );
}

export {AdminPage}