import React, { useContext, useState, useEffect } from "react";
import { useLocation } from "react-router-dom";

import { Box } from "@mui/material";

import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Caminho from "../../components/Caminho/Caminho";
import BarraProgressaoProposta from "../../components/BarraProgressaoProposta/BarraProgressaoProposta";

import FontContext from "../../service/FontContext";

const CriarProposta = () => {
    // Context para alterar o tamanho da fonte
    const { FontConfig, setFontConfig } = useContext(FontContext);

    const location = useLocation();

    const [dados, setDados] = useState(location.state);

    useEffect(() => {
        setDados(location.state);
    }, [])

    return (
        <FundoComHeader>
            <Box className='p-2'>
                <Caminho />
                <Box className='w-full flex justify-center'>
                    <Box className='w-5/6'>
                        <BarraProgressaoProposta dados={dados} steps={['Proposta', 'Escopo', 'Custos', 'Gerais']} />
                    </Box>
                </Box>
            </Box>
        </FundoComHeader>
    )
}

export default CriarProposta;