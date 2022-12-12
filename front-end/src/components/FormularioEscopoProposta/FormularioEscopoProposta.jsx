import React from "react";

import TextEditor from "../TextEditor/TextEditor";

import { Box } from "@mui/material";

const FormularioEscopoProposta = () => {
  return (
    <Box className="mt-8" sx={{ height: "45rem" }}>
      <TextEditor />
    </Box>
  );
};

export default FormularioEscopoProposta;
