import React, { useState, useEffect, useContext } from "react";

import { Box, Button, Typography } from "@mui/material";
import AddCircleOutlineOutlinedIcon from "@mui/icons-material/AddCircleOutlineOutlined";

import Custos from "../Custos/Custos";

import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";
import CustosService from "../../service/custosService";

// Etapa de criação de proposta para adicionar as tabelas de custos
const FormularioCustosProposta = (props) => {

  // Contexto para trocar a linguagem
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  /** Função usada para excluir uma tabela de custos */
  const deletarTabelaCustos = (index) => {
    CustosService.deleteTabela(props.custos[index].id).then((response) => {
      let custosNovos = [...props.custos];
      custosNovos.splice(index, 1);
      props.setCustos(custosNovos);
    });
  }

  /** Função para criar uma tabela de custos no banco de dados e adicionar na lista */
  const criarTabelaCusto = () => {
    CustosService.postTabela({
      custos: [
        {
          tipoDespesa: "",
          perfilDespesa: "",
          periodoExecucao: "",
          horas: "",
          valorHora: ""
        },
      ],
      ccs: [
        {
          codigo: "",
          porcentagem: ""
        },
      ],
    }).then((response) => {
      props.setCustos([...props.custos, response]);
    });
  };

  // UseStates para armazenar as horas e o valor total
  const [horasTotais, setHorasTotais] = useState(0);
  const [valorTotal, setValorTotal] = useState(0);

  // UseEffect para calcular as horas totais
  useEffect(() => {
    let aux = 0;
    for (let i = 0; i < props.custos.length; i++) {
      for (let j = 0; j < props.custos[i].custos.length; j++) {
        aux += props.custos[i].custos[j].horas * 1;
      }
    }
    setHorasTotais(aux);
  }, [props.custos]);

  // UseEffect para calcular o valor total
  useEffect(() => {
    let aux = 0;
    for (let i = 0; i < props.custos.length; i++) {
      for (let j = 0; j < props.custos[i].custos.length; j++) {
        aux += props.custos[i].custos[j].total * 1;
      }
    }
    setValorTotal(aux.toFixed(2));
  }, [props.custos]);

  return (
    <Box className="flex flex-col">
      <Box className="flex w-full justify-between mt-10 items-end">
        <Box className="flex ml-10">
          <Typography fontSize={FontConfig.medium} sx={{ marginRight: "8px" }}>
            {texts.formularioCustosProposta.total}:
          </Typography>
          <Typography fontSize={FontConfig.medium} sx={{ marginRight: "15px" }}>
            {horasTotais}{texts.formularioCustosProposta.horas}
          </Typography>
          <Typography fontSize={FontConfig.medium} sx={{ marginRight: "15px" }}>
            -
          </Typography>
          <Typography fontSize={FontConfig.medium} sx={{ marginRight: "8px" }}>
            {texts.formularioCustosProposta.moeda}{valorTotal}
          </Typography>
        </Box>
        <Button
          sx={{ backgroundColor: "primary.main", color: "text.white", fontSize: FontConfig.default, }}
          variant="contained"
          disableElevation
          onClick={criarTabelaCusto}
        >
          {texts.formularioCustosProposta.adicionarCustos}
          <AddCircleOutlineOutlinedIcon className="ml-2" />
        </Button>
      </Box>
      <Box>
        {props.custos?.map((custo, index) => {
          return (
            <Custos
              key={index}
              index={index}
              dados={custo}
              setCustos={props.setCustos}
              custos={props.custos}
              deletarCustos={deletarTabelaCustos}
            />
          );
        })}
      </Box>
      <Box className="w-full" sx={{ height: "8rem" }} />
    </Box>
  );
};

export default FormularioCustosProposta;