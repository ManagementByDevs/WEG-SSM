import React, { useContext, useEffect } from "react";

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

import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";

// Modal de ordenação do sistema
const ModalOrdenacao = (props) => {
  // Context para alterar a linguagem do sistema
  const { texts, setTexts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  /** Função para mudar o valor do checkbox de ordenação por score "Menor Score" */
  const mudarCheck1 = () => {
    props.setOrdenacaoScore([!props.ordenacaoScore[0], false]);
  };

  /** Função para mudar o valor do checkbox de ordenação por score "Maior Score" */
  const mudarCheck2 = () => {
    props.setOrdenacaoScore([false, !props.ordenacaoScore[1]]);
  };

  /** Função para mudar o valor do checkbox de ordenação por título "Z-A" */
  const mudarCheck3 = () => {
    props.setOrdenacaoTitulo([!props.ordenacaoTitulo[0], false]);
  };

  /** Função para mudar o valor do checkbox de ordenação por título "A-Z" */
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
    } else if (!props.lendo) {
      if ("speechSynthesis" in window) {
        const synthesis = window.speechSynthesis;
        synthesis.cancel();
      }
    }
  }, [props.texto, props.lendo]);

  return (
    <Modal
      open={true}
      onClose={props.fecharModal}
      BackdropProps={{ invisible: true }}
    >
      <Fade in={true}>
        <Box
          className="absolute flex flex-col items-center justify-evenly"
          sx={{
            top: "34%",
            left: "34%",
            transform: "translate(-50%, -50%)",
            width: 310,
            bgcolor: "background.paper",
            borderRadius: "5px",
            borderTop: "10px solid #00579D",
            boxShadow: 24,
            p: 1.5,
          }}
        >
          <CloseIcon
            onClick={props.fecharModal}
            sx={{
              position: "absolute",
              left: "90%",
              top: "3%",
              cursor: "pointer",
            }}
          />
          {/* Checkboxes de Score */}
          <Grid container spacing={0}>
            <Grid item xs={20}>
              <FormGroup className="flex w-full h-full justify-evenly items-start flex-col">
                <Typography
                  sx={{
                    color: "secundary.main",
                    fontSize: FontConfig.big,
                    fontWeight: "600",
                  }}
                  onClick={() => lerTexto(texts.modalOrdenacao.score)}
                >
                  {texts.modalOrdenacao.score}:
                </Typography>
                <div className="w-full flex justify-between items-center">
                  <FormControlLabel
                    checked={props.ordenacaoScore[1]}
                    onChange={mudarCheck2}
                    control={<Checkbox />}
                    label={texts.modalOrdenacao.maiorScore}
                  />
                  <FormControlLabel
                    checked={props.ordenacaoScore[0]}
                    onChange={mudarCheck1}
                    control={<Checkbox />}
                    label={texts.modalOrdenacao.menorScore}
                  />
                </div>
              </FormGroup>
            </Grid>
          </Grid>
          {/* Checkboxes de título */}
          <Grid container spacing={0}>
            <Grid item xs={9.2}>
              <FormGroup className="flex w-full h-full justify-evenly items-start flex-col">
                <Typography
                  sx={{
                    color: "secundary.main",
                    fontSize: FontConfig.big,
                    fontWeight: "600",
                  }}
                  onClick={() => lerTexto(texts.modalOrdenacao.titulo)}
                >
                  {texts.modalOrdenacao.titulo}:
                </Typography>
                <div className="w-full flex justify-between items-center">
                  <FormControlLabel
                    checked={props.ordenacaoTitulo[1]}
                    onChange={mudarCheck4}
                    control={<Checkbox />}
                    label={texts.modalOrdenacao.az}
                  />
                  <FormControlLabel
                    checked={props.ordenacaoTitulo[0]}
                    onChange={mudarCheck3}
                    control={<Checkbox />}
                    label={texts.modalOrdenacao.za}
                  />
                </div>
              </FormGroup>
            </Grid>
          </Grid>
          {/* Checkboxes de Data */}
          <Grid container spacing={0}>
            <Grid item xs={"auto"}>
              <FormGroup className="flex w-full h-full justify-evenly items-start flex-col">
                <Typography
                  sx={{
                    color: "secundary.main",
                    fontSize: FontConfig.big,
                    fontWeight: "600",
                  }}
                  onClick={() => lerTexto(texts.modalOrdenacao.data)}
                >
                  {texts.modalOrdenacao.data}:
                </Typography>
                <div className="w-full flex justify-between items-center">
                  <FormControlLabel
                    checked={props.ordenacaoDate[1]}
                    onChange={mudarCheck6}
                    control={<Checkbox />}
                    label={texts.modalOrdenacao.maisRecente}
                  />
                  <FormControlLabel
                    checked={props.ordenacaoDate[0]}
                    onChange={mudarCheck5}
                    control={<Checkbox />}
                    label={texts.modalOrdenacao.maisAntiga}
                  />
                </div>
              </FormGroup>
            </Grid>
          </Grid>
        </Box>
      </Fade>
    </Modal>
  );
};

export default ModalOrdenacao;
