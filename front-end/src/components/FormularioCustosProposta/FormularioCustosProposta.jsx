import React, { useState, useContext } from "react";
import { Box, Button, Typography } from "@mui/material";

import Custos from "../Custos/Custos";

import FontConfig from "../../service/FontConfig";

import AddCircleOutlineOutlinedIcon from "@mui/icons-material/AddCircleOutlineOutlined";
import { useEffect } from "react";

import FontContext from "../../service/FontContext";

const FormularioCustosProposta = (props) => {
  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);
  
  const deletarCustos = (index) => {
    let aux = [...props.custos];
    aux[index].visible = false;
    props.setCustos(aux);
  };


  const [horasTotais, setHorasTotais] = useState(0);
  const [valorTotal, setValorTotal] = useState(0);

  useEffect(() => {
    let aux = 0;
    for (let i = 0; i < props.custos.length; i++) {
      for (let j = 0; j < props.custos[i].despesas.length; j++) {
        aux += props.custos[i].despesas[j].horas * 1;
      }
    }
    setHorasTotais(aux);
  }, [props.custos]);

  useEffect(() => {
    let aux = 0;
    for (let i = 0; i < props.custos.length; i++) {
      for (let j = 0; j < props.custos[i].despesas.length; j++) {
        aux += props.custos[i].despesas[j].total * 1;
      }
    }
    setValorTotal(aux.toFixed(2));
  }, [props.custos]);


  return (
    <Box className="flex flex-col">
      <Box className="flex w-full justify-between mt-10 items-end">
        <Box className="flex ml-10">
          <Typography fontSize={FontConfig.medium} sx={{ marginRight: "8px" }}>
            Total:
          </Typography>
          <Typography fontSize={FontConfig.medium} sx={{ marginRight: "15px" }}>
            {horasTotais}h
          </Typography>
          <Typography fontSize={FontConfig.medium} sx={{ marginRight: "15px" }}>
            -
          </Typography>
          <Typography fontSize={FontConfig.medium} sx={{ marginRight: "8px" }}>
            R${valorTotal}
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
