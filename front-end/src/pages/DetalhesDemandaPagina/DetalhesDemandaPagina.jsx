import React, { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";

import { Box } from "@mui/material";

import SaveAltOutlinedIcon from "@mui/icons-material/SaveAltOutlined";

import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Caminho from "../../components/Caminho/Caminho";
import DetalhesDemanda from "../../components/DetalhesDemanda/DetalhesDemanda";

const DetalhesDemandaPagina = () => {
  const [dados, setDados] = useState({
    titulo: "Sistema de Gestão de Demandas",
    problema:
      "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries",
    proposta:
      "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen  book. It has survived not only five centuries is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since  the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries",
    frequencia: "Lorem Ipsum is simply dummy text of the printing and",
    beneficios: [
      {
        tipo: "Real",
        valorMensal: "300,00",
        moeda: "BR",
        memoriaCalculo:
          "aqui vai a memória de cálculo, onde conterá as informações necessárias dele",
        visible: true,
      },
    ],
  });

  const location = useLocation();

  const showDetails = () => {
    dados.beneficios[0].teste = "a;";
  };

  useEffect(() => {
    setDados(location.state);
  }, [])

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
              onClick={showDetails}
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
