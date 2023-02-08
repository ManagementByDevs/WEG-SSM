import React, {useContext} from 'react';

import { Typography, Box, Button } from '@mui/material';
import { useNavigate } from "react-router-dom";

import Error from '../../assets/Error.png';

import FontContext from "../../service/FontContext";

const NotFound = (props) => {
    // Context para alterar o tamanho da fonte
    const { FontConfig, setFontConfig } = useContext(FontContext);

    // Navigate utilizado para navegar para outra página
    let navigate = useNavigate();

    // Variáveis de estilo utilizadas no componente 
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

    // Função para retornar a home
    const voltarPaginaPrincipal = () => {
        navigate('/')
    };

    return (
        <Box sx={containerPagina}>
            {/* Componente com informações da página */}
            <Box sx={containerNotFound}>

                <img src={Error} />

                <Typography fontSize={FontConfig.title} color={'primary.main'} fontWeight={650}>
                    Page Not Found
                </Typography>

                <Typography fontSize={FontConfig.veryBig} fontWeight={500}>
                    Desculpe, a página informada não foi encontrada!
                </Typography>

                <Typography fontSize={FontConfig.veryBig} fontWeight={500}>
                    Por favor, volte para a página principal.
                </Typography>

                <Button sx={botaoVoltar} onClick={voltarPaginaPrincipal} variant="contained">
                    Voltar
                </Button>
            </Box>
        </Box>
    );
};

export default NotFound;