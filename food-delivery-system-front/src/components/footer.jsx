import { FaFacebook } from "react-icons/fa";
import { FaInstagramSquare } from "react-icons/fa";

import "./footerHeader.css"

function Footer(){

    return(
        <div className="container-fluid pt-5 pb-4 footer">
            <div className="row">
                <div className="col offset-md-1">
                    <span>Contact numbers:<br/>+375-29-343-34-32<br/>+375-29-555-33-22</span>
                </div>
                <div className="col offset-md-1">
                    <a className="icons-contacts" href="https://www.instagram.com/wwwmenuby/?hl=ru"><FaInstagramSquare /></a>
                    <a className="icons-contacts" href="https://www.facebook.com/menuby/"><FaFacebook /></a>
                </div>
                <div className="col">
                    <span><br/>@copyright G1_Team_itechart</span>
                </div>
            </div>
        </div>
    );
}

export default Footer;