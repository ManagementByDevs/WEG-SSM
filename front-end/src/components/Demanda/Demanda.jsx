import React, { useState, useContext } from "react";

import { Box, Typography, Button, Paper } from "@mui/material";

import FontConfig from "../../service/FontConfig";

import ModalMotivoRecusa from "../ModalMotivoRecusa/ModalMotivoRecusa";

import FontContext from "../../service/FontContext";

const Demanda = (props) => {
  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);
  
  // Cor do status da demansa
  let corStatus = getStatusColor();
  let tamanhoHeight = getTamanhoHeight();

  // Função para receber a cor do status da demanda
  function getStatusColor() {
    if (props.demanda.status == "CANCELLED") {
      return "#DA0303";
    } else if (props.demanda.status == "BACKLOG_REVISAO") {
      return "#C4C4C4";
    } else if (props.demanda.status == "BACKLOG_EDICAO") {
      return "#FFD600";
    } else if(props.demanda.status == "BACKLOG_APROVACAO") {
      return "#00579D";
    } else if (props.demanda.status == "ASSESSMENT") {
      return "#11B703";
    }
  }

  // Função para formatar o nome do status da demanda
  const formatarNomeStatus = () => {
    if (props.demanda.status == "CANCELLED") {
      return "Reprovada";
    } else if (props.demanda.status == "BACKLOG_REVISAO") {
      return "Aguardando Revisão";
    } else if (props.demanda.status == "BACKLOG_EDICAO") {
      return "Aguardando Edição";
    } else if(props.demanda.status == "BACKLOG_APROVACAO") {
      return "Em Aprovação";
    } else if (props.demanda.status == "ASSESSMENT") {
      return "Aprovada";
    }
  };

  function getTamanhoHeight() {
    if (
      parseInt(localStorage.getItem("userId")) != props.demanda?.solicitante?.id
    ) {
      return "8rem";
    } else {
      return "10rem";
    }
  }

  // useState para abrir o modal de motivo recusa
  const [abrirModal, setOpenModal] = useState(false);

  const abrirModalMotivoRecusa = () => {
    setOpenModal(true);
  };

  return (
    <>
      {/* Abrindo o modal de motivo recusa */}
      {abrirModal && (
        <ModalMotivoRecusa
          open={abrirModal}
          setOpen={setOpenModal}
          motivoRecusa={props.demanda?.motivoRecusa}
        />
      )}
      <Paper
        onClick={props.onClick}
        sx={{
          "&:hover": {
            backgroundColor: "hover.main",
          },
          borderColor: "primary.main",
          minWidth: "550px",
          maxWidth: "100%",
          minHeight: tamanhoHeight,
          maxHeight: "12rem",
          cursor: "pointer",
        }}
        className={`items-center h-30 text-justify border-t-4 pt-2 pb-3 px-6 drop-shadow-lg transition duration-200 hover:transition hover:duration-200`}
      >
        <Box className={`flex justify-between`} sx={{ marginBottom: "1%" }}>
          {/* Título da demanda */}
          <Typography
            fontSize={FontConfig.veryBig}
            sx={{ fontWeight: "600" }}
            color="text.primary"
          >
            {props.demanda.titulo}
          </Typography>

          {/* Lógica para mostrar o status da demanda somente caso o usuário seja o dono dela */}
          {parseInt(localStorage.getItem("usuarioId")) ==
            props.demanda?.solicitante?.id && (
              <Box className={`items-center text-justify flex`}>
                <Typography
                  fontSize={FontConfig.default}
                  sx={{ fontWeight: "600" }}
                >
                  {formatarNomeStatus()}
                </Typography>
                <Box
                  sx={{
                    backgroundColor: corStatus,
                    width: "12px",
                    height: "12px",
                    borderRadius: "10px",
                    marginLeft: "10px",
                  }}
                  className={`items-center h-30 text-justify`}
                />
              </Box>
            )}
        </Box>

        {/* Proposta da demanda */}
        <Typography
          gutterBottom
          fontSize={FontConfig.default}
          color="text.secondary"
        >
          {props.demanda.proposta}
        </Typography>
        <Box className={`flex justify-end`} sx={{ marginTop: ".5%" }}>
          {/* Lógica para mostrar o nome do solicitante que criou a demanda caso o usuário logado não seja ele */}
          {parseInt(localStorage.getItem("usuarioId")) !=
            props.demanda?.solicitante?.id ? (
            <Typography
              fontSize={FontConfig.default}
              sx={{ fontWeight: "600", cursor: "default" }}
              color="text.primary"
            >
              {props.demanda.solicitante?.nome}
            </Typography>
          ) : (props.demanda?.status == "CANCELLED" || props.demanda?.status == "BACKLOG_EDICAO") &&
            props.demanda?.solicitante?.id ===
            parseInt(localStorage.getItem("usuarioId")) ? (
            <Button
              onClick={(e) => {
                e.stopPropagation();
                abrirModalMotivoRecusa();
              }}
              variant="contained"
            >
              Motivo
            </Button>
          ) : null}
        </Box>
      </Paper>
    </>
  );
};

export default Demanda;
