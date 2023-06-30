import React, { useState, useContext, useEffect } from "react";

import { TableContainer, Table, TableHead, TableRow, TableBody, Paper, Typography, Box, Tooltip, FormControl, Select, MenuItem, InputLabel, } from "@mui/material";

import DeleteOutlineOutlinedIcon from "@mui/icons-material/DeleteOutlineOutlined";
import AddCircleOutlineOutlinedIcon from "@mui/icons-material/AddCircleOutlineOutlined";

import LinhaTabelaCustos from "../LinhaTabelaCustos/LinhaTabelaCustos";
import LinhaTabelaCCs from "../LinhaTabelaCCs/LinhaTabelaCCs";

import FontContext from "../../service/FontContext";
import CustosService from "../../service/custosService";
import TextLanguageContext from "../../service/TextLanguageContext";
import SpeechSynthesisContext from "../../service/SpeechSynthesisContext";

/** Componente utilizado para representar a tabela de custos utilizada na proposta */
const Custos = (props) => {

  /** Contexto para trocar a linguagem */
  const { texts } = useContext(TextLanguageContext);

  /** Context para alterar o tamanho da fonte */
  const { FontConfig } = useContext(FontContext);

  /** Context para ler o texto da tela */
  const { lerTexto } = useContext(SpeechSynthesisContext);

  /** UseState para armazenar as horas totais dos custos */
  const [horasTotais, setHorasTotais] = useState(0);

  /** UseState para armazenar o valor total dos custos */
  const [valorTotal, setValorTotal] = useState(0);

  /** UseState para armazenar a porcentagem total dos custos */
  const [porcentagemTotal, setPorcentagemTotal] = useState(0);

  /** UseState para armazenar o valor do tipo da despesa */
  const [tipoDespesa, setTipoDespesa] = useState("");

  /** UseEffect para atualizar as horas totais dos custos */
  useEffect(() => {
    let aux = 0;
    for (let i = 0; i < props.custos[props.index].custos.length; i++) {
      const horasString = props.custos[props.index].custos[i].horas;
      if (horasString) {
        if (typeof horasString !== "number") {
          const horasNumber = parseFloat(horasString.replace(",", "."));

          if (!isNaN(horasNumber)) {
            aux += horasNumber;
          }
        } else {
          aux += horasString;
        }
      } else {
        aux += 0;
      }
    }
    setHorasTotais(aux);
  }, [props.custos, props.index]);

  /** UseEffect para atualizar o valor total dos custos */
  useEffect(() => {
    let aux = 0;
    for (let i = 0; i < props.custos[props.index].custos.length; i++) {
      const totalString = props.custos[props.index].custos[i].total;

      if (totalString) {
        const totalNumber = parseFloat(totalString.replace(",", "."));

        if (!isNaN(totalNumber)) {
          aux += totalNumber;
        }
      } else {
        aux += 0;
      }
    }
    setValorTotal(aux.toFixed(2));
  }, [props.custos]);

  /** UseEffect para atualizar a porcentagem total dos custos */
  useEffect(() => {
    let aux = 0;
    for (let i = 0; i < props.custos[props.index].ccs.length; i++) {
      aux += props.custos[props.index].ccs[i].porcentagem * 1;
    }
    setPorcentagemTotal(aux);
  }, [props.custos]);

  /** useEffec para alterar o tipo de despesa do select */
  useEffect(() => {
    setTipoDespesa(props.custos[props.index].tipoDespesa);
  }, [props.custos]);

  /** Função para criar um custo no banco de dados e adicioná-lo como uma linha na tabela */
  const adicionarLinhaCustos = () => {
    CustosService.postCusto({
      tipoDespesa: "",
      perfilDespesa: "",
      periodoExecucao: "",
      horas: "",
      valorHora: "",
    }).then((response) => {
      let custosNovos = [...props.custos];
      custosNovos[props.index].custos.push(response);
      props.setCustos(custosNovos);
    });
  };

  /** Função usada para excluir uma linha de custo da tabela de custos */
  const deletarLinhaCustos = (custoId, indexCusto) => {
    CustosService.deleteCusto(custoId).then((response) => {
      let custosNovos = [...props.custos];
      custosNovos[props.index].custos.splice(indexCusto, 1);
      props.setCustos(custosNovos);
    });
  };

  /** Função para adicionar uma nova linha de CC no centro de custos */
  const adicionarLinhaCC = () => {
    CustosService.postCC({ codigo: 0, porcentagem: 0 }).then((response) => {
      let custosNovos = [...props.custos];
      custosNovos[props.index].ccs.push(response);
      props.setCustos(custosNovos);
    });
  };

  /** Função para tirar uma linha de CC do centro de custos */
  const deletarLinhaCC = (ccId, indexCC) => {
    CustosService.deleteCC(ccId).then((response) => {
      let custosNovos = [...props.custos];
      custosNovos[props.index].ccs.splice(indexCC, 1);
      props.setCustos(custosNovos);
    });
  };

  /** handleChance para selecionar o tipo de despesa do custo */
  const handleChange = (tipo) => {
    let aux = [...props.custos];
    aux[props.index].tipoDespesa = tipo.target.value;
    props.setCustos(aux);
  };

  return (
    <Box className="flex w-full mt-5">
      <Box className="flex mr-2 items-top">
        <Box className="h-full flex items-center">
          {/* Ícone para deletar uma tabela de custos */}
          <Tooltip title={texts.custos.excluirTabelaDeCustos}>
            <DeleteOutlineOutlinedIcon
              fontSize="large"
              className="mr-2 delay-120 hover:scale-110 duration-300"
              sx={{ color: "icon.main", cursor: "pointer" }}
              onClick={() => {
                props.deletarCustos(props.index);
              }}
            />
          </Tooltip>
        </Box>
        <Box>
          {/* Dropdown tipo despesa */}
          <Box>
            <FormControl variant="standard" sx={{ minWidth: "9rem" }}>
              <InputLabel id="demo-simple-select-standard-label">
                {texts.custos.tipoDaDespesa}
              </InputLabel>
              <Select
                labelId="demo-simple-select-standard-label"
                id="demo-simple-select-standard"
                value={tipoDespesa || ""}
                onChange={handleChange}
                label={texts.custos.tipoDaDespesa}
              >
                <MenuItem value={"Interna"}>
                  <Typography fontSize={FontConfig.medium}>
                    {texts.custos.interna}
                  </Typography>
                </MenuItem>
                <MenuItem value={"Externa"}>
                  <Typography fontSize={FontConfig.medium}>
                    {texts.custos.externa}
                  </Typography>
                </MenuItem>
              </Select>
            </FormControl>
          </Box>
          <Box className="flex items-top mt-4">
            <Box className="mr-3">
              {/* Criação da tabela e adicionando as informações nela */}
              <Paper className="w-full pb-1">
                <TableContainer component={Paper}>
                  <Table
                    sx={{ minWidth: "90%", alignItems: "center" }}
                    aria-label="customized table"
                  >
                    <TableHead sx={{ backgroundColor: "primary.main" }}>
                      <TableRow>
                        <th
                          align="center"
                          className="p-4 w-0"
                          style={{ width: "20%" }}
                        >
                          <Typography
                            fontSize={FontConfig.big}
                            fontWeight="800"
                            color="text.white"
                            onClick={() => {
                              lerTexto(texts.custos.perfilDaDespesa);
                            }}
                          >
                            {texts.custos.perfilDaDespesa}
                          </Typography>
                        </th>
                        <th
                          align="center"
                          className="p-4 w-0"
                          style={{ width: "15%", minWidth: "200px" }}
                        >
                          <Typography
                            fontSize={FontConfig.big}
                            fontWeight="800"
                            color="text.white"
                            onClick={() => {
                              lerTexto(texts.custos.periodoDeExecucao);
                            }}
                          >
                            {texts.custos.periodoDeExecucao}
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
                              lerTexto(texts.custos.horas);
                            }}
                          >
                            {texts.custos.horas}
                          </Typography>
                        </th>
                        <th
                          align="center"
                          className="p-4 w-0"
                          style={{ width: "20%" }}
                        >
                          <Typography
                            fontSize={FontConfig.big}
                            fontWeight="800"
                            color="text.white"
                            onClick={() => {
                              lerTexto(texts.custos.valorHora);
                            }}
                          >
                            {texts.custos.valorHora}
                          </Typography>
                        </th>
                        <th
                          align="center"
                          className="p-4 w-0"
                          style={{ width: "20%" }}
                        >
                          <Typography
                            fontSize={FontConfig.big}
                            fontWeight="800"
                            color="text.white"
                            onClick={() => {
                              lerTexto(texts.custos.total);
                            }}
                          >
                            {texts.custos.total}
                          </Typography>
                        </th>
                      </TableRow>
                    </TableHead>
                    {/* Criando uma nova linha a cada custo */}
                    <TableBody>
                      {props.dados.custos?.map((despesa, index) => {
                        return (
                          <LinhaTabelaCustos
                            key={index}
                            dados={props.dados}
                            index={index}
                            deletarLinhaCustos={deletarLinhaCustos}
                            indexCusto={props.index}
                            setCustos={props.setCustos}
                            custos={props.custos}
                          />
                        );
                      })}
                    </TableBody>
                  </Table>
                </TableContainer>
                <Box className="w-full flex justify-between items-center m-2">
                  <Box className="flex w-full">
                    <Typography
                      fontSize={FontConfig.medium}
                      sx={{ marginRight: "8px" }}
                      onClick={() => {
                        lerTexto(texts.custos.total);
                      }}
                    >
                      {texts.custos.total}:{" "}
                    </Typography>
                    <Typography
                      fontSize={FontConfig.medium}
                      sx={{ marginRight: "15px" }}
                      onClick={() => {
                        lerTexto(horasTotais, " ", texts.custos.h);
                      }}
                    >
                      {horasTotais}
                      {texts.custos.h}
                    </Typography>
                    <Typography
                      fontSize={FontConfig.medium}
                      sx={{ marginRight: "15px" }}
                    >
                      -
                    </Typography>
                    <Typography
                      fontSize={FontConfig.medium}
                      sx={{ marginRight: "8px" }}
                      onClick={() => {
                        lerTexto(texts.custos.moeda, " ", texts.custos.moeda);
                      }}
                    >
                      {texts.custos.moeda}
                      {valorTotal}
                    </Typography>
                  </Box>

                  {/* Ícone para adicionar uma nova linha de custos */}
                  <Tooltip title={texts.custos.adicionarNovaLinha}>
                    <AddCircleOutlineOutlinedIcon
                      fontSize="medium"
                      className="mr-3 delay-120 hover:scale-110 duration-300"
                      sx={{ color: "icon.main", cursor: "pointer" }}
                      onClick={adicionarLinhaCustos}
                    />
                  </Tooltip>
                </Box>
              </Paper>
            </Box>
            <Paper
              className="h-full pb-1"
              sx={{ width: "25%", minWidth: "263px" }}
            >
              {/* Outra tabela para os CCs */}
              <TableContainer component={Paper}>
                <Table sx={{ minWidth: "100%" }} aria-label="customized table">
                  <TableHead
                    sx={{ backgroundColor: "primary.main", height: "5.4rem" }}
                  >
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
                            lerTexto(texts.custos.ccs);
                          }}
                        >
                          {texts.custos.ccs}
                        </Typography>
                      </th>
                    </TableRow>
                  </TableHead>

                  {/* Criando uma nova linha a cada cc */}
                  <TableBody>
                    {props.dados.ccs?.map((cc, index) => {
                      return (
                        <LinhaTabelaCCs
                          key={index}
                          dados={props.dados}
                          index={index}
                          deletarLinhaCCs={deletarLinhaCC}
                          indexCusto={props.index}
                          setCustos={props.setCustos}
                          custos={props.custos}
                        />
                      );
                    })}
                  </TableBody>
                </Table>
              </TableContainer>
              <Box className="w-full flex justify-between items-center m-2">
                <Box className="flex">
                  <Typography
                    fontSize={FontConfig.medium}
                    sx={{ marginRight: "8px" }}
                    onClick={() => {
                      lerTexto(texts.custos.total);
                    }}
                  >
                    {texts.custos.total}:
                  </Typography>
                  <Typography
                    fontSize={FontConfig.medium}
                    onClick={() => {
                      lerTexto(porcentagemTotal);
                    }}
                  >
                    {porcentagemTotal}%{" "}
                  </Typography>
                </Box>

                {/* Ícone para adicionar uma nova linha de CC */}
                <Tooltip title={texts.custos.adicionarNovaLinha}>
                  <AddCircleOutlineOutlinedIcon
                    fontSize="medium"
                    className="mr-3 delay-120 hover:scale-110 duration-300"
                    sx={{ color: "icon.main", cursor: "pointer" }}
                    onClick={adicionarLinhaCC}
                  />
                </Tooltip>
              </Box>
            </Paper>
          </Box>
        </Box>
      </Box>
    </Box>
  );
};

export default Custos;