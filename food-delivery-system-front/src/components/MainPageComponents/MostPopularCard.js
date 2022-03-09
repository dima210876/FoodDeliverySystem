import { FiMinusCircle } from "react-icons/fi";
import { FiPlusCircle } from "react-icons/fi";
import React from "react";

function MostPopularCard(props){

    const [countOfProducts, setCountOfProducts] = React.useState(0);

    const increaseCountOfProducts = () => {
        setCountOfProducts(countOfProducts+1);
    }

    const decreaseCountOfProducts = () => {
        if(countOfProducts > 0)
            setCountOfProducts(countOfProducts-1);
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
                    <button className="button button5" onClick={decreaseCountOfProducts}><FiMinusCircle /></button>
                    <span>{countOfProducts}</span>
                    <button className="button button5" onClick={increaseCountOfProducts}><FiPlusCircle /></button>
                </div>
            </div>
            
            
        </div>
    );
}

export default MostPopularCard