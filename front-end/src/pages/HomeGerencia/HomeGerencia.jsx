import React, { useContext, useState, useEffect, useRef } from "react";
import { useNavigate, useLocation } from "react-router-dom";

import { Box, Button, IconButton, Tab, Tooltip } from "@mui/material";
import { TabContext, TabList, TabPanel } from "@mui/lab";

import SwapVertIcon from "@mui/icons-material/SwapVert";
import FilterAltOutlinedIcon from "@mui/icons-material/FilterAltOutlined";
import AddIcon from "@mui/icons-material/Add";
import FileDownloadIcon from "@mui/icons-material/FileDownload";
import ViewListIcon from "@mui/icons-material/ViewList";
import ViewModuleIcon from "@mui/icons-material/ViewModule";

import Pauta from "../../components/Pauta/Pauta";
import ModalFiltroGerencia from "../../components/ModalFiltroGerencia/ModalFiltroGerencia";
import ModalOrdenacao from "../../components/ModalOrdenacao/ModalOrdenacao";
import Paginacao from "../../components/Paginacao/Paginacao";
import Feedback from "../../components/Feedback/Feedback";
import Ajuda from "../../components/Ajuda/Ajuda";
import DemandaGerenciaModoVisualizacao from "../../components/DemandaGerenciaModoVisualizacao/DemandaGerenciaModoVisualizacao";
import DemandaModoVisualizacao from "../../components/DemandaModoVisualizacao/DemandaModoVisualizacao";
import PautaAtaModoVisualizacao from "../../components/PautaAtaModoVisualizacao/PautaAtaModoVisualizacao";
import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import DemandaGerencia from "../../components/DemandaGerencia/DemandaGerencia";
import ModalConfirmacao from "../../components/ModalConfirmacao/ModalConfirmacao";
import SearchOutlinedIcon from "@mui/icons-material/SearchOutlined";

import MicNoneOutlinedIcon from "@mui/icons-material/MicNoneOutlined";
import MicOutlinedIcon from "@mui/icons-material/MicOutlined";

import UsuarioService from "../../service/usuarioService";
import DemandaService from "../../service/demandaService";
import ForumService from "../../service/forumService";
import DepartamentoService from "../../service/departamentoService";
import PropostaService from "../../service/propostaService";
import ExportExcelService from "../../service/exportExcelService";
import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";
import PautaService from "../../service/pautaService";
import AtaService from "../../service/ataService";
import CookieService from "../../service/cookieService";
import EntitiesObjectService from "../../service/entitiesObjectService";
import ColorModeContext from "../../service/TemaContext";
import ExportPdfService from "../../service/exportPdfService";

import Tour from "reactour";
import ClipLoader from "react-spinners/ClipLoader";

/** Tela de home para a gerência ( Analista, Gerente e Gestor de TI), possui mais telas e funções do que a home */
const HomeGerencia = (props) => {
  /** Context que contém os textos do sistema */
  const { texts } = useContext(TextLanguageContext);

  /** Variável para determinar se a tour de demandas está aberta */
  const [isTourDemandasOpen, setIsTourDemandasOpen] = useState(false);

  /** Variável para determinar se a tour de criar proposta está aberta */
  const [isTourCriarPropostasOpen, setIsTourCriarPropostasOpen] =
    useState(false);

  /** Variável para determinar se a tour de propostas está aberta */
  const [isTourPropostasOpen, setIsTourPropostasOpen] = useState(false);

  /** Variável para determinar se a tour de pautas está aberta */
  const [isTourPautasOpen, setIsTourPautasOpen] = useState(false);

  /** Variável para determinar se a tour de atas está aberta */
  const [isTourAtasOpen, setIsTourAtasOpen] = useState(false);

  /** Variável usada para receber a localização atual */
  const location = useLocation();

  /** Variável usada para navegação entre as páginas */
  const navigate = useNavigate();

  const [filtroProposta, setFiltroProposta] = useState(false);

  /** Parâmetros para pesquisa das demandas e propostas (filtros e pesquisa por título) */
  const [params, setParams] = useState({
    titulo: null,
    solicitante: null,
    gerente: null,
    analista: null,
    forum: null,
    departamento: null,
    tamanho: null,
    status: null,
    presenteEm: null,
  });

  const stepsMinhasDemandas = [
    {
      selector: "#primeiroMinhasDemandas",
      content: texts.homeGerencia.toursDemandas.tour1,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#segundoDemandas",
      content: texts.homeGerencia.toursDemandas.tour2,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#terceiroDemandas",
      content: texts.homeGerencia.toursDemandas.tour3,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#nonoDemandas",
      content: texts.homeGerencia.toursDemandas.tour9,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#quintoDemandas",
      content: texts.homeGerencia.toursDemandas.tour5,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#sextoMinhasDemandas",
      content: texts.homeGerencia.toursMinhasDemandas.tour6,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#oitavoDemandas",
      content: texts.homeGerencia.toursDemandas.tour8,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
  ];

  //JSONs que contém as informações do tour
  const stepsDemandas = [
    {
      selector: "#quartoDemandas",
      content: texts.homeGerencia.toursDemandas.tour4,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#primeiroDemandas",
      content: texts.homeGerencia.toursDemandas.tour6,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#setimoDemandas",
      content: texts.homeGerencia.toursDemandas.tour7,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
  ];

  const stepsCriarPropostas = [
    {
      selector: "#primeiroCriarPropostas",
      content: texts.homeGerencia.toursCriarPropostas.tour1,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
  ];

  const stepsPropostas = [
    {
      selector: "#primeiroPropostas",
      content: texts.homeGerencia.toursPropostas.tour1,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#segundoPropostas",
      content: texts.homeGerencia.toursPropostas.tour2,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#setimoDemandas",
      content: texts.homeGerencia.toursPropostas.tour3,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
  ];

  const stepsPautas = [
    {
      selector: "#primeiroPautas",
      content: texts.homeGerencia.toursPautas.tour1,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#segundoPautas",
      content: texts.homeGerencia.toursPautas.tour2,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
  ];

  const stepsAtas = [
    {
      selector: "#primeiroAtas",
      content: texts.homeGerencia.toursAtas.tour1,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
  ];

  // const [caraLogado, setCaraLogado] = useState(UsuarioService.getUserCookies())

  // const { enviar, inscrever, stompClient } = useContext(WebSocketContext);

  // const [mensagens, setMensagens] = useState([]);

  // const inscreverSocket = () => {
  //   const acaoNovaMensagem = (response) => {
  //     const mensagemRecebida = JSON.parse(response.body);
  //     let mensagemNova = {
  //       ...mensagemRecebida.body,
  //       texto: mensagemRecebida.body.texto.replace(/%BREAK%/g, "\n"),
  //     };
  //     setMensagens((oldMensagens) => [...oldMensagens, mensagemNova]);
  //   };

  //   chatService.getByRemetente(caraLogado.usuario.id).then((response) => {
  //     const chats = response;
  //     chats.map((chat) => {
  //     if (chat.id) {
  //       let inscricaoId = inscrever(
  //         `/weg_ssm/mensagem/${chat.id}/chat`,
  //         acaoNovaMensagem
  //       );
  //     }
  //     });
  //   });
  // }

  // useEffect(() => {
  //   inscreverSocket();
  // }, [stompClient]);

  // Context para ver o tema do sistema
  const { mode } = useContext(ColorModeContext);

  // Contexto para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  /** Variável armazenando qual a aba atual que o usuário está */
  const [valorAba, setValorAba] = useState("1");

  /** Lista geral de itens (demandas, propostas, pautas e atas) para mostrar nas abas */
  const [listaItens, setListaItens] = useState([]);

  /** Lista armazenando os fóruns presentes no sistema para uso no modal de filtros */
  const [listaForum, setListaForum] = useState([]);

  /** Lista armazenando os departamentos presentes no sistema para uso no modal de filtros */
  const [listaDepartamento, setListaDepartamento] = useState([]);

  /** Lista de solicitantes utilizada no modal de filtro */
  const [listaSolicitantes, setListaSolicitantes] = useState([]);

  /** Lista de gerentes utilizada no modal de filtro */
  const [listaGerentes, setListaGerentes] = useState([]);

  /** Lista de analistas utilizada no modal de filtro */
  const [listaAnalistas, setListaAnalistas] = useState([]);

  /** Número de páginas totais recebido nas buscas de itens para paginação */
  const [totalPaginas, setTotalPaginas] = useState(1);

  /** Variável armazenando a página atual usada para a paginação e busca de itens */
  const [paginaAtual, setPaginaAtual] = useState(0);

  /** Variável editável na paginação com o número de itens em uma página */
  const [tamanhoPagina, setTamanhoPagina] = useState(20);

  /** String para ordenação dos itens atualizada com o valor dos checkboxes a cada busca de itens */
  const [ordenacao, setOrdenacao] = useState("sort=id,asc&");

  /** Valores dos checkboxes de score no modal de ordenação (0 - "Menor Score" | 1 - "Maior Score") */
  const [ordenacaoScore, setOrdenacaoScore] = useState([false, true]);

  /** Valores dos checkboxes de título no modal de ordenação (0 - "Z-A" | 1 - "A-Z") */
  const [ordenacaoTitulo, setOrdenacaoTitulo] = useState([false, false]);

  /** Valores dos checkboxes de data no modal de ordenação (0 - "Mais Antiga" | 1 - "Mais Recente") */
  const [ordenacaoDate, setOrdenacaoDate] = useState([false, false]);

  /** Valor do input de pesquisa por título */
  const [valorPesquisa, setValorPesquisa] = useState("");

  /** Variável booleana que determina se o modal de ordenação está aberto */
  const [abrirOrdenacao, setOpenOrdenacao] = useState(false);

  /** Variável para definir se o modal filtro está aberto */
  const [modalFiltro, setModalFiltro] = useState(false);

  // Gambiarra para que na primeira vez arrumando as preferências do usuário o sistema entenda que nas minhas demandas é para pesquisar as demandas
  const [isFirstTime, setIsFirstTime] = useState(false);

  const [isTourMinhasDemandasOpen, setIsTourMinhasDemandasOpen] =
    useState(false);

  /** Objeto contendo os filtros selecionados no sistema, usado no modal de filtro */
  const [filtrosAtuais, setFiltrosAtuais] = useState({
    solicitante: null,
    forum: "",
    tamanho: "",
    gerente: null,
    departamento: "",
    id: null,
    codigoPPM: null,
    analista: null,
    presenteEm: null,
  });

  /** Objeto contendo o usuário logado no sistema */
  const [usuario, setUsuario] = useState({
    id: 0,
    email: "",
    nome: "",
    senha: "",
    tipoUsuario: "",
    visibilidade: 1,
    departamento: null,
    preferencias: null,
  });

  // Parâmetros para pesquisa das pautas (barra de pesquisa somente)
  const [paramsPautas, setParamsPautas] = useState({
    titulo: null,
  });

  // Parâmetros para pesquisa das pautas (barra de pesquisa somente)
  const [paramsAtas, setParamsAtas] = useState({
    titulo: null,
  });

  // UsaState que controla a visibilidade do modal de confirmação para exclusão de uma pauta
  const [openModalConfirmacao, setOpenModalConfirmacao] = useState(false);

  const [pautaSelecionada, setPautaSelecionada] = useState();

  // Mostra o próximo modo de visualização
  const [nextModoVisualizacao, setNextModoVisualizacao] = useState("TABLE");

  /** Variável para esconder a lista de itens e mostrar um ícone de carregamento enquanto busca os itens no banco */
  const [carregamentoItens, setCarregamentoItens] = useState(true);

  /** Variável para esconder a página e mostrar um ícone de carregamento enquanto busca as preferências do usuário */
  const [carregamentoPreferencias, setCarregamentoPreferencias] =
    useState(true);

  /** Variável para o feedback de demanda aceita */
  const [feedbackDemandaAceita, setFeedbackDemandaAceita] = useState(false);

  /** Variável para o feedback de demanda devolvida */
  const [feedbackDemandaDevolvida, setFeedbackDemandaDevolvida] =
    useState(false);

  /** Variável para o feedback de demanda recusada */
  const [feedbackDemandaRecusada, setFeedbackDemandaRecusada] = useState(false);

  /** Variável para o feedback de proposta criada */
  const [feedbackPropostaCriada, setFeedbackPropostaCriada] = useState(false);

  // Feedback ata publicada
  const [feedbackAta, setOpenFeedbackAta] = useState(false);

  // Feedback ata criada
  const [feedbackAtaCriada, setFeedbackAtaCriada] = useState(false);

  // Feedback propostas atualizadas
  const [feedbackPropostasAtualizadas, setFeedbackPropostasAtualizadas] =
    useState(false);

  /** Feedback deletar pauta */
  const [feedbackDeletarPauta, setFeedbackDeletarPauta] = useState(false);

  /** Feedback atualizar proposta */
  const [feedbackPropostaAtualizada, setFeedbackPropostaAtualizada] =
    useState(false);

  const [feedbackDemandaCriada, setFeedbackDemandaCriada] = useState(false);

  const [feedbackAbrirChat, setFeedbackAbrirChat] = useState(false);

  // useState para fechar o chat minimizado
  const [fecharChatMinimizado, setFecharChatMinimizado] = useState(false);

  // UseEffect para buscar o usuário assim que entrar na página
  useEffect(() => {
    verificarFeedbacks();
    buscarUsuario();
    buscarFiltros();
    // inscreverSocket();
  }, []);

  useEffect(() => {
    let paramsTemp = {
      solicitante: null,
      forum: null,
      tamanho: null,
      gerente: null,
      departamento: null,
      analista: null,
      presenteEm: null,
    };

    if (filtrosAtuais.solicitante != "") {
      paramsTemp.solicitante = filtrosAtuais.solicitante;
    }
    if (filtrosAtuais.forum != "") {
      paramsTemp.forum = filtrosAtuais.forum;
    }
    if (filtrosAtuais.tamanho != "") {
      paramsTemp.tamanho = filtrosAtuais.tamanho;
    }
    if (filtrosAtuais.gerente != "") {
      paramsTemp.gerente = filtrosAtuais.gerente;
    }
    if (filtrosAtuais.departamento != "") {
      paramsTemp.departamento = filtrosAtuais.departamento;
    }
    if (filtrosAtuais.analista != "") {
      paramsTemp.analista = filtrosAtuais.analista;
    }
    if (filtrosAtuais.presenteEm != "") {
      paramsTemp.presenteEm = filtrosAtuais.presenteEm;
    }

    setParams({
      titulo: valorPesquisa,
      solicitante: JSON.parse(params.solicitante) || null,
      gerente: paramsTemp.gerente,
      analista: paramsTemp.analista,
      forum: paramsTemp.forum,
      tamanho: paramsTemp.tamanho,
      status: params.status,
      departamento: paramsTemp.departamento,
      presenteEm: paramsTemp.presenteEm,
    });
  }, [filtrosAtuais]);

  // UseEffect para mudar os parâmetros de pesquisa quando a aba for mudada
  useEffect(() => {
    switch (valorAba) {
      case "1":
        setParams({
          ...params,
          gerente: null,
          status: null,
          solicitante: usuario,
        });
        setFiltroProposta(false);
        break;
      case "2":
        if (usuario.tipoUsuario == "GERENTE") {
          setParams({
            ...params,
            gerente: usuario,
            solicitante: null,
            status: "BACKLOG_APROVACAO",
          });
        } else {
          setParams({
            ...params,
            gerente: null,
            solicitante: null,
            status: "BACKLOG_REVISAO",
          });
        }
        setFiltroProposta(false);
        break;
      case "3":
        setParams({
          ...params,
          gerente: null,
          solicitante: null,
          status: "ASSESSMENT",
        });
        setFiltroProposta(false);
        break;
      case "4":
        setParams({
          ...params,
          gerente: null,
          solicitante: null,
          status: null,
        });
        setFiltroProposta(true);
        break;
      case "5":
        setParamsPautas({ ...paramsPautas });
        setFiltroProposta(false);
        break;
      case "6":
        setParamsAtas({ ...paramsAtas });
        setFiltroProposta(false);
        break;
    }
  }, [valorAba, isFirstTime]);

  // UseEffect para iniciar os parâmetros para busca da demanda (filtrando pelo usuário)
  useEffect(() => {
    arrangePreferences();
    if (listaAnalistas.length == 0 && usuario.tipoUsuario == "ANALISTA") {
      listaAnalistas.push(usuario);
    }
  }, [usuario]);

  // UseEffect para buscar as demandas sempre que os parâmetros (filtros) forem modificados
  useEffect(() => {
    buscarItens();
  }, [params, paginaAtual, tamanhoPagina, paramsPautas, paramsAtas, ordenacao]);

  // UseEffect para modificar o texto de ordenação para a pesquisa quando um checkbox for acionado no modal
  useEffect(() => {
    let textoNovo = "";
    if (ordenacaoScore[1]) {
      textoNovo += "sort=score,desc&";
    }
    if (ordenacaoScore[0]) {
      textoNovo += "sort=score,asc&";
    }
    if (ordenacaoTitulo[1]) {
      textoNovo += "sort=titulo,asc&";
    }
    if (ordenacaoTitulo[0]) {
      textoNovo += "sort=titulo,desc&";
    }
    if (ordenacaoDate[0]) {
      textoNovo += "sort=data,asc&";
    }
    if (ordenacaoDate[1]) {
      textoNovo += "sort=data,desc&";
    }
    if (textoNovo == "") {
      textoNovo = "sort=id,asc&";
    }

    setOrdenacao(textoNovo);
  }, [ordenacaoTitulo, ordenacaoScore, ordenacaoDate]);

  // UseEffect para retirar o ícone de carregamento quando os itens forem buscados do banco de dados
  useEffect(() => {
    setCarregamentoItens(false);
  }, [listaItens]);

  /** Função para ativar feedbacks vindos de outras páginas, chamada quando entrar na página */
  const verificarFeedbacks = () => {
    if (localStorage.getItem("tipoFeedback") == "2") {
      setFeedbackDemandaAceita(true);
    } else if (localStorage.getItem("tipoFeedback") == "3") {
      setFeedbackDemandaDevolvida(true);
    } else if (localStorage.getItem("tipoFeedback") == "4") {
      setFeedbackDemandaRecusada(true);
    } else if (localStorage.getItem("tipoFeedback") == "5") {
      setFeedbackPropostaCriada(true);
    }
    localStorage.removeItem("tipoFeedback");
  };

  /** Função para buscar o usuário logado no sistema pelo cookie salvo no navegador */
  const buscarUsuario = () => {
    UsuarioService.getUsuarioByEmail(CookieService.getCookie("jwt").sub).then(
      (e) => {
        setUsuario(e);
      }
    );
  };

  /** Função para buscar a lista de fóruns e departamentos do sistema para o modal de filtros */
  const buscarFiltros = () => {
    if (listaForum.length == 0) {
      ForumService.getAll().then((response) => {
        setListaForum(response);
      });
    }
    if (listaDepartamento.length == 0) {
      DepartamentoService.getAll().then((response) => {
        setListaDepartamento(response);
      });
    }
  };

  /** Função para buscar uma demanda pelo seu número sequencial, possível no campo de pesquisa */
  const buscarPorNumero = (numero) => {
    DemandaService.getById(numero).then((response) => {
      formatarItens(response.content);
      setTotalPaginas(response.totalPages);
    });
  };

  /** Função para buscar uma proposta pelo seu PPM, possível no campo de pesquisa */
  const buscarPorPPM = (ppm) => {
    PropostaService.getByPPM(ppm).then((response) => {
      formatarItens(response.content);
      setTotalPaginas(response.totalPages);
    });
  };

  /** Função para formatar os campos necessários das demandas e propostas para HTML */
  const formatarItens = (listaDemandas) => {
    let listaNova = [];
    for (let demanda of listaDemandas) {
      let listaNovaBeneficios = [];
      for (let beneficio of demanda.beneficios) {
        listaNovaBeneficios.push({
          ...beneficio,
          memoriaCalculo: atob(beneficio.memoriaCalculo),
        });
      }

      listaNova.push({
        ...demanda,
        problema: atob(demanda.problema),
        proposta: atob(demanda.proposta),
        beneficios: listaNovaBeneficios,
      });
    }
    setListaItens(listaNova);
  };

  const buscarItens = () => {
    setCarregamentoItens(true);
    switch (valorAba) {
      case "1":
        if (params.solicitante && params.solicitante.id != 0) {
          DemandaService.getPage(
            params,
            ordenacao + "size=" + tamanhoPagina + "&page=" + paginaAtual
          ).then((response) => {
            formatarItens(response.content);
            setTotalPaginas(response.totalPages);
          });
        }
        break;
      case "2":
        if (params.status != null || params.gerente != null) {
          DemandaService.getPage(
            params,
            ordenacao + "size=" + tamanhoPagina + "&page=" + paginaAtual
          ).then((response) => {
            formatarItens(response.content);
            setTotalPaginas(response.totalPages);
          });
        }
        break;
      case "3":
        if (params.status != null) {
          DemandaService.getPage(
            params,
            ordenacao + "size=" + tamanhoPagina + "&page=" + paginaAtual
          ).then((response) => {
            formatarItens(response.content);
            setTotalPaginas(response.totalPages);
          });
        }
        break;
      case "4":
        PropostaService.getPage(
          params,
          ordenacao + "size=" + tamanhoPagina + "&page=" + paginaAtual
        ).then((response) => {
          formatarItens(response.content);
          setTotalPaginas(response.totalPages);
        });
        break;
      case "5":
        PautaService.getPage(
          paramsPautas,
          ordenacao + "size=" + tamanhoPagina + "&page=" + paginaAtual
        ).then((response) => {
          setListaItens([...response.content]);
          setTotalPaginas(response.totalPages);
        });
        break;
      case "6":
        AtaService.getPage(
          paramsAtas,
          ordenacao + "size=" + tamanhoPagina + "&page=" + paginaAtual
        ).then((response) => {
          setListaItens([...response.content]);
          setTotalPaginas(response.totalPages);
        });
        break;
    }
  };

  // Função para alterar a aba selecionada
  const handleChange = (event, novoValor) => {
    setListaItens([]);
    saveNewPreference("abaPadrao", novoValor);
    setValorAba(novoValor);
  };

  /** Função para trocar o modo de visualização dos itens (bloco / lista) */
  const trocarModoVisualizacao = () => {
    let novoModo = nextModoVisualizacao === "GRID" ? "TABLE" : "GRID";
    saveNewPreference("itemsVisualizationMode", novoModo);
    setNextModoVisualizacao(novoModo);
  };

  // Função para ir na tela de detalhes da demanda, salvando a demanda no location state
  const verDemanda = (demanda) => {
    if (demanda.status == "ASSESSMENT" && usuario.tipoUsuario == "ANALISTA") {
      navigate("/criar-proposta", { state: demanda });
    } else {
      navigate("/detalhes-demanda", { state: demanda });
    }
  };

  // Função para ir na tela de detalhes da proposta pelo id
  const verProposta = (proposta) => {
    navigate("/detalhes-proposta/" + proposta.id, { state: proposta });
  };

  const isGerente = !(usuario.tipoUsuario == "GERENTE");

  // Função para "ouvir" um evento de teclado no input de pesquisa e fazer a pesquisa caso seja a tecla "Enter"
  const eventoTeclado = (e) => {
    if (e.key == "Enter") {
      pesquisaTitulo();
    }
  };

  // Função para salvar o input de pesquisa quando houver alteração
  const salvarPesquisa = (e) => {
    setValorPesquisa(e);
  };

  /** Função para modificar os parâmetros da demanda ao pesquisar no campo de texto */
  const pesquisaTitulo = () => {
    if (!parseInt(valorPesquisa)) {
      setParams({
        ...params,
        titulo: valorPesquisa,
        codigoPPM: null,
        id: null,
      });
    } else {
      if (valorAba < 3) {
        buscarPorNumero(valorPesquisa);
      } else {
        buscarPorPPM(valorPesquisa);
      }
    }
  };

  useEffect(() => {
    if (location.state?.feedback) {
      switch (location.state.feedback) {
        case "ata-criada":
          setFeedbackAtaCriada(true);
          break;
        case "propostas-atualizadas":
          setFeedbackPropostasAtualizadas(true);
          break;
        default:
          setOpenFeedbackAta(true);
          break;
      }
    }
  }, [location.state?.feedback]);

  // Função para exportar para excel
  const exportarExcel = () => {
    if (props.lendo) {
      lerTexto(texts.homeGerencia.exportar);
    } else {
      let listaObjetosString = [];

      for (const object in listaItens) {
        listaObjetosString.push(JSON.stringify(listaItens[object]));
      }

      if (listaObjetosString != null && listaObjetosString.length > 0) {
        // Verificação para saber em qual aba o usuário deseja exportar para excel
        if (valorAba == 2) {
          let listaIdDemandasBacklog = [];

          for (const object in listaItens) {
            listaIdDemandasBacklog.push(listaItens[object].id);
          }

          ExportExcelService.exportDemandasBacklogToExcel(
            listaIdDemandasBacklog
          ).then((response) => {
            let blob = new Blob([response], { type: "application/excel" });
            let url = URL.createObjectURL(blob);
            let link = document.createElement("a");
            let data = new Date();
            let dataFormatada =
              data.getDate() +
              "-" +
              (data.getMonth() + 1) +
              "-" +
              data.getFullYear();
            link.href = url;
            link.download = "demandas-backlog " + dataFormatada + " .xlsx";
            link.click();
          });
        } else if (valorAba == 3) {
          let listaIdDemandasAssessment = [];

          for (const object in listaItens) {
            listaIdDemandasAssessment.push(listaItens[object].id);
          }

          ExportExcelService.exportDemandasAssessmentToExcel(
            listaIdDemandasAssessment
          ).then((response) => {
            let blob = new Blob([response], { type: "application/excel" });
            let url = URL.createObjectURL(blob);
            let link = document.createElement("a");
            let data = new Date();
            let dataFormatada =
              data.getDate() +
              "-" +
              (data.getMonth() + 1) +
              "-" +
              data.getFullYear();
            link.href = url;
            link.download = "demandas-assessment " + dataFormatada + " .xlsx";
            link.click();
          });
        } else if (valorAba == 4) {
          let listaIdPropostas = [];

          for (const object in listaItens) {
            listaIdPropostas.push(listaItens[object].id);
          }

          ExportExcelService.exportPropostasToExcel(listaIdPropostas).then(
            (response) => {
              let blob = new Blob([response], { type: "application/excel" });
              let url = URL.createObjectURL(blob);
              let link = document.createElement("a");
              link.href = url;
              let data = new Date();
              let dataFormatada =
                data.getDate() +
                "-" +
                (data.getMonth() + 1) +
                "-" +
                data.getFullYear();
              link.download = "propostas " + dataFormatada + " .xlsx";
              link.click();
            }
          );
        } else if (valorAba == 5) {
          let listaIdPautas = [];

          for (const object in listaItens) {
            listaIdPautas.push(listaItens[object].id);
          }

          if (listaIdPautas.length > 0) {
            ExportExcelService.exportPautasToExcel(listaIdPautas).then(
              (response) => {
                let blob = new Blob([response], { type: "application/excel" });
                let url = URL.createObjectURL(blob);
                let link = document.createElement("a");
                let data = new Date();
                let dataFormatada =
                  data.getDate() +
                  "-" +
                  (data.getMonth() + 1) +
                  "-" +
                  data.getFullYear();
                link.href = url;
                link.download = "pautas " + dataFormatada + " .xlsx";
                link.click();
              }
            );
          }
        } else {
          // MUDAR TUDO PARA LISTAITENS, NÃO DEIXAR NA LISTA ATAS
          let listaIdAtas = [];

          for (const object in listaItens) {
            listaIdAtas.push(listaItens[object].id);
          }

          if (listaIdAtas.length > 0) {
            ExportExcelService.exportAtasToExcel(listaIdAtas).then(
              (response) => {
                let blob = new Blob([response], { type: "application/excel" });
                let url = URL.createObjectURL(blob);
                let link = document.createElement("a");
                let data = new Date();
                let dataFormatada =
                  data.getDate() +
                  "-" +
                  (data.getMonth() + 1) +
                  "-" +
                  data.getFullYear();
                link.href = url;
                link.download = "atas " + dataFormatada + " .xlsx";
                link.click();
              }
            );
          }
        }
      }
    }
  };

  // Função que deleta uma pauta
  const deletePauta = () => {
    // Atualiza as propostas contidas na pauta para que não tenham mais os atributos de quando estavam na pauta
    for (let propostaAux of pautaSelecionada.propostas) {
      PropostaService.removerPresenca(propostaAux.id).then((response) => {
        // Salvamento de histórico
        ExportPdfService.exportProposta(response.id).then((file) => {
          let arquivo = new Blob([file], { type: "application/pdf" });
          PropostaService.addHistorico(
            response.id,
            "Pauta #" + pautaSelecionada.numeroSequencial + " Excluída",
            arquivo,
            CookieService.getUser().id
          ).then(() => {});
        });
      });
    }

    // Deleta a pauta
    PautaService.delete(pautaSelecionada.id).then((res) => {
      setFeedbackDeletarPauta(true);
      setPautaSelecionada(null);
      buscarItens();
    });
  };

  // Função acionada quando o usuário cancela a deleção de uma pauta
  const handleOnCancelClickDeletePauta = () => {
    setPautaSelecionada(null);
  };

  useEffect(() => {
    if (pautaSelecionada) {
      setOpenModalConfirmacao(true);
    }
  }, [pautaSelecionada]);

  // ********************************************** Preferências **********************************************
  /**
   * Função que arruma o modo de visualização das preferências do usuário para o qual ele escolheu por último
   */
  const arrangePreferences = () => {
    UsuarioService.getPreferencias(CookieService.getCookie("jwt").sub).then(
      (preferencias = EntitiesObjectService.preferencias()) => {
        let itemsVisualizationMode =
          preferencias?.itemsVisualizationMode?.toUpperCase();

        // ItemsVisualizationMode é o modo de visualização preferido do usuário, porém o nextModoVisualizao é o próximo modo para o qual será trocado a visualização
        if (itemsVisualizationMode == nextModoVisualizacao) {
          setNextModoVisualizacao("GRID");
        }

        // Setando valor da nova aba
        setValorAba(preferencias?.abaPadrao);

        // Timeout para retirar o carregamento após as preferências serem atualizadas
        setTimeout(() => {
          setIsFirstTime(true);
          setCarregamentoPreferencias(false);
        }, 500);
      }
    );
  };

  /**
   * Função que salva a nova preferência do usuário
   */
  const saveNewPreference = (preferenciaTipo = "", value) => {
    if (!CookieService.getCookie("jwt")) return;

    UsuarioService.getUsuarioByEmail(CookieService.getCookie("jwt").sub).then(
      (user) => {
        let preferencias = JSON.parse(user.preferencias);

        switch (preferenciaTipo) {
          case "itemsVisualizationMode":
            // Nova preferência do modo de visualização
            preferencias.itemsVisualizationMode =
              value == "TABLE" ? "grid" : "table";
            break;
          case "abaPadrao":
            // Nova preferência da aba padrão
            preferencias.abaPadrao = value;
            // setValorAba(preferencias.abaPadrao);
            break;
        }

        user.preferencias = JSON.stringify(preferencias);

        UsuarioService.updateUser(user.id, user);
      }
    );
  };
  // ********************************************** Fim Preferências **********************************************

  // ********************************************** Funções de voz **********************************************

  const [
    feedbackErroNavegadorIncompativel,
    setFeedbackErroNavegadorIncompativel,
  ] = useState(false);
  const [feedbackErroReconhecimentoVoz, setFeedbackErroReconhecimentoVoz] =
    useState(false);

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
        default:
          recognition.lang = "pt-BR";
          break;
      }

      recognition.onstart = () => {};

      recognition.onresult = (event) => {
        const transcript =
          event.results[event.results.length - 1][0].transcript;
        setValorPesquisa(transcript);
      };

      recognition.onerror = (event) => {
        setFeedbackErroReconhecimentoVoz(true);
      };

      recognitionRef.current = recognition;
      recognition.start();
    } else {
      setFeedbackErroNavegadorIncompativel(true);
    }
  };

  const stopRecognition = () => {
    if (recognitionRef.current) {
      recognitionRef.current.stop();
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

  // ********************************************** Fim Funções de voz **********************************************

  // Função que irá setar o texto que será "lido" pela a API
  const lerTexto = (texto) => {
    if (props.lendo) {
      props.setTexto(texto);
    }
  };

  // Função que irá "ouvir" o texto que será "lido" pela a API
  useEffect(() => {
    if (props.lendo && props.texto != "") {
      if ("speechSynthesis" in window) {
        const synthesis = window.speechSynthesis;
        const utterance = new SpeechSynthesisUtterance(props.texto);
        synthesis.speak(utterance);
      }
      props.setTexto("");
    } else if (!props.lendo) {
      if ("speechSynthesis" in window) {
        const synthesis = window.speechSynthesis;
        synthesis.cancel();
      }
    }
  }, [props.texto, props.lendo]);

  return (
    <FundoComHeader
      lendo={props.lendo}
      texto={props.texto}
      setTexto={props.setTexto}
    >
      {/* {!fecharChatMinimizado && (
        <ChatMinimizado fecharChatMinimizado={fecharChatMinimizado} setFecharChatMinimizado={setFecharChatMinimizado}/>
      )} */}
      {/* Help Tour's */}
      <Tour
        steps={stepsMinhasDemandas}
        isOpen={isTourMinhasDemandasOpen}
        onRequestClose={() => setIsTourMinhasDemandasOpen(false)}
        accentColor="#00579D"
        rounded={10}
        showCloseButton={false}
      />
      <Tour
        steps={stepsDemandas}
        isOpen={isTourDemandasOpen}
        onRequestClose={() => setIsTourDemandasOpen(false)}
        accentColor="#00579D"
        rounded={10}
        showCloseButton={false}
      />
      <Tour
        steps={stepsCriarPropostas}
        isOpen={isTourCriarPropostasOpen}
        onRequestClose={() => setIsTourCriarPropostasOpen(false)}
        accentColor="#00579D"
        rounded={10}
        showCloseButton={false}
      />
      <Tour
        steps={stepsPropostas}
        isOpen={isTourPropostasOpen}
        onRequestClose={() => setIsTourPropostasOpen(false)}
        accentColor="#00579D"
        rounded={10}
        showCloseButton={false}
      />
      <Tour
        steps={stepsPautas}
        isOpen={isTourPautasOpen}
        onRequestClose={() => setIsTourPautasOpen(false)}
        accentColor="#00579D"
        rounded={10}
        showCloseButton={false}
      />
      <Tour
        steps={stepsAtas}
        isOpen={isTourAtasOpen}
        onRequestClose={() => setIsTourAtasOpen(false)}
        accentColor="#00579D"
        rounded={10}
        showCloseButton={false}
      />

      <ModalConfirmacao
        open={openModalConfirmacao}
        setOpen={setOpenModalConfirmacao}
        textoModal={"confirmarExclusao"}
        textoBotao={"sim"}
        onConfirmClick={deletePauta}
        onCancelClick={() => {
          handleOnCancelClickDeletePauta();
        }}
        lendo={props.lendo}
        texto={props.texto}
        setTexto={props.setTexto}
      />

      {/* Div container */}
      <Box
        className="flex justify-center mt-8"
        sx={{ backgroundColor: "background.default", width: "100%" }}
      >
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
        {/* Feedback Não pode abrir chat com você mesmo */}
        <Feedback
          open={feedbackAbrirChat}
          handleClose={() => {
            setFeedbackAbrirChat(false);
          }}
          status={"erro"}
          mensagem={texts.homeGerencia.feedback.feedback11}
          lendo={props.lendo}
          texto={props.texto}
          setTexto={props.setTexto}
        />
        {/* Feedback ata publicada */}
        <Feedback
          open={feedbackAta}
          handleClose={() => {
            setOpenFeedbackAta(false);
          }}
          status={"sucesso"}
          mensagem={texts.homeGerencia.feedback.feedback1}
          lendo={props.lendo}
          texto={props.texto}
          setTexto={props.setTexto}
        />

        {/* Feedback ata criada */}
        <Feedback
          open={feedbackAtaCriada}
          handleClose={() => {
            setFeedbackAtaCriada(false);
          }}
          status={"sucesso"}
          mensagem={texts.homeGerencia.feedback.feedback8}
          lendo={props.lendo}
          texto={props.texto}
          setTexto={props.setTexto}
        />

        {/* Feedback propostas atualizadas */}
        <Feedback
          open={feedbackPropostasAtualizadas}
          handleClose={() => {
            setFeedbackPropostasAtualizadas(false);
          }}
          status={"sucesso"}
          mensagem={texts.homeGerencia.feedback.feedback9}
          lendo={props.lendo}
          texto={props.texto}
          setTexto={props.setTexto}
        />

        {/* Feedback demanda criada  */}
        <Feedback
          open={feedbackDemandaCriada}
          handleClose={() => {
            setFeedbackDemandaCriada(false);
          }}
          status={"sucesso"}
          mensagem={texts.homeGerencia.feedback.feedback10}
          lendo={props.lendo}
          texto={props.texto}
          setTexto={props.setTexto}
        />

        <Feedback
          open={feedbackDemandaAceita}
          handleClose={() => {
            setFeedbackDemandaAceita(false);
          }}
          status={"sucesso"}
          mensagem={texts.homeGerencia.feedback.feedback2}
          lendo={props.lendo}
          texto={props.texto}
          setTexto={props.setTexto}
        />

        <Feedback
          open={feedbackDemandaRecusada}
          handleClose={() => {
            setFeedbackDemandaRecusada(false);
          }}
          status={"sucesso"}
          mensagem={texts.homeGerencia.feedback.feedback3}
          lendo={props.lendo}
          texto={props.texto}
          setTexto={props.setTexto}
        />

        <Feedback
          open={feedbackDemandaDevolvida}
          handleClose={() => {
            setFeedbackDemandaDevolvida(false);
          }}
          status={"sucesso"}
          mensagem={texts.homeGerencia.feedback.feedback4}
          lendo={props.lendo}
          texto={props.texto}
          setTexto={props.setTexto}
        />

        <Feedback
          open={feedbackPropostaCriada}
          handleClose={() => {
            setFeedbackPropostaCriada(false);
          }}
          status={"sucesso"}
          mensagem={texts.homeGerencia.feedback.feedback5}
          lendo={props.lendo}
          texto={props.texto}
          setTexto={props.setTexto}
        />

        {/* Feedback pauta deletada */}
        <Feedback
          open={feedbackDeletarPauta}
          handleClose={() => {
            setFeedbackDeletarPauta(false);
          }}
          status={"sucesso"}
          mensagem={texts.homeGerencia.feedback.feedback6}
          lendo={props.lendo}
          texto={props.texto}
          setTexto={props.setTexto}
        />
        {/* Feedback proposta atualizada */}
        <Feedback
          open={feedbackPropostaAtualizada}
          handleClose={() => {
            setFeedbackPropostaAtualizada(false);
          }}
          status={"sucesso"}
          mensagem={texts.homeGerencia.feedback.feedback7}
          lendo={props.lendo}
          texto={props.texto}
          setTexto={props.setTexto}
        />

        {/* Div container para o conteúdo da home */}
        <Box sx={{ width: "90%" }}>
          {carregamentoPreferencias ? (
            <Box className="mt-6 w-full h-full flex justify-center items-center">
              <ClipLoader color="#00579D" size={110} />
            </Box>
          ) : (
            <TabContext value={valorAba}>
              <Box
                className="relative mb-4"
                sx={{
                  borderBottom: 1,
                  borderColor: "divider.main",
                  minWidth: "47rem",
                }}
              >
                <TabList
                  onChange={handleChange}
                  aria-label="lab API tabs example"
                >
                  <Tab
                    sx={{
                      color: "text.secondary",
                      fontSize: FontConfig.medium,
                    }}
                    label={texts.home.minhasDemandas}
                    value="1"
                    onClick={() => {
                      lerTexto(texts.home.minhasDemandas);
                    }}
                  />
                  <Tab
                    sx={{
                      color: "text.secondary",
                      fontSize: FontConfig.medium,
                    }}
                    label={texts.homeGerencia.demandas}
                    value="2"
                    onClick={() => {
                      lerTexto(texts.homeGerencia.demandas);
                    }}
                  />

                  {isGerente && (
                    <Tab
                      sx={{
                        color: "text.secondary",
                        fontSize: FontConfig.medium,
                      }}
                      label={texts.homeGerencia.criarPropostas}
                      value="3"
                      onClick={() => {
                        lerTexto(texts.homeGerencia.criarPropostas);
                      }}
                    />
                  )}

                  {isGerente && (
                    <Tab
                      sx={{
                        color: "text.secondary",
                        fontSize: FontConfig.medium,
                      }}
                      label={texts.homeGerencia.propostas}
                      value="4"
                      onClick={() => {
                        lerTexto(texts.homeGerencia.propostas);
                      }}
                    />
                  )}

                  {isGerente && (
                    <Tab
                      sx={{
                        color: "text.secondary",
                        fontSize: FontConfig.medium,
                      }}
                      label={texts.homeGerencia.pautas}
                      value="5"
                      onClick={() => {
                        lerTexto(texts.homeGerencia.pautas);
                      }}
                    />
                  )}

                  {isGerente && (
                    <Tab
                      sx={{
                        color: "text.secondary",
                        fontSize: FontConfig.medium,
                      }}
                      label={texts.homeGerencia.atas}
                      value="6"
                      onClick={() => {
                        lerTexto(texts.homeGerencia.atas);
                      }}
                    />
                  )}
                </TabList>
                <Box id="nonoDemandas" className="absolute right-0 top-2">
                  {nextModoVisualizacao == "TABLE" ? (
                    <Tooltip title={texts.homeGerencia.visualizacaoEmTabela}>
                      <IconButton
                        onClick={() => {
                          trocarModoVisualizacao();
                        }}
                      >
                        <ViewListIcon color="primary" />
                      </IconButton>
                    </Tooltip>
                  ) : (
                    <Tooltip title={texts.homeGerencia.visualizacaoEmBloco}>
                      <IconButton
                        onClick={() => {
                          trocarModoVisualizacao();
                        }}
                      >
                        <ViewModuleIcon color="primary" />
                      </IconButton>
                    </Tooltip>
                  )}
                </Box>
              </Box>

              {/* Container das ações abaixo das abas (input de pesquisa, filtrar e criar demanda) */}
              <Box className="flex justify-between w-full">
                {/* Container para o input e botão de filtrar */}
                <Box className="flex gap-2 w-2/4 items-center">
                  {/* Input de pesquisa */}
                  {/* <InputPesquisa
                  eventoTeclado={eventoTeclado}
                  pesquisaTitulo={pesquisaTitulo}
                  salvarPesquisa={salvarPesquisa}
                  valorPesquisa={valorPesquisa}
                  setValorPesquisa={setValorPesquisa}
                  params={params}
                  ordenacao={ordenacao}
                  tamanhoPagina={tamanhoPagina}
                  paginaAtual={paginaAtual}
                  setListaItens={setListaItens}
                  setTotalPaginas={setTotalPaginas}
                  listaAutocomplete={listaAutocomplete}
                /> */}
                  <Box
                    className="flex justify-between items-center border px-3 py-1"
                    sx={{
                      backgroundColor: "input.main",
                      width: "50%",
                      minWidth: "10rem",
                    }}
                    id="primeiroMinhasDemandas"
                  >
                    {/* Input de pesquisa*/}
                    <Box
                      className="w-full"
                      component="input"
                      sx={{
                        backgroundColor: "input.main",
                        outline: "none",
                        color: "text.primary",
                        fontSize: FontConfig.medium,
                      }}
                      placeholder={texts.homeGerencia.pesquisarPorTitulo}
                      onKeyDown={(e) => {
                        eventoTeclado(e);
                      }}
                      onBlur={() => {
                        pesquisaTitulo();
                      }}
                      onChange={(e) => {
                        salvarPesquisa(e.target.value);
                      }}
                      value={valorPesquisa}
                    />
                    {/* Container para os ícones */}
                    <Box className="flex gap-2 items-center">
                      {/* Ícone de microfone */}
                      <Tooltip
                        className="hover:cursor-pointer"
                        title={texts.homeGerencia.gravarAudio}
                        onClick={() => {
                          startRecognition();
                        }}
                      >
                        {escutar ? (
                          <MicOutlinedIcon
                            sx={{ color: "text.secondary", fontSize: "1.3rem" }}
                          />
                        ) : (
                          <MicNoneOutlinedIcon
                            sx={{ color: "text.secondary", fontSize: "1.3rem" }}
                          />
                        )}
                      </Tooltip>
                      {/* Ícone de pesquisa */}
                      <Tooltip
                        className="hover:cursor-pointer"
                        title={texts.homeGerencia.pesquisar}
                        onClick={() => {
                          pesquisaTitulo();
                        }}
                      >
                        <SearchOutlinedIcon sx={{ color: "text.secondary" }} />
                      </Tooltip>

                      {/* Ícone de ordenação */}
                      <Tooltip title={texts.homeGerencia.ordenacao}>
                        <SwapVertIcon
                          id="segundoDemandas"
                          onClick={() => {
                            setOpenOrdenacao(true);
                          }}
                          className="cursor-pointer"
                          sx={{ color: "text.secondary" }}
                        />
                      </Tooltip>
                    </Box>
                  </Box>

                  {/* <Tooltip title={texts.homeGerencia.ordenacao}>
                  <SwapVertIcon
                    id="segundoDemandas"
                    onClick={() => {
                      setOpenOrdenacao(true);
                    }}
                    className="cursor-pointer"
                    sx={{ color: "text.secondary" }}
                  />
                </Tooltip> */}
                  {/* Modal de ordenação */}
                  {abrirOrdenacao && (
                    <ModalOrdenacao
                      tipoComponente="demanda"
                      ordenacaoTitulo={ordenacaoTitulo}
                      setOrdenacaoTitulo={setOrdenacaoTitulo}
                      ordenacaoScore={ordenacaoScore}
                      setOrdenacaoScore={setOrdenacaoScore}
                      ordenacaoDate={ordenacaoDate}
                      setOrdenacaoDate={setOrdenacaoDate}
                      fecharModal={() => setOpenOrdenacao(false)}
                      lendo={props.lendo}
                      texto={props.texto}
                      setTexto={props.setTexto}
                      valorAba={valorAba}
                    />
                  )}

                  {/* Botão de filtrar */}
                  {valorAba < 5 && (
                    <Button
                      id="terceiroDemandas"
                      className="flex gap-1"
                      sx={{
                        backgroundColor: "primary.main",
                        color: "text.white",
                        fontSize: FontConfig.default,
                        minWidth: "5rem",
                      }}
                      onClick={() => {
                        if (!props.lendo) {
                          setModalFiltro(true);
                        } else {
                          lerTexto(texts.homeGerencia.filtrar);
                        }
                      }}
                      variant="contained"
                      disableElevation
                    >
                      {texts.homeGerencia.filtrar} <FilterAltOutlinedIcon />
                    </Button>
                  )}
                  {modalFiltro && (
                    <ModalFiltroGerencia
                      fecharModal={() => {
                        setModalFiltro(false);
                      }}
                      filtro={filtrosAtuais}
                      setFiltro={setFiltrosAtuais}
                      listaForuns={listaForum}
                      listaDepartamentos={listaDepartamento}
                      listaSolicitantes={listaSolicitantes}
                      setListaSolicitantes={setListaSolicitantes}
                      listaGerentes={listaGerentes}
                      setListaGerentes={setListaGerentes}
                      listaAnalistas={listaAnalistas}
                      setListaAnalistas={setListaAnalistas}
                      filtroProposta={filtroProposta}
                      lendo={props.lendo}
                      texto={props.texto}
                      setTexto={props.setTexto}
                    />
                  )}

                  {/* Botão de exportar */}
                  {valorAba != 1 && (
                    <Button
                      id="quartoDemandas"
                      className="flex gap-1"
                      sx={{
                        backgroundColor: "primary.main",
                        color: "text.white",
                        fontSize: FontConfig.default,
                        minWidth: "6rem",
                      }}
                      onClick={exportarExcel}
                      variant="contained"
                      disableElevation
                    >
                      {texts.homeGerencia.exportar} <FileDownloadIcon />
                    </Button>
                  )}
                </Box>

                {/* Botão de criar demanda */}
                <Button
                  id="quintoDemandas"
                  className="gap-2"
                  sx={{
                    backgroundColor: "primary.main",
                    color: "text.white",
                    fontSize: FontConfig.default,
                    maxHeight: "2.5rem",
                    minWidth: "10.5rem",
                  }}
                  variant="contained"
                  disableElevation
                  onClick={() => {
                    if (!props.lendo) {
                      navigate("/criar-demanda");
                    } else {
                      lerTexto(texts.homeGerencia.criarDemanda);
                    }
                  }}
                >
                  {texts.homeGerencia.criarDemanda}
                  <AddIcon />
                </Button>
              </Box>

              {carregamentoItens ? (
                <Box className="mt-6 w-full h-full flex justify-center items-center">
                  <ClipLoader color="#00579D" size={110} />
                </Box>
              ) : (
                <Box className="mt-6" id="sextoMinhasDemandas">
                  <Box>
                    <TabPanel sx={{ padding: 0 }} value="1">
                      <Ajuda
                        onClick={() => setIsTourMinhasDemandasOpen(true)}
                      />
                      <Box>
                        {isTourMinhasDemandasOpen ? (
                          <DemandaGerencia
                            key={1}
                            isTourDemandasOpen={isTourDemandasOpen}
                            setIsTourDemandasOpen={setIsTourDemandasOpen}
                            dados={{
                              analista: {},
                              beneficios: [{}],
                              buSolicitante: {},
                              busBeneficiados: [{}],
                              departamento: {},
                              frequencia: "",
                              gerente: {},
                              tamanho: "",
                              id: 0,
                              titulo: texts.homeGerencia.demandaParaTour,
                              problema: "",
                              proposta: "",
                              motivoRecusa: "",
                              status: "ASSESSMENT",
                              data: "",
                              solicitante: {
                                nome: texts.homeGerencia.demandaParaTour,
                              },
                            }}
                            semHistorico={true}
                            tipo="demanda"
                            lendo={props.lendo}
                            texto={props.texto}
                            setTexto={props.setTexto}
                          />
                        ) : (
                          <DemandaModoVisualizacao
                            listaDemandas={listaItens}
                            onDemandaClick={verDemanda}
                            myDemandas={true}
                            nextModoVisualizacao={nextModoVisualizacao}
                            lendo={props.lendo}
                            texto={props.texto}
                            setTexto={props.setTexto}
                          />
                        )}
                      </Box>
                    </TabPanel>
                  </Box>
                  {/* Valores para as abas selecionadas */}
                  <Box id="primeiroDemandas">
                    <TabPanel sx={{ padding: 0 }} value="2">
                      <Ajuda onClick={() => setIsTourDemandasOpen(true)} />
                      {isTourDemandasOpen ? (
                        <DemandaGerencia
                          key={1}
                          isTourDemandasOpen={isTourDemandasOpen}
                          setFeedbackAbrirChat={setFeedbackAbrirChat}
                          dados={{
                            analista: {},
                            beneficios: [{}],
                            buSolicitante: {},
                            busBeneficiados: [{}],
                            departamento: {},
                            frequencia: "",
                            gerente: {},
                            tamanho: "",
                            id: 0,
                            titulo: texts.homeGerencia.demandaParaTour,
                            problema: "",
                            proposta: "",
                            motivoRecusa: "",
                            status: "BACKLOG_REVISAO",
                            data: "",
                            solicitante: {
                              nome: texts.homeGerencia.demandaParaTour,
                            },
                          }}
                          tipo="demanda"
                          lendo={props.lendo}
                          texto={props.texto}
                          setTexto={props.setTexto}
                        />
                      ) : (
                        <DemandaGerenciaModoVisualizacao
                          listaDemandas={listaItens}
                          onDemandaClick={verDemanda}
                          setFeedbackAbrirChat={setFeedbackAbrirChat}
                          nextModoVisualizacao={nextModoVisualizacao}
                          lendo={props.lendo}
                          texto={props.texto}
                          setTexto={props.setTexto}
                        />
                      )}
                    </TabPanel>
                  </Box>
                  {isGerente && (
                    <>
                      <TabPanel
                        sx={{ padding: 0 }}
                        value="3"
                        onClick={() => { }}
                      >
                        <Ajuda
                          onClick={() => setIsTourCriarPropostasOpen(true)}
                        />
                        <Box
                          sx={{
                            display: "grid",
                            gap: "1rem",
                            gridTemplateColumns:
                              "repeat(auto-fit, minmax(720px, 1fr))",
                          }}
                        >
                          <DemandaGerenciaModoVisualizacao
                            listaDemandas={listaItens}
                            onDemandaClick={verDemanda}
                            setFeedbackAbrirChat={setFeedbackAbrirChat}
                            nextModoVisualizacao={nextModoVisualizacao}
                            lendo={props.lendo}
                            texto={props.texto}
                            setTexto={props.setTexto}
                          />
                        </Box>
                      </TabPanel>
                      <TabPanel
                        sx={{ padding: 0 }}
                        value="4"
                        onClick={() => { }}
                      >
                        <Box
                          sx={{
                            display: "grid",
                            gap: "1rem",
                            gridTemplateColumns:
                              "repeat(auto-fit, minmax(720px, 1fr))",
                          }}
                        >
                          <Ajuda onClick={() => setIsTourPropostasOpen(true)} />
                          {isTourPropostasOpen ? (
                            <DemandaGerencia
                              key={1}
                              isTourDemandasOpen={isTourDemandasOpen}
                              setFeedbackAbrirChat={setFeedbackAbrirChat}
                              dados={{
                                analista: {},
                                beneficios: [{}],
                                buSolicitante: {},
                                busBeneficiados: [{}],
                                departamento: {},
                                frequencia: "",
                                gerente: {},
                                tamanho: "",
                                id: 0,
                                titulo: texts.homeGerencia.demandaParaTour,
                                problema: "",
                                proposta: "",
                                motivoRecusa: "",
                                status: "ASSESSMENT",
                                data: "",
                                solicitante: {
                                  nome: texts.homeGerencia.demandaParaTour,
                                },
                              }}
                              tipo="proposta"
                              lendo={props.lendo}
                              texto={props.texto}
                              setTexto={props.setTexto}
                            />
                          ) : (
                            <Box
                              sx={{
                                display: "grid",
                                gap: "1rem",
                                gridTemplateColumns:
                                  "repeat(auto-fit, minmax(720px, 1fr))",
                              }}
                            >
                              <DemandaGerenciaModoVisualizacao
                                listaDemandas={listaItens}
                                onDemandaClick={verProposta}
                                setFeedbackAbrirChat={setFeedbackAbrirChat}
                                nextModoVisualizacao={nextModoVisualizacao}
                                isProposta={true}
                                lendo={props.lendo}
                                texto={props.texto}
                                setTexto={props.setTexto}
                              />
                            </Box>
                          )}
                        </Box>
                      </TabPanel>
                      <Box id="primeiroPautas">
                        <TabPanel sx={{ padding: 0 }} value="5">
                          <Ajuda onClick={() => setIsTourPautasOpen(true)} />
                          {isTourPautasOpen ? (
                            <Pauta
                              key={1}
                              isTourDemandasOpen={isTourDemandasOpen}
                              dados={{
                                numeroSequencial: "Pauta para Tour",
                              }}
                              tipo="pauta"
                              lendo={props.lendo}
                              texto={props.texto}
                              setTexto={props.setTexto}
                            />
                          ) : (
                            <PautaAtaModoVisualizacao
                              listaPautas={listaItens}
                              onItemClick={(pauta) => {
                                navigate("/detalhes-pauta", {
                                  state: { pauta },
                                });
                              }}
                              nextModoVisualizacao={nextModoVisualizacao}
                              setPautaSelecionada={setPautaSelecionada}
                              lendo={props.lendo}
                              texto={props.texto}
                              setTexto={props.setTexto}
                            />
                          )}
                        </TabPanel>
                      </Box>
                      <Box id="primeiroAtas">
                        <TabPanel sx={{ padding: 0 }} value="6">
                          <Ajuda onClick={() => setIsTourAtasOpen(true)} />
                          {isTourAtasOpen ? (
                            <Pauta
                              key={1}
                              isTourDemandasOpen={isTourDemandasOpen}
                              dados={{
                                numeroSequencial: "Ata para Tour",
                              }}
                              tipo="ata"
                              lendo={props.lendo}
                              texto={props.texto}
                              setTexto={props.setTexto}
                            />
                          ) : (
                            <PautaAtaModoVisualizacao
                              listaPautas={listaItens}
                              onItemClick={(ata) => {
                                navigate("/detalhes-ata", { state: { ata } });
                              }}
                              nextModoVisualizacao={nextModoVisualizacao}
                              setPautaSelecionada={setPautaSelecionada}
                              isAta={true}
                            />
                          )}
                        </TabPanel>
                      </Box>
                    </>
                  )}
                </Box>
              )}
            </TabContext>
          )}
        </Box>
      </Box>
      <Box className="flex justify-end mt-10" sx={{ width: "95%" }}>
        {totalPaginas > 1 || listaItens.length > 20 ? (
          <Paginacao
            totalPaginas={totalPaginas}
            setTamanho={setTamanhoPagina}
            tamanhoPagina={tamanhoPagina}
            setPaginaAtual={setPaginaAtual}
          />
        ) : null}
      </Box>
    </FundoComHeader>
  );
};

export default HomeGerencia;
