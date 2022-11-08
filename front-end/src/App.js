import './App.css';
import React, { useContext } from 'react';
import {
  BrowserRouter as Router,
  Routes,
  Route
} from "react-router-dom";

import Home from './pages/Home/Home';
import Login from './pages/Login/Login';
import { useTheme } from '@emotion/react';

import ToggleColorMode from './service/TemaProvedor';
import ColorModeContext from './service/TemaContext';

const App = () => {
  // const theme = useTheme();
  const colorMode = useContext(ColorModeContext);

  return (
    <ToggleColorMode>
      <Router>
        <Routes>
          <Route path="/login" element={<Login></Login>} />
          <Route path="/" element={<Home togglePalette={colorMode.toggleColorMode}></Home>} />
        </Routes>
      </Router>
    </ToggleColorMode>
  );
}

export default App;