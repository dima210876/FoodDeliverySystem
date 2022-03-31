import changeInfoService from "../../services/changeInfoService";

export const CHANGE_DELIVERY_ORGANIZATION_INFO_SUCCESS = "CHANGE DELIVERY_ORGANIZATION_INFO_SUCCESS";
export const CHANGE_RESTAURANT_INFO_SUCCESS = "CHANGE CHANGE_RESTAURANT_INFO_SUCCESS";

export const changeDeliveryOrganizationInfo = (organizationId, name, accountNumber, phone, address, latitude, longtitude) => (dispatch) => {
    return changeInfoService.changeDeliveryOrganizationInfo(organizationId, name, accountNumber, phone, address, latitude, longtitude).then(
        (response) => {
            dispatch({
                type: CHANGE_DELIVERY_ORGANIZATION_INFO_SUCCESS,
                payload: response,
            });
            return Promise.resolve();
        })
}

export const changeRestaurantInfo = (restaurantId, name, description, phone, address, latitude, longitude, workingTime, restaurantTypes) => (dispatch) => {
    return changeInfoService.changeRestaurant(restaurantId, name, description, phone, address, latitude, longitude, workingTime, restaurantTypes).then(
        (response) => {
            dispatch({
                type: CHANGE_RESTAURANT_INFO_SUCCESS,
                payload: response,
            });
            return Promise.resolve();
        })
}