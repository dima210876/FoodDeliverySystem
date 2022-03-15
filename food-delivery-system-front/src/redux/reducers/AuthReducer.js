import {REGISTER_SUCCESS, LOGIN_SUCCESS} from '../actions/AuthActions';

const initialState = {
    auth: {
        email: '',
        token: '',
        // localId: '',
        // expiresIn: '',
        // refreshToken: '',
    },
};

export function AuthReducer(state = initialState, action) {
    switch (type) {
        case REGISTER_SUCCESS:
        {
            return {
                ...state,
            };
        }

        case LOGIN_SUCCESS:
            return {
                ...state,
                auth: action.payload,
            };
        default:
            return state;
    }
}