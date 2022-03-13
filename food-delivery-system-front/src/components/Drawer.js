import {useDispatch, useSelector} from "react-redux";
import DrawerItem from "./DrawerItem";
import React from "react";
import setStartCountOfProduct from "../local_storage_helper/LocalStorageHelper";

function Drawer(){

    const dispatch = useDispatch();
    const items = useSelector(state => state.cart.items);

    function checkItems(id){
        if(items.filter(item => item.id === id).length === 0){
            return setStartCountOfProduct(id);
        } else {
            return items.filter(item => item.id === id).count;
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
                <h5> items selected</h5>
                <hr/>
                <div className="row">
                    <div className="col"><span>Total price</span></div>
                    <div className="col total-price"><span><b>$</b></span></div>
                </div>
                </div>
            </div>
        </div>
    );
}

export default Drawer;