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
  const { FontConfig, setFontConfig } = useContext(FontContext);

  /** Lista de benefícios adicionados */
  const [beneficios, setBeneficios] = useState([]);

  // UseEffect para pegar os benefícios já adicionados na demanda na troca de página
  useEffect(() => {
    setBeneficios(props.dados);
  }, []);

  // UseEffect que irá atualizar a lista de benefícios a serem salvos (que não foram excluídos)
  useEffect(() => {
    props.setDados(
      beneficios?.filter((beneficio) => beneficio.visible === true)
    );
  }, [beneficios]);

  /** Adiciona um benefício na lista de benefícios, já criando ele no banco de dados para receber um id */
  function adicionarBeneficio() {
    BeneficioService.post({
      tipoBeneficio: "",
      valor_mensal: "",
      moeda: "",
      memoriaCalculo: "",
    }).then((response) => {
      setBeneficios([
        ...beneficios,
        {
          id: response.id,
          tipoBeneficio: "",
          valor_mensal: "",
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
    setBeneficios(
      beneficios.map((beneficio, index) => {
        if (index === desiredIndex) {
          beneficio.visible = false;
          BeneficioService.delete(beneficio.id).then((response) => { });
        }
        return beneficio;
      })
    );
  }

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
            <Typography fontSize={FontConfig.default}>
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
            if (beneficio.visible) {
              return (
                <Beneficios
                  key={index}
                  save={salvarDados}
                  index={index}
                  removerBeneficio={removerBeneficio}
                  dados={beneficio}
                />
              );
            }
          })}
        </Box>
      </Box>
    </Box>
  );
};

export default FormularioBeneficiosDemanda;
