import './App.css';
import React from 'react';
import {
  BrowserRouter as Router,
  Routes,
  Route
} from "react-router-dom";
import { ThemeProvider, useTheme, createTheme } from '@mui/material/styles';

import Button from '@mui/material/Button';
import Icon from '@mui/material/Icon';

import Home from './pages/Home/home';
import Login from './pages/Login/login';

export const ColorModeContext = React.createContext({ toggleColorMode: () => { } });

const getDesignTokens = (mode) => ({
  palette: {
    mode,
    ...(mode == 'dark')
      ? {
        primary: {
          main: '#000000',
        },
        secondary: {
          main: '#00579D',
        },
        divider: '#00579D',
        text: {
          primary: '#000000',
          secondary: '#535353',
        }
      }
      : {
        primary: {
          main: '#00579D',
        },
        secondary: {
          main: '#00579D',
        },
        divider: '#00579D',
        text: {
          primary: '#000000',
          secondary: '#000000',
        }

        // ? {
        //   primary: '#00579D',
        //   main: '#00579D',
        //   divider: 'rgba(0, 0, 0, 0.3)',
        //   text: {
        //     primary: '#000000',
        //     secondary: '#535353',
        //   }
        // }
        // : {
        //   primary: '#00579D',
        //   main: '#00579D',
        //   divider: '#E5E5E5',
        //   text: {
        //     primary: '#000000',
        //     secondary: '#000000',
        //   }
      }
  },
});

function App() {
  const theme = useTheme();
  const colorMode = React.useContext(ColorModeContext);

  console.log('theme: ', theme);

  return (
    <Router>
      <Routes>
        <Route path="/login" element={<Login></Login>} />
        <Route path="/" element={<Home></Home>} />
      </Routes>
    </Router>
  );
}

export default function ProvedorDeTema() {
  const [mode, setMode] = React.useState('light');
  const colorMode = React.useMemo(
    () => ({
      toggleColorMode: () => {
        setMode((prevMode) =>
          prevMode === 'light' ? 'dark' : 'light',
        );
      },
    }),
    [],
  );

  const theme = React.useMemo(() => createTheme(getDesignTokens(mode)), [mode]);

  return (
    <ColorModeContext.Provider value={colorMode}>
      <ThemeProvider theme={theme}>
        <App />
      </ThemeProvider>
    </ColorModeContext.Provider>
  );
}
