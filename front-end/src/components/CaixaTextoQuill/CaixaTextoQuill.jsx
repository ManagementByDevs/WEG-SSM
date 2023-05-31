import React, { useContext, useEffect, useRef, useState } from "react";
import ReactQuill from "react-quill";
import "react-quill/dist/quill.snow.css";

import TextLanguageContext from "../../service/TextLanguageContext";

import MicNoneOutlinedIcon from "@mui/icons-material/MicNoneOutlined";
import MicOutlinedIcon from "@mui/icons-material/MicOutlined";
import { Box, IconButton, Toolbar } from "@mui/material";

// Componente utilizado para formatação em campos de texto durante o sistema
function CaixaTextoQuill({
  texto,
  placeholder = "",
  useScroll = false,
  setScroll = false,
  useScrollEdit = false,
  onChange,
}) {
  // Contexto para trocar a linguagem
  const { texts } = useContext(TextLanguageContext);

  const quillRef = useRef();

  /** useEffect utilizado para setar as informações e permitir as edições caso necessário */
  useEffect(() => {
    if (quillRef.current) {
      const quill = quillRef.current.getEditor();

      quill.on("text-change", () => {
        const value = quillRef.current.getEditor().root.innerHTML;

        if (value !== texto && onChange) {
          onChange(value);
        }
      });
    }
  }, [quillRef, onChange, texto]);

  /** Caso não seja passado um placeholder, utiliza o padrão "Mais informações" */
  if (placeholder == null || placeholder == "") {
    placeholder = texts.detalhesProposta.maisInformacoes;
  }

  // // ********************************************** Gravar audio **********************************************

  // const [
  //   feedbackErroNavegadorIncompativel,
  //   setFeedbackErroNavegadorIncompativel,
  // ] = useState(false);
  // const [feedbackErroReconhecimentoVoz, setFeedbackErroReconhecimentoVoz] =
  //   useState(false);

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
      };

      recognition.onresult = (event) => {
        const transcript =
          event.results[event.results.length - 1][0].transcript;
        setPalavrasJuntas((palavrasJuntas) => palavrasJuntas + transcript);

      };

      recognition.onerror = (event) => {
        // props.setFeedbackErroReconhecimentoVoz(true);
        setEscutar(false);
      };

      recognitionRef.current = recognition;
      recognition.start();
    } else {
      // props.setFeedbackErroNavegadorIncompativel(true);
      setEscutar(false);
    }
  };

  useEffect(() => {
    if (palavrasJuntas) {
      onChange(palavrasJuntas);
    }
  }, [palavrasJuntas]);

  const stopRecognition = () => {
    if (recognitionRef.current) {
      recognitionRef.current.stop();
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

  {
    /* Feedback Erro reconhecimento de voz */
  }
  {
    /* <Feedback
  open={feedbackErroReconhecimentoVoz}
  handleClose={() => {
    setFeedbackErroReconhecimentoVoz(false);
  }}
  status={"erro"}
  mensagem={texts.homeGerencia.feedback.feedback12}
/> */
  }
  {
    /* Feedback Não navegador incompativel */
  }
  {
    /* <Feedback
  open={feedbackErroNavegadorIncompativel}
  handleClose={() => {
    setFeedbackErroNavegadorIncompativel(false);
  }}
  status={"erro"}
  mensagem={texts.homeGerencia.feedback.feedback13}
/> */
  }

  // // ********************************************** Fim Gravar audio **********************************************

  return (
    <Box
      className="relative w-full h-full"
    >
      <ReactQuill
        className="w-full"
        ref={quillRef}
        value={texto}
        onChange={(value) => {
          onChange(value);
        }}
        modules={{
          toolbar: [
            [{ size: [] }],
            ["bold", "italic", "underline", "strike", "blockquote"],
            [
              { list: "ordered" },
              { list: "bullet" },
              { indent: "-1" },
              { indent: "+1" },
            ],
            [{ script: "sub" }, { script: "super" }],
            ["clean"],
          ],
        }}
        placeholder={placeholder}
        readOnly={false}
        style={
          useScroll
            ? { height: "5rem", overflowY: "scroll" }
            : setScroll
              ? { height: "5rem", overflowY: "scroll" }
              : {}
        }
      />
      <Box className="absolute" sx={{ right: 6, bottom: 8 }}>
        {escutar ? (
          <MicOutlinedIcon
            sx={{
              cursor: "pointer",
              color: "primary.main",
              fontSize: "1.3rem",
            }}
            onClick={startRecognition}
          />
        ) : (
          <MicNoneOutlinedIcon
            sx={{
              cursor: "pointer",
              color: "text.secondary",
              fontSize: "1.3rem",
            }}
            onClick={startRecognition}
          />
        )}
      </Box>
    </Box>
  );
}

export default CaixaTextoQuill;
