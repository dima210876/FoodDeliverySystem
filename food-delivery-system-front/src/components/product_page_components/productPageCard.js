import { FiMinusCircle } from "react-icons/fi";
import { FiPlusCircle } from "react-icons/fi";
import React from "react";
import {useDispatch, useSelector} from "react-redux";
import setStartCountOfProduct from "../../local_storage_helper/localStorageHelper";
import "./productPageCard.css";
import AWS from "aws-sdk";

const INCREASE_COUNT_OF_PRODUCT = 'INCREASE_COUNT_OF_PRODUCT';
const DECREASE_COUNT_OF_PRODUCT = 'DECREASE_COUNT_OF_PRODUCT';

function ProductPageCard(props){

    const dispatch = useDispatch();
    const items = useSelector(state => state.cart.items);
    const [src, setSrc] = React.useState("");

    const increaseCountOfProducts = (id, title, price, imageUrl, count, restaurant, restaurantId) => {
        dispatch({type: INCREASE_COUNT_OF_PRODUCT, payload: {
                id: id,
                title: title,
                price: price,
                imageUrl: imageUrl,
                restaurant: restaurant,
                count: count,
                restaurantId: restaurantId
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

    function getImage(){
        var AWS = require('aws-sdk');
        var s3 = new AWS.S3({accessKeyId:'AKIA4B22QZQFIT6FKUNZ', secretAccessKey:'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', region:'us-east-1'});
        var params = {Bucket: 'food-delivery-dishes', Key: props.id.toString()};
        s3.getSignedUrl('getObject', params, function (err, url) {
            setSrc(url);
        });
    }

    return(
        <div className="most-popular-card">
            {getImage()}
            <img src = {src} alt="Image of the dish" width={100} height={100} />
            <p className="product-name">{props.title}</p>
            <div className="bottom-row">
                <div className="popular-card-price">
                    <span><b>{props.price.toFixed(2)}</b>$ for 1 portion</span>
                </div>
                <div className="btn-plus-minus">
                    <div><button className="button" onClick={() => decreaseCountOfProduct(props.id,  props.count)}><FiMinusCircle /></button></div>
                    <div className="count-of-product"><span>{checkCountOfItems(props.id)}</span></div>
                    <div><button className="button" onClick={() => increaseCountOfProducts(props.id, props.title, props.price, props.imageUrl,  props.count, props.restaurant, props.restaurantId)}><FiPlusCircle /></button></div>
                </div>
            </div>
        </div>
    );
}

export default ProductPageCard