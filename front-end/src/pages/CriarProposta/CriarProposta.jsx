import React from "react";

import { Box } from "@mui/material";

import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Caminho from "../../components/Caminho/Caminho";
import BarraProgressaoProposta from "../../components/BarraProgressaoProposta/BarraProgressaoProposta";

const CriarProposta = () => {

    return (
        <FundoComHeader>
            <Box className='p-2'>
                <Caminho />
                <Box className='w-full flex justify-center'>
                    <Box className='w-5/6'>
                        <BarraProgressaoProposta steps={['Proposta','Escopo', 'Custos', 'Gerais']} />
                    </Box>
                </Box>
            </Box>
        </FundoComHeader>
    )
}

export default CriarProposta;