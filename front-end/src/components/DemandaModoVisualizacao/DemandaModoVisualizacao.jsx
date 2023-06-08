import React, { useContext, useState } from "react";

import {
  Box,
  Paper,
  Table,
  TableBody,
  TableHead,
  TableRow,
  Typography,
  Button,
} from "@mui/material";

import "./DemandaModoVisualizacao.css";

import Demanda from "../Demanda/Demanda";

import DateService from "../../service/dateService";
import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";
import ModalMotivoRecusa from "../ModalMotivoRecusa/ModalMotivoRecusa";
import SpeechSynthesisContext from "../../service/SpeechSynthesisContext";

// Componente para mudar o modo de visualização das demandas (Grid, tabela ou nenhuma demanda encontrada) - Usuário padrão
const DemandaModoVisualizacao = ({
  listaDemandas,
  onDemandaClick,
  nextModoVisualizacao,
  myDemandas,
}) => {
  // Verificacao para ver se retornou alignProperty, caso não tenha nada, mostra o componente "NadaEncontrado"
  if (listaDemandas.length == 0) {
    return <NadaEncontrado />;
  }

  // Verificacao para ver se o próximo modo de visualização é "TABLE", caso seja, mostra o componente "DemandaGrid" se não retorna "DemandaTable"
  if (nextModoVisualizacao == "TABLE")
    return (
      <DemandaGrid
        listaDemandas={listaDemandas}
        onDemandaClick={onDemandaClick}
        myDemandas={myDemandas}
      />
    );

  return (
    <DemandaTable
      listaDemandas={listaDemandas}
      onDemandaClick={onDemandaClick}
      myDemandas={myDemandas}
    />
  );
};

// Componente da DemandaTable
const DemandaTable = ({
  listaDemandas = [
    {
      id: 0,
      titulo: "",
      problema: "",
      proposta: "",
      motivoRecusa: "",
      status: "",
      data: "",
      solicitante: {},
    },
  ],
  onDemandaClick,
  myDemandas,
}) => {
  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  /** Context para ler o texto da tela */
  const { lerTexto, lendoTexto } = useContext(SpeechSynthesisContext);

  // useState para abrir o modal de motivo recusa
  const [abrirModal, setOpenModal] = useState(false);

  // Contexto para trocar a linguagem
  const { texts } = useContext(TextLanguageContext);

  // Guarda a demanda selecionada para abrir o modal de motivo recusa
  const [demandaSelecionada, setDemandaSelecionada] = useState();

  // Função para receber a cor do status da demanda
  function getStatusColor(status) {
    if (status == "CANCELLED") {
      return "#DA0303";
    } else if (status == "BACKLOG_REVISAO") {
      return "#C4C4C4";
    } else if (status == "BACKLOG_EDICAO") {
      return "#FFD600";
    } else if (status == "BACKLOG_APROVACAO") {
      return "#00579D";
    } else if (status == "ASSESSMENT") {
      return "#11B703";
    } else if (status == "ASSESSMENT_APROVACAO") {
      return "#F7DC6F";
    } else if (status == "DONE") {
      return "#7EB61C";
    }
  }

  // Função para formatar o nome do status da demanda
  const formatarNomeStatus = (status) => {
    if (status == "CANCELLED") {
      return texts.demandaModoVisualizacao.statusDemanda?.reprovada;
    } else if (status == "BACKLOG_REVISAO") {
      return texts.demandaModoVisualizacao.statusDemanda?.aguardandoRevisao;
    } else if (status == "BACKLOG_EDICAO") {
      return texts.demandaModoVisualizacao.statusDemanda?.aguardandoEdicao;
    } else if (status == "BACKLOG_APROVACAO") {
      return texts.demandaModoVisualizacao.statusDemanda?.emAprovacao;
    } else if (status == "ASSESSMENT") {
      return texts.demandaModoVisualizacao.statusDemanda?.aprovada;
    } else if (status == "ASSESSMENT_APROVACAO") {
      return texts.demandaModoVisualizacao.statusDemanda?.emAndamento;
    } else if (status == "DONE") {
      return texts.demandaModoVisualizacao.statusDemanda?.emDesenvolvimento;
    }
  };

  //Abre o modal de motivo recusa
  const abrirModalMotivoRecusa = (demanda) => {
    setDemandaSelecionada(demanda);
    setOpenModal(true);
  };

  return (
    <Paper sx={{ width: "100%" }} square>
      {/* Abrindo o modal de motivo recusa */}
      {abrirModal && (
        <ModalMotivoRecusa
          open={true}
          setOpen={setOpenModal}
          motivoRecusa={demandaSelecionada?.motivoRecusa}
        />
      )}
      {/* Cabeçário da tabela */}
      <Table
        className="mb-8 table-fixed"
        sx={{ width: "100%", minWidth: "74rem" }}
      >
        <TableHead>
          <TableRow sx={{ backgroundColor: "primary.main" }}>
            {/* Texto codigo */}
            <th className="text-white p-3 w-1/10">
              <Typography
                fontSize={FontConfig.big}
                onClick={(e) => {
                  if (lendoTexto) {
                    e.preventDefault();
                    lerTexto(texts.demandaModoVisualizacao.codigo);
                  }
                }}
              >
                {texts.demandaModoVisualizacao.codigo}
              </Typography>
            </th>
            {/* Texto titulo */}
            <th className="text-left text-white p-3 w-3/6">
              <Typography
                fontSize={FontConfig.big}
                onClick={(e) => {
                  if (lendoTexto) {
                    e.preventDefault();
                    lerTexto(texts.demandaModoVisualizacao.titulo);
                  }
                }}
              >
                {texts.demandaModoVisualizacao.titulo}
              </Typography>
            </th>
            {/* Texto status */}
            {myDemandas && (
              <th className="text-left text-white p-3 w-1/6">
                <Typography
                  fontSize={FontConfig.big}
                  onClick={(e) => {
                    if (lendoTexto) {
                      e.preventDefault();
                      lerTexto(texts.demandaModoVisualizacao.statusString);
                    }
                  }}
                >
                  {texts.demandaModoVisualizacao.statusString}
                </Typography>
              </th>
            )}
            {/* Texto data */}
            <th className="text-white p-3 w-1/12">
              <Typography
                fontSize={FontConfig.big}
                onClick={(e) => {
                  if (lendoTexto) {
                    e.preventDefault();
                    lerTexto(texts.demandaModoVisualizacao.data);
                  }
                }}
              >
                {texts.demandaModoVisualizacao.data}
              </Typography>
            </th>
          </TableRow>
        </TableHead>
        <TableBody>
          {/* Mapeando as demandas, mostra cada linha de preenchimento de demanda */}
          {listaDemandas.map((row, index) => (
            <TableRow
              className="cursor-pointer tabela-linha-demanda"
              hover
              key={index}
              sx={{
                "&:last-child td, &:last-child th": { border: 0 },
              }}
              onClick={() => {
                if (!lendoTexto) {
                  onDemandaClick(row);
                }
              }}
            >
              {/* codigo da demanda */}
              <td className="text-center p-3" title={row.id}>
                <Typography
                  className="truncate"
                  fontSize={FontConfig.medium}
                  onClick={(e) => {
                    if (lendoTexto) {
                      e.preventDefault();
                      lerTexto(row.id);
                    }
                  }}
                >
                  {row.id}
                </Typography>
              </td>
              {/* titulo da demanda */}
              <td className="text-left p-3" title={row.titulo}>
                <Typography
                  className="truncate"
                  fontSize={FontConfig.medium}
                  onClick={(e) => {
                    if (lendoTexto) {
                      e.preventDefault();
                      lerTexto(row.titulo);
                    }
                  }}
                >
                  {row.titulo}
                </Typography>
              </td>
              {/* status das minhas demandas e botão de motivo da recusa */}
              {myDemandas && (
                <td
                  className="text-left p-3"
                  title={formatarNomeStatus(row.status)}
                >
                  <Box className="flex items-center gap-2 text-center">
                    {/* cor do status */}
                    <Box
                      sx={{
                        backgroundColor: getStatusColor(row.status),
                        width: "12px",
                        height: "2rem",
                        borderRadius: "3px",
                      }}
                    />
                    <Box className="w-full flex justify-between items-center">
                      {/* nome do status */}
                      <Typography
                        className="truncate"
                        fontSize={FontConfig.medium}
                        onClick={(e) => {
                          if (lendoTexto) {
                            e.preventDefault();
                            lerTexto(formatarNomeStatus(row.status));
                          }
                        }}
                      >
                        {formatarNomeStatus(row.status)}
                      </Typography>
                      {/* Botao do motivo da recusa caso foi cancelado ou esperando edição */}
                      {row.status == "CANCELLED" ||
                      row.status == "BACKLOG_EDICAO" ? (
                        <Button
                          className="tabela-linha-demanda-motivo-recusa"
                          onClick={(e) => {
                            e.preventDefault();
                            if (lendoTexto) {
                              lerTexto(texts.demandaModoVisualizacao.motivo);
                            } else {
                              abrirModalMotivoRecusa(row);
                            }
                          }}
                          variant="contained"
                          disableElevation
                          size="small"
                        >
                          {texts.demandaModoVisualizacao.motivo}
                        </Button>
                      ) : null}
                    </Box>
                  </Box>
                </td>
              )}
              {/* data da demanda */}
              <td
                className="text-center p-3"
                title={DateService.getTodaysDateUSFormat(
                  row.data,
                  texts.linguagem
                )}
              >
                <Typography
                  className="truncate"
                  fontSize={FontConfig.default}
                  onClick={(e) => {
                    if (lendoTexto) {
                      e.preventDefault();
                      lerTexto(
                        DateService.getTodaysDateUSFormat(
                          row.data,
                          texts.linguagem
                        )
                      );
                    }
                  }}
                >
                  {DateService.getTodaysDateUSFormat(row.data, texts.linguagem)}
                </Typography>
              </td>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </Paper>
  );
};

// Componente para exibir as demanda em forma de grid
const DemandaGrid = ({ listaDemandas, onDemandaClick }) => {
  return (
    <Box
      sx={{
        display: "grid",
        gap: "1rem",
        gridTemplateColumns: "repeat(auto-fit, minmax(650px, 1fr))",
      }}
    >
      {listaDemandas?.map((e, index) => (
        // mostra o Componente de demanda em relação a quantidade de demandas que tem
        <Demanda
          key={index}
          demanda={e}
          onClick={() => {
            onDemandaClick(e);
          }}
        />
      ))}
    </Box>
  );
};

// Componente para exibir nada encontrado
const NadaEncontrado = (props) => {
  // Contexto para trocar a linguagem
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  /** Context para ler o texto da tela */
  const { lerTexto } = useContext(SpeechSynthesisContext);

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
      {/* texto nada encontrado */}
      <Typography
        fontSize={FontConfig.big}
        sx={{ color: "text.secondary", mb: 1 }}
        onClick={() => {
          lerTexto(texts.demandaModoVisualizacao.nadaEncontrado);
        }}
      >
        {texts.demandaModoVisualizacao.nadaEncontrado}
      </Typography>
      {/* texto tente novamente mais tarde */}
      <Typography
        fontSize={FontConfig.medium}
        sx={{ color: "text.secondary", mb: 1 }}
        onClick={() => {
          lerTexto(texts.demandaModoVisualizacao.tenteNovamenteMaisTarde);
        }}
      >
        {texts.demandaModoVisualizacao.tenteNovamenteMaisTarde}
      </Typography>
    </Box>
  );
};

export default DemandaModoVisualizacao;
