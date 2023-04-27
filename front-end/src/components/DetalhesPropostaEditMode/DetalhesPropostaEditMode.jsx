import React, { useContext, useEffect, useState, useRef } from "react";

import {
  Box,
  Divider,
  IconButton,
  Input,
  MenuItem,
  Paper,
  Select,
  Table,
  TableBody,
  TableHead,
  TableRow,
  TextField,
  Tooltip,
  Typography,
} from "@mui/material";

// import "./DetalhesPropostaEditMode.css"

import ClipLoader from "react-spinners/ClipLoader";

import LogoWEG from "../../assets/logo-weg.png";

import DownloadIcon from "@mui/icons-material/Download";
import CheckIcon from "@mui/icons-material/Check";
import SyncAltIcon from "@mui/icons-material/SyncAlt";
import ClearIcon from "@mui/icons-material/Clear";

import CaixaTextoQuill from "../CaixaTextoQuill/CaixaTextoQuill";

import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";
import EntitiesObjectService from "../../service/entitiesObjectService";
import DateService from "../../service/dateService";
import BuService from "../../service/buService";
import ForumService from "../../service/forumService";
import SecaoTIService from "../../service/secaoTIService";
import ReactQuill from "react-quill";

const propostaExample = EntitiesObjectService.proposta();

const DetalhesPropostaEditMode = ({
  propostaData = propostaExample,
  setPropostaData = () => {},
  setIsEditing = () => {},
}) => {
  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  // Estado da proposta editável
  const [proposta, setProposta] = useState(propostaData);

  // Estado para mostrar tela de loading
  const [isLoading, setIsLoading] = useState(true);

  // Estado da lista de BU
  const [listaBus, setListaBus] = useState([EntitiesObjectService.bu()]);

  // Estado da lista de forum
  const [listaForuns, setListaForuns] = useState([
    EntitiesObjectService.forum(),
  ]);

  // Estado da lista de seções de TI
  const [listaSecoesTI, setListaSecoesTI] = useState([
    EntitiesObjectService.secaoTI(),
  ]);

  // Referência para o texto do problema
  const problemaText = useRef(null);

  // Referência para o texto da proposta
  const propostaText = useRef(null);

  // Modules usados para o React Quill
  const modulesQuill = {
    toolbar: [
      [{ size: [] }],
      ["bold", "italic", "underline", "strike", "blockquote"],
      [
        { list: "ordered" },
        { list: "bullet" },
        { indent: "-1" },
        { indent: "+1" },
      ],
      [{ script: "sub" }, { script: "super" }],
      ["clean"],
    ],
  };

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

  useEffect(() => {
    console.log("update proposta: ", proposta);
  }, [proposta]);

  // Função para saber se as listas necessárias para a edição da proposta fizeram a requisição
  const isAllsListsPopulated = () => {
    return (
      listaBus.length > 1 && listaForuns.length > 1 && listaSecoesTI.length > 1
    );
  };

  // ***************************************** Handlers ***************************************** //

  // Handler cancelar edição
  const handleOnCancelEditClick = () => {
    setIsEditing(false);
  };

  // Handler salvar edição
  const handleOnSaveEditClick = () => {
    setIsEditing(false);
  };

  const handleOnPPMChange = (event) => {
    setProposta({ ...proposta, codigoPPM: event.target.value });
  };

  const handleOnDataChange = (event) => {
    setProposta({ ...proposta, data: event.target.value });
  };

  const handleOnPublicadaClick = () => {
    setProposta({ ...proposta, publicada: !proposta.publicada });
  };

  const handleOnTituloChange = (event) => {
    setProposta({ ...proposta, titulo: event.target.value });
  };

  // Handler para quando for selecionado uma nova BU
  const handleOnBuSolicitanteSelect = (event) => {
    setProposta({
      ...proposta,
      buSolicitante: listaBus.find((bu) => bu.idBu == event.target.value),
    });
  };

  // Handler para quando for selecionado um novo fórum
  const handleOnForumSelect = (event) => {
    setProposta({
      ...proposta,
      forum: listaForuns.find((forum) => forum.idForum == event.target.value),
    });
  };

  // Handler para quando for selecionado um novo tamanho
  const handleOnTamanhoSelect = (event) => {
    setProposta({
      ...proposta,
      tamanho: event.target.value,
    });
  };

  const handleOnSecaoTISelect = (event) => {
    setProposta({
      ...proposta,
      secaoTI: listaSecoesTI.find(
        (secaoTI) => secaoTI.idSecao == event.target.value
      ),
    });
  };

  const handleOnPropostaChange = (event) => {
    setProposta({
      ...proposta,
      proposta: event,
    });
  };

  const handleOnProblemaChange = (event) => {
    setProposta({
      ...proposta,
      problema: event,
    });
  };

  const handleOnEscopoChange = (event) => {
    setProposta({
      ...proposta,
      escopo: event,
    });
  };

  const handleOnFrequenciaChange = (event) => {
    setProposta({
      ...proposta,
      frequencia: event,
    });
  };

  // ***************************************** Fim Handlers ***************************************** //

  // ***************************************** UseEffects ***************************************** //

  useEffect(() => {
    // Colocando o texto do problema em seu elemento
    if (problemaText.current) {
      problemaText.current.innerHTML = proposta.problema;
    }

    // Colocando o texto da proposta em seu elemento
    if (propostaText.current) {
      propostaText.current.innerHTML = proposta.proposta;
    }
  });

  useEffect(() => {
    if (isAllsListsPopulated()) {
      setIsLoading(false);
      
    }
  }, [listaBus, listaForuns, listaSecoesTI]);

  useEffect(() => {
    BuService.getAll().then((busReponse) => {
      setListaBus(busReponse);
    });

    ForumService.getAll().then((forunsResponse) => {
      setListaForuns(forunsResponse);
    });

    SecaoTIService.getAll().then((secoesTIResponse) => {
      setListaSecoesTI(secoesTIResponse);
    });
  }, []);

  // ***************************************** Fim UseEffects ***************************************** //

  if (isLoading)
    return (
      <Box className="flex justify-center">
        <Box
          className="flex justify-center border rounded px-10 py-4 border-t-6 relative"
          sx={{ width: "55rem", borderTopColor: "primary.main" }}
        >
          <ClipLoader className="mt-2" color="#00579D" size={110} />
        </Box>
      </Box>
    );

  return (
    <>
      {/* Box header */}
      <Box className="w-full flex justify-between">
        <Box className="flex gap-4">
          <Box className="flex gap-2">
            <Typography
              color="primary"
              fontWeight="bold"
              fontSize={FontConfig.big}
            >
              {texts.detalhesProposta.ppm}:
            </Typography>

            <TextField
              variant="standard"
              size="small"
              value={proposta.codigoPPM}
              onChange={handleOnPPMChange}
              sx={{ width: "7rem" }}
            />
          </Box>
          <Box className="flex gap-2">
            <Typography
              color="primary"
              fontWeight="bold"
              fontSize={FontConfig.big}
            >
              {texts.detalhesProposta.data}{" "}
            </Typography>
            <TextField
              variant="standard"
              size="small"
              value={DateService.getTodaysDateUSFormat(proposta.data)}
              onChange={handleOnDataChange}
              type="date"
              sx={{ width: "8rem" }}
            />
          </Box>
          {proposta.publicada != null && (
            <Box className="flex gap items-start">
              <Typography
                color="primary"
                fontWeight="bold"
                fontSize={FontConfig.big}
              >
                {proposta.publicada
                  ? texts.detalhesProposta.publicada.toUpperCase()
                  : texts.detalhesProposta.naoPublicada.toUpperCase()}
              </Typography>
              <IconButton
                onClick={handleOnPublicadaClick}
                className="relative -top-1"
              >
                <SyncAltIcon
                  color="primary"
                  sx={{ fontSize: "18px", padding: 0 }}
                />
              </IconButton>
            </Box>
          )}
        </Box>
        <Box className="flex w-16">
          <img src={LogoWEG} className="w-16 h-11" alt="Logo WEG" />
        </Box>
      </Box>

      {/* Box Conteudo */}
      <Box className="w-full">
        {/* Titulo */}
        <Box>
          <Input
            size="small"
            value={proposta.titulo}
            onChange={handleOnTituloChange}
            type="text"
            fullWidth
            sx={{ color: "primary.main", fontSize: FontConfig.smallTitle }}
            multiline={true}
          />
        </Box>

        {/* Box Informações gerais */}
        <Box className="relative">
          <Box className="flex absolute -right-8 -top-2">
            <Tooltip title={texts.detalhesProposta.cancelarEdicao}>
              <Box>
                <IconButton
                  sx={{ color: "primary.main" }}
                  onClick={handleOnCancelEditClick}
                >
                  <ClearIcon />
                </IconButton>
              </Box>
            </Tooltip>

            <Tooltip title={texts.detalhesProposta.salvarEdicao}>
              <Box>
                <IconButton
                  sx={{ color: "primary.main" }}
                  onClick={handleOnSaveEditClick}
                >
                  <CheckIcon />
                </IconButton>
              </Box>
            </Tooltip>
          </Box>
          {/* Solicitante */}
          <Box className="flex mt-4">
            <Typography fontSize={FontConfig.medium} fontWeight="bold">
              {texts.detalhesProposta.solicitante}:&nbsp;
            </Typography>
            <Typography fontSize={FontConfig.medium}>
              {proposta.solicitante.nome} -{" "}
              {proposta.solicitante.departamento.nome}
            </Typography>
          </Box>

          {/* Bu solicitante */}
          <Box className="flex mt-4 gap-2">
            <Typography fontSize={FontConfig.medium} fontWeight="bold">
              {texts.detalhesProposta.buSolicitante}:&nbsp;
            </Typography>
            {/* Select de BU solicitante */}
            <Select
              value={proposta.buSolicitante.idBu}
              onChange={handleOnBuSolicitanteSelect}
              variant="standard"
              size="small"
            >
              {listaBus.length > 1 ? (
                listaBus.map((bu, index) => (
                  <MenuItem key={index} value={bu.idBu}>
                    {bu.siglaBu} - {bu.nomeBu}
                  </MenuItem>
                ))
              ) : (
                <MenuItem selected>
                  {texts.demandaGerenciaModoVisualizacao.nadaEncontrado}
                </MenuItem>
              )}
            </Select>
          </Box>

          {/* Gerente */}
          <Box className="flex mt-4">
            <Typography fontSize={FontConfig.medium} fontWeight="bold">
              {texts.detalhesProposta.gerente}:&nbsp;
            </Typography>
            <Typography fontSize={FontConfig.medium}>
              {proposta.gerente.nome} - {proposta.gerente.departamento.nome}
            </Typography>
          </Box>

          {/* Fórum e Tamanho*/}
          <Box className="flex w-full justify-between mt-4">
            {/* Fórum */}
            <Box className="flex flex-row w-3/4 gap-2">
              <Typography fontSize={FontConfig.medium} fontWeight="bold">
                {texts.detalhesProposta.forum}:&nbsp;
              </Typography>

              {/* Select de Fórum */}
              <Select
                value={proposta.forum.idForum}
                onChange={handleOnForumSelect}
                variant="standard"
                size="small"
                sx={{ maxWidth: "25rem" }}
              >
                {listaForuns.length > 1 ? (
                  listaForuns.map((forum, index) => (
                    <MenuItem key={index} value={forum.idForum}>
                      {forum.siglaForum} - {forum.nomeForum}
                    </MenuItem>
                  ))
                ) : (
                  <MenuItem selected>
                    {texts.demandaGerenciaModoVisualizacao.nadaEncontrado}
                  </MenuItem>
                )}
              </Select>
            </Box>
            {/* Tamanho */}
            <Box className="flex flex-row gap-2">
              <Typography fontSize={FontConfig.medium} fontWeight="bold">
                {texts.detalhesProposta.tamanho}:&nbsp;
              </Typography>

              {/* Select de tamanho */}
              <Select
                value={proposta.tamanho}
                onChange={handleOnTamanhoSelect}
                variant="standard"
                size="small"
              >
                <MenuItem key={"Muito Pequeno"} value={"Muito Pequeno"}>
                  {texts.modalAceitarDemanda.muitoPequeno}
                </MenuItem>
                <MenuItem key={"Pequeno"} value={"Pequeno"}>
                  {texts.modalAceitarDemanda.pequeno}
                </MenuItem>
                <MenuItem key={"Médio"} value={"Médio"}>
                  {texts.modalAceitarDemanda.medio}
                </MenuItem>
                <MenuItem key={"Grande"} value={"Grande"}>
                  {texts.modalAceitarDemanda.grande}
                </MenuItem>
                <MenuItem key={"Muito Grande"} value={"Muito Grande"}>
                  {texts.modalAceitarDemanda.muitoGrande}
                </MenuItem>
              </Select>
            </Box>
          </Box>

          {/* Secao TI */}
          <Box className="flex mt-4 gap-2">
            <Typography fontSize={FontConfig.medium} fontWeight="bold">
              {texts.detalhesProposta.secaoTi}:&nbsp;
            </Typography>

            {/* Select de Seção TI */}
            <Select
              value={proposta.secaoTI.idSecao}
              onChange={handleOnSecaoTISelect}
              variant="standard"
              size="small"
            >
              {listaSecoesTI.length > 1 ? (
                listaSecoesTI.map((secaoTI, index) => (
                  <MenuItem key={index} value={secaoTI.idSecao}>
                    {secaoTI.siglaSecao} - {secaoTI.nomeSecao}
                  </MenuItem>
                ))
              ) : (
                <MenuItem selected>
                  {texts.demandaGerenciaModoVisualizacao.nadaEncontrado}
                </MenuItem>
              )}
            </Select>
          </Box>

          {/* Proposta / Objetivo */}
          <Box className="flex flex-col gap-2 mt-4">
            <Typography fontSize={FontConfig.medium} fontWeight="bold">
              {texts.detalhesProposta.proposta}:&nbsp;
            </Typography>
            <Box className="mx-4">
              <ReactQuill
                value={proposta.proposta}
                onChange={handleOnPropostaChange}
                modules={modulesQuill}
              />
            </Box>
          </Box>

          {/* Problema / Situação atual */}
          <Box className="flex flex-col gap-2 mt-4">
            <Typography fontSize={FontConfig.medium} fontWeight="bold">
              {texts.detalhesProposta.problema}:&nbsp;
            </Typography>
            <Box className="mx-4">
              <ReactQuill
                value={proposta.problema}
                onChange={handleOnProblemaChange}
                modules={modulesQuill}
              />
            </Box>
          </Box>

          {/* Escopo da proposta */}
          <Box className="mt-4">
            <Typography fontSize={FontConfig.medium} fontWeight="bold">
              {texts.detalhesProposta.escopoDaProposta}:&nbsp;
            </Typography>
            <Box className="mx-4">
              <ReactQuill
                value={proposta.escopo}
                onChange={handleOnEscopoChange}
                modules={modulesQuill}
              />
            </Box>
          </Box>

          {/* Frequência */}
          <Box className="flex flex-col mt-4">
            <Typography fontSize={FontConfig.medium} fontWeight="bold">
              {texts.detalhesProposta.frequencia}:&nbsp;
            </Typography>
            <Box className="mx-4">
              <Input
                size="small"
                value={proposta.frequencia}
                onChange={handleOnFrequenciaChange}
                type="text"
                fullWidth
                sx={{ fontSize: FontConfig.medium }}
                multiline={true}
              />
            </Box>
          </Box>

          {/* Tabela de custos */}
          <Box className="mt-4">
            <Typography fontSize={FontConfig.medium} fontWeight="bold">
              {texts.detalhesProposta.tabelaDeCustos}:&nbsp;
            </Typography>
            <Box className="mx-4">
              {proposta.tabelaCustos?.map((tabela, index) => {
                return <TabelaCustos key={index} dados={tabela} />;
              })}
            </Box>
          </Box>

          {/* Benefícios */}
          <Box className="mt-4">
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

          {/* BUs beneficiadas */}
          <Box className="mt-4">
            <Typography fontSize={FontConfig.medium} fontWeight="bold">
              {texts.detalhesProposta.busBeneficiadas}:&nbsp;
            </Typography>
            <Box className="mx-8">
              {proposta.busBeneficiadas.length > 0 ? (
                <ol className="list-disc">
                  {proposta.busBeneficiadas.map((bu, index) => {
                    return (
                      <li key={index}>
                        {bu.siglaBu} - {bu.nomeBu}
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

          {/* Link do Jira */}
          <Box className="flex mt-4">
            <Typography fontSize={FontConfig.medium} fontWeight="bold">
              {texts.detalhesProposta.linkJira}:&nbsp;
            </Typography>
            <Typography fontSize={FontConfig.medium}>
              {proposta.linkJira}
            </Typography>
          </Box>

          {/* Período de execução */}
          <Box className="flex flex-row mt-4">
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
          <Box className="flex mt-4">
            <Typography fontSize={FontConfig.medium} fontWeight="bold">
              {texts.detalhesProposta.payback}:&nbsp;
            </Typography>
            <Box>
              <Typography fontSize={FontConfig.medium}>
                {proposta.paybackValor} {proposta.paybackTipo.toLowerCase()}
              </Typography>
            </Box>
          </Box>

          {/* Anexos */}
          <Box className="mt-4">
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

          {/* Responsáveis do negócio */}
          <Box className="mt-6 mb-4 text-center">
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

          {!(proposta.status == "ASSESSMENT_APROVACAO") && (
            <>
              <Divider />
              {/* Pareceres */}
              <Box className="mt-3">
                <Typography fontSize={FontConfig.big} fontWeight="bold">
                  {texts.detalhesProposta.pareceres}:&nbsp;
                </Typography>
                <Box className="mx-4">
                  {/* Parecer da Comissão */}
                  <ParecerComissao
                    proposta={proposta}
                    setProposta={setProposta}
                  />

                  {/* Parecer da Diretoria */}
                  {[
                    "ASSESSMENT_DG",
                    "DONE",
                    "ASSESSMENT_EDICAO",
                    "CANCELLED",
                  ].includes(proposta.status) && (
                    <ParecerDG proposta={proposta} setProposta={setProposta} />
                  )}
                </Box>
              </Box>
            </>
          )}
        </Box>
      </Box>
    </>
  );
};

// Mostrar a tabela de custos
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
    ccs: [{ id: 0, codigo: 0, porcentagem: 0.0 }],
  },
}) => {
  // Context para obter as configurações de fontes do sistema
  const { FontConfig } = useContext(FontContext);

  // Context para obter os textos do sistema
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
                      {cc.porcentagem}%
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

// Mostrar os custos na proposta
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
  // Context para obter as configurações de fonte do sistema
  const { FontConfig } = useContext(FontContext);

  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  // Formatando o tipo da moeda de acordo com o local do usuário
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

// Mostrar os benefícios da proposta
const Beneficio = ({ beneficio = EntitiesObjectService.beneficio() }) => {
  // Context para obter as configurações de fonte do sistema
  const { FontConfig } = useContext(FontContext);

  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  if (beneficio.id === 0) return null;

  return (
    <Paper
      className="flex justify-between items-center mt-2 border-t-4"
      sx={{ borderTopColor: "primary.main" }}
      square
    >
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

// Chamar o parecer da comissão
const ParecerComissao = ({
  proposta = propostaExample,
  setProposta = () => {},
}) => {
  if (proposta.status == "ASSESSMENT_COMISSAO")
    return (
      <ParecerComissaoInsertText
        proposta={proposta}
        setProposta={setProposta}
      />
    );
  return <ParecerComissaoOnlyRead proposta={proposta} />;
};

// Chamar o parecer da DG
const ParecerDG = ({ proposta = propostaExample, setProposta = () => {} }) => {
  if (proposta.status == "ASSESSMENT_DG")
    return (
      <ParecerDGInsertText proposta={proposta} setProposta={setProposta} />
    );
  return <ParecerDGOnlyRead proposta={proposta} />;
};

// Escrever o parecer da comissão
const ParecerComissaoInsertText = ({
  proposta = propostaExample,
  setProposta = () => {},
}) => {
  // Context para obter as configurações de fontes do sistema
  const { FontConfig } = useContext(FontContext);

  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  return (
    <Box>
      <Box className="flex">
        <Box className="flex items-center mt-4">
          <Typography fontSize={FontConfig.medium}>
            {texts.detalhesProposta.comissao} {proposta.forum.nome}:&nbsp;
          </Typography>
        </Box>
        <TextField
          select
          label={texts.detalhesProposta.parecer}
          value={proposta.parecerComissao ? proposta.parecerComissao : ""}
          onChange={(event) =>
            setProposta({ ...proposta, parecerComissao: event.target.value })
          }
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
          <MenuItem key={"Mais informações"} value={"MAIS_INFORMACOES"}>
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
      <Box className="mt-4">
        <CaixaTextoQuill
          texto={proposta.parecerInformacao ? proposta.parecerInformacao : ""}
          setTexto={(e) => setProposta({ ...proposta, parecerInformacao: e })}
        />
      </Box>
    </Box>
  );
};

// Visualizar o parecer da comissão
const ParecerComissaoOnlyRead = ({ proposta = propostaExample }) => {
  // Context para obter as configurações das fontes do sistema
  const { FontConfig } = useContext(FontContext);

  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  // Variável para armazenar as informações do parecer da comissão
  const parecerComissaoInformacoesBox = useRef(null);

  // useEffect para atualizar o texto do parecer da comissão
  useEffect(() => {
    if (parecerComissaoInformacoesBox.current) {
      parecerComissaoInformacoesBox.current.innerHTML =
        proposta.parecerInformacao
          ? proposta.parecerInformacao
          : texts.detalhesProposta.semInformacoesAdicionais;
    }
  }, []);

  // Função para formatar o parecer
  const getParecerComissaoFomartted = (parecer) => {
    return parecer
      ? parecer[0].toUpperCase() + parecer.substring(1).toLowerCase()
      : texts.detalhesProposta.semParecer;
  };

  return (
    <Box>
      <Box className="flex">
        <Box className="flex items-center mt-4">
          <Typography fontSize={FontConfig.medium}>
            {texts.detalhesProposta.comissao} {proposta.forum.nome}:&nbsp;
          </Typography>
          <Typography fontSize={FontConfig.medium} fontWeight="bold">
            {getParecerComissaoFomartted(proposta.parecerComissao)}
          </Typography>
        </Box>
      </Box>
      {/* Comporta o texto do parecer da comissão */}
      <Box
        ref={parecerComissaoInformacoesBox}
        className="mt-2 mx-4 border-l-2 px-2"
        sx={{ borderColor: "primary.main" }}
      />
    </Box>
  );
};

// Escrever o parecer da DG
const ParecerDGInsertText = ({
  proposta = propostaExample,
  setProposta = () => {},
}) => {
  // Context para obter as configurações das fontes do sistema
  const { FontConfig } = useContext(FontContext);

  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  return (
    <Box className="mt-4">
      <Box className="flex">
        <Box className="flex items-center mt-4">
          <Typography>{texts.detalhesProposta.direcaoGeral}: &nbsp;</Typography>
        </Box>
        <TextField
          select
          label={texts.detalhesProposta.parecer}
          value={proposta.parecerDG ? proposta.parecerDG : ""}
          onChange={(event) =>
            setProposta({ ...proposta, parecerDG: event.target.value })
          }
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
        </TextField>
      </Box>
      <Box className="mt-4">
        <CaixaTextoQuill
          texto={
            proposta.parecerInformacaoDG ? proposta.parecerInformacaoDG : ""
          }
          setTexto={(e) => setProposta({ ...proposta, parecerInformacaoDG: e })}
        />
      </Box>
    </Box>
  );
};

// Visualizar o parecer da DG
const ParecerDGOnlyRead = ({ proposta = propostaExample }) => {
  // Context para obter as configurações das fontes do sistema
  const { FontConfig } = useContext(FontContext);

  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  // Variável para armazenar o parecer da DG
  const parecerDGInformacoesBox = useRef(null);

  // UseEffect utilizado para armazenar o valor do parecer da dg
  useEffect(() => {
    if (parecerDGInformacoesBox.current) {
      parecerDGInformacoesBox.current.innerHTML = proposta.parecerInformacaoDG
        ? proposta.parecerInformacaoDG
        : texts.detalhesProposta.semInformacoesAdicionais;
    }
  }, []);

  // Função para formatar o parecer da DG
  const getParecerDGFomartted = (parecer) => {
    return parecer
      ? parecer[0].toUpperCase() + parecer.substring(1).toLowerCase()
      : texts.detalhesProposta.semParecer;
  };

  return (
    <Box>
      <Box className="flex">
        <Box className="flex items-center mt-4">
          <Typography fontSize={FontConfig.medium}>
            {texts.detalhesProposta.direcaoGeral}:&nbsp;
          </Typography>
          <Typography fontSize={FontConfig.medium} fontWeight="bold">
            {getParecerDGFomartted(proposta.parecerDG)}
          </Typography>
        </Box>
      </Box>
      {/* Comporta o texto do parecer da comissão */}
      <Box
        ref={parecerDGInformacoesBox}
        className="mt-2 mx-4 border-l-2 px-2"
        sx={{ borderColor: "primary.main" }}
      />
    </Box>
  );
};

export default DetalhesPropostaEditMode;
