import React from "react";
import BankCarousel from "./BankCarousel";

function AppHome() {
  return (
    <div className="container mt-5">
      <div className="row">
        <div className="col-md-8">
          <BankCarousel />
        </div>
        <div className="col-md-4">
          <div
            className="d-flex flex-column p-4 rounded shadow"
            style={{ backgroundColor: "rgba(127, 25, 52, 0.5)" }}
          >
            <h3 className="mb-4 text-white">Quick Links</h3>
            <a href="/login" className="btn btn-secondary  mb-2">
              Login
            </a>
            <a href="/register" className="btn btn-secondary  mb-2">
              Register
            </a>
            <a href="/apply" className="btn btn-secondary  mb-2">
              Apply Online
            </a>
            <a href="/resetpassword" className="btn btn-secondary  mb-2">
              Forgot / Reset Password
            </a>
            <a href="/forgotuserid" className="btn btn-secondary  mb-2">
              Forgot User ID
            </a>
          </div>
        </div>
      </div>
    </div>
  );
}

export default AppHome;
