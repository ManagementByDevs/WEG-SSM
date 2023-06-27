import React, { useContext, } from "react";

import { Modal, Typography, Box, Divider } from "@mui/material";

import Fade from "@mui/material/Fade";
import CloseIcon from "@mui/icons-material/Close";

import ContainerHistorico from "../ContainerHistorico/ContainerHistorico";

import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";
import SpeechSynthesisContext from "../../service/SpeechSynthesisContext";

/** Modal de histórico acessado na DemandaGerencia, que mostra o histórico de edição de uma demanda
 * Recebe a lista de históricos através do props (props.historico)
 */
const ModalHistoricoDemanda = (props) => {

  // Context para alterar a linguagem do sistema
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  /** Context para ler o texto da tela */
  const { lerTexto } = useContext(SpeechSynthesisContext);

  return (
    <Modal
      open={props.open}
      onClose={() => { props.setOpen(false); }}
      closeAfterTransition
    >
      <Fade in={props.open}>
        <Box
          className="absolute flex justify-evenly items-center flex-col"
          sx={{
            top: "50%",
            left: "50%",
            transform: "translate(-50%, -50%)",
            width: 700,
            height: 480,
            bgcolor: "background.paper",
            borderRadius: "5px",
            borderTop: "10px solid #00579D",
            boxShadow: 24,
            p: 4,
          }}
        >
          {/* Ícone de fechar modal */}
          <CloseIcon
            onClick={() => { props.setOpen(false); }}
            sx={{
              position: "absolute",
              left: "90%",
              top: "3%",
              cursor: "pointer",
            }}
          />

          {/* Título do modal */}
          <Typography
            fontWeight={650}
            color={"primary.main"}
            fontSize={FontConfig.smallTitle}
            onClick={() => {
              lerTexto(texts.modalHistoricoDemanda.historico);
            }}
          >
            {texts.modalHistoricoDemanda.historico}
          </Typography>
          <Divider sx={{ width: "60%", borderColor: "tertiary.main" }} />
          <Box className="flex flex-col items-center overflow-auto w-full h-4/5">
            {/* Lista de históricos sendo usada */}
            {props?.historico?.map((e) => (
              <ContainerHistorico key={e.id} historico={e} />
            ))}
          </Box>
        </Box>
      </Fade>
    </Modal>
  );
};

export default ModalHistoricoDemanda;