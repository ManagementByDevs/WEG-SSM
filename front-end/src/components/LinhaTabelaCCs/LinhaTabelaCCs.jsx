import React, { useState, useContext, useEffect } from "react";

import {
  TableRow,
  Box,
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

  // UseEffect utilizado para ouvir áudio do local clicado
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
      <td align="center" className="flex relative items-center">
        <Box className="absolute right-2">
          <Tooltip title={texts.linhaTabelaCCs.titleExcluirLinha}>
            <IconButton
              onClick={() =>
                props.deletarLinhaCCs(
                  props.dados.ccs[props.index].id,
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
        <Box className="w-full flex gap-2 p-5">
          <Input
            className="w-3/5 border-solid border pl-1 text-center"
            sx={{
              borderLeft: "4px solid #00579d",
            }}
            fontSize={FontConfig.medium}
            placeholder={texts.linhaTabelaCCs.digiteCodigo}
            value={props.dados.ccs[props.index].codigo || ""}
            onChange={(e) => {
              let aux = [...props.custos];
              aux[props.indexCusto].ccs[props.index].codigo = e.target.value;
              props.setCustos(aux);
            }}
            endAdornment={
              <InputAdornment position="end">
                <Tooltip title={texts.homeGerencia.gravarAudio}>
                  <IconButton
                    onClick={() => {
                      startRecognition("codigoCcs");
                    }}
                    size="small"
                  >
                    {escutar && localClique == "codigoCcs" ? (
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

          <Input
            className="border-solid border pl-1 text-center"
            sx={{
              borderLeft: "4px solid #00579d",
              width: "32%",
            }}
            fontSize={FontConfig.medium}
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
            endAdornment={
              <InputAdornment position="end">
                %
                <Tooltip title={texts.homeGerencia.gravarAudio}>
                  <IconButton
                    onClick={() => {
                      startRecognition("porcentagemCcs");
                    }}
                  >
                    {escutar && localClique == "porcentagemCcs" ? (
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
        </Box>
      </td>
    </TableRow>
  );
};

export default LinhaTabelaCCs;
