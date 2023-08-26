import React from "react";

function Header() {
  return (
    <header className="bg-dark text-white rounded-pill">
      <div className="container-fluid py-2 text-center">
        <a href="/" style={{ textDecoration: "none" }}>
          <h1>Fortune Bank</h1>
        </a>
      </div>
    </header>
  );
}

export default Header;
