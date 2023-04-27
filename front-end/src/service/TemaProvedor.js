import React, { useMemo, useState, useEffect } from "react";

import { ThemeProvider, createTheme } from "@mui/material/styles";
import { CssBaseline } from "@mui/material";

import getDesignTokens from "./TemaConfig";
import ColorModeContext from "./TemaContext";

import UsuarioService from "./usuarioService";
import CookieService from "./cookieService";

const ToggleColorMode = (props) => {

  const [preferencias, setPreferencias] = useState(null);

  useEffect(() => {
    if(!CookieService.getCookie()) return;
    UsuarioService.getPreferencias(CookieService.getCookie().sub).then((prefs) => {
      setPreferencias(prefs);
    });
  }, []);

  const prefersDarkMode = preferencias?.themeMode;
  
  const [mode, setMode] = useState(prefersDarkMode == "dark" ? "dark" : "light");
  const colorMode = useMemo(
    () => ({
      toggleColorMode: () => {
        setMode((prevMode) => (prevMode === "light" ? "dark" : "light"));
      },
      mode,
    }),
    [mode]
  );

  const theme = useMemo(() => createTheme(getDesignTokens(mode)), [mode]);

  return (
    <ColorModeContext.Provider value={colorMode}>
      <ThemeProvider theme={theme}>
        <CssBaseline />
        {props.children}
      </ThemeProvider>
    </ColorModeContext.Provider>
  );
};

export default ToggleColorMode;
