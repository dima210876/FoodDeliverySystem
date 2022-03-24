import AuthService from "../../services/AuthService";

export const REGISTER_SUCCESS = "REGISTER_SUCCESS";
export const REGISTER_FAIL = "REGISTER_FAIL";
export const LOGIN_SUCCESS = "LOGIN_SUCCESS";
export const LOGIN_FAIL = "LOGIN_FAIL";

export const registerUser = (firstName, lastName, email, phone, password) => (dispatch) => {
    return AuthService.registerUser(firstName, lastName, email, phone, password).then(
        (response) => {
            dispatch({
                type: REGISTER_SUCCESS,
                payload: response,
            });
             return Promise.resolve();
        })
}

export const registerRestaurant = (restaurantName, firstName, lastName, email, phone, password) => (dispatch) => {
    return AuthService.registerRestaurant(restaurantName, firstName, lastName, email, phone, password).then(
        (response) => {
            dispatch({
                type: REGISTER_SUCCESS,
                payload: response,
            });
            return Promise.resolve();
        })
}

export const registerOrganization = (organizationName, firstName, lastName, email, phone, password) => (dispatch) => {
    return AuthService.registerOrganization(organizationName, firstName, lastName, email, phone, password).then(
        (response) => {
            dispatch({
                type: REGISTER_SUCCESS,
                payload: response,
            });
            return Promise.resolve();
        })
}

export const login = (email, password) => (dispatch) => {
    return AuthService.login(email, password).then(
        (response) => {
            dispatch({
                type: LOGIN_SUCCESS,
                payload: response,
            });
            return Promise.resolve();
        })
}
