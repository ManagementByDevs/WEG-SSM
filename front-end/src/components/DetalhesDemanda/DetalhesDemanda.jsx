import React, { useState, useContext, useEffect, useRef } from "react";
import { useNavigate } from "react-router-dom";

import {
  Box,
  Typography,
  Button,
  Divider,
  Paper,
  IconButton,
  Tooltip,
} from "@mui/material";

import ModeEditOutlineOutlinedIcon from "@mui/icons-material/ModeEditOutlineOutlined";
import EditOffOutlinedIcon from "@mui/icons-material/EditOffOutlined";
import AddCircleOutlineOutlinedIcon from "@mui/icons-material/AddCircleOutlineOutlined";
import CloseIcon from "@mui/icons-material/Close";
import DownloadIcon from "@mui/icons-material/Download";
import MicNoneOutlinedIcon from "@mui/icons-material/MicNoneOutlined";
import MicOutlinedIcon from "@mui/icons-material/MicOutlined";

import BeneficiosDetalheDemanda from "../../components/BeneficiosDetalheDemanda/BeneficiosDetalheDemanda";
import ModalConfirmacao from "../../components/ModalConfirmacao/ModalConfirmacao";
import ModalAceitarDemanda from "../../components/ModalAceitarDemanda/ModalAceitarDemanda";
import ModalRecusarDemanda from "../ModalRecusarDemanda/ModalRecusarDemanda";
import Feedback from "../Feedback/Feedback";
import CaixaTextoQuill from "../CaixaTextoQuill/CaixaTextoQuill";

import TextLanguageContext from "../../service/TextLanguageContext";
import ColorModeContext from "../../service/TemaContext";
import BeneficioService from "../../service/beneficioService";
import DemandaService from "../../service/demandaService";
import AnexoService from "../../service/anexoService";
import NotificacaoService from "../../service/notificacaoService";
import ExportPdfService from "../../service/exportPdfService";
import FontContext from "../../service/FontContext";
import CookieService from "../../service/cookieService";

// Componente para mostrar os detalhes de uma demanda e suas respectivas funções
const DetalhesDemanda = (props) => {
  // Contexto para trocar a linguagem
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  // Navigate utilizado para navegar para outras páginas
  const navigate = useNavigate();

  // Variável hexadecimal para alterar a cor de certos componentes ao mudar o tema da página
  const [corFundoTextArea, setCorFundoTextArea] = useState("#FFFF");

  // Variável armazenando o tema da página (light / dark)
  const { temaPagina } = useContext(ColorModeContext);
  const inputFile = useRef(null);

  // UseStates para armazenar os dados de demanda
  const [tituloDemanda, setTituloDemanda] = useState(props.dados.titulo);
  const [problema, setProblema] = useState(props.dados.problema);
  const [proposta, setProposta] = useState(props.dados.proposta);
  const [frequencia, setFrequencia] = useState(props.dados.frequencia);
  const [beneficios, setBeneficios] = useState(null);
  const [beneficiosNovos, setBeneficiosNovos] = useState([]);
  const [beneficiosExcluidos, setBeneficiosExcluidos] = useState([]);
  const [anexosDemanda, setAnexosDemanda] = useState(props.dados.anexo);

  // Verificar se a demanda está em modo de edição
  const [demandaEmEdicao, setDemandaEmEdicao] = useState(false);

  // UseState do modal de aceitar demanda
  const [openModalAceitarDemanda, setOpenModalAceitarDemanda] = useState(false);
  const [openModalRecusa, setOpenModalRecusa] = useState(false);

  // modal para a confirmação da aprovação da demanda
  const [modalAprovarDemanda, setModalAprovarDemanda] = useState(false);

  // UseState para armazenar o motivo da recusa da demanda
  const [motivoRecusaDemanda, setMotivoRecusaDemanda] = useState("");

  // UseState para exibir o modal de recusa da demanda
  const [modoModalRecusa, setModoModalRecusa] = useState("recusa");

  // UseState para permitir edição na demanda
  const [editar, setEditar] = useState(false);

  // UseState para exibir o modal de confirmação de edição da demanda
  const [openModal, setOpenModal] = useState(false);

  // Feedback caso o usuário coloque um nome de anexo com mesmo nome de outro anexo
  const [feedbackComAnexoMesmoNome, setFeedbackComAnexoMesmoNome] =
    useState(false);

  // Feedback caso o usuário tente salvar a demanda sem ter feito nenhuma alteração
  const [feedbackFacaAlteracao, setFeedbackFacaAlteracao] = useState(false);

  // UseEffect para atualizar a variável "corFundoTextArea" quando o tema da página for modificado
  useEffect(() => {
    temaPagina === "dark"
      ? setCorFundoTextArea("#212121")
      : setCorFundoTextArea("#FFFFFF");
  }, [temaPagina]);

  // UseEffect para atualizar os dados da demanda quando o componente for montado
  useEffect(() => {
    setTituloDemanda(props.dados.titulo);
    setProblema(props.dados.problema);
    setProposta(props.dados.proposta);
    setFrequencia(props.dados.frequencia);
    setBeneficios(formatarBeneficios(props.dados.beneficios));
    setAnexosDemanda(props.dados.anexo);
  }, []);

  // ----------------------------------------------------------------------------------------------------------------------------
  // Funções de edição da demanda

  // UseEffect para setar a variável "editar" para false quando o usuário sair da página de edição da demanda
  useEffect(() => {
    if (!props.edicao) setEditar(false);
  }, [props.edicao]);

  // Função para editar a demanda
  function editarDemanda() {
    if (editar) {
      setOpenModal(true);
    } else {
      setEditar(true);
      if (props.setEdicao) {
        props.setEdicao(true);
      }
    }
  }

  const [visualizarTexto, setVisualizarTexto] = useState();

  // Função de cancelamento da edição da demanda, retornando os dados ao salvamento anterior
  function resetarTextoInput() {
    excluirBeneficiosAdicionados();
    excluirAnexosAdicionados();
    setBeneficios(formatarBeneficios(props.dados.beneficios));
    setBeneficiosExcluidos([]);
    setVisualizarTexto(true);
    setEditar(false);
    setTituloDemanda(props.dados.titulo);
    setProblema(props.dados.problema);
    setProposta(props.dados.proposta);
    setFrequencia(props.dados.frequencia);
    setAnexosDemanda(props.dados.anexo);
  }

  // Função para formatar os benefícios recebidos do banco para a lista de benefícios na página
  const formatarBeneficios = (listaBeneficios) => {
    const aux = listaBeneficios.map((beneficio) => {
      return {
        id: beneficio.id,
        tipoBeneficio:
          beneficio.tipoBeneficio?.charAt(0) +
          beneficio.tipoBeneficio
            ?.substring(1, beneficio.tipoBeneficio?.length)
            ?.toLowerCase() || texts.DetalhesDemanda.real,
        valor_mensal: beneficio.valor_mensal,
        moeda: beneficio.moeda,
        memoriaCalculo: beneficio.memoriaCalculo,
        visible: true
      };
    });
    return aux;
  };

  // Função para formatar os benefícios do front-end para enviá-los ao salvamento da demanda
  const formatarBeneficiosRequisicao = (listaBeneficios) => {
    let listaNova = [];
    for (let beneficio of listaBeneficios) {
      if (beneficio.visible) {
        listaNova.push({
          id: beneficio.id,
          memoriaCalculo: beneficio.memoriaCalculo,
          moeda: beneficio.moeda,
          valor_mensal: beneficio.valor_mensal,
          tipoBeneficio: beneficio.tipoBeneficio.toUpperCase(),
        });
      }
    }
    return listaNova;
  };

  // Função para alterar o texto de algum dos campos da demanda
  const alterarTexto = (e, input) => {
    if (input === "titulo") {
      setTituloDemanda(e.target.value);
    } else if (input === "problema") {
      setProblema(e);
    } else if (input === "proposta") {
      setProposta(e);
    } else if (input === "frequencia") {
      setFrequencia(e.target.value);
    }
  };

  // Aciona o input de anexos ao clicar no add anexos
  const onAddAnexoButtonClick = () => {
    inputFile.current.click();
  };

  // Coloca o arquivo selecionado no input no state de anexos
  const onFilesSelect = () => {
    for (let file of inputFile.current.files) {
      AnexoService.save(file).then((response) => {
        setNovosAnexos([...novosAnexos, response]);
        setAnexosDemanda([...anexosDemanda, response]);
      });
    }
  };

  /** Função para procurar dentro de uma lista se um objeto com determinado ID está presente */
  const estaPresente = (idObjeto, listaProcurada) => {
    for (let objeto of listaProcurada) {
      if (objeto.id == idObjeto) {
        return true;
      }
    }
    return false;
  };

  // Função para remover um anexo da lista de anexos
  const removerAnexo = (index) => {
    if (estaPresente(anexosDemanda[index].id, novosAnexos)) {
      removeAnexosNovos(anexosDemanda[index]);
      AnexoService.deleteById(anexosDemanda[index].id).then((response) => { });
    } else {
      setAnexosRemovidos([...anexosRemovidos, anexosDemanda[index]]);
    }
    let aux = [...anexosDemanda];
    aux.splice(index, 1);
    setAnexosDemanda(aux);
  };

  // Função que cria um benefício no banco e usa o id nele em um objeto novo na lista da página
  const adicionarBeneficio = () => {
    BeneficioService.post().then((response) => {
      setBeneficiosNovos([
        ...beneficiosNovos,
        { ...response, tipoBeneficio: "", moeda: "", visible: true },
      ]);
      setBeneficios([
        ...beneficios,
        { ...response, tipoBeneficio: "", moeda: "", visible: true },
      ]);
    });
  };

  // Função para atualizar o benefício quando ele recebe alguma alteração
  const alterarTextoBeneficio = (beneficio, index) => {
    let aux = beneficios.map((beneficio) => {
      return {
        id: beneficio.id,
        tipoBeneficio: beneficio.tipoBeneficio,
        valor_mensal: beneficio.valor_mensal,
        moeda: beneficio.moeda,
        memoriaCalculo: beneficio.memoriaCalculo,
        visible: true
      };
    });
    aux[index] = beneficio;
    setBeneficios(aux);
  };

  // Função para excluir um benefício da lista
  const deleteBeneficio = (indexBeneficio) => {
    let listaNova = [];
    for (let contagem = 0; contagem < beneficios.length; contagem++) {
      if (contagem != indexBeneficio) {
        listaNova.push({ ...beneficios[contagem] });
      } else {
        listaNova.push({ ...beneficios[contagem], visible: false });
        setBeneficiosExcluidos([...beneficiosExcluidos, beneficios[contagem]]);
      }
    }
    setBeneficios(listaNova);
  };

  // Função para excluir os benefícios que foram criados no banco, porém excluídos da demanda
  const excluirBeneficiosRemovidos = () => {
    for (let beneficio of beneficiosExcluidos) {
      BeneficioService.delete(beneficio.id).then(() => { });
    }
    setBeneficiosExcluidos([]);
  };

  // Função para excluir todos os benefícios adicionados em uma edição caso ela seja cancelada
  const excluirBeneficiosAdicionados = () => {
    for (let beneficio of beneficiosNovos) {
      BeneficioService.delete(beneficio.id).then(() => { });
    }
    setBeneficiosNovos([]);
  };

  /** Função para excluir todos os anexos adicionados numa edição se essa mesma edição for cancelada */
  const excluirAnexosAdicionados = () => {
    for (let anexo of novosAnexos) {
      AnexoService.deleteById(anexo.id).then(() => { });
    }
    setNovosAnexos([]);
  };

  /** Função para formatar o HTML em casos como a falta de fechamentos em tags "<br>" */
  const formatarHtml = (texto) => {
    texto = texto.replace(/<br>/g, "<br/>");
    return texto;
  };

  // Função inicial da edição da demanda, atualizando os benefícios dela
  const salvarEdicao = () => {
    if (!podeSalvar()) {
      setFeedbackFacaAlteracao(true);
      return null;
    }

    let listaBeneficiosFinal = formatarBeneficiosRequisicao(beneficios);
    let contagem = 0;

    if (listaBeneficiosFinal.length > 0) {
      for (let beneficio of listaBeneficiosFinal) {
        BeneficioService.put(beneficio, beneficio.memoriaCalculo).then(
          (response) => { }
        );
        contagem++;

        if (contagem == listaBeneficiosFinal.length) {
          setDemandaEmEdicao(true);
        }
      }
    } else {
      setDemandaEmEdicao(true);
    }
  };

  // Função que verifica se o usuário adicionou algum benefício
  const checkIfBeneficiosChanged = () => {
    // Se foi Adicionado um novo benefício
    if (beneficiosNovos.length > 0 || beneficiosExcluidos.length > 0)
      return true;

    return !beneficios.every((e, index) => {
      return (
        e.tipoBeneficio.toLowerCase() ==
        props.dados.beneficios[index].tipoBeneficio.toLowerCase() &&
        e.valor_mensal == props.dados.beneficios[index].valor_mensal &&
        e.moeda.toLowerCase() ==
        props.dados.beneficios[index].moeda.toLowerCase() &&
        e.memoriaCalculo.toLowerCase() ==
        props.dados.beneficios[index].memoriaCalculo.toLowerCase()
      );
    });
  };

  /** Função que determina se o usuário pode salvar a demanda ou não, se baseando se ele editou alguma coisa */
  const podeSalvar = () => {
    return (
      checkIfBeneficiosChanged() ||
      novosAnexos.length > 0 ||
      anexosRemovidos.length > 0 ||
      tituloDemanda != props.dados.titulo ||
      problema != props.dados.problema ||
      proposta != props.dados.proposta ||
      frequencia != props.dados.frequencia
    );
  };

  // UseEffect ativado quando os benefícios da demanda são atualizados no banco, salvando os outros dados da demanda
  useEffect(() => {
    if (demandaEmEdicao) {
      const demandaAtualizada = {
        id: props.dados.id,
        titulo: tituloDemanda,
        problema: btoa(formatarHtml(problema)),
        proposta: btoa(formatarHtml(proposta)),
        frequencia: frequencia,
        beneficios: retornarIdsObjetos(beneficios),
        data: props.dados.data,
        status: "BACKLOG_REVISAO",
        solicitante: props.dados.solicitante,
        gerente: props.dados.gerente,
        anexo: retornarIdsObjetos(anexosDemanda),
        departamento: props.dados?.departamento,
        analista: props.dados?.analista,
        historicoDemanda: props.dados?.historicoDemanda,
      };

      DemandaService.put(demandaAtualizada).then((response) => {
        setEditar(false);
        excluirBeneficiosRemovidos();
        setDemandaEmEdicao(false);
        props.updateDemandaProps(response);
        salvarHistorico("Demanda Editada");
      });

      if (anexosRemovidos.length > 0) {
        for (let anexoRemovido of anexosRemovidos) {
          AnexoService.deleteById(anexoRemovido.id);
        }
      }
      setAnexosRemovidos([]);
    }
  }, [demandaEmEdicao]);

  // Const para retornar uma lista das BUs beneficiadas em forma de texto
  const retornarBUsBeneficiadas = () => {
    let textoFinal = "";
    for (const bu of props.dados.busBeneficiadas) {
      textoFinal += bu.siglaBu + ", ";
    }
    textoFinal = textoFinal.substring(0, textoFinal.length - 2);
    return textoFinal;
  };

  // -----------------------------------------------------------------------------------------------------------------------------------
  // Funções de aceite/recusa do analista/gerente

  // Função para fechar o modal de confirmação
  const handleCloseModalAceitarDemanda = () => {
    setOpenModalAceitarDemanda(false);
  };

  // Função para fechar o modal de recusa
  const handleCloseModalRecusar = () => {
    setMotivoRecusaDemanda("");
    setOpenModalRecusa(false);
  };

  // Acionado quando o usuário clicar em "Aceitar" na demanda
  const aceitarDemanda = () => {
    if (!props.lendo) {
      setOpenModalAceitarDemanda(true);
    } else {
      lerTexto(texts.DetalhesDemanda.botaoAceitar);
    }
  };

  // Acionado quando o usuário clicar em "Aprovar" na demanda
  const aprovarDemanda = () => {
    if (!props.lendo) {
      setModalAprovarDemanda(true);
    } else {
      lerTexto(texts.DetalhesDemanda.botaoAceitar);
    }
  };

  // Função para abrir o modal de recusa
  const abrirRecusaDemanda = (modo) => {
    setModoModalRecusa(modo);
    setOpenModalRecusa(true);
  };

  /** Função para aceitar a demanda como gerente, enviando-a para a criação de proposta */
  const aprovarDemandaGerencia = () => {
    DemandaService.atualizarStatus(props.dados.id, "ASSESSMENT").then(() => {
      salvarHistorico("Demanda Aprovada");
      navegarHome(1);
    });
  };

  // Função acionada quando o usuário clica em "Aceitar" no modal de confirmação
  const confirmAceitarDemanda = (dados) => {
    const demandaAtualizada = {
      ...props.dados,
      problema: btoa(props.dados.problema),
      proposta: btoa(props.dados.proposta),
      anexo: [
        ...retornarIdsObjetos(dados.anexos),
        ...retornarIdsObjetos(props.dados.anexo),
      ],
      beneficios: retornarIdsObjetos(props.dados.beneficios),
      status: "BACKLOG_APROVACAO",
      tamanho: dados.tamanho,
      secaoTI: dados.secaoTI,
      busBeneficiadas: dados.busBeneficiadas,
      buSolicitante: dados.buSolicitante,
      forum: dados.forum,
      analista: props.usuario,
    };

    DemandaService.put(demandaAtualizada).then(() => {
      salvarHistorico("Demanda Aprovada");
      navegarHome(1);
    });
    NotificacaoService.post(
      NotificacaoService.createNotificationObject(
        NotificacaoService.aprovado,
        props.dados
      )
    );
  };

  /** Função para formatar uma lista de objetos, retornando somente o id de cada objeto presente, com a lista sendo recebida como parâmetro */
  const retornarIdsObjetos = (listaObjetos) => {
    let listaNova = [];
    for (let objeto of listaObjetos) {
      listaNova.push({ id: objeto.id });
    }
    return listaNova;
  };

  /** Função para formatar uma demanda para envio para edição da mesma */
  const formatarDemanda = () => {
    let demanda = {
      ...props.dados,
      problema: btoa(props.dados.problema),
      proposta: btoa(props.dados.proposta),
      beneficios: retornarIdsObjetos(props.dados.beneficios),
      anexo: retornarIdsObjetos(props.dados.anexo),
    };
    return demanda;
  };

  /** Função para criar e retornar um objeto de histórico para salvamento */
  const retornaObjetoHistorico = (acaoRealizada) => {
    const historico = {
      data: new Date(),
      acaoRealizada: acaoRealizada,
      autor: { id: CookieService.getUser().id },
    };
    return historico;
  };

  /** Função usada para atualizar o histórico da demanda quando ela for atualizada no banco, recebendo um texto da ação realizada */
  const salvarHistorico = (texto) => {
    ExportPdfService.exportDemanda(props.dados.id).then((file) => {
      let arquivo = new Blob([file], { type: "application/pdf" });
      DemandaService.addHistorico(
        props.dados.id,
        retornaObjetoHistorico(texto),
        arquivo
      );
    });
  };

  /** Função acionada quando o analista recusa uma demanda, tanto devolução quanto reprovação */
  const confirmRecusaDemanda = () => {
    if (!motivoRecusaDemanda) return;
    setOpenModalRecusa(false);
    const status =
      modoModalRecusa === "devolucao" ? "BACKLOG_EDICAO" : "CANCELLED";

    DemandaService.put({
      ...formatarDemanda(),
      motivoRecusa: motivoRecusaDemanda,
      status: status,
    }).then(() => {
      const tipoNotificacao =
        modoModalRecusa === "devolucao"
          ? NotificacaoService.maisInformacoes
          : NotificacaoService.reprovado;
      NotificacaoService.post(
        NotificacaoService.createNotificationObject(
          tipoNotificacao,
          props.dados
        )
      );
      salvarHistorico(
        modoModalRecusa === "devolucao"
          ? "Demanda Devolvida"
          : "Demanda Reprovada"
      );
      navegarHome(modoModalRecusa === "devolucao" ? 2 : 3);
    });
  };

  /** Função para transformar uma string em base64 para um ArrayBuffer, usada para baixar anexos */
  function converterBase64(base64) {
    const textoBinario = window.atob(base64).toString("utf-8");
    const bytes = new Uint8Array(textoBinario.length);
    return bytes.map((byte, i) => textoBinario.charCodeAt(i));
  }

  /** Função usada para baixar um anexo */
  const baixarAnexo = (index) => {
    const arquivo = anexosDemanda[index];
    let blob =
      arquivo instanceof File
        ? arquivo
        : new Blob([converterBase64(arquivo.dados)]);
    let nomeArquivo =
      arquivo instanceof File ? arquivo.name : `${arquivo.nome}`;

    if (navigator.msSaveBlob) {
      navigator.msSaveBlob(blob, nomeArquivo);
    } else {
      let link = document.createElement("a");
      if (link.download !== undefined) {
        const url = URL.createObjectURL(blob);
        link.setAttribute("href", url);
        link.setAttribute("download", nomeArquivo);
        link.style.visibility = "hidden";
        document.body.appendChild(link);
        link.click();

        setTimeout(() => {
          document.body.removeChild(link);
          URL.revokeObjectURL(url);
        }, 1000);
      }
    }
  };

  /** Novos anexos que serão adicionados na edição da demanda */
  const [novosAnexos, setNovosAnexos] = useState([]);

  /** Anexos que já estavam na demanda e que serão removidos na edição da demanda */
  const [anexosRemovidos, setAnexosRemovidos] = useState([]);

  /** Função que remove um anexo da lista anexosNovos caso o usuário o remova, 
  só é removido anexos que não estavam salvos no banco de dados */
  const removeAnexosNovos = (anexo) => {
    setNovosAnexos(
      novosAnexos.filter((anexoItem) => {
        return anexoItem.id != anexo.id;
      })
    );
  };

  /** Função usada para navegar à home salvando um valor de feedback no localStorage */
  const navegarHome = (tipoFeedback) => {
    switch (tipoFeedback) {
      case 1:
        localStorage.setItem("tipoFeedback", "2");
        break;
      case 2:
        localStorage.setItem("tipoFeedback", "3");
        break;
      case 3:
        localStorage.setItem("tipoFeedback", "4");
        break;
    }
    navigate("/");
  };

  // Variáveis utilizadas para armazenar o valor html do campo
  const problemaDaDemanda = useRef(null);
  const propostaDaDemanda = useRef(null);

  // useEffect utilizado para atualizar o valor html do campo
  useEffect(() => {
    if (problemaDaDemanda.current) {
      problemaDaDemanda.current.innerHTML = props.dados.problema;
    }
    if (propostaDaDemanda.current) {
      propostaDaDemanda.current.innerHTML = props.dados.proposta;
    }
  }, [props, visualizarTexto, editar]);

  // Função utilizada para formatar o texto do problema
  const getProblemaFomartted = (problema) => {
    return problema[0].toUpperCase() + problema.substring(1).toLowerCase();
  };

  // Função utilizada para formatar o texto da proposta
  const getPropostaFomartted = (proposta) => {
    return proposta[0].toUpperCase() + proposta.substring(1).toLowerCase();
  };

  // ********************************************** Gravar audio **********************************************

  const [
    feedbackErroNavegadorIncompativel,
    setFeedbackErroNavegadorIncompativel,
  ] = useState(false);
  const [feedbackErroReconhecimentoVoz, setFeedbackErroReconhecimentoVoz] =
    useState(false);

  const recognitionRef = useRef(null);

  const [escutar, setEscutar] = useState(false);

  const [localClique, setLocalClique] = useState("");

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
        switch (localClique) {
          case "tituloDemanda":
            setTituloDemanda(transcript);
            break;
          case "frequenciaUso":
            setFrequencia(transcript);
            break;
          default:
            break;
        }
      };

      recognition.onerror = (event) => {
        setFeedbackErroReconhecimentoVoz(true);
        setEscutar(false);
      };

      recognitionRef.current = recognition;
      recognition.start();
    } else {
      setFeedbackErroNavegadorIncompativel(true);
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
    setLocalClique(ondeClicou);
  };

  useEffect(() => {
    if (escutar) {
      ouvirAudio();
    } else {
      stopRecognition();
    }
  }, [escutar]);

  // ********************************************** Fim Gravar audio **********************************************

  const [textoLeitura,setTextoLeitura] = useState("");

  // Função que irá setar o texto que será "lido" pela a API
  const lerTexto = (escrita) => {
    if (props.lendo) {
      setTextoLeitura(escrita);
    }
  };

  // Função que irá "ouvir" o texto que será "lido" pela a API
  useEffect(() => {
    const synthesis = window.speechSynthesis;
    const utterance = new SpeechSynthesisUtterance(textoLeitura);

    const finalizarLeitura = () => {
      if ("speechSynthesis" in window) {
        synthesis.cancel();
      }
    };

    if (props.lendo && textoLeitura !== "") {
      if ("speechSynthesis" in window) {
        synthesis.speak(utterance);
      }
    } else {
      finalizarLeitura();
    }

    return () => {
      finalizarLeitura();
    };
  }, [textoLeitura]);

  return (
    <Box className="flex flex-col justify-center relative items-center mt-10 mb-16">
      {/* Feedback Erro reconhecimento de voz */}
      <Feedback
        open={feedbackErroReconhecimentoVoz}
        handleClose={() => {
          setFeedbackErroReconhecimentoVoz(false);
        }}
        status={"erro"}
        mensagem={texts.homeGerencia.feedback.feedback12}
        lendo={props.lendo}
        texto={props.texto}
        setTexto={props.setTexto}
      />
      {/* Feedback Não navegador incompativel */}
      <Feedback
        open={feedbackErroNavegadorIncompativel}
        handleClose={() => {
          setFeedbackErroNavegadorIncompativel(false);
        }}
        status={"erro"}
        mensagem={texts.homeGerencia.feedback.feedback13}
        lendo={props.lendo}
        texto={props.texto}
        setTexto={props.setTexto}
      />
      <Feedback
        open={feedbackFacaAlteracao}
        handleClose={() => setFeedbackFacaAlteracao(false)}
        status={"erro"}
        mensagem={texts.DetalhesDemanda.facaAlgumaAlteracaoParaPoderSalvar}
        lendo={props.lendo}
        texto={props.texto}
        setTexto={props.setTexto}
      />
      <Feedback
        open={feedbackComAnexoMesmoNome}
        handleClose={() => setFeedbackComAnexoMesmoNome(false)}
        status={"erro"}
        mensagem={texts.DetalhesDemanda.jaHaUmAnexoComEsseNome}
        lendo={props.lendo}
        texto={props.texto}
        setTexto={props.setTexto}
      />
      <ModalAceitarDemanda
        open={openModalAceitarDemanda}
        setOpen={setOpenModalAceitarDemanda}
        handleClose={handleCloseModalAceitarDemanda}
        confirmAceitarDemanda={confirmAceitarDemanda}
        lendo={props.lendo}
        texto={props.texto}
        setTexto={props.setTexto}
      />
      <ModalRecusarDemanda
        open={openModalRecusa}
        setOpen={setOpenModalRecusa}
        handleClose={handleCloseModalRecusar}
        confirmRecusarDemanda={confirmRecusaDemanda}
        motivo={motivoRecusaDemanda}
        setMotivo={setMotivoRecusaDemanda}
        setFeedbackErroNavegadorIncompativel={
          setFeedbackErroNavegadorIncompativel
        }
        setFeedbackErroReconhecimentoVoz={setFeedbackErroReconhecimentoVoz}
        lendo={props.lendo}
        texto={props.texto}
        setTexto={props.setTexto}
      />
      <ModalConfirmacao
        open={openModal}
        setOpen={setOpenModal}
        onConfirmClick={resetarTextoInput}
        onCancelClick={setEditar}
        textoModal="cancelarEdicao"
        textoBotao="sim"
        atualizarTexto={true}
        lendo={props.lendo}
        texto={props.texto}
        setTexto={props.setTexto}
      />
      <ModalConfirmacao
        open={modalAprovarDemanda}
        setOpen={setModalAprovarDemanda}
        onConfirmClick={aprovarDemandaGerencia}
        textoModal="aceitarDemanda"
        textoBotao="aceitar"
        lendo={props.lendo}
        texto={props.texto}
        setTexto={props.setTexto}
      />
      <Box
        id="primeiro"
        className="flex flex-col gap-5 border rounded relative p-10 drop-shadow-lg"
        sx={{ width: "55rem" }}
      >
        <Box
          className="absolute cursor-pointer"
          sx={{ top: "10px", right: "10px" }}
          onClick={editarDemanda}
        >
          {props.usuario?.id == props.dados.solicitante?.id &&
            props.dados.status == "BACKLOG_EDICAO" &&
            !editar ? (
            <ModeEditOutlineOutlinedIcon
              id="terceiro"
              fontSize="large"
              className="delay-120 hover:scale-110 duration-300"
              sx={{ color: "icon.main" }}
            />
          ) : null}
          {props.usuario?.id == props.dados.solicitante?.id &&
            props.dados.status == "BACKLOG_EDICAO" &&
            editar ? (
            <EditOffOutlinedIcon
              fontSize="large"
              className="delay-120 hover:scale-110 duration-300"
              sx={{ color: "icon.main" }}
            />
          ) : null}
        </Box>
        {!editar ? (
          <>
            <Box className="flex justify-center">
              <Typography
                fontSize={FontConfig.title}
                sx={{
                  fontWeight: "600",
                  cursor: "default",
                  inlineSize: "800px",
                  overflowWrap: "break-word",
                }}
                color="primary.main"
                onClick={() => {
                  lerTexto(props.dados.titulo);
                }}
              >
                {props.dados.titulo}
              </Typography>
            </Box>
            <Divider />

            <Box>
              <Typography
                fontSize={FontConfig.veryBig}
                fontWeight="600"
                color="text.primary"
                onClick={() => {
                  lerTexto(texts.DetalhesDemanda.problem);
                }}
              >
                {texts.DetalhesDemanda.problema}:
              </Typography>

              <Typography
                fontSize={FontConfig.medium}
                className="text-justify"
                color="text.secondary"
                sx={{ marginLeft: "30px" }}
                ref={problemaDaDemanda}
                onClick={() => {
                  lerTexto(getProblemaFomartted(props.dados.problema));
                }}
              >
                {getProblemaFomartted(props.dados.problema)}
              </Typography>
            </Box>

            <Box>
              <Typography
                fontSize={FontConfig.veryBig}
                fontWeight="600"
                color="text.primary"
                onClick={() => {
                  lerTexto(texts.DetalhesDemanda.proposal);
                }}
              >
                {texts.DetalhesDemanda.proposta}:
              </Typography>
              <Typography
                fontSize={FontConfig.medium}
                className="text-justify"
                color="text.secondary"
                sx={{ marginLeft: "30px" }}
                ref={propostaDaDemanda}
                onClick={() => {
                  lerTexto(getPropostaFomartted(props.dados.proposta));
                }}
              >
                {getPropostaFomartted(props.dados.proposta)}
              </Typography>
            </Box>
            <Box>
              <Box>
                <Typography
                  fontSize={FontConfig.veryBig}
                  fontWeight="600"
                  color="text.primary"
                  onClick={() => {
                    lerTexto(texts.DetalhesDemanda.beneficios);
                  }}
                >
                  {texts.DetalhesDemanda.beneficios}:
                </Typography>
              </Box>
              <Box className="mt-2 flex flex-col gap-5">
                {beneficios?.map((beneficio, index) => {
                  return (
                    <BeneficiosDetalheDemanda
                      editavel={false}
                      key={index}
                      index={index}
                      beneficio={beneficio}
                      setFeedbackErroNavegadorIncompativel={
                        setFeedbackErroNavegadorIncompativel
                      }
                      setFeedbackErroReconhecimentoVoz={
                        setFeedbackErroReconhecimentoVoz
                      }
                      lendo={props.lendo}
                      texto={props.texto}
                      setTexto={props.setTexto}
                    />
                  );
                })}
              </Box>
            </Box>
            <Box>
              <Typography
                fontSize={FontConfig.veryBig}
                fontWeight="600"
                color="text.primary"
                onClick={() => {
                  lerTexto(texts.DetalhesDemanda.frequenciaDeUso);
                }}
              >
                {texts.DetalhesDemanda.frequenciaDeUso}:
              </Typography>
              <Typography
                fontSize={FontConfig.medium}
                className="text-justify"
                color="text.secondary"
                sx={{ marginLeft: "30px" }}
                onClick={() => {
                  lerTexto(props.dados.frequencia);
                }}
              >
                {props.dados.frequencia}
              </Typography>
            </Box>
            {props.dados.tamanho && props.dados.secaoTI && (
              <Box className="flex justify-between items-center">
                <Box className="flex items-center">
                  <Typography
                    fontSize={FontConfig.veryBig}
                    fontWeight="600"
                    color="text.primary"
                    onClick={() => {
                      lerTexto(texts.DetalhesDemanda.tamanho);
                    }}
                  >
                    {texts.DetalhesDemanda.tamanho}:
                  </Typography>
                  <Typography
                    fontSize={FontConfig.medium}
                    className="text-justify"
                    color="text.secondary"
                    sx={{ marginLeft: "10px" }}
                    onClick={() => {
                      lerTexto(props.dados.tamanho);
                    }}
                  >
                    {props.dados.tamanho}
                  </Typography>
                </Box>
                <Box className="flex items-center">
                  <Typography
                    fontSize={FontConfig.veryBig}
                    fontWeight="600"
                    color="text.primary"
                    onClick={() => {
                      lerTexto(texts.DetalhesDemanda.secaoDeTi);
                    }}
                  >
                    {texts.DetalhesDemanda.secaoDeTi}:
                  </Typography>
                  <Typography
                    fontSize={FontConfig.medium}
                    className="text-justify"
                    color="text.secondary"
                    sx={{ marginLeft: "10px", marginRight: "15px" }}
                    onClick={() => {
                      lerTexto(props.dados.secaoTI.siglaSecao);
                    }}
                  >
                    {props.dados.secaoTI.siglaSecao}
                  </Typography>
                </Box>
              </Box>
            )}
            {props.dados.buSolicitante && (
              <Box className="flex justify-between items-center">
                <Box className="flex items-center">
                  <Typography
                    fontSize={FontConfig.veryBig}
                    fontWeight="600"
                    color="text.primary"
                    onClick={() => {
                      lerTexto(texts.DetalhesDemanda.buSolicitante);
                    }}
                  >
                    {texts.DetalhesDemanda.buSolicitante}:
                  </Typography>
                  <Typography
                    fontSize={FontConfig.medium}
                    className="text-justify"
                    color="text.secondary"
                    sx={{ marginLeft: "10px" }}
                    onClick={() => {
                      lerTexto(props.dados.buSolicitante.siglaBu);
                    }}
                  >
                    {props.dados.buSolicitante.siglaBu}
                  </Typography>
                </Box>
                <Box className="flex items-center">
                  <Typography
                    fontSize={FontConfig.veryBig}
                    fontWeight="600"
                    color="text.primary"
                    onClick={() => {
                      lerTexto(texts.DetalhesDemanda.buBeneficiadas);
                    }}
                  >
                    {texts.DetalhesDemanda.busBeneficiadas}:
                  </Typography>
                  <Typography
                    fontSize={FontConfig.medium}
                    className="text-justify"
                    color="text.secondary"
                    sx={{ marginLeft: "10px", marginRight: "15px" }}
                    onClick={() => {
                      lerTexto(retornarBUsBeneficiadas());
                    }}
                  >
                    {retornarBUsBeneficiadas()}
                  </Typography>
                </Box>
              </Box>
            )}
            {props.dados.forum && (
              <Box className="flex items-center">
                <Typography
                  fontSize={FontConfig.veryBig}
                  fontWeight="600"
                  color="text.primary"
                  onClick={() => {
                    lerTexto(texts.DetalhesDemanda.forum);
                  }}
                >
                  {texts.DetalhesDemanda.forum}:
                </Typography>
                <Typography
                  fontSize={FontConfig.medium}
                  className="text-justify"
                  color="text.secondary"
                  sx={{ marginLeft: "10px" }}
                  onClick={() => {
                    lerTexto(props.dados.forum.nomeForum);
                  }}
                >
                  {props.dados.forum.siglaForum} - {props.dados.forum.nomeForum}
                </Typography>
              </Box>
            )}
            <Box>
              <Typography
                fontSize={FontConfig.veryBig}
                fontWeight="600"
                color="text.primary"
                onClick={() => {
                  lerTexto(texts.DetalhesDemanda.anexos);
                }}
              >
                {texts.DetalhesDemanda.anexos}:
              </Typography>
              {props.dados.anexo != null && props.dados.anexo.length > 0 ? (
                <Box className="flex flex-col gap-2">
                  {props.dados.anexo.map((anexo, index) => (
                    <Paper
                      key={index}
                      className="flex justify-between items-center"
                      sx={{
                        borderLeftWidth: "4px",
                        borderLeftColor: "primary.main",
                        borderLeftStyle: "solid",
                        backgroundColor: "background.default",
                        padding: "0.2rem 1rem",
                      }}
                      elevation={0}
                    >
                      <Typography
                        sx={{
                          color: "text.primary",
                          fontSize: FontConfig.default,
                        }}
                        onClick={() => {
                          if (anexo.nome) {
                            lerTexto(anexo.nome);
                          } else {
                            lerTexto(anexo.name);
                          }
                        }}
                      >
                        {anexo.nome ? anexo.nome : anexo.name}
                      </Typography>
                      <Tooltip title={texts.DetalhesDemanda.baixar}>
                        <IconButton
                          onClick={() => {
                            baixarAnexo(index);
                          }}
                        >
                          <DownloadIcon sx={{ color: "text.primary" }} />
                        </IconButton>
                      </Tooltip>
                    </Paper>
                  ))}
                </Box>
              ) : (
                <Typography
                  textAlign="center"
                  sx={{ color: "text.primary", fontSize: FontConfig.default }}
                  onClick={() => {
                    lerTexto(texts.DetalhesDemanda.nenhumAnexoAdicionado);
                  }}
                >
                  {texts.DetalhesDemanda.nenhumAnexoAdicionado}
                </Typography>
              )}
            </Box>
          </>
        ) : (
          <>
            <Box className="flex justify-center items-center">
              <Box
                className="flex justify-between items-center w-full border-solid border px-1 drop-shadow-sm rounded mt-2"
                sx={{ backgroundColor: "background.default" }}
              >
                <Box
                  value={tituloDemanda}
                  onChange={(e) => {
                    alterarTexto(e, "titulo");
                  }}
                  fontSize={FontConfig.title}
                  color="primary.main"
                  className="flex outline-none"
                  sx={{
                    width: "95%;",
                    height: "54px",
                    backgroundColor: "transparent",
                    fontWeight: "600",
                  }}
                  component="input"
                  placeholder={texts.DetalhesDemanda.digiteTituloDaDemanda}
                />
                <Tooltip
                  className="hover:cursor-pointer"
                  title={texts.homeGerencia.gravarAudio}
                  onClick={() => {
                    startRecognition("tituloDemanda");
                  }}
                >
                  {escutar && localClique == "tituloDemanda" ? (
                    <MicOutlinedIcon
                      sx={{ color: "primary.main", fontSize: "2rem" }}
                    />
                  ) : (
                    <MicNoneOutlinedIcon
                      sx={{ color: "text.secondary", fontSize: "2rem" }}
                    />
                  )}
                </Tooltip>
              </Box>
            </Box>
            <Divider />
            <Box>
              <Typography
                fontSize={FontConfig.veryBig}
                fontWeight="600"
                color="text.primary"
                onClick={() => {
                  lerTexto(texts.DetalhesDemanda.problema);
                }}
              >
                {texts.DetalhesDemanda.problema}:
              </Typography>
              <CaixaTextoQuill
                texto={problema}
                setTexto={setProblema}
                onChange={(value) => {
                  alterarTexto(value, "problema");
                }}
                placeholder={texts.DetalhesDemanda.digiteProblema}
              />
            </Box>
            <Box>
              <Typography
                fontSize={FontConfig.veryBig}
                fontWeight="600"
                color="text.primary"
                onClick={() => {
                  lerTexto(texts.DetalhesDemanda.proposta);
                }}
              >
                {texts.DetalhesDemanda.proposta}:
              </Typography>
              <CaixaTextoQuill
                texto={proposta}
                setTexto={setProposta}
                onChange={(value) => {
                  alterarTexto(value, "proposta");
                }}
                placeholder={texts.DetalhesDemanda.digiteProposta}
              />
            </Box>
            <Box>
              <Box className="flex items-center">
                <Typography
                  fontSize={FontConfig.veryBig}
                  fontWeight="600"
                  color="text.primary"
                  onClick={() => {
                    lerTexto(texts.DetalhesDemanda.beneficios);
                  }}
                >
                  {texts.DetalhesDemanda.beneficios}:
                </Typography>
                <AddCircleOutlineOutlinedIcon
                  className="delay-120 hover:scale-110 duration-300 ml-1"
                  onClick={() => {
                    adicionarBeneficio();
                  }}
                  sx={{ color: "primary.main", cursor: "pointer" }}
                />
              </Box>
              <Box className="mt-2 flex flex-col gap-5">
                {beneficios?.map((beneficio, index) => {
                  if (beneficio.visible) {
                    return (
                      <BeneficiosDetalheDemanda
                        editavel={true}
                        key={index}
                        index={index}
                        delete={deleteBeneficio}
                        beneficio={beneficio}
                        setBeneficio={alterarTextoBeneficio}
                        setFeedbackErroNavegadorIncompativel={
                          setFeedbackErroNavegadorIncompativel
                        }
                        setFeedbackErroReconhecimentoVoz={
                          setFeedbackErroReconhecimentoVoz
                        }
                        lendo={props.lendo}
                        texto={props.texto}
                        setTexto={props.setTexto}
                      />
                    );
                  }
                })}
              </Box>
            </Box>
            <Box>
              <Typography
                fontSize={FontConfig.veryBig}
                fontWeight="600"
                color="text.primary"
                onClick={() => {
                  lerTexto(texts.DetalhesDemanda.frequenciaDeUso);
                }}
              >
                {texts.DetalhesDemanda.frequenciaDeUso}:
              </Typography>
              <Box
                className="flex items-center justify-between border-solid border px-2 py-1.5 drop-shadow-sm rounded"
                sx={{
                  backgroundColor: corFundoTextArea,
                  width: "90%",
                  marginLeft: "30px",
                }}
              >
                <Box
                  value={frequencia}
                  onChange={(e) => {
                    alterarTexto(e, "frequencia");
                  }}
                  fontSize={FontConfig.medium}
                  className="outline-none"
                  sx={{
                    width: "95%",
                    backgroundColor: "transparent",
                  }}
                  component="input"
                  placeholder={texts.DetalhesDemanda.digiteFrequenciaDeUso}
                />
                <Tooltip
                  className="hover:cursor-pointer"
                  title={texts.homeGerencia.gravarAudio}
                  onClick={() => {
                    startRecognition("frequenciaUso");
                  }}
                >
                  {escutar && localClique == "frequenciaUso" ? (
                    <MicOutlinedIcon
                      sx={{ color: "primary.main", fontSize: "1.8rem" }}
                    />
                  ) : (
                    <MicNoneOutlinedIcon
                      sx={{ color: "text.secondary", fontSize: "1.8rem" }}
                    />
                  )}
                </Tooltip>
              </Box>
            </Box>
            <Box>
              <Box className="flex items-center">
                <Typography
                  fontSize={FontConfig.veryBig}
                  fontWeight="600"
                  color="text.primary"
                  onClick={() => {
                    lerTexto(texts.DetalhesDemanda.anexos);
                  }}
                >
                  {texts.DetalhesDemanda.anexos}:
                </Typography>
                <AddCircleOutlineOutlinedIcon
                  className="delay-120 hover:scale-110 duration-300 ml-1"
                  sx={{ color: "primary.main", cursor: "pointer" }}
                  onClick={onAddAnexoButtonClick}
                />
                <input
                  onChange={onFilesSelect}
                  ref={inputFile}
                  type="file"
                  multiple
                  hidden
                />
              </Box>
              {anexosDemanda.length > 0 ? (
                <Box className="flex flex-col gap-2">
                  {anexosDemanda?.map((anexo, index) => (
                    <Paper
                      key={index}
                      className="flex justify-between items-center"
                      sx={{
                        borderLeftWidth: "4px",
                        borderLeftColor: "primary.main",
                        borderLeftStyle: "solid",
                        backgroundColor: "background.default",
                        padding: "0.2rem 1rem",
                      }}
                      elevation={0}
                    >
                      <Typography
                        sx={{
                          color: "text.primary",
                          fontSize: FontConfig.default,
                        }}
                        onClick={() => {
                          if (anexo.nome) {
                            lerTexto(anexo.nome);
                          } else {
                            lerTexto(anexo.name);
                          }
                        }}
                      >
                        {anexo.nome ? anexo.nome : anexo.name}
                      </Typography>
                      <Box className="flex gap-2">
                        <Tooltip title={texts.DetalhesDemanda.baixar}>
                          <IconButton
                            onClick={() => {
                              baixarAnexo(index);
                            }}
                          >
                            <DownloadIcon sx={{ color: "text.primary" }} />
                          </IconButton>
                        </Tooltip>
                        <Tooltip title={texts.DetalhesDemanda.remover}>
                          <IconButton
                            onClick={() => {
                              removerAnexo(index);
                            }}
                          >
                            <CloseIcon sx={{ color: "text.primary" }} />
                          </IconButton>
                        </Tooltip>
                      </Box>
                    </Paper>
                  ))}
                </Box>
              ) : (
                <Typography
                  textAlign="center"
                  sx={{ color: "text.primary", fontSize: FontConfig.default }}
                  onClick={() => {
                    lerTexto(texts.DetalhesDemanda.nenhumAnexoAdicionado);
                  }}
                >
                  {texts.DetalhesDemanda.nenhumAnexoAdicionado}
                </Typography>
              )}
            </Box>
          </>
        )}
      </Box>
      <Box
        className="flex fixed justify-end"
        sx={{ width: "15rem", bottom: "20px", right: "20px" }}
      >
        {props.usuario?.tipoUsuario == "ANALISTA" &&
          props.botao &&
          !editar &&
          props.dados.status == "BACKLOG_REVISAO" && (
            <Box className="flex justify-around w-full">
              <Button
                sx={{
                  backgroundColor: "primary.main",
                  color: "text.white",
                  fontSize: FontConfig.default,
                }}
                variant="contained"
                onClick={() => {
                  if (!props.lendo) {
                    abrirRecusaDemanda("recusa");
                  } else {
                    lerTexto(texts.DetalhesDemanda.botaoRecusar);
                  }
                }}
              >
                {texts.DetalhesDemanda.botaoRecusar}
              </Button>

              <Button
                sx={{
                  backgroundColor: "primary.main",
                  color: "text.white",
                  fontSize: FontConfig.default,
                }}
                variant="contained"
                onClick={() => {
                  if (!props.lendo) {
                    abrirRecusaDemanda("devolucao");
                  } else {
                    lerTexto(texts.DetalhesDemanda.botaoDevolver);
                  }
                }}
              >
                {texts.DetalhesDemanda.botaoDevolver}
              </Button>
              <Button
                sx={{
                  backgroundColor: "primary.main",
                  color: "text.white",
                  fontSize: FontConfig.default,
                }}
                variant="contained"
                onClick={aceitarDemanda}
              >
                {texts.DetalhesDemanda.botaoAceitar}
              </Button>
            </Box>
          )}

        {/* caso o usuário seja um gerente */}
        {props.usuario?.tipoUsuario == "GERENTE" &&
          props.botao &&
          !editar &&
          props.dados.status == "BACKLOG_APROVACAO" && (
            <Box className=" w-full flex justify-around">
              <Button
                sx={{
                  backgroundColor: "primary.main",
                  color: "text.white",
                  fontSize: FontConfig.default,
                }}
                variant="contained"
                onClick={() => {
                  if (!props.lendo) {
                    abrirRecusaDemanda("recusa");
                  } else {
                    lerTexto(texts.DetalhesDemanda.botaoRecusar);
                  }
                }}
              >
                {texts.DetalhesDemanda.botaoRecusar}
              </Button>
              <Button
                sx={{
                  backgroundColor: "primary.main",
                  color: "text.white",
                  fontSize: FontConfig.default,
                }}
                variant="contained"
                onClick={aprovarDemanda}
              >
                {texts.DetalhesDemanda.botaoAceitar}
              </Button>
            </Box>
          )}

        {editar && props.salvar && (
          <Button
            sx={{
              backgroundColor: "primary.main",
              color: "text.white",
              fontSize: FontConfig.default,
            }}
            variant="contained"
            onClick={() => {
              if (!props.lendo) {
                salvarEdicao();
              } else {
                lerTexto(texts.DetalhesDemanda.botaoSalvar);
              }
            }}
          >
            {texts.DetalhesDemanda.botaoSalvar}
          </Button>
        )}
      </Box>
    </Box>
  );
};

export default DetalhesDemanda;
