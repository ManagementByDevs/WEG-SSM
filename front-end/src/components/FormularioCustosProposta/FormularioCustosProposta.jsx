import React, { useState } from "react";
import { Box, Button } from "@mui/material";

import Custos from "../Custos/Custos";

import FontConfig from "../../service/FontConfig";

import AddCircleOutlineOutlinedIcon from "@mui/icons-material/AddCircleOutlineOutlined";

const FormularioCustosProposta = (props) => {

  const deletarCustos = (index) => {
    let aux = props.custos.map((custo) => {
      return {
        tipoDespesa: custo.tipoDespesa,
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
    props.setCustos(aux);
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
          onClick={() => {
            props.setCustos([
              ...props.custos,
              {
                tipoDespesa: "",
                perfilDespesa: "",
                periodoExecucao: "",
                horas: "",
                valorHora: "",
                total: "",
                ccs: "",
                visible: true,
              },
            ]);
          }}
        >
          Adicionar Custos
          <AddCircleOutlineOutlinedIcon className="ml-2" />
        </Button>
      </Box>
      <Box>
        {props.custos?.map((custo, index) => {
          return (
            custo.visible && (
              <Custos
                key={index}
                index={index}
                dados={custo}
                deletarCustos={deletarCustos}
                setDespesas={props.setDespesas}
                deletarLinhaCustos={props.deletarLinhaCustos}
                setCustos={props.setCustos}
                custos={props.custos}
              />
            )
          );
        })}
      </Box>
    </Box>
  );
};

export default FormularioCustosProposta;
