import "./../styles/UserLogin.css";
import React, { useState } from "react";
import axios from "axios"; // Import axios
import { useNavigate } from "react-router-dom";

function UserLogin() {
  const history = useNavigate();
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleUsernameChange = (event) => {
    setUsername(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    const data = {
      userid: username,
      password: password,
    };

    // Send POST request using axios
    axios
      .post("http://localhost:8080/fortunebank/api/login", data)
      .then((response) => {
        console.log("Login successful:", response.data);
        alert("Login successful");
        if (response.data) {
          setTimeout(() => {
            history("/userhome");
          }, 2000);
        }
      })
      .catch((error) => {
        console.error("Login failed:", error, data);
      });
  };

  return (
    <div className="container">
      <h2>Login</h2>
      <form onSubmit={handleSubmit}>
        <label htmlFor="beneficiaryname">Beneficiary Name: </label>
        <input
          type="text"
          id="beneficiaryname"
          value={beneficiaryname}
          onChange={handleBeneficiarynameChange}
        />

        <label htmlFor="accountnumber">Account Number: </label>
        <input
          type="text"
          id="accountnumber"
          value={accountnumber}
          onChange={handleAccountnumberChange}
        />

        <label htmlFor="nickname">Nickname: </label>
        <input
          type="text"
          id="nickname"
          value={nickname}
          onChange={handleNicknameChange}
        />

        <label htmlFor="savebeneficiary">Save Beneficiary: </label>
        <input
          type="checkbox"
          value={savebeneficiary}
          onChange={handleSaveBeneficiaryChange}
        />

        <button type="submit">Login</button>
      </form>
    </div>
  );
}

export default UserLogin;
