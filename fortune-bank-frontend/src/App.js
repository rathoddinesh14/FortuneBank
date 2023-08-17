import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import UserLogin from "./components/UserLogin";
import NetBankingRegistration from "./components/NetBankingRegistration";
import ApplyOnline from "./components/ApplyOnline";
import UserHome from "./components/UserHome";
import ForgotUserId from "./components/ForgotUserId";
import ForgotPassword from "./components/ForgotPassword";
import AppHome from "./components/AppHome";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<AppHome />} />
        <Route path="/login" element={<UserLogin />} />
        <Route path="/register" element={<NetBankingRegistration />} />
        <Route path="/apply" element={<ApplyOnline />} />
        <Route path="/userhome" element={<UserHome />} />
        {/* forgotuserid */}
        <Route path="/forgotuserid" element={<ForgotUserId />} />
        {/* forgotpassword */}
        <Route path="/resetpassword" element={<ForgotPassword />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
