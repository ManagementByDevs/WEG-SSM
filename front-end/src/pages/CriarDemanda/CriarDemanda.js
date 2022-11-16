import React from 'react'

import { Box } from '@mui/material';

import Header from '../../components/Header/Header';
import Caminho from '../../components/Caminho/Caminho';
import BarraProgressao from '../../components/BarraProgressao/BarraProgressao';

const CriarDemanda = () => {
    return (
        <>
            <Header />
            <Box className='p-2'>
                <Caminho />
                <Box className='w-full flex justify-center'>
                    <Box className='w-5/6'>
                        <BarraProgressao steps={['Dados', 'Benefícios', 'Anexos']}/>
                    </Box>
                </Box>
            </Box>
        </>
    )
}

export default CriarDemanda