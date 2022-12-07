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
       <Caminho></Caminho>
    );
}

export default PropostaDeAta;