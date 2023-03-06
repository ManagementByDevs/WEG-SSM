import React, { useContext } from "react";

import { Box, IconButton, Paper, Tooltip, Typography } from "@mui/material";

import DeleteOutlineOutlinedIcon from "@mui/icons-material/DeleteOutlineOutlined";

import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";
import DateService from "../../service/dateService";

const Pautas = (props) => {
  // Context para alterar a linguagem do sistema
  const { texts, setTexts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

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
  const getDataFormatada = (dataInicio) => {
    return DateService.getTodaysDateUSFormat(
      DateService.getDateByMySQLFormat(dataInicio)
    );
  };

  return (
    <Paper
      onClick={props.onItemClick}
      className="flex flex-col border-t-4 pt-2 pb-3 px-6"
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
        >
          #{props.dados.numeroSequencial}
        </Typography>
        <Box className="flex items-center">
          <Typography
            fontSize={FontConfig.medium}
            fontWeight="600"
            sx={{ color: "text.secondary" }}
          >
            {getDataFormatada(props.dados.inicioDataReuniao)}
          </Typography>
          {props.tipo === "ata" && (
            <Typography
              fontSize={FontConfig.default}
              fontWeight="600"
              sx={{ color: "text.secondary", marginLeft: "5px" }}
            >
              {getHorasFormatado(
                props.dados.inicioDataReuniao,
                props.dados.fimDataReuniao
              )}
            </Typography>
          )}
          {props.tipo === "pauta" && (
            <Box sx={{ marginRight: "-16px" }} className="ml-2">
              <Tooltip title={texts.pauta.deletar}>
                <IconButton
                  onClick={(e) => {
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
          )}
        </Box>
      </Box>
      <Box className="flex items-center mt-3">
        <Typography fontSize={FontConfig.medium} fontWeight="600">
          {texts.pauta.comissao}:
        </Typography>
        <Typography
          className="overflow-hidden text-ellipsis whitespace-nowrap"
          fontSize={FontConfig.default}
          fontWeight="600"
          sx={{ color: "text.secondary", marginLeft: "5px" }}
        >
          {props.dados.comissao}
        </Typography>
      </Box>
      <Box className="flex items-center">
        <Typography fontSize={FontConfig.medium} fontWeight="600">
          {texts.pauta.analistaResponsavel}:
        </Typography>
        <Typography
          className="overflow-hidden text-ellipsis whitespace-nowrap"
          fontSize={FontConfig.default}
          fontWeight="600"
          sx={{ color: "text.secondary", marginLeft: "5px", width: "60%" }}
        >
          {props.dados.analista.nome}
        </Typography>
      </Box>
    </Paper>
  );
};

export default Pautas;
