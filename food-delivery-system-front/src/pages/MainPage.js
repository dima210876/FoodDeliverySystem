import 'bootstrap/dist/css/bootstrap.min.css'
import CategoryHeader from '../components/MainPageComponents/CategoryHeader';
import CategoryList from '../components/MainPageComponents/CategoryList';
import 'font-awesome/css/font-awesome.css'
import MostPopularHeader from '../components/MainPageComponents/MostPopularHeader';
import React from 'react';

import MostPopularList from '../components/MainPageComponents/MostPopularList';
import OurSalesList from '../components/MainPageComponents/OurSalesList';
import OurSalesHeader from '../components/MainPageComponents/OurSalesHeader';
import Footer from '../components/footer';


const MainPage = (props) => {

    return(
        <>
        <div className='main-page-container'>
            <div className='main-page'>
                <OurSalesHeader />
                <OurSalesList  /*onClickPlus={({title, id, price}) => props.onClickPlus({title, id, price})}*//>
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