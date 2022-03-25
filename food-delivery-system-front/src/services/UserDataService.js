import axios from "axios";
const API_URL = "http://localhost:8085/";
class UserDataService {
    getCourierManagerInfo(managerId) {
        return axios
            .post(API_URL + "identity/get-org-info", {
                id: managerId,
            })
            .then((response) => {
                return response.data;
            });
    }
}
export default new UserDataService();