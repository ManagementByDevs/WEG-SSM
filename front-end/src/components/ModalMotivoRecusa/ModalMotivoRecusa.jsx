import React, { useState, useContext } from "react";

import { Modal, Typography, Box, Fade } from "@mui/material";

import Backdrop from "@mui/material/Backdrop";
import CloseIcon from "@mui/icons-material/Close";

import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";

const ModalMotivoRecusa = (props) => {
  // Context para alterar a linguagem do sistema
  const { texts, setTexts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // props para abrir o modal através de outra tela
  let open = false;
  open = props.open;
  const setOpen = props.setOpen;

  // useState para abrir e fechar o modal
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  return (
    <Modal open={open} onClose={handleClose} closeAfterTransition>
      <Fade in={open}>
        <Box sx={{ transform: 'translate(-50%, -50%)', width: 480, height: 350, borderTop: '10px solid #00579D', boxShadow: 24, p: 4, display: 'flex', justifyContent: 'space-between', alignItems: 'center', flexDirection: 'column', top: '50%', left: '50%', position: 'absolute' }} bgcolor={'background.paper'}>
          <CloseIcon
            onClick={handleClose}
            sx={{
              position: "absolute",
              left: "93%",
              top: "3%",
              cursor: "pointer",
            }}
          />
          <Typography fontSize={FontConfig.veryBig}>
            {texts.modalMotivoRecusa.motivoDaRecusa}
          </Typography>
          <Box sx={{ marginTop: '5%', display: 'flex', textAlign: 'justify', border: '1px solid', borderColor: 'divider.main', borderRadius: '5px', p: 2, width: '100%', height: '100%', overflow: 'auto' }}>
            <Typography fontSize={FontConfig.normal}>
              {props.motivoRecusa}
            </Typography>
          </Box>
        </Box>
      </Fade>
    </Modal>
  );
};

export default ModalMotivoRecusa;
