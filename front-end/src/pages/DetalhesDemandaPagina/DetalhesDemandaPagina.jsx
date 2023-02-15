import React, { useState, useEffect, useContext } from "react";
import { Box, IconButton } from "@mui/material";

import { useLocation } from "react-router-dom";

import SaveAltOutlinedIcon from "@mui/icons-material/SaveAltOutlined";

import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Caminho from "../../components/Caminho/Caminho";
import DetalhesDemanda from "../../components/DetalhesDemanda/DetalhesDemanda";
import FontContext from "../../service/FontContext";
import Ajuda from "../../components/Ajuda/Ajuda";

import UsuarioService from "../../service/usuarioService";
import ExportPdfService from "../../service/exportPdfService";

import Tour from "reactour";

const DetalhesDemandaPagina = () => {
  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // Location utilizado para pegar os dados da demanda
  const location = useLocation();

  // Variável utilizada para receber os dados de uma demanda
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

  // UseEffect utilizado para buscar o usuário do sistema e pegar os dados do state
  useEffect(() => {
    setDados(location.state);
    buscarUsuario();
  }, []);

  // Função utilizada para buscar o usuário que está logado no sistema
  const buscarUsuario = () => {
    UsuarioService.getUsuarioById(
      parseInt(localStorage.getItem("usuarioId"))
    ).then((e) => {
      setUsuario(e);
    });
  };

  // Função utilizada para alterar dados da demanda
  const updateDemandaProps = (demanda) => {
    setDados(demanda);
    location.state = demanda;
  };

  // Variável utilizada para o tour
  const [isTourOpen, setIsTourOpen] = useState(false);

  // Lista de mensagens utilizadas no tour
  const steps = [
    {
      selector: "#primeiro",
      content:
        "Essa é a página de detalhes da demanda. Aqui você pode ver todos os detalhes da demanda selecionada.",
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#segundo",
      content: "Cicando aqui, você consegue baixar em PDF essa demanda.",
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#terceiro",
      content:
        "Clicando neste lápis, você consegue estar editando as informações.",
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
  ];

  const baixarDemanda = () => {
    ExportPdfService.exportDemanda(dados.id).then((response) => {
      let blob = new Blob([response], { type: 'application/pdf' });
      let url = URL.createObjectURL(blob);
      let link = document.createElement('a');
      link.href = url;
      link.download = 'pdf_demanda.pdf';
      link.click();
    })
  }

  return (
    <FundoComHeader>
      {/* Tour ao usuário */}
      <Tour
        steps={steps}
        isOpen={isTourOpen}
        onRequestClose={() => setIsTourOpen(false)}
        accentColor="#00579D"
        rounded={10}
        showCloseButton={false}
      />
      <Ajuda onClick={() => setIsTourOpen(true)} />
      <Box className="p-2">
        <Box className="flex w-full relative">
          <Caminho />
          <Box
            className=" absolute"
            sx={{ top: "10px", right: "20px", cursor: "pointer" }}
          >
            <IconButton
              onClick={baixarDemanda}
            >
              <SaveAltOutlinedIcon
                id="segundo"
                fontSize="large"
                className="delay-120 hover:scale-110 duration-600"
                sx={{ color: "icon.main" }}
              />
            </IconButton>
          </Box>
        </Box>
        {/* Mostrar os dados da demanda */}
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
