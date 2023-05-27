import React, { useContext, useEffect, useState } from "react";

import { useLocation, useParams } from "react-router-dom";

import VLibras from "@djpfs/react-vlibras";

import { Box, IconButton, Button, Tooltip } from "@mui/material";

import SaveAltOutlinedIcon from "@mui/icons-material/SaveAltOutlined";
import BookmarkAddIcon from "@mui/icons-material/BookmarkAdd";

import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Caminho from "../../components/Caminho/Caminho";
import DetalhesProposta from "../../components/DetalhesProposta/DetalhesProposta";
import ModalAddPropostaPauta from "../../components/ModalAddPropostaPauta/ModalAddPropostaPauta";
import Feedback from "../../components/Feedback/Feedback";

import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";
import ExportPdfService from "../../service/exportPdfService";
import EntitiesObjectService from "../../service/entitiesObjectService";

// Página que mostra os detalhes da proposta selecionada, com opção de download para pdf
const DetalhesPropostaPagina = ({
  lendo = false,
}) => {
  // Location utilizado para pegar os dados da demanda
  const location = useLocation();

  const paramsPath = useParams();

  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  // useState utilizado para abrir e fechar o modal de adicionar a pauta
  const [openModalAddPropostaPauta, setOpenModalAddPropostaPauta] =
    useState(false);

  // Estado para feedback de edição feita com sucesso
  const [feedbackEditSuccess, setFeedbackEditSuccess] = useState(false);

  // função para abrir o modal de adicionar a pauta
  const adicionarAPauta = () => {
    setOpenModalAddPropostaPauta(true);
  };

  // função utilizada para baixar a proposta em formatado pdf
  const baixarProposta = () => {
    ExportPdfService.exportProposta(location.state.id).then((response) => {
      let blob = new Blob([response], { type: "application/pdf" });
      let url = URL.createObjectURL(blob);
      let link = document.createElement("a");
      link.href = url;
      link.download = "pdf_proposta.pdf";
      link.click();
    });
  };

  return (
    <FundoComHeader lendo={lendo} texto={texto} setTexto={setTexto}>
      <VLibras forceOnload />
      {/* Feedback edição bem sucedida */}
      <Feedback
        open={feedbackEditSuccess}
        handleClose={() => setFeedbackEditSuccess(false)}
        status={"sucesso"}
        mensagem={texts.detalhesProposta.editadoComSucesso}
        lendo={lendo}
        texto={texto}
        setTexto={setTexto}
      />
      <ModalAddPropostaPauta
        open={openModalAddPropostaPauta}
        setOpen={setOpenModalAddPropostaPauta}
        proposta={location.state}
        lendo={lendo}
      />
      <Box className="relative p-2" sx={{ minWidth: "45rem" }}>
        <Box className="flex w-full relative mb-10">
          <Caminho lendo={lendo} texto={texto} setTexto={setTexto} />
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
          <Box className="absolute bottom-4 right-6  p-1">
            {/* Botão de adicionar proposta em pauta */}
            <Tooltip title={texts.detalhesPropostaPagina.adicionarAPauta}>
              <Button
                variant="contained"
                sx={{ borderRadius: "9999px" }}
                onClick={adicionarAPauta}
              >
                <BookmarkAddIcon
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
