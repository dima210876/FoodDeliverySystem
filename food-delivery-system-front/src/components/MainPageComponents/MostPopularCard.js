import { FiMinusCircle } from "react-icons/fi";
import { FiPlusCircle } from "react-icons/fi";
import React from "react";
import {useDispatch, useSelector} from "react-redux";
import setStartCountOfProduct from "../../local_storage_helper/LocalStorageHelper";
import "./MostPopularCard.css";

function MostPopularCard(props){

    const dispatch = useDispatch();
    const items = useSelector(state => state.cart.items);

    const increaseCountOfProducts = (id, title, price, imageUrl, count, restaurant) => {

        dispatch({type: 'INCREASE_COUNT_OF_PRODUCT', payload: {
                id: id,
                title: title,
                price: price,
                imageUrl: imageUrl,
                restaurant: restaurant,
                count: count
            }
        });
        console.log(items);
    }


    const decreaseCountOfProduct = (id, count) => {
        dispatch({type: 'DECREASE_COUNT_OF_PRODUCT', payload: {
                id: id,
                count: count
            }});
    }

    function checkItems(id){
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
                    <span><b>{props.price}</b>$ for 1 portions</span>
                </div>
                <div className="btn-plus-minus">
                    <button className="button" onClick={() => decreaseCountOfProduct(props.id,  props.count)}><FiMinusCircle /></button>
                    <span> {checkItems(props.id)}</span>
                    <button className="button" onClick={() => increaseCountOfProducts(props.id, props.title, props.price, props.imageUrl,  props.count, props.restaurant)}><FiPlusCircle /></button>
                </div>
            </div>
            
            
        </div>
    );
}

export default MostPopularCard