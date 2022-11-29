import React, { useState } from "react";

import { Modal, Typography, Box, Button, Checkbox, FormGroup, FormControlLabel, Grid } from '@mui/material';

import Backdrop from '@mui/material/Backdrop';
import Fade from '@mui/material/Fade';
import FontConfig from '../../service/FontConfig'
import CloseIcon from '@mui/icons-material/Close';

const ModalOrdenacao = (props) => {

    const style = {
        position: 'absolute',
        top: '42%',
        left: '34.5%',
        transform: 'translate(-50%, -50%)',
        width: 320,
        height: 250,
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
    }

    // useState para abrir e fechar o modal

    let open = false;
    open = props.open;
    const setOpen = props.setOpen;

    // useState para limitar um checkbox

    const [check, setCheck] = useState(false);

    function mudarCheck() {
        setCheck(!check);
    }

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
                    <CloseIcon onClick={handleClose} sx={{ position: 'absolute', left: '90%', top: '3%' }} />
                    <Grid container spacing={0}>
                        <Grid item xs={9.2}>
                            <FormGroup sx={cssSelect}>
                                <Typography sx={{ color: 'secundary.main', fontSize: FontConfig.big, fontWeight: '600' }}>
                                    Título:
                                </Typography>
                                <div style={styleDiv}>
                                    <FormControlLabel id="checkA" onClick={mudarCheck} control={<Checkbox  />} label="A-Z" />
                                    <FormControlLabel id="checkZ" disabled={check} control={<Checkbox  />} label="Z-A" />
                                </div>
                            </FormGroup>
                        </Grid>
                    </Grid>

                    <Grid container spacing={0}>
                        <Grid item xs={20}>
                            <FormGroup sx={cssSelect}>
                                <Typography sx={{ color: 'secundary.main', fontSize: FontConfig.big, fontWeight: '600' }}>
                                    Score:
                                </Typography>
                                <div style={styleDiv}>
                                    <FormControlLabel control={<Checkbox  />} label="Maior Score" />
                                    <FormControlLabel control={<Checkbox  />} label="Menor Score" />
                                </div>
                            </FormGroup>
                        </Grid>
                    </Grid>

                    <Grid container spacing={0}>
                        <Grid item xs={11.4}>
                            <FormGroup sx={cssSelect}>
                                <Typography sx={{ color: 'secundary.main', fontSize: FontConfig.big, fontWeight: '600' }}>
                                    Data:
                                </Typography>
                                <div style={styleDiv}>
                                    <FormControlLabel control={<Checkbox  />} label="Mais Nova" />
                                    <FormControlLabel control={<Checkbox  />} label="Mais Velha" />
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