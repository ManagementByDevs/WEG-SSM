import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

import { Box, Button, Tab } from "@mui/material";
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
import ModalInformarMotivo from "../../components/ModalInformarMotivo/ModalInformarMotivo";

const HomeGerencia = () => {
  // UseState para poder visualizar e alterar a aba selecionada
  const [value, setValue] = useState("1");

  const [demandas, setDemandas] = useState([
    {
      titulo: "Demanda 1",
      status: "Backlog",
      solicitante: "Kenzo Sato",
      departamento: "TI",
      gerenteResponsavel: "João da Silva",
    },
    // {
    //   titulo: "Demanda 2",
    //   status: "Backlog",
    //   solicitante: "Kenzo Sato",
    //   departamento: "TI",
    //   gerenteResponsavel: "João da Silva",
    // },
    // {
    //   titulo: "Demanda 3",
    //   status: "Backlog",
    //   solicitante: "Kenzo Sato",
    //   departamento: "TI",
    //   gerenteResponsavel: "João da Silva",
    // },
    // {
    //   titulo: "Demanda 4",
    //   status: "Backlog",
    //   solicitante: "Kenzo Sato",
    //   departamento: "TI",
    //   gerenteResponsavel: "João da Silva",
    // },
  ]);

  // Função para alterar a aba selecionada
  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  const [modalFiltro, setOpenModal] = useState(false);
  const [modalMotivo, setOpenModalMotivo] = useState(false);

  const abrirModalFiltro = () => {
    setOpenModal(true);
  };

  const abrirModalInformarMotivo = () =>{
    setOpenModalMotivo(true);
  }

  const navigate = useNavigate();

  return (
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
                  label="Demandas"
                  value="1"
                />
                <Tab
                  sx={{ color: "text.secondary", fontSize: FontConfig.medium }}
                  label="Criar Propostas"
                  value="2"
                />
                <Tab
                  sx={{ color: "text.secondary", fontSize: FontConfig.medium }}
                  label="Propostas"
                  value="3"
                />
                <Tab
                  sx={{ color: "text.secondary", fontSize: FontConfig.medium }}
                  label="Pautas"
                  value="4"
                />
                <Tab
                  sx={{ color: "text.secondary", fontSize: FontConfig.medium }}
                  label="Atas"
                  value="5"
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
                  />

                  {/* Container para os ícones */}
                  <Box className="flex gap-2">
                    {/* Ícone de pesquisa */}
                    <SearchOutlinedIcon sx={{ color: "text.secondary" }} />

                    {/* Ícone de ordenação */}
                    <SwapVertIcon
                      onClick={() => {}}
                      className="cursor-pointer"
                      sx={{ color: "text.secondary" }}
                    />
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
                  onClick={() => {}}
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
                    gridTemplateColumns: "repeat(auto-fit, minmax(650px, 1fr))",
                  }}
                >
                  {demandas?.map((demanda, index) => {
                    <DemandaGerencia key={index} dados={demanda} />;
                  })}
                </Box>
              </TabPanel>
              <TabPanel sx={{ padding: 0 }} value="2" onClick={() => {}}>
                <Box
                  sx={{
                    display: "grid",
                    gap: "1rem",
                    gridTemplateColumns: "repeat(auto-fit, minmax(650px, 1fr))",
                  }}
                >
                  Criar propostas
                </Box>
              </TabPanel>
              <TabPanel sx={{ padding: 0 }} value="3" onClick={() => {}}>
                <Box
                  sx={{
                    display: "grid",
                    gap: "1rem",
                    gridTemplateColumns: "repeat(auto-fit, minmax(650px, 1fr))",
                  }}
                >
                  Propostas
                </Box>
              </TabPanel>
              <TabPanel sx={{ padding: 0 }} value="4" onClick={() => {}}>
                <Box
                  sx={{
                    display: "grid",
                    gap: "1rem",
                    gridTemplateColumns: "repeat(auto-fit, minmax(650px, 1fr))",
                  }}
                >
                  Pautas
                </Box>
              </TabPanel>
              <TabPanel sx={{ padding: 0 }} value="5" onClick={() => {}}>
                <Box
                  sx={{
                    display: "grid",
                    gap: "1rem",
                    gridTemplateColumns: "repeat(auto-fit, minmax(650px, 1fr))",
                  }}
                >
                  Atas
                </Box>
              </TabPanel>
            </Box>
          </TabContext>
        </Box>
      </Box>
    </FundoComHeader>
  );
};

export default HomeGerencia;
