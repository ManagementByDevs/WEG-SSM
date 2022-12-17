import React, { useState } from "react";

import { Modal, Typography, Box, Button } from "@mui/material";

import Backdrop from "@mui/material/Backdrop";
import Fade from "@mui/material/Fade";
import FontConfig from "../../service/FontConfig";

import ErrorOutlineIcon from "@mui/icons-material/ErrorOutline";

const ModalConfirmacao = (props) => {
  // Como chamar:
  // <ModalConfirmacao open={boolean} setOpen={function} textoModal={"descartarRascunho"} onConfirmClick={'funcao executada ao confirmar'} onCancelClick={'funcao executada ao cancelar'} textoBotao={"sim"}/>

  // Variáveis de estilo para o componente
  const styleModal = {
    position: "absolute",
    top: "50%",
    left: "50%",
    transform: "translate(-50%, -50%)",
    width: 450,
    height: 300,
    bgcolor: "background.paper",
    borderRadius: "5px",
    borderTop: "10px solid #00579D",
    boxShadow: 24,
    p: 4,
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    flexDirection: "column",
  };

  const styleBotoes = {
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    marginTop: "6%",
  };

  // Funções para retornar um tipo de mensagem para o modal e o botão

  const mensagemModal = (tipoMensagem) => {
    switch (tipoMensagem) {
      case "descartarRascunho":
        return "Deseja descartar o escopo?";
      case "sairCriacao":
        return "Deseja sair da criação da demanda?";
      case "enviarDemanda":
        return "Deseja criar a demanda?";
      case "sairSemSalvar":
        return "Deseja sair sem salvar?";
      case "descartarProposta":
        return "Deseja descartar a proposta?";
      case "cancelarEdicao":
        return "Deseja descartar as alterações?";
    }
  };

  const mensagemBotao = (mensagemBotao) => {
    switch (mensagemBotao) {
      case "sim":
        return "Sim";
      case "enviar":
        return "Enviar";
      case "criar":
        return "Criar";
    }
  };

  // useState para abrir e fechar o modal

  // const [open, setOpen] = useState(props.open);

  let open = false;
  open = props.open;
  const setOpen = props.setOpen;

  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  return (
    <Modal
      open={props.open}
      onClose={handleClose}
      closeAfterTransition
      BackdropComponent={Backdrop}
      BackdropProps={{
        timeout: 500,
      }}
    >
      <Fade in={props.open}>
        <Box sx={styleModal}>
          <ErrorOutlineIcon sx={{ fontSize: "100px", color: "primary.main" }} />
          <Typography fontSize={FontConfig.veryBig} sx={{ mt: 2 }}>
            {mensagemModal(props.textoModal)}
          </Typography>
          <Box sx={styleBotoes}>
            <Button
              onClick={() => {
                handleClose();
                props.onCancelClick(true);
              }}
              variant="container"
              disableElevation
              color="tertiary"
              sx={{
                border: "solid 1px",
                borderColor: "tertiary.main",
                margin: "10px",
                width: "7.5rem",
                fontSize: FontConfig.big,
              }}
            >
              Cancelar
            </Button>
            <Button
              onClick={() => {
                handleClose();
                props.onConfirmClick(false);
              }}
              variant="contained"
              disableElevation
              color="primary"
              sx={{ margin: "10px", width: "7.5rem", fontSize: FontConfig.big }}
            >
              {mensagemBotao(props.textoBotao)}
            </Button>
          </Box>
        </Box>
      </Fade>
    </Modal>
  );
};

export default ModalConfirmacao;
