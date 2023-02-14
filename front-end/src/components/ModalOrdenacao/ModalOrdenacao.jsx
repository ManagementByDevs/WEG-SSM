import React, { useContext } from "react";
import { Modal, Typography, Box, Checkbox, FormGroup, FormControlLabel, Grid, Fade } from "@mui/material";
import CloseIcon from "@mui/icons-material/Close";

import FontContext from "../../service/FontContext";

const ModalOrdenacao = (props) => {
  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  /** Função para mudar o valor do checkbox de ordenação por score "Menor Score" */
  function mudarCheck1() {
    props.setOrdenacaoScore([!props.ordenacaoScore[0], false]);
  }

  /** Função para mudar o valor do checkbox de ordenação por score "Maior Score" */
  function mudarCheck2() {
    props.setOrdenacaoScore([false, !props.ordenacaoScore[1]]);
  }

  /** Função para mudar o valor do checkbox de ordenação por título "Z-A" */
  function mudarCheck3() {
    props.setOrdenacaoTitulo([!props.ordenacaoTitulo[0], false]);
  }

  /** Função para mudar o valor do checkbox de ordenação por título "A-Z" */
  function mudarCheck4() {
    props.setOrdenacaoTitulo([false, !props.ordenacaoTitulo[1]]);
  }

  /** Função para mudar o valor do checkbox de ordenação por data "Mais Velha" */
  function mudarCheck5() {
    props.setOrdenacaoDate([!props.setOrdenacaoDate[0], false]);
  }

  /** Função para mudar o valor do checkbox de ordenação por data "Mais Nova" */
  function mudarCheck6() {
    props.setOrdenacaoDate([false, !props.setOrdenacaoDate[1]]);
  }

  return (
    <Modal open={true} onClose={props.fecharModal}>
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
            sx={{ position: "absolute", left: "90%", top: "3%", cursor: "pointer", }}
          />
          {/* Checkboxes de Score */}
          <Grid container spacing={0}>
            <Grid item xs={20}>
              <FormGroup className="flex w-full h-full justify-evenly items-start flex-col">
                <Typography
                  sx={{ color: "secundary.main", fontSize: FontConfig.big, fontWeight: "600", }}
                >
                  Score:
                </Typography>
                <div className="w-full flex justify-between items-center">
                  <FormControlLabel
                    checked={props.ordenacaoScore[1]}
                    onChange={mudarCheck2}
                    control={<Checkbox />}
                    label="Maior Score"
                  />
                  <FormControlLabel
                    checked={props.ordenacaoScore[0]}
                    onChange={mudarCheck1}
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
                  sx={{ color: "secundary.main", fontSize: FontConfig.big, fontWeight: "600", }}
                >
                  Título:
                </Typography>
                <div className="w-full flex justify-between items-center">
                  <FormControlLabel
                    checked={props.ordenacaoTitulo[1]}
                    onChange={mudarCheck4}
                    control={<Checkbox />}
                    label="A-Z"
                  />
                  <FormControlLabel
                    checked={props.ordenacaoTitulo[0]}
                    onChange={mudarCheck3}
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
                  sx={{ color: "secundary.main", fontSize: FontConfig.big, fontWeight: "600", }}
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
