import React from "react";
import "./../styles/AppHome.css"; // Import CSS file

function AppHome() {
  return (
    <div className="container">
      <a href="/login">Login</a>
      <a href="/register">Register</a>
      <a href="/apply">Apply online</a>
      <a href="/resetpassword">Forgot / Reset Password</a>
      <a href="/forgotuserid">Forgot User ID</a>
    </div>
  );
}

export default AppHome;
