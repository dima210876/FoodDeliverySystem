import axios from "axios";
const API_URL = "http://localhost:8081/";
class UserDataService {
    getOrganizationInfo(userId, token) {
        return axios
            .get(API_URL + "confirm/" + token, {
                params: { id: userId }
            })
            .then((response) => {
                return response.data;
            });
    }
}
export default new UserDataService();