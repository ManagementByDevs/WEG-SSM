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

  const [listaDemandas, setListaDemandas] = useState([]);

  const [usuario, setUsuario] = useState({ id: 0, email: "", nome: "", senha: "", tipo_usuario: 0, visibilidade: 1, departamento: null });
  const [params, setParams] = useState({ titulo: null, solicitante: null, gerente: null, forum: null, departamento: null, tamanho: null, status: null });
  const [page, setPage] = useState("sort=id,asc&size=20&page=0");

  // UseState para poder visualizar e alterar a aba selecionada
  const [value, setValue] = useState('1');
  const [valorPesquisa, setValorPesquisa] = useState("");

  useEffect(() => {
    buscarUsuario();
  }, []);

  useEffect(() => {
    setParams({ ...params, solicitante: usuario })
  }, [usuario])

  useEffect(() => {
    buscarDemandas();
  }, [params])

  const buscarUsuario = () => {
    UsuarioService.getUsuarioById(parseInt(localStorage.getItem("usuarioId"))).then((e) => {
      setUsuario(e)
    });
  }

  const buscarDemandas = () => {
    if (params.departamento != null || params.solicitante != null) {
      DemandaService.getPage(params, page).then((e) => {
        setListaDemandas(e.content);
      })
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

  const verDemanda = (demanda) => {
    localStorage.setItem("demandaAtual", JSON.stringify(demanda));
    navigate('/detalhes-demanda');
  }

  const salvarPesquisa = (e) => {
    setValorPesquisa(e.target.value);
  }

  const pesquisaTitulo = () => {
    if (params.solicitante != null) {
      setParams({ ...params, titulo: valorPesquisa, solicitante: usuario });
    } else {
      setParams({ ...params, titulo: valorPesquisa, departamento: usuario.departamento });
    }
  }

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
                    {abrirOrdenacao && <ModalOrdenacao open={abrirOrdenacao} setOpen={setOpenOrdenacao} />}
                  </Box>
                </Box>

                {/* Botão de filtrar */}
                <Button
                  sx={{
                    backgroundColor: "primary.main",
                    color: "text.white",
                    fontSize: FontConfig.default,
                  }}
                  // onClick={handleClick()}
                  onClick={abrirModalFiltro}
                  variant="contained"
                  disableElevation
                >
                  Filtrar <FilterAltOutlinedIcon />
                </Button>
                {abrirFiltro && <ModalFiltro open={abrirFiltro} setOpen={setOpenFiltro} filtroDemanda={true} />}

                <Feedback open={open} handleClose={handleClose} status="sucesso" />
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
