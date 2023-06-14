import React from "react";

import { Box } from "@mui/material";

import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Caminho from "../../components/Caminho/Caminho";
import CriarPautaContent from "../../components/CriarPautaContent/CriarPautaContent";

const CriarPauta = () => {
  return (
    <FundoComHeader>
      <Box className="p-2">
        <Caminho feedback={true} />
        <Box className="w-full flex justify-center mt-4">
          <Box className="w-5/6 relative">
            <CriarPautaContent />
          </Box>
        </Box>
      </Box>
    </FundoComHeader>
  );
};

export default CriarPauta;
