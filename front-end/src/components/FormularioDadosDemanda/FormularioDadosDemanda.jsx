import React, { useContext } from "react";

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

  return (
    <>
      <Box className="flex justify-center items-center" sx={{ height: "45rem", minWidth: "50rem" }} >
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

          <Box>
            <Typography fontSize={FontConfig.default} fontWeight={600}>{texts.formularioDadosDemanda.problema}</Typography>

            <Box sx={{ marginTop: '0.5%', borderLeft: 'solid 4px', borderColor: 'primary.main' }}>
              <CaixaTextoQuill
                placeholder={texts.formularioDadosDemanda.digiteProblema}
                texto={props.dados.problema}
                setTexto={(e) => salvarProblema(e)}
                useScroll={true}
              />
            </Box>
          </Box>

          <Box>
            <Typography fontSize={FontConfig.default} fontWeight={600}>{texts.formularioDadosDemanda.proposta}</Typography>

            <Box sx={{ marginTop: '0.5%', borderLeft: 'solid 4px', borderColor: 'primary.main' }}>
              <CaixaTextoQuill
                placeholder={texts.formularioDadosDemanda.digiteProposta}
                texto={props.dados.proposta}
                setTexto={(e) => salvarProposta(e)}
                useScroll={true}
              />
            </Box>
          </Box>

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
