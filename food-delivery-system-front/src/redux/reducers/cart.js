import {useSelector} from "react-redux";
import React from "react";


const decreaseCountOfProducts = (id) => {
    if(localStorage.getItem('TOTAL_PRICE') === null){
        localStorage.setItem('TOTAL_PRICE', JSON.stringify({price: 0}))
    }
    let totalPriceOfAllProducts = JSON.parse(localStorage.getItem('TOTAL_PRICE'));
    let totalCountOfAllProducts = JSON.parse(localStorage.getItem('TOTAL_COUNT'));
    const productsWithDiscount = JSON.parse(localStorage.getItem('PRODUCTS_WITH_DISCOUNT'));
    for(let i = 0; i < productsWithDiscount.items.length; i++){
        if(productsWithDiscount.items[i].id === id) {
            const obj = productsWithDiscount.items[i];
            productsWithDiscount.items[i].count--;
            if(productsWithDiscount.items[i].count === 0){
                productsWithDiscount.items.splice(i,1);
            }
            if(totalCountOfAllProducts.count > 0)
                totalCountOfAllProducts.count--;
            totalPriceOfAllProducts.price -= obj.price;
        }
        localStorage.setItem('PRODUCTS_WITH_DISCOUNT', JSON.stringify(productsWithDiscount));
    }
    localStorage.setItem('TOTAL_COUNT', JSON.stringify(totalCountOfAllProducts))
    if(totalPriceOfAllProducts.price - 0.01 < 0){
        totalPriceOfAllProducts.price = 0;
    }
    localStorage.setItem('TOTAL_PRICE', JSON.stringify(totalPriceOfAllProducts))
}


function increaseCountOfProducts (id, title, price, imageUrl, restaurant){
    if(localStorage.getItem('TOTAL_PRICE') === null){
        localStorage.setItem('TOTAL_PRICE', JSON.stringify({price: 0}))
    }
    let totalPriceOfAllProducts = JSON.parse(localStorage.getItem('TOTAL_PRICE'));
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
        totalPriceOfAllProducts.price += object.items[0].price;
    } else {
        const productsWithDiscount = JSON.parse(localStorage.getItem('PRODUCTS_WITH_DISCOUNT'));
        let isFind = false;
        for(let i = 0; i < productsWithDiscount.items.length; i++){
            if(productsWithDiscount.items[i].id === id) {
                totalPriceOfAllProducts.price += productsWithDiscount.items[i].price;
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
            totalPriceOfAllProducts.price += newItem.price;
            productsWithDiscount.items.push(newItem);
        }
        localStorage.setItem('PRODUCTS_WITH_DISCOUNT', JSON.stringify(productsWithDiscount));
    }
    if(localStorage.getItem('TOTAL_COUNT') === null){
        localStorage.setItem('TOTAL_COUNT', JSON.stringify({count: 0}))
    }
    let totalCountOfAllProducts = JSON.parse(localStorage.getItem('TOTAL_COUNT'));
    totalCountOfAllProducts.count++;
    localStorage.setItem('TOTAL_COUNT', JSON.stringify(totalCountOfAllProducts))
    localStorage.setItem('TOTAL_PRICE', JSON.stringify(totalPriceOfAllProducts))
}

function removeItem(id){
    const productsWithDiscount = JSON.parse(localStorage.getItem('PRODUCTS_WITH_DISCOUNT'));
    let newProducts = {items: []};
    let newTotalCount = {count: 0};
    let newTotalPrice = {price: 0};
    for (let i = 0; i < productsWithDiscount.items.length; i++) {
        if(productsWithDiscount.items[i].id !== id){
            newProducts.items.push(productsWithDiscount.items[i]);
            newTotalCount.count += productsWithDiscount.items[i].count;
            newTotalPrice.price += productsWithDiscount.items[i].price * productsWithDiscount.items[i].count;
        }
    }
    localStorage.setItem('TOTAL_COUNT', JSON.stringify(newTotalCount));
    localStorage.setItem('TOTAL_PRICE', JSON.stringify(newTotalPrice));
    localStorage.setItem('PRODUCTS_WITH_DISCOUNT', JSON.stringify(newProducts));
}

const initialState = {
    items: [],
    totalCount: 0,
    totalPrice: 0
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

            state.totalPrice = 0;
            state.totalCount = 0;
            for (let i = 0; i < state.items.length; i++) {
                state.totalPrice += state.items[i].count * state.items[i].price;
                state.totalCount += state.items[i].count;
            }
            return {...state, items: [...state.items], totalCount: state.totalCount, totalPrice: state.totalPrice}
        }

         case 'SET_STATE':{
             state.totalPrice = 0;
             state.totalCount = 0;
             for (let i = 0; i < state.items.length; i++) {
                 state.totalPrice += state.items[i].count * state.items[i].price;
                 state.totalCount += state.items[i].count;
             }
            return {...state, items: [...action.payload.id], totalCount: state.totalCount, totalPrice: state.totalPrice};
        }

        case  'REMOVE_ITEM':{
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