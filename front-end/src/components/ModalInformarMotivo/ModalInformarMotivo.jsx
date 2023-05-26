import React, { useState, useContext } from "react";

import {
  Modal,
  Typography,
  Box,
  Fade,
  TextareaAutosize,
  Button,
} from "@mui/material";

import Backdrop from "@mui/material/Backdrop";

import CloseIcon from "@mui/icons-material/Close";
import MicNoneOutlinedIcon from "@mui/icons-material/MicNoneOutlined";
import MicOutlinedIcon from "@mui/icons-material/MicOutlined";

import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";

// Modal para informar o motivo da recusa ou devolvimento da demanda
const ModalInformarMotivo = (props) => {
  // Context para alterar a linguagem do sistema
  const { texts, setTexts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // // ********************************************** Gravar audio **********************************************

  const recognitionRef = useRef(null);

  const [escutar, setEscutar] = useState(false);

  const ouvirAudio = () => {
    // Verifica se a API é suportada pelo navegador
    if ("webkitSpeechRecognition" in window) {
      const recognition = new window.webkitSpeechRecognition();
      recognition.continuous = true;
      switch (texts.linguagem) {
        case "pt":
          recognition.lang = "pt-BR";
          break;
        case "en":
          recognition.lang = "en-US";
          break;
        case "es":
          recognition.lang = "es-ES";
          break;
        case "ch":
          recognition.lang = "cmn-Hans-CN";
          break;
        default:
          recognition.lang = "pt-BR";
          break;
      }

      recognition.onstart = () => {
        // console.log("Reconhecimento de fala iniciado. Fale algo...");
      };

      recognition.onresult = (event) => {
        const transcript =
          event.results[event.results.length - 1][0].transcript;
        // setValorPesquisa(transcript);
      };

      recognition.onerror = (event) => {
        props.setFeedbackErroReconhecimentoVoz(true);
        setEscutar(false);
      };

      recognitionRef.current = recognition;
      recognition.start();
    } else {
      props.setFeedbackErroNavegadorIncompativel(true);
      setEscutar(false);
    }
  };

  const stopRecognition = () => {
    if (recognitionRef.current) {
      recognitionRef.current.stop();
      // console.log("Reconhecimento de fala interrompido.");
    }
  };

  const startRecognition = () => {
    setEscutar(!escutar);
  };

  useEffect(() => {
    if (escutar) {
      ouvirAudio();
    } else {
      stopRecognition();
    }
  }, [escutar]);

  // // ********************************************** Fim Gravar audio **********************************************

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
          sx={{
            transform: "translate(-50%, -50%)",
            width: 480,
            height: 350,
            borderTop: "10px solid #00579D",
            boxShadow: 24,
            p: 4,
            display: "flex",
            justifyContent: "space-between",
            alignItems: "center",
            flexDirection: "column",
            top: "50%",
            left: "50%",
            position: "absolute",
          }}
          bgcolor={"background.paper"}
        >
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
          <Typography
            fontWeight={650}
            color={"primary.main"}
            fontSize={FontConfig.smallTitle}
            onClick={() => {
              lerTexto(texts.modalInformarMotivo.informarMotivo);
            }}
          >
            {texts.modalInformarMotivo.informarMotivo}
          </Typography>
          <Box
            className="flex w-full h-full justify-between items-center"
            sx={{
              marginTop: "5%",
              textAlign: "justify",
              border: "1px solid",
              borderColor: "divider.main",
              borderRadius: "5px",
              p: 2,
              overflow: "auto",
            }}
          >
            {/* text area para informar o motivo */}
            <TextareaAutosize
              placeholder={texts.modalInformarMotivo.informeMotivo}
              style={{
                width: "100%",
                height: "100%",
                overflow: "auto",
                resize: "none",
                textAlign: "justify",
                padding: "3%",
                background: "transparent",
              }}
            />
            <Tooltip
              className="hover:cursor-pointer"
              title={texts.homeGerencia.gravarAudio}
              onClick={() => {
                startRecognition();
              }}
            >
              {escutar ? (
                <MicOutlinedIcon
                  sx={{ color: "primary.main", fontSize: "1.3rem" }}
                />
              ) : (
                <MicNoneOutlinedIcon
                  sx={{ color: "text.secondary", fontSize: "1.3rem" }}
                />
              )}
            </Tooltip>
          </Box>
          <Button
            onClick={() => {
              if (!props.lendo) {
                props.handleClose();
              } else {
                lerTexto(texts.modalInformarMotivo.confirmar);
              }
            }}
            variant="contained"
            disableElevation
            color="primary"
            sx={{
              marginTop: "2%",
              width: "8rem",
              height: "3rem",
              fontSize: FontConfig.normal,
            }}
          >
            {texts.modalInformarMotivo.confirmar}
          </Button>
        </Box>
      </Fade>
    </Modal>
  );
};

export default ModalInformarMotivo;
