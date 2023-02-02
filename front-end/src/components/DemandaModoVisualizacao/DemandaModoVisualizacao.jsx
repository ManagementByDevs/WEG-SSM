import React, { useContext } from "react";

import {
  Box,
  Paper,
  Table,
  TableBody,
  TableHead,
  TableRow,
  Typography,
} from "@mui/material";

import Demanda from "../Demanda/Demanda";

import DateService from "../../service/dateService";

import FontContext from "../../service/FontContext";

const DemandaModoVisualizacao = ({
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
    }
  }

  // Função para formatar o nome do status da demanda
  const formatarNomeStatus = (status) => {
    if (status == "CANCELLED") {
      return "Reprovada";
    } else if (status == "BACKLOG_REVISAO") {
      return "Aguardando Revisão";
    } else if (status == "BACKLOG_EDICAO") {
      return "Aguardando Edição";
    } else if (status == "BACKLOG_APROVACAO") {
      return "Em Aprovação";
    } else if (status == "ASSESSMENT") {
      return "Aprovada";
    }
  };

  return (
    <Paper sx={{ width: "100%" }} square>
      <Table className="mb-8" sx={{ width: "100%" }}>
        <TableHead>
          <TableRow sx={{ backgroundColor: "primary.main" }}>
            <th className="text-white p-1 w-1/6">
              <Typography fontSize={FontConfig.big}>Cód. Sequencial</Typography>
            </th>
            <th className="text-white">
              <Typography fontSize={FontConfig.big}>Título</Typography>
            </th>
            <th className=" text-white">
              <Typography fontSize={FontConfig.big}>Status</Typography>
            </th>
            <th className=" text-white">
              <Typography fontSize={FontConfig.big}>Data</Typography>
            </th>
          </TableRow>
        </TableHead>
        <TableBody>
          {listaDemandas.map((row, index) => (
            <TableRow
              hover
              key={index}
              sx={{
                "&:last-child td, &:last-child th": { border: 0 },
              }}
              onClick={() => {
                onDemandaClick(row);
              }}
            >
              <td className="text-center p-2">
                <Typography fontSize={FontConfig.medium}>{row.id}</Typography>
              </td>
              <td className="text-center">
                <Typography fontSize={FontConfig.medium}>
                  {row.titulo}
                </Typography>
              </td>
              <td className="text-left">
                <Box className="flex items-center gap-2 text-center">
                  <Box
                    sx={{
                      backgroundColor: getStatusColor(row.status),
                      width: "12px",
                      height: "1rem",
                      borderRadius: "3px",
                    }}
                  />
                  <Typography fontSize={FontConfig.medium}>
                    {formatarNomeStatus(row.status)}
                  </Typography>
                </Box>
              </td>
              <td className="text-center">
                <Typography fontSize={FontConfig.default}>
                  {/* {DateService.getTodaysDateUSFormat(row.data)} */}
                </Typography>
              </td>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </Paper>
  );
};

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

export default DemandaModoVisualizacao;
