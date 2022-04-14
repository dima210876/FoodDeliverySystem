import StatisticsService from "../../services/statisticsService";

export const GET_STATISTICS_SUCCESS = "GET_STATISTICS_SUCCESS";

export const getStatisticsInfo = (restaurantId) => (dispatch) => {
    return StatisticsService.getStatisticsInfo(restaurantId).then(
        (response) => {
            dispatch({
                type: GET_STATISTICS_SUCCESS,
                payload: response,
            });
            return Promise.resolve();
        })
}