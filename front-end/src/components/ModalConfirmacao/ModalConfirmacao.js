import React, { useState } from "react";

import { Box } from '@mui/system';
import { Typography } from '@mui/material/';
import { Modal } from '@mui/material';

import Button from '@mui/material/Button';
import Backdrop from '@mui/material/Backdrop';
import Fade from '@mui/material/Fade';

const ModalConfirmacao = () => {

    const style = {
        position: 'absolute',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        width: 400,
        bgcolor: 'background.paper',
        border: '2px solid #000',
        boxShadow: 24,
        p: 4,
    };

    const mensagemModal = (tipoMensagem) => {
        switch (tipoMensagem) {
            case 1:
                return "Deseja descartar o rascunho?";
            case 2:
                return "Deseja sair da criação da demanda?";
            case 3:
                return "Deseja enviar a demanda?";
            case 4:
                return "Deseja sair sem salvar?";
            case 5:
                return "Deseja descartar a proposta?";
        }
    };

    const [open, setOpen] = React.useState(false);
    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    return (
        <div>
            <Button onClick={handleOpen}>Modal teste</Button>
            <Modal
                aria-labelledby="transition-modal-title"
                aria-describedby="transition-modal-description"
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
                        <Typography id="transition-modal-title" variant="h6" component="h2">
                            Modal Confirmação
                        </Typography>
                        <Typography id="transition-modal-description" sx={{ mt: 2 }}>
                            {mensagemModal(1)}
                        </Typography>
                    </Box>
                </Fade>
            </Modal>
        </div>
    );
};

export default ModalConfirmacao;