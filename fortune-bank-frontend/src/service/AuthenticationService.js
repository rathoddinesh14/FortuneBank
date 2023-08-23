import axios from "axios";
import UserService from "./UserService";

const AUTH_API_URL = "http://localhost:8080/fortunebank/api/";
export const ACCOUNT_NUMBER_SESSION_ATTRIBUTE_NAME = "authenticatedUser";
export const ACCOUNT_NAME_SESSION_ATTRIBUTE_NAME = "accountName";

class AuthenticationService {
  static setSessionAttribute(key, value) {
    localStorage.setItem(key, value);
  }

  static registerSuccessfulLogin(accountnumber) {
    sessionStorage.setItem(
      ACCOUNT_NUMBER_SESSION_ATTRIBUTE_NAME,
      accountnumber
    );
    UserService.getName()
      .then((response) => this.setAccountName(response.data))
      .catch((error) => {
        console.error("Login failed:", error);
      });
  }

  static setAccountName(accountname) {
    sessionStorage.setItem(ACCOUNT_NAME_SESSION_ATTRIBUTE_NAME, accountname);
  }

  static getAccountName() {
    let user = sessionStorage.getItem(ACCOUNT_NAME_SESSION_ATTRIBUTE_NAME);
    if (user === null) return "";
    return user;
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

  static logout() {
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
