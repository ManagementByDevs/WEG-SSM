import React, { useEffect } from 'react'

import { Box } from '@mui/material';

import FundoComHeader from '../../components/FundoComHeader/FundoComHeader';
import Caminho from '../../components/Caminho/Caminho';
import BarraProgressaoDemanda from '../../components/BarraProgressaoDemanda/BarraProgressaoDemanda';

const EditarEscopo = () => {
    return (
        <FundoComHeader>
            <Box className='p-2'>
                <Caminho />
                <Box className='w-full flex justify-center'>
                    <Box className='w-5/6'>
                        <BarraProgressaoDemanda steps={['Dados', 'Benefícios', 'Anexos']} />
                    </Box>
                </Box>
            </Box>
        </FundoComHeader>
    )
}

export default EditarEscopo;