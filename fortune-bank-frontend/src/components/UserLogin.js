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
    <div className="container mt-5">
      <div className="row">
        <div className="col-md-8">{/* Main content in the left column */}</div>
        <div className="col-md-4 bg-white p-4">
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
        </div>
      </div>
    </div>
  );
}

export default UserLogin;
