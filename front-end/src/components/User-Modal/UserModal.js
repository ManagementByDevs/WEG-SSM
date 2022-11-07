import React, { useState } from 'react';
import { Menu, MenuItem, Tooltip, IconButton, Avatar, Typography } from '@mui/material/';

import NotificationsOutlinedIcon from '@mui/icons-material/NotificationsOutlined';
import BorderColorOutlinedIcon from '@mui/icons-material/BorderColorOutlined';
import ChatBubbleOutlineOutlinedIcon from '@mui/icons-material/ChatBubbleOutlineOutlined';
import MarkChatUnreadOutlinedIcon from '@mui/icons-material/MarkChatUnreadOutlined';

import FontConfig from '../../service/FontConfig';
import { Link } from 'react-router-dom';

const UserModal = () => {
    const [chatIcon, setChatIcon] = useState(ChatBubbleOutlineOutlinedIcon);
    const [user, setUser] = useState('Nome Sobrenome');
    const [departamento, setDepartamento] = useState("Departamento");
    const [anchorEl, setAnchorEl] = useState(null);

    const open = Boolean(anchorEl);
    const handleClick = (event) => {
        setAnchorEl(event.currentTarget);
    };
    const handleClose = () => {
        setAnchorEl(null);
    };

    return (
        <>
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
            <Menu
                id="basic-menu"
                anchorEl={anchorEl}
                open={open}
                onClose={handleClose}
                MenuListProps={{
                    'aria-labelledby': 'basic-button',
                }}
                sx={{ padding: '12rem' }}
            >
                <Typography className='px-4 pt-1.5' color={'text.primary'} fontSize={FontConfig.medium} sx={{ fontWeight: 600 }}>{user}</Typography>
                <Typography className='px-4 pb-1.5' color={'text.secondary'} fontSize={FontConfig.medium}>{departamento}</Typography>
                <MenuItem className='gap-2' onClick={handleClose}>
                    <NotificationsOutlinedIcon />
                    Notificações
                </MenuItem>
                <div className='w-full flex justify-center'>
                    <hr className='w-10/12' />
                </div>
                <MenuItem className='gap-2' onClick={handleClose}>
                    <BorderColorOutlinedIcon />
                    Escopos</MenuItem>
                <div className='w-full flex justify-center'>
                    <hr className='w-10/12' />
                </div>
                <MenuItem className='gap-2' onClick={handleClose}>
                    {chatIcon == ChatBubbleOutlineOutlinedIcon ? <ChatBubbleOutlineOutlinedIcon /> : <MarkChatUnreadOutlinedIcon />}
                    Chats
                </MenuItem>
                <Typography className='px-4 pt-1.5 ' color={'primary.main'} variant="body2" fontSize={FontConfig.medium} align="right" sx={{ fontWeight: 600 }}>
                    <Link to={"/login"} >
                        Sair
                    </Link>
                </Typography>
            </Menu>
        </>
    )
}

export default UserModal;