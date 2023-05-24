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
  };

  /** Função para criar uma tabela de custos no banco de dados e adicionar na lista */
  const criarTabelaCusto = () => {
    if (props.lendo) {
      lerTexto(texts.formularioCustosProposta.adicionarCustos);
    } else {
      CustosService.postTabela({
        custos: [
          {
            tipoDespesa: "",
            perfilDespesa: "",
            periodoExecucao: "",
            horas: "",
            valorHora: "",
          },
        ],
        ccs: [
          {
            codigo: "",
            porcentagem: "",
          },
        ],
      }).then((response) => {
        props.setCustos([...props.custos, response]);
      });
    }
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

  // Função que irá setar o texto que será "lido" pela a API
  const lerTexto = (texto) => {
    if (props.lendo) {
      props.setTexto(texto);
    }
  };

  // Função que irá "ouvir" o texto que será "lido" pela a API
  useEffect(() => {
    if (props.lendo && props.texto != "") {
      if ("speechSynthesis" in window) {
        const synthesis = window.speechSynthesis;
        const utterance = new SpeechSynthesisUtterance(props.texto);
        synthesis.speak(utterance);
      }
      props.setTexto("");
    }
  }, [props.texto]);

  return (
    <Box className="flex flex-col">
      <Box className="flex w-full justify-between mt-10 items-end">
        <Box className="flex ml-10">
          <Typography
            fontSize={FontConfig.medium}
            sx={{ marginRight: "8px" }}
            onClick={() => lerTexto(texts.formularioCustosProposta.total)}
          >
            {texts.formularioCustosProposta.total}:
          </Typography>
          <Typography
            fontSize={FontConfig.medium}
            sx={{ marginRight: "15px" }}
            onClick={() =>
              lerTexto(horasTotais + " " + texts.formularioCustosProposta.horas)
            }
          >
            {horasTotais}
            {texts.formularioCustosProposta.horas}
          </Typography>
          <Typography fontSize={FontConfig.medium} sx={{ marginRight: "15px" }}>
            -
          </Typography>
          <Typography
            fontSize={FontConfig.medium}
            sx={{ marginRight: "8px" }}
            onClick={() =>
              lerTexto(texts.formularioCustosProposta.moeda + " " + valorTotal)
            }
          >
            {texts.formularioCustosProposta.moeda}
            {valorTotal}
          </Typography>
        </Box>
        <Button
          sx={{
            minWidth: "179px",
            backgroundColor: "primary.main",
            color: "text.white",
            fontSize: FontConfig.default,
          }}
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
              setFeedbackErroNavegadorIncompativel={
                props.setFeedbackErroNavegadorIncompativel
              }
              setFeedbackErroReconhecimentoVoz={
                props.setFeedbackErroReconhecimentoVoz
              }
              lendo={props.lendo}
              texto={props.texto}
              setTexto={props.setTexto}
            />
          );
        })}
      </Box>
      <Box className="w-full" sx={{ height: "8rem" }} />
    </Box>
  );
};

export default FormularioCustosProposta;
