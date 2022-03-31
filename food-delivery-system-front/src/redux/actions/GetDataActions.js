import {GET_ORG_INFO_SUCCESS} from "./AuthActions";
import GetDataService from "../../services/GetDataService";

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