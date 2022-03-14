import React from "react";

export default function setStartCountOfProduct(id){
    if(localStorage.getItem('PRODUCTS_WITH_DISCOUNT') === null || JSON.parse(localStorage.getItem('PRODUCTS_WITH_DISCOUNT')).items.length === 0){
        return 0;
    } else {
        const products = JSON.parse(localStorage.getItem('PRODUCTS_WITH_DISCOUNT'));
        for(let i = 0; i < products.items.length; i++){
            if(products.items[i].id === id) {
                return products.items[i].count;
            }
        }
        return  0;
    }
}