import React, { useState, useEffect, useContext } from "react";

import { Box, Divider, IconButton, Tooltip, Typography, } from "@mui/material";
import AddOutlinedIcon from "@mui/icons-material/AddOutlined";

import "./FormularioBeneficiosDemanda.css";
import Beneficios from "../../Beneficios/Beneficios";

import BeneficioService from "../../../service/beneficioService";
import TextLanguageContext from "../../../service/TextLanguageContext";
import FontContext from "../../../service/FontContext";
import SpeechSynthesisContext from "../../../service/SpeechSynthesisContext";

/** Segunda etapa da criação de demanda, usando uma lista de benefícios dos props */
const FormularioBeneficiosDemanda = (props) => {

  // Contexto para trocar a linguagem
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  /** Context para ler o texto da tela */
  const { lerTexto } = useContext(SpeechSynthesisContext);

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
          valor_mensal: "0,00",
          moeda: "",
          memoriaCalculo: "",
          visible: true,
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
        } else {
          listaNova.push({ ...beneficios[contagem], visible: false });
        }
      }
      setBeneficios(listaNova);
    });
  }

  return (
    <Box className="flex justify-center items-center mb-20">
      <Box
        className="flex flex-col w-3/4"
        sx={{ height: "85%", minWidth: "44rem" }}
      >
        <Box
          className="flex justify-between items-center border-l-4 px-2 mt-5"
          sx={{ borderColor: "primary.main" }}
        >
          <Typography
            fontSize={FontConfig.big}
            fontWeight={500}
            onClick={() =>
              lerTexto(texts.formularioBeneficiosDemanda.listaDeBeneficios)
            }
          >
            {texts.formularioBeneficiosDemanda.listaDeBeneficios}
          </Typography>

          {/* Botão para adicionar novo benefício */}
          <Tooltip title={texts.formularioBeneficiosDemanda.adicionar}>
            <IconButton color="primary" onClick={adicionarBeneficio}>
              <AddOutlinedIcon />
            </IconButton>
          </Tooltip>
        </Box>
        <Divider />
        <Box
          className="flex flex-col w-full"
          sx={{
            marginTop: "3%",
            gap: "5%",
            minHeight: "35rem",
          }}
        >
          {/* Lista de benefícios */}
          {beneficios.some((beneficioAux) => beneficioAux.visible) ? (
            beneficios?.map((beneficio, index) => {
              if (beneficio.visible) {
                return (
                  <Box className="mb-5" key={index}>
                    <Beneficios
                      key={index}
                      save={salvarDados}
                      index={index}
                      removerBeneficio={removerBeneficio}
                      dados={beneficio}
                    />
                  </Box>
                );
              }
            })
          ) : (
            <Box color="text.secondary" className="text-center">
              <Typography>...</Typography>
              <Typography fontSize={FontConfig.big} fontWeight={400}>
                {texts.formularioBeneficiosDemanda.semBeneficios}
              </Typography>
            </Box>
          )}
        </Box>
      </Box>
    </Box>
  );
};

export default FormularioBeneficiosDemanda;