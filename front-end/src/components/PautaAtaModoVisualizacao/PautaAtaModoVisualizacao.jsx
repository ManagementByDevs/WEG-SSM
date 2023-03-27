import React, { useContext } from "react";

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

import "./PautaAtaModoVisualizacao.css";

import Pautas from "../Pauta/Pauta";

import DeleteOutlineOutlinedIcon from "@mui/icons-material/DeleteOutlineOutlined";

import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";
import DateService from "../../service/dateService";

const PautaAtaModoVisualizacao = ({
  listaPautas,
  onItemClick,
  nextModoVisualizacao,
  isAta = false,
  setPautaSelecionada = () => {},
}) => {
  if (listaPautas.length == 0) {
    return <NadaEncontrado />;
  }

  if (nextModoVisualizacao == "TABLE")
    return (
      <PautaGrid
        listaPautas={listaPautas}
        onItemClick={onItemClick}
        isAta={isAta}
        setPautaSelecionada={setPautaSelecionada}
      />
    );
  return (
    <PautaTable
      listaPautas={listaPautas}
      onItemClick={onItemClick}
      isAta={isAta}
      setPautaSelecionada={setPautaSelecionada}
    />
  );
};

const PautaTable = ({
  listaPautas = [
    {
      id: 0,
      numeroSequencial: 0,
      dataReuniao: "",
      comissao: { idForum: 0, siglaForum: "", nomeForum: "" },
      propostas: [{}],
      analistaResponsavel: { nome: "" },
    },
  ],
  onItemClick,
  isAta,
  setPautaSelecionada = () => {},
}) => {
  // Context para alterar a linguagem do sistema
  const { texts, setTexts } = useContext(TextLanguageContext);

  const { FontConfig, setFontConfig } = useContext(FontContext);

  // Retorna data formatada para melhor leitura
  const getDataFormatada = (dataInicio) => {
    let dateInicio = new Date(DateService.getDateByMySQLFormat(dataInicio));

    return `${DateService.getTodaysDateUSFormat(dataInicio)} ${
      texts.pautaAtaModoVisualizacao.as
    } ${dateInicio.getHours()}:${
      dateInicio.getMinutes() < 10
        ? "0" + dateInicio.getMinutes()
        : dateInicio.getMinutes()
    }`;
  };

  return (
    <Paper sx={{ width: "100%", minWidth: "81rem" }} square>
      <Table sx={{ width: "100%" }} className="table-fixed">
        <TableHead>
          <TableRow sx={{ backgroundColor: "primary.main" }}>
            <th className="text-white p-2 width-75/1000">
              <Typography fontSize={FontConfig.big}>
                {texts.pautaAtaModoVisualizacao.numeroSequencial}
              </Typography>
            </th>
            <th className="text-left text-white p-3">
              <Typography fontSize={FontConfig.big}>
                {texts.pautaAtaModoVisualizacao.comissao}
              </Typography>
            </th>
            <th className="text-left text-white p-3 width-3/10">
              <Typography fontSize={FontConfig.big}>
                {texts.pautaAtaModoVisualizacao.analistaResponsavel}
              </Typography>
            </th>
            <th className="text-center text-white p-3 width-2/10">
              <Typography fontSize={FontConfig.big}>
                {texts.pautaAtaModoVisualizacao.data}
              </Typography>
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
                <Typography className="truncate" fontSize={FontConfig.medium}>
                  {row.numeroSequencial}
                </Typography>
              </td>
              <td className="text-left p-3" title={row.comissao}>
                <Typography className="truncate" fontSize={FontConfig.medium}>
                  {row.comissao.siglaForum} - {row.comissao.nomeForum}
                </Typography>
              </td>
              <td
                className="text-left p-3"
                title={row.analistaResponsavel?.nome}
              >
                <Typography className="truncate" fontSize={FontConfig.medium}>
                  {row.analistaResponsavel?.nome}
                </Typography>
              </td>
              {!isAta ? (
                <td
                  className="flex justify-center p-3"
                  title={getDataFormatada(row.dataReuniao)}
                >
                  <Typography
                    className="tabela-linha-pauta-data truncate"
                    fontSize={FontConfig.medium}
                  >
                    {getDataFormatada(row.dataReuniao)}
                  </Typography>
                  <Tooltip title={texts.pautaAtaModoVisualizacao.deletar}>
                    <DeleteOutlineOutlinedIcon
                      className="delay-120 hover:scale-110 duration-300 tabela-linha-pauta-icon"
                      size="small"
                      sx={{
                        color: "icon.main",
                        cursor: "pointer",
                      }}
                      onClick={(e) => {
                        setPautaSelecionada(row);
                        e.stopPropagation();
                      }}
                    />
                  </Tooltip>
                </td>
              ) : (
                <td
                  className="flex justify-center text-right p-3"
                  title={getDataFormatada(row.dataReuniao)}
                >
                  <Typography className="truncate" fontSize={FontConfig.medium}>
                    {getDataFormatada(row.dataReuniao)}
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

const PautaGrid = ({
  listaPautas = [
    {
      id: 0,
      numeroSequencial: 0,
      dataReuniao: "",
      comissao: { idForum: 0, siglaForum: "", nomeForum: "" },
      propostas: [{}],
      analistaResponsavel: { nome: "" },
    },
  ],
  onItemClick,
  isAta,
  setPautaSelecionada = () => {},
}) => {
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
            setPautaSelecionada={setPautaSelecionada}
          />
        );
      })}
    </Box>
  );
};

const NadaEncontrado = () => {
  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  return (
    <Box
      sx={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        flexDirection: "column",
        height: "100%",
      }}
    >
      <Typography
        fontSize={FontConfig.big}
        sx={{ color: "text.secondary", mb: 1 }}
      >
        {texts.pautaAtaModoVisualizacao.nadaEncontrado}
      </Typography>
      <Typography
        fontSize={FontConfig.medium}
        sx={{ color: "text.secondary", mb: 1 }}
      >
        {texts.pautaAtaModoVisualizacao.tenteNovamenteMaisTarde}
      </Typography>
    </Box>
  );
};

export default PautaAtaModoVisualizacao;
