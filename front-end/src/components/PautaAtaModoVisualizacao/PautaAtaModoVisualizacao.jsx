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

import "./PautaAtaModoVisualizacao.css";

import Pautas from "../Pauta/Pauta";

import DeleteOutlineOutlinedIcon from "@mui/icons-material/DeleteOutlineOutlined";

import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";
import DateService from "../../service/dateService";
import EntitiesObjectService from "../../service/entitiesObjectService";

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
  lendo,
  texto,
  setTexto,
}) => {
  // Context para alterar a linguagem do sistema
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

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

  // Função que retorna a cor do status da ata
  const getStatusColor = (ata = EntitiesObjectService.ata()) => {
    if (ata.propostas[0]?.parecerDG != null) return "success.main";
    return "#C4C4C4";
  };

  const isApreciada = (ata = EntitiesObjectService.ata()) => {
    return ata.propostas[0]?.parecerDG != null;
  };

  // Função que irá setar o texto que será "lido" pela a API
  const lerTexto = (escrita) => {
    if (lendo) {
      setTexto(escrita);
    }
  };

  // Função que irá "ouvir" o texto que será "lido" pela a API
  useEffect(() => {
    if (lendo && texto != "") {
      if ("speechSynthesis" in window) {
        const synthesis = window.speechSynthesis;
        const utterance = new SpeechSynthesisUtterance(texto);
        synthesis.speak(utterance);
      }
      setTexto("");
    }
  }, [texto]);

  return (
    <Paper sx={{ width: "100%", minWidth: "81rem" }} square>
      <Table sx={{ width: "100%" }} className="table-fixed">
        <TableHead>
          <TableRow sx={{ backgroundColor: "primary.main" }}>
            <th className="text-white p-2 width-75/1000">
              <Typography
                fontSize={FontConfig.big}
                onClick={() => {
                  lerTexto(texts.pautaAtaModoVisualizacao.numeroSequencial);
                }}
              >
                {texts.pautaAtaModoVisualizacao.numeroSequencial}
              </Typography>
            </th>
            <th className="text-left text-white p-3">
              <Typography
                fontSize={FontConfig.big}
                onClick={() => {
                  lerTexto(texts.pautaAtaModoVisualizacao.comissao);
                }}
              >
                {texts.pautaAtaModoVisualizacao.comissao}
              </Typography>
            </th>
            <th className="text-left text-white p-3 width-3/10">
              <Typography
                fontSize={FontConfig.big}
                onClick={() => {
                  lerTexto(texts.pautaAtaModoVisualizacao.analistaResponsavel);
                }}
              >
                {texts.pautaAtaModoVisualizacao.analistaResponsavel}
              </Typography>
            </th>
            <th className="text-center text-white p-3 width-2/10">
              <Typography
                fontSize={FontConfig.big}
                onClick={() => {
                  lerTexto(texts.pautaAtaModoVisualizacao.data);
                }}
              >
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
                <Typography
                  className="truncate"
                  fontSize={FontConfig.medium}
                  onClick={() => {
                    lerTexto(row.numeroSequencial);
                  }}
                >
                  {row.numeroSequencial}
                </Typography>
              </td>
              <td className="text-left p-3" title={row.comissao}>
                <Typography
                  className="truncate"
                  fontSize={FontConfig.medium}
                  onClick={() => {
                    lerTexto(row.comissao.nomeForum);
                  }}
                >
                  {row.comissao.siglaForum} - {row.comissao.nomeForum}
                </Typography>
              </td>
              <td
                className="text-left p-3"
                title={row.analistaResponsavel?.nome}
              >
                <Typography
                  className="truncate"
                  fontSize={FontConfig.medium}
                  onClick={() => {
                    lerTexto(row.analistaResponsavel?.nome);
                  }}
                >
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
                    onClick={() => {
                      lerTexto(getDataFormatada(row.dataReuniao));
                    }}
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
                  className="flex justify-center items-center text-right p-3"
                  title={getDataFormatada(row.dataReuniao)}
                >
                  <Typography
                    className="truncate"
                    fontSize={FontConfig.medium}
                    onClick={() => {
                      lerTexto(getDataFormatada(row.dataReuniao));
                    }}
                  >
                    {getDataFormatada(row.dataReuniao)}
                  </Typography>

                  <Tooltip
                    title={
                      isApreciada(row)
                        ? texts.pauta.jaApreciada
                        : texts.pauta.naoApreciada
                    }
                  >
                    <Box
                      className="w-6 h-4 ml-3 rounded"
                      sx={{ backgroundColor: getStatusColor(row) }}
                    />
                  </Tooltip>
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

const NadaEncontrado = (props) => {
  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  // Função que irá setar o texto que será "lido" pela a API
  const lerTexto = (texto) => {
    if (props.lendo) {
      props.setTexto(texto);
    }
  };

  // Função que irá "ouvir" o texto que será "lido" pela a API
  useEffect(() => {
    if (props.lendo && props.texto != "") {
      if ("speechSynthesis" in window) {
        const synthesis = window.speechSynthesis;
        const utterance = new SpeechSynthesisUtterance(props.texto);
        synthesis.speak(utterance);
      }
      props.setTexto("");
    }
  }, [props.texto]);

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
        onClick={() => {
          lerTexto(texts.pautaAtaModoVisualizacao.nadaEncontrado);
        }}
      >
        {texts.pautaAtaModoVisualizacao.nadaEncontrado}
      </Typography>
      <Typography
        fontSize={FontConfig.medium}
        sx={{ color: "text.secondary", mb: 1 }}
        onClick={() => {
          lerTexto(texts.pautaAtaModoVisualizacao.tenteNovamenteMaisTarde);
        }}
      >
        {texts.pautaAtaModoVisualizacao.tenteNovamenteMaisTarde}
      </Typography>
    </Box>
  );
};

export default PautaAtaModoVisualizacao;
