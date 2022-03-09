import { FiMinusCircle } from "react-icons/fi";
import { FiPlusCircle } from "react-icons/fi";
import React from "react";

function DrawerItem(props){

    //const [countOfProducts, setCountOfProducts] = React.useState(0);

    /*const increaseCountOfProducts = () => {
        setCountOfProducts(countOfProducts+1);
    }*/

    /*const decreaseCountOfProducts = () => {
        if(countOfProducts > 0)
            setCountOfProducts(countOfProducts-1);
    }*/

    return(
        <div className='drawer-item'>
            <div className='product-name-and-restaraunt'>
              <div className='product-name-of-drawer-item'>
                <span className='name-of-product'><b>{props.title}</b></span>
              </div>
              <div className='name-of-restaurant'>
                <span>{props.restaurantName}</span>
              </div>
            </div>
            
            <div className="row">
                <div className="col price-in-item-drawer">
                    <span><b>{props.price}</b>$</span>
                </div>
                <div className="col btn-plus-minus-in-item-drawer">
                    <button className="button-on-item-drawer" /*onClick={decreaseCountOfProducts}*/><FiMinusCircle /></button>
                    <span>{props.inCartCount}</span>
                    <button className="button-on-item-drawer" /*onClick={increaseCountOfProducts}*/><FiPlusCircle /></button>
                </div>
            </div>
            
            
        </div>
    );
}

export default DrawerItem;