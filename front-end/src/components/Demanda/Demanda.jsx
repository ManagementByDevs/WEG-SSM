import React, { useState, useContext } from "react";
import { Box, Typography, Button, Paper } from "@mui/material";

import ModalMotivoRecusa from "../ModalMotivoRecusa/ModalMotivoRecusa";
import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";

/** Componente de demanda em formato de bloco, usado na listagem de demandas para os usuários.
 * Também possui a função de redirecionar a outra página com detalhes da demanda.
 */
const Demanda = (props) => {
  // Contexto para trocar a linguagem
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // UseState determinando o estado do modal de motivo recusa
  const [modalMotivoRecusa, setModalMotivoRecusa] = useState(false);

  /** Função para receber a altura da div principal da demanda (é maior caso o solicitante seja o usuário logado) */
  const retornaAlturaDemanda = () => {
    if (parseInt(localStorage.getItem("userId")) != props.demanda?.solicitante?.id) {
      return "8rem";
    } else {
      return "10rem";
    }
  }

  /** Função para receber a cor do status da demanda de acordo com o status */
  const getStatusColor = () => {
    if (props.demanda.status == "CANCELLED") {
      return "#DA0303";
    } else if (props.demanda.status == "BACKLOG_REVISAO") {
      return "#C4C4C4";
    } else if (props.demanda.status == "BACKLOG_EDICAO") {
      return "#FFD600";
    } else if (props.demanda.status == "BACKLOG_APROVACAO") {
      return "#00579D";
    } else if (props.demanda.status == "ASSESSMENT") {
      return "#11B703";
    } else if (props.demanda.status == "ASSESSMENT_APROVACAO") {
      return "#F7DC6F";
    }
  }

  /** Função para formatar o nome do status da demanda para o solicitante */
  const formatarNomeStatus = () => {
    if (props.demanda.status == "CANCELLED") {
      return texts.demanda.status.reprovada;
    } else if (props.demanda.status == "BACKLOG_REVISAO") {
      return texts.demanda.status.aguardandoRevisao;
    } else if (props.demanda.status == "BACKLOG_EDICAO") {
      return texts.demanda.status.aguardandoEdicao;
    } else if (props.demanda.status == "BACKLOG_APROVACAO") {
      return texts.demanda.status.emAprovacao;
    } else if (props.demanda.status == "ASSESSMENT") {
      return texts.demanda.status.aprovada;
    } else if (props.demanda.status == "ASSESSMENT_APROVACAO") {
      return texts.demanda.status.emAndamento;
    }
  };

  return (
    <>
      {/* Modal de motivo recusa */}
      {modalMotivoRecusa && (
        <ModalMotivoRecusa
          open={true}
          setOpen={setModalMotivoRecusa}
          motivoRecusa={props.demanda?.motivoRecusa}
        />
      )}
      <Paper
        onClick={props.onClick}
        sx={{
          "&:hover": { backgroundColor: "hover.main", },
          borderColor: "primary.main",
          minWidth: "550px",
          maxWidth: "100%",
          minHeight: retornaAlturaDemanda(),
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
              <Box id="oitavo" className={`items-center text-justify flex`}>
                <Typography
                  fontSize={FontConfig.default}
                  sx={{ fontWeight: "600" }}
                >
                  {formatarNomeStatus()}
                </Typography>
                <Box
                  sx={{
                    backgroundColor: getStatusColor(),
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
          {parseInt(localStorage.getItem("usuarioId")) != props.demanda?.solicitante?.id ? (
            <Typography
              fontSize={FontConfig.default}
              sx={{ fontWeight: "600", cursor: "default" }}
              color="text.primary"
            >
              {props.demanda.solicitante?.nome}
            </Typography>
          ) : (props.demanda?.status == "CANCELLED" || props.demanda?.status == "BACKLOG_EDICAO") &&
            props.demanda?.solicitante?.id === parseInt(localStorage.getItem("usuarioId")) ? (
            <Button
              id="setimo"
              onClick={(e) => {
                e.stopPropagation();
                setModalMotivoRecusa(true);
              }}
              variant="contained"
            >
              {texts.demanda.motivo}
            </Button>
          ) : null}
        </Box>
      </Paper>
    </>
  );
};

export default Demanda;
