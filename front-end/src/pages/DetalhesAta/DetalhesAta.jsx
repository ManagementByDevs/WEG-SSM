import { React, useState, useEffect, useContext } from "react";
import { useNavigate, useLocation } from "react-router-dom";

import { Box, Typography, Button, Divider } from "@mui/material";

import SaveAltOutlinedIcon from "@mui/icons-material/SaveAltOutlined";
import OtherHousesIcon from '@mui/icons-material/OtherHouses';
import DensitySmallIcon from '@mui/icons-material/DensitySmall';

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

    // Variáveis de estilo para utilizar no componente 
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
        marginTop: '1%'
    };

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
                    memoriaCalculo:
                        "memória de cálculo",
                    visible: true,
                },
            ],
            periodoExecucao: "07/12/2022 à 08/12/2022",
            paybackSimples: "10 meses",
            ppm: "1234",
            linkJira: "https://www.jira.com",
            responsavelNegocio: "Matheus Franzener Hohmann",
            area: "Weg Digital"
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
                    memoriaCalculo:
                        "memória de cálculo",
                    visible: true,
                },
            ],
            periodoExecucao: "07/12/2022 à 08/12/2022",
            paybackSimples: "10 meses",
            ppm: "1234",
            linkJira: "https://www.jira.com",
            responsavelNegocio: "Matheus Franzener Hohmann",
            area: "Weg Digital"
        }
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
    const [minimizar, setMinimizar] = useState(false);

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
        setDadosProposta(listaProposta[index])
        setProposta(true)
    }

    // Função para voltar para uma proposta
    const voltar = () => {
        if (indexProposta == 0) {
            setProposta(false);
        } else {
            setBotaoProximo(true);
            setDadosProposta(listaProposta[indexProposta - 1])
            setIndexProposta(indexProposta - 1)
        }
    }

    // Função para passar para a próxima proposta
    const proximo = () => {
        if (indexProposta == listaProposta.length - 1) {
            setBotaoProximo(false);
        } else {
            setProposta(true);
            setDadosProposta(listaProposta[indexProposta + 1]);
            setIndexProposta(indexProposta + 1);
        }
    }

    // Função de criar ata e enviar feedback
    const criarAta = () => {
        navigate('/', { state: { feedback: true } })
    }

    // Função para minimizar os botões da página
    const minimizarBotoes = () => {
        setMinimizar(!minimizar);
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
                <Feedback open={feedbackAta} handleClose={() => {
                    setFeedbackAta(false);
                }} status={"sucesso"} mensagem={"Ata criada com sucesso!"} />

                {/* container geral da tela */}
                <Box className="flex flex-col justify-center relative items-center mt-3">
                    {/* container da folha */}
                    <Box className="flex flex-col gap-5 border rounded relative p-10 drop-shadow-lg" sx={{ width: "55rem" }}>
                        <Box className="flex justify-center flex-col" >
                            <Typography
                                fontSize={FontConfig.title}
                                sx={{
                                    fontWeight: "600",
                                    cursor: "default",
                                    inlineSize: "800px",
                                    overflowWrap: "break-word",
                                    textAlign: "center",
                                    color: "primary.main"
                                }}
                            >
                                Ata
                            </Typography>
                            <Typography sx={informacoesAta}>
                                {/* {props.numeroSequencial} */}
                                Número Sequencial: 01/2022
                            </Typography>
                            <Typography sx={informacoesAta}>
                                {/* {props.data} */}
                                Data: 06/12/2022
                            </Typography>
                            <Typography sx={informacoesAta}>
                                {/* {props.inicio} */}
                                Início: 14:30 Horas
                            </Typography>
                            <Typography sx={informacoesAta}>
                                {/* {props.fim} */}
                                Fim: 15:30 Horas
                            </Typography>
                            <Divider sx={{ marginTop: '1%' }} />
                        </Box>

                        {/* Verificação para mostrar o sumário ou mostrar o componente da proposta */}
                        {!proposta ?

                            <Box>
                                <Typography
                                    fontSize={FontConfig.title}
                                    sx={{
                                        fontWeight: "600",
                                        cursor: "default",
                                        inlineSize: "800px",
                                        overflowWrap: "break-word",
                                        textAlign: "center"
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
                                    }}>


                                    {listaProposta.map((proposta, index) => {
                                        return (
                                            <Typography
                                                fontSize={FontConfig.big}
                                                sx={tituloProposta}
                                                key={index}
                                                setIndexTitulo={index}
                                                onClick={() => onClickProposta(index)}>
                                                {index} - {proposta.titulo}
                                            </Typography>
                                        )
                                    })}
                                </Box>
                            </Box>
                            :
                            <Box>
                                <Typography sx={{ marginBottom: '2%', display: 'flex', justifyContent: 'center' }} fontSize={FontConfig.title} fontWeight={650}>Proposta  {indexProposta}</Typography>
                                <PropostaDeAta dadosProposta={dadosProposta} propostaPauta={false} parecerDG={true} />
                            </Box>
                        }
                    </Box>

                    {/* Botões de navegação entre as propostas da ata */}
                    <Box className="flex fixed justify-end"
                        sx={{ width: "25rem", bottom: "20px", right: "20px" }}>
                        <Box className="flex justify-around w-full">
                            {!minimizar &&
                                <Box className="flex justify-around w-full">
                                    <Button
                                        sx={{
                                            backgroundColor: "primary.main",
                                            color: "text.white",
                                            fontSize: FontConfig.default,
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
                                        }}
                                        variant="contained"
                                        onClick={proximo}
                                    >
                                        <Typography>
                                            Próximo
                                        </Typography>
                                    </Button>

                                    <Button
                                        sx={{
                                            backgroundColor: "primary.main",
                                            color: "text.white",
                                            fontSize: FontConfig.default,
                                        }}
                                        variant="contained"
                                        onClick={criarAta}
                                    >
                                        Publicar
                                    </Button>

                                </Box>
                            }

                            {/* Minimizar botões da tela */}
                            {!minimizar ?
                                <Box>
                                    <Button
                                        sx={{
                                            backgroundColor: "primary.main",
                                            color: "text.white",
                                            fontSize: FontConfig.default,
                                        }}
                                        variant="contained"
                                        onClick={minimizarBotoes}
                                    >
                                        <DensitySmallIcon></DensitySmallIcon>
                                    </Button>
                                </Box>
                                :
                                <Box sx={{
                                    marginLeft: '84%'
                                }}>
                                    <Button
                                        sx={{
                                            backgroundColor: "primary.main",
                                            color: "text.white",
                                            fontSize: FontConfig.default,
                                        }}
                                        variant="contained"
                                        onClick={minimizarBotoes}
                                    >
                                        <DensitySmallIcon></DensitySmallIcon>
                                    </Button>
                                </Box>
                            }
                        </Box>
                    </Box>
                </Box>
            </Box>
        </FundoComHeader>
    );
}

export default DetalhesAta;
