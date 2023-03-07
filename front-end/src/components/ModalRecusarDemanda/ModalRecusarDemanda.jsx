import React, { useContext } from "react";
import { Modal, Typography, Box, Fade, TextareaAutosize, Button } from '@mui/material';

import CloseIcon from '@mui/icons-material/Close';

import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";

/** Modal de recusar demanda na etapa de aprovação inicial (analista e gerente) */
const ModalRecusarDemanda = (props) => {

    // Context para alterar a linguagem do sistema
    const { texts, setTexts } = useContext(TextLanguageContext);

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

    // props para abrir o modal através de outra tela
    let open = false;
    open = props.open;
    const setOpen = props.setOpen;

    // useState para abrir e fechar o modal
    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    // Função para alterar o texto do motivo
    const alterarTexto = (e) => {
        props.setMotivo(e.target.value);
    }

    return (
        <Modal
            open={open}
            onClose={handleClose}
            closeAfterTransition
        >
            <Fade in={open}>
                <Box className="absolute top-2/4 left-2/4 flex flex-col justify-between items-center"
                    sx={{ transform: 'translate(-50%, -50%)', width: 480, height: 350, bgcolor: 'background.paper', borderRadius: '5px', borderTop: '10px solid #00579D', boxShadow: 24, p: 4 }}
                >
                    <CloseIcon onClick={handleClose} sx={{ position: 'absolute', left: '93%', top: '3%', cursor: 'pointer' }} />
                    <Typography fontSize={FontConfig.veryBig}>
                        {texts.modalRecusarDemanda.motivoDaRecusa}
                    </Typography>

                    {/* Textarea para escrita do motivo da recusa */}
                    <TextareaAutosize
                        style={{ width: "90%", height: "70%", resize: "none" }}
                        value={props.motivo}
                        fontSize={FontConfig.medium}
                        onChange={(e) => { alterarTexto(e, "problema") }}
                        className="flex outline-none border-solid border px-1 py-1.5 drop-shadow-sm rounded text-center text-justify"
                        placeholder={texts.modalRecusarDemanda.informeMotivo}
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
                            {texts.modalRecusarDemanda.enviar}
                        </Button>
                    </Box>
                </Box>
            </Fade>
        </Modal>
    );
}

export default ModalRecusarDemanda;