import React, { useState, useContext, useEffect, useRef, memo } from "react";

import {
  TableContainer,
  Table,
  TableHead,
  TableRow,
  TableBody,
  Paper,
  Typography,
  Box,
  FormControl,
  Select,
  MenuItem,
  Tooltip,
} from "@mui/material";
import { styled } from "@mui/material/styles";

import DeleteOutlineOutlinedIcon from "@mui/icons-material/DeleteOutlineOutlined";
import MicNoneOutlinedIcon from "@mui/icons-material/MicNoneOutlined";
import MicOutlinedIcon from "@mui/icons-material/MicOutlined";

import CaixaTextoQuill from "../CaixaTextoQuill/CaixaTextoQuill";

import ColorModeContext from "../../service/TemaContext";
import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";

/** Componente de um benefício dentro da lista de benefícios na página de detalhes da demanda, podendo ser editável ou não (props.editavel) */
const BeneficiosDetalheDemanda = (props) => {
  // Contexto para trocar a linguagem
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // UseState utilizado para mudar a cor do textArea
  const [corFundoTextArea, setCorFundoTextArea] = useState("#FFFF");

  // Variável utilizada para o dark mode ( para mudar a cor do textArea )
  const { mode } = useContext(ColorModeContext);

  // UseEffect utilizado para mudar a cor do textArea dependendo do modo ( claro ou escuro )
  useEffect(() => {
    if (mode === "dark") {
      setCorFundoTextArea("#212121");
    } else {
      setCorFundoTextArea("#FFFF");
    }
  }, [mode]);

  /** Função responsável por estilizar as linhas da tabela de benefícios */
  const StyledTableRow = styled(TableRow)(({ theme }) => ({
    "&:nth-of-type(odd)": {
      backgroundColor: theme.palette.action.hover,
    },
    "&:last-child td, &:last-child th": {
      border: 0,
    },
  }));

  /** Variável para armazenar o valor html da memória de cálculo */
  const memoriaCalculo = useRef(null);

  /** UseEffect para setar o valor em html na variável */
  useEffect(() => {
    if (
      memoriaCalculo.current &&
      props.beneficio.memoriaCalculo !== undefined
    ) {
      memoriaCalculo.current.innerHTML = props.beneficio.memoriaCalculo;
    }
  });

  /** Função para formatar o texto html */
  const getMemoriaCalculoFomartted = (memoria) => {
    return memoria[0].toUpperCase() + memoria.substring(1).toLowerCase();
  };

  /** Função utilizada para editar um texto do campo de memória de cálculo */
  const alterarTexto = (e) => {
    props.setBeneficio({ ...props.beneficio, memoriaCalculo: e }, props.index);
  };

  /** Variável utilizada durante uma possível edição de texto na memória cálculo */
  const [memoriaEdicao, setMemoriaEdicao] = useState(
    props.beneficio.memoriaCalculo
  );

  /** useEffect para mostrar o texto que poderá ser editado */
  useEffect(() => {
    if (!props.carregamento) {
      setMemoriaEdicao(props.beneficio.memoriaCalculo);
    }
  }, [props.carregamento]);

  // ********************************************** Gravar audio **********************************************

  const recognitionRef = useRef(null);

  const [escutar, setEscutar] = useState(false);

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
        props.setBeneficio(
          {
            ...props.beneficio,
            valor_mensal: transcript,
          },
          props.index
        );
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

  const startRecognition = () => {
    setEscutar(!escutar);
  };

  useEffect(() => {
    if (escutar) {
      ouvirAudio();
    } else {
      stopRecognition();
    }
  }, [escutar]);

  // ********************************************** Fim Gravar audio **********************************************

  // Função que irá setar o texto que será "lido" pela a API
  const lerTexto = (texto) => {
    if (props.lendo) {
      props.setTexto(texto);
    }
  };

  // Função que irá "ouvir" o texto que será "lido" pela a API
  useEffect(() => {
    const synthesis = window.speechSynthesis;
    const utterance = new SpeechSynthesisUtterance(props.texto);

    const finalizarLeitura = () => {
      if ("speechSynthesis" in window) {
        synthesis.cancel();
      }
    };

    if (props.lendo && props.texto !== "") {
      if ("speechSynthesis" in window) {
        synthesis.speak(utterance);
      }
    } else if (!props.lendo) {
      finalizarLeitura();
    }

    return () => {
      finalizarLeitura();
    };
  }, [props.texto, props.lendo]);

  return (
    <Box className="flex items-center">
      {/* Beneficios editáveis */}
      {props.editavel ? (
        <>
          {/* Botão de excluir benefício */}
          <DeleteOutlineOutlinedIcon
            fontSize="large"
            className="delay-120 hover:scale-110 duration-300 mr-2"
            sx={{ color: "icon.main", cursor: "pointer" }}
            onClick={() => {
              props.delete(props.index);
            }}
          />
          <TableContainer component={Paper}>
            <Table sx={{ minWidth: 750 }} aria-label="customized table">
              <TableHead sx={{ backgroundColor: "primary.main" }}>
                <TableRow>
                  <th
                    align="center"
                    className="p-4 w-0"
                    style={{ width: "10%" }}
                  >
                    <Typography
                      fontSize={FontConfig.big}
                      fontWeight="800"
                      color="text.white"
                      onClick={() => {
                        lerTexto(texts.BeneficiosDetalheDemanda.tipo);
                      }}
                    >
                      {texts.BeneficiosDetalheDemanda.tipo}
                    </Typography>
                  </th>
                  <th
                    align="center"
                    className="p-4 w-0"
                    style={{ width: "15%" }}
                  >
                    <Typography
                      fontSize={FontConfig.big}
                      fontWeight="800"
                      color="text.white"
                      onClick={() => {
                        lerTexto(texts.BeneficiosDetalheDemanda.valorMensal);
                      }}
                    >
                      {texts.BeneficiosDetalheDemanda.valorMensal}
                    </Typography>
                  </th>
                  <th
                    align="center"
                    className="p-4 w-0"
                    style={{ width: "10%" }}
                  >
                    <Typography
                      fontSize={FontConfig.big}
                      fontWeight="800"
                      color="text.white"
                      onClick={() => {
                        lerTexto(texts.BeneficiosDetalheDemanda.moeda);
                      }}
                    >
                      {texts.BeneficiosDetalheDemanda.moeda}
                    </Typography>
                  </th>
                  <th
                    align="center"
                    className="p-4 w-0"
                    style={{ width: "30%" }}
                  >
                    <Typography
                      fontSize={FontConfig.big}
                      fontWeight="800"
                      color="text.white"
                      onClick={() => {
                        lerTexto(texts.BeneficiosDetalheDemanda.memoriaCalculo);
                      }}
                    >
                      {texts.BeneficiosDetalheDemanda.memoriaCalculo}
                    </Typography>
                  </th>
                </TableRow>
              </TableHead>
              <TableBody>
                <TableRow>
                  <td align="center">
                    <FormControl
                      variant="standard"
                      sx={{ marginRight: "10px", minWidth: 90 }}
                    >
                      {/* Select de tipo do benefício */}
                      <Select
                        labelId="demo-simple-select-standard-label"
                        id="demo-simple-select-standard"
                        value={props.beneficio.tipoBeneficio}
                        onChange={(e) => {
                          if (e.target.value != "Qualitativo") {
                            props.setBeneficio(
                              {
                                ...props.beneficio,
                                tipoBeneficio: e.target.value,
                              },
                              props.index
                            );
                          } else {
                            props.setBeneficio(
                              {
                                ...props.beneficio,
                                tipoBeneficio: e.target.value,
                                valor_mensal: "",
                                moeda: "",
                              },
                              props.index
                            );
                          }
                        }}
                      >
                        <MenuItem value={"Real"}>
                          {texts.BeneficiosDetalheDemanda.real}
                        </MenuItem>
                        <MenuItem value={"Potencial"}>
                          {texts.BeneficiosDetalheDemanda.potencial}
                        </MenuItem>
                        <MenuItem value={"Qualitativo"}>
                          {texts.BeneficiosDetalheDemanda.qualitativo}
                        </MenuItem>
                      </Select>
                    </FormControl>
                  </td>
                  <td align="center">
                    {props.beneficio.tipoBeneficio != "QUALITATIVO" &&
                      props.beneficio.tipoBeneficio != "Qualitativo" && (
                        <>
                          <Box className="flex items-center justify-center">
                            {/* Input de valor mensal */}
                            <Box
                              value={props.beneficio.valor_mensal || ""}
                              fontSize={FontConfig.medium}
                              onChange={(e) => {
                                props.setBeneficio(
                                  {
                                    ...props.beneficio,
                                    valor_mensal: e.target.value,
                                  },
                                  props.index
                                );
                              }}
                              color="text.primary"
                              className="flex outline-none border-solid border px-1 py-1.5 drop-shadow-sm rounded text-center"
                              sx={{
                                width: "70%;",
                                height: "30px",
                                backgroundColor: "background.default",
                              }}
                              component="input"
                              placeholder={
                                texts.BeneficiosDetalheDemanda.digiteValorMensal
                              }
                            />
                            <Tooltip
                              className="hover:cursor-pointer"
                              title={texts.homeGerencia.gravarAudio}
                              onClick={() => {
                                startRecognition();
                              }}
                            >
                              {escutar ? (
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
                            </Tooltip>
                          </Box>
                        </>
                      )}
                  </td>
                  <td align="center">
                    {props.beneficio.tipoBeneficio != "QUALITATIVO" &&
                      props.beneficio.tipoBeneficio != "Qualitativo" && (
                        <FormControl
                          variant="standard"
                          sx={{ marginRight: "10px", minWidth: 90 }}
                        >
                          {/* Select de moeda do benefício */}
                          <Select
                            labelId="demo-simple-select-standard-label"
                            id="demo-simple-select-standard"
                            value={props.beneficio.moeda || ""}
                            onChange={(e) => {
                              props.setBeneficio(
                                { ...props.beneficio, moeda: e.target.value },
                                props.index
                              );
                            }}
                          >
                            <MenuItem value={"Real"}>BRL</MenuItem>
                            <MenuItem value={"Dolar"}>USD</MenuItem>
                            <MenuItem value={"Euro"}>EUR</MenuItem>
                          </Select>
                        </FormControl>
                      )}
                  </td>
                  <td className="p-5 flex justify-center overflow-auto">
                    <Box sx={{ height: "10rem" }}>
                      {/* Caixa de texto para edição da memória cálculo */}
                      <CaixaTextoQuill
                        texto={props.beneficio.memoriaCalculo}
                        onChange={(value) => {
                          alterarTexto(value);
                        }}
                      />
                    </Box>
                  </td>
                </TableRow>
              </TableBody>
            </Table>
          </TableContainer>
        </>
      ) : (
        // Benefícios não editáveis
        <TableContainer component={Paper}>
          <Table sx={{ minWidth: 750 }} aria-label="customized table">
            <TableHead sx={{ backgroundColor: "primary.main" }}>
              <TableRow>
                <th align="center" className="p-4 w-0" style={{ width: "10%" }}>
                  <Typography
                    fontSize={FontConfig.big}
                    fontWeight="800"
                    color="text.white"
                    onClick={() => {
                      lerTexto(texts.BeneficiosDetalheDemanda.tipo);
                    }}
                  >
                    {texts.BeneficiosDetalheDemanda.tipo}
                  </Typography>
                </th>
                <th align="center" className="p-4 w-0" style={{ width: "15%" }}>
                  <Typography
                    fontSize={FontConfig.big}
                    fontWeight="800"
                    color="text.white"
                    onClick={() => {
                      lerTexto(texts.BeneficiosDetalheDemanda.valorMensal);
                    }}
                  >
                    {texts.BeneficiosDetalheDemanda.valorMensal}
                  </Typography>
                </th>
                <th align="center" className="p-4 w-0" style={{ width: "10%" }}>
                  <Typography
                    fontSize={FontConfig.big}
                    fontWeight="800"
                    color="text.white"
                    onClick={() => {
                      lerTexto(texts.BeneficiosDetalheDemanda.moeda);
                    }}
                  >
                    {texts.BeneficiosDetalheDemanda.moeda}
                  </Typography>
                </th>
                <th align="center" className="p-4 w-0" style={{ width: "30%" }}>
                  <Typography
                    fontSize={FontConfig.big}
                    fontWeight="800"
                    color="text.white"
                    onClick={() => {
                      lerTexto(texts.BeneficiosDetalheDemanda.memoriaCalculo);
                    }}
                  >
                    {texts.BeneficiosDetalheDemanda.memoriaCalculo}
                  </Typography>
                </th>
              </TableRow>
            </TableHead>
            <TableBody>
              <StyledTableRow className="flex">
                <td align="center">
                  {/* Tipo do benefício */}
                  <Typography
                    fontSize={FontConfig.medium}
                    color="text.primary"
                    onClick={() => {
                      lerTexto(props.beneficio.tipoBeneficio);
                    }}
                  >
                    {props.beneficio.tipoBeneficio}
                  </Typography>
                </td>
                <td align="center">
                  {/* Valor mensal */}
                  <Typography
                    fontSize={FontConfig.medium}
                    color="text.primary"
                    onClick={() => {
                      lerTexto(props.beneficio.valor_mensal);
                    }}
                  >
                    {props.beneficio.valor_mensal}
                  </Typography>
                </td>
                <td align="center">
                  {/* Moeda do benefício */}
                  <Typography
                    fontSize={FontConfig.medium}
                    color="text.primary"
                    onClick={() => {
                      lerTexto(props.beneficio.moeda);
                    }}
                  >
                    {props.beneficio.moeda}
                  </Typography>
                </td>
                <td
                  align="center"
                  className="p-3 pl-5 pr-5 flex justify-center"
                >
                  {/* Memória de cálculo */}

                  <Typography
                    className="text-center"
                    fontSize={FontConfig.medium}
                    color="text.primary"
                    sx={{ width: "100%" }}
                    ref={memoriaCalculo}
                    onClick={() => {
                      lerTexto(
                        getMemoriaCalculoFomartted(
                          props.beneficio.memoriaCalculo
                        )
                      );
                    }}
                  >
                    {getMemoriaCalculoFomartted(props.beneficio.memoriaCalculo)}
                  </Typography>
                </td>
              </StyledTableRow>
            </TableBody>
          </Table>
        </TableContainer>
      )}
    </Box>
  );
};

export default BeneficiosDetalheDemanda;
