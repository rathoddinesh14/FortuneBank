import axios from "axios";

const AUTH_API_URL = "http://localhost:8080/fortunebank/api/";
export const ACCOUNT_NUMBER_SESSION_ATTRIBUTE_NAME = "authenticatedUser";
class AuthenticationService {
  static setSessionAttribute(key, value) {
    localStorage.setItem(key, value);
  }

  static registerSuccessfulLogin(accountnumber) {
    sessionStorage.setItem(
      ACCOUNT_NUMBER_SESSION_ATTRIBUTE_NAME,
      accountnumber
    );
  }

  static isUserLoggedIn() {
    let user = sessionStorage.getItem(ACCOUNT_NUMBER_SESSION_ATTRIBUTE_NAME);
    if (user === null) return false;
    return true;
  }

  static getLoggedInAccountNumber() {
    let user = sessionStorage.getItem(ACCOUNT_NUMBER_SESSION_ATTRIBUTE_NAME);
    if (user === null) return "";
    return user;
  }

  logout() {
    sessionStorage.removeItem(ACCOUNT_NUMBER_SESSION_ATTRIBUTE_NAME);
  }

  static login(username, password) {
    const data = {
      userid: username,
      password: password,
    };
    return axios.post(AUTH_API_URL + "login", data);
  }
}

export default AuthenticationService;
