import React from "react";

const TOTAL_PRICE = 'TOTAL_PRICE';
const TOTAL_COUNT = 'TOTAL_COUNT';
const PRODUCTS = 'PRODUCTS';
const INCREASE_COUNT_OF_PRODUCT = 'INCREASE_COUNT_OF_PRODUCT';
const DECREASE_COUNT_OF_PRODUCT = 'DECREASE_COUNT_OF_PRODUCT';
const SET_STATE = 'SET_STATE';
const REMOVE_ITEM = 'REMOVE_ITEM';

const decreaseCountOfProducts = (id) => {
    if(localStorage.getItem(TOTAL_PRICE) === null){
        localStorage.setItem(TOTAL_PRICE, JSON.stringify({price: 0}))
    }

    let totalPriceOfAllProducts = JSON.parse(localStorage.getItem(TOTAL_PRICE));
    let totalCountOfAllProducts = JSON.parse(localStorage.getItem(TOTAL_COUNT));
    const products = JSON.parse(localStorage.getItem(PRODUCTS));

    for(let i = 0; i < products.items.length; i++){
        if(products.items[i].id === id) {
            const obj = products.items[i];
            products.items[i].count--;
            if(products.items[i].count === 0){
                products.items.splice(i,1);
            }
            if(totalCountOfAllProducts.count > 0)
                totalCountOfAllProducts.count--;
            totalPriceOfAllProducts.price -= obj.price;
        }
        localStorage.setItem(PRODUCTS, JSON.stringify(products));
    }

    if(totalPriceOfAllProducts.price - 0.01 < 0){
        totalPriceOfAllProducts.price = 0;
    }
    localStorage.setItem(TOTAL_COUNT, JSON.stringify(totalCountOfAllProducts))
    localStorage.setItem(TOTAL_PRICE, JSON.stringify(totalPriceOfAllProducts))
}


function increaseCountOfProducts (item){
    if(localStorage.getItem(TOTAL_COUNT) === null){
        localStorage.setItem(TOTAL_COUNT, JSON.stringify({count: 0}))
    }

    if(localStorage.getItem(TOTAL_PRICE) === null){
        localStorage.setItem(TOTAL_PRICE, JSON.stringify({price: 0}))
    }
    let totalPriceOfAllProducts = JSON.parse(localStorage.getItem(TOTAL_PRICE));
    if(localStorage.getItem(PRODUCTS) === null){
        const object = {
            items: [
                    {id: item.id,
                    title: item.title,
                    price: item.price,
                    imageUrl: item.imageUrl,
                    restaurant: item.restaurant,
                    count: 1}
            ]
        }
        localStorage.setItem(PRODUCTS, JSON.stringify(object));
        totalPriceOfAllProducts.price += object.items[0].price;
    } else {
        const products = JSON.parse(localStorage.getItem(PRODUCTS));
        let isFind = false;
        for(let i = 0; i < products.items.length; i++){
            if(products.items[i].id === item.id) {
                totalPriceOfAllProducts.price += products.items[i].price;
                products.items[i].count++;
                isFind = true;
            }
        }
        if(!isFind){
            const newItem = {
                id: item.id,
                title: item.title,
                price: item.price,
                imageUrl: item.imageUrl,
                restaurant: item.restaurant,
                count: 1
            }
            totalPriceOfAllProducts.price += newItem.price;
            products.items.push(newItem);
        }
        localStorage.setItem(PRODUCTS, JSON.stringify(products));
    }

    if(localStorage.getItem(TOTAL_COUNT) === null){
        localStorage.setItem(TOTAL_COUNT, JSON.stringify({count: 0}))
    }
    let totalCountOfAllProducts = JSON.parse(localStorage.getItem(TOTAL_COUNT));
    totalCountOfAllProducts.count++;
    localStorage.setItem(TOTAL_COUNT, JSON.stringify(totalCountOfAllProducts))
    localStorage.setItem(TOTAL_PRICE, JSON.stringify(totalPriceOfAllProducts))
}

function removeItem(id){
    const products = JSON.parse(localStorage.getItem(PRODUCTS));
    let newProducts = {items: []};
    let newTotalCount = {count: 0};
    let newTotalPrice = {price: 0};
    for (let i = 0; i < products.items.length; i++) {
        if(products.items[i].id !== id){
            newProducts.items.push(products.items[i]);
            newTotalCount.count += products.items[i].count;
            newTotalPrice.price += products.items[i].price * products.items[i].count;
        }
    }
    localStorage.setItem(TOTAL_COUNT, JSON.stringify(newTotalCount));
    localStorage.setItem(TOTAL_PRICE, JSON.stringify(newTotalPrice));
    localStorage.setItem(PRODUCTS, JSON.stringify(newProducts));
}

const initialState = {
    items: [],
    totalCount: 0,
    totalPrice: 0
};

const cart = (state = initialState, action) => {
    switch (action.type) {
        case INCREASE_COUNT_OF_PRODUCT:{
            increaseCountOfProducts(action.payload);
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

            state.totalCount = 0;
            state.totalPrice = 0;
            for (let i = 0; i < state.items.length; i++) {
                if(state.items[i].id === action.payload.id){
                    state.items[i].count++;
                }
                state.totalPrice += state.items[i].count * state.items[i].price;
                state.totalCount += state.items[i].count;
            }

            return {...state, items: [...state.items], totalCount: state.totalCount, totalPrice: state.totalPrice}

        }

        case DECREASE_COUNT_OF_PRODUCT:{
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

            state.totalPrice = 0;
            state.totalCount = 0;
            for (let i = 0; i < state.items.length; i++) {
                state.totalPrice += state.items[i].count * state.items[i].price;
                state.totalCount += state.items[i].count;
            }
            return {...state, items: [...state.items], totalCount: state.totalCount, totalPrice: state.totalPrice}
        }

         case SET_STATE:{
             state.totalPrice = 0;
             state.totalCount = 0;
             for (let i = 0; i < state.items.length; i++) {
                 state.totalPrice += state.items[i].count * state.items[i].price;
                 state.totalCount += state.items[i].count;
             }
            return {...state, items: [...action.payload.id], totalCount: state.totalCount, totalPrice: state.totalPrice};
        }

        case  REMOVE_ITEM:{
            removeItem(action.payload.id);
            let newItems = [];
            state.totalPrice = 0;
            state.totalCount = 0;
            for (let i = 0; i < state.items.length; i++) {
                if(state.items[i].id !== action.payload.id){
                    newItems.push(state.items[i]);
                    state.totalPrice += state.items[i].count * state.items[i].price;
                    state.totalCount += state.items[i].count;
                }
            }
            return {...state, items: [...newItems], totalCount: state.totalCount, totalPrice: state.totalPrice}
        }
      
        default:
            return state;
    }
    
};

export default cart;