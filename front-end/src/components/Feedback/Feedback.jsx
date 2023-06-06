import React, { useState, useContext } from "react";
import { Snackbar, Alert } from "@mui/material";

import SpeechSynthesisContext from "../../service/SpeechSynthesisContext";

/** Feedback padrão para avisos do sistema sobre processos concluídos / problemas no sistema */
const Feedback = (props) => {
  // Variáveis de estilo para o componente (definir a posição)
  const vertical = "top";
  const horizontal = "right";

  // Context para ler o texto da tela
  const { lerTexto } = useContext(SpeechSynthesisContext);

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
