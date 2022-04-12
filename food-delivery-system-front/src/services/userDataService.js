import axios from "axios";
const API_URL = "http://localhost:";
class UserDataService {
    getCustomerInfo(customerId, token) {
        return axios
            .get(API_URL + "8080/getCustomerInfo", {
                headers: { "Authorization" : `Bearer ${token}`},
                params: {
                    id: customerId
                }
            })
            .then((response) => {
                return response.data;
            });
    }

    getCourierInfo(courierId, token) {
        return axios
            .get(API_URL + "8084/getCourierInfo", {
                headers: { "Authorization" : `Bearer ${token}`},
                params: {
                    id: courierId
                }
            })
            .then((response) => {
                return response.data;
            });
    }

    getCourierManagerInfo(managerId, token) {
        return axios
            .get(API_URL + "8084/getManagerInfo", {
                headers: { "Authorization" : `Bearer ${token}`},
                params: {
                    id: managerId
                }
            })
            .then((response) => {
                return response.data;
            });
    }

    getRestaurantManagerInfo(managerId, token) {
        return axios
            .get(API_URL + "8083/getManagerInfo", {
                headers: { "Authorization" : `Bearer ${token}`},
                params: {
                    id: managerId
                }
            })
            .then((response) => {
                return response.data;
            });
    }
}
export default new UserDataService();