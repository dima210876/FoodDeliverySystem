import ProductPageCard from "./productPageCard";
import React, {useState} from "react";
import axios from "axios";
import {useSelector} from "react-redux";
import setStartCountOfProduct from "../../local_storage_helper/LocalStorageHelper";
import "./productPageList.css"
import './paginationBar.css'
import './sortBar.css'

/*var productPageList = [{"title":"Salat","price":4, "imageUrl":"/img/food.png"},
                         {"title":"Burger","price":5,"imageUrl":"/img/food.png"},
                         {"title":"Rolls","price":11,"imageUrl":"/img/food.png"},
                         {"title":"Chocolate mues","price":9, "imageUrl":"/img/food.png"}];*/

const COUNT_OF_CARD_ON_PAGE = 16;
const ACTION_MINUS = 'minus';
const ACTION_PLUS = 'plus';
const EMPTY_ACTION = '';
const SORT_TYPE_PRICE = 'price';
const SORT_TYPE_NAME = 'name';
const endpointName = 'http://localhost:8083/getItems';

function ProductPageList(){

    const [productPageList, setProductPageList] = React.useState([]);
    const [currentPage, setCurrentPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    const [priceSort, setPriceSort] = useState(true);
    const [titleSort, setTitleSort] = useState(false);
    const [vectorOfSort, setVectorOfSort] = useState(true);
    const [typeOfSort, setTypeOfSort] = useState(SORT_TYPE_PRICE);
    const items = useSelector(state => state.cart.items);
    const title = useSelector(state => state.category.title);

    function makeRequest(action, vector, sortColumn){
        let page = currentPage;
        if(action === ACTION_PLUS)
            page = currentPage + 1;
        if(action === ACTION_MINUS)
            page = currentPage - 1;
        axios.get(endpointName, {params: {
                category: title,
                page: page,
                size:COUNT_OF_CARD_ON_PAGE,
                sortColumn: sortColumn,
                vectorOfSort: vector
            }})
            .then(function (response) {
                getProductPageList(response.data.content);
            })
    }

    const decreasePageNumber = () => {
        if(currentPage > 0 ){
            setCurrentPage(currentPage - 1);
            console.log(currentPage);
            makeRequest(ACTION_MINUS, vectorOfSort, typeOfSort);
        }
    }

    const increasePageNumber = () => {
        if(currentPage + 1 < totalPages){
            setCurrentPage(currentPage + 1);
            console.log(currentPage);
            makeRequest(ACTION_PLUS, vectorOfSort, typeOfSort);
        }
    }

    function checkItems(id){
        if(items.filter(item => item.id === id).length === 0){
            return setStartCountOfProduct(id);
        } else {
            return items.filter(item => item.id === id).count;
        }
    }

    function getProductPageList(products){
        let listForProductsPage = [];
        products.map((item) => {
            listForProductsPage.push({id: item.id,
                                      title: item.name,
                                      price: item.price,
                                      restaurant: item.restaurant});
        });
        setProductPageList(listForProductsPage);
        console.log(productPageList);
    }

    React.useEffect(() => {
        axios.get(endpointName, {params: {
                category: title,
                page: currentPage,
                size:COUNT_OF_CARD_ON_PAGE,
                sortColumn: typeOfSort,
                vectorOfSort: vectorOfSort
            }})
            .then(function (response) {
                getProductPageList(response.data.content);
                setTotalPages(response.data.totalPages);
            })
    }, [])

    const sortByPrice = () => {
        const currentVectorOfSort = !priceSort;
        setPriceSort(currentVectorOfSort);
        setVectorOfSort(currentVectorOfSort);
        setTypeOfSort(SORT_TYPE_PRICE);
        makeRequest(EMPTY_ACTION, currentVectorOfSort, SORT_TYPE_PRICE);
    }

    const sortByTitle = () => {
        const currentVectorOfSort = !titleSort;
        setTitleSort(currentVectorOfSort);
        setVectorOfSort(currentVectorOfSort);
        setTypeOfSort(SORT_TYPE_NAME);
        makeRequest(EMPTY_ACTION, currentVectorOfSort, SORT_TYPE_NAME);
    }

    return(
        <>
            <div className="sort-bar">
                <div><span className="sort-text">Sort by:</span></div>
                <div><button className="button-sort" onClick={sortByPrice}>price</button></div>
                <div><button className="button-sort" onClick={sortByTitle}>title</button></div>
            </div>
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
            <div className="pagination-bar">
                <div><button className="button-move-on-page" onClick={decreasePageNumber}>back</button></div>
                <div className='div-current-page'><span className="current-page">{currentPage + 1} out of {totalPages}</span></div>
                <div><button className="button-move-on-page" onClick={increasePageNumber}>forward</button></div>
            </div>
        </>
    );
}

export default ProductPageList;