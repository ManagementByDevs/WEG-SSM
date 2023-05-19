import React, { useContext, useState, useEffect } from "react";

import { Box, Tooltip } from "@mui/material";
import { styled } from "@mui/system";

import HearingOutlinedIcon from "@mui/icons-material/HearingOutlined";

const LerTexto = (props) => {
  // ********************************************** Funções de ler texto **********************************************

  const [corButton, setCorButton] = useState(
    "linear-gradient(to right, #0083B4, #00579D)"
  );

  const lerTexto = () => {
    if (props.lendo && props.texto != "") {
      if ("speechSynthesis" in window) {
        const synthesis = window.speechSynthesis;
        const utterance = new SpeechSynthesisUtterance(props.texto);
        synthesis.speak(utterance);
        console.log("Lendo texto...");
      } else {
        console.log(
          "A API de síntese de fala não é suportada neste navegador."
        );
      }
      console.log("Limpando texto...");
      props.setTexto("");
    } else {
      console.log("Não está lendo o texto...");
      props.setLendo(!props.lendo);
    }
  };

  useEffect(() => {
    if (props.lendo) {
      setCorButton("linear-gradient(to right, #00A8E8, #0083B4)");
    } else {
      setCorButton("linear-gradient(to right, #0083B4, #00579D)");
    }
  }, [props.lendo]);

  // ********************************************** Fim Funções de ler texto **********************************************

  return (
    <Tooltip title="Ouvir Texto" placement="left">
      <Box
        className="flex items-center justify-center absolute cursor-pointer"
        sx={{
          width: "2.35rem",
          height: "2.35rem",
          borderRadius: "30%",
          background: corButton,
          right: 0,
          top: 320,
          zIndex: 1000,
        }}
        onClick={lerTexto}
      >
        <HearingOutlinedIcon sx={{ color: "white" }} />
      </Box>
    </Tooltip>
  );
};

export default LerTexto;
