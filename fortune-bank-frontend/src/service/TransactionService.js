import axios from "axios";
import AuthenticationService from "./AuthenticationService";

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

  // static createProduct(product){
  //     return axios.post(PRODUCTS_REST_API_URL,product);
  // }

  // static getProductById(productId){
  //     return axios.get(PRODUCTS_REST_API_URL+'/'+productId);
  // }

  // static updateProduct(product,productId){
  //     return axios.put(PRODUCTS_REST_API_URL+'/'+productId,product);
  // }
}

export default TransactionService;
