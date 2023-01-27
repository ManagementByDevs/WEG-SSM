import React, {useContext} from "react";
import { Snackbar, Alert } from "@mui/material";

import FontContext from "../../service/FontContext";

const Feedback = (props) => {
  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);
  
  // Como chamar:
  // <Feedback open={true} handleClose={funcao para deixar o open false} status={"o status que quer: sucesso, erro, aviso, info"} mensagem={"mensagem que quer que apareça"}/>

  // Variáveis de estilo para o componente (para definir a posição)
  const vertical = "top";
  const horizontal = "right";

  // Váriável que contém uma mensagem para o feedback
  const mensagem = getMensagem();

  // Função para retornar uma mensagem para o feedback
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
