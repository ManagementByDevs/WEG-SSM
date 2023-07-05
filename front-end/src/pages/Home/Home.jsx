import React, { useState, useEffect, useContext, useRef } from "react";
import { useNavigate } from "react-router-dom";

import Tour from "reactour";
import ClipLoader from "react-spinners/ClipLoader";

import { Button, Tab, Box, Tooltip, IconButton, Drawer } from "@mui/material";
import { TabContext, TabList, TabPanel } from "@mui/lab";

import SearchOutlinedIcon from "@mui/icons-material/SearchOutlined";
import SwapVertIcon from "@mui/icons-material/SwapVert";
import FilterAltOutlinedIcon from "@mui/icons-material/FilterAltOutlined";
import AddIcon from "@mui/icons-material/Add";
import ViewListIcon from "@mui/icons-material/ViewList";
import ViewModuleIcon from "@mui/icons-material/ViewModule";
import MicNoneOutlinedIcon from "@mui/icons-material/MicNoneOutlined";
import MicOutlinedIcon from "@mui/icons-material/MicOutlined";
import CloseIcon from "@mui/icons-material/Close";

import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Feedback from "../../components/Feedback/Feedback";
import SideBarFiltroHome from "../../components/SideBarFiltroHome/SideBarFiltroHome";
import Paginacao from "../../components/Paginacao/Paginacao";
import DemandaModoVisualizacao from "../../components/DemandaModoVisualizacao/DemandaModoVisualizacao";
import Ajuda from "../../components/Ajuda/Ajuda";
import Demanda from "../../components/Demanda/Demanda";

import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";
import UsuarioService from "../../service/usuarioService";
import DemandaService from "../../service/demandaService";
import CookieService from "../../service/cookieService";
import SpeechSynthesisContext from "../../service/SpeechSynthesisContext";
import { SpeechRecognitionContext } from "../../service/SpeechRecognitionService";

/** Página principal do solicitante */
const Home = () => {
  /** Context para alterar o tamanho da fonte */
  const { FontConfig } = useContext(FontContext);

  /** useContext para alterar a linguagem do sistema */
  const { texts } = useContext(TextLanguageContext);

  /** Context para ler o texto da tela */
  const { lendoTexto, lerTexto, librasAtivo } = useContext(SpeechSynthesisContext);

  /** Context para obter a função de leitura de texto */
  const { startRecognition, escutar, localClique, palavrasJuntas } = useContext(
    SpeechRecognitionContext
  );

  /** Variável para navegação entre páginas */
  const navigate = useNavigate();

  /** Lista de demandas usadas para a listagem */
  const [listaDemandas, setListaDemandas] = useState([]);

  /** Variável que determina o total de páginas de demandas, para o componente de paginação */
  const [totalPaginas, setTotalPaginas] = useState(1);

  /** Variável com a página atual das demandas, usada na paginação */
  const [paginaAtual, setPaginaAtual] = useState(0);

  /** Variável que salva o tamanho da página determinada pela paginação para pesquisa de demandas */
  const [tamanhoPagina, setTamanhoPagina] = useState(20);

  /** Mostra o próximo modo de visualização */
  const [nextModoVisualizacao, setNextModoVisualizacao] = useState("TABLE");

  /** Abrir modal feedback de demanda criada */
  const [feedbackDemandaCriada, setFeedbackDemandaCriada] = useState(false);

  /** Objeto do usuário que está logado no sistema */
  const [usuario, setUsuario] = useState({
    id: 0,
    email: "",
    nome: "",
    senha: "",
    tipo_usuario: 0,
    visibilidade: 1,
    departamento: null,
  });

  /** Parâmetros para pesquisa das demandas (filtros) */
  const [params, setParams] = useState({
    titulo: null,
    solicitante: null,
    gerente: null,
    forum: null,
    departamento: null,
    tamanho: null,
    status: null,
  });

  /** String para ordenação das demandas */
  const [stringOrdenacao, setStringOrdenacao] = useState("sort=id,asc&");

  /** Lista de valores booleanos usada no modal de filtro para determinar qual filtro está selecionado */
  const [listaFiltros, setListaFiltros] = useState([
    false,
    false,
    false,
    false,
    false,
    false,
    false,
  ]);

  /** Valores dos checkboxes de Score no modal de ordenação */
  const [ordenacaoScore, setOrdenacaoScore] = useState([false, true]);

  /** Valores dos checkboxes de Titulo no modal de ordenação */
  const [ordenacaoTitulo, setOrdenacaoTitulo] = useState([false, false]);

  /** Valores dos checkboxes de Data no modal de ordenação */
  const [ordenacaoDate, setOrdenacaoDate] = useState([false, false]);

  /** UseState para poder visualizar e alterar a aba selecionada */
  const [valorAba, setValorAba] = useState("1");

  /** Valor do input de pesquisa por título da demanda */
  let valorPesquisa = "";

  /** Variável de referência ao input de pesquisa */
  const inputPesquisa = useRef(null);

  /** Variável booleana que determina se o input de pesquisa está vazio ou não, usado para a limpagem do input */
  const [inputPreenchido, setInputPreenchido] = useState(false);

  /** Variável para determinar se o modal de ordenação está aberto */
  const [abrirOrdenacao, setOpenOrdenacao] = useState(false);

  /** Variável para determinar se o modal de filtragem está aberto */
  const [filtroAberto, setFiltroAberto] = useState(false);

  /** Variável para esconder a lista de itens e mostrar um ícone de carregamento enquanto busca os itens no banco */
  const [carregamentoItens, setCarregamentoItens] = useState(true);

  /** Variável para esconder a página e mostrar um ícone de carregamento enquanto busca as preferências do usuário */
  const [carregamentoPreferencias, setCarregamentoPreferencias] =
    useState(true);

  /** useState para abrir e fechar o tour */
  const [isTourOpen, setIsTourOpen] = useState(false);

  /** useState para abrir e fechar o tour da aba de departamento */
  const [isTourOpenDepartamento, setIsTourOpenDepartamento] = useState(false);

  /** Passos do tour da aba de minhas demandas */
  const stepsTour = [
    {
      selector: "#primeiro",
      content: texts.home.tourAjuda.barraDePesquisa,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#terceiro",
      content: texts.home.tourAjuda.botaoFiltrar,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#sexto",
      content: texts.home.tourAjuda.modoVisualizacao,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#quarto",
      content: texts.home.tourAjuda.criarNovaDemanda,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#quinto",
      content: texts.home.tourAjuda.areaDemanda,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#oitavo",
      content: texts.home.tourAjuda.statusDemanda,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#setimo",
      content: texts.home.tourAjuda.botaoMotivo,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#nono",
      content: texts.home.tourAjuda.botaoChat,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
  ];

  /** Passos do tour da aba de departamento */
  const stepsTourDepartamento = [
    {
      selector: "#primeiro",
      content: texts.home.tourAjuda.barraDePesquisa,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#sexto",
      content: texts.home.tourAjuda.modoVisualizacao,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#quarto",
      content: texts.home.tourAjuda.criarNovaDemanda,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#quinto",
      content: texts.home.tourAjuda.areaDemanda,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
  ];

  /** UseEffect para buscar o usuário e ativar possíveis filtros assim que entrar na página */
  useEffect(() => {
    ativarFeedback();
    buscarUsuario();
    arrangePreferences();
  }, []);

  /** UseEffect utilizado para buscar as preferências do usuário */
  useEffect(() => {
    arrangePreferences();
  }, [usuario]);

  /** UseEffect para buscar as demandas sempre que os parâmetros (filtros, ordenação ou páginas) forem modificados */
  useEffect(() => {
    buscarDemandas();
  }, [params, stringOrdenacao, tamanhoPagina, paginaAtual]);

  /** UseEffect para modificar o texto de ordenação para a pesquisa quando um checkbox for acionado no modal de ordenação */
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

    setStringOrdenacao(textoNovo);
  }, [ordenacaoTitulo, ordenacaoScore, ordenacaoDate]);

  /** UseEffect para atualizar o string de filtro quando algum checkbox for ativado no modal de filtragem */
  useEffect(() => {
    if (listaFiltros[0]) {
      atualizarFiltro("ASSESSMENT");
    } else if (listaFiltros[1]) {
      atualizarFiltro("CANCELLED");
    } else if (listaFiltros[2]) {
      atualizarFiltro("BACKLOG_EDICAO");
    } else if (listaFiltros[3]) {
      atualizarFiltro("BACKLOG_REVISAO");
    } else if (listaFiltros[4]) {
      atualizarFiltro("BACKLOG_APROVACAO");
    } else if (listaFiltros[5]) {
      atualizarFiltro("ASSESSMENT_APROVACAO");
    } else if (listaFiltros[6]) {
      atualizarFiltro("DONE");
    } else {
      atualizarFiltro(null);
    }
  }, [listaFiltros]);

  /** UseEffect para retirar o ícone de carregamento quando as demandas forem atualizadas */
  useEffect(() => {
    setCarregamentoItens(false);
  }, [listaDemandas]);

  /** Função para mostrar feedbacks possíveis advindos de outras páginas, utilizando o localStorage */
  const ativarFeedback = () => {
    //Feedback de demanda criada
    if (localStorage.getItem("tipoFeedback") == "1") {
      setFeedbackDemandaCriada(true);
      localStorage.removeItem("tipoFeedback");
    }
  };

  /** Função para buscar o usuário logado no sistema pelos cookies assim que ele entrar na página */
  const buscarUsuario = () => {
    UsuarioService.getUsuarioByEmail(CookieService.getCookie("jwt").sub).then(
      (e) => {
        setUsuario(e);
        setParams({ ...params, solicitante: e });
      }
    );
  };

  /** Função que verifica se os parâmetros estão nulos */
  const isParamsNull = () => {
    return Object.values(params).every((e) => e == null);
  };

  /** Função para buscar as demandas com os parâmetros e ordenação salvos */
  const buscarDemandas = () => {
    // Verifica se os parâmetros estão nulos, se estiverem, não faz nada
    if (isParamsNull()) {
      return;
    }

    // Verifica o solicitante está em JSON, se está, transforma para um objeto
    if (typeof params.solicitante == "string") {
      params.solicitante = JSON.parse(params.solicitante);
    }

    // Verifica se tem um solicitante, senão ele busca as demandas com um usuário vazio
    if (params.solicitante && params.solicitante.id == 0) {
      return;
    }

    setCarregamentoItens(true);
    if (
      params.titulo ||
      params.solicitante ||
      params.gerente ||
      params.forum ||
      params.departamento ||
      params.tamanho ||
      params.status
    ) {
      DemandaService.getPage(
        params,
        stringOrdenacao + "size=" + tamanhoPagina + "&page=" + paginaAtual
      ).then((e) => {
        setTotalPaginas(e.totalPages);
        setListaDemandas(e.content);
      });
    }
  };

  /** Função para atualizar o filtro de status quando modificado no modal de filtros */
  const atualizarFiltro = (status) => {
    setParams({ ...params, status: status });
  };

  /** Função para pesquisar novas demandas quando a aba for modificada */
  const atualizarAba = (event, newValue) => {
    setValorAba(newValue);
    setPaginaAtual(0);

    // Ao trocar a aba, a forma de pesquisar demandas irá mudar (usuário / departamento)
    if (newValue == 1) {
      setParams({ ...params, departamento: null, solicitante: usuario });
    } else {
      setParams({
        ...params,
        solicitante: null,
        departamento: usuario?.departamento,
      });
    }
  };

  /** Função para ir para a tela de detalhes de uma demanda selecionada */
  const verDemanda = (demanda) => {
    navigate("/detalhes-demanda", { state: demanda.id });
  };

  /** Função para salvar o valor do input de pesquisa quando houver alteração */
  const salvarPesquisa = (e) => {
    valorPesquisa = e.target?.value;
    if (valorPesquisa != "") {
      if (!inputPreenchido) {
        setInputPreenchido(true);
      }
    } else {
      if (inputPreenchido) {
        setInputPreenchido(false);
      }
    }
  };

  /** Função para modificar os parâmetros da demanda ao pesquisar no campo de texto, consequentemente buscando as demandas */
  const pesquisaTitulo = () => {
    setParams({
      ...params,
      titulo: !parseInt(valorPesquisa) ? valorPesquisa : null,
      id:
        parseInt(valorPesquisa) && valorAba < 4
          ? parseInt(valorPesquisa)
          : null,
    });
  };

  // ********************************************** Preferências ********************************************** //

  /** Função que arruma o modo de visualização das preferências do usuário para o qual ele escolheu por último */
  const arrangePreferences = () => {
    UsuarioService.getPreferencias(CookieService.getCookie("jwt").sub).then(
      (preferencias) => {
        let itemsVisualizationMode =
          preferencias?.itemsVisualizationMode?.toUpperCase();

        setValorAba(preferencias?.abaPadrao);
        // ItemsVisualizationMode é o modo de visualização preferido do usuário, porém o nextModoVisualizao é o próximo modo para o qual será trocado a visualização
        if (itemsVisualizationMode == nextModoVisualizacao) {
          setNextModoVisualizacao("GRID");
        }

        // Timeout para retirar o carregamento após as preferências serem atualizadas
        setTimeout(() => {
          setCarregamentoPreferencias(false);
        }, 500);
      }
    );
  };

  /** Função que salva a nova preferência do usuário */
  const saveNewPreference = () => {
    if (!CookieService.getCookie("jwt")) return;
    UsuarioService.getUsuarioByEmail(CookieService.getCookie("jwt").sub).then(
      (user) => {
        let preferencias = JSON.parse(user.preferencias);

        preferencias.itemsVisualizationMode =
          nextModoVisualizacao == "TABLE" ? "grid" : "table";

        user.preferencias = JSON.stringify(preferencias);

        UsuarioService.updateUser(user.id, user).then((e) => {});
      }
    );
  };

  /** UseEffect para salvar as novas preferências do usuário */
  useEffect(() => {
    saveNewPreference("itemsVisualizationMode");
  }, [nextModoVisualizacao]);

  /** UseEffect utilizado para salvar as preferências caso mude de aba */
  useEffect(() => {
    saveNewPreference("abaPadrao");
  }, [valorAba]);

  // ********************************************** Fim Preferências ********************************************** //

  /** useEffect utilizado para atualizar o input de pesquisa com o texto reconhecido */
  useEffect(() => {
    if (localClique == "demanda") {
      valorPesquisa = palavrasJuntas;
    }
  }, [palavrasJuntas]);

  const [state, setState] = useState({
    right: false,
  });

  const toggleDrawer = (anchor, open) => (event) => {
    if (
      event.type === "keydown" &&
      (event.key === "Tab" || event.key === "Shift")
    ) {
      return;
    }

    setState({ ...state, [anchor]: open });
  };

  return (
    <FundoComHeader>
      {/* Div container */}
      {/* Tour de ajuda para as minhas demandas*/}
      <Tour
        steps={stepsTour}
        isOpen={isTourOpen}
        onRequestClose={() => setIsTourOpen(false)}
        accentColor="#00579D"
        rounded={10}
        showCloseButton={false}
      />
      {/* Tour de ajuda para o departamento */}
      <Tour
        steps={stepsTourDepartamento}
        isOpen={isTourOpenDepartamento}
        onRequestClose={() => setIsTourOpenDepartamento(false)}
        accentColor="#00579D"
        rounded={10}
        showCloseButton={false}
      />

      <Box
        className="flex justify-center mt-8"
        sx={{ backgroundColor: "background.default", width: "100%" }}
      >
        {/* Feedback de demanda criada */}
        <Feedback
          open={feedbackDemandaCriada}
          handleClose={() => {
            setFeedbackDemandaCriada(false);
          }}
          status={"sucesso"}
          mensagem={texts.home.demandaCriadaComSucesso}
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
                className="mb-4 relative"
                sx={{ borderBottom: 1, borderColor: "divider.main" }}
              >
                <TabList
                  onChange={atualizarAba}
                  aria-label="lab API tabs example"
                >
                  <Tab
                    sx={{
                      color: "text.secondary",
                      fontSize: FontConfig?.medium,
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
                      fontSize: FontConfig?.medium,
                    }}
                    label={texts.home.meuDepartamento}
                    value="2"
                    onClick={() => {
                      lerTexto(texts.home.meuDepartamento);
                    }}
                  />
                </TabList>

                <Box className="absolute right-0 top-2" id="sexto">
                  {nextModoVisualizacao == "TABLE" ? (
                    <Tooltip title={texts.home.visualizacaoEmTabela}>
                      <IconButton
                        onClick={() => {
                          setNextModoVisualizacao("GRID");
                        }}
                      >
                        <ViewListIcon color="primary" />
                      </IconButton>
                    </Tooltip>
                  ) : (
                    <Tooltip title={texts.home.visualizacaoEmBloco}>
                      <IconButton
                        onClick={() => {
                          setNextModoVisualizacao("TABLE");
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
                    id="primeiro"
                    className="flex justify-between items-center border px-3 py-1"
                    sx={{
                      backgroundColor: "input.main",
                      width: "50%",
                      minWidth: "10rem",
                    }}
                  >
                    {/* Input de pesquisa */}
                    <Box
                      className="w-full"
                      component="input"
                      sx={{
                        backgroundColor: "input.main",
                        outline: "none",
                        color: "text.primary",
                        fontSize: FontConfig?.medium,
                      }}
                      ref={inputPesquisa}
                      contentEditable
                      placeholder={texts.home.pesquisarPorTituloOuNumero}
                      onKeyDown={(e) => {
                        if (e.key == "Enter") {
                          valorPesquisa = inputPesquisa.current.value;
                          pesquisaTitulo();
                        }
                      }}
                      onChange={(e) => {
                        salvarPesquisa(e);
                      }}
                    />

                    {/* Container para os ícones */}
                    <Box className="flex gap-2">
                      {inputPreenchido ? (
                        <Tooltip
                          className="hover:cursor-pointer"
                          title={texts.homeGerencia.limparBusca}
                          onClick={() => {
                            valorPesquisa = "";
                            inputPesquisa.current.value = "";
                            pesquisaTitulo();
                            setInputPreenchido(false);
                          }}
                        >
                          <CloseIcon
                            sx={{
                              cursor: "pointer",
                              color: "text.secondary",
                              fontSize: "1.3rem",
                            }}
                          />
                        </Tooltip>
                      ) : null}

                      {/* Ícone de microfone */}
                      <Tooltip
                        className="hover:cursor-pointer"
                        title={texts.homeGerencia.gravarAudio}
                        onClick={() => {
                          startRecognition("demanda");
                        }}
                      >
                        {escutar ? (
                          <MicOutlinedIcon
                            sx={{
                              cursor: "pointer",
                              color: "primary.main",
                              fontSize: "1.3rem",
                            }}
                          />
                        ) : (
                          <MicNoneOutlinedIcon
                            sx={{
                              cursor: "pointer",
                              color: "text.secondary",
                              fontSize: "1.3rem",
                            }}
                          />
                        )}
                      </Tooltip>

                      {/* Ícone de pesquisa */}
                      <Tooltip title={texts.home.pesquisar}>
                        <SearchOutlinedIcon
                          onClick={() => {
                            valorPesquisa = inputPesquisa.current.value;
                            pesquisaTitulo();
                          }}
                          className="hover:cursor-pointer"
                          sx={{ color: "text.secondary" }}
                        />
                      </Tooltip>
                    </Box>
                  </Box>
                  <Box id="terceiro" className="flex gap-2">
                    <React.Fragment key="right">
                      <Button
                        sx={{
                          backgroundColor: "primary.main",
                          color: "text.white",
                          fontSize: FontConfig?.default,
                        }}
                        onClick={toggleDrawer("right", true)}
                        variant="contained"
                        disableElevation
                      >
                        <Tooltip title={texts.home.filtrar}>
                          <FilterAltOutlinedIcon />
                        </Tooltip>
                      </Button>
                      <Drawer
                        anchor={"right"}
                        open={state["right"]}
                        onClose={toggleDrawer("right", false)}
                      >
                        <SideBarFiltroHome
                          listaFiltros={listaFiltros}
                          setListaFiltros={setListaFiltros}
                          tipoUsuario={usuario.tipo_usuario}
                          ordenacaoTitulo={ordenacaoTitulo}
                          setOrdenacaoTitulo={setOrdenacaoTitulo}
                          ordenacaoScore={ordenacaoScore}
                          setOrdenacaoScore={setOrdenacaoScore}
                          ordenacaoDate={ordenacaoDate}
                          setOrdenacaoDate={setOrdenacaoDate}
                          valorAba={valorAba}
                        />
                      </Drawer>
                    </React.Fragment>
                  </Box>
                </Box>
                {/* Botão de criar demanda */}
                <Button
                  id="quarto"
                  className="gap-2"
                  sx={{
                    backgroundColor: "primary.main",
                    color: "text.white",
                    fontSize: FontConfig?.default,
                    maxHeight: "2.5rem",
                    minWidth: "10.5rem",
                  }}
                  variant="contained"
                  disableElevation
                  onClick={() => {
                    if (!lendoTexto && !librasAtivo) {
                      navigate("/criar-demanda");
                    } else if (librasAtivo) {
                    } else {
                      lerTexto(texts.home.criarDemanda);
                    }
                  }}
                >
                  {texts.home.criarDemanda}
                  <AddIcon />
                </Button>
              </Box>

              <Box className="mt-6" id="quinto">
                {carregamentoItens ? (
                  <Box className="mt-6 w-full h-full flex justify-center items-center">
                    <ClipLoader color="#00579D" size={110} />
                  </Box>
                ) : (
                  <>
                    <TabPanel sx={{ padding: 0 }} value="1">
                      <Ajuda
                        onClick={() => {
                          setIsTourOpen(true);
                          setState({ ...state, ["right"]: false });
                        }}
                      />
                      <Box>
                        {isTourOpen ? (
                          <Demanda
                            demanda={{
                              id: 0,
                              titulo: texts.home.demandaTour,
                              problema: texts.home.esseUmExemploDeDemanda,
                              proposta: texts.home.esseUmExemploDeDemanda,
                              motivoRecusa: texts.home.esseUmExemploDeDemanda,
                              status: "BACKLOG_EDICAO",
                              data: "10/10/10",
                              solicitante: {
                                id: 1,
                                tour: true,
                              },
                            }}
                          />
                        ) : (
                          <DemandaModoVisualizacao
                            listaDemandas={listaDemandas}
                            onDemandaClick={verDemanda}
                            myDemandas={true}
                            nextModoVisualizacao={nextModoVisualizacao}
                          />
                        )}
                      </Box>
                    </TabPanel>
                    <TabPanel sx={{ padding: 0 }} value="2">
                      <Ajuda
                        onClick={() => {
                          setIsTourOpenDepartamento(true);
                          setState({ ...state, ["right"]: false });
                        }}
                      />
                      <Box>
                        {isTourOpenDepartamento ? (
                          <Demanda
                            demanda={{
                              id: 0,
                              titulo: texts.home.demandaTour,
                              problema: texts.home.esseUmExemploDeDemanda,
                              proposta: texts.home.esseUmExemploDeDemanda,
                              solicitante: {
                                id: 1,
                                nome: texts.home.nomeDoSolicitante,
                                tour: false,
                              },
                            }}
                          />
                        ) : (
                          <DemandaModoVisualizacao
                            listaDemandas={listaDemandas}
                            onDemandaClick={verDemanda}
                            myDemandas={false}
                            nextModoVisualizacao={nextModoVisualizacao}
                          />
                        )}
                      </Box>
                    </TabPanel>
                  </>
                )}
              </Box>
            </TabContext>
          )}
        </Box>
      </Box>
      <Box className="flex justify-end mt-10" sx={{ width: "95%" }}>
        {totalPaginas > 1 || listaDemandas.length > 20 ? (
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

export default Home;
