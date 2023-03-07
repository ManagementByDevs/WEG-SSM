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
import PautaAtaModoVisualizacao from "../../components/PautaAtaModoVisualizacao/PautaAtaModoVisualizacao";
import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import DemandaGerencia from "../../components/DemandaGerencia/DemandaGerencia";
import ChatMinimizado from "../../components/ChatMinimizado/ChatMinimizado";

import UsuarioService from "../../service/usuarioService";
import DemandaService from "../../service/demandaService";
import ForumService from "../../service/forumService";
import DepartamentoService from "../../service/departamentoService";
import PropostaService from "../../service/propostaService";
import ExportExcelService from "../../service/exportExcelService";
import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";
import ColorModeContext from "../../service/TemaContext";

import Tour from "reactour";

const HomeGerencia = () => {
  //UseState utilizado para controlar o tour, se ele está aberto ou fechado
  const [isTourDemandasOpen, setIsTourDemandasOpen] = useState(false);
  const [isTourCriarPropostasOpen, setIsTourCriarPropostasOpen] = useState(false);
  const [isTourPropostasOpen, setIsTourPropostasOpen] = useState(false);
  const [isTourPautasOpen, setIsTourPautasOpen] = useState(false);
  const [isTourAtasOpen, setIsTourAtasOpen] = useState(false);

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
      content:
        texts.homeGerencia.toursDemandas.tour3,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#quartoDemandas",
      content:
        texts.homeGerencia.toursDemandas.tour4,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#nonoDemandas",
      content:
        texts.homeGerencia.toursDemandas.tour9,
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
      content:
        texts.homeGerencia.toursDemandas.tour6,
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
      content:
        texts.homeGerencia.toursDemandas.tour7,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
  ];

  const stepsCriarPropostas = [
    {
      selector: "#primeiroCriarPropostas",
      content:
        texts.homeGerencia.toursCriarPropostas.tour1,
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
      content:
        texts.homeGerencia.toursPropostas.tour1,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#segundoPropostas",
      content:
        texts.homeGerencia.toursPropostas.tour2,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#setimoDemandas",
      content:
        texts.homeGerencia.toursPropostas.tour3,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
  ];

  const stepsPautas = [
    {
      selector: "#primeiroPautas",
      content:
        texts.homeGerencia.toursPautas.tour1,
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
      content:
        texts.homeGerencia.toursAtas.tour1,
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

  // Context que contém os textos do sistema
  const { texts, setTexts } = useContext(TextLanguageContext);

  // UseState para poder visualizar e alterar a aba selecionada
  const [value, setValue] = useState("1");

  const [listaItens, setListaItens] = useState([]);

  // Listas para os inputs de fórum e departamento no modal de filtro
  const [listaForum, setListaForum] = useState([]);
  const [listaDepartamento, setListaDepartamento] = useState([]);

  // Listas usadas para os inputs de solicitante e gerente no modal de filtro
  const [listaSolicitantes, setListaSolicitantes] = useState([]);
  const [listaGerentes, setListaGerentes] = useState([]);
  const [listaAnalistas, setListaAnalistas] = useState([]);

  // String para ordenação das demandas
  const [totalPaginas, setTotalPaginas] = useState(1);

  const [paginaAtual, setPaginaAtual] = useState(0);
  const [tamanhoPagina, setTamanhoPagina] = useState(20);

  const [ordenacao, setOrdenacao] = useState("sort=id,asc&");

  // Valores dos checkboxes no modal de ordenação
  const [ordenacaoTitulo, setOrdenacaoTitulo] = useState([false, false]);
  const [ordenacaoScore, setOrdenacaoScore] = useState([false, true]);
  const [ordenacaoDate, setOrdenacaoDate] = useState([false, false]);

  // Valor do input de pesquisa
  const [valorPesquisa, setValorPesquisa] = useState("");

  const [abrirOrdenacao, setOpenOrdenacao] = useState(false);
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
  const [modoFiltro, setModoFiltro] = useState("demanda");

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

  // Parâmetros para pesquisa das demandas (filtros)
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

  // Mostra o próximo modo de visualização
  const [nextModoVisualizacao, setNextModoVisualizacao] = useState("TABLE");

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
      solicitante: paramsTemp.solicitante,
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
    switch (value) {
      case "1":
        if (usuario.tipoUsuario == "GERENTE") {
          setParams({
            ...params,
            gerente: usuario,
            status: "BACKLOG_APROVACAO",
          });
        } else {
          setParams({
            ...params,
            gerente: null,
            status: "BACKLOG_REVISAO",
          });
        }
        setModoFiltro("demanda");
        break;
      case "2":
        setParams({
          ...params,
          gerente: null,
          status: "ASSESSMENT",
        });
        setModoFiltro("demanda");
        break;
      case "3":
        setParams({
          ...params,
          gerente: null,
          status: "ASSESSMENT_APROVACAO",
        });
        setModoFiltro("proposta");
        break;
      case "4":
        setModoFiltro("proposta");
        break;
      case "5":
        setModoFiltro("proposta");
        break;
    }
  }, [value]);

  // UseEffect para iniciar os parâmetros para busca da demanda (filtrando pelo usuário)
  useEffect(() => {
    if (usuario.tipoUsuario == "GERENTE") {
      setParams({ ...params, gerente: usuario, status: "BACKLOG_APROVACAO" });
    } else {
      setParams({ ...params, status: "BACKLOG_REVISAO" });
    }

    if (listaAnalistas.length == 0 && usuario.tipoUsuario == "ANALISTA") {
      listaAnalistas.push(usuario);
    }
  }, [usuario]);

  // UseEffect para buscar as demandas sempre que os parâmetros (filtros) forem modificados
  useEffect(() => {
    buscarItens();
  }, [params, paginaAtual, tamanhoPagina]);

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
      setUsuario({
        id: e.id,
        email: e.email,
        nome: e.nome,
        senha: e.senha,
        tipoUsuario: e.tipoUsuario,
        departamento: e.departamento,
        visibilidade: e.visibilidade,
        preferencias: JSON.parse(e.preferencias),
      });
    });
  };

  const buscarItens = () => {
    switch (value) {
      case "1":
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
      case "2":
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
      case "3":
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
      case "4":
        break;
      case "5":
        break;
    }
  };

  const [pautas, setPautas] = useState([
    {
      id: 1,
      numeroSequencial: "1/2022",
      comissao: "Comissão 1",
      analista: { nome: "Kenzo Sato" },
      inicioDataReuniao: "2022-12-15 19:23:57.443000",
      fimDataReuniao: "2023-02-07 19:06:26.511000",
      propostas: [{}],
    },
    {
      id: 1,
      numeroSequencial: "2/2022",
      comissao: "Comissão 2",
      analista: { nome: "Kenzo Sato" },
      inicioDataReuniao: "2022-12-15 19:23:57.443000",
      fimDataReuniao: "2023-02-07 19:06:26.511000",
      propostas: [{}],
    },
    {
      id: 1,
      numeroSequencial: "1/2022",
      comissao: "Comissão 3",
      analista: { nome: "Kenzo Sato" },
      inicioDataReuniao: "2022-12-15 19:23:57.443000",
      fimDataReuniao: "2023-02-07 19:06:26.511000",
      propostas: [{}],
    },
    {
      id: 1,
      numeroSequencial: "4/2022",
      comissao: "Comissão 1",
      analista: { nome: "Kenzo Sato" },
      inicioDataReuniao: "2022-12-15 19:23:57.443000",
      fimDataReuniao: "2023-02-07 19:06:26.511000",
      propostas: [{}],
    },
  ]);

  const [atas, setAtas] = useState([
    {
      numeroSequencial: "1/2022",
      comissao: "Comissão 1",
      analistaResponsavel: "Kenzo Sato",
      data: "01/01/2022",
      horaInicio: "10:00",
      horaFim: "11:00",
    },
    {
      numeroSequencial: "2/2022",
      comissao: "Comissão 2",
      analistaResponsavel: "Kenzo S",
      data: "02/02/2022",
      horaInicio: "10:00",
      horaFim: "11:00",
    },
    {
      numeroSequencial: "3/2022",
      comissao: "Comissão 3",
      analistaResponsavel: "Kenzo S",
      data: "03/03/2022",
      horaInicio: "10:00",
      horaFim: "11:00",
    },
    {
      numeroSequencial: "4/2022",
      comissao: "Comissão 4",
      analistaResponsavel: "Kenzo S",
      data: "04/04/2022",
      horaInicio: "10:00",
      horaFim: "11:00",
    },
  ]);

  // Função para alterar a aba selecionada
  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  const [modalFiltro, setModalFiltro] = useState(false);

  const abrirModalFiltro = () => {
    setModalFiltro(true);
  };

  // Função para ir na tela de detalhes da demanda, salvando a demanda no localStorage
  const verDemanda = (demanda) => {
    if (demanda.status == "ASSESSMENT") {
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
      if (value < 3) {
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
  const abrirDetalhesPauta = () => {
    console.log("clicou");
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
    console.log(listaItens);
    ExportExcelService.exportExcel(listaItens).then((response) => {
      let blob = new Blob([response], { type: "application/excel" });
      let url = URL.createObjectURL(blob);
      let link = document.createElement("a");
      link.href = url;
      link.download = "demandas.xlsx";
      link.click();
    });
  };

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

        {/* Div container para o conteúdo da home */}
        <Box sx={{ width: "90%" }}>
          {/* Sistema de abas */}
          <TabContext value={value}>
            <Box
              className="relative mb-4"
              sx={{ borderBottom: 1, borderColor: "divider.main" }}
            >
              <TabList
                onChange={handleChange}
                aria-label="lab API tabs example"
              >
                <Tab
                  sx={{ color: "text.secondary", fontSize: FontConfig.medium }}
                  label={texts.homeGerencia.demandas}
                  value="1"
                />

                {isGerente && (
                  <Tab
                    sx={{
                      color: "text.secondary",
                      fontSize: FontConfig.medium,
                    }}
                    label={texts.homeGerencia.criarPropostas}
                    value="2"
                  />
                )}

                {isGerente && (
                  <Tab
                    sx={{
                      color: "text.secondary",
                      fontSize: FontConfig.medium,
                    }}
                    label={texts.homeGerencia.propostas}
                    value="3"
                  />
                )}

                {isGerente && (
                  <Tab
                    sx={{
                      color: "text.secondary",
                      fontSize: FontConfig.medium,
                    }}
                    label={texts.homeGerencia.pautas}
                    value="4"
                  />
                )}

                {isGerente && (
                  <Tab
                    sx={{
                      color: "text.secondary",
                      fontSize: FontConfig.medium,
                    }}
                    label={texts.homeGerencia.atas}
                    value="5"
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
                  sx={{ backgroundColor: "input.main", width: "50%" }}
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
                    open={abrirOrdenacao}
                    setOpen={setOpenOrdenacao}
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
                {value < 4 && (
                  <Button
                    id="terceiroDemandas"
                    className="flex gap-1"
                    sx={{
                      backgroundColor: "primary.main",
                      color: "text.white",
                      fontSize: FontConfig.default,
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
                    modo={modoFiltro}
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

            {/* Container para o conteúdo das abas */}
            <Box className="mt-6" id="sextoDemandas">
              {/* Valores para as abas selecionadas */}
              <TabPanel sx={{ padding: 0 }} value="1">
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
                      solicitante: { nome: texts.homeGerencia.demandaParaTour },
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
                  <TabPanel sx={{ padding: 0 }} value="2" onClick={() => {}}>
                    <Ajuda onClick={() => setIsTourCriarPropostasOpen(true)} />
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
                  <TabPanel sx={{ padding: 0 }} value="3" onClick={() => {}}>
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
                  <TabPanel sx={{ padding: 0 }} value="4">
                    <Ajuda onClick={() => setIsTourPautasOpen(true)} />
                    <PautaAtaModoVisualizacao
                      listaPautas={pautas}
                      onItemClick={() => {
                        navigate("/detalhes-pauta");
                      }}
                      nextModoVisualizacao={nextModoVisualizacao}
                    />
                  </TabPanel>
                  <TabPanel sx={{ padding: 0 }} value="5">
                    <Ajuda onClick={() => setIsTourAtasOpen(true)} />
                    <PautaAtaModoVisualizacao
                      listaPautas={pautas}
                      onItemClick={() => {
                        navigate("/detalhes-pauta");
                      }}
                      nextModoVisualizacao={nextModoVisualizacao}
                      isAta={true}
                    />
                  </TabPanel>
                </>
              )}
            </Box>
          </TabContext>
        </Box>
      </Box>
      <Box className="flex justify-end mt-10" sx={{ width: "95%" }}>
        {totalPaginas > 1 || listaItens.length > 20 ? (
          <Paginacao
            totalPaginas={totalPaginas}
            setTamanho={setTamanhoPagina}
            tamanhoPagina={tamanhoPagina}
            tipo={value}
            setPaginaAtual={setPaginaAtual}
          />
        ) : null}
      </Box>
    </FundoComHeader>
  );
};

export default HomeGerencia;
