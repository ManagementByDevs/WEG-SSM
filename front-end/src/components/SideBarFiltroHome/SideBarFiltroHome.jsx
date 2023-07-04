import React, { useContext, useState } from "react";
import {
  List,
  Typography,
  Divider,
  InputLabel,
  MenuItem,
  FormControl,
  Select,
  Box,
} from "@mui/material";

import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";
import SpeechSynthesisContext from "../../service/SpeechSynthesisContext";

import BookmarkBorderOutlinedIcon from "@mui/icons-material/BookmarkBorderOutlined";

import UsuarioService from "../../service/usuarioService";

import SideBarOrdenacao from "../SideBarOrdenacao/SideBarOrdenacao";

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
  ];

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
          onClick={() => {
            lerTexto(texts.sideBarFiltro.filtrar);
          }}
        >
          {texts.sideBarFiltro.filtrar}
        </Typography>
        <Divider />
      </Box>
      <Box className="flex flex-col w-full items-center mt-5">
        {opcoesFiltrar.map((opcao, index) =>
          props.valorAba == 1 && opcao.id == 1 ? (
            <Input
              key={index}
              opcao={opcao}
              listaFiltros={props.listaFiltros}
              setListaFiltros={props.setListaFiltros}
            />
          ) : null
        )}
      </Box>
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
        {opcoesOrdenar.map((opcao, index) => (
          <>
            {props.valorAba < 4 && opcao.id != 4 && opcao.id != 2 ? (
              <SideBarOrdenacao
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
              <SideBarOrdenacao
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
                <SideBarOrdenacao
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

function Input(props) {
  // Context para alterar a linguagem do sistema
  const { texts } = useContext(TextLanguageContext);

  const [status, setStatus] = useState("");

  /** Função para atualizar os filtros quando um status for selecionado */
  const selecionarStatus = (event) => {
    setStatus(event.target.value);
    switch (event.target.value) {
      case "Aprovado":
        props.setListaFiltros([
          !props.listaFiltros[0],
          false,
          false,
          false,
          false,
          false,
          false,
        ]);
        break;
      case "Reprovado":
        props.setListaFiltros([
          false,
          !props.listaFiltros[1],
          false,
          false,
          false,
          false,
          false,
        ]);
        break;
      case "Aguardando Edição":
        props.setListaFiltros([
          false,
          false,
          !props.listaFiltros[2],
          false,
          false,
          false,
          false,
        ]);
        break;
      case "Aguardando Revisão":
        props.setListaFiltros([
          false,
          false,
          false,
          !props.listaFiltros[3],
          false,
          false,
          false,
        ]);
        break;
      case "Em Aprovação":
        props.setListaFiltros([
          false,
          false,
          false,
          false,
          !props.listaFiltros[4],
          false,
          false,
        ]);
        break;
      case "Em Andamento":
        props.setListaFiltros([
          false,
          false,
          false,
          false,
          false,
          !props.listaFiltros[5],
          false,
        ]);
        break;
      case "Em Desenvolvimento":
        props.setListaFiltros([
          false,
          false,
          false,
          false,
          false,
          false,
          !props.listaFiltros[6],
        ]);
        break;
      default:
        props.setListaFiltros([
          false,
          false,
          false,
          false,
          false,
          false,
          false,
        ]);
        break;
    }
  };

  return (
    <Box className="flex flex-col items-center w-full mb-5">
      <FormControl sx={{ width: "85%" }} size="small">
        <InputLabel id="demo-select-small-label" className="flex items-center">
          <BookmarkBorderOutlinedIcon className="mr-2" />
          {props.opcao.tipo}
        </InputLabel>
        <Select
          labelId="demo-simple-select-label"
          id="demo-simple-select"
          value={status}
          label={"Icon" + texts.sideBarFiltro.status}
          onChange={selecionarStatus}
        >
          <MenuItem selected value={""}>
            {texts.sideBarFiltro.semFiltro}
          </MenuItem>
          <MenuItem value={"Aprovado"}>
            {texts.sideBarFiltro.aprovada}
          </MenuItem>
          <MenuItem value={"Reprovado"}>
            {texts.sideBarFiltro.reprovada}
          </MenuItem>
          <MenuItem value={"Aguardando Edição"}>
            {texts.sideBarFiltro.aguardandoEdicao}
          </MenuItem>
          <MenuItem value={"Aguardando Revisão"}>
            {texts.sideBarFiltro.aguardandoRevisao}
          </MenuItem>
          <MenuItem value={"Em Aprovação"}>
            {texts.sideBarFiltro.emAprovacao}
          </MenuItem>
          <MenuItem value={"Em Andamento"}>
            {texts.sideBarFiltro.emAndamento}
          </MenuItem>
          <MenuItem value={"Em Desenvolvimento"}>
            {texts.sideBarFiltro.emDesenvolvimento}
          </MenuItem>
        </Select>
      </FormControl>
    </Box>
  );
}
