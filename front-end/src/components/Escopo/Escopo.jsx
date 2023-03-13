import React, { useContext } from "react";

import { Box, Paper, Tooltip, Typography } from "@mui/material";

import DeleteIcon from "@mui/icons-material/Delete";

import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";

// Componente para mostrar os dados do escopo
const Escopo = (props) => {

  // Contexto para trocar a linguagem
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  return (
    <Paper
      className="flex flex-col gap-1 border-t-4 pt-2 pb-3 px-6 cursor-pointer"
      sx={{ borderColor: "primary.main" }}
      onClick={(e) => {
        if (!props.isTourOpen) {
          e.stopPropagation();
          props.onclick(props.index);
        }
      }}
    >
      {/* Container titulo e progressao */}
      <Box className="flex w-full gap-4 items-center">
        <Typography
          className="w-3/4 overflow-hidden text-ellipsis whitespace-nowrap"
          fontSize={FontConfig.veryBig}
          fontWeight="600"
        >
          {props.escopo.titulo}
        </Typography>

        {/* Progressão do escopo */}
        <Box id="quarto" className="flex w-1/4 h-full gap-2 items-center">
          <Box
            className="w-11/12 h-4 flex rounded"
            sx={{ backgroundColor: "divider.main" }}
          >
            <Box
              className="w-full rounded"
              sx={{
                backgroundColor: "primary.main",
                width: props.escopo.porcentagem,
              }}
            />
          </Box>
          <Typography fontSize={FontConfig.medium}>
            {props.escopo.porcentagem}
          </Typography>
        </Box>
      </Box>

      {/* Proposta (dados do escopo) */}
      <Box className="relative">
        <Box className="h-16">
          <Typography
            className="w-11/12 h-full overflow-hidden text-ellipsis whitespace-pre-wrap"
            fontSize={FontConfig.default}
          >
            {props.escopo.proposta}
          </Typography>
        </Box>
        <Tooltip title={texts.escopo.titleExcluir}>
          <DeleteIcon
            id="terceiro"
            className="absolute bottom-0 delay-120 hover:scale-110 duration-300"
            sx={{ right: "-10px" }}
            color="primary"
            onClick={(e) => {
              if (!props.isTourOpen) {
                e.stopPropagation();
                props.handleDelete(props.index);
              }
            }}
          />
        </Tooltip>
      </Box>
    </Paper>
  );
};

export default Escopo;
