import React, { useState, useContext } from "react";

import { Modal, Typography, Box, Button, InputLabel, Select, MenuItem, FormControl, Autocomplete, TextField } from "@mui/material";

import Backdrop from "@mui/material/Backdrop";
import Fade from "@mui/material/Fade";
import FontConfig from "../../service/FontConfig";
import CloseIcon from '@mui/icons-material/Close';
import UsuarioService from "../../service/usuarioService";

import FontContext from "../../service/FontContext";

const ModalFiltroGerencia = (props) => {
    // Context para alterar o tamanho da fonte
    const { FontConfig, setFontConfig } = useContext(FontContext);

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

    const handleClose = () => {
        setOpen(false);
    }

    const limparFiltro = () => {
        props.setFiltro({ solicitante: null, forum: "", tamanho: "", gerente: null, departamento: "" });
        handleClose();
    }

    const selecionarSolicitante = (event, value) => {
        props.setFiltro({ solicitante: value, forum: props.filtro.forum, tamanho: props.filtro.tamanho, gerente: props.filtro.gerente, departamento: props.filtro.departamento });
    }

    const selecionarForum = (event) => {
        props.setFiltro({ solicitante: props.filtro.solicitante, forum: event.target.value, tamanho: props.filtro.tamanho, gerente: props.filtro.gerente, departamento: props.filtro.departamento });
    };

    const selecionarTamanho = (event) => {
        props.setFiltro({ solicitante: props.filtro.solicitante, forum: props.filtro.forum, tamanho: event.target.value, gerente: props.filtro.gerente, departamento: props.filtro.departamento });
    };

    const selecionarGerente = (event, value) => {
        props.setFiltro({ solicitante: props.filtro.solicitante, forum: props.filtro.forum, tamanho: props.filtro.tamanho, gerente: value, departamento: props.filtro.departamento });
    };

    const selecionarDepartamento = (event) => {
        props.setFiltro({ solicitante: props.filtro.solicitante, forum: props.filtro.forum, tamanho: props.filtro.tamanho, gerente: props.filtro.gerente, departamento: event.target.value });
    };

    // Pesquisa de solicitantes feita quando algum input é digitado
    const pesquisarSolicitantes = (event) => {
        if (event?.target.value?.length > 0) {
            UsuarioService.getUsuarioByNomeAndTipo(event.target.value, "SOLICITANTE").then((response) => {
                props.setListaSolicitantes(response);
            });
        }
    }

    // Pesquisa de gerentes feita quando algum input é digitado
    const pesquisarGerentes = (event) => {
        if (event?.target.value?.length > 0) {
            UsuarioService.getUsuarioByNomeAndTipo(event.target.value, "GERENTE").then((response) => {
                props.setListaGerentes(response);
            });
        }
    }

    // Função para salvar o número sequencial digitado no input
    const salvarNumero = (event) => {
        if (event?.target?.value?.length > 0) {
            props.buscarPorNumero(event.target.value);
        }
    }

    // Função para salvar o número PPM digitado no input
    const salvarPPM = (event) => {
        if (event?.target?.value?.length > 0) {
            props.buscarPorNumero(event?.target?.value);
        }
    }

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
                            <Autocomplete
                                disablePortal
                                id="combo-box-demo"
                                options={props.listaSolicitantes}
                                noOptionsText={"Sem Resultados"}
                                sx={{ width: 240 }}
                                value={props.filtro.solicitante}
                                onInputChange={(e) => { pesquisarSolicitantes(e) }}
                                onChange={(e, value) => { selecionarSolicitante(e, value) }}
                                getOptionLabel={(option) => {
                                    return option?.nome || "";
                                }}
                                renderInput={(params) => <TextField {...params} label="Solicitante" />}
                            />
                            <FormControl sx={{ width: '15rem' }}>
                                <InputLabel id="demo-simple-select-label">Fórum</InputLabel>
                                <Select
                                    labelId="demo-simple-select-label"
                                    id="demo-simple-select"
                                    value={props.filtro.forum}
                                    label="Fórum"
                                    onChange={selecionarForum}
                                >
                                    <MenuItem selected value={""}>Selecionar</MenuItem>
                                    {props.listaForuns.map((forum) => {
                                        return (
                                            <MenuItem key={forum.id} value={forum}>{forum.nome}</MenuItem>
                                        )
                                    })}
                                </Select>
                            </FormControl>
                            <FormControl sx={{ width: '15rem' }}>
                                <InputLabel id="demo-simple-select-label">Tamanho</InputLabel>
                                <Select
                                    labelId="demo-simple-select-label"
                                    id="demo-simple-select"
                                    value={props.filtro.tamanho}
                                    label="Tamanho"
                                    onChange={selecionarTamanho}
                                >
                                    <MenuItem selected value={""}>Selecionar</MenuItem>
                                    <MenuItem value={"Muito Pequeno"}>Muito Pequeno</MenuItem>
                                    <MenuItem value={"Pequeno"}>Pequeno</MenuItem>
                                    <MenuItem value={"Médio"}>Médio</MenuItem>
                                    <MenuItem value={"Grande"}>Grande</MenuItem>
                                    <MenuItem value={"Muito Grande"}>Muito Grande</MenuItem>
                                </Select>
                            </FormControl>
                        </Box>
                        <Box sx={styleFiltroDireita}>
                            <Autocomplete
                                disablePortal
                                id="combo-box-demo"
                                options={props.listaGerentes}
                                noOptionsText={"Sem Resultados"}
                                sx={{ width: 240 }}
                                value={props.filtro.gerente}
                                onInputChange={(e) => { pesquisarGerentes(e) }}
                                onChange={(e, value) => { selecionarGerente(e, value) }}
                                getOptionLabel={(option) => {
                                    return option?.nome || "";
                                }}
                                renderInput={(params) => <TextField {...params} label="Gerente Responsável" />}
                            />
                            <FormControl sx={{ width: '15rem' }}>
                                <InputLabel id="demo-simple-select-label">Departamento</InputLabel>
                                <Select
                                    labelId="demo-simple-select-label"
                                    id="demo-simple-select"
                                    value={props.filtro.departamento}
                                    label="Departamento"
                                    onChange={selecionarDepartamento}
                                >
                                    <MenuItem selected value={""}>Selecionar</MenuItem>
                                    {props.listaDepartamentos.map((departamento) => {
                                        return (
                                            <MenuItem key={departamento.id} value={departamento}>{departamento.nome}</MenuItem>
                                        )
                                    })}
                                </Select>
                            </FormControl>
                            <Box sx={styleInputFiltro}>
                                <input onChange={(e) => { salvarNumero(e) }} style={{ width: '7rem', height: '2.938rem', textAlign: 'center', border: 'solid 1px #c4c4c4', color: 'primary.main', borderRadius: '5px', background: 'transparent' }} placeholder="Número"></input>
                                <input onChange={(e) => { salvarPPM(e) }} style={{ width: '7rem', height: '2.938rem', textAlign: 'center', border: 'solid 1px #c4c4c4', color: 'primary.main', borderRadius: '5px', background: 'transparent' }} placeholder="PPM"></input>
                            </Box>
                        </Box>
                    </Box>
                    <Button onClick={limparFiltro} variant="contained" disableElevation color="primary" sx={{ marginTop: '1%', width: '8rem', height: '3rem', fontSize: FontConfig.normal }}>Limpar Filtros</Button>
                </Box>
            </Fade>
        </Modal>
    );
}

export default ModalFiltroGerencia;