import React, { useState } from "react";

import { Modal, Typography, Box, Fade, TextareaAutosize, Button } from '@mui/material';

import Backdrop from '@mui/material/Backdrop';

import FontConfig from '../../service/FontConfig';
import CloseIcon from '@mui/icons-material/Close';

const ModalInformarMotivo = (props) => {

    // variáveis de estilo para o modal

    const style = {
        position: 'absolute',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        width: 500,
        height: 380,
        bgcolor: 'background.paper',
        borderRadius: '5px',
        borderTop: '10px solid #00579D',
        boxShadow: 24,
        p: 4,
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'center',
        flexDirection: 'column'
    };

    const styleMensagem = {
        display: 'flex',
        border: '1px solid',
        borderColor: 'divider.main',
        borderRadius: '5px',
        marginTop: '3%',
        width: '100%',
        height: '100%'
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
                    <Typography fontWeight={650} color={'primary.main'} fontSize={FontConfig.smallTitle}>
                        Informar Motivo
                    </Typography>
                    <Box sx={styleMensagem}>
                        {/* text area para informar o motivo */}
                        <TextareaAutosize
                            placeholder="Informe o motivo..."
                            style={{ width: '100%', height: '100%', overflow: 'auto', resize: 'none', textAlign: 'justify', padding: '3%', background: 'transparent' }}
                        />
                    </Box>
                    <Button onClick={handleClose} variant="contained" disableElevation color="primary" sx={{ marginTop: '2%', width: '8rem', height: '3rem', fontSize: FontConfig.normal }}>Confirmar</Button>
                </Box>
            </Fade>
        </Modal>
    );
}

export default ModalInformarMotivo;