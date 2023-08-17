import "./../styles/NetBankingRegistration.css";
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
    <div className="container">
      <h2>Net Banking Registration</h2>
      <form onSubmit={handleSubmit}>
        <label htmlFor="accountNumber">Account Number: </label>
        <input
          type="text"
          id="accountNumber"
          value={accountNumber}
          onChange={(e) => setAccountNumber(e.target.value)}
        />

        <label htmlFor="loginPassword">Login Password: </label>
        <input
          type="password"
          id="loginPassword"
          value={loginPassword}
          onChange={(e) => setLoginPassword(e.target.value)}
        />

        <label htmlFor="transactionPassword">Transaction Password: </label>
        <input
          type="password"
          id="transactionPassword"
          value={transactionPassword}
          onChange={(e) => setTransactionPassword(e.target.value)}
        />

        <label htmlFor="otp">OTP: </label>
        <input
          type="text"
          id="otp"
          value={otp}
          onChange={(e) => setOtp(e.target.value)}
        />

        <button type="submit">Register</button>
      </form>
    </div>
  );
}

export default NetBankingRegistration;
