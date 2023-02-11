import React, { useContext } from "react";
import { Modal, Typography, Box, Checkbox, FormGroup, FormControlLabel, Grid } from "@mui/material";

import Fade from "@mui/material/Fade";
import CloseIcon from "@mui/icons-material/Close";

import FontContext from "../../service/FontContext";

const ModalOrdenacao = (props) => {
  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // Funções para controlar os checkboxes de ordenação
  function mudarCheck1() {
    if (props.ordenacaoTitulo[0]) {
      props.setOrdenacaoTitulo([false, false]);
    } else {
      props.setOrdenacaoTitulo([true, false]);
    }
  }

  function mudarCheck2() {
    if (props.ordenacaoTitulo[1]) {
      props.setOrdenacaoTitulo([false, false]);
    } else {
      props.setOrdenacaoTitulo([false, true]);
    }
  }

  function mudarCheck3() {
    if (props.ordenacaoScore[0]) {
      props.setOrdenacaoScore([false, false]);
    } else {
      props.setOrdenacaoScore([true, false]);
    }
  }

  function mudarCheck4() {
    if (props.ordenacaoScore[1]) {
      props.setOrdenacaoScore([false, false]);
    } else {
      props.setOrdenacaoScore([false, true]);
    }
  }

  function mudarCheck5() {
    if (props.ordenacaoDate[0]) {
      props.setOrdenacaoDate([false, false]);
    } else {
      props.setOrdenacaoDate([true, false]);
    }
  }

  function mudarCheck6() {
    if (props.ordenacaoDate[1]) {
      props.setOrdenacaoDate([false, false]);
    } else {
      props.setOrdenacaoDate([false, true]);
    }
  }

  return (
    <Modal
      open={true}
      onClose={props.fecharModal}
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
                >
                  Score:
                </Typography>
                <div className="w-full flex justify-between items-center">
                  <FormControlLabel
                    checked={props.ordenacaoScore[1]}
                    onChange={mudarCheck4}
                    control={<Checkbox />}
                    label="Maior Score"
                  />
                  <FormControlLabel
                    checked={props.ordenacaoScore[0]}
                    onChange={mudarCheck3}
                    control={<Checkbox />}
                    label="Menor Score"
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
                >
                  Título:
                </Typography>
                <div className="w-full flex justify-between items-center">
                  <FormControlLabel
                    checked={props.ordenacaoTitulo[1]}
                    onChange={mudarCheck2}
                    control={<Checkbox />}
                    label="A-Z"
                  />
                  <FormControlLabel
                    checked={props.ordenacaoTitulo[0]}
                    onChange={mudarCheck1}
                    control={<Checkbox />}
                    label="Z-A"
                  />
                </div>
              </FormGroup>
            </Grid>
          </Grid>
          {/* Checkboxes de Data */}
          <Grid container spacing={0}>
            <Grid item xs={11.4}>
              <FormGroup className="flex w-full h-full justify-evenly items-start flex-col">
                <Typography
                  sx={{
                    color: "secundary.main",
                    fontSize: FontConfig.big,
                    fontWeight: "600",
                  }}
                >
                  Data:
                </Typography>
                <div className="w-full flex justify-between items-center">
                  <FormControlLabel
                    checked={props.ordenacaoDate[1]}
                    onChange={mudarCheck6}
                    control={<Checkbox />}
                    label="Mais Nova"
                  />
                  <FormControlLabel
                    checked={props.ordenacaoDate[0]}
                    onChange={mudarCheck5}
                    control={<Checkbox />}
                    label="Mais Velha"
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
