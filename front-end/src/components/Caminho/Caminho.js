import React from 'react'
import { useNavigate } from 'react-router-dom';

import { Typography, Box } from '@mui/material';
import HomeOutlinedIcon from '@mui/icons-material/HomeOutlined';
import ArrowForwardIosOutlinedIcon from '@mui/icons-material/ArrowForwardIosOutlined';

import FontConfig from '../../service/FontConfig';

const Caminho = () => {
    const navigate = useNavigate();
    return (
        <Box className='flex items-center gap-x-1' color="link.main">
            <HomeOutlinedIcon className='cursor-pointer' sx={{ fontSize: '32px' }} onClick={() => { navigate("/") }} />
            <ArrowForwardIosOutlinedIcon sx={{ fontSize: '20px' }} />
            <Typography className='cursor-pointer' fontSize={FontConfig.default} sx={{ fontWeight: 500 }} onClick={() => { navigate("/criar-demanda") }}>Criar Demanda</Typography>
        </Box>
    )
}

export default Caminho