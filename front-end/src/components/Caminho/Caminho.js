import React from 'react'
import { useNavigate } from 'react-router-dom';

import { Typography, Box } from '@mui/material';
import HomeOutlinedIcon from '@mui/icons-material/HomeOutlined';
import ArrowForwardIosOutlinedIcon from '@mui/icons-material/ArrowForwardIosOutlined';

import FontConfig from '../../service/FontConfig';

const Caminho = () => {
    const navigate = useNavigate();
    return (
        <Box className='flex items-center gap-x-1'>
            <HomeOutlinedIcon className='cursor-pointer' sx={{ color: "primary.main", fontSize: '32px' }} onClick={() => { navigate("/") }} />
            <ArrowForwardIosOutlinedIcon sx={{ color: "primary.main", fontSize: '20px' }} />
            <Typography className='cursor-pointer' fontSize={FontConfig.default} color="primary" sx={{ fontWeight: 500 }} onClick={() => { navigate("/criar-demanda") }}>Criar Demanda</Typography>
        </Box>
    )
}

export default Caminho