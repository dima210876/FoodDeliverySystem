import CategoryCard from "./categoryCard";
import React from "react";
import axios from "axios";
import "./categoryList.css"

function CategoryList(){

    const [categoryList, setCategoryList] = React.useState([
        {"title": "Burgers", "imageUrl": "/img/sandwich.png"},
        {"title": "Pizza", "imageUrl": "/img/pizza-slice.png"},
        {"title": "Desserts and shakes", "imageUrl": "/img/opera.png"},
        {"title": "Drinks", "imageUrl": "/img/drinks.png"},
        {"title": "Bakery", "imageUrl": "/img/bread.png"},
        {"title": "Snacks", "imageUrl": "/img/nachos.png"},
        {"title": "Soups", "imageUrl": "/img/soup.png"},
        {"title": "Salads", "imageUrl": "/img/salad.png"},
        {"title": "Fish and seafoods", "imageUrl": "/img/crab.png"},
        {"title": "Hot dishes", "imageUrl": "/img/rice.png"}
    ]);

    // React.useEffect(() =>{
    //     axios.get('endpoint-for-pictures-and-their-buckets-s3')
    // .then(function (response) {
    // setCategoryList(response.data);
    // console.log(categoryList);
    // })}, [])

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