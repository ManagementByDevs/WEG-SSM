import React, { useState, useContext, useEffect, useRef } from "react";
import { Box, Typography, TextareaAutosize, Tooltip } from "@mui/material";

import BeneficiosDetalheDemanda from "../../components/BeneficiosDetalheDemanda/BeneficiosDetalheDemanda";

import MenuItem from "@mui/material/MenuItem";
import FormControl from "@mui/material/FormControl";
import Select from "@mui/material/Select";

import MicNoneOutlinedIcon from "@mui/icons-material/MicNoneOutlined";
import MicOutlinedIcon from "@mui/icons-material/MicOutlined";

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

  // Variável para armazenar o titulo da demanda
  const [tituloDemanda, setTituloDemanda] = useState(dados.titulo);

  // Variável para armazenar o problema da demanda
  const [problema, setProblema] = useState(dados.problema);

  // Variável para armazenar a proposta da demanda
  const [proposta, setProposta] = useState(dados.proposta);

  // Variável para armazenar a frequencia de uso da demanda
  const [frequencia, setFrequencia] = useState(dados.frequencia);

  // Variável para armazenar os beneficios da demanda
  const [beneficios, setBeneficios] = useState(null);

  // Variável para armazenar uma proposta de pauta
  const [propostaDePauta, setPropostaDePauta] = useState(false);

  // Variávle para armazenar o parecer
  const [parecer, setParecer] = useState("");

  // Variável para armazenar o parecer da comissão
  const [parecerComissao, setParecerComissao] = useState(false);

  // Variável para armazenar o parecer da DG
  const [parecerDG, setParecerDG] = useState(props.parecerDG);

  // UseEffect para setar os dados da proposta
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

  // useEffect para aparecer a parte da comissão, caso seja uma porposta de uma pauta
  useEffect(() => {
    setPropostaDePauta(props.propostaPauta);
  });

  // mudar o conteúdo do select e
  const mudarParecer = (event) => {
    if (event.target.value == 2) {
      setParecerComissao(true);
    } else {
      setParecerComissao(false);
    }
    setParecer(event.target.value);
  };

  // // ********************************************** Gravar audio **********************************************

  const recognitionRef = useRef(null);

  const [escutar, setEscutar] = useState(false);

  const [palavrasJuntas, setPalavrasJuntas] = useState("");

  const ouvirAudio = () => {
    // Verifica se a API é suportada pelo navegador
    if ("webkitSpeechRecognition" in window) {
      const recognition = new window.webkitSpeechRecognition();
      recognition.continuous = true;
      switch (texts.linguagem) {
        case "pt":
          recognition.lang = "pt-BR";
          break;
        case "en":
          recognition.lang = "en-US";
          break;
        case "es":
          recognition.lang = "es-ES";
          break;
        case "ch":
          recognition.lang = "cmn-Hans-CN";
          break;
        default:
          recognition.lang = "pt-BR";
          break;
      }

      recognition.onstart = () => {
      };

      recognition.onresult = (event) => {
        const transcript =
          event.results[event.results.length - 1][0].transcript;
        setPalavrasJuntas((palavrasJuntas) => palavrasJuntas + transcript);

        // setValorPesquisa(transcript);
      };

      recognition.onerror = (event) => {
        props.setFeedbackErroReconhecimentoVoz(true);
        setEscutar(false);
      };

      recognitionRef.current = recognition;
      recognition.start();
    } else {
      props.setFeedbackErroNavegadorIncompativel(true);
      setEscutar(false);
    }
  };

  const stopRecognition = () => {
    if (recognitionRef.current) {
      recognitionRef.current.stop();
    }
  };

  const startRecognition = () => {
    setEscutar(!escutar);
  };

  useEffect(() => {
    if (escutar) {
      ouvirAudio();
    } else {
      stopRecognition();
    }
  }, [escutar]);

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
                  setBeneficios={setBeneficios}
                  lendo={props.lendo}
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
                </Select>
              </FormControl>
            ) : (
              <Box sx={{ marginLeft: "2%" }}>
                {texts.propostaDeAta.aprovado}
              </Box>
            )}
          </Box>

          {parecerComissao ? (
            <Box
              className="flex items-center justify-between"
              sx={{
                width: "70%",
                height: "8rem",
                marginTop: "2%",
                alignItems: "center",
              }}
            >
              <TextareaAutosize
                placeholder={texts.propostaDeAta.escrevaNovaInformacao}
                style={{
                  width: "95%",
                  overflow: "auto",
                  resize: "none",
                  textAlign: "justify",
                  padding: "3%",
                  background: "transparent",
                  border: "solid 1px",
                  borderRadius: "5px",
                }}
              />
              <Tooltip
                className="hover:cursor-pointer"
                title={texts.homeGerencia.gravarAudio}
                onClick={() => {
                  startRecognition();
                }}
              >
                {escutar ? (
                  <MicOutlinedIcon
                    sx={{
                      cursor: "pointer",
                      color: "primary.main",
                      fontSize: "1.3rem",
                    }}
                  />
                ) : (
                  <MicNoneOutlinedIcon
                    sx={{
                      cursor: "pointer",
                      color: "text.secondary",
                      fontSize: "1.3rem",
                    }}
                  />
                )}
              </Tooltip>
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