import { useState, useEffect, React } from "react";

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
import DeleteIcon from '@mui/icons-material/Delete';

const DetalhesPauta = (props) => {

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

    const [proposta, setProposta] = useState(false);
    const [botaoProximo, setBotaoProximo] = useState(true);
    const [dadosProposta, setDadosProposta] = useState(listaProposta[0]);
    const [indexProposta, setIndexProposta] = useState(-1);
    const [indexTitulo, setIndexTitulo] = useState(0);

    const onClickProposta = (index) => {
        setIndexProposta(index);
        setDadosProposta(listaProposta[index])
        setProposta(true)
    }

    const voltar = () => {
        if (indexProposta == 0) {
            setProposta(false);
        } else {
            setBotaoProximo(true);
            setDadosProposta(listaProposta[indexProposta - 1])
            setIndexProposta(indexProposta - 1)
        }
    };

    const voltarSumario = () => {
        setBotaoProximo(true);
        setIndexProposta(-1);
        setProposta(false);
    };

    const proximo = () => {
        if (indexProposta == listaProposta.length - 1) {
            setBotaoProximo(false);
        } else {
            setProposta(true);
            setDadosProposta(listaProposta[indexProposta + 1]);
            setIndexProposta(indexProposta + 1);
        }
    };

    return (
        <FundoComHeader>
            <Box className="p-2">
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

                <Box className="flex flex-col justify-center relative items-center mt-3">
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
                            <Divider sx={{ marginTop: '1%' }} />
                        </Box>

                        {
                            !proposta ?
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
                                    <Box sx={{ display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
                                        <Typography sx={{ marginBottom: '2%', display: 'flex', justifyContent: 'center' }} fontSize={FontConfig.title} fontWeight={650}>Proposta  {indexProposta}</Typography>
                                        <DeleteIcon sx={{ position: 'absolute', left: '90%', width: '40px', height: '40px', color: 'primary.main', cursor: 'pointer' }}></DeleteIcon>
                                    </Box>
                                    <PropostaDeAta dadosProposta={dadosProposta} propostaPauta={false} />
                                </Box>
                        }

                    </Box>
                </Box>

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
                            {botaoProximo ?
                                <Typography>
                                    Próximo
                                </Typography>
                                :
                                <Typography>
                                    Criar Ata
                                </Typography>
                            }
                        </Button>
                    </Box>
                </Box>
            </Box>
        </FundoComHeader>
    );
};

export default DetalhesPauta;