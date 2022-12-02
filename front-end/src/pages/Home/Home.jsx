import React, { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom';

import { Button, Tab, Box, Snackbar, Alert } from "@mui/material";
import { TabContext, TabList, TabPanel } from "@mui/lab";
import SearchOutlinedIcon from "@mui/icons-material/SearchOutlined";
import SwapVertIcon from "@mui/icons-material/SwapVert";
import FilterAltOutlinedIcon from "@mui/icons-material/FilterAltOutlined";
import AddIcon from "@mui/icons-material/Add";

import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Demanda from "../../components/Demanda/Demanda";
import Feedback from "../../components/Feedback/Feedback";

import FontConfig from "../../service/FontConfig";
import ModalOrdenacao from "../../components/ModalOrdenacao/ModalOrdenacao";

import UsuarioService from "../../service/usuarioService"
import DemandaService from "../../service/demandaService";
import ModalFiltro from '../../components/ModalFiltro/ModalFiltro';

const Home = () => {

  // Lista de demandas presente
  const [listaDemandas, setListaDemandas] = useState([]);

  // Usuário que está logado no sistema
  const [usuario, setUsuario] = useState({ id: 0, email: "", nome: "", senha: "", tipo_usuario: 0, visibilidade: 1, departamento: null });

  // Parâmetros para pesquisa das demandas (filtros)
  const [params, setParams] = useState({ titulo: null, solicitante: null, gerente: null, forum: null, departamento: null, tamanho: null, status: null });

  // String para ordenação das demandas
  const [page, setPage] = useState("size=20&page=0");
  const [ordenacao, setOrdenacao] = useState("sort=id,asc&");
  const [filtroAtual, setFiltroAtual] = useState(null);

  // UseState para poder visualizar e alterar a aba selecionada
  const [value, setValue] = useState('1');

  // Valor do input de pesquisa
  const [valorPesquisa, setValorPesquisa] = useState("");

  // UseEffect para buscar o usuário assim que entrar na página
  useEffect(() => {
    buscarUsuario();
  }, []);

  // UseEffect para iniciar os parâmetros para busca da demanda (filtrando pelo usuário)
  useEffect(() => {
    setParams({ ...params, solicitante: usuario })
  }, [usuario])

  // UseEffect para buscar as demandas sempre que os parâmetros (filtros) forem modificados
  useEffect(() => {
    buscarDemandas();
  }, [params])

  // UseEffect para redefinir os parâmteros quando a ordenação for modificada
  useEffect(() => {
    if (params.solicitante != null) {
      setParams({ ...params, solicitante: usuario });
    } else {
      setParams({ ...params, departamento: usuario.departamento });
    }
  }, [ordenacao])

  // Função para buscar o usuário logado no sistema
  const buscarUsuario = () => {
    UsuarioService.getUsuarioById(parseInt(localStorage.getItem("usuarioId"))).then((e) => {
      setUsuario(e)
    });
  }

  // Função para buscar as demandas com os parâmetros e ordenação
  const buscarDemandas = () => {
    if (params.departamento != null || params.solicitante != null) {
      DemandaService.getPage(params, (ordenacao + page)).then((e) => {
        setListaDemandas(e.content);
      })
    }
  }

  // Função para atualizar o filtro de status quando modificado no modal de filtros
  const atualizarFiltro = (status) => {
    if (params.solicitante != null) {
      setParams({ ...params, solicitante: usuario, status: status });
      setFiltroAtual(status)
    } else {
      setParams({ ...params, departamento: usuario.departamento, status: status });
      setFiltroAtual(status)
    }
  }

  // Função para alterar a aba selecionada
  const handleChange = (event, newValue) => {
    setValue(newValue);
    if (newValue == 1) {
      setParams({ ...params, departamento: null, solicitante: usuario });
    } else {
      setParams({ ...params, solicitante: null, departamento: usuario?.departamento });
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
    navigate('/detalhes-demanda', { state: demanda });
  }

  // Função para salvar o input de pesquisa quando houver alteração
  const salvarPesquisa = (e) => {
    setValorPesquisa(e.target.value);
  }

  // Função para modificar os parâmetros da demanda ao pesquisar no campo de texto
  const pesquisaTitulo = () => {
    if (params.solicitante != null) {
      setParams({ ...params, titulo: valorPesquisa, solicitante: usuario });
    } else {
      setParams({ ...params, titulo: valorPesquisa, departamento: usuario.departamento });
    }
  }

  // Função para "ouvir" um evento de teclado no input de pesquisa e fazer a pesquisa caso seja a tecla "Enter"
  const eventoTeclado = (e) => {
    if (e.key == "Enter") {
      pesquisaTitulo();
    }
  }

  const navigate = useNavigate();

  return (
    // Container pai
    <FundoComHeader>
      {/* Div container */}
      <Box
        className="flex justify-center mt-12"
        sx={{ backgroundColor: "background.default", width: "100%" }}
      >
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
                  className="flex justify-between border px-3 py-1"
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
                    onKeyDown={(e) => { eventoTeclado(e) }}
                    onBlur={() => { pesquisaTitulo() }}
                    onChange={(e) => { salvarPesquisa(e) }}
                  />

                  {/* Container para os ícones */}
                  <Box className="flex gap-2">
                    {/* Ícone de pesquisa */}
                    <SearchOutlinedIcon onClick={pesquisaTitulo} className='hover:cursor-pointer' sx={{ color: "text.secondary" }} />

                    {/* Ícone de ordenação */}
                    <SwapVertIcon
                      onClick={abrirModalOrdenacao}
                      className="cursor-pointer"
                      sx={{ color: "text.secondary" }}
                    />

                    {/* Modal de ordenação */}
                    {abrirOrdenacao && <ModalOrdenacao ordenacao={ordenacao} setOrdenacao={setOrdenacao} open={abrirOrdenacao} setOpen={setOpenOrdenacao} />}
                  </Box>
                </Box>

                {/* Botão de filtrar */}
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

                {/* Modal de filtro */}
                {abrirFiltro && <ModalFiltro filtros={filtroAtual} setParams={atualizarFiltro} open={abrirFiltro} setOpen={setOpenFiltro} filtroDemanda={true} />}
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
                <Box sx={{ display: 'grid', gap: '1rem', gridTemplateColumns: 'repeat(auto-fit, minmax(650px, 1fr))' }}>
                  {listaDemandas?.map((e, index) => (
                    <Demanda key={index} demanda={e} onClick={() => { verDemanda(e) }} />
                  ))}
                </Box>
              </TabPanel>
              <TabPanel sx={{ padding: 0 }} value="2">
                <Box sx={{ display: "grid", gap: "1rem", gridTemplateColumns: "repeat(auto-fit, minmax(650px, 1fr))" }}>
                  {listaDemandas?.map((e, index) => (
                    <Demanda key={index} demanda={e} onClick={() => { verDemanda(e) }} />
                  ))}
                </Box>
              </TabPanel>
            </Box>
          </TabContext>
        </Box>
      </Box>
    </FundoComHeader>
  );
};

export default Home;
