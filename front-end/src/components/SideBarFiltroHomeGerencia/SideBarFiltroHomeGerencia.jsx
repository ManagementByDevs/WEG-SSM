import React, { useContext, useState } from "react";
import {
  List,
  Typography,
  Divider,
  InputLabel,
  MenuItem,
  FormControl,
  Select,
  Autocomplete,
  TextField,
  Box,
  Button,
} from "@mui/material";

import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";
import SpeechSynthesisContext from "../../service/SpeechSynthesisContext";

import BookmarkBorderOutlinedIcon from "@mui/icons-material/BookmarkBorderOutlined";
import AssignmentIndOutlinedIcon from "@mui/icons-material/AssignmentIndOutlined";
import Diversity3OutlinedIcon from "@mui/icons-material/Diversity3Outlined";
import BusinessOutlinedIcon from "@mui/icons-material/BusinessOutlined";
import AspectRatioOutlinedIcon from "@mui/icons-material/AspectRatioOutlined";
import PersonOutlineOutlinedIcon from "@mui/icons-material/PersonOutlineOutlined";
import PersonSearchOutlinedIcon from "@mui/icons-material/PersonSearchOutlined";
import ContentPasteSearchOutlinedIcon from "@mui/icons-material/ContentPasteSearchOutlined";
import VisibilityOutlinedIcon from "@mui/icons-material/VisibilityOutlined";
import PublicOutlinedIcon from "@mui/icons-material/PublicOutlined";

import SideBarOrdenacao from "../SideBarOrdenacao/SideBarOrdenacao";

import UsuarioService from "../../service/usuarioService";

/** SliderBar para os filtros e ordenações */
export default function SliderBar(props) {
  // Context para alterar a linguagem do sistema
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  /** Context para ler o texto da tela */
  const { lerTexto } = useContext(SpeechSynthesisContext);

  // Array com as opções de filtro
  const opcoesOrdenar = [
    {
      id: 1,
      tipo: texts.sideBarFiltro.titulo,
    },
    {
      id: 2,
      tipo: texts.sideBarFiltro.numeroSequencial,
    },
    {
      id: 3,
      tipo: texts.sideBarFiltro.score,
    },
    {
      id: 4,
      tipo: texts.sideBarFiltro.dataReuniao,
    },
    {
      id: 5,
      tipo: texts.sideBarFiltro.dataDeCriacao,
    },
  ];

  // Array com as opções de filtro
  const opcoesFiltrar = [
    {
      id: 1,
      tipo: texts.sideBarFiltro.status,
    },
    {
      id: 3,
      tipo: texts.sideBarFiltro.forum,
    },
    {
      id: 4,
      tipo: texts.sideBarFiltro.departamento,
    },
    {
      id: 5,
      tipo: texts.sideBarFiltro.tamanho,
    },
    {
      id: 8,
      tipo: texts.sideBarFiltro.atribuidoA,
    },
    {
      id: 9,
      tipo: texts.sideBarFiltro.apreciada,
    },
    {
      id: 10,
      tipo: texts.sideBarFiltro.publicada,
    },
    {
      id: 2,
      tipo: texts.sideBarFiltro.gerenteResponsavel,
    },
    {
      id: 6,
      tipo: texts.sideBarFiltro.analistaResponsavel,
    },
    {
      id: 7,
      tipo: texts.sideBarFiltro.solicitante,
    },
  ];

  const [age, setAge] = React.useState("");

  const handleChange = (event) => {
    setAge(event.target.value);
  };

  return (
    <List
      sx={{ width: "18rem", bgcolor: "background.paper", padding: "0" }}
      component="nav"
      className="h-full flex flex-col"
      aria-labelledby="nested-list-subheader"
    >
      <Box className="p-2" sx={{ backgroundColor: "primary.main" }}>
        <Divider />
        <Typography
          sx={{
            marginY: "0.3rem",
            textAlign: "center",
            fontSize: FontConfig.big,
            fontWeight: 600,
            color: "text.white",
          }}
          onClick={() => {
            lerTexto(texts.sideBarFiltro.filtrar);
          }}
        >
          {texts.sideBarFiltro.filtrar}
        </Typography>
        <Divider />
      </Box>
      {props.valorAba != 5 ? (
        <Box className="flex flex-col w-full items-center mt-5">
          {opcoesFiltrar.map((opcao, index) =>
            props.valorAba == 1 && opcao.id <= 6 ? (
              <Input
                key={"Filtro " + index}
                age={age}
                handleChange={handleChange}
                opcao={opcao}
                filtro={props.filtro}
                setFiltro={props.setFiltro}
                filtroAtas={props.filtroAtas}
                setFiltroAtas={props.setFiltroAtas}
                listaForuns={props.listaForuns}
                listaDepartamentos={props.listaDepartamentos}
                listaSolicitantes={props.listaSolicitantes}
                setListaSolicitantes={props.setListaSolicitantes}
                listaGerentes={props.listaGerentes}
                setListaGerentes={props.setListaGerentes}
                listaAnalistas={props.listaAnalistas}
                setListaAnalistas={props.setListaAnalistas}
                filtroProposta={props.filtroProposta}
              />
            ) : (props.valorAba == 2 || props.valorAba == 3) &&
              opcao.id > 1 &&
              opcao.id <= 6 ? (
              <Input
                key={"Filtro " + index}
                age={age}
                handleChange={handleChange}
                opcao={opcao}
                filtro={props.filtro}
                setFiltro={props.setFiltro}
                filtroAtas={props.filtroAtas}
                setFiltroAtas={props.setFiltroAtas}
                listaForuns={props.listaForuns}
                listaDepartamentos={props.listaDepartamentos}
                listaSolicitantes={props.listaSolicitantes}
                setListaSolicitantes={props.setListaSolicitantes}
                listaGerentes={props.listaGerentes}
                setListaGerentes={props.setListaGerentes}
                listaAnalistas={props.listaAnalistas}
                setListaAnalistas={props.setListaAnalistas}
                filtroProposta={props.filtroProposta}
              />
            ) : props.valorAba == 4 && opcao.id <= 8 ? (
              <Input
                key={"Filtro " + index}
                age={age}
                handleChange={handleChange}
                opcao={opcao}
                filtro={props.filtro}
                setFiltro={props.setFiltro}
                filtroAtas={props.filtroAtas}
                setFiltroAtas={props.setFiltroAtas}
                listaForuns={props.listaForuns}
                listaDepartamentos={props.listaDepartamentos}
                listaSolicitantes={props.listaSolicitantes}
                setListaSolicitantes={props.setListaSolicitantes}
                listaGerentes={props.listaGerentes}
                setListaGerentes={props.setListaGerentes}
                listaAnalistas={props.listaAnalistas}
                setListaAnalistas={props.setListaAnalistas}
                filtroProposta={props.filtroProposta}
              />
            ) : props.valorAba == 6 && (opcao.id == 9 || opcao.id == 10) ? (
              <Input
                key={"Filtro " + index}
                age={age}
                handleChange={handleChange}
                opcao={opcao}
                filtro={props.filtro}
                setFiltro={props.setFiltro}
                filtroAtas={props.filtroAtas}
                setFiltroAtas={props.setFiltroAtas}
                listaForuns={props.listaForuns}
                listaDepartamentos={props.listaDepartamentos}
                listaSolicitantes={props.listaSolicitantes}
                setListaSolicitantes={props.setListaSolicitantes}
                listaGerentes={props.listaGerentes}
                setListaGerentes={props.setListaGerentes}
                listaAnalistas={props.listaAnalistas}
                setListaAnalistas={props.setListaAnalistas}
                filtroProposta={props.filtroProposta}
              />
            ) : null
          )}
        </Box>
      ) : (
        <Box className="flex flex-col w-full items-center my-3">
          <Typography
            sx={{
              textAlign: "center",
              fontSize: FontConfig.big,
              fontWeight: 600,
              color: "text.secondary",
            }}
          >{texts.sideBarFiltro.semFiltro}</Typography>
        </Box>
      )}
      <Box className="my-1 p-2" sx={{ backgroundColor: "primary.main" }}>
        <Divider />
        <Typography
          sx={{
            marginY: "0.3rem",
            textAlign: "center",
            fontSize: FontConfig.big,
            fontWeight: 600,
            color: "text.white",
          }}
          onClick={() => {
            lerTexto(texts.sideBarFiltro.ordenar);
          }}
        >
          {texts.sideBarFiltro.ordenar}
        </Typography>
        <Divider />
      </Box>
      <Box>
        {opcoesOrdenar.map((opcao, index) =>
          props.valorAba < 4 && opcao.id != 4 && opcao.id != 2 ? (
            <SideBarOrdenacao
              opcao={opcao}
              key={"Ordenação" + index}
              ordenacaoTitulo={props.ordenacaoTitulo}
              setOrdenacaoTitulo={props.setOrdenacaoTitulo}
              ordenacaoNum={props.ordenacaoNum}
              setOrdenacaoNum={props.setOrdenacaoNum}
              ordenacaoScore={props.ordenacaoScore}
              setOrdenacaoScore={props.setOrdenacaoScore}
              ordenacaoDate={props.ordenacaoDate}
              setOrdenacaoDate={props.setOrdenacaoDate}
              valorAba={props.valorAba}
            />
          ) : props.valorAba == 4 && opcao.id != 4 ? (
            <SideBarOrdenacao
              opcao={opcao}
              key={"Ordenação" + index}
              ordenacaoTitulo={props.ordenacaoTitulo}
              setOrdenacaoTitulo={props.setOrdenacaoTitulo}
              ordenacaoNum={props.ordenacaoNum}
              setOrdenacaoNum={props.setOrdenacaoNum}
              ordenacaoScore={props.ordenacaoScore}
              setOrdenacaoScore={props.setOrdenacaoScore}
              ordenacaoDate={props.ordenacaoDate}
              setOrdenacaoDate={props.setOrdenacaoDate}
              valorAba={props.valorAba}
            />
          ) : (
            props.valorAba > 4 &&
            opcao.id != 1 && (
              <SideBarOrdenacao
                opcao={opcao}
                key={"Ordenação" + index}
                ordenacaoTitulo={props.ordenacaoTitulo}
                setOrdenacaoTitulo={props.setOrdenacaoTitulo}
                ordenacaoNum={props.ordenacaoNum}
                setOrdenacaoNum={props.setOrdenacaoNum}
                ordenacaoScore={props.ordenacaoScore}
                setOrdenacaoScore={props.setOrdenacaoScore}
                ordenacaoDate={props.ordenacaoDate}
                setOrdenacaoDate={props.setOrdenacaoDate}
                valorAba={props.valorAba}
              />
            )
          )
        )}
      </Box>
      <Box className="mt-4 flex justify-center">
        <Button
          id="terceiroDemandas"
          className="flex"
          sx={{
            backgroundColor: "primary.main",
            color: "text.white",
            fontSize: FontConfig.default,
          }}
          onClick={props.limparFiltro}
          variant="contained"
          disableElevation
        >
          {texts.sideBarFiltro.limparFilros}
        </Button>
      </Box>
    </List>
  );
}

function Input(props) {
  // Context para alterar a linguagem do sistema
  const { texts } = useContext(TextLanguageContext);

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

  /** Função para atualizar os filtros quando um status for selecionado */
  const selecionarStatus = (event) => {
    props.setFiltro({ ...props.filtro, status: event.target.value });
  };

  /** Função para atualizar os filtros quando a atribuição for selecionada */
  const selecionarAtribuicao = (event) => {
    props.setFiltro({ ...props.filtro, presenteEm: event.target.value });
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

  /**  useState para armezenar o tipo de apreciação selecionada para o value */
  const [tipoApreciacao, setTipoApreciacao] = useState("");

  /** Função para atualizar os filtros quando uma apreciação for selecionada */
  const selecionarApreciacao = (event, value) => {
    if (value.props.value == "Apreciada") {
      setTipoApreciacao("Apreciada");
      props.setFiltroAtas({
        ...props.filtroAtas,
        apreciada: true,
        naoApreciada: false,
      });
    } else if (value.props.value == "NaoApreciada") {
      setTipoApreciacao("NaoApreciada");
      props.setFiltroAtas({
        ...props.filtroAtas,
        apreciada: false,
        naoApreciada: true,
      });
    } else {
      setTipoApreciacao("");
      props.setFiltroAtas({
        ...props.filtroAtas,
        apreciada: false,
        naoApreciada: false,
      });
    }
  };

  /**  useState para armezenar o tipo de publicação selecionada para o value */
  const [tipoPublicacao, setTipoPublicacao] = useState("");

  /** Função para atualizar os filtros quando uma publicação for selecionada */
  const selecionarPublicidade = (event, value) => {
    if (value.props.value == "Publicado") {
      setTipoPublicacao("Publicado");
      props.setFiltroAtas({
        ...props.filtroAtas,
        naoPublicada: false,
        publicada: true,
      });
    } else if (value.props.value == "NaoPublicado") {
      setTipoPublicacao("NaoPublicado");
      props.setFiltroAtas({
        ...props.filtroAtas,
        naoPublicada: true,
        publicada: false,
      });
    } else {
      setTipoPublicacao("");
      props.setFiltroAtas({
        ...props.filtroAtas,
        naoPublicada: false,
        publicada: false,
      });
    }
  };

  const gerentes = {
    options: props.listaGerentes,
    getOptionLabel: (option) => option.nome,
  };

  const analistas = {
    options: props.listaAnalistas,
    getOptionLabel: (option) => option.nome,
  };

  const solicitantes = {
    options: props.listaSolicitantes,
    getOptionLabel: (option) => option.nome,
  };

  return (
    <Box className="flex flex-col items-center w-full mb-5">
      {props.opcao.id == 2 ? (

        // Input de gerente
        <Box className="w-full flex justify-start">
          <Autocomplete
            {...gerentes}
            sx={{ width: "82%", marginLeft: "8%" }}
            noOptionsText={texts.modalFiltroGerencia?.semResultados}
            value={props.filtro.gerente}
            id="disable-close-on-select"
            disableCloseOnSelect
            onInputChange={(e) => {
              pesquisarGerentes(e);
            }}
            onChange={(e, value) => {
              selecionarGerente(e, value);
            }}
            isOptionEqualToValue={(option, value) => option.id == value.id}
            renderInput={(params) => (
              <TextField
                {...params}
                label={texts.sideBarFiltro.gerenteResponsavel}
                variant="standard"
              />
            )}
          />
        </Box>
      ) : props.opcao.id == 6 ? (

        // Input de analista responsável
        <Box className="w-full flex justify-start">
          <Autocomplete
            {...analistas}
            sx={{ width: "82%", marginLeft: "8%" }}
            noOptionsText={texts.modalFiltroGerencia?.semResultados}
            value={props.filtro.analista}
            id="disable-close-on-select"
            disableCloseOnSelect
            onInputChange={(e) => {
              pesquisarAnalistas(e);
            }}
            onChange={(e, value) => {
              selecionarAnalista(e, value);
            }}
            isOptionEqualToValue={(option, value) => option.id == value.id}
            renderInput={(params) => (
              <TextField
                {...params}
                label={texts.sideBarFiltro.analistaResponsavel}
                variant="standard"
              />
            )}
          />
        </Box>
      ) : props.opcao.id == 7 ? (

        // Input de solicitante
        <Box className="w-full flex justify-start">
          <Autocomplete
            {...solicitantes}
            sx={{ width: "82%", marginLeft: "8%" }}
            noOptionsText={texts.modalFiltroGerencia?.semResultados}
            value={props.filtro.solicitante}
            id="disable-close-on-select"
            disableCloseOnSelect
            onInputChange={(e) => {
              pesquisarSolicitantes(e);
            }}
            onChange={(e, value) => {
              selecionarSolicitante(e, value);
            }}
            isOptionEqualToValue={(option, value) => option.id == value.id}
            renderInput={(params) => (
              <TextField
                {...params}
                label={texts.sideBarFiltro.labelSolicitante}
                variant="standard"
              />
            )}
          />
        </Box>
      ) : (
        <FormControl sx={{ width: "85%" }} size="small">
          <InputLabel
            id="demo-select-small-label"
            className="flex items-center"
          >
            {props.opcao.id == 1 ? (
              <BookmarkBorderOutlinedIcon className="mr-2" />
            ) : props.opcao.id == 2 ? (
              <AssignmentIndOutlinedIcon className="mr-2" />
            ) : props.opcao.id == 3 ? (
              <Diversity3OutlinedIcon className="mr-2" />
            ) : props.opcao.id == 4 ? (
              <BusinessOutlinedIcon className="mr-2" />
            ) : props.opcao.id == 5 ? (
              <AspectRatioOutlinedIcon className="mr-2" />
            ) : props.opcao.id == 6 ? (
              <PersonSearchOutlinedIcon className="mr-2" />
            ) : props.opcao.id == 7 ? (
              <PersonOutlineOutlinedIcon className="mr-2" />
            ) : props.opcao.id == 8 ? (
              <ContentPasteSearchOutlinedIcon className="mr-2" />
            ) : props.opcao.id == 9 ? (
              <VisibilityOutlinedIcon className="mr-2" />
            ) : props.opcao.id == 10 ? (
              <PublicOutlinedIcon className="mr-2" />
            ) : null}
            {props.opcao.tipo}
          </InputLabel>
          {props.opcao.id == 1 ? (
            props?.valorAba == "1" ? (

              // Input de status para a aba "Minhas Demandas"
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                value={props.filtro.status}
                label={"Icon" + texts.sideBarFiltro.status}
                onChange={selecionarStatus}
              >
                <MenuItem selected value={""}>
                  {texts.sideBarFiltro.semFiltro}
                </MenuItem>
                <MenuItem value={"CANCELLED"}>
                  {texts.sideBarFiltro.reprovada}
                </MenuItem>
                <MenuItem value={"BACKLOG_REVISAO"}>
                  {texts.sideBarFiltro.aguardandoRevisao}
                </MenuItem>
                <MenuItem value={"BACKLOG_EDICAO"}>
                  {texts.sideBarFiltro.aguardandoEdicao}
                </MenuItem>
                <MenuItem value={"BACKLOG_APROVACAO"}>
                  {texts.sideBarFiltro.emAprovacao}
                </MenuItem>
                <MenuItem value={"ASSESSMENT"}>
                  {texts.sideBarFiltro.aprovada}
                </MenuItem>
                <MenuItem value={"ASSESSMENT_APROVACAO"}>
                  {texts.sideBarFiltro.emAndamento}
                </MenuItem>
                <MenuItem value={"DONE"}>
                  {texts.sideBarFiltro.emDesenvolvimento}
                </MenuItem>
              </Select>
            ) : (

              // Input de status para a aba "Propostas"
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                value={props.filtro.status}
                label={"Icon" + texts.sideBarFiltro.status}
                onChange={selecionarStatus}
              >
                <MenuItem selected value={""}>
                  {texts.sideBarFiltro.semFiltro}
                </MenuItem>
                <MenuItem value={"CANCELLED"}>
                  {texts.sideBarFiltro.cancelled}
                </MenuItem>
                <MenuItem value={"BUSINESS_CASE"}>
                  {texts.sideBarFiltro.businessCase}
                </MenuItem>
                <MenuItem value={"DONE"}>{texts.sideBarFiltro.done}</MenuItem>
                <MenuItem value={"ASSESSMENT_APROVACAO"}>
                  {texts.sideBarFiltro.assessment}
                </MenuItem>
                <MenuItem value={"ASSESSMENT_EDICAO"}>
                  {texts.sideBarFiltro.assessmentEdicao}
                </MenuItem>
                <MenuItem value={"ASSESSMENT_COMISSAO"}>
                  {texts.sideBarFiltro.assessmentComissao}
                </MenuItem>
                <MenuItem value={"ASSESSMENT_DG"}>
                  {texts.sideBarFiltro.assessmentDg}
                </MenuItem>
              </Select>
            )
          ) : props.opcao.id == 3 ? (

            // Input de fórum
            <Select
              labelId="demo-simple-select-label"
              id="demo-simple-select"
              value={props.filtro.forum}
              label={"Icon" + texts.sideBarFiltro.forum}
              onChange={selecionarForum}
            >
              <MenuItem selected value={""}>
                {texts.sideBarFiltro.semFiltro}
              </MenuItem>
              {props.listaForuns.map((forum) => {
                return (
                  <MenuItem key={"Fórum" + forum.idForum} value={forum}>
                    {forum.nomeForum}
                  </MenuItem>
                );
              })}
            </Select>
          ) : props.opcao.id == 4 ? (

            // Input de departamento
            <Select
              labelId="demo-simple-select-label"
              id="demo-simple-select"
              value={props.filtro.departamento}
              label="icon Departamento"
              onChange={selecionarDepartamento}
            >
              <MenuItem selected value={""}>
                {texts.sideBarFiltro.semFiltro}
              </MenuItem>
              {props.listaDepartamentos.map((departamento) => {
                return (
                  <MenuItem
                    key={"Departamento" + departamento.id}
                    value={departamento}
                  >
                    {departamento.nome}
                  </MenuItem>
                );
              })}
            </Select>
          ) : props.opcao.id == 5 ? (

            // Input de tamanho
            <Select
              labelId="demo-simple-select-label"
              id="demo-simple-select"
              value={props.filtro.tamanho}
              label={"Icon" + texts.sideBarFiltro.tamanho}
              onChange={selecionarTamanho}
            >
              <MenuItem selected value={""}>
                {texts.sideBarFiltro.semFiltro}
              </MenuItem>
              <MenuItem value={"Muito Pequeno"}>
                {texts.sideBarFiltro.muitoPequeno}
              </MenuItem>
              <MenuItem value={"Pequeno"}>
                {texts.sideBarFiltro.pequeno}
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
          ) : props.opcao.id == 8 ? (

            // Input de "presenteEm/AtribuidoA"
            <Select
              labelId="demo-simple-select-label"
              id="demo-simple-select"
              value={props.filtro.presenteEm}
              label={"Icon" + texts.sideBarFiltro.atribuido}
              onChange={selecionarAtribuicao}
            >
              <MenuItem selected value={""}>
                {texts.sideBarFiltro.semFiltro}
              </MenuItem>
              <MenuItem value={"Ata"}>{texts.sideBarFiltro.ata}</MenuItem>
              <MenuItem value={"Pauta"}>{texts.sideBarFiltro.pauta}</MenuItem>
              <MenuItem value={"Solta"}>
                {texts.sideBarFiltro.semAtribuicao}
              </MenuItem>
            </Select>
          ) : props.opcao.id == 9 ? (

            // Input de "apreciada" (atas)
            <Select
              labelId="demo-select-small-label"
              id="demo-select-small"
              value={tipoApreciacao}
              label={"icon " + props.opcao.tipo}
              onChange={selecionarApreciacao}
            >
              <MenuItem value="">
                <em>{texts.sideBarFiltro.semFiltro}</em>
              </MenuItem>
              <MenuItem value={"Apreciada"}>
                {texts.sideBarFiltro.apreciada}
              </MenuItem>
              <MenuItem value={"NaoApreciada"}>
                {texts.sideBarFiltro.naoApreciada}
              </MenuItem>
            </Select>
          ) : props.opcao.id == 10 ? (

            // Input de "publicada" (atas)
            <Select
              labelId="demo-select-small-label"
              id="demo-select-small"
              value={tipoPublicacao}
              label={"icon " + props.opcao.tipo}
              onChange={selecionarPublicidade}
            >
              <MenuItem value="">
                <em>{texts.sideBarFiltro.semFiltro}</em>
              </MenuItem>
              <MenuItem value={"Publicado"}>
                {texts.sideBarFiltro.publicado}
              </MenuItem>
              <MenuItem value={"NaoPublicado"}>
                {texts.sideBarFiltro.naoPublicado}
              </MenuItem>
            </Select>
          ) : null}
        </FormControl>
      )}
    </Box>
  );
}
