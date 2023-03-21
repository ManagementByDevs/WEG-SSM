import React, { useState, useEffect, useContext } from "react";
import { useNavigate } from "react-router-dom";

import { Button, Tab, Box, Tooltip, IconButton } from "@mui/material";
import { TabContext, TabList, TabPanel } from "@mui/lab";

import SearchOutlinedIcon from "@mui/icons-material/SearchOutlined";
import SwapVertIcon from "@mui/icons-material/SwapVert";
import FilterAltOutlinedIcon from "@mui/icons-material/FilterAltOutlined";
import AddIcon from "@mui/icons-material/Add";
import ViewListIcon from "@mui/icons-material/ViewList";
import ViewModuleIcon from "@mui/icons-material/ViewModule";

import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";
import UsuarioService from "../../service/usuarioService";
import DemandaService from "../../service/demandaService";

import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Feedback from "../../components/Feedback/Feedback";
import ModalOrdenacao from "../../components/ModalOrdenacao/ModalOrdenacao";
import ModalFiltro from "../../components/ModalFiltro/ModalFiltro";
import Paginacao from "../../components/Paginacao/Paginacao";
import DemandaModoVisualizacao from "../../components/DemandaModoVisualizacao/DemandaModoVisualizacao";
import Ajuda from "../../components/Ajuda/Ajuda";
import Demanda from "../../components/Demanda/Demanda";

import Tour from "reactour";

// import TextLinguage from "../../service/TextLinguage/TextLinguage";

/** Página principal do solicitante */
const Home = () => {
  // useContext para alterar a linguagem do sistema
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // Lista de demandas presentes
  const [listaDemandas, setListaDemandas] = useState([]);

  // Variável que determina o total de páginas de demandas, para o componente de paginação
  const [totalPaginas, setTotalPaginas] = useState(1);

  // Variável com a página atual das demandas, usada na paginação
  const [paginaAtual, setPaginaAtual] = useState(0);

  // Variável que salva o tamanho da página determinada pela paginação para pesquisa de demandas
  const [tamanhoPagina, setTamanhoPagina] = useState(20);

  // Mostra o próximo modo de visualização
  const [nextModoVisualizacao, setNextModoVisualizacao] = useState("TABLE");

  const navigate = useNavigate();

  // Abrir modal feedback de demanda criada
  const [feedbackDemandaCriada, setFeedbackDemandaCriada] = useState(false);

  // Objeto do usuário que está logado no sistema
  const [usuario, setUsuario] = useState({
    id: 0,
    email: "",
    nome: "",
    senha: "",
    tipo_usuario: 0,
    visibilidade: 1,
    departamento: null,
  });

  // Parâmetros para pesquisa das demandas (filtros)
  const [params, setParams] = useState({
    titulo: null,
    solicitante: null,
    gerente: null,
    forum: null,
    departamento: null,
    tamanho: null,
    status: null,
  });

  // String para ordenação das demandas
  const [stringOrdenacao, setStringOrdenacao] = useState("sort=id,asc&");

  // Lista de valores booleanos usada no modal de filtro para determinar qual filtro está selecionado
  const [listaFiltros, setListaFiltros] = useState([
    false,
    false,
    false,
    false,
    false,
    false,
  ]);

  // Valores dos checkboxes no modal de ordenação
  const [ordenacaoScore, setOrdenacaoScore] = useState([false, true]);
  const [ordenacaoTitulo, setOrdenacaoTitulo] = useState([false, false]);
  const [ordenacaoDate, setOrdenacaoDate] = useState([false, false]);

  // UseState para poder visualizar e alterar a aba selecionada
  const [valorAba, setValorAba] = useState("1");

  // Valor do input de pesquisa por título da demanda
  const [valorPesquisa, setValorPesquisa] = useState("");

  // Variável para determinar se o modal de ordenação está aberto
  const [abrirOrdenacao, setOpenOrdenacao] = useState(false);

  // Variável para determinar se o modal de filtragem está aberto
  const [filtroAberto, setFiltroAberto] = useState(false);

  // UseEffect para buscar o usuário e ativar possíveis filtros assim que entrar na página
  useEffect(() => {
    ativarFeedback();
    buscarUsuario();
    arrangePreferences();
  }, []);

  // UseEffect para buscar as demandas sempre que os parâmetros (filtros e ordenação) forem modificados
  useEffect(() => {
    buscarDemandas();
  }, [params]);

  // UseEffect para redefinir os parâmetros quando a ordenação ou a paginação for modificada, consequentemente buscando as demandas
  useEffect(() => {
    setParams({
      titulo: params.titulo,
      solicitante: JSON.parse(params.solicitante),
      gerente: JSON.parse(params.gerente),
      forum: JSON.parse(params.forum),
      departamento: JSON.parse(params.departamento),
      tamanho: params.tamanho,
      status: params.status,
    });
  }, [stringOrdenacao, tamanhoPagina, paginaAtual]);

  // UseEffect para modificar o texto de ordenação para a pesquisa quando um checkbox for acionado no modal de ordenação
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

    setStringOrdenacao(textoNovo);
  }, [ordenacaoTitulo, ordenacaoScore, ordenacaoDate]);

  // UseEffect para atualizar o string de filtro quando algum checkbox for ativado no modal de filtragem
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
    } else {
      atualizarFiltro(null);
    }
  }, [listaFiltros]);

  /** Função para mostrar feedbacks possíveis advindos de outras páginas, utilizando o localStorage */
  const ativarFeedback = () => {
    //Feedback de demanda criada
    if (localStorage.getItem("tipoFeedback") == "1") {
      localStorage.removeItem("tipoFeedback");
      setFeedbackDemandaCriada(true);
    }
  };

  /** Função para buscar o usuário logado no sistema pelo localStorage assim que ele entrar na página */
  const buscarUsuario = () => {
    UsuarioService.getUsuarioById(
      parseInt(localStorage.getItem("usuarioId"))
    ).then((e) => {
      setUsuario(e);
      setParams({ ...params, solicitante: e });
    });
  };

  /** Função para buscar as demandas com os parâmetros e ordenação salvos */
  const buscarDemandas = () => {
    if (params.status != null || params.solicitante != null) {
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
    setParams({
      titulo: params.titulo,
      solicitante: JSON.parse(params.solicitante),
      gerente: JSON.parse(params.gerente),
      forum: JSON.parse(params.forum),
      departamento: JSON.parse(params.departamento),
      tamanho: params.tamanho,
      status: status,
    });
  };

  /** Função para pesquisar novas demandas quando a aba for modificada */
  const atualizarAba = (event, newValue) => {
    setValorAba(newValue);

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

  /** Função para abrir o modal de ordenação */
  const abrirModalOrdenacao = () => {
    setOpenOrdenacao(true);
  };

  /** Função para abrir o modal de filtragem */
  const abrirModalFiltro = () => {
    setFiltroAberto(true);
  };

  /** Função para ir para a tela de detalhes de uma demanda selecionada */
  const verDemanda = (demanda) => {
    navigate("/detalhes-demanda", { state: demanda });
  };

  /** Função para salvar o valor do input de pesquisa quando houver alteração */
  const salvarPesquisa = (e) => {
    setValorPesquisa(e.target?.value);
  };

  /** Função para modificar os parâmetros da demanda ao pesquisar no campo de texto, consequentemente buscando as demandas */
  const pesquisaTitulo = () => {
    setParams({
      titulo: valorPesquisa,
      solicitante: JSON.parse(params.solicitante),
      gerente: JSON.parse(params.gerente),
      forum: JSON.parse(params.forum),
      departamento: JSON.parse(params.departamento),
      tamanho: params.tamanho,
      status: params.status,
    });
  };

  /** Função para "ouvir" um evento de teclado no input de pesquisa e fazer a pesquisa caso seja a tecla "Enter" */
  const eventoTeclado = (e) => {
    if (e.key == "Enter") {
      pesquisaTitulo();
    }
  };

  // useState para abrir e fechar o tour
  const [isTourOpen, setIsTourOpen] = useState(false);

  // Passos do tour
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
      selector: "#segundo",
      content: texts.home.tourAjuda.iconeOredenar,
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
  ];

  // ********************************************** Preferências **********************************************
  /**
   * Função que arruma o modo de visualização das preferências do usuário para o qual ele escolheu por último
   */
  const arrangePreferences = () => {
    let itemsVisualizationMode =
      UsuarioService.getPreferencias().itemsVisualizationMode.toUpperCase();

    // ItemsVisualizationMode é o modo de visualização preferido do usuário, porém o nextModoVisualizao é o próximo modo para o qual será trocado a visualização
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
      {/* Div container */}
      <Tour
        steps={stepsTour}
        isOpen={isTourOpen}
        onRequestClose={() => setIsTourOpen(false)}
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
          {/* Sistema de abas */}
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
                  sx={{ color: "text.secondary", fontSize: FontConfig.medium }}
                  label={texts.home.minhasDemandas}
                  value="1"
                />
                <Tab
                  sx={{ color: "text.secondary", fontSize: FontConfig.medium }}
                  label={texts.home.meuDepartamento}
                  value="2"
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
                      fontSize: FontConfig.medium,
                    }}
                    contentEditable
                    placeholder={texts.home.pesquisarPorTitulo}
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
                    <Tooltip title={texts.home.pesquisar}>
                      <SearchOutlinedIcon
                        onClick={pesquisaTitulo}
                        className="hover:cursor-pointer"
                        sx={{ color: "text.secondary" }}
                      />
                    </Tooltip>

                    {/* Ícone de ordenação */}
                    <Tooltip title={texts.home.ordenacao}>
                      <SwapVertIcon
                        id="segundo"
                        onClick={abrirModalOrdenacao}
                        className="cursor-pointer"
                        sx={{ color: "text.secondary" }}
                      />
                    </Tooltip>

                    {/* Modal de ordenação */}
                    {abrirOrdenacao && (
                      <ModalOrdenacao
                        fecharModal={() => {
                          setOpenOrdenacao(false);
                        }}
                        ordenacaoTitulo={ordenacaoTitulo}
                        setOrdenacaoTitulo={setOrdenacaoTitulo}
                        ordenacaoScore={ordenacaoScore}
                        setOrdenacaoScore={setOrdenacaoScore}
                        ordenacaoDate={ordenacaoDate}
                        setOrdenacaoDate={setOrdenacaoDate}
                      />
                    )}
                  </Box>
                </Box>
                <Box id="terceiro" className="flex gap-2">
                  {/* Botão de filtrar */}
                  {valorAba == 1 && (
                    <Button
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
                      {texts.home.filtrar} <FilterAltOutlinedIcon />
                    </Button>
                  )}
                </Box>
                {/* Modal de filtro */}
                {filtroAberto && (
                  <ModalFiltro
                    fecharModal={() => {
                      setFiltroAberto(false);
                    }}
                    listaFiltros={listaFiltros}
                    setListaFiltros={setListaFiltros}
                  />
                )}
              </Box>

              {/* Botão de criar demanda */}
              <Button
                id="quarto"
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
                {texts.home.criarDemanda}
                <AddIcon />
              </Button>
            </Box>

            {/* Container para o conteúdo das abas */}
            <Box className="mt-6" id="quinto">
              {/* Valores para as abas selecionadas */}
              <TabPanel sx={{ padding: 0 }} value="1">
                <Ajuda onClick={() => setIsTourOpen(true)} />
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
                          nome: texts.home.nomeDoSolicitante,
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
                <DemandaModoVisualizacao
                  listaDemandas={listaDemandas}
                  onDemandaClick={verDemanda}
                  myDemandas={false}
                  nextModoVisualizacao={nextModoVisualizacao}
                />
              </TabPanel>
            </Box>
          </TabContext>
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
