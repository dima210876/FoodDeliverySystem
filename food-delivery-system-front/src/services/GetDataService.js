import axios from "axios";
const API_URL = "http://localhost:8085/";
class GetDataService {
    getOrganizationInfo(userId) {
        return axios
            .post(API_URL + "identity/get-org-info", {
                id: userId,
            })
            .then((response) => {
                return response.data;
            });
    }
}
export default new GetDataService();