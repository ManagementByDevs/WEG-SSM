import React, { useState } from "react";

import { Modal, Typography, Box, Button, Checkbox, FormGroup, FormControlLabel, Grid } from '@mui/material';

import Fade from '@mui/material/Fade';
import FontConfig from '../../service/FontConfig'
import CloseIcon from '@mui/icons-material/Close';

const ModalOrdenacao = (props) => {

    const style = {
        position: 'absolute',
        top: '39.5%',
        left: '34.5%',
        transform: 'translate(-50%, -50%)',
        width: 310,
        height: 280,
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

    const [checkTitulo, setCheckTitulo] = useState([false, false]);
    const [checkScore, setCheckScore] = useState([false, false]);
    const [checkDate, setCheckDate] = useState([false, false]);

    function mudarCheck1() {
        if (checkTitulo[0]) {
            setCheckTitulo([false, false]);
        } else {
            setCheckTitulo([true, false]);
        }
    }

    function mudarCheck2() {
        if (checkTitulo[1]) {
            setCheckTitulo([false, false]);
        } else {
            setCheckTitulo([false, true]);
        }
    }

    function mudarCheck3() {
        if (checkScore[0]) {
            setCheckScore([false, false]);
        } else {
            setCheckScore([true, false]);
        }
    }

    function mudarCheck4() {
        if (checkScore[1]) {
            setCheckScore([false, false]);
        } else {
            setCheckScore([false, true]);
        }
    }

    function mudarCheck5() {
        if (checkDate[0]) {
            setCheckDate([false, false]);
        } else {
            setCheckDate([true, false]);
        }
    }

    function mudarCheck6() {
        if (checkDate[1]) {
            setCheckDate([false, false]);
        } else {
            setCheckDate([false, true]);
        }
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
                    <CloseIcon onClick={handleClose} sx={{ position: 'absolute', left: '90%', top: '3%', cursor: 'pointer' }} />
                    <Grid container spacing={0}>
                        <Grid item xs={9.2}>
                            <FormGroup sx={cssSelect}>
                                <Typography sx={{ color: 'secundary.main', fontSize: FontConfig.big, fontWeight: '600' }}>
                                    Título:
                                </Typography>
                                <div style={styleDiv}>
                                    <FormControlLabel checked={checkTitulo[1]} onChange={mudarCheck2} control={<Checkbox />} label="A-Z" />
                                    <FormControlLabel checked={checkTitulo[0]} onChange={mudarCheck1} control={<Checkbox />} label="Z-A" />
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
                                    <FormControlLabel checked={checkScore[1]} onChange={mudarCheck4} control={<Checkbox />} label="Maior Score" />
                                    <FormControlLabel checked={checkScore[0]} onChange={mudarCheck3} control={<Checkbox />} label="Menor Score" />
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
                                    <FormControlLabel checked={checkDate[1]} onChange={mudarCheck6} control={<Checkbox />} label="Mais Nova" />
                                    <FormControlLabel checked={checkDate[0]} onChange={mudarCheck5} control={<Checkbox />} label="Mais Velha" />
                                </div>
                            </FormGroup>
                        </Grid>
                    </Grid>

                    <Button onClick={handleClose} variant="contained" disableElevation color="primary" sx={{ marginTop: '2%', width: '5rem', fontSize: FontConfig.normal }}>Aplicar</Button>
                </Box>
            </Fade>
        </Modal>
    );
};

export default ModalOrdenacao;