import React, { useContext } from "react";

import { Modal, Typography, Box, Fade, TextareaAutosize, Button } from '@mui/material';

import CloseIcon from "@mui/icons-material/Close";

import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";

/** Modal de recusar demanda na etapa de aprovação inicial (analista e gerente) */
const ModalRecusarDemanda = (props) => {
  // Context para alterar a linguagem do sistema
  const { texts, setTexts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // Função para alterar o texto do motivo
  const alterarTexto = (e) => {
    props.setMotivo(e.target.value);
  };

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
            width: 480,
            height: 350,
            bgcolor: "background.paper",
            borderRadius: "5px",
            borderTop: "10px solid #00579D",
            boxShadow: 24,
            p: 4,
          }}
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
          <Typography fontSize={FontConfig.veryBig}>
            {texts.modalRecusarDemanda.motivoDaRecusa}
          </Typography>

          {/* Textarea para escrita do motivo da recusa */}
          <TextareaAutosize
            style={{
              width: "90%",
              height: "70%",
              resize: "none",
              overflow: "auto",
              marginTop: "4%",
              background: "transparent",
            }}
            value={props.motivo}
            fontSize={FontConfig.medium}
            onChange={(e) => {
              alterarTexto(e, "problema");
            }}
            className="flex outline-none border-solid border px-1 py-1.5 drop-shadow-sm rounded text-center text-justify"
            placeholder={texts.modalRecusarDemanda.informeMotivo}
          />
          <Box className="flex justify-end" sx={{ width: "90%" }}>
            <Button
              sx={{
                backgroundColor: "primary.main",
                color: "text.white",
                fontSize: FontConfig.default,
                marginTop: "2%",
              }}
              variant="contained"
              onClick={props.confirmRecusarDemanda}
            >
              {texts.modalRecusarDemanda.enviar}
            </Button>
          </Box>
        </Box>
      </Fade>
    </Modal>
  );
};

export default ModalRecusarDemanda;
