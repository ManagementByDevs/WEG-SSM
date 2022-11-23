import React, { useState } from "react";

import { Modal, Typography, Box, Button } from '@mui/material';

import Backdrop from '@mui/material/Backdrop';
import Fade from '@mui/material/Fade';
import FontConfig from '../../service/FontConfig'

import HelpOutlineOutlinedIcon from '@mui/icons-material/HelpOutlineOutlined';

const ModalConfirmacao = (props) => {

    const style = {
        position: 'absolute',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        width: 550,
        height: 350,
        bgcolor: 'background.paper',
        border: '2px solid #000',
        boxShadow: 24,
        p: 4,
        display: 'flex',
        justify: 'center',
        alignItems: 'center',
        flexDirection: 'column',
    };

    const mensagemModal = (tipoMensagem) => {
        switch (tipoMensagem) {
            case "descartarRascunho":
                return "Deseja descartar o rascunho?";
            case "sairCriacao":
                return "Deseja sair da criação da demanda?";
            case "enviarDemanda":
                return "Deseja enviar a demanda?";
            case "sairSemSalvar":
                return "Deseja sair sem salvar?";
            case "descartarProposta":
                return "Deseja descartar a proposta?";
        }
    };

    const mensagemBotao = (mensagemBotao) => {
        switch (mensagemBotao) {
            case "sim":
                return "Sim"
            case "enviar":
                return "Enviar"
        }
    };

    const [open, setOpen] = useState(false);
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
                       <HelpOutlineOutlinedIcon sx = {{fontSize: '100px'}}/>
                        <Typography id="transition-modal-description" sx={{ mt: 2 }}>
                            {mensagemModal("sairCriacao")}
                        </Typography>
                        <Box>
                            <Button>Cancelar</Button>
                            <Button variant="contained" disableElevation color="secondary">{mensagemBotao(props.titulo)}</Button>
                        </Box>
                    </Box>
                </Fade>
            </Modal>
        </div>
    );
};

export default ModalConfirmacao;