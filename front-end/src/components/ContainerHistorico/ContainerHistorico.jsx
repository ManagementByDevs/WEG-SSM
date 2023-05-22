import React, { useContext, useEffect } from "react";
import { Typography, Box, Tooltip, IconButton } from "@mui/material";

import DownloadIcon from "@mui/icons-material/Download";

import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";

import DateService from "../../service/dateService";

/** Componente de representação de um histórico da demanda, sendo repetido dentro de uma lista no componente ModalHistoricoDemanda
 * Objeto de histórico recebido pelo props (props.historico)
 */
const ContainerHistorico = (props) => {
  // Contexto para trocar a linguagem
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  /** Função para transformar uma string em base64 para um ArrayBuffer, usada para baixar anexos */
  function converterBase64(base64) {
    const textoBinario = window.atob(base64);
    const bytes = new Uint8Array(textoBinario.length);
    return bytes.map((byte, i) => textoBinario.charCodeAt(i));
  }

  /** Função para baixar o pdf do histórico da lista, recebendo o documento do props */
  const baixarHistorico = () => {
    const arquivo = props.historico.documentoHistorico;
    let blob =
      arquivo instanceof File
        ? arquivo
        : new Blob([converterBase64(arquivo.dados)], {
            type: "application/pdf",
          });
    let nomeArquivo =
      arquivo instanceof File ? arquivo.name : `${arquivo.nome}`;

    if (navigator.msSaveBlob) {
      navigator.msSaveBlob(blob, nomeArquivo);
    } else {
      const link = document.createElement("a");
      if (link.download !== undefined) {
        const url = URL.createObjectURL(blob);
        link.setAttribute("href", url);
        link.setAttribute("download", nomeArquivo);
        link.style.visibility = "hidden";
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
        URL.revokeObjectURL(url);
      }
    }
  };

  // Função que irá setar o texto que será "lido" pela a API
  const lerTexto = (texto) => {
    if (props.lendo) {
      props.setTexto(texto);
    }
  };

  // Função que irá "ouvir" o texto que será "lido" pela a API
  useEffect(() => {
    if (props.lendo && props.texto != "") {
      if ("speechSynthesis" in window) {
        const synthesis = window.speechSynthesis;
        const utterance = new SpeechSynthesisUtterance(props.texto);
        synthesis.speak(utterance);
      }
      props.setTexto("");
    }
  }, [props.texto]);

  return (
    <Box
      className="flex justify-between items-center border border-solid"
      sx={{
        borderLeft: "8px solid",
        borderColor: "primary.main",
        width: "90%",
        height: "4.5rem",
        borderRadius: "5px",
        p: 2,
        margin: "1%",
      }}
    >
      {/* Nome do autor */}
      <Typography
        sx={{ width: "40%" }}
        fontWeight={650}
        fontSize={FontConfig.veryBig}
        onClick={() => {
          lerTexto(props.historico?.autor.nome);
        }}
      >
        {props.historico?.autor.nome}
      </Typography>
      <Box
        sx={{
          display: "flex",
          justifyContent: "center",
          alignItens: "center",
          flexDirection: "column",
          textAlign: "center",
        }}
      >
        {/* Data do histórico */}
        <Typography
          fontSize={FontConfig.small}
          onClick={() => {
            lerTexto(DateService.getFullDateUSFormat(props.historico?.data));
          }}
        >
          {DateService.getFullDateUSFormat(props.historico?.data)}
        </Typography>

        {/* Texto da ação feita */}
        <Typography
          fontSize={FontConfig.big}
          onClick={() => {
            lerTexto(props.historico?.acaoRealizada);
          }}
        >
          {props.historico?.acaoRealizada}
        </Typography>
      </Box>

      {/* Botão para baixar o documento do histórico */}
      <Tooltip title="Baixar">
        <IconButton onClick={baixarHistorico}>
          <DownloadIcon sx={{ color: "text.primary" }} />
        </IconButton>
      </Tooltip>
    </Box>
  );
};

export default ContainerHistorico;
