import React from "react";

import TextEditor from "../TextEditor/TextEditor";

import { Box } from "@mui/material";

const FormularioEscopoProposta = (props) => {
  return (
    <Box className="mt-8" sx={{color: "black"}}>
      <TextEditor escopo={props.escopo} setEscopo={props.setEscopo} />
    </Box>
  );
};

export default FormularioEscopoProposta;
