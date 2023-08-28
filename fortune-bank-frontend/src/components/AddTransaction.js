import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import AuthenticationService from "../service/AuthenticationService";
import BeneficiaryService from "../service/BeneficiaryService";
import TransactionService from "../service/TransactionService";

function AddTransaction() {
  const history = useNavigate();

  const [toaccount, setToaccount] = useState("");
  const [beneficiaries, setBeneficiaries] = useState([]);
  const [amount, setAmount] = useState("");
  const [remarks, setRemarks] = useState("");
  const [maturityInstructions, setMaturityInstructions] = useState("");
  const [beneficiaryMessage, setBeneficiaryMessage] = useState("");
  const [transactionMode, setTransactionMode] = useState("");
  const [message, setMessage] = useState("");

  const handleBeneficiaryChange = (event) => {
    setToaccount(event.target.value);
  };

  const handleTransactionModeChange = (event) => {
    setTransactionMode(event.target.value);
  };

  const handleAmountChange = (event) => {
    const regdouble = /^(\d*\.)?\d+$/;
    if (event.target.value === "" || regdouble.test(event.target.value)) {
      setAmount(event.target.value);
    }
  };

  const handleRemarksChange = (event) => {
    setRemarks(event.target.value);
  };

  const handleMaturityInstructionsChange = (event) => {
    setMaturityInstructions(event.target.value);
  };

  const fetchBeneficiaries = () => {
    BeneficiaryService.getBeneficiaries().then((response) => {
      setBeneficiaries(response.data);
      setBeneficiaryMessage("Beneficiaries Fetched : " + response.data.length);
    });
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    const data = {
      fromaccount: AuthenticationService.getLoggedInAccountNumber(),
      toaccount: toaccount,
      amount: amount,
      remarks: remarks,
      maturityInstructions: maturityInstructions,
      mode: transactionMode,
    };

    // Send POST request using axios
    TransactionService.addTransaction(data)
      .then((response) => {
        if (response.status === 200) {
          setTimeout(() => {
            AuthenticationService.setTransactionStatus(
              JSON.stringify(response.data)
            );
            history("/transaction/success");
          }, 1000);
        }
      })
      .catch((error) => {
        setTimeout(() => {
          AuthenticationService.setTransactionStatus(
            JSON.stringify(error.response.data)
          );
          history("/transaction/failure");
        }, 1000);
      });
  };

  return (
    <div className="container mt-5 bg-white">
      <h2>Transaction</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <div className="row">
            <div className="col-6">
              <label htmlFor="fromaccount" className="form-label">
                Your Account Number:
              </label>
              <input
                type="text"
                className="form-control"
                id="fromaccount"
                value={AuthenticationService.getLoggedInAccountNumber()}
                readOnly
              />
            </div>
            <div className="col-6">
              <label htmlFor="transactionMode" className="form-label">
                Transaction Mode
              </label>
              <select
                className="form-select"
                id="transactionMode"
                value={transactionMode}
                onChange={handleTransactionModeChange}
              >
                <option value="" disabled>
                  Select Transaction Mode
                </option>
                <option value="NEFT">NEFT</option>
                <option value="IMPS">IMPS</option>
                <option value="RTGS">RTGS</option>
              </select>
            </div>
          </div>
        </div>

        <div className="form-group">
          <label htmlFor="toaccount" className="form-label">
            Beneficiary Account Number:
          </label>
          <div className="mb-3">
            <select
              className="form-select"
              id="toaccount"
              value={toaccount}
              onChange={handleBeneficiaryChange}
            >
              <option value="" disabled>
                Select Beneficiary
              </option>
              {beneficiaries.map((beneficiary) => (
                <option
                  key={beneficiary.payeeAccountNumber}
                  value={beneficiary.payeeAccountNumber}
                >
                  {beneficiary.payeeAccountNumber} - {beneficiary.name} -{" "}
                  {beneficiary.nickName}
                </option>
              ))}
            </select>
          </div>
          <div className="row pb-1">
            <div className="col-2">
              <button
                className="btn-primary"
                type="button"
                onClick={fetchBeneficiaries}
              >
                Fetch Beneficiaries
              </button>
            </div>
            <div className="col-6">
              <span className="text-success col-4">{beneficiaryMessage}</span>
            </div>
          </div>
        </div>

        <div className="form-group">
          <label htmlFor="amount" className="form-label">
            Amount:
          </label>
          <input
            type="number"
            className="form-control"
            id="amount"
            value={amount}
            onChange={handleAmountChange}
          />
        </div>

        <div className="form-group">
          <label htmlFor="remarks" className="form-label">
            Remarks:
          </label>
          <textarea
            className="form-control"
            id="remarks"
            value={remarks}
            placeholder="Optional"
            onChange={handleRemarksChange}
          />
        </div>

        <div className="form-group">
          <label htmlFor="maturityInstructions" className="form-label">
            Maturity Instructions:
          </label>
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
