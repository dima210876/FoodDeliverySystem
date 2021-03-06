import {
    GET_CUSTOMER_INFO_SUCCESS,
    GET_COURIER_INFO_SUCCESS,
    GET_COURIER_MANAGER_INFO_SUCCESS,
    GET_RESTAURANT_MANAGER_INFO_SUCCESS
} from "../actions/userDataActions";
import {CHANGE_DELIVERY_ORGANIZATION_INFO_SUCCESS, CHANGE_RESTAURANT_INFO_SUCCESS} from "../actions/changeInfoActions";
import {
    CHANGE_COURIER_ORDER_STATUS_SUCCESS,
    CHANGE_ORDER_STATUS_SUCCESS,
    GET_COURIER_ORDERS_SUCCESS,
    GET_ORDERS_SUCCESS
} from "../actions/orderActions";

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
            restaurantAddress: '',
            latitude: '',
            longitude: '',
            workingTime: [
                {dayOfWeek: '1', openingTime: '', closingTime: ''},
                {dayOfWeek: '2', openingTime: '', closingTime: ''},
                {dayOfWeek: '3', openingTime: '', closingTime: ''},
                {dayOfWeek: '4', openingTime: '', closingTime: ''},
                {dayOfWeek: '5', openingTime: '', closingTime: ''},
                {dayOfWeek: '6', openingTime: '', closingTime: ''},
                {dayOfWeek: '7', openingTime: '', closingTime: ''},
            ],
            restaurantTypes: [{restaurantType: ''}],
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
            id: '',
            name: '',
            accountNumber: '',
            phoneNumber: '',
            officeAddress: '',
            latitude: '',
            longitude: '',
            couriers: []
        }
        // }
    },
    courierData: {
        userId: '',
        email: '',
        firstName: '',
        lastName: '',
        role: '',
        phoneNumber: '',
        organization: {
            id: '',
            name: '',
            accountNumber: '',
            phoneNumber: '',
            officeAddress: '',
            latitude: '',
            longitude: ''
        },
        orders: []
    },
    customerData: {
        userId: '',
        email: '',
        firstName: '',
        lastName: '',
        phoneNumber: '',
        role: '',
        orders: [
            {
                id: '',
                courierId: '',
                orderStatus: '',
                orderAddress: '',
                orderPrice: '',
                shippingPrice: '',
                discount: '',
                creationTime: [],
                deliveryTime: [],
                latitude: '',
                longitude: ''
            }
        ]
    },
    restaurantOrders: [],
    courierOrders: [],
};

export function UserDataReducer(state = initialState, action) {
    const {type, payload} = action;
    switch (type) {

        case GET_CUSTOMER_INFO_SUCCESS:
            return {
                ...state,
                customerData: payload,
            }

        case GET_COURIER_INFO_SUCCESS:
            return {
                ...state,
                courierData: payload,
            }

        case GET_ORDERS_SUCCESS:
            return {
                ...state,
                restaurantOrders: payload,
            }

        case GET_COURIER_ORDERS_SUCCESS:
            return {
                ...state,
                courierOrders: payload,
            }

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

        case CHANGE_ORDER_STATUS_SUCCESS:
            return {
                ...state
            }

        case CHANGE_COURIER_ORDER_STATUS_SUCCESS:
            return {
                ...state
            }

        default:
            return state;
    }
}