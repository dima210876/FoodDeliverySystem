import React from 'react';
import LoginPage from "./pages/login-registration-pages/loginPage";
import RegistrationPage from "./pages/login-registration-pages/registrationPage";
import {Route, Routes, useNavigate} from "react-router-dom";
import { RestaurantPage } from './pages/main-menu-pages/restaurantPage';
import RestaurantManagerRegPage from "./pages/personal-spaces-pages/super-admin/restaurantManagerRegPage";
import { MainPage } from './pages/main-menu-pages/mainPage';
import { OrderPage } from './pages/main-menu-pages/orderPage';
import { AdminPage} from "./pages/personal-spaces-pages/super-admin/adminPage";
import {useDispatch, useSelector} from "react-redux";
import {CourierManagerPage} from "./pages/personal-spaces-pages/courier-manager/courierManagerPage";
import CourierRegPage from "./pages/personal-spaces-pages/courier-manager/courierRegPage";
import CourierManagerRegPage from "./pages/personal-spaces-pages/super-admin/courierManagerRegPage";
import {ProductPage} from "./pages/productPage";
import * as GetDataActions from "./redux/actions/userDataActions"
function App() {

    const dispatch = useDispatch();
    const authData = useSelector(state => state.auth.authData);
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
        switch (authData.user.role) {
            case 'ROLE_SUPER_ADMIN':
                return <AdminPage/>;
            case 'ROLE_COURIER_SERVICE_MANAGER':
                GetDataActions.getOrganizationInfo(authData.user.id, authData.token)(dispatch).then(() => {
                    navigate("/courier-manager");
                });
            /*default:
                return ;*/
        }
    }
}


export default App;
