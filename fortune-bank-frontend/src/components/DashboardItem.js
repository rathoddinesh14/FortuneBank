import AddPayee from "./AddPayee";
import AddTransaction from "./AddTransaction";
import UserTransactions from "./UserTransactions";
import Beneficiaries from "./Beneficiaries";
import React from "react";
import AccountDetails from "./AccountDetails";
import TransactionStatus from "./TransactionSuccessful";
import AmountForm from "./AmountForm";
import CustomerList from "./CustomerList";
import CustomerSearch from "./CustomerSearch";
import AccountSummary from "./AccountSummary";
import BankCarousel from "./BankCarousel";

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
  } else if (props.urlParam === "transaction") {
    return <TransactionStatus />;
  } else if (props.urlParam === "deposit") {
    return <AmountForm type="Deposit" />;
  } else if (props.urlParam === "withdraw") {
    return <AmountForm type="Withdraw" />;
  } else if (props.urlParam === "customers") {
    return <CustomerList />;
  } else if (props.urlParam === "customersearch") {
    return <CustomerSearch />;
  } else if (props.urlParam === "accountsummary") {
    return <AccountSummary />;
  } else {
    return (
      <h1 className="mt-3">
        {/* Welcome to Online Banking Dashboard {props.urlParam} */}
        <BankCarousel />
      </h1>
    );
  }
}

export default DashboardItem;
