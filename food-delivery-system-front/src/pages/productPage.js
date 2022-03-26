import Footer from '../components/footer';
import "./productPage.css"
import React from "react";
import Navbar from "../components/Navbar";

const ProductPage = () => {
    return(
        <>
            <Navbar/>
            <div className='product-page-container'>
                <div className='product-page'>
                 hello
                </div>
            </div>
            <Footer />
        </>
    );
}

export {ProductPage}