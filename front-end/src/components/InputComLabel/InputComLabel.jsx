import React, { useState, useEffect, useContext, useRef } from "react";

import { Box, Typography, Tooltip } from "@mui/material";

import MicNoneOutlinedIcon from "@mui/icons-material/MicNoneOutlined";
import MicOutlinedIcon from "@mui/icons-material/MicOutlined";

import TextLanguageContext from "../../service/TextLanguageContext";

/** Input padrão usado no sistema, com label acima */
const InputComLabel = (props) => {
  // Contexto para trocar a linguagem
  const { texts } = useContext(TextLanguageContext);

  /** Função para salvar o valor do props recebido para o input (mudando também o valor do próprio input) */
  const save = (e) => {
    props.saveInputValue(e.target.value);
  };

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
        props.saveInputValue(transcript);
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
    <Box sx={{ width: "100%" }}>
      <Box className="flex">
        {/* Label acima do input */}
        <Typography
          fontSize={props.fontConfig}
          sx={{ fontWeight: "600", cursor: "default" }}
          gutterBottom
          onClick={() => {
            lerTexto(props.label);
          }}
        >
          {props.label}
        </Typography>
        <Typography
          fontSize={props.fontConfig}
          sx={{ fontWeight: "800", cursor: "default", margin: "0 .2% .2% .2%" }}
          className="text-red-600"
          gutterBottom
        >
          *
        </Typography>
      </Box>

      {/* Lógica usada para diferenciação entre input normal / textarea */}
      {props.component === "input" ? (
        <Box
          className="flex items-center justify-between border-solid border border-l-4 px-1 py-1.5 drop-shadow-sm rounded"
          sx={{
            width: "100%",
            backgroundColor: "background.default",
            borderLeftColor: "primary.main",
          }}
        >
          <Box
            value={props.texto}
            onChange={(e) => {
              save(e);
            }}
            fontSize={props.fontConfig}
            className="outline-none "
            sx={{
              width: "95%",
              fontWeight: "300",
            }}
            component="input"
            placeholder={props.placeholder}
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
      ) : (
        <Box
          className="flex items-center justify-between border-solid border border-l-4 px-1 py-1.5 drop-shadow-sm rounded"
          sx={{
            borderLeftColor: "primary.main",
            width: "100%;",
            backgroundColor: "background.default",
          }}
        >
          <Box
            value={props.texto}
            onChange={(e) => {
              save(e);
            }}
            fontSize={props.fontConfig}
            className="outline-none"
            sx={{
              width: "95%;",
              backgroundColor: "transparent",
              fontWeight: "300",
              resize: "none",
            }}
            component="textarea"
            placeholder={props.placeholder}
            rows={props.rows}
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
      )}
    </Box>
  );
};

export default InputComLabel;
