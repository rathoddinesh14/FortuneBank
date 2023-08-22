import AddPayee from "./AddPayee";
import AddTransaction from "./AddTransaction";
import UserTransactions from "./UserTransactions";
import Beneficiaries from "./Beneficiaries";

function DashboardItem(props) {
  if (props.urlParam === "addbeneficiary") {
    return <AddPayee />;
  } else if (props.urlParam === "transfer") {
    return <AddTransaction />;
  } else if (props.urlParam === "transactions") {
    return <UserTransactions />;
  } else if (props.urlParam === "beneficiaries") {
    return <Beneficiaries />;
  } else {
    return (
      <h1 className="mt-3">
        Welcome to Online Banking Dashboard {props.urlParam}
      </h1>
    );
  }
}

export default DashboardItem;
