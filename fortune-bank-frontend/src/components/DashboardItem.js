import AddPayee from "./AddPayee";
import AddTransaction from "./AddTransaction";
import UserTransactions from "./UserTransactions";
import Beneficiaries from "./Beneficiaries";
import React from "react";
import AccountDetails from "./AccountDetails";
import TransactionSuccessful from "./TransactionSuccessful";
import AmountForm from "./AmountForm";

function DashboardItem(props) {
  if (props.urlParam === "addbeneficiary") {
    return <AddPayee />;
  } else if (props.urlParam === "transfer") {
    return <AddTransaction />;
  } else if (props.urlParam === "transactions") {
    return <UserTransactions />;
  } else if (props.urlParam === "beneficiaries") {
    return <Beneficiaries />;
  } else if (props.urlParam === "overview") {
    return <AccountDetails />;
  } else if (props.urlParam === "transactionsuccess") {
    return <TransactionSuccessful />;
  } else if (props.urlParam === "deposit") {
    return <AmountForm type="deposit" />;
  } else if (props.urlParam === "withdraw") {
    return <AmountForm type="withdraw" />;
  } else {
    return (
      <h1 className="mt-3">
        Welcome to Online Banking Dashboard {props.urlParam}
      </h1>
    );
  }
}

export default DashboardItem;
