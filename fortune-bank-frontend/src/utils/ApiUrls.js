const API_URLS = {
  getUserProfile: "/userprofile", // Example URL
  getTransactions: "/transactions", // Example URL
  adminBeneficiaries:
    "http://localhost:8080/fortunebank/api/admin/beneficiaries",
  adminCustomers: "http://localhost:8080/fortunebank/api/admin/customers",
  adminAccountStatus:
    "http://localhost:8080/fortunebank/api/admin/updateaccountstatus",
  adminCustomerSearch:
    "http://localhost:8080/fortunebank/api/admin/customersearch",
  adminTransactionsBetweenDates:
    "http://localhost:8080/fortunebank/api/admin/transactions-between-dates?",
};

export default API_URLS;
