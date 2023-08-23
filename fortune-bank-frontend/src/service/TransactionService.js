import axios from "axios";

const TRANSACTIONS_REST_API_URL =
  "http://localhost:8080/fortunebank/api/transaction";

class TransactionService {
  static getTransactions(accountNumber) {
    return axios.get(TRANSACTIONS_REST_API_URL + "/get/" + accountNumber);
  }

  static addTransaction(transaction) {
    return axios.post(TRANSACTIONS_REST_API_URL + "/transfer", transaction);
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
