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
import { CourierRegPage } from "./pages/personal-spaces-pages/courier-manager/courierRegPage";
import { getRestaurantManagerInfo, getCourierManagerInfo } from "./redux/actions/userDataActions";
import {ProductPage} from "./pages/productPage";
import CourierManagerRegPage from "./pages/personal-spaces-pages/super-admin/courierManagerRegPage";
import {ModifyOrganizationInfoPage} from "./pages/personal-spaces-pages/courier-manager/modifyOrganizationInfoPage";
import RestaurantManagerPage from "./pages/personal-spaces-pages/restaurant-manager/restaurantManagerPage";
import ModifyRestaurantInfoPage from "./pages/personal-spaces-pages/restaurant-manager/modifyRestaurantInfoPage";

function App() {
    const navigate = useNavigate();
    const authData = useSelector(state => state.auth.authData);
    const dispatch = useDispatch();

    return (
        <>
            <Routes>
                <Route path='/' element={<MainPage/>}/>
                <Route path='/restaurant' element={<RestaurantPage/>}/>
                <Route path='/order' element={<OrderConfirmationPage/>}/>
                <Route path='/payment' element={<PaymentPage/>}/>
                <Route path='/account' element={<ChooseRole/>}/>
                <Route path='/products' element={<ProductPage/>}/>
                <Route path='/login' element={<LoginPage/>}/>
                <Route path='/registration' element={<RegistrationPage/>}/>
                <Route path='/admin' element={<AdminPage/>}/>
                <Route path='/admin/restaurant-registration' element={<RestaurantManagerRegPage/>}/>
                <Route path='/admin/delivery-registration' element={<CourierManagerRegPage/>}/>
                <Route path='/courier' element={<CourierPage/>}/>
                <Route path='/courier-manager' element={<CourierManagerPage/>}/>
                <Route path='/courier-manager/courier-registration' element={<CourierRegPage/>}/>
                <Route path='/courier-manager/modify-organization-info' element={<ModifyOrganizationInfoPage/>}/>
                <Route path='/restaurant-manager' element={<RestaurantManagerPage/>}/>
                <Route path='/restaurant-manager/modify-restaurant-info' element={<ModifyRestaurantInfoPage/>}/>
                <Route path='/restaurant-manager/new-dish' element={<NewDishRegPage/>}/>
                <Route path='/restaurant-manager/orders' element={<OrdersListPage/>}/>
                <Route path='/customer' element={<CustomerPage/>}/>
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
                getCourierManagerInfo(authData.user.id, authData.token)(dispatch).then(() => {
                    navigate("/courier-manager");
                });
            case 'ROLE_MANAGER':
                getRestaurantManagerInfo(authData.user.id, authData.token)(dispatch).then(() => {
                    navigate("/restaurant-manager");
                })
            /*case 'customer':
                return; */
            /*default:
                return ;*/
        }
    }
}


export default App;