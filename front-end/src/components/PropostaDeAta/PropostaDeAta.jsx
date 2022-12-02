import React, { useState } from "react";

import { Box, Typography } from "@mui/material";

import Caminho from "../../components/Caminho/Caminho";

import FontConfig from "../../service/FontConfig";

import SaveAltOutlinedIcon from "@mui/icons-material/SaveAltOutlined";

const PropostaDeAta = (props) => {

    // variáveis para estilo do componente

    const containerProposta = {

    }

    // useState para pegar dados fixos da proposta

    const [dadosProposta, setDadosProposta] = useState(
        {
            titulo: "Dificuldade para o gerenciamento",
            solicitante: "Fulano de Tal - WEG Tintas",
            objetivo: "Lorem ipsum, dolor sit amet consectetur adipisicing elit. Unde vero omnis similique beatae assumenda obcaecati vitae ab consequatur alias eligendi! Nemo voluptates natus possimus qui perferendis, ducimus itaque at porro?",
        }
    )

    return (
        <Box sx={containerProposta}>
            {/* box para parte d cima da tela com o caminho e o ícone de baixar pdf */}
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

            {/* container principal onde estão as informacoes sobre a ata */}
            <Box className="flex flex-col justify-center relative items-center mt-3">
                <Box className="flex flex-col gap-5 border rounded relative p-10 drop-shadow-lg"
                    sx={{ width: "55rem" }}>
                    <Typography>
                        {dadosProposta.titulo}
                    </Typography>
                    
                    <Typography>
                        {dadosProposta.solicitante}
                    </Typography>

                    <Typography>
                        {dadosProposta.objetivo}
                    </Typography>
                </Box>
            </Box>

        </Box>
    );
}

export default PropostaDeAta;