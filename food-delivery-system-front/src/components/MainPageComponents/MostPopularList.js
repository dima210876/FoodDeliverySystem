import MostPopularCard from "./MostPopularCard";
import React from "react";
import axios from "axios";
import {useSelector} from "react-redux";
import setStartCountOfProduct from "../../local_storage_helper/LocalStorageHelper";
import "./MostPopularList.css"

/*var mostPopularList = [{"title":"Salat","price":4, "imageUrl":"/img/food.png"},
                         {"title":"Burger","price":5,"imageUrl":"/img/food.png"},
                         {"title":"Rolls","price":11,"imageUrl":"/img/food.png"},
                         {"title":"Chocolate mues","price":9, "imageUrl":"/img/food.png"}];*/

function MostPopularList(){

    const [mostPopularList, setMostPopularList] = React.useState([]);
    const items = useSelector(state => state.cart.items);

    function checkItems(id){
        if(items.filter(item => item.id === id).length === 0){
            return setStartCountOfProduct(id);
        } else {
            return items.filter(item => item.id === id).count;
        }
    }

    React.useEffect(() =>{
        axios.get('https://62265d432dfa524018038bde.mockapi.io/popularProducts')
  .then(function (response) {
    setMostPopularList(response.data);
    console.log(mostPopularList);
  })
    }, [])

    return(
        <div className="most-popular-list">   
                {mostPopularList.map((obj) => (
                    <MostPopularCard
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

export default MostPopularList;