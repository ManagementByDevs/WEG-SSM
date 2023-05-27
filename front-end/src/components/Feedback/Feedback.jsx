import React, { useEffect, useState } from "react";
import { Snackbar, Alert } from "@mui/material";

/** Feedback padrão para avisos do sistema sobre processos concluídos / problemas no sistema */
const Feedback = (props) => {
  // Variáveis de estilo para o componente (definir a posição)
  const vertical = "top";
  const horizontal = "right";

  const [textoLeitura, setTextoLeitura] = useState("");

  const getStatus = () => {
    switch (props.status) {
      case "sucesso":
        return "success";
      case "erro":
        return "error";
      case "aviso":
        return "warning";
      case "info":
        return "info";
      default:
        return "info";
    }
  };

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
    <Snackbar
      anchorOrigin={{ vertical, horizontal }}
      open={props.open}
      autoHideDuration={5000}
      onClose={props.handleClose}
    >
      <Alert
        onClose={props.handleClose}
        severity={getStatus()}
        sx={{ width: "100%" }}
        onClick={() => {
          lerTexto(props.mensagem);
        }}
      >
        {props.mensagem}
      </Alert>
    </Snackbar>
  );
};

export default Feedback;
