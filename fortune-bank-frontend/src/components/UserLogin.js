import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import AuthenticationService from "../service/AuthenticationService";
import UserService from "../service/UserService";
import handleError from "../utils/ErrorHandler";

function UserLogin() {
  const history = useNavigate();
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [message, setMessage] = useState("");
  const [isAdmin, setIsAdmin] = useState(false);

  const handleUsernameChange = (event) => {
    setUsername(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const handleToggleMode = () => {
    setIsAdmin(!isAdmin);
    setMessage("");
    setUsername("");
    setPassword("");
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    if (isAdmin) {
      AuthenticationService.adminlogin(username, password)
        .then((response) => {
          AuthenticationService.setAdminMode(true);
          setMessage(response.data);
          if (response.status === 200) {
            setTimeout(() => {
              history("/userhome");
            }, 1000);
          }
        })
        .catch((error) => {
          handleError(error, setMessage);
        });
    } else {
      AuthenticationService.login(username, password)
        .then((response) => {
          setMessage(response.data);
          if (response.status === 200) {
            UserService.getAccountNumber(username).then((response) => {
              AuthenticationService.registerSuccessfulLogin(response.data);
            });
            AuthenticationService.setAdminMode(false);
            setTimeout(() => {
              history("/userhome");
            }, 1000);
          }
        })
        .catch((error) => {
          handleError(error, setMessage);
        });
    }
  };

  return (
    <>
      <div>
        <div className="d-flex justify-content-end mb-3">
          {/* Toggle buttons */}
          <button
            className={`btn btn-sm ${
              isAdmin ? "btn-secondary" : "btn-primary"
            } mr-2`}
            onClick={handleToggleMode}
          >
            User Login
          </button>
          <button
            className={`btn btn-sm ${
              isAdmin ? "btn-primary" : "btn-secondary"
            }`}
            onClick={handleToggleMode}
          >
            Admin Login
          </button>
        </div>
        <h2 className="mb-4">{isAdmin ? "Admin" : "User"} Login</h2>
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
        {message && (
          <div className="alert alert-success mt-3" role="alert" id="message">
            {message}
          </div>
        )}
      </div>
    </>
  );
}

export default UserLogin;
