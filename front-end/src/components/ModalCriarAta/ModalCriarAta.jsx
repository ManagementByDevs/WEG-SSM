import React, { useState, useContext } from "react";

import { Modal, Fade, Divider, Typography, Box, Button } from "@mui/material";

import CloseIcon from "@mui/icons-material/Close";

import Feedback from "../Feedback/Feedback";
import InputComLabel from "../InputComLabel/InputComLabel";

import ColorModeContext from "../../service/TemaContext";
import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";

// Modal de adicionar uma proposta em uma pauta
const ModalCriarAta = (props) => {
  // Variável para alterar o tema
  const { mode } = useContext(ColorModeContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  // variáveis de estilo para os itens do componente
  const cssModal = {
    position: "absolute",
    top: "50%",
    left: "50%",
    transform: "translate(-50%, -50%)",
    width: 400,
    bgcolor: "background.paper",
    borderRadius: "5px",
    borderTop: "10px solid #00579D",
    boxShadow: 24,
    p: 2,
    display: "flex",
    justifyContent: "space-between",
    flexDirection: "column",
  };

  const botaoCancelar = {
    width: "7rem",
    border: "solid 1px",
    color: "tertiary.main",
    p: 1,
  };

  const botaoDesabilitado = {
    width: "7rem",
    backgroundColor: "primary.main",
    color: "white",
    border: "solid 1px #000",
    p: 1,
  };

  // props para abrir o modal através de outra tela
  let open = false;
  open = props.open;
  const setOpen = props.setOpen;

  // useState para abrir e fechar o modal
  const handleClose = () => setOpen(false);

  // Feedback para quando o usuário não preencher todos os campos obrigatórios
  const [feedbackCamposFaltantes, setFeedbackCamposFaltantes] = useState(false);

  const [numeroSequencial, setNumeroSequencial] = useState("");

  const [dataReuniao, setDataReuniao] = useState("");

  const criarAta = () => {
    if (numeroSequencial == "" || numeroSequencial == null || dataReuniao == "" || dataReuniao == null) {
      setFeedbackCamposFaltantes(true);
    } else {
      props.criarAta(numeroSequencial, dataReuniao);
    }
  };

  return (
    <>
      {/* Feedback campos faltantes */}
      <Feedback
        open={feedbackCamposFaltantes}
        handleClose={() => {
          setFeedbackCamposFaltantes(false);
        }}
        status={"erro"}
        mensagem={texts.modalCriarAta.feedback}
      />
      <Modal
        open={open}
        onClose={handleClose}
        closeAfterTransition
        sx={{ minWidth: "40rem" }}
      >
        <Fade in={open}>
          <Box sx={cssModal}>
            {/* Topo modal*/}
            <Box className="flex flex-col w-full items-center">
              <Box className="flex">
                {/* Título do modal */}
                <Typography
                  fontWeight={650}
                  fontSize={FontConfig.smallTitle}
                  color={"primary.main"}
                >
                  {texts.modalCriarAta.criacaoDaAta}
                </Typography>
                {/* Botao fechar modal */}
                <CloseIcon
                  onClick={handleClose}
                  sx={{
                    position: "absolute",
                    left: "93%",
                    top: "3%",
                    cursor: "pointer",
                  }}
                />
              </Box>
              {/* Divisor */}
              <Divider
                sx={{
                  width: "80%",
                  borderColor: "tertiary.main",
                }}
              />
            </Box>
            {/* Conteudo modal */}
            <Box className="w-full flex justify-center">
              <Box
                className="flex flex-col mt-8"
                sx={{ width: "80%", height: "10rem" }}
              >
                <InputComLabel
                  label={"Número Sequencial: "}
                  component={"input"}
                  placeholder={texts.modalCriarAta.digiteNumeroSequencial}
                  texto={numeroSequencial}
                  saveInputValue={setNumeroSequencial}
                />
                {/* input de data */}
                <Box className="mt-4">
                  <Box className="flex">
                    <Typography
                      fontSize={props.fontConfig}
                      sx={{ fontWeight: "600", cursor: "default" }}
                      gutterBottom
                    >
                      {texts.modalCriarAta.dataDaReuniao}
                    </Typography>
                    <Typography
                      fontSize={props.fontConfig}
                      sx={{
                        fontWeight: "800",
                        cursor: "default",
                        margin: "0 .2% .2% .2%",
                      }}
                      className="text-red-600"
                      gutterBottom
                    >
                      *
                    </Typography>
                  </Box>
                  <Box
                    fontSize={FontConfig.medium}
                    color="text.primary"
                    className="flex outline-none border-solid border px-1 py-1.5 drop-shadow-sm rounded border-l-4"
                    sx={{
                      width: "100%;",
                      height: "30px",
                      backgroundColor: "background.default",
                      borderLeftColor: "primary.main",
                      colorScheme: mode,
                    }}
                    component="input"
                    type="datetime-local"
                    placeholder={texts.formularioGeralProposta.digiteCodigo}
                    value={dataReuniao || ""}
                    onChange={(e) => setDataReuniao(e.target.value)}
                  />
                </Box>
              </Box>
            </Box>
            {/* Parte debaixo do modal */}
            <Box className="w-full flex justify-center">
              <Box
                sx={{
                  width: "80%",
                  display: "flex",
                  justifyContent: "space-between",
                  alignItems: "center",
                  marginTop: "3%",
                }}
              >
                <Button
                  sx={botaoCancelar}
                  disableElevation
                  onClick={handleClose}
                >
                  <Typography fontSize={FontConfig.default}>
                    {texts.modalCriarAta.botaoCancelar}
                  </Typography>
                </Button>
                <Button
                  sx={botaoDesabilitado}
                  disableElevation
                  variant="contained"
                  onClick={criarAta}
                >
                  {texts.modalCriarAta.botaoCriar}
                </Button>
              </Box>
            </Box>
          </Box>
        </Fade>
      </Modal>
    </>
  );
};

export default ModalCriarAta;