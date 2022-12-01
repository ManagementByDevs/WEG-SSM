import React from "react";
import { Snackbar, Alert } from "@mui/material";

const Feedback = (props) => {
  const vertical = "top";
  const horizontal = "right";

  const mensagem = getMensagem();

  function getMensagem() {
    return props.mensagem;
  }

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
          {mensagem}
        </Alert>
      ) : props.status === "erro" ? (
        <Alert
          onClose={props.handleClose}
          severity="error"
          sx={{ width: "100%" }}
        >
          {mensagem}
        </Alert>
      ) : props.status === "aviso" ? (
        <Alert
          onClose={props.handleClose}
          severity="warning"
          sx={{ width: "100%" }}
        >
          {mensagem}
        </Alert>
      ) : props.status === "info" ? (
        <Alert
          onClose={props.handleClose}
          severity="info"
          sx={{ width: "100%" }}
        >
          {mensagem}
        </Alert>
      ) : null}
    </Snackbar>
  );
};

export default Feedback;
