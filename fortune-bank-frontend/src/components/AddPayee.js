import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import AuthenticationService from "../service/AuthenticationService";
import BeneficiaryService from "../service/BeneficiaryService";

function AddPayee() {
  const history = useNavigate();
  const [beneficiaryname, setBeneficiaryname] = useState("");
  const accountnumber = AuthenticationService.getLoggedInAccountNumber();
  const [payeeaccountnumber, setPayeeaccountnumber] = useState("");
  const [nickname, setNickname] = useState("");
  const [savebeneficiary, setSavebeneficiary] = useState("");
  const [message, setMessage] = useState("");

  const handleBeneficiarynameChange = (event) => {
    setBeneficiaryname(event.target.value);
  };

  const handlePayeeAccountnumberChange = (event) => {
    setPayeeaccountnumber(event.target.value);
  };

  const handleNicknameChange = (event) => {
    setNickname(event.target.value);
  };

  const handleSaveBeneficiaryChange = (event) => {
    if (event.target.checked) {
      setSavebeneficiary(true);
    } else {
      setSavebeneficiary(false);
    }
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    const data = {
      beneficiaryname: beneficiaryname,
      accountnumber: accountnumber,
      payeeaccountnumber: payeeaccountnumber,
      nickname: nickname,
      savebeneficiary: savebeneficiary,
    };

    // Send POST request using axios
    BeneficiaryService.addBeneficiary(data)
      .then((response) => {
        if (response.status === 200) {
          setMessage(response.data);
          setTimeout(() => {
            history("/userhome");
          }, 2000);
        }
      })
      .catch((error) => {
        console.error("Unable to add Beneficiary:", error, data);
      });
  };

  return (
    <div className="container mt-5 bg-white">
      <h2>Add Beneficiary</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="accountnumber">Account Number:</label>
          <input
            type="text"
            className="form-control"
            id="accountnumber"
            value={accountnumber}
            readOnly
          />
        </div>

        <div className="form-group">
          <label htmlFor="beneficiaryname">Beneficiary Name:</label>
          <input
            type="text"
            className="form-control"
            id="beneficiaryname"
            value={beneficiaryname}
            onChange={handleBeneficiarynameChange}
          />
        </div>

        <div className="form-group">
          <label htmlFor="payeeaccountnumber">Payee Account Number:</label>
          <input
            type="text"
            className="form-control"
            id="payeeaccountnumber"
            value={payeeaccountnumber}
            onChange={handlePayeeAccountnumberChange}
          />
        </div>

        <div className="form-group">
          <label htmlFor="nickname">Nickname:</label>
          <input
            type="text"
            className="form-control"
            id="nickname"
            value={nickname}
            onChange={handleNicknameChange}
          />
        </div>

        <div className="form-group">
          <div className="form-check">
            <input
              type="checkbox"
              className="form-check-input"
              id="savebeneficiary"
              checked={savebeneficiary}
              onChange={handleSaveBeneficiaryChange}
            />
            <label
              className="form-check-label d-flex justify-content-between"
              htmlFor="savebeneficiary"
            >
              <span>Save Beneficiary</span>
            </label>
          </div>
        </div>

        <div className="mb-3">{/* Add margin bottom spacing */}</div>

        <button type="submit" className="btn btn-primary">
          Add Beneficiary
        </button>
      </form>
      {message && <div className="alert alert-success mt-3">{message}</div>}
    </div>
  );
}

export default AddPayee;
