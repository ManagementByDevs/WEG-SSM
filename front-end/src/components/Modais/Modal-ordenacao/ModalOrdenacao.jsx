import React, { useContext } from "react";
import {
  List,
  Box,
  Typography,
  Divider,
  InputLabel,
  MenuItem,
  FormControl,
  Select,
  Chip,
} from "@mui/material";

import TextLanguageContext from "../../../service/TextLanguageContext";
import FontContext from "../../../service/FontContext";
import SpeechSynthesisContext from "../../../service/SpeechSynthesisContext";

import ItemOrdenacao from "./ItemOrdenacao/itemOrdenacao";

export default function TemporaryDrawer(props) {
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
      id: 2,
      tipo: "Gerente Responsável",
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
      id: 6,
      tipo: "Analista Responsável",
    },
    {
      id: 7,
      tipo: "Solicitante",
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
      <Box
        className="w-full flex items-center justify-center"
        sx={{ backgroundColor: "primary.main", height: "4.5rem" }}
      >
        <Typography
          fontSize={FontConfig.smallTitle}
          sx={{ color: "text.white", fontWeight: 600 }}
          onClick={() => {
            lerTexto(texts.modalOrdenacao.ordenar);
          }}
        >
          {texts.modalOrdenacao.filtrar}
        </Typography>
      </Box>
      {props.valorAba != 5 && (
        <Box className="flex flex-col w-full items-center mt-5">
          {opcoesFiltrar.map((opcao, index) =>
            props.valorAba == 1 && opcao.id <= 6 ? (
              <Input age={age} handleChange={handleChange} opcao={opcao} />
            ) : (props.valorAba == 2 || props.valorAba == 3) &&
              opcao.id > 1 &&
              opcao.id <= 6 ? (
              <Input age={age} handleChange={handleChange} opcao={opcao} />
            ) : props.valorAba == 4 && opcao.id <= 8 ? (
              <Input age={age} handleChange={handleChange} opcao={opcao} />
            ) : props.valorAba == 6 && (opcao.id == 9 || opcao.id == 10) ? (
              <Input age={age} handleChange={handleChange} opcao={opcao} />
            ) : null
          )}
        </Box>
      )}
      <Box className="my-1 p-2">
        <Divider />
        <Typography
          sx={{
            marginY: "0.3rem",
            textAlign: "center",
            fontSize: FontConfig.big,
            fontWeight: 600,
            color: "primary.main",
          }}
        >
          {texts.modalOrdenacao.ordenar}
        </Typography>
        <Divider />
      </Box>
      <Box>
        {opcoesOrdenar.map((opcao, index) => (
          <>
            {props.valorAba != 5 && props.valorAba != 6 && opcao.id != 4 ? (
              <ItemOrdenacao
                opcao={opcao}
                key={index}
                ordenacaoTitulo={props.ordenacaoTitulo}
                setOrdenacaoTitulo={props.setOrdenacaoTitulo}
                ordenacaoScore={props.ordenacaoScore}
                setOrdenacaoScore={props.setOrdenacaoScore}
                ordenacaoDate={props.ordenacaoDate}
                setOrdenacaoDate={props.setOrdenacaoDate}
                valorAba={props.valorAba}
              />
            ) : (
              (props.valorAba == 5 || props.valorAba == 6) &&
              opcao.id != 1 && (
                <ItemOrdenacao
                  opcao={opcao}
                  key={index}
                  ordenacaoTitulo={props.ordenacaoTitulo}
                  setOrdenacaoTitulo={props.setOrdenacaoTitulo}
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

const Input = (props) => (
  <FormControl sx={{ marginBottom: "1rem", width: "80%" }} size="small">
    <InputLabel id="demo-select-small-label">Age</InputLabel>
    <Select
      labelId="demo-select-small-label"
      id="demo-select-small"
      value={props.age}
      label="Age"
      onChange={props.handleChange}
    >
      <MenuItem value="">
        <em>None</em>
      </MenuItem>
      <MenuItem value={10}>Ten</MenuItem>
      <MenuItem value={20}>Twenty</MenuItem>
      <MenuItem value={30}>Thirty</MenuItem>
    </Select>
  </FormControl>
);
