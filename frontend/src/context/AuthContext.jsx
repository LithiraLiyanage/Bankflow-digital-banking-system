import { createContext, useContext, useState } from "react";
import api from "../services/api";
const AuthContext = createContext();
export const AuthProvider = ({children}) => {
  const [user,setUser] = useState(JSON.parse(localStorage.getItem("bankflow_user") || "null"));
  const login = async (email,password) => {
    const {data} = await api.post("/auth/login",{email,password});
    localStorage.setItem("bankflow_user", JSON.stringify(data.data)); setUser(data.data);
    return data.data.role === "ADMIN" ? "/admin" : "/customer";
  };
  const register = async (payload) => {
    const {data} = await api.post("/auth/register",payload);
    localStorage.setItem("bankflow_user", JSON.stringify(data.data)); setUser(data.data);
    return data.data.role === "ADMIN" ? "/admin" : "/customer";
  };
  const logout = () => { localStorage.removeItem("bankflow_user"); setUser(null); };
  return <AuthContext.Provider value={{user,login,register,logout}}>{children}</AuthContext.Provider>;
};
export const useAuth = () => useContext(AuthContext);
