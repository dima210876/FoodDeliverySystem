import orderService from "../../services/orderService";

export const GET_ORDERS_SUCCESS = "GET_ORDERS_SUCCESS";
export const CHANGE_ORDER_STATUS_SUCCESS = "CHANGE_ORDER_STATUS_SUCCESS";

export const changeOrderStatus = (orderId, newStatus) => (dispatch) => {
    return orderService.changeOrderStatus(orderId, newStatus).then(
        (response) => {
            dispatch({
                type: CHANGE_ORDER_STATUS_SUCCESS,
                payload: response,
            });
            return Promise.resolve();
        })
}

export const getRestaurantOrders = (restaurantId) => (dispatch) => {
    return orderService.getRestaurantOrders(restaurantId).then(
        (response) => {
            dispatch({
                type: GET_ORDERS_SUCCESS,
                payload: response,
            });
            return Promise.resolve();
        })
}