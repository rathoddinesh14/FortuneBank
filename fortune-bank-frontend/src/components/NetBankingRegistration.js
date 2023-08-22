import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

function NetBankingRegistration() {
  const history = useNavigate();
  const [accountNumber, setAccountNumber] = useState("");
  const [loginPassword, setLoginPassword] = useState("");
  const [transactionPassword, setTransactionPassword] = useState("");
  const [otp, setOtp] = useState("");

  const handleSubmit = (event) => {
    event.preventDefault();

    const data = {
      accountnumber: accountNumber,
      loginpassword: loginPassword,
      transactionpassword: transactionPassword,
      otp: otp,
    };

    // Send POST request using axios
    axios
      .post("http://localhost:8080/fortunebank/api/register", data)
      .then((response) => {
        console.log("Registration successful:", response.data);
        alert("Registration successful");
        if (response.data) {
          setTimeout(() => {
            history("/");
          }, 2000);
        }
      })
      .catch((error) => {
        console.error("Registration failed:", error, data);
      });

    // You can perform form validation and submit the data to your backend here
    // For now, just logging the values
    console.log("Account Number:", accountNumber);
    console.log("Login Password:", loginPassword);
    console.log("Transaction Password:", transactionPassword);
    console.log("OTP:", otp);
  };

  return (
    <>
      <h2 className="mb-4">Net Banking Registration</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="accountNumber">Account Number:</label>
          <input
            type="text"
            className="form-control"
            id="accountNumber"
            value={accountNumber}
            onChange={(e) => setAccountNumber(e.target.value)}
          />
        </div>

        <div className="form-group">
          <label htmlFor="loginPassword">Login Password:</label>
          <input
            type="password"
            className="form-control"
            id="loginPassword"
            value={loginPassword}
            onChange={(e) => setLoginPassword(e.target.value)}
          />
        </div>

        <div className="form-group">
          <label htmlFor="transactionPassword">Transaction Password:</label>
          <input
            type="password"
            className="form-control"
            id="transactionPassword"
            value={transactionPassword}
            onChange={(e) => setTransactionPassword(e.target.value)}
          />
        </div>

        <div className="form-group">
          <label htmlFor="otp">OTP:</label>
          <input
            type="text"
            className="form-control"
            id="otp"
            value={otp}
            onChange={(e) => setOtp(e.target.value)}
          />
        </div>

        <div className="mb-3">{/* Add margin bottom spacing */}</div>

        <button type="submit" className="btn btn-primary">
          Register
        </button>
      </form>
    </>
  );
}

export default NetBankingRegistration;
