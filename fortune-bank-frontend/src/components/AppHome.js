import React from "react";

function AppHome() {
  return (
    <div className="container mt-5">
      <div className="row">
        <div className="col-md-8">
          {/* Your main content in the left column */}
        </div>
        <div className="col-md-4">
          <div className="d-flex flex-column align-items-center">
            <h3 className="mb-4">Quick Links</h3>
            <a href="/login" className="container-fluid btn btn-primary mb-2">
              Login
            </a>
            <a
              href="/register"
              className="container-fluid btn btn-primary mb-2"
            >
              Register
            </a>
            <a href="/apply" className="container-fluid btn btn-primary mb-2">
              Apply online
            </a>
            <a
              href="/resetpassword"
              className="container-fluid btn btn-primary mb-2"
            >
              Forgot / Reset Password
            </a>
            <a
              href="/forgotuserid"
              className="container-fluid btn btn-primary mb-2"
            >
              Forgot User ID
            </a>
          </div>
        </div>
      </div>
    </div>
  );
}

export default AppHome;
