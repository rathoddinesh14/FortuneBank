import React, { Component } from "react";
import UserService from "../service/UserService";
import { isEnable } from "../utils/TextHandler";

class CustomerList extends Component {
  constructor(props) {
    super(props);
    this.state = {
      customers: [],
      viewMode: "card",
    };
  }

  handleToggleCustomer = (customerId) => {
    this.state.customers.forEach((customer) => {
      if (customer.accountNumber === customerId) {
        UserService.updateUser(
          customerId,
          !isEnable(customer.accountStatus) ? "ENABLED" : "DISABLED"
        )
          .then((response) => {
            this.setState((prevState) => ({
              customers: prevState.customers.map((customer) =>
                customer.accountNumber === customerId
                  ? {
                      ...customer,
                      accountStatus: !isEnable(customer.accountStatus)
                        ? "ENABLED"
                        : "DISABLED",
                    }
                  : customer
              ),
            }));
          })
          .catch((error) => {
            console.log(error);
          });
      }
    });
  };

  componentDidMount() {
    UserService.getAllUsers()
      .then((response) => {
        this.setState({ customers: response.data });
      })
      .catch((error) => {
        console.log(error);
      });
  }

  handleViewModeChange = (mode) => {
    this.setState({ viewMode: mode });
  };

  render() {
    const { customers, viewMode } = this.state;

    return (
      <div>
        <h2>Customer List</h2>
        <div className="d-flex justify-content-end mb-3">
          {/* Toggle button to switch view modes */}
          <button
            className={`btn btn-sm ${
              viewMode === "card" ? "btn-primary" : "btn-secondary"
            } mr-2`}
            onClick={() => this.handleViewModeChange("card")}
          >
            Card View
          </button>
          <button
            className={`btn btn-sm ${
              viewMode === "table" ? "btn-primary" : "btn-secondary"
            }`}
            onClick={() => this.handleViewModeChange("table")}
          >
            Table View
          </button>
        </div>
        {viewMode === "card" ? (
          <div style={{ maxHeight: "400px", overflowY: "auto" }}>
            <div className="card-deck">
              {customers.map((customer) => (
                <div className="card mb-3" key={customer.accountNumber}>
                  <div className="card-body">
                    <h5 className="card-title">
                      {customer.firstName} {customer.middleName}{" "}
                      {customer.lastName}
                    </h5>
                    <p className="card-text">{customer.email}</p>
                    <button
                      className={`btn btn-sm ${
                        isEnable(customer.accountStatus)
                          ? "btn-danger"
                          : "btn-success"
                      }`}
                      onClick={() =>
                        this.handleToggleCustomer(customer.accountNumber)
                      }
                    >
                      {isEnable(customer.accountStatus) ? "Disable" : "Enable"}
                    </button>
                  </div>
                </div>
              ))}
            </div>
          </div>
        ) : (
          <table className="table table-striped">
            <thead>
              <tr>
                <th>Account Number</th>
                <th>Name</th>
                <th>Email</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {customers.map((customer) => (
                <tr key={customer.accountNumber}>
                  <td>{customer.accountNumber}</td>
                  <td>
                    {customer.firstName} {customer.middleName}{" "}
                    {customer.lastName}
                  </td>
                  <td>{customer.email}</td>
                  <td>
                    <button
                      className={`btn btn-sm ${
                        isEnable(customer.accountStatus)
                          ? "btn-danger"
                          : "btn-success"
                      }`}
                      onClick={() =>
                        this.handleToggleCustomer(customer.accountNumber)
                      }
                    >
                      {isEnable(customer.accountStatus) ? "Disable" : "Enable"}
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
    );
  }
}

export default CustomerList;
