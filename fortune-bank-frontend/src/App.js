import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import UserLogin from "./components/UserLogin";
import NetBankingRegistration from "./components/NetBankingRegistration";
import ApplyOnline from "./components/ApplyOnline";
import UserHome from "./components/UserHome";
import ForgotUserId from "./components/ForgotUserId";
import ForgotPassword from "./components/ForgotPassword";
import AppHome from "./components/AppHome";
import Footer from "./components/Footer";
import Header from "./components/Header";

function App() {
  return (
    <div className="d-flex flex-column min-vh-100">
      <Header />
      <section className="container-fluid flex-grow-1">
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<AppHome />} />
            <Route path="/login" element={<UserLogin />} />
            <Route path="/register" element={<NetBankingRegistration />} />
            <Route path="/apply" element={<ApplyOnline />} />
            <Route path="/userhome" element={<UserHome />} />
            <Route path="/forgotuserid" element={<ForgotUserId />} />
            <Route path="/resetpassword" element={<ForgotPassword />} />
          </Routes>
        </BrowserRouter>
      </section>
      <Footer />
    </div>
  );
}

export default App;
