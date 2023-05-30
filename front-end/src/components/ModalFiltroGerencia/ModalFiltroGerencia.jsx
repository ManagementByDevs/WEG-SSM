import React, { useContext, useRef, useState, useEffect } from "react";
import {
  Modal,
  Typography,
  Box,
  Button,
  InputLabel,
  Select,
  MenuItem,
  FormControl,
  Autocomplete,
  TextField,
  FormGroup,
  FormControlLabel,
  Checkbox,
  RadioGroup,
  Radio,
} from "@mui/material";

import Fade from "@mui/material/Fade";
import CloseIcon from "@mui/icons-material/Close";
import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";

import UsuarioService from "../../service/usuarioService";

/** Componente de filtro exclusivo para a página "HomeGerencia", com diferentes opções de filtragem que o filtro usado para o solicitante */
const ModalFiltroGerencia = (props) => {
  // Context para alterar a linguagem do sistema
  const { texts, setTexts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  /** Variável para armazenar o valor do radio button */
  const [selectedValue, setSelectedValue] = useState("");

  /** Variável para manter a seleção do radio button caso feche o modal */
  const lastSelectedValueRef = useRef("");

  /** useEffect para pegar o valor do radio button caso o modal seja fechado */
  useEffect(() => {
    setSelectedValue(props.filtro.presenteEm);
  }, [selectedValue]);

  /** Função para mudar o valor do radio button e chamar a função do filtro */
  const handleChange = (event) => {
    let value = event.target.value;
    setSelectedValue(value);
    lastSelectedValueRef.current = value;
    selecionarPresenteEm(value);
  };

  /** Função para limpar os filtros ativos e fechar o modal */
  const limparFiltro = () => {

    if(!props.lendo) {
      props.setFiltro({
        solicitante: null,
        forum: "",
        tamanho: "",
        gerente: null,
        departamento: "",
        analista: null,
        presenteEm: "",
      });
      props.fecharModal();
    } else {
      lerTexto(texts.modalFiltroGerencia.limparFilros);
    }
  };

  /** Pesquisa de solicitantes feita quando algum input é digitado */
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

  /** Pesquisa de gerentes feita quando algum input é digitado */
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
      });
    }
  };

  /** Função para atualizar os filtros quando um solicitante for selecionado */
  const selecionarSolicitante = (event, value) => {
    props.setFiltro({
      solicitante: value,
      forum: props.filtro.forum,
      tamanho: props.filtro.tamanho,
      gerente: props.filtro.gerente,
      departamento: props.filtro.departamento,
      id: props.filtro.id,
      codigoPPM: props.filtro.codigoPPM,
      analista: props.filtro.analista,
      presenteEm: props.filtro.presenteEm,
    });
  };

  /** Função para atualizar os filtros quando um forum for selecionado */
  const selecionarForum = (event) => {
    props.setFiltro({
      solicitante: props.filtro.solicitante,
      forum: event.target.value,
      tamanho: props.filtro.tamanho,
      gerente: props.filtro.gerente,
      departamento: props.filtro.departamento,
      id: props.filtro.id,
      codigoPPM: props.filtro.codigoPPM,
      analista: props.filtro.analista,
      presenteEm: props.filtro.presenteEm,
    });
  };

  /** Função para atualizar os filtros quando um tamanho for selecionado */
  const selecionarTamanho = (event) => {
    props.setFiltro({
      solicitante: props.filtro.solicitante,
      forum: props.filtro.forum,
      tamanho: event.target.value,
      gerente: props.filtro.gerente,
      departamento: props.filtro.departamento,
      id: props.filtro.id,
      codigoPPM: props.filtro.codigoPPM,
      analista: props.filtro.analista,
      presenteEm: props.filtro.presenteEm,
    });
  };

  /** Função para atualizar os filtros quando um gerente for selecionado */
  const selecionarGerente = (event, value) => {
    props.setFiltro({
      solicitante: props.filtro.solicitante,
      forum: props.filtro.forum,
      tamanho: props.filtro.tamanho,
      gerente: value,
      departamento: props.filtro.departamento,
      id: props.filtro.id,
      codigoPPM: props.filtro.codigoPPM,
      analista: props.filtro.analista,
      presenteEm: props.filtro.presenteEm,
    });
  };

  /** Função para atualizar os filtros quando um departamento for selecionado */
  const selecionarDepartamento = (event) => {
    props.setFiltro({
      solicitante: props.filtro.solicitante,
      forum: props.filtro.forum,
      tamanho: props.filtro.tamanho,
      gerente: props.filtro.gerente,
      departamento: event.target.value,
      id: props.filtro.id,
      codigoPPM: props.filtro.codigoPPM,
      analista: props.filtro.analista,
      presenteEm: props.filtro.presenteEm,
    });
  };

  /** Função para atualizar os filtros quando um analista for selecionado */
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
      presenteEm: props.filtro.presenteEm,
    });
  };

  /** Função para atualizar os filtros quando em pauta, em ata ou em edição for selecionado */
  const selecionarPresenteEm = (value) => {
    props.setFiltro({
      solicitante: props.filtro.solicitante,
      forum: props.filtro.forum,
      tamanho: props.filtro.tamanho,
      gerente: props.filtro.gerente,
      analista: props.filtro.analista,
      departamento: props.filtro.departamento,
      id: props.filtro.id,
      codigoPPM: props.filtro.codigoPPM,
      presenteEm: value,
    });
  };

 
  // Função que irá setar o texto que será "lido" pela a API
  const lerTexto = (escrita) => {
    if (props.lendo) {
      const synthesis = window.speechSynthesis;
      const utterance = new SpeechSynthesisUtterance(escrita);
  
      const finalizarLeitura = () => {
        if ("speechSynthesis" in window) {
          synthesis.cancel();
        }
      };
  
      if (props.lendo && escrita !== "") {
        if ("speechSynthesis" in window) {
          synthesis.speak(utterance);
        }
      } else {
        finalizarLeitura();
      }
  
      return () => {
        finalizarLeitura();
      };
    }
  };

  return (
    <Modal open={true} onClose={props.fecharModal} closeAfterTransition>
      <Fade in={true}>
        <Box
          className="absolute flex justify-evenly items-center flex-col"
          sx={{
            top: "50%",
            left: "50%",
            transform: "translate(-50%, -50%)",
            width: 580,
            height: 440,
            bgcolor: "background.paper",
            borderRadius: "5px",
            borderTop: "10px solid #00579D",
            boxShadow: 24,
            p: 4,
          }}
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
            onClick={() => {
              lerTexto(texts.modalFiltroGerencia.filtros);
            }}
          >
            {texts.modalFiltroGerencia.filtros}
          </Typography>
          <Box className="flex justify-center items-center w-full h-full">
            <Box className="flex flex-col justify-evenly items-center h-full w-1/2">
              {/* Select de solicitante */}
              <Autocomplete
                disablePortal
                id="combo-box-demo"
                options={props.listaSolicitantes}
                noOptionsText={texts.modalFiltroGerencia.semResultados}
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
                  <TextField
                    {...params}
                    label={texts.modalFiltroGerencia.labelSolicitante}
                  />
                )}
              />

              {/* Select de fórum */}
              <FormControl sx={{ width: "15rem" }}>
                <InputLabel id="demo-simple-select-label">
                  {texts.modalFiltroGerencia.forum}
                </InputLabel>
                <Select
                  labelId="demo-simple-select-label"
                  id="demo-simple-select"
                  value={props.filtro.forum}
                  label={texts.modalFiltroGerencia.forum}
                  onChange={selecionarForum}
                >
                  <MenuItem selected value={""}>
                    {texts.modalFiltroGerencia.selecionar}
                  </MenuItem>
                  {props.listaForuns.map((forum) => {
                    return (
                      <MenuItem key={forum.idForum} value={forum}>
                        {forum.nomeForum}
                      </MenuItem>
                    );
                  })}
                </Select>
              </FormControl>

              {/* Select de tamanho */}
              <FormControl sx={{ width: "15rem" }}>
                <InputLabel
                  id="demo-simple-select-label"
                  onClick={() => {
                    lerTexto(texts.modalFiltroGerencia.tamanho);
                  }}
                >
                  {texts.modalFiltroGerencia.tamanho}
                </InputLabel>
                <Select
                  labelId="demo-simple-select-label"
                  id="demo-simple-select"
                  value={props.filtro.tamanho}
                  label={texts.modalFiltroGerencia.tamanho}
                  onChange={selecionarTamanho}
                >
                  <MenuItem selected value={""}>
                    {texts.modalFiltroGerencia.selecionar}
                  </MenuItem>
                  <MenuItem value={"Muito Pequeno"}>
                    {texts.modalFiltroGerencia.muitoPequeno}
                  </MenuItem>
                  <MenuItem value={"Pequeno"}>
                    {texts.modalFiltroGerencia.pequeno}
                  </MenuItem>
                  <MenuItem value={"Médio"}>
                    {texts.modalAceitarDemanda.medio}
                  </MenuItem>
                  <MenuItem value={"Grande"}>
                    {texts.modalAceitarDemanda.grande}
                  </MenuItem>
                  <MenuItem value={"Muito Grande"}>
                    {texts.modalAceitarDemanda.muitoGrande}
                  </MenuItem>
                </Select>
              </FormControl>
            </Box>
            <Box className="flex flex-col justify-evenly items-center h-full w-1/2">
              {/* Select de gerente */}
              <Autocomplete
                disablePortal
                id="combo-box-demo"
                options={props.listaGerentes}
                noOptionsText={texts.modalFiltroGerencia.semResultados}
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
                  <TextField
                    {...params}
                    label={texts.modalFiltroGerencia.gerenteResponsavel}
                  />
                )}
              />

              {/* Select de departamento */}
              <FormControl sx={{ width: "15rem" }}>
                <InputLabel id="demo-simple-select-label"
                onClick={() => {
                  lerTexto(texts.modalFiltroGerencia.departamento);
                }}
                >
                  {texts.modalFiltroGerencia.departamento}
                </InputLabel>
                <Select
                  labelId="demo-simple-select-label"
                  id="demo-simple-select"
                  value={props.filtro.departamento}
                  label="Departamento"
                  onChange={selecionarDepartamento}
                >
                  <MenuItem selected value={""}>
                    {texts.modalFiltroGerencia.selecionar}
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
                noOptionsText={texts.modalFiltroGerencia.semResultados}
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
                  <TextField
                    {...params}
                    label={texts.modalFiltroGerencia.analistaResponsavel}
                  />
                )}
              />
            </Box>
          </Box>

          {/* CheckBox em ata, em pauta e em edição */}
          {props.filtroProposta ? (
            <Box className="flex flex-row w-3/4">
              <RadioGroup
                className="justify-between w-full"
                row
                value={selectedValue}
                onChange={handleChange}
              >
                <FormControlLabel
                  value="Ata"
                  control={<Radio />}
                  label="Em Ata"
                />
                <FormControlLabel
                  value="Pauta"
                  control={<Radio />}
                  label="Em Pauta"
                />
                <FormControlLabel
                  value="Nada"
                  control={<Radio />}
                  label="Em Edição"
                />
              </RadioGroup>
            </Box>
          ) : null}

          {/* Botão de limpar filtros */}
          <Button
            onClick={limparFiltro}
            variant="contained"
            disableElevation
            color="primary"
            sx={{
              marginTop: "4%",
              width: "8rem",
              height: "3rem",
              fontSize: FontConfig.normal,
            }}
          >
            {texts.modalFiltroGerencia.limparFilros}
          </Button>
        </Box>
      </Fade>
    </Modal>
  );
};

export default ModalFiltroGerencia;
