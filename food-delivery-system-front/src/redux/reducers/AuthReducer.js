import {REGISTER_SUCCESS, LOGIN_SUCCESS, GET_ORG_INFO_SUCCESS} from '../actions/AuthActions';

const initialState = {
    auth: {
        user: {
            id:'',
            email: '',
            firstName: '',
            lastName: '',
            role: ''
        },
        token: '',
        // expiresIn: '',
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
                auth: payload,
            };

        default:
            return state;
    }
}