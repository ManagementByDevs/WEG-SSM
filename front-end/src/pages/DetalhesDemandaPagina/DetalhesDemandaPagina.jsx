import React, { useState, useEffect, useContext } from "react";
import { ClipLoader } from "react-spinners";
import { useLocation, useParams } from "react-router-dom";

import { Box, IconButton } from "@mui/material";

 
import Tour from "reactour";

import SaveAltOutlinedIcon from "@mui/icons-material/SaveAltOutlined";

import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Caminho from "../../components/Caminho/Caminho";
import DetalhesDemanda from "../../components/DetalhesDemanda/DetalhesDemanda";
import Ajuda from "../../components/Ajuda/Ajuda";

import TextLanguageContext from "../../service/TextLanguageContext";
import UsuarioService from "../../service/usuarioService";
import ExportPdfService from "../../service/exportPdfService";
import CookieService from "../../service/cookieService";
import DemandaService from "../../service/demandaService";

/** Página de detalhes de uma demanda, com a base para as informações (componente DetalhesDemanda) e opção de baixar */
const DetalhesDemandaPagina = () => {

  const paramsPath = useParams();

  // Context para alterar a linguagem do sistema
  const { texts } = useContext(TextLanguageContext);

  /** Location utilizado para pegar os dados da demanda */
  const location = useLocation();

  /** Variável utilizada para receber os dados de uma demanda */
  const [dados, setDados] = useState(null);

  /** Variável para esconder os dados da demanda enquanto busca as preferências do usuário */
  const [carregamento, setCarregamento] = useState(true);

  /** Usuário que está logado no sistema */
  const [usuario, setUsuario] = useState({
    id: 0,
    email: "",
    nome: "",
    senha: "",
    tipo_usuario: 0,
    visibilidade: 1,
    departamento: null,
  });

  /** Variável utilizada para o tour */
  const [isTourOpen, setIsTourOpen] = useState(false);

  /** Lista de mensagens utilizadas no tour */
  const [steps, setSteps] = useState([
    {
      selector: "#primeiro",
      content: texts.detalhesDemandaPagina.tour.tour1,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#segundo",
      content: texts.detalhesDemandaPagina.tour.tour2,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
  ]);

  /** UseEffect utilizado para buscar o usuário do sistema e pegar os dados do state */
  useEffect(() => {
    buscarDemanda(paramsPath.id);
    buscarUsuario();
  }, []);

  /** useEffect para o carregamento das preferências do usuário */
  useEffect(() => {
    if (usuario) {
      setTimeout(() => {
        setCarregamento(false);
      }, 500);
    }
  }, [usuario]);

  /** useEffect utilizado para adicionar uma mensagem ao tour caso o status da demanda seja "backlog_edicao" */
  useEffect(() => {
    if (dados && dados.status === "BACKLOG_EDICAO") {
      setSteps([
        ...steps,
        {
          selector: "#terceiro",
          content: texts.detalhesDemandaPagina.tour.tour3,
          style: {
            backgroundColor: "#DCDCDC",
            color: "#000000",
          },
        },
      ]);
    }
  }, [dados]);

  /** Função para buscar a demanda da página pelo seu ID, recebido no location.state */
  const buscarDemanda = (id) => {
    DemandaService.getById(id, null).then((response) => {
      setDados(response.content[0])
    })
  }

  /** Função utilizada para buscar o usuário que está logado no sistema */
  const buscarUsuario = () => {
    UsuarioService.getUsuarioByEmail(CookieService.getCookie("jwt").sub).then(
      (e) => {
        setUsuario(e);
      }
    );
  };

  /** Função utilizada para alterar dados da demanda */
  const updateDemandaProps = (demanda) => {
    setDados({ ...demanda });
    location.state = { ...demanda };
  };

  /** Função para baixar a demanda em formato pdf */
  const baixarDemanda = () => {
    ExportPdfService.exportDemanda(dados.id).then((response) => {
      let blob = new Blob([response], { type: "application/pdf" });
      let url = URL.createObjectURL(blob);
      let link = document.createElement("a");
      link.href = url;
      link.download = dados.titulo;
      link.click();
    });
  };

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

      <Box className="p-2 w-full" sx={{ minWidth: "58rem" }}>
        {carregamento ? (
          <Box className="mt-6 w-full h-full flex justify-center items-center">
            <ClipLoader color="#00579D" size={110} />
          </Box>
        ) : (
          <>
            <Box className="flex w-full relative">
              <Caminho />
              <Box
                className=" absolute"
                sx={{ top: "10px", right: "20px", cursor: "pointer" }}
              >
                <IconButton onClick={baixarDemanda}>
                  <SaveAltOutlinedIcon
                    id="segundo"
                    fontSize="large"
                    className="delay-120 hover:scale-110 duration-600"
                    sx={{ color: "icon.main" }}
                  />
                </IconButton>
              </Box>
            </Box>
            <Box className="w-full mt-10 mb-16">
              {/* Mostrar os dados da demanda */}
              <DetalhesDemanda
                dados={dados}
                usuario={usuario}
                botao={true}
                salvar={true}
                updateDemandaProps={updateDemandaProps}
                onlyView={false}
              />
            </Box>
          </>
        )}
      </Box>
    </FundoComHeader>
  );
};

export default DetalhesDemandaPagina;
