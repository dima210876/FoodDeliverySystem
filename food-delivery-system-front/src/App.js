import React from "react";
import { Routes, Route } from 'react-router-dom';
import { RestaurantPage } from './pages/RestaurantPage';
import { MainPage } from './pages/MainPage';
import { OrderPage } from './pages/OrderPage';
import Navbar from "./components/Navbar";
import LoginPage from "./pages/login-registration-pages/LoginPage";
import RegistrationPage from "./pages/login-registration-pages/RegistrationPage";

function App() {
  return (
    <>
      <Navbar/>
      <Routes>
        <Route path='/' element={<MainPage/>}/>
        <Route path='/restaurant' element={<RestaurantPage/>}/>
        <Route path='/order' element={<OrderPage/>}/>
          <Route path='/login' element={<LoginPage />}/>
          <Route path='/registration' element={<RegistrationPage />}/>
      </Routes>
    </>
  );
}

export default App;
>>>>>>> origin/JL2-11
