import React, { useState } from "react";
import UserService from "../service/UserService";
import { useTable, useSortBy } from "react-table";

function CustomerSearch() {
  const [searchParam, setSearchParam] = useState("accountnumber");
  const [searchValue, setSearchValue] = useState("");
  const [searchResults, setSearchResults] = useState([]);
  const [message, setMessage] = useState("");

  const handleSearchParamChange = (event) => {
    setSearchParam(event.target.value);
  };

  const handleSearchValueChange = (event) => {
    setSearchValue(event.target.value);
  };

  const handleSearch = () => {
    UserService.searchUsers(searchParam, searchValue)
      .then((response) => {
        setSearchResults(response.data);
      })
      .catch((error) => {
        console.error("Error searching users:", error);
      });
  };

  const data = React.useMemo(() => searchResults, [searchResults]);

  const columns = React.useMemo(
    () => [
      {
        Header: "Account Number",
        accessor: "accountNumber",
      },
      {
        Header: "First Name",
        accessor: "firstName",
      },
      {
        Header: "Middle Name",
        accessor: "middleName",
      },
      {
        Header: "Last Name",
        accessor: "lastName",
      },
      {
        Header: "Father's Name",
        accessor: "fatherName",
      },
      {
        Header: "Phone",
        accessor: "phone",
      },
      {
        Header: "Email",
        accessor: "email",
      },
      {
        Header: "Aadhar Number",
        accessor: "aadharNumber",
      },
      {
        Header: "Date of Birth",
        accessor: "dob",
      },
      // {
      //   Header: "Account Type",
      //   accessor: "accountType",
      // },
      {
        Header: "Balance",
        accessor: "balance",
      },
      {
        Header: "Account Status",
        accessor: "accountStatus",
      },
    ],
    []
  );

  const { getTableProps, getTableBodyProps, headerGroups, rows, prepareRow } =
    useTable({ columns, data }, useSortBy);

  return (
    <div className="container">
      <h1 className="mt-4">User Search</h1>
      <div className="row mt-3">
        <div className="col-md-4">
          <select
            className="form-select"
            value={searchParam}
            onChange={handleSearchParamChange}
          >
            <option value="accountnumber">Account Number</option>
            <option value="firstname">First Name</option>
            <option value="middlename">Middle Name</option>
            <option value="lastname">Last Name</option>
            <option value="fathername">Father's Name</option>
            <option value="phone">Phone</option>
            <option value="email">Email</option>
            <option value="aadharnumber">Aadhar Number</option>
            <option value="dob">Date of Birth</option>
            {/* <option value="accounttype">Account Type</option> */}
            <option value="accountstatus">Account Status</option>
          </select>
        </div>
        <div className="col-md-4">
          {searchParam === "accountstatus" ? (
            <select
              className="form-select"
              value={searchValue}
              onChange={handleSearchValueChange}
            >
              <option value="" disabled>
                Select Account Status
              </option>
              <option value="ENABLED">ENABLED</option>
              <option value="DISABLED">DISABLED</option>
            </select>
          ) : (
            <input
              className="form-control"
              {...(searchParam === "dob" && { type: "date" })}
              {...(searchParam !== "dob" && { type: "text" })}
              value={searchValue}
              onChange={handleSearchValueChange}
              placeholder={`Search by ${searchParam}`}
            />
          )}
        </div>
        <div className="col-md-4">
          <button className="btn btn-primary" onClick={handleSearch}>
            Search
          </button>
        </div>

        <div className="row justify-content-center mt-3">
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
    </div>
  );
}

export default CustomerSearch;
