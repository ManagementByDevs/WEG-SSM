import React, { useState } from "react";
import { Box, Button, Typography } from "@mui/material";

import Custos from "../Custos/Custos";

import FontConfig from "../../service/FontConfig";

import AddCircleOutlineOutlinedIcon from "@mui/icons-material/AddCircleOutlineOutlined";
import { useEffect } from "react";

const FormularioCustosProposta = (props) => {
  useEffect(() => {
    console.log("props.custos: ", props.custos);
  }, [props.custos]);

  const deletarCustos = (index) => {
    let aux = [...props.custos];
    aux[index].visible = false;
    props.setCustos(aux);
  };

  return (
    <Box className="flex flex-col">
      <Box className="flex w-full justify-between mt-10 items-end">
        <Box className="flex ml-10">
          <Typography fontSize={FontConfig.medium} sx={{ marginRight: "8px" }}>
            Total:
          </Typography>
          <Typography fontSize={FontConfig.medium} sx={{ marginRight: "15px" }}>
            00:00h
          </Typography>
          <Typography fontSize={FontConfig.medium} sx={{ marginRight: "15px" }}>
            -
          </Typography>
          <Typography fontSize={FontConfig.medium} sx={{ marginRight: "8px" }}>
            R$00,00
          </Typography>
        </Box>
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
                despesas: [
                  {
                    tipoDespesa: "",
                    perfilDespesa: "",
                    periodoExecucao: "",
                    horas: "",
                    valorHora: "",
                    total: "",
                    visible: true,
                  },
                ],
                ccs: [
                  {
                    codigo: "",
                    porcentagem: "",
                    visible: true,
                  },
                ],
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
                setCcs={props.setCcs}
                deletarLinhaCustos={props.deletarLinhaCustos}
                deletarLinhaCCs={props.deletarLinhaCCs}
                setCustos={props.setCustos}
                custos={props.custos}
              />
            )
          );
        })}
      </Box>
      <Box className="w-full" sx={{ height: "8rem" }} />
    </Box>
  );
};

export default FormularioCustosProposta;
