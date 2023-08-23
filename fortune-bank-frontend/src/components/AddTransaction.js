import React, { useState } from "react";
import axios from "axios"; // Import axios
import { useNavigate } from "react-router-dom";
import AuthenticationService from "../service/AuthenticationService";

function AddTransaction() {
  const history = useNavigate();

  const [toaccount, setToaccount] = useState("");
  const [amount, setAmount] = useState("");
  const [remarks, setRemarks] = useState("");
  const [maturityInstructions, setMaturityInstructions] = useState("");

  const handleToAccountChange = (event) => {
    setToaccount(event.target.value);
  };

  const handleAmountChange = (event) => {
    setAmount(event.target.value);
  };

  const handleRemarksChange = (event) => {
    setRemarks(event.target.value);
  };

  const handleMaturityInstructionsChange = (event) => {
    setMaturityInstructions(event.target.value);
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    const data = {
      toaccount: toaccount,
      amount: amount,
      remarks: remarks,
    };

    // Send POST request using axios
    axios
      .post("http://localhost:8080/fortunebank/api/transaction/transfer", data)
      .then((response) => {
        console.log("Transaction Successful:", response.data);
        alert("Transaction Successful");
        if (response.data) {
          setTimeout(() => {
            history("/userhome");
          }, 2000);
        }
      })
      .catch((error) => {
        console.error("Transaction Failed:", error, data);
      });
  };

  return (
    <div className="container mt-5 bg-white">
      <h2>Transaction Form</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="fromaccount">Your Account Number:</label>
          <input
            type="text"
            className="form-control"
            id="fromaccount"
            value={AuthenticationService.getLoggedInAccountNumber()}
            readOnly
          />
        </div>

        <div className="form-group">
          <label htmlFor="toaccount">Beneficiary Account Number:</label>
          <input
            type="text"
            className="form-control"
            id="toaccount"
            value={toaccount}
            onChange={handleToAccountChange}
          />
        </div>

        <div className="form-group">
          <label htmlFor="amount">Amount:</label>
          <input
            type="text"
            className="form-control"
            id="amount"
            value={amount}
            onChange={handleAmountChange}
          />
        </div>

        <div className="form-group">
          <label htmlFor="remarks">Remarks:</label>
          <textarea
            className="form-control"
            id="remarks"
            value={remarks}
            placeholder="Optional"
            onChange={handleRemarksChange}
          />
        </div>

        <div className="form-group">
          <label htmlFor="maturityInstructions">Maturity Instructions:</label>
          <textarea
            className="form-control"
            id="maturityInstructions"
            placeholder="Optional"
            value={maturityInstructions}
            onChange={handleMaturityInstructionsChange}
          />
        </div>

        <div className="mb-3">{/* Add margin bottom spacing */}</div>

        <button type="submit" className="btn btn-primary">
          Proceed
        </button>
      </form>
    </div>
  );
}

export default AddTransaction;
