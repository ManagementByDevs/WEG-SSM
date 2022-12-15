import { React, useState } from 'react';

import { Typography, Box, Button } from '@mui/material';

import FontConfig from '../../service/FontConfig';

import ManageSearchOutlinedIcon from '@mui/icons-material/ManageSearchOutlined';
import HomeOutlinedIcon from '@mui/icons-material/HomeOutlined';

import Error from '../../assets/Error.png';
import { height } from '@mui/system';

const NotFound = (props) => {

    const containerPagina = {
        with: '100vw',
        height: '100vh',
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
    };

    const containerNotFound = {
        display: 'flex',
        height: '50%',
        justifyContent: 'space-evenly',
        alignItems: 'center',
        flexDirection: 'column',
    };

    const botaoVoltar = {
        width: '8rem',
        fontSize: FontConfig.big,
        borderRadius: '10px',
        p: 1
    };

    return (
        <Box sx={containerPagina}>
            <Box sx={containerNotFound}>
                
                <img src={Error} />

                <Typography fontSize={FontConfig.title} color={'primary.main'} fontWeight={650}>
                    Page Not Found
                </Typography>

                <Typography fontSize={FontConfig.veryBig} fontWeight={500}>
                    Desculpe, a página informada não foi encontrada!
                </Typography>

                <Typography fontSize={FontConfig.veryBig} fontWeight={500}>
                    Por favor, volte para a página principal
                </Typography>

                <Button sx={botaoVoltar} variant="contained">
                    Voltar
                </Button>
            </Box>
        </Box>
    );
};

export default NotFound;