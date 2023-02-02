import React, { useContext } from "react";
import { Box, IconButton, Tooltip, Typography } from "@mui/material";

import FontConfig from "../../service/FontConfig";
import FontContext from "../../service/FontContext";

import HelpOutlineOutlinedIcon from "@mui/icons-material/HelpOutlineOutlined";

import { keyframes } from "@emotion/react";

const aparecer = keyframes({ from: { width: "1.3rem" }, to: { width: "3.2rem" } });
const sumir = keyframes({ from: { width: "3.2rem" }, to: { width: "1.3rem" } });

const Ajuda = () => {
  // Contexto para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  return (
    <Box
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
      <Tooltip title="Ajuda" placement="top">
        <IconButton sx={{ color: "text.white" }}>
          <HelpOutlineOutlinedIcon sx={{ fontSize: "30px" }} />
        </IconButton>
      </Tooltip>
    </Box>
  );
};

export default Ajuda;
