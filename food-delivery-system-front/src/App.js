import React from 'react';
import LoginPage from "./pages/login-registration-pages/LoginPage";
import RegistrationPage from "./pages/login-registration-pages/RegistrationPage";
import { Route, Routes} from "react-router-dom";
import { RestaurantPage } from './pages/main-menu-pages/RestaurantPage';
import RestaurantManagerRegPage from "./pages/personal-spaces-pages/super-admin/RestaurantManagerRegPage";
import { MainPage } from './pages/main-menu-pages/MainPage';
import { OrderPage } from './pages/main-menu-pages/OrderPage';
import { AdminPage} from "./pages/personal-spaces-pages/super-admin/AdminPage";
import {useSelector} from "react-redux";
import {CourierManagerPage} from "./pages/personal-spaces-pages/courier-manager/CourierManagerPage";
import CourierRegPage from "./pages/personal-spaces-pages/courier-manager/CourierRegPage";
import {getCourierManagerInfo} from "./redux/actions/UserDataActions";
import ModifyOrganizationInfoPage from "./pages/personal-spaces-pages/courier-manager/ModifyOrganizationInfoPage";

function App() {

    const user = useSelector(state => state.auth.authData.user);

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
                <Route path='/courier-manager' element={<CourierManagerPage/>}/>
                <Route path='/courier-manager/courier-registration' element={<CourierRegPage/>}/>
                <Route path='/courier-manager/modify-organization-info' element={<ModifyOrganizationInfoPage/>}/>
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
                getCourierManagerInfo(user.id);
                return <CourierManagerPage/>;
            /*case 'customer':
                return; */
            /*default:
                return ;*/
        }
    }
}


export default App;