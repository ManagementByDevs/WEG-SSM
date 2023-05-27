import React, { useContext, useEffect, useState } from "react";

import { Box, IconButton, Paper, Tooltip, Typography } from "@mui/material";

import DeleteOutlineOutlinedIcon from "@mui/icons-material/DeleteOutlineOutlined";

import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";
import DateService from "../../service/dateService";

const Pautas = (props) => {
  // Context para alterar a linguagem do sistema
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  // Estado para saber se a ata já foi apreciada pela DG
  const [isApreciada, setIsApreciada] = useState(false);

  // Retorna as horas de início e fim formatadas para melhor leitura
  const getHorasFormatado = (dataInicio, dataFim) => {
    let dateInicio = new Date(DateService.getDateByMySQLFormat(dataInicio));
    let dateFim = new Date(DateService.getDateByMySQLFormat(dataFim));

    return `${dateInicio.getHours()}:${
      dateInicio.getMinutes() < 10
        ? "0" + dateInicio.getMinutes()
        : dateInicio.getMinutes()
    } às ${dateFim.getHours()}:${
      dateFim.getMinutes() < 10
        ? "0" + dateFim.getMinutes()
        : dateFim.getMinutes()
    }`;
  };

  // Função para formatar a data para melhor leitura
  const getDataFormatada = (dataReuniao) => {
    return props.tipo == "pauta"
      ? DateService.getTodaysDateUSFormat(
          DateService.getDateByMySQLFormat(dataReuniao)
        )
      : DateService.getFullDateUSFormat(
          DateService.getDateByMySQLFormat(dataReuniao)
        );
  };

  // Função que retorna a cor do status da ata
  const getStatusColor = () => {
    if (isApreciada) return "success.main";
    return "#C4C4C4";
  };

  useEffect(() => {
    // Faz a verificação se a ata já foi apreciada pela DG
    if (props.dados.propostas?.length > 0) {
      setIsApreciada(props.dados.propostas[0].parecerDG != null);
    }
  }, []);
  const [textoLeitura,setTextoLeitura] = useState("");

  // Função que irá setar o texto que será "lido" pela a API
  const lerTexto = (escrita) => {
    if (props.lendo) {
      setTextoLeitura(escrita);
    }
  };

  // Função que irá "ouvir" o texto que será "lido" pela a API
  useEffect(() => {
    const synthesis = window.speechSynthesis;
    const utterance = new SpeechSynthesisUtterance(textoLeitura);

    const finalizarLeitura = () => {
      if ("speechSynthesis" in window) {
        synthesis.cancel();
      }
    };

    if (props.lendo && textoLeitura !== "") {
      if ("speechSynthesis" in window) {
        synthesis.speak(utterance);
      }
    } else {
      finalizarLeitura();
    }

    return () => {
      finalizarLeitura();
    };
  }, [textoLeitura]);
  
  return (
    <Paper
      onClick={() => props.onItemClick(props.dados)}
      className="flex flex-col border-t-4 pt-2 pb-3 pl-6 pr-4"
      sx={{
        "&:hover": {
          backgroundColor: "hover.main",
        },
        borderColor: "primary.main",
        minWidth: "500px",
        cursor: "pointer",
      }}
    >
      <Box className="flex w-full justify-between">
        <Typography
          fontSize={FontConfig.big}
          fontWeight="600"
          sx={{ color: "primary.main" }}
          onClick={() => lerTexto(props.dados.numeroSequencial)}
        >
          #{props.dados.numeroSequencial}
        </Typography>
        <Box className="flex items-center">
          <Typography
            fontSize={FontConfig.medium}
            fontWeight="600"
            sx={{ color: "text.secondary" }}
            onClick={() => lerTexto(getDataFormatada(props.dados.dataReuniao))}
          >
            {getDataFormatada(props.dados.dataReuniao)}
          </Typography>
          {props.tipo == "pauta" ? (
            <Box sx={{ marginRight: "-8px" }} className="ml-2">
              <Tooltip title={texts.pauta.deletar}>
                <IconButton
                  onClick={(e) => {
                    props.setPautaSelecionada(props.dados);
                    e.stopPropagation();
                  }}
                >
                  <DeleteOutlineOutlinedIcon
                    id="segundoPautas"
                    className="delay-120 hover:scale-110 duration-300 "
                    sx={{
                      color: "icon.main",
                      cursor: "pointer",
                      fontSize: "30px",
                    }}
                  />
                </IconButton>
              </Tooltip>
            </Box>
          ) : (
            <Tooltip
              title={
                isApreciada ? texts.pauta.jaApreciada : texts.pauta.naoApreciada
              }
            >
              <Box
                className="w-6 h-4 ml-3 rounded"
                sx={{ backgroundColor: getStatusColor() }}
              />
            </Tooltip>
          )}
        </Box>
      </Box>
      <Box className="flex items-center mt-3">
        <Typography
          fontSize={FontConfig.medium}
          fontWeight="600"
          onClick={() => lerTexto(props.dados.titulo)}
        >
          {texts.pauta.comissao}:
        </Typography>
        <Typography
          className="overflow-hidden text-ellipsis whitespace-nowrap"
          fontSize={FontConfig.default}
          fontWeight="600"
          sx={{ color: "text.secondary", marginLeft: "5px" }}
          onClick={() => lerTexto(props.dados.comissao?.nomeForum)}
        >
          {props.dados.comissao?.siglaForum} - {props.dados.comissao?.nomeForum}
        </Typography>
      </Box>
      <Box className="flex items-center">
        <Typography
          fontSize={FontConfig.medium}
          fontWeight="600"
          onClick={() => lerTexto(texts.pauta.analistaResponsavel)}
        >
          {texts.pauta.analistaResponsavel}:
        </Typography>
        <Typography
          className="overflow-hidden text-ellipsis whitespace-nowrap"
          fontSize={FontConfig.default}
          fontWeight="600"
          sx={{ color: "text.secondary", marginLeft: "5px", width: "60%" }}
          onClick={() => lerTexto(props.dados.analistaResponsavel?.nome)}
        >
          {props.dados.analistaResponsavel?.nome}
        </Typography>
      </Box>
    </Paper>
  );
};

export default Pautas;
