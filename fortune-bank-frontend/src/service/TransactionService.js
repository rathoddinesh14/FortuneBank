import axios from "axios";
import AuthenticationService from "./AuthenticationService";
import API_URLS from "../utils/ApiUrls";

const TRANSACTIONS_REST_API_URL =
  "http://localhost:8080/fortunebank/api/transaction";

const ADMIN_TRANSACTIONS_REST_API_URL =
  "http://localhost:8080/fortunebank/api/admin/transactions";

class TransactionService {
  static getTransactions() {
    if (AuthenticationService.isAdminMode()) {
      return axios.get(ADMIN_TRANSACTIONS_REST_API_URL);
    } else {
      return axios.get(
        TRANSACTIONS_REST_API_URL +
          "/get/" +
          AuthenticationService.getLoggedInAccountNumber()
      );
    }
  }

  static addTransaction(transaction) {
    return axios.post(TRANSACTIONS_REST_API_URL + "/transfer", transaction);
  }

  static deposit(transaction) {
    return axios.post(TRANSACTIONS_REST_API_URL + "/deposit", transaction);
  }

  static withdraw(transaction) {
    return axios.post(TRANSACTIONS_REST_API_URL + "/withdraw", transaction);
  }

  static getTransactionsBetweenDates(startDate, endDate) {
    let url = "";
    if (AuthenticationService.isAdminMode()) {
      url = API_URLS.adminTransactionsBetweenDates;
    } else {
      url =
        TRANSACTIONS_REST_API_URL +
        "/transactions-between-dates?accountNumber=" +
        AuthenticationService.getLoggedInAccountNumber() +
        "&";
    }

    return axios.get(url + "startDate=" + startDate + "&endDate=" + endDate);
  }
}

export default TransactionService;
