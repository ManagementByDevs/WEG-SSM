import React from "react";

import { useLocation } from "react-router-dom";

import { Box, IconButton } from "@mui/material";

import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Caminho from "../../components/Caminho/Caminho";
import DetalhesProposta from "../../components/DetalhesProposta/DetalhesProposta";

import SaveAltOutlinedIcon from "@mui/icons-material/SaveAltOutlined";

const DetalhesPropostaPagina = () => {
  // Location utilizado para pegar os dados da demanda
  const location = useLocation();

  console.log(location.state);
  return (
    <FundoComHeader>
      <Box className="p-2">
        <Box className="flex w-full relative">
          <Caminho />
          <Box
            className=" absolute"
            sx={{ top: "10px", right: "20px", cursor: "pointer" }}
          >
            <IconButton>
              <SaveAltOutlinedIcon
                id="segundo"
                fontSize="large"
                className="delay-120 hover:scale-110 duration-600"
                sx={{ color: "icon.main" }}
              />
            </IconButton>
          </Box>
        </Box>
        <DetalhesProposta proposta={location.state} />
      </Box>
    </FundoComHeader>
  );
};

export default DetalhesPropostaPagina;
