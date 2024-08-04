import axios from "axios";
import UserService from "./UserService";

const AUTH_API_URL = "http://localhost:8080/fortunebank/api/";

export const ACCOUNT_NUMBER_SESSION_ATTRIBUTE_NAME = "authenticatedUser";
export const ACCOUNT_NAME_SESSION_ATTRIBUTE_NAME = "accountName";
export const ADMIN_MODE_SESSION_ATTRIBUTE_NAME = "isAdminMode";
export const TRANSACTION_STATUS_SESSION_ATTRIBUTE_NAME = "transactionStatus";

class AuthenticationService {
  static setSessionAttribute(key: string, value: string) {
    localStorage.setItem(key, value);
  }

  static registerSuccessfulLogin(accountnumber: string) {
    sessionStorage.setItem(
      ACCOUNT_NUMBER_SESSION_ATTRIBUTE_NAME,
      accountnumber
    );
    UserService.getName()
      .then((response: any) => this.setAccountName(response.data))
      .catch((error: any) => {
        console.error("Login failed:", error);
      });
  }

  static setAccountName(accountname: string) {
    sessionStorage.setItem(ACCOUNT_NAME_SESSION_ATTRIBUTE_NAME, accountname);
  }

  static getAccountName(): string {
    let user = sessionStorage.getItem(ACCOUNT_NAME_SESSION_ATTRIBUTE_NAME);
    if (user === null) return "";
    return user;
  }

  static isUserLoggedIn(): boolean {
    let user = sessionStorage.getItem(ACCOUNT_NUMBER_SESSION_ATTRIBUTE_NAME);
    if (user === null) return false;
    return true;
  }

  static getLoggedInAccountNumber(): string {
    let user = sessionStorage.getItem(ACCOUNT_NUMBER_SESSION_ATTRIBUTE_NAME);
    if (user === null) return "";
    return user;
  }

  static logout() {
    sessionStorage.removeItem(ACCOUNT_NUMBER_SESSION_ATTRIBUTE_NAME);
  }

  static login(username: string, password: string): Promise<any> {
    const data = {
      userid: username,
      password: password,
    };
    return axios.post(AUTH_API_URL + "login", data);
  }

  static adminlogin(username: string, password: string): Promise<any> {
    const data = {
      userid: username,
      password: password,
    };
    return axios.post(AUTH_API_URL + "admin/login", data);
  }

  static setAdminMode(isAdmin: boolean) {
    sessionStorage.setItem(ADMIN_MODE_SESSION_ATTRIBUTE_NAME, isAdmin.toString());
  }

  static isAdminMode(): boolean {
    let isAdmin = sessionStorage.getItem(ADMIN_MODE_SESSION_ATTRIBUTE_NAME);
    if (isAdmin === null) return false;
    if (isAdmin === "true") return true;
    return false;
  }

  static setTransactionStatus(transactionStatus: string) {
    sessionStorage.setItem(
      TRANSACTION_STATUS_SESSION_ATTRIBUTE_NAME,
      transactionStatus
    );
  }

  static getTransactionStatus(): string {
    let transactionStatus = sessionStorage.getItem(
      TRANSACTION_STATUS_SESSION_ATTRIBUTE_NAME
    );
    if (transactionStatus === null) return "";
    return transactionStatus;
  }
}

export default AuthenticationService;