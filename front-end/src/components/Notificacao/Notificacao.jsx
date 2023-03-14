import React, { useContext } from "react";

import { Box, IconButton, Typography } from "@mui/material";

import "./Notificacao.css";

import MarkEmailReadOutlinedIcon from "@mui/icons-material/MarkEmailReadOutlined";

import NotificacaoDetermineIcon from "../NotificacaoDetermineIcon/NotificacaoDetermineIcon";

import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";
import DateService from "../../service/dateService";
import NotificacaoService from "../../service/notificacaoService";

// Componente para exibir as notificações do sistema
const Notificacao = ({ notificacao, onNotificacaoClick, index }) => {

  // Context para alterar a linguagem do sistema
  const { texts, setTexts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // Calculo de datas
  let dataAtual = DateService.getTodaysDate();
  let dataNotificacao = DateService.getDateByPreviousDate(notificacao.data);
  const diferencaMeses = dataAtual - dataNotificacao;
  const diferencaDias = diferencaMeses / (1000 * 60 * 60 * 24);

  // Função para marcar a notificação como lida
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
            ? `${diferencaDias.toFixed(0) * 1 - 1} ${texts.notificacao.diasAtras}`
            : diferencaDias < 1 && diferencaDias > 0
              ? texts.notificacao.hoje
              : diferencaDias > 7 && diferencaDias < 14
                ? texts.notificacao.umaSemanaAtras
                : diferencaDias > 14 && diferencaDias < 21
                  ? texts.notificacao.duasSemanasAtras
                  : diferencaDias > 21 && diferencaDias < 28
                    ? texts.notificacao.tresSemanasAtras
                    : diferencaDias > 28 && diferencaDias < 30
                      ? texts.notificacao.quatroSemanasAtras
                      : diferencaDias > 31
                        ? texts.notificacao.maisDeUmMesAtras
                        : null}
        </Typography>
      </Box>
      <MarkEmailReadOutlinedIcon className="notificacao-item-componente-read-icon" />
    </Box>
  );
};

export default Notificacao;