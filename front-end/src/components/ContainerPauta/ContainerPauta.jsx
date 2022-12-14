import { React, useState, useEffect } from 'react';

import { Box } from '@mui/system';
import { Typography, Paper } from '@mui/material';

const ContainerPauta = (props) => {

    const containerGeral = {
        width: '90%',
        height: '5.5rem',
        border: '1px solid',
        borderLeft: 'solid 6px',
        borderColor: 'primary.main',
        borderRadius: '5px',
        p: 4,
        margin: '1%',
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        flexDirection: 'column',
        cursor: 'pointer',
    };

    const containerSelecionado = {
        width: '90%',
        height: '5.5rem',
        border: '1px solid',
        borderLeft: 'solid 6px',
        borderColor: 'primary.main',
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

    // verificações para selecionar apenas uma pauta conforme seu index

    const [pautaSelecionada, setPautaSelecionada] = useState(false);

    const selecionarPauta = () => {
        if (props.indexPautaSelecionada == props.index) {
            props.setIndexPautaSelecionada(null);
        } else {
            props.setIndexPautaSelecionada(props.index);
        }
    };

    useEffect(() => {
        if (props.indexPautaSelecionada == props.index) {
            setPautaSelecionada(!pautaSelecionada)
        } else {
            setPautaSelecionada(false)
        }
    }, [props.indexPautaSelecionada])

    return (
        <>
            {/* Verificação para ver se a pauta foi selecionada ou não */}
            {!pautaSelecionada ?
                <Paper sx={containerGeral} onClick={selecionarPauta} >
                    <Box sx={parteCima}>
                        <Typography>
                            Propostas:
                        </Typography>
                        <Typography>
                            {/* props.dataAta */}
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
                            {/* props.dataAta */}
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

export default ContainerPauta;