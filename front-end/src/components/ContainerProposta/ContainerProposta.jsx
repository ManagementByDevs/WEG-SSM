import { React, useState } from 'react';

import { Box } from '@mui/system';
import { Typography } from '@mui/material';

const ContainerProposta = (props) => {
    
    const containerGeral = {
        width: '90%',
        height: '5rem',
        border: 'solid 1px black',
        borderRadius: '5px',
        p: 4,
        margin: '1%',
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        flexDirection: 'column',
    };

    const parteCima = {
        width: '100%',
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'center',
    };

    const parteBaixo = {
        width: '100%',

    };

    return (
        <Box sx={containerGeral}>
            <Box sx={parteCima}>
                <Typography>
                    Propostas: 
                </Typography>
                <Typography>
                    01/2022
                </Typography>
            </Box>
            <Box sx={parteBaixo}>
                {/* <Typography>
                    Dificuldade para o gerenciamento de demandas
                </Typography>
                <Typography>
                    Grande dificuldade para o gerencimento de demandas na weg
                </Typography> */}
            </Box>
        </Box>
    );
};

export default ContainerProposta;