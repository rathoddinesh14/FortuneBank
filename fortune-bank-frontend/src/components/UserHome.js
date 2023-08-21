import AddPayee from "./AddPayee";
import AddTransaction from "./AddTransaction";
function UserHome() {
  return (
    <div>
      <h1>User Home</h1>
      <AddPayee></AddPayee>
      <AddTransaction></AddTransaction>
    </div>
  );
}

export default UserHome;
