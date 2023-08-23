import Dashboard from "./Dashboard";

function UserHome(props) {
  return (
    <div className="container">
      <h1>Welcome user</h1>
      <Dashboard urlParam={props.urlParam} />
    </div>
  );
}

export default UserHome;
