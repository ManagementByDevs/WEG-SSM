import { React, useContext, useEffect } from "react";

import {
  Modal,
  Typography,
  Box,
  Checkbox,
  FormGroup,
  FormControlLabel,
  Grid,
  Fade,
} from "@mui/material";

import CloseIcon from "@mui/icons-material/Close";

import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";

// Modal para do filtro do usuário padrão
const ModalFiltro = (props) => {
  // Context para alterar a linguagem do sistema
  const { texts, setTexts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  /** Função para mudar o valor da primeira checkbox do filtro */
  const mudarCheck1 = () => {
    props.setListaFiltros([
      !props.listaFiltros[0],
      false,
      false,
      false,
      false,
      false,
    ]);
  };

  /** Função para mudar o valor da segunda checkbox do filtro */
  const mudarCheck2 = () => {
    props.setListaFiltros([
      false,
      !props.listaFiltros[1],
      false,
      false,
      false,
      false,
    ]);
  };

  /** Função para mudar o valor da terceira checkbox do filtro */
  const mudarCheck3 = () => {
    props.setListaFiltros([
      false,
      false,
      !props.listaFiltros[2],
      false,
      false,
      false,
    ]);
  };

  /** Função para mudar o valor da quarta checkbox do filtro */
  const mudarCheck4 = () => {
    props.setListaFiltros([
      false,
      false,
      false,
      !props.listaFiltros[3],
      false,
      false,
    ]);
  };

  /** Função para mudar o valor da quinta checkbox do filtro */
  const mudarCheck5 = () => {
    props.setListaFiltros([
      false,
      false,
      false,
      false,
      !props.listaFiltros[4],
      false,
    ]);
  };

  /** Função para mudar o valor da sexta checkbox do filtro */
  const mudarCheck6 = () => {
    props.setListaFiltros([
      false,
      false,
      false,
      false,
      false,
      !props.listaFiltros[5],
    ]);
  };

  // Função que irá setar o texto que será "lido" pela a API
  const lerTexto = (texto) => {
    if (props.lendo) {
      props.setTexto(texto);
    }
  };

  // Função que irá "ouvir" o texto que será "lido" pela a API
  useEffect(() => {
    if (props.lendo && props.texto != "") {
      if ("speechSynthesis" in window) {
        const synthesis = window.speechSynthesis;
        const utterance = new SpeechSynthesisUtterance(props.texto);
        synthesis.speak(utterance);
      }
      props.setTexto("");
    }
  }, [props.texto]);

  return (
    <Modal
      open={true}
      onClose={props.fecharModal}
      BackdropProps={{ invisible: true }}
      sx={{
        display: "flex",
        alignItems: "center",
        justifyContent: "center",
        width: "10rem",
        position: "absolute",
        left: "35%",
        top: "2%",
      }}
    >
      <Fade in={true}>
        <Box
          className="absolute flex justify-evenly items-center flex-col"
          sx={{
            top: "36%",
            left: "37%",
            transform: "translate(-50%, -50%)",
            width: 310,
            height: 320,
            bgcolor: "background.paper",
            borderRadius: "5px",
            borderTop: "10px solid #00579D",
            boxShadow: 24,
            p: 2,
          }}
        >
          {/* Botão para fechar o modal */}
          <CloseIcon
            onClick={props.fecharModal}
            sx={{
              position: "absolute",
              left: "90%",
              top: "3%",
              cursor: "pointer",
            }}
          />
          <Grid container spacing={0}>
            <Grid item xs={9.2}>
              <FormGroup className="w-full h-full flex justify-evenly items-center">
                <Typography
                  sx={{
                    color: "secundary.main",
                    fontSize: FontConfig.big,
                    fontWeight: "600",
                  }}
                  onClick={() => {
                    lerTexto(texts.modalFiltro.status);
                  }}
                >
                  {texts.modalFiltro.status}:
                </Typography>

                {/* Checkboxes com os filtros */}
                <Box className="w-full flex justify-between flex-col m-0">
                  <FormControlLabel
                    checked={props.listaFiltros[0]}
                    onChange={mudarCheck1}
                    control={<Checkbox />}
                    label={texts.modalFiltro.labels.aprovada}
                  />
                  <FormControlLabel
                    checked={props.listaFiltros[1]}
                    onChange={mudarCheck2}
                    control={<Checkbox />}
                    label={texts.modalFiltro.labels.reprovada}
                  />
                  <FormControlLabel
                    checked={props.listaFiltros[2]}
                    onChange={mudarCheck3}
                    control={<Checkbox />}
                    label={texts.modalFiltro.labels.aguardandoEdicao}
                  />
                  <FormControlLabel
                    checked={props.listaFiltros[3]}
                    onChange={mudarCheck4}
                    control={<Checkbox />}
                    label={texts.modalFiltro.labels.aguardandoRevisao}
                  />
                  <FormControlLabel
                    checked={props.listaFiltros[4]}
                    onChange={mudarCheck5}
                    control={<Checkbox />}
                    label={texts.modalFiltro.labels.emAprovacao}
                  />
                  <FormControlLabel
                    checked={props.listaFiltros[5]}
                    onChange={mudarCheck6}
                    control={<Checkbox />}
                    label={texts.modalFiltro.labels.emAndamento}
                  />
                </Box>
              </FormGroup>
            </Grid>
          </Grid>
        </Box>
      </Fade>
    </Modal>
  );
};

export default ModalFiltro;
