import { FiMinusCircle } from "react-icons/fi";
import { FiPlusCircle } from "react-icons/fi";
import React from "react";



function OurSalesCard(props){

    //const [countOfProducts, setCountOfProducts] = React.useState(0);

    const increaseCountOfProducts = (id, title, price, onClickPlus) => {
        //setCountOfProducts(countOfProducts+1);
        onClickPlus({id, title, price});
    }

    /*const decreaseCountOfProducts = () => {
        if(countOfProducts > 0)
            setCountOfProducts(countOfProducts-1);
    }*/

    return(
        <div className="most-popular-card">
            <img src = {props.imageUrl} alt="" width={100} height={100} />  
            <p className="product-name">{props.title}</p>
            <div className="bottom-row">
                <div className="our-sales">
                    <span className="discount"><b>{props.discount}$</b></span><span className="price"><b>{props.price}$</b><br/></span><span className="price-text">for 1 portions</span>
                </div>
                <div className="btn-plus-minus-on-sales-car">
                    <button className="button minus-on-sales-card" /*onClick={decreaseCountOfProducts}*/><FiMinusCircle /></button>
                    {props.inCartCount && <span>{props.inCartCount}</span>}
                    <button className="button plus-on-sales-card" onClick={() => increaseCountOfProducts(props.id, props.title, props.discount, props.onClickPlus)}><FiPlusCircle /></button>
                </div>
            </div>
        </div>
    );
}

export default OurSalesCard