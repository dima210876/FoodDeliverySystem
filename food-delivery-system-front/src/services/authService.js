import axios from "axios";
const API_URL = "http://localhost:";
class AuthService {
    login(email, password) {
        return axios
            .post(API_URL + "8081/login", {
                email: email,
                password: password
            })
            .then((response) => {
                return response.data;
            });
    }

    registerUser(firstName, lastName, email, phone, password) {
        return axios.post(API_URL + "8080/registerCustomer", {
            firstName: firstName,
            lastName: lastName,
            email: email,
            phoneNumber: phone,
            password: password,
        });
    }

    registerRestaurant(restaurantName, firstName, lastName, email, phone, password) {
        return axios.post(API_URL + "8083/registerManager", {
            restaurantName: restaurantName,
            firstName: firstName,
            lastName: lastName,
            email: email,
            phoneNumber: phone,
            password: password,
        });
    }

    registerOrganization(organizationName, firstName, lastName, email, phone, password) {
        return axios.post(API_URL + "8084/registerCourierManager", {
            organizationName: organizationName,
            firstName: firstName,
            lastName: lastName,
            email: email,
            phoneNumber: phone,
            password: password,
        });
    }

    registerCourier(organizationId, firstName, lastName, email, phone, password) {
        return axios.post(API_URL + "8084/registerCourier", {
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