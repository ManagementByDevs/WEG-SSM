import React from "react";
import { Box, Avatar, Typography } from "@mui/material";

import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Caminho from "../../components/Caminho/Caminho";
import Contato from "../../components/Contato/Contato";

import FontConfig from "../../service/FontConfig";

import MoreVertOutlinedIcon from '@mui/icons-material/MoreVertOutlined';
import SearchOutlinedIcon from '@mui/icons-material/SearchOutlined';

const Chat = () => {
    return (
        <FundoComHeader>
            <Box className='p-2'>
                <Caminho />
                <Box className='w-full flex justify-center items-center'>
                    <Box className='flex justify-evenly items-center rounded border mt-4' sx={{ width: '100rem', height: '50rem' }}>
                        <Box className='flex items-center rounded border flex-col gap-3 overflow-y-auto overflow-x-hidden' sx={{ width: '20%', height: '95%' }}>
                            <Box className='flex border px-3 py-1 m-4 rounded-lg' sx={{ backgroundColor: 'input.main', width: "90%", height: '5%' }}>
                                <Box className='w-full' component="input" sx={{ backgroundColor: 'input.main', outline: 'none', color: "text.primary", fontSize: FontConfig.medium }} placeholder="Pesquisar por nome..." />
                                <Box className='flex gap-2'>
                                    <SearchOutlinedIcon sx={{ color: 'text.secondary' }} />
                                </Box>
                            </Box>
                            <Contato />
                            <Contato />
                            <Contato />
                            <Contato />
                            <Contato />
                            <Contato />
                            <Contato />
                            <Contato />
                            <Contato />
                            <Contato />
                            <Contato />
                            <Contato />
                            <Contato />
                        </Box>
                        <Box className='flex flex-col items-center justify-between rounded border' sx={{ width: '75%', height: '95%' }}>
                            <Box className='flex justify-between items-center w-full' sx={{ backgroundColor: 'primary.main', height: '10%' }}>
                                <Box className="flex items-center">
                                    <Avatar className='ml-7' sx={{ width: '3.5rem', height: '3.5rem' }} />
                                    <Box className='flex flex-col ml-3' sx={{ cursor: 'default' }}>
                                        <Typography className='ml-2' fontSize={FontConfig.veryBig} fontWeight="600">Nome</Typography>
                                        <Typography fontSize={FontConfig.small}>Cargo</Typography>
                                    </Box>
                                </Box>
                                <Box className="rounded-3xl p-0.5 mr-7 delay-120 hover:scale-110 duration-300" sx={{ cursor: 'pointer' }}>
                                    <MoreVertOutlinedIcon fontSize="large" />
                                </Box>
                            </Box>
                            <Box className="flex flex-col mt-4" sx={{ width: '95%', height: '85%' }}>

                            </Box>
                            <Box className='flex border px-3 py-1 m-4 rounded items-center' sx={{ backgroundColor: 'input.main', width: "90%", height: '6.5%' }}>
                                <Box className='w-full' component="input" sx={{ backgroundColor: 'input.main', outline: 'none', color: "text.primary", fontSize: FontConfig.medium }} placeholder="Escreva sua mensagem..." />
                                <Box className='flex gap-2'>
                                    <SearchOutlinedIcon sx={{ color: 'text.secondary' }} />
                                </Box>
                            </Box>
                        </Box>
                    </Box>
                </Box>
            </Box>
        </FundoComHeader>
    )
}

export default Chat;