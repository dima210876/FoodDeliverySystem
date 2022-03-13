import {useDispatch, useSelector} from "react-redux";
import DrawerItem from "./DrawerItem";
import React from "react";
import setStartCountOfProduct from "../local_storage_helper/LocalStorageHelper";

function Drawer(){

    const dispatch = useDispatch();
    const items = useSelector(state => state.cart.items);
    const totalCountOfAllProducts = useSelector(state => state.cart.totalCount);
    let totalPriceOfAllProducts = useSelector(state => state.cart.totalPrice);

    function checkTotalPrice(){
        const totalPrice = JSON.parse(localStorage.getItem('TOTAL_PRICE'));
        if(totalPrice !== null && totalPrice.price !== totalPriceOfAllProducts){
            if(totalPrice.price - 0.001 < 0)
                totalPrice.price = 0;
            return totalPrice.price.toFixed(2);
        } else {
            if(totalPrice === null){
                localStorage.setItem('TOTAL_PRICE', JSON.stringify({price: totalPriceOfAllProducts}))
            }
            if(totalPriceOfAllProducts - 0.001 < 0)
                totalPriceOfAllProducts = 0;
            return totalPriceOfAllProducts.toFixed(2);
        }
    }

    function checkTotalCount(){
        const totalCount = JSON.parse(localStorage.getItem('TOTAL_COUNT'));
        if(totalCount !== null && totalCount.count !== totalCountOfAllProducts){
            return totalCount.count;
        } else {
            if(totalCount === null){
                localStorage.setItem('TOTAL_COUNT', JSON.stringify({count: totalCountOfAllProducts}))
            }
            return totalCountOfAllProducts;
        }
    }

    function checkAllItems(){
        const productsWithDiscount = JSON.parse(localStorage.getItem('PRODUCTS_WITH_DISCOUNT'));
        if (productsWithDiscount !== null && items.length !== productsWithDiscount.items.length){
            if(localStorage.getItem('PRODUCTS_WITH_DISCOUNT') === null  || JSON.parse(localStorage.getItem('PRODUCTS_WITH_DISCOUNT')).items.length === 0){
                return null;
            }

            dispatch({type: 'SET_STATE', payload: {id: productsWithDiscount.items}});
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
            <div className="drawer fixed-top col-md-2 offset-md-9">
                <h5>Card</h5>
                <div className='drawer-items'>
                {checkAllItems()}
                </div>
                <div className='drawer-footer'>
                <h5>{checkTotalCount()} items selected</h5>
                <hr/>
                <div className="row">
                    <div className="col"><span>Total price</span></div>
                    <div className="col total-price"><span><b>{checkTotalPrice()}$</b></span></div>
                </div>
                </div>
            </div>
        </div>
    );
}

export default Drawer;