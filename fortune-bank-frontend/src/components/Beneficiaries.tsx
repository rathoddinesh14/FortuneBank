import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import BeneficiaryService from "../service/BeneficiaryService";
import { ParseText } from "../utils/TextHandler";
import AuthenticationService from "../service/AuthenticationService";
import DisplayTable from "./DisplayTable";

function Beneficiaries(props) {
  const history = useNavigate();

  const [beneficiaries, setBeneficiaries] = useState([]);
  const [message, setMessage] = useState("");

  useEffect(() => {
    fetchBeneficiaries();
  }, []);

  const fetchBeneficiaries = () => {
    BeneficiaryService.getBeneficiaries().then((response) => {
      setBeneficiaries(response.data);
    });
  };

  const deleteBeneficiary = (id) => {
    alert("Are you sure you want to delete this Beneficiary ?");
    BeneficiaryService.deleteBeneficiary(id).then((reponse) => {
      if (reponse.data) {
        fetchBeneficiaries(); // Refresh products list
        setMessage("Beneficiary deleted successfully.");
        // Clear the message after 3 seconds
        setTimeout(() => {
          setMessage("");
        }, 2000);
      } else {
        setMessage("Beneficiary not deleted.");
        // Clear the message after 3 seconds
        setTimeout(() => {
          setMessage("");
        }, 2000);
      }
    });
  };

  const columns = React.useMemo(
    () =>
      [
        AuthenticationService.isAdminMode() && {
          Header: "Account Number",
          accessor: "accountNumber",
          Cell: ({ value }) => ParseText(value),
        },
        {
          Header: "Payee Account Number",
          accessor: "payeeAccountNumber",
          Cell: ({ value }) => ParseText(value),
        },
        {
          Header: "Name",
          accessor: "name",
          Cell: ({ value }) => ParseText(value),
        },
        {
          Header: "Nick Name",
          accessor: "nickName",
          Cell: ({ value }) => ParseText(value),
        },
        {
          Header: "Actions",
          accessor: "bid",
          Cell: ({ value }) => (
            <button
              className="btn btn-danger"
              onClick={() => deleteBeneficiary(value)}
            >
              Delete
            </button>
          ),
        },
      ].filter(Boolean),
    []
  );

  return (
    <DisplayTable
      data={beneficiaries}
      columns={columns}
      table="Beneficiaries"
    />
  );
}

export default Beneficiaries;
