import Footer from '../components/footer';
import "./productPage.css"
import React from "react";
import Navbar from "../components/Navbar";
import ProductPageHeader from "../components/product_page_components/productPageHeader";

const ProductPage = () => {
    return(
        <>
            <Navbar/>
            <div className='product-page-container'>
                <div className='product-page'>
                     <ProductPageHeader/>
                </div>
            </div>
            <Footer />
        </>
    );
}

export {ProductPage}