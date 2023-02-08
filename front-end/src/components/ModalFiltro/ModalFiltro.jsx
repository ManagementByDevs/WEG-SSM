import { React, useState, useContext } from "react";
import { Modal, Typography, Box, Checkbox, FormGroup, FormControlLabel, Grid, Fade } from '@mui/material';

import CloseIcon from '@mui/icons-material/Close';

import FontContext from "../../service/FontContext";
import DateService from "../../service/dateService";

const ModalFiltro = (props) => {

    // Context para alterar o tamanho da fonte
    const { FontConfig, setFontConfig } = useContext(FontContext);

    // props para abrir o modal a partir da página de home

    let open = false;
    open = props.open;
    const setOpen = props.setOpen;

    // abrir e fechar modal

    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    // useState para permitir apenas um checkbox selecionado

    const [check, setCheck] = useState([false, false, false, false, false]);

    // funções para permitir o check de apenas um checkbox

    function mudarCheck1() {
        if (props.listaFiltros[0]) {
            props.setListaFiltros([false, false, false, false, false]);
        } else {
            props.setListaFiltros([true, false, false, false, false]);
        }
    }

    function mudarCheck2() {
        if (props.listaFiltros[1]) {
            props.setListaFiltros([false, false, false, false, false]);
        } else {
            props.setListaFiltros([false, true, false, false, false]);
        }
    }

    function mudarCheck3() {
        if (props.listaFiltros[2]) {
            props.setListaFiltros([false, false, false, false, false]);
        } else {
            props.setListaFiltros([false, false, true, false, false]);
        }
    }

    function mudarCheck4() {
        if (props.listaFiltros[3]) {
            props.setListaFiltros([false, false, false, false, false]);
        } else {
            props.setListaFiltros([false, false, false, true, false]);
        }
    }

    function mudarCheck5() {
        if (props.listaFiltros[4]) {
            props.setListaFiltros([false, false, false, false, false]);
        } else {
            props.setListaFiltros([false, false, false, false, true]);
        }
    }

    // função para pegar a data atual no modelo yyyy-mm-dd
    const getTodaysDate = () => {
        return DateService.getTodaysDateUSFormat();
    }

    // função para pegar o valor do input e passar para o componente pai
    const getValue = (newValue) => {
        props.setDate(newValue);
    }

    return (
        <Modal
            open={props.open}
            onClose={handleClose}
            BackdropProps={{ invisible: true }}
        >
            <Fade in={props.open}>

                {/* 'ngIf' para o modal de filtro das demandas e filtro das notificações */}
                {/* se o props filtroDemanda == true, chama o modal de filtro das demandas, caso contrário chama o modal de filtro das notificações */}

                {props.filtroDemanda ?
                    <Box className="absolute flex justify-evenly items-center flex-col"
                        sx={{ top: '36%', left: '37%', transform: 'translate(-50%, -50%)', width: 310, height: 280, bgcolor: 'background.paper', borderRadius: '5px', borderTop: '10px solid #00579D', boxShadow: 24, p: 2 }}>
                        <CloseIcon onClick={handleClose} sx={{ position: 'absolute', left: '90%', top: '3%', cursor: 'pointer' }} />
                        <Grid container spacing={0}>
                            <Grid item xs={9.2}>
                                <FormGroup className="w-full h-full flex justify-evenly items-center">
                                    <Typography sx={{ color: 'secundary.main', fontSize: FontConfig.big, fontWeight: '600' }}>
                                        Status:
                                    </Typography>
                                    <Box className="w-full flex justify-between flex-col m-0">
                                        <FormControlLabel checked={props.listaFiltros[0]} onChange={mudarCheck1} control={<Checkbox />} label="Aprovada" />
                                        <FormControlLabel checked={props.listaFiltros[1]} onChange={mudarCheck2} control={<Checkbox />} label="Reprovada" />
                                        <FormControlLabel checked={props.listaFiltros[2]} onChange={mudarCheck3} control={<Checkbox />} label="Aguardando Edição" />
                                        <FormControlLabel checked={props.listaFiltros[3]} onChange={mudarCheck4} control={<Checkbox />} label="Aguardando Revisão" />
                                        <FormControlLabel checked={props.listaFiltros[4]} onChange={mudarCheck5} control={<Checkbox />} label="Em Aprovação" />
                                    </Box>
                                </FormGroup>
                            </Grid>
                        </Grid>
                    </Box>
                    :
                    <Box className="absolute flex justiy-evenly items-center flex-col"
                        sx={{ top: '39%', left: '83%', transform: 'translate(-50%, -50%)', width: 270, height: 300, bgcolor: 'background.paper', borderRadius: '5px', borderTop: '10px solid #00579D', boxShadow: 24, p: 2 }}>
                        <CloseIcon onClick={handleClose} sx={{ position: 'absolute', left: '90%', top: '3%', cursor: 'pointer' }} />
                        <Grid container spacing={0}>
                            <Grid item xs={9.2}>
                                <FormGroup className="w-full h-full flex justify-evenly items-center">
                                    <Typography sx={{ color: 'secundary.main', fontSize: FontConfig.big, fontWeight: '600' }}>
                                        Status:
                                    </Typography>
                                    <Box className="w-full flex justify-between flex-col m-0">
                                        <FormControlLabel checked={check[0]} onChange={mudarCheck1} control={<Checkbox />} label="Aprovada" />
                                        <FormControlLabel checked={check[1]} onChange={mudarCheck2} control={<Checkbox />} label="Reprovada" />
                                        <FormControlLabel checked={check[2]} onChange={mudarCheck3} control={<Checkbox />} label="Mais Informações" />
                                        <FormControlLabel checked={check[3]} onChange={mudarCheck4} control={<Checkbox />} label="Mensagens" />
                                        <Typography sx={{ color: 'secundary.main', fontSize: FontConfig.big, fontWeight: '600' }}>
                                            Data:
                                        </Typography>
                                        <input
                                            className="border border-solid rounded-md bg-transparent text-center"
                                            style={{ width: '15rem', height: '2.2rem', color: 'primary.secondary', filter: 'white' }}
                                            onChange={(e) => getValue(e.target.value)} type="date" max={getTodaysDate()} />
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