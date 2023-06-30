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
  ListItemIcon,
  ListItemText,
  ListItemButton,
  Collapse,
  Checkbox,
  FormControlLabel,
  Box,
} from "@mui/material";

import TextLanguageContext from "../../../service/TextLanguageContext";
import FontContext from "../../../service/FontContext";
import SpeechSynthesisContext from "../../../service/SpeechSynthesisContext";

import ExpandLess from "@mui/icons-material/ExpandLess";
import ExpandMore from "@mui/icons-material/ExpandMore";

import AbcOutlinedIcon from "@mui/icons-material/AbcOutlined";
import PinOutlinedIcon from "@mui/icons-material/PinOutlined";
import OutlinedFlagIcon from "@mui/icons-material/OutlinedFlag";
import CalendarMonthOutlinedIcon from "@mui/icons-material/CalendarMonthOutlined";
import TodayOutlinedIcon from "@mui/icons-material/TodayOutlined";
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

import UsuarioService from "../../../service/usuarioService";

export default function SiddeBar(props) {
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
      tipo: texts.modalOrdenacao.titulo,
    },
    {
      id: 2,
      tipo: texts.modalOrdenacao.numeroSequencial,
    },
    {
      id: 3,
      tipo: texts.modalOrdenacao.score,
    },
    {
      id: 4,
      tipo: texts.modalOrdenacao.dataReuniao,
    },
    {
      id: 5,
      tipo: texts.modalOrdenacao.dataDeCriacao,
    },
  ];

  // Array com as opções de filtro
  const opcoesFiltrar = [
    {
      id: 1,
      tipo: "Status",
    },
    {
      id: 3,
      tipo: "Fórum",
    },
    {
      id: 4,
      tipo: "Departamento",
    },
    {
      id: 5,
      tipo: "Tamanho",
    },
    {
      id: 8,
      tipo: "Atribuído à",
    },
    {
      id: 9,
      tipo: "Apreciada",
    },
    {
      id: 10,
      tipo: "Publicada",
    },
    {
      id: 2,
      tipo: "Gerente Responsável",
    },
    {
      id: 6,
      tipo: "Analista Responsável",
    },
    {
      id: 7,
      tipo: "Solicitante",
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
        >
          {texts.modalOrdenacao.filtrar}
        </Typography>
        <Divider />
      </Box>
      {props.valorAba != 5 ? (
        <Box className="flex flex-col w-full items-center mt-5">
          {opcoesFiltrar.map((opcao, index) =>
            props.valorAba == 1 && opcao.id <= 6 ? (
              <Input
                key={index}
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
                key={index}
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
                key={index}
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
                key={index}
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
        <Box className="flex flex-col w-full items-center mt-1">
          <Typography
            sx={{
              marginY: "0.3rem",
              textAlign: "center",
              fontSize: FontConfig.medium,
              fontWeight: 600,
              color: "text.secondary",
            }}
          >
            Sem Filtro...
          </Typography>
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
        >
          {texts.modalOrdenacao.ordenar}
        </Typography>
        <Divider />
      </Box>
      <Box>
        {opcoesOrdenar.map((opcao, index) => (
          <>
            {props.valorAba < 4 && opcao.id != 4 && opcao.id != 2 ? (
              <ItemOrdenacao
                opcao={opcao}
                key={index}
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
              <ItemOrdenacao
                opcao={opcao}
                key={index}
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
                <ItemOrdenacao
                  opcao={opcao}
                  key={index}
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
            )}
          </>
        ))}
      </Box>
    </List>
  );
}

function ItemOrdenacao(props) {
  // Context para alterar a linguagem do sistema
  const { texts } = useContext(TextLanguageContext);

  /** Context para ler o texto da tela */
  const { lerTexto, lendoTexto } = useContext(SpeechSynthesisContext);

  // useState utilizado para abrir o modal
  const [open, setOpen] = useState(false);

  // handleClick para fechar o modal
  const handleClick = () => {
    setOpen(!open);
  };

  /** Função para mudar o valor do checkbox de ordenação por score "Menor Score" */
  const mudarCheck1 = () => {
    props.setOrdenacaoScore([!props.ordenacaoScore[0], false]);
  };

  /** Função para mudar o valor do checkbox de ordenação por score "Maior Score" */
  const mudarCheck2 = () => {
    props.setOrdenacaoScore([false, !props.ordenacaoScore[1]]);
  };

  /** Função para mudar o valor do checkbox de ordenação por título "Z-A"  */
  const mudarCheck3 = () => {
    props.setOrdenacaoTitulo([!props.ordenacaoTitulo[0], false]);
  };

  /** Função para mudar o valor do checkbox de ordenação por título "A-Z"  */
  const mudarCheck4 = () => {
    props.setOrdenacaoTitulo([false, !props.ordenacaoTitulo[1]]);
  };

  /** Função para mudar o valor do checkbox de ordenação por data "Mais Velha" */
  const mudarCheck5 = () => {
    props.setOrdenacaoDate([!props.ordenacaoDate[0], false]);
  };

  /** Função para mudar o valor do checkbox de ordenação por data "Mais Nova" */
  const mudarCheck6 = () => {
    props.setOrdenacaoDate([false, !props.ordenacaoDate[1]]);
  };

  /** Função para mudar o valor do checkbox de ordenação por número sequencial "Decrescente" */
  const mudarCheck7 = () => {
    props.setOrdenacaoNum([!props.ordenacaoNum[0], false]);
  };

  /** Função para mudar o valor do checkbox de ordenação por número sequencial "Crescente" */
  const mudarCheck8 = () => {
    props.setOrdenacaoNum([false, !props.ordenacaoNum[1]]);
  };

  return (
    <>
      <ListItemButton onClick={handleClick}>
        <ListItemIcon>
          {props.opcao.id == 1 ? (
            <AbcOutlinedIcon />
          ) : props.opcao.id == 2 ? (
            <PinOutlinedIcon />
          ) : props.opcao.id == 3 ? (
            <OutlinedFlagIcon />
          ) : props.opcao.id == 4 ? (
            <CalendarMonthOutlinedIcon />
          ) : props.opcao.id == 5 ? (
            <TodayOutlinedIcon />
          ) : null}
        </ListItemIcon>
        {props.valorAba == 4 && props.opcao.id == 2 ? (
          <ListItemText
            onClick={() => {
              lerTexto("PPM");
            }}
            primary="PPM"
          />
        ) : (
          <ListItemText
            onClick={() => {
              lerTexto(props.opcao.tipo);
            }}
            primary={props.opcao.tipo}
          />
        )}
        {open ? <ExpandLess /> : <ExpandMore />}
      </ListItemButton>
      <Divider />
      <Collapse in={open}>
        <List component="div">
          <ListItemButton>
            {props.opcao.id == 1 ? (
              <FormControlLabel
                checked={props.ordenacaoTitulo[1]}
                onChange={mudarCheck4}
                control={<Checkbox />}
                label={texts.modalOrdenacao.az}
                onClick={() => {
                  if (lendoTexto) {
                    lerTexto(texts.modalOrdenacao.az);
                  }
                }}
              />
            ) : props.opcao.id == 2 ? (
              <FormControlLabel
                checked={props.ordenacaoNum[1]}
                onChange={mudarCheck8}
                control={<Checkbox />}
                label={texts.modalOrdenacao.crescente}
                onClick={() => {
                  if (lendoTexto) {
                    lerTexto(texts.modalOrdenacao.crescente);
                  }
                }}
              />
            ) : props.opcao.id == 3 ? (
              <FormControlLabel
                checked={props.ordenacaoScore[1]}
                onChange={mudarCheck2}
                control={<Checkbox />}
                label={texts.modalOrdenacao.maiorScore}
                onClick={() => {
                  if (lendoTexto) {
                    lerTexto(texts.modalOrdenacao.maiorScore);
                  }
                }}
              />
            ) : props.opcao.id == 4 ? (
              <FormControlLabel
                checked={props.ordenacaoDate[1]}
                onChange={mudarCheck6}
                control={<Checkbox />}
                label={texts.modalOrdenacao.maisRecente}
                onClick={() => {
                  if (lendoTexto) {
                    lerTexto(texts.modalOrdenacao.maisRecente);
                  }
                }}
              />
            ) : props.opcao.id == 5 ? (
              <FormControlLabel
                checked={props.ordenacaoDate[1]}
                onChange={mudarCheck6}
                control={<Checkbox />}
                label={texts.modalOrdenacao.maisRecente}
                onClick={() => {
                  if (lendoTexto) {
                    lerTexto(texts.modalOrdenacao.maisRecente);
                  }
                }}
              />
            ) : (
              <Box />
            )}
          </ListItemButton>
          <Divider />
          <ListItemButton>
            {props.opcao.id == 1 ? (
              <FormControlLabel
                checked={props.ordenacaoTitulo[0]}
                onChange={mudarCheck3}
                control={<Checkbox />}
                label={texts.modalOrdenacao.za}
                onClick={() => {
                  if (lendoTexto) {
                    lerTexto(texts.modalOrdenacao.za);
                  }
                }}
              />
            ) : props.opcao.id == 2 ? (
              <FormControlLabel
                checked={props.ordenacaoNum[0]}
                onChange={mudarCheck7}
                control={<Checkbox />}
                label={texts.modalOrdenacao.decrescente}
                onClick={() => {
                  if (lendoTexto) {
                    lerTexto(texts.modalOrdenacao.decrescente);
                  }
                }}
              />
            ) : props.opcao.id == 3 ? (
              <FormControlLabel
                checked={props.ordenacaoScore[0]}
                onChange={mudarCheck1}
                control={<Checkbox />}
                label={texts.modalOrdenacao.menorScore}
                onClick={() => {
                  if (lendoTexto) {
                    lerTexto(texts.modalOrdenacao.menorScore);
                  }
                }}
              />
            ) : props.opcao.id == 4 ? (
              <FormControlLabel
                checked={props.ordenacaoDate[0]}
                onChange={mudarCheck5}
                control={<Checkbox />}
                label={texts.modalOrdenacao.maisAntiga}
                onClick={() => {
                  if (lendoTexto) {
                    lerTexto(texts.modalOrdenacao.maisAntiga);
                  }
                }}
              />
            ) : props.opcao.id == 5 ? (
              <FormControlLabel
                checked={props.ordenacaoDate[0]}
                onChange={mudarCheck5}
                control={<Checkbox />}
                label={texts.modalOrdenacao.maisAntiga}
                onClick={() => {
                  if (lendoTexto) {
                    lerTexto(texts.modalOrdenacao.maisAntiga);
                  }
                }}
              />
            ) : (
              <Box />
            )}
          </ListItemButton>
          <Divider />
        </List>
      </Collapse>
    </>
  );
}

function Input(props) {
  // Context para alterar a linguagem do sistema
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  /** Context para ler o texto da tela */
  const { lerTexto } = useContext(SpeechSynthesisContext);

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
        <Box className="w-full flex justify-start">
          <Autocomplete
            {...gerentes}
            sx={{ width: "72%", marginLeft: "8%" }}
            noOptionsText={texts.modalFiltroGerencia.semResultados}
            value={props.filtro.gerente}
            id="disable-close-on-select"
            disableCloseOnSelect
            onInputChange={(e) => {
              pesquisarGerentes(e);
            }}
            onChange={(e, value) => {
              selecionarGerente(e, value);
            }}
            renderInput={(params) => (
              <TextField
                {...params}
                label={texts.modalFiltroGerencia.gerenteResponsavel}
                variant="standard"
              />
            )}
          />
        </Box>
      ) : props.opcao.id == 6 ? (
        <Box className="w-full flex justify-start">
          <Autocomplete
            {...analistas}
            sx={{ width: "72%", marginLeft: "8%" }}
            noOptionsText={texts.modalFiltroGerencia.semResultados}
            value={props.filtro.analista}
            id="disable-close-on-select"
            disableCloseOnSelect
            onInputChange={(e) => {
              pesquisarAnalistas(e);
            }}
            onChange={(e, value) => {
              selecionarAnalista(e, value);
            }}
            renderInput={(params) => (
              <TextField
                {...params}
                label={texts.modalFiltroGerencia.analistaResponsavel}
                variant="standard"
              />
            )}
          />
        </Box>
      ) : props.opcao.id == 7 ? (
        <Box className="w-full flex justify-start">
          <Autocomplete
            {...solicitantes}
            sx={{ width: "72%", marginLeft: "8%" }}
            noOptionsText={texts.modalFiltroGerencia.semResultados}
            value={props.filtro.solicitante}
            id="disable-close-on-select"
            disableCloseOnSelect
            onInputChange={(e) => {
              pesquisarSolicitantes(e);
            }}
            onChange={(e, value) => {
              selecionarSolicitante(e, value);
            }}
            renderInput={(params) => (
              <TextField
                {...params}
                label={texts.modalFiltroGerencia.labelSolicitante}
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
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                value={props.filtro.status}
                label={"Icon" + texts.modalFiltroGerencia.status}
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
                label={"Icon" + texts.modalFiltroGerencia.status}
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
            )
          ) : props.opcao.id == 3 ? (
            <Select
              labelId="demo-simple-select-label"
              id="demo-simple-select"
              value={props.filtro.forum}
              label={"Icon" + texts.modalFiltroGerencia.forum}
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
          ) : props.opcao.id == 4 ? (
            <Select
              labelId="demo-simple-select-label"
              id="demo-simple-select"
              value={props.filtro.departamento}
              label="icon Departamento"
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
          ) : props.opcao.id == 5 ? (
            <Select
              labelId="demo-simple-select-label"
              id="demo-simple-select"
              value={props.filtro.tamanho}
              label={"Icon" + texts.modalFiltroGerencia.tamanho}
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
          ) : props.opcao.id == 8 ? (
            <Select
              labelId="demo-simple-select-label"
              id="demo-simple-select"
              value={props.filtro.presenteEm}
              label={"Icon" + texts.modalFiltroGerencia.atribuido}
              onChange={selecionarAtribuicao}
            >
              <MenuItem selected value={""}>
                {texts.modalFiltroGerencia.selecionar}
              </MenuItem>
              <MenuItem value={"Ata"}>{texts.modalFiltroGerencia.ata}</MenuItem>
              <MenuItem value={"Pauta"}>
                {texts.modalFiltroGerencia.pauta}
              </MenuItem>
              <MenuItem value={"Solta"}>
                {texts.modalFiltroGerencia.semAtribuicao}
              </MenuItem>
            </Select>
          ) : props.opcao.id == 9 ? (
            <Select
              labelId="demo-select-small-label"
              id="demo-select-small"
              value={tipoApreciacao}
              label={"icon " + props.opcao.tipo}
              onChange={selecionarApreciacao}
            >
              <MenuItem value="">
                <em>Sem filtro</em>
              </MenuItem>
              <MenuItem value={"Apreciada"}>Apreciada</MenuItem>
              <MenuItem value={"NaoApreciada"}>Não Apreciada</MenuItem>
            </Select>
          ) : props.opcao.id == 10 ? (
            <Select
              labelId="demo-select-small-label"
              id="demo-select-small"
              value={tipoPublicacao}
              label={"icon " + props.opcao.tipo}
              onChange={selecionarPublicidade}
            >
              <MenuItem value="">
                <em>Sem filtro</em>
              </MenuItem>
              <MenuItem value={"Publicado"}>Publicado</MenuItem>
              <MenuItem value={"NaoPublicado"}>Não Publicado</MenuItem>
            </Select>
          ) : null}
        </FormControl>
      )}
    </Box>
  );
}
