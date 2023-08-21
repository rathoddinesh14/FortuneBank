import React, { useState } from "react";

function TransactionTable({ transactions }) {
  return (
    <div className="transaction-table">
      <table>
        <thead>
          <tr>
            <th>Transaction ID</th>
            <th>From Account</th>
            <th>To Account</th>
            <th>Amount</th>
            <th>Remarks</th>
          </tr>
        </thead>
        <tbody>
          {transactions.map((transaction) => (
            <tr key={transaction.tid}>
              <td>{transaction.tid}</td>
              <td>{transaction.fromaccount}</td>
              <td>{transaction.toaccount}</td>
              <td>{transaction.amount}</td>
              <td>{transaction.remarks}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

function UserTransactions({ userId }) {
  const [transactions, setTransactions] = useState([]);

  // Fetch transactions for the given userId (simulated data for example)
  // Replace this with actual API call to fetch transactions
  // For example: fetch(`/api/transactions/${userId}`)
  // Update the state with the fetched transactions
  // setTransactions([...fetchedTransactions]);

  // Simulated transactions data for example
  const simulatedTransactions = [
    {
      tid: 1,
      fromaccount: 1234567890,
      toaccount: 9876543210,
      amount: 1000.0,
      remarks: "Payment for services",
    },
    {
      tid: 1,
      fromaccount: 1234567890,
      toaccount: 9876543210,
      amount: 1000.0,
      remarks: "Payment for services",
    },
    {
      tid: 1,
      fromaccount: 1234567890,
      toaccount: 9876543210,
      amount: 1000.0,
      remarks: "Payment for services",
    },
    {
      tid: 1,
      fromaccount: 1234567890,
      toaccount: 9876543210,
      amount: 1000.0,
      remarks: "Payment for services",
    },
    {
      tid: 1,
      fromaccount: 1234567890,
      toaccount: 9876543210,
      amount: 1000.0,
      remarks: "Payment for services",
    },
    {
      tid: 1,
      fromaccount: 1234567890,
      toaccount: 9876543210,
      amount: 1000.0,
      remarks: "Payment for services",
    },
    {
      tid: 1,
      fromaccount: 1234567890,
      toaccount: 9876543210,
      amount: 1000.0,
      remarks: "Payment for services",
    },
    {
      tid: 1,
      fromaccount: 1234567890,
      toaccount: 9876543210,
      amount: 1000.0,
      remarks: "Payment for services",
    },
    {
      tid: 1,
      fromaccount: 1234567890,
      toaccount: 9876543210,
      amount: 1000.0,
      remarks: "Payment for services",
    },
    {
      tid: 1,
      fromaccount: 1234567890,
      toaccount: 9876543210,
      amount: 1000.0,
      remarks: "Payment for services",
    },
    {
      tid: 1,
      fromaccount: 1234567890,
      toaccount: 9876543210,
      amount: 1000.0,
      remarks: "Payment for services",
    },
    {
      tid: 1,
      fromaccount: 1234567890,
      toaccount: 9876543210,
      amount: 1000.0,
      remarks: "Payment for services",
    },
    {
      tid: 1,
      fromaccount: 1234567890,
      toaccount: 9876543210,
      amount: 1000.0,
      remarks: "Payment for services",
    },
    {
      tid: 1,
      fromaccount: 1234567890,
      toaccount: 9876543210,
      amount: 1000.0,
      remarks: "Payment for services",
    },
    {
      tid: 1,
      fromaccount: 1234567890,
      toaccount: 9876543210,
      amount: 1000.0,
      remarks: "Payment for services",
    },
    {
      tid: 1,
      fromaccount: 1234567890,
      toaccount: 9876543210,
      amount: 1000.0,
      remarks: "Payment for services",
    },
    {
      tid: 1,
      fromaccount: 1234567890,
      toaccount: 9876543210,
      amount: 1000.0,
      remarks: "Payment for services",
    },
    {
      tid: 1,
      fromaccount: 1234567890,
      toaccount: 9876543210,
      amount: 1000.0,
      remarks: "Payment for services",
    },
    {
      tid: 1,
      fromaccount: 1234567890,
      toaccount: 9876543210,
      amount: 1000.0,
      remarks: "Payment for services",
    },
    {
      tid: 1,
      fromaccount: 1234567890,
      toaccount: 9876543210,
      amount: 1000.0,
      remarks: "Payment for services",
    },
    {
      tid: 1,
      fromaccount: 1234567890,
      toaccount: 9876543210,
      amount: 1000.0,
      remarks: "Payment for services",
    },
    {
      tid: 1,
      fromaccount: 1234567890,
      toaccount: 9876543210,
      amount: 1000.0,
      remarks: "Payment for services",
    },
    {
      tid: 1,
      fromaccount: 1234567890,
      toaccount: 9876543210,
      amount: 1000.0,
      remarks: "Payment for services",
    },
    {
      tid: 1,
      fromaccount: 1234567890,
      toaccount: 9876543210,
      amount: 1000.0,
      remarks: "Payment for services",
    },
    {
      tid: 1,
      fromaccount: 1234567890,
      toaccount: 9876543210,
      amount: 1000.0,
      remarks: "Payment for services",
    },
    {
      tid: 1,
      fromaccount: 1234567890,
      toaccount: 9876543210,
      amount: 1000.0,
      remarks: "Payment for services",
    },
    {
      tid: 1,
      fromaccount: 1234567890,
      toaccount: 9876543210,
      amount: 1000.0,
      remarks: "Payment for services",
    },

    // Add more transactions here
  ];

  return (
    <div className="app-container">
      <h1>Transactions</h1>
      <TransactionTable transactions={simulatedTransactions} />
    </div>
  );
}

export default UserTransactions;
