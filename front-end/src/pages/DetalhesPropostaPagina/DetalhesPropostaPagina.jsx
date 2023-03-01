import React, { useContext } from "react";

import { useLocation } from "react-router-dom";

import { Box, IconButton, Button, Tooltip } from "@mui/material";

import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Caminho from "../../components/Caminho/Caminho";
import DetalhesProposta from "../../components/DetalhesProposta/DetalhesProposta";

import SaveAltOutlinedIcon from "@mui/icons-material/SaveAltOutlined";
import BookmarkAddIcon from "@mui/icons-material/BookmarkAdd";

import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";

const DetalhesPropostaPagina = () => {
  // Location utilizado para pegar os dados da demanda
  const location = useLocation();

  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  return (
    <FundoComHeader>
      <Box className="relative p-2">
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
      <Box className="absolute bottom-4 right-6  p-1">
        <Tooltip title={texts.detalhesPropostaPagina.adicionarAPauta}>
          <Button variant="contained" sx={{ borderRadius: "9999px" }}>
            <BookmarkAddIcon sx={{ fontSize: "28px", color: "text.white" }} />
          </Button>
        </Tooltip>
      </Box>
    </FundoComHeader>
  );
};

export default DetalhesPropostaPagina;
