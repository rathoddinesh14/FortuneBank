import Dashboard from "./Dashboard";
// import AddPayee from "./AddPayee";
// import AddTransaction from "./AddTransaction";

function UserHome() {
  return (
    <div className="container">
      <h1>Welcome user</h1>
      <Dashboard />
      {/* <AddPayee /> */}
      {/* <AddTransaction /> */}
    </div>
  );
}

export default UserHome;
