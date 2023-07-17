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
import InfoOutlinedIcon from "@mui/icons-material/InfoOutlined";

import BeneficiosDetalheDemanda from "../../components/BeneficiosDetalheDemanda/BeneficiosDetalheDemanda";
import ModalConfirmacao from "../Modais/Modal-confirmacao/ModalConfirmacao";
import ModalAceitarDemanda from "../Modais/Modal-aceitarDemanda/ModalAceitarDemanda";
import ModalRecusarDemanda from "../Modais/Modal-recusarDemanda/ModalRecusarDemanda";
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
import { WebSocketContext } from "../../service/WebSocketService";
import SpeechSynthesisContext from "../../service/SpeechSynthesisContext";
import { SpeechRecognitionContext } from "../../service/SpeechRecognitionService";
import ModalMotivoRecusa from "../Modais/Modal-motivoRecusa/ModalMotivoRecusa";

// Componente para mostrar os detalhes de uma demanda e suas respectivas funções
const DetalhesDemanda = (props) => {

  // Contexto para trocar a linguagem
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  /** Context para ler o texto da tela */
  const { lendoTexto, lerTexto, librasAtivo } = useContext(
    SpeechSynthesisContext
  );

  /** Context para obter a função de leitura de texto */
  const { startRecognition, escutar, localClique, palavrasJuntas } = useContext(
    SpeechRecognitionContext
  );

  // Navigate utilizado para navegar para outras páginas
  const navigate = useNavigate();

  // Variável hexadecimal para alterar a cor de certos componentes ao mudar o tema da página
  const [corFundoTextArea, setCorFundoTextArea] = useState("#FFFF");

  // Variável armazenando o tema da página (light / dark)
  const { temaPagina } = useContext(ColorModeContext);

  // Ref do input de arquivo para upload de anexos
  const inputFile = useRef(null);

  /** Variável para armazenar o título da demanda */
  const [tituloDemanda, setTituloDemanda] = useState(props.dados.titulo);

  /** Variável para armazenar o problema da demanda */
  const [problema, setProblema] = useState(props.dados.problema);

  /** Variávle para armazenar a propsota da demanda */
  const [proposta, setProposta] = useState(props.dados.proposta);

  /** Variável para armazenar a frequencia da demanda */
  const [frequencia, setFrequencia] = useState(props.dados.frequencia);

  /** Variável para armazenar os benefícios da demanda */
  const [beneficios, setBeneficios] = useState(null);

  /** Variável para armazenar os novos benefícios da demanda */
  const [beneficiosNovos, setBeneficiosNovos] = useState([]);

  /** Variável para armazenar os benefícios excluídos da demanda */
  const [beneficiosExcluidos, setBeneficiosExcluidos] = useState([]);

  /** Variável para armazenar os anexos da demanda */
  const [anexosDemanda, setAnexosDemanda] = useState(props.dados.anexo);

  // UseState do modal de aceitar demanda
  const [openModalAceitarDemanda, setOpenModalAceitarDemanda] = useState(false);

  /** UseState do modal de recusa da demanda */
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

  /** UseState determinando o estado do modal de motivo recusa */
  const [modalMotivoRecusa, setModalMotivoRecusa] = useState(false);

  // Feedback caso o usuário coloque um nome de anexo com mesmo nome de outro anexo
  const [feedbackComAnexoMesmoNome, setFeedbackComAnexoMesmoNome] = useState(false);

  // Feedback caso o usuário tente salvar a demanda sem ter feito nenhuma alteração
  const [feedbackFacaAlteracao, setFeedbackFacaAlteracao] = useState(false);

  /** Feedback ativado quando a demanda é editada */
  const [feedbackDemandaEditada, setFeedbackDemandaEditada] = useState(false);

  /**  Context do WebSocket */
  const { enviar } = useContext(WebSocketContext);

  /** Variável de verificação para visualização de texto */
  const [visualizarTexto, setVisualizarTexto] = useState();

  /** Novos anexos que serão adicionados na edição da demanda */
  const [novosAnexos, setNovosAnexos] = useState([]);

  /** Anexos que já estavam na demanda e que serão removidos na edição da demanda */
  const [anexosRemovidos, setAnexosRemovidos] = useState([]);

  // Variável utilizada para armazenar o problema da demanda em html
  const problemaDaDemanda = useRef(null);

  // Variável utilizada para armazenar a proposta da demanda em html
  const propostaDaDemanda = useRef(null);

  const [recomendacao, setRecomendacao] = useState("")

  // useEffect utilizado para atualizar o valor html do campo
  useEffect(() => {
    if (problemaDaDemanda.current) {
      problemaDaDemanda.current.innerHTML = props.dados.problema;
    }
    if (propostaDaDemanda.current) {
      propostaDaDemanda.current.innerHTML = props.dados.proposta;
    }
  }, [props, visualizarTexto, editar]);

  // UseEffect para atualizar a variável "corFundoTextArea" quando o tema da página for modificado
  useEffect(() => {
    temaPagina === "dark"
      ? setCorFundoTextArea("#212121")
      : setCorFundoTextArea("#FFFFFF");
  }, [temaPagina]);

  // UseEffect para atualizar os dados da demanda quando o componente for montado
  useEffect(() => {
    atribuirDadosPadroes();
  }, []);

  // UseEffect para setar a variável "editar" para false quando o usuário sair da página de edição da demanda
  useEffect(() => {
    if (!props.edicao) setEditar(false);
  }, [props.edicao]);

  /** useEffect utilizado para gravação de áudio */
  useEffect(() => {
    switch (localClique) {
      case "tituloDemanda":
        setTituloDemanda(palavrasJuntas);
        break;
      case "frequenciaUso":
        setFrequencia(palavrasJuntas);
        break;
      default:
        break;
    }
  }, [palavrasJuntas]);

  /** Função para abrir o modal de confirmação de edição de demanda, caso esteja em modo de edição, ou
   * abrir o modo de edição, caso esteja em modo de visualização
   */
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

  /** Função para atribuir aos campos de texto da página seus dados recebidos pelos props, além de
   * resetar as variáveis de edição, sendo usada tanto para inicialização do componente quanto para
   * cancelamento de edição
   */
  const atribuirDadosPadroes = () => {
    setEditar(false);
    setBeneficios(receberBeneficios(props.dados.beneficios));
    setTituloDemanda(props.dados.titulo);
    setProblema(props.dados.problema);
    setProposta(props.dados.proposta);
    setFrequencia(props.dados.frequencia);
    setAnexosDemanda(props.dados.anexo);

    excluirBeneficiosAdicionados();
    excluirAnexosAdicionados();
    setBeneficiosExcluidos([]);
    setVisualizarTexto(true);
  };

  /** Função para receber os benefícios do banco de dados, adicionando o atributo "visible" neles */
  const receberBeneficios = (listaBeneficios) => {
    let listaNova = [];
    for (let beneficio of listaBeneficios) {
      listaNova.push({ ...beneficio, visible: true, valor_mensal: formatarValorMensal(beneficio.valor_mensal) });
    }
    return listaNova;
  };

  /** Função para formatar o valor mensal de um benefício ao recebê-lo */
  const formatarValorMensal = (valor_mensal) => {
    if (!valor_mensal) return;
    let valorMensalString = valor_mensal.toString();
    let arrayValor = valorMensalString.split(".");

    if (arrayValor.length == 1) {
      return valor_mensal + ",00"
    } else if (arrayValor[1].length == 1) {
      if (arrayValor[1].charAt(0)) {
        return valor_mensal + "0"
      } else {
        return arrayValor[0] + ",0" + arrayValor[1]
      }
    } else {
      return arrayValor[0] + "," + arrayValor[1]
    }
  }

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
    let aux = [...beneficios];
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
  const salvarEdicaoDemanda = () => {
    if (!podeSalvar()) {
      setFeedbackFacaAlteracao(true);
      return;
    }

    atualizarBeneficios(beneficios);

    const demandaAtualizada = {
      ...retornarObjetoDemanda(),
      status: "BACKLOG_REVISAO",
    };
    DemandaService.put(demandaAtualizada).then((response) => {
      setEditar(false);
      excluirBeneficiosRemovidos();
      props.updateDemandaProps(response);

      salvarHistorico("Demanda Editada");
      setFeedbackDemandaEditada(true);
    });

    if (anexosRemovidos.length > 0) {
      for (let anexoRemovido of anexosRemovidos) {
        AnexoService.deleteById(anexoRemovido.id);
      }
    }
    setAnexosRemovidos([]);
  };

  /** Função para atualizar uma lista de benefícios na edição de uma demanda */
  const atualizarBeneficios = (listaBeneficios) => {
    for (const beneficio of listaBeneficios) {
      if (beneficio.visible) {
        let beneficioNovo = { ...beneficio };
        
        if(beneficio.valor_mensal) {
          beneficioNovo.valor_mensal = parseFloat(beneficioNovo.valor_mensal.toString().replace(",", "."));
        }
        BeneficioService.put(beneficioNovo).then((response) => { });
      }
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
        e.memoriaCalculo?.toLowerCase() ==
        props.dados.beneficios[index].memoriaCalculo?.toLowerCase()
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

  // Const para retornar uma lista das BUs beneficiadas em forma de texto
  const retornarBUsBeneficiadas = () => {
    let textoFinal = "";
    for (const bu of props.dados.busBeneficiadas) {
      textoFinal += bu.siglaBu + ", ";
    }
    textoFinal = textoFinal.substring(0, textoFinal.length - 2);
    return textoFinal;
  };

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
    if (!lendoTexto) {
      setOpenModalAceitarDemanda(true);
    } else if (librasAtivo) {
    } else {
      lerTexto(texts.DetalhesDemanda.botaoAceitar);
    }
  };

  // Acionado quando o usuário clicar em "Aprovar" na demanda
  const aprovarDemanda = () => {
    if (!lendoTexto) {
      setModalAprovarDemanda(true);
    } else if (librasAtivo) {
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
    if (recomendacao == "" || recomendacao == null) {
      DemandaService.atualizarStatus(props.dados.id, "ASSESSMENT").then(() => {
        salvarHistorico("Demanda Aprovada");
        navegarHome(1);

        const notificacao = NotificacaoService.createNotificationObject(
          NotificacaoService.aprovadoGerente,
          props.dados,
          CookieService.getUser().id
        );
        enviar(
          `/app/weg_ssm/notificacao/${props.dados.solicitante.id}`,
          JSON.stringify(notificacao)
        );
      });
    } else {
      DemandaService.atualizarStatusERecomendacao(props.dados.id, "ASSESSMENT", recomendacao).then(() => {
        salvarHistorico("Demanda Aprovada");
        navegarHome(1);

        const notificacao = NotificacaoService.createNotificationObject(
          NotificacaoService.aprovadoGerente,
          props.dados,
          CookieService.getUser().id
        );
        enviar(
          `/app/weg_ssm/notificacao/${props.dados.solicitante.id}`,
          JSON.stringify(notificacao)
        );
      });
    }

  };

  // Função acionada quando o usuário clica em "Aceitar" no modal de confirmação
  const confirmAceitarDemanda = (dados) => {
    const demandaAtualizada = {
      ...retornarObjetoDemanda(),
      anexo: [
        ...retornarIdsObjetos(dados.anexos),
        ...retornarIdsObjetos(props.dados.anexo),
      ],
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

      const notificacao = NotificacaoService.createNotificationObject(
        NotificacaoService.aprovado,
        props.dados,
        CookieService.getUser().id
      );
      enviar(
        `/app/weg_ssm/notificacao/${props.dados.solicitante.id}`,
        JSON.stringify(notificacao)
      );
    });
  };

  /** Função para formatar uma lista de objetos, retornando somente o id de cada objeto presente, com a lista sendo recebida como parâmetro */
  const retornarIdsObjetos = (listaObjetos) => {
    let listaNova = [];
    for (let objeto of listaObjetos) {
      listaNova.push({ id: objeto.id });
    }
    return listaNova;
  };

  /** Função para retornar um objeto da demanda formatada para edição */
  const retornarObjetoDemanda = () => {
    let demanda = {
      ...props.dados,
      titulo: tituloDemanda,
      problema: formatarHtml(problema),
      proposta: formatarHtml(proposta),
      frequencia: frequencia,
      beneficios: retornarIdsObjetos(beneficios),
      anexo: retornarIdsObjetos(anexosDemanda),
    };
    return demanda;
  };

  /** Função para criar e retornar um objeto de histórico para salvamento */
  const retornaObjetoHistorico = (acaoRealizada) => {
    const historico = {
      data: new Date(),
      acaoRealizada: acaoRealizada,
      autor: { id: CookieService.getUser().id },
      informacaoAdicional: (motivoRecusaDemanda && (acaoRealizada == "Demanda Devolvida" || acaoRealizada == "Demanda Reprovada")) ? motivoRecusaDemanda :
        recomendacao ? recomendacao : null
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
      ...retornarObjetoDemanda(),
      motivoRecusa: motivoRecusaDemanda,
      status: status,
    }).then(() => {
      const tipoNotificacao =
        modoModalRecusa === "devolucao"
          ? NotificacaoService.maisInformacoes
          : NotificacaoService.reprovado;
      const notificacao = NotificacaoService.createNotificationObject(
        tipoNotificacao,
        props.dados,
        CookieService.getUser().id
      );
      enviar(
        `/app/weg_ssm/notificacao/${props.dados.solicitante.id}`,
        JSON.stringify(notificacao)
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

  // Função utilizada para formatar o texto do problema
  const getProblemaFomartted = (problema) => {
    return problema[0].toUpperCase() + problema.substring(1).toLowerCase();
  };

  // Função utilizada para formatar o texto da proposta
  const getPropostaFomartted = (proposta) => {
    return proposta[0].toUpperCase() + proposta.substring(1).toLowerCase();
  };

  return (
    <Box className="flex flex-col justify-center relative items-center mt-10 mb-16">
      {/* Feedback demanda Editada */}
      <Feedback
        open={feedbackDemandaEditada}
        handleClose={() => setFeedbackDemandaEditada(false)}
        status={"sucesso"}
        mensagem={texts.DetalhesDemanda.feedbackDemandaEditada}
      />
      {/* Feedback Faca alguma alteração para poder salvar */}
      <Feedback
        open={feedbackFacaAlteracao}
        handleClose={() => setFeedbackFacaAlteracao(false)}
        status={"erro"}
        mensagem={texts.DetalhesDemanda.facaAlgumaAlteracaoParaPoderSalvar}
      />
      {/* Feedback já existe uma anexo com aquele nome */}
      <Feedback
        open={feedbackComAnexoMesmoNome}
        handleClose={() => setFeedbackComAnexoMesmoNome(false)}
        status={"erro"}
        mensagem={texts.DetalhesDemanda.jaHaUmAnexoComEsseNome}
      />
      {/* Modal para aceitar a demanda */}
      <ModalAceitarDemanda
        open={openModalAceitarDemanda}
        setOpen={setOpenModalAceitarDemanda}
        handleClose={handleCloseModalAceitarDemanda}
        confirmAceitarDemanda={confirmAceitarDemanda}
      />
      {/* Modal para recusar a demanda */}
      <ModalRecusarDemanda
        open={openModalRecusa}
        setOpen={setOpenModalRecusa}
        handleClose={handleCloseModalRecusar}
        confirmRecusarDemanda={confirmRecusaDemanda}
        motivo={motivoRecusaDemanda}
        setMotivo={setMotivoRecusaDemanda}
      />
      {/* Modal para confirmar o cancelamento da edição */}
      <ModalConfirmacao
        open={openModal}
        setOpen={setOpenModal}
        onConfirmClick={atribuirDadosPadroes}
        onCancelClick={setEditar}
        textoModal="cancelarEdicao"
        textoBotao="sim"
        atualizarTexto={true}
      />

      {/* Modal para aceitar a demanda */}
      <ModalConfirmacao
        open={modalAprovarDemanda}
        setOpen={setModalAprovarDemanda}
        onConfirmClick={aprovarDemandaGerencia}
        textoModal="aceitarDemanda"
        textoBotao="aceitar"
        aceitarGerente={true}
        setRecomendacao={setRecomendacao}
      />

      {/* Modal de motivo recusa */}
      {modalMotivoRecusa && (
        <ModalMotivoRecusa
          open={true}
          setOpen={setModalMotivoRecusa}
          motivoRecusa={props.dados?.motivoRecusa}
        />
      )}

      <Box
        id="primeiro"
        className="flex flex-col gap-3 border rounded px-10 py-4 border-t-6 relative"
        sx={{ width: "60rem", borderTopColor: "primary.main" }}
      >

        {props.usuario?.id == props.dados.solicitante?.id || props.usuario.tipoUsuario != "SOLICITANTE" ? (
          <StatusDemanda
            status={props.dados.status}
          />
        ) : null}

        {/* Mostrar o icone de edição caso siga os requisitos */}
        <Box
          className="absolute cursor-pointer flex flex-col"
          sx={{ top: "40px", right: "5px" }}
          onClick={editarDemanda}
        >
          {props.usuario?.id == props.dados.solicitante?.id &&
            props.dados.status == "BACKLOG_EDICAO" &&
            !editar ? (
            <ModeEditOutlineOutlinedIcon
              id="terceiro"
              className="delay-120 hover:scale-110 duration-300"
              sx={{ color: "icon.main", fontSize: "25px" }}
            />
          ) : null}
          {props.usuario?.id == props.dados.solicitante?.id &&
            props.dados.status == "BACKLOG_EDICAO" &&
            editar ? (
            <EditOffOutlinedIcon
              className="delay-120 hover:scale-110 duration-300"
              sx={{ color: "icon.main", fontSize: "25px" }}
            />
          ) : null}

          {props.usuario?.id == props.dados.solicitante?.id && props.dados.status == "BACKLOG_EDICAO" ? (
            <InfoOutlinedIcon
              onClick={(e) => {
                e.stopPropagation();
                if (!lendoTexto && !librasAtivo) {
                  setModalMotivoRecusa(true);
                } else if (librasAtivo) {

                } else {
                  lerTexto(texts.demanda.motivo);
                }
              }}
              id="setimo"
              className="delay-120 hover:scale-110 duration-300 mt-3"
              sx={{
                color: "icon.main",
                cursor: "pointer",
                fontSize: "25px",
              }}
            />
          ) : null}
        </Box>
        {/* o que irá aparecer caso não esteja editando */}
        {!editar ? (
          <>
            <Box>
              {/* o id */}
              <Typography
                fontSize={FontConfig.medium}
                fontWeight={600}
                onClick={() => {
                  lerTexto(props.dados.id);
                }}
                color="text.secondary"
              >
                {texts.demandaGerenciaModoVisualizacao.codigo}: {props.dados.id}
              </Typography>
              {/* o titulo */}
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
            {/* Uma divisão */}
            <Divider />
            {/* o problema */}
            <Box>
              <Typography
                fontSize={FontConfig.big}
                fontWeight="600"
                color="text.primary"
                onClick={() => {
                  lerTexto(texts.DetalhesDemanda.problema);
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
            {/* a proposta */}
            <Box>
              <Typography
                fontSize={FontConfig.big}
                fontWeight="600"
                color="text.primary"
                onClick={() => {
                  lerTexto(texts.DetalhesDemanda.proposta);
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
            {/* Os beneficios */}
            <Box>
              <Box>
                <Typography
                  fontSize={FontConfig.big}
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
                      setBeneficio={alterarTextoBeneficio}
                    />
                  );
                })}
              </Box>
            </Box>
            {/* A frequencia de uso */}
            <Box>
              <Typography
                fontSize={FontConfig.big}
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
            {/* Caso tenha tamanho e secao de TI*/}
            {props.dados.tamanho && props.dados.secaoTI && (
              <Box className="flex justify-between items-center">
                {/* Tamanho da demanda */}
                <Box className="flex items-center">
                  <Typography
                    fontSize={FontConfig.big}
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
                {/* Seção de TI */}
                <Box className="flex items-center">
                  <Typography
                    fontSize={FontConfig.big}
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
            {/* Caso tenha BU solicitante */}
            {props.dados.buSolicitante && (
              // Mostra as BUs beneficiadas
              <Box className="flex justify-between items-center">
                <Box className="flex items-center">
                  <Typography
                    fontSize={FontConfig.big}
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
                  {/* BUs beneficiadas */}
                  <Typography
                    fontSize={FontConfig.big}
                    fontWeight="600"
                    color="text.primary"
                    onClick={() => {
                      lerTexto(texts.DetalhesDemanda.busBeneficiadas);
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
            {/* Caso tenha forum */}
            {props.dados.forum && (
              // Mostra o forum
              <Box className="flex items-center">
                <Typography
                  fontSize={FontConfig.big}
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
            {/* Anexos */}
            <Box>
              <Typography
                fontSize={FontConfig.big}
                fontWeight="600"
                color="text.primary"
                onClick={() => {
                  lerTexto(texts.DetalhesDemanda.anexos);
                }}
              >
                {texts.DetalhesDemanda.anexos}:
              </Typography>
              {/* caso tenha um anexo, irá aparecer os anexos aqui */}
              {props.dados.anexo != null && props.dados.anexo.length > 0 ? (
                <Box className="flex flex-col gap-2 mt-2">
                  {props.dados.anexo.map((anexo, index) => (
                    <Paper
                      key={index}
                      className="flex justify-between items-center px-2 border border-l-4"
                      sx={{
                        borderLeftWidth: "4px",
                        borderLeftColor: "primary.main",
                        borderLeftStyle: "solid",
                        backgroundColor: "background.default",
                      }}
                      elevation={0}
                      square
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
                // caso nao tenha anexos, mostra que nenhum anexo foi adicionado
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
                {/* Titulo da demanda */}
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
                      sx={{
                        cursor: "pointer",
                        color: "primary.main",
                        fontSize: "2rem",
                      }}
                    />
                  ) : (
                    <MicNoneOutlinedIcon
                      sx={{
                        cursor: "pointer",
                        color: "text.secondary",
                        fontSize: "2rem",
                      }}
                    />
                  )}
                </Tooltip>
              </Box>
            </Box>
            {/* Divisao */}
            <Divider />
            {/* Problema da demanda */}
            <Box>
              <Typography
                fontSize={FontConfig.big}
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
                onChange={(value) => {
                  alterarTexto(value, "problema");
                }}
                placeholder={texts.DetalhesDemanda.digiteProblema}
                label="problema"
              />
            </Box>
            {/* Proposta da demanda */}
            <Box>
              <Typography
                fontSize={FontConfig.big}
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
                label="proposta"
              />
            </Box>
            {/* Beneficios da demanda */}
            <Box>
              <Box className="flex items-center">
                <Typography
                  fontSize={FontConfig.big}
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
                      />
                    );
                  }
                })}
              </Box>
            </Box>
            {/* Frequencia de uso */}
            <Box>
              <Typography
                fontSize={FontConfig.big}
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
                      sx={{
                        cursor: "pointer",
                        color: "primary.main",
                        fontSize: "1.8rem",
                      }}
                    />
                  ) : (
                    <MicNoneOutlinedIcon
                      sx={{
                        cursor: "pointer",
                        color: "text.secondary",
                        fontSize: "1.8rem",
                      }}
                    />
                  )}
                </Tooltip>
              </Box>
            </Box>
            {/* Anexos */}
            <Box>
              <Box className="flex items-center">
                <Typography
                  fontSize={FontConfig.big}
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
                <Box className="flex flex-col gap-2 mt-2">
                  {anexosDemanda?.map((anexo, index) => (
                    <Paper
                      key={index}
                      className="flex justify-between items-center px-2 border border-l-4"
                      sx={{
                        borderLeftWidth: "4px",
                        borderLeftColor: "primary.main",
                        borderLeftStyle: "solid",
                        backgroundColor: "background.default",
                      }}
                      elevation={0}
                      square
                    >
                      <Typography
                        sx={{
                          color: "text.primary",
                        }}
                        fontSize={FontConfig.default}
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
        sx={{ width: "15rem", bottom: "20px", right: "80px" }}
      >
        {(props.usuario?.tipoUsuario == "ANALISTA" ||
          props.usuario?.tipoUsuario == "GESTOR") &&
          props.botao &&
          !editar &&
          props.dados.status == "BACKLOG_REVISAO" && (
            // Opções para avaliar a demanda
            <Box className="flex justify-around w-full gap-3 mr-2">
              {/* botao para recusar */}
              <Button
                sx={{
                  backgroundColor: "primary.main",
                  color: "text.white",
                  fontSize: FontConfig.default,
                }}
                variant="contained"
                onClick={() => {
                  if (!lendoTexto) {
                    abrirRecusaDemanda("recusa");
                  } else if (librasAtivo) {
                  } else {
                    lerTexto(texts.DetalhesDemanda.botaoRecusar);
                  }
                }}
              >
                {texts.DetalhesDemanda.botaoRecusar}
              </Button>
              {/* botao para devolver */}
              <Button
                sx={{
                  backgroundColor: "primary.main",
                  color: "text.white",
                  fontSize: FontConfig.default,
                }}
                variant="contained"
                onClick={() => {
                  if (!lendoTexto) {
                    abrirRecusaDemanda("devolucao");
                  } else if (librasAtivo) {
                  } else {
                    lerTexto(texts.DetalhesDemanda.botaoDevolver);
                  }
                }}
              >
                {texts.DetalhesDemanda.botaoDevolver}
              </Button>
              {/* botao para aceitar */}
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
            // Opções para avaliar a demanda
            <Box className=" w-full flex justify-around">
              {/* Botao de recusar demanda */}
              <Button
                sx={{
                  backgroundColor: "primary.main",
                  color: "text.white",
                  fontSize: FontConfig.default,
                }}
                variant="contained"
                onClick={() => {
                  if (!lendoTexto) {
                    abrirRecusaDemanda("recusa");
                  } else if (librasAtivo) {
                  } else {
                    lerTexto(texts.DetalhesDemanda.botaoRecusar);
                  }
                }}
              >
                {texts.DetalhesDemanda.botaoRecusar}
              </Button>
              {/* Botao para aceitar demanda */}
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
          // Botao para salvar as alterações feitas
          <Button
            sx={{
              backgroundColor: "primary.main",
              color: "text.white",
              fontSize: FontConfig.default,
            }}
            variant="contained"
            onClick={() => {
              if (!lendoTexto) {
                salvarEdicaoDemanda();
              } else if (librasAtivo) {
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

// Status da Demanda
const StatusDemanda = ({
  status = "",
}) => {

  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext)

  /** Retorna o texto do status recebido */
  const getStatusFormatted = (status) => {
    if (status == "CANCELLED") {
      return texts.demanda.status.reprovada;
    } else if (status == "BACKLOG_REVISAO") {
      return texts.demanda.status.aguardandoRevisao;
    } else if (status == "BACKLOG_EDICAO") {
      return texts.demanda.status.aguardandoEdicao;
    } else if (status == "BACKLOG_APROVACAO") {
      return texts.demanda.status.emAprovacao;
    } else if (status == "ASSESSMENT") {
      return texts.demanda.status.aprovada;
    } else if (status == "ASSESSMENT_APROVACAO" || status == "ASSESSMENT_EDICAO" || status == "ASSESSMENT_COMISSAO" || status == "ASSESSMENT_DG") {
      return texts.demanda.status.emAndamento;
    } else if (status == "DONE") {
      return texts.demanda.status.emDesenvolvimento;
    }
  };

  /** Retorna a cor hexadecimal do status da demanda */
  const getCorStatus = (status) => {
    if (status == "CANCELLED") {
      return "#DA0303";
    } else if (status == "BACKLOG_REVISAO") {
      return "#C4C4C4";
    } else if (status == "BACKLOG_EDICAO") {
      return "#FFD600";
    } else if (status == "BACKLOG_APROVACAO") {
      return "#00579D";
    } else if (status == "ASSESSMENT") {
      return "#11B703";
    } else if (status == "ASSESSMENT_APROVACAO" || status == "ASSESSMENT_EDICAO" || status == "ASSESSMENT_COMISSAO" || status == "ASSESSMENT_DG") {
      return "#F7DC6F";
    } else if (status == "DONE") {
      return "#7EB61C";
    }
  };

  return (
    <Tooltip title={getStatusFormatted(status)}>
      <Box
        className="flex absolute right-2 top-0 cursor-pointer"
      >
        <Box
          className="w-0 h-0 relative left-4"
          sx={{
            borderTop: `1.8rem solid ${getCorStatus(status)}`,
            borderRight: "1.1rem solid transparent",
            borderLeft: "0px solid transparent",
          }}
        />
        <Box
          className="w-0 h-0 relative"
          sx={{
            borderTop: `1.8rem solid ${getCorStatus(status)}`,
            borderRight: "0rem solid transparent",
            borderLeft: "1.1rem solid transparent",
          }}
        />
      </Box>
    </Tooltip>
  );
};

export default DetalhesDemanda;
