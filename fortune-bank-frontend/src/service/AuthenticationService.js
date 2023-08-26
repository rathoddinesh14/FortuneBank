import axios from "axios";
import UserService from "./UserService";

const AUTH_API_URL = "http://localhost:8080/fortunebank/api/";
export const ACCOUNT_NUMBER_SESSION_ATTRIBUTE_NAME = "authenticatedUser";
export const ACCOUNT_NAME_SESSION_ATTRIBUTE_NAME = "accountName";
export const ADMIN_MODE_SESSION_ATTRIBUTE_NAME = "isAdminMode";

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

  static adminlogin(username, password) {
    const data = {
      userid: username,
      password: password,
    };
    return axios.post(AUTH_API_URL + "admin/login", data);
  }

  static setAdminMode(isAdmin) {
    sessionStorage.setItem(ADMIN_MODE_SESSION_ATTRIBUTE_NAME, isAdmin);
  }

  static isAdminMode() {
    let isAdmin = sessionStorage.getItem(ADMIN_MODE_SESSION_ATTRIBUTE_NAME);
    if (isAdmin === null) return false;
    if (isAdmin === "true") return true;
    return false;
  }
}

export default AuthenticationService;
