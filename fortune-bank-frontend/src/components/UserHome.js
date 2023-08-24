import AuthenticationService from "../service/AuthenticationService";
import Dashboard from "./Dashboard";

function UserHome(props) {
  return (
    <div className="container">
      {!AuthenticationService.isAdminMode() && (
        <h1 className="">Welcome {AuthenticationService.getAccountName()}, </h1>
      )}
      {AuthenticationService.isAdminMode() && (
        <h1 className="">Welcome Admin, </h1>
      )}
      <Dashboard urlParam={props.urlParam} />
    </div>
  );
}

export default UserHome;
