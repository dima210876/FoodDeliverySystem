import axios from "axios";
import {registerRestaurant} from "../redux/actions/AuthActions";
const API_URL = "http://localhost:8085/";
class AuthService {
    login(email, password) {
        return axios
            .post(API_URL + "identity/login", { email, password })
            .then((response) => {
                return response.data;
            });
    }

    registerUser(firstName, lastName, email, phone, password) {
        return axios.post(API_URL + "identity/register", {
            firstName,
            lastName,
            email,
            phone,
            password,
        });
    }

    registerRestaurant(restaurantName, firstName, lastName, email, phone, password) {
        return axios.post(API_URL + "admin/register/restaurant", {
            firstName,
            lastName,
            email,
            phone,
            password,
        });
    }
}
export default new AuthService();