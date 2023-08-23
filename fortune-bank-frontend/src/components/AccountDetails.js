import React, { Component } from "react";
import AuthenticationService from "../service/AuthenticationService";
import UserService from "../service/UserService";

class AccountDetails extends Component {
  state = {
    user: {},
  };

  componentDidMount() {
    UserService.getUserDetails(AuthenticationService.getLoggedInAccountNumber())
      .then((response) => {
        this.setState({ user: response.data });
      })
      .catch((error) => {
        console.error("Error fetching user details:", error);
      });
  }

  render() {
    const { user } = this.state;

    return (
      <div className="container mt-4">
        <div className="card">
          <div className="card-header">
            <h6 className="card-title">Account Details</h6>
          </div>
          <div className="card-body">
            <table className="table table-bordered">
              <tbody>
                {Object.entries(user).map(([key, value], index) => (
                  <tr key={index}>
                    <td className="">{key}</td>
                    <td className="">{value}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      </div>
    );
  }
}

export default AccountDetails;
