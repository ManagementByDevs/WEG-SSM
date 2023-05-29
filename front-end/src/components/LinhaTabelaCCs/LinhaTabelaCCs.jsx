import React, { useState, useContext, useEffect, useRef } from "react";

import { TableRow, Box, Tooltip, Input, InputAdornment } from "@mui/material";

import DeleteOutlineOutlinedIcon from "@mui/icons-material/DeleteOutlineOutlined";
import MicNoneOutlinedIcon from "@mui/icons-material/MicNoneOutlined";
import MicOutlinedIcon from "@mui/icons-material/MicOutlined";

import ColorModeContext from "../../service/TemaContext";
import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";

// Componente para criar uma linha da tabela de CCs
const LinhaTabelaCCs = (props) => {
  // Context que contém os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // UseState para alterar a cor do fundo textArea
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

  // // ********************************************** Gravar audio **********************************************

  // const [
  //   feedbackErroNavegadorIncompativel,
  //   setFeedbackErroNavegadorIncompativel,
  // ] = useState(false);
  // const [feedbackErroReconhecimentoVoz, setFeedbackErroReconhecimentoVoz] =
  //   useState(false);

  const recognitionRef = useRef(null);

  const [escutar, setEscutar] = useState(false);

  const [localClicado, setLocalClicado] = useState("");

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
        // console.log("Reconhecimento de fala iniciado. Fale algo...");
      };

      recognition.onresult = (event) => {
        const transcript =
          event.results[event.results.length - 1][0].transcript;
        let aux = [...props.custos];
        switch (localClicado) {
          case "codigoCcs":
            aux[props.indexCusto].ccs[props.index].codigo = transcript;
            props.setCustos(aux);
            break;
          case "porcentagemCcs":
            aux[props.indexCusto].ccs[props.index].porcentagem = transcript;
            props.setCustos(aux);
            break;
          default:
            break;
        }
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

  const stopRecognition = () => {
    if (recognitionRef.current) {
      recognitionRef.current.stop();
      // console.log("Reconhecimento de fala interrompido.");
    }
  };

  const startRecognition = (ondeClicou) => {
    setEscutar(!escutar);
    setLocalClicado(ondeClicou);
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
      <td align="center" className="pb-5 relative">
        <Box className="flex w-full justify-end absolute" sx={{ width: "98%" }}>
          <Tooltip title={texts.linhaTabelaCCs.titleExcluirLinha}>
            <DeleteOutlineOutlinedIcon
              fontSize="medium"
              className="mt-1 delay-120 hover:scale-110 duration-300"
              sx={{ color: "icon.main", cursor: "pointer" }}
              onClick={() =>
                props.deletarLinhaCCs(
                  props.dados.ccs[props.index].id,
                  props.index
                )
              }
            />
          </Tooltip>
        </Box>
        <Box
          className="flex w-full justify-around pr-10 pl-4"
          sx={{ marginTop: "2rem" }}
        >
          <Box
            className="flex items-center justify-between border rounded drop-shadow-sm"
            sx={{
              width: "60%",
              minWidth: "134px",
              height: "2.4rem",
              backgroundColor: corFundoTextArea,
            }}
          >
            <Input
              className="outline-none"
              fontSize={FontConfig.medium}
              placeholder={texts.linhaTabelaCCs.digiteCodigo}
              size="small"
              style={{
                width: "95%",
                backgroundColor: "transparent",
                paddingLeft: "10px",
              }}
              value={props.dados.ccs[props.index].codigo || ""}
              onChange={(e) => {
                let aux = [...props.custos];
                aux[props.indexCusto].ccs[props.index].codigo = e.target.value;
                props.setCustos(aux);
              }}
            />
            <Tooltip
              className="hover:cursor-pointer"
              title={texts.homeGerencia.gravarAudio}
              onClick={() => {
                startRecognition("codigoCcs");
              }}
            >
              {escutar && localClicado == "codigoCcs" ? (
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

          <Box
            className="flex items-center justify-between border rounded drop-shadow-sm"
            sx={{
              width: "32%",
              minWidth: "73px",
              backgroundColor: corFundoTextArea,
            }}
          >
            <Input
              className="outline-none"
              endAdornment={
                <InputAdornment className="mb-1" position="end">
                  %
                </InputAdornment>
              }
              aria-describedby="standard-weight-helper-text"
              fontSize={FontConfig.medium}
              size="small"
              style={{
                width: "95%",
                backgroundColor: "transparent",
                padding: "0 10px",
              }}
              value={props.dados.ccs[props.index].porcentagem || ""}
              onChange={(e) => {
                let aux = [...props.custos];
                aux[props.indexCusto].ccs[props.index].porcentagem =
                  e.target.value;
                props.setCustos(aux);
              }}
            />
            <Tooltip
              className="hover:cursor-pointer"
              title={texts.homeGerencia.gravarAudio}
              onClick={() => {
                startRecognition("porcentagemCcs");
              }}
            >
              {escutar && localClicado == "porcentagemCcs" ? (
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
        </Box>
      </td>
    </TableRow>
  );
};

export default LinhaTabelaCCs;
