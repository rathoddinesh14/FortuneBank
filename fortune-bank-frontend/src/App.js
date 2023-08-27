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
import FormTemplate from "./components/FormTemplate";
import Logout from "./components/Logout";
import AccountConfirmation from "./components/ApplyConfirmation";

function App() {
  return (
    <div className="d-flex flex-column min-vh-100">
      <Header />
      <section className="container-fluid flex-grow-1">
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<AppHome />} />
            <Route
              path="/login"
              element={<FormTemplate children={<UserLogin />} />}
            />
            <Route
              path="/register"
              element={<FormTemplate children={<NetBankingRegistration />} />}
            />
            <Route path="/apply" element={<ApplyOnline />} />
            <Route path="/userhome" element={<UserHome />} />
            <Route
              path="/forgotuserid"
              element={<FormTemplate children={<ForgotUserId />} />}
            />
            <Route
              path="/resetpassword"
              element={<FormTemplate children={<ForgotPassword />} />}
            />
            <Route
              path="/overview"
              element={<UserHome urlParam="overview" />}
            />
            <Route
              path="/payments"
              element={<UserHome urlParam="payments" />}
            />
            <Route
              path="/statements"
              element={<UserHome urlParam="statements" />}
            />
            <Route
              path="/beneficiaries"
              element={<UserHome urlParam="beneficiaries" />}
            />
            <Route
              path="/addbeneficiary"
              element={<UserHome urlParam="addbeneficiary" />}
            />
            <Route
              path="/transactions"
              element={<UserHome urlParam="transactions" />}
            />
            <Route
              path="/accountsummary"
              element={<UserHome urlParam="accountsummary" />}
            />
            <Route
              path="/transfer"
              element={<UserHome urlParam="transfer" />}
            />
            <Route
              path="/overview"
              element={<UserHome urlParam="overview" />}
            />
            <Route
              path="/transaction/:transactionStatus"
              element={<UserHome urlParam="transaction" />}
            />
            <Route path="/deposit" element={<UserHome urlParam="deposit" />} />
            <Route
              path="/withdraw"
              element={<UserHome urlParam="withdraw" />}
            />
            <Route
              path="/customers"
              element={<UserHome urlParam="customers" />}
            />
            <Route
              path="/customersearch"
              element={<UserHome urlParam="customersearch" />}
            />
            <Route path="/logout" element={<Logout />} />
            <Route
              path="/accountconfirm/:status"
              element={<AccountConfirmation />}
            />
          </Routes>
        </BrowserRouter>
      </section>
      <Footer />
    </div>
  );
}

export default App;
