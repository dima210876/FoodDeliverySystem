import axios from "axios";
const API_URL = "http://localhost:";
class orderService {
    getRestaurantOrders(restaurantId) {
        return axios
            .get(API_URL + "8083/getAllRestaurantOrders", {
                params: {
                    id: restaurantId
                }
            })
            .then((response) => {
                return response.data;
            });
    }

    getCourierOrders(courierId) {
        return axios
            .get(API_URL + "8084/getCourierOrders", {
                params: {
                    id: courierId
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

    changeCourierOrderStatus(orderId, newStatus) {
        console.log({
            id: orderId,
            orderStatus: newStatus
        });
        return axios
            .post(API_URL + "8084/changeOrderStatus", {
                    id: orderId,
                    orderStatus: newStatus
                }
            );
    }
}

export default new orderService();