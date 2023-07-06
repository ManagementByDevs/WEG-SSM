import React, { useContext } from "react";
import { List, Typography, Divider, InputLabel, MenuItem, FormControl, Select, Box, Button } from "@mui/material";

import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";
import SpeechSynthesisContext from "../../service/SpeechSynthesisContext";

import BookmarkBorderOutlinedIcon from "@mui/icons-material/BookmarkBorderOutlined";

import SideBarOrdenacao from "../SideBarOrdenacao/SideBarOrdenacao";

/** Barra lateral de filtragem e ordenação das demandas, usada na Página Inicial do Solicitante */
const SliderBar = (props) => {

  /** Context para alterar a linguagem do sistema */
  const { texts } = useContext(TextLanguageContext);

  /** Context para alterar o tamanho da fonte */
  const { FontConfig } = useContext(FontContext);

  /** Context para ler o texto da tela */
  const { lerTexto } = useContext(SpeechSynthesisContext);

  /** Lista com todas as opções disponíveis de ordenação */
  const opcoesOrdenar = [
    {
      id: 1,
      tipo: texts.sideBarFiltro.titulo,
    },
    {
      id: 3,
      tipo: texts.sideBarFiltro.score,
    },
    {
      id: 5,
      tipo: texts.sideBarFiltro.dataDeCriacao,
    }
  ];

  /** Lista com as opções de filtragem */
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
      <Box className="flex flex-col w-full items-center">
        {opcoesFiltrar.map((opcao, index) =>
          props.valorAba == 1 && opcao.id == 1 ? (
            <Input
              key={index}
              opcao={opcao}
              statusFiltroAtual={props.statusFiltroAtual}
              setStatusFiltroAtual={props.setStatusFiltroAtual}
            />
          ) : (
            <Box key={index} className="flex flex-col w-full items-center my-3">
              <Typography
                sx={{
                  textAlign: "center",
                  fontSize: FontConfig.big,
                  fontWeight: 600,
                  color: "text.secondary",
                }}
              >
                {texts.sideBarFiltro.semFiltro}
              </Typography>
            </Box>
          )
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
        {opcoesOrdenar.map((opcao, index) =>
          <SideBarOrdenacao
            key={index}
            opcao={opcao}
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

/** Input para o filtro de "status" na SideBarFiltroHome */
const Input = (props) => {

  /** Context para alterar a linguagem do sistema */
  const { texts } = useContext(TextLanguageContext);

  /** Função para atualizar os filtros quando um status for selecionado */
  const selecionarStatus = (event) => {
    props.setStatusFiltroAtual(event.target?.value || "");
  };

  return (
    <Box className="flex flex-col items-center w-full my-5">
      <FormControl sx={{ width: "85%" }} size="small">
        <InputLabel id="demo-select-small-label" className="flex items-center">
          <BookmarkBorderOutlinedIcon className="mr-2" />
          {props.opcao.tipo}
        </InputLabel>
        <Select
          labelId="demo-simple-select-label"
          id="demo-simple-select"
          value={props.statusFiltroAtual}
          label={"Icon" + texts.sideBarFiltro.status}
          onChange={selecionarStatus}
        >
          <MenuItem selected value={""}>
            {texts.sideBarFiltro.semFiltro}
          </MenuItem>
          <MenuItem value={"ASSESSMENT"}>
            {texts.sideBarFiltro.aprovada}
          </MenuItem>
          <MenuItem value={"CANCELLED"}>
            {texts.sideBarFiltro.reprovada}
          </MenuItem>
          <MenuItem value={"BACKLOG_EDICAO"}>
            {texts.sideBarFiltro.aguardandoEdicao}
          </MenuItem>
          <MenuItem value={"BACKLOG_REVISAO"}>
            {texts.sideBarFiltro.aguardandoRevisao}
          </MenuItem>
          <MenuItem value={"BACKLOG_APROVACAO"}>
            {texts.sideBarFiltro.emAprovacao}
          </MenuItem>
          <MenuItem value={"ASSESSMENT_APROVACAO"}>
            {texts.sideBarFiltro.emAndamento}
          </MenuItem>
          <MenuItem value={"DONE"}>
            {texts.sideBarFiltro.emDesenvolvimento}
          </MenuItem>
        </Select>
      </FormControl>
    </Box>
  );
}

export default SliderBar;