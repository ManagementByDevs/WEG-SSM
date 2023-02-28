import React, { useContext } from "react";
import { Modal, Typography, Box, Button, InputLabel, Select, MenuItem, FormControl, Autocomplete, TextField } from "@mui/material";

import Fade from "@mui/material/Fade";
import CloseIcon from "@mui/icons-material/Close";
import UsuarioService from "../../service/usuarioService";

import FontContext from "../../service/FontContext";

/** Componente de filtro exclusivo para a página "HomeGerencia", com diferentes opções de filtragem que o filtro usado para o solicitante */
const ModalFiltroGerencia = (props) => {

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  /** Função para limpar os filtros ativos e fechar o modal */
  const limparFiltro = () => {
    props.setFiltro({ solicitante: null, forum: "", tamanho: "", gerente: null, departamento: "", analista: null });
    props.fecharModal();
  };

  /** Pesquisa de solicitantes feita quando algum input é digitado */
  const pesquisarSolicitantes = (event) => {
    if (event?.target.value?.length > 0) {
      UsuarioService.getUsuarioByNomeAndTipo(event.target.value, "SOLICITANTE").then((response) => {
        props.setListaSolicitantes(response);
      });
    }
  };

  /** Pesquisa de gerentes feita quando algum input é digitado */
  const pesquisarGerentes = (event) => {
    if (event?.target.value?.length > 0) {
      UsuarioService.getUsuarioByNomeAndTipo(event.target.value, "GERENTE").then((response) => {
        props.setListaGerentes(response);
      });
    }
  };

  /** Pesquisa de analistas feita quando algum input é digitado */
  const pesquisarAnalistas = (event) => {
    if (event?.target.value?.length > 0) {
      UsuarioService.getUsuarioByNomeAndTipo(event.target.value, "ANALISTA").then((response) => {
        props.setListaAnalistas(response);
      });
    }
  }

  /** Função para atualizar os filtros quando um solicitante for selecionado */
  const selecionarSolicitante = (event, value) => {
    props.setFiltro({
      solicitante: value, forum: props.filtro.forum, tamanho: props.filtro.tamanho, gerente: props.filtro.gerente, departamento: props.filtro.departamento, id: props.filtro.id, codigoPPM: props.filtro.codigoPPM, analista: props.filtro.analista
    });
  };

  /** Função para atualizar os filtros quando um forum for selecionado */
  const selecionarForum = (event) => {
    props.setFiltro({
      solicitante: props.filtro.solicitante, forum: event.target.value, tamanho: props.filtro.tamanho, gerente: props.filtro.gerente, departamento: props.filtro.departamento, id: props.filtro.id, codigoPPM: props.filtro.codigoPPM, analista: props.filtro.analista
    });
  };

  /** Função para atualizar os filtros quando um tamanho for selecionado */
  const selecionarTamanho = (event) => {
    props.setFiltro({
      solicitante: props.filtro.solicitante, forum: props.filtro.forum, tamanho: event.target.value, gerente: props.filtro.gerente, departamento: props.filtro.departamento, id: props.filtro.id, codigoPPM: props.filtro.codigoPPM, analista: props.filtro.analista
    });
  };

  /** Função para atualizar os filtros quando um gerente for selecionado */
  const selecionarGerente = (event, value) => {
    props.setFiltro({
      solicitante: props.filtro.solicitante, forum: props.filtro.forum, tamanho: props.filtro.tamanho, gerente: value, departamento: props.filtro.departamento, id: props.filtro.id, codigoPPM: props.filtro.codigoPPM, analista: props.filtro.analista
    });
  };

  /** Função para atualizar os filtros quando um departamento for selecionado */
  const selecionarDepartamento = (event) => {
    props.setFiltro({
      solicitante: props.filtro.solicitante, forum: props.filtro.forum, tamanho: props.filtro.tamanho, gerente: props.filtro.gerente, departamento: event.target.value, id: props.filtro.id, codigoPPM: props.filtro.codigoPPM, analista: props.filtro.analista
    });
  };

  /** Função para atualizar os filtros quando um analista for selecionado */
  const selecionarAnalista = (event, value) => {
    props.setFiltro({
      solicitante: props.filtro.solicitante, forum: props.filtro.forum, tamanho: props.filtro.tamanho, gerente: props.filtro.gerente, analista: value, departamento: props.filtro.departamento, id: props.filtro.id, codigoPPM: props.filtro.codigoPPM,
    });
  };

  return (
    <Modal open={true} onClose={props.fecharModal} closeAfterTransition>
      <Fade in={true}>
        <Box
          className="absolute flex justify-evenly items-center flex-col"
          sx={{ top: "50%", left: "50%", transform: "translate(-50%, -50%)", width: 580, height: 400, bgcolor: "background.paper", borderRadius: "5px", borderTop: "10px solid #00579D", boxShadow: 24, p: 4 }}
        >
          <CloseIcon
            onClick={props.fecharModal}
            sx={{
              position: "absolute",
              left: "93%",
              top: "3%",
              cursor: "pointer",
            }}
          />
          <Typography
            fontWeight={650}
            color={"primary.main"}
            fontSize={FontConfig.smallTitle}
          >
            Filtros
          </Typography>
          <Box className="flex justify-center items-center w-full h-full">
            <Box className="flex flex-col justify-evenly items-center h-full w-1/2">

              {/* Select de solicitante */}
              <Autocomplete
                disablePortal
                id="combo-box-demo"
                options={props.listaSolicitantes}
                noOptionsText={"Sem Resultados"}
                sx={{ width: 240 }}
                value={props.filtro.solicitante}
                onInputChange={(e) => {
                  pesquisarSolicitantes(e);
                }}
                onChange={(e, value) => {
                  selecionarSolicitante(e, value);
                }}
                getOptionLabel={(option) => {
                  return option?.nome || "";
                }}
                renderInput={(params) => (
                  <TextField {...params} label="Solicitante" />
                )}
              />

              {/* Select de fórum */}
              <FormControl sx={{ width: "15rem" }}>
                <InputLabel id="demo-simple-select-label">Fórum</InputLabel>
                <Select
                  labelId="demo-simple-select-label"
                  id="demo-simple-select"
                  value={props.filtro.forum}
                  label="Fórum"
                  onChange={selecionarForum}
                >
                  <MenuItem selected value={""}>
                    Selecionar
                  </MenuItem>
                  {props.listaForuns.map((forum) => {
                    return (
                      <MenuItem key={forum.id} value={forum}>
                        {forum.nome}
                      </MenuItem>
                    );
                  })}
                </Select>
              </FormControl>

              {/* Select de tamanho */}
              <FormControl sx={{ width: "15rem" }}>
                <InputLabel id="demo-simple-select-label">Tamanho</InputLabel>
                <Select
                  labelId="demo-simple-select-label"
                  id="demo-simple-select"
                  value={props.filtro.tamanho}
                  label="Tamanho"
                  onChange={selecionarTamanho}
                >
                  <MenuItem selected value={""}>
                    Selecionar
                  </MenuItem>
                  <MenuItem value={"Muito Pequeno"}>Muito Pequeno</MenuItem>
                  <MenuItem value={"Pequeno"}>Pequeno</MenuItem>
                  <MenuItem value={"Médio"}>Médio</MenuItem>
                  <MenuItem value={"Grande"}>Grande</MenuItem>
                  <MenuItem value={"Muito Grande"}>Muito Grande</MenuItem>
                </Select>
              </FormControl>
            </Box>
            <Box className="flex flex-col justify-evenly items-center h-full w-1/2">

              {/* Select de gerente */}
              <Autocomplete
                disablePortal
                id="combo-box-demo"
                options={props.listaGerentes}
                noOptionsText={"Sem Resultados"}
                sx={{ width: 240 }}
                value={props.filtro.gerente}
                onInputChange={(e) => {
                  pesquisarGerentes(e);
                }}
                onChange={(e, value) => {
                  selecionarGerente(e, value);
                }}
                getOptionLabel={(option) => {
                  return option?.nome || "";
                }}
                renderInput={(params) => (
                  <TextField {...params} label="Gerente Responsável" />
                )}
              />

              {/* Select de departamento */}
              <FormControl sx={{ width: "15rem" }}>
                <InputLabel id="demo-simple-select-label">
                  Departamento
                </InputLabel>
                <Select
                  labelId="demo-simple-select-label"
                  id="demo-simple-select"
                  value={props.filtro.departamento}
                  label="Departamento"
                  onChange={selecionarDepartamento}
                >
                  <MenuItem selected value={""}>
                    Selecionar
                  </MenuItem>
                  {props.listaDepartamentos.map((departamento) => {
                    return (
                      <MenuItem key={departamento.id} value={departamento}>
                        {departamento.nome}
                      </MenuItem>
                    );
                  })}
                </Select>
              </FormControl>

              {/* Select de analista */}
              <Autocomplete
                disablePortal
                id="combo-box-demo"
                options={props.listaAnalistas}
                noOptionsText={"Sem Resultados"}
                sx={{ width: 240 }}
                value={props.filtro.analista}
                onInputChange={(e) => {
                  pesquisarAnalistas(e);
                }}
                onChange={(e, value) => {
                  selecionarAnalista(e, value);
                }}
                getOptionLabel={(option) => {
                  return option?.nome || "";
                }}
                renderInput={(params) => (
                  <TextField {...params} label="Analista Responsável" />
                )}
              />
            </Box>
          </Box>

          {/* Botão de limpar filtros */}
          <Button
            onClick={limparFiltro}
            variant="contained"
            disableElevation
            color="primary"
            sx={{
              marginTop: "1%",
              width: "8rem",
              height: "3rem",
              fontSize: FontConfig.normal,
            }}
          >
            Limpar Filtros
          </Button>
        </Box>
      </Fade>
    </Modal>
  );
};

export default ModalFiltroGerencia;
