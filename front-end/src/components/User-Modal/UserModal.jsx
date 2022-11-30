import React, { useState, useEffect, useContext } from 'react';
import { useNavigate, Link } from 'react-router-dom';

import { Menu, MenuItem, Tooltip, IconButton, Avatar, Typography, Box, FormControlLabel, Switch } from '@mui/material/';
import { styled } from '@mui/material/styles';

import NotificationsOutlinedIcon from '@mui/icons-material/NotificationsOutlined';
import BorderColorOutlinedIcon from '@mui/icons-material/BorderColorOutlined';
import ChatBubbleOutlineOutlinedIcon from '@mui/icons-material/ChatBubbleOutlineOutlined';
import MarkChatUnreadOutlinedIcon from '@mui/icons-material/MarkChatUnreadOutlined';

import FontConfig from '../../service/FontConfig';

import UsuarioService from "../../service/usuarioService"

import ColorModeContext from "../../service/TemaContext";

const MaterialUISwitch = styled(Switch)(({ theme }) => ({
    width: 62,
    height: 34,
    padding: 7,
    '& .MuiSwitch-switchBase': {
        margin: 1,
        padding: 0,
        transform: 'translateX(6px)',
        '&.Mui-checked': {
            color: '#fff',
            transform: 'translateX(22px)',
            '& .MuiSwitch-thumb:before': {
                backgroundImage: `url('data:image/svg+xml;utf8,<svg xmlns="http://www.w3.org/2000/svg" height="20" width="20" viewBox="0 0 20 20"><path fill="${encodeURIComponent(
                    '#fff',
                )}" d="M4.2 2.5l-.7 1.8-1.8.7 1.8.7.7 1.8.6-1.8L6.7 5l-1.9-.7-.6-1.8zm15 8.3a6.7 6.7 0 11-6.6-6.6 5.8 5.8 0 006.6 6.6z"/></svg>')`,
            },
            '& + .MuiSwitch-track': {
                opacity: 1,
                backgroundColor: theme.palette.mode === 'dark' ? '#e5e7eb' : '#e5e7eb',
            },
        },
    },
    '& .MuiSwitch-thumb': {
        backgroundColor: theme.palette.mode === 'dark' ? '#00579d' : '#00579d',
        width: 32,
        height: 32,
        '&:before': {
            content: "''",
            position: 'absolute',
            width: '100%',
            height: '100%',
            left: 0,
            top: 0,
            backgroundRepeat: 'no-repeat',
            backgroundPosition: 'center',
            backgroundImage: `url('data:image/svg+xml;utf8,<svg xmlns="http://www.w3.org/2000/svg" height="20" width="20" viewBox="0 0 20 20"><path fill="${encodeURIComponent(
                '#fff',
            )}" d="M9.305 1.667V3.75h1.389V1.667h-1.39zm-4.707 1.95l-.982.982L5.09 6.072l.982-.982-1.473-1.473zm10.802 0L13.927 5.09l.982.982 1.473-1.473-.982-.982zM10 5.139a4.872 4.872 0 00-4.862 4.86A4.872 4.872 0 0010 14.862 4.872 4.872 0 0014.86 10 4.872 4.872 0 0010 5.139zm0 1.389A3.462 3.462 0 0113.471 10a3.462 3.462 0 01-3.473 3.472A3.462 3.462 0 016.527 10 3.462 3.462 0 0110 6.528zM1.665 9.305v1.39h2.083v-1.39H1.666zm14.583 0v1.39h2.084v-1.39h-2.084zM5.09 13.928L3.616 15.4l.982.982 1.473-1.473-.982-.982zm9.82 0l-.982.982 1.473 1.473.982-.982-1.473-1.473zM9.305 16.25v2.083h1.389V16.25h-1.39z"/></svg>')`,
        },
    },
    '& .MuiSwitch-track': {
        opacity: 1,
        backgroundColor: theme.palette.mode === 'dark' ? '#e5e7eb' : '#e5e7eb',
        borderRadius: 20 / 2,
    },
}));

const UserModal = () => {
    // Desestruturação de objeto em duas variáveis:
    // - Mode: modo do tema atual ("light" ou "dark")
    // - toggleColorMode: função para alternar o tema
    const { mode, toggleColorMode } = useContext(ColorModeContext);
    // Variável de estado para controlar o tema
    const [temaDark, setTemaDark] = useState(false);

    useEffect(() => {
        UsuarioService.getUsuarioById(parseInt(localStorage.getItem("usuarioId"))).then((e) => {
            setUsuario(e);
        });
    }, []);

    const navigate = useNavigate();

    // UseState para poder visualizar e alterar o chat icon
    const [chatIcon, setChatIcon] = useState(ChatBubbleOutlineOutlinedIcon);

    // UseState com as informações do usuário, recebidas no useEffect ao criar o componente
    const [usuario, setUsuario] = useState({ id: 0, email: "", nome: "", senha: "", tipo_usuario: 0, visibilidade: 1, departamento: null })

    // UseState para poder visualizar e alterar a visibilidade do menu
    const [anchorEl, setAnchorEl] = useState(null);

    // Variável que é usada para saber se o menu está aberto ou não
    const open = Boolean(anchorEl);

    // Função para abrir o menu
    const handleClick = (event) => {
        setAnchorEl(event.currentTarget);
    };

    // Função para fechar o menu
    const handleClose = () => {
        setAnchorEl(null);
    };

    // Função para sair da conta do usuário
    const sair = () => {
        localStorage.removeItem("usuarioId");
    }

    useEffect(() => {
        toggleColorMode();
    }, [temaDark]);

    return (
        <>
            {/* Botão para abrir o menu */}
            <Tooltip title="Configurações">
                <IconButton
                    onClick={handleClick}
                    size="small"
                    aria-controls={open ? 'account-menu' : undefined}
                    aria-haspopup="true"
                    aria-expanded={open ? 'true' : undefined}
                >
                    <Avatar sx={{ width: 40, height: 40, backgroundColor: "primary.main", border: '1px solid rgba(255, 255, 255, 0.5)', color: 'rgba(255, 255, 255, 0.9)' }}></Avatar>
                </IconButton>
            </Tooltip>

            {/* Menu (modal) */}
            <Menu
                id="basic-menu"
                anchorEl={anchorEl}
                open={open}
                onClose={handleClose}
                MenuListProps={{
                    'aria-labelledby': 'basic-button',
                }}
            >
                <Box className="w-52">
                    {/* Itens do menu */}
                    <Typography className='px-4 pt-1.5' color={'text.primary'} fontSize={FontConfig.medium} sx={{ fontWeight: 600 }}>{usuario.nome}</Typography>
                    {usuario.departamento != null ? <Typography className='px-4' color={'text.secondary'} fontSize={FontConfig.medium}>{usuario.departamento.nome}</Typography> : null}
                    <MenuItem className='gap-2' onClick={() => { handleClose(); navigate("/notificacao") }}>
                        <NotificationsOutlinedIcon />
                        Notificações
                    </MenuItem>

                    {/* Divisão de um item clicável e outro no modal */}
                    <div className='w-full flex justify-center pt-1.5'>
                        <hr className='w-10/12 my-1.5' />
                    </div>

                    <MenuItem className='gap-2' onClick={handleClose}>
                        <BorderColorOutlinedIcon />
                        <Typography color={'text.primary'} fontSize={FontConfig.medium} sx={{ fontWeight: 500 }}>Escopos</Typography>
                    </MenuItem>

                    {/* Divisão de um item clicável e outro no modal */}
                    <div className='w-full flex justify-center'>
                        <hr className='w-10/12 my-1.5' />
                    </div>

                    <MenuItem className='gap-2' onClick={() => { handleClose(); navigate("/chat") }}>
                        {chatIcon == ChatBubbleOutlineOutlinedIcon ? <ChatBubbleOutlineOutlinedIcon /> : <MarkChatUnreadOutlinedIcon />}
                        Chats
                    </MenuItem>
                    <Box className='w-full flex gap-2 px-4 items-center justify-center ml-4 mt-1'>
                        {/* <Typography color={'text.primary'} fontSize={FontConfig.medium} sx={{ fontWeight: 500 }}>Tema</Typography> */}
                        <FormControlLabel control={<MaterialUISwitch checked={temaDark} onChange={() => { setTemaDark(!temaDark); }} sx={{ m: 1 }} />} />
                    </Box>

                    {/* Link para deslogar do sistema */}
                    <Typography className='px-4 pt-1.5' color={'icon.main'} variant="body2" fontSize={FontConfig.medium} align="right" sx={{ fontWeight: 600, mt: '-16px' }}>
                        <Link to={"/login"} onClick={sair} >
                            Sair
                        </Link>
                    </Typography>
                </Box>
            </Menu>
        </>
    )
}

export default UserModal;