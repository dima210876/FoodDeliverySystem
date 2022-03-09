import React from "react";
import { Routes, Route } from 'react-router-dom';
import { RestaurantPage } from './pages/RestaurantPage';
import { MainPage } from './pages/MainPage';
import { OrderPage } from './pages/OrderPage';
import Navbar from "./components/Navbar";
import {connect} from 'react-redux';
import store from './redux/store';
import { addProductToCart } from "./redux/actions/card";

function App() {

 const handleAddProductToCart = (obj) => {
    store.dispatch({
        type: 'ADD_PRODUCT_TO_CART',
        payload: obj
    })
}

const addChooseProductToCart = ({title, id, price}) => {
    const object = {
        title,
        id, 
        price
    };
    handleAddProductToCart(object);
}

  return (
    <>
      <Navbar/>
      <Routes>
        <Route path='/' element={<MainPage onClickPlus={({title, id, price}) => addChooseProductToCart({title, id, price})}/>}/>
        <Route path='/restaurant' element={<RestaurantPage/>}/>
        <Route path='/order' element={<OrderPage/>}/>
      </Routes>
    </>
  );
}



const mapStateToProps = (state) => { 
  console.log(state);
  return { items: state.cart.items }; 
};

const mapDispatchProps = dispatch => {
  return {
    addProductToCart: (items) => dispatch(addProductToCart(items))
  };
}

export default connect(mapStateToProps, mapDispatchProps)(App);