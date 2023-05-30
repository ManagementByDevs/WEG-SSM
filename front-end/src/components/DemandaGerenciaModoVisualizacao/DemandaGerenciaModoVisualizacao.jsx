import React, { useContext, useState, useEffect } from "react";

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
import ModalHistoricoDemanda from "../ModalHistoricoDemanda/ModalHistoricoDemanda";

import ContentPasteOutlinedIcon from "@mui/icons-material/ContentPasteOutlined";
import BeenhereOutlinedIcon from "@mui/icons-material/BeenhereOutlined";
import HistoryOutlinedIcon from "@mui/icons-material/HistoryOutlined";
import ChatOutlinedIcon from "@mui/icons-material/ChatOutlined";

import FontContext from "../../service/FontContext";
import DateService from "../../service/dateService";
import TextLanguageContext from "../../service/TextLanguageContext";
import EntitiesObjectService from "../../service/entitiesObjectService";

// Componente para mudar o modo de visualização das demandas (Grid, tabela ou nenhuma demanda encontrada) - Gerência
const DemandaGerenciaModoVisualizacao = ({
  listaDemandas = [EntitiesObjectService.proposta()],
  onDemandaClick,
  nextModoVisualizacao,
  isProposta = false,
  setFeedbackAbrirChat,
  lendo = false,
}) => {
  if (listaDemandas.length == 0) {
    return <NadaEncontrado lendo={lendo} />;
  }

  if (nextModoVisualizacao == "TABLE")
    return (
      <DemandaGrid
        listaDemandas={listaDemandas}
        onDemandaClick={onDemandaClick}
        isProposta={isProposta}
        setFeedbackAbrirChat={setFeedbackAbrirChat}
        lendo={lendo}
      />
    );

  return (
    <DemandaTable
      listaDemandas={listaDemandas}
      onDemandaClick={onDemandaClick}
      isProposta={isProposta}
      setFeedbackAbrirChat={setFeedbackAbrirChat}
      lendo={lendo}
    />
  );
};

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
  lendo = false,
}) => {
  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  // Controla o estado do modal de histórico da demanda
  const [modalHistorico, setModalHistorico] = useState(false);

  /** Variável usada para determinar o histórico a ser usado no modal */
  const [historicoSelecionado, setHistoricoSelecionado] = useState(null);

  // Contexto para trocar a linguagem
  const { texts } = useContext(TextLanguageContext);

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

  const [textoLeitura, setTextoLeitura] = useState("");

  // Função que irá setar o texto que será "lido" pela a API
  const lerTexto = (escrita) => {
    if (lendo) {
      setTextoLeitura(escrita);
    }
  };

  // Função que irá "ouvir" o texto que será "lido" pela a API
  useEffect(() => {
    const synthesis = window.speechSynthesis;
    const utterance = new SpeechSynthesisUtterance(textoLeitura);

    const finalizarLeitura = () => {
      if ("speechSynthesis" in window) {
        synthesis.cancel();
      }
    };

    if (lendo && textoLeitura !== "") {
      if ("speechSynthesis" in window) {
        synthesis.speak(utterance);
      }
    } else {
      finalizarLeitura();
    }

    return () => {
      finalizarLeitura();
    };
  }, [textoLeitura]);

  return (
    <>
      {modalHistorico && (
        <ModalHistoricoDemanda
          open={modalHistorico}
          setOpen={setModalHistorico}
          historico={historicoSelecionado}
          lendo={lendo}
        />
      )}
      <Paper sx={{ width: "100%", minWidth: "74rem" }} square>
        <Table sx={{ width: "100%" }} className="table-fixed">
          <TableHead sx={{ width: "100%" }}>
            <TableRow
              sx={{
                backgroundColor: "primary.main",
                minWidth: "75rem",
                width: "100%",
              }}
            >
              <th className="text-white p-2 width-75/1000">
                <Typography
                  fontSize={FontConfig.big}
                  onClick={(e) => {
                    if (lendo) {
                      e.preventDefault();
                      if (!isProposta) {
                        lerTexto(texts.demandaGerenciaModoVisualizacao.codigo);
                      } else {
                        lerTexto("PPM");
                      }
                    }
                  }}
                >
                  {!isProposta
                    ? texts.demandaGerenciaModoVisualizacao.codigo
                    : "PPM"}
                </Typography>
              </th>
              <th className="text-left text-white p-3 width-4/10">
                <Typography
                  fontSize={FontConfig.big}
                  onClick={(e) => {
                    if (lendo) {
                      e.preventDefault();
                      lerTexto(texts.demandaGerenciaModoVisualizacao.titulo);
                    }
                  }}
                >
                  {texts.demandaGerenciaModoVisualizacao.titulo}
                </Typography>
              </th>
              <th className="text-left text-white p-3">
                <Typography
                  fontSize={FontConfig.big}
                  onClick={(e) => {
                    if (lendo) {
                      e.preventDefault();
                      lerTexto(
                        texts.demandaGerenciaModoVisualizacao.solicitante
                      );
                    }
                  }}
                >
                  {texts.demandaGerenciaModoVisualizacao.solicitante}
                </Typography>
              </th>
              <th className="text-left text-white p-3">
                <Typography
                  fontSize={FontConfig.big}
                  onClick={(e) => {
                    if (lendo) {
                      e.preventDefault();
                      lerTexto(
                        texts.demandaGerenciaModoVisualizacao.departamento
                      );
                    }
                  }}
                >
                  {texts.demandaGerenciaModoVisualizacao.departamento}
                </Typography>
              </th>
              <th className="text-left text-white p-3">
                <Typography
                  fontSize={FontConfig.big}
                  onClick={(e) => {
                    if (lendo) {
                      e.preventDefault();
                      lerTexto(
                        texts.demandaGerenciaModoVisualizacao.gerenteResponsavel
                      );
                    }
                  }}
                >
                  {texts.demandaGerenciaModoVisualizacao.gerenteResponsavel}
                </Typography>
              </th>
              <th className="text-left text-white p-3 ">
                <Typography
                  fontSize={FontConfig.big}
                  onClick={(e) => {
                    if (lendo) {
                      e.preventDefault();
                      lerTexto(texts.demandaGerenciaModoVisualizacao.status);
                    }
                  }}
                >
                  {texts.demandaGerenciaModoVisualizacao.status}
                </Typography>
              </th>
              <th className="text-white p-3 width-75/1000">
                <Typography
                  fontSize={FontConfig.big}
                  onClick={(e) => {
                    if (lendo) {
                      e.preventDefault();
                      lerTexto(texts.demandaGerenciaModoVisualizacao.data);
                    }
                  }}
                >
                  {texts.demandaGerenciaModoVisualizacao.data}
                </Typography>
              </th>
            </TableRow>
          </TableHead>
          <TableBody sx={{ minWidth: "75rem", width: "100%" }}>
            {listaDemandas.map((row, index) => (
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
                  if (!lendo) {
                    onDemandaClick(row);
                  }
                }}
              >
                <td
                  className="text-center p-3 width-1/10"
                  title={row.codigoPPM}
                >
                  <Typography
                    className="truncate"
                    fontSize={FontConfig.medium}
                    onClick={(e) => {
                      if (lendo) {
                        e.preventDefault();
                        if (!isProposta) {
                          lerTexto(row.id);
                        } else {
                          lerTexto(row.codigoPPM);
                        }
                      }
                    }}
                  >
                    {!isProposta ? row.id : row.codigoPPM}
                  </Typography>
                </td>
                <td
                  className="text-left p-3 width-4/12 flex"
                  title={row.titulo}
                >
                  {(row.presenteEm == "Pauta" || row.presenteEm == "Ata") && (
                    <Box className="mr-4">
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
                  <Typography
                    className="truncate"
                    fontSize={FontConfig.medium}
                    onClick={(e) => {
                      if (lendo) {
                        e.preventDefault();
                        lerTexto(row.titulo);
                      }
                    }}
                  >
                    {row.titulo}
                  </Typography>
                </td>
                <td
                  className="text-left p-3 width-1/10"
                  title={row.solicitante.nome}
                >
                  <Typography
                    className="truncate"
                    fontSize={FontConfig.medium}
                    onClick={(e) => {
                      if (lendo) {
                        e.preventDefault();
                        lerTexto(row.solicitante.nome);
                      }
                    }}
                  >
                    {row.solicitante.nome}
                  </Typography>
                </td>
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
                      if (lendo) {
                        e.preventDefault();
                        if (row.departamento) {
                          lerTexto(row.departamento.nome);
                        } else {
                          lerTexto(
                            texts.demandaGerenciaModoVisualizacao.naoAtribuido
                          );
                        }
                      }
                    }}
                  >
                    {row.departamento
                      ? row.departamento.nome
                      : texts.demandaGerenciaModoVisualizacao.naoAtribuido}
                  </Typography>
                </td>
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
                      if (lendo) {
                        e.preventDefault();
                        if (row.gerente?.nome) {
                          lerTexto(row.gerente.nome);
                        } else {
                          lerTexto(
                            texts.demandaGerenciaModoVisualizacao.naoAtribuido
                          );
                        }
                      }
                    }}
                  >
                    {row.gerente?.nome
                      ? row.gerente.nome
                      : texts.demandaGerenciaModoVisualizacao.naoAtribuido}
                  </Typography>
                </td>
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
                    <Box className="w-full flex justify-between items-center">
                      <Typography
                        className="truncate"
                        fontSize={FontConfig.medium}
                        onClick={(e) => {
                          if (lendo) {
                            e.preventDefault();
                            lerTexto(formatarNomeStatus(row.status));
                          }
                        }}
                      >
                        {formatarNomeStatus(row.status)}
                      </Typography>
                    </Box>
                  </Box>
                </td>
                <td
                  className="p-3 width-1/10 "
                  title={DateService.getTodaysDateUSFormat(row.data)}
                >
                  <Box className="w-full gap-3 flex justify-center items-center">
                    <Typography
                      className="visualizacao-tabela-gerencia-data truncate"
                      fontSize={FontConfig.default}
                      onClick={(e) => {
                        if (lendo) {
                          e.preventDefault();
                          lerTexto(
                            DateService.getTodaysDateUSFormat(
                              DateService.getDateByMySQLFormat(row.data)
                            )
                          );
                        }
                      }}
                    >
                      {DateService.getTodaysDateUSFormat(
                        DateService.getDateByMySQLFormat(row.data)
                      )}
                    </Typography>
                    <Tooltip
                      title={texts.demandaGerenciaModoVisualizacao.historico}
                      className="visualizacao-tabela-gerencia-icon"
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
                    {isProposta && (
                      <Tooltip
                        title={texts.demandaGerenciaModoVisualizacao.chat}
                        className="visualizacao-tabela-gerencia-icon"
                      >
                        <ChatOutlinedIcon
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
  setFeedbackAbrirChat,
  lendo = false,
}) => {
  return (
    <Box
      sx={{
        display: "grid",
        gap: "1rem",
        gridTemplateColumns: "repeat(auto-fit, minmax(720px, 1fr))",
      }}
    >
      {listaDemandas?.map((demanda, index) => {
        return (
          <DemandaGerencia
            key={index}
            dados={demanda}
            tipo={isProposta ? "proposta" : "demanda"}
            setFeedbackAbrirChat={setFeedbackAbrirChat}
            onClick={() => {
              onDemandaClick(demanda);
            }}
            lendo={lendo}
          />
        );
      })}
    </Box>
  );
};

// Componente para exibição de nada encontrado
const NadaEncontrado = (props) => {
  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  const [textoLeitura, setTextoLeitura] = useState("");

  // Função que irá setar o texto que será "lido" pela a API
  const lerTexto = (escrita) => {
    if (props.lendo) {
      setTextoLeitura(escrita);
    }
  };

  // Função que irá "ouvir" o texto que será "lido" pela a API
  useEffect(() => {
    const synthesis = window.speechSynthesis;
    const utterance = new SpeechSynthesisUtterance(textoLeitura);

    const finalizarLeitura = () => {
      if ("speechSynthesis" in window) {
        synthesis.cancel();
      }
    };

    if (props.lendo && textoLeitura !== "") {
      if ("speechSynthesis" in window) {
        synthesis.speak(utterance);
      }
    } else {
      finalizarLeitura();
    }

    return () => {
      finalizarLeitura();
    };
  }, [textoLeitura]);

  return (
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
      <Typography
        fontSize={FontConfig.big}
        sx={{ color: "text.secondary", mb: 1 }}
        onClick={() => {
          lerTexto(texts.demandaGerenciaModoVisualizacao.nadaEncontrado);
        }}
      >
        {texts.demandaGerenciaModoVisualizacao.nadaEncontrado}
      </Typography>
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
