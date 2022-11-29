import React from "react";
import { Snackbar, Alert } from "@mui/material";

const Feedback = (props) => {
  const [state, setState] = React.useState({
    open: false,
    vertical: "top",
    horizontal: "right",
  });
  const { vertical, horizontal } = state;

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
          Parabéns conseguiu filtrar!
        </Alert>
      ) : props.status === "erro" ? (
        <Alert
          onClose={props.handleClose}
          severity="error"
          sx={{ width: "100%" }}
        >
          Ops, algo deu errado!
        </Alert>
      ) : props.status === "aviso" ? (
        <Alert
          onClose={props.handleClose}
          severity="warning"
          sx={{ width: "100%" }}
        >
          Ops, algo deu errado!
        </Alert>
      ) : props.status === "info" ? (
        <Alert
          onClose={props.handleClose}
          severity="info"
          sx={{ width: "100%" }}
        >
          Talvez algo deu errado!
        </Alert>
      ) : null}
    </Snackbar>
  );
};

export default Feedback;
