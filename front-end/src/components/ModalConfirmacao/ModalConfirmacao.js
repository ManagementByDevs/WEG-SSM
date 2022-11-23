import React, { useState } from "react";

import { Modal, Typography, Box, Button } from '@mui/material';

import Backdrop from '@mui/material/Backdrop';
import Fade from '@mui/material/Fade';
import FontConfig from '../../service/FontConfig'

import ErrorOutlineIcon from '@mui/icons-material/ErrorOutline';

const ModalConfirmacao = (props) => {

    // Como chamar:
    // <ModalConfirmacao textoModal={"descartarRascunho"} textoBotao={"sim"}/>

    // Variáveis de estilo para o componente
    const style = {
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

    const estilo = {
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        marginTop: '6%',
    }

    // Funções para retornar um tipo de mensagem para o modal e o botão

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

    // useState para abrir e fechar o modal

    const [open, setOpen] = useState(true);
    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    return (
        <div>
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
                        <ErrorOutlineIcon sx={{ fontSize: '100px', color: 'primary.main' }} />
                        <Typography fontSize={FontConfig.veryBig} sx={{ mt: 2 }}>
                            {mensagemModal(props.textoModal)}
                        </Typography>
                        <Box sx={estilo}>
                            <Button onClick={handleClose} variant="container" disableElevation color="tertiary" sx={{ border: 'solid 1px', borderColor: 'tertiary.main', margin: '10px', width: '7.5rem', fontSize: FontConfig.big }}>Cancelar</Button>
                            <Button variant="contained" disableElevation color="primary" sx={{ margin: '10px', width: '7.5rem', fontSize: FontConfig.big }}>{mensagemBotao(props.textoBotao)}</Button>
                        </Box>
                    </Box>
                </Fade>
            </Modal>
        </div>
    );
};

export default ModalConfirmacao;