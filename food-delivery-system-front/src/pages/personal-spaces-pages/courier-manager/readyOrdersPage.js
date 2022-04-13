import './readyOrdersPage.css';
import Navbar from "../../../components/navbar";
import React, {useState} from "react";
import axios from "axios";
import ProductPageCard from "../../../components/product_page_components/productPageCard";
import {useNavigate} from "react-router-dom";
import {useDispatch} from "react-redux";

const ReadyOrdersPage = () => {
    const CHOOSE_ORDER = 'CHOOSE_ORDER';
    const endpointName = "http://localhost:8080/readyOrders";
    const [orderList, setOrderList] = useState([]);
    const [listEmptyTitle, setListEmptyTitle] = useState(false);

    const navigate = useNavigate();
    const dispatch = useDispatch();

    function getOrderList(orders){
        let readyOrderList = [];
        orders.map((item) => {
            readyOrderList.push({id: item.id,
                customer: item.customer,
                orderPrice: item.orderPrice,
                orderAddress: item.orderAddress});
        });
        setOrderList(readyOrderList);
        if(readyOrderList.length === 0)
            setListEmptyTitle(true);
        else
            setListEmptyTitle(false);
    }

    React.useEffect(() => {
        axios.get(endpointName)
            .then(function (response) {
                setListEmptyTitle(false);
                console.log(response.data);
                getOrderList(response.data);
            }).catch(() => setListEmptyTitle(true))
    }, [])

    const openUnoccupiedCouriersPage = (props) => {
        dispatch({type: CHOOSE_ORDER, payload:
                {firstName: props.customer.firstName,
                lastName: props.customer.lastName,
                orderAddress: props.orderAddress,
                orderPrice: props.orderPrice,
                id: props.id}
        })
        navigate('/courier-manager/unoccupied-couriers');
    }

    return(
        <>
            <Navbar />
            <div className='box-'>
                <div className='title'>
                    <h1>Ready orders</h1>
                </div>
                <div className="order-first-row">
                    <div className="order-element"><b>First name</b></div>
                    <div className="order-element"><b>Last name</b></div>
                    <div className="order-element"><b>Order price</b></div>
                    <div className="order-element"><b>Order address</b></div>
                </div>
                {listEmptyTitle ? <div><h1 className='no-found'>No ready orders found</h1></div> : null}
                {orderList.map((obj) => (
                    <div className="order-row" onClick={() => openUnoccupiedCouriersPage(obj)}>
                        <div className="order-element">{obj.customer.firstName}</div>
                        <div className="order-element">{obj.customer.lastName}</div>
                        <div className="order-element">{obj.orderPrice}$</div>
                        <div className="order-element">{obj.orderAddress}</div>
                    </div>
                ))}
            </div>
        </>
    );
}

export {ReadyOrdersPage};