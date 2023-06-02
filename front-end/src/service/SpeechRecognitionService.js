import React, {
  createContext,
  useRef,
  useState,
  useEffect,
  useContext,
} from "react";

import Feedback from "../components/Feedback/Feedback";

import TextLanguageContext from "./TextLanguageContext";

export const SpeechRecognitionContext = createContext({
  ouvirAudio: () => {},
  stopRecognition: () => {},
  startRecognition: () => {},
  escutar: false,
  localClique: "",
  palavrasJuntas: "",
});

export const SpeechRecognitionService = ({ children }) => {
  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  // // ********************************************** Gravar audio **********************************************
  const [
    feedbackErroNavegadorIncompativel,
    setFeedbackErroNavegadorIncompativel,
  ] = useState(false);
  const [feedbackErroReconhecimentoVoz, setFeedbackErroReconhecimentoVoz] =
    useState(false);

  const recognitionRef = useRef(null);

  const [escutar, setEscutar] = useState(false);

  const [localClique, setLocalClique] = useState("");

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

      recognition.onstart = () => {};

      recognition.onresult = (event) => {
        const transcript =
          event.results[event.results.length - 1][0].transcript;
        setPalavrasJuntas((palavrasJuntas) => palavrasJuntas + transcript);
      };

      recognition.onerror = (event) => {
        setFeedbackErroReconhecimentoVoz(true);
        setEscutar(false);
      };

      recognitionRef.current = recognition;
      recognition.start();
    } else {
      setFeedbackErroNavegadorIncompativel(true);
      setEscutar(false);
    }
  };

  const stopRecognition = () => {
    if (recognitionRef.current) {
      recognitionRef.current.stop();
    }
  };

  const startRecognition = (ondeClicou) => {
    setLocalClique(ondeClicou);
    setEscutar(!escutar);
  };

  useEffect(() => {
    if (escutar) {
      ouvirAudio();
    } else {
      setLocalClique("");
      stopRecognition();
    }
  }, [escutar]);

  useEffect(() => {
    setPalavrasJuntas("");
  }, [localClique]);

  // // ********************************************** Fim Gravar audio **********************************************

  return (
    <SpeechRecognitionContext.Provider
      value={{
        ouvirAudio,
        stopRecognition,
        startRecognition,
        escutar,
        localClique,
        palavrasJuntas,
      }}
    >
      {/* Feedback Erro reconhecimento de voz */}
      <Feedback
        open={feedbackErroReconhecimentoVoz}
        handleClose={() => {
          setFeedbackErroReconhecimentoVoz(false);
        }}
        status={"erro"}
        mensagem={texts.homeGerencia.feedback.feedback12}
      />
      {/* Feedback Não navegador incompativel */}
      <Feedback
        open={feedbackErroNavegadorIncompativel}
        handleClose={() => {
          setFeedbackErroNavegadorIncompativel(false);
        }}
        status={"erro"}
        mensagem={texts.homeGerencia.feedback.feedback13}
      />
      {children}
    </SpeechRecognitionContext.Provider>
  );
};
