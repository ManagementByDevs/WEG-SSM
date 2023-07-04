import React, { useContext, useState, useEffect } from "react";

import { useLocation, useParams } from "react-router-dom";

import { Box, IconButton, Button, Tooltip } from "@mui/material";

import SaveAltOutlinedIcon from "@mui/icons-material/SaveAltOutlined";
import PostAddOutlinedIcon from "@mui/icons-material/PostAddOutlined";

import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Caminho from "../../components/Caminho/Caminho";
import DetalhesProposta from "../../components/DetalhesProposta/DetalhesProposta";
import ModalAddPropostaPauta from "../../components/Modais/Modal-addPropostaPauta/ModalAddPropostaPauta";
import Feedback from "../../components/Feedback/Feedback";

import ExportPdfService from "../../service/exportPdfService";
import TextLanguageContext from "../../service/TextLanguageContext";
import PropostaService from "../../service/propostaService";

import Tour from "reactour";

import Ajuda from "../../components/Ajuda/Ajuda";

/** Página que mostra os detalhes da proposta selecionada, com opção de download para pdf */
const DetalhesPropostaPagina = () => {

  /** Location utilizado para pegar os dados da proposta */
  const location = useLocation();

  /** Params da rota */
  const paramsPath = useParams();

  /** Context para obter os textos do sistema */
  const { texts } = useContext(TextLanguageContext);

  /** useState utilizado para abrir e fechar o modal de adicionar a pauta */
  const [openModalAddPropostaPauta, setOpenModalAddPropostaPauta] =
    useState(false);

  /** useState para feedback de edição feita com sucesso */
  const [feedbackEditSuccess, setFeedbackEditSuccess] = useState(false);

  /** variável para armazenar o titulo da proposta  */
  const [tituloProposta, setTituloProposta] = useState();

  /** useState para abrir e fechar o tour */
  const [isTourOpen, setIsTourOpen] = useState(false);

  const stepsTour = [
    {
      selector: "#primeiro",
      content: texts.detalhesProposta.tour.tour1,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#segundo",
      content: texts.detalhesProposta.tour.tour2,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#terceiro",
      content: texts.detalhesProposta.tour.tour3,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
  ];

  /** useEffect utilizado para buscar o nome da proposta para colocar no arquivo pdf */
  useEffect(() => {
    // Buscando os dados da proposta usando o propostaId
    PropostaService.getById(paramsPath.id).then((proposal) => {
      setTituloProposta(proposal.titulo);
    });
  }, []);

  /** Função para abrir o modal de adicionar a pauta */
  const adicionarAPauta = () => {
    setOpenModalAddPropostaPauta(true);
  };

  /** Função utilizada para baixar a proposta em formatado pdf */
  const baixarProposta = () => {
    ExportPdfService.exportProposta(location.state.id).then((response) => {
      let blob = new Blob([response], { type: "application/pdf" });
      let url = URL.createObjectURL(blob);
      let link = document.createElement("a");
      link.href = url;
      link.download = tituloProposta + ".pdf";
      link.click();
    });
  };

  return (
    <FundoComHeader>

      <Tour
        steps={stepsTour}
        isOpen={isTourOpen}
        onRequestClose={() => setIsTourOpen(false)}
        accentColor="#00579D"
        rounded={10}
        showCloseButton={false}
      />

      <Ajuda onClick={() => setIsTourOpen(true)} />

      {/* Feedback edição bem sucedida */}
      <Feedback
        open={feedbackEditSuccess}
        handleClose={() => setFeedbackEditSuccess(false)}
        status={"sucesso"}
        mensagem={texts.detalhesProposta.editadoComSucesso}
      />
      {/* Modal para adicionar a proposta em pauta */}
      <ModalAddPropostaPauta
        open={openModalAddPropostaPauta}
        setOpen={setOpenModalAddPropostaPauta}
        proposta={location.state}
      />
      <Box className="relative p-2 mb-14" sx={{ minWidth: "45rem" }}>
        <Box className="flex w-full relative mb-10">
          <Caminho />
          <Box
            className=" absolute"
            sx={{ top: "10px", right: "20px", cursor: "pointer" }}
          >
            {/* Ícone para baixar a proposta em formato pdf */}
            <IconButton onClick={baixarProposta}>
              <SaveAltOutlinedIcon
                id="segundo"
                fontSize="large"
                className="delay-120 hover:scale-110 duration-600"
                sx={{ color: "icon.main" }}
              />
            </IconButton>
          </Box>
        </Box>
        {/* Mostra o conteúdo da proposta */}
        <DetalhesProposta
          propostaId={paramsPath.id}
          setFeedbackEditSuccess={setFeedbackEditSuccess}
        />
      </Box>
      {location.state.status != "CANCELLED" &&
        location.state.presenteEm != "Pauta" &&
        location.state.presenteEm != "Ata" && (
          <Box className="absolute bottom-4 right-20 p-1">
            {/* Botão de adicionar proposta em pauta */}
            <Tooltip title={texts.detalhesPropostaPagina.adicionarAPauta}>
              <Button
                variant="contained"
                sx={{ borderRadius: "9999px" }}
                onClick={adicionarAPauta}
                id="terceiro"
              >
                <PostAddOutlinedIcon
                  sx={{ fontSize: "28px", color: "text.white" }}
                />
              </Button>
            </Tooltip>
          </Box>
        )}
    </FundoComHeader>
  );
};

export default DetalhesPropostaPagina;
