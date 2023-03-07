import { Box, Typography, Avatar } from "@mui/material";
import React, { useContext, useState, useEffect } from "react";

import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";

const Contato = (props) => {
  // Contexto para trocar a linguagem
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // UseState para saber se o contato foi selecionado ou não
  const [corSelecionado, setCorSelecionado] = useState("transparent");

  // UseEffect para alterar a cor do contato quando ele for selecionado
  useEffect(() => {
    if (props.usuarioAtual === props.index) {
      setCorSelecionado("chat.eu");
    } else {
      setCorSelecionado("transparent");
    }
  }, [props.usuarioAtual]);

  return (
    <Box
      id="segundo"
      onClick={props.onClick}
      className="flex justify-evenly items-center rounded-lg border delay-120 hover:scale-105 duration-300"
      sx={{ width: "90%", minWidth: "195px", minHeight: "8%", cursor: "pointer", backgroundColor: corSelecionado, "&:hover": { backgroundColor: "chat.eu", } }}
      title={props.usuario.demanda}
    >
      <Box className="flex justify-content items-center">
        <Avatar
          sx={{ width: "3rem", height: "3rem" }}
          src={props.usuario.foto}
        />
      </Box>
      <Box className="flex justify-content flex-col" sx={{ width: "70%" }}>
        <Box className="flex justify-between">
          <Typography fontSize={FontConfig.medium} fontWeight="600">
            {props.usuario.nome}
          </Typography>
          <Typography
            fontSize={FontConfig.default}
            fontWeight="600"
            sx={{ color: "primary.main" }}
          >
            {props.usuario.codigoDemanda}
          </Typography>
        </Box>
        <Typography
          fontSize={FontConfig.small}
          fontWeight="400"
          className="overflow-hidden truncate"
          sx={{ width: "100%" }}
        >
          {texts.contato.demanda}: {props.usuario.demanda}
        </Typography>
      </Box>
    </Box>
  );
};

export default Contato;
