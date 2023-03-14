import React, { useContext } from "react";

import { Box } from "@mui/material";

import InputComLabel from "../InputComLabel/InputComLabel";

import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";

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

  return (
    <>
      <Box className="flex justify-center items-center" sx={{ height: "45rem" }} >
        <Box className="w-3/4 flex flex-col justify-evenly" sx={{ height: "85%" }} >

          {/* Input de título */}
          <InputComLabel
            texto={props.dados.titulo}
            saveInputValue={salvarTitulo}
            component="input"
            label={texts.formularioDadosDemanda.titulo}
            placeholder={texts.formularioDadosDemanda.digiteTitulo}
            fontConfig={FontConfig.default}
          />

          {/* Input de problema */}
          <InputComLabel
            texto={props.dados.problema}
            saveInputValue={salvarProblema}
            component="textarea"
            label={texts.formularioDadosDemanda.problema}
            placeholder={texts.formularioDadosDemanda.digiteProblema}
            fontConfig={FontConfig.default}
            rows="5"
          />

          {/* Input de proposta */}
          <InputComLabel
            texto={props.dados.proposta}
            saveInputValue={salvarProposta}
            component="textarea"
            label={texts.formularioDadosDemanda.proposta}
            placeholder={texts.formularioDadosDemanda.digiteProposta}
            fontConfig={FontConfig.default}
            rows="8"
          />

          <Box sx={{ width: "40%" }}>
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
