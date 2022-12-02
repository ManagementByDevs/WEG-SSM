import React from "react";

import { Box, Paper, Typography } from "@mui/material";

import FontConfig from "../../service/FontConfig";

import HistoryOutlinedIcon from "@mui/icons-material/HistoryOutlined";
import { Height } from "@mui/icons-material";

const DemandaGerencia = (props) => {
  function getCorStatus() {
    if (props.dados.status === "Backlog") {
      return "#00579D";
    } else if (props.dados.status === "Assessment") {
      return "#8862A2";
    }
  }

  return (
    <Paper
      className="flex flex-col border-t-4 pt-2 pb-3 px-6"
      sx={{ borderColor: "primary.main", minWidth: "729px" }}
    >
      {/* Container titulo e status */}
      <Box className="flex w-full justify-between">
        {/* Título */}
        <Box className="flex items-end">
          <Typography
            fontSize={FontConfig.medium}
            fontWeight="600"
            sx={{color: "primary.main" }}
          >
            PPM
          </Typography>
          <Typography
            fontSize={FontConfig.veryBig}
            fontWeight="600"
          >
            {props.dados.titulo}
          </Typography>
        </Box>

        {/* Status do componente */}
        <Box className="flex w-1/4 h-full gap-2 items-center justify-end">
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
                className="w-11/12 overflow-hidden text-ellipsis whitespace-pre-wrap"
                fontSize={FontConfig.default}
                fontWeight="600"
                sx={{ color: "text.secondary", marginLeft: "5px" }}
              >
                {props.dados.solicitante}
              </Typography>
            </Box>

            {/* Departamento */}
            <Box className="flex">
              <Typography fontSize={FontConfig.default} fontWeight="600">
                Departamento:
              </Typography>
              <Typography
                className="w-1/2 overflow-hidden text-ellipsis whitespace-pre-wrap"
                fontSize={FontConfig.default}
                fontWeight="600"
                sx={{ color: "text.secondary", marginLeft: "5px" }}
              >
                {props.dados.departamento}
              </Typography>
            </Box>
          </Box>
          {/* Infos gerente responsável e icons */}
          <Box className="flex items-center justify-end w-full">
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
                {props.dados.gerenteResponsavel}
              </Typography>
            </Box>
            {/* Icon de histórico */}
            <Box>
              <HistoryOutlinedIcon
                className="delay-120 hover:scale-110 duration-300 ml-5"
                sx={{ color: "icon.main", cursor: "pointer", fontSize: "30px" }}
              />
            </Box>
          </Box>
        </Box>
      </Box>
    </Paper>
  );
};

export default DemandaGerencia;
