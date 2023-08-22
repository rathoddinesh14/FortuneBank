import React, { useState } from "react";

function AddressForm() {
  const [addressType, setAddressType] = useState("temporary");
  const [sameAsPermanent, setSameAsPermanent] = useState(false);

  const handleAddressTypeChange = (type) => {
    setAddressType(type);
  };

  const handleSameAsPermanentChange = () => {
    setSameAsPermanent(!sameAsPermanent);
    if (!sameAsPermanent) {
      setPermanentAddress({ ...temporaryAddress });
    } else {
      setPermanentAddress(permanentAddressInitialValues);
    }
  };

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

  const handleInputChange = (event, addressType) => {
    const { name, value } = event.target;
    if (addressType === "temporary") {
      setTemporaryAddress({ ...temporaryAddress, [name]: value });
    } else {
      setPermanentAddress({ ...permanentAddress, [name]: value });
    }
  };

  return (
    <div className="container mt-5">
      <h2>Address Information</h2>
      <ul className="nav nav-tabs">
        <li className="nav-item">
          <button
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
            className={`nav-link ${
              addressType === "permanent" ? "active" : ""
            }`}
            onClick={() => handleAddressTypeChange("permanent")}
          >
            Permanent Address
          </button>
        </li>
      </ul>
      <form>
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
                value={temporaryAddress.line1}
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
                value={temporaryAddress.line2}
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
                value={temporaryAddress.landmark}
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
                value={temporaryAddress.state}
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
                value={temporaryAddress.city}
                onChange={(e) => handleInputChange(e, "temporary")}
              />
            </div>
            <div className="form-group">
              <label htmlFor="tempPincode">Pincode :</label>
              <input
                type="text"
                className="form-control"
                id="tempPincode"
                name="pincode"
                value={temporaryAddress.pincode}
                onChange={(e) => handleInputChange(e, "temporary")}
              />
            </div>
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
                value={permanentAddress.line1}
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
                value={permanentAddress.line2}
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
                value={permanentAddress.landmark}
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
                value={permanentAddress.state}
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
                value={permanentAddress.city}
                onChange={(e) => handleInputChange(e, "permanent")}
              />
            </div>
            <div className="form-group">
              <label htmlFor="permPincode">Pincode :</label>
              <input
                type="text"
                className="form-control"
                id="permPincode"
                name="pincode"
                value={permanentAddress.pincode}
                onChange={(e) => handleInputChange(e, "permanent")}
              />
            </div>
          </div>
        )}
      </form>
    </div>
  );
}

export default AddressForm;
