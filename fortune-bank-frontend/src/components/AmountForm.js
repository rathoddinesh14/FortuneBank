import React, { useState } from "react";
import TransactionService from "../service/TransactionService";
import AuthenticationService from "../service/AuthenticationService";
import { useNavigate } from "react-router-dom";

function AmountForm(props) {
  const history = useNavigate();
  const [amount, setAmount] = useState("");
  const [message, setMessage] = useState("");

  const handleAmountChange = (event) => {
    setAmount(event.target.value);
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    if (props.type === "deposit") {
      TransactionService.deposit({
        accountNumber: AuthenticationService.getLoggedInAccountNumber(),
        amount: amount,
      }).then((response) => {
        setMessage(response.data);
        setTimeout(() => {
          setMessage("");
          history("/transactionsuccess");
        }, 1000);
      });
    } else if (props.type === "withdraw") {
      TransactionService.withdraw({
        accountNumber: AuthenticationService.getLoggedInAccountNumber(),
        amount: amount,
      }).then((response) => {
        setMessage(response.data);
        setTimeout(() => {
          setMessage("");
          history("/transactionsuccess");
        }, 1000);
      });
    }
  };

  return (
    <div className="container mt-4">
      <h2>{props.type}</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="depositAmount">Enter {props.type} Amount:</label>
          <input
            type="number"
            className="form-control"
            id="depositAmount"
            value={amount}
            onChange={handleAmountChange}
            required
          />
        </div>
        <div className="mt-3"></div>
        <button type="submit" className="btn btn-primary">
          {props.type}
        </button>
      </form>
      {message && <div className="alert alert-success mt-3">{message}</div>}
    </div>
  );
}

export default AmountForm;
