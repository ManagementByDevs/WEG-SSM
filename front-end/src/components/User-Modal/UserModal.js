import React, { useState } from 'react';
import { Menu, MenuItem, Tooltip, IconButton, Avatar, Typography } from '@mui/material/';

import NotificationsOutlinedIcon from '@mui/icons-material/NotificationsOutlined';
import BorderColorOutlinedIcon from '@mui/icons-material/BorderColorOutlined';
import ChatBubbleOutlineOutlinedIcon from '@mui/icons-material/ChatBubbleOutlineOutlined';
import MarkChatUnreadOutlinedIcon from '@mui/icons-material/MarkChatUnreadOutlined';

import FontConfig from '../../service/FontConfig';
import { Link } from 'react-router-dom';
import { Box } from '@mui/system';

const UserModal = () => {
    // UseState para poder visualizar e alterar o chat icon
    const [chatIcon, setChatIcon] = useState(ChatBubbleOutlineOutlinedIcon);

    // UseState para poder visualizar e alterar o usuário
    const [user, setUser] = useState('Nome Sobrenome');

    // UseState para poder visualizar e alterar o departamento do usuário
    const [departamento, setDepartamento] = useState("Departamento");

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
                    <Typography className='px-4 pt-1.5' color={'text.primary'} fontSize={FontConfig.medium} sx={{ fontWeight: 600 }}>{user}</Typography>
                    <Typography className='px-4 pb-1.5' color={'text.secondary'} fontSize={FontConfig.medium}>{departamento}</Typography>
                    <MenuItem className='gap-2' onClick={handleClose}>
                        <NotificationsOutlinedIcon />
                        Notificações
                    </MenuItem>

                    {/* Divisão de um item clicável e outro no modal */}
                    <div className='w-full flex justify-center'>
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

                    <MenuItem className='gap-2' onClick={handleClose}>
                        {chatIcon == ChatBubbleOutlineOutlinedIcon ? <ChatBubbleOutlineOutlinedIcon /> : <MarkChatUnreadOutlinedIcon />}
                        Chats
                    </MenuItem>

                    {/* Link para deslogar do sistema */}
                    <Typography className='px-4 pt-1.5 ' color={'icon.main'} variant="body2" fontSize={FontConfig.medium} align="right" sx={{ fontWeight: 600 }}>
                        <Link to={"/login"} >
                            Sair
                        </Link>
                    </Typography>
                </Box>
            </Menu>
        </>
    )
}

export default UserModal;