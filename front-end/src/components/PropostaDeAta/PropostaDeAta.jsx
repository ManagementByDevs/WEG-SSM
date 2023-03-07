import React, { useState, useContext, useEffect } from "react";
import { Box, Typography, TextareaAutosize } from "@mui/material";

import BeneficiosDetalheDemanda from "../../components/BeneficiosDetalheDemanda/BeneficiosDetalheDemanda";

import MenuItem from "@mui/material/MenuItem";
import FormControl from "@mui/material/FormControl";
import Select from "@mui/material/Select";

import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";

const PropostaDeAta = (props) => {
  // Context para alterar o idioma do texto
  const { texts, setTexts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // Variáveis de estilo para usar no componente
  const textoConteudo = {
    textAlign: "justify",
    color: "text.secondary",
    marginLeft: "30px",
  };

  // Variável para armazenar os dados da proposta
  const dados = props.dadosProposta;

  // UseEffect para setar os dadpos da proposta
  useEffect(() => {
    setTituloDemanda(dados.titulo);
    setProblema(dados.problema);
    setProposta(dados.proposta);
    setFrequencia(dados.frequencia);
    setBeneficios(dados.beneficios);

    const aux = dados.beneficios.map((beneficio) => {
      return {
        tipoBeneficio: beneficio.tipoBeneficio,
        valor_mensal: beneficio.valor_mensal,
        moeda: beneficio.moeda,
        memoriaCalculo: beneficio.memoriaCalculo,
        visible: beneficio.visible,
      };
    });
    setBeneficios(aux);
  }, [dados]);

  // UseStates para armazenar os valores dos campos da proposta
  const [tituloDemanda, setTituloDemanda] = useState(dados.titulo);
  const [problema, setProblema] = useState(dados.problema);
  const [proposta, setProposta] = useState(dados.proposta);
  const [frequencia, setFrequencia] = useState(dados.frequencia);
  const [beneficios, setBeneficios] = useState(null);
  const [propostaDePauta, setPropostaDePauta] = useState(false);

  // função para aparecer a parte da comissão, caso seja uma porposta de uma pauta
  useEffect(() => {
    setPropostaDePauta(props.propostaPauta);
  });

  // select do parecer da comissao
  const [parecer, setParecer] = useState("");
  const [parecerComissao, setParecerComissao] = useState(false);

  // useState para a pauta criada
  const [parecerDG, setParecerDG] = useState(props.parecerDG);

  // mudar o conteúdo do select e
  const mudarParecer = (event) => {
    if (event.target.value == 2) {
      setParecerComissao(true);
    } else {
      setParecerComissao(false);
    }
    setParecer(event.target.value);
  };

  return (
    <Box>
      {/* Conteúdo da proposta, titulo, problema... */}

      <Box sx={{ marginTop: "2%" }}>
        <Typography
          fontSize={FontConfig.veryBig}
          fontWeight="600"
          color="text.primary"
        >
          {texts.propostaDeAta.codigoPpm}:
        </Typography>
        <Typography fontSize={FontConfig.medium} sx={textoConteudo}>
          {dados.ppm}
        </Typography>
      </Box>

      <Box sx={{ marginTop: "2%" }}>
        <Typography
          fontSize={FontConfig.veryBig}
          fontWeight="600"
          color="text.primary"
        >
          {texts.propostaDeAta.tituloDaProposta}:
        </Typography>
        <Typography fontSize={FontConfig.medium} sx={textoConteudo}>
          {dados.titulo}
        </Typography>
      </Box>

      <Box sx={{ marginTop: "2%" }}>
        <Typography
          fontSize={FontConfig.veryBig}
          fontWeight="600"
          color="text.primary"
        >
          {texts.propostaDeAta.responsavelDoNegocio}:
        </Typography>
        <Typography fontSize={FontConfig.medium} sx={textoConteudo}>
          {dados.responsavelNegocio} - {dados.area}
        </Typography>
      </Box>

      <Box sx={{ marginTop: "2%" }}>
        <Typography
          fontSize={FontConfig.veryBig}
          fontWeight="600"
          color="text.primary"
        >
          {texts.propostaDeAta.problema}:
        </Typography>
        <Typography fontSize={FontConfig.medium} sx={textoConteudo}>
          {dados.problema}
        </Typography>
      </Box>

      <Box sx={{ marginTop: "2%" }}>
        <Typography
          fontSize={FontConfig.veryBig}
          fontWeight="600"
          color="text.primary"
        >
          {texts.propostaDeAta.proposta}:
        </Typography>
        <Typography fontSize={FontConfig.medium} sx={textoConteudo}>
          {dados.proposta}
        </Typography>
      </Box>

      <Box sx={{ marginTop: "2%" }}>
        <Typography
          fontSize={FontConfig.veryBig}
          fontWeight="600"
          color="text.primary"
        >
          {texts.propostaDeAta.beneficios}:
        </Typography>
        <Box className="mt-2 flex flex-col gap-5">
          {dados.beneficios.map((beneficio, index) => {
            if (beneficio.visible) {
              return (
                <BeneficiosDetalheDemanda
                  editavel={false}
                  key={index}
                  index={index}
                  beneficio={beneficio}
                />
              );
            }
          })}
        </Box>
      </Box>

      <Box sx={{ marginTop: "2%" }}>
        <Typography
          fontSize={FontConfig.veryBig}
          fontWeight="600"
          color="text.primary"
        >
          {texts.propostaDeAta.frequenciaDeUso}:
        </Typography>
        <Typography fontSize={FontConfig.medium} sx={textoConteudo}>
          {dados.frequencia}
        </Typography>
      </Box>

      <Box sx={{ marginTop: "2%" }}>
        <Typography
          fontSize={FontConfig.veryBig}
          fontWeight="600"
          color="text.primary"
        >
          {texts.propostaDeAta.custos}:
        </Typography>
        <Typography fontSize={FontConfig.medium} sx={textoConteudo}>
          {texts.propostaDeAta.custosAqui}
        </Typography>
      </Box>

      <Box sx={{ marginTop: "2%" }}>
        <Typography
          fontSize={FontConfig.veryBig}
          fontWeight="600"
          color="text.primary"
        >
          {texts.propostaDeAta.periodoDeExecucao}:
        </Typography>
        <Typography fontSize={FontConfig.medium} sx={textoConteudo}>
          {dados.periodoExecucao}
        </Typography>
      </Box>

      <Box sx={{ marginTop: "2%" }}>
        <Typography
          fontSize={FontConfig.veryBig}
          fontWeight="600"
          color="text.primary"
        >
          {texts.propostaDeAta.paybackSimples}:
        </Typography>
        <Typography fontSize={FontConfig.medium} sx={textoConteudo}>
          {dados.paybackSimples}
        </Typography>
      </Box>

      <Box sx={{ marginTop: "2%" }}>
        <Typography
          fontSize={FontConfig.veryBig}
          fontWeight="600"
          color="text.primary"
        >
          {texts.propostaDeAta.linkDoJira}:
        </Typography>
        <Typography fontSize={FontConfig.medium} sx={textoConteudo}>
          {dados.linkJira}
        </Typography>
      </Box>

      <Box sx={{ marginTop: "2%" }}>
        <Typography
          fontSize={FontConfig.veryBig}
          fontWeight="600"
          color="text.primary"
        >
          {texts.propostaDeAta.anexos}:
        </Typography>
        <Typography fontSize={FontConfig.medium} sx={textoConteudo}>
          {texts.propostaDeAta.anexos}
        </Typography>
      </Box>

      {/* Parecer da comissão caso seja uma proposta de uma pauta */}

      {!propostaDePauta ? (
        <Box sx={{ display: "flex", flexDirection: "column" }}>
          <Box
            sx={{
              marginTop: "2%",
              display: "flex",
              flexDirection: "row",
              alignItems: "center",
            }}
          >
            <Typography
              fontSize={FontConfig.veryBig}
              fontWeight="600"
              color="text.primary"
            >
              {texts.propostaDeAta.parecerComissao}:
            </Typography>

            {/* Aparecer o select do parecer da comissao ou aparecer o resultado do parecer, caso a pauta ja esteja criada */}
            {!parecerDG ? (
              <FormControl sx={{ width: "12rem", marginLeft: "2%" }}>
                <Select
                  value={parecer}
                  onChange={mudarParecer}
                  displayEmpty
                  inputProps={{ "aria-label": "Without label" }}
                >
                  <MenuItem value="">{texts.propostaDeAta.aprovado}</MenuItem>
                  <MenuItem value={1}>{texts.propostaDeAta.reprovado}</MenuItem>
                  <MenuItem value={2}>{texts.propostaDeAta.maisInformacoes}</MenuItem>
                  <MenuItem value={3}>{texts.propostaDeAta.businessCase}</MenuItem>
                </Select>
              </FormControl>
            ) : (
              <Box sx={{ marginLeft: "2%" }}>{texts.propostaDeAta.aprovado}</Box>
            )}
          </Box>

          {parecerComissao ? (
            <Box
              sx={{
                marginTop: "2%",
                display: "flex",
                flexDirection: "row",
                alignItems: "center",
              }}
            >
              <TextareaAutosize
                placeholder={texts.propostaDeAta.escrevaNovaInformacao}
                style={{
                  width: "70%",
                  height: "8rem",
                  overflow: "auto",
                  resize: "none",
                  textAlign: "justify",
                  padding: "3%",
                  background: "transparent",
                  border: "solid 1px",
                  borderRadius: "5px",
                }}
              />
            </Box>
          ) : (
            <></>
          )}

          {/* Adicionar o parecer da dg */}
          {!parecerDG ? (
            <></>
          ) : (
            <Box
              sx={{
                marginTop: "2%",
                display: "flex",
                flexDirection: "row",
                alignItems: "center",
              }}
            >
              <Typography
                fontSize={FontConfig.veryBig}
                fontWeight="600"
                color="text.primary"
              >
                {texts.propostaDeAta.parecerDG}:
              </Typography>

              <FormControl sx={{ width: "12rem", marginLeft: "2%" }}>
                <Select
                  value={parecer}
                  onChange={mudarParecer}
                  displayEmpty
                  inputProps={{ "aria-label": "Without label" }}
                >
                  <MenuItem value="">{texts.propostaDeAta.aprovado}</MenuItem>
                  <MenuItem value={1}>{texts.propostaDeAta.reprovado}</MenuItem>
                </Select>
              </FormControl>
            </Box>
          )}
        </Box>
      ) : (
        <></>
      )}
    </Box>
  );
};

export default PropostaDeAta;
