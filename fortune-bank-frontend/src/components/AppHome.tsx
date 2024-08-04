import React from "react";
import { useNavigate } from "react-router-dom";

function AppHome() {
  const history = useNavigate();

  const handleLogin = (e) => {
    e.preventDefault();
    history("/login");
  };

  const handleRegister = (e) => {
    e.preventDefault();
    history("/register");
  };

  const handleApplyOnline = (e) => {
    e.preventDefault();
    history("/apply");
  };

  const handleResetPassword = (e) => {
    e.preventDefault();
    history("/resetpassword");
  };

  const handleForgetUserId = (e) => {
    e.preventDefault();
    history("/forgotuserid");
  };

  return (
    <div className="container mt-1">
      <div className="row">
        <div className="col-md-8">{/* <BankCarousel /> */}</div>
        <div className="col-md-4">
          <div
            className="d-flex flex-column p-4 rounded shadow"
            style={{ backgroundColor: "rgba(127, 25, 52, 0.5)" }}
          >
            <h3 className="mb-4 text-white">Quick Links</h3>
            <button className="btn btn-secondary mb-2" onClick={handleLogin}>
              Login
            </button>
            <button
              className="btn btn-secondary  mb-2"
              onClick={handleRegister}
            >
              Register
            </button>
            <button
              className="btn btn-secondary  mb-2"
              onClick={handleApplyOnline}
            >
              Apply Online
            </button>
            <button
              className="btn btn-secondary  mb-2"
              onClick={handleResetPassword}
            >
              Forgot / Reset Password
            </button>
            <button
              className="btn btn-secondary  mb-2"
              onClick={handleForgetUserId}
            >
              Forgot User ID
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default AppHome;
