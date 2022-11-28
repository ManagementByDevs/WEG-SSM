import { Box, Typography, Avatar } from '@mui/material'
import React from 'react'
import FontConfig from '../../service/FontConfig'

const Contato = () => {

    const tituloDemanda = 'Colocar pote de bala nas mesas de TI'

    return (
        <Box className="flex justify-evenly items-center rounded-lg border hover:bg-gray-600 delay-120 hover:scale-105 duration-300" sx={{ width: '90%', minHeight: '8%', cursor: 'pointer'}} title={tituloDemanda}>
            <Box className="flex justify-content items-center">
                <Avatar sx={{ width: '3rem', height: '3rem' }} />
            </Box>
            <Box className="flex justify-content flex-col" sx={{ width: '70%' }}>
                <Typography fontSize={FontConfig.medium} fontWeight="600">Nome</Typography>
                <Typography fontSize={FontConfig.small} fontWeight="400" className="overflow-hidden truncate w-52">Demanda: "{tituloDemanda}"</Typography>
            </Box>
        </Box>
    )
}



export default Contato