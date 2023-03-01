import React, { useContext } from "react";

import { Typography, Box } from "@mui/material";

import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";

const ContainerHistorico = (props) => {
  // Contexto para trocar a linguagem
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // variável de estilo para o container do histórico
  const styleComponenteHistorico = {
    display: "flex",
    justifyContent: "space-between",
    alignItems: "center",
    border: "solid 1px",
    borderLeft: "8px solid",
    borderColor: "primary.main",
    width: "90%",
    height: "4.5rem",
    borderRadius: "5px",
    p: 2,
    margin: "1%",
  };

  return (
    <Box sx={styleComponenteHistorico}>
      <Typography fontWeight={650} fontSize={FontConfig.veryBig}>
        {props.autorHistorico}
      </Typography>
      <Box
        sx={{
          display: "flex",
          justifyContent: "center",
          alignItens: "center",
          flexDirection: "column",
          textAlign: "center",
        }}
      >
        <Typography fontSize={FontConfig.small}>
          {props.dataHistorico}
        </Typography>
        <Typography fontSize={FontConfig.big}>
          {props.tituloHistorico}
        </Typography>
      </Box>
      <Typography fontWeight={650} fontSize={FontConfig.veryBig}>
        {props.versaoHistorico}
      </Typography>
    </Box>
  );
};

export default ContainerHistorico;
