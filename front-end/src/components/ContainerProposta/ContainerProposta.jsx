import { React, useState } from 'react';

import { Box } from '@mui/system';
import { Typography, Paper } from '@mui/material';

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
        cursor: 'pointer'
    };

    const parteCima = {
        width: '100%',
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'center',
    };

    const parteBaixo = {
        width: '100%',
        display: "grid",
        color: 'primary.main',
        marginTop: "2%",
        gap: "1rem",
        gridTemplateColumns: "repeat(auto-fit, minmax(15%, 1fr))",
    };

    const tituloProposta = {
        overflow: "hidden",
        whiteSpace: "nowrap",
        textOverflow: "ellipsis",
    };

    const containerSelecionado = {
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
        cursor: 'pointer',
        backgroundColor: 'rgba(196, 196, 196, 0.7)'
    };

    const [pautaSelecionada, setPautaSelecionada] = useState(false);

    const selecionarPauta = () => {
        setPautaSelecionada(!pautaSelecionada);
    };

    return (
        <>
            {!pautaSelecionada ?
                <Paper sx={containerGeral} onClick={selecionarPauta} >
                    <Box sx={parteCima}>
                        <Typography>
                            Propostas:
                        </Typography>
                        <Typography>
                            01/2022
                        </Typography>
                    </Box>
                    <Box sx={parteBaixo}>
                        <Typography sx={tituloProposta}>
                            Titulo da primeira proposta
                        </Typography>
                        <Typography sx={tituloProposta}>
                            Titulo da segunda proposta
                        </Typography>
                    </Box>
                </Paper >
                :
                <Paper sx={containerSelecionado} onClick={selecionarPauta} >
                    <Box sx={parteCima}>
                        <Typography>
                            Propostas:
                        </Typography>
                        <Typography>
                            01/2022
                        </Typography>
                    </Box>
                    <Box sx={parteBaixo}>
                        <Typography sx={tituloProposta}>
                            Titulo da primeira proposta
                        </Typography>
                        <Typography sx={tituloProposta}>
                            Titulo da segunda proposta
                        </Typography>
                    </Box>
                </Paper >
            }
        </>

    );
};

export default ContainerProposta;