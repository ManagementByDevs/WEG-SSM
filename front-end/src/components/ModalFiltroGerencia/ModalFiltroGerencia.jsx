import React, { useContext } from "react";
import { Modal, Typography, Box, Button, InputLabel, Select, MenuItem, FormControl, Autocomplete, TextField } from "@mui/material";

import Fade from "@mui/material/Fade";
import CloseIcon from "@mui/icons-material/Close";
import UsuarioService from "../../service/usuarioService";

import FontContext from "../../service/FontContext";

const ModalFiltroGerencia = (props) => {

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  /** Função para limpar os filtros ativos e fechar o modal */
  const limparFiltro = () => {
    props.setFiltro({ solicitante: null, forum: "", tamanho: "", gerente: null, departamento: "", analista: null });
    props.fecharModal();
  };

  // Função para selecionar o solicitante
  const selecionarSolicitante = (event, value) => {
    props.setFiltro({
      solicitante: value,
      forum: props.filtro.forum,
      tamanho: props.filtro.tamanho,
      gerente: props.filtro.gerente,
      departamento: props.filtro.departamento,
      id: props.filtro.id,
      codigoPPM: props.filtro.codigoPPM,
      analista: props.filtro.analista
    });
  };

  // Função para selecionar o fórum
  const selecionarForum = (event) => {
    props.setFiltro({
      solicitante: props.filtro.solicitante,
      forum: event.target.value,
      tamanho: props.filtro.tamanho,
      gerente: props.filtro.gerente,
      departamento: props.filtro.departamento,
      id: props.filtro.id,
      codigoPPM: props.filtro.codigoPPM,
      analista: props.filtro.analista
    });
  };

  // Função para selecionar o tamanho
  const selecionarTamanho = (event) => {
    props.setFiltro({
      solicitante: props.filtro.solicitante,
      forum: props.filtro.forum,
      tamanho: event.target.value,
      gerente: props.filtro.gerente,
      departamento: props.filtro.departamento,
      id: props.filtro.id,
      codigoPPM: props.filtro.codigoPPM,
      analista: props.filtro.analista
    });
  };

  // Função para selecionar o gerente
  const selecionarGerente = (event, value) => {
    props.setFiltro({
      solicitante: props.filtro.solicitante,
      forum: props.filtro.forum,
      tamanho: props.filtro.tamanho,
      gerente: value,
      departamento: props.filtro.departamento,
      id: props.filtro.id,
      codigoPPM: props.filtro.codigoPPM,
      analista: props.filtro.analista
    });
  };

  // Função para selecionar o analista
  const selecionarAnalista = (event, value) => {
    props.setFiltro({
      solicitante: props.filtro.solicitante,
      forum: props.filtro.forum,
      tamanho: props.filtro.tamanho,
      gerente: props.filtro.gerente,
      analista: value,
      departamento: props.filtro.departamento,
      id: props.filtro.id,
      codigoPPM: props.filtro.codigoPPM,
    });
  };

  // Função para selecionar o departamento
  const selecionarDepartamento = (event) => {
    props.setFiltro({
      solicitante: props.filtro.solicitante,
      forum: props.filtro.forum,
      tamanho: props.filtro.tamanho,
      gerente: props.filtro.gerente,
      departamento: event.target.value,
      id: props.filtro.id,
      codigoPPM: props.filtro.codigoPPM,
      analista: props.filtro.analista
    });
  };

  // Pesquisa de solicitantes feita quando algum input é digitado
  const pesquisarSolicitantes = (event) => {
    if (event?.target.value?.length > 0) {
      UsuarioService.getUsuarioByNomeAndTipo(
        event.target.value,
        "SOLICITANTE"
      ).then((response) => {
        props.setListaSolicitantes(response);
      });
    }
  };

  // Pesquisa de gerentes feita quando algum input é digitado
  const pesquisarGerentes = (event) => {
    if (event?.target.value?.length > 0) {
      UsuarioService.getUsuarioByNomeAndTipo(
        event.target.value,
        "GERENTE"
      ).then((response) => {
        props.setListaGerentes(response);
      });
    }
  };

  /** Pesquisa de analistas feita quando algum input é digitado */
  const pesquisarAnalistas = (event) => {
    if (event?.target.value?.length > 0) {
      UsuarioService.getUsuarioByNomeAndTipo(
        event.target.value,
        "ANALISTA"
      ).then((response) => {
        props.setListaAnalistas(response);
      })
    }
  }

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
