import React, { useContext } from "react";
import { useNavigate } from "react-router-dom";

import VLibras from "@djpfs/react-vlibras";

import { Typography, Box, Button } from "@mui/material";

import Error from "../../assets/Error.png";

import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";

/** Página de Not Found, caso o usuário entre em alguma url inexistente no sistema */
const NotFound = (props) => {

  /** useContext para alterar o idioma do sistema */
  const { texts } = useContext(TextLanguageContext);

  /** Context para alterar o tamanho da fonte */
  const { FontConfig, setFontConfig } = useContext(FontContext);

  /** Navigate utilizado para navegar para outra página */
  let navigate = useNavigate();

  /** Função para retornar a home */
  const voltarPaginaPrincipal = () => {
    if (!props.lendo) {
      navigate("/");
    } else {
      lerTexto(texts.notFound.voltar);
    }
  };

  /** Função que irá setar o texto que será "lido" pela a API */
  const lerTexto = (escrita) => {
    if (props.lendo) {
      const synthesis = window.speechSynthesis;
      const utterance = new SpeechSynthesisUtterance(escrita);

      const finalizarLeitura = () => {
        if ("speechSynthesis" in window) {
          synthesis.cancel();
        }
      };

      if (props.lendo && escrita !== "") {
        if ("speechSynthesis" in window) {
          synthesis.speak(utterance);
        }
      } else {
        finalizarLeitura();
      }

      return () => {
        finalizarLeitura();
      };
    }
  };

  return (
    <Box className="flex justify-center items-center w-screen h-screen">
      <VLibras forceOnload />
      {/* Componente com informações da página */}
      <Box className="flex justify-evenly flex-col items-center h-1/2">
        <img src={Error} />

        <Typography
          fontSize={FontConfig.title}
          color={"primary.main"}
          fontWeight={650}
          onClick={() => { lerTexto(texts.notFound.paginaNaoEncontrada) }}
        >
          {texts.notFound.paginaNaoEncontrada}
        </Typography>

        <Typography fontSize={FontConfig.veryBig} fontWeight={500} onClick={() => {
          lerTexto(texts.notFound.paginaNaoEncontrada);
        }}>
          {texts.notFound.paginaNaoEncontrada}
        </Typography>

        <Typography fontSize={FontConfig.veryBig} fontWeight={500} onClick={() => {
          lerTexto(texts.notFound.porfavorVolteParaPaginaPrincipal);
        }}>
          {texts.notFound.porfavorVolteParaPaginaPrincipal}
        </Typography>

        <Button
          className="w-32 rounded-sm p-1"
          fontSize={FontConfig.big}
          onClick={voltarPaginaPrincipal}
          variant="contained"
        >
          {texts.notFound.voltar}
        </Button>
      </Box>
    </Box>
  );
};

export default NotFound;
