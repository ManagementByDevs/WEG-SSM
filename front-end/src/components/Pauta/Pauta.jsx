import React, { useContext, useEffect, useState } from "react";

import { Box, IconButton, Paper, Tooltip, Typography } from "@mui/material";

import DeleteOutlineOutlinedIcon from "@mui/icons-material/DeleteOutlineOutlined";

import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";
import SpeechSynthesisContext from "../../service/SpeechSynthesisContext";
import DateService from "../../service/dateService";

const Pautas = (props) => {

  // Context para alterar a linguagem do sistema
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  /** Context para ler o texto da tela */
  const { lerTexto, lendoTexto } = useContext(SpeechSynthesisContext);

  // Estado para saber se a ata já foi apreciada pela DG
  const [isApreciada, setIsApreciada] = useState(false);

  // useEffect utilizado para verificar se a ata já foi apreciada pela DG
  useEffect(() => {
    // Faz a verificação se a ata já foi apreciada pela DG
    setIsApreciada(props.dados.publicadaDg);
  }, []);

  // Função para formatar a data para melhor leitura
  const getDataFormatada = (dataReuniao) => {
    return props.tipo == "pauta"
      ? DateService.getTodaysDateUSFormat(
        DateService.getDateByMySQLFormat(dataReuniao),
        texts.linguagem
      )
      : DateService.getFullDateUSFormat(
        DateService.getDateByMySQLFormat(dataReuniao),
        texts.linguagem
      );
  };

  // Função que retorna a cor do status da ata
  const getStatusColor = () => {
    if (isApreciada) return "success.main";
    return "#C4C4C4";
  };

  // Função que retorna o tamanho do componente que conterá o texto
  const retornaTamanhoTexto = () => {
    if (props.dados.publicada) {
      return "5rem";
    } else {
      return "7rem";
    }
  };

  return (
    <Paper
      onClick={(e) => { props.onItemClick(props.dados); }}
      className="flex flex-col border-t-4 pt-2 pb-3 pl-6 pr-4"
      sx={{
        "&:hover": { backgroundColor: "hover.main", },
        borderColor: "primary.main",
        cursor: "pointer",
      }}
    >
      <Box className="flex w-full justify-between">
        <Typography
          fontSize={FontConfig.big}
          fontWeight="600"
          sx={{ color: "primary.main" }}
          onClick={(e) => {
            if (lendoTexto) {
              e.stopPropagation();
              lerTexto(props.dados.numeroSequencial);
            }
          }}
        >
          #{props.dados.numeroSequencial}
        </Typography>
        <Box className="flex items-center">
          <Typography
            fontSize={FontConfig.medium}
            fontWeight="600"
            sx={{ color: "text.secondary" }}
            onClick={(e) => {
              if (lendoTexto) {
                e.stopPropagation();
                lerTexto(getDataFormatada(props.dados.dataReuniao));
              }
            }}
          >
            {getDataFormatada(props.dados.dataReuniao)}
          </Typography>
          {props.tipo == "pauta" ? (
            <Box sx={{ marginRight: "-8px" }} className="ml-2">
              <Tooltip title={texts.pauta.deletar}>
                <IconButton
                  onClick={(e) => {
                    props.setPautaSelecionada(props.dados);
                    e.stopPropagation();
                  }}
                >
                  <DeleteOutlineOutlinedIcon
                    id="segundoPautas"
                    className="delay-120 hover:scale-110 duration-300 "
                    sx={{
                      color: "icon.main",
                      cursor: "pointer",
                      fontSize: "30px",
                    }}
                  />
                </IconButton>
              </Tooltip>
            </Box>
          ) : (
            <Tooltip
              title={isApreciada || !props.dados.publicada ? texts.pauta.jaApreciada : texts.pauta.naoApreciada}
            >
              <Box
                className="w-6 h-4 ml-3 rounded"
                sx={{ backgroundColor: getStatusColor() }}
              />
            </Tooltip>
          )}
        </Box>
      </Box>
      <Box className="w-full flex">
        <Box sx={{ width: "80%" }}>
          <Box
            className="flex items-center mt-3"
          >
            <Typography
              fontSize={FontConfig.medium}
              fontWeight="600"
              onClick={(e) => {
                if (lendoTexto) {
                  e.stopPropagation();
                  lerTexto(texts.pauta.comissao);
                }
              }}
            >
              {texts.pauta.comissao}:
            </Typography>
            <Typography
              className="overflow-hidden text-ellipsis whitespace-nowrap"
              fontSize={FontConfig.default}
              fontWeight="600"
              sx={{
                color: "text.secondary",
                marginLeft: "5px",
              }}
              onClick={(e) => {
                if (lendoTexto) {
                  e.stopPropagation();
                  lerTexto(props.dados.comissao?.nomeForum);
                }
              }}
            >
              {props.dados.comissao?.siglaForum} -{" "}
              {props.dados.comissao?.nomeForum}
            </Typography>
          </Box>
          <Box className="flex items-center">
            <Typography
              fontSize={FontConfig.medium}
              fontWeight="600"
              onClick={(e) => {
                if (lendoTexto) {
                  e.stopPropagation();
                  lerTexto(texts.pauta.analistaResponsavel);
                }
              }}
            >
              {texts.pauta.analistaResponsavel}:
            </Typography>
            <Typography
              className="overflow-hidden text-ellipsis whitespace-nowrap"
              fontSize={FontConfig.default}
              fontWeight="600"
              sx={{ color: "text.secondary", marginLeft: "5px" }}
              onClick={(e) => {
                if (lendoTexto) {
                  e.stopPropagation();
                  lerTexto(props.dados.analistaResponsavel?.nome);
                }
              }}
            >
              {props.dados.analistaResponsavel?.nome}
            </Typography>
          </Box>
        </Box>
        {props.tipo == "ata" ? (
          <Box className="w-full flex justify-end">
            <Box className="text-center" sx={{ width: retornaTamanhoTexto }}>
              {/* Texto para dizer se está em ata ou em pauta */}
              <Typography
                fontSize={FontConfig.small}
                fontWeight="600"
                sx={{
                  color: "text.primary",
                  backgroundColor: "divider.claro",
                  borderRadius: "5px",
                  padding: "2px 5px",
                }}
                onClick={(e) => {
                  if (lendoTexto) {
                    e.stopPropagation();
                    if (props.dados.publicada) {
                      lerTexto(texts.pauta.publicada);
                    } else {
                      lerTexto(texts.pauta.naoPublicada);
                    }
                  }
                }}
              >
                {props.dados.publicada
                  ? texts.pauta.publicada
                  : texts.pauta.naoPublicada}
              </Typography>
            </Box>
          </Box>
        ) : null}
      </Box>
    </Paper>
  );
};

export default Pautas;