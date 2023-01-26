import React, { useState, useEffect } from "react";
import { useNavigate, useLocation } from "react-router-dom";

import { Box, Button, Tab, Tooltip } from "@mui/material";
import { TabContext, TabList, TabPanel } from "@mui/lab";

import SearchOutlinedIcon from "@mui/icons-material/SearchOutlined";
import SwapVertIcon from "@mui/icons-material/SwapVert";
import FilterAltOutlinedIcon from "@mui/icons-material/FilterAltOutlined";
import AddIcon from "@mui/icons-material/Add";
import FileDownloadIcon from "@mui/icons-material/FileDownload";

import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";

import FontConfig from "../../service/FontConfig";

import ModalFiltroGerencia from "../../components/ModalFiltroGerencia/ModalFiltroGerencia";
import ModalHistoricoDemanda from "../../components/ModalHistoricoDemanda/ModalHistoricoDemanda";
import DemandaGerencia from "../../components/DemandaGerencia/DemandaGerencia";
import ModalOrdenacao from "../../components/ModalOrdenacao/ModalOrdenacao";
import ModalInformarMotivo from "../../components/ModalInformarMotivo/ModalInformarMotivo";
import Paginacao from "../../components/Paginacao/Paginacao";
import Pauta from "../../components/Pauta/Pauta";
import Feedback from "../../components/Feedback/Feedback";

import UsuarioService from "../../service/usuarioService";
import DemandaService from "../../service/demandaService";

const HomeGerencia = () => {
  // UseState para poder visualizar e alterar a aba selecionada
  const [value, setValue] = useState("1");

  const [listaItens, setListaItens] = useState([]);

  // String para ordenação das demandas
  const [page, setPage] = useState("size=20&page=0");
  const [ordenacao, setOrdenacao] = useState("sort=id,asc&");

  // Valores dos checkboxes no modal de ordenação
  const [ordenacaoTitulo, setOrdenacaoTitulo] = useState([false, false]);
  const [ordenacaoScore, setOrdenacaoScore] = useState([false, false]);
  const [ordenacaoDate, setOrdenacaoDate] = useState([false, false]);

  // Valor do input de pesquisa
  const [valorPesquisa, setValorPesquisa] = useState("");

  const [abrirOrdenacao, setOpenOrdenacao] = useState(false);
  const [filtrosAtuais, setFiltrosAtuais] = useState({ solicitante: null, forum: "", tamanho: "", gerente: null, departamento: "" });

  const [usuario, setUsuario] = useState({
    id: 0,
    email: "",
    nome: "",
    senha: "",
    tipoUsuario: "",
    visibilidade: 1,
    departamento: null,
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
  });

  // UseEffect para buscar o usuário assim que entrar na página
  useEffect(() => {
    buscarUsuario();
  }, []);

  // UseEffect para redefinir os parâmteros quando a ordenação for modificada
  useEffect(() => {
    setParams({ titulo: valorPesquisa, solicitante: JSON.parse(params.solicitante), gerente: JSON.parse(params.gerente), analista: JSON.parse(params.analista), forum: JSON.parse(params.forum), tamanho: params.tamanho, status: params.status, departamento: JSON.parse(params.departamento) });
  }, [ordenacao]);

  useEffect(() => {
    let paramsTemp = { solicitante: null, forum: null, tamanho: null, gerente: null, departamento: null };

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

    setParams({ titulo: valorPesquisa, solicitante: paramsTemp.solicitante, gerente: paramsTemp.gerente, analista: params.analista, forum: paramsTemp.forum, tamanho: paramsTemp.tamanho, status: params.status, departamento: paramsTemp.departamento });
  }, [filtrosAtuais]);


  // feedbacks para o gerenciamento das demandas por parte do analista

  const [feedbackDemandaAceita, setFeedbackDemandaAceita] = useState(false);
  const [feedbackDemandaDevolvida, setFeedbackDemandaDevolvida] = useState(false);
  const [feedbackDemandaRecusada, setFeedbackDemandaRecusada] = useState(false);
  
  useEffect(() => {
    
    if (localStorage.getItem('tipoFeedback') == "2") {
      setFeedbackDemandaAceita(true);
    } else if (localStorage.getItem('tipoFeedback') == "3") {
      setFeedbackDemandaDevolvida(true);
    } else if (localStorage.getItem('tipoFeedback') == "4") {
      setFeedbackDemandaRecusada(true);
    }
    
  }, []);

  useEffect(() => {
    switch (value) {
      case "1":
        if (usuario.tipoUsuario == "GERENTE") {
          setParams({
            ...params,
            gerente: usuario,
            status: "BACKLOG_APROVACAO",
            analista: null,
          });
        } else {
          setParams({
            ...params,
            gerente: null,
            status: "BACKLOG_REVISAO",
            analista: null,
          });
        }
        break;
      case "2":
        setParams({
          ...params,
          gerente: null,
          status: "ASSESSMENT",
          analista: usuario,
        });
        break;
      case "3":
        break;
      case "4":
        break;
      case "5":
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
  }, [usuario]);

  // UseEffect para buscar as demandas sempre que os parâmetros (filtros) forem modificados
  useEffect(() => {
    buscarItens();
  }, [params]);

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

  // Função para buscar o usuário logado no sistema
  const buscarUsuario = () => {
    UsuarioService.getUsuarioById(
      parseInt(localStorage.getItem("usuarioId"))
    ).then((e) => {
      setUsuario(e);
    });
  };

  const buscarItens = () => {
    switch (value) {
      case "1":
        if (params.status != null || params.gerente != null) {
          DemandaService.getPage(params, ordenacao + page).then((response) => {
            setListaItens([...response.content]);
          });
        }
        break;
      case "2":
        if (params.status != null && params.analista != null) {
          DemandaService.getPage(params, ordenacao + page).then((response) => {
            setListaItens([...response.content]);
          });
        }
        break;
      case "3":
        break;
      case "4":
        break;
      case "5":
        break;
    }
  };

  const [propostas, setPropostas] = useState([
    {
      titulo: "Proposta 1",
      status: "Assessment",
      solicitante: "Kenzo Sato",
      departamento: "TI",
      gerenteResponsavel:
        "Enzo João da Silva Cleitom Sauro Rex Pereira Silvério",
      ppm: "12353",
    },
    {
      titulo: "Proposta 2",
      status: "Assessment",
      solicitante: "Kenzo Sato",
      departamento: "TI",
      gerenteResponsavel: "João da Silva",
      ppm: "12353",
    },
    {
      titulo: "DemaPropostanda 3",
      status: "Assessment",
      solicitante: "Kenzo Sato",
      departamento: "TI",
      gerenteResponsavel: "João da Silva",
      ppm: "12353",
    },
    {
      titulo: "Proposta 4",
      status: "Assessment",
      solicitante: "Kenzo Sato",
      departamento: "TI",
      gerenteResponsavel: "João da Silva",
      ppm: "12353",
    },
  ]);

  const [pautas, setPautas] = useState([
    {
      numeroSequencial: "1/2022",
      comissao: "Comissão 1",
      analistaResponsavel: "Kenzo Sato",
      data: "01/01/2022",
    },
    {
      numeroSequencial: "2/2022",
      comissao: "Comissão 2",
      analistaResponsavel: "Kenzo S",
      data: "02/02/2022",
    },
    {
      numeroSequencial: "3/2022",
      comissao: "Comissão 3",
      analistaResponsavel: "Kenzo S",
      data: "03/03/2022",
    },
    {
      numeroSequencial: "4/2022",
      comissao: "Comissão 4",
      analistaResponsavel:
        "Kenzo Sato tem nome muito grande mesmo até passa o componente",
      data: "04/04/2022",
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

  const [modalFiltro, setOpenModal] = useState(false);

  const abrirModalFiltro = () => {
    setOpenModal(true);
  };

  // Função para ir na tela de detalhes da demanda, salvando a demanda no localStorage
  const verDemanda = (demanda) => {
    if (demanda.status == "ASSESSMENT") {
      navigate("/criar-proposta", { state: demanda });
    } else {
      navigate("/detalhes-demanda", { state: demanda });
    }
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
    setParams({ titulo: valorPesquisa, solicitante: JSON.parse(params.solicitante), gerente: JSON.parse(params.gerente), analista: JSON.parse(params.analista), forum: JSON.parse(params.forum), tamanho: params.tamanho, status: params.status, departamento: JSON.parse(params.departamento) });
  };

  const abrirModalOrdenacao = () => {
    setOpenOrdenacao(true);
  };
  const abrirDetalhesPauta = () => {
    console.log("clicou")
  }

  const location = useLocation();
  const navigate = useNavigate();

  const [feedbackAta, setOpenFeedbackAta] = useState(false);

  useEffect(() => {
    if (location.state?.feedback) {
      setOpenFeedbackAta(true);
    }
  }, [location.state?.feedback]);

  return (
    <FundoComHeader>
      {/* Div container */}
      <Box
        className="flex justify-center mt-8"
        sx={{ backgroundColor: "background.default", width: "100%" }}
      >
        {/* Feedback ata criada */}
        <Feedback open={feedbackAta} handleClose={() => {
          setOpenFeedbackAta(false);
        }} status={"sucesso"} mensagem={"Ata criada com sucesso!"} />

        <Feedback open={feedbackDemandaAceita} handleClose={() => {
          setFeedbackDemandaAceita(false);
        }} status={"sucesso"} mensagem={"Demanda aceita com sucesso!"} />

        <Feedback open={feedbackDemandaRecusada} handleClose={() => {
          setFeedbackDemandaRecusada(false);
        }} status={"sucesso"} mensagem={"Demanda recusada com sucesso!"} />

        <Feedback open={feedbackDemandaDevolvida} handleClose={() => {
          setFeedbackDemandaDevolvida(false);
        }} status={"sucesso"} mensagem={"Demanda devolvida com sucesso!"} />

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
                  label="Demandas"
                  value="1"
                />

                {isGerente && (
                  <Tab
                    sx={{
                      color: "text.secondary",
                      fontSize: FontConfig.medium,
                    }}
                    label="Criar Propostas"
                    value="2"
                  />
                )}

                {isGerente && (
                  <Tab
                    sx={{
                      color: "text.secondary",
                      fontSize: FontConfig.medium,
                    }}
                    label="Propostas"
                    value="3"
                  />
                )}

                {isGerente && (
                  <Tab
                    sx={{
                      color: "text.secondary",
                      fontSize: FontConfig.medium,
                    }}
                    label="Pautas"
                    value="4"
                  />
                )}

                {isGerente && (
                  <Tab
                    sx={{
                      color: "text.secondary",
                      fontSize: FontConfig.medium,
                    }}
                    label="Atas"
                    value="5"
                  />
                )}
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
                    <Tooltip className="hover:cursor-pointer" title="Pesquisar" onClick={() => { pesquisaTitulo(); }}>
                      <SearchOutlinedIcon sx={{ color: "text.secondary" }} />
                    </Tooltip>

                    {/* Ícone de ordenação */}
                    <Tooltip title="Ordenação">
                      <SwapVertIcon
                        onClick={() => { abrirModalOrdenacao(); }}
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
                <Button
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
                  Filtrar <FilterAltOutlinedIcon />
                </Button>
                {modalFiltro && (
                  <ModalFiltroGerencia
                    open={modalFiltro}
                    setOpen={setOpenModal}
                    filtro={filtrosAtuais}
                    setFiltro={setFiltrosAtuais}
                  />
                )}

                {/* Botão de exportar */}
                <Button
                  className="flex gap-1"
                  sx={{
                    backgroundColor: "primary.main",
                    color: "text.white",
                    fontSize: FontConfig.default,
                  }}
                  onClick={() => { }}
                  variant="contained"
                  disableElevation
                >
                  Exportar <FileDownloadIcon />
                </Button>
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
                    gridTemplateColumns: "repeat(auto-fit, minmax(720px, 1fr))",
                  }}
                >
                  {listaItens?.map((demanda, index) => {
                    return (
                      <DemandaGerencia
                        key={index}
                        dados={demanda}
                        tipo="demanda"
                        onClick={() => {
                          verDemanda(demanda);
                        }}
                      />
                    );
                  })}
                </Box>
              </TabPanel>
              {isGerente && (
                <>
                  <TabPanel sx={{ padding: 0 }} value="2" onClick={() => { }}>
                    <Box
                      sx={{
                        display: "grid",
                        gap: "1rem",
                        gridTemplateColumns:
                          "repeat(auto-fit, minmax(720px, 1fr))",
                      }}
                    >
                      {listaItens?.map((demanda, index) => {
                        return (
                          <DemandaGerencia
                            key={index}
                            dados={demanda}
                            tipo="demanda"
                            onClick={() => {
                              verDemanda(demanda);
                            }}
                          />
                        );
                      })}
                    </Box>
                  </TabPanel>
                  <TabPanel sx={{ padding: 0 }} value="3" onClick={() => { }}>
                    <Box
                      sx={{
                        display: "grid",
                        gap: "1rem",
                        gridTemplateColumns:
                          "repeat(auto-fit, minmax(720px, 1fr))",
                      }}
                    >
                      {propostas?.map((proposta, index) => {
                        return (
                          <DemandaGerencia
                            key={index}
                            dados={proposta}
                            tipo="proposta"
                          />
                        );
                      })}
                    </Box>
                  </TabPanel>
                  <TabPanel sx={{ padding: 0 }} value="4" onClick={() => { navigate("/detalhes-pauta"); }}>
                    <Box
                      sx={{
                        display: "grid",
                        gap: "1rem",
                        gridTemplateColumns:
                          "repeat(auto-fit, minmax(700px, 1fr))",
                      }}
                    >
                      {pautas?.map((pauta, index) => {
                        return <Pauta key={index} dados={pauta} tipo="pauta" />;
                      })}
                    </Box>
                  </TabPanel>
                  <TabPanel sx={{ padding: 0 }} value="5" onClick={() => { navigate("/detalhes-ata", {}) }}>
                    <Box
                      sx={{
                        display: "grid",
                        gap: "1rem",
                        gridTemplateColumns:
                          "repeat(auto-fit, minmax(700px, 1fr))",
                      }}
                    >
                      {atas?.map((ata, index) => {
                        return <Pauta key={index} dados={ata} tipo="ata" />;
                      })}
                    </Box>
                  </TabPanel>
                </>
              )}
            </Box>
          </TabContext>
        </Box>
      </Box>
      <Box className="flex justify-end mt-10" sx={{ width: "95%" }}>
        {listaItens.length > 18 ? (
          <Paginacao tipo={value} />
        ) : null}
      </Box>
    </FundoComHeader>
  );
};

export default HomeGerencia;