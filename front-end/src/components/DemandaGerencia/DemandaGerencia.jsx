import { React, useState, useContext } from "react";
import { useNavigate } from "react-router-dom";

import { Box, Paper, Tooltip, Typography, IconButton } from "@mui/material";

import HistoryOutlinedIcon from "@mui/icons-material/HistoryOutlined";
import ChatOutlinedIcon from "@mui/icons-material/ChatOutlined";

import ModalHistoricoDemanda from "../Modais/Modal-historicoDemanda/ModalHistoricoDemanda";

import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";
import SpeechSynthesisContext from "../../service/SpeechSynthesisContext";

import ChatService from "../../service/chatService";
import UsuarioService from "../../service/usuarioService";

// Componente para exibir uma demanda ou proposta na tela de gerência, contendo mais opções de ação
const DemandaGerencia = (props) => {

  /** Navigate utilizado para navegar para outras páginas */
  const navigate = useNavigate();

  // Contexto para trocar a linguagem
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  /** Context para ler o texto da tela */
  const { lerTexto, lendoTexto } = useContext(SpeechSynthesisContext);

  // Variável pare receber o tipo ( proposta ou demanda )
  const tipo = props.tipo;

  // Variável para obter o usuário logado
  const [user] = useState(UsuarioService.getUserCookies());

  // useState para abrir o modal de histórico
  const [modalHistorico, setModalHistorico] = useState(false);

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

  // Função para abrir o chat caso o solicitante da demanda/proposta não seja o usuario
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

  return (
    <>
      {/* Verifica se existe foi solicitado que abrisse o modal, caso seja solicitado, será exibido o modal de historico*/}
      {modalHistorico && (
        <ModalHistoricoDemanda
          open={modalHistorico}
          setOpen={setModalHistorico}
          historico={
            props.dados.historicoDemanda || props.dados.historicoProposta
          }
        />
      )}
      {/* Container da demanda/proposta */}
      <Paper
        onClick={props.onClick}
        className="flex flex-col border-t-4 pt-2 pb-3 px-6 drop-shadow-lg transition duration-200 hover:transition hover:duration-200"
        sx={{
          "&:hover": {
            backgroundColor: "hover.main",
          },
          minWidth: "570px",
          borderColor: "primary.main",
          cursor: "pointer",
        }}
      >
        {/* Container titulo, ppm e status */}
        <Box className="flex justify-between">
          {/* Container titulo/ppm */}
          <Box className="flex mt-1">
            <Typography
              variant="h1"
              fontSize={FontConfig.veryBig}
              fontWeight="600"
              className="overflow-hidden text-ellipsis whitespace-nowrap"
              title={props.dados.titulo}
              onClick={(e) => {
                if (lendoTexto) {
                  e.stopPropagation();
                  lerTexto(props.dados.titulo);
                }
              }}
            >
              {/* verifica se o tipo do componente é proposta, caso seja, exibe o PPM da proposta */}
              {tipo === "proposta" && (
                <Typography
                  fontSize={FontConfig.default}
                  fontWeight="600"
                  sx={{ color: "primary.main" }}
                  onClick={(e) => {
                    if (lendoTexto) {
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
              {/* Título */}
              {props.dados.titulo}
            </Typography>
          </Box>

          {/* Status do componente */}
          <Box className="h-full">
            <Box className="flex items-end flex-col">
              <Box id="segundoCriarPropostas">
                <Box id="oitavoDemandas" className="flex items-center gap-2">
                  {/* Nome do status */}
                  <Typography
                    fontSize={FontConfig.medium}
                    fontWeight="600"
                    onClick={(e) => {
                      if (lendoTexto) {
                        e.stopPropagation();
                        lerTexto(formatarStatus(props.dados.status));
                      }
                    }}
                  >
                    {formatarStatus(props.dados.status)}
                  </Typography>
                  {/* cor do status */}
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
              {props.dados.presenteEm && props.dados.presenteEm != "Solta" && (
                <Box>
                  {/* Texto para dizer se está em ata ou em pauta */}
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
                      if (lendoTexto) {
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
            <Box sx={{ width: "50%" }}>
              {/* Solicitante */}
              <Box className="flex">
                <Typography
                  className="overflow-hidden truncate"
                  fontSize={FontConfig.default}
                  fontWeight="600"
                  onClick={(e) => {
                    if (lendoTexto) {
                      e.stopPropagation();
                      lerTexto(texts.demandaGerencia.solicitante);
                    }
                  }}
                >
                  {texts.demandaGerencia.solicitante}:
                </Typography>
                {/* Nome do solicitante */}
                <Typography
                  className="w-11/12 overflow-hidden text-ellipsis whitespace-nowrap"
                  fontSize={FontConfig.default}
                  fontWeight="600"
                  sx={{
                    color: "text.secondary",
                    marginLeft: "5px",
                    width: "50%",
                  }}
                  onClick={(e) => {
                    if (lendoTexto) {
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
                  className="overflow-hidden truncate"
                  fontSize={FontConfig.default}
                  fontWeight="600"
                  onClick={(e) => {
                    if (lendoTexto) {
                      e.stopPropagation();
                      lerTexto(texts.demandaGerencia.departamento);
                    }
                  }}
                >
                  {texts.demandaGerencia.departamento}:
                </Typography>
                {/* Nome do departamento */}
                <Typography
                  className="w-1/2 overflow-hidden text-ellipsis whitespace-nowrap"
                  fontSize={FontConfig.default}
                  fontWeight="600"
                  sx={{
                    color: "text.secondary",
                    marginLeft: "5px",
                    width: "20%",
                  }}
                  onClick={(e) => {
                    if (lendoTexto) {
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
            {/* Infos gerente responsável, analista responsavel e icons */}
            <Box className="flex items-end justify-end" sx={{ width: "80%" }}>
              <Box className="flex flex-col" sx={{ width: "100%", height: "100%" }}>
                {/* Container analista responsavel */}
                <Box className="flex">
                  <Typography
                    className="overflow-hidden truncate"
                    fontSize={FontConfig.default}
                    fontWeight="600"
                    onClick={(e) => {
                      if (lendoTexto) {
                        e.stopPropagation();
                        lerTexto(texts.demandaGerencia.analistaResponsavel);
                      }
                    }}
                  >
                    {texts.demandaGerencia.analistaResponsavel}:
                  </Typography>
                  {/* nome analista responsavel */}
                  <Typography
                    className="overflow-hidden truncate"
                    fontSize={FontConfig.default}
                    fontWeight="600"
                    sx={{
                      color: "text.secondary",
                      marginLeft: "5px",
                      width: "40%",
                    }}
                    onClick={(e) => {
                      if (lendoTexto) {
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
                {/* container gerente responsavel */}
                <Box className="flex">
                  <Typography
                    className="overflow-hidden truncate"
                    fontSize={FontConfig.default}
                    fontWeight="600"
                    onClick={(e) => {
                      if (lendoTexto) {
                        e.stopPropagation();
                        lerTexto(texts.demandaGerencia.gerenteResponsavel);
                      }
                    }}
                  >
                    {texts.demandaGerencia.gerenteResponsavel}:
                  </Typography>
                  {/* nome gerente responsavel */}
                  <Typography
                    className="overflow-hidden truncate"
                    fontSize={FontConfig.default}
                    fontWeight="600"
                    sx={{
                      color: "text.secondary",
                      marginLeft: "5px",
                    }}
                    onClick={(e) => {
                      if (lendoTexto) {
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
                <Box id="terceiroCriarPropostas" className="flex relative">
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
                      // se for um adm, mostra o icone de historico
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