import React, { useState, useContext, useEffect } from "react";

import { Box, Typography, TextareaAutosize } from "@mui/material";

import FontConfig from "../../service/FontConfig";

import BeneficiosDetalheDemanda from "../../components/BeneficiosDetalheDemanda/BeneficiosDetalheDemanda";

import { useLocation } from "react-router-dom";

import MenuItem from '@mui/material/MenuItem';
import FormHelperText from '@mui/material/FormHelperText';
import FormControl from '@mui/material/FormControl';
import Select, { SelectChangeEvent } from '@mui/material/Select';

const PropostaDeAta = (props) => {

    // Variáveis de estilo para usar no componente

    const textoConteudo = {
        textAlign: "justify",
        color: "text.secondary",
        marginLeft: "30px"
    };

    // Lógica dos dados da proposta

    const location = useLocation();

    const dados = props.dadosProposta;

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

    const [tituloDemanda, setTituloDemanda] = useState(dados.titulo);
    const [problema, setProblema] = useState(dados.problema);
    const [proposta, setProposta] = useState(dados.proposta);
    const [frequencia, setFrequencia] = useState(dados.frequencia);
    const [beneficios, setBeneficios] = useState(null);

    // useEffect(() => {
    //     location.state.beneficios.map((beneficio) => {
    //         beneficio.visible = true;
    //     });
    //     console.log(location.state)
    //     setDados(location.state);
    // }, []);

    // select do parecer da comissao

    const [parecer, setParecer] = useState('');
    const [parecerComissao, setParecerComissao] = useState(false);

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
                    Código PPM:
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
                    Título da Proposta:
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
                    Responsável Negócio:
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
                    Problema:
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
                    Proposta:
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
                    Benefícios:
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
                    Frequência de Uso:
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
                    Custos:
                </Typography>
                <Typography fontSize={FontConfig.medium} sx={textoConteudo}>
                    Custos aqui
                </Typography>
            </Box>

            <Box sx={{ marginTop: "2%" }}>
                <Typography
                    fontSize={FontConfig.veryBig}
                    fontWeight="600"
                    color="text.primary"
                >
                    Período de Execução:
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
                    Payback Simples:
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
                    Link do Jira:
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
                    Anexos:
                </Typography>
                <Typography fontSize={FontConfig.medium} sx={textoConteudo}>
                    Anexos
                </Typography>
            </Box>

            <Box sx={{ marginTop: "2%", display: "flex", flexDirection: "row", alignItems: "center" }}>
                <Typography
                    fontSize={FontConfig.veryBig}
                    fontWeight="600"
                    color="text.primary"
                >
                    Parecer Comissão:
                </Typography>
                <FormControl sx={{ width: "12rem", marginLeft: "2%" }}>
                    <Select
                        value={parecer}
                        onChange={mudarParecer}
                        displayEmpty
                        inputProps={{ 'aria-label': 'Without label' }}
                    >
                        <MenuItem value="">
                            Aprovado
                        </MenuItem>
                        <MenuItem value={1}>Reprovado</MenuItem>
                        <MenuItem value={2}>Mais Informações</MenuItem>
                        <MenuItem value={3}>Business Case</MenuItem>
                    </Select>
                </FormControl>
            </Box>

            {parecerComissao ?
                <Box sx={{ marginTop: "2%", display: "flex", flexDirection: "row", alignItems: "center" }}>
                    <TextareaAutosize
                        placeholder="Escreva a nova informação..."
                        style={{ width: '70%', height: '8rem', overflow: 'auto', resize: 'none', textAlign: 'justify', padding: '3%', background: 'transparent', border: 'solid 1px', borderRadius: '5px' }}
                    />
                </Box>
                :
                <></>
            }
        </Box>
    );
}

export default PropostaDeAta;