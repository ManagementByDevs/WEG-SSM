import './App.css';
import React from 'react';
import {
  BrowserRouter as Router,
  Routes,
  Route
} from "react-router-dom";
import { ThemeProvider, useTheme, createTheme } from '@mui/material/styles';
import { CssBaseline } from '@mui/material';

import Home from './pages/Home/Home';
import Login from './pages/Login/Login';

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
          main: '#3D3F45',
        },
        divider: { main: '#3D3F45' },
        text: {
          primary: '#FFFFFF',
          secondary: '#BDBEC0',
          white: '#FFFFFF'
        },
        background: {
          default: '#22252C',
          paper: '#22252C'
        },
        component: {
          main: '#3D3F45'
        },
        input: {
          main: '#909295'
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
          white: '#FFFFFF'
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