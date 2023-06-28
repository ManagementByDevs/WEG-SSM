import React from "react";

import { Box } from "@mui/material";

import Header from "../Header/Header";

// Componente que cria um fundo com um header
const FundoComHeader = (props) => {
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
      <Box sx={{ width: "100%", height: "92.8vh", overflow: "auto" }}>
        {props.children}
      </Box>
    </Box>
  );
};

export default FundoComHeader;