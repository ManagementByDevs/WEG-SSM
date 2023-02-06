import React, { useEffect, useContext } from "react";

import { Box } from "@mui/material";

import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Caminho from "../../components/Caminho/Caminho";
import BarraProgressaoDemanda from "../../components/BarraProgressaoDemanda/BarraProgressaoDemanda";
import Ajuda from "../../components/Ajuda/Ajuda";

import FontContext from "../../service/FontContext";

import Tour from "reactour";

const CriarDemanda = () => {
  // const [isTourDemandasOpen, setIsTourDemandasOpen] = useState(false);

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  return (
    <FundoComHeader>
      <Box className="p-2">
        <Caminho feedback={true} />
        <Box className="w-full flex justify-center">
          <Box className="w-5/6">
            <BarraProgressaoDemanda steps={["Dados", "Benefícios", "Anexos"]} />
          </Box>
        </Box>
      </Box>
    </FundoComHeader>
  );
};

export default CriarDemanda;
