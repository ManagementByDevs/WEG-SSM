import React, { useState, useContext, useEffect, useRef } from "react";

import { TableRow, Box, Tooltip, Input, InputAdornment } from "@mui/material";

import DeleteOutlineOutlinedIcon from "@mui/icons-material/DeleteOutlineOutlined";
import MicNoneOutlinedIcon from "@mui/icons-material/MicNoneOutlined";
import MicOutlinedIcon from "@mui/icons-material/MicOutlined";

import ColorModeContext from "../../service/TemaContext";
import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";
import { SpeechRecognitionContext } from "../../service/SpeechRecognitionService";

// Componente para criar uma linha da tabela de CCs
const LinhaTabelaCCs = (props) => {
  // Context que contém os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  /** Context para obter a função de leitura de texto */
  const { startRecognition, escutar, palavrasJuntas, localClique } = useContext(
    SpeechRecognitionContext
  );

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

  useEffect(() => {
    let aux = [...props.custos];
    switch (localClique) {
      case "codigoCcs":
        aux[props.indexCusto].ccs[props.index].codigo = palavrasJuntas;
        props.setCustos(aux);
        break;
      case "porcentagemCcs":
        aux[props.indexCusto].ccs[props.index].porcentagem = palavrasJuntas;
        props.setCustos(aux);
        break;
      default:
        break;
    }
  }, [palavrasJuntas]);

  /** Função para verificar os caracteres digitados no cc  */
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
              {escutar && localClique == "codigoCcs" ? (
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
                if (!verificarCaracteres(e.target.value)) {
                  if (e.target.value <= 100) {
                    let aux = [...props.custos];
                    aux[props.indexCusto].ccs[props.index].porcentagem =
                      e.target.value;
                    props.setCustos(aux);
                  }
                }
              }}
            />
            <Tooltip
              className="hover:cursor-pointer"
              title={texts.homeGerencia.gravarAudio}
              onClick={() => {
                startRecognition("porcentagemCcs");
              }}
            >
              {escutar && localClique == "porcentagemCcs" ? (
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
