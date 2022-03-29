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
import { CourierManagerPage } from "./pages/personal-spaces-pages/courier-manager/CourierManagerPage";
import { CourierRegPage } from "./pages/personal-spaces-pages/courier-manager/CourierRegPage";
import { ModifyOrganizationInfoPage}  from "./pages/personal-spaces-pages/courier-manager/ModifyOrganizationInfoPage";
import * as UserDataActions from "./redux/actions/UserDataActions";
import * as AuthActions from "./redux/actions/AuthActions";
// import {getCourierManagerInfo} from "./redux/actions/UserDataActions";

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
                UserDataActions.getCourierManagerInfo(user.id)(dispatch).then(() => {
                    navigate("/courier-manager");
                });

            /*case 'customer':
                return; */
            /*default:
                return ;*/
        }
    }
}


export default App;