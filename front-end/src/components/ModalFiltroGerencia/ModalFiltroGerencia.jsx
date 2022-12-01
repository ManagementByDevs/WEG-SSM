import React, { useState } from "react";

import { Modal, Typography, Box, Button, InputLabel, Select, MenuItem, FormControl } from "@mui/material";

import Backdrop from "@mui/material/Backdrop";
import Fade from "@mui/material/Fade";
import FontConfig from "../../service/FontConfig";
import CloseIcon from '@mui/icons-material/Close';


const ModalFiltroGerencia = (props) => {

    // variáveis de estilo para o modal do filtro

    const styleModal = {
        position: "absolute",
        top: "50%",
        left: "50%",
        transform: "translate(-50%, -50%)",
        width: 580,
        height: 400,
        bgcolor: "background.paper",
        borderRadius: "5px",
        borderTop: "10px solid #00579D",
        boxShadow: 24,
        p: 4,
        display: "flex",
        justifyContent: "space-evenly",
        alignItems: "center",
        flexDirection: "column",
    };

    const styleFiltros = {
        display: "flex",
        justifyContent: "center",
        alignItems: 'center',
        width: "100%",
        height: "100%",
    }

    const styleFiltroDireita = {
        display: "flex",
        justifyContent: "space-evenly",
        alignItems: 'center',
        flexDirection: "column",
        height: "100%",
        width: "50%",
    }

    const styleFiltroEsquerda = {
        display: "flex",
        justifyContent: "space-evenly",
        alignItems: "center",
        flexDirection: "column",
        height: "100%",
        width: "50%",
    }

    const styleInputFiltro = {
        display: "flex",
        justifyContent: "space-evenly",
        alignItems: 'center',
        width: "100%",
    }

    // variáveis para abrir o modal através de outra tela

    let open = false;
    open = props.open;
    const setOpen = props.setOpen;

    // abrir e fechar modal

    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    // modificar o valor do select

    const [solicitante, setSolicitante] = React.useState('');
    const [forum, setForum] = React.useState('');
    const [tamanho, setTamanho] = React.useState('');
    const [gerente, setGerente] = React.useState('');
    const [departamento, setDepartamento] = React.useState('');

    const selecionarSolicitante = (event) => {
        setSolicitante(event.target.value);
    };

    const selecionarForum = (event) => {
        setForum(event.target.value);
    };

    const selecionarTamanho = (event) => {
        setTamanho(event.target.value);
    };

    const selecionarGerente = (event) => {
        setGerente(event.target.value);
    };

    const selecionarDepartamento = (event) => {
        setDepartamento(event.target.value);
    };

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
                <Box sx={styleModal}>
                    <CloseIcon onClick={handleClose} sx={{ position: 'absolute', left: '93%', top: '3%', cursor: 'pointer' }} />
                    <Typography fontWeight={650} color={'primary.main'} fontSize={FontConfig.smallTitle}>
                        Filtros
                    </Typography>
                    <Box sx={styleFiltros}>
                        <Box sx={styleFiltroEsquerda}>
                            <FormControl sx={{ width: '15rem' }}>
                                <InputLabel id="demo-simple-select-label">Solicitante</InputLabel>
                                <Select
                                    labelId="demo-simple-select-label"
                                    id="demo-simple-select"
                                    value={solicitante}
                                    label="Solicitante"
                                    onChange={selecionarSolicitante}
                                >
                                    <MenuItem value={1}>Cléber Andrade</MenuItem>
                                    <MenuItem value={2}>Jair dos Santos</MenuItem>
                                </Select>
                            </FormControl>
                            <FormControl sx={{ width: '15rem' }}>
                                <InputLabel id="demo-simple-select-label">Fórum</InputLabel>
                                <Select
                                    labelId="demo-simple-select-label"
                                    id="demo-simple-select"
                                    value={forum}
                                    label="Fórum"
                                    onChange={selecionarForum}
                                >
                                    <MenuItem value={1}>Fórum 01</MenuItem>
                                    <MenuItem value={2}>Fórum 02</MenuItem>
                                </Select>
                            </FormControl>
                            <FormControl sx={{ width: '15rem' }}>
                                <InputLabel id="demo-simple-select-label">Tamanho</InputLabel>
                                <Select
                                    labelId="demo-simple-select-label"
                                    id="demo-simple-select"
                                    value={tamanho}
                                    label="Tamanho"
                                    onChange={selecionarTamanho}
                                >
                                    <MenuItem value={1}>Grande</MenuItem>
                                    <MenuItem value={2}>Pequeno</MenuItem>
                                </Select>
                            </FormControl>
                        </Box>
                        <Box sx={styleFiltroDireita}>
                            <FormControl sx={{ width: '15rem' }}>
                                <InputLabel id="demo-simple-select-label">Gerente Responsável</InputLabel>
                                <Select
                                    labelId="demo-simple-select-label"
                                    id="demo-simple-select"
                                    value={gerente}
                                    label="Gerente Responsável"
                                    onChange={selecionarGerente}
                                >
                                    <MenuItem value={1}>Jair</MenuItem>
                                    <MenuItem value={2}>Jairo</MenuItem>
                                </Select>
                            </FormControl>
                            <FormControl sx={{ width: '15rem' }}>
                                <InputLabel id="demo-simple-select-label">Departamento</InputLabel>
                                <Select
                                    labelId="demo-simple-select-label"
                                    id="demo-simple-select"
                                    value={departamento}
                                    label="Departamento"
                                    onChange={selecionarDepartamento}
                                >
                                    <MenuItem value={1}>Weg Digital</MenuItem>
                                    <MenuItem value={2}>Weg Motores</MenuItem>
                                </Select>
                            </FormControl>
                            <Box sx={styleInputFiltro}>
                                <input style={{ width: '7rem', height: '2.938rem', textAlign: 'center', border: 'solid 1px', color: 'primary.secondary', borderRadius: '5px', background: 'transparent' }} placeholder="Número"></input>
                                <input style={{ width: '7rem', height: '2.938rem', textAlign: 'center', border: 'solid 1px', color: 'primary.secondary', borderRadius: '5px', background: 'transparent' }} placeholder="PPM"></input>
                            </Box>
                        </Box>
                    </Box>
                    <Button onClick={handleClose} variant="contained" disableElevation color="primary" sx={{ marginTop: '1%', width: '8rem', height: '3rem', fontSize: FontConfig.normal }}>Filtrar</Button>
                </Box>
            </Fade>
        </Modal>
    );
}

export default ModalFiltroGerencia;






