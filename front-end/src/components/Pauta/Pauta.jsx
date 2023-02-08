import React, { useContext } from "react";

import { Box, IconButton, Paper, Tooltip, Typography } from "@mui/material";

import DeleteOutlineOutlinedIcon from "@mui/icons-material/DeleteOutlineOutlined";

import FontConfig from "../../service/FontConfig";

import FontContext from "../../service/FontContext";

const Pautas = (props) => {
  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  return (
    <Paper
      onClick={props.onClick}
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
            <Box sx={{ marginRight: "-16px" }} className="ml-2">
              <Tooltip title="Deletar">
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
        <Typography fontSize={FontConfig.medium} fontWeight="600">
          Analista Responsável:
        </Typography>
        <Typography
          className="overflow-hidden text-ellipsis whitespace-nowrap"
          fontSize={FontConfig.default}
          fontWeight="600"
          sx={{ color: "text.secondary", marginLeft: "5px", width: "60%" }}
        >
          {props.dados.analistaResponsavel}
        </Typography>
      </Box>
    </Paper>
  );
};

export default Pautas;
