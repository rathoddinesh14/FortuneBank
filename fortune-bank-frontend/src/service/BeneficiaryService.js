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

  static addBeneficiary(beneficiary) {
    return axios.post(BENEFICIARIES_REST_API_URL + "/add", beneficiary);
  }

  static deleteBeneficiary(beneficiaryId) {
    return axios.delete(
      BENEFICIARIES_REST_API_URL +
        "/delete/" +
        AuthenticationService.getLoggedInAccountNumber() +
        "/" +
        beneficiaryId
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
