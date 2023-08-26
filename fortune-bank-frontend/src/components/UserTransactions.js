import React, { useState, useEffect } from "react";
import TransactionService from "../service/TransactionService";
import TransactionTable from "./TransactionTable";

function UserTransactions() {
  const [transactions, setTransactions] = useState([]);

  useEffect(() => {
    fetchTransactions();
  }, []);

  const fetchTransactions = () => {
    TransactionService.getTransactions().then((response) => {
      setTransactions(response.data);
    });
  };

  return <TransactionTable data={transactions} />;
}

export default UserTransactions;
