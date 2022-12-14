import React, { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";

import { Box } from "@mui/material";

import SaveAltOutlinedIcon from "@mui/icons-material/SaveAltOutlined";

import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Caminho from "../../components/Caminho/Caminho";
import DetalhesDemanda from "../../components/DetalhesDemanda/DetalhesDemanda";

const DetalhesDemandaPagina = () => {
  const location = useLocation();
  
  const [dados, setDados] = useState(location.state);

  return (
    <FundoComHeader>
      <Box className="p-2">
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
        <DetalhesDemanda
          dados={dados}
          setDados={setDados}
          botao="sim"
          salvar="sim"
        />
      </Box>
    </FundoComHeader>
  );
};

export default DetalhesDemandaPagina;
