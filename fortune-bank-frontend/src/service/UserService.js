import axios from "axios";

const USER_API_URL = "http://localhost:8080/fortunebank/api";

class UserService {
  static apply(data) {
    return axios.post(USER_API_URL + "/apply", data);
  }

  static getAccountNumber(userid) {
    return axios.get(USER_API_URL + "/getacno/" + userid);
  }

  static async getUserDetails(accountNumber) {
    return await axios.get(USER_API_URL + "/userprofile/" + accountNumber);
  }
}

export default UserService;
