import React, { useContext, useState } from "react";

import { Box, Paper, Table, TableBody, TableHead, TableRow, Typography, Button } from "@mui/material";

import "./DemandaModoVisualizacao.css";

import Demanda from "../Demanda/Demanda";

import DateService from "../../service/dateService";
import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";
import ModalMotivoRecusa from "../ModalMotivoRecusa/ModalMotivoRecusa";

// Componente para mudar o modo de visualização das demandas (Grid, tabela ou nenhuma demanda encontrada) - Usuário padrão
const DemandaModoVisualizacao = ({
  listaDemandas,
  onDemandaClick,
  nextModoVisualizacao,
  myDemandas,
}) => {
  if (listaDemandas.length == 0) {
    return <NadaEncontrado />;
  }

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
  const { FontConfig, setFontConfig } = useContext(FontContext);

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
    }
  }

  // Função para formatar o nome do status da demanda
  const formatarNomeStatus = (status) => {
    if (status == "CANCELLED") {
      return texts.demandaModoVisualizacao.status.reprovada;
    } else if (status == "BACKLOG_REVISAO") {
      return texts.demandaModoVisualizacao.status.aguardandoRevisao;
    } else if (status == "BACKLOG_EDICAO") {
      return texts.demandaModoVisualizacao.status.aguardandoEdicao;
    } else if (status == "BACKLOG_APROVACAO") {
      return texts.demandaModoVisualizacao.status.emAprovacao;
    } else if (status == "ASSESSMENT") {
      return texts.demandaModoVisualizacao.status.aprovada;
    } else if (status == "ASSESSMENT_APROVACAO") {
      return texts.demandaModoVisualizacao.status.emAndamento;
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
      <Table className="mb-8 table-fixed" sx={{ width: "100%" }}>
        <TableHead>
          <TableRow sx={{ backgroundColor: "primary.main" }}>
            <th className="text-white p-3 w-1/10">
              <Typography fontSize={FontConfig.big}>
                {texts.demandaModoVisualizacao.codigo}
              </Typography>
            </th>
            <th className="text-left text-white p-3 w-3/6">
              <Typography fontSize={FontConfig.big}>
                {texts.demandaModoVisualizacao.titulo}
              </Typography>
            </th>
            {myDemandas && (
              <th className="text-left text-white p-3 w-1/6">
                <Typography fontSize={FontConfig.big}>
                  {texts.demandaModoVisualizacao.status}
                </Typography>
              </th>
            )}
            <th className="text-white p-3 w-1/12">
              <Typography fontSize={FontConfig.big}>
                {texts.demandaModoVisualizacao.data}
              </Typography>
            </th>
          </TableRow>
        </TableHead>
        <TableBody>
          {listaDemandas.map((row, index) => (
            <TableRow
              className="cursor-pointer tabela-linha-demanda"
              hover
              key={index}
              sx={{
                "&:last-child td, &:last-child th": { border: 0 },
              }}
              onClick={() => {
                onDemandaClick(row);
              }}
            >
              <td className="text-center p-3" title={row.id}>
                <Typography className="truncate" fontSize={FontConfig.medium}>
                  {row.id}
                </Typography>
              </td>
              <td className="text-left p-3" title={row.titulo}>
                <Typography className="truncate" fontSize={FontConfig.medium}>
                  {row.titulo}
                </Typography>
              </td>
              {myDemandas && (
                <td
                  className="text-left p-3"
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
                      >
                        {formatarNomeStatus(row.status)}
                      </Typography>
                      {row.status == "CANCELLED" ||
                        row.status == "BACKLOG_EDICAO" ? (
                        <Button
                          className="tabela-linha-demanda-motivo-recusa"
                          onClick={(e) => {
                            e.stopPropagation();
                            abrirModalMotivoRecusa(row);
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
              <td
                className="text-center p-3"
                title={DateService.getTodaysDateUSFormat(row.data)}
              >
                <Typography className="truncate" fontSize={FontConfig.default}>
                  {DateService.getTodaysDateUSFormat(row.data)}
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
const NadaEncontrado = () => {

  // Contexto para trocar a linguagem
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

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
      >
        {texts.demandaModoVisualizacao.nadaEncontrado}
      </Typography>
      <Typography
        fontSize={FontConfig.medium}
        sx={{ color: "text.secondary", mb: 1 }}
      >
        {texts.demandaModoVisualizacao.tenteNovamenteMaisTarde}
      </Typography>
    </Box>
  );
};

export default DemandaModoVisualizacao;