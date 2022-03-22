import React, {useState} from 'react';
import LoginPage from "./pages/login-registration-pages/LoginPage";
import RegistrationPage from "./pages/login-registration-pages/RegistrationPage";
import RestaurantManagerRegPage from "./pages/admin-personal-space-pages/RestaurantManagerRegPage";
import {BrowserRouter, Link, Route, Routes} from "react-router-dom";

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path='/login' element={<LoginPage />}/>
                <Route path='/registration' element={<RegistrationPage />}/>
                <Route path='/admin/restaurant-registration' element={<RestaurantManagerRegPage />}/>
            </Routes>
        </BrowserRouter>
    );
}

export default App;
