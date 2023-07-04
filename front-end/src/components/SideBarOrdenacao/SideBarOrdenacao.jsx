import React, { useContext, useState } from "react";
import {
  List,
  Divider,
  ListItemIcon,
  ListItemText,
  ListItemButton,
  Collapse,
  Checkbox,
  FormControlLabel,
  Box,
} from "@mui/material";

import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";
import SpeechSynthesisContext from "../../service/SpeechSynthesisContext";

import ExpandLess from "@mui/icons-material/ExpandLess";
import ExpandMore from "@mui/icons-material/ExpandMore";

import AbcOutlinedIcon from "@mui/icons-material/AbcOutlined";
import PinOutlinedIcon from "@mui/icons-material/PinOutlined";
import OutlinedFlagIcon from "@mui/icons-material/OutlinedFlag";
import CalendarMonthOutlinedIcon from "@mui/icons-material/CalendarMonthOutlined";
import TodayOutlinedIcon from "@mui/icons-material/TodayOutlined";

import UsuarioService from "../../service/usuarioService";

export default function ItemOrdenacao(props) {
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
                  label={texts.sideBarFiltro?.az}
                  onClick={() => {
                    if (lendoTexto) {
                      lerTexto(texts.sideBarFiltro?.az);
                    }
                  }}
                />
              ) : props.opcao.id == 2 ? (
                <FormControlLabel
                  checked={props.ordenacaoNum[1]}
                  onChange={mudarCheck8}
                  control={<Checkbox />}
                  label={texts.sideBarFiltro?.crescente}
                  onClick={() => {
                    if (lendoTexto) {
                      lerTexto(texts.sideBarFiltro?.crescente);
                    }
                  }}
                />
              ) : props.opcao.id == 3 ? (
                <FormControlLabel
                  checked={props.ordenacaoScore[1]}
                  onChange={mudarCheck2}
                  control={<Checkbox />}
                  label={texts.sideBarFiltro?.maiorScore}
                  onClick={() => {
                    if (lendoTexto) {
                      lerTexto(texts.sideBarFiltro?.maiorScore);
                    }
                  }}
                />
              ) : props.opcao.id == 4 ? (
                <FormControlLabel
                  checked={props.ordenacaoDate[1]}
                  onChange={mudarCheck6}
                  control={<Checkbox />}
                  label={texts.sideBarFiltro?.maisRecente}
                  onClick={() => {
                    if (lendoTexto) {
                      lerTexto(texts.sideBarFiltro?.maisRecente);
                    }
                  }}
                />
              ) : props.opcao.id == 5 ? (
                <FormControlLabel
                  checked={props.ordenacaoDate[1]}
                  onChange={mudarCheck6}
                  control={<Checkbox />}
                  label={texts.sideBarFiltro?.maisRecente}
                  onClick={() => {
                    if (lendoTexto) {
                      lerTexto(texts.sideBarFiltro?.maisRecente);
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
                  label={texts.sideBarFiltro?.za}
                  onClick={() => {
                    if (lendoTexto) {
                      lerTexto(texts.sideBarFiltro?.za);
                    }
                  }}
                />
              ) : props.opcao.id == 2 ? (
                <FormControlLabel
                  checked={props.ordenacaoNum[0]}
                  onChange={mudarCheck7}
                  control={<Checkbox />}
                  label={texts.sideBarFiltro?.decrescente}
                  onClick={() => {
                    if (lendoTexto) {
                      lerTexto(texts.sideBarFiltro?.decrescente);
                    }
                  }}
                />
              ) : props.opcao.id == 3 ? (
                <FormControlLabel
                  checked={props.ordenacaoScore[0]}
                  onChange={mudarCheck1}
                  control={<Checkbox />}
                  label={texts.sideBarFiltro?.menorScore}
                  onClick={() => {
                    if (lendoTexto) {
                      lerTexto(texts.sideBarFiltro?.menorScore);
                    }
                  }}
                />
              ) : props.opcao.id == 4 ? (
                <FormControlLabel
                  checked={props.ordenacaoDate[0]}
                  onChange={mudarCheck5}
                  control={<Checkbox />}
                  label={texts.sideBarFiltro?.maisAntiga}
                  onClick={() => {
                    if (lendoTexto) {
                      lerTexto(texts.sideBarFiltro?.maisAntiga);
                    }
                  }}
                />
              ) : props.opcao.id == 5 ? (
                <FormControlLabel
                  checked={props.ordenacaoDate[0]}
                  onChange={mudarCheck5}
                  control={<Checkbox />}
                  label={texts.sideBarFiltro?.maisAntiga}
                  onClick={() => {
                    if (lendoTexto) {
                      lerTexto(texts.sideBarFiltro?.maisAntiga);
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