import React, { Component } from "react";
import AuthenticationService from "../service/AuthenticationService";

class Logout extends Component {
  componentDidMount() {
    AuthenticationService.logout();
  }

  render() {
    return (
      <div className="container mt-4">
        <div className="row"></div>
        <div className="row">
          <div className="col-md-3">
            {/* <img
              src="images/logout.jpeg"
              alt="Thank You"
              className="img-fluid"
            /> */}
          </div>
          <div className="col-md-6">
            {/* <div className="alert alert-success text-center" role="alert">
              <h1 className="display-4">Thank You</h1>
              <p className="lead">
                We appreciate your visit. Have a great day!
              </p>
            </div> */}
            <h1
              style={{
                textAlign: "center",
                display: "inline",
                position: "absolute",
                top: "40%",
                left: "43%",
              }}
            >
              Thank you
            </h1>
            <h6
              style={{
                textAlign: "center",
                display: "inline",
                position: "absolute",
                top: "48%",
                left: "38.7%",
                fontWeight: "bold",
              }}
            >
              We appreciate your visit. Have a great day!
            </h6>
          </div>
          <div className="col-md-3"></div>
        </div>
      </div>
    );
  }
}

export default Logout;
