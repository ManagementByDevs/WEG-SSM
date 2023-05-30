import React, { useContext, useEffect, useRef, useState } from "react";

import { Box, Typography, Tooltip } from "@mui/material";

import DeleteOutlineOutlinedIcon from "@mui/icons-material/DeleteOutlineOutlined";
import MicNoneOutlinedIcon from "@mui/icons-material/MicNoneOutlined";
import MicOutlinedIcon from "@mui/icons-material/MicOutlined";

import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";

const ResponsavelNegocio = (props) => {
  // Context para alterar o idioma
  const { texts, setTexts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // // ********************************************** Gravar audio **********************************************

  const recognitionRef = useRef(null);

  const [escutar, setEscutar] = useState(false);

  const [localClicado, setLocalClicado] = useState("");

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
      };

      recognition.onresult = (event) => {
        const transcript =
          event.results[event.results.length - 1][0].transcript;
        setPalavrasJuntas((palavrasJuntas) => palavrasJuntas + transcript);

        switch (localClicado) {
          case "nome":
            props.setDados({ ...props.dados, nome: palavrasJuntas });
            break;
          case "area":
            props.setDados({ ...props.dados, area: palavrasJuntas });
            break;
          default:
            break;
        }
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
       
    }
  };

  const startRecognition = (ondeClicou) => {
    setEscutar(!escutar);
    setLocalClicado(ondeClicou);
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
    <Box className="flex w-full mt-5 items-end">
      <Box className="flex flex-col">
        <Box className="flex mb-2">
          <Typography
            sx={{ fontSize: FontConfig.big, fontWeight: "600" }}
            onClick={() => {
              lerTexto(texts.responsavelNegocio.responsavelDoNegocio);
            }}
          >
            {texts.responsavelNegocio.responsavelDoNegocio}:
          </Typography>
          <Typography
            sx={{
              fontSize: FontConfig.big,
              color: "red",
              marginLeft: "5px",
            }}
          >
            *
          </Typography>
        </Box>
        <Box sx={{ width: "30rem" }}>
          <Box
            className="flex items-center justify-between border-solid border px-1 py-1.5 drop-shadow-sm rounded border-l-4"
            sx={{
              width: "100%;",
              height: "30px",
              backgroundColor: "background.default",
              borderLeftColor: "primary.main",
            }}
          >
            <Box
              fontSize={FontConfig.medium}
              color="text.primary"
              className="flex outline-none"
              sx={{
                width: "95%;",
                backgroundColor: "transparent",
              }}
              component="input"
              placeholder={texts.responsavelNegocio.insiraResponsavelDoNegocio}
              value={props.dados.nome}
              onChange={(e) =>
                props.setDados({ ...props.dados, nome: e.target.value })
              }
            />
            <Tooltip
              className="hover:cursor-pointer"
              title={texts.homeGerencia.gravarAudio}
              onClick={() => {
                startRecognition("nome");
              }}
            >
              {escutar && localClicado == "nome" ? (
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
        </Box>
      </Box>
      <Box className="flex flex-col ml-10">
        <Box className="flex mb-2">
          <Typography
            sx={{ fontSize: FontConfig.big, fontWeight: "600" }}
            onClick={() => {
              lerTexto(texts.responsavelNegocio.area);
            }}
          >
            {texts.responsavelNegocio.area}:
          </Typography>
          <Typography
            sx={{
              fontSize: FontConfig.big,
              color: "red",
              marginLeft: "5px",
            }}
          >
            *
          </Typography>
        </Box>
        <Box sx={{ width: "20rem" }}>
          <Box
            className="flex items-center justify-between border-solid border px-1 py-1.5 drop-shadow-sm rounded border-l-4"
            sx={{
              width: "100%;",
              height: "30px",
              backgroundColor: "background.default",
              borderLeftColor: "primary.main",
            }}
          >
            <Box
              fontSize={FontConfig.medium}
              color="text.primary"
              className="flex outline-none"
              sx={{
                width: "95%;",
                backgroundColor: "transparent",
              }}
              component="input"
              placeholder={texts.responsavelNegocio.insiraAreaDoResponsavel}
              value={props.dados.area}
              onChange={(e) =>
                props.setDados({ ...props.dados, area: e.target.value })
              }
            />
            <Tooltip
              className="hover:cursor-pointer"
              title={texts.homeGerencia.gravarAudio}
              onClick={() => {
                startRecognition("area");
              }}
            >
              {escutar && localClicado == "area" ? (
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
        </Box>
      </Box>
      {props.index !== 0 && (
        <Box className="flex ml-3">
          <DeleteOutlineOutlinedIcon
            className="delay-120 hover:scale-110 duration-300"
            fontSize="large"
            sx={{ color: "icon.main", cursor: "pointer" }}
            onClick={() => props.deleteResponsavel(props.index)}
          />
        </Box>
      )}
    </Box>
  );
};

export default ResponsavelNegocio;
