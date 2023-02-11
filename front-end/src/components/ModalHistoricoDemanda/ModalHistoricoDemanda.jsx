import React, { useState, useContext } from "react";

import { Modal, Typography, Box, Divider } from '@mui/material';

import Backdrop from '@mui/material/Backdrop';
import Fade from '@mui/material/Fade';

import CloseIcon from '@mui/icons-material/Close';

import ContainerHistorico from "../ContainerHistorico/ContainerHistorico";

import FontContext from "../../service/FontContext";

const ModalHistoricoDemanda = (props) => {
    // Context para alterar o tamanho da fonte
    const { FontConfig, setFontConfig } = useContext(FontContext);

    // Variáveis de estilo para o modal
    const styleModal = {
        position: 'absolute',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        width: 550,
        height: 480,
        bgcolor: 'background.paper',
        borderRadius: '5px',
        borderTop: '10px solid #00579D',
        boxShadow: 24,
        p: 4,
        display: 'flex',
        justifyContent: 'space-evenly',
        alignItems: 'center',
        flexDirection: 'column',
    };

    const styleContainerHistorico = {
        display: 'flex',
        alignItems: 'center',
        flexDirection: 'column',
        overflow: 'auto',
        width: '100%',
        height: '80%'
    }

    // variáveis para abrir o modal a partir de outra tela
    let open = false;
    open = props.open;
    const setOpen = props.setOpen;

    // useState para abrir e fechar o modal
    const handleClose = () => setOpen(false);

    return (
        <Modal
            open={open}
            onClose={handleClose}
            closeAfterTransition
        >
            <Fade in={open}>
                <Box sx={styleModal}>
                    <CloseIcon onClick={handleClose} sx={{ position: 'absolute', left: '90%', top: '3%', cursor: 'pointer' }} />
                    <Typography fontWeight={650} color={'primary.main'} fontSize={FontConfig.smallTitle}>
                        Histórico
                    </Typography>
                    <Divider sx={{ width: '60%', borderColor: 'tertiary.main' }} />
                    <Box sx={styleContainerHistorico}>
                        <ContainerHistorico autorHistorico={"Autor 1"} dataHistorico={"30/11/2022"} tituloHistorico={"Demanda Cancelada"} versaoHistorico={"V1"} />
                        <ContainerHistorico autorHistorico={"Autor 1"} dataHistorico={"30/11/2022"} tituloHistorico={"Demanda Cancelada"} versaoHistorico={"V1"} />
                        <ContainerHistorico autorHistorico={"Autor 1"} dataHistorico={"30/11/2022"} tituloHistorico={"Demanda Cancelada"} versaoHistorico={"V1"} />
                        <ContainerHistorico autorHistorico={"Autor 1"} dataHistorico={"30/11/2022"} tituloHistorico={"Demanda Cancelada"} versaoHistorico={"V1"} />
                        <ContainerHistorico autorHistorico={"Autor 1"} dataHistorico={"30/11/2022"} tituloHistorico={"Demanda Cancelada"} versaoHistorico={"V1"} />
                    </Box>
                </Box>
            </Fade>
        </Modal>
    );
}

export default ModalHistoricoDemanda;