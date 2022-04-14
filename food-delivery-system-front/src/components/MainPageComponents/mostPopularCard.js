import { FiMinusCircle } from "react-icons/fi";
import { FiPlusCircle } from "react-icons/fi";
import React from "react";
import {useDispatch, useSelector} from "react-redux";
import setStartCountOfProduct from "../../local_storage_helper/localStorageHelper";
import "./mostPopularCard.css";

const INCREASE_COUNT_OF_PRODUCT = 'INCREASE_COUNT_OF_PRODUCT';
const DECREASE_COUNT_OF_PRODUCT = 'DECREASE_COUNT_OF_PRODUCT';

function MostPopularCard(props){

    const dispatch = useDispatch();
    const items = useSelector(state => state.cart.items);

    const increaseCountOfProducts = (id, title, price, imageUrl, count, restaurant) => {
        dispatch({type: INCREASE_COUNT_OF_PRODUCT, payload: {
                id: id,
                title: title,
                price: price,
                imageUrl: imageUrl,
                restaurant: restaurant,
                count: count
            }
        });
    }

    const decreaseCountOfProduct = (id, count) => {
        dispatch({type: DECREASE_COUNT_OF_PRODUCT, payload: {
                id: id,
                count: count
        }});
    }

    function checkCountOfItems(id){
        if(items.filter(item => item.id === id).length === 0){
            return setStartCountOfProduct(id);
        } else {
            return items.filter(item => item.id === props.id)[0].count;
        }
    }

    return(
        <div className="most-popular-card">
            <img src = {props.imageUrl} alt="" width={100} height={100} />  
            <p className="product-name">{props.title}</p>
            <div className="bottom-row">
                <div className="popular-card-price">
                    <div className="priceNum"><b>{props.price.toFixed(2)}</b>$</div>
                    <span>for 1 portion</span>
                </div>
                <div className="btn-plus-minus">
                    <div><button className="button" onClick={() => decreaseCountOfProduct(props.id,  props.count)}><FiMinusCircle /></button></div>
                    <div className="count-of-product"><span>{checkCountOfItems(props.id)}</span></div>
                    <div><button className="button" onClick={() => increaseCountOfProducts(props.id, props.title, props.price, props.imageUrl,  props.count, props.restaurant)}><FiPlusCircle /></button></div>
                </div>
            </div>
        </div>
    );
}

export default MostPopularCard