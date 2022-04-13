import ProductPageCard from "./productPageCard";
import React, {useState} from "react";
import axios from "axios";
import {useSelector} from "react-redux";
import setStartCountOfProduct from "../../local_storage_helper/localStorageHelper";
import "./productPageList.css"
import './paginationBar.css'
import './sortBar.css'

/*var productPageList = [{"title":"Salat","price":4, "imageUrl":"/img/food.png"},
                         {"title":"Burger","price":5,"imageUrl":"/img/food.png"},
                         {"title":"Rolls","price":11,"imageUrl":"/img/food.png"},
                         {"title":"Chocolate mues","price":9, "imageUrl":"/img/food.png"}];*/

const COUNT_OF_CARD_ON_PAGE = 8;
const ACTION_MINUS = 'minus';
const ACTION_PLUS = 'plus';
const EMPTY_ACTION = '';
const EMPTY_FILTER = '';
const SORT_TYPE_PRICE = 'price';
const SORT_TYPE_NAME = 'name';
const START_MIN_PRICE = 0;
const START_MAX_PRICE = 100000000;
const endpointName = 'http://localhost:8083/getItems';

function ProductPageList(){

    const [productPageList, setProductPageList] = React.useState([]);
    const [listEmptyCard, setListEmptyCard] = useState(false);
    const [currentPage, setCurrentPage] = useState(0);
    const [totalPages, setTotalPages] = useState(1);
    const [priceSort, setPriceSort] = useState(true);
    const [titleSort, setTitleSort] = useState(false);
    const [vectorOfSort, setVectorOfSort] = useState(true);
    const [typeOfSort, setTypeOfSort] = useState(SORT_TYPE_PRICE);
    const items = useSelector(state => state.cart.items);
    const title = useSelector(state => state.category.title);
    const [nameFilter, setNameFilter] = useState(EMPTY_FILTER);
    const [restaurantFilter, setRestaurantFilter, ] = useState(EMPTY_FILTER);
    const [minPriceFilter, setMinPriceFilter] = useState(START_MIN_PRICE);
    const [maxPriceFilter, setMaxPriceFilter] = useState(START_MAX_PRICE);

    function makeRequest(filterRestaurant, filterMinPrice, filterMaxPrice, filterName, action, vector, sortColumn){
        let page = currentPage;
        if((filterName !== EMPTY_FILTER && nameFilter === EMPTY_FILTER)
        || (filterRestaurant !== EMPTY_FILTER && restaurantFilter === EMPTY_FILTER)
        || (filterMinPrice !== START_MIN_PRICE && minPriceFilter === START_MIN_PRICE)
        || (filterMaxPrice !== START_MAX_PRICE && maxPriceFilter === START_MAX_PRICE)){
            page = 0;
            setCurrentPage(0);
        } else {
            if (action === ACTION_PLUS)
                page = currentPage + 1;
            if (action === ACTION_MINUS)
                page = currentPage - 1;
        }
        axios.get(endpointName, {params: {
                category: title,
                page: page,
                size:COUNT_OF_CARD_ON_PAGE,
                sortColumn: sortColumn,
                vectorOfSort: vector,
                filterName: filterName,
                filterMinPrice: filterMinPrice,
                filterMaxPrice: filterMaxPrice,
                filterRestaurant: filterRestaurant
            }})
            .then(function (response) {
                setListEmptyCard(false);
                if(response.data.totalPages === 0)
                    setTotalPages(1);
                else
                    setTotalPages(response.data.totalPages);
                getProductPageList(response.data.content);
            }).catch(() => setListEmptyCard(true))
    }

    const decreasePageNumber = () => {
        if(currentPage > 0 ){
            setCurrentPage(currentPage - 1);
            console.log(currentPage);
            makeRequest(restaurantFilter, minPriceFilter, maxPriceFilter, nameFilter, ACTION_MINUS, vectorOfSort, typeOfSort);
        }
    }

    const increasePageNumber = () => {
        if(currentPage + 1 < totalPages){
            setCurrentPage(currentPage + 1);
            console.log(currentPage);
            makeRequest(restaurantFilter, minPriceFilter, maxPriceFilter, nameFilter, ACTION_PLUS, vectorOfSort, typeOfSort);
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
                                      restaurant: item.restaurant,
                                      restaurantId: item.restaurantId,
                                      image: item.image});
        });
        setProductPageList(listForProductsPage);
        console.log(listForProductsPage);
        if(listForProductsPage.length === 0)
            setListEmptyCard(true);
        else
            setListEmptyCard(false);
    }

    React.useEffect(() => {
        axios.get(endpointName, {params: {
                category: title,
                page: currentPage,
                size:COUNT_OF_CARD_ON_PAGE,
                sortColumn: typeOfSort,
                vectorOfSort: vectorOfSort,
                filterName: nameFilter,
                filterMinPrice: minPriceFilter,
                filterMaxPrice: maxPriceFilter,
                filterRestaurant: restaurantFilter
            }})
            .then(function (response) {
                setListEmptyCard(false);
                getProductPageList(response.data.content);
                if(response.data.totalPages === 0)
                    setTotalPages(1);
                else
                    setTotalPages(response.data.totalPages);
            }).catch(() => setListEmptyCard(true))
    }, [])

    const sortByPrice = () => {
        const currentVectorOfSort = !priceSort;
        setPriceSort(currentVectorOfSort);
        setVectorOfSort(currentVectorOfSort);
        setTypeOfSort(SORT_TYPE_PRICE);
        makeRequest(restaurantFilter, minPriceFilter, maxPriceFilter, nameFilter, EMPTY_ACTION, currentVectorOfSort, SORT_TYPE_PRICE);
    }

    const sortByTitle = () => {
        const currentVectorOfSort = !titleSort;
        setTitleSort(currentVectorOfSort);
        setVectorOfSort(currentVectorOfSort);
        setTypeOfSort(SORT_TYPE_NAME);
        makeRequest(restaurantFilter, minPriceFilter, maxPriceFilter, nameFilter, EMPTY_ACTION, currentVectorOfSort, SORT_TYPE_NAME);
    }

    function changeNameFilter(event) {
        setNameFilter(event.target.value);
        makeRequest(restaurantFilter, minPriceFilter, maxPriceFilter, event.target.value, EMPTY_ACTION, vectorOfSort, typeOfSort)
    }

    function changeRestaurantFilter(event) {
        setRestaurantFilter(event.target.value);
        makeRequest(event.target.value, minPriceFilter, maxPriceFilter, nameFilter, EMPTY_ACTION, vectorOfSort, typeOfSort)
    }

    function changeMinPriceFilter(event) {
        if(event.target.value.length === 0){
            setMinPriceFilter(START_MIN_PRICE);
            makeRequest(restaurantFilter, START_MIN_PRICE, maxPriceFilter, nameFilter, EMPTY_ACTION, vectorOfSort, typeOfSort)
        } else {
            setMinPriceFilter(event.target.value);
            makeRequest(restaurantFilter, event.target.value, maxPriceFilter, nameFilter, EMPTY_ACTION, vectorOfSort, typeOfSort)
        }
    }

    function changeMaxPriceFilter(event) {
        if(event.target.value.length === 0){
            setMaxPriceFilter(START_MAX_PRICE);
            makeRequest(restaurantFilter, minPriceFilter, START_MAX_PRICE,  nameFilter, EMPTY_ACTION, vectorOfSort, typeOfSort)
        } else {
            setMaxPriceFilter(event.target.value);
            makeRequest(restaurantFilter, minPriceFilter, event.target.value,  nameFilter, EMPTY_ACTION, vectorOfSort, typeOfSort)
        }
    }




    return(
        <>
            <div className="sort-bar">
                <div><span className="sort-text">Sort by:</span></div>
                <div><button className="button-sort" onClick={sortByPrice}>price</button></div>
                <div><button className="button-sort" onClick={sortByTitle}>title</button></div>
            </div>
            <div className="filter-bar">
                <div><span className="sort-text">Filter by:</span></div>
                <input className='name-filter' value={nameFilter} onChange={changeNameFilter} placeholder='dish title'/>
                <input className='restaurant-filter' value={restaurantFilter} onChange={changeRestaurantFilter} placeholder='restaurant name'/>
                <input className='min-price-filter' onChange={changeMinPriceFilter} min={0} placeholder='min price' type='number' />
                <input className='max-price-filter' onChange={changeMaxPriceFilter} min={0} placeholder='max price' type='number' />
            </div>
            {listEmptyCard ? <div className='no-found'>No {title} found</div> : null}
            <div className="product-page-list">
                {productPageList.map((obj) => (
                    <ProductPageCard
                        id={obj.id}
                        title={obj.title}
                        price={obj.price}
                        image={obj.image}
                        count={checkItems(obj.id)}
                        restaurant={obj.restaurant}
                        restaurantId={obj.restaurantId}
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