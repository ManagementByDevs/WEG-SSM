import React, { useState, useEffect, useContext } from "react";
import { Box, Select, FormControl, InputLabel, MenuItem, Typography } from "@mui/material";

import "./Beneficios.css";

import InputComLabel from "../InputComLabel/InputComLabel";
import DeleteOutlineOutlinedIcon from "@mui/icons-material/DeleteOutlineOutlined";
import FontContext from "../../service/FontContext";

const Beneficios = (props) => {
  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // Objeto que armazenas os dados do beneficio
  const [dadosBeneficio, setDadosBeneficio] = useState({
    id: props.dados.id,
    tipoBeneficio: props.dados.tipoBeneficio,
    valor_mensal: props.dados.valor_mensal,
    moeda: props.dados.moeda,
    memoriaCalculo: props.dados.memoriaCalculo,
    visible: true,
  });

  // Função utilizada para setar o tipo do benefício
  const handleChange = (event) => {
    setDadosBeneficio({ ...dadosBeneficio, tipoBeneficio: event.target.value });
  };


  // Função utilizada para setar o valor mensal do benefício
  const handleChangeValor = (event) => {
    setDadosBeneficio({ ...dadosBeneficio, valor_mensal: event.target.value });
  };

  // Função utilizada para setar a moeda do benefício
  const handleChangeMoeda = (event) => {
    setDadosBeneficio({ ...dadosBeneficio, moeda: event.target.value });
  };


  // Função utilizada para setar a memória de cálculo do benefício
  const handleChangeMemoriaCalculo = (event) => {
    setDadosBeneficio({
      ...dadosBeneficio,
      memoriaCalculo: event.target.value,
    });
  };

  // UseEffect utilizado para salvar os dados do benefício a cada alteração
  useEffect(() => {
    props.save(dadosBeneficio);
  }, [dadosBeneficio]);

  // Função para salvar o valor mensal do benefício
  function salvarValorMensal(texto) {
    setDadosBeneficio({ ...dadosBeneficio, valor_mensal: texto });
  }

  // Função para salvar a memória de cálculo do benefício
  function salvarMemoriaCalculo(texto) {
    setDadosBeneficio({ ...dadosBeneficio, memoriaCalculo: texto });
  }

  // Função para remover algum benefício
  function removerComponente() {
    props.removerBeneficio(props.index);
  }


  // Função para selecionar algum tipo de benefício
  const getSelectedTipo = (tipo) => {
    return tipo === dadosBeneficio.tipoBeneficio;
  };

  return (
    <Box
      className="flex rounded max-h-52 border-2 drop-shadow-md"
      sx={{
        backgroundColor: "background.default",
        padding: "1%",
        position: "relative",
        minWidth: "725px",
      }}
    >
      <Box className="flex flex-col" sx={{ width: "30%", marginTop: "1%" }}>
        <Box className="" sx={{ width: "100%", margin: "1%" }}>
          <FormControl sx={{ width: "68%", height: "35%" }}>
            <InputLabel
              id="demo-simple-select-label"
              sx={{ margin: "-2px 0 0 0" }}
            >
              <Typography fontSize={FontConfig.medium}>Benefícios</Typography>
            </InputLabel>
            <Select
              labelId="demo-simple-select-label"
              id="demo-simple-select"
              value={dadosBeneficio.tipoBeneficio}
              onChange={handleChange}
              label="Beneficio"
              defaultValue={dadosBeneficio.tipoBeneficio}
            >
              <MenuItem value={"Real"}>
                <Typography fontSize={FontConfig.medium}> Real </Typography>
              </MenuItem>
              <MenuItem value={"Potencial"}>
                <Typography fontSize={FontConfig.medium}>Potencial</Typography>
              </MenuItem>
              <MenuItem value={"Qualitativo"}>
                <Typography fontSize={FontConfig.medium}>
                  Qualitativo
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
                  <InputComLabel
                    saveInputValue={salvarValorMensal}
                    component="input"
                    label="Valor Mensal:"
                    placeholder="Ex: 1000,00"
                    fontConfig={FontConfig.default}
                    texto={dadosBeneficio.valor_mensal}
                    onChange={handleChangeValor}
                  />
                </Box>
                <FormControl
                  variant="filled"
                  sx={{ margin: "0 0 0 2rem", minWidth: "90px", width: "10%" }}
                >
                  <InputLabel
                    id="demo-simple-select-label"
                    sx={{ margin: "-10px 0 0 -5px" }}
                  >
                    <Typography fontSize={FontConfig.default}>Moeda</Typography>
                  </InputLabel>
                  <Select
                    labelId="demo-simple-select-filled-label"
                    id="demo-simple-select-filled"
                    value={dadosBeneficio.moeda}
                    onChange={handleChangeMoeda}
                  >
                    <MenuItem value={"Real"}>
                      <Typography fontSize={FontConfig.medium}>BRL</Typography>
                    </MenuItem>
                    <MenuItem value={"Dolar"}>
                      <Typography fontSize={FontConfig.medium}>URD</Typography>
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
          <InputComLabel
            saveInputValue={salvarMemoriaCalculo}
            component="textarea"
            label="Memória de cálculo:"
            placeholder="Digite a memória de cálculo..."
            fontConfig={FontConfig.default}
            rows="4"
            sx={{ width: "100%" }}
            texto={dadosBeneficio.memoriaCalculo}
            onChange={handleChangeMemoriaCalculo}
          />
        </Box>
      ) : null}
      <Box
        className="flex flex-col justify-end items-end"
        sx={{ position: "absolute", bottom: "5px", right: "5px" }}
      >
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
