import CategoryCard from "./categoryCard";
import React from "react";
import axios from "axios";
import "./categoryList.css"

/*var categoryList = [{"title":"Pizzas","imageUrl":"/img/food.png"},
                        {"title":"Burgers","imageUrl":"/img/food.png"},
                        {"title":"Rolls","imageUrl":"/img/food.png"},
                        {"title":"Salat","imageUrl":"/img/food.png"},
                        {"title":"Sups","imageUrl":"/img/food.png"},
                        {"title":"Beer","imageUrl":"/img/food.png"},
                        {"title":"Cakes","imageUrl":"/img/food.png"}];*/

function CategoryList(){

    const [categoryList, setCategoryList] = React.useState([]);

    React.useEffect(() =>{
        axios.get('https://62265d432dfa524018038bde.mockapi.io/categoryList')
    .then(function (response) {
    setCategoryList(response.data);
    console.log(categoryList);
    })}, [])

    return(
        <div className="category-list">
            {categoryList.map((obj) => (
                    <CategoryCard 
                        title={obj.title}
                        imageUrl={obj.imageUrl}
                    />
            ))}
        </div>
    );
}

export default CategoryList