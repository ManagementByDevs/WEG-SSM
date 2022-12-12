import React from "react";

import { Box } from "@mui/material";

import Header from "../Header/Header";

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
      <Box sx={{ height: "100%", overflow: "auto" }}>{props.children}</Box>
    </Box>
  );
};

export default FundoComHeader;
