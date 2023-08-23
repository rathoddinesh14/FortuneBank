import React, { Component } from "react";

class Logout extends Component {
  render() {
    return (
      <>
        <br />
        <h1 style={{ color: "wheat" }}>You are logged out</h1>

        <div>
          <img
            style={{
              borderRadius: "20px",
            }}
            src="/images/run.jpg"
            alt="pms"
            height={400}
            width={400}
          ></img>
        </div>
        <br />

        <div className="container">Thank You for Using Our Application.</div>
      </>
    );
  }
}

export default Logout;
