import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function ForgotUserId() {
  const history = useNavigate();
  const [accountnumber, setAccountNumber] = useState("");
  const [otp, setOtp] = useState("");

  const handleSubmit = (event) => {
    event.preventDefault();

    // You can perform form validation and submit the data to your backend here
    // For now, just logging the values
    console.log("Account Number:", accountnumber);
    console.log("OTP:", otp);

    const data = {
      accountnumber: accountnumber,
      otp: otp,
    };

    // Send POST request using axios
    axios
      .post("http://localhost:8080/fortunebank/api/forgotuserid", data)
      .then((response) => {
        console.log("successful:", response.data);
        if (response.data) {
          setTimeout(() => {
            history("/");
          }, 2000);
        }
      })
      .catch((error) => {
        console.error("Request failed:", error, data);
      });
  };

  return (
    <>
      <h2 className="mb-4">Forgot User ID</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="accountnumber">Account Number:</label>
          <input
            type="text"
            className="form-control"
            id="accountnumber"
            value={accountnumber}
            onChange={(e) => setAccountNumber(e.target.value)}
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
          Submit
        </button>
      </form>
    </>
  );
}

export default ForgotUserId;
