import axios from "axios";

const API_URL = "http://localhost:";

class addDishService {

    addNewDish(dishName, description, price, discount, type, feature, image, ingredients, managerEmail) {
        console.log(image);
        return axios
            .post(API_URL + "8083/newItem", {
                name: dishName,
                description: description,
                price: price,
                discount: discount,
                available: true,
                itemType: type,
                feature: feature,
                managerEmail: managerEmail,
                ingredients: ingredients,
                image: image

            })
            .then((response) => {
                return response.data;
            });
    };
}

export default new addDishService();