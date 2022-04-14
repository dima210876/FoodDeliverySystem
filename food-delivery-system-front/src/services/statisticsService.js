import axios from "axios";
const API_URL = "http://localhost:";
class StatisticsService {
    getStatisticsInfo(restaurantId) {
        return axios
            .get(API_URL + "8083/getRestaurantStatistics", {
                params: {
                    id: restaurantId
                }
            })
            .then((response) => {
                return response.data;
            });
    }
}
export default new StatisticsService();