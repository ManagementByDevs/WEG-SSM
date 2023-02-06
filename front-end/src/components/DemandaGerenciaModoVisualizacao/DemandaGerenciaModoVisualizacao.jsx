import React, { useContext, useState } from "react";

import {
  Box,
  IconButton,
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

import HistoryOutlinedIcon from "@mui/icons-material/HistoryOutlined";

import FontContext from "../../service/FontContext";
import DateService from "../../service/dateService";

const DemandaGerenciaModoVisualizacao = ({
  listaDemandas,
  onDemandaClick,
  nextModoVisualizacao,
}) => {
  if (nextModoVisualizacao == "TABLE")
    return (
      <DemandaGrid
        listaDemandas={listaDemandas}
        onDemandaClick={onDemandaClick}
      />
    );
  return (
    <DemandaTable
      listaDemandas={listaDemandas}
      onDemandaClick={onDemandaClick}
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
}) => {
  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);
  // Controla o estado do modal de histórico da demanda
  const [modalHistorico, setModalHistorico] = useState(false);

  // Função para receber a cor do status da demanda
  const getStatusColor = (status) => {
    if (status === "BACKLOG_REVISAO") {
      return "#00579D";
    } else if (status === "ASSESSMENT") {
      return "#8862A2";
    }
  };

  const formatarNomeStatus = (status) => {
    if (status == "BACKLOG_REVISAO") {
      return "Backlog";
    } else if (status == "ASSESSMENT") {
      return "Assessment";
    }
  };

  const abrirModalHistorico = () => {
    setModalHistorico(true);
  };
  console.log(":", listaDemandas);
  return (
    <>
      {modalHistorico && (
        <ModalHistoricoDemanda
          open={modalHistorico}
          setOpen={setModalHistorico}
        />
      )}
      <Paper sx={{ width: "100%" }} square>
        <Table className="mb-8" sx={{ width: "100%" }}>
          <TableHead>
            <TableRow sx={{ backgroundColor: "primary.main" }}>
              <th className="text-white p-2 w-1/10">
                <Typography fontSize={FontConfig.big}>Código</Typography>
              </th>
              <th className="text-left text-white p-3 w-2/6">
                <Typography fontSize={FontConfig.big}>Título</Typography>
              </th>
              <th className="text-left text-white p-3 w-1/6">
                <Typography fontSize={FontConfig.big}>Solicitante</Typography>
              </th>
              <th className="text-left text-white p-3 w-1/10">
                <Typography fontSize={FontConfig.big}>Departamento</Typography>
              </th>
              <th className="text-left text-white p-3 w-1/6">
                <Typography fontSize={FontConfig.big}>
                  Gerente Responsável
                </Typography>
              </th>
              <th className="text-left text-white p-3 w-1/10">
                <Typography fontSize={FontConfig.big}>Status</Typography>
              </th>
              <th className="text-white p-3 w-1/12">
                <Typography fontSize={FontConfig.big}>Data</Typography>
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
                <td className="text-center p-3 w-fit">
                  <Typography fontSize={FontConfig.medium}>{row.id}</Typography>
                </td>
                <td className="text-left p-3">
                  <Typography fontSize={FontConfig.medium}>
                    {row.titulo}
                  </Typography>
                </td>
                <td className="text-left p-3">
                  <Typography fontSize={FontConfig.medium}>
                    {row.solicitante.nome}
                  </Typography>
                </td>
                <td className="text-left p-3">
                  <Typography fontSize={FontConfig.medium}>
                    {row.departamento ? row.departamento : "Não atribuído"}
                  </Typography>
                </td>
                <td className="text-left p-3">
                  <Typography fontSize={FontConfig.medium}>
                    {row.gerente?.nome ? row.gerente.nome : "Não atribuído"}
                  </Typography>
                </td>
                <td className="text-left p-3">
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
                      <Typography fontSize={FontConfig.medium}>
                        {formatarNomeStatus(row.status)}
                      </Typography>
                    </Box>
                  </Box>
                </td>
                <td className="h-16 flex justify-center items-center p-3">
                  <Typography
                    className="visualizacao-tabela-gerencia-data"
                    fontSize={FontConfig.default}
                  >
                    {DateService.getTodaysDateUSFormat(row.data)}
                  </Typography>
                  <Tooltip
                    title="Histórico"
                    className="visualizacao-tabela-gerencia-historico"
                  >
                    <IconButton
                      size="small"
                      onClick={(e) => {
                        e.stopPropagation();
                        abrirModalHistorico();
                      }}
                    >
                      <HistoryOutlinedIcon
                        size="small"
                        className="delay-120 hover:scale-110 duration-300"
                        sx={{
                          color: "icon.main",
                          cursor: "pointer",
                        }}
                      />
                    </IconButton>
                  </Tooltip>
                </td>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </Paper>
    </>
  );
};

const DemandaGrid = ({ listaDemandas, onDemandaClick }) => {
  return (
    <Box
      id="sextaDemandas"
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
            tipo="demanda"
            onClick={() => {
              onDemandaClick(demanda);
            }}
          />
        );
      })}
    </Box>
  );
};

export default DemandaGerenciaModoVisualizacao;
