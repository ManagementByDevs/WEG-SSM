import { React, useContext, useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";

import { Typography, Box, Tooltip } from "@mui/material";
import HomeOutlinedIcon from "@mui/icons-material/HomeOutlined";
import ArrowForwardIosOutlinedIcon from "@mui/icons-material/ArrowForwardIosOutlined";

import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";
import UsuarioService from "../../service/usuarioService";
import CookieService from "../../service/cookieService";

// Componente utilizado para mostrar o caminho atual do usuário no sistema
const Caminho = (props) => {
  // Contexto para trocar a linguagem
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // Navigate utilizado para nevegar para uma outra página
  const navigate = useNavigate();

  // Variável que armazena o caminho atual
  const caminhoURL = useLocation().pathname;

  // Variável que armazena o caminho url sem a "/"
  const listaCaminho = caminhoURL.split("/");

  const listaRotasPT = [
    "login",
    "criar-demanda",
    "notificacao",
    "chat",
    "detalhes-demanda",
    "editar-escopo",
    "escopos",
    "criar-proposta",
    "detalhes-proposta",
    "detalhes-ata",
    "detalhes-pauta",
  ];

  // Função responsável por retornar para o caminho desejado
  const getPathName = (item) => {
    item = item.charAt(0).toUpperCase() + item.slice(1);
    let indexCaminho = listaRotasPT.findIndex((e) => e == item.toLowerCase());

    return texts.rotas[indexCaminho];
  };

  // Função que irá setar o texto que será "lido" pela a API
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
    <Box
      className="flex items-center gap-x-1"
      color="link.main"
      sx={{ minWidth: "20rem" }}
    >
      <Tooltip title={texts.caminho.home}>
        <HomeOutlinedIcon
          className="cursor-pointer"
          sx={{ fontSize: "32px" }}
          onClick={() => {
            if (!CookieService.getCookie()) navigate("/");
            UsuarioService.getUsuarioByEmail(
              CookieService.getCookie().sub
            ).then((usuario) => {
              if (usuario.tipoUsuario == "SOLICITANTE") {
                navigate("/home");
              } else {
                navigate("/home-gerencia");
              }
            });
          }}
        />
      </Tooltip>
      <ArrowForwardIosOutlinedIcon sx={{ fontSize: "20px" }} />
      {/* Mostrando o caminho atual no sistema */}
      {listaCaminho.map((item, index) => {
        if (item !== "") {
          return (
            <Box key={index}>
              {!props.feedback ? (
                <Typography
                  className="cursor-pointer"
                  fontSize={FontConfig.default}
                  sx={{ fontWeight: 500 }}
                  onClick={() => {
                    if (!props.lendo) {
                      navigate("/" + item);
                    } else {
                      lerTexto(getPathName(item));
                    }
                  }}
                >
                  {getPathName(item)}
                </Typography>
              ) : (
                <Typography
                  className="cursor-pointer"
                  fontSize={FontConfig.default}
                  sx={{ fontWeight: 500 }}
                  onClick={() => {
                    lerTexto(getPathName(item));
                  }}
                >
                  {getPathName(item)}
                </Typography>
              )}
            </Box>
          );
        }
      })}
    </Box>
  );
};

export default Caminho;
