import axios from "axios";
import AuthenticationService from "./AuthenticationService";

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
}

export default UserService;
