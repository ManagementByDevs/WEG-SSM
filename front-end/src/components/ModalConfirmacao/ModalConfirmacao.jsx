import React, { useContext, useEffect } from "react";

import { Modal, Typography, Box, Button } from "@mui/material";

import Fade from "@mui/material/Fade";
import ErrorOutlineIcon from "@mui/icons-material/ErrorOutline";

import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";

/** Modal padrão usado para confirmação de ações (ex: criação de demanda, aprovação de demanda) */
const ModalConfirmacao = (props) => {
  // Context para alterar a linguagem do sistema
  const { texts, setTexts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  /** Função para retornar um tipo de mensagem no modal */
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
      case "abrirChat":
        return texts.modalConfirmacao.mensagensModal.abrirChat;
      case "tirarPropostaDePauta":
        return texts.modalConfirmacao.mensagensModal.tirarPropostaDePauta;
      case "alterarStatusProposta":
        return texts.modalConfirmacao.mensagensModal.alterarStatusProposta;
      case "confirmEditar":
        return texts.modalConfirmacao.mensagensModal.confirmEditar;
    }
  };

  /** Função para retornar um tipo de mensagem no botão */
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

  // Função que irá setar o texto que será "lido" pela a API
  const lerTexto = (texto) => {
    if (props.lendo) {
      props.setTexto(texto);
    }
  };

  // Função que irá "ouvir" o texto que será "lido" pela a API
  useEffect(() => {
    const synthesis = window.speechSynthesis;
    const utterance = new SpeechSynthesisUtterance(props.texto);
    if (props.lendo && props.texto != "" && countFala == 0) {
      if ("speechSynthesis" in window) {
        synthesis.speak(utterance);
      }
    } else if (!props.lendo) {
      if ("speechSynthesis" in window) {
        synthesis.cancel();
      }
    }
  }, [props.texto, props.lendo]);

  return (
    <Modal
      open={props.open}
      onClose={() => {
        props.setOpen(false);
      }}
      closeAfterTransition
    >
      <Fade in={props.open}>
        <Box
          className="absolute top-2/4 left-2/4 flex flex-col justify-between items-center"
          sx={{
            transform: "translate(-50%, -50%)",
            width: 450,
            bgcolor: "background.paper",
            borderRadius: "5px",
            borderTop: "10px solid #00579D",
            boxShadow: 24,
            p: 2,
          }}
        >
          <ErrorOutlineIcon sx={{ fontSize: "100px", color: "primary.main" }} />
          <Typography
            fontSize={FontConfig.veryBig}
            className="text-center"
            sx={{ mt: 2 }}
            onClick={() => {
              lerTexto(mensagemModal(props.textoModal));
            }}
          >
            {mensagemModal(props.textoModal)}
          </Typography>
          <Box className="flex justify-center items-center mt-5">
            {/* Botão de cancelar */}
            <Button
              onClick={() => {
                if (!props.lendo) {
                  props.setOpen(false);
                  props.onCancelClick(true);
                } else {
                  lerTexto(texts.modalConfirmacao.cancelar);
                }
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

            {/* Botão de confirmação */}
            <Button
              onClick={() => {
                if (!props.lendo) {
                  props.setOpen(false);
                  props.onConfirmClick(false);
                } else {
                  lerTexto(mensagemBotao(props.textoBotao));
                }
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
