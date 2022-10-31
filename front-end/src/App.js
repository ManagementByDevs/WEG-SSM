import './App.css';
import React from 'react';
import {
  BrowserRouter as Router,
  Routes,
  Route
} from "react-router-dom";
import Home from './pages/Home/home';
import Login from './pages/Login/login';

import Icon from '@mui/material/Icon';
import { ThemeProvider, useTheme, createTheme } from '@mui/material/styles';
import { amber, deepOrange, grey } from '@mui/material/colors';

// const theme = createTheme({
//   palette: {
//     primary: {
//       main: '#00579D'
//     },
//   },
// });

const getDesignTokens = (mode) => ({
  palette: {
    mode,
    primary: {
      ...amber,
      ...(mode === 'dark' && {
        main: amber[300],
      }),
    },
    ...(mode === 'dark' && {
      background: {
        default: deepOrange[900],
        paper: deepOrange[900],
      },
    }),
    text: {
      ...(mode === 'light'
        ? {
            primary: grey[900],
            secondary: grey[800],
          }
        : {
            primary: '#fff',
            secondary: grey[500],
          }),
    },
  },
});

function App() {
  const theme = useTheme();

  return (
    <Router>
      <Routes>
          <Route path="/login" element={<Login></Login>} />
          <Route path="/" element={<Home></Home>} />
      </Routes>
    </Router>
    // <div>
    //   Hello world!
    //   <Icon>home_outline</Icon>;
    //   <h1 className="text-3xl font-bold underline">
    //     Hello world!
    //   </h1>
    // </div>
  );
}

const darkModeTheme = createTheme(getDesignTokens('dark'));

export default function DarkThemeWithCustomPalette() {
  return (
    <ThemeProvider theme={darkModeTheme}>
      <App />
    </ThemeProvider>
  );
}
