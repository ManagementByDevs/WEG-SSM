import React, { useState, useContext, useEffect, useRef } from "react";

import { TableRow, Box, TextareaAutosize, Tooltip } from "@mui/material";

import DeleteOutlineOutlinedIcon from "@mui/icons-material/DeleteOutlineOutlined";
import MicNoneOutlinedIcon from "@mui/icons-material/MicNoneOutlined";
import MicOutlinedIcon from "@mui/icons-material/MicOutlined";

import ColorModeContext from "../../service/TemaContext";
import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";
import { SpeechRecognitionContext } from "../../service/SpeechRecognitionService";

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

  return (
    <TableRow className="border-b">
      <td align="center" className="pt-5 pb-5">
        <Box
          className="flex items-center justify-between border-solid border px-1 py-1.5 drop-shadow-sm rounded"
          sx={{
            width: "80%",
            backgroundColor: corFundoTextArea,
            marginTop: "0.8rem",
          }}
        >
          <TextareaAutosize
            style={{
              width: "100%",
              resize: "none",
              textAlign: "center",
              backgroundColor: "transparent",
            }}
            fontSize={FontConfig.medium}
            className="flex outline-none"
            placeholder={texts.linhaTabelaCustos.digitePerfil}
            value={props.dados.custos[props.index].perfilDespesa || ""}
            onChange={(e) => {
              let aux = [...props.custos];
              aux[props.indexCusto].custos[props.index].perfilDespesa =
                e.target.value;
              props.setCustos(aux);
            }}
          />
          <Tooltip
            className="hover:cursor-pointer"
            title={texts.homeGerencia.gravarAudio}
            onClick={() => {
              startRecognition("perfilDespesa");
            }}
          >
            {escutar && localClique == "perfilDespesa" ? (
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
          </Tooltip>
        </Box>
      </td>
      <td align="center" className="pt-5 pb-5">
        <Box
          className="flex items-center justify-between border-solid border px-1 py-1.5 drop-shadow-sm rounded"
          sx={{
            width: "95%",
            minWidth: "155px",
            backgroundColor: corFundoTextArea,
            marginTop: "0.8rem",
          }}
        >
          <TextareaAutosize
            style={{
              width: "95%",
              resize: "none",
              textAlign: "center",
              backgroundColor: "transparent",
            }}
            fontSize={FontConfig.medium}
            className="flex outline-none"
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
          />
          <Tooltip
            className="hover:cursor-pointer"
            title={texts.homeGerencia.gravarAudio}
            onClick={() => {
              startRecognition("periodoExecucao");
            }}
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
          </Tooltip>
        </Box>
      </td>
      <td align="center" className="pt-5 pb-5">
        <Box
          className="flex items-center justify-between border-solid border px-1 py-1.5 drop-shadow-sm rounded"
          sx={{
            width: "90%",
            backgroundColor: corFundoTextArea,
            marginTop: "0.8rem",
          }}
        >
          <TextareaAutosize
            style={{
              width: "95%",
              resize: "none",
              textAlign: "center",
              backgroundColor: "transparent",
            }}
            fontSize={FontConfig.medium}
            className="flex outline-none"
            placeholder={texts.linhaTabelaCustos.digiteHoras}
            value={props.dados.custos[props.index].horas || ""}
            onChange={(e) => {
              if (!verificarCaracteres(e.target.value)) {
                let aux = [...props.custos];
                aux[props.indexCusto].custos[props.index].horas =
                  e.target.value;
                props.setCustos(aux);
              }
            }}
          />
          <Tooltip
            className="hover:cursor-pointer"
            title={texts.homeGerencia.gravarAudio}
            onClick={() => {
              startRecognition("horas");
            }}
          >
            {escutar && localClique == "horas" ? (
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
          </Tooltip>
        </Box>
      </td>
      <td align="center" className="pt-5 pb-5">
        <Box
          className="flex items-center justify-between border-solid border px-1 py-1.5 drop-shadow-sm rounded"
          sx={{
            width: "95%",
            backgroundColor: corFundoTextArea,
            marginTop: "0.8rem",
          }}
        >
          <TextareaAutosize
            style={{
              width: "95%",
              resize: "none",
              textAlign: "center",
              backgroundColor: "transparent",
            }}
            fontSize={FontConfig.medium}
            className="flex outline-none"
            placeholder={texts.linhaTabelaCustos.digiteValor}
            value={props.dados.custos[props.index].valorHora || ""}
            onChange={(e) => {
              if (!verificarCaracteres(e.target.value)) {
                let aux = [...props.custos];
                aux[props.indexCusto].custos[props.index].valorHora =
                  e.target.value;
                props.setCustos(aux);
              }
            }}
          />
          <Tooltip
            className="hover:cursor-pointer"
            title={texts.homeGerencia.gravarAudio}
            onClick={() => {
              startRecognition("valorHora");
            }}
          >
            {escutar && localClique == "valorHora" ? (
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
          </Tooltip>
        </Box>
      </td>
      <td className="relative">
        <Box
          className="flex w-full justify-end absolute"
          sx={{ width: "98%", top: 0 }}
        >
          <Tooltip title={texts.linhaTabelaCustos.titleExcluirLinha}>
            <DeleteOutlineOutlinedIcon
              fontSize="medium"
              className="mt-1 delay-120 hover:scale-110 duration-300"
              sx={{ color: "icon.main", cursor: "pointer" }}
              onClick={() =>
                props.deletarLinhaCustos(
                  props.dados.custos[props.index].id,
                  props.index
                )
              }
            />
          </Tooltip>
        </Box>
        <Box className="flex justify-center mt-5">
          {props.dados.custos[props.index].total || ""}
        </Box>
      </td>
    </TableRow>
  );
};

export default LinhaTabelaCustos;
