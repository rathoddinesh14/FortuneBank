import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function ForgotPassword() {
  const history = useNavigate();
  const [userid, setUserid] = useState("");
  const [password, setPassword] = useState("");
  const [otp, setOtp] = useState("");

  const handleSubmit = (event) => {
    event.preventDefault();

    // You can perform form validation and submit the data to your backend here
    // For now, just logging the values
    console.log("UserID:", userid);
    console.log("Password:", password);
    console.log("OTP:", otp);

    const data = {
      userid: userid,
      password: password,
      otp: otp,
    };

    // Send POST request using axios
    axios
      .post("http://localhost:8080/fortunebank/api/resetpassword", data)
      .then((response) => {
        console.log("Reset Password successful:", response.data);
        if (response.data) {
          setTimeout(() => {
            history("/");
          }, 2000);
        }
      })
      .catch((error) => {
        console.error("Reset Password failed:", error, data);
      });
  };

  return (
    <>
      <h2 className="mb-4">Forgot Password</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="userid">User ID:</label>
          <input
            type="text"
            className="form-control"
            id="userid"
            value={userid}
            onChange={(e) => setUserid(e.target.value)}
          />
        </div>

        <div className="form-group">
          <label htmlFor="password">Password:</label>
          <input
            type="password"
            className="form-control"
            id="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
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

export default ForgotPassword;
