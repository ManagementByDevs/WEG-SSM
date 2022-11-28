import { Box, Typography, Avatar } from '@mui/material'
import React from 'react'
import FontConfig from '../../service/FontConfig'

const Contato = (props) => {



    const tituloDemanda = 'Colocar pote de bala nas mesas de TI'

    return (
        <Box onClick={props.onClick} className="flex justify-evenly items-center rounded-lg border delay-120 hover:scale-105 duration-300" sx={{
            width: '90%', minWidth: '195px', minHeight: '8%', cursor: 'pointer',
            "&:hover": {
                backgroundColor: 'visualizado.false'
            }
        }} title={tituloDemanda}>
            <Box className="flex justify-content items-center">
                <Avatar sx={{ width: '3rem', height: '3rem' }} />
            </Box>
            <Box className="flex justify-content flex-col" sx={{ width: '70%' }}>
                <Typography fontSize={FontConfig.medium} fontWeight="600">Nome</Typography>
                <Typography fontSize={FontConfig.small} fontWeight="400" className="overflow-hidden truncate" sx={{ width: '100%' }}>Demanda: "{tituloDemanda}"</Typography>
            </Box>
        </Box>
    )
}



export default Contato