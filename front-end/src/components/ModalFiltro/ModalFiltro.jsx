import React, { useState } from "react";

import { Modal, Typography, Box, Button, Checkbox, FormGroup, FormControlLabel, Grid, Fade } from '@mui/material';

import FontConfig from '../../service/FontConfig'

const ModalFiltro = (props) => {

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

    let open = false;
    open = props.open;
    const setOpen = props.setOpen;

    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    const [check, setCheck] = useState([false, false]);

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
                    <Grid container spacing={0}>
                        <Grid item xs={9.2}>
                            <FormGroup sx={cssSelect}>
                                <Typography sx={{ color: 'secundary.main', fontSize: FontConfig.big, fontWeight: '600' }}>
                                    Status:
                                </Typography>
                                <div style={styleDiv}>
                                    <FormControlLabel id="checkA" checked={check[1]} onChange={mudarCheck2} name control={<Checkbox />} label="A-Z" />
                                    <FormControlLabel id="checkZ" checked={check[0]} onChange={mudarCheck1} control={<Checkbox />} label="Z-A" />
                                </div>
                            </FormGroup>
                        </Grid>
                    </Grid>

                    <Button onClick={handleClose} variant="contained" disableElevation color="primary" sx={{ marginTop: '2%', width: '5rem', fontSize: FontConfig.normal }}>Aplicar</Button>
                </Box>
            </Fade>
        </Modal>
    );
}

export default ModalFiltro;