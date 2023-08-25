import axios from "axios";
import AuthenticationService from "./AuthenticationService";
import API_URLS from "../utils/ApiUrls";

const USER_API_URL = "http://localhost:8080/fortunebank/api";

class UserService {
  static apply(data) {
    return axios.post(USER_API_URL + "/apply", data);
  }

  static getAccountNumber(userid) {
    return axios.get(USER_API_URL + "/getacno/" + userid);
  }

  static getName() {
    return axios.get(
      USER_API_URL +
        "/getname/" +
        AuthenticationService.getLoggedInAccountNumber()
    );
  }

  static async getUserDetails() {
    return await axios.get(
      USER_API_URL +
        "/userprofile/" +
        AuthenticationService.getLoggedInAccountNumber()
    );
  }

  static getAllUsers() {
    return axios.get(API_URLS.adminCustomers);
  }

  static updateUser(accountNumber, status) {
    return axios.put(
      API_URLS.adminAccountStatus + "/" + accountNumber + "/" + status
    );
  }

  static searchUsers(searchParam, searchValue) {
    return axios.post(API_URLS.adminCustomerSearch + "/" + searchParam, {
      input: searchValue,
    });
  }
}

export default UserService;
