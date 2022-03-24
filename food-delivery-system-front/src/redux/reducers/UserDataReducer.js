import {REGISTER_SUCCESS, LOGIN_SUCCESS, GET_ORG_INFO_SUCCESS} from '../actions/AuthActions';

const initialState = {
    managerData: {
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
                description: '',
                phoneNumber: '',
                address: '',
                latitude: '',
                longitude: ''
            },
        },
    },
};

export function UserDataReducer(state = initialState, action) {
    const { type, payload } = action;
    switch (type) {

        case GET_ORG_INFO_SUCCESS:
            return {
                ...state,
                managerData: payload,
            }

        default:
            return state;
    }
}