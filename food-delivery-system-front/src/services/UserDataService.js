import axios from "axios";
const API_URL = "http://localhost:8085/";
class UserDataService {
    getCourierManagerInfo(managerId) {
        return axios
            .get(API_URL + "identity/get-courier-manager-info", {
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