import React, { useContext } from "react";

import { Box, Select, FormControl, InputLabel, MenuItem, Typography, } from "@mui/material";
import DeleteOutlineOutlinedIcon from "@mui/icons-material/DeleteOutlineOutlined";

import InputComLabel from "../InputComLabel/InputComLabel";
import CaixaTextoQuill from "../CaixaTextoQuill/CaixaTextoQuill";

import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";

import "./Beneficios.css";

/** Componente de benefício editável utilizado na segunda etapa da criação da demanda */
const Beneficios = (props) => {

  /** Contexto para trocar a linguagem */
  const { texts } = useContext(TextLanguageContext);

  /** Context para alterar o tamanho da fonte */
  const { FontConfig, setFontConfig } = useContext(FontContext);

  /** Função para salvar o tipo do benefício */
  const salvarTipoBeneficio = (event) => {
    props.save({ ...props.dados, tipoBeneficio: event.target.value });
  };

  /** Função para salvar a moeda do benefício */
  const salvarMoeda = (event) => {
    props.save({ ...props.dados, moeda: event.target.value });
  };

  /** Função para salvar a memória de cálculo do benefício */
  function salvarMemoriaCalculo(texto) {
    props.save({ ...props.dados, memoriaCalculo: texto });
  }

  /** Função para remover algum benefício, utilizando o props.removerBeneficio() para tirar a sua visibilidade */
  const removerComponente = () => {
    props.removerBeneficio(props.index);
  };

  /** Função para salvar o valor mensal do benefício */
  function salvarValorMensal(texto) {
    let regexp = new RegExp(/^[0-9]*\.?[0-9]*$/);

    if (regexp.test(texto)) {
      props.save({ ...props.dados, valor_mensal: texto });
    }
  }

  /** Função que irá setar o texto que será "lido" pela a API */
  const lerTexto = (escrita) => {
    if (props.lendo) {
      const synthesis = window.speechSynthesis;
      const utterance = new SpeechSynthesisUtterance(escrita);

      const finalizarLeitura = () => {
        if ("speechSynthesis" in window) {
          synthesis.cancel();
        }
      };

      if (props.lendo && escrita !== "") {
        if ("speechSynthesis" in window) {
          synthesis.speak(utterance);
        }
      } else {
        finalizarLeitura();
      }

      return () => {
        finalizarLeitura();
      };
    }
  };

  return (
    <Box
      className="flex rounded max-h-52 border-2 drop-shadow-md"
      sx={{
        backgroundColor: "background.default",
        padding: "1%",
        position: "relative",
        minWidth: "44rem",
      }}
    >
      <Box className="flex flex-col" sx={{ width: "30%", marginTop: "1%" }}>
        <Box className="" sx={{ width: "100%", margin: "1%" }}>
          <FormControl sx={{ width: "70%", height: "35%", minWidth: "12rem" }}>
            <InputLabel
              id="demo-simple-select-label"
              sx={{ margin: "-2px 0 0 0" }}
            >
              <Typography
                fontSize={FontConfig.medium}
                onClick={() => {
                  lerTexto(texts.beneficios.beneficios);
                }}
              >
                {texts.beneficios.beneficios}
              </Typography>
            </InputLabel>

            {/* Select do tipo do benefício */}
            <Select
              labelId="demo-simple-select-label"
              id="demo-simple-select"
              value={props.dados?.tipoBeneficio}
              onChange={salvarTipoBeneficio}
              label="Beneficio"
              defaultValue={props.dados?.tipoBeneficio}
            >
              <MenuItem value={"Real"}>
                <Typography fontSize={FontConfig.medium}>
                  {texts.beneficios.real}
                </Typography>
              </MenuItem>
              <MenuItem value={"Potencial"}>
                <Typography fontSize={FontConfig.medium}>
                  {texts.beneficios.potencial}
                </Typography>
              </MenuItem>
              <MenuItem value={"Qualitativo"}>
                <Typography fontSize={FontConfig.medium}>
                  {texts.beneficios.qualitativo}
                </Typography>
              </MenuItem>
            </Select>
          </FormControl>
        </Box>

        {/* Verificação para mostar o input de valor mensal */}
        {props.dados?.tipoBeneficio === "Real" ||
          props.dados?.tipoBeneficio === "Potencial" ? (
          <Box className="flex items-end" sx={{ minWidth: "275px" }}>
            <Box
              className="flex items-end"
              sx={{ width: "92%", margin: "3% 1% 1% 1%" }}
            >
              <Box className="flex items-end" sx={{ width: "100%" }}>
                <Box sx={{ width: "40%" }}>
                  {/* Input de valor mensal */}
                  <InputComLabel
                    saveInputValue={salvarValorMensal}
                    component="input"
                    label={texts.beneficios.valorMensal}
                    placeholder={texts.beneficios.exemploValorMensal}
                    fontConfig={FontConfig.default}
                    texto={props.dados?.valor_mensal}
                    setFeedbackErroNavegadorIncompativel={
                      props.setFeedbackErroNavegadorIncompativel
                    }
                    setFeedbackErroReconhecimentoVoz={
                      props.setFeedbackErroReconhecimentoVoz
                    }
                    lendo={props.lendo}
                  />
                </Box>
                <FormControl
                  variant="filled"
                  sx={{ margin: "0 0 0 2%", minWidth: "5rem", width: "10%" }}
                >
                  <InputLabel
                    id="demo-simple-select-label"
                    sx={{ margin: "-10px 0 0 -5px" }}
                  >
                    <Typography
                      fontSize={FontConfig.default}
                      onClick={() => {
                        lerTexto(texts.beneficios.moeda);
                      }}
                    >
                      {texts.beneficios.moeda}
                    </Typography>
                  </InputLabel>

                  {/* Select da moeda do benefício */}
                  <Select
                    labelId="demo-simple-select-filled-label"
                    id="demo-simple-select-filled"
                    value={props.dados?.moeda}
                    onChange={salvarMoeda}
                  >
                    <MenuItem value={"Real"}>
                      <Typography fontSize={FontConfig.medium}>BRL</Typography>
                    </MenuItem>
                    <MenuItem value={"Dolar"}>
                      <Typography fontSize={FontConfig.medium}>USD</Typography>
                    </MenuItem>
                    <MenuItem value={"Euro"}>
                      <Typography fontSize={FontConfig.medium}>EUR</Typography>
                    </MenuItem>
                  </Select>
                </FormControl>
              </Box>
            </Box>
          </Box>
        ) : null}
      </Box>

      {/* Verificação para mostar o input de memória de cálculo */}
      {props.dados?.tipoBeneficio === "Real" ||
        props.dados?.tipoBeneficio === "Potencial" ||
        props.dados?.tipoBeneficio === "Qualitativo" ? (
        <Box className="flex items-center" sx={{ width: "65%" }}>
          {/* Input que permite estilização para a memória de cálculo do benefício */}
          <Box className="flex flex-col overflow-auto w-full h-full">
            <Box className="flex">
              <Typography
                fontSize={FontConfig.default}
                fontWeight={600}
                onClick={() => {
                  lerTexto(texts.beneficios.memoriaCalculo);
                }}
              >
                {texts.beneficios.memoriaCalculo}
              </Typography>

              <Typography
                fontSize={props.fontConfig}
                sx={{
                  fontWeight: "800",
                  cursor: "default",
                  margin: "0 .2% .2% .2%",
                }}
                className="text-red-600"
                gutterBottom
              >
                *
              </Typography>
            </Box>

            <Box sx={{ borderLeft: "solid 4px", borderColor: "primary.main" }}>
              {/* Caixa de edição para o campo memória de cálculo */}
              <CaixaTextoQuill
                placeholder={texts.beneficios.digiteMemoriaCalculo}
                texto={props.dados?.memoriaCalculo}
                onChange={(value) => {
                  salvarMemoriaCalculo(value);
                }}
              />
            </Box>
          </Box>
        </Box>
      ) : null}
      <Box
        className="flex flex-col justify-end items-end"
        sx={{ position: "absolute", bottom: "5px", right: "5px" }}
      >
        {/* Botão de excluir benefício */}
        <DeleteOutlineOutlinedIcon
          className="delay-120 hover:scale-110 duration-300"
          sx={{ color: "icon.main", cursor: "pointer", fontSize: "30px" }}
          onClick={removerComponente}
        />
      </Box>
    </Box>
  );
};

export default Beneficios;
