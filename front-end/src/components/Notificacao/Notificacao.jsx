import React, { useContext } from "react";
import { Box, IconButton, Typography } from "@mui/material";

import "./Notificacao.css";

import MarkEmailReadOutlinedIcon from "@mui/icons-material/MarkEmailReadOutlined";

import NotificacaoDetermineIcon from "../NotificacaoDetermineIcon/NotificacaoDetermineIcon";

import FontContext from "../../service/FontContext";
import DateService from "../../service/dateService";
import NotificacaoService from "../../service/notificacaoService";

const Notificacao = ({ notificacao, onNotificacaoClick, index }) => {
  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // Calculo de datas
  let dataAtual = DateService.getTodaysDate();
  let dataNotificacao = DateService.getDateByPreviousDate(notificacao.data);
  const diferencaMeses = dataAtual - dataNotificacao;
  const diferencaDias = diferencaMeses / (1000 * 60 * 60 * 24);

  const handleClick = () => {
    NotificacaoService.put({
      ...notificacao,
      visualizado: !notificacao.visualizado,
    }).then((data) => {
      onNotificacaoClick();
    });
  };

  return (
    // Container da natificacao
    <Box
      className="notificacao-item-componente flex items-center border rounded px-2 py-1 my-1 delay-120 hover:scale-105 duration-300"
      title={notificacao.titulo}
      onClick={handleClick}
      sx={{
        width: "92%",
        height: "90%",
        backgroundColor: "visualizado.true",
        cursor: "pointer",
        "&:hover": {
          backgroundColor: "visualizado.false",
        },
      }}
    >
      {/* Icon da notificacao */}
      <NotificacaoDetermineIcon tipoIcone={notificacao.tipoNotificacao} />

      {/* Texto da notificacao */}
      <Box className="flex flex-col w-3/4 mt-2">
        {/* A propria notificacao */}
        <Typography
          className="notificacao-item-componente-titulo overflow-hidden text-ellipsis whitespace-nowrap"
          fontSize={FontConfig.default}
          color={"text.primary"}
          sx={{ fontWeight: 600 }}
        >
          {notificacao.titulo}
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
            : diferencaDias > 28 && diferencaDias < 30
            ? "4 semanas atrás"
            : diferencaDias > 31
            ? "mais de 1 mês atrás"
            : null}
        </Typography>
      </Box>
      <MarkEmailReadOutlinedIcon className="notificacao-item-componente-read-icon" />
    </Box>
  );
};

export default Notificacao;
