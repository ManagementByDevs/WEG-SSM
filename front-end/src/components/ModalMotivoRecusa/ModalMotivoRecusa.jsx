import React, { useState,useContext } from "react";

import { Modal, Typography, Box, Fade } from '@mui/material';

import Backdrop from '@mui/material/Backdrop';

import FontConfig from '../../service/FontConfig';
import CloseIcon from '@mui/icons-material/Close';

import FontContext from "../../service/FontContext";

const ModalMotivoRecusa = (props) => {
    // Context para alterar o tamanho da fonte
    const { FontConfig, setFontConfig } = useContext(FontContext);

    // Variável de estilo para o modal

    const style = {
        position: 'absolute',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        width: 480,
        height: 350,
        bgcolor: 'background.paper',
        borderRadius: '5px',
        borderTop: '10px solid #00579D',
        boxShadow: 24,
        p: 4,
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'center',
        flexDirection: 'column',
    };

    const styleMensagem = {
        marginTop: '5%',
        display: 'flex',
        textAlign: 'justify',
        border: '1px solid',
        borderColor: 'divider.main',
        borderRadius: '5px',
        p: 2,
        width: '100%',
        height: '100%',
    }

    // props para abrir o modal através de outra tela

    let open = false;
    open = props.open;
    const setOpen = props.setOpen;

    // useState para abrir e fechar o modal

    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    return (
        <Modal
            open={open}
            onClose={handleClose}
            closeAfterTransition
            BackdropComponent={Backdrop}
            BackdropProps={{
                timeout: 500,
            }}
        >
            <Fade in={open}>
                <Box sx={style}>
                    <CloseIcon onClick={handleClose} sx={{ position: 'absolute', left: '93%', top: '3%', cursor: 'pointer' }} />
                    <Typography fontSize={FontConfig.veryBig}>
                        Motivo da Recusa
                    </Typography>
                    <Box sx={styleMensagem}>
                        <Typography fontSize={FontConfig.normal}>
                            {props.motivoRecusa}
                        </Typography>
                    </Box>
                </Box>
            </Fade>
        </Modal>
    );
}

export default ModalMotivoRecusa;