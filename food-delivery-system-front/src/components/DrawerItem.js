import { FiMinusCircle } from "react-icons/fi";
import { FiPlusCircle } from "react-icons/fi";
import React from "react";
import {useDispatch, useSelector} from "react-redux";
import setStartCountOfProduct from "../local_storage_helper/LocalStorageHelper";
import "./DrawerItem.css"

function DrawerItem(props){

    const dispatch = useDispatch();
    const items = useSelector(state => state.cart.items);

    function checkItems(id){
        if(items.filter(item => item.id === id).length === 0){
            return setStartCountOfProduct(id);
        } else {
            return items.filter(item => item.id === id)[0].count;
        }
    }

    const increaseCountOfProduct = (id, title, price, imageUrl, restaurant, count) => {
        dispatch({type: 'INCREASE_COUNT_OF_PRODUCT', payload: {
                id: id,
                title: title,
                price: price,
                imageUrl: imageUrl,
                restaurant: restaurant,
                count: count
        }});
    }

    const decreaseCountOfProduct = (id, count) => {
        dispatch({type: 'DECREASE_COUNT_OF_PRODUCT', payload: {
                id: id,
                count: count
        }});
    }

    const removeItem = (id) => {
        dispatch({type: 'REMOVE_ITEM', payload: {id: id}});
    }

    return(
        <div className='drawer-item'>
            <div>
                <div className='row'>
                    <div className='col'>
                        <span><b>{props.title}</b></span>
                    </div>
                    <div className='col btn-remove'>
                        <button className='rm' onClick={() => removeItem(props.id)}>&times;</button>
                    </div>
                </div>
              <div>
                <span>{props.restaurant}</span>
              </div>
            </div>
            <div className="row">
                <div className=" col price-in-item-drawer">
                    <span><b>{props.price}</b>$</span>
                </div>
                <div className="col btns">
                    <div><button className="button-on-item-drawer" onClick={() => decreaseCountOfProduct(props.id,  props.count)}><FiMinusCircle /></button></div>
                    <div className="cnt-of-product"><span>{checkItems(props.id)}</span></div>
                    <div><button className="button-on-item-drawer" onClick={()=>increaseCountOfProduct(props.id, props.title, props.price, props.imageUrl, props.restaurant, props.count)}><FiPlusCircle /></button></div>
                </div>
            </div>
        </div>
    );
}

export default DrawerItem;