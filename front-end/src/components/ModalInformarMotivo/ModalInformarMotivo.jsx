import React, { useState, useContext } from "react";

import {
  Modal,
  Typography,
  Box,
  Fade,
  TextareaAutosize,
  Button,
} from "@mui/material";

import CloseIcon from "@mui/icons-material/Close";
import MicNoneOutlinedIcon from "@mui/icons-material/MicNoneOutlined";
import MicOutlinedIcon from "@mui/icons-material/MicOutlined";

import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";
import SpeechSynthesisContext from "../../service/SpeechSynthesisContext";

// Modal para informar o motivo da recusa ou devolvimento da demanda
const ModalInformarMotivo = (props) => {
  // Context para alterar a linguagem do sistema
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  /** Context para ler o texto da tela */
  const { lerTexto, lendoTexto } = useContext(SpeechSynthesisContext);

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
          sx={{
            transform: "translate(-50%, -50%)",
            width: 480,
            height: 350,
            borderTop: "10px solid #00579D",
            boxShadow: 24,
            p: 4,
            display: "flex",
            justifyContent: "space-between",
            alignItems: "center",
            flexDirection: "column",
            top: "50%",
            left: "50%",
            position: "absolute",
          }}
          bgcolor={"background.paper"}
        >
          <CloseIcon
            onClick={() => {
              props.setOpen(false);
            }}
            sx={{
              position: "absolute",
              left: "93%",
              top: "3%",
              cursor: "pointer",
            }}
          />
          <Typography
            fontWeight={650}
            color={"primary.main"}
            fontSize={FontConfig.smallTitle}
            onClick={() => {
              lerTexto(texts.modalInformarMotivo.informarMotivo);
            }}
          >
            {texts.modalInformarMotivo.informarMotivo}
          </Typography>
          <Box
            className="flex w-full h-full justify-between items-center"
            sx={{
              marginTop: "5%",
              textAlign: "justify",
              border: "1px solid",
              borderColor: "divider.main",
              borderRadius: "5px",
              p: 2,
              overflow: "auto",
            }}
          >
            {/* text area para informar o motivo */}
            <TextareaAutosize
              placeholder={texts.modalInformarMotivo.informeMotivo}
              style={{
                width: "100%",
                height: "100%",
                overflow: "auto",
                resize: "none",
                textAlign: "justify",
                padding: "3%",
                background: "transparent",
              }}
            />
            <Tooltip
              className="hover:cursor-pointer"
              title={texts.homeGerencia.gravarAudio}
              onClick={() => {
                startRecognition();
              }}
            >
              {escutar ? (
                <MicOutlinedIcon
                  sx={{
                    cursor: "pointer",
                    color: "primary.main",
                    fontSize: "1.3rem",
                  }}
                />
              ) : (
                <MicNoneOutlinedIcon
                  sx={{
                    cursor: "pointer",
                    color: "text.secondary",
                    fontSize: "1.3rem",
                  }}
                />
              )}
            </Tooltip>
          </Box>
          <Button
            onClick={() => {
              if (!lendoTexto) {
                props.handleClose();
              } else {
                lerTexto(texts.modalInformarMotivo.confirmar);
              }
            }}
            variant="contained"
            disableElevation
            color="primary"
            sx={{
              marginTop: "2%",
              width: "8rem",
              height: "3rem",
              fontSize: FontConfig.normal,
            }}
          >
            {texts.modalInformarMotivo.confirmar}
          </Button>
        </Box>
      </Fade>
    </Modal>
  );
};

export default ModalInformarMotivo;
