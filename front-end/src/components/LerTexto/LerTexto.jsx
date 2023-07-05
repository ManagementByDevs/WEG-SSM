import React, { useState, useEffect, useContext } from "react";

import { Box, Tooltip } from "@mui/material";

import VolumeUpIcon from "@mui/icons-material/VolumeUp";

import SpeechSynthesisContext from "../../service/SpeechSynthesisContext";

// Componente utilizado para ler um texto
const LerTexto = () => {

  /**  Variável utilizada para ler o texto */
  const { lendoTexto, setLendoTexto, librasAtivo } = useContext(SpeechSynthesisContext);

  /** Variável utilizada pra setar a cor do botao */
  const [corButton, setCorButton] = useState("linear-gradient(to right, #0083B4, #00579D)");

  /** Função para ler o texto selecionado */
  const lerTexto = () => {
    setLendoTexto(!lendoTexto);
  };

  /** useEffect utilizado para setar a cor do botão */
  useEffect(() => {
    if (lendoTexto) {
      setCorButton("linear-gradient(to right, #00658a, #002f54)");
    } else {
      setCorButton("linear-gradient(to right, #0083B4, #00579D)");
    }
  }, [lendoTexto]);

  return (
    <Tooltip title="Ouvir Texto" placement="left">
      <Box
        className="flex items-center justify-center cursor-pointer"
        sx={{
          minWidth: "40px",
          minHeight: "40px",
          borderRadius: "30%",
          background: corButton,
          right: "0",
          bottom: "8.5rem",
          position: "fixed",
          zIndex: 999999,
        }}
        onClick={lerTexto}
      >
        <VolumeUpIcon sx={{ color: "white" }} />
      </Box>
    </Tooltip>
  );
};

export default LerTexto;