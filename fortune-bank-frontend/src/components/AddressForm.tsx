import React, { useState } from "react";
import FormInput from "../utils/FormInput";

function AddressForm(props) {
  const [addressType, setAddressType] = useState("temporary");
  const [sameAsPermanent, setSameAsPermanent] = useState(false);

  const handleAddressTypeChange = (type) => {
    setAddressType(type);
  };

  const handleSameAsPermanentChange = () => {
    setSameAsPermanent(!sameAsPermanent);
    if (!sameAsPermanent) {
      props.setPermanentAddress({ ...props.temporaryAddress });
    } else {
      props.setPermanentAddress(props.permanentAddressInitialValues);
    }
  };

  const handleInputChange = (event, addressType) => {
    const { name, value } = event.target;
    if (addressType === "temporary") {
      props.setTemporaryAddress({ ...props.temporaryAddress, [name]: value });
    } else {
      props.setPermanentAddress({ ...props.permanentAddress, [name]: value });
    }
  };

  return (
    <div className="container mt-5">
      {/* <h2>Address Information</h2> */}
      <br /> <br />
      <ul className="nav nav-tabs">
        <li className="nav-item">
          <button
            type="button"
            className={`nav-link ${
              addressType === "temporary" ? "active" : ""
            }`}
            onClick={() => handleAddressTypeChange("temporary")}
          >
            Temporary Address
          </button>
        </li>
        <li className="nav-item">
          <button
            type="button"
            className={`nav-link ${
              addressType === "permanent" ? "active" : ""
            }`}
            onClick={() => handleAddressTypeChange("permanent")}
          >
            Permanent Address
          </button>
        </li>
      </ul>
      {addressType === "temporary" ? (
        <div>
          <br />
          <div className="form-group">
            <label htmlFor="tempLine1">Line 1 :</label>
            <input
              type="text"
              className="form-control"
              id="tempLine1"
              name="line1"
              value={props.temporaryAddress.line1}
              onChange={(e) => handleInputChange(e, "temporary")}
            />
          </div>
          <div className="form-group">
            <label htmlFor="tempLine2">Line 2 :</label>
            <input
              type="text"
              className="form-control"
              id="tempLine2"
              name="line2"
              value={props.temporaryAddress.line2}
              onChange={(e) => handleInputChange(e, "temporary")}
            />
          </div>
          <div className="form-group">
            <label htmlFor="tempLandmark">Landmark :</label>
            <input
              type="text"
              className="form-control"
              id="tempLandmark"
              name="landmark"
              value={props.temporaryAddress.landmark}
              onChange={(e) => handleInputChange(e, "temporary")}
            />
          </div>
          <div className="form-group">
            <label htmlFor="tempState">State :</label>
            <input
              type="text"
              className="form-control"
              id="tempState"
              name="state"
              value={props.temporaryAddress.state}
              onChange={(e) => handleInputChange(e, "temporary")}
            />
          </div>
          <div className="form-group">
            <label htmlFor="tempCity">City :</label>
            <input
              type="text"
              className="form-control"
              id="tempCity"
              name="city"
              value={props.temporaryAddress.city}
              onChange={(e) => handleInputChange(e, "temporary")}
            />
          </div>
          <FormInput
            label="Pincode"
            name="pincode"
            value={props.temporaryAddress.pincode}
            onChange={(value) =>
              props.setTemporaryAddress({
                ...props.temporaryAddress,
                pincode: value,
              })
            }
            validate={(value) => {
              return /^\d+$/.test(value) && value.length <= 6;
            }}
            message="Pincode must be 6 digits"
            blur={(value) => {
              return value.length === 6;
            }}
          />
        </div>
      ) : (
        <div>
          <br />
          <div className="form-group form-check">
            <input
              type="checkbox"
              className="form-check-input"
              id="sameAsPermanent"
              checked={sameAsPermanent}
              onChange={handleSameAsPermanentChange}
            />
            <label className="form-check-label" htmlFor="sameAsPermanent">
              Same as Temporary Address
            </label>
          </div>
          <div className="form-group">
            <label htmlFor="permLine1">Line 1 :</label>
            <input
              type="text"
              className="form-control"
              id="permLine1"
              name="line1"
              value={props.permanentAddress.line1}
              onChange={(e) => handleInputChange(e, "permanent")}
            />
          </div>
          <div className="form-group">
            <label htmlFor="permLine2">Line 2 :</label>
            <input
              type="text"
              className="form-control"
              id="permLine2"
              name="line2"
              value={props.permanentAddress.line2}
              onChange={(e) => handleInputChange(e, "permanent")}
            />
          </div>
          <div className="form-group">
            <label htmlFor="permLandmark">Landmark :</label>
            <input
              type="text"
              className="form-control"
              id="permLandmark"
              name="landmark"
              value={props.permanentAddress.landmark}
              onChange={(e) => handleInputChange(e, "permanent")}
            />
          </div>
          <div className="form-group">
            <label htmlFor="permState">State :</label>
            <input
              type="text"
              className="form-control"
              id="permState"
              name="state"
              value={props.permanentAddress.state}
              onChange={(e) => handleInputChange(e, "permanent")}
            />
          </div>
          <div className="form-group">
            <label htmlFor="permCity">City :</label>
            <input
              type="text"
              className="form-control"
              id="permCity"
              name="city"
              value={props.permanentAddress.city}
              onChange={(e) => handleInputChange(e, "permanent")}
            />
          </div>
          <FormInput
            label="Pincode"
            name="pincode"
            value={props.permanentAddress.pincode}
            onChange={(value) =>
              props.setPermanentAddress({
                ...props.permanentAddress,
                pincode: value,
              })
            }
            validate={(value) => {
              return /^\d+$/.test(value) && value.length <= 6;
            }}
            message="Pincode must be 6 digits"
            blur={(value) => {
              return value.length === 6;
            }}
          />
        </div>
      )}
    </div>
  );
}

export default AddressForm;
