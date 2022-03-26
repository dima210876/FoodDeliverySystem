import ProductPageCard from "./productPageCard";
import React from "react";
import axios from "axios";
import {useSelector} from "react-redux";
import setStartCountOfProduct from "../../local_storage_helper/LocalStorageHelper";
import "./productPageList.css"

/*var productPageList = [{"title":"Salat","price":4, "imageUrl":"/img/food.png"},
                         {"title":"Burger","price":5,"imageUrl":"/img/food.png"},
                         {"title":"Rolls","price":11,"imageUrl":"/img/food.png"},
                         {"title":"Chocolate mues","price":9, "imageUrl":"/img/food.png"}];*/

const endpointName = 'https://62265d432dfa524018038bde.mockapi.io/productsForProductPage';

function ProductPageList(){

    const [productPageList, setProductPageList] = React.useState([]);
    const items = useSelector(state => state.cart.items);

    function checkItems(id){
        if(items.filter(item => item.id === id).length === 0){
            return setStartCountOfProduct(id);
        } else {
            return items.filter(item => item.id === id).count;
        }
    }

    React.useEffect(() => {
        axios.get(endpointName)
            .then(function (response) {
                setProductPageList(response.data);
                console.log(productPageList);
            })}, [])

    return(
        <div className="product-page-list">
            {productPageList.map((obj) => (
                <ProductPageCard
                    id={obj.id}
                    title={obj.title}
                    price={obj.price}
                    imageUrl={obj.imageUrl}
                    count={checkItems(obj.id)}
                    restaurant={obj.restaurant}
                />
            ))}
        </div>
    );
}

export default ProductPageList;