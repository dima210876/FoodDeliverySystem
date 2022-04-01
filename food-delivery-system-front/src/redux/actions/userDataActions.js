import GetDataService from "../../services/userDataService";

export const GET_COURIER_MANAGER_INFO_SUCCESS = "GET_COURIER_MANAGER_INFO_SUCCESS";
export const GET_RESTAURANT_MANAGER_INFO_SUCCESS = "GET_RESTAURANT_MANAGER_INFO_SUCCESS";

export const getCourierManagerInfo = (userId, token) => (dispatch) => {
    return GetDataService.getCourierManagerInfo(userId, token).then(
        (response) => {
            dispatch({
                type: GET_COURIER_MANAGER_INFO_SUCCESS,
                payload: response,
            });
            return Promise.resolve();
        })
}

export const getRestaurantManagerInfo = (userId, token) => (dispatch) => {
    return GetDataService.getRestaurantManagerInfo(userId, token).then(
        (response) => {
            dispatch({
                type: GET_RESTAURANT_MANAGER_INFO_SUCCESS,
                payload: response,
            });
            return Promise.resolve();
        })
}