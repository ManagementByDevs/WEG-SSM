import React, { useState } from "react";

import {
    Box,
    Typography,
    Button,
    Divider,
} from "@mui/material";

import SaveAltOutlinedIcon from "@mui/icons-material/SaveAltOutlined";
import OtherHousesIcon from '@mui/icons-material/OtherHouses';

import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Caminho from "../../components/Caminho/Caminho";
import PropostaDeAta from "../../components/PropostaDeAta/PropostaDeAta";

import FontConfig from "../../service/FontConfig";
import { useEffect } from "react";

const DetalhesAta = (props) => {

    // Varuáveis de estilo para usar no componente 

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

    // useState para mostar uma proposta

    const [proposta, setProposta] = useState(false);
    const [dadosProposta, setDadosProposta] = useState(listaProposta[0]);
    const [indexProposta, setIndexProposta] = useState(-1)

    const mostarProposta = () => {
        setProposta(true);
    };

    const voltarSumario = () => {
        setIndexProposta(-1);
        setProposta(false);
    };

    const onClickProposta = (index) => {
        setIndexProposta(index);
        setDadosProposta(listaProposta[index])
        setProposta(true)
    }

    const voltar = () => {
        if (indexProposta == 0) {
            setProposta(false);
        } else {
            console.log("entrou")
            setDadosProposta(listaProposta[indexProposta - 1])
            setIndexProposta(indexProposta - 1)
        }
    }

    const proximo = () => {
        if (indexProposta == listaProposta.length - 1) {

        } else {
            setProposta(true);
            setDadosProposta(listaProposta[indexProposta + 1]);
            setIndexProposta(indexProposta + 1);
        }
    }

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
                                                onClick={() => onClickProposta(index)}>
                                                {proposta.titulo}
                                            </Typography>
                                        )
                                    })}
                                </Box>
                            </Box>
                            :
                            <PropostaDeAta dadosProposta={dadosProposta} />
                        }
                    </Box>

                    {/* botões de navegação entre as proposta da ata */}
                    <Box className="flex fixed justify-end"
                        sx={{ width: "20rem", bottom: "20px", right: "20px" }}>
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
                                Próximo
                            </Button>
                        </Box>
                    </Box>
                </Box>
            </Box>
        </FundoComHeader>
    );
}

export default DetalhesAta;


