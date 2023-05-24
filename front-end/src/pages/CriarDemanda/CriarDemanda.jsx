import React from "react";

import VLibras from "@djpfs/react-vlibras";

import { Box } from "@mui/material";

import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Caminho from "../../components/Caminho/Caminho";
import BarraProgressaoDemanda from "../../components/BarraProgressaoDemanda/BarraProgressaoDemanda";

/** Componente de página para a criação de demanda, chamando a barra de progressão para as etapas de criação */
const CriarDemanda = ({ lendo = false, texto = "", setTexto = () => {} }) => {
  return (
    <FundoComHeader lendo={lendo} texto={texto} setTexto={setTexto}>
      <VLibras forceOnload />
      <Box className="p-2">
        <Caminho
          feedback={true}
          lendo={lendo}
          texto={texto}
          setTexto={setTexto}
        />
        <Box className="w-full flex justify-center">
          <Box className="w-5/6 relative">
            {/* Chamada do componente principal para criação da demanda */}
            <BarraProgressaoDemanda
              lendo={lendo}
              texto={texto}
              setTexto={setTexto}
            />
          </Box>
        </Box>
      </Box>
    </FundoComHeader>
  );
};

export default CriarDemanda;
