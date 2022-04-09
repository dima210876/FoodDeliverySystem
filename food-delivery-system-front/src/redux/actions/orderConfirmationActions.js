import orderConfirmationService from "../../services/orderConfirmationService";

export const CREATE_NEW_ORDER_SUCCESS = "CREATE_NEW_ORDER_SUCCESS";
export const CONFIRM_PAYMENT_SUCCESS = "CONFIRM_PAYMENT_SUCCESS";

export const confirmOrderDetails = (userId, items, address, latitude, longitude, totalPrice, shippingPrice) => (dispatch) => {
    return orderConfirmationService.confirmOrderDetails(userId, items, address, latitude, longitude, totalPrice, shippingPrice)
        .then(
        (response) => {
            dispatch({
                type: CREATE_NEW_ORDER_SUCCESS,
                payload: response,
            });
            return Promise.resolve();
        })
};

export const confirmPayment = (orderId, paymentProviderName, cardNumber, validityPeriod, cardCode) => (dispatch) => {
    return orderConfirmationService.confirmPayment(orderId, paymentProviderName, cardNumber, validityPeriod, cardCode).then(
        (response) => {
            dispatch({
                type: CONFIRM_PAYMENT_SUCCESS,
                payload: response,
            });
            return Promise.resolve();
        })
};

