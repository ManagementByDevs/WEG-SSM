import React from "react";
import { Snackbar, Alert } from "@mui/material";

/** Feedback padrão para avisos do sistema sobre processos concluídos / problemas no sistema */
const Feedback = (props) => {

  // Como chamar:
  // <Feedback open={true} handleClose={funcao para deixar o open false} status={"o status que quer: sucesso, erro, aviso, info"} mensagem={"mensagem que quer que apareça"}/>

  // Variáveis de estilo para o componente (definir a posição)
  const vertical = "top";
  const horizontal = "right";

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
        >
          {props.mensagem}
        </Alert>
      ) : props.status === "erro" ? (
        <Alert
          onClose={props.handleClose}
          severity="error"
          sx={{ width: "100%" }}
        >
          {props.mensagem}
        </Alert>
      ) : props.status === "aviso" ? (
        <Alert
          onClose={props.handleClose}
          severity="warning"
          sx={{ width: "100%" }}
        >
          {props.mensagem}
        </Alert>
      ) : props.status === "info" ? (
        <Alert
          onClose={props.handleClose}
          severity="info"
          sx={{ width: "100%" }}
        >
          {props.mensagem}
        </Alert>
      ) : null}
    </Snackbar>
  );
};

export default Feedback;
