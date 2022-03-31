import Footer from '../components/footer';
import "./productPage.css"
import React from "react";
import Navbar from "../components/navbar";
import ProductPageHeader from "../components/product_page_components/productPageHeader";
import ProductPageList from "../components/product_page_components/productPageList";

const ProductPage = () => {
    return(
        <>
            <Navbar/>
            <div className='product-page-container'>
                <div className='product-page'>
                    <ProductPageHeader/>
                    <ProductPageList/>
                </div>
            </div>
            <Footer />
        </>
    );
}

export {ProductPage}