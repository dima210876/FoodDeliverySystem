import axios from "axios";
const API_URL = "http://localhost:8085/";
class changeInfoService {
    changeDeliveryOrganizationInfo(organizationId, name, accountNumber, phone, address, latitude, longtitude) {
        return axios
            .post(API_URL + "url-for-changing-delivery-org-info", {
                organizationId: organizationId,
                name: name,
                accountNumber: accountNumber,
                phoneNumber: phone,
                address: address,
                latitude: latitude,
                longtitude: longtitude
            })
            .then((response) => {
                return response.data;
            });
    }
}

export default new changeInfoService();