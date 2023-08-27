import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import AddressForm from "./AddressForm";
import UserService from "../service/UserService";
import FormInput from "../utils/FormInput";

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

  const temporaryAddressInitialValues = {
    type: "Temporary",
    line1: "",
    line2: "",
    landmark: "",
    state: "",
    city: "",
    pincode: "",
  };

  const permanentAddressInitialValues = {
    type: "Permanent",
    line1: "",
    line2: "",
    landmark: "",
    state: "",
    city: "",
    pincode: "",
  };

  const [temporaryAddress, setTemporaryAddress] = useState(
    temporaryAddressInitialValues
  );
  const [permanentAddress, setPermanentAddress] = useState(
    permanentAddressInitialValues
  );

  const handleSubmit = (event) => {
    event.preventDefault();

    if (
      !firstName ||
      !lastName ||
      !phone ||
      !email ||
      !aadharNumber ||
      !dob ||
      !temporaryAddress ||
      !permanentAddress
    ) {
      alert("Please fill all the fields");
      return;
    }

    const data = {
      firstName: firstName,
      middleName: middleName,
      lastName: lastName,
      fatherName: fatherName,
      phone: phone,
      email: email,
      aadharNumber: aadharNumber,
      dob: dob,
      taddress: temporaryAddress,
      paddress: permanentAddress,
    };

    // checking if temporary is filled
    if (
      !temporaryAddress.line1 ||
      !temporaryAddress.line2 ||
      !temporaryAddress.landmark ||
      !temporaryAddress.state ||
      !temporaryAddress.city ||
      !temporaryAddress.pincode
    ) {
      alert("Please fill temporary address");
      return;
    }

    // checking if permanent is filled
    if (
      !permanentAddress.line1 ||
      !permanentAddress.line2 ||
      !permanentAddress.landmark ||
      !permanentAddress.state ||
      !permanentAddress.city ||
      !permanentAddress.pincode
    ) {
      alert("Please fill permanent address");
      return;
    }

    UserService.apply(data)
      .then((response) => {
        if (response.status === 200) {
          setTimeout(() => {
            history("/accountconfirm/success");
          }, 2000);
        }
      })
      .catch((error) => {
        console.error("Registration failed:", error, data);
        setTimeout(() => {
          history("/accountconfirm/failure");
        }, 2000);
      });
  };

  return (
    <div
      className="container mt-5"
      style={{ backgroundColor: "rgba(255, 255, 255, 0.7)" }}
    >
      <form onSubmit={handleSubmit}>
        <div className="row">
          <div className="col-md-6 p-4">
            <h2 className="mb-4">User Registration</h2>
            <FormInput
              label="First Name"
              name="firstName"
              value={firstName}
              onChange={(value) => setFirstName(value)}
              validate={(value) => {
                // only alphabets
                const regex = /^[a-zA-Z]+$/;
                return regex.test(value);
              }}
              message="Only alphabets are allowed and must be greater than 0 characters"
              blur={(value) => {
                return /^[a-zA-Z]+$/.test(value) && value.length > 0;
              }}
            />

            <FormInput
              label="Middle Name"
              name="middleName"
              value={middleName}
              onChange={(value) => setMiddleName(value)}
              validate={(value) => {
                // Only alphabets
                const regex = /^[a-zA-Z]+$/;
                return regex.test(value);
              }}
              message="Only alphabets are allowed. (Optional)"
              blur={(value) => {
                return /^[a-zA-Z]+$/.test(value) || value.length === 0;
              }}
            />

            <FormInput
              label="Last Name"
              name="lastName"
              value={lastName}
              onChange={(value) => setLastName(value)}
              validate={(value) => {
                // Only alphabets
                const regex = /^[a-zA-Z]+$/;
                return regex.test(value);
              }}
              message="Only alphabets are allowed and must be greater than 0 characters"
              blur={(value) => {
                return /^[a-zA-Z]+$/.test(value) && value.length > 0;
              }}
            />

            <FormInput
              label="Father's Name"
              name="fatherName"
              value={fatherName}
              onChange={(value) => setFatherName(value)}
              validate={(value) => {
                // Only alphabets
                const regex = /^[a-zA-Z]+$/;
                return regex.test(value);
              }}
              message="Only alphabets are allowed. (Optional)"
              blur={(value) => {
                return /^[a-zA-Z]+$/.test(value) || value.length === 0;
              }}
            />

            <FormInput
              label="Phone"
              name="phone"
              value={phone}
              onChange={(value) => setPhone(value)}
              validate={(value) => {
                // Valid phone number format > 1
                const regex = /^[0-9]+$/;
                return (
                  regex.test(value) && value.length >= 0 && value.length <= 10
                );
              }}
              message="Invalid phone number and must be 10 digits"
              blur={(value) => {
                return value.length === 10;
              }}
            />

            <FormInput
              label="Email"
              name="email"
              value={email}
              onChange={(value) => setEmail(value)}
              validate={(value) => {}}
              message="Invalid email address"
              blur={(value) => {
                // Validate email address on blur
                const regex = /^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$/;
                return regex.test(value);
              }}
            />

            <FormInput
              label="Aadhar Number"
              name="aadharNumber"
              value={aadharNumber}
              onChange={(value) => setAadharNumber(value)}
              validate={(value) => {
                const regex = /^[0-9]+$/;
                return regex.test(value) && value.length <= 12;
              }}
              message="Invalid Aadhar number and must be 12 digits"
              blur={(value) => {
                return value.length === 12;
              }}
            />

            <FormInput
              label="Date of Birth"
              name="dob"
              value={dob}
              onChange={(value) => setDob(value)}
              validate={(value) => true}
              message="Invalid date format (dd/mm/yyyy)"
              blur={(value) => true}
            />
          </div>
          <div className="col-md-6 p-4">
            <AddressForm
              temporaryAddress={temporaryAddress}
              permanentAddress={permanentAddress}
              setTemporaryAddress={setTemporaryAddress}
              setPermanentAddress={setPermanentAddress}
              permanentAddressInitialValues={permanentAddressInitialValues}
              temporaryAddressInitialValues={temporaryAddressInitialValues}
            />
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
