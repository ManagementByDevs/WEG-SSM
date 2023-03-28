import React, { useContext, useState, useEffect } from "react";
import { useNavigate, useLocation } from "react-router-dom";

import { Box, Button, IconButton, Tab, Tooltip } from "@mui/material";
import { TabContext, TabList, TabPanel } from "@mui/lab";

import SearchOutlinedIcon from "@mui/icons-material/SearchOutlined";
import SwapVertIcon from "@mui/icons-material/SwapVert";
import FilterAltOutlinedIcon from "@mui/icons-material/FilterAltOutlined";
import AddIcon from "@mui/icons-material/Add";
import FileDownloadIcon from "@mui/icons-material/FileDownload";
import ViewListIcon from "@mui/icons-material/ViewList";
import ViewModuleIcon from "@mui/icons-material/ViewModule";

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

import UsuarioService from "../../service/usuarioService";
import DemandaService from "../../service/demandaService";
import ForumService from "../../service/forumService";
import DepartamentoService from "../../service/departamentoService";
import PropostaService from "../../service/propostaService";
import ExportExcelService from "../../service/exportExcelService";
import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";
import ColorModeContext from "../../service/TemaContext";
import PautaService from "../../service/pautaService";
import AtaService from "../../service/ataService";

import Tour from "reactour";
import ClipLoader from "react-spinners/ClipLoader";

/** Tela de home para a gerência ( Analista, Gerente e Gestor de TI), possui mais telas e funções do que a home */
const HomeGerencia = () => {
  // Context que contém os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  //UseState utilizado para controlar o tour, se ele está aberto ou fechado
  const [isTourDemandasOpen, setIsTourDemandasOpen] = useState(false);
  const [isTourCriarPropostasOpen, setIsTourCriarPropostasOpen] =
    useState(false);
  const [isTourPropostasOpen, setIsTourPropostasOpen] = useState(false);
  const [isTourPautasOpen, setIsTourPautasOpen] = useState(false);
  const [isTourAtasOpen, setIsTourAtasOpen] = useState(false);

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
    codigoPPM: null,
    id: null,
  });

  //JSONs que contém as informações do tour
  const stepsDemandas = [
    {
      selector: "#primeiroDemandas",
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
      selector: "#quartoDemandas",
      content: texts.homeGerencia.toursDemandas.tour4,
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
      selector: "#sextoDemandas",
      content: texts.homeGerencia.toursDemandas.tour6,
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
    {
      selector: "#segundoCriarPropostas",
      content: texts.homeGerencia.toursCriarPropostas.tour2,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#terceiroCriarPropostas",
      content: texts.homeGerencia.toursCriarPropostas.tour3,
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

  // Context para alterar o tema do sistema
  const { mode, toggleColorMode } = useContext(ColorModeContext);

  // Contexto para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

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
  const [carregamento, setCarregamento] = useState(false);

  const handleOnVisualizationModeClick = () => {
    setNextModoVisualizacao((prevMode) => {
      if (prevMode === "GRID") {
        return "TABLE";
      }
      return "GRID";
    });
  };

  // UseEffect para buscar o usuário assim que entrar na página
  useEffect(() => {
    buscarUsuario();
    buscarFiltros();
    arrangePreferences();
  }, []);

  // UseEffect para redefinir os parâmteros quando a ordenação for modificada
  useEffect(() => {
    setParams({
      titulo: valorPesquisa,
      solicitante: JSON.parse(params.solicitante),
      gerente: JSON.parse(params.gerente),
      analista: JSON.parse(params.analista),
      forum: JSON.parse(params.forum),
      tamanho: params.tamanho,
      status: params.status,
      departamento: JSON.parse(params.departamento),
      codigoPPM: params.codigoPPM,
      id: params.id,
    });
  }, [ordenacao]);

  useEffect(() => {
    let paramsTemp = {
      solicitante: null,
      forum: null,
      tamanho: null,
      gerente: null,
      departamento: null,
      codigoPPM: null,
      id: null,
      analista: null,
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
    if (filtrosAtuais.codigoPPM != "") {
      paramsTemp.codigoPPM = filtrosAtuais.codigoPPM;
    }
    if (filtrosAtuais.id != "") {
      paramsTemp.id = filtrosAtuais.id;
    }
    if (filtrosAtuais.analista != "") {
      paramsTemp.analista = filtrosAtuais.analista;
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
      codigoPPM: paramsTemp.codigoPPM,
      id: paramsTemp.id,
    });
  }, [filtrosAtuais]);

  // feedbacks para o gerenciamento das demandas por parte do analista

  const [feedbackDemandaAceita, setFeedbackDemandaAceita] = useState(false);
  const [feedbackDemandaDevolvida, setFeedbackDemandaDevolvida] =
    useState(false);
  const [feedbackDemandaRecusada, setFeedbackDemandaRecusada] = useState(false);
  const [feedbackPropostaCriada, setFeedbackPropostaCriada] = useState(false);

  useEffect(() => {
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
  }, []);

  useEffect(() => {
    setCarregamento(true);
    switch (valorAba) {
      case "1":
        setParams({
          ...params,
          gerente: null,
          status: null,
          solicitante: usuario,
        });
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
        break;
      case "3":
        setParams({
          ...params,
          gerente: null,
          solicitante: null,
          status: "ASSESSMENT",
        });
        break;
      case "4":
        setParams({
          ...params,
          gerente: null,
          solicitante: null,
          status: "ASSESSMENT_APROVACAO",
        });
        break;
      case "5":
        setParamsPautas({ ...paramsPautas });
        break;
      case "6":
        setParamsPautas({ ...paramsAtas });
        break;
    }
  }, [valorAba]);

  // UseEffect para iniciar os parâmetros para busca da demanda (filtrando pelo usuário)
  useEffect(() => {
    setParams({ ...params, solicitante: usuario });

    if (listaAnalistas.length == 0 && usuario.tipoUsuario == "ANALISTA") {
      listaAnalistas.push(usuario);
    }
  }, [usuario]);

  // UseEffect para buscar as demandas sempre que os parâmetros (filtros) forem modificados
  useEffect(() => {
    buscarItens();
  }, [params, paginaAtual, tamanhoPagina, paramsPautas, paramsAtas]);

  // UseEffect para modificar o texto de ordenação para a pesquisa quando um checkbox for acionado no modal
  useEffect(() => {
    let textoNovo = "";
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
    setCarregamento(false);
  }, [listaItens]);

  // Função para buscar a lista de fóruns e departamentos para o modal de filtros
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

  const buscarPorNumero = (numero) => {
    DemandaService.getById(numero).then((response) => {
      setListaItens([response]);
    });
  };

  const buscarPorPPM = (ppm) => {
    DemandaService.getByPPM(ppm).then((response) => {
      setListaItens([response]);
    });
  };

  // Função para buscar o usuário logado no sistema
  const buscarUsuario = () => {
    UsuarioService.getUsuarioById(
      parseInt(localStorage.getItem("usuarioId"))
    ).then((e) => {
      setUsuario(e);
      setParams({ ...params, solicitante: e });
    });
  };

  const buscarItens = () => {
    setCarregamento(true);
    switch (valorAba) {
      case "1":
        if (usuario.id != 0) {
          DemandaService.getPage(
            params,
            ordenacao + "size=" + tamanhoPagina + "&page=" + paginaAtual
          ).then((response) => {
            setListaItens([...response.content]);
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
            setListaItens([...response.content]);
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
            setListaItens([...response.content]);
            setTotalPaginas(response.totalPages);
          });
        }
        break;
      case "4":
        if (params.status != null) {
          PropostaService.getPage(
            params,
            ordenacao + "size=" + tamanhoPagina + "&page=" + paginaAtual
          ).then((response) => {
            setListaItens([...response.content]);
            setTotalPaginas(response.totalPages);
          });
        }
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

  const [pautas, setPautas] = useState([]);

  const [atas, setAtas] = useState([]);

  // Função para alterar a aba selecionada
  const handleChange = (event, novoValor) => {
    setValorAba(novoValor);
  };

  const [modalFiltro, setModalFiltro] = useState(false);

  const abrirModalFiltro = () => {
    setModalFiltro(true);
  };

  // Função para ir na tela de detalhes da demanda, salvando a demanda no localStorage
  const verDemanda = (demanda) => {
    if (demanda.status == "ASSESSMENT" && usuario.tipoUsuario == "ANALISTA") {
      navigate("/criar-proposta", { state: demanda });
    } else {
      navigate("/detalhes-demanda", { state: demanda });
    }
  };

  // Função para ir na tela de detalhes da proposta, salvando a proposta no localStorage
  const verProposta = (proposta) => {
    navigate("/detalhes-proposta", { state: proposta });
  };

  const isGerente = !(
    JSON.parse(localStorage.getItem("user")).tipoUsuario == "GERENTE"
  );

  // Função para "ouvir" um evento de teclado no input de pesquisa e fazer a pesquisa caso seja a tecla "Enter"
  const eventoTeclado = (e) => {
    if (e.key == "Enter") {
      pesquisaTitulo();
    }
  };

  // Função para salvar o input de pesquisa quando houver alteração
  const salvarPesquisa = (e) => {
    setValorPesquisa(e.target.value);
  };

  // Função para modificar os parâmetros da demanda ao pesquisar no campo de texto
  const pesquisaTitulo = () => {
    if (!parseInt(valorPesquisa)) {
      setParams({
        titulo: valorPesquisa,
        solicitante: JSON.parse(params.solicitante),
        gerente: JSON.parse(params.gerente),
        analista: JSON.parse(params.analista),
        forum: JSON.parse(params.forum),
        tamanho: params.tamanho,
        status: params.status,
        departamento: JSON.parse(params.departamento),
        codigoPPM: null,
        id: null,
      });
    } else {
      if (valorAba < 3) {
        setParams({
          titulo: params.titulo,
          solicitante: JSON.parse(params.solicitante),
          gerente: JSON.parse(params.gerente),
          analista: JSON.parse(params.analista),
          forum: JSON.parse(params.forum),
          tamanho: params.tamanho,
          status: params.status,
          departamento: JSON.parse(params.departamento),
          codigoPPM: params.codigoPPM,
          id: valorPesquisa,
        });
      } else {
        setParams({
          titulo: params.titulo,
          solicitante: JSON.parse(params.solicitante),
          gerente: JSON.parse(params.gerente),
          analista: JSON.parse(params.analista),
          forum: JSON.parse(params.forum),
          tamanho: params.tamanho,
          status: params.status,
          departamento: JSON.parse(params.departamento),
          codigoPPM: valorPesquisa,
          id: params.id,
        });
      }
    }
  };

  const abrirModalOrdenacao = () => {
    setOpenOrdenacao(true);
  };

  const location = useLocation();
  const navigate = useNavigate();

  const [feedbackAta, setOpenFeedbackAta] = useState(false);

  useEffect(() => {
    if (location.state?.feedback) {
      setOpenFeedbackAta(true);
    }
  }, [location.state?.feedback]);

  // useState para fechar o chat minimizado
  const [fecharChatMinimizado, setFecharChatMinimizado] = useState(false);

  // Função para exportar para excel
  const exportarExcel = () => {
    let listaObjetosString = [];

    for (const object in listaItens) {
      listaObjetosString.push(JSON.stringify(listaItens[object]));
    }

    // Verificação para saber em qual aba o usuário deseja exportar para excel
    if (valorAba == 2) {
      ExportExcelService.exportDemandasBacklogToExcel(listaObjetosString).then(
        (response) => {
          let blob = new Blob([response], { type: "application/excel" });
          let url = URL.createObjectURL(blob);
          let link = document.createElement("a");
          link.href = url;
          link.download = "demandas.xlsx";
          link.click();
        }
      );
    } else if (valorAba == 3) {
      ExportExcelService.exportDemandasAssessmentToExcel(
        listaObjetosString
      ).then((response) => {
        let blob = new Blob([response], { type: "application/excel" });
        let url = URL.createObjectURL(blob);
        let link = document.createElement("a");
        link.href = url;
        link.download = "demandas.xlsx";
        link.click();
      });
    } else if (valorAba == 4) {
      ExportExcelService.exportPropostasToExcel(listaObjetosString).then(
        (response) => {
          let blob = new Blob([response], { type: "application/excel" });
          let url = URL.createObjectURL(blob);
          let link = document.createElement("a");
          link.href = url;
          link.download = "propostas.xlsx";
          link.click();
        }
      );
    } else if (valorAba == 5) {
      let listaIdPautas = [];
      for (const object in listaItens) {
        listaIdPautas.push(listaItens[object].id);
      }

      ExportExcelService.exportPautasToExcel(listaIdPautas).then((response) => {
        let blob = new Blob([response], { type: "application/excel" });
        let url = URL.createObjectURL(blob);
        let link = document.createElement("a");
        link.href = url;
        link.download = "pautas.xlsx";
        link.click();
      });
    } else {
      // MUDAR TUDO PARA LISTAITENS, NÃO DEIXAR NA LISTA ATAS
      let listaIdAtas = [];

      for (const object in atas) {
        listaIdAtas.push(atas[object].id);
      }

      ExportExcelService.exportAtasToExcel(listaIdAtas).then((response) => {
        let blob = new Blob([response], { type: "application/excel" });
        let url = URL.createObjectURL(blob);
        let link = document.createElement("a");
        link.href = url;
        link.download = "atas.xlsx";
        link.click();
      });
    }
  };

  const [feedbackDeletarPauta, setFeedbackDeletarPauta] = useState(false);
  const [feedbackPropostaAtualizada, setFeedbackPropostaAtualizada] =
    useState(false);

  // Função que deleta uma pauta
  const deletePauta = () => {
    // Atualiza as propostas contidas na pauta para que não tenham mais os atributos de quando estavam na pauta
    for (let propostaAux of pautaSelecionada.propostas) {
      propostaAux.publicada = null;
      propostaAux.status = "ASSESSMENT_APROVACAO";
      propostaAux.parecerInformacao = null;
      propostaAux.parecerComissao = null;
      propostaAux.parecerDG = null;

      PropostaService.putWithoutArquivos(propostaAux, propostaAux.id).then(
        (newProposta) => {
          setFeedbackPropostaAtualizada(true);
        }
      );
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

  // String para ordenação das demandas
  const [stringOrdenacao, setStringOrdenacao] = useState("sort=id,asc&");

  // ********************************************** Preferências **********************************************
  /**
   * Função que arruma o modo de visualização das preferências do usuário para o qual ele escolheu por último
   */
  const arrangePreferences = () => {
    let itemsVisualizationMode =
      UsuarioService.getPreferencias().itemsVisualizationMode.toUpperCase();

    // ItemsVisualizationMode é o modo de visualização preferido do usuário, porém o nextModoVisualizao é o
    // próximo modo para o qual será trocado a visualização
    if (itemsVisualizationMode == nextModoVisualizacao) {
      setNextModoVisualizacao("GRID");
    }
  };

  /**
   * Função que salva a nova preferência do usuário
   */
  const saveNewPreference = () => {
    let user = UsuarioService.getUser();
    let preferencias = UsuarioService.getPreferencias();

    preferencias.itemsVisualizationMode =
      nextModoVisualizacao == "TABLE" ? "grid" : "table";

    user.preferencias = JSON.stringify(preferencias);

    UsuarioService.updateUser(user.id, user).then((e) => {
      UsuarioService.updateUserInLocalStorage();
    });
  };

  // UseEffect para salvar as novas preferências do usuário
  useEffect(() => {
    saveNewPreference();
  }, [nextModoVisualizacao]);
  // ********************************************** Fim Preferências **********************************************

  return (
    <FundoComHeader>
      {/* {!fecharChatMinimizado && (
        <ChatMinimizado fecharChatMinimizado={fecharChatMinimizado} setFecharChatMinimizado={setFecharChatMinimizado}/>
      )} */}
      {/* Help Tour's */}
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
      />
      {/* Div container */}

      <Box
        className="flex justify-center mt-8"
        sx={{ backgroundColor: "background.default", width: "100%" }}
      >
        {/* Feedback ata criada */}
        <Feedback
          open={feedbackAta}
          handleClose={() => {
            setOpenFeedbackAta(false);
          }}
          status={"sucesso"}
          mensagem={texts.homeGerencia.feedback.feedback1}
        />

        <Feedback
          open={feedbackDemandaAceita}
          handleClose={() => {
            setFeedbackDemandaAceita(false);
          }}
          status={"sucesso"}
          mensagem={texts.homeGerencia.feedback.feedback2}
        />

        <Feedback
          open={feedbackDemandaRecusada}
          handleClose={() => {
            setFeedbackDemandaRecusada(false);
          }}
          status={"sucesso"}
          mensagem={texts.homeGerencia.feedback.feedback3}
        />

        <Feedback
          open={feedbackDemandaDevolvida}
          handleClose={() => {
            setFeedbackDemandaDevolvida(false);
          }}
          status={"sucesso"}
          mensagem={texts.homeGerencia.feedback.feedback4}
        />

        <Feedback
          open={feedbackPropostaCriada}
          handleClose={() => {
            setFeedbackPropostaCriada(false);
          }}
          status={"sucesso"}
          mensagem={texts.homeGerencia.feedback.feedback5}
        />

        {/* Feedback pauta deletada */}
        <Feedback
          open={feedbackDeletarPauta}
          handleClose={() => {
            setFeedbackDeletarPauta(false);
          }}
          status={"sucesso"}
          mensagem={texts.homeGerencia.feedback.feedback6}
        />
        {/* Feedback proposta atualizada */}
        <Feedback
          open={feedbackPropostaAtualizada}
          handleClose={() => {
            setFeedbackPropostaAtualizada(false);
          }}
          status={"sucesso"}
          mensagem={texts.homeGerencia.feedback.feedback7}
        />

        {/* Div container para o conteúdo da home */}
        <Box sx={{ width: "90%" }}>
          {/* Sistema de abas */}
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
                  sx={{ color: "text.secondary", fontSize: FontConfig.medium }}
                  label={texts.home.minhasDemandas}
                  value="1"
                />
                <Tab
                  sx={{ color: "text.secondary", fontSize: FontConfig.medium }}
                  label={texts.homeGerencia.demandas}
                  value="2"
                />

                {isGerente && (
                  <Tab
                    sx={{
                      color: "text.secondary",
                      fontSize: FontConfig.medium,
                    }}
                    label={texts.homeGerencia.criarPropostas}
                    value="3"
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
                  />
                )}
              </TabList>
              <Box id="nonoDemandas" className="absolute right-0 top-2">
                {nextModoVisualizacao == "TABLE" ? (
                  <Tooltip title={texts.homeGerencia.visualizacaoEmTabela}>
                    <IconButton
                      onClick={() => {
                        handleOnVisualizationModeClick();
                        // setNextModoVisualizacao("GRID");
                      }}
                    >
                      <ViewListIcon color="primary" />
                    </IconButton>
                  </Tooltip>
                ) : (
                  <Tooltip title={texts.homeGerencia.visualizacaoEmBloco}>
                    <IconButton
                      onClick={() => {
                        handleOnVisualizationModeClick();
                        // setNextModoVisualizacao("TABLE");
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
              <Box className="flex gap-4 w-2/4">
                {/* Input de pesquisa */}
                <Box
                  className="flex justify-between items-center border px-3 py-1"
                  sx={{
                    backgroundColor: "input.main",
                    width: "50%",
                    minWidth: "10rem",
                  }}
                  id="primeiroDemandas"
                >
                  {/* Input de pesquisa */}
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
                      salvarPesquisa(e);
                    }}
                  />

                  {/* Container para os ícones */}
                  <Box className="flex gap-2">
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
                          abrirModalOrdenacao();
                        }}
                        className="cursor-pointer"
                        sx={{ color: "text.secondary" }}
                      />
                    </Tooltip>
                  </Box>
                </Box>

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
                    onClick={abrirModalFiltro}
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
                    buscarPorNumero={buscarPorNumero}
                    buscarPorPPM={buscarPorPPM}
                  />
                )}

                {/* Botão de exportar */}
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
                  navigate("/criar-demanda");
                }}
              >
                {texts.homeGerencia.criarDemanda}
                <AddIcon />
              </Button>
            </Box>

            {carregamento ? (
              <Box className="mt-6 w-full h-full flex justify-center items-center">
                <ClipLoader color={"primary.main"} size={110} />
              </Box>
            ) : (
              <Box className="mt-6" id="sextoDemandas">
                <Box>
                  <TabPanel sx={{ padding: 0 }} value="1">
                    <Ajuda />
                    <Box>
                      <DemandaModoVisualizacao
                        listaDemandas={listaItens}
                        onDemandaClick={verDemanda}
                        myDemandas={true}
                        nextModoVisualizacao={nextModoVisualizacao}
                      />
                    </Box>
                  </TabPanel>
                </Box>
                {/* Valores para as abas selecionadas */}
                <TabPanel sx={{ padding: 0 }} value="2">
                  <Ajuda onClick={() => setIsTourDemandasOpen(true)} />
                  {isTourDemandasOpen ? (
                    <DemandaGerencia
                      key={1}
                      isTourDemandasOpen={isTourDemandasOpen}
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
                    />
                  ) : (
                    <DemandaGerenciaModoVisualizacao
                      listaDemandas={listaItens}
                      onDemandaClick={verDemanda}
                      nextModoVisualizacao={nextModoVisualizacao}
                    />
                  )}
                </TabPanel>
                {isGerente && (
                  <>
                    <TabPanel sx={{ padding: 0 }} value="3" onClick={() => {}}>
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
                          nextModoVisualizacao={nextModoVisualizacao}
                        />
                      </Box>
                    </TabPanel>
                    <TabPanel sx={{ padding: 0 }} value="4" onClick={() => {}}>
                      <Ajuda onClick={() => setIsTourPropostasOpen(true)} />
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
                          nextModoVisualizacao={nextModoVisualizacao}
                          isProposta={true}
                        />
                      </Box>
                    </TabPanel>
                    <TabPanel sx={{ padding: 0 }} value="5">
                      <Ajuda onClick={() => setIsTourPautasOpen(true)} />
                      <PautaAtaModoVisualizacao
                        listaPautas={listaItens}
                        onItemClick={(pauta) => {
                          navigate("/detalhes-pauta", {
                            state: { pauta },
                          });
                        }}
                        nextModoVisualizacao={nextModoVisualizacao}
                        setPautaSelecionada={setPautaSelecionada}
                      />
                    </TabPanel>
                    <TabPanel sx={{ padding: 0 }} value="6">
                      <Ajuda onClick={() => setIsTourAtasOpen(true)} />
                      <PautaAtaModoVisualizacao
                        listaPautas={listaItens}
                        onItemClick={(ata) => {
                          navigate("/detalhes-ata", { state: { ata } });
                        }}
                        nextModoVisualizacao={nextModoVisualizacao}
                        setPautaSelecionada={setPautaSelecionada}
                        isAta={true}
                      />
                    </TabPanel>
                  </>
                )}
              </Box>
            )}
          </TabContext>
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
