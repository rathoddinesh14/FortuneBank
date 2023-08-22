import React from "react";

function Footer() {
  return (
    <footer className="text-black text-center">
      <div className="container">
        &copy; {new Date().getFullYear()} Fortune Bank. All rights reserved.
      </div>
    </footer>
  );
}

export default Footer;
