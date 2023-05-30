import React, { useState, useContext, useEffect, useRef } from "react";

import { TableRow, Box, TextareaAutosize, Tooltip } from "@mui/material";

import DeleteOutlineOutlinedIcon from "@mui/icons-material/DeleteOutlineOutlined";
import MicNoneOutlinedIcon from "@mui/icons-material/MicNoneOutlined";
import MicOutlinedIcon from "@mui/icons-material/MicOutlined";

import ColorModeContext from "../../service/TemaContext";
import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";

// Componente para criar uma linha na tabela de custos
const LinhaTabelaCustos = (props) => {
  // Context que contém os textos do sistema
  const { texts, setTexts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

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
    aux[props.indexCusto].custos[props.index].total = (
      aux[props.indexCusto].custos[props.index].horas *
      1 *
      (aux[props.indexCusto].custos[props.index].valorHora * 1)
    ).toFixed(2);
    props.setCustos(aux);
  }, [
    props.custos[props.indexCusto].custos[props.index].horas,
    props.custos[props.indexCusto].custos[props.index].valorHora,
  ]);

  // // ********************************************** Gravar audio **********************************************
  const recognitionRef = useRef(null);

  const [escutar, setEscutar] = useState(false);

  const [localClicou, setLocalClicou] = useState("");

  const [palavrasJuntas, setPalavrasJuntas] = useState("");

  const ouvirAudio = () => {
    // Verifica se a API é suportada pelo navegador
    if ("webkitSpeechRecognition" in window) {
      const recognition = new window.webkitSpeechRecognition();
      recognition.continuous = true;
      switch (texts.linguagem) {
        case "pt":
          recognition.lang = "pt-BR";
          break;
        case "en":
          recognition.lang = "en-US";
          break;
        case "es":
          recognition.lang = "es-ES";
          break;
        case "ch":
          recognition.lang = "cmn-Hans-CN";
          break;
        default:
          recognition.lang = "pt-BR";
          break;
      }

      recognition.onstart = () => {
          
      };

      recognition.onresult = (event) => {
        const transcript =
          event.results[event.results.length - 1][0].transcript;
        setPalavrasJuntas((palavrasJuntas) => palavrasJuntas + transcript);
        
      };

      recognition.onerror = (event) => {
        props.setFeedbackErroReconhecimentoVoz(true);
        setEscutar(false);
      };

      recognitionRef.current = recognition;
      recognition.start();
    } else {
      props.setFeedbackErroNavegadorIncompativel(true);
      setEscutar(false);
    }
  };

  useEffect(() => {
    let aux = [...props.custos];
        switch (localClicou) {
          case "tipoDespesa":
            aux[props.indexCusto].custos[props.index].tipoDespesa =
              palavrasJuntas;
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
            aux[props.indexCusto].custos[props.index].valorHora =
              palavrasJuntas;
            props.setCustos(aux);
            break;
          default:
            break;
        }
  }, [palavrasJuntas]);

  const stopRecognition = () => {
    if (recognitionRef.current) {
      recognitionRef.current.stop();
       
    }
  };

  const startRecognition = (ondeClicou) => {
    setEscutar(!escutar);
    setLocalClicou(ondeClicou);
  };

  useEffect(() => {
    if (escutar) {
      ouvirAudio();
    } else {
      stopRecognition();
    }
  }, [escutar]);

  // // ********************************************** Fim Gravar audio **********************************************

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
              width: "95%",
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
            {escutar && localClicou == "perfilDespesa" ? (
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
              let aux = [...props.custos];
              aux[props.indexCusto].custos[props.index].periodoExecucao =
                e.target.value;
              props.setCustos(aux);
            }}
          />
          <Tooltip
            className="hover:cursor-pointer"
            title={texts.homeGerencia.gravarAudio}
            onClick={() => {
              startRecognition("periodoExecucao");
            }}
          >
            {escutar && localClicou == "periodoExecucao" ? (
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
              let aux = [...props.custos];
              aux[props.indexCusto].custos[props.index].horas = e.target.value;
              props.setCustos(aux);
            }}
          />
          <Tooltip
            className="hover:cursor-pointer"
            title={texts.homeGerencia.gravarAudio}
            onClick={() => {
              startRecognition("horas");
            }}
          >
            {escutar && localClicou == "horas" ? (
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
              let aux = [...props.custos];
              aux[props.indexCusto].custos[props.index].valorHora =
                e.target.value;
              props.setCustos(aux);
            }}
          />
          <Tooltip
            className="hover:cursor-pointer"
            title={texts.homeGerencia.gravarAudio}
            onClick={() => {
              startRecognition("valorHora");
            }}
          >
            {escutar && localClicou == "valorHora" ? (
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
