import React, { useEffect, useContext } from "react";

import { Box, Typography, Tooltip } from "@mui/material";

import MicNoneOutlinedIcon from "@mui/icons-material/MicNoneOutlined";
import MicOutlinedIcon from "@mui/icons-material/MicOutlined";

import TextLanguageContext from "../../service/TextLanguageContext";
import SpeechSynthesisContext from "../../service/SpeechSynthesisContext";
import { SpeechRecognitionContext } from "../../service/SpeechRecognitionService";

/** Input padrão usado no sistema, com label acima */
const InputComLabel = (props) => {
  // Contexto para trocar a linguagem
  const { texts } = useContext(TextLanguageContext);

  /** Context para ler o texto da tela */
  const { lerTexto } = useContext(SpeechSynthesisContext);

  /** Context para obter a função de leitura de texto */
  const { startRecognition, escutar, palavrasJuntas, localClique } = useContext(
    SpeechRecognitionContext
  );

  /** Função para salvar o valor do props recebido para o input (mudando também o valor do próprio input) */
  const save = (e) => {
    props.saveInputValue(e.target.value);
  };

  /** useEffect utilizado para ouvir áudio */
  useEffect(() => {
    if (escutar && localClique == props.label) {
      props.saveInputValue(palavrasJuntas);
    }
  }, [palavrasJuntas]);

  useEffect(() => {
    if (props.smartRecomendation) {
    }
  }, []);

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
            height: "40px",
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
              startRecognition(props.label);
            }}
          >
            {escutar && localClique == props.label ? (
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
              startRecognition(props.label);
            }}
          >
            {escutar && localClique == props.label ? (
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
      )}
    </Box>
  );
};

export default InputComLabel;
