import React, { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";

import VLibras from "@djpfs/react-vlibras";

import { Box } from "@mui/material";

import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Caminho from "../../components/Caminho/Caminho";
import BarraProgressaoProposta from "../../components/BarraProgressaoProposta/BarraProgressaoProposta";

/** Tela para criação de uma proposta ( chama o componente para criação ) */
const CriarProposta = ({ lendo = false }) => {

  /** Location utilizado para mandar os dados para a variável "dados" */
  const location = useLocation();

  /** Variável dados utilizada para armazenar os dados da proposta */
  const [dados, setDados] = useState(location.state);

  /** UseEffect utilizado para setar os dados na variável assim que haja alguma alteração */
  useEffect(() => {
    setDados(location.state);
  }, []);

  return (
    <FundoComHeader lendo={lendo}>
      <VLibras forceOnload />
      <Box className="p-2">
        <Caminho lendo={lendo} />
        <Box className="w-full flex justify-center">
          <Box className="w-5/6">
            {/* Chamando componente de criação da proposta mandando os dados */}
            <BarraProgressaoProposta dados={dados} lendo={lendo} />
          </Box>
        </Box>
      </Box>
    </FundoComHeader>
  );
};

export default CriarProposta;