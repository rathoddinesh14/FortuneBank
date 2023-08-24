import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import TransactionService from "../service/TransactionService";
import AuthenticationService from "../service/AuthenticationService";

function UserTransactions({ userId }) {
  const history = useNavigate();

  const [transactions, setTransactions] = useState([]);
  const [message, setMessage] = useState("");

  useEffect(() => {
    fetchTransactions();
  }, []);

  const fetchTransactions = () => {
    TransactionService.getTransactions(
      AuthenticationService.getLoggedInAccountNumber()
    ).then((response) => {
      setTransactions(response.data);
    });
    console.log(transactions);
  };

  return (
    <div>
      <h1 className="text-warning">Transactions List</h1>
      <br />
      {/* <div className = "row justify-content-center">
              <button className="btn btn-info w-auto" onClick={addProduct}>Add Product</button>
          </div> */}
      <br />
      <div className="row justify-content-center">
        <table className="table table-success w-auto">
          <thead>
            <tr className="table-danger">
              <th> Transaction Id</th>
              <th> Transaction Type</th>
              <th> From Account</th>
              <th> To Account</th>
              <th> Transaction Amount</th>
              <th> Transaction Date</th>
              <th> Remarks</th>
              <th> Maturity Instructions</th>
            </tr>
          </thead>
          <tbody>
            {transactions.map((transaction) => (
              <tr key={transaction.tid} className="text-center">
                <td> {transaction.tid} </td>
                <td> {transaction.transactionType}</td>
                <td> {transaction.fromAccountNumber} </td>
                <td> {transaction.toAccountNumber} </td>
                <td> {transaction.amount} </td>
                <td> {transaction.date} </td>
                <td> {transaction.remark} </td>
                <td> {transaction.maturityInstructions}</td>
                {/* <td>
                              <button className="btn btn-success" onClick={() => editProduct(prod.pid)}>
                                      
                                 Update</button>
                                 &nbsp;
                                  <button className="btn btn-danger" onClick={() => deleteProduct(prod.pid)}>
                                     
                                Delete </button>
                                &nbsp;
                                 <button className="btn btn-secondary" onClick={() => viewProduct(prod.pid)}>
                                      
                                View </button>
                              </td>  */}
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      {message && <div className="alert alert-success">{message}</div>}
    </div>
  );
}

export default UserTransactions;
