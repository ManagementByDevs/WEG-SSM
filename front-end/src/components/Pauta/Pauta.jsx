import React from "react";

import { Box, Paper, Typography } from "@mui/material";

import DeleteOutlineOutlinedIcon from "@mui/icons-material/DeleteOutlineOutlined";

import FontConfig from "../../service/FontConfig";

const Pautas = (props) => {
  return (
    <Paper
      className="flex flex-col border-t-4 pt-2 pb-3 px-6"
      sx={{ borderColor: "primary.main", minWidth: "500px" }}
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
            {props.dados.data}
          </Typography>
          {props.tipo === "ata" && (
            <Typography
              fontSize={FontConfig.default}
              fontWeight="600"
              sx={{ color: "text.secondary", marginLeft: "5px" }}
            >
              - {props.dados.horaInicio} à {props.dados.horaFim}
            </Typography>
          )}
          {props.tipo === "pauta" && (
            <DeleteOutlineOutlinedIcon
              className="delay-120 hover:scale-110 duration-300 ml-5"
              sx={{
                color: "icon.main",
                cursor: "pointer",
                fontSize: "30px",
              }}
            />
          )}
        </Box>
      </Box>
      <Box className="flex items-center">
        <Typography fontSize={FontConfig.medium} fontWeight="600">
          Comissão:
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
        <Typography
          fontSize={FontConfig.medium}
          fontWeight="600"
        >
          Analista Responsável:
        </Typography>
        <Typography
          className="overflow-hidden text-ellipsis whitespace-nowrap"
          fontSize={FontConfig.default}
          fontWeight="600"
          sx={{ color: "text.secondary", marginLeft: "5px", width: '60%' }}
        >
          {props.dados.analistaResponsavel}
        </Typography>
      </Box>
    </Paper>
  );
};

export default Pautas;
