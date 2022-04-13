import axios from "axios";
const API_URL = "http://localhost:";
class orderService {
    getRestaurantOrders(restaurantId) {
        return axios
            .get(API_URL + "8083/getAllOrders", {
                params: {
                    id: restaurantId
                }
            })
            .then((response) => {
                return response.data;
            });
    }

    changeOrderStatus(orderId, newStatus) {
        return axios
            .post(API_URL + "8083/changeOrderStatus", {
                id: orderId,
                restaurantStatus: newStatus
            }
        );
    }
}

export default new orderService();