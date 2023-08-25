import React, { useState, useEffect } from "react";
import { useTable, useSortBy } from "react-table";
import TransactionService from "../service/TransactionService";

function UserTransactions() {
  const [transactions, setTransactions] = useState([]);
  const [message, setMessage] = useState("");

  useEffect(() => {
    fetchTransactions();
  }, []);

  const fetchTransactions = () => {
    TransactionService.getTransactions().then((response) => {
      setTransactions(response.data);
    });
  };

  const data = React.useMemo(() => transactions, []);

  const columns = React.useMemo(
    () => [
      {
        Header: "Transaction Id",
        accessor: "tid",
      },
      {
        Header: "Transaction Type",
        accessor: "transactionType",
      },
      {
        Header: "From Account",
        accessor: "fromAccountNumber",
      },
      {
        Header: "To Account",
        accessor: "toAccountNumber",
      },
      {
        Header: "Transaction Amount",
        accessor: "amount",
      },
      {
        Header: "Transaction Date",
        accessor: "date",
      },
      {
        Header: "Remarks",
        accessor: "remark",
      },
      {
        Header: "Maturity Instructions",
        accessor: "maturityInstructions",
      },
    ],
    []
  );

  const { getTableProps, getTableBodyProps, headerGroups, rows, prepareRow } =
    useTable({ columns, data }, useSortBy);

  return (
    <div>
      <h1 className="text-warning">Transactions List</h1>
      <br />
      {/* <div className = "row justify-content-center">
              <button className="btn btn-info w-auto" onClick={addProduct}>Add Product</button>
          </div> */}
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

export default UserTransactions;
