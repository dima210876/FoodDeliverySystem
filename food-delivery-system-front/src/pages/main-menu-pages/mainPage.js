import 'bootstrap/dist/css/bootstrap.min.css'
import CategoryHeader from '../../components/MainPageComponents/categoryHeader';
import CategoryList from '../../components/MainPageComponents/categoryList';
import 'font-awesome/css/font-awesome.css'
import MostPopularHeader from '../../components/MainPageComponents/mostPopularHeader';
import React from 'react';

import MostPopularList from '../../components/MainPageComponents/mostPopularList';
import OurSalesList from '../../components/MainPageComponents/ourSalesList';
import OurSalesHeader from '../../components/MainPageComponents/ourSalesHeader';
import Footer from '../../components/footer';
import "./mainPage.css"
import Navbar from "../../components/navbar";


const MainPage = () => {
    return(
        <>
            <Navbar/>
            <div className='main-page-container'>
                <div className='main-page'>
                    <OurSalesHeader />
                    <OurSalesList  />
                    <MostPopularHeader />
                    <MostPopularList />
                    <CategoryHeader />
                    <CategoryList />
                </div>
            </div>
            <Footer />
        </> 
    );
}

export {MainPage}