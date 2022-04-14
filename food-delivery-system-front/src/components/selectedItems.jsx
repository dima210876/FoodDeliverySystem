import React from 'react';
import DrawerItem from "./drawerItem";
import {useDispatch, useSelector} from "react-redux";
import "./drawer.css"

const SelectedItems = () => {
    const PRODUCTS = 'PRODUCTS';
    const SET_STATE = 'SET_STATE';

    const dispatch = useDispatch();
    const items = useSelector(state => state.cart.items);

    function checkAllItems() {
        const productsWithDiscount = JSON.parse(localStorage.getItem(PRODUCTS));
        if (productsWithDiscount !== null && items.length !== productsWithDiscount.items.length) {
            if (localStorage.getItem(PRODUCTS) === null ||
                JSON.parse(localStorage.getItem(PRODUCTS)).items.length === 0) {
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

    return (
            <div className='col drawer-items'>
                {checkAllItems()}
            </div>
    );
};

export default SelectedItems;