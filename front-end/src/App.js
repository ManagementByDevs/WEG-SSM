import './App.css';
import React, { useContext } from 'react';
import {
  BrowserRouter as Router,
  Routes,
  Route
} from "react-router-dom";

import Home from './pages/Home/Home';
import Login from './pages/Login/Login';
import CriarDemanda from './pages/CriarDemanda/CriarDemanda';
import Demanda from './components/Demanda/Demanda';

import ToggleColorMode from './service/TemaProvedor';

const App = () => {
  return (
    <ToggleColorMode>
      <Router>
        <Routes>
          <Route path="/login" element={<Login></Login>} />
          <Route path="/" element={<Home></Home>} />
          <Route path="/criar-demanda" element={<CriarDemanda />} />
        </Routes>
      </Router>
    </ToggleColorMode>
  );
}

export default App;