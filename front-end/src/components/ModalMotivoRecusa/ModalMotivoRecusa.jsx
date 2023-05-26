import React, { useContext, useEffect } from "react";

import { Modal, Typography, Box, Fade } from "@mui/material";

import CloseIcon from "@mui/icons-material/Close";

import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";

/** Modal para ver o motivo da recusa de demanda para o solicitante */
const ModalMotivoRecusa = (props) => {
  // Context para alterar a linguagem do sistema
  const { texts, setTexts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // Função que irá setar o texto que será "lido" pela a API
  const lerTexto = (texto) => {
    if (props.lendo) {
      props.setTexto(texto);
    }
  };

  // Função que irá "ouvir" o texto que será "lido" pela a API
  useEffect(() => {
    const synthesis = window.speechSynthesis;
    const utterance = new SpeechSynthesisUtterance(props.texto);

    const finalizarLeitura = () => {
      if ("speechSynthesis" in window) {
        synthesis.cancel();
      }
    };

    if (props.lendo && props.texto !== "") {
      if ("speechSynthesis" in window) {
        synthesis.speak(utterance);
      }
    } else if (!props.lendo) {
      finalizarLeitura();
    }

    return () => {
      finalizarLeitura();
    };
  }, [props.texto, props.lendo]);

  return (
    <Modal
      open={props.open}
      onClose={() => {
        props.setOpen(false);
      }}
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
            onClick={() => {
              props.setOpen(false);
            }}
            sx={{
              position: "absolute",
              left: "93%",
              top: "3%",
              cursor: "pointer",
            }}
          />
          <Typography fontSize={FontConfig.veryBig}
            onClick={() => {
              lerTexto(texts.modalMotivoRecusa.motivoDaRecusa);
            }}
          >
            {texts.modalMotivoRecusa.motivoDaRecusa}
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
            {/* Motivo da recusa */}
            <Typography fontSize={FontConfig.normal}
              onClick={() => {
                lerTexto(props.motivoRecusa);
              }}
            >
              {props.motivoRecusa}
            </Typography>
          </Box>
        </Box>
      </Fade>
    </Modal>
  );
};

export default ModalMotivoRecusa;