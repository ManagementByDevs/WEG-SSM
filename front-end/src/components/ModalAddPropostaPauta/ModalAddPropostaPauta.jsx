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
    Paper
} from '@mui/material';

import Backdrop from '@mui/material/Backdrop';

import FontConfig from '../../service/FontConfig';
import CloseIcon from '@mui/icons-material/Close';
import ContainerProposta from '../ContainerProposta/ContainerProposta';
import { useEffect } from 'react';

const ModalAddPropostaPauta = (props) => {

    const cssModal = {
        position: 'absolute',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        width: 500,
        height: 500,
        bgcolor: 'background.paper',
        borderRadius: '5px',
        borderTop: '10px solid #00579D',
        boxShadow: 24,
        p: 2,
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'center',
        flexDirection: 'column'
    };

    const parteCheck = {
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        flexDirection: 'column',
        p: 1.5
    };

    const botaoCriar = {
        width: '7rem',
        border: 'solid 1px',
        color: 'tertiary.main',
        p: 1
    };

    const botaoDesabilitado = {
        width: '7rem',
        border: 'solid 1px',
        p: 1
    };

    const listaPropostas = {
        display: 'flex',
        alignItems: 'center',
        flexDirection: 'column',
        overflow: 'auto',
        width: '100%',
        height: '80%',
        p: 1.5
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

    const [habilitadoAdd, setHabilitadoAdd] = useState(false);
    const [indexPautaSelecionada, setIndexPautaSelecionada] = useState(null);
    const [novaPauta, setnovaPauta] = useState(false);

    useEffect(() => {
        console.log(habilitadoAdd)
    }, [habilitadoAdd])

    const [listaPautas, setListaPautas] = useState([1, 2]);

    const addPauta = () => {
        setListaPautas([...listaPautas, listaPautas.length + 1]);
        setnovaPauta(true);
    };

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
                        {listaPautas.map((proposta, index) => {
                            return (
                                <ContainerProposta key={index} setIndexPautaSelecionada={setIndexPautaSelecionada} index={index} indexPautaSelecionada={indexPautaSelecionada} novaPauta={novaPauta} />
                            )
                        })}

                    </Box>

                    <Divider sx={{ marginTop: '2%', width: '80%', borderColor: 'tertiary.main' }} />

                    <Box sx={parteCheck}>
                        <Typography fontWeight={650} fontSize={FontConfig.veryBig} color={'primary.main'}>
                            Adicionar Como Proposta
                        </Typography>
                        <Box>
                            <FormGroup>
                                <Box sx={{ display: 'flex', justifyContent: 'center', padding: '4px' }}>
                                    <FormControlLabel checked={check[0]} onChange={mudarCheck1} control={<Checkbox />} label="Publicada" />
                                    <FormControlLabel checked={check[1]} onChange={mudarCheck2} control={<Checkbox />} label="Não publicada" />
                                </Box>
                            </FormGroup>
                        </Box>
                        <Box sx={{ width: '90%', display: 'flex', justifyContent: 'space-between', marginTop: '3%' }}>
                            <Button sx={botaoCriar} disableElevation onClick={addPauta} disabled={novaPauta != false}>Criar Pauta</Button>
                            <Button sx={botaoDesabilitado} disableElevation disabled={indexPautaSelecionada == null} variant="contained" onClick={handleClose}>Adicionar</Button>
                        </Box>
                    </Box>
                </Box>
            </Fade>
        </Modal>
    );
};

export default ModalAddPropostaPauta;