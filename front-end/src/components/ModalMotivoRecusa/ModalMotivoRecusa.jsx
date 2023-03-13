import React, { useContext } from "react";

import { Modal, Typography, Box, Fade } from "@mui/material";

import CloseIcon from "@mui/icons-material/Close";

import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";

/** Modal para ver o motivo da recusa de demanda para o solicitante */
const ModalMotivoRecusa = (props) => {

  // Context para alterar a linguagem do sistema
  const { texts, setTexts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  return (
    <Modal open={props.open} onClose={() => { props.setOpen(false) }} closeAfterTransition>
      <Fade in={props.open}>
        <Box className="absolute top-2/4 left-2/4 flex flex-col justify-between items-center"
          sx={{ transform: "translate(-50%, -50%)", width: 480, height: 350, bgcolor: "background.paper", borderRadius: "5px", borderTop: "10px solid #00579D", boxShadow: 24, p: 4 }}>
          {/* Ícone de fechar o modal */}
          <CloseIcon
            onClick={() => { props.setOpen(false) }}
            sx={{ position: "absolute", left: "93%", top: "3%", cursor: "pointer" }}
          />
          <Typography fontSize={FontConfig.veryBig}>
            {texts.modalMotivoRecusa.motivoDaRecusa}
          </Typography>
          <Box className="flex text-justify border border-solid w-full h-full"
            sx={{ marginTop: "5%", borderColor: "divider.main", borderRadius: "5px", p: 2 }}
          >
            {/* Motivo da recusa */}
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