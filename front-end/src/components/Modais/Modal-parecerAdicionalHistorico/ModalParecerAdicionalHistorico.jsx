import React, { useContext, useRef } from "react";

import { Modal, Typography, Box, Fade } from "@mui/material";

import CloseIcon from "@mui/icons-material/Close";

import TextLanguageContext from "../../../service/TextLanguageContext";
import FontContext from "../../../service/FontContext";
import SpeechSynthesisContext from "../../../service/SpeechSynthesisContext";
import { useEffect } from "react";
import { useState } from "react";

/** Modal para ver o motivo da recusa de demanda para o solicitante */
const ModalParecerAdicionalHistorico = (props) => {

  // Context para alterar a linguagem do sistema
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  /** Context para ler o texto da tela */
  const { lerTexto } = useContext(SpeechSynthesisContext);

  // Variável utilizada para armazenar o texto
  const referenciaTexto = useRef(null);

  /** Variável para carregar o texto do modal quando a variável de referência carregar */
  const [dadosAtribuidos, setDadosAtribuidos] = useState(false);

  // Função utilizada para formatar o texto da informação, caso seja HTML
  const getTextoFomartted = (texto) => {
    return texto[0].toUpperCase() + texto.substring(1).toLowerCase();
  };

  useEffect(() => {
    setTimeout(() => {
      if (referenciaTexto.current) {
        setDadosAtribuidos(true);
      }
    }, 10)
  }, [])

  useEffect(() => {
    if (dadosAtribuidos) {
      referenciaTexto.current.innerHTML = props.parecerAdicional;
    }
  }, [dadosAtribuidos])

  return (
    <Modal
      open={props.open}
      onClose={() => { props.setOpen(false); }}
      closeAfterTransition
    >
      <Fade in={props.open}>
        <Box
          className="absolute top-2/4 left-2/4 flex flex-col justify-between items-center"
          sx={{
            transform: "translate(-50%, -50%)",
            width: 480,
            height: 350,
            bgcolor: "background.paper",
            borderRadius: "5px",
            borderTop: "10px solid #00579D",
            boxShadow: 24,
            p: 4,
          }}
        >
          {/* Ícone de fechar o modal */}
          <CloseIcon
            onClick={() => { props.setOpen(false); }}
            sx={{
              position: "absolute",
              left: "93%",
              top: "3%",
              cursor: "pointer",
            }}
          />
          <Typography
            fontSize={FontConfig.veryBig}
            onClick={() => {
              lerTexto(texts.modalHistoricoDemanda.informacaoAdicional);
            }}
          >
            {texts.modalHistoricoDemanda.informacaoAdicional}
          </Typography>
          <Box
            className="flex text-justify border border-solid w-full h-full"
            sx={{
              marginTop: "5%",
              borderColor: "divider.main",
              borderRadius: "5px",
              p: 2,
            }}
          >
            {/* Parecer Adicional */}
            <Typography
              fontSize={FontConfig.normal}
              onClick={() => { lerTexto(props.parecerAdicional); }}
              ref={referenciaTexto}
            >
              {getTextoFomartted(props.parecerAdicional)}
            </Typography>
          </Box>
        </Box>
      </Fade>
    </Modal>
  );
};

export default ModalParecerAdicionalHistorico;