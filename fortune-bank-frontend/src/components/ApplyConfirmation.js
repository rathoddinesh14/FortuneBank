import React from "react";
import { useParams } from "react-router-dom";

const AccountConfirmation = () => {
  const { status } = useParams();

  return (
    <div className="container mt-5">
      <div className="alert alert-success" role="alert">
        {status === "success" && (
          <div>
            <h4 className="alert-heading">Account created successfully!</h4>
            <p>
              Your account has been created successfully. Please check your
              email for further instructions.
            </p>
          </div>
        )}
        {status === "failure" && (
          <div>
            <h4 className="alert-heading">Account creation failed!</h4>
            <p>Your account could not be created. Please try again later.</p>
          </div>
        )}
      </div>
      <p>Thank you for choosing our services.</p>
    </div>
  );
};

export default AccountConfirmation;
