import React, { useContext } from "react";

import { Box, IconButton, Paper, Typography } from "@mui/material";

import AddIcon from "@mui/icons-material/Add";

import EntitiesObjectService from "../../../service/entitiesObjectService";
import FontContext from "../../../service/FontContext";
import TextLanguageContext from "../../../service/TextLanguageContext";
import SpeechSynthesisContext from "../../../service/SpeechSynthesisContext";

const PropostaItemList = ({
  draggable = false,
  onDragStart = () => {},
  proposta = EntitiesObjectService.proposta(),
  children,
}) => {
  /** Context para alterar o tamanho da fonte */
  const { FontConfig } = useContext(FontContext);

  /** Context para obter os textos do sistema */
  const { texts } = useContext(TextLanguageContext);

  /** Context para ler o texto da tela */
  const { lerTexto } = useContext(SpeechSynthesisContext);

  /** Retorna o ano a partir de uma data SQL */
  const getAno = (dateSql) => {
    return dateSql.split("-")[0];
  };

  return (
    <Paper
      className="w-full mt-1 flex justify-between p-1 border"
      square
      variant="outlined"
      draggable={draggable}
      onDragStart={(e) => onDragStart(e, proposta)}
    >
      <Box className="flex items-center gap-2 w-2/3">
        <Box className="w-1/3">
          <Typography
            fontSize={FontConfig.default}
            fontWeight={500}
            color="primary"
          >
            {proposta.codigoPPM}/{getAno(proposta.data)}
          </Typography>
        </Box>
        <Box className="w-2/3">
          <Typography
            fontSize={FontConfig.default}
            fontWeight={500}
            color="text.primary"
          >
            {proposta.titulo}
          </Typography>
        </Box>
      </Box>
      <Box className="w-1/3 flex justify-end">{children}</Box>
    </Paper>
  );
};

export default PropostaItemList;
