import {REGISTER_SUCCESS, LOGIN_SUCCESS, GET_ORG_INFO_SUCCESS} from '../actions/authActions';

const initialState = {
    authData: {
        user: {
            user_id:'',
            email: '',
            firstName: '',
            lastName: '',
            role: ''
        },
        token: ''
    }
};

export function AuthReducer(state = initialState, action) {
    const { type, payload } = action;
    switch (type) {
        case REGISTER_SUCCESS:
            return {
                ...state,
            };

        case LOGIN_SUCCESS:
            return {
                ...state,
                authData: payload,
            };

        default:
            return state;
    }
}