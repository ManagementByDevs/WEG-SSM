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
  Input,
  TextField,
  InputAdornment,
  IconButton,
} from "@mui/material";
import { styled } from "@mui/material/styles";

import DeleteOutlineOutlinedIcon from "@mui/icons-material/DeleteOutlineOutlined";
import MicNoneOutlinedIcon from "@mui/icons-material/MicNoneOutlined";
import MicOutlinedIcon from "@mui/icons-material/MicOutlined";

import CaixaTextoQuill from "../CaixaTextoQuill/CaixaTextoQuill";

import ColorModeContext from "../../service/TemaContext";
import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";
import SpeechSynthesisContext from "../../service/SpeechSynthesisContext";
import { SpeechRecognitionContext } from "../../service/SpeechRecognitionService";
import InputDinheiro from "../InputDinheiro/InputDinheiro";

/** Componente de um benefício dentro da lista de benefícios na página de detalhes da demanda, podendo ser editável ou não (props.editavel) */
const BeneficiosDetalheDemanda = (props) => {
  /** Contexto para trocar a linguagem */
  const { texts } = useContext(TextLanguageContext);

  /** Context para alterar o tamanho da fonte */
  const { FontConfig } = useContext(FontContext);

  /** Context para ler o texto da tela */
  const { lerTexto } = useContext(SpeechSynthesisContext);

  /** Context para obter a função de leitura de texto */
  const { startRecognition, escutar, palavrasJuntas, localClique } = useContext(
    SpeechRecognitionContext
  );

  /** UseState utilizado para mudar a cor do textArea */
  const [corFundoTextArea, setCorFundoTextArea] = useState("#FFFF");

  /** Variável utilizada para o dark mode ( para mudar a cor do textArea ) */
  const { mode } = useContext(ColorModeContext);

  /** Variável para armazenar o valor html da memória de cálculo */
  const memoriaCalculo = useRef(null);

  /** UseEffect utilizado para mudar a cor do textArea dependendo do modo ( claro ou escuro ) */
  useEffect(() => {
    if (mode === "dark") {
      setCorFundoTextArea("#212121");
    } else {
      setCorFundoTextArea("#FFFF");
    }
  }, [mode]);

  useEffect(() => {
    if (
      memoriaCalculo.current &&
      props.beneficio.memoriaCalculo !== undefined
    ) {
      memoriaCalculo.current.innerHTML = props.beneficio.memoriaCalculo;
    }
  })

  /** useEffect utilizado para setar o valor do input de valor_mensal de um benefício com o input de voz */
  useEffect(() => {
    switch (localClique) {
      case "valor_mensal":
        props.setBeneficio(
          {
            ...props.beneficio,
            valor_mensal: palavrasJuntas,
          },
          props.index
        );
        break;
      default:
        break;
    }
  }, [palavrasJuntas]);

  /** Função responsável por estilizar as linhas da tabela de benefícios */
  const StyledTableRow = styled(TableRow)(({ theme }) => ({
    "&:nth-of-type(odd)": {
      backgroundColor: theme.palette.action.hover,
    },
    "&:last-child td, &:last-child th": {
      border: 0,
    },
  }));

  /** Função utilizada para editar um texto do campo de memória de cálculo */
  const alterarTexto = (e) => {
    props.setBeneficio({ ...props.beneficio, memoriaCalculo: e }, props.index);
  };

  /** Função para formatar o tipo de benefício */
  const formatarTipoBeneficio = (tipoBeneficio) => {
    const novoTipo =
      tipoBeneficio?.charAt(0) +
      tipoBeneficio?.substring(1, tipoBeneficio?.length)?.toLowerCase() ||
      texts.DetalhesDemanda.real;
    return novoTipo;
  };

  return (
    <Box className="flex items-center">
      {/* Beneficios editáveis */}
      {props.editavel ? (
        <>
          {/* Tabela que contem os beneficios */}
          <TableContainer component={Paper} square>
            <Box className="w-full relative">
              {/* Botão de excluir benefício */}
              <Box className="absolute right-0">
                <Tooltip title={texts.formularioPropostaProposta.titleRemover}>
                  <IconButton
                    size="large"
                    onClick={() => {
                      props.delete(props.index);
                    }}
                    color="whiteAll"
                  >
                    <DeleteOutlineOutlinedIcon />
                  </IconButton>
                </Tooltip>
              </Box>
            </Box>
            <Table sx={{ minWidth: 750 }} aria-label="customized table">
              {/* Criação do esqueleto da tabela */}
              <TableHead sx={{ backgroundColor: "primary.main" }}>
                <TableRow>
                  <th align="center" className="p-3" style={{ width: "10%" }}>
                    <Typography
                      fontSize={FontConfig.medium}
                      fontWeight="800"
                      color="text.white"
                      onClick={() => {
                        lerTexto(texts.BeneficiosDetalheDemanda.tipo);
                      }}
                    >
                      {texts.BeneficiosDetalheDemanda.tipo}
                    </Typography>
                  </th>
                  {props.beneficio.tipoBeneficio != "QUALITATIVO" &&
                    props.beneficio.tipoBeneficio != "Qualitativo" && (
                      <>
                        <th
                          align="center"
                          className="p-3"
                          style={{ width: "15%" }}
                        >
                          <Typography
                            fontSize={FontConfig.medium}
                            fontWeight="800"
                            color="text.white"
                            onClick={() => {
                              lerTexto(
                                texts.BeneficiosDetalheDemanda.valorMensal
                              );
                            }}
                          >
                            {texts.BeneficiosDetalheDemanda.valorMensal}
                          </Typography>
                        </th>
                        <th
                          align="center"
                          className="p-3"
                          style={{ width: "10%" }}
                        >
                          <Typography
                            fontSize={FontConfig.medium}
                            fontWeight="800"
                            color="text.white"
                            onClick={() => {
                              lerTexto(texts.BeneficiosDetalheDemanda.moeda);
                            }}
                          >
                            {texts.BeneficiosDetalheDemanda.moeda}
                          </Typography>
                        </th>
                      </>
                    )}
                  <th align="center" className="p-3" style={{ width: "30%" }}>
                    <Typography
                      fontSize={FontConfig.medium}
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
                {/* Colocando as informações na tabela */}
                <TableRow>
                  <td align="center" className="p-2">
                    <FormControl
                      variant="standard"
                      sx={{ marginRight: "10px", minWidth: 90 }}
                    >
                      {/* Select de tipo do benefício */}
                      <Select
                        labelId="demo-simple-select-standard-label"
                        id="demo-simple-select-standard"
                        className="border-solid border px-1 text-center"
                        sx={{
                          borderLeft: "4px solid #00579d",
                        }}
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
                        <MenuItem value={"REAL"}>
                          {texts.BeneficiosDetalheDemanda.real}
                        </MenuItem>
                        <MenuItem value={"POTENCIAL"}>
                          {texts.BeneficiosDetalheDemanda.potencial}
                        </MenuItem>
                        <MenuItem value={"QUALITATIVO"}>
                          {texts.BeneficiosDetalheDemanda.qualitativo}
                        </MenuItem>
                      </Select>
                    </FormControl>
                  </td>
                  {props.beneficio.tipoBeneficio != "QUALITATIVO" &&
                    props.beneficio.tipoBeneficio != "Qualitativo" && (
                      <>
                        <td align="center" className="p-2">
                          <Box className="flex items-center justify-center">
                            {/* Input de valor mensal */}
                            <InputDinheiro
                              placeholder={texts.BeneficiosDetalheDemanda.digiteValorMensal}
                              fontConfig={FontConfig.medium}
                              texto={props.beneficio?.valor_mensal}
                              saveInputValue={(e) => {
                                props.setBeneficio(
                                  {
                                    ...props.beneficio,
                                    valor_mensal: e
                                  },
                                  props.index
                                );
                              }}
                              moeda={props.beneficio.moeda}
                            />
                          </Box>
                        </td>
                        <td align="center" className="p-2">
                          <FormControl
                            variant="standard"
                            sx={{ marginRight: "10px", minWidth: 90 }}
                          >
                            {/* Select de moeda do benefício */}
                            <Select
                              labelId="demo-simple-select-standard-label"
                              id="demo-simple-select-standard"
                              className="border-solid border px-1 text-center"
                              sx={{
                                borderLeft: "4px solid #00579d",
                              }}
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
                        </td>
                      </>
                    )}
                  <td className="p-2 px-5 flex justify-center overflow-auto">
                    <Box sx={{ maxHeight: "10rem" }}>
                      {/* Caixa de texto para edição da memória cálculo */}
                      <CaixaTextoQuill
                        texto={props.beneficio.memoriaCalculo}
                        onChange={(value) => {
                          alterarTexto(value);
                        }}
                        label="memoriaCalculo"
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
        <TableContainer component={Paper} square>
          <Table sx={{ minWidth: 750 }} aria-label="customized table">
            <TableHead sx={{ backgroundColor: "primary.main" }}>
              <TableRow>
                <th align="center" className="p-2" style={{ width: "10%" }}>
                  <Typography
                    fontSize={FontConfig.medium}
                    fontWeight="800"
                    color="text.white"
                    onClick={() => {
                      lerTexto(texts.BeneficiosDetalheDemanda.tipo);
                    }}
                  >
                    {texts.BeneficiosDetalheDemanda.tipo}
                  </Typography>
                </th>
                {props.beneficio.tipoBeneficio != "QUALITATIVO" &&
                  props.beneficio.tipoBeneficio != "Qualitativo" && (
                    <>
                      <th
                        align="center"
                        className="p-2"
                        style={{ width: "15%" }}
                      >
                        <Typography
                          fontSize={FontConfig.medium}
                          fontWeight="800"
                          color="text.white"
                          onClick={() => {
                            lerTexto(
                              texts.BeneficiosDetalheDemanda.valorMensal
                            );
                          }}
                        >
                          {texts.BeneficiosDetalheDemanda.valorMensal}
                        </Typography>
                      </th>
                      <th
                        align="center"
                        className="p-2"
                        style={{ width: "10%" }}
                      >
                        <Typography
                          fontSize={FontConfig.medium}
                          fontWeight="800"
                          color="text.white"
                          onClick={() => {
                            lerTexto(texts.BeneficiosDetalheDemanda.moeda);
                          }}
                        >
                          {texts.BeneficiosDetalheDemanda.moeda}
                        </Typography>
                      </th>
                    </>
                  )}
                <th align="center" style={{ width: "30%" }}>
                  <Typography
                    fontSize={FontConfig.medium}
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
                <td align="center" className="p-2">
                  {/* Tipo do benefício */}
                  <Typography
                    fontSize={FontConfig.medium}
                    color="text.primary"
                    onClick={() => {
                      lerTexto(
                        formatarTipoBeneficio(props.beneficio.tipoBeneficio)
                      );
                    }}
                  >
                    {formatarTipoBeneficio(props.beneficio.tipoBeneficio)}
                  </Typography>
                </td>
                {props.beneficio.tipoBeneficio != "QUALITATIVO" &&
                  props.beneficio.tipoBeneficio != "Qualitativo" && (
                    <>
                      {/* Valor Mensal */}
                      <td align="center" className="p-2">
                        {props.beneficio.moeda == "Real" ? (
                          <Typography
                            fontSize={FontConfig.medium}
                            color="text.primary"
                            onClick={() => {
                              lerTexto(props.beneficio.valor_mensal);
                            }}
                          >
                            R$ {props.beneficio.valor_mensal}
                          </Typography>
                        ) : props.beneficio.moeda == "Dolar" ? (
                          <Typography
                            fontSize={FontConfig.medium}
                            color="text.primary"
                            onClick={() => {
                              lerTexto(props.beneficio.valor_mensal);
                            }}
                          >
                            $ {props.beneficio.valor_mensal}
                          </Typography>
                        ) : props.beneficio.moeda == "Euro" ? (
                          <Typography
                            fontSize={FontConfig.medium}
                            color="text.primary"
                            onClick={() => {
                              lerTexto(props.beneficio.valor_mensal);
                            }}
                          >
                            {props.beneficio.valor_mensal} €
                          </Typography>
                        ) : null}
                      </td>
                      <td align="center" className="p-2">
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
                    </>
                  )}
                <td align="center" className="p-2 px-5">
                  {/* Memória de cálculo */}
                  <Typography
                    className="text-center"
                    fontSize={FontConfig.medium}
                    color="text.primary"
                    sx={{ width: "100%" }}
                    ref={memoriaCalculo}
                    onClick={() => {
                      lerTexto(props.beneficio.memoriaCalculo);
                    }}
                  >
                    {props.beneficio.memoriaCalculo}
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
