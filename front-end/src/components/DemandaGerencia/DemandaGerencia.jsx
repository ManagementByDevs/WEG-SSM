import {React, useState} from "react";

import { Box, Paper, Typography } from "@mui/material";

import HistoryOutlinedIcon from "@mui/icons-material/HistoryOutlined";
import ChatOutlinedIcon from "@mui/icons-material/ChatOutlined";

import FontConfig from "../../service/FontConfig";

import ModalHistoricoDemanda from "../ModalHistoricoDemanda/ModalHistoricoDemanda";

const DemandaGerencia = (props) => {
  // Como usar:
  // Ao chamar o componente, passar duas props:
  // - dados: um objeto com os dados da demanda ou proposta
  // - tipo: "demanda" ou "proposta"
  // Exemplo:
  // <DemandaGerencia dados={demanda} tipo="demanda" />

  const tipo = props.tipo;

  function getCorStatus() {
    if (props.dados.status === "BACKLOG") {
      return "#00579D";
    } else if (props.dados.status === "ASSESSMENT") {
      return "#8862A2";
    }
  }

  const [modalHistorico, setModalHistorico] = useState(false);

  const abrirModalHistorico = () => {
    setModalHistorico(true);
  };

  return (
    <Paper
      className="flex flex-col border-t-4 pt-2 pb-3 px-6 drop-shadow-lg"
      sx={{
        "&:hover": {
          backgroundColor: "hover.main",

        },
        borderColor: "primary.main",
        minWidth: "729px",
        cursor: "pointer",
      }}
    >
      {/* Container titulo e status */}
      <Box className="flex w-full justify-between">
        {/* Título */}
        <Box className="flex w-3/4 mt-1">
          <Typography
            variant="h1"
            fontSize={FontConfig.veryBig}
            fontWeight="600"
            className="w-full overflow-hidden text-ellipsis whitespace-nowrap"
          >
            {tipo === "proposta" && (
              <Typography
                fontSize={FontConfig.default}
                fontWeight="600"
                sx={{ color: "primary.main" }}
              >
                PPM
              </Typography>
            )}
            {props.dados.titulo}
          </Typography>
        </Box>

        {/* Status do componente */}
        <Box className="w-1/4 h-full">
          <Box className="flex items-center gap-2 justify-end">
            <Typography fontSize={FontConfig.medium} fontWeight="600">
              {props.dados.status}
            </Typography>
            <Box
              className="rounded-full"
              sx={{
                width: "10px",
                height: "10px",
                backgroundColor: getCorStatus(),
              }}
            ></Box>
          </Box>
        </Box>
      </Box>

      {/* Dados do componente (demanda / proposta) */}
      <Box className="relative">
        <Box className="h-16 items-end w-full flex justify-between">
          {/* Infos Solicitante e Departamento */}
          <Box sx={{ width: "40%" }}>
            {/* Solicitante */}
            <Box className="flex">
              <Typography fontSize={FontConfig.default} fontWeight="600">
                Solicitante:
              </Typography>
              <Typography
                className="w-11/12 overflow-hidden text-ellipsis whitespace-nowrap"
                fontSize={FontConfig.default}
                fontWeight="600"
                sx={{ color: "text.secondary", marginLeft: "5px" }}
              >
                {props.dados.solicitante.nome}
              </Typography>
            </Box>

            {/* Departamento */}
            <Box className="flex">
              <Typography fontSize={FontConfig.default} fontWeight="600">
                Departamento:
              </Typography>
              <Typography
                className="w-1/2 overflow-hidden text-ellipsis whitespace-nowrap"
                fontSize={FontConfig.default}
                fontWeight="600"
                sx={{ color: "text.secondary", marginLeft: "5px" }}
              >
                {props.dados.departamento?.nome || "Não Atribuído"}
              </Typography>
            </Box>
          </Box>
          {/* Infos gerente responsável e icons */}
          <Box className="flex items-end justify-end w-full">
            <Box className="flex" sx={{ width: "19rem" }}>
              <Typography fontSize={FontConfig.default} fontWeight="600">
                Gerente responsável:
              </Typography>
              <Typography
                className="overflow-hidden truncate"
                fontSize={FontConfig.default}
                fontWeight="600"
                sx={{
                  color: "text.secondary",
                  marginLeft: "5px",
                  width: "50%",
                }}
              >
                {props.dados.gerenteResponsavel?.nome || "Não Atribuído"}
              </Typography>
            </Box>
            <Box>
              {/* Icon de histórico  e chat*/}
              <Box className="flex flex-col">
                {
                  // Se for uma proposta, mostra o icone de chat
                  tipo === "proposta" && (
                    <ChatOutlinedIcon
                      className="delay-120 hover:scale-110 duration-300 ml-5"
                      sx={{
                        color: "icon.main",
                        cursor: "pointer",
                        fontSize: "30px",
                      }}
                    />
                  )
                }
                <HistoryOutlinedIcon
                  className="delay-120 hover:scale-110 duration-300 ml-5"
                  sx={{
                    color: "icon.main",
                    cursor: "pointer",
                    fontSize: "30px",
                  }}
                  onClick={abrirModalHistorico}
                />
                {modalHistorico && <ModalHistoricoDemanda open={modalHistorico} setOpen={setModalHistorico}/>}
              </Box>
            </Box>
          </Box>
        </Box>
      </Box>
    </Paper>
  );
};

export default DemandaGerencia;
