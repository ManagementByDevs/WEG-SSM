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

import BookmarkBorderOutlinedIcon from '@mui/icons-material/BookmarkBorderOutlined';
import AssignmentIndOutlinedIcon from '@mui/icons-material/AssignmentIndOutlined';
import Diversity3OutlinedIcon from '@mui/icons-material/Diversity3Outlined';
import BusinessOutlinedIcon from '@mui/icons-material/BusinessOutlined';
import AspectRatioOutlinedIcon from '@mui/icons-material/AspectRatioOutlined';
import PersonOutlineOutlinedIcon from '@mui/icons-material/PersonOutlineOutlined';
import VisibilityOutlinedIcon from '@mui/icons-material/VisibilityOutlined';
import PublicOutlinedIcon from '@mui/icons-material/PublicOutlined';

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
      <Box className="p-2" sx={{backgroundColor: "primary.main"}}>
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
      <Box className="my-1 p-2" sx={{backgroundColor: "primary.main"}}>
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
  <FormControl sx={{ marginBottom: "1rem", width: "85%" }} size="small">
    <InputLabel id="demo-select-small-label" className="flex items-center">
      {
        props.opcao.id == 1 ? <BookmarkBorderOutlinedIcon className="mr-2"/> :
        props.opcao.id == 2 ? <AssignmentIndOutlinedIcon className="mr-2"/> :
        props.opcao.id == 3 ? <Diversity3OutlinedIcon className="mr-2"/> :
        props.opcao.id == 4 ? <BusinessOutlinedIcon className="mr-2"/> :
        props.opcao.id == 5 ? <AspectRatioOutlinedIcon className="mr-2"/> :
        props.opcao.id == 6 ? <PersonOutlineOutlinedIcon className="mr-2"/> :
        props.opcao.id == 7 ? <PersonOutlineOutlinedIcon className="mr-2"/> :
        props.opcao.id == 8 ? <PersonOutlineOutlinedIcon className="mr-2"/> :
        props.opcao.id == 9 ? <VisibilityOutlinedIcon className="mr-2"/> :
        props.opcao.id == 10 ? <PublicOutlinedIcon className="mr-2"/> :
        null
      }
      {props.opcao.tipo}
      </InputLabel>
    <Select
      labelId="demo-select-small-label"
      id="demo-select-small"
      value={props.age}
      label={"icon " + props.opcao.tipo}
      onChange={props.handleChange}
    >
      <MenuItem value="">
        <em>Nenhum</em>
      </MenuItem>
      {
        props.opcao.id == 1 ? (
          <>
          <MenuItem value={10}>Ten</MenuItem>
          <MenuItem value={10}>Ten</MenuItem>
          <MenuItem value={10}>Ten</MenuItem>
          </>
        ) :
        props.opcao.id == 2 ? <MenuItem value={20}>Twenty</MenuItem> :
        props.opcao.id == 3 ? <MenuItem value={30}>Thirty</MenuItem> :
        props.opcao.id == 4 ? <MenuItem value={30}>Thirty</MenuItem> :
        props.opcao.id == 5 ? <MenuItem value={30}>Thirty</MenuItem> :
        props.opcao.id == 6 ? <MenuItem value={30}>Thirty</MenuItem> :
        props.opcao.id == 7 ? <MenuItem value={30}>Thirty</MenuItem> :
        props.opcao.id == 8 ? <MenuItem value={30}>Thirty</MenuItem> :
        props.opcao.id == 9 ? <MenuItem value={30}>Thirty</MenuItem> :
        props.opcao.id == 10 ? <MenuItem value={30}>Thirty</MenuItem> :
        null
      }
    </Select>
  </FormControl>
);
