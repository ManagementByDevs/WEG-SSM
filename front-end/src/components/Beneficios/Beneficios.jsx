import React, { useState, useEffect, useContext } from "react";

import { Box, Select, FormControl, InputLabel, MenuItem, Typography } from "@mui/material";
import DeleteOutlineOutlinedIcon from "@mui/icons-material/DeleteOutlineOutlined";

import InputComLabel from "../InputComLabel/InputComLabel";

import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";

import "./Beneficios.css";

/** Componente de benefício editável utilizado na segunda etapa da criação da demanda */
const Beneficios = (props) => {

  // Contexto para trocar a linguagem
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  /** Objeto que armazenas os dados do beneficio (recebido pelo props.dados) */
  const [dadosBeneficio, setDadosBeneficio] = useState({
    id: props.dados.id,
    tipoBeneficio: props.dados.tipoBeneficio,
    valor_mensal: props.dados.valor_mensal,
    moeda: props.dados.moeda,
    memoriaCalculo: props.dados.memoriaCalculo,
    visible: true,
  });

  // UseEffect utilizado para salvar os dados do benefício a cada alteração feita, utilizando o props.save()
  useEffect(() => {
    props.save(dadosBeneficio);
  }, [dadosBeneficio]);

  /** Função para salvar o tipo do benefício */
  const salvarTipoBeneficio = (event) => {
    setDadosBeneficio({ ...dadosBeneficio, tipoBeneficio: event.target.value });
  };

  /** Função para salvar a moeda do benefício */
  const salvarMoeda = (event) => {
    setDadosBeneficio({ ...dadosBeneficio, moeda: event.target.value });
  };

  /** Função para salvar o valor mensal do benefício */
  function salvarValorMensal(texto) {
    setDadosBeneficio({ ...dadosBeneficio, valor_mensal: texto });
  }

  /** Função para salvar a memória de cálculo do benefício */
  function salvarMemoriaCalculo(texto) {
    setDadosBeneficio({ ...dadosBeneficio, memoriaCalculo: texto });
  }

  /** Função para remover algum benefício, utilizando o props.removerBeneficio() para tirar a sua visibilidade */
  const removerComponente = () => {
    props.removerBeneficio(props.index);
  }

  return (
    <Box
      className="flex rounded max-h-52 border-2 drop-shadow-md"
      sx={{ backgroundColor: "background.default", padding: "1%", position: "relative", minWidth: "725px", }}
    >
      <Box className="flex flex-col" sx={{ width: "30%", marginTop: "1%" }}>
        <Box className="" sx={{ width: "100%", margin: "1%" }}>
          <FormControl sx={{ width: "68%", height: "35%" }}>
            <InputLabel
              id="demo-simple-select-label"
              sx={{ margin: "-2px 0 0 0" }}
            >
              <Typography fontSize={FontConfig.medium}>
                {texts.beneficios.beneficios}
              </Typography>
            </InputLabel>

            {/* Select do tipo do benefício */}
            <Select
              labelId="demo-simple-select-label"
              id="demo-simple-select"
              value={dadosBeneficio.tipoBeneficio}
              onChange={salvarTipoBeneficio}
              label="Beneficio"
              defaultValue={dadosBeneficio.tipoBeneficio}
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
        {dadosBeneficio.tipoBeneficio === "Real" ||
          dadosBeneficio.tipoBeneficio === "Potencial" ? (
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
                    placeholder="Ex: 1000,00"
                    fontConfig={FontConfig.default}
                    texto={dadosBeneficio.valor_mensal}
                  />
                </Box>
                <FormControl
                  variant="filled"
                  sx={{ margin: "0 0 0 2rem", minWidth: "90px", width: "10%" }}
                >
                  <InputLabel id="demo-simple-select-label" sx={{ margin: "-10px 0 0 -5px" }}>
                    <Typography fontSize={FontConfig.default}>{texts.beneficios.moeda}</Typography>
                  </InputLabel>

                  {/* Select da moeda do benefício */}
                  <Select
                    labelId="demo-simple-select-filled-label"
                    id="demo-simple-select-filled"
                    value={dadosBeneficio.moeda}
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
      {dadosBeneficio.tipoBeneficio === "Real" ||
        dadosBeneficio.tipoBeneficio === "Potencial" ||
        dadosBeneficio.tipoBeneficio === "Qualitativo" ? (
        <Box className="flex items-end" sx={{ width: "65%" }}>

          {/* Input de memória de cálculo */}
          <InputComLabel
            saveInputValue={salvarMemoriaCalculo}
            component="textarea"
            label={texts.beneficios.memoriaCalculo}
            placeholder={texts.beneficios.digiteMemoriaCalculo}
            fontConfig={FontConfig.default}
            rows="4"
            sx={{ width: "100%" }}
            texto={dadosBeneficio.memoriaCalculo}
          />
        </Box>
      ) : null}
      <Box className="flex flex-col justify-end items-end" sx={{ position: "absolute", bottom: "5px", right: "5px" }}>
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