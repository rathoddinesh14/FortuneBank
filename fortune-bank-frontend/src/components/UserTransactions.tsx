import React, { useState, useEffect } from "react";
import TransactionService from "../service/TransactionService";
import DisplayTable from "./DisplayTable";
import TransactionColumns from "./TransactionColumns";

interface Transaction {
  // Add transaction properties here (e.g. id, date, amount, etc.)
}

function UserTransactions() {
  const [transactions, setTransactions] = useState<Transaction[]>([]);

  useEffect(() => {
    fetchTransactions();
  }, []);

  const fetchTransactions = () => {
    TransactionService.getTransactions().then((response: any) => {
      setTransactions(response.data);
    });
  };

  return (
    <DisplayTable
      data={transactions}
      columns={TransactionColumns}
      table="Transactions"
    />
  );
}

export default UserTransactions;