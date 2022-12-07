import React, { useState, useContext, useEffect } from "react";

import {
    Box,
    Typography,
    Button,
    Divider,
    TextareaAutosize,
} from "@mui/material";

import SaveAltOutlinedIcon from "@mui/icons-material/SaveAltOutlined";
import OtherHousesIcon from '@mui/icons-material/OtherHouses';

import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Caminho from "../../components/Caminho/Caminho";

import FontConfig from "../../service/FontConfig";

const DetalhesAta = (props) => {

    return (
        // Começo com o header da página
        <FundoComHeader>
            <Box className="p-2">
                {/* caminho da página */}
                <Box className="flex w-full relative">
                    <Caminho />
                    <Box
                        className=" absolute"
                        sx={{ top: "10px", right: "20px", cursor: "pointer" }}
                    >
                        <SaveAltOutlinedIcon
                            fontSize="large"
                            className="delay-120 hover:scale-110 duration-300"
                            sx={{ color: "icon.main" }}
                        />
                    </Box>
                </Box>
                <Box className="flex flex-col justify-center relative items-center mt-3">
                    <Box className="flex flex-col gap-5 border rounded relative p-10 drop-shadow-lg" sx={{ width: "55rem" }}>
                        <Box className="flex justify-center flex-col" >
                            <Typography
                                fontSize={FontConfig.title}
                                sx={{
                                    fontWeight: "600",
                                    cursor: "default",
                                    inlineSize: "800px",
                                    overflowWrap: "break-word",
                                    textAlign: "center"
                                }}
                                color="primary.main"
                            >
                                Ata
                            </Typography>
                            <Typography sx={{
                                fontWeight: "600",
                                cursor: "default",
                                marginTop: '1%'
                            }}>
                                Número Sequencial: 01/2022
                            </Typography>
                            <Typography sx={{
                                fontWeight: "600",
                                cursor: "default",
                                marginTop: '1%'
                            }}>
                                Data: 06/12/2022
                            </Typography>
                            <Typography sx={{
                                fontWeight: "600",
                                cursor: "default",
                                marginTop: '1%'
                            }}>
                                Início: 14:30 Horas
                            </Typography>
                            <Typography sx={{
                                fontWeight: "600",
                                cursor: "default",
                                marginTop: '1%'
                            }}>
                                Fim: 15:30 Horas
                            </Typography>
                            <Divider sx={{ marginTop: '1%' }} />
                        </Box>
                        <Box>
                            <Typography
                                fontSize={FontConfig.title}
                                sx={{
                                    fontWeight: "600",
                                    cursor: "default",
                                    inlineSize: "800px",
                                    overflowWrap: "break-word",
                                    textAlign: "center"
                                }}
                                color="primary.main"
                            >
                                Sumário
                            </Typography>
                            <Box className="flex justify-evenly align-center mt-5">
                                <Typography fontSize={FontConfig.big}
                                    sx={{
                                        textDecoration: "underline",
                                        cursor: "pointer",
                                        color: "primary.main"
                                    }}>
                                    Aqui vai o título 1
                                </Typography>
                                <Typography fontSize={FontConfig.big}
                                    sx={{
                                        textDecoration: "underline",
                                        cursor: "pointer",
                                        color: "primary.main"
                                    }}>
                                    Aqui vai o título 2
                                </Typography>
                                <Typography fontSize={FontConfig.big}
                                    sx={{
                                        textDecoration: "underline",
                                        cursor: "pointer",
                                        color: "primary.main"
                                    }}>
                                    Aqui vai o título 3
                                </Typography>
                            </Box>
                            <Box className="flex justify-evenly align-center mt-5">
                                <Typography fontSize={FontConfig.big}
                                    sx={{
                                        textDecoration: "underline",
                                        cursor: "pointer",
                                        color: "primary.main"
                                    }}>
                                    Aqui vai o título 1
                                </Typography>
                                <Typography fontSize={FontConfig.big}
                                    sx={{
                                        textDecoration: "underline",
                                        cursor: "pointer",
                                        color: "primary.main"
                                    }}>
                                    Aqui vai o título 2
                                </Typography>
                                <Typography fontSize={FontConfig.big}
                                    sx={{
                                        textDecoration: "underline",
                                        cursor: "pointer",
                                        color: "primary.main"
                                    }}>
                                    Aqui vai o título 3
                                </Typography>
                            </Box>
                        </Box>
                    </Box>
                    <Box className="flex fixed justify-end"
                        sx={{ width: "20rem", bottom: "20px", right: "20px" }}>
                        <Box className="flex justify-around w-full">
                            <Button
                                sx={{
                                    backgroundColor: "primary.main",
                                    color: "text.white",
                                    fontSize: FontConfig.default,
                                }}
                                variant="contained"
                            >
                                Voltar
                            </Button>
                            <Button
                                sx={{
                                    backgroundColor: "primary.main",
                                    color: "text.white",
                                    fontSize: FontConfig.default,
                                }}
                                variant="contained"
                            >
                                <OtherHousesIcon></OtherHousesIcon>
                            </Button>
                            <Button
                                sx={{
                                    backgroundColor: "primary.main",
                                    color: "text.white",
                                    fontSize: FontConfig.default,
                                }}
                                variant="contained"
                            >
                                Próximo
                            </Button>
                        </Box>
                    </Box>
                </Box>
            </Box>
        </FundoComHeader>
    );
}

export default DetalhesAta;


