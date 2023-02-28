import React, { useContext, useState } from "react";

import {
  Box,
  Divider,
  IconButton,
  MenuItem,
  Paper,
  Table,
  TableBody,
  TableHead,
  TableRow,
  TextField,
  Tooltip,
  Typography,
} from "@mui/material";

import LogoWEG from "../../assets/logo-weg.png";

import DownloadIcon from "@mui/icons-material/Download";

import FontContext from "../../service/FontContext";
import DateService from "../../service/dateService";
import TextLanguageContext from "../../service/TextLanguageContext";

const DetalhesProposta = ({
  proposta = {
    analista: {},
    anexo: [{ id: 0, nome: "", tipo: "", dados: "" }],
    beneficios: [
      {
        id: 0,
        tipoBeneficio: "POTENCIAL" | "QUALITATIVO" | "REAL",
        valor_mensal: 0,
        moeda: "",
        memoriaCalculo: "",
      },
    ],
    buSolicitante: { id: 0, nome: "" },
    busBeneficiadas: [{ id: 0, nome: "" }],
    codigoPPM: 0,
    data: "",
    demanda: 0,
    departamento: 0,
    escopo: 0,
    fimExecucao: "",
    forum: { id: 0, nome: "", visibilidade: true },
    frequencia: "",
    gerente: 0,
    historicoProposta: [],
    id: 0,
    inicioExecucao: "",
    linkJira: "",
    naoPublicada: true,
    parecerComissao: 0,
    parecerDG: 0,
    parecerInformacao: "",
    paybackTipo: "",
    paybackValor: 0,
    problema: "",
    proposta: "",
    publicada: false,
    responsavelNegocio: [],
    secaoTI: "",
    solicitante: {},
    status: "",
    tabelaCustos: [
      {
        id: 0,
        custos: [
          {
            id: 0,
            tipoDespesa: "",
            perfilDespesa: "",
            periodoExecucao: 0,
            horas: 0,
            valorHora: 0,
          },
        ],
        ccs: [{ id: 0, codigo: 0, porcentegem: 0.0 }],
      },
    ],
    tamanho: "",
    titulo: "",
    visibilidade: true,
  },
}) => {
  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  // UseState do parecer da comissão
  const [parecerComissao, setParecerComissao] = useState("");

  // Função para baixar um anexo
  const downloadAnexo = (anexo = { id: 0, nome: "", tipo: "", dados: "" }) => {
    const file = anexo;
    let blob = new Blob([base64ToArrayBuffer(file.dados)]);
    let fileName = `${file.nome}`;

    if (navigator.msSaveBlob) {
      navigator.msSaveBlob(blob, fileName);
    } else {
      const link = document.createElement("a");
      if (link.download !== undefined) {
        const url = URL.createObjectURL(blob);
        link.setAttribute("href", url);
        link.setAttribute("download", fileName);
        link.style.visibility = "hidden";
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
        URL.revokeObjectURL(url);
      }
    }
  };

  // Função para transformar uma string em base64 para um ArrayBuffer
  const base64ToArrayBuffer = (base64) => {
    const binaryString = window.atob(base64);
    const bytes = new Uint8Array(binaryString.length);
    return bytes.map((byte, i) => binaryString.charCodeAt(i));
  };

  console.log(proposta);

  React.useEffect(() => {
    console.log(parecerComissao);
  }, [parecerComissao]);

  return (
    <Box className="mt-10 flex justify-center">
      <Box
        className="border rounded px-10 py-4 border-t-6"
        sx={{ width: "55rem", borderTopColor: "primary.main" }}
      >
        {/* Box header */}
        <Box className="w-full flex justify-between">
          <Box className="flex gap-4">
            <Typography
              color="primary"
              fontWeight="bold"
              fontSize={FontConfig.big}
            >
              {texts.detalhesProposta.ppm} {proposta.codigoPPM}
            </Typography>
            <Typography
              color="primary"
              fontWeight="bold"
              fontSize={FontConfig.big}
            >
              {texts.detalhesProposta.data}{" "}
              {DateService.getTodaysDateUSFormat(
                DateService.getDateByMySQLFormat(proposta.data)
              )}
            </Typography>
          </Box>
          <Box className="w-16">
            <img src={LogoWEG} alt="Logo WEG" />
          </Box>
        </Box>

        {/* Box Conteudo */}
        <Box className="w-full">
          {/* Titulo */}
          <Box>
            <Typography fontSize={FontConfig.smallTitle}>
              {proposta.titulo}
            </Typography>
          </Box>
          <Divider />

          {/* Box Informações gerais */}
          <Box>
            {/* Solicitante */}
            <Box className="flex mt-2">
              <Typography fontSize={FontConfig.medium} fontWeight="bold">
                {texts.detalhesProposta.solicitante}:&nbsp;
              </Typography>
              <Typography fontSize={FontConfig.medium}>
                {proposta.solicitante.nome} -{" "}
                {proposta.solicitante.departamento.nome}
              </Typography>
            </Box>

            {/* Bu solicitante */}
            <Box className="flex mt-2">
              <Typography fontSize={FontConfig.medium} fontWeight="bold">
                {texts.detalhesProposta.buSolicitante}:&nbsp;
              </Typography>
              <Typography fontSize={FontConfig.medium}>
                {proposta.buSolicitante.nome} - {proposta.buSolicitante.id}
              </Typography>
            </Box>

            {/* Gerente */}
            <Box className="flex mt-2">
              <Typography fontSize={FontConfig.medium} fontWeight="bold">
                {texts.detalhesProposta.gerente}:&nbsp;
              </Typography>
              <Typography fontSize={FontConfig.medium}>
                {proposta.gerente.nome} - {proposta.gerente.departamento.nome}
              </Typography>
            </Box>

            {/* Fórum */}
            <Box className="flex mt-2">
              <Typography fontSize={FontConfig.medium} fontWeight="bold">
                {texts.detalhesProposta.forum}:&nbsp;
              </Typography>
              <Typography fontSize={FontConfig.medium}>
                {proposta.forum.nome}
              </Typography>
            </Box>

            {/* Proposta / Objetivo */}
            <Box className="mt-2">
              <Typography fontSize={FontConfig.medium} fontWeight="bold">
                {texts.detalhesProposta.proposta}:&nbsp;
              </Typography>
              <Box className="mx-4">
                <Typography fontSize={FontConfig.medium}>
                  {proposta.proposta}
                </Typography>
              </Box>
            </Box>

            {/* Problema / Situação atual */}
            <Box className="mt-2">
              <Typography fontSize={FontConfig.medium} fontWeight="bold">
                {texts.detalhesProposta.problema}:&nbsp;
              </Typography>
              <Box className="mx-4">
                <Typography fontSize={FontConfig.medium}>
                  {proposta.problema}
                </Typography>
              </Box>
            </Box>

            {/* Escopo da proposta */}
            <Box className="mt-2">
              <Typography fontSize={FontConfig.medium} fontWeight="bold">
                {texts.detalhesProposta.escopoDaProposta}:&nbsp;
              </Typography>
              <Box className="mx-4">
                <Typography fontSize={FontConfig.medium}>
                  {proposta.escopo}
                </Typography>
              </Box>
            </Box>

            {/* Frequência */}
            <Box className="flex mt-2">
              <Typography fontSize={FontConfig.medium} fontWeight="bold">
                {texts.detalhesProposta.frequencia}:&nbsp;
              </Typography>
              <Typography fontSize={FontConfig.medium}>
                {proposta.frequencia}
              </Typography>
            </Box>

            {/* Tamanho */}
            <Box className="flex mt-2">
              <Typography fontSize={FontConfig.medium} fontWeight="bold">
                {texts.detalhesProposta.tamanho}:&nbsp;
              </Typography>
              <Typography fontSize={FontConfig.medium}>
                {proposta.tamanho}
              </Typography>
            </Box>

            {/* {proposta.anexo}
            {proposta.beneficios}
            {proposta.busBeneficiadas}
            {proposta.buSolicitante}
            {proposta.frequencia}
            {proposta.linkJira}
            {proposta.tamanho}
            {proposta.data} 
            {proposta.gerente}
            {proposta.forum}

            {proposta.parecerComissao}
            {proposta.parecerDG}
            {proposta.parecerInformacao}
            */}

            {/* Resto das informações */}

            {/* Tabela de custos */}
            <Box className="mt-2">
              <Typography fontSize={FontConfig.medium} fontWeight="bold">
                {texts.detalhesProposta.tabelaDeCustos}:&nbsp;
              </Typography>
              <Box className="mx-4">
                {
                  // [
                  //   {
                  //     id: 0,
                  //     custos: [
                  //       {
                  //         id: 1,
                  //         tipoDespesa: "Tipo 1",
                  //         perfilDespesa: "Perfil",
                  //         periodoExecucao: 12,
                  //         horas: 12,
                  //         valorHora: 59.0,
                  //       },
                  //     ],
                  //     ccs: [
                  //       { id: 0, codigo: 21312321321, porcentegem: 0.0 },
                  //       { id: 0, codigo: 0, porcentegem: 0.0 },
                  //       { id: 0, codigo: 0, porcentegem: 0.0 },
                  //     ],
                  //   },
                  //   {
                  //     id: 0,
                  //     custos: [
                  //       {
                  //         id: 1,
                  //         tipoDespesa: "Tipo 1",
                  //         perfilDespesa: "Perfil",
                  //         periodoExecucao: 12,
                  //         horas: 12,
                  //         valorHora: 59.0,
                  //       },
                  //     ],
                  //     ccs: [
                  //       { id: 0, codigo: 21312321321, porcentegem: 0.0 },
                  //       { id: 0, codigo: 0, porcentegem: 0.0 },
                  //       { id: 0, codigo: 0, porcentegem: 0.0 },
                  //     ],
                  //   },
                  // ]
                  proposta.tabelaCustos.map((tabela, index) => {
                    return <TabelaCustos key={index} dados={tabela} />;
                  })
                }
              </Box>
            </Box>

            {/* Benefícios */}
            <Box className="mt-2">
              <Typography fontSize={FontConfig.medium} fontWeight="bold">
                {texts.detalhesProposta.beneficios}:&nbsp;
              </Typography>
              <Box className="mx-4">
                {proposta.beneficios.length > 0 ? (
                  proposta.beneficios.map((beneficio, index) => {
                    return <Beneficio key={index} beneficio={beneficio} />;
                  })
                ) : (
                  <Typography
                    className="text-center"
                    fontSize={FontConfig.medium}
                    color="text.secondary"
                  >
                    {texts.detalhesProposta.semBeneficios}
                  </Typography>
                )}
              </Box>
            </Box>

            {/* Bus beneficiadas */}
            <Box className="mt-2">
              <Typography fontSize={FontConfig.medium} fontWeight="bold">
                {texts.detalhesProposta.busBeneficiadas}:&nbsp;
              </Typography>
              <Box className="mx-8">
                {proposta.busBeneficiadas.length > 0 ? (
                  <ol className="list-disc">
                    {proposta.busBeneficiadas.map((bu, index) => {
                      return (
                        <li key={index}>
                          {bu.nome} - {bu.id}
                        </li>
                      );
                    })}
                  </ol>
                ) : (
                  <Typography
                    className="text-center"
                    fontSize={FontConfig.medium}
                    color="text.secondary"
                  >
                    {texts.detalhesProposta.semBuBeneficiada}
                  </Typography>
                )}
              </Box>
            </Box>

            {/* Anexos */}
            <Box className="mt-2">
              <Typography fontSize={FontConfig.medium} fontWeight="bold">
                {texts.detalhesProposta.anexos}:&nbsp;
              </Typography>
              <Box className="mx-4">
                {proposta.anexo.length > 0 ? (
                  proposta.anexo.map((anexo, index) => {
                    return (
                      <Paper
                        key={index}
                        elevation={0}
                        className="flex justify-between items-center mt-2 px-2 border border-l-4"
                        sx={{ borderLeftColor: "primary.main" }}
                        square
                      >
                        <Typography fontSize={FontConfig.medium}>
                          {anexo.nome} - {anexo.tipo}
                        </Typography>
                        <Tooltip title={texts.detalhesProposta.download}>
                          <IconButton onClick={() => downloadAnexo(anexo)}>
                            <DownloadIcon />
                          </IconButton>
                        </Tooltip>
                      </Paper>
                    );
                  })
                ) : (
                  <Typography
                    className="text-center"
                    fontSize={FontConfig.medium}
                    color="text.secondary"
                  >
                    {texts.detalhesProposta.semAnexos}
                  </Typography>
                )}
              </Box>
            </Box>

            {/* Link do Jira */}
            <Box className="flex mt-2">
              <Typography fontSize={FontConfig.medium} fontWeight="bold">
                {texts.detalhesProposta.linkJira}:&nbsp;
              </Typography>
              <Typography fontSize={FontConfig.medium}>
                {proposta.linkJira}
              </Typography>
            </Box>

            {/* Período de execução */}
            <Box className="flex mt-2">
              <Typography fontSize={FontConfig.medium} fontWeight="bold">
                {texts.detalhesProposta.periodoDeExecucao}:&nbsp;
              </Typography>
              <Typography fontSize={FontConfig.medium}>
                {DateService.getTodaysDateUSFormat(
                  DateService.getDateByMySQLFormat(proposta.inicioExecucao)
                )}{" "}
                {texts.detalhesProposta.ate}{" "}
                {DateService.getTodaysDateUSFormat(
                  DateService.getDateByMySQLFormat(proposta.fimExecucao)
                )}
              </Typography>
            </Box>

            {/* Payback */}
            <Box className="flex mt-2">
              <Typography fontSize={FontConfig.default} fontWeight="bold">
                {texts.detalhesProposta.payback}:&nbsp;
              </Typography>
              <Typography fontSize={FontConfig.default}>
                {proposta.paybackValor} {proposta.paybackTipo.toLowerCase()}
              </Typography>
            </Box>

            {/* Pareceres */}
            <Box className="mt-6">
              <Typography fontSize={FontConfig.medium} fontWeight="bold">
                {texts.detalhesProposta.pareceres}:&nbsp;
              </Typography>
              <Box className="mx-4 mt-2">
                <Box className="flex">
                  <Box className="flex items-center mt-4">
                    <Typography>{proposta.forum.nome}: &nbsp;</Typography>
                  </Box>
                  <TextField
                    select
                    label={texts.detalhesProposta.parecer}
                    value={parecerComissao}
                    onChange={(event) => setParecerComissao(event.target.value)}
                    variant="standard"
                    sx={{ width: "10rem", marginLeft: "0.5rem" }}
                  >
                    <MenuItem key={"Aprovado"} value={"APROVADO"}>
                      <Typography fontSize={FontConfig.medium}>
                        {texts.detalhesProposta.aprovado}
                      </Typography>
                    </MenuItem>
                    <MenuItem key={"Reprovado"} value={"REPROVADO"}>
                      <Typography fontSize={FontConfig.medium}>
                        {texts.detalhesProposta.reprovado}
                      </Typography>
                    </MenuItem>
                    <MenuItem key={"Devolvido"} value={"DEVOLVIDO"}>
                      <Typography fontSize={FontConfig.medium}>
                        {texts.detalhesProposta.devolvido}
                      </Typography>
                    </MenuItem>
                    <MenuItem key={"Business Case"} value={"BUSINESSCASE"}>
                      <Typography fontSize={FontConfig.medium}>
                        {texts.detalhesProposta.businessCase}
                      </Typography>
                    </MenuItem>
                  </TextField>
                </Box>
              </Box>
            </Box>

            {/* Pareceres */}
            {proposta.status == "?" ? <></> : null}

            {/* Responsáveis do negócio */}
            <Box className="mt-4 text-center">
              {proposta.responsavelNegocio.map((responsavel, index) => {
                return (
                  <Typography key={index} fontSize={FontConfig.medium}>
                    {responsavel.nome} - {responsavel.area}
                  </Typography>
                );
              })}
              <Typography fontSize={FontConfig.medium} fontWeight="bold">
                {texts.detalhesProposta.reponsaveisPeloNegocio}
              </Typography>
            </Box>
          </Box>
        </Box>
      </Box>
    </Box>
  );
};

const TabelaCustos = ({
  dados = {
    id: 0,
    custos: [
      {
        id: 0,
        tipoDespesa: "",
        perfilDespesa: "",
        periodoExecucao: 0,
        horas: 0,
        valorHora: 0,
      },
    ],
    ccs: [{ id: 0, codigo: 0, porcentegem: 0.0 }],
  },
}) => {
  const { FontConfig } = useContext(FontContext);
  const { texts } = useContext(TextLanguageContext);

  return (
    <Paper className="w-full mt-2 mb-6" square>
      <Table className="table-fixed w-full">
        <TableHead>
          <TableRow sx={{ backgroundColor: "primary.main" }}>
            <th className="text-white p-1">
              <Typography fontWeight="bold" fontSize={FontConfig.default}>
                {texts.detalhesProposta.tipoDaDespesa}
              </Typography>
            </th>
            <th className="text-white p-1">
              <Typography fontWeight="bold" fontSize={FontConfig.default}>
                {texts.detalhesProposta.perfilDaDespesa}
              </Typography>
            </th>
            <th className="text-white p-1">
              <Typography fontWeight="bold" fontSize={FontConfig.default}>
                {texts.detalhesProposta.periodoDeExecucaoTabela}
              </Typography>
            </th>
            <th className="text-white p-1">
              <Typography fontWeight="bold" fontSize={FontConfig.default}>
                {texts.detalhesProposta.horas}
              </Typography>
            </th>
            <th className="text-white p-1">
              <Typography fontWeight="bold" fontSize={FontConfig.default}>
                {texts.detalhesProposta.valorHora}
              </Typography>
            </th>
            <th className="text-white p-1">
              <Typography fontWeight="bold" fontSize={FontConfig.default}>
                {texts.detalhesProposta.total}
              </Typography>
            </th>
          </TableRow>
        </TableHead>
        <TableBody>
          {dados.custos.map((custo, index) => {
            return <CustosRow key={index} custo={custo} />;
          })}
        </TableBody>
      </Table>
      <Box className="mt-2">
        <Table className="table-fixed w-full">
          <TableHead>
            <TableRow sx={{ backgroundColor: "primary.main" }}>
              <th className="text-white p-1">
                <Typography fontSize={FontConfig.medium}>
                  {texts.detalhesProposta.ccs}
                </Typography>
              </th>
              <th className="text-white p-1">
                <Typography fontSize={FontConfig.medium}>
                  {texts.detalhesProposta.porcentagem}
                </Typography>
              </th>
            </TableRow>
          </TableHead>
          <TableBody>
            {dados.ccs.map((cc, index) => {
              return (
                <TableRow key={index} className="w-full border rounded">
                  <td className="text-center p-1">
                    <Typography fontSize={FontConfig.medium}>
                      {cc.codigo}
                    </Typography>
                  </td>
                  <td className="text-center p-1">
                    <Typography fontSize={FontConfig.medium}>
                      {cc.porcentegem}%
                    </Typography>
                  </td>
                </TableRow>
              );
            })}
          </TableBody>
        </Table>
      </Box>
    </Paper>
  );
};

const CustosRow = ({
  custo = {
    id: 0,
    tipoDespesa: "",
    perfilDespesa: "",
    periodoExecucao: 0,
    horas: 0,
    valorHora: 0,
  },
}) => {
  const { FontConfig } = useContext(FontContext);
  const { texts } = useContext(TextLanguageContext);

  const getValorFormatted = (valor) => {
    let local = "pt-BR";
    let tipoMoeda = "BRL";

    switch (texts.linguagem) {
      case "pt":
        local = "pt-BR";
        tipoMoeda = "BRL";
        break;
      case "en":
        local = "en-US";
        tipoMoeda = "USD";
        break;
      case "ch":
        local = "zh-CN";
        tipoMoeda = "CNY";
        break;
      default:
        local = "pt-BR";
        tipoMoeda = "BRL";
        break;
    }

    return valor
      ? valor.toLocaleString(local, {
          style: "currency",
          currency: tipoMoeda,
        })
      : 0.0;
  };

  return (
    <TableRow>
      <td className="p-2 text-center">
        <Typography fontSize={FontConfig.default}>
          {custo.tipoDespesa}
        </Typography>
      </td>
      <td className="p-2 text-center">
        <Typography fontSize={FontConfig.default}>
          {custo.perfilDespesa}
        </Typography>
      </td>
      <td className="p-2 text-center">
        <Typography fontSize={FontConfig.default}>
          {custo.periodoExecucao}
        </Typography>
      </td>
      <td className="p-2 text-center">
        <Typography fontSize={FontConfig.default}>{custo.horas}</Typography>
      </td>
      <td className="p-2 text-center">
        <Typography fontSize={FontConfig.default}>
          {getValorFormatted(custo.valorHora)}
        </Typography>
      </td>
      <td className="p-2 text-center">
        <Typography fontSize={FontConfig.default}>
          {getValorFormatted(custo.horas * custo.valorHora)}
        </Typography>
      </td>
    </TableRow>
  );
};

const Beneficio = ({
  beneficio = {
    id: 0,
    tipoBeneficio: "POTENCIAL" | "QUALITATIVO" | "REAL",
    valor_mensal: 0,
    moeda: "",
    memoriaCalculo: "",
  },
}) => {
  // Context para obter as configurações de fonte do sistema
  const { FontConfig } = useContext(FontContext);
  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);
  return (
    <Paper
      className="flex justify-between items-center mt-2 border-t-4"
      sx={{ borderTopColor: "primary.main" }}
      square
    >
      {/*  */}
      <Table>
        <TableBody>
          <TableRow>
            <th className="p-1">
              <Typography
                color="primary"
                fontWeight="bold"
                fontSize={FontConfig.medium}
              >
                {texts.detalhesProposta.tipoBeneficio}
              </Typography>
            </th>
            <th className="p-1">
              <Typography
                color="primary"
                fontWeight="bold"
                fontSize={FontConfig.medium}
              >
                {texts.detalhesProposta.valorMensal}
              </Typography>
            </th>
            <th className="p-1">
              <Typography
                color="primary"
                fontWeight="bold"
                fontSize={FontConfig.medium}
              >
                {texts.detalhesProposta.moeda}
              </Typography>
            </th>
            <th className="p-1">
              <Typography
                color="primary"
                fontWeight="bold"
                fontSize={FontConfig.medium}
              >
                {texts.detalhesProposta.memoriaCalculo}
              </Typography>
            </th>
          </TableRow>
        </TableBody>
        <TableBody className="border-t">
          <TableRow>
            <td className="text-center p-1">
              <Typography fontSize={FontConfig.default}>
                {beneficio.tipoBeneficio[0].toUpperCase() +
                  beneficio.tipoBeneficio.substring(1).toLowerCase()}
              </Typography>
            </td>
            <td className="text-center p-1">
              <Typography fontSize={FontConfig.default}>
                {beneficio.valor_mensal}
              </Typography>
            </td>
            <td className="text-center p-1">
              <Typography fontSize={FontConfig.default}>
                {beneficio.moeda}
              </Typography>
            </td>
            <td className="text-center p-1">
              <Typography fontSize={FontConfig.default}>
                {beneficio.memoriaCalculo}
              </Typography>
            </td>
          </TableRow>
        </TableBody>
      </Table>
    </Paper>
  );
};

export default DetalhesProposta;
