import React from 'react'

import { Box, Button } from '@mui/material'

import SearchOutlinedIcon from "@mui/icons-material/SearchOutlined";
import SwapVertIcon from "@mui/icons-material/SwapVert";
import FilterAltOutlinedIcon from "@mui/icons-material/FilterAltOutlined";

import Caminho from '../../components/Caminho/Caminho'
import FundoComHeader from '../../components/FundoComHeader/FundoComHeader'
import Escopo from '../../components/Escopo/Escopo';

import FontConfig from '../../service/FontConfig'

const Escopos = () => {
    return (
        <FundoComHeader>
            <Box className='p-2'>
                <Caminho />
                {/* Div pegando width inteira para fazer o espaçamento das bordas */}
                <Box className='flex justify-center w-full'>
                    {/* Container conteudo */}
                    <Box className="w-11/12 mt-10">
                        {/* Container para o input e botão de filtrar */}
                        <Box className="flex gap-4 w-2/4">
                            {/* Input de pesquisa */}
                            <Box
                                className="flex justify-between border px-3 py-1"
                                sx={{ backgroundColor: "input.main", width: "50%" }}
                            >
                                {/* Input de pesquisa */}
                                <Box
                                    className="w-full"
                                    component="input"
                                    sx={{
                                        backgroundColor: "input.main",
                                        outline: "none",
                                        color: "text.primary",
                                        fontSize: FontConfig.medium,
                                    }}
                                    placeholder="Pesquisar por título..."
                                />

                                {/* Container para os ícones */}
                                <Box className="flex gap-2">
                                    {/* Ícone de pesquisa */}
                                    <SearchOutlinedIcon sx={{ color: "text.secondary" }} />

                                    {/* Ícone de ordenação */}
                                    <SwapVertIcon
                                        onClick={() => { }}
                                        className="cursor-pointer"
                                        sx={{ color: "text.secondary" }}
                                    />
                                </Box>
                            </Box>

                            {/* Botão de filtrar */}
                            <Button
                                className='flex gap-1'
                                sx={{
                                    backgroundColor: "primary.main",
                                    color: "text.white",
                                    fontSize: FontConfig.default,
                                }}
                                onClick={() => { }}
                                variant="contained"
                                disableElevation
                            >
                                Filtrar <FilterAltOutlinedIcon />
                            </Button>
                        </Box>
                        <Box className='mt-6'>

                            <Escopo porcentagem="75%" />
                        </Box>
                    </Box>

                </Box>
            </Box>
        </FundoComHeader>
    )
}

export default Escopos