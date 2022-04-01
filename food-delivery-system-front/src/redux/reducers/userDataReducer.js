import {GET_COURIER_MANAGER_INFO_SUCCESS, GET_RESTAURANT_MANAGER_INFO_SUCCESS} from "../actions/userDataActions";
import {CHANGE_DELIVERY_ORGANIZATION_INFO_SUCCESS, CHANGE_RESTAURANT_INFO_SUCCESS} from "../actions/changeInfoActions";

const initialState = {
    restaurantManagerData: {
        // manager: {
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
                longitude: '',
                workingTime: [
                    {dayOfWeek: 1, dayName: 'Monday', openingTime: '', closingTime: ''},
                    {dayOfWeek: 2, dayName: 'Tuesday', openingTime: '', closingTime: ''},
                    {dayOfWeek: 3, dayName: 'Wednesday', openingTime: '', closingTime: ''},
                    {dayOfWeek: 4, dayName: 'Thursday', openingTime: '', closingTime: ''},
                    {dayOfWeek: 5, dayName: 'Friday', openingTime: '', closingTime: ''},
                    {dayOfWeek: 6, dayName: 'Saturday', openingTime: '', closingTime: ''},
                    {dayOfWeek: 7, dayName: 'Sunday', openingTime: '', closingTime: ''},
                ],
                restaurantTypes: [],
            }
        // }
    },
    deliveryOrgManagerData: {
        // manager: {
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
        // }
    }
};

export function UserDataReducer(state = initialState, action) {
    const {type, payload} = action;
    switch (type) {

        case GET_COURIER_MANAGER_INFO_SUCCESS:
            return {
                ...state,
                deliveryOrgManagerData: payload,
            }

        case GET_RESTAURANT_MANAGER_INFO_SUCCESS:
            return {
                ...state,
                restaurantManagerData: payload,
            }

        case CHANGE_DELIVERY_ORGANIZATION_INFO_SUCCESS:
            return {
                ...state,
            };

        case CHANGE_RESTAURANT_INFO_SUCCESS:
            return {
                ...state,
            };

        default:
            return state;
    }
}