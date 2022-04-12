import {CONFIRM_PAYMENT_SUCCESS, CREATE_NEW_ORDER_SUCCESS} from "../actions/orderConfirmationActions";

const initialState = {
    orderData: {
        orderId: '',
        totalPrice: ''
    }
};

export function OrderReducer(state = initialState, action) {
    const {type, payload} = action;
    switch (type) {
        case CREATE_NEW_ORDER_SUCCESS:
            return {
                ...state,
                orderData: payload,
            };
        case CONFIRM_PAYMENT_SUCCESS:
            return {
                ...state,
            };

        default:
            return state;
    }
}