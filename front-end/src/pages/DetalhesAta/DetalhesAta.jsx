import { Typography } from "@mui/material";
import React, { useState } from "react";


import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import PropostaDeAta from "../../components/PropostaDeAta/PropostaDeAta";

const DetalhesAta = (props) => {

    return (
        <FundoComHeader>
            <PropostaDeAta />
        </FundoComHeader>
    );
}

export default DetalhesAta;


