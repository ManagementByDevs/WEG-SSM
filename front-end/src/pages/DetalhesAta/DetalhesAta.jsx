import { React, useState, useEffect, useContext } from "react";
import { useNavigate, useLocation } from "react-router-dom";

import { keyframes } from "@emotion/react";

import {
  Box,
  Typography,
  Button,
  Divider,
  Tooltip,
  IconButton,
} from "@mui/material";

import SaveAltOutlinedIcon from "@mui/icons-material/SaveAltOutlined";
import OtherHousesIcon from "@mui/icons-material/OtherHouses";
import DensitySmallIcon from "@mui/icons-material/DensitySmall";
import DoneOutlinedIcon from "@mui/icons-material/DoneOutlined";

import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Caminho from "../../components/Caminho/Caminho";
import Feedback from "../../components/Feedback/Feedback";
import DetalhesProposta from "../../components/DetalhesProposta/DetalhesProposta";

import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";
import EntitiesObjectService from "../../service/entitiesObjectService";
import ExportPdfService from "../../service/exportPdfService";
import PropostaService from "../../service/propostaService";
import AtaService from "../../service/ataService";

// Página para mostrar os detalhes da ata selecionada, com opçao de download para pdf
const DetalhesAta = (props) => {
  // Context para trocar a liguagem
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  // Navigate e location utilizados para verificar state passado por parametro para determinada verificação
  const navigate = useNavigate();
  const location = useLocation();

  // Lista de propostas provisória apenas para visualização na tela
  const listaProposta = [EntitiesObjectService.proposta()];

  // Variável de verificação utilizada para mostrar o sumário ou uma proposta
  const [proposta, setProposta] = useState(false);

  // Variável utilizada para mostrar os dados de uma proposta
  const [dadosProposta, setDadosProposta] = useState(null);

  // Index de uma proposta, utilizado para mostrar os dados de uma porposta específica
  const [indexProposta, setIndexProposta] = useState(-1);

  // Variável utilizada para passar para a próxima proposta
  const [botaoProximo, setBotaoProximo] = useState(true);

  // Variável utilizada para minimizar os botões da página
  const [minimizar, setMinimizar] = useState(true);

  // feedback para ata criada com sucesso
  const [feedbackAta, setFeedbackAta] = useState(false);

  const [ata, setAta] = useState(EntitiesObjectService.ata());

  // useEffect usado para feedback
  useEffect(() => {
    if (location.state?.feedback) {
      setFeedbackAta(true);
    }
  }, [location.state?.feedback]);

  // Função para voltar ao sumário da ata
  const voltarSumario = () => {
    setBotaoProximo(true);
    setIndexProposta(-1);
    setProposta(false);
  };

  // Função para selecionar uma proposta do sumário
  const onClickProposta = (index) => {
    setIndexProposta(index);
    setDadosProposta(ata.propostas[index]);
    setProposta(true);
  };

  useEffect(() => {
    if (indexProposta != -1) {
      setProposta(true);
    }
  }, [indexProposta]);

  // Função para voltar para uma proposta
  const voltar = () => {
    if (indexProposta <= 0) {
      setProposta(false);
      setIndexProposta(-1);
    } else {
      setBotaoProximo(true);
      setProposta(false);
      setDadosProposta(ata.propostas[indexProposta - 1]);
      setIndexProposta(indexProposta - 1);
    }
  };

  // Função para passar para a próxima proposta
  const proximo = () => {
    if (indexProposta == ata.propostas.length - 1) {
      setBotaoProximo(false);
    } else {
      setProposta(false);
      setDadosProposta(ata.propostas[indexProposta + 1]);
      setIndexProposta(indexProposta + 1);
    }
  };

  //   Fazer uma animação para os notões de navegação
  const girar = keyframes({
    from: { rotate: "90deg" },
    to: { rotate: "0deg" },
  });
  const girar2 = keyframes({
    from: { rotate: "0deg" },
    to: { rotate: "90deg" },
  });
  const aparecer = keyframes({
    "0%": { width: "10rem", opacity: "0" },
    "100%": { width: "15.5rem", opacity: "1" },
  });
  const sumir = keyframes({
    "0%": { width: "15.5rem", opacity: "1" },
    "100%": { width: "8rem", opacity: "0" },
  });

  // useStates utilizados para animar os botões de navegação na ata
  const [girarIcon, setGirarIcon] = useState(false);
  const [aparecerSumir, setAparecerSumir] = useState(false);
  const [display, setDisplay] = useState("hidden");

  // Função para animar os botões de acordo com o click
  const animarBotoes = () => {
    if (minimizar) {
      setGirarIcon(girar);
      setDisplay("flex");
      setAparecerSumir(aparecer);
    } else {
      setGirarIcon(girar2);
      setTimeout(() => {
        setDisplay("hidden");
      }, 1000);
      setAparecerSumir(sumir);
    }
  };

  // Função para baixar o arquivo pdf da respectiva ata
  const baixarPDF = () => {
    ExportPdfService.exportAta(ata.id).then((response) => {
      let blob = new Blob([response], { type: "application/pdf" });
      let url = URL.createObjectURL(blob);
      let link = document.createElement("a");
      link.href = url;
      link.download = "pdf_ata.pdf";
      link.click();
    });
  };

  // Função para verificar se todos os campos estão preenchidos
  const isAllFieldsFilled = () => {
    // Verifica se os pareceres das propostas foram preenchidos
    let isFilled = ata.propostas.every((proposta) => {
      return (
        proposta.parecerDG != null &&
        proposta.parecerInformacaoDG != null && // Essa variável sempre começa como null
        proposta.parecerInformacaoDG != "<p><br></p>" // Necessário para o editor de texto, pois ele insere esse código quando o campo está vazio
      );
    });

    return isFilled;
  };

  // Atualiza a lista de propostas passada por parâmetro
  const updatePropostas = (
    listaPropostasToUpdate = [EntitiesObjectService.proposta()]
  ) => {
    for (let proposta of listaPropostasToUpdate) {
      PropostaService.putWithoutArquivos(proposta, proposta.id).then(
        (response) => {
          console.log("Proposta atualizada com sucesso! ", response);
        }
      );
    }
  };

  // Função de criar ata e enviar feedback
  const publicarAta = () => {
    if (!isAllFieldsFilled()) {
      console.log("Preencha todos os campos!");
      return;
    }

    // Criação do objeto da ata publicada
    let ataPublished = { ...ata };

    for (let proposta of ataPublished.propostas) {
      if (proposta.parecerDG == "APROVADO") {
        proposta.status = "DONE";
      } else {
        proposta.status = "CANCELLED";
      }
    }

    updatePropostas(ataPublished.propostas);

    console.log("ata a ser ataulizada: ", ataPublished);
    AtaService.put(ataPublished, ataPublished.id).then((response) => {
      console.log("Ata atualizada com sucesso! ", response);
    });

    navigate("/", { state: { feedback: true } });
  };

  // Função que atualiza as propostas da pauta sempre que uma proposta é atualizada
  useEffect(() => {
    if (indexProposta > -1 && dadosProposta != null) {
      let aux = [...ata.propostas];
      aux[indexProposta] = { ...dadosProposta };
      setAta({ ...ata, propostas: aux });
    }
  }, [dadosProposta]);

  useEffect(() => {
    if (location.state?.ata) {
      setAta(location.state.ata);
    }
  }, []);

  useEffect(() => {
    console.log("ata: ", ata);
  }, [ata]);

  return (
    // Começo com o header da página
    <FundoComHeader>
      <Box className="p-2">
        {/* caminho da página */}
        <Box className="flex w-full relative">
          <Caminho />
          <Box
            className=" absolute"
            sx={{ top: "10px", right: "20px", cursor: "pointer" }}
          >
            <SaveAltOutlinedIcon
              fontSize="large"
              className="delay-120 hover:scale-110 duration-300"
              sx={{ color: "icon.main" }}
              onClick={baixarPDF}
            />
          </Box>
        </Box>

        {/* container geral da tela */}
        <Box className="flex flex-col justify-center relative items-center mt-5">
          {/* container da folha */}
          <Box
            className="flex flex-col gap-5 border rounded relative p-10 drop-shadow-lg"
            sx={{ width: "55rem" }}
          >
            <Box className="flex justify-center flex-col">
              <Typography
                fontSize={FontConfig.title}
                sx={{
                  fontWeight: "600",
                  cursor: "default",
                  inlineSize: "800px",
                  overflowWrap: "break-word",
                  textAlign: "center",
                  color: "primary.main",
                }}
              >
                {texts.detalhesAta.ata}
              </Typography>
              <Typography className="cursor-default mt-2" fontWeight={600}>
                {/* {props.numeroSequencial} */}
                {texts.detalhesAta.numeroSequencial}: {ata.numeroSequencial}
              </Typography>
              <Typography className="cursor-default mt-2" fontWeight={600}>
                {/* {analista responsavel} */}
                {texts.detalhesAta.analistaResponsavel}:{" "}
                {ata.analistaResponsavel.nome}
              </Typography>
              <Typography className="cursor-default mt-2" fontWeight={600}>
                {/* {props.inicio} */}
                {texts.detalhesAta.comissao}: {ata.comissao.siglaForum} -{" "}
                {ata.comissao.nomeForum}
              </Typography>
              <Divider sx={{ marginTop: "1%" }} />
            </Box>

            {/* Verificação para mostrar o sumário ou mostrar o componente da proposta */}
            {!proposta ? (
              <Box>
                <Typography
                  fontSize={FontConfig.title}
                  sx={{
                    fontWeight: "600",
                    cursor: "default",
                    inlineSize: "800px",
                    overflowWrap: "break-word",
                    textAlign: "center",
                  }}
                  color="primary.main"
                >
                  {texts.detalhesAta.sumario}
                </Typography>
                <Box
                  sx={{
                    display: "grid",
                    textAlign: "center",
                    marginTop: "2%",
                    gap: "1rem",
                    gridTemplateColumns: "repeat(auto-fit, minmax(30%, 1fr))",
                  }}
                >
                  {ata.propostas?.map((proposta, index) => {
                    return (
                      <Typography
                        fontSize={FontConfig.big}
                        color={"primary.main"}
                        className="underline cursor-pointer overflow-hidden whitespace-nowrap text-ellipsis text-center"
                        key={index}
                        onClick={() => onClickProposta(index)}
                      >
                        {index + 1} - {proposta.titulo}
                      </Typography>
                    );
                  })}
                </Box>
              </Box>
            ) : (
              // Mostrar uma proposta e seus dados
              <Box>
                <Box
                  sx={{
                    display: "flex",
                    alignItems: "center",
                    justifyContent: "center",
                  }}
                >
                  <Typography
                    sx={{
                      marginBottom: "2%",
                      display: "flex",
                      justifyContent: "center",
                    }}
                    fontSize={FontConfig.title}
                    fontWeight={650}
                  >
                    {texts.detalhesPauta.proposta} {indexProposta + 1}
                  </Typography>
                </Box>
                <DetalhesProposta
                  proposta={dadosProposta}
                  setProposta={setDadosProposta}
                />
              </Box>
            )}
          </Box>

          {/* Botões de navegação entre as propostas da ata */}
          {ata.propostas[0].parecerDG == null && (
            <Box
              className="flex fixed justify-end items-center"
              sx={{ width: "30rem", bottom: "20px", right: "20px" }}
            >
              <Box className="flex justify-center">
                <Box className="flex justify-end">
                  <Box
                    className={`w-full ${display} items-center mr-1`}
                    sx={{ animation: `${aparecerSumir} 1.2s forwards` }}
                  >
                    <Box className="flex justify-around w-full">
                      <Button
                        sx={{
                          backgroundColor: "primary.main",
                          color: "text.white",
                          fontSize: FontConfig.default,
                          maxHeight: "2.5rem",
                        }}
                        variant="contained"
                        onClick={() => voltar()}
                      >
                        {texts.detalhesAta.voltar}
                      </Button>
                      <Button
                        sx={{
                          backgroundColor: "primary.main",
                          color: "text.white",
                          fontSize: FontConfig.default,
                          maxHeight: "2.5rem",
                        }}
                        variant="contained"
                        onClick={voltarSumario}
                      >
                        <OtherHousesIcon />
                      </Button>
                      <Button
                        sx={{
                          backgroundColor: "primary.main",
                          color: "text.white",
                          fontSize: FontConfig.default,
                          maxHeight: "2.5rem",
                        }}
                        variant="contained"
                        onClick={proximo}
                      >
                        <Typography>{texts.detalhesAta.proximo}</Typography>
                      </Button>
                    </Box>
                  </Box>
                  <Tooltip title={texts.detalhesAta.navegacao}>
                    <Box
                      className="flex justify-center items-center w-12 h-12 rounded-full cursor-pointer delay-120 hover:scale-110 duration-300"
                      sx={{
                        backgroundColor: "primary.main",
                        color: "text.white",
                        fontSize: FontConfig.default,
                      }}
                      onClick={() => {
                        animarBotoes();
                        setMinimizar(!minimizar);
                      }}
                    >
                      <DensitySmallIcon
                        sx={{
                          rotate: "90deg",
                          animation: `${girarIcon} 1.2s forwards`,
                        }}
                      ></DensitySmallIcon>
                    </Box>
                  </Tooltip>
                </Box>
                <Tooltip title={texts.detalhesAta.publicarAta}>
                  <Box
                    onClick={publicarAta}
                    className="flex justify-center items-center w-12 h-12 rounded-full cursor-pointer delay-120 hover:scale-110 duration-300"
                    sx={{
                      backgroundColor: "primary.main",
                      fontSize: FontConfig.default,
                      marginLeft: "1rem",
                      color: "#FFFF",
                    }}
                  >
                    <DoneOutlinedIcon fontSize="large" />
                  </Box>
                </Tooltip>
              </Box>
            </Box>
          )}
        </Box>
      </Box>
    </FundoComHeader>
  );
};

export default DetalhesAta;
