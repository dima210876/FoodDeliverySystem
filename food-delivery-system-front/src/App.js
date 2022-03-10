import React, {useState} from 'react';
import LoginPage from "./components/LoginPage/LoginPage";
import RegistrationUser from "./components/RegistrationPage/RegistrationUser";
import {BrowserRouter, Link, Route, Routes} from "react-router-dom";
import RegistrationCourier from "./components/RegistrationPage/RegistrationCourier";
import RegistrationManager from "./components/RegistrationPage/RegistrationManager";

function App() {
  return (
      <BrowserRouter>
        <Routes>
            <Route path='/login' element={<LoginPage />}/>
            <Route path='/registration-user' element={<RegistrationUser />}/>
            <Route path='/registration-courier' element={<RegistrationCourier/>}/>
            <Route path='/registration-manager' element={<RegistrationManager/>}/>
        </Routes>
      </BrowserRouter>
  );
}

export default App;
