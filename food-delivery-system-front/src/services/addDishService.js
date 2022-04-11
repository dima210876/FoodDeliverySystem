import axios from "axios";

const API_URL = "http://localhost:";

class addDishService {

   async addNewDish(dishName, description, price, discount, type, feature, image, ingredients, managerEmail) {
        console.log("image" + image);

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
                },)
            .then((response) => {
                let id = response.data;
                var data = new FormData();
                data.append("image", image);
                data.append("id", id);
                return axios
                    .post(API_URL + "8083/addImage", data,
                        {
                            headers: {
                                'Content-Type': 'multipart/form-data',
                                'Access-Control-Allow-Origin' : '*'
                            }

                        })
                    .then((response) => {
                        return response.data;
                    });
            });
    };
}

export default new addDishService();