import "./../styles/UserLogin.css";
import React, { useState } from "react";
import axios from "axios"; // Import axios
import { useNavigate } from "react-router-dom";

function AddTransaction() {
  const history = useNavigate();
 
  const [fromaccount, setFromaccount] = useState("");
  const [toaccount, setToaccount] = useState("");
  const [amount, setAmount] = useState("");
  const [remarks, setRemarks] = useState("");

  // const handleUsernameChange = (event) => {
  //   setUsername(event.target.value);
  // };

  // const handlePasswordChange = (event) => {
  //   setPassword(event.target.value);
  // };

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
      remarks: remarks
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
    <div className="container">
      <h2>Transaction Form</h2>
      <form onSubmit={handleSubmit}>
        <label htmlFor="fromaccount">Your Account Number: </label>
        <input
          type="text"
          id="fromaccount"
          value={fromaccount}
          onChange={handleFromAccountChange}
        />

        <label htmlFor="toaccount">Beneficiary Account Number: </label>
        <input
          type="text"
          id="toaccount"
          value={toaccount}
          onChange={handleToAccountChange}
        />

      <label htmlFor="amount">Amount: </label>
        <input
          type="text"
          id="amount"
          value={amount}
          onChange={handleAmountChange}
        />

        <label htmlFor="remarks">Remarks: </label>
        <input
          type="textarea"
          id="remarks"
          value={remarks}
          onChange={handleRemarksChange}
        />
    

        <button type="submit">Proceed</button>
      </form>
    </div>
  );
}

export default AddTransaction;
