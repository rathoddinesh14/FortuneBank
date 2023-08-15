import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import UserLogin from "./components/UserLogin";
import NetBankingRegistration from "./components/NetBankingRegistration";
import ApplyOnline from "./components/ApplyOnline";

const Home = () => (
  <div className="App">
    <header className="App-header">
      <a href="/login">Login</a>
      <a href="/register">Register</a>
      <a href="/apply">Apply online</a>
    </header>
  </div>
);

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<UserLogin />} />
        <Route path="/register" element={<NetBankingRegistration />} />
        <Route path="/apply" element={<ApplyOnline />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
