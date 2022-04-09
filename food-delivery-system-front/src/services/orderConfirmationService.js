import axios from "axios";

const API_URL = "http://localhost:";

class orderConfirmationService {
    confirmOrderDetails(userId, items, address, latitude, longitude, totalPrice, shippingPrice) {
        return axios
            .post(API_URL + "/here-must-be-url-for-creating-order", {
           // .post("http://localhost:8080/createOrder", {
                customerId: userId,
                orderAddress: address,
                orderStatus: "not_paid",
                orderPrice: totalPrice,
                shippingPrice: shippingPrice,
                discount: 0,
                creationTime: new Date(),
                latitude: latitude,
                longitude: longitude,
                items: items,
            })
            .then((response) => {
                return response.data;
            });
    };

    confirmPayment(orderId, paymentProviderName, cardNumber, validityPeriod, cardCode) {
        return axios
            .post(API_URL + "/orderPayment", {
                orderId: orderId,
                paymentProviderName: paymentProviderName,
                cardNumber: cardNumber,
                validityPeriod: validityPeriod,
                cardCode: cardCode
            })
            .then((response) => {
                return response.data;
            });
    }

}

export default new orderConfirmationService();