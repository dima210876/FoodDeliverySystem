import React from "react";
import { Routes, Route } from 'react-router-dom';
import { RestaurantPage } from './pages/RestaurantPage';
import { MainPage } from './pages/MainPage';
import { OrderPage } from './pages/OrderPage';
import Navbar from "./components/Navbar";

function App() {
  return (
    <>
      <Navbar/>
      <Routes>
        <Route path='/main' element={<MainPage/>}/>
        <Route path='/restaurant' element={<RestaurantPage/>}/>
        <Route path='/order' element={<OrderPage/>}/>
      </Routes>
    </>
  );
}

export default App;