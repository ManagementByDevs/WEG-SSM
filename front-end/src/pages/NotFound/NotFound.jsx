import React, {useContext} from 'react';

import { Typography, Box, Button } from '@mui/material';
import { useNavigate } from "react-router-dom";

import Error from '../../assets/Error.png';

import TextLanguageContext from '../../service/TextLanguageContext';
import FontContext from "../../service/FontContext";

const NotFound = (props) => {
    // useContext para alterar o idioma do sistema
    const { texts } = useContext(TextLanguageContext);

    // Context para alterar o tamanho da fonte
    const { FontConfig, setFontConfig } = useContext(FontContext);

    // Navigate utilizado para navegar para outra página
    let navigate = useNavigate();

    // Função para retornar a home
    const voltarPaginaPrincipal = () => {
        navigate('/')
    };

    return (
        <Box className='flex justify-center items-center w-screen h-screen'>
            {/* Componente com informações da página */}
            <Box className='flex justify-evenly flex-col items-center h-1/2'>

                <img src={Error} />

                <Typography fontSize={FontConfig.title} color={'primary.main'} fontWeight={650}>
                   {texts.notFound.paginaNaoEncontrada}
                </Typography>

                <Typography fontSize={FontConfig.veryBig} fontWeight={500}>
                    {texts.notFound.paginaNaoEncontrada}
                </Typography>

                <Typography fontSize={FontConfig.veryBig} fontWeight={500}>
                    {texts.notFound.porfavorVolteParaPaginaPrincipal}
                </Typography>

                <Button className='w-32 rounded-sm p-1' fontSize={FontConfig.big} onClick={voltarPaginaPrincipal} variant="contained">
                    {texts.notFound.voltar}
                </Button>
            </Box>
        </Box>
    );
};

export default NotFound;