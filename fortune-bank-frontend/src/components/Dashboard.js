import React from "react";
import { useNavigate } from "react-router-dom";
import DashboardItem from "./DashboardItem";
import AuthenticationService from "../service/AuthenticationService";

function Dashboard(props) {
  const history = useNavigate();

  const handleAddBeneficiary = (e) => {
    e.preventDefault();
    history("/addbeneficiary");
  };

  const handleDeposit = (e) => {
    e.preventDefault();
    history("/deposit");
  };

  const handleAccountSummary = (e) => {
    e.preventDefault();
    history("/accountsummary");
  };

  const handleWithdraw = (e) => {
    e.preventDefault();
    history("/withdraw");
  };

  const handleTransfer = (e) => {
    e.preventDefault();
    history("/transfer");
  };

  const handleLogout = (e) => {
    e.preventDefault();
    history("/logout");
  };

  const handleBeneficiaries = (e) => {
    e.preventDefault();
    history("/beneficiaries");
  };

  const handleCustomers = (e) => {
    e.preventDefault();
    history("/customers");
  };

  const handleCustomerSearch = (e) => {
    e.preventDefault();
    history("/customersearch");
  };

  const handleHome = (e) => {
    e.preventDefault();
    history("/userhome");
  };

  const handleOverview = (e) => {
    e.preventDefault();
    history("/overview");
  };

  const handleTransactions = (e) => {
    e.preventDefault();
    history("/transactions");
  };

  return (
    <div className="container bg-white pb-4">
      <nav className="navbar navbar-expand-md navbar-dark bg-dark">
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarNav"
          aria-controls="navbarNav"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarNav">
          <ul className="navbar-nav ml-auto">
            <li className="nav-item">
              <button className="nav-link" onClick={handleHome}>
                Home
              </button>
            </li>
            {AuthenticationService.isAdminMode() && (
              <li className="nav-item">
                <button className="nav-link" onClick={handleCustomerSearch}>
                  Customer Search
                </button>
              </li>
            )}
            {AuthenticationService.isAdminMode() && (
              <li className="nav-item">
                <button className="nav-link" onClick={handleCustomers}>
                  Customers
                </button>
              </li>
            )}
            <li className="nav-item">
              <button className="nav-link" onClick={handleBeneficiaries}>
                Beneficiaries
              </button>
            </li>
            <li className="nav-item">
              <button className="nav-link" onClick={handleTransactions}>
                Transactions
              </button>
            </li>
            <li className="nav-item">
              <button className="nav-link" onClick={handleLogout}>
                Logout
              </button>
            </li>
          </ul>
        </div>
      </nav>

      <div className="row">
        <nav className="col-md-2 d-none d-md-block bg-light sidebar ">
          {/* Vertical navigation */}
          <div className="sidebar-sticky">
            <ul className="nav flex-column">
              {!AuthenticationService.isAdminMode() && (
                <li className="nav-item">
                  <a
                    className="nav-link"
                    href="/overview"
                    onClick={handleOverview}
                  >
                    Overview
                  </a>
                </li>
              )}

              <li className="nav-item">
                <button className="nav-link" onClick={handleAccountSummary}>
                  Account Statements
                </button>
              </li>
              {!AuthenticationService.isAdminMode() && (
                <>
                  <li className="nav-item">
                    <button className="nav-link" onClick={handleDeposit}>
                      Deposit Amount
                    </button>
                  </li>
                  <li className="nav-item">
                    <button className="nav-link" onClick={handleWithdraw}>
                      Withdraw Amount
                    </button>
                  </li>
                  <li className="nav-item">
                    <button className="nav-link" onClick={handleAddBeneficiary}>
                      Add Beneficiary
                    </button>
                  </li>

                  <li className="nav-item">
                    <a
                      className="nav-link"
                      href="/transfer"
                      onClick={handleTransfer}
                    >
                      Transfer
                    </a>
                  </li>
                </>
              )}
            </ul>
          </div>
        </nav>

        <main role="main" className="col-md-9 ml-sm-auto col-lg-10 px-4">
          <DashboardItem urlParam={props.urlParam} className="m-4" />
        </main>
      </div>
    </div>
  );
}

export default Dashboard;
