import React, {useContext} from "react";

import TextEditor from "../TextEditor/TextEditor";

import { Box } from "@mui/material";

import FontContext from "../../service/FontContext";

const FormularioEscopoProposta = (props) => {
  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);
  
  return (
    <Box className="mt-8" sx={{color: "black"}}>
      <TextEditor escopo={props.escopo} setEscopo={props.setEscopo} />
    </Box>
  );
};

export default FormularioEscopoProposta;
