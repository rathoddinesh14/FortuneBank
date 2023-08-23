import axios from "axios";
import AuthenticationService from "./AuthenticationService";

const BENEFICIARIES_REST_API_URL =
  "http://localhost:8080/fortunebank/api/beneficiary";

class BeneficiaryService {
  static getBeneficiaries() {
    return axios.get(
      BENEFICIARIES_REST_API_URL +
        "/get/" +
        AuthenticationService.getLoggedInAccountNumber()
    );
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

export default BeneficiaryService;
