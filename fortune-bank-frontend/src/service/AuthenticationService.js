import axios from "axios";

const AUTH_API_URL = "http://localhost:8080/fortunebank/api/";

class AuthenticationService {
  static login(username, password) {
    const data = {
      userid: username,
      password: password,
    };
    return axios.post(AUTH_API_URL + "login", data);
  }
}

export default AuthenticationService;
