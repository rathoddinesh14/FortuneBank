import React, { Component } from "react";
import AuthenticationService from "../service/AuthenticationService";

class Logout extends Component {
  componentDidMount() {
    AuthenticationService.logout();
  }

  render() {
    return (
      <div className="container mt-4">
        <div className="row">
          <div className="col-md-6">
            <img
              src="images/logout.jpeg"
              alt="Thank You"
              className="img-fluid"
            />
          </div>
          <div className="col-md-6">
            <h1>Thank You</h1>
            <p>We appreciate your visit. Have a great day!</p>
          </div>
        </div>
      </div>
    );
  }
}

export default Logout;
