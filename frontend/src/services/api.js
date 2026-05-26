import axios from "axios";
const api = axios.create({ baseURL: import.meta.env.VITE_API_URL || "http://localhost:8080/api" });
api.interceptors.request.use(c => {
  const u = JSON.parse(localStorage.getItem("bankflow_user") || "null");
  if (u?.token) c.headers.Authorization = `Bearer ${u.token}`;
  return c;
});
export default api;
