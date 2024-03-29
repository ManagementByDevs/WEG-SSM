import React, { useState, useEffect, useContext } from "react";

import { Box, Button, Divider, Typography } from "@mui/material";
import AddCircleOutlineOutlinedIcon from "@mui/icons-material/AddCircleOutlineOutlined";

import Custos from "../../Custos/Custos";

import TextLanguageContext from "../../../service/TextLanguageContext";
import FontContext from "../../../service/FontContext";
import CustosService from "../../../service/custosService";
import SpeechSynthesisContext from "../../../service/SpeechSynthesisContext";

// Etapa de criação de proposta para adicionar as tabelas de custos
const FormularioCustosProposta = (props) => {
  // Contexto para trocar a linguagem
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  /** Context para ler o texto da tela */
  const { lendoTexto, lerTexto, librasAtivo } = useContext(
    SpeechSynthesisContext
  );

  // UseState para armazenar as horas totais dos custos
  const [horasTotais, setHorasTotais] = useState(0);

  // UseState para armazenar o valor total dos custos
  const [valorTotal, setValorTotal] = useState(0);

  // UseEffect para calcular as horas totais
  useEffect(() => {
    let aux = 0;
    for (let i = 0; i < props.custos.length; i++) {
      for (let j = 0; j < props.custos[i].custos.length; j++) {
        const horasString = props.custos[i].custos[j].horas;

        if (horasString) {
          if (typeof horasString !== "number") {
            const horasNumber = parseFloat(horasString.replace(",", "."));
            if (!isNaN(horasNumber)) {
              aux += horasNumber;
            }
          } else {
            aux += horasString;
          }
        } else {
          aux += 0;
        }
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
    if (lendoTexto) {
      lerTexto(texts.formularioCustosProposta.adicionarCustos);
    } else if (librasAtivo) {
      return;
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

  /** Função para formatar um valor em formato double para string (troca de "." por ",") */
  const formatarCusto = (valor) => {
    return valor.toString().replace(".", ",");
  }

  return (
    <Box className="flex flex-col">
      <Box className="flex w-full justify-between items-end mt-10 mb-2">
        <Box className="flex">
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
              lerTexto(horasTotais, " ", texts.formularioCustosProposta.horas)
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
              lerTexto(texts.formularioCustosProposta.moeda, " ", valorTotal)
            }
          >
            {texts.formularioCustosProposta.moeda}
            {formatarCusto(valorTotal)}
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
        <Divider />
        {props.custos?.map((custo, index) => {
          return (
            <Box key={index}>
              <Custos
                index={index}
                dados={custo}
                setCustos={props.setCustos}
                custos={props.custos}
                deletarCustos={deletarTabelaCustos}
              />
              <Divider sx={{ marginTop: "1.3rem" }} />
            </Box>
          );
        })}
      </Box>
      <Box className="w-full" sx={{ height: "8rem" }} />
    </Box>
  );
};

export default FormularioCustosProposta;
