import axios from "axios";
import {useState} from "react";

const API_URL = "http://localhost:";

class changeInfoService {
    changeDeliveryOrganizationInfo(organizationId, name, accountNumber, phone, address, latitude, longitude) {
        return axios
            .post(API_URL + "8084/editOrganizationInfo", {
                organizationId: organizationId,
                name: name,
                accountNumber: accountNumber,
                phoneNumber: phone,
                officeAddress: address,
                latitude: latitude,
                longitude: longitude
            })
            .then((response) => {
                return response.data;
            });
    };

    changeRestaurant(restaurantId, name, description, phone, address, latitude, longitude, workingTime, restaurantTypes) {
        return axios
            .post(API_URL + "8083/editRestaurantInfo", {
                restaurantId: restaurantId,
                name: name,
                description: description,
                phoneNumber: phone,
                restaurantAddress: address,
                latitude: latitude,
                longitude: longitude,
                workingTime: workingTime,
                restaurantTypes: restaurantTypes
            })
            .then((response) => {
                return response.data;
            });
    };
}

export default new changeInfoService();