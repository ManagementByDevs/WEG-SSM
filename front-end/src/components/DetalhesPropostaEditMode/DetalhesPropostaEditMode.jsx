import React, { useContext, useEffect, useState, useRef } from "react";

import {
  Autocomplete,
  Box,
  Checkbox,
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

import ClipLoader from "react-spinners/ClipLoader";
import ReactQuill from "react-quill";

import LogoWEG from "../../assets/logo-weg.png";

import DownloadIcon from "@mui/icons-material/Download";
import CheckIcon from "@mui/icons-material/Check";
import SyncAltIcon from "@mui/icons-material/SyncAlt";
import ClearIcon from "@mui/icons-material/Clear";
import AddCircleIcon from "@mui/icons-material/AddCircle";
import AddIcon from "@mui/icons-material/Add";
import CloseIcon from "@mui/icons-material/Close";
import CheckBoxOutlineBlankIcon from "@mui/icons-material/CheckBoxOutlineBlank";
import CheckBoxIcon from "@mui/icons-material/CheckBox";
import DeleteIcon from "@mui/icons-material/Delete";
import RemoveIcon from "@mui/icons-material/Remove";

import CaixaTextoQuill from "../CaixaTextoQuill/CaixaTextoQuill";
import Feedback from "../Feedback/Feedback";
import ModalConfirmacao from "../ModalConfirmacao/ModalConfirmacao";

import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";
import EntitiesObjectService from "../../service/entitiesObjectService";
import DateService from "../../service/dateService";
import BuService from "../../service/buService";
import ForumService from "../../service/forumService";
import SecaoTIService from "../../service/secaoTIService";
import PropostaService from "../../service/propostaService";

const propostaExample = EntitiesObjectService.proposta();

// Variável para armazenar os ícones do checkbox
const icon = <CheckBoxOutlineBlankIcon fontSize="small" />;
const checkedIcon = <CheckBoxIcon fontSize="small" />;

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

  // Feedback caso o usuário coloque um nome de anexo com mesmo nome de outro anexo
  const [feedbackComAnexoMesmoNome, setFeedbackComAnexoMesmoNome] =
    useState(false);

  // Modal de confirmação para quando o usuário clicar em cancelar ou salvar edição
  const [modalConfirmacao, setModalConfirmacao] = useState(false);

  // Texto do modal de confirmação que irá aparecer para o usuário
  const [textoModalConfirmacao, setTextoModalConfirmacao] = useState("");

  const [isBeneficiosVisible, setIsBeneficiosVisible] = useState(false);

  const [isTabelaCustosVisile, setIsTabelaCustosVisible] = useState(false);

  // Referênica para o input de arquivo
  const inputFile = useRef(null);

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

  // Função para transformar uma string em base64 para um ArrayBuffer
  const base64ToArrayBuffer = (base64) => {
    const binaryString = window.atob(base64);
    const bytes = new Uint8Array(binaryString.length);
    return bytes.map((byte, i) => binaryString.charCodeAt(i));
  };

  // Função para saber se as listas necessárias para a edição da proposta fizeram a requisição
  const isAllsListsPopulated = () => {
    return (
      listaBus.length > 1 && listaForuns.length > 1 && listaSecoesTI.length > 1
    );
  };

  // Função para verificar se o nome do anexo selecionado já existe dentro da proposta
  const existsInAnexos = (jsFiles) => {
    let exists = false;

    for (let file of jsFiles) {
      for (let anexo of proposta.anexo) {
        if (anexo.nome == file.name || anexo.name == file.name) {
          exists = true;
        }
      }
    }

    return exists;
  };

  // Função que retorna o nome do anexo formatado a ser mostrado para o usuário
  const getAnexoNome = (file) => {
    return file.nome
      ? file.nome + " - " + file.tipo
      : file.name + " - " + file.type;
  };

  // Retorna booleano indicando se o parecer da comissão deve aparecer na tela
  const isParecerGerenciaVisible = () => {
    let possibleStatus = [
      "CANCELLED",
      "TO_DO",
      "BUSINESS_CASE",
      "ASSESSMENT_DG",
      "DONE",
    ];

    if (possibleStatus.includes(proposta.status)) return true;
    return false;
  };

  // Salva as edições da proposta no banco de dados
  const saveProposal = () => {
    // Fazer verificação dos campos

    let propostaAux = { ...proposta };

    let novasTabelasCusto = propostaAux.tabelaCustos.filter((tabelaCusto) => {
      if (tabelaCusto.id < 0) {
        // Se o ID for negativo ele foi adicionado
        propostaAux.tabelaCustos.splice(
          propostaAux.tabelaCustos.find((element) => element == tabelaCusto),
          1
        );
        return tabelaCusto;
      }
    });

    let novosBeneficios = propostaAux.beneficios.filter((beneficio) => {
      if (beneficio.id < 0) {
        // Se o ID for negativo ele foi adicionado
        propostaAux.beneficios.splice(
          propostaAux.beneficios.find((element) => element == beneficio),
          1
        );
        return beneficio;
      }
    });

    let listaIdsAnexos = propostaAux.anexo.filter((anexo) => {
      if (anexo.id) {
        // Se o ID for positivo ele já existia
        propostaAux.anexo.splice(
          propostaAux.anexo.find((element) => element == anexo),
          1
        );
        return anexo.id;
      }
    });

    console.log(
      "DATA: ",
      propostaAux,
      novasTabelasCusto,
      novosBeneficios,
      listaIdsAnexos
    );
    // return ;

    PropostaService.putComNovosDados(
      propostaAux,
      proposta.id,
      novasTabelasCusto,
      novosBeneficios,
      listaIdsAnexos
    ).then((response) => {
      console.log("response", response.data);
      setPropostaData(response.data);
    });
  };

  // ***************************************** Handlers ***************************************** //

  // Handler cancelar edição
  const handleOnCancelEditClick = () => {
    setTextoModalConfirmacao("cancelarEdicao");
    setModalConfirmacao(true);
  };

  // Handler salvar edição
  const handleOnSaveEditClick = () => {
    setTextoModalConfirmacao("confirmEditar");
    setModalConfirmacao(true);
  };

  // Função disparada quando clicado em confirmar no modal de confirmação
  const handleOnConfirmClick = () => {
    if (textoModalConfirmacao == "cancelarEdicao") {
      setIsEditing(false);
    } else if (textoModalConfirmacao == "confirmEditar") {
      // setIsLoading(true);
      saveProposal();
    }

    setModalConfirmacao(false);
  };

  // Handler do códigoPPM
  const handleOnPPMChange = (event) => {
    setProposta({ ...proposta, codigoPPM: event.target.value });
  };

  // Handler da data da proposta
  const handleOnDataChange = (event) => {
    setProposta({ ...proposta, data: event.target.value });
  };

  // Handler do isPublicada da proposta
  const handleOnPublicadaClick = () => {
    setProposta({ ...proposta, publicada: !proposta.publicada });
  };

  // Handler do título da proposta
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

  // Handler para a seção de TI
  const handleOnSecaoTISelect = (event) => {
    setProposta({
      ...proposta,
      secaoTI: listaSecoesTI.find(
        (secaoTI) => secaoTI.idSecao == event.target.value
      ),
    });
  };

  // Handler da proposta da proposta
  const handleOnPropostaChange = (event) => {
    setProposta({
      ...proposta,
      proposta: event,
    });
  };

  // Handler do problema da proposta
  const handleOnProblemaChange = (event) => {
    setProposta({
      ...proposta,
      problema: event,
    });
  };

  // Handler do escopo da proposta
  const handleOnEscopoChange = (event) => {
    setProposta({
      ...proposta,
      escopo: event,
    });
  };

  // Handler da frequência da proposta
  const handleOnFrequenciaChange = (event) => {
    setProposta({
      ...proposta,
      frequencia: event.target.value,
    });
  };

  const handleOnBeneficiosAddClick = () => {
    let newBeneficio = { ...EntitiesObjectService.beneficio() };

    let ultimoEl = proposta.beneficios[proposta.beneficios.length - 1];
    // Se o último elemento for novo, o id vai ser o id dele menos 1
    if (ultimoEl.id < 0) {
      newBeneficio.id = ultimoEl.id - 1;
    } else {
      newBeneficio.id = proposta.beneficios.length * -1 - 1;
    }

    let beneficiosAux = [...proposta.beneficios, newBeneficio];

    setIsBeneficiosVisible(false);
    setProposta({
      ...proposta,
      beneficios: [...beneficiosAux],
    });
  };

  // Handler do benefício da proposta
  const handleOnBeneficioChange = (newBeneficio) => {
    let beneficiosAux = proposta.beneficios.map((beneficio) => {
      if (beneficio.id == newBeneficio.id) {
        return newBeneficio;
      }
      return beneficio;
    });

    setProposta({ ...proposta, beneficios: [...beneficiosAux] });
  };

  // Handler para deletar um benefício
  const handleDeleteBeneficio = (idBeneficio) => {
    let beneficiosAux = [...proposta.beneficios];
    beneficiosAux.splice(
      beneficiosAux.findIndex((beneficio) => beneficio.id == idBeneficio),
      1
    );

    setIsBeneficiosVisible(false);
    setProposta({ ...proposta, beneficios: [...beneficiosAux] });
  };

  // Handler do link do jira da proposta
  const handleOnLinkJiraChange = (event) => {
    setProposta({
      ...proposta,
      linkJira: event.target.value,
    });
  };

  // Handler do início de execução da proposta
  const handleOnInicioExecucaoChange = (event) => {
    setProposta({
      ...proposta,
      inicioExecucao: event.target.value,
    });
  };

  // Handler do fim de execução da proposta
  const handleOnFimExecucaoChange = (event) => {
    setProposta({
      ...proposta,
      fimExecucao: event.target.value,
    });
  };

  // Handler do payback valor da proposta
  const handleOnPaybackValorChange = (event) => {
    setProposta({
      ...proposta,
      paybackValor: event.target.value,
    });
  };

  // Handler do payback tipo da proposta
  const handlePaybackTIpoSelect = (event) => {
    setProposta({
      ...proposta,
      paybackTipo: event.target.value,
    });
  };

  const handleOnBuBeneficiadaSelect = (event, newValue) => {
    setProposta({
      ...proposta,
      busBeneficiadas: newValue,
    });
  };

  // Handler para quando clicar no ícone de add anexo
  const handleAnexosAddOnClick = () => {
    inputFile.current.click();
  };

  // Handler para quando for selecionado um ou mais arquivos
  const handleOnFilesSelect = () => {
    if (!existsInAnexos(inputFile.current.files)) {
      setProposta({
        ...proposta,
        anexo: [...proposta.anexo, ...inputFile.current.files],
      });
      inputFile.current.value = "";
    } else {
      // Feedback de anexo já existente
      setFeedbackComAnexoMesmoNome(true);
    }
  };

  // Handler para baixar um anexo
  const handleOnDownloadAnexo = (anexo) => {
    const file = anexo;
    let blob;
    let fileName;

    if (anexo instanceof File) {
      blob = file;
      fileName = file.name;
    } else {
      blob = new Blob([base64ToArrayBuffer(file.dados)]);
      fileName = `${file.nome}`;
    }

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

  // Handler para deletar um anexo
  const handleOnDeleteAnexo = (file) => {
    let anexosAux = [...proposta.anexo];
    anexosAux.splice(
      anexosAux.findIndex((oldFile) => oldFile == file),
      1
    );
    
    setProposta({ ...proposta, anexo: [...anexosAux] });
  };

  // Handler para quando a tabela de custos for alterada
  const handleOnTabelaCustosChange = (
    newTabelaCustos = EntitiesObjectService.tabelaCustos()
  ) => {
    let tabelaCustosAux = proposta.tabelaCustos.map((tabelaCusto) => {
      if (tabelaCusto.id == newTabelaCustos.id) {
        return newTabelaCustos;
      }
      return tabelaCusto;
    });

    setProposta({ ...proposta, tabelaCustos: [...tabelaCustosAux] });
  };

  const handleDeleteTabelaCusto = (idTabela) => {
    let tabelaCustosAux = proposta.tabelaCustos;
    tabelaCustosAux.splice(
      tabelaCustosAux.findIndex((tabelaCusto) => tabelaCusto.id == idTabela),
      1
    );

    setIsTabelaCustosVisible(false);
    setProposta({ ...proposta, tabelaCustos: tabelaCustosAux });
  };

  // Handler para quando clicar no botão de adicionar criar uma nova tabela de custos
  const handleOnTabelaCustosAddClick = () => {
    let newTabelaCustos = EntitiesObjectService.tabelaCustos();

    let ultimoEl = proposta.tabelaCustos[proposta.tabelaCustos.length - 1];
    // Se o último elemento for novo, o id vai ser o id dele menos 1
    if (ultimoEl.id < 0) {
      newTabelaCustos.id = ultimoEl.id - 1;
    } else {
      newTabelaCustos.id = proposta.tabelaCustos.length * -1 - 1;
    }

    setIsTabelaCustosVisible(false);
    setProposta({
      ...proposta,
      tabelaCustos: [...proposta.tabelaCustos, { ...newTabelaCustos }],
    });
  };

  // ***************************************** Fim Handlers ***************************************** //

  // ***************************************** UseEffects ***************************************** //

  // Carregamento enquanto as requisições são feitas
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

    let beneficiosAux = proposta.beneficios.map((beneficio) => {
      if (beneficio.tipoBeneficio == "QUALITATIVO") {
        beneficio.moeda = "";
        beneficio.valor_mensal = "";
      }
      return beneficio;
    });

    setProposta({ ...proposta, beneficios: [...beneficiosAux] });
  }, []);

  useEffect(() => {
    console.log("Update proposta: ", proposta);
  }, [proposta]);

  useEffect(() => {
    if (!isBeneficiosVisible) {
      setIsBeneficiosVisible(true);
    }

    if (!isTabelaCustosVisile) {
      setIsTabelaCustosVisible(true);
    }
  }, [isBeneficiosVisible, isTabelaCustosVisile]);

  // ***************************************** Fim UseEffects ***************************************** //

  if (isLoading)
    return (
      <Box className="flex justify-center">
        <ClipLoader className="mt-2 border-t-6" color="#00579D" size={110} />
      </Box>
    );

  return (
    <>
      <ModalConfirmacao
        open={modalConfirmacao}
        setOpen={setModalConfirmacao}
        textoModal={textoModalConfirmacao}
        textoBotao={"sim"}
        onConfirmClick={handleOnConfirmClick}
        onCancelClick={() => {}}
      />
      <Feedback
        open={feedbackComAnexoMesmoNome}
        handleClose={() => setFeedbackComAnexoMesmoNome(false)}
        status={"erro"}
        mensagem={texts.DetalhesDemanda.jaHaUmAnexoComEsseNome}
      />
      {/* Box header */}
      <Box className="w-full flex justify-between">
        <Box className="flex gap-4">
          <Box className="flex gap-2">
            {/* Código PPM */}
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
            {/* Data */}
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

          {/* Publicada */}
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
              <IconButton
                sx={{ color: "primary.main" }}
                onClick={handleOnCancelEditClick}
              >
                <ClearIcon />
              </IconButton>
            </Tooltip>

            <Tooltip title={texts.detalhesProposta.salvarEdicao}>
              <IconButton
                sx={{ color: "primary.main" }}
                onClick={handleOnSaveEditClick}
              >
                <CheckIcon />
              </IconButton>
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
            <Box className="flex items-center">
              <Typography fontSize={FontConfig.medium} fontWeight="bold">
                {texts.detalhesProposta.tabelaDeCustos}:&nbsp;
              </Typography>
              <Tooltip title={texts.formularioBeneficiosDemanda.adicionar}>
                <IconButton onClick={handleOnTabelaCustosAddClick}>
                  <AddCircleIcon color="primary" />
                </IconButton>
              </Tooltip>
            </Box>
            <Box className="mx-4">
              {proposta.tabelaCustos.length > 0 && isTabelaCustosVisile
                ? proposta.tabelaCustos?.map((tabela, index) => {
                    return (
                      <TabelaCustos
                        key={index}
                        dados={tabela}
                        handleOnTabelaCustosChange={handleOnTabelaCustosChange}
                        handleDeleteTabelaCusto={handleDeleteTabelaCusto}
                      />
                    );
                  })
                : null}
            </Box>
          </Box>

          {/* Benefícios */}
          <Box className="mt-4">
            <Box className="flex items-center">
              <Typography fontSize={FontConfig.medium} fontWeight="bold">
                {texts.detalhesProposta.beneficios}:&nbsp;
              </Typography>
              <Tooltip title={texts.formularioBeneficiosDemanda.adicionar}>
                <IconButton onClick={handleOnBeneficiosAddClick}>
                  <AddCircleIcon color="primary" />
                </IconButton>
              </Tooltip>
            </Box>
            <Box className="mx-4">
              {proposta.beneficios.length > 0 && isBeneficiosVisible ? (
                proposta.beneficios.map((beneficio, index) => {
                  return (
                    <Beneficio
                      key={index}
                      beneficio={beneficio}
                      handleOnBeneficioChange={handleOnBeneficioChange}
                      handleDeleteBeneficio={handleDeleteBeneficio}
                    />
                  );
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
            <Box className="flex items-center gap-4">
              <Typography
                className="whitespace-nowrap"
                fontSize={FontConfig.medium}
                fontWeight="bold"
              >
                {texts.detalhesProposta.busBeneficiadas}:&nbsp;
              </Typography>
              <Box className="w-full">
                {/* Select de BUs beneficiadas */}
                <Autocomplete
                  multiple
                  options={listaBus}
                  disableCloseOnSelect
                  onChange={handleOnBuBeneficiadaSelect}
                  getOptionLabel={(option) => option.siglaBu}
                  renderOption={(props, option, { selected }) => (
                    <li {...props} title={option.nomeBu}>
                      <Checkbox
                        icon={icon}
                        checkedIcon={checkedIcon}
                        style={{ marginRight: 8 }}
                        checked={selected}
                      />
                      {option.siglaBu}
                    </li>
                  )}
                  fullWidth
                  noOptionsText={texts.modalAceitarDemanda.nenhumaBuEncontrada}
                  renderInput={(params) => (
                    <TextField
                      {...params}
                      variant="standard"
                      placeholder={
                        texts.modalAceitarDemanda.selecioneUmaOuMaisBus
                      }
                    />
                  )}
                />
              </Box>
            </Box>
            <Box className="mx-8 mt-4">
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
          <Box className="mt-4">
            <Typography fontSize={FontConfig.medium} fontWeight="bold">
              {texts.detalhesProposta.linkJira}:&nbsp;
            </Typography>
            <Box className="mx-4">
              <Input
                size="small"
                value={proposta.linkJira}
                onChange={handleOnLinkJiraChange}
                type="text"
                fullWidth
                sx={{ fontSize: FontConfig.medium }}
                multiline={true}
              />
            </Box>
          </Box>

          {/* Período de execução */}
          <Box className="flex flex-row mt-4">
            <Typography fontSize={FontConfig.medium} fontWeight="bold">
              {texts.detalhesProposta.periodoDeExecucao}:&nbsp;
            </Typography>
            <Box className="flex gap-2 mx-4">
              <TextField
                variant="standard"
                size="small"
                value={DateService.getTodaysDateUSFormat(
                  proposta.inicioExecucao
                )}
                onChange={handleOnInicioExecucaoChange}
                type="date"
              />
              {texts.detalhesProposta.ate}{" "}
              <TextField
                variant="standard"
                size="small"
                value={DateService.getTodaysDateUSFormat(proposta.fimExecucao)}
                onChange={handleOnFimExecucaoChange}
                type="date"
              />
            </Box>
          </Box>

          {/* Payback */}
          <Box className="flex gap-2 mt-4">
            <Typography fontSize={FontConfig.medium} fontWeight="bold">
              {texts.detalhesProposta.payback}:&nbsp;
            </Typography>

            {/* Payback Valor */}
            <TextField
              size="small"
              variant="standard"
              value={proposta.paybackValor}
              onChange={handleOnPaybackValorChange}
              sx={{ fontSize: FontConfig.medium, width: "3rem" }}
            />

            {/* Select de payback tipo */}
            <Select
              value={proposta.paybackTipo}
              onChange={handlePaybackTIpoSelect}
              variant="standard"
              size="small"
            >
              <MenuItem value={"DIAS"}>
                {texts.formularioGeralProposta.dias}
              </MenuItem>
              <MenuItem value={"SEMANAS"}>
                {texts.formularioGeralProposta.semanas}
              </MenuItem>
              <MenuItem value={"MESES"}>
                {texts.formularioGeralProposta.meses}
              </MenuItem>
            </Select>
          </Box>

          {/* Anexos */}
          <Box className="mt-4">
            <Box className="flex items-center">
              <Typography fontSize={FontConfig.medium} fontWeight="bold">
                {texts.detalhesProposta.anexos}:&nbsp;
              </Typography>
              <Tooltip title={texts.formularioGeralProposta.adicionarNovoAnexo}>
                <IconButton onClick={handleAnexosAddOnClick}>
                  <AddCircleIcon color="primary" />
                </IconButton>
              </Tooltip>
              <input
                onChange={handleOnFilesSelect}
                ref={inputFile}
                type="file"
                multiple
                hidden
              />
            </Box>
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
                        {getAnexoNome(anexo)}
                      </Typography>
                      <Box className="flex gap-2">
                        <Tooltip title={texts.detalhesProposta.download}>
                          <IconButton
                            onClick={() => handleOnDownloadAnexo(anexo)}
                          >
                            <DownloadIcon />
                          </IconButton>
                        </Tooltip>
                        <Tooltip title={texts.formularioAnexosDemanda.remover}>
                          <IconButton
                            onClick={() => handleOnDeleteAnexo(anexo)}
                          >
                            <CloseIcon />
                          </IconButton>
                        </Tooltip>
                      </Box>
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

          <Divider />
          {/* Pareceres */}
          <Box className="mt-3">
            <Typography fontSize={FontConfig.big} fontWeight="bold">
              {texts.detalhesProposta.pareceres}:&nbsp;
            </Typography>
            <Box className="mx-4">
              {/* Parecer da Comissão */}
              <ParecerComissaoInsertText
                proposta={proposta}
                setProposta={setProposta}
              />

              {/* Parecer da Diretoria */}
              {isParecerGerenciaVisible() && (
                <ParecerDGInsertText
                  proposta={proposta}
                  setProposta={setProposta}
                />
              )}
            </Box>
          </Box>
        </Box>
      </Box>
    </>
  );
};

// Mostrar a tabela de custos
const TabelaCustos = ({
  dados = EntitiesObjectService.tabelaCustos(),
  handleOnTabelaCustosChange = (
    newTabela = EntitiesObjectService.tabelaCustos()
  ) => {},
  handleDeleteTabelaCusto = () => {},
}) => {
  // Context para obter as configurações de fontes do sistema
  const { FontConfig } = useContext(FontContext);

  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  // ***************************************** Handlers ***************************************** //

  // Handler para quando for alterado algum custo
  const handleOnCustoChange = (newCusto = EntitiesObjectService.custo()) => {
    let custosAux = dados.custos.map((custo) => {
      if (custo.id == newCusto.id) {
        return newCusto;
      }
      return custo;
    });

    handleOnTabelaCustosChange({ ...dados, custos: [...custosAux] });
  };

  // Handler para quando for deletado uma linha de custo
  const handleOnDeleteCustoClick = () => {
    let custosAux = [...dados.custos];
    custosAux.pop();
    handleOnTabelaCustosChange({ ...dados, custos: [...custosAux] });
  };

  // Handler para quando for deletado uma linha de cc
  const handleOnDeleteCCClick = () => {
    let ccsAux = [...dados.ccs];
    ccsAux.pop();
    handleOnTabelaCustosChange({ ...dados, ccs: [...ccsAux] });
  };

  // Handler para quando for alterado algum cc
  const handleOnCCChange = (newCC = EntitiesObjectService.centroDeCusto()) => {
    let ccsAux = dados.ccs.map((custo) => {
      if (custo.id == newCC.id) {
        return newCC;
      }
      return custo;
    });

    handleOnTabelaCustosChange({ ...dados, ccs: [...ccsAux] });
  };

  // Handler para quando for deletado uma tabela inteira
  const handleOnDeleteTabelaClick = () => {
    handleDeleteTabelaCusto(dados.id);
  };

  // Handler para quando for clicado no botão de adicionar custo
  const handleOnAddCustoClick = () => {
    let newCusto = EntitiesObjectService.custo();

    let ultimoEl = dados.custos[dados.custos.length - 1];
    // Se o último elemento for novo, o id vai ser o id dele menos 1
    if (ultimoEl.id < 0) {
      newCusto.id = ultimoEl.id - 1;
    } else {
      newCusto.id = dados.custos.length * -1 - 1;
    }

    handleOnTabelaCustosChange({
      ...dados,
      custos: [...dados.custos, { ...newCusto }],
    });
  };

  // Função para quando for clicado no botão de adicionar CC
  const handleOnAddCCClick = () => {
    let newCC = EntitiesObjectService.cc();

    let ultimoEl = dados.ccs[dados.ccs.length - 1];
    // Se o último elemento for novo, o id vai ser o id dele menos 1
    if (ultimoEl.id < 0) {
      newCC.id = ultimoEl.id - 1;
    } else {
      newCC.id = dados.ccs.length * -1 - 1;
    }

    handleOnTabelaCustosChange({
      ...dados,
      ccs: [...dados.ccs, { ...newCC }],
    });
  };

  // ***************************************** Fim Handlers ***************************************** //

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
            return (
              <CustosRow
                key={index}
                custo={custo}
                handleOnCustoChange={handleOnCustoChange}
              />
            );
          })}
        </TableBody>
      </Table>
      <Divider />
      <Box className="w-full flex justify-end px-2">
        <Tooltip title={texts.linhaTabelaCCs.titleExcluirLinha}>
          <IconButton
            onClick={handleOnDeleteCustoClick}
            size="small"
            color="primary"
          >
            <RemoveIcon />
          </IconButton>
        </Tooltip>
        <Tooltip title={texts.formularioBeneficiosDemanda.adicionar}>
          <IconButton
            onClick={handleOnAddCustoClick}
            size="small"
            color="primary"
          >
            <AddIcon />
          </IconButton>
        </Tooltip>
      </Box>
      <Table className="table-fixed w-full">
        <TableHead>
          <TableRow sx={{ backgroundColor: "primary.main" }}>
            <th className="text-white p-1">
              <Typography fontSize={FontConfig.medium}>
                {texts.detalhesProposta.ccs}
              </Typography>
            </th>
            <th className="text-white p-1 w-1/4">
              <Typography fontSize={FontConfig.medium}>
                {texts.detalhesProposta.porcentagem}
              </Typography>
            </th>
          </TableRow>
        </TableHead>
        <TableBody>
          {dados.ccs.map((cc, index) => {
            return (
              <CC key={index} cc={cc} handleOnCCChange={handleOnCCChange} />
            );
          })}
        </TableBody>
      </Table>
      <Box className="w-full flex justify-end px-2">
        <Tooltip title={texts.formularioAnexosDemanda.remover}>
          <IconButton
            onClick={handleOnDeleteTabelaClick}
            size="small"
            color="primary"
          >
            <DeleteIcon />
          </IconButton>
        </Tooltip>
        <Tooltip title={texts.linhaTabelaCCs.titleExcluirLinha}>
          <IconButton
            onClick={handleOnDeleteCCClick}
            size="small"
            color="primary"
          >
            <RemoveIcon />
          </IconButton>
        </Tooltip>
        <Tooltip title={texts.formularioBeneficiosDemanda.adicionar}>
          <IconButton onClick={handleOnAddCCClick} size="small" color="primary">
            <AddIcon />
          </IconButton>
        </Tooltip>
      </Box>
    </Paper>
  );
};

const CC = ({
  cc = EntitiesObjectService.cc(),
  handleOnCCChange = (newCC = EntitiesObjectService.cc()) => {},
}) => {
  // Context para obter as configurações de fonte do sistema
  const { FontConfig } = useContext(FontContext);

  // ***************************************** Handlers ***************************************** //

  const handleOnCodigoChange = (event) => {
    handleOnCCChange({ ...cc, codigo: event.target.value });
  };

  const handleOnPorcentagemChange = (event) => {
    handleOnCCChange({ ...cc, porcentagem: event.target.value });
  };

  // ***************************************** Fim Handlers ***************************************** //

  return (
    <TableRow className="w-full border rounded">
      <td className="text-center p-1">
        <Input
          value={cc.codigo}
          onChange={handleOnCodigoChange}
          size="small"
          type="text"
          multiline
          fullWidth
          sx={{ fontConfig: FontConfig.default }}
        />
      </td>
      <td className="text-center p-1">
        <Input
          value={cc.porcentagem}
          onChange={handleOnPorcentagemChange}
          size="small"
          type="text"
          multiline
          fullWidth
          sx={{ fontConfig: FontConfig.default }}
        />
      </td>
    </TableRow>
  );
};

// Mostrar os custos na proposta
const CustosRow = ({
  custo = EntitiesObjectService.custo(),
  handleOnCustoChange = (newCusto = EntitiesObjectService.custo()) => {},
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

  // ***************************************** Handlers ***************************************** //

  // Handler para quando o tipo de despesa for alterado
  const handleOnTipoDespesaChange = (event) => {
    handleOnCustoChange({ ...custo, tipoDespesa: event.target.value });
  };

  // Handler para quando o tipo de despesa for alterado
  const handleOnPerfilDespesaChange = (event) => {
    handleOnCustoChange({ ...custo, perfilDespesa: event.target.value });
  };

  // Handler para quando o tipo de despesa for alterado
  const handleOnPeriodoExecucaoChange = (event) => {
    handleOnCustoChange({ ...custo, periodoExecucao: event.target.value });
  };

  // Handler para quando o tipo de despesa for alterado
  const handleOnHorasChange = (event) => {
    handleOnCustoChange({ ...custo, horas: event.target.value });
  };

  // Handler para quando o tipo de despesa for alterado
  const handleOnValorHoraChange = (event) => {
    handleOnCustoChange({ ...custo, valorHora: event.target.value });
  };

  // ***************************************** Fim Handlers ***************************************** //

  return (
    <TableRow>
      <td className="p-2 text-center">
        {/* Tipo de Despesa */}
        <Input
          value={custo.tipoDespesa}
          onChange={handleOnTipoDespesaChange}
          fullWidth
          size="small"
          type="text"
          multiline={true}
          sx={{ fontConfig: FontConfig.default }}
        />
      </td>
      <td className="p-2 text-center">
        {/* Perfil da Despesa */}
        <Input
          value={custo.perfilDespesa}
          onChange={handleOnPerfilDespesaChange}
          fullWidth
          size="small"
          type="text"
          multiline={true}
          sx={{ fontConfig: FontConfig.default }}
        />
      </td>
      <td className="p-2 text-center">
        {/* Período de Execução */}
        <Input
          value={custo.periodoExecucao}
          onChange={handleOnPeriodoExecucaoChange}
          fullWidth
          size="small"
          type="text"
          multiline={true}
          sx={{ fontConfig: FontConfig.default }}
        />
      </td>
      <td className="p-2 text-center">
        {/* Horas */}
        <Input
          value={custo.horas}
          onChange={handleOnHorasChange}
          fullWidth
          size="small"
          type="text"
          multiline={true}
          sx={{ fontConfig: FontConfig.default }}
        />
      </td>
      <td className="p-2 text-center">
        {/* Valor da Hora */}
        <Input
          value={custo.valorHora}
          onChange={handleOnValorHoraChange}
          fullWidth
          size="small"
          type="text"
          multiline={true}
          sx={{ fontConfig: FontConfig.default }}
        />
      </td>
      <td className="p-2 text-center">
        {/* Total */}
        <Typography fontSize={FontConfig.default}>
          {getValorFormatted(custo.horas * custo.valorHora)}
        </Typography>
      </td>
    </TableRow>
  );
};

// Mostrar os benefícios da proposta
const Beneficio = ({
  beneficio = EntitiesObjectService.beneficio(),
  handleOnBeneficioChange = () => {},
  handleDeleteBeneficio = () => {},
}) => {
  // Context para obter as configurações de fonte do sistema
  const { FontConfig } = useContext(FontContext);

  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  // Estado se é um beneficio com tipo qualitativo
  const [isQualitativo, setIsQualitativo] = useState(false);

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

  // Verifica se o benefício é do tipo qualitativo
  const verifyIsQualitativo = () => {
    if (beneficio.tipoBeneficio == "QUALITATIVO") {
      setIsQualitativo(true);
    } else {
      setIsQualitativo(false);
    }
  };

  // ***************************************** Handlers ***************************************** //

  // Handler do tipo de benefício
  const handleOnTipoBeneficioSelect = (event) => {
    let beneficioAux = { ...beneficio, tipoBeneficio: event.target.value };

    if (event.target.value == "QUALITATIVO") {
      beneficioAux.moeda = "";
      beneficioAux.valor_mensal = "";
    }

    handleOnBeneficioChange({
      ...beneficioAux,
    });
  };

  // Handler do valor mensal do benefício
  const handleOnValorMensalChange = (event) => {
    handleOnBeneficioChange({ ...beneficio, valor_mensal: event.target.value });
  };

  // Handler da moeda do benefício
  const handleOnMoedaChange = (event) => {
    handleOnBeneficioChange({ ...beneficio, moeda: event.target.value });
  };

  // Handler da memória calculo do benefício
  const handleOnMemoriaCalculoChange = (event) => {
    handleOnBeneficioChange({
      ...beneficio,
      memoriaCalculo: event,
    });
  };

  const handleOnDeleteBeneficio = () => {
    handleDeleteBeneficio(beneficio.id);
  };

  // ***************************************** Fim Handlers ***************************************** //

  // ***************************************** UseEffects ***************************************** //

  useEffect(() => {
    verifyIsQualitativo();
  }, []);

  useEffect(() => {
    verifyIsQualitativo();
  }, [beneficio]);

  // ***************************************** Fim UseEffects ***************************************** //

  if (beneficio.id === 0) return null;

  return (
    <Paper
      className="relative flex justify-between mt-2 border-t-4"
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
            {!isQualitativo && (
              <>
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
              </>
            )}
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
            <td className="text-center p-2">
              {/* Select de tipo benefício */}
              <Select
                value={beneficio.tipoBeneficio}
                onChange={handleOnTipoBeneficioSelect}
                variant="standard"
                size="small"
              >
                <MenuItem value={"REAL"}>
                  <Typography fontSize={FontConfig.medium}>
                    {texts.beneficios.real}
                  </Typography>
                </MenuItem>
                <MenuItem value={"POTENCIAL"}>
                  <Typography fontSize={FontConfig.medium}>
                    {texts.beneficios.potencial}
                  </Typography>
                </MenuItem>
                <MenuItem value={"QUALITATIVO"}>
                  <Typography fontSize={FontConfig.medium}>
                    {texts.beneficios.qualitativo}
                  </Typography>
                </MenuItem>
              </Select>
            </td>
            {!isQualitativo && (
              <>
                <td className="text-center p-2">
                  {/* Input de valor mensal */}
                  <Input
                    size="small"
                    value={beneficio.valor_mensal}
                    onChange={handleOnValorMensalChange}
                    type="text"
                    fullWidth
                    sx={{ fontSize: FontConfig.default }}
                    multiline={true}
                  />
                </td>
                <td className="text-center p-2">
                  {/* Select da moeda do benefício */}
                  <Select
                    value={beneficio.moeda}
                    onChange={handleOnMoedaChange}
                    variant="standard"
                    size="small"
                  >
                    <MenuItem value={"Real"}>
                      <Typography fontSize={FontConfig.medium}>BRL</Typography>
                    </MenuItem>
                    <MenuItem value={"Dolar"}>
                      <Typography fontSize={FontConfig.medium}>USD</Typography>
                    </MenuItem>
                    <MenuItem value={"Euro"}>
                      <Typography fontSize={FontConfig.medium}>EUR</Typography>
                    </MenuItem>
                  </Select>
                </td>
              </>
            )}
            <td className="text-center p-2">
              {/* Caixa de texto para memória de cálculo */}
              <ReactQuill
                value={beneficio.memoriaCalculo}
                onChange={handleOnMemoriaCalculoChange}
                modules={modulesQuill}
              />
            </td>
          </TableRow>
        </TableBody>
      </Table>
      <Box className="absolute right-0">
        <Tooltip title={texts.formularioAnexosDemanda.remover}>
          <IconButton
            onClick={handleOnDeleteBeneficio}
            size="small"
            color="primary"
          >
            <DeleteIcon />
          </IconButton>
        </Tooltip>
      </Box>
    </Paper>
  );
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
