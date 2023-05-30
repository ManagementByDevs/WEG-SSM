import React, { useContext } from "react";

import VLibras from "@djpfs/react-vlibras";

import { Box } from "@mui/material";

import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Caminho from "../../components/Caminho/Caminho";
import BarraProgressaoDemanda from "../../components/BarraProgressaoDemanda/BarraProgressaoDemanda";

import FontContext from "../../service/FontContext";

// Tela de base que mostra a edição do escopo
const EditarEscopo = ({ lendo = false}) => {
  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  return (
    <FundoComHeader lendo={lendo}>
      <VLibras forceOnload />
      <Box className="p-2">
        <Caminho lendo={lendo}/>
        <Box className="w-full flex justify-center">
          <Box className="w-5/6">
            <BarraProgressaoDemanda
              steps={["Dados", "Benefícios", "Anexos"]}
              lendo={lendo}
            />
          </Box>
        </Box>
      </Box>
    </FundoComHeader>
  );
};

export default EditarEscopo;
