import React, { useContext, useState, useEffect } from "react";

import { Box, Paper, Table, TableBody, TableHead, TableRow, Tooltip, Typography } from "@mui/material";

import "./PautaAtaModoVisualizacao.css";

import Pautas from "../Pauta/Pauta";

import PublicIcon from "@mui/icons-material/Public";
import PublicOffIcon from "@mui/icons-material/PublicOff";
import DeleteOutlineOutlinedIcon from "@mui/icons-material/DeleteOutlineOutlined";

import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";
import DateService from "../../service/dateService";
import EntitiesObjectService from "../../service/entitiesObjectService";
import SpeechSynthesisContext from "../../service/SpeechSynthesisContext";

const PautaAtaModoVisualizacao = ({
  listaPautas,
  onItemClick,
  nextModoVisualizacao,
  isAta = false,
  setPautaSelecionada = () => { },
}) => {
  // Verificação utilizada para aparecer a tela de nenhum resultado encontrado
  if (listaPautas.length == 0) {
    return <NadaEncontrado />;
  }

  // Verificação utilizada para mostrar o formato grid ou table
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

//Função para mostra a pauta em formato de tabela
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
  setPautaSelecionada = () => { },
}) => {

  // Context para alterar a linguagem do sistema
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  /** Context para ler o texto da tela */
  const { lendoTexto, lerTexto, librasAtivo } = useContext(SpeechSynthesisContext);

  // Retorna data formatada para melhor leitura
  const getDataFormatada = (dataInicio) => {
    return DateService.getTodaysDateUSFormat(dataInicio, texts.linguagem);
  };

  // Função que retorna a cor do status da ata
  const getStatusColor = (ata = EntitiesObjectService.ata()) => {
    if (ata.propostas[0]?.parecerDG != null || !ata.publicada)
      return "success.main";
    return "#C4C4C4";
  };

  // Função para mostrar se a ata já foi apreciada pela DG
  const isApreciada = (ata = EntitiesObjectService.ata()) => {
    return ata.parecerDG;
  };

  return (
    <Paper sx={{ width: "100%", minWidth: "74rem" }} square>
      <Table sx={{ width: "100%" }} className="table-fixed">
        <TableHead>
          <TableRow sx={{ backgroundColor: "primary.main" }}>
            <th className="text-white p-2 width-75/1000">
              <Typography
                fontSize={FontConfig.big}
                onClick={(e) => {
                  if (lendoTexto) {
                    e.stopPropagation();
                    lerTexto(texts.pautaAtaModoVisualizacao.numeroSequencial);
                  } else if (librasAtivo) {
                    e.stopPropagation(); 
                  }
                }}
              >
                {texts.pautaAtaModoVisualizacao.numeroSequencial}
              </Typography>
            </th>
            <th className="text-left text-white p-3">
              <Typography
                fontSize={FontConfig.big}
                onClick={(e) => {
                  if (lendoTexto) {
                    e.stopPropagation();
                    lerTexto(texts.pautaAtaModoVisualizacao.comissao);
                  } else if (librasAtivo) {
                    e.stopPropagation(); 
                  }
                }}
              >
                {texts.pautaAtaModoVisualizacao.comissao}
              </Typography>
            </th>
            <th className="text-left text-white p-3 width-3/10">
              <Typography
                fontSize={FontConfig.big}
                onClick={(e) => {
                  if (lendoTexto) {
                    e.stopPropagation();
                    lerTexto(
                      texts.pautaAtaModoVisualizacao.analistaResponsavel
                    );
                  } else if (librasAtivo) {
                    e.stopPropagation(); 
                  }
                }}
              >
                {texts.pautaAtaModoVisualizacao.analistaResponsavel}
              </Typography>
            </th>
            <th className="text-center text-white p-3 width-2/10">
              <Typography
                fontSize={FontConfig.big}
                onClick={(e) => {
                  if (lendoTexto) {
                    e.stopPropagation();
                    lerTexto(texts.pautaAtaModoVisualizacao.data);
                  } else if (librasAtivo) {
                    e.stopPropagation(); 
                  }
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
                  onClick={(e) => {
                    if (lendoTexto) {
                      e.stopPropagation();
                      lerTexto(row.numeroSequencial);
                    } else if (librasAtivo) {
                      e.stopPropagation(); 
                    }
                  }}
                >
                  {row.numeroSequencial}
                </Typography>
              </td>
              <td className="text-left p-3" title={row.comissao}>
                <Typography
                  className="truncate"
                  fontSize={FontConfig.medium}
                  onClick={(e) => {
                    if (lendoTexto) {
                      e.stopPropagation();
                      lerTexto(row.comissao.nomeForum);
                    } else if (librasAtivo) {
                      e.stopPropagation(); 
                    }
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
                  onClick={(e) => {
                    if (lendoTexto) {
                      e.stopPropagation();
                      lerTexto(row.analistaResponsavel?.nome);
                    } else if (librasAtivo) {
                      e.stopPropagation(); 
                    }
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
                    onClick={(e) => {
                      if (lendoTexto) {
                        e.stopPropagation();
                        lerTexto(getDataFormatada(row.dataReuniao));
                      } else if (librasAtivo) {
                        e.stopPropagation(); 
                      }
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
                <td title={getDataFormatada(row.dataReuniao)}>
                  <Box
                    className="flex justify-center items-center text-right p-3"
                    sx={{ width: "100%" }}
                  >
                    <Box sx={{ width: "60%" }}>
                      <Typography
                        className="truncate"
                        fontSize={FontConfig.medium}
                        onClick={(e) => {
                          if (lendoTexto) {
                            e.stopPropagation();
                            lerTexto(getDataFormatada(row.dataReuniao));
                          } else if (librasAtivo) {
                            e.stopPropagation(); 
                          }
                        }}
                      >
                        {getDataFormatada(row.dataReuniao)}
                      </Typography>
                    </Box>

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
                    <Box
                      className="flex justify-end items-center"
                      sx={{ width: "3rem" }}
                    >
                      <Tooltip
                        title={
                          row.publicada
                            ? texts.pauta.publicada
                            : texts.pauta.naoPublicada
                        }
                      >
                        {row.publicada ? (
                          <PublicIcon sx={{ color: "text.secondary" }} />
                        ) : (
                          <PublicOffIcon sx={{ color: "text.secondary" }} />
                        )}
                      </Tooltip>
                    </Box>
                  </Box>
                </td>
              )}
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </Paper>
  );
};

// Função para mostra a pauta em formato de grid
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
  setPautaSelecionada = () => { },
}) => {
  return (
    <Box
      sx={{
        display: "grid",
        gap: "1rem",
        gridTemplateColumns: "repeat(auto-fit, minmax(600px, 1fr))",
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

// Função para mostrar "Nada Encontrado" caso não tenha nenhuma pauta no sistema
const NadaEncontrado = () => {

  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  // Context para ler o texto da tela 
  const { lendoTexto, lerTexto, librasAtivo } = useContext(SpeechSynthesisContext);

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
        onClick={(e) => {
          if (lendoTexto) {
            e.stopPropagation();
            lerTexto(texts.pautaAtaModoVisualizacao.nadaEncontrado);
          } else if (librasAtivo) {
            e.stopPropagation(); 
          }
        }}
      >
        {texts.pautaAtaModoVisualizacao.nadaEncontrado}
      </Typography>
      <Typography
        fontSize={FontConfig.medium}
        sx={{ color: "text.secondary", mb: 1 }}
        onClick={(e) => {
          if (lendoTexto) {
            e.stopPropagation();
            lerTexto(texts.pautaAtaModoVisualizacao.tenteNovamenteMaisTarde);
          } else if (librasAtivo) {
            e.stopPropagation(); 
          }
        }}
      >
        {texts.pautaAtaModoVisualizacao.tenteNovamenteMaisTarde}
      </Typography>
    </Box>
  );
};

export default PautaAtaModoVisualizacao;