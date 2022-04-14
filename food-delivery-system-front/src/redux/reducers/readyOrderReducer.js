const CHOOSE_ORDER = 'CHOOSE_ORDER';

const initialState = {
    firstName: '',
    lastName: '',
    orderAddress: '',
    orderPrice: 0,
    id: 0,
    phoneNumber: ''
};

export function ReadyOrderReducer(state = initialState, action) {
    switch (action.type) {

        case CHOOSE_ORDER:
            return {
                ...state,
                firstName: action.payload.firstName,
                lastName: action.payload.lastName,
                orderAddress: action.payload.orderAddress,
                orderPrice: action.payload.orderPrice,
                id: action.payload.id,
                phoneNumber: action.payload.phoneNumber
            };

        default:
            return state;
    }
}