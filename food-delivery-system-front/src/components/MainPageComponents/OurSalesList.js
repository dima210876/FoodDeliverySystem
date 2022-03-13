import axios from "axios";
import React from "react";
import { useSelector } from "react-redux";
import OurSalesCard from "./OurSalesCard";
import setStartCountOfProduct from "../../local_storage_helper/LocalStorageHelper";

/*var listWithDiscounts = [{"title":"Salat","price":4,"discount":3,"imageUrl":"/img/food.png"},
                         {"title":"Burger","price":5,"discount":3,"imageUrl":"/img/food.png"},
                         {"title":"Rolls","price":11,"discount":3,"imageUrl":"/img/food.png"},
                         {"title":"Chocolate mues","price":9,"discount":3,"imageUrl":"/img/food.png"}];*/

function OurSalesList(){

    const [listWithDiscounts, setListWithDiscounts] = React.useState([]);
    const items = useSelector(state => state.cart.items);

    function checkItems(id){
        if(items.filter(item => item.id === id).length === 0){
            return setStartCountOfProduct(id);
        } else {
            return items.filter(item => item.id === id).count;
        }
    }

    React.useEffect(() =>{
        axios.get('https://62265d432dfa524018038bde.mockapi.io/productsWithDiscount')
  .then(function (response) {
    setListWithDiscounts(response.data);
    console.log(listWithDiscounts);
  })
    }, [])

    return(
        <div className="most-popular-list">
                {listWithDiscounts.map((obj) => (
                    <OurSalesCard
                        id={obj.id}
                        title={obj.title}
                        price={obj.price}
                        discount={obj.discount}
                        imageUrl={obj.imageUrl}
                        count={checkItems(obj.id)}
                        restaurant={obj.restaurant}
                    />
                ))}
            </div>
    );
}

export default OurSalesList