import React, { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";

import { Box, IconButton } from "@mui/material";

import SaveAltOutlinedIcon from "@mui/icons-material/SaveAltOutlined";

import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Caminho from "../../components/Caminho/Caminho";
import DetalhesDemanda from "../../components/DetalhesDemanda/DetalhesDemanda";

import UsuarioService from "../../service/usuarioService";

const DetalhesDemandaPagina = () => {
  const location = useLocation();

  const [dados, setDados] = useState(location.state);

  // Usuário que está logado no sistema
  const [usuario, setUsuario] = useState({
    id: 0,
    email: "",
    nome: "",
    senha: "",
    tipo_usuario: 0,
    visibilidade: 1,
    departamento: null,
  });

  useEffect(() => {
    setDados(location.state);
    buscarUsuario();
  }, []);

  const buscarUsuario = () => {
    UsuarioService.getUsuarioById(
      parseInt(localStorage.getItem("usuarioId"))
    ).then((e) => {
      setUsuario(e);
    });
  };

  const updateDemandaProps = (demanda) => {
    setDados(demanda);
    location.state = demanda;
  };

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
                fontSize="large"
                className="delay-120 hover:scale-110 duration-600"
                sx={{ color: "icon.main" }}
              />
            </IconButton>
          </Box>
        </Box>
        <DetalhesDemanda
          dados={dados}
          usuario={usuario}
          setDados={setDados}
          botao="sim"
          salvar="sim"
          updateDemandaProps={updateDemandaProps}
        />
      </Box>
    </FundoComHeader>
  );
};

export default DetalhesDemandaPagina;
