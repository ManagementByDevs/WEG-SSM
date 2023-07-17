import React, { useState, useEffect, useContext } from "react";
import { useNavigate } from "react-router-dom";
import {
  Modal,
  Fade,
  Divider,
  Typography,
  Box,
  Button,
  FormControlLabel,
  Paper,
  Select,
  FormControl,
  MenuItem,
  RadioGroup,
  IconButton,
  Tooltip,
  Input,
  Radio,
} from "@mui/material";

import { red } from "@mui/material/colors";

import DeleteOutlineOutlinedIcon from '@mui/icons-material/DeleteOutlineOutlined';
import CloseIcon from "@mui/icons-material/Close";
import AddCircleIcon from "@mui/icons-material/AddCircle";

import Feedback from "../../Feedback/Feedback";
import ContainerPauta from "../../ContainerPauta/ContainerPauta";

import FontContext from "../../../service/FontContext";
import TextLanguageContext from "../../../service/TextLanguageContext";
import TemaContext from "../../../service/TemaContext";
import PautaService from "../../../service/pautaService";
import PropostaService from "../../../service/propostaService";
import ForumService from "../../../service/forumService";
import CookieService from "../../../service/cookieService";
import ExportPdfService from "../../../service/exportPdfService";
import SpeechSynthesisContext from "../../../service/SpeechSynthesisContext";

// Modal de adicionar uma proposta em uma pauta
const ModalAddPropostaPauta = (props) => {
  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  // Contexet para verificar o tema atual
  const { mode } = useContext(TemaContext);

  /** Context para ler o texto da tela */
  const { lendoTexto, lerTexto, librasAtivo } = useContext(
    SpeechSynthesisContext
  );

  // Navigate utilizado para navegação entre as páginas
  const navigate = useNavigate();

  // useState para fechar o modal
  const handleClose = () => props.setOpen(false);

  // veriricação para marcar apenas um check
  const [check, setCheck] = useState(false);

  // Feedback para preencher todos os campos
  const [feedbackPreenchaTodosCampos, setFeedbackPreenchaTodosCampos] =
    useState(false);

  // Feedback pauta atualizada
  const [feedbackPautaAtualizada, setFeedbackPautaAtualizada] = useState(false);

  // UseState index para selecionar a pauta
  const [indexPautaSelecionada, setIndexPautaSelecionada] = useState(null);

  // UseState para criar uma nova pauta
  const [novaPauta, setnovaPauta] = useState(false);

  // UseState para armazenar o número sequencial da pauta
  const [numSequencial, setNumSequencial] = useState("");

  // UseState para armazenar a lista de pautas
  const [listaPautas, setListaPautas] = useState([]);

  const [listaComissoes, setListaComissoes] = useState([]);

  // UseState para armazenar a data da reunião
  const [inputDataReuniao, setInputDataReuniao] = useState("");

  // UseState para armazenar a comissão
  const [comissao, setComissao] = useState(props.proposta.forum);

  // useEffect utilizado para obter as pautas e os foruns
  useEffect(() => {
    PautaService.get().then((res) => {
      setListaPautas(res);
    });

    ForumService.getAll().then((res) => {
      setListaComissoes(res);
    });
  }, []);

  // função para adicionar uma nova pauta
  const addPauta = () => {
    setIndexPautaSelecionada(null);
    setnovaPauta(true);
    setComissao(() => {
      for (const forum of listaComissoes) {
        if (forum.idForum == props.proposta.forum.idForum) return forum;
      }
    });
  };

  // função para mudar o valor do select na nova pauta criada
  const handleChange = (event) => {
    setComissao(event.target.value);
  };

  // Função para retornar o id do analista que será responsável pela pauta (usuário logado)
  const getIdAnalistaResponsavel = () => {
    return CookieService.getUser().id;
  };

  /** Função para formatar uma lista de objetos, retornando somente o id de cada objeto presente, com a lista sendo recebida como parâmetro */
  const retornarIdsObjetos = (listaObjetos) => {
    let listaNova = [];
    for (let objeto of listaObjetos) {
      listaNova.push({ id: objeto.id });
    }
    return listaNova;
  };

  /** Função para criar e retornar um objeto de histórico para salvamento */
  const retornaObjetoHistorico = (acaoRealizada) => {
    const historico = {
      data: new Date(),
      acaoRealizada: acaoRealizada,
      autor: { id: CookieService.getUser().id },
    };
    return historico;
  };

  // Função para adicionar a proposta na pauta selecionada
  const addPropostaInPauta = () => {
    if (lendoTexto) {
      lerTexto(texts.modalAddPropostaPauta.adicionar);
      return;
    } else if (librasAtivo) {
      return;
    }

    let pauta;
    if (novaPauta) {
      if (!isAllFieldsFilled()) {
        setFeedbackPreenchaTodosCampos(true);
        return;
      }

      pauta = PautaService.createPautaObjectWithPropostas(
        numSequencial,
        inputDataReuniao,
        comissao,
        getIdAnalistaResponsavel(),
        [{ id: props.proposta.id }]
      );

      PautaService.post(pauta).then((res) => {
        PropostaService.atualizacaoPauta(props.proposta.id, check).then(
          (response) => {
            // Salvamento de histórico
            ExportPdfService.exportProposta(response.id).then((file) => {
              let arquivo = new Blob([file], { type: "application/pdf" });
              PropostaService.addHistorico(
                response.id,
                retornaObjetoHistorico(
                  "Adicionada na Pauta #" + res.numeroSequencial
                ),
                arquivo
              ).then(() => {});
            });
          }
        );
      });
    } else {
      pauta = listaPautas[indexPautaSelecionada];
      pauta.propostas = retornarIdsObjetos([
        ...pauta.propostas,
        { id: props.proposta.id },
      ]);

      PautaService.put(pauta).then((res) => {
        PropostaService.atualizacaoPauta(props.proposta.id, check).then(
          (response) => {
            setFeedbackPautaAtualizada(true);

            // Salvamento de histórico
            ExportPdfService.exportProposta(response.id).then((file) => {
              let arquivo = new Blob([file], { type: "application/pdf" });
              PropostaService.addHistorico(
                response.id,
                retornaObjetoHistorico(
                  "Adicionada na Pauta #" + res.numeroSequencial
                ),
                arquivo
              ).then(() => {});
            });
          }
        );
      });
    }

    localStorage.setItem("tipoFeedback", "6");
    handleClose();
    navigate("/");
  };

  // Verifica se todos os campos necessários para a criação de uma pauta estão preenchidos
  const isAllFieldsFilled = () => {
    return inputDataReuniao != "" && comissao != "" && numSequencial != "";
  };

  // Verifica se a proposta já se encontra em alguma pauta;
  const isPropostaInPauta = () => {
    let isPropostaInPauta = false;

    listaPautas.forEach((pauta) => {
      pauta.propostas.forEach((proposta) => {
        if (proposta.id == props.proposta.id) {
          isPropostaInPauta = true;
        }
      });
    });

    return isPropostaInPauta;
  };

  /** Handler de quando alguma pauta for selecionada */
  const handleOnPautaSelect = (event) => {
    setIndexPautaSelecionada(event.target.value);
  };

  /** Controla o radio box de proposta publicada */
  const handleOnPublicadaChange = () => {
    setCheck(!check);
  };

  return (
    <>
      {/* Feedback proposta atualizada */}
      <Feedback
        open={feedbackPreenchaTodosCampos}
        handleClose={() => {
          setFeedbackPreenchaTodosCampos(false);
        }}
        status={"erro"}
        mensagem={texts.modalAddPropostaPauta.feedbacks.feedback1}
      />
      {/* Feedback pauta atualizada */}
      <Feedback
        open={feedbackPautaAtualizada}
        handleClose={() => {
          setFeedbackPautaAtualizada(false);
        }}
        status={"sucesso"}
        mensagem={texts.modalAddPropostaPauta.feedbacks.feedback2}
      />
      <Modal
        open={props.open}
        onClose={handleClose}
        closeAfterTransition
        sx={{ minWidth: "40rem" }}
      >
        <Fade in={props.open}>
          {/* Início conteúdo modal */}
          <Box
            className="absolute flex justify-between items-center flex-col"
            sx={{
              top: "50%",
              left: "50%",
              transform: "translate(-50%, -50%)",
              width: 500,
              height: 500,
              bgcolor: "background.paper",
              borderRadius: "5px",
              borderTop: "10px solid #00579D",
              boxShadow: 24,
              p: 2,
            }}
          >
            {/* Título do modal */}
            <Typography
              fontWeight={650}
              fontSize={FontConfig.smallTitle}
              color={"primary.main"}
              onClick={() => {
                lerTexto(texts.modalAddPropostaPauta.selecioneAPauta);
              }}
            >
              {texts.modalAddPropostaPauta.selecioneAPauta}
            </Typography>
            {isPropostaInPauta() && (
              <Paper
                className="px-2 py-1 mb-3"
                sx={{ backgroundColor: red[200] }}
                elevation={0}
              >
                <Typography
                  fontWeight="bold"
                  fontSize={FontConfig.default}
                  color="#000000"
                  onClick={() => {
                    lerTexto(
                      texts.modalAddPropostaPauta
                        .essaPropostaJaSeEncontraEmUmaPauta
                    );
                  }}
                >
                  {
                    texts.modalAddPropostaPauta
                      .essaPropostaJaSeEncontraEmUmaPauta
                  }
                </Typography>
              </Paper>
            )}
            <CloseIcon
              onClick={handleClose}
              sx={{
                position: "absolute",
                left: "93%",
                top: "3%",
                cursor: "pointer",
              }}
            />

            {/* Botão de add uma nova pauta e ícone de fechar */}
            <Box className="flex items-center justify-between mt-2" sx={{width: "98%"}}>
              <Box className="flex items-center gap-1">
                <Typography
                  fontSize={FontConfig.big}
                  fontWeight={600}
                  color={"primary"}
                >
                  {texts.modalAddPropostaPauta.novaPauta}
                </Typography>
                <IconButton
                  size="small"
                  color="primary"
                  onClick={addPauta}
                  disabled={novaPauta}
                >
                  <AddCircleIcon />
                </IconButton>
              </Box>
              {novaPauta && (
                <Tooltip title="Fechar nova pauta">
                  <IconButton
                    size="small"
                    color="primary"
                    onClick={() => setnovaPauta(false)}
                  >
                    <DeleteOutlineOutlinedIcon />
                  </IconButton>
                </Tooltip>
              )}
            </Box>

            {novaPauta && (
              <Box className="w-full px-3">
                <Paper
                  className="w-full flex border-l-4 py-2 pr-4 pl-1 mb-4 mt-2"
                  sx={{ borderLeftColor: "primary.main" }}
                  square
                >
                  <Box className="flex items-center" sx={{ width: "10%" }}>
                    <Radio checked={novaPauta} size="small" />
                  </Box>
                  <Box className="flex flex-col gap-4" sx={{ width: "90%" }}>
                    <Box className="flex items-center justify-between gap-2">
                      <Box className="w-1/3 flex items-center">
                        <Input
                          value={numSequencial}
                          onChange={(e) => setNumSequencial(e.target.value)}
                          fullWidth
                          placeholder={
                            texts.modalAddPropostaPauta.numSequencial
                          }
                        />
                      </Box>

                      <Input
                        style={{ colorScheme: mode }}
                        value={inputDataReuniao}
                        onChange={(e) => setInputDataReuniao(e.target.value)}
                        type="datetime-local"
                      />
                    </Box>

                    <Box className="truncate">
                      <FormControl size="small">
                        <Select
                          value={comissao}
                          onChange={handleChange}
                          displayEmpty
                          variant="standard"
                        >
                          <MenuItem value={comissao} disabled>
                            {texts.modalAddPropostaPauta.forum}
                          </MenuItem>
                          {listaComissoes?.map((e, index) => (
                            <MenuItem key={index} value={e} title={e.nomeForum}>
                              {e.siglaForum} - {e.nomeForum}
                            </MenuItem>
                          ))}
                        </Select>
                      </FormControl>
                    </Box>
                  </Box>
                </Paper>
              </Box>
            )}

            <Divider flexItem />

            <Box
              className="flex items-center flex-col overflow-auto"
              sx={{ width: "100%", height: "80%", p: 1.5 }}
            >
              {/* Exibe as pautas do sistema */}
              {listaPautas.length > 0 || novaPauta ? (
                <>
                  <RadioGroup
                    className="w-full"
                    value={indexPautaSelecionada}
                    onChange={handleOnPautaSelect}
                  >
                    {listaPautas.map((pauta, index) => {
                      return (
                        <ContainerPauta
                          key={index}
                          pauta={pauta}
                          setIndexPautaSelecionada={setIndexPautaSelecionada}
                          index={index}
                          indexPautaSelecionada={indexPautaSelecionada}
                          novaPauta={novaPauta}
                        />
                      );
                    })}
                  </RadioGroup>
                </>
              ) : (
                <>
                  <Typography
                    fontSize={FontConfig.medium}
                    onClick={() => {
                      lerTexto(
                        texts.modalAddPropostaPauta.nenhumaPautaEncontrada
                      );
                    }}
                  >
                    {texts.modalAddPropostaPauta.nenhumaPautaEncontrada}
                  </Typography>
                  <Typography
                    fontSize={FontConfig.default}
                    onClick={() => {
                      lerTexto(texts.modalAddPropostaPauta.pfvCrieUmaNova);
                    }}
                  >
                    {texts.modalAddPropostaPauta.pfvCrieUmaNova}
                  </Typography>
                </>
              )}
            </Box>

            <Divider />

            {/* Parte de baixo do componente, com a opção de selecionar se é uma pauta publicada ou não publicada, 
                        assim como opção de criar nova pauta ou adicionar em alguma pauta */}
            <Box className="w-full p-3">
              <Divider />

              <Box className="w-full flex justify-center mt-4">
                <FormControlLabel
                  checked={check}
                  control={<Radio onClick={handleOnPublicadaChange} />}
                  label={texts.modalAddPropostaPauta.publicada}
                />
                <Button
                  disableElevation
                  disabled={indexPautaSelecionada == null && !novaPauta}
                  variant="contained"
                  onClick={addPropostaInPauta}
                >
                  {texts.modalAddPropostaPauta.adicionar}
                </Button>
              </Box>
            </Box>
          </Box>
        </Fade>
      </Modal>
    </>
  );
};

export default ModalAddPropostaPauta;
