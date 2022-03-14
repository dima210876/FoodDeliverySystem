import {useDispatch, useSelector} from "react-redux";
import DrawerItem from "./DrawerItem";
import React from "react";
import setStartCountOfProduct from "../local_storage_helper/LocalStorageHelper";
import "./Drawer.css"

const TOTAL_PRICE = 'TOTAL_PRICE';
const TOTAL_COUNT = 'TOTAL_COUNT';
const PRODUCTS_WITH_DISCOUNT = 'PRODUCTS_WITH_DISCOUNT';
const SET_STATE = 'SET_STATE';

function Drawer(){

    const dispatch = useDispatch();
    const items = useSelector(state => state.cart.items);
    const totalCountOfAllProducts = useSelector(state => state.cart.totalCount);
    let totalPriceOfAllProducts = useSelector(state => state.cart.totalPrice);

    function checkTotalPrice(){
        const totalPrice = JSON.parse(localStorage.getItem(TOTAL_PRICE));
        if(totalPrice !== null && totalPrice.price !== totalPriceOfAllProducts){
            if(totalPrice.price - 0.001 < 0)
                totalPrice.price = 0;
            return totalPrice.price.toFixed(2);
        } else {
            if(totalPrice === null){
                localStorage.setItem(TOTAL_PRICE, JSON.stringify({price: totalPriceOfAllProducts}))
            }
            if(totalPriceOfAllProducts - 0.001 < 0)
                totalPriceOfAllProducts = 0;
            return totalPriceOfAllProducts.toFixed(2);
        }
    }

    function checkTotalCount(){
        const totalCount = JSON.parse(localStorage.getItem(TOTAL_COUNT));
        if(totalCount !== null && totalCount.count !== totalCountOfAllProducts){
            return totalCount.count;
        } else {
            if(totalCount === null){
                localStorage.setItem(TOTAL_COUNT, JSON.stringify({count: totalCountOfAllProducts}))
            }
            return totalCountOfAllProducts;
        }
    }

    function checkAllItems(){
        const productsWithDiscount = JSON.parse(localStorage.getItem(PRODUCTS_WITH_DISCOUNT));
        if (productsWithDiscount !== null && items.length !== productsWithDiscount.items.length){
            if(localStorage.getItem(PRODUCTS_WITH_DISCOUNT) === null  ||
                JSON.parse(localStorage.getItem(PRODUCTS_WITH_DISCOUNT)).items.length === 0){
                return null;
            }
            dispatch({type: SET_STATE, payload: {id: productsWithDiscount.items}});
            return productsWithDiscount.items.map((obj) => (
                <DrawerItem
                    id={obj.id}
                    title={obj.title}
                    price={obj.price}
                    imageUrl={obj.imageUrl}
                    restaurant={obj.restaurant}
                    count={obj.count}
                />
            ))
        } else {
            return items.map((obj) => (
                <DrawerItem
                    id={obj.id}
                    title={obj.title}
                    price={obj.price}
                    imageUrl={obj.imageUrl}
                    restaurant={obj.restaurant}
                    count={obj.count}
                />
            ))
        }
    }

    return(
        <div className="overlay">
            <div className="drawer fixed-top col-md-2 col-xs-2 col-sm-2 col-lg-2 offset-xs-9 offset-sm-9 offset-md-9 offset-lg-9">
                <h5 className='drawer-name'>Card</h5>
                <div className='col drawer-items'>
                    {checkAllItems()}
                </div>
                <div className='drawer-footer'>
                    <h5 className="total-count">{checkTotalCount()} items selected</h5>
                    <hr className="line"/>
                    <div className="row drawer-price">
                        <div className="col"><span>Total price</span></div>
                        <div className="col total-price"><span><b>{checkTotalPrice()}$</b></span></div>
                    </div>
                    <div className="blk-place-an-order"><button  className="place-an-order-btn">Place an order</button></div>
                </div>
            </div>
        </div>
    );
}

export default Drawer;