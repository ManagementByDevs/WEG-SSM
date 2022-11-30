import React, { useState } from "react";

import { Modal, Typography, Box } from '@mui/material';

import Backdrop from '@mui/material/Backdrop';
import Fade from '@mui/material/Fade';
import FontConfig from '../../service/FontConfig';

import CloseIcon from '@mui/icons-material/Close';

const ModalHistoricoDemanda = (props) => {

    // Variáveis de estilo para o modal

    const styleModal = {
        position: 'absolute',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        width: 450,
        height: 300,
        bgcolor: 'background.paper',
        borderRadius: '5px',
        borderTop: '10px solid #00579D',
        boxShadow: 24,
        p: 4,
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        flexDirection: 'column',
    };

    // useState para abrir e fechar o modal

    const [open, setOpen] = useState(true);
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
                <Box sx={styleModal}>
                    <CloseIcon onClick={handleClose} sx={{ position: 'absolute', left: '90%', top: '3%', cursor: 'pointer' }} />
                    <Typography fontSize={FontConfig.veryBig} sx={{ mt: 2 }}>
                        Histórico
                    </Typography>
                </Box>
            </Fade>
        </Modal>
    );
}

export default ModalHistoricoDemanda;