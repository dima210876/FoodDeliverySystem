import React from 'react';
import {useSelector} from "react-redux";
import "./drawer.css"

const TotalCount = () => {
    const TOTAL_PRICE = 'TOTAL_PRICE';
    const TOTAL_COUNT = 'TOTAL_COUNT';

    const totalCountOfAllProducts = useSelector(state => state.cart.totalCount);
    let totalPriceOfAllProducts = useSelector(state => state.cart.totalPrice);

    function checkTotalPrice() {
        const totalPrice = JSON.parse(localStorage.getItem(TOTAL_PRICE));
        if (totalPrice !== null && totalPrice.price !== totalPriceOfAllProducts) {
            if (totalPrice.price - 0.001 < 0)
                totalPrice.price = 0;
            return totalPrice.price.toFixed(2);
        } else {
            if (totalPrice === null) {
                localStorage.setItem(TOTAL_PRICE, JSON.stringify({price: totalPriceOfAllProducts}))
            }
            if (totalPriceOfAllProducts - 0.001 < 0)
                totalPriceOfAllProducts = 0;
            return totalPriceOfAllProducts.toFixed(2);
        }
    }

    function checkTotalCount() {
        const totalCount = JSON.parse(localStorage.getItem(TOTAL_COUNT));
        if (totalCount !== null && totalCount.count !== totalCountOfAllProducts) {
            return totalCount.count;
        } else {
            if (totalCount === null) {
                localStorage.setItem(TOTAL_COUNT, JSON.stringify({count: totalCountOfAllProducts}))
            }
            return totalCountOfAllProducts;
        }
    }

    return (
        <div>
            <h5 className="total-count">{checkTotalCount()} items selected</h5>
            <hr className="line"/>
            <div className="row drawer-price">
                <div className="col"><span>Total price</span></div>
                <div className="col total-price"><span><b>{checkTotalPrice()}$</b></span></div>
            </div>
        </div>
    );
};

export default TotalCount;