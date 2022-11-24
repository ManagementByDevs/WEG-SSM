import React from "react";
import { Box } from "@mui/material";

import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Caminho from "../../components/Caminho/Caminho";

import FontConfig from "../../service/FontConfig";


import SearchOutlinedIcon from '@mui/icons-material/SearchOutlined';

const Chat = () => {
    return (
        <FundoComHeader>
            <Box className='p-2'>
                <Caminho />
                <Box className='w-full flex justify-center items-center'>
                    <Box className='flex justify-evenly items-center rounded border mt-4' sx={{ width: '100rem', height: '50rem' }}>
                        <Box className='flex justify-center items-center rounded border' sx={{ width: '20%', height: '95%' }}>
                            <Box className='flex justify-between border px-3 py-1' sx={{ backgroundColor: 'input.main', width: "80%" }}>

                                {/* Input de pesquisa */}
                                <Box className='w-full' component="input" sx={{ backgroundColor: 'input.main', outline: 'none', color: "text.primary", fontSize: FontConfig.medium }} placeholder="Pesquisar por título..." />

                                {/* Container para os ícones */}
                                <Box className='flex gap-2'>

                                    {/* Ícone de pesquisa */}
                                    <SearchOutlinedIcon sx={{ color: 'text.secondary' }} />
                                </Box>
                            </Box>
                        </Box>
                        <Box className='flex justify-center items-center rounded border' sx={{ width: '75%', height: '95%' }}>
                        </Box>
                    </Box>
                </Box>
            </Box>
        </FundoComHeader>
    )
}

export default Chat;