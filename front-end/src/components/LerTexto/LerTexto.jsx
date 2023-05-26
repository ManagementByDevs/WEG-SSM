import React, { useContext, useState, useEffect } from "react";

import { Box, Tooltip } from "@mui/material";
import { styled } from "@mui/system";

import VolumeUpIcon from "@mui/icons-material/VolumeUp";

const LerTexto = (props) => {
  // ********************************************** Funções de ler texto **********************************************

  const [corButton, setCorButton] = useState(
    "linear-gradient(to right, #0083B4, #00579D)"
  );

  const lerTexto = () => {
    props.setLendo(!props.lendo);
  };

  useEffect(() => {
    if (props.lendo) {
      setCorButton("linear-gradient(to right, #00658a, #002f54)");
    } else {
      setCorButton("linear-gradient(to right, #0083B4, #00579D)");
    }
  }, [props.lendo]);

  // ********************************************** Fim Funções de ler texto **********************************************

  return (
    <Tooltip title="Ouvir Texto" placement="left">
      <Box
        className="flex items-center justify-center fixed cursor-pointer"
        sx={{
          width: "2.35rem",
          height: "2.35rem",
          borderRadius: "30%",
          background: corButton,
          right: 0,
          top: "33%",
          zIndex: 9999999,
        }}
        onClick={lerTexto}
      >
        <VolumeUpIcon sx={{ color: "white" }} />
      </Box>
    </Tooltip>
  );
};

export default LerTexto;
