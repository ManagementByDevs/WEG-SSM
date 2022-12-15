import { React, useState, useEffect } from 'react';

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
    Paper,
    Select,
    FormControl,
    MenuItem
} from '@mui/material';

import FontConfig from '../../service/FontConfig';
import CloseIcon from '@mui/icons-material/Close';
import ContainerProposta from '../ContainerProposta/ContainerProposta';

const ModalAddPropostaPauta = (props) => {

    // variáveis de estilo para os itens do componente 

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

    const containerGeral = {
        width: '90%',
        height: '5.5rem',
        border: '1px solid',
        borderLeft: 'solid 6px',
        borderColor: 'primary.main',
        borderRadius: '5px',
        p: 4,
        margin: '1%',
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        flexDirection: 'column',
        cursor: 'pointer',
    };

    const containerSelecionado = {
        width: '90%',
        height: '5.5rem',
        border: '1px solid',
        borderLeft: 'solid 6px',
        borderColor: 'primary.main',
        borderRadius: '5px',
        p: 4,
        margin: '1%',
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        flexDirection: 'column',
        cursor: 'pointer',
        backgroundColor: 'rgba(196, 196, 196, 0.7)'
    };

    const parteCima = {
        width: '100%',
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'center',
    };

    const parteBaixo = {
        display: 'flex',
        justifyContent: 'flex-end',
        alignItems: 'flex-end',
        width: '100%',
        marginTop: '2%'
    };

    const selectComissao = {
        width: '10rem',
    };

    const data = {
        width: '10rem',
        border: 'solid 1px',
        color: 'grey',
        textAlign: 'center',
        borderRadius: '3px',
        color: 'primary.secondary',
        background: 'transparent',
        filter: 'white'
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

    // useStates utilizados no componente

    const [habilitadoAdd, setHabilitadoAdd] = useState(false);
    const [indexPautaSelecionada, setIndexPautaSelecionada] = useState(null);
    const [novaPauta, setnovaPauta] = useState(false);
    const [novaPautaSelecionada, setnovaPautaSelecionada] = useState(false);
    const [botaoNovaPauta, setBotaoNovaPauta] = useState(false);
    const [listaPautas, setListaPautas] = useState([1, 2]);
    const [inputData, setInputData] = useState("");
    const [comissao, setComissao] = useState("");

    // useEffect para habilitar ou desabilitar o botão de adicionar

    useEffect(() => {

    }, [habilitadoAdd])

    // função para adicionar uma nova pauta

    const addPauta = () => {
        setnovaPauta(true);
    };

    // função para mudar o valor do select na nova pauta criada

    const handleChange = (event) => {
        setComissao(event.target.value);
    };

    // função para selecionar a nova pauta

    const selecionarNovaPauta = () => {
        setnovaPautaSelecionada(!novaPautaSelecionada);
        setBotaoNovaPauta(!botaoNovaPauta);
    };

    return (
        <Modal
            open={open}
            onClose={handleClose}
            closeAfterTransition
        >
            <Fade in={open}>

                {/* Início conteúdo modal */}
                <Box sx={cssModal}>
                    <Typography fontWeight={650} fontSize={FontConfig.smallTitle} color={'primary.main'}>Selecione a Pauta</Typography>
                    <CloseIcon onClick={handleClose} sx={{ position: 'absolute', left: '93%', top: '3%', cursor: 'pointer' }} ></CloseIcon>

                    <Box sx={listaPropostas}>

                        {/* Exibe as pautas do sistema */}
                        {listaPautas.map((proposta, index) => {
                            return (
                                <ContainerProposta key={index} setIndexPautaSelecionada={setIndexPautaSelecionada} index={index} indexPautaSelecionada={indexPautaSelecionada} />
                            )
                        })}

                        {/* Nova pauta criada */}
                        {novaPauta &&
                            <>
                                {/* Verificar se a nova pauta foi selecionada */}
                                {!novaPautaSelecionada ?
                                    <Paper sx={containerGeral} onClick={selecionarNovaPauta}>
                                        <Box sx={parteCima}>
                                            <Typography>
                                                Propostas:
                                            </Typography>
                                            <input style={data} type="date"></input>
                                        </Box>

                                        <Box sx={parteBaixo}>
                                            <FormControl sx={selectComissao} size="small">
                                                <Select
                                                    value={comissao}
                                                    onChange={handleChange}
                                                    displayEmpty
                                                    inputProps={{ 'aria-label': 'Without label' }}
                                                >
                                                    <MenuItem value="">
                                                        Comissão
                                                    </MenuItem>
                                                    <MenuItem value={1}>Exemplo 01</MenuItem>
                                                    <MenuItem value={2}>Exemplo 02</MenuItem>
                                                </Select>
                                            </FormControl>
                                        </Box>
                                    </Paper >
                                    :
                                    <Paper sx={containerSelecionado} onClick={selecionarNovaPauta}>
                                        <Box sx={parteCima}>
                                            <Typography>
                                                Propostas:
                                            </Typography>
                                            <input style={data} type="date"></input>
                                        </Box>

                                        <Box sx={parteBaixo}>
                                            <FormControl sx={selectComissao} size="small">
                                                <Select
                                                    value={comissao}
                                                    onChange={handleChange}
                                                    displayEmpty
                                                    inputProps={{ 'aria-label': 'Without label' }}
                                                >
                                                    <MenuItem value="">
                                                        Comissão
                                                    </MenuItem>
                                                    <MenuItem value={1}>Exemplo 01</MenuItem>
                                                    <MenuItem value={2}>Exemplo 02</MenuItem>
                                                </Select>
                                            </FormControl>
                                        </Box>
                                    </Paper >
                                }
                            </>
                        }

                    </Box>

                    <Divider sx={{ marginTop: '2%', width: '80%', borderColor: 'tertiary.main' }} />

                    {/* Parte de baixo do componente, com a opção de selecionar se é uma pauta publicada ou não publicada, 
                        assim como opção de criar nova pauta ou adicionar em alguma pauta */}
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
                            <Button sx={botaoDesabilitado} disableElevation disabled={indexPautaSelecionada == null && botaoNovaPauta == false} variant="contained" onClick={handleClose}>Adicionar</Button>
                        </Box>
                    </Box>
                </Box>
            </Fade>
        </Modal>
    );
};

export default ModalAddPropostaPauta;