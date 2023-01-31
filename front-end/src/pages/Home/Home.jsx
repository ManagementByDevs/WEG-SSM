import React, { useState, useEffect, useContext } from "react";
import { useNavigate } from "react-router-dom";
import { useLocation } from "react-router-dom";

import { Button, Tab, Box, Snackbar, Alert, Tooltip } from "@mui/material";
import { TabContext, TabList, TabPanel } from "@mui/lab";
import SearchOutlinedIcon from "@mui/icons-material/SearchOutlined";
import SwapVertIcon from "@mui/icons-material/SwapVert";
import FilterAltOutlinedIcon from "@mui/icons-material/FilterAltOutlined";
import AddIcon from "@mui/icons-material/Add";

import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Demanda from "../../components/Demanda/Demanda";
import Feedback from "../../components/Feedback/Feedback";

import FontContext from "../../service/FontContext";
import FontConfig from "../../service/FontConfig";
import ModalOrdenacao from "../../components/ModalOrdenacao/ModalOrdenacao";

import UsuarioService from "../../service/usuarioService";
import DemandaService from "../../service/demandaService";
import ModalFiltro from "../../components/ModalFiltro/ModalFiltro";
import Paginacao from "../../components/Paginacao/Paginacao";



const Home = () => {
  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // Lista de demandas presente
  const [listaDemandas, setListaDemandas] = useState([]);
  const [totalPaginas, setTotalPaginas] = useState(1);

  const [paginaAtual, setPaginaAtual] = useState(0);
  const [tamanhoPagina, setTamanhoPagina] = useState(20);

  // Abrir modal feedback de demanda criada

  const navigate = useNavigate();
  const location = useLocation();

  const [feedbackDemandaCriada, setFeedbackDemandaCriada] = useState(false);

  useEffect(() => {
    if (localStorage.getItem('tipoFeedback') == "1") {
      setFeedbackDemandaCriada(true);
    }
  }, []);

  // Usuário que está logado no sistema
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
  const [ordenacao, setOrdenacao] = useState("sort=id,asc&");

  const [listaFiltros, setListaFiltros] = useState([false, false, false, false, false]);

  // Valores dos checkboxes no modal de ordenação
  const [ordenacaoTitulo, setOrdenacaoTitulo] = useState([false, false]);
  const [ordenacaoScore, setOrdenacaoScore] = useState([false, false]);
  const [ordenacaoDate, setOrdenacaoDate] = useState([false, false]);

  // UseState para poder visualizar e alterar a aba selecionada
  const [value, setValue] = useState("1");

  // Valor do input de pesquisa
  const [valorPesquisa, setValorPesquisa] = useState("");

  // UseEffect para buscar o usuário assim que entrar na página
  useEffect(() => {
    buscarUsuario();
  }, []);

  // UseEffect para iniciar os parâmetros para busca da demanda (filtrando pelo usuário)
  useEffect(() => {
    setParams({ ...params, solicitante: usuario });
  }, [usuario]);

  // UseEffect para buscar as demandas sempre que os parâmetros (filtros) forem modificados
  useEffect(() => {
    buscarDemandas();
  }, [params]);

  // UseEffect para redefinir os parâmteros quando a ordenação for modificada
  useEffect(() => {
    if (params.solicitante != null) {
      setParams({ ...params, solicitante: usuario });
    } else {
      setParams({ ...params, departamento: usuario.departamento });
    }
  }, [ordenacao]);

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

  // UseEffect para atualizar o string de filtro quando algum checkbox for ativado
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
    } else {
      atualizarFiltro(null);
    }
  }, [listaFiltros]);

  // UseEffect para buscar demandas quando a paginação for modificada
  useEffect(() => {
    setParams({ titulo: params.titulo, solicitante: JSON.parse(params.solicitante), gerente: JSON.parse(params.gerente), forum: JSON.parse(params.forum), departamento: JSON.parse(params.departamento), tamanho: params.tamanho, status: params.status })
  }, [tamanhoPagina, paginaAtual])

  // Função para buscar o usuário logado no sistema
  const buscarUsuario = () => {
    UsuarioService.getUsuarioById(
      parseInt(localStorage.getItem("usuarioId"))
    ).then((e) => {
      setUsuario(e);
    });
  };

  // Função para buscar as demandas com os parâmetros e ordenação
  const buscarDemandas = () => {
    if (params.departamento != null || params.solicitante != null) {
      DemandaService.getPage(params, ordenacao + "size=" + tamanhoPagina + "&page=" + paginaAtual).then((e) => {
        setTotalPaginas(e.totalPages);
        setListaDemandas(e.content);
      });
    }
  };

  // Função para atualizar o filtro de status quando modificado no modal de filtros
  const atualizarFiltro = (status) => {
    if (params.solicitante != null) {
      setParams({ ...params, solicitante: usuario, status: status });
    } else {
      setParams({ ...params, departamento: usuario.departamento, status: status });
    }
  };

  // Função para alterar a aba selecionada
  const handleChange = (event, newValue) => {
    setValue(newValue);
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

  const [state, setState] = React.useState({
    open: false,
  });

  const { open } = state;

  const handleClick = (newState) => () => {
    setState({ open: true, ...newState });
  };

  const handleClose = () => {
    setState({ ...state, open: false });
  };

  const [abrirOrdenacao, setOpenOrdenacao] = useState(false);
  const [abrirFiltro, setOpenFiltro] = useState(false);

  const abrirModalOrdenacao = () => {
    setOpenOrdenacao(true);
  };

  const abrirModalFiltro = () => {
    setOpenFiltro(true);
  };

  // Função para ir na tela de detalhes da demanda, salvando a demanda no localStorage
  const verDemanda = (demanda) => {
    navigate("/detalhes-demanda", { state: demanda });
  };

  // Função para salvar o input de pesquisa quando houver alteração
  const salvarPesquisa = (e) => {
    setValorPesquisa(e.target.value);
  };

  // Função para modificar os parâmetros da demanda ao pesquisar no campo de texto
  const pesquisaTitulo = () => {
    if (params.solicitante != null) {
      setParams({ ...params, titulo: valorPesquisa, solicitante: usuario });
    } else {
      setParams({
        ...params,
        titulo: valorPesquisa,
        departamento: usuario.departamento,
      });
    }
  };

  // Função para "ouvir" um evento de teclado no input de pesquisa e fazer a pesquisa caso seja a tecla "Enter"
  const eventoTeclado = (e) => {
    if (e.key == "Enter") {
      pesquisaTitulo();
    }
  };

  return (
    // Container pai
    <FundoComHeader>
      {/* Div container */}
      <Box
        className="flex justify-center mt-8"
        sx={{ backgroundColor: "background.default", width: "100%" }}
      >
        <Feedback open={feedbackDemandaCriada} handleClose={() => {
          setFeedbackDemandaCriada(false);
        }} status={"sucesso"} mensagem={"Demanda criada com sucesso!"} />

        {/* Div container para o conteúdo da home */}
        <Box sx={{ width: "90%" }}>
          {/* Sistema de abas */}
          <TabContext value={value}>
            <Box
              className="mb-4"
              sx={{ borderBottom: 1, borderColor: "divider.main" }}
            >
              <TabList
                onChange={handleChange}
                aria-label="lab API tabs example"
              >
                <Tab
                  sx={{ color: "text.secondary", fontSize: FontConfig.medium }}
                  label="Minhas Demandas"
                  value="1"
                />
                <Tab
                  sx={{ color: "text.secondary", fontSize: FontConfig.medium }}
                  label="Meu Departamento"
                  value="2"
                />
              </TabList>
            </Box>

            {/* Container das ações abaixo das abas (input de pesquisa, filtrar e criar demanda) */}
            <Box className="flex justify-between w-full">
              {/* Container para o input e botão de filtrar */}
              <Box className="flex gap-4 w-2/4">
                {/* Input de pesquisa */}
                <Box
                  className="flex justify-between items-center border px-3 py-1"
                  sx={{ backgroundColor: "input.main", width: "50%" }}
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
                    placeholder="Pesquisar por título..."
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
                    <Tooltip title="Pesquisar">
                      <SearchOutlinedIcon
                        onClick={pesquisaTitulo}
                        className="hover:cursor-pointer"
                        sx={{ color: "text.secondary" }}
                      />
                    </Tooltip>

                    {/* Ícone de ordenação */}
                    <Tooltip title="Ordenação">
                      <SwapVertIcon
                        onClick={abrirModalOrdenacao}
                        className="cursor-pointer"
                        sx={{ color: "text.secondary" }}
                      />
                    </Tooltip>

                    {/* Modal de ordenação */}
                    {abrirOrdenacao && (
                      <ModalOrdenacao
                        open={abrirOrdenacao}
                        setOpen={setOpenOrdenacao}
                        tipoComponente='demanda'

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

                {/* Botão de filtrar */}
                {value == 1 && (
                  <Button
                    sx={{
                      backgroundColor: "primary.main",
                      color: "text.white",
                      fontSize: FontConfig.default,
                    }}
                    onClick={abrirModalFiltro}
                    variant="contained"
                    disableElevation
                  >
                    Filtrar <FilterAltOutlinedIcon />
                  </Button>

                )}

                {/* Modal de filtro */}
                {abrirFiltro && (
                  <ModalFiltro
                    setParams={atualizarFiltro}
                    open={abrirFiltro}
                    setOpen={setOpenFiltro}
                    filtroDemanda={true}

                    listaFiltros={listaFiltros}
                    setListaFiltros={setListaFiltros}
                  />
                )}
              </Box>

              {/* Botão de criar demanda */}
              <Button
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
                Criar demanda
                <AddIcon />
              </Button>
            </Box>

            {/* Container para o conteúdo das abas */}
            <Box className="mt-6">
              {/* Valores para as abas selecionadas */}
              <TabPanel sx={{ padding: 0 }} value="1">
                <Box
                  sx={{
                    display: "grid",
                    gap: "1rem",
                    gridTemplateColumns: "repeat(auto-fit, minmax(650px, 1fr))",
                  }}
                >
                  {listaDemandas?.map((e, index) => (
                    <Demanda
                      key={index}
                      demanda={e}
                      onClick={() => {
                        verDemanda(e);
                      }}
                    />
                  ))}
                </Box>
              </TabPanel>
              <TabPanel sx={{ padding: 0 }} value="2">
                <Box
                  sx={{
                    display: "grid",
                    gap: "1rem",
                    gridTemplateColumns: "repeat(auto-fit, minmax(650px, 1fr))",
                  }}
                >
                  {listaDemandas?.map((e, index) => (
                    <Demanda
                      key={index}
                      demanda={e}
                      onClick={() => {
                        verDemanda(e);
                      }}
                    />
                  ))}
                </Box>
              </TabPanel>
            </Box>
          </TabContext>
        </Box>
      </Box>
      <Box className="flex justify-end mt-10" sx={{ width: "95%" }}>
        {(totalPaginas > 1 || listaDemandas.length > 20) && value == "1" ? (
          <Paginacao totalPaginas={totalPaginas} setTamanho={setTamanhoPagina} tamanhoPagina={tamanhoPagina} tipo={value} setPaginaAtual={setPaginaAtual} />
        ) : null}
      </Box>
    </FundoComHeader>
  );
};

export default Home;
