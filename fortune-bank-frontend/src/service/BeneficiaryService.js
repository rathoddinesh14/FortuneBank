import axios from 'axios' ;

const BENEFICIARIES_REST_API_URL='http://localhost:8080/fortunebank/api/beneficiary';

class BeneficiaryService{

   static getBeneficiaries(accountNumber){
        return axios.get(BENEFICIARIES_REST_API_URL + "/get/" + accountNumber);
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