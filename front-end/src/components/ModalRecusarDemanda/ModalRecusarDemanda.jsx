import React, { useState } from "react";

import { Modal, Typography, Box, Fade, TextareaAutosize, Backdrop, Button } from '@mui/material';

import FontConfig from '../../service/FontConfig';
import CloseIcon from '@mui/icons-material/Close';

const ModalRecusarDemanda = (props) => {

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

    const alterarTexto = (e) => {
        props.setMotivo(e.target.value);
    }

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
                    <TextareaAutosize
                        style={{
                            width: "90%",
                            height: "70%",
                            resize: "none",
                        }}
                        value={props.motivo}
                        fontSize={FontConfig.medium}
                        onChange={(e) => {
                            alterarTexto(e, "problema");
                        }}
                        className="flex outline-none border-solid border px-1 py-1.5 drop-shadow-sm rounded text-center text-justify"
                        placeholder="Motivo da recusa..."
                    />
                    <Box className="flex justify-end" sx={{ width: "90%" }}>
                        <Button
                            sx={{
                                backgroundColor: "primary.main",
                                color: "text.white",
                                fontSize: FontConfig.default,
                            }}
                            variant="contained"
                            onClick={props.confirmRecusarDemanda}
                        > 
                            Enviar
                        </Button>
                    </Box>
                </Box>
            </Fade>
        </Modal>
    );
}

export default ModalRecusarDemanda;