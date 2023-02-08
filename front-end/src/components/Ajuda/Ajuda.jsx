import { React } from "react";
import { Box, IconButton, Tooltip } from "@mui/material";

import HelpOutlineOutlinedIcon from "@mui/icons-material/HelpOutlineOutlined";

import { keyframes } from "@emotion/react";

// Função utilizada para aparecer o ícone de ajuda ao passar o mouse
const aparecer = keyframes({ from: { width: "1.3rem" }, to: { width: "3.2rem" } });

// Função utilizada para desaparecer o ícone de ajuda ao tirar o mouse
const sumir = keyframes({ from: { width: "3.2rem" }, to: { width: "1.3rem" } });

const Ajuda = (props) => {

  return (
    // Box com verificação das funções de aparecer e desaparecer do ícone
    <Box
      onClick={props.onClick}
      className="flex absolute items-center "
      sx={{
        width: "1.5rem",
        height: "2.5rem",
        backgroundColor: "primary.main",
        borderRadius: "15px 0 0 15px",
        right: 0,
        bottom: "2rem",
        cursor: "pointer",
        overflow: "hidden",
        animation: `${sumir} 1s forwards`,
        "&:hover": {
          animation: `${aparecer} 1s forwards`,
        },
      }}
    >
      {/* Ícone de ajuda */}
      <Tooltip title="Ajuda" placement="top">
        <IconButton sx={{ color: "text.white" }}>
          <HelpOutlineOutlinedIcon sx={{ fontSize: "30px" }} />
        </IconButton>
      </Tooltip>
    </Box>
  );
};

export default Ajuda;
