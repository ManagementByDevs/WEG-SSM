import React, { useContext } from "react";

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

import "./PautaAtaModoVisualizacao.css";

import Pautas from "../Pauta/Pauta";

import DeleteOutlineOutlinedIcon from "@mui/icons-material/DeleteOutlineOutlined";

import FontContext from "../../service/FontContext";
import DateService from "../../service/dateService";

const PautaAtaModoVisualizacao = ({
  listaPautas,
  onItemClick,
  nextModoVisualizacao,
  isAta = false,
}) => {
  if (nextModoVisualizacao == "TABLE")
    return (
      <PautaGrid
        listaPautas={listaPautas}
        onItemClick={onItemClick}
        isAta={isAta}
      />
    );
  return (
    <PautaTable
      listaPautas={listaPautas}
      onItemClick={onItemClick}
      isAta={isAta}
    />
  );
};

const PautaTable = ({
  listaPautas = [
    {
      id: 0,
      numeroSequencial: 0,
      inicioDataReuniao: "",
      fimDataReuniao: "",
      comissao: "",
      propostas: [{}],
      visibilidade: false,
      analista: { nome: "" },
    },
  ],
  onItemClick,
  isAta,
}) => {
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // Retorna data formatada para melhor leitura
  const getDataFormatado = (dataInicio, dataFim) => {
    let dateInicio = new Date(DateService.getDateByMySQLFormat(dataInicio));
    let dateFim = new Date(DateService.getDateByMySQLFormat(dataFim));

    return `${DateService.getTodaysDateUSFormat(
      dataInicio
    )} às ${dateInicio.getHours()}:${
      dateInicio.getMinutes() < 10
        ? "0" + dateInicio.getMinutes()
        : dateInicio.getMinutes()
    } - ${dateFim.getHours()}:${
      dateFim.getMinutes() < 10
        ? "0" + dateFim.getMinutes()
        : dateFim.getMinutes()
    }`;
  };

  return (
    <Paper sx={{ width: "100%" }} square>
      <Table sx={{ width: "100%" }}>
        <TableHead>
          <TableRow sx={{ backgroundColor: "primary.main" }}>
            <th className="text-white p-2 w-1/10">
              <Typography fontSize={FontConfig.big}>N. Sequencial</Typography>
            </th>
            <th className="text-left text-white p-3 w-2/6">
              <Typography fontSize={FontConfig.big}>Comissão</Typography>
            </th>
            <th className="text-left text-white p-3 w-1/6">
              <Typography fontSize={FontConfig.big}>Anal. Resp.</Typography>
            </th>
            <th className="text-right text-white p-3">
              <Typography fontSize={FontConfig.big}>Data</Typography>
            </th>
          </TableRow>
        </TableHead>
        <TableBody>
          {listaPautas.map((row, index) => (
            <TableRow
              className="cursor-pointer tabela-linha-pauta"
              hover
              key={index}
              sx={{
                "&:last-child td, &:last-child th": { border: 0 },
              }}
              onClick={() => {
                onItemClick(row);
              }}
            >
              <td className="text-center p-3" title={row.numeroSequencial}>
                <Typography fontSize={FontConfig.medium}>
                  {row.numeroSequencial}
                </Typography>
              </td>
              <td className="text-left p-3" title={row.comissao}>
                <Typography fontSize={FontConfig.medium}>
                  {row.comissao}
                </Typography>
              </td>
              <td className="text-left p-3" title={row.analista.nome}>
                <Typography fontSize={FontConfig.medium}>
                  {row.analista.nome}
                </Typography>
              </td>
              {!isAta ? (
                <td
                  className="flex justify-end text-right p-3"
                  title={getDataFormatado(
                    row.inicioDataReuniao,
                    row.fimDataReuniao
                  )}
                >
                  <Typography
                    className="tabela-linha-pauta-data"
                    fontSize={FontConfig.medium}
                  >
                    {getDataFormatado(
                      row.inicioDataReuniao,
                      row.fimDataReuniao
                    )}
                  </Typography>
                  <DeleteOutlineOutlinedIcon
                    className="delay-120 hover:scale-110 duration-300 tabela-linha-pauta-icon"
                    sx={{
                      color: "icon.main",
                      cursor: "pointer",
                      fontSize: "30px",
                    }}
                  />
                </td>
              ) : (
                <td
                  className="flex justify-end text-right p-3"
                  title={getDataFormatado(
                    row.inicioDataReuniao,
                    row.fimDataReuniao
                  )}
                >
                  <Typography fontSize={FontConfig.medium}>
                    {getDataFormatado(
                      row.inicioDataReuniao,
                      row.fimDataReuniao
                    )}
                  </Typography>
                </td>
              )}
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </Paper>
  );
};

const PautaGrid = ({ listaPautas, onItemClick, isAta }) => {
  return (
    <Box
      sx={{
        display: "grid",
        gap: "1rem",
        gridTemplateColumns: "repeat(auto-fit, minmax(700px, 1fr))",
      }}
    >
      {listaPautas?.map((pauta, index) => {
        return (
          <Pautas
            key={index}
            dados={pauta}
            tipo={!isAta ? "pauta" : "ata"}
            onItemClick={onItemClick}
          />
        );
      })}
    </Box>
  );
};

export default PautaAtaModoVisualizacao;
