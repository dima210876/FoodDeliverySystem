import {CREATE_NEW_ORDER_SUCCESS} from "../actions/orderConfirmationActions";

const initialState = {
    orderData: {
        userId: '',
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
                authData: payload,
            };

        default:
            return state;
    }
}