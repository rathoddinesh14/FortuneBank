import React from "react";
import { useParams } from "react-router-dom";
import AuthenticationService from "../service/AuthenticationService";

function TransactionStatus() {
  const { transactionStatus } = useParams();

  const responseTransaction = JSON.parse(
    AuthenticationService.getTransactionStatus()
  );

  return (
    <div className="container mt-5">
      <div
        className={`alert alert-${
          transactionStatus === "success" ? "success" : "danger"
        }`}
        role="alert"
      >
        <h4 className="alert-heading">
          {transactionStatus === "success"
            ? "Transaction Successful!"
            : "Transaction Failed!"}
        </h4>
        <p>
          {transactionStatus === "success"
            ? "Your transaction has been completed successfully."
            : "Your transaction has failed. Please try again."}
        </p>
        <hr />
        <p className="mb-0">
          Transaction ID: {responseTransaction.tid} <br />
          From Account: {responseTransaction.fromAccountNumber} <br />
          To Account: {responseTransaction.toAccountNumber} <br />
          Amount: Rs. {responseTransaction.amount} <br />
          Date: {responseTransaction.date} <br />
          Remark: {responseTransaction.remark} <br />
          Maturity Instructions: {responseTransaction.maturityInstructions}{" "}
          <br />
          Transaction Type: {responseTransaction.transactionType}
          <br />
          Transaction Mode: {responseTransaction.transactionMode}
        </p>
      </div>
    </div>
  );
}

export default TransactionStatus;
