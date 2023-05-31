import { React, useState, useContext, useEffect } from "react";
import { useNavigate } from "react-router-dom";

import { Box, Paper, Tooltip, Typography, IconButton } from "@mui/material";

import HistoryOutlinedIcon from "@mui/icons-material/HistoryOutlined";
import ChatOutlinedIcon from "@mui/icons-material/ChatOutlined";

import ModalHistoricoDemanda from "../ModalHistoricoDemanda/ModalHistoricoDemanda";

import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";

import ChatService from "../../service/chatService";
import UsuarioService from "../../service/usuarioService";

// Componente para exibir uma demanda ou proposta na tela de gerência, contendo mais opções de ação
const DemandaGerencia = (props) => {

  /** Navigate utilizado para navegar para outras páginas */
  const navigate = useNavigate();

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

  // Variável para obter o usuário logado
  const [user, setUser] = useState(UsuarioService.getUserCookies());

  // Função para mudar a cor do status da demanda
  function getCorStatus() {
    if (props.dados.status === "BACKLOG_REVISAO") {
      return "#00579D";
    } else if (props.dados.status?.startsWith("ASSESSMENT")) {
      return "#8862A2";
    } else if (props.dados.status == "CANCELLED") {
      return "#DA0303";
    } else if (props.dados.status == "DONE") {
      return "#7EB61C";
    } else if (props.dados.status == "BUSINESS_CASE") {
      return "#FFD600";
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
    } else if (props.dados.status == "CANCELLED") {
      return texts.demandaGerencia.cancelled;
    } else if (props.dados.status == "DONE") {
      return texts.demandaGerencia.done;
    } else if (props.dados.status == "BUSINESS_CASE") {
      return texts.demandaGerencia.businessCase;
    }
  };

  const entrarChat = (e) => {
    e.stopPropagation();
    if (props.dados.solicitante.id !== user.usuario.id) {
      let chat;
      ChatService.getByPropostaAndUser(props.dados.id, user.usuario.id).then(
        (response) => {
          chat = response;
          if (chat.length > 0) {
            if (chat[0].conversaEncerrada == true) {
              ChatService.put(
                {
                  ...chat[0],
                  conversaEncerrada: false,
                },
                chat[0].id
              ).then((response) => {
                chat = response;
              });
            }
            navigate(`/chat/${chat[0].id}`);
          } else {
            let newChat = {
              idProposta: { id: props.dados.id },
              usuariosChat: [
                { id: user.usuario.id },
                { id: props.dados.solicitante.id },
              ],
            };
            ChatService.post(newChat).then((response) => {
              chat = response;
              navigate(`/chat/${chat.id}`);
            });
          }
        }
      );
    } else {
      props.setFeedbackAbrirChat(true);
    }
  };

   // Função que irá setar o texto que será "lido" pela a API
  const lerTexto = (escrita) => {
    if (props.lendo) {
      const synthesis = window.speechSynthesis;
      const utterance = new SpeechSynthesisUtterance(escrita);
  
      const finalizarLeitura = () => {
        if ("speechSynthesis" in window) {
          synthesis.cancel();
        }
      };
  
      if (props.lendo && escrita !== "") {
        if ("speechSynthesis" in window) {
          synthesis.speak(utterance);
        }
      } else {
        finalizarLeitura();
      }
  
      return () => {
        finalizarLeitura();
      };
    }
  };

  return (
    <>
      {modalHistorico && (
        <ModalHistoricoDemanda
          open={modalHistorico}
          setOpen={setModalHistorico}
          historico={
            props.dados.historicoDemanda || props.dados.historicoProposta
          }
          lendo={props.lendo}
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
          <Box className="flex w-4/5 mt-1">
            <Typography
              variant="h1"
              fontSize={FontConfig.veryBig}
              fontWeight="600"
              className="w-full overflow-hidden text-ellipsis whitespace-nowrap"
              title={props.dados.titulo}
              onClick={(e) => {
                if (props.lendo) {
                  e.stopPropagation();
                  lerTexto(props.dados.titulo);
                }
              }}
            >
              {tipo === "proposta" && (
                <Typography
                  fontSize={FontConfig.default}
                  fontWeight="600"
                  sx={{ color: "primary.main" }}
                  onClick={(e) => {
                    if (props.lendo) {
                      e.stopPropagation();
                      lerTexto(
                        texts.demandaGerencia.ppm + ": " + props.dados.codigoPPM
                      );
                    }
                  }}
                >
                  {texts.demandaGerencia.ppm} {props.dados.codigoPPM}
                </Typography>
              )}
              {props.dados.titulo}
            </Typography>
          </Box>

          {/* Status do componente */}
          <Box className="w-1/4 h-full">
            <Box className="flex items-end flex-col">
              <Box id="segundoCriarPropostas">
                <Box id="oitavoDemandas" className="flex items-center gap-2">
                  <Typography
                    fontSize={FontConfig.medium}
                    fontWeight="600"
                    onClick={(e) => {
                      if (props.lendo) {
                        e.stopPropagation();
                        lerTexto(formatarStatus(props.dados.status));
                      }
                    }}
                  >
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

              {/* Verificando se está em ata, em pauta ou em edição */}
              {props.dados.presenteEm && props.dados.presenteEm != "Nada" && (
                <Box>
                  <Typography
                    fontSize={FontConfig.small}
                    fontWeight="600"
                    sx={{
                      color: "text.primary",
                      backgroundColor: "divider.claro",
                      borderRadius: "5px",
                      padding: "2px 15px",
                    }}
                    onClick={(e) => {
                      if (props.lendo) {
                        e.stopPropagation();
                        if (props.dados.presenteEm == "Pauta") {
                          lerTexto(texts.demandaGerencia.emPauta);
                        } else {
                          lerTexto(texts.demandaGerencia.emAta);
                        }
                      }
                    }}
                  >
                    {props.dados.presenteEm === "Pauta"
                      ? texts.demandaGerencia.emPauta
                      : texts.demandaGerencia.emAta}
                  </Typography>
                </Box>
              )}
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
                <Typography
                  fontSize={FontConfig.default}
                  fontWeight="600"
                  onClick={(e) => {
                    if (props.lendo) {
                      e.stopPropagation();
                      lerTexto(texts.demandaGerencia.solicitante);
                    }
                  }}
                >
                  {texts.demandaGerencia.solicitante}:
                </Typography>
                <Typography
                  className="w-11/12 overflow-hidden text-ellipsis whitespace-nowrap"
                  fontSize={FontConfig.default}
                  fontWeight="600"
                  sx={{ color: "text.secondary", marginLeft: "5px" }}
                  onClick={(e) => {
                    if (props.lendo) {
                      e.stopPropagation();
                      lerTexto(props.dados.solicitante?.nome);
                    }
                  }}
                >
                  {props.dados.solicitante?.nome}
                </Typography>
              </Box>

              {/* Departamento */}
              <Box className="flex">
                <Typography
                  fontSize={FontConfig.default}
                  fontWeight="600"
                  onClick={(e) => {
                    if (props.lendo) {
                      e.stopPropagation();
                      lerTexto(texts.demandaGerencia.departamento);
                    }
                  }}
                >
                  {texts.demandaGerencia.departamento}:
                </Typography>
                <Typography
                  className="w-1/2 overflow-hidden text-ellipsis whitespace-nowrap"
                  fontSize={FontConfig.default}
                  fontWeight="600"
                  sx={{ color: "text.secondary", marginLeft: "5px" }}
                  onClick={(e) => {
                    if (props.lendo) {
                      e.stopPropagation();
                      if (props.dados.departamento?.nome) {
                        lerTexto(props.dados.departamento?.nome);
                      } else {
                        lerTexto(texts.demandaGerencia.naoAtribuido);
                      }
                    }
                  }}
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
                    onClick={(e) => {
                      if (props.lendo) {
                        e.stopPropagation();
                        lerTexto(texts.demandaGerencia.analistaResponsavel);
                      }
                    }}
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
                    onClick={(e) => {
                      if (props.lendo) {
                        e.stopPropagation();
                        if (props.dados.analista?.nome) {
                          lerTexto(props.dados.analista?.nome);
                        } else {
                          lerTexto(texts.demandaGerencia.naoAtribuido);
                        }
                      }
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
                    onClick={(e) => {
                      if (props.lendo) {
                        e.stopPropagation();
                        lerTexto(texts.demandaGerencia.gerenteResponsavel);
                      }
                    }}
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
                    onClick={(e) => {
                      if (props.lendo) {
                        e.stopPropagation();
                        if (props.dados.gerente?.nome) {
                          lerTexto(props.dados.gerente?.nome);
                        } else {
                          lerTexto(texts.demandaGerencia.naoAtribuido);
                        }
                      }
                    }}
                  >
                    {props.dados.gerente?.nome ||
                      texts.demandaGerencia.naoAtribuido}
                  </Typography>
                </Box>
              </Box>
              <Box>
                {/* Icon de histórico  e chat*/}
                <Box id="terceiroCriarPropostas" className="flex">
                  {
                    // Se for uma proposta, mostra o icone de chat
                    tipo === "proposta" && (
                      <Tooltip title={texts.demandaGerencia.chat}>
                        <IconButton onClick={entrarChat}>
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
                  {props.semHistorico == false ||
                    (props.semHistorico == null && (
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
                    ))}
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
