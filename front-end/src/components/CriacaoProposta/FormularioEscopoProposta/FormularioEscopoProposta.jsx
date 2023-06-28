import React from "react";

import { Box } from "@mui/material";

import TextEditor from "../../TextEditor/TextEditor";

/** Segunda etapa da criação da proposta, com um campo de texto para seu escopo */
const FormularioEscopoProposta = (props) => {

  return (
    <Box className="mt-8" sx={{ color: "black" }}>
      <TextEditor escopoTemp={props.escopoTemp} escopo={props.escopo} setEscopo={props.setEscopo} />
    </Box>
  );
};

export default FormularioEscopoProposta;