import React, { useContext, useRef, useState, useEffect } from "react";

import {
  Modal,
  Typography,
  Box,
  Fade,
  TextareaAutosize,
  Button,
  Tooltip,
  TextField,
} from "@mui/material";

import CloseIcon from "@mui/icons-material/Close";
import MicNoneOutlinedIcon from "@mui/icons-material/MicNoneOutlined";
import MicOutlinedIcon from "@mui/icons-material/MicOutlined";

import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";

/** Modal de recusar demanda na etapa de aprovação inicial (analista e gerente) */
const ModalRecusarDemanda = (props) => {
  // Context para alterar a linguagem do sistema
  const { texts, setTexts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // Função para alterar o texto do motivo
  const alterarTexto = (e) => {
    props.setMotivo(e.target.value);
  };

  // // ********************************************** Gravar audio **********************************************

  const recognitionRef = useRef(null);

  const [escutar, setEscutar] = useState(false);

  const [palavrasJuntas, setPalavrasJuntas] = useState("");

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
        setPalavrasJuntas((palavrasJuntas) => palavrasJuntas + transcript);
        
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

  useEffect(() => {
    props.setMotivo(palavrasJuntas);
  }, [palavrasJuntas]);

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
    <Modal
      open={props.open}
      onClose={() => {
        props.setOpen(false);
      }}
      closeAfterTransition
    >
      <Fade in={props.open}>
        <Box
          id="teste"
          className="absolute top-2/4 left-2/4 flex flex-col justify-between items-center"
          sx={{
            transform: "translate(-50%, -50%)",
            bgcolor: "background.paper",
            borderRadius: "5px",
            borderTop: "10px solid #00579D",
            boxShadow: 24,
            p: 4,
            width: 480,
            height: 350,
          }}
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
            fontSize={FontConfig.veryBig}
            onClick={() => {
              lerTexto(texts.modalRecusarDemanda.motivoDaRecusa);
            }}
          >
            {texts.modalRecusarDemanda.motivoDaRecusa}
          </Typography>

          {/* Textarea para escrita do motivo da recusa */}
          <Box className="drop-shadow-sm rounded text-center text-justify max-h-96 overflow-y-auto w-full px-2 mt-2">
            <TextField
              multiline
              minRows={6}
              variant="outlined"
              fullWidth
              value={props.motivo}
              fontSize={FontConfig.medium}
              onChange={(e) => {
                alterarTexto(e, "problema");
              }}
              placeholder={texts.modalRecusarDemanda.informeMotivo}
            />
            <Tooltip
              className="absolute cursor-pointer right-3 top-2 "
              title={texts.homeGerencia.gravarAudio}
              onClick={() => {
                startRecognition();
              }}
            >
              {escutar ? (
                <MicOutlinedIcon
                  sx={{
                    cursor: "pointer",
                    color: "primary.main",
                    fontSize: "1.3rem",
                  }}
                />
              ) : (
                <MicNoneOutlinedIcon
                  sx={{
                    cursor: "pointer",
                    color: "text.secondary",
                    fontSize: "1.3rem",
                  }}
                />
              )}
            </Tooltip>
          </Box>
          <Box className="flex justify-end" sx={{ width: "90%" }}>
            <Button
              sx={{
                backgroundColor: "primary.main",
                color: "text.white",
                fontSize: FontConfig.default,
                marginTop: "2%",
              }}
              variant="contained"
              onClick={() => {
                if (!props.lendo) {
                  props.confirmRecusarDemanda();
                } else {
                  lerTexto(texts.modalRecusarDemanda.enviar);
                }
              }}
            >
              {texts.modalRecusarDemanda.enviar}
            </Button>
          </Box>
        </Box>
      </Fade>
    </Modal>
  );
};

export default ModalRecusarDemanda;
