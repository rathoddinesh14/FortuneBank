import AddPayee from "./AddPayee";
import AddTransaction from "./AddTransaction";

function DashboardItem(props) {
  if (props.urlParam === "addbeneficiary") {
    return <AddPayee />;
  } else if (props.urlParam === "transfer") {
    return <AddTransaction />;
  } else {
    return (
      <h1 className="mt-3">
        Welcome to Online Banking Dashboard {props.urlParam}
      </h1>
    );
  }
}

export default DashboardItem;
