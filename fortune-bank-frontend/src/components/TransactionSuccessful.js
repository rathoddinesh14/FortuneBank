import React from "react";

function TransactionSuccessful() {
  return (
    <div className="container mt-5">
      <div className="alert alert-success" role="alert">
        <h4 className="alert-heading">Transaction Successful!</h4>
        <p>Your transaction has been completed successfully.</p>
        <hr />
        <p className="mb-0">Thank you for using our services.</p>
      </div>
    </div>
  );
}

export default TransactionSuccessful;
