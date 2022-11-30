import React, { useState } from "react";

import { Modal, Typography, Box, Button, Checkbox, FormGroup, FormControlLabel, Grid, Fade } from '@mui/material';

import FontConfig from '../../service/FontConfig'
import CloseIcon from '@mui/icons-material/Close';

const ModalFiltro = (props) => {

    const style = {
        position: 'absolute',
        top: '43%',
        left: '38%',
        transform: 'translate(-50%, -50%)',
        width: 297,
        height: 280,
        bgcolor: 'background.paper',
        borderRadius: '5px',
        borderTop: '10px solid #00579D',
        boxShadow: 24,
        display: 'flex',
        justifyContent: 'space-evenly',
        alignItems: 'center',
        flexDirection: 'column',
        p: 2,
    };

    const cssSelect = {
        width: '100%',
        height: '100%',
        display: 'flex',
        justifyContent: 'space-evenly',
        alignItems: 'flex-start',
    }

    const styleDiv = {
        width: '100%',
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'flex-center',
        flexDirection: 'column',
        margin: '0px'
    }

    let open = false;
    open = props.open;
    const setOpen = props.setOpen;

    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    const [check, setCheck] = useState([false, false, false, false]);

    function mudarCheck1() {
        if (check[0]) {
            setCheck([false, false, false, false]);
        } else {
            setCheck([true, false, false, false]);
        }
    }

    function mudarCheck2() {
        if (check[1]) {
            setCheck([false, false, false, false]);
        } else {
            setCheck([false, true, false, false]);
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
                    <CloseIcon onClick={handleClose} sx={{ position: 'absolute', left: '90%', top: '3%', cursor: 'pointer' }} />
                    <Grid container spacing={0}>
                        <Grid item xs={9.2}>
                            <FormGroup sx={cssSelect}>
                                <Typography sx={{ color: 'secundary.main', fontSize: FontConfig.big, fontWeight: '600' }}>
                                    Status:
                                </Typography>
                                <div style={styleDiv}>
                                    <FormControlLabel checked={check[1]} onChange={mudarCheck2} control={<Checkbox />} label="Aprovada" />
                                    <FormControlLabel checked={check[0]} onChange={mudarCheck1} control={<Checkbox />} label="Reprovada" />
                                    <FormControlLabel checked={check[1]} onChange={mudarCheck2} control={<Checkbox />} label="Aguardando Edição" />
                                    <FormControlLabel checked={check[0]} onChange={mudarCheck1} control={<Checkbox />} label="Aguardando Revisão" />
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