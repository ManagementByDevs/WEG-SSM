import { React, useState, useEffect, useContext } from "react";

import { Box } from "@mui/system";
import { Typography, Paper } from "@mui/material";

import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";
import DateService from "../../service/dateService";
import TemaContext from "../../service/TemaContext";
import SpeechSynthesisContext from "../../service/SpeechSynthesisContext";

/** Componente para representar uma pauta no sistema, contendo suas informações e ações */
const ContainerPauta = (props) => {
  /** Contexto para trocar a linguagem */
  const { texts } = useContext(TextLanguageContext);

  /** Context para alterar o tamanho da fonte */
  const { FontConfig } = useContext(FontContext);

  /** Context para ler o texto da tela */
  const { lerTexto } = useContext(SpeechSynthesisContext);

  /** Contexet para verificar o tema atual  */
  const { mode } = useContext(TemaContext);

  /** Variável de estado para verificar se a pauta foi selecionada ou não */
  const [pautaSelecionada, setPautaSelecionada] = useState(false);

  /** UseEffect para verificar se a pauta foi selecionada ou não */
  useEffect(() => {
    if (props.indexPautaSelecionada == props.index) {
      setPautaSelecionada(!pautaSelecionada);
    } else {
      setPautaSelecionada(false);
    }
  }, [props.indexPautaSelecionada]);

  /** Função para selecionar a pauta clicada */
  const selecionarPauta = () => {
    if (props.indexPautaSelecionada == props.index) {
      props.setIndexPautaSelecionada(null);
    } else {
      props.setIndexPautaSelecionada(props.index);
    }
  };

  /** Função para formatar a data de acordo com a linguagem do sistema */
  const getFormattedDate = (dateInMySQL) => {
    let date = DateService.getDateByMySQLFormat(dateInMySQL);

    console.log("Data: ", date, dateInMySQL);
    console.log("Props", props.pauta);

    switch (texts.linguagem) {
      case "pt":
        return (
          date.getDate() +
          "/" +
          (date.getMonth() + 1) +
          "/" +
          date.getFullYear()
        );
      case "en":
        return (
          date.getMonth() + 1 + "/" + date.getDate() + "/" + date.getFullYear()
        );
      case "es":
        return (
          date.getDate() +
          "/" +
          (date.getMonth() + 1) +
          "/" +
          date.getFullYear()
        );
      case "ch":
        return (
          date.getFullYear() +
          "/" +
          (date.getMonth() + 1) +
          "/" +
          date.getDate()
        );
      default:
        return (
          date.getDate() +
          "/" +
          (date.getMonth() + 1) +
          "/" +
          date.getFullYear()
        );
    }
  };

  /** Função para retornar a cor do background do componente de pauta corretamente */
  const getBackgroundColor = () => {
    if (!pautaSelecionada) {
      return mode == "dark" ? "#22252C" : "#FFFFFF";
    } else {
      return mode == "dark" ? "#2E2E2E" : "#E4E4E4";
    }
  };

  return (
    <Paper
      className="flex justify-center items-center flex-col w-11/12 h-24 rounded-md cursor-pointer"
      sx={{
        border: "1px solid",
        borderLeft: "solid 6px",
        borderColor: "primary.main",
        p: 4,
        margin: "1%",
        backgroundColor: getBackgroundColor(),
      }}
      onClick={selecionarPauta}
    >
      {/* Cabeçalho da pauta */}
      <Box className="w-full flex justify-between items-center">
        <Typography
          fontSize={FontConfig.medium}
          onClick={() => {
            lerTexto(texts.containerPauta.propostas);
          }}
        >
          {texts.containerPauta.propostas}:
        </Typography>
        <Typography
          fontSize={FontConfig.medium}
          onClick={() => {
            lerTexto(props.pauta.numeroSequencial);
          }}
        >
          {props.pauta.numeroSequencial} -&nbsp;
          {getFormattedDate(props.pauta.dataReuniao)}
        </Typography>
      </Box>

      {/* Título das propostas que estão na pauta */}
      <Box
        className="w-full grid gap-4"
        sx={{
          color: "primary.main",
          marginTop: "2%",
          gridTemplateColumns: "repeat(auto-fit, minmax(15%, 1fr))",
        }}
      >
        <Typography
          fontSize={FontConfig.medium}
          className="overflow-hidden whitespace-nowrap text-ellipsis"
          onClick={() => {
            lerTexto(
              props.pauta.propostas[0] ? props.pauta.propostas[0].titulo : ""
            );
          }}
        >
          {props.pauta.propostas[0] ? props.pauta.propostas[0].titulo : ""}
        </Typography>
        <Typography
          className="overflow-hidden whitespace-nowrap text-ellipsis"
          onClick={() => {
            lerTexto(
              props.pauta.propostas[1] ? props.pauta.propostas[0].titulo : ""
            );
          }}
        >
          {props.pauta.propostas[1] ? props.pauta.propostas[0].titulo : ""}
        </Typography>
      </Box>
    </Paper>
  );
};

export default ContainerPauta;
