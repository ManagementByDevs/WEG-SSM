import { React, useState } from 'react';

import { Box } from '@mui/system';

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
    };

    return (
        <Box sx={containerGeral}>
            Container da proposta aqui
        </Box>
    );
};

export default ContainerProposta;