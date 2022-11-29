import React, { useState } from "react";

import { Modal, Typography, Box, Button, Checkbox, FormGroup, FormControlLabel, Grid } from '@mui/material';

import Backdrop from '@mui/material/Backdrop';
import Fade from '@mui/material/Fade';
import FontConfig from '../../service/FontConfig'
import CloseIcon from '@mui/icons-material/Close';

const ModalOrdenacao = (props) => {

    const style = {
        position: 'absolute',
        top: '46%',
        left: '35%',
        transform: 'translate(-50%, -50%)',
        width: 335,
        height: 330 ,
        bgcolor: 'background.paper',
        borderRadius: '5px',
        borderTop: '10px solid #00579D',
        boxShadow: 24,
        display: 'flex',
        justifyContent: 'space-evenly',
        alignItems: 'center',
        flexDirection: 'column',
        p: 1.5,
    };

    const cssSelect = {
        width: '100%',
        height: '100%',
        display: 'flex',
        justifyContent: 'space-evenly',
        alignItems: 'flex-start',
        flexDirection: 'column',
    }

    const styleDiv = {
        width: '100%',
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'flex-center',
        marginBottom: '4%'
    }

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
            BackdropComponent={false}
            BackdropProps={{
                timeout: 500,
            }}
            disableEnforceFocus
        >
            <Fade in={props.open}>
                <Box sx={style}>
                    <CloseIcon onClick={handleClose} sx={{marginLeft: '95%'}}/>
                    <Grid container spacing={0}>
                        <Grid item xs={9.2}>
                            <FormGroup sx={cssSelect}>
                                <Typography fontSize={FontConfig.big} sx={{ mt: 2 }}>
                                    Título:
                                </Typography>
                                <div style={styleDiv}>
                                    <FormControlLabel control={<Checkbox defaultChecked />} label="A-Z" />
                                    <FormControlLabel control={<Checkbox defaultChecked />} label="Z-A" />
                                </div>
                            </FormGroup>
                        </Grid>
                    </Grid>

                    <Grid container spacing={0}>
                        <Grid item xs={20}>
                            <FormGroup sx={cssSelect}>
                                <Typography fontSize={FontConfig.big} sx={{ mt: 2 }}>
                                    Score:
                                </Typography>
                                <div style={styleDiv}>
                                    <FormControlLabel control={<Checkbox defaultChecked />} label="Maior Score" />
                                    <FormControlLabel control={<Checkbox defaultChecked />} label="Menor Score" />
                                </div>
                            </FormGroup>
                        </Grid>
                    </Grid>

                    <Grid container spacing={0}>
                        <Grid item xs={11.4}>
                            <FormGroup sx={cssSelect}>
                                <Typography fontSize={FontConfig.big} sx={{ mt: 2 }}>
                                    Data:
                                </Typography>
                                <div style={styleDiv}>
                                    <FormControlLabel control={<Checkbox defaultChecked />} label="Mais Nova" />
                                    <FormControlLabel control={<Checkbox defaultChecked />} label="Mais Velha" />
                                </div>
                            </FormGroup>
                        </Grid>
                    </Grid>
                </Box>
            </Fade>
        </Modal>
    );
};

export default ModalOrdenacao;