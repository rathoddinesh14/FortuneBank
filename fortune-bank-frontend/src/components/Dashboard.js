import React from "react";
import { useNavigate } from "react-router-dom";
import DashboardItem from "./DashboardItem";

function Dashboard(props) {
  const history = useNavigate();

  const handleAddBeneficiary = (e) => {
    console.log(e);
    e.preventDefault();
    history("/addbeneficiary");
  };

  const handleTransfer = (e) => {
    console.log(e);
    e.preventDefault();
    history("/transfer");
  };

  const handleLogout = (e) => {
    console.log(e);
    e.preventDefault();
    history("/logout");
  };

  const handleBeneficiaries = (e) => {
    console.log(e);
    e.preventDefault();
    history("/beneficiaries");
  };

  const handleOverview = (e) => {
    console.log("Overview", e);
    e.preventDefault();
    history("/overview");
  };

  const handleTransactions = (e) => {
    console.log(e);
    e.preventDefault();
    history("/transactions");
  };

  return (
    <div className="container bg-white pb-4">
      <nav className="navbar navbar-expand-md navbar-dark bg-dark">
        {/* <a className="navbar-brand" href="/userhome">
          Online Banking
        </a> */}
        <button
          className="navbar-toggler"
          type="button"
          data-toggle="collapse"
          data-target="#navbarNav"
          aria-controls="navbarNav"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarNav">
          <ul className="navbar-nav ml-auto">
            <li className="nav-item">
              <button className="nav-link" type="button">
                Home
              </button>
            </li>
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

            {/* Add more links as needed */}
          </ul>
        </div>
      </nav>

      <div className="row">
        <nav className="col-md-2 d-none d-md-block bg-light sidebar ">
          {/* Vertical navigation */}
          <div className="sidebar-sticky">
            <ul className="nav flex-column">
              <li className="nav-item">
                <a
                  className="nav-link"
                  href="/overview"
                  onClick={handleOverview}
                >
                  Overview
                </a>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="/payments">
                  Payments
                </a>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="/statements">
                  Statements
                </a>
              </li>
              <li className="nav-item">
                <a
                  className="nav-link"
                  href="/addbeneficiary"
                  onClick={handleAddBeneficiary}
                >
                  Add Beneficiary
                </a>
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
              {/* Add more vertical navigation items */}
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
