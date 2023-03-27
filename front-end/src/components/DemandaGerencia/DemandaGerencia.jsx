import { React, useState, useContext } from "react";

import { Box, Paper, Tooltip, Typography, IconButton } from "@mui/material";

import HistoryOutlinedIcon from "@mui/icons-material/HistoryOutlined";
import ChatOutlinedIcon from "@mui/icons-material/ChatOutlined";

import ModalHistoricoDemanda from "../ModalHistoricoDemanda/ModalHistoricoDemanda";

import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";

// Componente para exibir uma demanda ou proposta na tela de gerência, contendo mais opções de ação
const DemandaGerencia = (props) => {
  // Contexto para trocar a linguagem
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // Como usar:
  // Ao chamar o componente, passar duas props:
  // - dados: um objeto com os dados da demanda ou proposta
  // - tipo: "demanda" ou "proposta"
  // Exemplo:
  // <DemandaGerencia dados={demanda} tipo="demanda" />

  // Variável pare receber o tipo ( proposta ou demanda )
  const tipo = props.tipo;

  // Função para mudar a cor do status da demanda
  function getCorStatus() {
    if (props.dados.status === "BACKLOG_REVISAO") {
      return "#00579D";
    } else if (props.dados.status?.startsWith("ASSESSMENT")) {
      return "#8862A2";
    }
  }

  // useState para abrir o modal de histórico
  const [modalHistorico, setModalHistorico] = useState(false);

  // Função para abrir o modal de histórico
  const abrirModalHistorico = () => {
    setModalHistorico(true);
  };

  // Função para formatar o nome do status da demanda/proposta
  const formatarStatus = () => {
    if (props.dados.status == "BACKLOG_REVISAO") {
      return texts.demandaGerencia.backlog;
    } else if (props.dados.status?.startsWith("ASSESSMENT")) {
      return texts.demandaGerencia.assessment;
    }
  };

  return (
    <>
      {modalHistorico && (
        <ModalHistoricoDemanda
          open={modalHistorico}
          setOpen={setModalHistorico}
          historico={props.dados.historicoDemanda}
        />
      )}
      <Paper
        onClick={props.onClick}
        className="flex flex-col border-t-4 pt-2 pb-3 px-6 drop-shadow-lg transition duration-200 hover:transition hover:duration-200"
        sx={{
          "&:hover": {
            backgroundColor: "hover.main",
          },
          borderColor: "primary.main",
          minWidth: "729px",
          cursor: "pointer",
        }}
      >
        {/* Container titulo e status */}
        <Box className="flex w-full justify-between">
          {/* Título */}
          <Box className="flex w-3/4 mt-1">
            <Typography
              variant="h1"
              fontSize={FontConfig.veryBig}
              fontWeight="600"
              className="w-full overflow-hidden text-ellipsis whitespace-nowrap"
              title={props.dados.titulo}
            >
              {tipo === "proposta" && (
                <Typography
                  fontSize={FontConfig.default}
                  fontWeight="600"
                  sx={{ color: "primary.main" }}
                >
                  {texts.demandaGerencia.ppm} {props.dados.codigoPPM}
                </Typography>
              )}
              {props.dados.titulo}
            </Typography>
          </Box>

          {/* Status do componente */}
          <Box className="w-1/4 h-full">
            <Box className="flex items-center justify-end">
              <Box id="segundoCriarPropostas">
                <Box id="oitavoDemandas" className="flex items-center gap-2">
                  <Typography fontSize={FontConfig.medium} fontWeight="600">
                    {formatarStatus(props.dados.status)}
                  </Typography>
                  <Box
                    className="rounded-full"
                    sx={{
                      width: "10px",
                      height: "10px",
                      backgroundColor: getCorStatus(),
                    }}
                  ></Box>
                </Box>
              </Box>
            </Box>
          </Box>
        </Box>

        {/* Dados do componente (demanda / proposta) */}
        <Box className="relative">
          <Box className="h-16 items-end w-full flex justify-between">
            {/* Infos Solicitante e Departamento */}
            <Box sx={{ width: "40%" }}>
              {/* Solicitante */}
              <Box className="flex">
                <Typography fontSize={FontConfig.default} fontWeight="600">
                  {texts.demandaGerencia.solicitante}:
                </Typography>
                <Typography
                  className="w-11/12 overflow-hidden text-ellipsis whitespace-nowrap"
                  fontSize={FontConfig.default}
                  fontWeight="600"
                  sx={{ color: "text.secondary", marginLeft: "5px" }}
                >
                  {props.dados.solicitante?.nome}
                </Typography>
              </Box>

              {/* Departamento */}
              <Box className="flex">
                <Typography fontSize={FontConfig.default} fontWeight="600">
                  {texts.demandaGerencia.departamento}:
                </Typography>
                <Typography
                  className="w-1/2 overflow-hidden text-ellipsis whitespace-nowrap"
                  fontSize={FontConfig.default}
                  fontWeight="600"
                  sx={{ color: "text.secondary", marginLeft: "5px" }}
                >
                  {props.dados.departamento?.nome ||
                    texts.demandaGerencia.naoAtribuido}
                </Typography>
              </Box>
            </Box>
            {/* Infos gerente responsável e icons */}
            <Box className="flex items-end justify-end w-full">
              <Box
                className="flex flex-col"
                sx={{ width: "24rem", height: "100%" }}
              >
                <Box className="flex" sx={{ width: "80%" }}>
                  <Typography
                    className="overflow-hidden truncate"
                    fontSize={FontConfig.default}
                    fontWeight="600"
                  >
                    {texts.demandaGerencia.analistaResponsavel}:
                  </Typography>
                  <Typography
                    className="overflow-hidden truncate"
                    fontSize={FontConfig.default}
                    fontWeight="600"
                    sx={{
                      color: "text.secondary",
                      marginLeft: "5px",
                      width: "50%",
                    }}
                  >
                    {props.dados.analista?.nome ||
                      texts.demandaGerencia.naoAtribuido}
                  </Typography>
                </Box>
                <Box className="flex" sx={{ width: "24rem" }}>
                  <Typography
                    className="overflow-hidden truncate"
                    fontSize={FontConfig.default}
                    fontWeight="600"
                  >
                    {texts.demandaGerencia.gerenteResponsavel}:
                  </Typography>
                  <Typography
                    className="overflow-hidden truncate"
                    fontSize={FontConfig.default}
                    fontWeight="600"
                    sx={{
                      color: "text.secondary",
                      marginLeft: "5px",
                      width: "50%",
                    }}
                  >
                    {props.dados.gerente?.nome ||
                      texts.demandaGerencia.naoAtribuido}
                  </Typography>
                </Box>
              </Box>
              <Box>
                {/* Icon de histórico  e chat*/}
                <Box id="terceiroCriarPropostas" className="flex flex-col">
                  {
                    // Se for uma proposta, mostra o icone de chat
                    tipo === "proposta" && (
                      <Tooltip title={texts.demandaGerencia.chat}>
                        <IconButton>
                          <ChatOutlinedIcon
                            id="segundoPropostas"
                            className="delay-120 hover:scale-110 duration-300"
                            sx={{
                              color: "icon.main",
                              cursor: "pointer",
                              fontSize: "30px",
                            }}
                          />
                        </IconButton>
                      </Tooltip>
                    )
                  }
                  <Tooltip title={texts.demandaGerencia.historico}>
                    <IconButton
                      onClick={(e) => {
                        if (
                          !props.isTourPropostasOpen &&
                          !props.isTourDemandasOpen &&
                          !props.isTourCriarPropostasOpen
                        ) {
                          e.stopPropagation();
                          abrirModalHistorico();
                        }
                      }}
                    >
                      <HistoryOutlinedIcon
                        id="setimoDemandas"
                        className="delay-120 hover:scale-110 duration-300"
                        sx={{
                          color: "icon.main",
                          cursor: "pointer",
                          fontSize: "30px",
                        }}
                      />
                    </IconButton>
                  </Tooltip>
                </Box>
              </Box>
            </Box>
          </Box>
        </Box>
      </Paper>
    </>
  );
};

export default DemandaGerencia;
