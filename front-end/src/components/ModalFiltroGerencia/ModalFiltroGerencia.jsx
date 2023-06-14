import React, { useContext } from "react";
import { Modal, Typography, Box, Button, InputLabel, Select, MenuItem, FormControl, Autocomplete, TextField } from "@mui/material";

import Fade from "@mui/material/Fade";
import CloseIcon from "@mui/icons-material/Close";
import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";
import SpeechSynthesisContext from "../../service/SpeechSynthesisContext";

import UsuarioService from "../../service/usuarioService";

/** Componente de filtro exclusivo para a página "HomeGerencia", com diferentes opções de filtragem que o filtro usado para o solicitante */
const ModalFiltroGerencia = (props) => {

  // Context para alterar a linguagem do sistema
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  /** Context para ler o texto da tela */
  const { lerTexto, lendoTexto } = useContext(SpeechSynthesisContext);

  /** Função para limpar os filtros ativos e fechar o modal */
  const limparFiltro = () => {
    if (!lendoTexto) {
      props.setFiltro({
        solicitante: null,
        forum: "",
        tamanho: "",
        gerente: null,
        departamento: "",
        analista: null,
        presenteEm: "",
        status: ""
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
    props.setFiltro({ ...props.filtro, solicitante: value });
  };

  /** Função para atualizar os filtros quando um forum for selecionado */
  const selecionarForum = (event) => {
    props.setFiltro({ ...props.filtro, forum: event.target.value });
  };

  /** Função para atualizar os filtros quando um tamanho for selecionado */
  const selecionarTamanho = (event) => {
    props.setFiltro({ ...props.filtro, tamanho: event.target.value });
  };

  /** Função para atualizar os filtros quando um gerente for selecionado */
  const selecionarGerente = (event, value) => {
    props.setFiltro({ ...props.filtro, gerente: value });
  };

  /** Função para atualizar os filtros quando um departamento for selecionado */
  const selecionarDepartamento = (event) => {
    props.setFiltro({ ...props.filtro, departamento: event.target.value });
  };

  /** Função para atualizar os filtros quando um analista for selecionado */
  const selecionarAnalista = (event, value) => {
    props.setFiltro({ ...props.filtro, analista: value });
  };

  /** Função para atualizar os filtros quando em pauta, em ata ou em edição for selecionado */
  const selecionarPresenteEm = (value) => {
    props.setFiltro({ ...props.filtro, presenteEm: value });
  };

  /** Função para atualizar os filtros quando um status for selecionado */
  const selecionarStatus = (event) => {
    props.setFiltro({ ...props.filtro, status: event.target.value });
  };

  /** Função para atualizar os filtros quando a atribuição for selecionada */
  const selecionarAtribuicao = (event) => {
    props.setFiltro({ ...props.filtro, presenteEm: event.target.value });
  }

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

              {props?.valorAba != "1" ? (
                <>
                  {/* Select de Solicitante */}
                  < Autocomplete
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
                </>
              ) : null}

              {props?.valorAba == "1" || props?.valorAba == "4" ? (
                <>
                  {/* Select de Status */}
                  <FormControl sx={{ width: "15rem" }}>
                    <InputLabel
                      id="demo-simple-select-label"
                      onClick={() => {
                        lerTexto(texts.modalFiltroGerencia.status);
                      }}
                    >
                      {texts.modalFiltroGerencia.status}
                    </InputLabel>
                    {props?.valorAba == "1" ? (
                      <Select
                        labelId="demo-simple-select-label"
                        id="demo-simple-select"
                        value={props.filtro.status}
                        label={texts.modalFiltroGerencia.status}
                        onChange={selecionarStatus}
                      >
                        <MenuItem selected value={""}>
                          {texts.modalFiltroGerencia.selecionar}
                        </MenuItem>
                        <MenuItem value={"CANCELLED"}>
                          {texts.modalFiltroGerencia.reprovada}
                        </MenuItem>
                        <MenuItem value={"BACKLOG_REVISAO"}>
                          {texts.modalFiltroGerencia.aguardandoRevisao}
                        </MenuItem>
                        <MenuItem value={"BACKLOG_EDICAO"}>
                          {texts.modalFiltroGerencia.aguardandoEdicao}
                        </MenuItem>
                        <MenuItem value={"BACKLOG_APROVACAO"}>
                          {texts.modalFiltroGerencia.emAprovacao}
                        </MenuItem>
                        <MenuItem value={"ASSESSMENT"}>
                          {texts.modalFiltroGerencia.aprovada}
                        </MenuItem>
                        <MenuItem value={"ASSESSMENT_APROVACAO"}>
                          {texts.modalFiltroGerencia.emAndamento}
                        </MenuItem>
                        <MenuItem value={"DONE"}>
                          {texts.modalFiltroGerencia.emDesenvolvimento}
                        </MenuItem>
                      </Select>
                    ) : (
                      <Select
                        labelId="demo-simple-select-label"
                        id="demo-simple-select"
                        value={props.filtro.status}
                        label={texts.modalFiltroGerencia.status}
                        onChange={selecionarStatus}
                      >
                        <MenuItem selected value={""}>
                          {texts.modalFiltroGerencia.selecionar}
                        </MenuItem>
                        <MenuItem value={"CANCELLED"}>
                          {texts.modalFiltroGerencia.cancelled}
                        </MenuItem>
                        <MenuItem value={"BUSINESS_CASE"}>
                          {texts.modalFiltroGerencia.businessCase}
                        </MenuItem>
                        <MenuItem value={"DONE"}>
                          {texts.modalFiltroGerencia.done}
                        </MenuItem>
                        <MenuItem value={"ASSESSMENT_APROVACAO"}>
                          {texts.modalFiltroGerencia.assessment}
                        </MenuItem>
                        <MenuItem value={"ASSESSMENT_EDICAO"}>
                          {texts.modalFiltroGerencia.assessmentEdicao}
                        </MenuItem>
                        <MenuItem value={"ASSESSMENT_COMISSAO"}>
                          {texts.modalFiltroGerencia.assessmentComissao}
                        </MenuItem>
                        <MenuItem value={"ASSESSMENT_DG"}>
                          {texts.modalFiltroGerencia.assessmentDg}
                        </MenuItem>
                      </Select>
                    )}
                  </FormControl>
                </>
              ) : null}

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
                <InputLabel
                  id="demo-simple-select-label"
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

              {props?.valorAba == "4" ? (
                <>
                  {/* Select "presenteEm" */}
                  <FormControl sx={{ width: "15rem" }}>
                    <InputLabel
                      id="demo-simple-select-label"
                      onClick={() => {
                        lerTexto(texts.modalFiltroGerencia.atribuido);
                      }}
                    >
                      {texts.modalFiltroGerencia.atribuido}
                    </InputLabel>
                    <Select
                      labelId="demo-simple-select-label"
                      id="demo-simple-select"
                      value={props.filtro.presenteEm}
                      label={texts.modalFiltroGerencia.atribuido}
                      onChange={selecionarAtribuicao}
                    >
                      <MenuItem selected value={""}>
                        {texts.modalFiltroGerencia.selecionar}
                      </MenuItem>
                      <MenuItem value={"Ata"}>
                        {texts.modalFiltroGerencia.ata}
                      </MenuItem>
                      <MenuItem value={"Pauta"}>
                        {texts.modalFiltroGerencia.pauta}
                      </MenuItem>
                      <MenuItem value={"Solta"}>
                        {texts.modalFiltroGerencia.semAtribuicao}
                      </MenuItem>
                    </Select>
                  </FormControl>
                </>
              ) : null}
            </Box>
          </Box>

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
