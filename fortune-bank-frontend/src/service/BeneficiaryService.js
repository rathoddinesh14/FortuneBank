import axios from "axios";
import AuthenticationService from "./AuthenticationService";
import API_URLS from "../utils/ApiUrls";

const BENEFICIARIES_REST_API_URL =
  "http://localhost:8080/fortunebank/api/beneficiary";

class BeneficiaryService {
  static getBeneficiaries() {
    if (AuthenticationService.isAdminMode()) {
      return axios.get(API_URLS.adminBeneficiaries);
    } else {
      return axios.get(
        BENEFICIARIES_REST_API_URL +
          "/get/" +
          AuthenticationService.getLoggedInAccountNumber()
      );
    }
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
}

export default BeneficiaryService;
