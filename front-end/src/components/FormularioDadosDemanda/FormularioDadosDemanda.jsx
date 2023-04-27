import React, { useContext } from "react";

import { Box, Typography } from "@mui/material";

import InputComLabel from "../InputComLabel/InputComLabel";

import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";
import CaixaTextoQuill from "../CaixaTextoQuill/CaixaTextoQuill";
import { useState } from "react";
import { useEffect } from "react";

/** Primeira etapa da criação de demanda, com os dados principais em inputs de texto */
const FormularioDadosDemanda = (props) => {
  // Contexto para trocar a linguagem
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // Todas as funções de salvamento modificam diretamente as variáveis do componente "BarraProgressãoDemanda" (paginaDados)
  // através do "props"

  /** Função para salvar o título da demanda */
  const salvarTitulo = (texto) => {
    props.setDados({ ...props.dados, titulo: texto });
  };

  /** Função para salvar o problema da demanda */
  const salvarProblema = (texto) => {
    props.setDados({ ...props.dados, problema: texto });
  };

  /** Função para salvar a proposta da demanda */
  const salvarProposta = (texto) => {
    props.setDados({ ...props.dados, proposta: texto });
  };

  /** Função para salvar a frequência da demanda */
  const salvarFrequencia = (texto) => {
    props.setDados({ ...props.dados, frequencia: texto });
  };

  const [problemaCaixa, setProblemaCaixa] = useState();
  const [propostaCaixa, setPropostaCaixa] = useState();

  return (
    <>
      <Box
        className="flex justify-center items-center"
        sx={{ minHeight: "45rem", minWidth: "50rem", overflowY: "auto" }}
      >
        <Box
          className="w-3/4 flex flex-col justify-evenly"
          sx={{ height: "85%" , marginBottom: "1rem", marginTop: "1rem"}}
        >
          {/* Input de título */}

          <InputComLabel
            texto={props.dados.titulo}
            saveInputValue={salvarTitulo}
            component="input"
            label={texts.formularioDadosDemanda.titulo}
            placeholder={texts.formularioDadosDemanda.digiteTitulo}
            fontConfig={FontConfig.default}
          />

          <Box>
            <Box className="flex">
              <Typography fontSize={FontConfig.default} fontWeight={600}>
                {texts.formularioDadosDemanda.problema}
              </Typography>

              <Typography
                fontSize={props.fontConfig}
                sx={{
                  fontWeight: "800",
                  cursor: "default",
                  margin: "0 .2% .2% .2%",
                }}
                className="text-red-600"
                gutterBottom
              >
                *
              </Typography>
            </Box>

            <Box
              sx={{
                margin: "0 .2% .2% .2%",
                borderLeft: "solid 4px",
                borderColor: "primary.main",
              }}
            >
              <CaixaTextoQuill
                placeholder={texts.formularioDadosDemanda.digiteProblema}
                texto={problemaCaixa}
                setTexto={setProblemaCaixa}
                onChange={(value) => {
                  salvarProblema(value);
                }}
              />
            </Box>
          </Box>

          <Box>
            <Box className="flex">
              <Typography fontSize={FontConfig.default} fontWeight={600}>
                {texts.formularioDadosDemanda.proposta}
              </Typography>

              <Typography
                fontSize={props.fontConfig}
                sx={{
                  fontWeight: "800",
                  cursor: "default",
                  margin: "0 .2% .2% .2%",
                }}
                className="text-red-600"
                gutterBottom
              >
                *
              </Typography>
            </Box>

            <Box
              sx={{
                margin: "0 .2% .2% .2%",
                borderLeft: "solid 4px",
                borderColor: "primary.main",
              }}
            >
              <CaixaTextoQuill
                placeholder={texts.formularioDadosDemanda.digiteProposta}
                texto={propostaCaixa}
                setTexto={setPropostaCaixa}
                onChange={(value) => {
                  salvarProposta(value);
                }}
              />
            </Box>
          </Box>

          <Box sx={{ width: "40%", margin: "0 .2% .2% .2%" }}>
            {/* Input de frequência */}
            <InputComLabel
              texto={props.dados.frequencia}
              saveInputValue={salvarFrequencia}
              component="input"
              label={texts.formularioDadosDemanda.frequenciaDeUso}
              placeholder={texts.formularioDadosDemanda.digiteFrequenciaDeUso}
              fontConfig={FontConfig.default}
            />
          </Box>
        </Box>
      </Box>
    </>
  );
};

export default FormularioDadosDemanda;
