import {GET_STATISTICS_SUCCESS} from "../actions/statisticsActions";

const initialState = {
    statisticsData: {
        numberOfDaysForChart: '',
        ordersNumberPerDay: [],
        incomePerDay: []
    }
};

export function StatisticsReducer(state = initialState, action) {
    const {type, payload} = action;
    switch (type) {
        case GET_STATISTICS_SUCCESS:
            return {
                ...state,
                statisticsData: payload,
            };

        default:
            return state;
    }
}