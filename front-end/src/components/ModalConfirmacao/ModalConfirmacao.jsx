import React, { useState, useContext } from "react";

import { Modal, Typography, Box, Button } from "@mui/material";

import Backdrop from "@mui/material/Backdrop";
import Fade from "@mui/material/Fade";

import ErrorOutlineIcon from "@mui/icons-material/ErrorOutline";

import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";

const ModalConfirmacao = (props) => {
  // Context para alterar a linguagem do sistema
  const { texts, setTexts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // Função para retornar um tipo de mensagem no modal
  const mensagemModal = (tipoMensagem) => {
    switch (tipoMensagem) {
      case "descartarRascunho":
        return texts.modalConfirmacao.mensagensModal.descartarRascunho;
      case "sairCriacao":
        return texts.modalConfirmacao.mensagensModal.sairCriacao;
      case "enviarDemanda":
        return texts.modalConfirmacao.mensagensModal.enviarDemanda;
      case "sairSemSalvar":
        return texts.modalConfirmacao.mensagensModal.sairSemSalvar;
      case "descartarProposta":
        return texts.modalConfirmacao.mensagensModal.descartarProposta;
      case "cancelarEdicao":
        return texts.modalConfirmacao.mensagensModal.cancelarEdicao;
      case "aceitarDemanda":
        return texts.modalConfirmacao.mensagensModal.aceitarDemanda;
      case "confirmarExclusao":
        return texts.modalConfirmacao.mensagensModal.confirmarExclusao;
      case "fecharChat":
        return texts.modalConfirmacao.mensagensModal.fecharChat;
    }
  };

  // Função para retornar um tipo de mensagem no botão
  const mensagemBotao = (mensagemBotao) => {
    switch (mensagemBotao) {
      case "sim":
        return texts.modalConfirmacao.mensagensBotao.sim;
      case "enviar":
        return texts.modalConfirmacao.mensagensBotao.enviar;
      case "criar":
        return texts.modalConfirmacao.mensagensBotao.criar;
      case "aceitar":
        return texts.modalConfirmacao.mensagensBotao.aceitar;
    }
  };

  return (
    <Modal
      open={props.open}
      onClose={() => { props.setOpen(false) }}
      closeAfterTransition
    >
      <Fade in={props.open}>
        <Box className="absolute top-2/4 left-2/4 flex flex-col justify-between items-center"
          sx={{ transform: "translate(-50%, -50%)", width: 450, height: 300, bgcolor: "background.paper", borderRadius: "5px", borderTop: "10px solid #00579D", boxShadow: 24, p: 4 }}>
          <ErrorOutlineIcon sx={{ fontSize: "100px", color: "primary.main" }} />
          <Typography fontSize={FontConfig.veryBig} sx={{ mt: 2 }}>
            {mensagemModal(props.textoModal)}
          </Typography>
          <Box className="flex justify-center items-center mt-5">
            <Button
              onClick={() => {
                props.setOpen(false);
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
              {texts.modalConfirmacao.cancelar}
            </Button>
            <Button
              onClick={() => {
                props.setOpen(false);
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
