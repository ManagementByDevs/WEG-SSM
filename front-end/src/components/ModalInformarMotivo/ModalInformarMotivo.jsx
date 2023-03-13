import React, { useState, useContext } from "react";

import {
  Modal,
  Typography,
  Box,
  Fade,
  TextareaAutosize,
  Button,
} from "@mui/material";

import Backdrop from "@mui/material/Backdrop";
import CloseIcon from "@mui/icons-material/Close";

import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";

const ModalInformarMotivo = (props) => {
  // Context para alterar a linguagem do sistema
  const { texts, setTexts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

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
          >
            {texts.modalInformarMotivo.informarMotivo}
          </Typography>
          <Box
            sx={{
              marginTop: "5%",
              display: "flex",
              textAlign: "justify",
              border: "1px solid",
              borderColor: "divider.main",
              borderRadius: "5px",
              p: 2,
              width: "100%",
              height: "100%",
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
          </Box>
          <Button
            onClick={handleClose}
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
