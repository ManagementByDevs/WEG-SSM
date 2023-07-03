import React, { useContext } from "react";

import { Box, Typography } from "@mui/material";

import "./Notificacao.css";

import MarkEmailReadOutlinedIcon from "@mui/icons-material/MarkEmailReadOutlined";

import NotificacaoDetermineIcon from "../NotificacaoDetermineIcon/NotificacaoDetermineIcon";

import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";
import DateService from "../../service/dateService";
import NotificacaoService from "../../service/notificacaoService";
import EntitiesObjectService from "../../service/entitiesObjectService";
import SpeechSynthesisContext from "../../service/SpeechSynthesisContext";

import ChineseLanguageService from "../../service/chineseLanguageService";

// Componente para exibir as notificações do sistema
const Notificacao = ({
  notificacao = EntitiesObjectService.notificacao(),
  onNotificacaoClick = () => { },
}) => {

  // Context para alterar a linguagem do sistema
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  /** Context para ler o texto da tela */
  const { lerTexto, lendoTexto } = useContext(SpeechSynthesisContext);

  // Função para marcar a notificação como lida
  const handleClick = () => {
    NotificacaoService.put({
      ...notificacao,
      visualizado: true,
    }).then((data) => {
      onNotificacaoClick();
    });
  };

  // Função para retornar o titulo da notificação
  const retornaTitulo = () => {
    if (notificacao.numeroSequencial) {
      return formataStatus();
    }
  };

  const formatarData = (data) => {
    let dataAtual = DateService.getTodaysDate();
    let dataNotificacao = DateService.getDateByPreviousDate(data);

    if (dataAtual.getDate() == dataNotificacao.getDate() &&
      dataAtual.getMonth() == dataNotificacao.getMonth() &&
      dataAtual.getFullYear() == dataNotificacao.getFullYear()) {

      let hora = dataNotificacao.getHours();
      let minutos = dataNotificacao.getMinutes();

      if (hora.toString().length == 1) {
        hora = "0" + hora.toString();
      }
      if (minutos.toString().length == 1) {
        minutos = "0" + minutos.toString();
      }

      if (texts.linguagem != "ch") {
        return hora + ":" + minutos;
      } else {
        return ChineseLanguageService.formatHora(hora + "-" + minutos);
      }

    } else if (dataAtual.getMonth() == dataNotificacao.getMonth() &&
      dataAtual.getFullYear() == dataNotificacao.getFullYear() &&
      dataAtual.getDate() - 1 == dataNotificacao.getDate()) {
      return texts.notificacaoComponente.ontem;
    } else {
      let dataFinal = "";
      const dd = String(dataNotificacao.getDate()).padStart(2, "0");
      const mm = String(dataNotificacao.getMonth() + 1).padStart(2, "0");
      let yyyy = "null";

      if (dataNotificacao.getFullYear() != dataAtual.getFullYear()) {
        yyyy = dataNotificacao.getFullYear();
      }

      switch (texts.linguagem) {
        case "pt":
          dataFinal += dd + "/" + mm;
          break;
        case "en":
          dataFinal += mm + "/" + dd;
          break;
        case "es":
          dataFinal += dd + "/" + mm;
          break;
        case "ch":
          dataFinal += ChineseLanguageService.formatarDataCompleta(yyyy + "/" + mm + "/" + dd + "/null");
          break;
        default:
          dataFinal += dd + "/" + mm;
      }

      if (yyyy != "null") {
        dataFinal += yyyy;
      }
      return dataFinal;
    }
  }

  // Função para formatar o status da notificação
  const formataStatus = () => {
    let textAux = texts.notificacaoComponente;

    switch (notificacao.tipoNotificacao) {
      case "APROVADO":
        return `${textAux.demandaDeNumero} ${notificacao.numeroSequencial} ${textAux.foi} ${textAux.aprovada} ${textAux.por} ${notificacao.remetente.nome}!`;
      case "REPROVADO":
        return `${textAux.demandaDeNumero} ${notificacao.numeroSequencial} ${textAux.foi} ${textAux.reprovada} ${textAux.por} ${notificacao.remetente.nome}!`;
      case "MENSAGENS":
        return `${textAux.vcRecebeuMensagem} ${notificacao.numeroSequencial}!`;
      case "MAIS_INFORMACOES":
        return `${textAux.demandaDeNumero} ${notificacao.numeroSequencial} ${textAux.foi} ${textAux.reprovadaPorFaltaDeInformacoes} ${textAux.por} ${notificacao.remetente.nome}!`;
      case "APROVADO_GERENTE":
        return `${textAux.demandaDeNumero} ${notificacao.numeroSequencial} ${textAux.foi} ${textAux.aprovada} ${textAux.por} ${notificacao.remetente.nome}!`;
      case "REPROVADO_GERENTE":
        return `${textAux.demandaDeNumero} ${notificacao.numeroSequencial} ${textAux.foi} ${textAux.reprovada} ${textAux.por} ${notificacao.remetente.nome}!`;
      case "CRIADO_PROPOSTA":
        return `${textAux.propostaDeDemanda} ${notificacao.numeroSequencial} ${textAux.por} ${notificacao.remetente.nome}!`;
      case "APROVADO_COMISSAO":
        return `${textAux.demandaDeNumero} ${notificacao.numeroSequencial} ${textAux.foi} ${textAux.aprovada} ${textAux.noForum}!`;
      case "REPROVADO_COMISSAO":
        return `${textAux.demandaDeNumero} ${notificacao.numeroSequencial} ${textAux.foi} ${textAux.reprovada} ${textAux.noForum}!`;
      case "BUSINESS_CASE_COMISSAO":
        return `${textAux.demandaDeNumero} ${notificacao.numeroSequencial} ${textAux.entrouEm} ${textAux.businessCase}!`;
      case "MAIS_INFORMACOES_COMISSAO":
        return `${textAux.demandaDeNumero} ${notificacao.numeroSequencial} ${textAux.foi} ${textAux.reprovadaPorFaltaDeInformacoes} ${textAux.noForum}!`;
      case "APROVADO_DG":
        return `${textAux.demandaDeNumero} ${notificacao.numeroSequencial} ${textAux.foi} ${textAux.aprovada} ${textAux.naDG}!`;
      case "REPROVADO_DG":
        return `${textAux.demandaDeNumero} ${notificacao.numeroSequencial} ${textAux.foi} ${textAux.reprovada} ${textAux.naDG}!`;
      case "ASSESSMENT_ANALISTA":
        return `${textAux.oAnalistaResposnavel} ${textAux.mudouStatusDemanda} ${notificacao.numeroSequencial} ${textAux.para} ${textAux.assessment}!`;
      case "BUSINESS_CASE_ANALISTA":
        return `${textAux.oAnalistaResposnavel} ${textAux.mudouStatusDemanda} ${notificacao.numeroSequencial} ${textAux.para} ${textAux.businessCase}!`;
      case "CANCELLED_ANALISTA":
        return `${textAux.oAnalistaResposnavel} ${textAux.mudouStatusDemanda} ${notificacao.numeroSequencial} ${textAux.para} ${textAux.cancelled}!`;
      case "DONE_ANALISTA":
        return `${textAux.oAnalistaResposnavel} ${textAux.mudouStatusDemanda} ${notificacao.numeroSequencial} ${textAux.para} ${textAux.done}!`;
      default:
        return `${textAux.demandaDeNumero} ${notificacao.numeroSequencial} ${textAux.foi} ${textAux.reprovadaPorFaltaDeInformacoes} ${textAux.por} ${notificacao.remetente.nome}!`;
    }
  };

  return (
    // Container da notificação
    <Box
      className="notificacao-item-componente flex items-center border rounded px-2 py-1 my-1 delay-120 hover:scale-105 duration-300"
      title={retornaTitulo()}
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
      <Box className="mr-2">
        <NotificacaoDetermineIcon tipoIcone={notificacao.tipoNotificacao} />
      </Box>

      {/* Texto da notificacao */}
      <Box className="flex flex-col w-3/4 mt-2">
        {/* A propria notificacao */}
        <Typography
          className="notificacao-item-componente-titulo overflow-hidden text-ellipsis whitespace-nowrap"
          fontSize={FontConfig.default}
          color={"text.primary"}
          sx={{ fontWeight: 600 }}
          onClick={(e) => {
            if (lendoTexto) {
              e.preventDefault();
              lerTexto(retornaTitulo());
            }
          }}
        >
          {retornaTitulo()}
        </Typography>
        {/* O tempo da notificação */}
        <Typography
          className="text-end "
          fontSize={FontConfig.small}
          color={"text.secondary"}
          sx={{ fontWeight: 600 }}
          onClick={(e) => {
            if (lendoTexto) {
              e.preventDefault();
              lerTexto(formatarData(notificacao.data))
            }
          }}
        >
          {formatarData(notificacao.data)}
        </Typography>
      </Box>

      <MarkEmailReadOutlinedIcon className="notificacao-item-componente-read-icon" />
    </Box>
  );
};

export default Notificacao;