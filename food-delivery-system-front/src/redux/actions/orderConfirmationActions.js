import orderConfirmationService from "../../services/orderConfirmationService";

export const CREATE_NEW_ORDER_SUCCESS = "CREATE_NEW_ORDER_SUCCESS";

export const confirmOrderDetails = (userId, items, address, latitude, longitude) => (dispatch) => {
    return orderConfirmationService.confirmOrderDetails(userId, items, address, latitude, longitude).then(
        (response) => {
            dispatch({
                type: CREATE_NEW_ORDER_SUCCESS,
                payload: response,
            });
            return Promise.resolve();
        })
}