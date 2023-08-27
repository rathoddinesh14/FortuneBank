import React, { useState } from "react";
import TransactionService from "../service/TransactionService";
import DisplayTable from "./DisplayTable";
import TransactionColumns from "./TransactionColumns";

function AccountSummary() {
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");
  const [transactions, setTransactions] = useState([]);

  const handleStartDateChange = (event) => {
    setStartDate(event.target.value);
  };

  const handleEndDateChange = (event) => {
    setEndDate(event.target.value);
  };

  const handleSearch = () => {
    TransactionService.getTransactionsBetweenDates(startDate, endDate)
      .then((response) => {
        setTransactions(response.data);
      })
      .catch((error) => {
        console.error("Error fetching transactions:", error);
      });
  };

  return (
    <div className="container mt-4">
      <h2>Transaction Search</h2>
      <div className="row">
        <div className="col-md-4">
          {" "}
          <div className="form-group">
            <label htmlFor="startDate">Start Date:</label>
            <input
              type="date"
              id="startDate"
              className="form-control"
              value={startDate}
              onChange={handleStartDateChange}
            />
          </div>
        </div>
        <div className="col-md-4">
          <div className="form-group">
            <label htmlFor="endDate">End Date:</label>
            <input
              type="date"
              id="endDate"
              className="form-control"
              value={endDate}
              onChange={handleEndDateChange}
            />
          </div>
        </div>
        <div className="col-md-4 ">
          {" "}
          <button className="btn btn-primary mt-4" onClick={handleSearch}>
            Search
          </button>
        </div>
      </div>
      <h3 className="mt-4">Transactions:</h3>
      <DisplayTable
        data={transactions}
        columns={TransactionColumns}
        table="Transactions"
      />
    </div>
  );
}

export default AccountSummary;
