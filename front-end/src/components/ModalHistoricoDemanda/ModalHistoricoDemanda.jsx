import React, { useState, useContext } from "react";
import { Modal, Typography, Box, Divider } from "@mui/material";

import Backdrop from "@mui/material/Backdrop";
import Fade from "@mui/material/Fade";
import CloseIcon from "@mui/icons-material/Close";

import ContainerHistorico from "../ContainerHistorico/ContainerHistorico";
import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";

const ModalHistoricoDemanda = (props) => {
  // Context para alterar a linguagem do sistema
  const { texts, setTexts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // variáveis para abrir o modal a partir de outra tela
  let open = false;
  open = props.open;
  const setOpen = props.setOpen;

  // useState para abrir e fechar o modal
  const handleClose = () => setOpen(false);

  return (
    <Modal open={open} onClose={handleClose} closeAfterTransition>
      <Fade in={open}>
        <Box
          className="absolute flex justify-evenly items-center flex-col"
          sx={{ top: "50%", left: "50%", transform: "translate(-50%, -50%)", width: 550, height: 480, bgcolor: "background.paper", borderRadius: "5px", borderTop: "10px solid #00579D", boxShadow: 24, p: 4 }}
        >
          <CloseIcon
            onClick={handleClose}
            sx={{
              position: "absolute",
              left: "90%",
              top: "3%",
              cursor: "pointer",
            }}
          />
          <Typography
            fontWeight={650}
            color={"primary.main"}
            fontSize={FontConfig.smallTitle}
          >
            {texts.modalHistoricoDemanda.historico}
          </Typography>
          <Divider sx={{ width: "60%", borderColor: "tertiary.main" }} />
          <Box className="flex items-center flex-col overflow-auto w-full h-4/5">
            {props?.historico?.map((e) => (
              <ContainerHistorico
                key={e.id}
                historico={e}
              />
            ))}
          </Box>
        </Box>
      </Fade>
    </Modal>
  );
};

export default ModalHistoricoDemanda;