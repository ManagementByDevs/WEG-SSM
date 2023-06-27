import React, { useContext, useEffect, useState, useRef } from "react";
import {
  Autocomplete,
  Box,
  Checkbox,
  Divider,
  IconButton,
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

import * as _ from "lodash";
import ClipLoader from "react-spinners/ClipLoader";

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

import Feedback from "../Feedback/Feedback";
import ModalConfirmacao from "../Modais/Modal-confirmacao/ModalConfirmacao";
import QuillCustom from "./Inputs/QuillCustom";

import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";
import EntitiesObjectService from "../../service/entitiesObjectService";
import DateService from "../../service/dateService";
import BuService from "../../service/buService";
import ForumService from "../../service/forumService";
import SecaoTIService from "../../service/secaoTIService";
import PropostaService from "../../service/propostaService";
import ExportPdfService from "../../service/exportPdfService";
import CookieService from "../../service/cookieService";
import InputCustom from "./Inputs/InputCustom";
import SpeechSynthesisContext from "../../service/SpeechSynthesisContext";
import { SpeechRecognitionContext } from "../../service/SpeechRecognitionService";

const propostaExample = EntitiesObjectService.proposta();

// Variável para armazenar os ícones do checkbox
const icon = <CheckBoxOutlineBlankIcon fontSize="small" />;
const checkedIcon = <CheckBoxIcon fontSize="small" />;

const DetalhesPropostaEditMode = ({
  propostaData = propostaExample,
  setPropostaData = () => { },
  setIsEditing = () => { },
  emAprovacao = false,
}) => {
  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  // Context para obter a função de leitura de texto
  const { localClique, palavrasJuntas } = useContext(SpeechRecognitionContext);

  // Context para ler o texto da tela
  const { lerTexto } = useContext(SpeechSynthesisContext);

  // Estado da proposta editável
  const [proposta, setProposta] = useState(
    JSON.parse(JSON.stringify(propostaData))
  );

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

  // State para atualizar os benefícios na tela
  const [isBeneficiosVisible, setIsBeneficiosVisible] = useState(false);

  // State para atualizar as tabelas de custo na tela
  const [isTabelaCustosVisile, setIsTabelaCustosVisible] = useState(false);

  // Visibilidade do feedback de dados inválidos
  const [feedbackDadosInvalidos, setFeedbackDadosInvalidos] = useState(false);

  // State para ter o texto de feedback de dados inválidos
  const [textoDadosInvalidos, setTextoDadosInvalidos] = useState("");

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

  /** Padrão de números que aceita um ponto */
  const regexOnlyNumber = new RegExp(/^[0-9]*\.?[0-9]*$/);

  /** Função para transformar uma string em base64 para um ArrayBuffer */
  const base64ToArrayBuffer = (base64) => {
    const binaryString = window.atob(base64);
    const bytes = new Uint8Array(binaryString.length);
    return bytes.map((byte, i) => binaryString.charCodeAt(i));
  };

  /** Função para saber se as listas necessárias para a edição da proposta fizeram a requisição */
  const isAllsListsPopulated = () => {
    return (
      listaBus.length > 1 && listaForuns.length > 1 && listaSecoesTI.length > 1
    );
  };

  /** Função para verificar se o nome do anexo selecionado já existe dentro da proposta */
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

  /** Função que retorna o nome do anexo formatado a ser mostrado para o usuário */
  const getAnexoNome = (file) => {
    return file.nome
      ? file.nome + " - " + file.tipo
      : file.name + " - " + file.type;
  };

  /** Retorna booleano indicando se o parecer da comissão deve aparecer na tela */
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

  /** Formata o HTML em casos como a falta de fechamentos em tags "<br>" */
  const formatarHtml = (texto) => {
    texto = texto.replace(/<br>/g, "<br/>");
    return texto;
  };

  /** Valida se os CCs são válidos */
  const areCCsValid = (
    tabelasCustos = [EntitiesObjectService.tabelaCustos()]
  ) => {
    for (let tabelaCusto of tabelasCustos) {
      let porcentagemTotal = 0;

      for (let cc of tabelaCusto.ccs) {
        porcentagemTotal += cc.porcentagem;
      }

      if (porcentagemTotal != 100) return false;
    }
    return true;
  };

  /** Verifica se todos os campos estão preenchidos corretamente */
  const isAllFieldsValid = () => {
    let propostaAux = EntitiesObjectService.proposta();
    let msgs = texts.detalhesPropostaEditMode;

    propostaAux = JSON.parse(JSON.stringify(proposta));

    if (!propostaAux.codigoPPM) {
      setTextoDadosInvalidos(`${msgs.dadoInvalido} ${msgs.ppm}`);
      return false;
    }
    if (!propostaAux.data) {
      setTextoDadosInvalidos(`${msgs.dadoInvalido} ${msgs.data}`);
      return false;
    }
    if (!propostaAux.titulo) {
      setTextoDadosInvalidos(`${msgs.dadoInvalido} ${msgs.titulo}`);
      return false;
    }
    if (!propostaAux.solicitante) {
      setTextoDadosInvalidos(`${msgs.dadoInvalido} ${msgs.solicitante}`);
      return false;
    }
    if (!propostaAux.buSolicitante) {
      setTextoDadosInvalidos(`${msgs.dadoInvalido} ${msgs.buSolicitante}`);
      return false;
    }
    if (!propostaAux.gerente) {
      setTextoDadosInvalidos(`${msgs.dadoInvalido} ${msgs.gerente}`);
      return false;
    }
    if (!propostaAux.forum) {
      setTextoDadosInvalidos(`${msgs.dadoInvalido} ${msgs.forum}`);
      return false;
    }
    if (!propostaAux.tamanho) {
      setTextoDadosInvalidos(`${msgs.dadoInvalido} ${msgs.tamanho}`);
      return false;
    }
    if (!propostaAux.secaoTI) {
      setTextoDadosInvalidos(`${msgs.dadoInvalido} ${msgs.secaoTi}`);
      return false;
    }

    if (!propostaAux.proposta || propostaAux.proposta == "<p><br></p>") {
      setTextoDadosInvalidos(`${msgs.dadoInvalido} ${msgs.proposta}`);
      return false;
    }
    if (!propostaAux.problema || propostaAux.problema == "<p><br></p>") {
      setTextoDadosInvalidos(`${msgs.dadoInvalido} ${msgs.problema}`);
      return false;
    }
    if (!propostaAux.escopo || propostaAux.escopo == "<p><br></p>") {
      setTextoDadosInvalidos(`${msgs.dadoInvalido} ${msgs.escopo}`);
      return false;
    }
    if (!propostaAux.frequencia) {
      setTextoDadosInvalidos(`${msgs.dadoInvalido} ${msgs.frequencia}`);
      return false;
    }
    if (propostaAux.busBeneficiadas.length == 0) {
      setTextoDadosInvalidos(`${msgs.dadoInvalido} ${msgs.busBeneficiadas}`);
      return false;
    }
    if (!propostaAux.linkJira) {
      setTextoDadosInvalidos(`${msgs.dadoInvalido} ${msgs.jira}`);
      return false;
    }
    if (!propostaAux.inicioExecucao) {
      setTextoDadosInvalidos(`${msgs.dadoInvalido} ${msgs.inicioExecucao}`);
      return false;
    }
    if (!propostaAux.fimExecucao) {
      setTextoDadosInvalidos(`${msgs.dadoInvalido} ${msgs.fimExecucao}`);
      return false;
    }
    if (!propostaAux.paybackValor) {
      setTextoDadosInvalidos(`${msgs.dadoInvalido} ${msgs.paybackValor}`);
      return false;
    }
    if (!propostaAux.paybackTipo) {
      setTextoDadosInvalidos(`${msgs.dadoInvalido} ${msgs.paybackTipo}`);
      return false;
    }
    if (propostaAux.responsavelNegocio.length == 0) {
      setTextoDadosInvalidos(`${msgs.dadoInvalido} ${msgs.responsavelNegocio}`);
      return false;
    }

    if (propostaAux.beneficios.length > 0)
      for (let beneficio of propostaAux.beneficios) {
        if (!beneficio.tipoBeneficio) {
          setTextoDadosInvalidos(`${msgs.dadoInvalido} ${msgs.benTipo}`);
          return false;
        }
        if (
          !beneficio.memoriaCalculo ||
          beneficio.memoriaCalculo == "<p><br></p>"
        ) {
          setTextoDadosInvalidos(
            `${msgs.dadoInvalido} ${msgs.benMemoriaCalculo}`
          );
          return false;
        }
        if (beneficio.tipoBeneficio != "QUALITATIVO") {
          if (!beneficio.moeda) {
            setTextoDadosInvalidos(`${msgs.dadoInvalido} ${msgs.benMoeda}`);
            return false;
          }
          if (!beneficio.valor_mensal) {
            setTextoDadosInvalidos(`${msgs.dadoInvalido} ${msgs.benValor}`);
            return false;
          }
        }
      }

    for (let tabelaCusto of propostaAux.tabelaCustos) {
      if (!tabelaCusto.tipoDespesa) {
        setTextoDadosInvalidos(`${msgs.dadoInvalido} ${msgs.tipoDespesa}`);
        return false;
      }
      for (let custo of tabelaCusto.custos) {
        if (!custo.perfilDespesa) {
          setTextoDadosInvalidos(`${msgs.dadoInvalido} ${msgs.perfilDespesa}`);
          return false;
        }
        if (!custo.periodoExecucao) {
          setTextoDadosInvalidos(
            `${msgs.dadoInvalido} ${msgs.periodoExecucao}`
          );
          return false;
        }
        if (!custo.horas) {
          setTextoDadosInvalidos(`${msgs.dadoInvalido} ${msgs.horas}`);
          return false;
        }
        if (!custo.valorHora) {
          setTextoDadosInvalidos(`${msgs.dadoInvalido} ${msgs.valorHora}`);
          return false;
        }
      }

      for (let cc of tabelaCusto.ccs) {
        if (!cc.codigo) {
          setTextoDadosInvalidos(`${msgs.dadoInvalido} ${msgs.codigoCC}`);
          return false;
        }
        if (!cc.porcentagem) {
          setTextoDadosInvalidos(`${msgs.dadoInvalido} ${msgs.porcentagemCC}`);
          return false;
        }
      }
    }

    if (!areCCsValid(propostaAux.tabelaCustos)) {
      setTextoDadosInvalidos(`${msgs.dadoInvalido} ${msgs.porcentagemCC100}`);
      return false;
    }

    return true;
  };

  /** Salva as edições da proposta no banco de dados */
  const saveProposal = () => {
    // Verificação dos campos
    if (!isAllFieldsValid()) return;

    // Dando erro ao salvar qualquer campo com editor de texto que contenha acento
    let propostaAux = EntitiesObjectService.proposta();
    propostaAux = JSON.parse(JSON.stringify(proposta));

    let novasTabelasCusto = JSON.parse(
      JSON.stringify(propostaAux.tabelaCustos)
    ).filter((tabelaCusto) => {
      if (tabelaCusto.id < 0) {
        // Se o ID for negativo ele é uma nova tabela de custos
        propostaAux.tabelaCustos.splice(
          propostaAux.tabelaCustos.findIndex(
            (element) => element == tabelaCusto
          ),
          1
        );
        return tabelaCusto;
      }
    });

    let novosBeneficios = JSON.parse(
      JSON.stringify(propostaAux.beneficios)
    ).filter((beneficio) => {
      if (beneficio.id < 0) {
        // Se o ID for negativo ele é um novo benefício
        propostaAux.beneficios.splice(
          propostaAux.beneficios.findIndex((element) => element == beneficio),
          1
        );
        return beneficio;
      }
    });

    let listaIdsAnexos = [];
    JSON.parse(JSON.stringify(propostaAux.anexo)).filter((anexo) => {
      if (anexo.id) {
        // Se existir ID ele já existia
        propostaAux.anexo.splice(
          propostaAux.anexo.findIndex((element) => element == anexo),
          1
        );
        listaIdsAnexos.push(anexo.id);
      }
    });

    // Verifica se o anexo não existia antes de ser editado e o adiciona na lista de novos anexos
    let novosAnexos = [];
    for (let anexo of proposta.anexo) {
      if (!anexo.id) {
        novosAnexos.push(anexo);
      }
    }

    propostaAux.anexo = []; // Setando como lista vazia porque os anexos estão sendo mandados em outras variáveis

    // Se o usuário quiser setar algum parecer como NONE, deve-se apagar o parecer no banco
    if (propostaAux.parecerComissao == "NONE")
      propostaAux.parecerComissao = null;
    if (propostaAux.parecerDG == "NONE") propostaAux.parecerDG = null;

    PropostaService.putComNovosDados(
      propostaAux,
      proposta.id,
      novasTabelasCusto,
      novosBeneficios,
      novosAnexos,
      listaIdsAnexos
    ).then((response) => {
      console.log(response);
      setPropostaData(response);
      setIsEditing(false);

      // Salvamento de histórico
      ExportPdfService.exportProposta(response.id).then((file) => {
        let arquivo = new Blob([file], { type: "application/pdf" });
        PropostaService.addHistorico(
          response.id,
          "Proposta Editada",
          arquivo,
          CookieService.getUser().id
        ).then(() => { });
      });
    });
  };

  // ***************************************** Handlers ***************************************** //

  /** Handler cancelar edição */
  const handleOnCancelEditClick = () => {
    setTextoModalConfirmacao("cancelarEdicao");
    setModalConfirmacao(true);
  };

  /** Handler salvar edição */
  const handleOnSaveEditClick = () => {
    setTextoModalConfirmacao("confirmEditar");
    setModalConfirmacao(true);
  };

  /** Função disparada quando clicado em confirmar no modal de confirmação */
  const handleOnConfirmClick = () => {
    if (textoModalConfirmacao == "cancelarEdicao") {
      setIsEditing(false);
    } else if (textoModalConfirmacao == "confirmEditar") {
      saveProposal();
    }

    setModalConfirmacao(false);
  };

  /** Handler do códigoPPM */
  const handleOnPPMChange = (event) => {
    if (!regexOnlyNumber.test(event.target.value)) return;
    setProposta({ ...proposta, codigoPPM: event.target.value });
  };

  /** Handler da data da proposta */
  const handleOnDataChange = (event) => {
    setProposta({ ...proposta, data: event.target.value });
  };

  /** Handler do isPublicada da proposta */
  const handleOnPublicadaClick = () => {
    setProposta({ ...proposta, publicada: !proposta.publicada });
  };

  /** Handler do título da proposta */
  const handleOnTituloChange = (event) => {
    setProposta({ ...proposta, titulo: event.target.value });
  };

  useEffect(() => {
    console.log("Proposta: ", proposta);
  }, [proposta]);

  /** Handler para quando for selecionado uma nova BU */
  const handleOnBuSolicitanteSelect = (event) => {
    setProposta({
      ...proposta,
      buSolicitante: listaBus.find((bu) => bu.idBu == event.target.value),
    });
  };

  /** Handler para quando for selecionado um novo fórum */
  const handleOnForumSelect = (event) => {
    setProposta({
      ...proposta,
      forum: listaForuns.find((forum) => forum.idForum == event.target.value),
    });
  };

  /** Handler para quando for selecionado um novo tamanho */
  const handleOnTamanhoSelect = (event) => {
    setProposta({
      ...proposta,
      tamanho: event.target.value,
    });
  };

  /** Handler para a seção de TI */
  const handleOnSecaoTISelect = (event) => {
    setProposta({
      ...proposta,
      secaoTI: listaSecoesTI.find(
        (secaoTI) => secaoTI.idSecao == event.target.value
      ),
    });
  };

  /** Handler da proposta da proposta */
  const handleOnPropostaChange = (event) => {
    setProposta({ ...proposta, proposta: event });
  };

  /** Handler do problema da proposta */
  const handleOnProblemaChange = (event) => {
    setProposta({ ...proposta, problema: event });
  };

  /** Handler do escopo da proposta */
  const handleOnEscopoChange = (event) => {
    setProposta({ ...proposta, escopo: event });
  };

  /** Handler da frequência da proposta */
  const handleOnFrequenciaChange = (event) => {
    setProposta({ ...proposta, frequencia: event.target.value });
  };

  const handleOnBeneficiosAddClick = () => {
    let newBeneficio = { ...EntitiesObjectService.beneficio() };
    let ultimoEl;

    if (proposta.beneficios.length == 0) {
      ultimoEl = { id: 0 };
    } else {
      ultimoEl = proposta.beneficios[proposta.beneficios.length - 1];
    }

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

  /** Handler do benefício da proposta */
  const handleOnBeneficioChange = (newBeneficio) => {
    let beneficiosAux = proposta.beneficios.map((beneficio) => {
      if (beneficio.id == newBeneficio.id) {
        return newBeneficio;
      }
      return beneficio;
    });

    setProposta({ ...proposta, beneficios: [...beneficiosAux] });
  };

  /** Handler para deletar um benefício */
  const handleDeleteBeneficio = (idBeneficio) => {
    let beneficiosAux = [...proposta.beneficios];
    beneficiosAux.splice(
      beneficiosAux.findIndex((beneficio) => beneficio.id == idBeneficio),
      1
    );

    setIsBeneficiosVisible(false);
    setProposta({ ...proposta, beneficios: [...beneficiosAux] });
  };

  /** Handler do link do jira da proposta */
  const handleOnLinkJiraChange = (event) => {
    setProposta({
      ...proposta,
      linkJira: event.target.value,
    });
  };

  /** Handler do início de execução da proposta */
  const handleOnInicioExecucaoChange = (event) => {
    setProposta({
      ...proposta,
      inicioExecucao: event.target.value,
    });
  };

  /** Handler do fim de execução da proposta */
  const handleOnFimExecucaoChange = (event) => {
    setProposta({
      ...proposta,
      fimExecucao: event.target.value,
    });
  };

  /** Handler do payback valor da proposta */
  const handleOnPaybackValorChange = (event) => {
    if (!regexOnlyNumber.test(event.target.value)) return;

    setProposta({
      ...proposta,
      paybackValor: event.target.value,
    });
  };

  /** Handler do payback tipo da proposta */
  const handlePaybackTIpoSelect = (event) => {
    setProposta({
      ...proposta,
      paybackTipo: event.target.value,
    });
  };

  /** Handler para quando for selecionado uma nova bu beneficiada */
  const handleOnBuBeneficiadaSelect = (event, newValue) => {
    setProposta({
      ...proposta,
      busBeneficiadas: newValue,
    });
  };

  /** Handler para quando clicar no ícone de add anexo */
  const handleAnexosAddOnClick = () => {
    inputFile.current.click();
  };

  /** Handler para quando for selecionado um ou mais arquivos */
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

  /** Handler para baixar um anexo */
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

  /** Handler para deletar um anexo */
  const handleOnDeleteAnexo = (file) => {
    let anexosAux = [...proposta.anexo];
    anexosAux.splice(
      anexosAux.findIndex((oldFile) => oldFile == file),
      1
    );

    setProposta({ ...proposta, anexo: [...anexosAux] });
  };

  /** Handler para quando a tabela de custos for alterada */
  const handleOnTabelaCustosChange = (
    newTabelaCustos = EntitiesObjectService.tabelaCustos()
  ) => {
    let tabelaCustosAux = proposta.tabelaCustos.map((tabelaCusto) => {
      if (tabelaCusto.id == newTabelaCustos.id) {
        return newTabelaCustos;
      }
      return tabelaCusto;
    });

    setProposta({
      ...proposta,
      tabelaCustos: [...tabelaCustosAux],
    });
  };

  /** Handle de quando uma tabela de custo for deletada */
  const handleDeleteTabelaCusto = (idTabela) => {
    let tabelaCustosAux = proposta.tabelaCustos;
    tabelaCustosAux.splice(
      tabelaCustosAux.findIndex((tabelaCusto) => tabelaCusto.id == idTabela),
      1
    );

    setIsTabelaCustosVisible(false);
    setProposta({ ...proposta, tabelaCustos: tabelaCustosAux });
  };

  /** Handler para quando clicar no botão de adicionar criar uma nova tabela de custos */
  const handleOnTabelaCustosAddClick = () => {
    let newTabelaCustos = EntitiesObjectService.tabelaCustos();
    let ultimoEl;

    if (proposta.tabelaCustos.length == 0) {
      ultimoEl = { id: 0 };
    } else {
      ultimoEl = proposta.tabelaCustos[proposta.tabelaCustos.length - 1];
    }
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

  /** Handler par quando for captado algum som do microfone */
  const handleOnMicChange = () => {
    switch (localClique) {
      case "titulo":
        setProposta({
          ...proposta,
          titulo: palavrasJuntas,
        });
        break;
      case "frequencia":
        setProposta({
          ...proposta,
          frequencia: palavrasJuntas,
        });
        break;
      case "linkJira":
        setProposta({
          ...proposta,
          linkJira: palavrasJuntas,
        });
      case "ppm":
        setProposta({ ...proposta, codigoPPM: palavrasJuntas });
        break;
      case "proposta":
        setProposta({ ...proposta, proposta: palavrasJuntas });
        break;
      case "problema":
        setProposta({ ...proposta, problema: palavrasJuntas });
        break;
      case "escopo":
        setProposta({ ...proposta, escopo: palavrasJuntas });
        break;
      case "paybackValor":
        setProposta({ ...proposta, paybackValor: palavrasJuntas });
        break;
      default:
        break;
    }
  };

  // ***************************************** Fim Handlers ***************************************** //

  // ***************************************** UseEffects ***************************************** //

  // Carregamento enquanto as requisições são feitas
  useEffect(() => {
    if (isAllsListsPopulated()) {
      setIsLoading(false);
    }
  }, [listaBus, listaForuns, listaSecoesTI]);

  // Carrega as informações iniciais (listas)
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

    setProposta({
      ...proposta,
      beneficios: [...beneficiosAux],
    });
  }, []);

  // Se os beneficios não estiverem visiveis, seta como visiveis
  useEffect(() => {
    if (!isBeneficiosVisible) {
      setIsBeneficiosVisible(true);
    }

    if (!isTabelaCustosVisile) {
      setIsTabelaCustosVisible(true);
    }
  }, [isBeneficiosVisible, isTabelaCustosVisile]);

  // se os dados estiverem invalidos ele chamar o feedback de dados invalidos
  useEffect(() => {
    if (textoDadosInvalidos) setFeedbackDadosInvalidos(true);
  }, [textoDadosInvalidos]);

  // ***************************************** Fim UseEffects ***************************************** //

  // Enquanto estiver buscando os dados, aparecerá o loader
  if (isLoading)
    return (
      <Box className="flex justify-center">
        <ClipLoader className="mt-2 border-t-6" color="#00579D" size={110} />
      </Box>
    );

  return (
    <>
      {/* Modal para confirmação */}
      <ModalConfirmacao
        open={modalConfirmacao}
        setOpen={setModalConfirmacao}
        textoModal={textoModalConfirmacao}
        textoBotao={"sim"}
        onConfirmClick={handleOnConfirmClick}
        onCancelClick={() => { }}
      />
      {/* Feedback de dados invalidos */}
      <Feedback
        open={feedbackDadosInvalidos}
        handleClose={() => setFeedbackDadosInvalidos(false)}
        status={"erro"}
        mensagem={textoDadosInvalidos}
      />
      {/* Feedback de anexos com mesmo nome */}
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
            {/* Palavra PPM */}
            <Typography
              color="primary"
              fontWeight="bold"
              fontSize={FontConfig.big}
              onClick={() => {
                lerTexto(texts.detalhesProposta.ppm);
              }}
            >
              {texts.detalhesProposta.ppm}:
              <Asterisco />
            </Typography>
            {/* input do PPM */}
            <InputCustom
              defaultText={proposta.codigoPPM}
              saveProposal={(text) =>
                handleOnPPMChange({ target: { value: text } })
              }
              label="ppm"
              sx={{ width: "7rem", height: "2rem" }}
              handleOnMicChange={handleOnMicChange}
              regex={regexOnlyNumber}
            />
          </Box>
          <Box className="flex gap-2">
            {/* Data */}
            <Typography
              color="primary"
              fontWeight="bold"
              fontSize={FontConfig.big}
              onClick={() => {
                lerTexto(texts.detalhesProposta.data);
              }}
            >
              {texts.detalhesProposta.data}:
              <Asterisco />
            </Typography>
            {/* input da data */}
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
                onClick={() => {
                  if (proposta.publicada) {
                    lerTexto(texts.detalhesProposta.publicada.toUpperCase());
                  } else {
                    lerTexto(texts.detalhesProposta.naoPublicada.toUpperCase());
                  }
                }}
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
        {/* Logo WEG */}
        <Box className="flex w-16">
          <img src={LogoWEG} className="w-16 h-11" alt="Logo WEG" />
        </Box>
      </Box>

      {/* Box Conteudo */}
      <Box className="w-full">
        {/* Titulo */}
        <Box className="flex items-center">
          <InputCustom
            label="titulo"
            defaultText={proposta.titulo}
            saveProposal={(text) =>
              handleOnTituloChange({ target: { value: text } })
            }
            sx={{ color: "primary.main", fontSize: FontConfig.smallTitle }}
            multiline={true}
            handleOnMicChange={handleOnMicChange}
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
            <Typography
              fontSize={FontConfig.medium}
              fontWeight="bold"
              onClick={() => {
                lerTexto(texts.detalhesProposta.solicitante);
              }}
            >
              {texts.detalhesProposta.solicitante}:&nbsp;
            </Typography>
            <Typography
              fontSize={FontConfig.medium}
              onClick={() => {
                lerTexto(
                  proposta.solicitante.nome +
                  " - " +
                  proposta.solicitante.departamento.nome
                );
              }}
            >
              {proposta.solicitante.nome} -{" "}
              {proposta.solicitante.departamento.nome}
            </Typography>
          </Box>

          {/* Bu solicitante */}
          <Box className="flex mt-4 gap-2">
            <Typography
              fontSize={FontConfig.medium}
              fontWeight="bold"
              onClick={() => {
                lerTexto(texts.detalhesProposta.buSolicitante);
              }}
            >
              {texts.detalhesProposta.buSolicitante}:<Asterisco />
              &nbsp;
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
            <Typography
              fontSize={FontConfig.medium}
              fontWeight="bold"
              onClick={() => {
                lerTexto(texts.detalhesProposta.gerente);
              }}
            >
              {texts.detalhesProposta.gerente}:&nbsp;
            </Typography>
            <Typography
              fontSize={FontConfig.medium}
              onClick={() => {
                lerTexto(
                  proposta.gerente.nome +
                  " - " +
                  proposta.gerente.departamento.nome
                );
              }}
            >
              {proposta.gerente.nome} - {proposta.gerente.departamento.nome}
            </Typography>
          </Box>

          {/* Fórum e Tamanho*/}
          <Box className="flex w-full justify-between mt-4">
            {/* Fórum */}
            <Box className="flex flex-row w-3/4 gap-2">
              <Typography
                fontSize={FontConfig.medium}
                fontWeight="bold"
                onClick={() => {
                  lerTexto(texts.detalhesProposta.forum);
                }}
              >
                {texts.detalhesProposta.forum}:<Asterisco />
                &nbsp;
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
              <Typography
                fontSize={FontConfig.medium}
                fontWeight="bold"
                onClick={() => {
                  lerTexto(texts.detalhesProposta.tamanho);
                }}
              >
                {texts.detalhesProposta.tamanho}:<Asterisco />
                &nbsp;
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
            <Typography
              fontSize={FontConfig.medium}
              fontWeight="bold"
              onClick={() => {
                lerTexto(texts.detalhesProposta.secaoTi);
              }}
            >
              {texts.detalhesProposta.secaoTi}:<Asterisco />
              &nbsp;
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
            <Typography
              fontSize={FontConfig.medium}
              fontWeight="bold"
              onClick={() => {
                lerTexto(texts.detalhesProposta.proposta);
              }}
            >
              {texts.detalhesProposta.proposta}:
              <Asterisco />
              &nbsp;
            </Typography>
            <Box className="mx-4">
              <QuillCustom
                label="proposta"
                defaultText={proposta.proposta}
                saveProposal={(value) => handleOnPropostaChange(value)}
                modules={modulesQuill}
                handleOnMicChange={handleOnMicChange}
              />
            </Box>
          </Box>

          {/* Problema / Situação atual */}
          <Box className="flex flex-col gap-2 mt-4">
            <Typography
              fontSize={FontConfig.medium}
              fontWeight="bold"
              onClick={() => {
                lerTexto(texts.detalhesProposta.problema);
              }}
            >
              {texts.detalhesProposta.problema}:
              <Asterisco />
              &nbsp;
            </Typography>
            <Box className="mx-4">
              <QuillCustom
                label="problema"
                defaultText={proposta.problema}
                saveProposal={(value) => handleOnProblemaChange(value)}
                modules={modulesQuill}
                handleOnMicChange={handleOnMicChange}
              />
            </Box>
          </Box>

          {/* Escopo da proposta */}
          <Box className="mt-4">
            <Typography
              fontSize={FontConfig.medium}
              fontWeight="bold"
              onClick={() => {
                lerTexto(texts.detalhesProposta.escopoDaProposta);
              }}
            >
              {texts.detalhesProposta.escopoDaProposta}:&nbsp;
            </Typography>
            <Box className="mx-4">
              <QuillCustom
                label="escopo"
                defaultText={proposta.escopo}
                saveProposal={(value) => handleOnEscopoChange(value)}
                modules={modulesQuill}
                handleOnMicChange={handleOnMicChange}
              />
            </Box>
          </Box>

          {/* Frequência */}
          <Box className="flex flex-col mt-4">
            <Typography
              fontSize={FontConfig.medium}
              fontWeight="bold"
              onClick={() => {
                lerTexto(texts.detalhesProposta.frequencia);
              }}
            >
              {texts.detalhesProposta.frequencia}:
              <Asterisco />
              &nbsp;
            </Typography>
            <Box className="mx-4 flex items-center">
              <InputCustom
                label="frequencia"
                defaultText={proposta.frequencia}
                saveProposal={(text) =>
                  handleOnFrequenciaChange({ target: { value: text } })
                }
                sx={{ fontSize: FontConfig.medium }}
                multiline={true}
                handleOnMicChange={handleOnMicChange}
              />
            </Box>
          </Box>

          {/* Tabela de custos */}
          <Box className="mt-4">
            <Box className="flex items-center">
              <Typography
                fontSize={FontConfig.medium}
                fontWeight="bold"
                onClick={() => {
                  lerTexto(texts.detalhesProposta.tabelaDeCustos);
                }}
              >
                {texts.detalhesProposta.tabelaDeCustos}:&nbsp;
              </Typography>
              <Tooltip title={texts.formularioBeneficiosDemanda.adicionar}>
                <IconButton onClick={handleOnTabelaCustosAddClick}>
                  <AddCircleIcon color="primary" />
                </IconButton>
              </Tooltip>
            </Box>
            <Box className="mx-4">
              {proposta.tabelaCustos.length > 0 && isTabelaCustosVisile ? (
                proposta.tabelaCustos?.map((tabela, index) => {
                  return (
                    <TabelaCustos
                      key={index}
                      dados={tabela}
                      handleOnTabelaCustosChange={handleOnTabelaCustosChange}
                      handleDeleteTabelaCusto={handleDeleteTabelaCusto}
                    />
                  );
                })
              ) : (
                <Typography
                  className="text-center"
                  fontSize={FontConfig.medium}
                  color="text.secondary"
                  onClick={() => lerTexto(texts.detalhesProposta.semBeneficios)}
                >
                  {texts.detalhesProposta.semTabelasDeCusto}
                </Typography>
              )}
            </Box>
          </Box>

          {/* Benefícios */}
          <Box className="mt-4">
            <Box className="flex items-center">
              <Typography
                fontSize={FontConfig.medium}
                fontWeight="bold"
                onClick={() => {
                  lerTexto(texts.detalhesProposta.beneficios);
                }}
              >
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
                  onClick={() => {
                    lerTexto(texts.detalhesProposta.semBeneficios);
                  }}
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
                onClick={() => {
                  lerTexto(texts.detalhesProposta.busBeneficiadas);
                }}
              >
                {texts.detalhesProposta.busBeneficiadas}:
                <Asterisco />
                &nbsp;
              </Typography>
              <Box className="w-full">
                {/* Select de BUs beneficiadas */}
                <Autocomplete
                  multiple
                  value={proposta.busBeneficiadas}
                  isOptionEqualToValue={(option, value) => {
                    return option.idBu === value.idBu;
                  }}
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
                      <li
                        onClick={() => lerTexto(bu.sigla + "-" + bu.nomeBu)}
                        key={index}
                      >
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
                  onClick={() => {
                    lerTexto(texts.detalhesProposta.semBuBeneficiada);
                  }}
                >
                  {texts.detalhesProposta.semBuBeneficiada}
                </Typography>
              )}
            </Box>
          </Box>

          {/* Link do Jira */}
          <Box className="mt-4">
            <Typography
              fontSize={FontConfig.medium}
              fontWeight="bold"
              onClick={() => {
                lerTexto(texts.detalhesProposta.linkJira);
              }}
            >
              {texts.detalhesProposta.linkJira}:
              <Asterisco />
              &nbsp;
            </Typography>
            <Box className="mx-4 flex items-center">
              <InputCustom
                label="linkJira"
                defaultText={proposta.linkJira}
                saveProposal={(text) =>
                  handleOnLinkJiraChange({ target: { value: text } })
                }
                sx={{ fontSize: FontConfig.medium, paddingLeft: "0.6rem" }}
                multiline={true}
                handleOnMicChange={handleOnMicChange}
              />
            </Box>
          </Box>

          {/* Período de execução */}
          <Box className="flex flex-row mt-4">
            <Typography
              fontSize={FontConfig.medium}
              fontWeight="bold"
              onClick={() => {
                lerTexto(texts.detalhesProposta.periodoDeExecucao);
              }}
            >
              {texts.detalhesProposta.periodoDeExecucao}:
              <Asterisco />
              &nbsp;
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
            <Typography
              fontSize={FontConfig.medium}
              fontWeight="bold"
              onClick={() => {
                lerTexto(texts.detalhesProposta.payback);
              }}
            >
              {texts.detalhesProposta.payback}:
              <Asterisco />
              &nbsp;
            </Typography>

            {/* Payback Valor */}
            <InputCustom
              label="paybackValor"
              defaultText={proposta.paybackValor}
              saveProposal={(text) =>
                handleOnPaybackValorChange({ target: { value: text } })
              }
              sx={{ fontSize: FontConfig.medium, width: "4rem" }}
              regex={regexOnlyNumber}
              handleOnMicChange={handleOnMicChange}
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
              <Typography
                fontSize={FontConfig.medium}
                fontWeight="bold"
                onClick={() => {
                  lerTexto(texts.detalhesProposta.anexos);
                }}
              >
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
                      <Typography
                        fontSize={FontConfig.medium}
                        onClick={() => {
                          lerTexto(getAnexoNome(anexo));
                        }}
                      >
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
                  onClick={() => {
                    lerTexto(texts.detalhesProposta.semAnexos);
                  }}
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
                <Typography
                  key={index}
                  fontSize={FontConfig.medium}
                  onClick={() => {
                    lerTexto(responsavel.nome + " - " + responsavel.area);
                  }}
                >
                  {responsavel.nome} - {responsavel.area}
                </Typography>
              );
            })}
            <Typography
              fontSize={FontConfig.medium}
              fontWeight="bold"
              onClick={() => {
                lerTexto(texts.detalhesProposta.reponsaveisPeloNegocio);
              }}
            >
              {texts.detalhesProposta.reponsaveisPeloNegocio}
            </Typography>
          </Box>

          {/* Pareceres */}
          {emAprovacao && (
            <>
              <Divider />
              <Box className="mt-3">
                <Typography
                  fontSize={FontConfig.big}
                  fontWeight="bold"
                  onClick={() => {
                    lerTexto(texts.detalhesProposta.pareceres);
                  }}
                >
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
            </>
          )}
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
  ) => { },
  handleDeleteTabelaCusto = () => { },
}) => {
  // Context para obter as configurações de fontes do sistema
  const { FontConfig } = useContext(FontContext);

  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  // Context para ler o texto da tela
  const { lerTexto } = useContext(SpeechSynthesisContext);

  // ***************************************** Handlers ***************************************** //

  // Handler para quando o tipo de despesa for alterado
  const handleOnTipoDespesaChange = (event) => {
    handleOnTabelaCustosChange({ ...dados, tipoDespesa: event.target.value });
  };

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
      <Paper
        className="flex items-center pt-2 pl-4 pb-2 gap-4"
        sx={{ backgroundColor: "primary.main", opacity: 2 }}
        square
      >
        {/* Tipo da despesa */}
        <Typography
          fontWeight="bold"
          color="text.white"
          fontSize={FontConfig.default}
          onClick={() => {
            lerTexto(texts.detalhesProposta.tipoDaDespesa);
          }}
        >
          {texts.detalhesProposta.tipoDaDespesa}
        </Typography>
        {/* Select do tipo da despesa */}
        <Select
          value={dados.tipoDespesa}
          onChange={handleOnTipoDespesaChange}
          sx={{ width: "7rem", color: "white" }}
          variant="standard"
        >
          <MenuItem selected value={"Interna"}>
            <Typography fontSize={FontConfig.medium}>Interna</Typography>
          </MenuItem>
          <MenuItem value={"Externa"}>
            <Typography fontSize={FontConfig.medium}>Externa</Typography>
          </MenuItem>
        </Select>
      </Paper>
      {/* Divisor */}
      <Divider />
      {/* Tabela de custos */}
      <Table className="table-fixed w-full">
        <TableHead>
          <TableRow sx={{ backgroundColor: "primary.main" }}>
            {/* Perfil da despesa */}
            <th className="text-white p-1">
              <Typography
                fontWeight="bold"
                fontSize={FontConfig.default}
                onClick={() => {
                  lerTexto(texts.detalhesProposta.perfilDaDespesa);
                }}
              >
                {texts.detalhesProposta.perfilDaDespesa}
              </Typography>
            </th>
            {/* Periodo de execução */}
            <th className="text-white p-1">
              <Typography
                fontWeight="bold"
                fontSize={FontConfig.default}
                onClick={() => {
                  lerTexto(texts.detalhesProposta.periodoDeExecucaoTabela);
                }}
              >
                {texts.detalhesProposta.periodoDeExecucaoTabela}
              </Typography>
            </th>
            {/* Quantidade de horas */}
            <th className="text-white p-1">
              <Typography
                fontWeight="bold"
                fontSize={FontConfig.default}
                onClick={() => {
                  lerTexto(texts.detalhesProposta.horas);
                }}
              >
                {texts.detalhesProposta.horas}
              </Typography>
            </th>
            {/* Valor por hora */}
            <th className="text-white p-1">
              <Typography
                fontWeight="bold"
                fontSize={FontConfig.default}
                onClick={() => {
                  lerTexto(texts.detalhesProposta.valorHora);
                }}
              >
                {texts.detalhesProposta.valorHora}
              </Typography>
            </th>
            {/* Valor total */}
            <th className="text-white p-1">
              <Typography
                fontWeight="bold"
                fontSize={FontConfig.default}
                onClick={() => {
                  lerTexto(texts.detalhesProposta.total);
                }}
              >
                {texts.detalhesProposta.total}
              </Typography>
            </th>
          </TableRow>
        </TableHead>
        {/* Linha de cada custo do beneficio */}
        <TableBody>
          {dados.custos.map((custo, index) => {
            return (
              <CustosRow
                key={index}
                custo={custo}
                dados={dados}
                handleOnCustoChange={handleOnCustoChange}
              />
            );
          })}
        </TableBody>
      </Table>
      {/* Divisor */}
      <Divider />
      {/* Botões de adicionar linha */}
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
      {/* Tabela do CCs */}
      <Table className="table-fixed w-full">
        <TableHead>
          <TableRow sx={{ backgroundColor: "primary.main" }}>
            {/* Ccs */}
            <th className="text-white p-1">
              <Typography
                fontSize={FontConfig.medium}
                onClick={() => {
                  lerTexto(texts.detalhesProposta.ccs);
                }}
              >
                {texts.detalhesProposta.ccs}
              </Typography>
            </th>
            {/* Porcentagem */}
            <th className="text-white p-1 w-1/4">
              <Typography
                fontSize={FontConfig.medium}
                onClick={() => {
                  lerTexto(texts.detalhesProposta.porcentagem);
                }}
              >
                {texts.detalhesProposta.porcentagem}
              </Typography>
            </th>
          </TableRow>
        </TableHead>
        {/* linha de cada CC */}
        <TableBody>
          {dados.ccs.map((cc, index) => {
            return (
              <CC key={index} cc={cc} handleOnCCChange={handleOnCCChange} />
            );
          })}
        </TableBody>
      </Table>
      {/* Botao de excluir e add */}
      <Box className="w-full flex justify-end px-2">
        {/* botao de excluir linha */}
        <Tooltip title={texts.linhaTabelaCCs.titleExcluirLinha}>
          <IconButton
            onClick={handleOnDeleteCCClick}
            size="small"
            color="primary"
          >
            <RemoveIcon />
          </IconButton>
        </Tooltip>
        {/* botao de adicionar */}
        <Tooltip title={texts.formularioBeneficiosDemanda.adicionar}>
          <IconButton onClick={handleOnAddCCClick} size="small" color="primary">
            <AddIcon />
          </IconButton>
        </Tooltip>
        {/* botao de remover o beneficio */}
        <Tooltip title={texts.formularioAnexosDemanda.remover}>
          <IconButton
            onClick={handleOnDeleteTabelaClick}
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

const CC = ({
  cc = EntitiesObjectService.cc(),
  handleOnCCChange = (newCC = EntitiesObjectService.cc()) => { },
}) => {
  // Context para obter as configurações de fonte do sistema
  const { FontConfig } = useContext(FontContext);

  // Context para obter a função de leitura de texto
  const { localClique, palavrasJuntas } = useContext(SpeechRecognitionContext);

  const regexOnlyNumber = new RegExp(/^[0-9]*\.?[0-9]*$/);

  // ***************************************** Handlers ***************************************** //

  const handleOnCodigoChange = (event) => {
    handleOnCCChange({ ...cc, codigo: event.target.value });
  };

  const handleOnPorcentagemChange = (event) => {
    if (!regexOnlyNumber.test(event.target.value)) return;

    handleOnCCChange({ ...cc, porcentagem: event.target.value });
  };

  const handleOnMicChange = () => {
    switch (localClique) {
      case "codigo":
        handleOnCCChange({ ...cc, codigo: palavrasJuntas });
        break;
      case "porcentagem":
        handleOnCCChange({ ...cc, porcentagem: palavrasJuntas });
        break;
      case "linkJira":
      default:
        break;
    }
  };

  // ***************************************** Fim Handlers ***************************************** //

  return (
    <TableRow className="w-full border rounded">
      {/* Codigo do CC */}
      <td className="text-center p-2">
        <InputCustom
          label="codigo"
          defaultText={cc.codigo}
          saveProposal={(text) =>
            handleOnCodigoChange({ target: { value: text } })
          }
          sx={{ fontConfig: FontConfig.default }}
          regex={regexOnlyNumber}
          multiline={true}
          handleOnMicChange={handleOnMicChange}
        />
      </td>
      {/* Porcentagem do CC */}
      <td className="text-center p-2">
        <InputCustom
          label="porcentagem"
          defaultText={cc.porcentagem}
          saveProposal={(text) =>
            handleOnPorcentagemChange({ target: { value: text } })
          }
          sx={{ fontConfig: FontConfig.default }}
          regex={regexOnlyNumber}
          multiline={true}
          handleOnMicChange={handleOnMicChange}
        />
      </td>
    </TableRow>
  );
};

// Mostra os custos na proposta
const CustosRow = ({
  custo = EntitiesObjectService.custo(),
  handleOnCustoChange = (newCusto = EntitiesObjectService.custo()) => { },
}) => {
  // Context para obter as configurações de fonte do sistema
  const { FontConfig } = useContext(FontContext);

  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  // Context para obter a função de leitura de texto
  const { localClique, palavrasJuntas } = useContext(SpeechRecognitionContext);

  // Context para ler o texto da tela
  const { lerTexto } = useContext(SpeechSynthesisContext);

  const regexOnlyNumber = new RegExp(/^[0-9]*\.?[0-9]*$/);

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
  const handleOnPerfilDespesaChange = (event) => {
    handleOnCustoChange({
      ...custo,
      perfilDespesa: event.target.value,
    });
  };

  // Handler para quando o tipo de despesa for alterado
  const handleOnPeriodoExecucaoChange = (event) => {
    if (!regexOnlyNumber.test(event.target.value)) return;

    handleOnCustoChange({
      ...custo,
      periodoExecucao: event.target.value,
    });
  };

  // Handler para quando o tipo de despesa for alterado
  const handleOnHorasChange = (event) => {
    if (!regexOnlyNumber.test(event.target.value)) return;

    handleOnCustoChange({ ...custo, horas: event.target.value });
  };

  // Handler para quando o tipo de despesa for alterado
  const handleOnValorHoraChange = (event) => {
    if (!regexOnlyNumber.test(event.target.value)) return;

    handleOnCustoChange({
      ...custo,
      valorHora: event.target.value,
    });
  };

  const handleOnMicChange = () => {
    switch (localClique) {
      case "tipoDespesa":
        handleOnCustoChange({ ...custo, tipoDespesa: palavrasJuntas });
        break;
      case "porcentagem":
        break;
      case "perfilDespesa":
        handleOnCustoChange({ ...custo, perfilDespesa: palavrasJuntas });
        break;
      case "periodoExecucao":
        handleOnCustoChange({ ...custo, periodoExecucao: palavrasJuntas });
        break;
      case "horas":
        handleOnCustoChange({ ...custo, horas: palavrasJuntas });
        break;
      case "valorHora":
        handleOnCustoChange({ ...custo, valorHora: palavrasJuntas });
      default:
        break;
    }
  };

  // ***************************************** Fim Handlers ***************************************** //

  return (
    <TableRow>
      <td className="p-2 text-center">
        {/* Perfil da Despesa */}
        <InputCustom
          label="perfilDespesa"
          defaultText={custo.perfilDespesa}
          saveProposal={(text) =>
            handleOnPerfilDespesaChange({ target: { value: text } })
          }
          sx={{ fontConfig: FontConfig.default }}
          multiline={true}
          handleOnMicChange={handleOnMicChange}
        />
      </td>
      <td className="p-2 text-center">
        {/* Período de Execução */}
        <InputCustom
          label="periodoExecucao"
          defaultText={custo.periodoExecucao}
          saveProposal={(text) =>
            handleOnPeriodoExecucaoChange({ target: { value: text } })
          }
          sx={{ fontConfig: FontConfig.default }}
          regex={regexOnlyNumber}
          multiline={true}
          handleOnMicChange={handleOnMicChange}
        />
      </td>
      <td className="p-2 text-center">
        {/* Horas */}
        <InputCustom
          label="horas"
          defaultText={custo.horas}
          saveProposal={(text) =>
            handleOnHorasChange({ target: { value: text } })
          }
          sx={{ fontConfig: FontConfig.default }}
          regex={regexOnlyNumber}
          multiline={true}
          handleOnMicChange={handleOnMicChange}
        />
      </td>
      <td className="p-2 text-center">
        {/* Valor da Hora */}
        <InputCustom
          label="valorHora"
          defaultText={custo.valorHora}
          saveProposal={(text) =>
            handleOnValorHoraChange({ target: { value: text } })
          }
          sx={{ fontConfig: FontConfig.default }}
          regex={regexOnlyNumber}
          multiline={true}
          handleOnMicChange={handleOnMicChange}
        />
      </td>
      <td className="p-2 text-center">
        {/* Total */}
        <Typography
          fontSize={FontConfig.default}
          onClick={() => {
            lerTexto(getValorFormatted(custo.horas * custo.valorHora));
          }}
        >
          {getValorFormatted(custo.horas * custo.valorHora)}
        </Typography>
      </td>
    </TableRow>
  );
};

// Mostrar os benefícios da proposta
const Beneficio = ({
  beneficio = EntitiesObjectService.beneficio(),
  handleOnBeneficioChange = () => { },
  handleDeleteBeneficio = () => { },
}) => {
  // Context para obter as configurações de fonte do sistema
  const { FontConfig } = useContext(FontContext);

  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  // Context para obter a função de leitura de texto
  const { localClique, palavrasJuntas } = useContext(SpeechRecognitionContext);

  // Context para ler o texto da tela
  const { lerTexto } = useContext(SpeechSynthesisContext);

  // Estado se é um beneficio com tipo qualitativo
  const [isQualitativo, setIsQualitativo] = useState(false);

  /** Debounce o setState passado por parâmetro */
  const debounceState = _.debounce((setState, value) => {
    setState(value);
  }, 100);

  const regexOnlyNumber = new RegExp(/^[0-9]*\.?[0-9]*$/);

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

  // verifica se é qualitativo
  useEffect(() => {
    verifyIsQualitativo();
  }, []);

  // verifica se o tipo de benefício mudou
  useEffect(() => {
    verifyIsQualitativo();
  }, [beneficio]);

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
    if (!regexOnlyNumber.test(event.target.value)) return;

    handleOnBeneficioChange({
      ...beneficio,
      valor_mensal: event.target.value,
    });
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

  // Deleta o beneficio
  const handleOnDeleteBeneficio = () => {
    handleDeleteBeneficio(beneficio.id);
  };

  // ***************************************** Fim Handlers ***************************************** //

  // handle para o mic
  const handleOnMicChange = () => {
    switch (localClique) {
      case "valorMensal":
        handleOnBeneficioChange({
          ...beneficio,
          valor_mensal: palavrasJuntas,
        });
        break;
      case "memoriaCalculo":
        handleOnBeneficioChange({
          ...beneficio,
          memoriaCalculo: palavrasJuntas,
        });
        break;
      default:
        break;
    }
  };

  // Se o benefício não existir, não renderiza
  if (beneficio.id === 0) return null;

  return (
    <Paper
      className="relative flex justify-between mt-2 border-t-4"
      sx={{ borderTopColor: "primary.main" }}
      square
    >
      <Table className="table-fixed">
        <TableBody>
          <TableRow>
            {/* Tipo do Beneficio */}
            <th className="p-1 w-2/12">
              <Typography
                color="primary"
                fontWeight="bold"
                fontSize={FontConfig.medium}
                onClick={() => {
                  lerTexto(texts.detalhesProposta.tipoBeneficio);
                }}
              >
                {texts.detalhesProposta.tipoBeneficio}
              </Typography>
            </th>
            {/* Se não for qualitativo, vair mostrar o valor Mensal e a moeda */}
            {!isQualitativo && (
              <>
                {/* valor mensal */}
                <th className="p-1 w-2/12">
                  <Typography
                    color="primary"
                    fontWeight="bold"
                    fontSize={FontConfig.medium}
                    onClick={() => {
                      lerTexto(texts.detalhesProposta.valorMensal);
                    }}
                  >
                    {texts.detalhesProposta.valorMensal}
                  </Typography>
                </th>
                {/* Moeda */}
                <th className="p-1 w-1/12">
                  <Typography
                    color="primary"
                    fontWeight="bold"
                    fontSize={FontConfig.medium}
                    onClick={() => {
                      lerTexto(texts.detalhesProposta.moeda);
                    }}
                  >
                    {texts.detalhesProposta.moeda}
                  </Typography>
                </th>
              </>
            )}
            {/* Memória de Cálculo */}
            <th className="p-1">
              <Typography
                color="primary"
                fontWeight="bold"
                fontSize={FontConfig.medium}
                onClick={() => {
                  lerTexto(texts.detalhesProposta.memoriaCalculo);
                }}
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
                fullWidth
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
            {/* Se não for qualitativo, vair mostrar o valor Mensal e a moeda */}
            {!isQualitativo && (
              <>
                {/* Input de valor mensal */}
                <td className="text-center p-2">
                  <InputCustom
                    label="valorMensal"
                    defaultText={beneficio.valor_mensal}
                    saveProposal={(text) =>
                      handleOnValorMensalChange({ target: { value: text } })
                    }
                    sx={{ fontConfig: FontConfig.default }}
                    regex={regexOnlyNumber}
                    multiline={true}
                    handleOnMicChange={handleOnMicChange}
                  />
                </td>
                {/* Select da moeda do benefício */}
                <td className="text-center p-2">
                  <Select
                    value={beneficio.moeda}
                    onChange={handleOnMoedaChange}
                    variant="standard"
                    size="small"
                    fullWidth
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
              <QuillCustom
                defaultText={beneficio.memoriaCalculo}
                label="memoriaCalculo"
                modules={modulesQuill}
                saveProposal={(value) => handleOnMemoriaCalculoChange(value)}
                handleOnMicChange={handleOnMicChange}
              />
            </td>
          </TableRow>
        </TableBody>
      </Table>
      {/* Botão para remover o beneficio */}
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
  setProposta = () => { },
}) => {
  // Context para obter as configurações de fontes do sistema
  const { FontConfig } = useContext(FontContext);

  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  // Context para obter a função de leitura de texto
  const { localClique, palavrasJuntas } = useContext(SpeechRecognitionContext);

  // Context para ler o texto da tela
  const { lerTexto } = useContext(SpeechSynthesisContext);

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

  // Handler para quando o texto do parecer da comissão é alterado
  const handleOnParecerComissaoChange = (value) => {
    setProposta({ ...proposta, parecerInformacao: value });
  };

  // Handler para quando o parecer da comissão é selecionado
  const handleOnSelectParecer = (event) => {
    let parecer = event.target.value;
    if (parecer == "NONE") {
      setProposta({
        ...proposta,
        parecerComissao: parecer,
        parecerInformacao: null,
      });
    } else {
      setProposta({ ...proposta, parecerComissao: parecer });
    }
  };

  // Handle para Mic
  const handleOnMicChange = () => {
    switch (localClique) {
      case "valorMensal":
        handleOnParecerComissaoChange(palavrasJuntas);
        break;
      default:
        break;
    }
  };

  return (
    <Box>
      <Box className="flex">
        <Box className="flex items-center mt-4">
          {/* Comissao */}
          <Typography
            fontSize={FontConfig.medium}
            onClick={() => {
              lerTexto(
                texts.detalhesProposta.comissao + " " + proposta.forum.nome
              );
            }}
          >
            {texts.detalhesProposta.comissao} {proposta.forum.nome}:&nbsp;
          </Typography>
        </Box>
        {/* Select para o parecer da comissão */}
        <TextField
          select
          label={texts.detalhesProposta.parecer}
          value={proposta.parecerComissao ? proposta.parecerComissao : ""}
          onChange={handleOnSelectParecer}
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
          <MenuItem key={"Nenhum"} value={"NONE"}>
            <Typography fontSize={FontConfig.medium}>
              {texts.detalhesProposta.nenhum}
            </Typography>
          </MenuItem>
        </TextField>
      </Box>
      <Box className="mt-4">
        {/* Caixa de texto para o parecer da comissão */}
        {proposta.parecerComissao != "NONE" && (
          <QuillCustom
            defaultText={
              proposta.parecerInformacao ? proposta.parecerInformacao : ""
            }
            label="parecerComsisao"
            modules={modulesQuill}
            saveProposal={(value) => handleOnParecerComissaoChange(value)}
            handleOnMicChange={handleOnMicChange}
          />
        )}
      </Box>
    </Box>
  );
};

// Escrever o parecer da DG
const ParecerDGInsertText = ({
  proposta = propostaExample,
  setProposta = () => { },
}) => {
  // Context para obter as configurações das fontes do sistema
  const { FontConfig } = useContext(FontContext);

  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  // Context para obter a função de leitura de texto
  const { localClique, palavrasJuntas } = useContext(SpeechRecognitionContext);

  // Context para ler o texto da tela
  const { lerTexto } = useContext(SpeechSynthesisContext);

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

  // Handler para quando o texto do parecer da comissão é alterado
  const handleOnParecerDGChange = (value) => {
    setProposta({ ...proposta, parecerInformacaoDG: value });
  };

  // Handler para o select do parecer da DG
  const handleOnSelectParecer = (event) => {
    let parecer = event.target.value;
    if (parecer == "NONE") {
      setProposta({
        ...proposta,
        parecerDG: parecer,
        parecerInformacaoDG: null,
      });
    } else {
      setProposta({ ...proposta, parecerDG: parecer });
    }
  };

  // Handler para Mic
  const handleOnMicChange = () => {
    switch (localClique) {
      case "parecerDG":
        handleOnParecerDGChange(palavrasJuntas);
        break;
      default:
        break;
    }
  };

  return (
    <Box className="mt-4">
      <Box className="flex">
        <Box className="flex items-center mt-4">
          {/* Direção Geral */}
          <Typography
            onClick={() => {
              lerTexto(texts.detalhesProposta.direcaoGeral);
            }}
          >
            {texts.detalhesProposta.direcaoGeral}: &nbsp;
          </Typography>
        </Box>
        {/* Select para o parecer da DG */}
        <TextField
          select
          label={texts.detalhesProposta.parecer}
          value={proposta.parecerDG ? proposta.parecerDG : ""}
          onChange={handleOnSelectParecer}
          variant="standard"
          sx={{ width: "10rem", marginLeft: "0.5rem" }}
        >
          <MenuItem key={"Aprovado"} value={"APROVADO"}>
            <Typography
              fontSize={FontConfig.medium}
              onClick={() => {
                lerTexto(texts.detalhesProposta.aprovado);
              }}
            >
              {texts.detalhesProposta.aprovado}
            </Typography>
          </MenuItem>
          <MenuItem key={"Reprovado"} value={"REPROVADO"}>
            <Typography
              fontSize={FontConfig.medium}
              onClick={() => {
                lerTexto(texts.detalhesProposta.reprovado);
              }}
            >
              {texts.detalhesProposta.reprovado}
            </Typography>
          </MenuItem>
          <MenuItem key={"Nenhum"} value={"NONE"}>
            <Typography
              fontSize={FontConfig.medium}
              onClick={() => {
                lerTexto(texts.detalhesProposta.nenhum);
              }}
            >
              {texts.detalhesProposta.nenhum}
            </Typography>
          </MenuItem>
        </TextField>
      </Box>
      {/* Caixa de texto para parecer da DG */}
      <Box className="mt-4">
        {proposta.parecerDG != "NONE" && (
          <QuillCustom
            defaultText={
              proposta.parecerInformacaoDG ? proposta.parecerInformacaoDG : ""
            }
            label="parecerDG"
            modules={modulesQuill}
            saveProposal={(value) => handleOnParecerDGChange(value)}
            handleOnMicChange={handleOnMicChange}
          />
        )}
      </Box>
    </Box>
  );
};

// Asterisco para campos obrigatórios
const Asterisco = () => {
  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  return (
    <Typography
      component="span"
      fontSize={FontConfig.medium}
      fontWeight={700}
      className="text-red-600"
    >
      *
    </Typography>
  );
};

export default DetalhesPropostaEditMode;
