const CHOOSE_CATEGORY = 'CHOOSE_CATEGORY';

const initialState = {
    title: ''
};

export function CategoryReducer(state = initialState, action) {
    switch (action.type) {

        case CHOOSE_CATEGORY:
            return {
                ...state,
                title: action.payload,
            };

        default:
            return state;
    }
}