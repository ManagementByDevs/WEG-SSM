import React, { useMemo, useState } from "react";

import { ThemeProvider, createTheme } from "@mui/material/styles";
import { CssBaseline } from "@mui/material";

import getDesignTokens from "./TemaConfig";
import ColorModeContext from "./TemaContext";

import UsuarioService from "./usuarioService";

const ToggleColorMode = (props) => {
  const prefersDarkMode = UsuarioService.getPreferencias().themeMode;
  
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
