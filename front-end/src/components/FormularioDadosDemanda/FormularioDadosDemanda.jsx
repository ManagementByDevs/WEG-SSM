import React, { useContext, useEffect } from "react";

import { Box, Typography } from "@mui/material";

import InputComLabel from "../InputComLabel/InputComLabel";

import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";
import CaixaTextoQuill from "../CaixaTextoQuill/CaixaTextoQuill";

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
    } else if (!props.lendo) {
      if ("speechSynthesis" in window) {
        const synthesis = window.speechSynthesis;
        synthesis.cancel();
      }
    }
  }, [props.texto, props.lendo]);

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
            setFeedbackErroReconhecimentoVoz={
              props.setFeedbackErroReconhecimentoVoz
            }
            setFeedbackErroNavegadorIncompativel={
              props.setFeedbackErroNavegadorIncompativel
            }
            lendo={props.lendo}
            textoFala={props.texto}
            setTexto={props.setTexto}
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

            <Box
              sx={{
                borderLeft: "solid 4px",
                borderColor: "primary.main",
              }}
            >
              <CaixaTextoQuill
                placeholder={texts.formularioDadosDemanda.digiteProblema}
                texto={props.dados.problema}
                onChange={(value) => {
                  salvarProblema(value);
                }}
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

            <Box
              sx={{
                borderLeft: "solid 4px",
                borderColor: "primary.main",
              }}
            >
              <CaixaTextoQuill
                placeholder={texts.formularioDadosDemanda.digiteProposta}
                texto={props.dados.proposta}
                onChange={(value) => {
                  salvarProposta(value);
                }}
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
              setFeedbackErroReconhecimentoVoz={
                props.setFeedbackErroReconhecimentoVoz
              }
              setFeedbackErroNavegadorIncompativel={
                props.setFeedbackErroNavegadorIncompativel
              }
            />
          </Box>
        </Box>
      </Box>
    </>
  );
};

export default FormularioDadosDemanda;
