import {useSelector} from "react-redux";
import React from "react";


const decreaseCountOfProducts = (id) => {
    const productsWithDiscount = JSON.parse(localStorage.getItem('PRODUCTS_WITH_DISCOUNT'));
    for(let i = 0; i < productsWithDiscount.items.length; i++){
        if(productsWithDiscount.items[i].id === id) {
            productsWithDiscount.items[i].count--;
            if(productsWithDiscount.items[i].count === 0){
                productsWithDiscount.items.splice(i,1);
            }
        }
        localStorage.setItem('PRODUCTS_WITH_DISCOUNT', JSON.stringify(productsWithDiscount));
    }
}


function increaseCountOfProducts (id, title, price, imageUrl, restaurant){
    if(localStorage.getItem('PRODUCTS_WITH_DISCOUNT') === null){
        const object = {
            items: [
                {id: id,
                    title: title,
                    price: price,
                    imageUrl: imageUrl,
                    restaurant: restaurant,
                    count: 1}
            ]
        }
        localStorage.setItem('PRODUCTS_WITH_DISCOUNT', JSON.stringify(object));

    } else {
        const productsWithDiscount = JSON.parse(localStorage.getItem('PRODUCTS_WITH_DISCOUNT'));
        let isFind = false;
        for(let i = 0; i < productsWithDiscount.items.length; i++){
            if(productsWithDiscount.items[i].id === id) {
                productsWithDiscount.items[i].count++;
                isFind = true;
            }
        }
        if(!isFind){
            const newItem = {
                id: id,
                title: title,
                price: price,
                imageUrl: imageUrl,
                restaurant: restaurant,
                count: 1
            }
            productsWithDiscount.items.push(newItem);
        }
        localStorage.setItem('PRODUCTS_WITH_DISCOUNT', JSON.stringify(productsWithDiscount));
    }
}

const initialState = {
    items: []
};

const cart = (state = initialState, action) => {
    switch (action.type) {
        case 'INCREASE_COUNT_OF_PRODUCT':{
            increaseCountOfProducts(action.payload.id, action.payload.title, action.payload.price, action.payload.imageUrl, action.payload.restaurant);
            const newItems = state.items.filter(item => item.id === action.payload.id);
            if(newItems.length === 0){
                const obj = {
                    id: action.payload.id,
                    title: action.payload.title,
                    price: action.payload.price,
                    imageUrl: action.payload.imageUrl,
                    restaurant: action.payload.restaurant,
                    count: action.payload.count+1
                }
                return {...state, items: [...state.items, obj]};
            }

            for (let i = 0; i < state.items.length; i++) {
                if(state.items[i].id === action.payload.id){
                    state.items[i].count++;
                }
            }

            return {...state, items: [...state.items]}

        }

        case 'DECREASE_COUNT_OF_PRODUCT':{
            decreaseCountOfProducts(action.payload.id);
            for (let i = 0; i < state.items.length; i++) {
                if(state.items[i].id === action.payload.id){
                    if(state.items[i].count > 1){
                        state.items[i].count--;
                    } else {
                        state.items.splice(i, 1);
                    }
                }
            }
            return {...state, items: [...state.items]}
        }

         case 'SET_STATE':{
            //console.log(action.payload.id + 'мда')
            return {...state, items: [...action.payload.id]};
        }
      
        default:
            return state;
    }
    
};

export default cart;