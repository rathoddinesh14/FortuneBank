import React from "react";

function Header() {
  return (
    <header className="py-4">
      <div className="container">
        <a href="/" style={{ textDecoration: "none" }}>
          <h1 style={{ fontWeight: "bold" }}>Fortune Bank</h1>
        </a>
        <p className="lead" style={{ fontWeight: "bold" }}>
          Where Money Matters
        </p>
      </div>
    </header>
  );
}

export default Header;
