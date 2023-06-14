import { Checkbox, Fade, FormControlLabel, FormGroup, Grid, Modal, Typography } from "@mui/material";
import { Box } from "@mui/system";
import React, { useContext } from "react";

import CloseIcon from "@mui/icons-material/Close";

import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";
import SpeechSynthesisContext from "../../service/SpeechSynthesisContext";

/** Modal de filtragem de atas, presente na aba da HomeGerencia */
const ModalFiltroAtas = (props) => {

    // Context para alterar a linguagem do sistema
    const { texts } = useContext(TextLanguageContext);

    // Context para alterar o tamanho da fonte
    const { FontConfig } = useContext(FontContext);

    /** Context para ler o texto da tela */
    const { lerTexto } = useContext(SpeechSynthesisContext);

    const mudarCheck1 = () => {
        if (props.filtro.apreciada) {
            props.setFiltro({ ...props.filtro, apreciada: false, naoApreciada: false });
        } else {
            props.setFiltro({ ...props.filtro, apreciada: true, naoApreciada: false });
        }
    }

    const mudarCheck2 = () => {
        if (props.filtro.naoApreciada) {
            props.setFiltro({ ...props.filtro, naoApreciada: false, apreciada: false });
        } else {
            props.setFiltro({ ...props.filtro, naoApreciada: true, apreciada: false });
        }
    }

    const mudarCheck3 = () => {
        if (props.filtro.publicada) {
            props.setFiltro({ ...props.filtro, publicada: false, naoPublicada: false });
        } else {
            props.setFiltro({ ...props.filtro, publicada: true, naoPublicada: false });
        }
    }

    const mudarCheck4 = () => {
        if (props.filtro.naoPublicada) {
            props.setFiltro({ ...props.filtro, naoPublicada: false, publicada: false });
        } else {
            props.setFiltro({ ...props.filtro, naoPublicada: true, publicada: false });
        }
    }

    return (
        <Modal
            open={true}
            onClose={props.fecharModal}
            BackdropProps={{ invisible: true }}
            sx={{
                display: "flex",
                alignItems: "center",
                justifyContent: "center",
                width: "10rem",
                position: "absolute",
                left: "35%",
                top: "2%",
            }}
        >
            <Fade in={true}>
                <Box
                    className="absolute flex justify-evenly items-center flex-col"
                    sx={{
                        top: "28%",
                        left: "37%",
                        transform: "translate(-50%, -50%)",
                        width: 340,
                        height: 160,
                        bgcolor: "background.paper",
                        borderRadius: "5px",
                        borderTop: "10px solid #00579D",
                        boxShadow: 24,
                        p: 2,
                    }}
                >
                    {/* Botão para fechar o modal */}
                    <CloseIcon
                        onClick={props.fecharModal}
                        sx={{
                            position: "absolute",
                            left: "90%",
                            top: "3%",
                            cursor: "pointer",
                        }}
                    />
                    <Box className="flex flex-col justify-center items-center w-full h-full">
                        <Box className="flex justify-between w-full h-2/5">
                            <FormControlLabel
                                checked={props.filtro?.apreciada}
                                onChange={mudarCheck1}
                                control={<Checkbox />}
                                label={texts.modalFiltroAtas.apreciada}
                            />
                            <FormControlLabel
                                checked={props.filtro?.naoApreciada}
                                onChange={mudarCheck2}
                                control={<Checkbox />}
                                label={texts.modalFiltroAtas.naoApreciada}
                            />
                        </Box>
                        <Box className="flex justify-between w-full h-2/5">
                            <FormControlLabel
                                checked={props.filtro?.publicada}
                                onChange={mudarCheck3}
                                control={<Checkbox />}
                                label={texts.modalFiltroAtas.publicada}
                            />
                            <FormControlLabel
                                checked={props.filtro?.naoPublicada}
                                onChange={mudarCheck4}
                                control={<Checkbox />}
                                label={texts.modalFiltroAtas.naoPublicada}
                            />
                        </Box>
                    </Box>
                </Box>
            </Fade>
        </Modal>
    );
}

export default ModalFiltroAtas;