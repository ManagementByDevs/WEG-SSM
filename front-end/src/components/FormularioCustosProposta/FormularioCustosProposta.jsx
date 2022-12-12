import React, { useState } from "react";
import { Box, Button } from "@mui/material";

import Custos from "../Custos/Custos";

import FontConfig from "../../service/FontConfig";

import AddCircleOutlineOutlinedIcon from "@mui/icons-material/AddCircleOutlineOutlined";

const FormularioCustosProposta = () => {
  const [custos, setCustos] = useState([
    {
      tipoDispesa: "",
      perfilDespesa: "",
      periodoExecucao: "",
      horas: "",
      valorHora: "",
      total: "",
      ccs: "",
      visible: true,
    },
  ]);

  const deletarCustos = (index) => {
    let aux = custos.map((custo) => {
      return {
        tipoDispesa: custo.tipoDispesa,
        perfilDespesa: custo.perfilDespesa,
        periodoExecucao: custo.periodoExecucao,
        horas: custo.horas,
        valorHora: custo.valorHora,
        total: custo.total,
        ccs: custo.ccs,
        visible: custo.visible,
      };
    });
    aux[index].visible = false;
    setCustos(aux);
  };

  return (
    <Box className="flex flex-col">
      <Box className="flex w-full justify-end mt-10">
        <Button
          sx={{
            backgroundColor: "primary.main",
            color: "text.white",
            fontSize: FontConfig.default,
          }}
          variant="contained"
          disableElevation
          onClick={() => setCustos([...custos, {}])}
        >
          Adicionar Custos
          <AddCircleOutlineOutlinedIcon className="ml-2" />
        </Button>
      </Box>
      <Box>
        {custos?.map((custo, index) => (
          <Custos key={index} dados={custo} deletarCustos={deletarCustos} />
        ))}
      </Box>
    </Box>
  );
};

export default FormularioCustosProposta;
