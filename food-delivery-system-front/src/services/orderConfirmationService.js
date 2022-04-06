import axios from "axios";

const API_URL = "http://localhost:";

class orderConfirmationService{
    confirmOrderDetails(userId, items, address, latitude, longitude){
        return axios
            .post(API_URL + "/here-must-be-url-for-creating-order", {
                userId: userId,
                items: items,
                address: address,
                latitude: latitude,
                longitude: longitude
            })
            .then((response ) => {
                return response.data;
            });
    };

    confirmPayment(orderId, paymentProviderName, cardNumber, validityPeriod, cardCode){
        return axios
            .post(API_URL + "/orderPayment", {
                orderId: orderId,
                paymentProviderName: paymentProviderName,
                cardNumber: cardNumber,
                validityPeriod: validityPeriod,
                cardCode: cardCode
            })
            .then((response ) => {
                return response.data;
            });
    }

}

export default new orderConfirmationService();