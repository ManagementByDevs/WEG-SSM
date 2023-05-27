import React, { useContext, useEffect, useRef, useState } from "react";

import { Box, Paper, Tooltip, Typography } from "@mui/material";

import DeleteIcon from "@mui/icons-material/Delete";

import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";

// Componente para mostrar os dados do escopo
const Escopo = (props) => {
  // Contexto para trocar a linguagem
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  /** Variável utilizada para armazenar o valor em html do campo */
  const propostaEscopo = useRef(null);

  /** useEffect utilizado para declarar o campo html na variável */
  useEffect(() => {
    if (propostaEscopo.current) {
      propostaEscopo.current.innerHTML = props.escopo.proposta;
    }
  }, []);

  /** Função para formatar o html em texto */
  const getPropostaFomartted = (proposta) => {
    return proposta[0]?.toUpperCase() + proposta.substring(1).toLowerCase();
  };

  const [textoLeitura,setTextoLeitura] = useState("");

  // Função que irá setar o texto que será "lido" pela a API
  const lerTexto = (escrita) => {
    if (props.lendo) {
      setTextoLeitura(escrita);
    }
  };

  // Função que irá "ouvir" o texto que será "lido" pela a API
  useEffect(() => {
    const synthesis = window.speechSynthesis;
    const utterance = new SpeechSynthesisUtterance(textoLeitura);

    const finalizarLeitura = () => {
      if ("speechSynthesis" in window) {
        synthesis.cancel();
      }
    };

    if (props.lendo && textoLeitura !== "") {
      if ("speechSynthesis" in window) {
        synthesis.speak(utterance);
      }
    } else {
      finalizarLeitura();
    }

    return () => {
      finalizarLeitura();
    };
  }, [textoLeitura]);

  return (
    <Paper
      className="flex flex-col gap-1 border-t-4 pt-2 pb-3 px-6 cursor-pointer"
      sx={{ borderColor: "primary.main" }}
      onClick={(e) => {
        if (!props.isTourOpen) {
          e.stopPropagation();
          props.onclick(props.index);
        }
      }}
    >
      {/* Container titulo e progressao */}
      <Box className="flex w-full gap-4 items-center">
        <Typography
          className="w-3/4 overflow-hidden text-ellipsis whitespace-nowrap"
          fontSize={FontConfig.veryBig}
          fontWeight="600"
          onClick={() => {
            lerTexto(props.escopo.titulo);
          }}
        >
          {props.escopo.titulo}
        </Typography>

        {/* Progressão do escopo */}
        <Box id="quarto" className="flex w-1/4 h-full gap-2 items-center">
          <Box
            className="w-11/12 h-4 flex rounded"
            sx={{ backgroundColor: "divider.main" }}
          >
            <Box
              className="w-full rounded"
              sx={{
                backgroundColor: "primary.main",
                width: props.escopo.porcentagem,
              }}
            />
          </Box>
          <Typography
            fontSize={FontConfig.medium}
            onClick={() => {
              lerTexto(props.escopo.porcentagem);
            }}
          >
            {props.escopo.porcentagem}
          </Typography>
        </Box>
      </Box>

      {/* Proposta (dados do escopo) */}
      <Box className="relative">
        <Box className="h-16">
          <Typography
            className="w-11/12 h-full overflow-hidden text-ellipsis whitespace-pre-wrap"
            fontSize={FontConfig.default}
            ref={propostaEscopo}
            onClick={() => {
              lerTexto(getPropostaFomartted(props.escopo.proposta));
            }}
          >
            {getPropostaFomartted(props.escopo.proposta)}
          </Typography>
        </Box>
        <Tooltip title={texts.escopo.titleExcluir}>
          <DeleteIcon
            id="terceiro"
            className="absolute bottom-0 delay-120 hover:scale-110 duration-300"
            sx={{ right: "-10px" }}
            color="primary"
            onClick={(e) => {
              if (!props.isTourOpen) {
                e.stopPropagation();
                props.handleDelete(props.index);
              }
            }}
          />
        </Tooltip>
      </Box>
    </Paper>
  );
};

export default Escopo;
