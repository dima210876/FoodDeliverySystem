import Footer from '../components/footer';
import "./productPage.css"
import React from "react";
import Navbar from "../components/Navbar";
import ProductPageHeader from "../components/product_page_components/productPageHeader";
import SortBar from "../components/product_page_components/sortBar";
import ProductPageList from "../components/product_page_components/productPageList";
import PaginationBar from "../components/product_page_components/paginationBar";

const ProductPage = () => {
    return(
        <>
            <Navbar/>
            <div className='product-page-container'>
                <div className='product-page'>
                     <ProductPageHeader/>
                    <SortBar/>
                    <ProductPageList/>
                    <PaginationBar/>
                </div>
            </div>
            <Footer />
        </>
    );
}

export {ProductPage}