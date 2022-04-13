import "./UnoccupiedCouriersPage.css"

import './readyOrdersPage.css';
import Navbar from "../../../components/navbar";
import React, {useState} from "react";
import axios from "axios";
import ProductPageCard from "../../../components/product_page_components/productPageCard";
import {useNavigate} from "react-router-dom";
import {useDispatch, useSelector} from "react-redux";

const UnoccupiedCouriersPage = () => {
    const endpointNameForGet = "http://localhost:8084/getUnoccupiedCouriers";
    const endpointNameForPost = "http://localhost:8084/changeStatus";
    const [courierList, setCourierList] = useState([]);
    const [listEmptyTitle, setListEmptyTitle] = useState(false);
    const email = useSelector(state => state.auth.authData.user.email);
    const navigate = useNavigate();
    const [stateOfAlert, setStateOfAlert] = useState(false);
    const orderId = useSelector(state => state.readyOrder.id);

    function getCourierList(couriers){
        let unoccupiedCourierList = [];
        couriers.map((item) => {
            unoccupiedCourierList.push({
                userId: item.userId,
                email: item.email,
                firstName: item.firstName,
                lastName: item.lastName,
                phoneNumber: item.phoneNumber,
                role: item.role,
                organization: {
                    id: item.organization.id,
                    name: item.organization.name,
                },
            });
        });
        setCourierList(unoccupiedCourierList);
        if(unoccupiedCourierList.length === 0)
            setListEmptyTitle(true);
        else
            setListEmptyTitle(false);
    }


    React.useEffect(() => {
        let formData = new FormData();
        formData.append("email", email);
        axios.get(endpointNameForGet,{params: {email:email}})
            .then(function (response) {
                setListEmptyTitle(false);
                console.log(response.data);
                getCourierList(response.data);
            }).catch(() => setListEmptyTitle(true))
    }, [])

    const chooseCourier = (obj) => {
        axios.post(endpointNameForPost,{orderId:orderId,
        userId: obj.userId})
            .then(function (response) {
                navigate("/courier-manager");
            }).catch(() => {
            setStateOfAlert(true);
            setTimeout(() => {
                setStateOfAlert(false);
            }, 3000)
        })
    }

    return(
        <>
            <Navbar />
            <div className='courier-box'>
                {stateOfAlert ? <div className="alert alert-danger" role='alert'>Error on the server</div> : null}
                <div className='courier-title-'>
                    <h1>Unoccupied couriers</h1>
                </div>
                <div className="courier-first-row">
                    <div className="courier-element"><b>First name</b></div>
                    <div className="courier-element"><b>Last name</b></div>
                    <div className="courier-element"><b>Phone number</b></div>
                    <div className="courier-element"><b>Organization name</b></div>
                </div>
                {listEmptyTitle ? <div><h1 className='no-found'>No unoccupied couriers found</h1></div> : null}
                {courierList.map((obj) => (
                    <div className="courier-row" onClick={() => chooseCourier(obj)}>
                        <div className="courier-element">{obj.firstName}</div>
                        <div className="courier-element">{obj.lastName}</div>
                        <div className="courier-element">{obj.phoneNumber}</div>
                        <div className="courier-element">{obj.organization.name}</div>
                    </div>
                ))}
            </div>
        </>
    );
}

export {UnoccupiedCouriersPage};