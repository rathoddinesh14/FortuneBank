import React from "react";
import { useParams } from "react-router-dom";

function TransactionStatus() {
  const { transactionStatus } = useParams();

  return (
    <div className="container mt-5">
      {transactionStatus === "success" ? (
        <div className="alert alert-success" role="alert">
          <h4 className="alert-heading">Transaction Successful!</h4>
          <p>Your transaction has been completed successfully.</p>
          <hr />
          <p className="mb-0">Thank you for using our services.</p>
        </div>
      ) : (
        <div className="alert alert-danger" role="alert">
          <h4 className="alert-heading">Transaction Failed!</h4>
          <p>Your transaction has failed.</p>
          <hr />
          <p className="mb-0">Please try again.</p>
        </div>
      )}
    </div>
  );
}

export default TransactionStatus;
