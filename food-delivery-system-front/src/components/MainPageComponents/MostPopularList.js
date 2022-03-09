import MostPopularCard from "./MostPopularCard";
import React from "react";
import axios from "axios";

/*var mostPopularList = [{"title":"Salat","price":4, "imageUrl":"/img/food.png"},
                         {"title":"Burger","price":5,"imageUrl":"/img/food.png"},
                         {"title":"Rolls","price":11,"imageUrl":"/img/food.png"},
                         {"title":"Chocolate mues","price":9, "imageUrl":"/img/food.png"}];*/

function MostPopularList(){

    const [mostPopularList, setMostPopularList] = React.useState([]);

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
                        title={obj.title}
                        price={obj.price}
                        imageUrl={obj.imageUrl}
                    />
                ))}
            </div>
    );
}

export default MostPopularList;