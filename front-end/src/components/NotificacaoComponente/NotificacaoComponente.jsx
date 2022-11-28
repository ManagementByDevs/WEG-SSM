import React, { } from 'react';
import { Box, Divider, getInitColorSchemeScript } from '@mui/material';

import FontConfig from '../../service/FontConfig';

import HighlightOffOutlinedIcon from '@mui/icons-material/HighlightOffOutlined';

const NotificacaoComponente = (props) => {

    const lido = props.lido;

    return (
        <Box>
        {
            lido === true ?
                <Box className='w-9/10 flex h-9/10 rounded border-solid border border-l-6 px-1 py-1.5' sx={{ borderColor: 'tertiary.main', borderLeftColor: 'primary.main', backgroundColor: 'visualizado.true', cursor: 'default' }}>
                    <Box className='flex items-center mr-1' sx={{ fontSize: FontConfig.title }}>
                        <HighlightOffOutlinedIcon sx={{ fontSize: '60px' }} />
                    </Box>
                    <Divider orientation="vertical" variant="middle" flexItem sx={{ borderColor: 'tertiary.main' }} />
                    <Box className='flex flex-col p-3'>
                        <Box className='flex justify-between'>
                            <Box color={'text.primary'} fontSize={FontConfig.big} fontWeight={'600'}>
                                Titulo Notificacao
                            </Box>
                            <Box color={'text.secondary'} fontSize={FontConfig.default}>
                                16:00 - 10/10/2021
                            </Box>
                        </Box>
                        <Box className='text-justify w-11/12' color={'text.secondary'} fontSize={FontConfig.default}>
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore
                        </Box>
                    </Box>
                </Box>
                :
                <Box className='w-9/10 flex h-9/10 rounded border-solid border border-l-6 px-1 py-1.5' sx={{ borderColor: 'tertiary.main', borderLeftColor: 'primary.main', backgroundColor: 'visualizado.false', cursor: 'pointer' }}>
                    <Box className='flex items-center mr-1' sx={{ fontSize: FontConfig.title }}>
                        <HighlightOffOutlinedIcon sx={{ fontSize: '60px' }} />
                    </Box>
                    <Divider orientation="vertical" variant="middle" flexItem sx={{ borderColor: 'tertiary.main' }} />
                    <Box className='flex flex-col p-3'>
                        <Box className='flex justify-between'>
                            <Box color={'text.primary'} fontSize={FontConfig.big} fontWeight={'600'}>
                                Titulo Notificacao
                            </Box>
                            <Box color={'text.secondary'} fontSize={FontConfig.default}>
                                16:00 - 10/10/2021
                            </Box>
                        </Box>
                        <Box className='text-justify w-11/12' color={'text.secondary'} fontSize={FontConfig.default}>
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore
                        </Box>
                    </Box>
                </Box>
        }
    </Box>
    );
}

export default NotificacaoComponente;