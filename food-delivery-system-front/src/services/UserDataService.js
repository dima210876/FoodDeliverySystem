import axios from "axios";
const API_URL = "http://localhost:8084/";
class UserDataService {
    getCourierManagerInfo(managerId) {
        console.log("BEFORE REQUEST");
        return axios
            .get(API_URL + "getManagerInfo", {
                params: {
                    id: managerId
                }
            })
            .then((response) => {
                return response.data;
            });
    }

    getRestaurantManagerInfo(managerId) {
        return axios
            .get(API_URL + "getRestaurantManagerInfo", {
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