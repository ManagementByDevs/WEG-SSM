import React, { useState, useContext, useEffect } from "react";

import {
  TableRow,
  Box,
  TextareaAutosize,
  Tooltip,
  Input,
  InputAdornment,
  IconButton,
} from "@mui/material";

import DeleteOutlineOutlinedIcon from "@mui/icons-material/DeleteOutlineOutlined";
import MicNoneOutlinedIcon from "@mui/icons-material/MicNoneOutlined";
import MicOutlinedIcon from "@mui/icons-material/MicOutlined";

import ColorModeContext from "../../service/TemaContext";
import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";
import { SpeechRecognitionContext } from "../../service/SpeechRecognitionService";
import InputDinheiro from "../InputDinheiro/InputDinheiro";

// Componente para criar uma linha na tabela de custos
const LinhaTabelaCustos = (props) => {
  // Context que contém os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  /** Context para obter a função de leitura de texto */
  const { startRecognition, escutar, palavrasJuntas, localClique } = useContext(
    SpeechRecognitionContext
  );

  // UseState para alterar a cor do textArea
  const [corFundoTextArea, setCorFundoTextArea] = useState("#FFFF");

  // Variável para alterar o tema
  const { mode } = useContext(ColorModeContext);

  // UseEffect para alterar a cor de fundo do textArea
  useEffect(() => {
    if (mode === "dark") {
      setCorFundoTextArea("#212121");
    } else {
      setCorFundoTextArea("#FFFF");
    }
  }, [mode]);

  // UseEffect para calcular o total de custo
  useEffect(() => {
    let aux = [...props.custos];
    let horas = 0;
    let valorHora = 0;

    for (let i = 0; i < aux[props.indexCusto].custos.length; i++) {
      horas = 0;
      valorHora = 0;
      const horasString = aux[props.indexCusto].custos[props.index].horas;

      if (horasString) {
        if (typeof horasString !== "number") {
          const horasNumber = parseFloat(horasString.replace(",", "."));
          if (!isNaN(horasNumber)) {
            horas += horasNumber;
          }
        } else {
          horas += horasString;
        }
      } else {
        horas += 0;
      }
      const valorHoraString =
        aux[props.indexCusto].custos[props.index].valorHora;

      if (valorHoraString) {
        if (typeof valorHoraString !== "number") {
          const valorHoraNumber = parseFloat(valorHoraString.replace(",", "."));

          if (!isNaN(valorHoraNumber)) {
            valorHora += valorHoraNumber;
          }
        } else {
          valorHora += valorHoraString;
        }
      } else {
        valorHora += 0;
      }
    }

    aux[props.indexCusto].custos[props.index].total = (
      horas * valorHora
    ).toFixed(2);
    props.setCustos(aux);
  }, [
    props.custos[props.indexCusto].custos[props.index].horas,
    props.custos[props.indexCusto].custos[props.index].valorHora,
  ]);

  // UseEffect utilizado para ouvir áudio do local clicado
  useEffect(() => {
    let aux = [...props.custos];
    switch (localClique) {
      case "tipoDespesa":
        aux[props.indexCusto].custos[props.index].tipoDespesa = palavrasJuntas;
        props.setCustos(aux);
        break;
      case "perfilDespesa":
        aux[props.indexCusto].custos[props.index].perfilDespesa =
          palavrasJuntas;
        props.setCustos(aux);
        break;
      case "periodoExecucao":
        aux[props.indexCusto].custos[props.index].periodoExecucao =
          palavrasJuntas;
        props.setCustos(aux);
        break;
      case "horas":
        aux[props.indexCusto].custos[props.index].horas = palavrasJuntas;
        props.setCustos(aux);
        break;
      case "valorHora":
        aux[props.indexCusto].custos[props.index].valorHora = palavrasJuntas;
        props.setCustos(aux);
        break;
      default:
        break;
    }
  }, [palavrasJuntas]);

  /** Função para verificar os caracteres digiados na tabela de custos */
  function verificarCaracteres(valorDigitado) {
    let temLetra = false;
    for (let i = 0; i < valorDigitado.length; i++) {
      const caracter = valorDigitado[i];

      if (isNaN(caracter) && caracter !== "," && caracter !== ".") {
        temLetra = true;
        break;
      } else {
        temLetra = false;
      }
    }
    return temLetra;
  }

  /** Função para formatar um valor em formato double para string (troca de "." por ",") */
  const formatarCusto = (valor) => {
    return valor.toString().replace(".", ",");
  }

  return (
    <TableRow className="border-b">
      <td align="center" className="pt-5 pb-5">
        <Input
          className="w-4/5 border-solid border-t border-r pl-1 text-center"
          sx={{
            borderLeft: "4px solid #00579d",
          }}
          fontSize={FontConfig.medium}
          placeholder={texts.linhaTabelaCustos.digitePerfil}
          value={props.dados.custos[props.index].perfilDespesa || ""}
          onChange={(e) => {
            let aux = [...props.custos];
            aux[props.indexCusto].custos[props.index].perfilDespesa =
              e.target.value;
            props.setCustos(aux);
          }}
          endAdornment={
            <InputAdornment position="end">
              <Tooltip title={texts.homeGerencia.gravarAudio}>
                <IconButton
                  onClick={() => {
                    startRecognition("perfilDespesa");
                  }}
                  size="small"
                >
                  {escutar && localClique == "perfilDespesa" ? (
                    <MicOutlinedIcon
                      sx={{
                        color: "primary.main",
                        fontSize: "1.3rem",
                      }}
                    />
                  ) : (
                    <MicNoneOutlinedIcon
                      sx={{
                        color: "text.secondary",
                        fontSize: "1.3rem",
                      }}
                    />
                  )}
                </IconButton>
              </Tooltip>
            </InputAdornment>
          }
        />
      </td>
      <td align="center" className="pt-5 pb-5">
        <Input
          className="w-3/5 border-solid border pl-1 text-center"
          sx={{
            borderLeft: "4px solid #00579d",
          }}
          fontSize={FontConfig.medium}
          placeholder={texts.linhaTabelaCustos.digitePeriodo}
          value={props.dados.custos[props.index].periodoExecucao || ""}
          onChange={(e) => {
            if (!verificarCaracteres(e.target.value)) {
              let aux = [...props.custos];
              aux[props.indexCusto].custos[props.index].periodoExecucao =
                e.target.value;
              props.setCustos(aux);
            }
          }}
          endAdornment={
            <InputAdornment position="end">
              <Tooltip title={texts.homeGerencia.gravarAudio}>
                <IconButton
                  onClick={() => {
                    startRecognition("periodoExecucao");
                  }}
                  size="small"
                >
                  {escutar && localClique == "periodoExecucao" ? (
                    <MicOutlinedIcon
                      sx={{
                        cursor: "pointer",
                        color: "primary.main",
                        fontSize: "1.3rem",
                      }}
                    />
                  ) : (
                    <MicNoneOutlinedIcon
                      sx={{
                        cursor: "pointer",
                        color: "text.secondary",
                        fontSize: "1.3rem",
                      }}
                    />
                  )}
                </IconButton>
              </Tooltip>
            </InputAdornment>
          }
        />
      </td>
      <td align="center" className="pt-5 pb-5">
        <Input
          className="w-3/5 border-solid border pl-1 text-center"
          sx={{
            borderLeft: "4px solid #00579d",
          }}
          fontSize={FontConfig.medium}
          placeholder={texts.linhaTabelaCustos.digiteHoras}
          value={props.dados.custos[props.index].horas || ""}
          onChange={(e) => {
            if (!verificarCaracteres(e.target.value)) {
              let aux = [...props.custos];
              aux[props.indexCusto].custos[props.index].horas = e.target.value;
              props.setCustos(aux);
            }
          }}
          endAdornment={
            <InputAdornment position="end">
              <Tooltip title={texts.homeGerencia.gravarAudio}>
                <IconButton
                  onClick={() => {
                    startRecognition("horas");
                  }}
                  size="small"
                >
                  {escutar && localClique == "horas" ? (
                    <MicOutlinedIcon
                      sx={{
                        color: "primary.main",
                        fontSize: "1.3rem",
                      }}
                    />
                  ) : (
                    <MicNoneOutlinedIcon
                      sx={{
                        color: "text.secondary",
                        fontSize: "1.3rem",
                      }}
                    />
                  )}
                </IconButton>
              </Tooltip>
            </InputAdornment>
          }
        />
      </td>
      <td align="center" className="pt-5 pb-5">
        <InputDinheiro
          className="w-3/5 border-solid border pl-1 text-center"
          placeholder={texts.linhaTabelaCustos.digiteValor}
          fontConfig={FontConfig.medium}
          texto={props.dados.custos[props.index].valorHora || ""}
          saveInputValue={(valor) => {
            let aux = [...props.custos];
            aux[props.indexCusto].custos[props.index].valorHora =
              valor;
            props.setCustos(aux);
          }}
          moeda={"Real"}
        />
      </td>
      <td>
        <Box className="flex justify-center items-center relative">
          {texts.formularioCustosProposta.moeda}
          {formatarCusto(props.dados.custos[props.index].total || 0)}
          <Box className="absolute right-2">
            <Tooltip title={texts.linhaTabelaCustos.titleExcluirLinha}>
              <IconButton
                onClick={() =>
                  props.deletarLinhaCustos(
                    props.dados.custos[props.index].id,
                    props.index
                  )
                }
                size="small"
                color="primary"
              >
                <DeleteOutlineOutlinedIcon />
              </IconButton>
            </Tooltip>
          </Box>
        </Box>
      </td>
    </TableRow>
  );
};

export default LinhaTabelaCustos;
