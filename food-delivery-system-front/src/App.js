import React, {useState} from 'react';
import LoginPage from "./pages/login-registration-pages/LoginPage";
import RegistrationPage from "./pages/login-registration-pages/RegistrationPage";
import {BrowserRouter, Link, Route, Routes} from "react-router-dom";

function App() {
  return (
      <BrowserRouter>
        <Routes>
            <Route path='/login' element={<LoginPage />}/>
            <Route path='/registration-user' element={<RegistrationPage />}/>
        </Routes>
      </BrowserRouter>
  );
}

export default App;
