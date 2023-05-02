import React, { useContext } from "react";

import { Box } from "@mui/material";

import TextEditor from "../TextEditor/TextEditor";

const FormularioEscopoProposta = (props) => {

  return (
    <Box className="mt-8" sx={{ color: "black" }}>
      <TextEditor escopoTemp={props.escopoTemp} escopo={props.escopo} setEscopo={props.setEscopo} />
    </Box>
  );
};

export default FormularioEscopoProposta;
