import React from 'react';
import LoginPage from "./pages/login-registration-pages/LoginPage";
import RegistrationPage from "./pages/login-registration-pages/RegistrationPage";
import { Route, Routes} from "react-router-dom";
import { RestaurantPage } from './pages/RestaurantPage';
import RestaurantManagerRegPage from "./pages/admin-personal-space-pages/RestaurantManagerRegPage";
import { MainPage } from './pages/MainPage';
import { OrderPage } from './pages/OrderPage';
import { AdminPage} from "./pages/AdminPage";
import {useSelector} from "react-redux";

function App() {

    const user = useSelector(state => state.auth.auth.user);

    return (
        <>
            <Routes>
                <Route path='/' element={<MainPage/>}/>
                <Route path='/restaurant' element={<RestaurantPage/>}/>
                <Route path='/order' element={<OrderPage/>}/>
                <Route path='/account' element={<ChooseRole/>}/>
                <Route path='/login' element={<LoginPage/>}/>
                <Route path='/registration' element={<RegistrationPage/>}/>
                <Route path='/admin/restaurant-registration' element={<RestaurantManagerRegPage/>}/>
            </Routes>
        </>
    );

    function ChooseRole() {
        return (
            <>
                {renderSwitch()}
            </>
        );
    }

    function renderSwitch() {
        switch (user.role) {
            case 'admin':
                return <AdminPage/>;
            /*case 'customer':
                return; */
            /*default:
                return ;*/
        }
    }
}


export default App;