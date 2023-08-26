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

  const handleBeneficiaryChange = (event) => {
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
    };

    // Send POST request using axios
    TransactionService.addTransaction(data)
      .then((response) => {
        if (response.data) {
          setTimeout(() => {
            history("/transactionsuccess");
          }, 1000);
        }
      })
      .catch((error) => {
        console.error("Transaction Failed:", error, data);
      });
  };

  return (
    <div className="container mt-5 bg-white">
      <h2>Transaction</h2>
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
                  key={beneficiary.accountnumber}
                  value={beneficiary.accountnumber}
                >
                  {beneficiary.accountnumber} - {beneficiary.name} -{" "}
                  {beneficiary.nickname}
                </option>
              ))}
            </select>
          </div>
          <div className="row pb-1">
            <button
              className="btn-primary col-2"
              type="button"
              onClick={fetchBeneficiaries}
            >
              Fetch Beneficiaries
            </button>
            &nbsp;
            <span className="text-success col-4">{beneficiaryMessage}</span>
          </div>
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
