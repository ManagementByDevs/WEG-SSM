import { React, useState } from 'react';

import {
    Modal,
    Fade,
    Divider,
    Typography,
    Box,
    Button,
    Checkbox,
    FormGroup,
    FormControlLabel,
} from '@mui/material';

import Backdrop from '@mui/material/Backdrop';

import FontConfig from '../../service/FontConfig';
import CloseIcon from '@mui/icons-material/Close';
import ContainerProposta from '../ContainerProposta/ContainerProposta';

const ModalAddPropostaPauta = (props) => {

    const cssModal = {
        position: 'absolute',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        width: 450,
        height: 450,
        bgcolor: 'background.paper',
        borderRadius: '5px',
        borderTop: '10px solid #00579D',
        boxShadow: 24,
        p: 3,
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'center',
        flexDirection: 'column'
    };

    const parteCheck = {
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        flexDirection: 'column'
    };

    const botaoCriar = {
        width: '7rem',
        border: 'solid 1px',
        color: 'tertiary'
    };

    const botaoDesabilitado = {
        width: '7rem',
        border: 'solid 1px',
        color: 'tertiary.main'
    };

    const botaoHabilitado = {
        width: '7rem',
        border: 'solid 1px'
    };

    const listaPropostas = {
        display: 'flex',
        alignItems: 'center',
        flexDirection: 'column',
        overflow: 'auto',
        width: '100%',
        height: '80%'
    };

    // props para abrir o modal através de outra tela

    let open = false;
    open = props.open;
    const setOpen = props.setOpen;

    // useState para abrir e fechar o modal

    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    // veriricação para marcar apenas um check

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
            open={open}
            onClose={handleClose}
            closeAfterTransition
        >
            <Fade in={open}>
                <Box sx={cssModal}>
                    <Typography fontWeight={650} fontSize={FontConfig.smallTitle} color={'primary.main'}>Selecione a Pauta</Typography>
                    <CloseIcon onClick={handleClose} sx={{ position: 'absolute', left: '93%', top: '3%', cursor: 'pointer' }} ></CloseIcon>

                    <Box sx={listaPropostas}>
                        <ContainerProposta></ContainerProposta>
                        <ContainerProposta></ContainerProposta>
                        <ContainerProposta></ContainerProposta>
                        <ContainerProposta></ContainerProposta>
                    </Box>

                    <Divider sx={{ marginTop: '1%' }} />

                    <Box sx={parteCheck}>
                        <Typography fontWeight={650} fontSize={FontConfig.veryBig} color={'primary.main'}>
                            Adicionar Como
                        </Typography>
                        <Box>
                            <FormGroup>
                                <Box sx={{ display: 'flex', justifyContent: 'center', flexDirection: 'column' }}>
                                    <FormControlLabel checked={check[0]} onChange={mudarCheck1} control={<Checkbox />} label="Proposta publicada" />
                                    <FormControlLabel checked={check[1]} onChange={mudarCheck2} control={<Checkbox />} label="Proposta não publicada" />
                                </Box>
                            </FormGroup>
                        </Box>
                        <Box sx={{ width: '15rem', display: 'flex', justifyContent: 'space-between', marginTop: '2%' }}>
                            <Button sx={botaoCriar} variant="container" disableElevation>Criar Pauta</Button>
                            <Button sx={botaoDesabilitado} variant="container" disableElevation>Adicionar</Button>
                        </Box>
                    </Box>
                </Box>
            </Fade>
        </Modal>
    );
};

export default ModalAddPropostaPauta;