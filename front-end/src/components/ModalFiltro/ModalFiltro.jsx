import React, { useState } from "react";

import { Modal, Typography, Box, Button, Checkbox, FormGroup, FormControlLabel, Grid, Fade } from '@mui/material';

import FontConfig from '../../service/FontConfig';
import CloseIcon from '@mui/icons-material/Close';

const ModalFiltro = (props) => {

    // variáveis de estilo para o css do modal

    const styleModal = {
        position: 'absolute',
        top: '37.5%',
        left: '37%',
        transform: 'translate(-50%, -50%)',
        width: 297,
        height: 240,
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

    const styleModalFiltro = {
        position: 'absolute',
        top: '41%',
        left: '82%',
        transform: 'translate(-50%, -50%)',
        width: 310,
        height: 300,
        bgcolor: 'background.paper',
        borderRadius: '5px',
        borderTop: '10px solid #00579D',
        boxShadow: 24,
        display: 'flex',
        justifyContent: 'space-evenly',
        alignItems: 'center',
        flexDirection: 'column',
        p: 2,
    }

    const styleSelect = {
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

    const styleInput = {
        width: '15rem',
        height: '2.2rem',
        border: 'solid 1px',
        textAlign: 'center',
        borderRadius: '5px',
        color: 'primary.secondary',
        background: 'transparent',
        filter: 'white'
    }

    // props para abrir o modal a partir da página de home

    let open = false;
    open = props.open;
    const setOpen = props.setOpen;

    // abrir e fechar modal

    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    // useState para permitir apenas um checkbox selecionado

    const [check, setCheck] = useState([false, false, false, false]);

    // funções para permitir o check de apenas um checkbox

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

    function mudarCheck3() {
        if (check[2]) {
            setCheck([false, false, false, false]);
        } else {
            setCheck([false, false, true, false]);
        }
    }

    function mudarCheck4() {
        if (check[3]) {
            setCheck([false, false, false, false]);
        } else {
            setCheck([false, false, false, true]);
        }
    }

    return (
        <Modal
            open={props.open}
            onClose={handleClose}
            BackdropProps={{invisible:true}}
        >
            <Fade in={props.open}>

                {/* 'ngIf' para o modal de filtro das demandas e filtro das notificações */}
                {/* se o props filtroDemanda == true, chama o modal de filtro das demandas, caso contrário chama o modal de filtro das notificações */}

                {
                    props.filtroDemanda ?
                        <Box sx={styleModal}>
                            <CloseIcon onClick={handleClose} sx={{ position: 'absolute', left: '90%', top: '3%', cursor: 'pointer' }} />
                            <Grid container spacing={0}>
                                <Grid item xs={9.2}>
                                    <FormGroup sx={styleSelect}>
                                        <Typography sx={{ color: 'secundary.main', fontSize: FontConfig.big, fontWeight: '600' }}>
                                            Status:
                                        </Typography>
                                        <Box sx={styleDiv}>
                                            <FormControlLabel checked={check[0]} onChange={mudarCheck1} control={<Checkbox />} label="Aprovada" />
                                            <FormControlLabel checked={check[1]} onChange={mudarCheck2} control={<Checkbox />} label="Reprovada" />
                                            <FormControlLabel checked={check[2]} onChange={mudarCheck3} control={<Checkbox />} label="Aguardando Edição" />
                                            <FormControlLabel checked={check[3]} onChange={mudarCheck4} control={<Checkbox />} label="Aguardando Revisão" />
                                        </Box>
                                    </FormGroup>
                                </Grid>
                            </Grid>
                        </Box>
                        :
                        <Box sx={styleModalFiltro}>
                            <CloseIcon onClick={handleClose} sx={{ position: 'absolute', left: '90%', top: '3%', cursor: 'pointer' }} />
                            <Grid container spacing={0}>
                                <Grid item xs={9.2}>
                                    <FormGroup sx={styleSelect}>
                                        <Typography sx={{ color: 'secundary.main', fontSize: FontConfig.big, fontWeight: '600' }}>
                                            Status:
                                        </Typography>
                                        <Box sx={styleDiv}>
                                            <FormControlLabel checked={check[0]} onChange={mudarCheck1} control={<Checkbox />} label="Aprovada" />
                                            <FormControlLabel checked={check[1]} onChange={mudarCheck2} control={<Checkbox />} label="Reprovada" />
                                            <FormControlLabel checked={check[2]} onChange={mudarCheck3} control={<Checkbox />} label="Mais Informações" />
                                            <FormControlLabel checked={check[3]} onChange={mudarCheck4} control={<Checkbox />} label="Mensagens" />
                                            <Typography sx={{ color: 'secundary.main', fontSize: FontConfig.big, fontWeight: '600' }}>
                                                Data:
                                            </Typography>
                                            <input style={styleInput} type="date"></input>
                                        </Box>
                                    </FormGroup>
                                </Grid>
                            </Grid>
                        </Box>
                }
            </Fade>
        </Modal >
    );
}

export default ModalFiltro;