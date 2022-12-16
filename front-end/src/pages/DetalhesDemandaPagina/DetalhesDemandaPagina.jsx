import React, { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";

import { Box } from "@mui/material";

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

  const showDetails = () => {
    dados.beneficios[0].teste = "a;";
  };

  useEffect(() => {
    setDados(location.state);

    // let listaAnexos = [];
    // for (let arquivo of location.state.anexo) {
    //   listaAnexos.push(new File([arquivo.dados], arquivo.nome, { type: arquivo.tipo }));
    // }
    // setDados({ ...dados, anexo: listaAnexos });
    buscarUsuario();
  }, []);

  const buscarUsuario = () => {
    UsuarioService.getUsuarioById(
      parseInt(localStorage.getItem("usuarioId"))
    ).then((e) => {
      setUsuario(e);
    });
  }

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
          usuario={usuario}
          setDados={setDados}
          botao="sim"
          salvar="sim"
        />
      </Box>
    </FundoComHeader>
  );
};

export default DetalhesDemandaPagina;
