import React from 'react';
import LoginPage from "./pages/login-registration-pages/LoginPage";
import RegistrationPage from "./pages/login-registration-pages/RegistrationPage";
import {Route, Routes, useNavigate} from "react-router-dom";
import { RestaurantPage } from './pages/main-menu-pages/RestaurantPage';
import RestaurantManagerRegPage from "./pages/personal-spaces-pages/super-admin/RestaurantManagerRegPage";
import { MainPage } from './pages/main-menu-pages/MainPage';
import { OrderPage } from './pages/main-menu-pages/OrderPage';
import { AdminPage} from "./pages/personal-spaces-pages/super-admin/AdminPage";
import {useDispatch, useSelector} from "react-redux";
import {CourierManagerPage} from "./pages/personal-spaces-pages/courier-manager/CourierManagerPage";
import CourierRegPage from "./pages/personal-spaces-pages/courier-manager/CourierRegPage";
import CourierManagerRegPage from "./pages/personal-spaces-pages/super-admin/CourierManagerRegPage";
import { getOrganizationInfo } from "./redux/actions/GetDataActions";
import {ProductPage} from "./pages/productPage";

function App() {

    const dispatch = useDispatch();
    const user = useSelector(state => state.auth.authData.user);
    const navigate = useNavigate();

    return (
        <>
            <Routes>
                <Route path='/' element={<MainPage/>}/>
                <Route path='/restaurant' element={<RestaurantPage/>}/>
                <Route path='/order' element={<OrderPage/>}/>
                <Route path='/account' element={<ChooseRole/>}/>
                <Route path='/products' element={<ProductPage/>}/>
                <Route path='/login' element={<LoginPage/>}/>
                <Route path='/registration' element={<RegistrationPage/>}/>
                <Route path='/admin/restaurant-registration' element={<RestaurantManagerRegPage/>}/>
                <Route path='/admin/delivery-registration' element={<CourierManagerRegPage/>}/>
                <Route path='/courier-manager' element={<CourierManagerPage/>}/>
                <Route path='/courier-manager/courier-registration' element={<CourierRegPage/>}/>
            </Routes>
        </>
    );

    function ChooseRole() {
        return (
            <>
                {
                    renderSwitch()
                }
            </>
        );
    }

    function renderSwitch() {
        switch (user.role) {
            case 'ROLE_SUPER_ADMIN':
                return <AdminPage/>;
            case 'ROLE_COURIER_SERVICE_MANAGER':
                return <CourierManagerPage/>;
            /*default:
                return ;*/
        }
    }
}


export default App;
