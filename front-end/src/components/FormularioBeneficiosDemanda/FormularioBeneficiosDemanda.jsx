import React, { useState, useEffect, useContext } from "react";

import { Box, Button, Typography } from "@mui/material";
import AddOutlinedIcon from "@mui/icons-material/AddOutlined";

import "./FormularioBeneficiosDemanda.css";
import Beneficios from "../Beneficios/Beneficios";

import BeneficioService from "../../service/beneficioService";
import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";

/** Segunda etapa da criação de demanda, usando uma lista de benefícios dos props */
const FormularioBeneficiosDemanda = (props) => {
  // Contexto para trocar a linguagem
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  /** Lista de benefícios adicionados */
  const [beneficios, setBeneficios] = useState([]);

  // UseEffect para pegar os benefícios já adicionados na demanda na troca de página
  useEffect(() => {
    setBeneficios(props.dados);
  }, []);

  // UseEffect que irá atualizar a lista de benefícios a serem salvos (que não foram excluídos)
  useEffect(() => {
    props.setDados(beneficios);
  }, [beneficios]);

  /** Adiciona um benefício na lista de benefícios, já criando ele no banco de dados para receber um id */
  function adicionarBeneficio() {
    props.salvarBeneficios();
    BeneficioService.post().then((response) => {
      setBeneficios([
        ...beneficios,
        {
          id: response.id,
          tipoBeneficio: "",
          valor_mensal: "",
          moeda: "",
          memoriaCalculo: "",
        },
      ]);
    });
  }

  /** Função para salvar os dados de um benefício quando editado */
  function salvarDados(dados) {
    setBeneficios(
      beneficios.map((beneficio) => {
        if (beneficio.id === dados.id) {
          beneficio = dados;
        }
        return beneficio;
      })
    );
  }

  /** Função que irá setar a visibildade de um benefício para false, considerando o benefício como excluído */
  function removerBeneficio(desiredIndex) {
    BeneficioService.delete(beneficios[desiredIndex].id).then((response) => {
      let listaNova = [];
      for (let contagem = 0; contagem < beneficios.length; contagem++) {
        if (contagem != desiredIndex) {
          listaNova.push({ ...beneficios[contagem] });
        }
      }
      setBeneficios(listaNova);
    });
  }

  // Função que irá setar o texto que será "lido" pela a API
  const lerTexto = (texto) => {
    if (props.lendo) {
      props.setTexto(texto);
    }
  };

  // Função que irá "ouvir" o texto que será "lido" pela a API
  useEffect(() => {
    let countFala = 0;
    const synthesis = window.speechSynthesis;
    const utterance = new SpeechSynthesisUtterance(props.texto);
    if (props.lendo && props.texto != "" && countFala == 0) {
      if ("speechSynthesis" in window) {
        synthesis.speak(utterance);
        countFala++;
      }
      props.setTexto("");
    } else if (!props.lendo || countFala > 0) {
      if ("speechSynthesis" in window) {
        synthesis.cancel();
      }
    }
  }, [props.texto, props.lendo]);

  return (
    <Box className="flex justify-center items-center" sx={{ height: "45rem" }}>
      <Box className="flex flex-col" sx={{ height: "85%", width: "48rem" }}>
        <Box>
          {/* Botão para adicionar novo benefício */}
          <Button
            className="rounded flex justify-evenly"
            color="primary"
            variant="contained"
            disableElevation
            onClick={adicionarBeneficio}
          >
            <Typography
              fontSize={FontConfig.default}
              onClick={() =>
                lerTexto(texts.formularioBeneficiosDemanda.adicionar)
              }
            >
              {texts.formularioBeneficiosDemanda.adicionar}
            </Typography>
            <AddOutlinedIcon />
          </Button>
        </Box>
        <Box
          className="flex flex-col w-full"
          sx={{
            marginTop: "3%",
            gap: "5%",
            minHeight: "35rem",
          }}
        >
          {/* Lista de benefícios */}
          {beneficios?.map((beneficio, index) => {
            return (
              <Beneficios
                key={index}
                save={salvarDados}
                index={index}
                removerBeneficio={removerBeneficio}
                dados={beneficio}
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
      </Box>
    </Box>
  );
};

export default FormularioBeneficiosDemanda;
