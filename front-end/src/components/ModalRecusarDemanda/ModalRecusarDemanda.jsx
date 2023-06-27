import React, { useContext, useEffect } from "react";

import { Modal, Typography, Box, Fade, Button, Tooltip, TextField, } from "@mui/material";

import CloseIcon from "@mui/icons-material/Close";
import MicNoneOutlinedIcon from "@mui/icons-material/MicNoneOutlined";
import MicOutlinedIcon from "@mui/icons-material/MicOutlined";

import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";
import SpeechSynthesisContext from "../../service/SpeechSynthesisContext";
import { SpeechRecognitionContext } from "../../service/SpeechRecognitionService";

/** Modal de recusar demanda na etapa de aprovação inicial (analista e gerente) */
const ModalRecusarDemanda = (props) => {

  // Context para alterar a linguagem do sistema
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  /** Context para ler o texto da tela */
  const { lerTexto, lendoTexto } = useContext(SpeechSynthesisContext);

  /** Context para obter a função de leitura de texto */
  const { startRecognition, escutar, palavrasJuntas } = useContext(
    SpeechRecognitionContext
  );

  // Função para alterar o texto do motivo
  const alterarTexto = (e) => {
    props.setMotivo(e.target.value);
  };

  // // ********************************************** Gravar audio **********************************************

  /** useEffect utilizado par gravação de áudio */
  useEffect(() => {
    if (palavrasJuntas) props.setMotivo(palavrasJuntas);
  }, [palavrasJuntas]);

  // // ********************************************** Fim Gravar audio **********************************************

  return (
    <Modal
      open={props.open}
      onClose={() => { props.setOpen(false); }}
      closeAfterTransition
    >
      <Fade in={props.open}>
        <Box
          id="teste"
          className="absolute top-2/4 left-2/4 flex flex-col justify-between items-center"
          sx={{
            transform: "translate(-50%, -50%)",
            bgcolor: "background.paper",
            borderRadius: "5px",
            borderTop: "10px solid #00579D",
            boxShadow: 24,
            p: 4,
            width: 480,
            height: 350,
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
          <Typography
            fontSize={FontConfig.veryBig}
            onClick={() => {
              lerTexto(texts.modalRecusarDemanda.motivoDaRecusa);
            }}
          >
            {texts.modalRecusarDemanda.motivoDaRecusa}
          </Typography>

          {/* Textarea para escrita do motivo da recusa */}
          <Box className="drop-shadow-sm rounded text-center text-justify max-h-96 overflow-y-auto w-full px-2 mt-2">
            <TextField
              multiline
              minRows={6}
              variant="outlined"
              fullWidth
              value={props.motivo}
              fontSize={FontConfig.medium}
              onChange={(e) => {
                alterarTexto(e, "problema");
              }}
              placeholder={texts.modalRecusarDemanda.informeMotivo}
            />
            <Tooltip
              className="absolute cursor-pointer right-3 top-2 "
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
          <Box className="flex justify-end" sx={{ width: "90%" }}>
            <Button
              sx={{
                backgroundColor: "primary.main",
                color: "text.white",
                fontSize: FontConfig.default,
                marginTop: "2%",
              }}
              variant="contained"
              onClick={() => {
                if (!lendoTexto) {
                  props.confirmRecusarDemanda();
                } else {
                  lerTexto(texts.modalRecusarDemanda.enviar);
                }
              }}
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