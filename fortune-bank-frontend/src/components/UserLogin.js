import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import AuthenticationService from "../service/AuthenticationService";
import UserService from "../service/UserService";

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

    AuthenticationService.login(username, password)
      .then((response) => {
        console.log("Login successful:", response.data);
        alert("Login successful");
        UserService.getAccountNumber(username).then((response) => {
          AuthenticationService.registerSuccessfulLogin(response.data);
          console.log("account number ", response.data);
        });
        if (response.data) {
          setTimeout(() => {
            history("/userhome");
          }, 2000);
        }
      })
      .catch((error) => {
        console.error("Login failed:", error);
      });
  };

  return (
    <>
      <h2 className="mb-4">Login</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="username">Username:</label>
          <input
            type="text"
            className="form-control"
            id="username"
            value={username}
            onChange={handleUsernameChange}
          />
        </div>

        <div className="form-group">
          <label htmlFor="password">Password:</label>
          <input
            type="password"
            className="form-control"
            id="password"
            value={password}
            onChange={handlePasswordChange}
          />
        </div>

        <div className="mb-3">{/* Add margin bottom spacing */}</div>

        <button type="submit" className="btn btn-primary">
          Login
        </button>
      </form>
    </>
  );
}

export default UserLogin;
