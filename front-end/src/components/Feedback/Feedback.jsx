import React, { useContext } from "react";
import { Snackbar, Alert } from "@mui/material";

import SpeechSynthesisContext from "../../service/SpeechSynthesisContext";

/** Feedback padrão para avisos do sistema sobre processos concluídos / problemas no sistema */
const Feedback = (props) => {

  // Variável para definir a posição vertical do feedback
  const vertical = "top";

  // Variável para definir a posição horizontal do feedback
  const horizontal = "right";

  // Context para ler o texto da tela
  const { lerTexto } = useContext(SpeechSynthesisContext);

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
      {/* Gerado o feedabck */}
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