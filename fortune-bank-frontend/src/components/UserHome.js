import Dashboard from "./Dashboard";
// import { useParams } from "react-router-dom";
// import AddPayee from "./AddPayee";
// import AddTransaction from "./AddTransaction";

function UserHome(props) {
  // url
  // const { id } = useParams();

  return (
    <div className="container">
      <h1>Welcome user</h1>
      <Dashboard urlParam={props.urlParam} />
      {/* <AddPayee /> */}
      {/* <AddTransaction /> */}
    </div>
  );
}

export default UserHome;
