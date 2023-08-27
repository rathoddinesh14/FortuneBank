import React from "react";

function Header() {
  return (
    <header className="py-4">
      <div className="container">
        <a href="/" style={{ textDecoration: "none" }}>
          <h1 className="display-3">Fortune Bank</h1>
        </a>
        <p className="lead">Where Money Matters</p>
      </div>
    </header>
  );
}

export default Header;
