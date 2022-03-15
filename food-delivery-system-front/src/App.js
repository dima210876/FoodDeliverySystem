import React, {useState} from 'react';
import LoginPage from "./pages/login-registration-pages/LoginPage";
import RegistrationPage from "./pages/login-registration-pages/RegistrationPage";
import {BrowserRouter, Link, Route, Routes} from "react-router-dom";
import CourierRegistrationPage from "./pages/admin-account-pages/RestaurantRegistrationPage";
import RestaurantRegistrationPage from "./pages/admin-account-pages/RestaurantRegistrationPage";

function App() {
  return (
      <BrowserRouter>
        <Routes>
            <Route path='/login' element={<LoginPage />}/>
            <Route path='/registration' element={<RegistrationPage />}/>
            <Route path='/admin/restaurant-registration' element={<RestaurantRegistrationPage />}/>
        </Routes>
      </BrowserRouter>
  );
}

export default App;
