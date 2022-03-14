import React from "react";

const PRODUCTS = 'PRODUCTS';

export default function setStartCountOfProduct(id){
    if(localStorage.getItem(PRODUCTS) === null || JSON.parse(localStorage.getItem(PRODUCTS)).items.length === 0){
        return 0;
    } else {
        const products = JSON.parse(localStorage.getItem(PRODUCTS));
        for(let i = 0; i < products.items.length; i++){
            if(products.items[i].id === id) {
                return products.items[i].count;
            }
        }
        return  0;
    }
}