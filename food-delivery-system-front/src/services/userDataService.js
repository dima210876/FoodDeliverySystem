import axios from "axios";
const API_URL = "http://localhost/";
class UserDataService {
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