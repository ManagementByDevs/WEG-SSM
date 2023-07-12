import { React, useContext } from "react";
import { useLocation, useNavigate } from "react-router-dom";

import { Typography, Box, Tooltip } from "@mui/material";

import HomeOutlinedIcon from "@mui/icons-material/HomeOutlined";
import ArrowForwardIosOutlinedIcon from "@mui/icons-material/ArrowForwardIosOutlined";

import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";
import SpeechSynthesisContext from "../../service/SpeechSynthesisContext";

/** Componente utilizado para mostrar o caminho atual do usuário no sistema */
const Caminho = (props) => {
  /** Contexto para trocar a linguagem */
  const { texts } = useContext(TextLanguageContext);

  /** Context para alterar o tamanho da fonte */
  const { FontConfig } = useContext(FontContext);

  /** Context para ler o texto da tela */
  const { lendoTexto, lerTexto, librasAtivo } = useContext(
    SpeechSynthesisContext
  );

  /** Navigate utilizado para navegar para outra página */
  const navigate = useNavigate();

  /** Variável que armazena o caminho atual */
  const caminhoURL = useLocation().pathname;

  /** Variável que armazena o caminho url sem a "/" */
  const listaCaminho = caminhoURL.split("/").filter((item) => item !== "");

  /** Lista para armazenar as rotas utilizadas pelo caminho */
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
    "criar-pauta",
  ];

  /** Função responsável por pegar o caminho no qual o usuário está */
  const buscarNomeCaminho = (item) => {
    item = item.charAt(0).toUpperCase() + item.slice(1);
    let indexCaminho = listaRotasPT.findIndex((e) => e === item.toLowerCase());

    return texts.rotas[indexCaminho];
  };

  return (
    <Box
      className="flex items-center gap-x-1"
      color="link.main"
      sx={{ minWidth: "20rem" }}
    >
      {/* Ícone de home */}
      <Tooltip title={texts.caminho.home}>
        <HomeOutlinedIcon
          className="cursor-pointer"
          sx={{ fontSize: "32px" }}
          onClick={() => {
            navigate("/");
          }}
        />
      </Tooltip>
      <ArrowForwardIosOutlinedIcon sx={{ fontSize: "15px" }} />
      {/* Mostrando o caminho atual no sistema */}
      {listaCaminho.map((item, index) => {
        const caminho = listaCaminho.slice(0, index + 1).join("/");
        const nomeCaminho = buscarNomeCaminho(item);

        return (
          <Box className="flex items-center" key={index}>
            {!props.feedback ? (
              <Typography
                className="cursor-pointer"
                fontSize={FontConfig.default}
                sx={{ fontWeight: 500 }}
                onClick={() => {
                  if (!lendoTexto && !librasAtivo) {
                    navigate("/" + caminho);
                  } else {
                    lerTexto(nomeCaminho);
                  }
                }}
              >
                {nomeCaminho}
              </Typography>
            ) : (
              <Typography
                className="cursor-pointer"
                fontSize={FontConfig.default}
                sx={{ fontWeight: 500 }}
                onClick={() => {
                  lerTexto(nomeCaminho);
                }}
              >
                {nomeCaminho}
              </Typography>
            )}
            {index < listaCaminho.length - 1 && (
              <ArrowForwardIosOutlinedIcon sx={{ fontSize: "15px" }} />
            )}
          </Box>
        );
      })}
    </Box>
  );
};

export default Caminho;
