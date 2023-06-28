import { React, useContext } from "react";
import { keyframes } from "@emotion/react";

import { Box, IconButton, Tooltip } from "@mui/material";
import HelpOutlineOutlinedIcon from "@mui/icons-material/HelpOutlineOutlined";

import TextLanguageContext from "../../service/TextLanguageContext";

/** Função utilizada para aparecer o ícone de ajuda ao passar o mouse */ 
const aparecer = keyframes({ from: { width: "2.5rem" }, to: { width: "3.5rem" } });

/** Função utilizada para desaparecer o ícone de ajuda ao tirar o mouse */ 
const sumir = keyframes({ from: { width: "3.5rem" }, to: { width: "2.5rem" } });

/** Componente de ajuda utilizado para demonstrar o usuário as principais funções do sistema */
const Ajuda = (props) => {

  /** Contexto para trocar a linguagem  */
  const { texts } = useContext(TextLanguageContext);

  return (
    // Box com verificação das funções de aparecer e desaparecer do ícone
    <Box
      onClick={props.onClick}
      className="flex absolute items-center "
      sx={{
        zIndex: 9999,
        width: "2.5rem",
        height: "2.5rem",
        backgroundColor: "primary.main",
        borderRadius: "15px 0 0 15px",
        right: 0,
        bottom: "2rem",
        cursor: "pointer",
        overflow: "hidden",
        animation: `${sumir} 1s forwards`,
        "&:hover": { animation: `${aparecer} 1s forwards`, },
      }}
    >
      {/* Ícone de ajuda */}
      <Tooltip title={texts.ajuda.ajuda} placement="left">
        <IconButton sx={{ color: "text.white" }}>
          <HelpOutlineOutlinedIcon sx={{ fontSize: "30px" }} />
        </IconButton>
      </Tooltip>
    </Box>
  );
};

export default Ajuda;