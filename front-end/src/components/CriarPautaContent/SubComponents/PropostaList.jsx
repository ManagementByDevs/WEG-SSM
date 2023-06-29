import React, { useContext, useEffect, useState, memo } from "react";

import {
  Box,
  Paper,
  Typography,
  Tooltip,
  IconButton,
  Dialog,
  DialogContent,
} from "@mui/material";

import AddIcon from "@mui/icons-material/Add";
import RemoveIcon from "@mui/icons-material/Remove";
import FullscreenIcon from "@mui/icons-material/Fullscreen";
import PublicIcon from "@mui/icons-material/Public";
import PublicOffIcon from "@mui/icons-material/PublicOff";

import EntitiesObjectService from "../../../service/entitiesObjectService";
import FontContext from "../../../service/FontContext";
import TextLanguageContext from "../../../service/TextLanguageContext";
import SpeechSynthesisContext from "../../../service/SpeechSynthesisContext";
import DetalhesProposta from "../../DetalhesProposta/DetalhesProposta";

const PropostaList = ({
  draggable = false,
  onDragStart = () => {},
  listaPropostas = [EntitiesObjectService.proposta()],
  setListaPropostas = () => {},
  addProposta = () => {},
  inDiscussion = false,
}) => {
  /** Context para alterar o tamanho da fonte */
  const { FontConfig } = useContext(FontContext);

  /** Context para obter os textos do sistema */
  const { texts } = useContext(TextLanguageContext);

  /** Context para ler o texto da tela */
  const { lerTexto } = useContext(SpeechSynthesisContext);

  /** Controla o modal de detalhes da proposta */
  const [modalDetalhesProposta, setModalDetalhesProposta] = useState({
    propostaId: 0,
    open: false,
  });

  /** Handler de quando for fechado o modal de detalhes da proposta */
  const handleOnCloseModalProposta = () => {
    setModalDetalhesProposta({ propostaId: 0, open: false });
  };

  /** Retorna o ano a partir de uma data SQL */
  const getAno = (dateSql) => {
    return dateSql.split("-")[0];
  };

  /** Troca o status publicada da proposta passada por parâmetro */
  const togglePropostaPublished = (
    proposta = EntitiesObjectService.proposta()
  ) => {
    if (proposta.id == 0) return;

    const listAux = [...listaPropostas];
    const propostaIndex = listAux.findIndex((item) => item.id == proposta.id);
    listAux[propostaIndex].publicada = !listAux[propostaIndex].publicada;

    setListaPropostas(listAux);
  };

  /** Abre modal de detalhes da proposta */
  const expandProposta = (propostaAux = EntitiesObjectService.proposta()) => {
    if (propostaAux.id == 0) return;

    setModalDetalhesProposta({ propostaId: propostaAux.id, open: true });
  };

  /** Remove quaisquer itens com o mesmo ID da lista passada por parâmetro */
  const removeItemFromList = (item, setList) => {
    setList((list) => list.filter((listItem) => listItem.id !== item.id));
  };

  return (
    <>
      <Dialog
        open={modalDetalhesProposta.open}
        onClose={handleOnCloseModalProposta}
        maxWidth="md"
      >
        <DialogContent>
          <DetalhesProposta
            onlyView
            propostaId={modalDetalhesProposta.propostaId}
          />
        </DialogContent>
      </Dialog>
      {listaPropostas.map((proposta, index) => {
        return (
          <Paper
            key={index}
            className="w-full mt-1 flex justify-between p-1 border"
            square
            variant="outlined"
            draggable={draggable}
            onDragStart={(e) => onDragStart(e, proposta)}
          >
            <Box className="flex items-center gap-2 w-2/3">
              <Box className="w-1/2">
                <Typography
                  fontSize={FontConfig.default}
                  fontWeight={500}
                  color="primary"
                >
                  {proposta.codigoPPM}/{getAno(proposta.data)}
                </Typography>
              </Box>
              <Box className="w-1/2">
                <Typography
                  fontSize={FontConfig.default}
                  fontWeight={500}
                  color="text.primary"
                  className="overflow-hidden text-ellipsis whitespace-nowrap"
                >
                  {proposta.titulo}
                </Typography>
              </Box>
            </Box>
            <Box className="w-1/3 flex justify-end">
              {inDiscussion ? (
                <Box className="flex items-center gap-2">
                  <Tooltip
                    title={proposta.publicada ? "Publicada" : "Não Publicada"}
                  >
                    <IconButton
                      size="small"
                      color="primary"
                      onClick={() => togglePropostaPublished(proposta)}
                    >
                      {proposta.publicada ? (
                        <PublicIcon color="primary" />
                      ) : (
                        <PublicOffIcon color="primary" />
                      )}
                    </IconButton>
                  </Tooltip>
                  <Tooltip title="Expandir">
                    <IconButton
                      size="small"
                      onClick={() => expandProposta(proposta)}
                    >
                      <FullscreenIcon />
                    </IconButton>
                  </Tooltip>
                  <Tooltip title="Remover">
                    <IconButton
                      size="small"
                      color="primary"
                      onClick={() => {
                        removeItemFromList(proposta, setListaPropostas);
                      }}
                    >
                      <RemoveIcon />
                    </IconButton>
                  </Tooltip>
                </Box>
              ) : (
                <>
                  <Tooltip title="Expandir">
                    <IconButton
                      size="small"
                      onClick={() => expandProposta(proposta)}
                    >
                      <FullscreenIcon />
                    </IconButton>
                  </Tooltip>
                  <Tooltip title="Adicionar">
                    <IconButton
                      size="small"
                      onClick={() => addProposta(proposta)}
                      color="primary"
                    >
                      <AddIcon />
                    </IconButton>
                  </Tooltip>
                </>
              )}
            </Box>
          </Paper>
        );
      })}
    </>
  );
};

export default PropostaList;
