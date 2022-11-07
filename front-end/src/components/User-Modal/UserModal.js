import React, { useState } from 'react';
import { Menu, MenuItem, Tooltip, IconButton, Avatar, Typography, Divider, Button } from '@mui/material/';
// import NotificationsIcon from '@mui/icons-material';

import FontConfig from '../../service/FontConfig';

const UserModal = () => {
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
                <Typography className='px-4 pt-1.5' color={'text.primary'} fontSize={FontConfig.medium} sx={{ fontWeight: 600 }}>Nome Sobrenome</Typography>
                <Typography className='px-4 pb-1.5' color={'text.secondary'} fontSize={FontConfig.medium}>Departamento</Typography>
                <MenuItem onClick={handleClose}>
                    Notificações
                    </MenuItem>
                {/* <Divider  variant="middle" /> */}
                <MenuItem onClick={handleClose}>Escopos</MenuItem>
                {/* <Divider  variant="middle" /> */}
                <MenuItem onClick={handleClose}>Chats</MenuItem>
                <Typography className='px-4 pt-1.5 ' color={'primary.main'} variant="body2" fontSize={FontConfig.medium} align="right" sx={{ fontWeight: 600 }}><span className='hover:cursor-pointer'>Sair</span> </Typography>
            </Menu>
        </>
    )
}

export default UserModal;