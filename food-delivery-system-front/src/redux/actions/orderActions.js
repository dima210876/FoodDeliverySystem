import orderService from "../../services/orderService";

export const GET_ORDERS_SUCCESS = "GET_ORDERS_SUCCESS";
export const GET_COURIER_ORDERS_SUCCESS = "GET_COURIER_ORDERS_SUCCESS";
export const CHANGE_ORDER_STATUS_SUCCESS = "CHANGE_ORDER_STATUS_SUCCESS";
export const CHANGE_COURIER_ORDER_STATUS_SUCCESS = "CHANGE_COURIER_ORDER_STATUS_SUCCESS";

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

export const changeCourierOrderStatus = (orderId, newStatus) => (dispatch) => {
    return orderService.changeCourierOrderStatus(orderId, newStatus).then(
        (response) => {
            dispatch({
                type: CHANGE_COURIER_ORDER_STATUS_SUCCESS,
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

export const getCourierOrders = (courierId) => (dispatch) => {
    return orderService.getCourierOrders(courierId).then(
        (response) => {
            dispatch({
                type: GET_COURIER_ORDERS_SUCCESS,
                payload: response,
            });
            return Promise.resolve();
        })
}
