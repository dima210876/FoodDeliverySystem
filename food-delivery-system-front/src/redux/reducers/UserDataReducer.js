import {GET_COURIER_MANAGER_INFO_SUCCESS} from "../actions/UserDataActions";

const initialState = {
    restaurantManagerData: {
        manager: {
            id: '',
            email: '',
            firstName: '',
            lastName: '',
            role: '',
            phoneNumber: '',
            restaurant: {
                restaurantId: '',
                name: '',
                description: '',
                phoneNumber: '',
                address: '',
                latitude: '',
                longitude: ''
            }
        }
    },
    deliveryOrgManagerData: {
        manager: {
            id: '',
            email: '',
            firstName: '',
            lastName: '',
            role: '',
            phoneNumber: '',
            organization: {
                organizationId: '',
                name: '',
                accountNumber: '',
                phoneNumber: '',
                address: '',
                latitude: '',
                longitude: ''
            }
        }
    }
};

export function UserDataReducer(state = initialState, action) {
    const { type, payload } = action;
    switch (type) {

        case GET_COURIER_MANAGER_INFO_SUCCESS:
            return {
                ...state,
                deliveryOrgManagerData: payload,
            }

        default:
            return state;
    }
}