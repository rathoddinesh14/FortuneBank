import "./../styles/UserLogin.css";
import React, { useState } from "react";
import axios from "axios"; // Import axios
import { useNavigate } from "react-router-dom";

function AddPayee() {
  const history = useNavigate();
  const [beneficiaryname, setBeneficiaryname] = useState("");
  const [accountnumber, setAccountnumber] = useState("");
  const [payeeaccountnumber, setPayeeaccountnumber] = useState("");
  const [nickname, setNickname] = useState("");
  const [savebeneficiary, setSavebeneficiary] = useState("");

  // const handleUsernameChange = (event) => {
  //   setUsername(event.target.value);
  // };

  // const handlePasswordChange = (event) => {
  //   setPassword(event.target.value);
  // };

  const handleBeneficiarynameChange = (event) => {
    setBeneficiaryname(event.target.value);
  };

  const handleAccountnumberChange = (event) => {
    setAccountnumber(event.target.value);
  };

  const handlePayeeAccountnumberChange = (event) => {
    setPayeeaccountnumber(event.target.value);
  };

  const handleNicknameChange = (event) => {
    setNickname(event.target.value);
  };
  
  const handleSaveBeneficiaryChange = (event) => {
    setSavebeneficiary(event.target.value);
  };


  const handleSubmit = (event) => {
    event.preventDefault();

    const data = {
      beneficiaryname: beneficiaryname,
      accountnumber: accountnumber,
      payeeaccountnumber: payeeaccountnumber,
      nickname: nickname,
      savebeneficiary: savebeneficiary
    };

    // Send POST request using axios
    axios
      .post("http://localhost:8080/fortunebank/api/beneficiary/add", data)
      .then((response) => {
        console.log("Beneficiary Added:", response.data);
        alert("Beneficiary Added");
        if (response.data) {
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
    <div className="container">
      <h2>Add Beneficiary</h2>
      <form onSubmit={handleSubmit}>
        <label htmlFor="beneficiaryname">Beneficiary Name: </label>
        <input
          type="text"
          id="beneficiaryname"
          value={beneficiaryname}
          onChange={handleBeneficiarynameChange}
        />

        <label htmlFor="accountnumber">Account Number: </label>
        <input
          type="text"
          id="accountnumber"
          value={accountnumber}
          onChange={handleAccountnumberChange}
        />

      <label htmlFor="payeeaccountnumber">Payee Account Number: </label>
        <input
          type="text"
          id="payeeaccountnumber"
          value={payeeaccountnumber}
          onChange={handlePayeeAccountnumberChange}
        />

        <label htmlFor="nickname">Nickname: </label>
        <input
          type="text"
          id="nickname"
          value={nickname}
          onChange={handleNicknameChange}
        />
    
        <label htmlFor="savebeneficiary">Save Beneficiary: </label>
        <input
          type="checkbox"
          value={savebeneficiary}
          onChange={handleSaveBeneficiaryChange}
        />

        <button type="submit">Add Beneficiary</button>
      </form>
    </div>
  );
}

export default AddPayee;
