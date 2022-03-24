import {REGISTER_SUCCESS, LOGIN_SUCCESS, GET_ORG_INFO_SUCCESS} from '../actions/AuthActions';

const initialState = {
    orgInfo: {
        organizationId: '',
        name: '',
        address: ''
    }
};

export function GetDataReducer(state = initialState, action) {
    const { type, payload } = action;
    switch (type) {

        case GET_ORG_INFO_SUCCESS:
            return {
                ...state,
                orgInfo: payload,
            }

        default:
            return state;
    }
}