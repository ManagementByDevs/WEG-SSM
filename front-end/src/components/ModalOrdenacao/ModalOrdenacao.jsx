import React, { useState } from "react";

import { Modal, Typography, Box, Button, Checkbox, FormGroup, FormControlLabel } from '@mui/material';

import Backdrop from '@mui/material/Backdrop';
import Fade from '@mui/material/Fade';
import FontConfig from '../../service/FontConfig'

const ModalOrdenacao = (props) => {

    const style = {
        position: 'absolute',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        width: 230,
        height: 280,
        bgcolor: 'background.paper',
        borderRadius: '5px',
        boxShadow: 24,
        p: 1,
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        flexDirection: 'column',
    };

    const cssSelect = {
        width: '100%',
        height: '50%',
        display: 'flex',
        justifyContent: 'start',
        alignItems: 'start',
        textAlign: 'center',
        flexDirection: 'column',
    }

    const opcoesOrenação = (tipoOrdenacao) => {
        switch (tipoOrdenacao) {
            case "az":
                return "A-Z";
            case "za":
                return "Z-A";
            case "ppm":
                return "PPM";
        }
    };

    // tipos de ordenação:
    //  az - za - score mais alto score menor - mais novas - mais antigas -  

    // useState para abrir e fechar o modal

    let open = false;
    open = props.open;
    const setOpen = props.setOpen;
    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    return (

        <Modal
            open={props.open}
            onClose={handleClose}
            closeAfterTransition
            BackdropComponent={Backdrop}
            BackdropProps={{
                timeout: 500,
            }}
        >
            <Fade in={props.open}>
                <Box sx={style}>
                    <FormGroup sx={cssSelect}>
                        <Typography fontSize={FontConfig.big} sx={{ mt: 2 }}>
                            Título
                        </Typography>
                        <FormControlLabel control={<Checkbox defaultChecked />} label="A-Z" />
                        <FormControlLabel control={<Checkbox defaultChecked />} label="Z-A" />
                    </FormGroup>
                    <FormGroup sx={cssSelect}>
                        <Typography fontSize={FontConfig.big} sx={{ mt: 2 }}>
                            Score
                        </Typography>
                        <FormControlLabel  control={<Checkbox defaultChecked />} label="Maior Score" />
                        <FormControlLabel control={<Checkbox defaultChecked />} label="Menor Score" />
                    </FormGroup>
                    <FormGroup sx={cssSelect}>
                        <Typography fontSize={FontConfig.big} sx={{ mt: 2 }}>
                            Data
                        </Typography>
                        <FormControlLabel control={<Checkbox defaultChecked />} label="Mais Nova" />
                        <FormControlLabel control={<Checkbox defaultChecked />} label="Mais Velha" />
                    </FormGroup>
                </Box>
            </Fade>
        </Modal>
    );
};

export default ModalOrdenacao;