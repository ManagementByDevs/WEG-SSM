import { React, useState, useEffect, useContext } from "react";

import { Box } from "@mui/system";
import { Typography, Paper } from "@mui/material";

import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";
import DateService from "../../service/dateService";
import TemaContext from "../../service/TemaContext";

const ContainerPauta = (props) => {
  // Contexto para trocar a linguagem
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // Contexet para verificar o tema atual
  const { mode } = useContext(TemaContext);

  // Variáveis de estilo utilizadas no componente
  const containerGeral = {
    width: "90%",
    height: "5.5rem",
    border: "1px solid",
    borderLeft: "solid 6px",
    borderColor: "primary.main",
    borderRadius: "5px",
    p: 4,
    margin: "1%",
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    flexDirection: "column",
    cursor: "pointer",
  };

  const parteCima = {
    width: "100%",
    display: "flex",
    justifyContent: "space-between",
    alignItems: "center",
  };

  const parteBaixo = {
    width: "100%",
    display: "grid",
    color: "primary.main",
    marginTop: "2%",
    gap: "1rem",
    gridTemplateColumns: "repeat(auto-fit, minmax(15%, 1fr))",
  };

  const tituloProposta = {
    overflow: "hidden",
    whiteSpace: "nowrap",
    textOverflow: "ellipsis",
  };

  // Variável de estado para verificar se a pauta foi selecionada ou não
  const [pautaSelecionada, setPautaSelecionada] = useState(false);

  // Função para selecionar a pauta clicada
  const selecionarPauta = () => {
    if (props.indexPautaSelecionada == props.index) {
      props.setIndexPautaSelecionada(null);
    } else {
      props.setIndexPautaSelecionada(props.index);
    }
  };

  // UseEffect para verificar se a pauta foi selecionada ou não
  useEffect(() => {
    if (props.indexPautaSelecionada == props.index) {
      setPautaSelecionada(!pautaSelecionada);
    } else {
      setPautaSelecionada(false);
    }
  }, [props.indexPautaSelecionada]);

  const getFormattedDate = (dateInMySQL) => {
    let date = DateService.getDateByMySQLFormat(dateInMySQL);

    return (
      date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear()
    );
  };

  // Função para retornar a cor do background do componente de pauta corretamente
  const getBackgroundColor = () => {
    if (!pautaSelecionada) {
      return mode == "dark" ? "#22252C" : "#FFFFFF";
    } else {
      return mode == "dark" ? "#2E2E2E" : "#E4E4E4";
    }
  };

  return (
    <Paper
      sx={{
        ...containerGeral,
        backgroundColor: getBackgroundColor(),
      }}
      onClick={selecionarPauta}
    >
      <Box sx={parteCima}>
        <Typography fontSize={FontConfig.medium}>
          {texts.containerPauta.propostas}:
        </Typography>
        <Typography fontSize={FontConfig.medium}>
          {props.pauta.numeroSequencial} -&nbsp;
          {getFormattedDate(props.pauta.dataReuniao)}
        </Typography>
      </Box>

      <Box sx={parteBaixo}>
        <Typography fontSize={FontConfig.medium} sx={tituloProposta}>{props.pauta.propostas[0] ? props.pauta.propostas[0].titulo : ""}</Typography>
        <Typography sx={tituloProposta}>{props.pauta.propostas[1] ? props.pauta.propostas[0].titulo : ""}</Typography>
      </Box>
    </Paper>
  );
};

export default ContainerPauta;
