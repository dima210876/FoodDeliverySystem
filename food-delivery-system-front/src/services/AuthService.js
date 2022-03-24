import axios from "axios";
const API_URL = "http://localhost:8085/";
class AuthService {
    login(email, password) {
        return axios
            .post(API_URL + "identity/login", {
                email: email,
                password: password
            })
            .then((response) => {
                return response.data;
            });
    }

    registerUser(firstName, lastName, email, phone, password) {
        return axios.post(API_URL + "identity/register", {
            firstName: firstName,
            lastName: lastName,
            email: email,
            phoneNumber: phone,
            password: password,
        });
    }

    registerRestaurant(restaurantName, firstName, lastName, email, phone, password) {
        return axios.post(API_URL + "restaurant-info-service/registerManager", {
            restaurantName: restaurantName,
            firstName: firstName,
            lastName: lastName,
            email: email,
            phoneNumber: phone,
            password: password,
        });
    }

    registerCourier(organizationId, firstName, lastName, email, phone, password) {
        return axios.post(API_URL + "courier-manager/registerCourier", {
            organizationId: organizationId,
            firstName: firstName,
            lastName: lastName,
            email: email,
            phoneNumber: phone,
            password: password,
        });
    }
}
export default new AuthService();