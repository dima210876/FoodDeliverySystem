import GetDataService from "../../services/userDataService";

export const GET_ORG_INFO_SUCCESS = "GET_ORG_INFO_SUCCESS";

export const getOrganizationInfo = (userId, token) => (dispatch) => {
    return GetDataService.getOrganizationInfo(userId, token).then(
        (response) => {
            dispatch({
                type: GET_ORG_INFO_SUCCESS,
                payload: response,
            });
            return Promise.resolve();
        })
}