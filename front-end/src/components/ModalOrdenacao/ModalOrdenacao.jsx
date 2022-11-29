import React, { useState } from "react";

import { Modal, Typography, Box, Button, Checkbox, FormGroup, FormControlLabel, Grid } from '@mui/material';

import Backdrop from '@mui/material/Backdrop';
import Fade from '@mui/material/Fade';
import FontConfig from '../../service/FontConfig'
import CloseIcon from '@mui/icons-material/Close';

const ModalOrdenacao = (props) => {

    const style = {
        position: 'absolute',
        top: '43%',
        left: '34%',
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

    const [check, setCheck] = useState([false, false]);
    const [check2, setCheck2] = useState([false, false]);
    const [check3, setCheck3] = useState([false, false]);

    function mudarCheck1() {
        if (check[0]) {
            setCheck([false, false]);
        } else {
            setCheck([true, false]);
        }
    }

    function mudarCheck2() {
        if (check[1]) {
            setCheck([false, false]);
        } else {
            setCheck([false, true]);
        }
    }

    function mudarCheck3() {
        if (check2[0]) {
            setCheck2([false, false]);
        } else {
            setCheck2([true, false]);
        }
    }

    function mudarCheck4() {
        if (check2[1]) {
            setCheck2([false, false]);
        } else {
            setCheck2([false, true]);
        }
    }

    function mudarCheck5() {
        if (check3[0]) {
            setCheck3([false, false]);
        } else {
            setCheck3([true, false]);
        }
    }

    function mudarCheck6() {
        if (check3[1]) {
            setCheck3([false, false]);
        } else {
            setCheck3([false, true]);
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
                                    <FormControlLabel id="checkA"  checked={check[1]} onChange={mudarCheck2} name control={<Checkbox />} label="A-Z" />
                                    <FormControlLabel id="checkZ"  checked={check[0]} onChange={mudarCheck1} control={<Checkbox />} label="Z-A" />
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
                                    <FormControlLabel  checked={check2[1]} onChange={mudarCheck4} control={<Checkbox />} label="Maior Score" />
                                    <FormControlLabel  checked={check2[0]} onChange={mudarCheck3} control={<Checkbox />} label="Menor Score" />
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
                                    <FormControlLabel checked={check3[1]} onChange={mudarCheck6} control={<Checkbox />} label="Mais Nova" />
                                    <FormControlLabel checked={check3[0]} onChange={mudarCheck5} control={<Checkbox />} label="Mais Velha" />
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