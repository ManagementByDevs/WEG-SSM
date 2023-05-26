import React, {useContext, useEffect, useState} from "react";
import { Snackbar, Alert } from "@mui/material";

/** Feedback padrão para avisos do sistema sobre processos concluídos / problemas no sistema */
const Feedback = (props) => {
  // Variáveis de estilo para o componente (definir a posição)
  const vertical = "top";
  const horizontal = "right";

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
    } else if (!props.lendo) {
      if ("speechSynthesis" in window) {
        const synthesis = window.speechSynthesis;
        synthesis.cancel();
      }
    }
  }, [props.texto, props.lendo]);

  return (
    <Snackbar
      anchorOrigin={{ vertical, horizontal }}
      open={props.open}
      autoHideDuration={5000}
      onClose={props.handleClose}
    >
      {props.status === "sucesso" ? (
        <Alert
          onClose={props.handleClose}
          severity="success"
          sx={{ width: "100%" }}
          onClick={() => {
            lerTexto(props.mensagem);
          }}
        >
          {props.mensagem}
        </Alert>
      ) : props.status === "erro" ? (
        <Alert
          onClose={props.handleClose}
          severity="error"
          sx={{ width: "100%" }}
          onClick={() => {
            lerTexto(props.mensagem);
          }}
        >
          {props.mensagem}
        </Alert>
      ) : props.status === "aviso" ? (
        <Alert
          onClose={props.handleClose}
          severity="warning"
          sx={{ width: "100%" }}
          onClick={() => {
            lerTexto(props.mensagem);
          }}
        >
          {props.mensagem}
        </Alert>
      ) : props.status === "info" ? (
        <Alert
          onClose={props.handleClose}
          severity="info"
          sx={{ width: "100%" }}
          onClick={() => {
            lerTexto(props.mensagem);
          }}
        >
          {props.mensagem}
        </Alert>
      ) : null}
    </Snackbar>
  );
};

export default Feedback;
