import GetDataService from "../../services/GetDataService";

export const GET_ORG_INFO_SUCCESS = "GET_ORG_INFO_SUCCESS";

export const getOrganizationInfo = (userId) => (dispatch) => {
    return GetDataService.getOrganizationInfo(userId).then(
        (response) => {
            dispatch({
                type: GET_ORG_INFO_SUCCESS,
                payload: response,
            });
            return Promise.resolve();
        })
}