import React from 'react'
import { useLocation, useNavigate } from 'react-router-dom';

import { Typography, Box } from '@mui/material';
import HomeOutlinedIcon from '@mui/icons-material/HomeOutlined';
import ArrowForwardIosOutlinedIcon from '@mui/icons-material/ArrowForwardIosOutlined';

import FontConfig from '../../service/FontConfig';

const Caminho = () => {
    const navigate = useNavigate();

    const caminhoURL = useLocation().pathname;

    const listaCaminho = caminhoURL.split('/');

    const getPathName = (item) => {
        item = item.charAt(0).toUpperCase() + item.slice(1);
        return item.replaceAll('-' , ' ') 
    }

    return (
        <Box className='flex items-center gap-x-1' color="link.main">
            <HomeOutlinedIcon className='cursor-pointer' sx={{ fontSize: '32px' }} onClick={() => { navigate("/") }} />
            <ArrowForwardIosOutlinedIcon sx={{ fontSize: '20px' }} />
            {listaCaminho.map((item, index) => {
                if (item !== "") {
                    return (
                        <Typography key={index} className='cursor-pointer' fontSize={FontConfig.default} sx={{ fontWeight: 500 }} onClick={() => { navigate("/" + item) }}>
                            {getPathName(item)}
                        </Typography>
                    )
                }
            })}
        </Box>
    )
}

export default Caminho