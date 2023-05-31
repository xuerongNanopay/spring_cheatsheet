import logo from './logo.svg';
import './App.css';
import Login from './component/Login.js'
import Logout from './component/Logout.js'
import {
  NavLink, 
  Link, 
  BrowserRouter, 
  Route, 
  Routes, 
  useParams, 
  Outlet, 
  useOutletContext, 
  useLocation, 
  Redirect, 
  useNavigate, 
  Navigate,
  HashRouter
} from 'react-router-dom'

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/login" element={<Login />} />
        <Route path="/Logout" element={<Logout />} />
        <Route path="*" element={<Navigate to="/" />} />
      </Routes>
    </BrowserRouter>
  );
}
  
export default App;