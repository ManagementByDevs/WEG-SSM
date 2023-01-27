import React, {useContext} from "react";

import { Box } from "@mui/material";

import Header from "../Header/Header";

import FontContext from "../../service/FontContext";

const FundoComHeader = (props) => {
  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);
  
  return (
    <Box
      sx={{
        backgroundColor: "background.default",
        height: "100vh",
        minHeight: "100vh",
        width: "100%",
        overflowY: "hidden",
      }}
    >
      <Header />

      {/* O box aqui precisa ter o height = 100vh - (h-header-weg) */}
      <Box sx={{ width: "100%", height: "92.8vh", overflow: "auto" }}>{props.children}</Box>
    </Box>
  );
};

export default FundoComHeader;
