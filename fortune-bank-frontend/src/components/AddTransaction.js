import React, { useState } from "react";
import axios from "axios"; // Import axios
import { useNavigate } from "react-router-dom";

function AddTransaction() {
  const history = useNavigate();

  const [fromaccount, setFromaccount] = useState("");
  const [toaccount, setToaccount] = useState("");
  const [amount, setAmount] = useState("");
  const [remarks, setRemarks] = useState("");

  const handleFromAccountChange = (event) => {
    setFromaccount(event.target.value);
  };

  const handleToAccountChange = (event) => {
    setToaccount(event.target.value);
  };

  const handleAmountChange = (event) => {
    setAmount(event.target.value);
  };

  const handleRemarksChange = (event) => {
    setRemarks(event.target.value);
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    const data = {
      fromaccount: fromaccount,
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
            value={fromaccount}
            onChange={handleFromAccountChange}
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
            onChange={handleRemarksChange}
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
