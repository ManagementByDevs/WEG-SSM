import React, { useContext, useEffect, useState } from "react";

import { Modal, Typography, Box, Fade, Button, Tooltip, Dialog, DialogContent, IconButton } from "@mui/material";

import ErrorOutlineIcon from "@mui/icons-material/ErrorOutline";
import AssignmentIcon from '@mui/icons-material/Assignment';

import DetalhesDemanda from "../../DetalhesDemanda/DetalhesDemanda";

import EntitiesObjectService from "../../../service/entitiesObjectService";
import TextLanguageContext from "../../../service/TextLanguageContext";
import FontContext from "../../../service/FontContext";
import SpeechSynthesisContext from "../../../service/SpeechSynthesisContext";
import { SpeechRecognitionContext } from "../../../service/SpeechRecognitionService";
import UsuarioService from "../../../service/usuarioService";
import CookieService from "../../../service/cookieService";

/** Modal de recusar demanda na etapa de aprovação inicial (analista e gerente) */
const ModalSimilaridade = (props) => {

  // Context para alterar a linguagem do sistema
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  /** Context para ler o texto da tela */
  const { lendoTexto, lerTexto, librasAtivo } = useContext(SpeechSynthesisContext);

  /** Context para obter a função de leitura de texto */
  const { startRecognition, escutar, palavrasJuntas } = useContext(
    SpeechRecognitionContext
  );

  /** Variável para abrir o modal de detalhes da demanda */
  const [modalDetalhesDemanda, setModalDetalhesDemanda] = useState({ demandaId: 0, open: false });

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

  // // ********************************************** Gravar audio **********************************************

  /** useEffect utilizado par gravação de áudio */
  useEffect(() => {
    if (palavrasJuntas) props.setMotivo(palavrasJuntas);
  }, [palavrasJuntas]);

  // // ********************************************** Fim Gravar audio **********************************************

  /** Handler de quando for fechado o modal de detalhes da proposta */
  const handleOnCloseModalDemanda = () => {
    setModalDetalhesDemanda({ demandaId: 0, open: false });
  };

  /** Abre modal de detalhes da demanda */
  const expandirDemanda = (demandaAux = EntitiesObjectService.demanda()) => {
    if (demandaAux.id == 0) return;

    setModalDetalhesDemanda({ demandaId: demandaAux.id, open: true });
  };

  useEffect(() => {
    UsuarioService.getUsuarioByEmail(CookieService.getCookie("jwt").sub).then(
      (e) => {
        setUsuario(e);
      }
    );
  }, [])

  return (
    <>
      <Dialog
        open={modalDetalhesDemanda.open}
        onClose={handleOnCloseModalDemanda}
        maxWidth="lg"
      >
        <DialogContent>
          <DetalhesDemanda
            dados={props.dados}
            usuario={usuario}
            botao={true}
            salvar={true}
            onlyView={true}
          />
        </DialogContent>
      </Dialog>
      <Modal
        open={props.open}
        onClose={() => {
          props.setOpen(false);
        }}
        closeAfterTransition
      >


        <Fade in={props.open}>
          <Box
            className="absolute top-2/4 left-2/4 flex flex-col justify-between items-center"
            sx={{
              transform: "translate(-50%, -50%)",
              width: 450,
              bgcolor: "background.paper",
              borderRadius: "5px",
              borderTop: "10px solid #00579D",
              boxShadow: 24,
              p: 2,
            }}
          >
            <ErrorOutlineIcon sx={{ fontSize: "100px", color: "primary.main" }} />
            <Typography
              fontSize={FontConfig.veryBig}
              className="text-center"
              sx={{ mt: 2 }}
              onClick={() => {
                lerTexto(texts.modalSimilaridade.avisoSimilaridade);
              }}
            >
              {texts.modalSimilaridade.avisoSimilaridade}
            </Typography>

            <Typography
              fontSize={FontConfig.default}
              className="text-center"
              sx={{ mt: 2 }}
              onClick={() => {
                lerTexto(texts.modalSimilaridade.descricao)
              }}
            >
              {texts.modalSimilaridade.descricao}
            </Typography>

            <Box className="flex justify-center items-center mt-5">
              {/* Botão de ignorar */}
              <Button
                onClick={() => {
                  if (!lendoTexto) {
                    props.setOpen(false);
                    props.criarSemVerificacao(false);
                  } else if (librasAtivo) {
                  } else {
                    lerTexto(texts.barraProgressaoDemanda.botaoCriar);
                  }
                }}
                variant="container"
                disableElevation
                color="tertiary"
                sx={{
                  border: "solid 1px",
                  borderColor: "tertiary.main",
                  margin: "10px",
                  width: "7.5rem",
                  fontSize: FontConfig.big,
                }}
              >
                {texts.barraProgressaoDemanda.botaoCriar}
              </Button>

              {/* Botão de cancelar a criação da demanda */}
              <Button
                onClick={() => {
                  if (!lendoTexto) {
                    props.setOpen(false);
                  } else if (librasAtivo) {
                  } else {
                    lerTexto(texts.modalSimilaridade.cancelar);
                  }
                }}
                variant="contained"
                disableElevation
                color="primary"
                sx={{ margin: "10px", width: "7.5rem", fontSize: FontConfig.big }}
              >
                {texts.modalSimilaridade.cancelar}
              </Button>

              <Box className="absolute right-10">
                <Tooltip title={texts.modalSimilaridade.visualizarDemanda}>
                  <IconButton onClick={() => {
                    expandirDemanda(props.dados)
                  }}
                    color="primary">
                    <AssignmentIcon />
                  </IconButton>
                </Tooltip>
              </Box>
            </Box>
          </Box>
        </Fade>
      </Modal>
    </>
  );
};

export default ModalSimilaridade;