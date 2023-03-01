import { useState, React, useContext } from "react";
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
import DeleteIcon from "@mui/icons-material/Delete";
import PostAddOutlinedIcon from "@mui/icons-material/PostAddOutlined";

import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Caminho from "../../components/Caminho/Caminho";
import PropostaDeAta from "../../components/PropostaDeAta/PropostaDeAta";

import { keyframes } from "@emotion/react";

import { useNavigate } from "react-router-dom";

import FontContext from "../../service/FontContext";

const DetalhesPauta = (props) => {
  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // Navigate utilizado para navegar para uma determianda página
  const navigate = useNavigate();

  // Variáveis de estilo para o componente
  const tituloProposta = {
    textDecoration: "underline",
    cursor: "pointer",
    color: "primary.main",
    overflow: "hidden",
    whiteSpace: "nowrap",
    textOverflow: "ellipsis",
    textAlign: "center",
  };

  const informacoesAta = {
    fontWeight: "600",
    cursor: "default",
    marginTop: "1%",
  };

  // Lista provisória de propostas para preencher a tela
  const listaProposta = [
    {
      titulo: "Exemplo de Proposta",
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

  // Variável utilizada para passar para a próxima proposta
  const [botaoProximo, setBotaoProximo] = useState(true);

  // Variável utilizada para mostrar os dados de uma proposta
  const [dadosProposta, setDadosProposta] = useState(listaProposta[0]);

  // Index de uma proposta, utilizado para mostrar os dados de uma porposta específica
  const [indexProposta, setIndexProposta] = useState(-1);

  // Variável utilizada para minimizar os botões da página
  const [minimizar, setMinimizar] = useState(true);

  // Função para selecionar uma proposta do sumário
  const onClickProposta = (index) => {
    setIndexProposta(index);
    setDadosProposta(listaProposta[index]);
    setProposta(true);
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

  // Função para voltar ao sumário da ata
  const voltarSumario = () => {
    setBotaoProximo(true);
    setIndexProposta(-1);
    setProposta(false);
  };

  const [fecharMenu, setFecharMenu] = useState(true);

  // Função para fechar os botões da página
  const funcaoFecharMenu = () => {
    setFecharMenu(!fecharMenu);
  };

  // Feedback de ata criada com sucesso
  const feedbackAta = () => {
    navigate("/detalhes-ata", { state: { feedback: true } });
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

  const [girarIcon, setGirarIcon] = useState(false);

  const [aparecerSumir, setAparecerSumir] = useState(false);

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

  const [display, setDisplay] = useState("hidden");

  return (
    <FundoComHeader>
      <Box className="p-2">
        <Box className="flex w-full relative">
          <Caminho />
          <Box
            className=" absolute"
            sx={{ top: "10px", right: "20px", cursor: "pointer" }}
          >
            <Tooltip title="Baixar PDF">
              <SaveAltOutlinedIcon
                fontSize="large"
                className="delay-120 hover:scale-110 duration-300"
                sx={{ color: "icon.main" }}
              />
            </Tooltip>
          </Box>
        </Box>
        {/* Corpo da pauta */}
        <Box className="flex flex-col justify-center relative items-center mt-3">
          <Box
            className="flex flex-col gap-5 border rounded relative p-10 drop-shadow-lg"
            sx={{ width: "55rem" }}
          >
            {/* Informações do header da pauta */}
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
                Pauta
              </Typography>
              <Typography sx={informacoesAta}>
                {/* {props.numeroSequencial} */}
                Número Sequencial: 01
              </Typography>
              <Typography sx={informacoesAta}>
                {/* {props.data} */}
                Ano: 2022
              </Typography>
              <Divider sx={{ marginTop: "1%" }} />
            </Box>

            {/* Verificação para mostrar o sumário da pauta ou uma proposta */}
            {!proposta ? (
              // Mostrar o sumário com os títulos das propostas
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
                  {/* Lista utilizada para mostrar os títulos no sumário */}
                  {listaProposta.map((proposta, index) => {
                    return (
                      <Typography
                        fontSize={FontConfig.big}
                        sx={tituloProposta}
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
                    Proposta {indexProposta}
                  </Typography>
                  <DeleteIcon
                    sx={{
                      position: "absolute",
                      left: "90%",
                      width: "40px",
                      height: "40px",
                      color: "primary.main",
                      cursor: "pointer",
                    }}
                  ></DeleteIcon>
                </Box>
                <PropostaDeAta
                  dadosProposta={dadosProposta}
                  propostaPauta={false}
                />
              </Box>
            )}
          </Box>
        </Box>

        {/* Botões da pauta (voltar, próximo...) */}
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
            <Tooltip title="Criar Ata">
              <Box
                onClick={feedbackAta}
                className="flex justify-center items-center w-12 h-12 rounded-full cursor-pointer delay-120 hover:scale-110 duration-300"
                sx={{
                  backgroundColor: "primary.main",
                  fontSize: FontConfig.default,
                  marginLeft: "1rem",
                  color: "#FFFF",
                }}
              >
                <PostAddOutlinedIcon fontSize="large" />
              </Box>
            </Tooltip>
          </Box>
        </Box>
      </Box>
    </FundoComHeader>
  );
};

export default DetalhesPauta;
