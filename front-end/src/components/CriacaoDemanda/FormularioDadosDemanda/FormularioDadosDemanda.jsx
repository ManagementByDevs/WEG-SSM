import React, { useContext } from "react";

import { Box, Typography } from "@mui/material";

import InputComLabel from "../../InputComLabel/InputComLabel";

import FontContext from "../../../service/FontContext";
import TextLanguageContext from "../../../service/TextLanguageContext";
import CaixaTextoQuill from "../../CaixaTextoQuill/CaixaTextoQuill";
import SpeechSynthesisContext from "../../../service/SpeechSynthesisContext";

/** Primeira etapa da criação de demanda, com os dados principais em inputs de texto */
const FormularioDadosDemanda = (props) => {
  // Contexto para trocar a linguagem
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  // Context para ler o texto da tela
  const { lerTexto } = useContext(SpeechSynthesisContext);

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

  return (
    <>
      <Box
        className="flex h-full justify-center items-center"
        sx={{ minWidth: "50rem", overflowY: "auto" }}
      >
        <Box
          className="w-3/4 flex flex-col justify-evenly"
          sx={{ marginBottom: "1rem", marginTop: "3rem" }}
        >
          {/* Input de título */}
          <InputComLabel
            texto={props.dados.titulo}
            saveInputValue={salvarTitulo}
            component="input"
            label={texts.formularioDadosDemanda.titulo}
            placeholder={texts.formularioDadosDemanda.digiteTitulo}
            fontConfig={FontConfig.default}
            erro={props.erro}
          />
          <Box>
            <Box className="flex" sx={{ marginTop: "3rem" }}>
              <Typography
                fontSize={FontConfig.default}
                fontWeight={600}
                onClick={() => {
                  lerTexto(texts.formularioDadosDemanda.problema);
                }}
              >
                {texts.formularioDadosDemanda.problema}
              </Typography>

              <Typography
                fontSize={props.fontConfig}
                sx={{
                  fontWeight: "800",
                  cursor: "default",
                }}
                className="text-red-600"
                gutterBottom
              >
                *
              </Typography>
            </Box>

            <Box sx={{ borderLeft: "solid 4px", borderColor: "primary.main" }}>
              <CaixaTextoQuill
                placeholder={texts.formularioDadosDemanda.digiteProblema}
                texto={props.dados.problema}
                onChange={(value) => {
                  salvarProblema(value);
                }}
                label="problema"
              />
            </Box>
          </Box>

          <Box>
            <Box className="flex" sx={{ marginTop: "3rem" }}>
              <Typography
                fontSize={FontConfig.default}
                fontWeight={600}
                onClick={() => lerTexto(texts.formularioDadosDemanda.proposta)}
              >
                {texts.formularioDadosDemanda.proposta}
              </Typography>

              <Typography
                fontSize={props.fontConfig}
                sx={{
                  fontWeight: "800",
                  cursor: "default",
                }}
                className="text-red-600"
                gutterBottom
              >
                *
              </Typography>
            </Box>
            <Box sx={{ borderLeft: "solid 4px", borderColor: "primary.main" }}>
              <CaixaTextoQuill
                placeholder={texts.formularioDadosDemanda.digiteProposta}
                texto={props.dados.proposta}
                onChange={(value) => {
                  salvarProposta(value);
                }}
                label="proposta"
              />
            </Box>
          </Box>
          <Box sx={{ width: "40%", marginTop: "3rem" }}>
            {/* Input de frequência */}
            <InputComLabel
              texto={props.dados.frequencia}
              saveInputValue={salvarFrequencia}
              component="input"
              label={texts.formularioDadosDemanda.frequenciaDeUso}
              placeholder={texts.formularioDadosDemanda.digiteFrequenciaDeUso}
              fontConfig={FontConfig.default}
              smartRecomendation
            />
          </Box>
        </Box>
      </Box>
    </>
  );
};

export default FormularioDadosDemanda;
