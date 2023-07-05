import React, { useContext, useState } from "react";
import { useNavigate } from "react-router-dom";

import {
  Box,
  Paper,
  Table,
  TableBody,
  TableHead,
  TableRow,
  Tooltip,
  Typography,
} from "@mui/material";

import "./DemandaGerenciaModoVisualizacao.css";

import DemandaGerencia from "../DemandaGerencia/DemandaGerencia";
import ModalHistoricoDemanda from "../Modais/Modal-historicoDemanda/ModalHistoricoDemanda";

import ContentPasteOutlinedIcon from "@mui/icons-material/ContentPasteOutlined";
import BeenhereOutlinedIcon from "@mui/icons-material/BeenhereOutlined";
import HistoryOutlinedIcon from "@mui/icons-material/HistoryOutlined";
import ChatOutlinedIcon from "@mui/icons-material/ChatOutlined";

import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";
import EntitiesObjectService from "../../service/entitiesObjectService";
import ChatService from "../../service/chatService";
import UsuarioService from "../../service/usuarioService";
import SpeechSynthesisContext from "../../service/SpeechSynthesisContext";

// Componente para mudar o modo de visualização das demandas (Grid, tabela ou nenhuma demanda encontrada) - Gerência
const DemandaGerenciaModoVisualizacao = ({
  listaDemandas = [EntitiesObjectService.proposta()],
  onDemandaClick,
  nextModoVisualizacao,
  isProposta = false,
  isChatVisible = false,
  setFeedbackAbrirChat,
}) => {
  // Verificação para ver se retorna algo, caso não retorne nada, mostre o componente "NadaEncontrado"
  if (listaDemandas.length == 0) {
    return <NadaEncontrado />;
  }

  // Verificação para ver se o próximo modo de visualização é "TABLE", caso seja, mostre o componente "DemandaGrid"
  // caso não seja, mostre o componente "DemandaTable"
  if (nextModoVisualizacao == "TABLE")
    return (
      <DemandaGrid
        listaDemandas={listaDemandas}
        onDemandaClick={onDemandaClick}
        isProposta={isProposta}
        setFeedbackAbrirChat={setFeedbackAbrirChat}
        isChatVisible={isChatVisible}
      />
    );
  return (
    <DemandaTable
      listaDemandas={listaDemandas}
      onDemandaClick={onDemandaClick}
      isProposta={isProposta}
      setFeedbackAbrirChat={setFeedbackAbrirChat}
      isChatVisible={isChatVisible}
    />
  );
};

// Componente do modo de visualização "TABLE"
const DemandaTable = ({
  listaDemandas = [
    {
      analista: {},
      beneficios: [{}],
      buSolicitante: {},
      busBeneficiados: [{}],
      departamento: {},
      frequencia: "",
      gerente: {},
      tamanho: "",
      id: 0,
      emAta: false,
      emPauta: false,
      titulo: "",
      problema: "",
      proposta: "",
      motivoRecusa: "",
      status: "",
      data: "",
      solicitante: {},
      historicoDemanda: [],
      historicoProposta: [],
    },
  ],
  onDemandaClick,
  isProposta = false,
  isChatVisible = false,
  setFeedbackAbrirChat,
}) => {
  // Variável utilizada para navegação no sistema
  const navigate = useNavigate();

  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  /** Context para ler o texto da tela */
  const { lendoTexto, lerTexto, librasAtivo } = useContext(SpeechSynthesisContext);

  // Controla o estado do modal de histórico da demanda
  const [modalHistorico, setModalHistorico] = useState(false);

  /** Variável usada para determinar o histórico a ser usado no modal */
  const [historicoSelecionado, setHistoricoSelecionado] = useState(null);

  // Contexto para trocar a linguagem
  const { texts } = useContext(TextLanguageContext);

  // UseState utilizado para obter o usuário logado
  const [user, setUser] = useState(UsuarioService.getUserCookies());

  // Função para receber a cor do status da demanda
  const getStatusColor = (status) => {
    if (status === "BACKLOG_REVISAO") {
      return "#00579D";
    } else if (status.startsWith("ASSESSMENT")) {
      return "#8862A2";
    } else if (status == "CANCELLED") {
      return "#DA0303";
    } else if (status == "DONE") {
      return "#7EB61C";
    } else if (status == "BUSINESS_CASE") {
      return "#FFD600";
    }
  };

  // Formata o status da demanda para melhor leitura
  const formatarNomeStatus = (status) => {
    if (status == "BACKLOG_REVISAO") {
      return texts.demandaGerenciaModoVisualizacao.backlog;
    } else if (status.startsWith("ASSESSMENT")) {
      return texts.demandaGerenciaModoVisualizacao.assessment;
    } else if (status == "CANCELLED") {
      return texts.demandaGerenciaModoVisualizacao.cancelled;
    } else if (status == "DONE") {
      return texts.demandaGerenciaModoVisualizacao.done;
    } else if (status == "BUSINESS_CASE") {
      return texts.demandaGerenciaModoVisualizacao.businessCase;
    }
  };

  // Abre o histórico da demanda
  const abrirModalHistorico = () => {
    setModalHistorico(true);
  };

  // Função que lida com o resultado da pesquisa do chat por demanda ou proposta
  const handleOnResultChat = (response, dados) => {
    let chat = response;
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
        idProposta: { id: dados.id },
        usuariosChat: [{ id: user.usuario.id }, { id: dados.solicitante.id }],
      };
      ChatService.post(newChat).then((response) => {
        chat = response;
        navigate(`/chat/${chat.id}`);
      });
    }
  };

  // Função para entrar em um chat se o usuario atual não seja o usuario solicitante da demanda/proposta
  const entrarChat = (e, dados) => {
    e.stopPropagation();
    if (dados.solicitante.id !== user.usuario.id) {
      if (isProposta) {
        ChatService.getByPropostaAndUser(dados.id, user.usuario.id).then(
          (response) => {
            handleOnResultChat(response, dados);
          }
        );
      } else {
        ChatService.getByDemandaAndUser(dados.id, user.usuario.id).then(
          (response) => {
            handleOnResultChat(response, dados);
          }
        );
      }
    } else {
      setFeedbackAbrirChat(true);
    }
  };

  return (
    <>
      {/* Modal de historico */}
      {modalHistorico && (
        <ModalHistoricoDemanda
          open={modalHistorico}
          setOpen={setModalHistorico}
          historico={historicoSelecionado}
        />
      )}
      {/* Container geral do componente */}
      <Paper sx={{ width: "100%", minWidth: "74rem" }} square>
        {/* inicia a criação da tabela */}
        <Table sx={{ width: "100%" }} className="table-fixed">
          <TableHead sx={{ width: "100%" }}>
            <TableRow
              sx={{
                backgroundColor: "primary.main",
                minWidth: "75rem",
                width: "100%",
              }}
            >
              {/* Titulo PPM */}
              <th className="text-white p-2 width-75/1000">
                <Typography
                  fontSize={FontConfig.big}
                  onClick={(e) => {
                    if (lendoTexto) {
                      e.preventDefault();
                      if (!isProposta) {
                        lerTexto(texts.demandaGerenciaModoVisualizacao.codigo);
                      } else {
                        lerTexto("PPM");
                      }
                    } else if (librasAtivo) {
                      e.stopPropagation();
                    }
                  }}
                >
                  {!isProposta
                    ? texts.demandaGerenciaModoVisualizacao.codigo
                    : "PPM"}
                </Typography>
              </th>
              {/* Palavra titulo */}
              <th className="text-left text-white p-3 width-4/10">
                <Typography
                  fontSize={FontConfig.big}
                  onClick={(e) => {
                    if (lendoTexto) {
                      e.preventDefault();
                      lerTexto(texts.demandaGerenciaModoVisualizacao.titulo);
                    } else if (librasAtivo) {
                      e.stopPropagation();
                    }
                  }}
                >
                  {texts.demandaGerenciaModoVisualizacao.titulo}
                </Typography>
              </th>
              {/* Palavra Solicitante */}
              <th className="text-left text-white p-3">
                <Typography
                  fontSize={FontConfig.big}
                  onClick={(e) => {
                    if (lendoTexto) {
                      e.preventDefault();
                      lerTexto(
                        texts.demandaGerenciaModoVisualizacao.solicitante
                      );
                    } else if (librasAtivo) {
                      e.stopPropagation();
                    }
                  }}
                >
                  {texts.demandaGerenciaModoVisualizacao.solicitante}
                </Typography>
              </th>
              {/* Palavra departamento */}
              <th className="text-left text-white p-3">
                <Typography
                  fontSize={FontConfig.big}
                  onClick={(e) => {
                    if (lendoTexto) {
                      e.preventDefault();
                      lerTexto(
                        texts.demandaGerenciaModoVisualizacao.departamento
                      );
                    } else if (librasAtivo) {
                      e.stopPropagation();
                    }
                  }}
                >
                  {texts.demandaGerenciaModoVisualizacao.departamento}
                </Typography>
              </th>
              {/* Palavra gerente responsavel */}
              <th className="text-left text-white p-3">
                <Typography
                  fontSize={FontConfig.big}
                  onClick={(e) => {
                    if (lendoTexto) {
                      e.preventDefault();
                      lerTexto(
                        texts.demandaGerenciaModoVisualizacao.gerenteResponsavel
                      );
                    } else if (librasAtivo) {
                      e.stopPropagation();
                    }
                  }}
                >
                  {texts.demandaGerenciaModoVisualizacao.gerenteResponsavel}
                </Typography>
              </th>
              {/* Palavra status */}
              <th className="text-left text-white p-3 ">
                <Typography
                  fontSize={FontConfig.big}
                  onClick={(e) => {
                    if (lendoTexto) {
                      e.preventDefault();
                      lerTexto(texts.demandaGerenciaModoVisualizacao.status);
                    } else if (librasAtivo) {
                      e.stopPropagation();
                    }
                  }}
                >
                  {texts.demandaGerenciaModoVisualizacao.status}
                </Typography>
              </th>
              <th className="text-white p-3 width-75/1000">
                <Typography></Typography>
              </th>
            </TableRow>
          </TableHead>
          {/* Corpo da tabela, ou seja, os "dados" */}
          <TableBody sx={{ minWidth: "75rem", width: "100%" }}>
            {listaDemandas.map((row, index) => (
              // dependendo de quantas demandas terá, haverá uma quantidade específica de linhas das tabelas
              <TableRow
                className="cursor-pointer tabela-linha-demanda"
                hover
                key={index}
                sx={{
                  "&:last-child td, &:last-child th": { border: 0 },
                  minWidth: "75rem",
                  width: "100%",
                }}
                onClick={() => {
                  if (!lendoTexto && !librasAtivo) {
                    onDemandaClick(row);
                  }
                }}
              >
                {/* Codigo PPM */}
                <td
                  className="text-center p-3 width-1/10"
                  title={row.codigoPPM}
                >
                  <Typography
                    className="truncate"
                    fontSize={FontConfig.medium}
                    onClick={(e) => {
                      if (lendoTexto) {
                        e.preventDefault();
                        if (!isProposta) {
                          lerTexto(row.id);
                        } else {
                          lerTexto(row.codigoPPM);
                        }
                      } else if (librasAtivo) {
                        e.stopPropagation();
                      }
                    }}
                  >
                    {!isProposta ? row.id : row.codigoPPM}
                  </Typography>
                </td>
                {/* Titulo */}
                <td
                  className="text-left p-3 width-4/12 flex"
                  title={row.titulo}
                >
                  <Typography
                    className="truncate"
                    fontSize={FontConfig.medium}
                    onClick={(e) => {
                      if (lendoTexto) {
                        e.preventDefault();
                        lerTexto(row.titulo);
                      } else if (librasAtivo) {
                        e.stopPropagation();
                      }
                    }}
                  >
                    {row.titulo}
                  </Typography>
                </td>
                {/* Solicitante */}
                <td
                  className="text-left p-3 width-1/10"
                  title={row.solicitante.nome}
                >
                  <Typography
                    className="truncate"
                    fontSize={FontConfig.medium}
                    onClick={(e) => {
                      if (lendoTexto) {
                        e.preventDefault();
                        lerTexto(row.solicitante.nome);
                      } else if (librasAtivo) {
                        e.stopPropagation();
                      }
                    }}
                  >
                    {row.solicitante.nome}
                  </Typography>
                </td>
                {/* Departamento */}
                <td
                  className="text-left p-3 width-1/10"
                  title={
                    row.departamento
                      ? row.departamento.nome
                      : texts.demandaGerenciaModoVisualizacao.naoAtribuido
                  }
                >
                  <Typography
                    className="truncate"
                    fontSize={FontConfig.medium}
                    onClick={(e) => {
                      if (lendoTexto) {
                        e.preventDefault();
                        if (row.departamento) {
                          lerTexto(row.departamento.nome);
                        } else {
                          lerTexto(
                            texts.demandaGerenciaModoVisualizacao.naoAtribuido
                          );
                        }
                      } else if (librasAtivo) {
                        e.stopPropagation();
                      }
                    }}
                  >
                    {row.departamento
                      ? row.departamento.nome
                      : texts.demandaGerenciaModoVisualizacao.naoAtribuido}
                  </Typography>
                </td>
                {/* Gerente responsavel */}
                <td
                  className="text-left p-3 width-1/10"
                  title={
                    row.gerente?.nome
                      ? row.gerente.nome
                      : texts.demandaGerenciaModoVisualizacao.naoAtribuido
                  }
                >
                  <Typography
                    className="truncate"
                    fontSize={FontConfig.medium}
                    onClick={(e) => {
                      if (lendoTexto) {
                        e.preventDefault();
                        if (row.gerente?.nome) {
                          lerTexto(row.gerente.nome);
                        } else {
                          lerTexto(
                            texts.demandaGerenciaModoVisualizacao.naoAtribuido
                          );
                        }
                      } else if (librasAtivo) {
                        e.stopPropagation();
                      }
                    }}
                  >
                    {row.gerente?.nome
                      ? row.gerente.nome
                      : texts.demandaGerenciaModoVisualizacao.naoAtribuido}
                  </Typography>
                </td>
                {/* Status e ve se está em auta ou em pauta */}
                <td
                  className="text-left p-3 width-1/10"
                  title={formatarNomeStatus(row.status)}
                >
                  <Box className="flex items-center gap-2 text-center">
                    <Box
                      sx={{
                        backgroundColor: getStatusColor(row.status),
                        width: "12px",
                        height: "2rem",
                        borderRadius: "3px",
                      }}
                    />
                    {/* Status */}
                    <Box className="w-full flex justify-between items-center">
                      <Typography
                        className="truncate"
                        fontSize={FontConfig.medium}
                        onClick={(e) => {
                          if (lendoTexto) {
                            e.preventDefault();
                            lerTexto(formatarNomeStatus(row.status));
                          } else if (librasAtivo) {
                            e.stopPropagation();
                          }
                        }}
                      >
                        {formatarNomeStatus(row.status)}
                      </Typography>
                    </Box>
                    {/* Em pauta ou em ata */}
                    {(row.presenteEm == "Pauta" || row.presenteEm == "Ata") && (
                      <Box className="flex items-center">
                        {row.presenteEm == "Pauta" ? (
                          <Box title="Em pauta">
                            <ContentPasteOutlinedIcon
                              sx={{ color: "icon.main" }}
                            />
                          </Box>
                        ) : (
                          <Box title="Em ata">
                            <BeenhereOutlinedIcon sx={{ color: "icon.main" }} />
                          </Box>
                        )}
                      </Box>
                    )}
                  </Box>
                </td>
                <td className="p-3 width-1/10 ">
                  {/*Icone de Historico e chat da proposta/demanda */}
                  <Box className="w-full gap-3 flex justify-center items-center">
                    {/*Icone de Historico */}
                    <Tooltip
                      title={texts.demandaGerenciaModoVisualizacao.historico}
                    >
                      <HistoryOutlinedIcon
                        size="small"
                        onClick={(e) => {
                          e.stopPropagation();
                          setHistoricoSelecionado(
                            row.historicoDemanda || row.historicoProposta
                          );
                          abrirModalHistorico();
                        }}
                        className="delay-120 hover:scale-110 duration-300"
                        sx={{ color: "icon.main", cursor: "pointer" }}
                      />
                    </Tooltip>
                    {/*Icone de Chat caso seja uma proposta */}
                    {(isProposta || isChatVisible) && (
                      <Tooltip
                        title={texts.demandaGerenciaModoVisualizacao.chat}
                      >
                        <ChatOutlinedIcon
                          onClick={(e) => {
                            e.stopPropagation();
                            setHistoricoSelecionado(
                              row.historicoDemanda || row.historicoProposta
                            );
                            entrarChat(e, row);
                          }}
                          className="delay-120 hover:scale-110 duration-300"
                          sx={{ color: "icon.main", cursor: "pointer" }}
                        />
                      </Tooltip>
                    )}
                  </Box>
                </td>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </Paper>
    </>
  );
};

// Componente para exibição de demandas em grid
const DemandaGrid = ({
  listaDemandas,
  onDemandaClick,
  isProposta = false,
  isChatVisible = false,
  setFeedbackAbrirChat,
}) => {
  return (
    <Box
      sx={{
        display: "grid",
        gap: "1rem",
        gridTemplateColumns: "repeat(auto-fit, minmax(570px, 1fr))",
      }}
    >
      {listaDemandas?.map((demanda, index) => {
        return (
          // Mostra o componente de "DemandaGerencia" conforme a quantidade de demandas da lista
          <DemandaGerencia
            key={index}
            dados={demanda}
            tipo={isProposta ? "proposta" : "demanda"}
            setFeedbackAbrirChat={setFeedbackAbrirChat}
            onClick={() => {
              onDemandaClick(demanda);
            }}
            isChatVisible={isChatVisible}
          />
        );
      })}
    </Box>
  );
};

// Componente para exibição de nada encontrado
const NadaEncontrado = () => {
  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  /** Context para ler o texto da tela */
  const { lerTexto } = useContext(SpeechSynthesisContext);

  return (
    // Container de textos
    <Box
      sx={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        flexDirection: "column",
        height: "100%",
        marginTop: "2rem",
      }}
    >
      {/* Texto de nada encontrado */}
      <Typography
        fontSize={FontConfig.big}
        sx={{ color: "text.secondary", mb: 1 }}
        onClick={() => {
          lerTexto(texts.demandaGerenciaModoVisualizacao.nadaEncontrado);
        }}
      >
        {texts.demandaGerenciaModoVisualizacao.nadaEncontrado}
      </Typography>
      {/* Texto de tente novamente mais tarde */}
      <Typography
        fontSize={FontConfig.medium}
        sx={{ color: "text.secondary", mb: 1 }}
        onClick={() => {
          lerTexto(
            texts.demandaGerenciaModoVisualizacao.tenteNovamenteMaisTarde
          );
        }}
      >
        {texts.demandaGerenciaModoVisualizacao.tenteNovamenteMaisTarde}
      </Typography>
    </Box>
  );
};

export default DemandaGerenciaModoVisualizacao;
