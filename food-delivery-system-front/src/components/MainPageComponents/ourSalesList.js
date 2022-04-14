import axios from "axios";
import React from "react";
import { useSelector } from "react-redux";
import OurSalesCard from "./ourSalesCard";
import setStartCountOfProduct from "../../local_storage_helper/localStorageHelper";
import "./ourSalesList.css"

// var listWithDiscounts = [{"title":"Salat","price":4,"discount":3,"imageUrl":"/img/food.png"},
//                          {"title":"Burger","price":5,"discount":3,"imageUrl":"/img/food.png"},
//                          {"title":"Rolls","price":11,"discount":3,"imageUrl":"/img/food.png"},
//                          {"title":"Rolls2","price":10,"discount":3,"imageUrl":"/img/food.png"},
//                          {"title":"Chocolate mues","price":9,"discount":3,"imageUrl":"/img/food.png"}];

const endpointName = 'https://62573a3b4428bb6c082076b4.mockapi.io/api/sales';

function OurSalesList(){

    const [listWithDiscounts, setListWithDiscounts] = React.useState([]);

    const items = useSelector(state => state.cart.items);

    function checkCountOfItems(id){
        if(items.filter(item => item.id === id).length === 0){
            return setStartCountOfProduct(id);
        } else {
            return items.filter(item => item.id === id).count;
        }
    }

    React.useEffect(() => {
        axios.get(endpointName)
            .then(function (response) {
                setListWithDiscounts(response.data);
                console.log(listWithDiscounts);
            })}, []);

    return(
        <div className="most-popular-list">
                {listWithDiscounts.map((obj) => (
                    <OurSalesCard
                        id={obj.id}
                        title={obj.name}
                        price={obj.price}
                        discount={obj.discount}
                        imageUrl={obj.image}
                        count={checkCountOfItems(obj.id)}
                        restaurant={obj.restaurant}
                    />
                ))}
        </div>
    );
}

export default OurSalesList