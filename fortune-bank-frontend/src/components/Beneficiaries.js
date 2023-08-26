import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import BeneficiaryService from "../service/BeneficiaryService";
import { ParseText } from "../utils/TextHandler";
import { useTable, useSortBy } from "react-table";
import AuthenticationService from "../service/AuthenticationService";

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

  const data = React.useMemo(() => beneficiaries, [beneficiaries]);

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

  const { getTableProps, getTableBodyProps, headerGroups, rows, prepareRow } =
    useTable({ columns, data }, useSortBy);

  // return (
  //   <div>
  //     <h1 className="text-warning text-center">Beneficiaries List</h1>
  //     <br />
  //     <div className="row justify-content-center">
  //       <table className="table table-success w-auto">
  //         <thead>
  //           <tr className="table-danger">
  //             <th> Beneficiary Account Number</th>
  //             <th> Beneficiary Name</th>
  //             {/* <th> Beneficiary Account Number</th> */}
  //             <th> Beneficiary NickName</th>
  //             <th> Actions</th>
  //           </tr>
  //         </thead>
  //         <tbody>
  //           {beneficiaries.map((beneficiary) => (
  //             <tr key={beneficiary.bid} className="text-center">
  //               <td> {ParseText(beneficiary.accountNumber)} </td>
  //               <td> {ParseText(beneficiary.name)} </td>
  //               {/* <td> {beneficiary.} </td> */}
  //               <td> {ParseText(beneficiary.nickName)} </td>
  //               <td>
  //                 {/* <button
  //                   className="btn btn-success"
  //                   onClick={() => editProduct(prod.pid)}
  //                 >
  //                   Update
  //                 </button> */}
  //                 {/* &nbsp; */}
  //                 <button
  //                   className="btn btn-danger"
  //                   onClick={() => deleteBeneficiary(beneficiary.bid)}
  //                 >
  //                   Delete{" "}
  //                 </button>
  //                 {/* &nbsp;
  //                 <button
  //                   className="btn btn-secondary"
  //                   onClick={() => viewProduct(prod.pid)}
  //                 >
  //                   View{" "}
  //                 </button> */}
  //               </td>
  //             </tr>
  //           ))}
  //         </tbody>
  //       </table>
  //     </div>
  //     {message && <div className="alert alert-success">{message}</div>}
  //   </div>
  // );

  return (
    <div>
      <h1 className="text-warning text-center">Beneficiary List</h1>
      <br />
      <div className="row justify-content-center">
        <table className="table table-success w-auto" {...getTableProps()}>
          <thead>
            {headerGroups.map((headerGroup) => (
              <tr
                {...headerGroup.getHeaderGroupProps()}
                className="table-danger"
              >
                {headerGroup.headers.map((column) => (
                  <th
                    {...column.getHeaderProps(column.getSortByToggleProps())}
                    style={{
                      borderBottom: "solid 3px red",
                      color: "black",
                    }}
                  >
                    {column.render("Header")}
                    <span>
                      {column.isSorted
                        ? column.isSortedDesc
                          ? "🔽"
                          : "🔼"
                        : ""}
                    </span>
                  </th>
                ))}
              </tr>
            ))}
          </thead>
          <tbody {...getTableBodyProps()}>
            {rows.map((row) => {
              prepareRow(row);
              return (
                <tr {...row.getRowProps()} className="text-center">
                  {row.cells.map((cell) => {
                    return (
                      <td
                        {...cell.getCellProps()}
                        style={{
                          padding: "10px",
                          border: "solid 1px gray",
                        }}
                      >
                        {cell.render("Cell")}
                      </td>
                    );
                  })}
                </tr>
              );
            })}
          </tbody>
        </table>
      </div>
      {message && <div className="alert alert-success">{message}</div>}
    </div>
  );
}

export default Beneficiaries;
