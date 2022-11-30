import React, { useState } from "react";

import { Modal, Typography, Box, Button, Checkbox, FormGroup, FormControlLabel, Grid, Fade } from '@mui/material';

import Backdrop from '@mui/material/Backdrop';

import FontConfig from '../../service/FontConfig';
import CloseIcon from '@mui/icons-material/Close';

const ModalMotivoRecusa = (props) => {

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
        p: 2,
        bgcolor: 'rgba(217, 217, 217, 0.55)',
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
                            A demanda foi recusada porque não houve uma preocupação com tal fato de tal porque sim e também não houve uma coisa. Sim porque não poderia haver uma retomada do poder do povo.
                        </Typography>
                    </Box>
                </Box>
            </Fade>
        </Modal>
    );
}

export default ModalMotivoRecusa;