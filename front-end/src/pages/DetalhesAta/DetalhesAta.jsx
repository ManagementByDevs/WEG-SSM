import { React, useState, useEffect, useContext } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import { keyframes } from "@emotion/react";

import { Box, Typography, Button, Divider, Tooltip } from "@mui/material";

import SaveAltOutlinedIcon from "@mui/icons-material/SaveAltOutlined";
import OtherHousesIcon from "@mui/icons-material/OtherHouses";
import DensitySmallIcon from "@mui/icons-material/DensitySmall";
import DoneOutlinedIcon from '@mui/icons-material/DoneOutlined';

import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Caminho from "../../components/Caminho/Caminho";
import PropostaDeAta from "../../components/PropostaDeAta/PropostaDeAta";
import Feedback from "../../components/Feedback/Feedback";

import FontContext from "../../service/FontContext";

const DetalhesAta = (props) => {
  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // Navigate e location utilizados para verificar state passado por parametro para determinada verificação
  const navigate = useNavigate();
  const location = useLocation();

  // Lista de propostas provisória apenas para visualização na tela
  const listaProposta = [
    {
      titulo: "oiiii",
      problema:
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries",
      proposta:
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen  book. It has survived not only five centuries is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since  the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries",
      frequencia: "Lorem Ipsum is simply dummy text of the printing and",
      beneficios: [
        {
          tipoBeneficio: "Real",
          valor_mensal: "300,00",
          moeda: "BR",
          memoriaCalculo: "memória de cálculo",
          visible: true,
        },
      ],
      periodoExecucao: "07/12/2022 à 08/12/2022",
      paybackSimples: "10 meses",
      ppm: "1234",
      linkJira: "https://www.jira.com",
      responsavelNegocio: "Matheus Franzener Hohmann",
      area: "Weg Digital",
    },
    {
      titulo: "Sistema de Gestão de Demandas",
      problema:
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries",
      proposta:
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen  book. It has survived not only five centuries is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since  the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries",
      frequencia: "Lorem Ipsum is simply dummy text of the printing and",
      beneficios: [
        {
          tipoBeneficio: "Real",
          valor_mensal: "300,00",
          moeda: "BR",
          memoriaCalculo: "memória de cálculo",
          visible: true,
        },
      ],
      periodoExecucao: "07/12/2022 à 08/12/2022",
      paybackSimples: "10 meses",
      ppm: "1234",
      linkJira: "https://www.jira.com",
      responsavelNegocio: "Matheus Franzener Hohmann",
      area: "Weg Digital",
    },
  ];

  // Variável de verificação utilizada para mostrar o sumário ou uma proposta
  const [proposta, setProposta] = useState(false);

  // Variável utilizada para mostrar os dados de uma proposta
  const [dadosProposta, setDadosProposta] = useState(listaProposta[0]);

  // Index de uma proposta, utilizado para mostrar os dados de uma porposta específica
  const [indexProposta, setIndexProposta] = useState(-1);

  // Variável utilizada para passar para a próxima proposta
  const [botaoProximo, setBotaoProximo] = useState(true);

  // Variável utilizada para minimizar os botões da página
  const [minimizar, setMinimizar] = useState(true);

  // feedback para ata criada com sucesso
  const [feedbackAta, setFeedbackAta] = useState(false);

  // useEffect usado para feedback
  useEffect(() => {
    if (location.state?.feedback) {
      setFeedbackAta(true);
    }
  }, [location.state?.feedback]);

  // Função para voltar ao sumário da ata
  const voltarSumario = () => {
    setBotaoProximo(true);
    setProposta(false);
    setIndexProposta(-1);
  };

  // Função para selecionar uma proposta do sumário
  const onClickProposta = (index) => {
    setIndexProposta(index);
    setDadosProposta(listaProposta[index]);
    setProposta(true);
  };

  // Função para voltar para uma proposta
  const voltar = () => {
    if (indexProposta == 0) {
      setProposta(false);
    } else {
      setBotaoProximo(true);
      setDadosProposta(listaProposta[indexProposta - 1]);
      setIndexProposta(indexProposta - 1);
    }
  };

  // Função para passar para a próxima proposta
  const proximo = () => {
    if (indexProposta == listaProposta.length - 1) {
      setBotaoProximo(false);
    } else {
      setProposta(true);
      setDadosProposta(listaProposta[indexProposta + 1]);
      setIndexProposta(indexProposta + 1);
    }
  };

  // Função de criar ata e enviar feedback
  const criarAta = () => {
    navigate("/", { state: { feedback: true } });
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
            />
          </Box>
        </Box>

        {/* Feedback ata criada com sucesso */}
        <Feedback
          open={feedbackAta}
          handleClose={() => {
            setFeedbackAta(false);
          }}
          status={"sucesso"}
          mensagem={"Ata criada com sucesso!"}
        />

        {/* container geral da tela */}
        <Box className="flex flex-col justify-center relative items-center mt-3">
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
                Ata
              </Typography>
              <Typography className="cursor-default mt-2" fontWeight={600}>
                {/* {props.numeroSequencial} */}
                Número Sequencial: 01/2022
              </Typography>
              <Typography className="cursor-default mt-2" fontWeight={600}>
                {/* {props.data} */}
                Data: 06/12/2022
              </Typography>
              <Typography className="cursor-default mt-2" fontWeight={600}>
                {/* {props.inicio} */}
                Início: 14:30 Horas
              </Typography>
              <Typography className="cursor-default mt-2" fontWeight={600}>
                {/* {props.fim} */}
                Fim: 15:30 Horas
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
                  Sumário
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
                  {listaProposta.map((proposta, index) => {
                    return (
                      <Typography
                        fontSize={FontConfig.big}
                        color={"primary.main"}
                        className="underline cursor-pointer overflow-hidden whitespace-nowrap text-ellipsis text-center"
                        key={index}
                        setIndexTitulo={index}
                        onClick={() => onClickProposta(index)}
                      >
                        {index} - {proposta.titulo}
                      </Typography>
                    );
                  })}
                </Box>
              </Box>
            ) : (
              <Box>
                <Typography
                  sx={{
                    marginBottom: "2%",
                    display: "flex",
                    justifyContent: "center",
                  }}
                  fontSize={FontConfig.title}
                  fontWeight={650}
                >
                  Proposta {indexProposta}
                </Typography>
                <PropostaDeAta
                  dadosProposta={dadosProposta}
                  propostaPauta={false}
                  parecerDG={true}
                />
              </Box>
            )}
          </Box>

          {/* Botões de navegação entre as propostas da ata */}
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
                      Voltar
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
                      <OtherHousesIcon></OtherHousesIcon>
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
                      <Typography>Próximo</Typography>
                    </Button>
                  </Box>
                </Box>
                <Tooltip title="Navegação">
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
              <Tooltip title="Publicar Ata">
                <Box
                  onClick={criarAta}
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
        </Box>
      </Box>
    </FundoComHeader>
  );
};

export default DetalhesAta;
