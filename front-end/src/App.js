import './App.css';
import React from 'react';
import {
  BrowserRouter as Router,
  Routes,
  Route
} from "react-router-dom";
import { ThemeProvider, useTheme, createTheme } from '@mui/material/styles';
import { CssBaseline } from '@mui/material';

import Home from './pages/Home/home';
import Login from './pages/Login/login';

export const ColorModeContext = React.createContext({
  toggleColorMode: () => { },
  mode: 'light'
});

const getDesignTokens = (mode) => ({
  palette: {
    mode,
    ...(mode == 'dark')
      ? {
        primary: {
          main: '#00579D',
        },
        secondary: {
          main: 'rgba(255, 255, 255, 0.12)',
        },
        divider: { main: 'rgba(255, 255, 255, 0.12)' },
        text: {
          primary: '#FFFFFF',
          secondary: 'rgba(255, 255, 255, 0.7)',
        },
        background: {
          default: '#22252C',
          paper: '#22252C'
        },
        component: {
          main: 'rgba(255, 255, 255, 0.12)'
        },
        input: {
          main: 'rgba(255, 255, 255, 0.5)'
        }
      }
      : {
        primary: {
          main: '#00579D',
        },
        secondary: {
          main: '#FFFFFF',
        },
        divider: { main: 'rgba(0, 0, 0, 0.3)' },
        text: {
          primary: '#000000',
          secondary: '#535353',
        },
        background: {
          default: '#FFFFFF',
          paper: '#FFFFFF'
        },
        component: {
          main: '#FFFFFF'
        },
        input: {
          main: '#F8F8F8'
        }
      }
  },
  typography: {
    fontFamily: "Inter, sans-serif",
    button: {
      textTransform: 'none',
    }
  }
});


function App() {
  const theme = useTheme();
  const colorMode = React.useContext(ColorModeContext);

  console.log('theme: ', theme);

  return (
    <Router>
      <Routes>
        <Route path="/login" element={<Login></Login>} />
        <Route path="/" element={<Home togglePalette={colorMode.toggleColorMode}></Home>} />
      </Routes>
    </Router>
  );
}

export default function ToggleColorMode() {
  const [mode, setMode] = React.useState('light');
  const colorMode = React.useMemo(
    () => ({
      toggleColorMode: () => {
        setMode((prevMode) =>
          prevMode === 'light' ? 'dark' : 'light',
        );
      },
      mode
    }),
    [mode],
  );

  const theme = React.useMemo(() => createTheme(getDesignTokens(mode)), [mode]);

  return (
    <ColorModeContext.Provider value={colorMode}>
      <ThemeProvider theme={theme}>
        <CssBaseline />
        <App />
      </ThemeProvider>
    </ColorModeContext.Provider>
  );
}