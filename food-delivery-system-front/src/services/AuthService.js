import axios from "axios";
const API_URL = "http://localhost:8085/identity/";
class AuthService {
    login(email, password) {
        return axios
            .post(API_URL + "login", { email, password })
            .then((response) => {
                return response.data;
            });
    }

    register(firstName, lastName, email, phone, password) {
        return axios.post(API_URL + "register", {
            firstName,
            lastName,
            email,
            phone,
            password,
        });
    }
}
export default new AuthService();