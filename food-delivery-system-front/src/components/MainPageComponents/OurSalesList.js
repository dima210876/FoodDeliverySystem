import axios from "axios";
import React from "react";
import { useSelector } from "react-redux";
import OurSalesCard from "./OurSalesCard";

/*var listWithDiscounts = [{"title":"Salat","price":4,"discount":3,"imageUrl":"/img/food.png"},
                         {"title":"Burger","price":5,"discount":3,"imageUrl":"/img/food.png"},
                         {"title":"Rolls","price":11,"discount":3,"imageUrl":"/img/food.png"},
                         {"title":"Chocolate mues","price":9,"discount":3,"imageUrl":"/img/food.png"}];*/

function OurSalesList(props){

    const cartItems = useSelector(({ cart }) => cart.items)

    const [listWithDiscounts, setListWithDiscounts] = React.useState([]);

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
                        onClickPlus={({title, id, price}) => props.onClickPlus({title, id, price})}
                        id={obj.id}
                        title={obj.title}
                        price={obj.price}
                        discount={obj.discount}
                        imageUrl={obj.imageUrl}
                        inCartCount={cartItems[obj.id] && cartItems[obj.id].length}
                    />
                ))}
            </div>
    );
}

export default OurSalesList