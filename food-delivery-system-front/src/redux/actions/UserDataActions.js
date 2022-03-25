import GetDataService from "../../services/UserDataService";

export const GET_COURIER_MANAGER_INFO_SUCCESS = "GET_COURIER_MANAGER_INFO_SUCCESS";

export const getCourierManagerInfo = (userId) => (dispatch) => {
    return GetDataService.getCourierManagerInfo(userId).then(
        (response) => {
            dispatch({
                type: GET_COURIER_MANAGER_INFO_SUCCESS,
                payload: response,
            });
            return Promise.resolve();
        })
}