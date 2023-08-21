import React from "react";

function Dashboard() {
  return (
    <div className="container bg-white">
      <nav className="navbar navbar-expand-md navbar-dark bg-dark">
        <a className="navbar-brand" href="/dashboard">
          Online Banking
        </a>
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
              <a className="nav-link" href="/dashboard">
                Home
              </a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="/accounts">
                Accounts
              </a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="/transactions">
                Transactions
              </a>
            </li>
            {/* Add more links as needed */}
          </ul>
        </div>
      </nav>

      <div className="row">
        <nav className="col-md-2 d-none d-md-block bg-light sidebar">
          {/* Vertical navigation */}
          <div className="sidebar-sticky">
            <ul className="nav flex-column">
              <li className="nav-item">
                <a className="nav-link" href="/overview">
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
              {/* Add more vertical navigation items */}
            </ul>
          </div>
        </nav>

        <main role="main" className="col-md-9 ml-sm-auto col-lg-10 px-4">
          {/* Main content of the dashboard */}
          <h1 className="mt-3">Welcome to Online Banking Dashboard</h1>
          {/* Add more content here */}
        </main>
      </div>
    </div>
  );
}

export default Dashboard;
