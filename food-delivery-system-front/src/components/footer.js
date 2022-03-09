import { FaFacebook } from "react-icons/fa";
import { FaInstagramSquare } from "react-icons/fa";

function Footer(){

    return( 
        <div className="container-fluid pt-5 pb-4 footer">
             <div className="row">

                <div className="col offset-md-1">
                    <span>Contact numbers:<br/>+3443 34 3432<br/>+344 555 33322</span>    
                </div>

                <div className="col offset-md-1">
                    <a className="icons-contacts" href="#"><FaInstagramSquare /></a>
                    <a className="icons-contacts" href="#"><FaFacebook /></a>   
                </div>

                <div className="col">
                    <span><br/>@copyright G1_Team_itechart</span>  
                </div>
            </div> 
        </div>     
    );
}

export default Footer;