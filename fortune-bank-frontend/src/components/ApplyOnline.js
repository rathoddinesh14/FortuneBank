import "./../styles/ApplyOnline.css";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

function ApplyOnline() {
  const history = useNavigate();
  const [firstName, setFirstName] = useState("");
  const [middleName, setMiddleName] = useState("");
  const [lastName, setLastName] = useState("");
  const [fatherName, setFatherName] = useState("");
  const [phone, setPhone] = useState("");
  const [email, setEmail] = useState("");
  const [aadharNumber, setAadharNumber] = useState("");
  const [dob, setDob] = useState("");

  const handleSubmit = (event) => {
    event.preventDefault();

    // You can perform form validation and submit the data to your backend here
    // For now, just logging the values
    console.log("First Name:", firstName);
    console.log("Middle Name:", middleName);
    console.log("Last Name:", lastName);
    console.log("Father Name:", fatherName);
    console.log("Phone:", phone);
    console.log("Email:", email);
    console.log("Aadhar Number:", aadharNumber);
    console.log("DOB:", dob);

    const data = {
      firstName: firstName,
      middleName: middleName,
      lastName: lastName,
      fatherName: fatherName,
      phone: phone,
      email: email,
      aadharNumber: aadharNumber,
      dob: dob,
    };

    // Send POST request using axios
    axios
      .post("http://localhost:8080/fortunebank/api/apply", data)
      .then((response) => {
        console.log("Apply Online successful:", response.data);
        if (response.data) {
          setTimeout(() => {
            history("/");
          }, 2000);
        }
      })
      .catch((error) => {
        console.error("Registration failed:", error, data);
      });
  };

  return (
    <div className="container">
      <h2>User Registration</h2>
      <form onSubmit={handleSubmit}>
        <label htmlFor="firstName">First Name:</label>
        <input
          type="text"
          id="firstName"
          value={firstName}
          onChange={(e) => setFirstName(e.target.value)}
        />

        <label htmlFor="middleName">Middle Name:</label>
        <input
          type="text"
          id="middleName"
          value={middleName}
          onChange={(e) => setMiddleName(e.target.value)}
        />

        <label htmlFor="lastName">Last Name:</label>
        <input
          type="text"
          id="lastName"
          value={lastName}
          onChange={(e) => setLastName(e.target.value)}
        />

        <label htmlFor="fatherName">Father's Name:</label>
        <input
          type="text"
          id="fatherName"
          value={fatherName}
          onChange={(e) => setFatherName(e.target.value)}
        />

        <label htmlFor="phone">Phone:</label>
        <input
          type="tel"
          id="phone"
          value={phone}
          onChange={(e) => setPhone(e.target.value)}
        />

        <label htmlFor="email">Email:</label>
        <input
          type="email"
          id="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />

        <label htmlFor="aadharNumber">Aadhar Number:</label>
        <input
          type="text"
          id="aadharNumber"
          value={aadharNumber}
          onChange={(e) => setAadharNumber(e.target.value)}
        />

        <label htmlFor="dob">Date of Birth:</label>
        <input
          type="date"
          id="dob"
          value={dob}
          onChange={(e) => setDob(e.target.value)}
        />

        <button type="submit">Register</button>
      </form>
    </div>
  );
}

export default ApplyOnline;
