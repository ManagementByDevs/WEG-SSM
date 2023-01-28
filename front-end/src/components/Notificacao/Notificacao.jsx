import React, { useState, useContext } from "react";
import { Box, Typography, FormControlLabel, Tooltip } from "@mui/material";

import FontConfig from "../../service/FontConfig";

import HighlightOffOutlinedIcon from "@mui/icons-material/HighlightOffOutlined";

import FontContext from "../../service/FontContext";

const Notificacao = (props) => {
  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);
  
  // Calculo de datas
  let dataAtual = new Date();
  let dataNotificacao = new Date(props.notificacao.data);
  const diferencaMeses = new Date(dataAtual) - new Date(dataNotificacao);
  const diferencaDias = diferencaMeses / (1000 * 60 * 60 * 24);

  const [corNotificacao, setCorNotificacao] = useState("visualizado.true");

  const handleClick = () => {
      if (corNotificacao === "visualizado.true") {
          setCorNotificacao("");
        }
        props.notificacao.lida = true;
  };

  return (
    // Container da natificacao
    <Box
      className="flex items-center border rounded px-2 py-1 mt-2 delay-120 hover:scale-105 duration-300"
      title={props.notificacao.notificacao}
      onClick={handleClick}
      sx={{
        width: "92%",
        height: "90%",
        backgroundColor: corNotificacao,
        cursor: "pointer",
      }}
    >
      {/* Icon da notificacao */}
      <HighlightOffOutlinedIcon sx={{ fontSize: "35px", marginRight: "5px" }} />

      {/* Texto da notificacao */}
      <Box className="flex flex-col w-44 mt-2">
        {/* A propria notificacao */}
        <Typography
          className="overflow-hidden text-ellipsis whitespace-nowrap"
          fontSize={FontConfig.default}
          color={"text.primary"}
          sx={{ fontWeight: 600 }}
        >
          {props.notificacao.notificacao}
        </Typography>
        {/* O tempo da notificação */}
        <Typography
          className="text-end "
          fontSize={FontConfig.small}
          color={"text.secondary"}
          sx={{ fontWeight: 600 }}
        >
          {diferencaDias < 7 && diferencaDias > 1
            ? `${diferencaDias.toFixed(0) * 1 - 1} dias atrás`
            : diferencaDias < 1 && diferencaDias > 0
            ? `Hoje`
            : diferencaDias > 7 && diferencaDias < 14
            ? "1 semana atrás"
            : diferencaDias > 14 && diferencaDias < 21
            ? "2 semanas atrás"
            : diferencaDias > 21 && diferencaDias < 28
            ? "3 semanas atrás"
            : diferencaDias > 28 && diferencaDias < 35
            ? "4 semanas atrás"
            : diferencaDias > 35
            ? "1 mês atrás"
            : diferencaDias > 42
            ? "mais de 1 mês atrás"
            : null}
        </Typography>
      </Box>
    </Box>
  );
};

export default Notificacao;
