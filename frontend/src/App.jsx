import { Routes, Route, Navigate, Link, Outlet } from "react-router-dom";
import { useAuth } from "./context/AuthContext";
import Home from "./pages/Home";
import Login from "./pages/Login";
import Register from "./pages/Register";
import CustomerDashboard from "./pages/customer/CustomerDashboard";
import Accounts from "./pages/customer/Accounts";
import Transactions from "./pages/customer/Transactions";
import Transfer from "./pages/customer/Transfer";
import AdminDashboard from "./pages/admin/AdminDashboard";

const Nav = () => {
  const {user, logout}=useAuth();
  return <nav className="border-b bg-white px-5 py-4 flex justify-between"><Link className="font-black text-xl text-primary" to="/">🏦 BankFlow</Link><div className="flex gap-3">{user?<><Link to={user.role==="ADMIN"?"/admin":"/customer"}>Dashboard</Link><button onClick={logout}>Logout</button></>:<><Link to="/login">Login</Link><Link to="/register">Register</Link></>}</div></nav>
};
const Protected=({role})=>{const{user}=useAuth(); if(!user)return <Navigate to="/login"/>; if(role&&user.role!==role)return <Navigate to="/"/>; return <Outlet/>};

function App(){
  return <><Nav/><Routes>
    <Route path="/" element={<Home/>}/>
    <Route path="/login" element={<Login/>}/>
    <Route path="/register" element={<Register/>}/>
    <Route element={<Protected role="CUSTOMER"/>}>
      <Route path="/customer" element={<CustomerDashboard/>}/>
      <Route path="/customer/accounts" element={<Accounts/>}/>
      <Route path="/customer/transactions" element={<Transactions/>}/>
      <Route path="/customer/transfer" element={<Transfer/>}/>
    </Route>
    <Route element={<Protected role="ADMIN"/>}>
      <Route path="/admin" element={<AdminDashboard/>}/>
    </Route>
  </Routes></>
}
export default App;
