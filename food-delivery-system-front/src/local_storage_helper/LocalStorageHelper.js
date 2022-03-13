import React from "react";

/*const [countOfProducts, setCountOfProducts] = React.useState(setStartCountOfProduct(props.id));
const [totalCount, setTotalCount] = React.useState(setStartTotalCount());*/

/*const increaseTotalCount = () => {
    setTotalCount(totalCount+1);
}

const decreaseTotalCount = () => {
    if(totalCount > 0)
        setTotalCount(totalCount-1);
}*/

/*function setStartTotalCount(){
    if(localStorage.getItem('PRODUCTS_WITH_DISCOUNT') === null){
        return 0;
    } else {
        const productsWithDiscount = JSON.parse(localStorage.getItem('PRODUCTS_WITH_DISCOUNT'));
        let sum = 0;
        for(let i = 0; i < productsWithDiscount.items.length; i++){
            sum += productsWithDiscount.items[i].count;
        }
        return  sum;
    }
}

const decreaseCountOfProducts = (id, decreaseTotalCount) => {
    decreaseTotalCount();
    if(countOfProducts > 0)
        setCountOfProducts(countOfProducts-1);
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

*/

export default function setStartCountOfProduct(id){
    if(localStorage.getItem('PRODUCTS_WITH_DISCOUNT') === null || JSON.parse(localStorage.getItem('PRODUCTS_WITH_DISCOUNT')).items.length === 0){
        return 0;
    } else {
        const productsWithDiscount = JSON.parse(localStorage.getItem('PRODUCTS_WITH_DISCOUNT'));
        for(let i = 0; i < productsWithDiscount.items.length; i++){
            if(productsWithDiscount.items[i].id === id) {
                return productsWithDiscount.items[i].count;
            }
        }
        return  0;
    }
}

/*const decreaseCountOfProducts = (id) => {
    if(countOfProducts > 0)
        setCountOfProducts(countOfProducts-1);
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

const increaseCountOfProducts = (id, title, price, imageUrl, discount) => {
    setCountOfProducts(countOfProducts+1);
    if(localStorage.getItem('PRODUCTS_WITH_DISCOUNT') === null){

        const object = {
            items: [
                {id: id,
                    title: title,
                    price: price,
                    imageUrl: imageUrl,
                    discount: discount,
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
                discount: discount,
                count: 1
            }
            productsWithDiscount.items.push(newItem);
        }
        localStorage.setItem('PRODUCTS_WITH_DISCOUNT', JSON.stringify(productsWithDiscount));
    }
}*/

/*function setStartCountOfProduct(id){
    if(localStorage.getItem('PRODUCTS_WITH_DISCOUNT') === null){
        return 0;
    } else {
        const productsWithDiscount = JSON.parse(localStorage.getItem('PRODUCTS_WITH_DISCOUNT'));
        for(let i = 0; i < productsWithDiscount.items.length; i++){
            if(productsWithDiscount.items[i].id === id) {
                return productsWithDiscount.items[i].count;
            }
        }
        return  0;
    }
}*/


