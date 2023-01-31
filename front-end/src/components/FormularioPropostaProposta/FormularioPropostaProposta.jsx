import React, { useState, useContext } from "react";

import { Box } from "@mui/material";

import DetalhesDemanda from "../DetalhesDemanda/DetalhesDemanda";

import FontContext from "../../service/FontContext";

const FormularioPropostaProposta = (props) => {
  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  return (
    <Box>
      <DetalhesDemanda
        dados={props.dados}
        setDados={props.setDados}
        edicao={props.editar}
        setEdicao={props.setEditar}
        salvarClick={props.salvarClick}
        setSalvarClick={props.setSalvarClick}
      />
    </Box>
  );
};

export default FormularioPropostaProposta;
