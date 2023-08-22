import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import AddressForm from "./AddressForm";

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
    <div className="container mt-5">
      <form onSubmit={handleSubmit}>
        <div className="row">
          <div className="col-md-6 bg-white p-4">
            <h2 className="mb-4">User Registration</h2>
            <div className="form-group">
              <label htmlFor="firstName">First Name:</label>
              <input
                type="text"
                className="form-control"
                id="firstName"
                value={firstName}
                onChange={(e) => setFirstName(e.target.value)}
              />
            </div>

            <div className="form-group">
              <label htmlFor="middleName">Middle Name:</label>
              <input
                type="text"
                className="form-control"
                id="middleName"
                value={middleName}
                onChange={(e) => setMiddleName(e.target.value)}
              />
            </div>

            <div className="form-group">
              <label htmlFor="lastName">Last Name:</label>
              <input
                type="text"
                className="form-control"
                id="lastName"
                value={lastName}
                onChange={(e) => setLastName(e.target.value)}
              />
            </div>

            <div className="form-group">
              <label htmlFor="fatherName">Father's Name:</label>
              <input
                type="text"
                className="form-control"
                id="fatherName"
                value={fatherName}
                onChange={(e) => setFatherName(e.target.value)}
              />
            </div>

            <div className="form-group">
              <label htmlFor="phone">Phone:</label>
              <input
                type="tel"
                className="form-control"
                id="phone"
                value={phone}
                onChange={(e) => setPhone(e.target.value)}
              />
            </div>

            <div className="form-group">
              <label htmlFor="email">Email:</label>
              <input
                type="email"
                className="form-control"
                id="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
              />
            </div>

            <div className="form-group">
              <label htmlFor="aadharNumber">Aadhar Number:</label>
              <input
                type="text"
                className="form-control"
                id="aadharNumber"
                value={aadharNumber}
                onChange={(e) => setAadharNumber(e.target.value)}
              />
            </div>

            <div className="form-group">
              <label htmlFor="dob">Date of Birth:</label>
              <input
                type="date"
                className="form-control"
                id="dob"
                value={dob}
                onChange={(e) => setDob(e.target.value)}
              />
            </div>

            {/* <div className="mb-3"></div> */}
          </div>
          <div className="col-md-6 bg-white p-4">
            <AddressForm />
          </div>
          <div className="row">
            <button type="submit" className="btn btn-primary">
              Register
            </button>
          </div>
        </div>
      </form>
    </div>
  );
}

export default ApplyOnline;
