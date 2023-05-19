import React, { useState, useEffect, useContext } from "react";
import { useNavigate } from "react-router-dom";
import { Modal, Fade, Divider, Typography, Box, Button, Checkbox, FormGroup, FormControlLabel, Paper, Select, FormControl, MenuItem, TextField } from "@mui/material";

import { red } from "@mui/material/colors";
import CloseIcon from "@mui/icons-material/Close";

import Feedback from "../../components/Feedback/Feedback";
import ContainerPauta from "../ContainerPauta/ContainerPauta";

import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";
import TemaContext from "../../service/TemaContext";
import PautaService from "../../service/pautaService";
import PropostaService from "../../service/propostaService";
import ForumService from "../../service/forumService";
import EntitiesObjectService from "../../service/entitiesObjectService";
import CookieService from "../../service/cookieService";
import ExportPdfService from "../../service/exportPdfService";

// Modal de adicionar uma proposta em uma pauta
const ModalAddPropostaPauta = (props) => {
  
  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  // Contexet para verificar o tema atual
  const { mode } = useContext(TemaContext);

  const navigate = useNavigate();

  // variáveis de estilo para os itens do componente
  const cssModal = {
    position: "absolute",
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
    display: "flex",
    justifyContent: "space-between",
    alignItems: "center",
    flexDirection: "column",
  };

  const parteCheck = {
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    flexDirection: "column",
    p: 1.5,
  };

  const botaoCriar = {
    width: "7rem",
    border: "solid 1px",
    color: "tertiary.main",
    p: 1,
  };

  const botaoDesabilitado = {
    width: "7rem",
    border: "solid 1px",
    p: 1,
  };

  const listaPropostas = {
    display: "flex",
    alignItems: "center",
    flexDirection: "column",
    overflow: "auto",
    width: "100%",
    height: "80%",
    p: 1.5,
  };

  const containerGeral = {
    width: "90%",
    height: "6rem",
    border: "1px solid",
    borderLeft: "solid 6px",
    borderColor: "primary.main",
    borderRadius: "5px",
    p: 4,
    margin: "1%",
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    flexDirection: "column",
    cursor: "pointer",
  };

  const parteCima = {
    width: "100%",
    display: "flex",
    justifyContent: "space-between",
    alignItems: "center",
  };

  const parteBaixo = {
    display: "flex",
    alignItems: "flex-end",
    width: "100%",
    marginTop: "2%",
  };

  const data = {
    border: "solid 1px",
    color: "grey",
    textAlign: "center",
    borderRadius: "3px",
    color: "primary.secondary",
    background: "transparent",
    filter: "white",
  };

  // Exemplo de um objeto proposta
  const propostaExample = EntitiesObjectService.proposta();

  useEffect(() => {
    PautaService.get().then((res) => {
      setListaPautas(res);
    });

    ForumService.getAll().then((res) => {
      setListaComissoes(res);
    });
  }, []);

  // props para abrir o modal através de outra tela
  let open = false;
  open = props.open;
  const setOpen = props.setOpen;

  // useState para abrir e fechar o modal
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  // veriricação para marcar apenas um check
  const [check, setCheck] = useState([false, false]);

  function mudarCheck1() {
    if (check[0]) {
      setCheck([false, false]);
    } else {
      setCheck([true, false]);
    }
  }

  function mudarCheck2() {
    if (check[1]) {
      setCheck([false, false]);
    } else {
      setCheck([false, true]);
    }
  }

  // UseState para habilitar ou desabilitar o botão de adicionar
  const [habilitadoAdd, setHabilitadoAdd] = useState(false);

  // UseState index para selecionar a pauta
  const [indexPautaSelecionada, setIndexPautaSelecionada] = useState(null);

  // UseState para criar uma nova pauta
  const [novaPauta, setnovaPauta] = useState(false);

  // UseState para selecionar a nova pauta criada
  const [novaPautaSelecionada, setnovaPautaSelecionada] = useState(false);

  // UseState para habilitar ou desabilitar o botão de adicionar a nova pauta
  const [botaoNovaPauta, setBotaoNovaPauta] = useState(false);

  // UseState para armazenar o número sequencial da pauta
  const [numSequencial, setNumSequencial] = useState("");

  // UseState para armazenar a lista de pautas
  const [listaPautas, setListaPautas] = useState([
    {
      id: 0,
      numeroSequencial: 0,
      dataReuniao: "",
      comissao: "",
      propostas: [propostaExample],
    },
  ]);

  const [listaComissoes, setListaComissoes] = useState([]);

  // UseState para armazenar a data da reunião
  const [inputDataReuniao, setInputDataReuniao] = useState("");

  const comissaoEstatico = {
    idForum: 0,
    nomeForum: texts.modalAddPropostaPauta.comissao,
    siglaForum: texts.modalAddPropostaPauta.comissao,
  };

  // UseState para armazenar a comissão
  const [comissao, setComissao] = useState(comissaoEstatico);

  // Provavelmente não possui uso no componente ( verificar depois )

  // useEffect(() => {

  // }, [habilitadoAdd])

  // função para adicionar uma nova pauta
  const addPauta = () => {
    setnovaPauta(true);
  };

  // função para mudar o valor do select na nova pauta criada
  const handleChange = (event) => {
    setComissao(event.target.value);
  };

  // função para selecionar a nova pauta
  const selecionarNovaPauta = () => {
    // Deseleciona a nova pauta
    if (!novaPautaSelecionada) {
      setIndexPautaSelecionada(null);
    }

    setnovaPautaSelecionada(!novaPautaSelecionada);
    setBotaoNovaPauta(!botaoNovaPauta);
  };

  // Função para retornar a cor do background do componente de pauta corretamente
  const getBackgroundColor = () => {
    if (!novaPautaSelecionada) {
      return mode == "dark" ? "#22252C" : "#FFFFFF";
    } else {
      return mode == "dark" ? "#2E2E2E" : "#E4E4E4";
    }
  };

  // Função para retornar o id do analista que será responsável pela pauta (usuário logado)
  const getIdAnalistaResponsavel = () => {
    return CookieService.getUser().id;
  };

  const [feedbackPreenchaTodosCampos, setFeedbackPreenchaTodosCampos] =
    useState(false);
  const [feedbackPautaAtualizada, setFeedbackPautaAtualizada] = useState(false);

  /** Função para formatar uma lista de objetos, retornando somente o id de cada objeto presente, com a lista sendo recebida como parâmetro */
  const retornarIdsObjetos = (listaObjetos) => {
    let listaNova = [];
    for (let objeto of listaObjetos) {
      listaNova.push({ id: objeto.id });
    }
    return listaNova;
  };

  // Função para adicionar a proposta na pauta selecionada
  const addPropostaInPauta = () => {
    let pauta;
    if (novaPautaSelecionada) {
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
        PropostaService.atualizacaoPauta(props.proposta.id, check[0]).then((response) => {

          // Salvamento de histórico
          ExportPdfService.exportProposta(response.id).then((file) => {

            let arquivo = new Blob([file], { type: "application/pdf" });
            PropostaService.addHistorico(response.id, "Adicionada na Pauta #" + res.id, arquivo, CookieService.getUser().id).then(() => { });
          });
        });
      });
    } else {
      if (!check.includes(true)) {
        setFeedbackPreenchaTodosCampos(true);
        return;
      }

      pauta = listaPautas[indexPautaSelecionada];
      pauta.propostas = retornarIdsObjetos([...pauta.propostas, { id: props.proposta.id }]);

      PautaService.put(pauta).then((res) => {
        PropostaService.atualizacaoPauta(props.proposta.id, check[0]).then((response) => {
          setFeedbackPautaAtualizada(true);

          // Salvamento de histórico
          ExportPdfService.exportProposta(response.id).then((file) => {

            let arquivo = new Blob([file], { type: "application/pdf" });
            PropostaService.addHistorico(response.id, "Adicionada na Pauta #" + res.id, arquivo, CookieService.getUser().id).then(() => { });
          });
        });
      });
    }

    handleClose();
    navigate("/");
  };

  // Verifica se todos os campos necessários para a criação de uma pauta estão preenchidos
  const isAllFieldsFilled = () => {
    return (
      inputDataReuniao != "" &&
      comissao != "" &&
      check.includes(true) &&
      numSequencial != ""
    );
  };

  // UseEffect para deselecionar a nova pauta quando selecionar outra pauta
  useEffect(() => {
    if (indexPautaSelecionada != null) {
      setnovaPautaSelecionada(false);
    }
  }, [indexPautaSelecionada]);

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
        open={open}
        onClose={handleClose}
        closeAfterTransition
        sx={{ minWidth: "40rem" }}
      >
        <Fade in={open}>
          {/* Início conteúdo modal */}
          <Box sx={cssModal}>
            {/* Título do modal */}
            <Typography
              fontWeight={650}
              fontSize={FontConfig.smallTitle}
              color={"primary.main"}
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

            <Box sx={listaPropostas}>
              {/* Exibe as pautas do sistema */}
              {listaPautas.length > 0 || novaPauta ? (
                listaPautas.map((pauta, index) => {
                  return (
                    <ContainerPauta
                      key={index}
                      pauta={pauta}
                      setIndexPautaSelecionada={setIndexPautaSelecionada}
                      index={index}
                      indexPautaSelecionada={indexPautaSelecionada}
                    />
                  );
                })
              ) : (
                <>
                  <Typography fontSize={FontConfig.medium}>
                    {texts.modalAddPropostaPauta.nenhumaPautaEncontrada}
                  </Typography>
                  <Typography fontSize={FontConfig.default}>
                    {texts.modalAddPropostaPauta.pfvCrieUmaNova}
                  </Typography>
                </>
              )}

              {/* Nova pauta criada */}
              {novaPauta && (
                <Paper
                  sx={{
                    ...containerGeral,
                    backgroundColor: getBackgroundColor(),
                  }}
                  onClick={selecionarNovaPauta}
                >
                  <Box sx={{ ...parteCima, colorScheme: mode }}>
                    <Typography fontSize={FontConfig.medium}>
                      {texts.modalAddPropostaPauta.propostas}:
                    </Typography>
                    <input
                      style={data}
                      value={inputDataReuniao}
                      onChange={(e) => setInputDataReuniao(e.target.value)}
                      type="datetime-local"
                    />
                  </Box>

                  <Box className="gap-4 justify-between" sx={parteBaixo}>
                    <Box className="w-1/2">
                      <TextField
                        value={numSequencial}
                        onChange={(e) => setNumSequencial(e.target.value)}
                        fullWidth
                        placeholder={texts.modalAddPropostaPauta.numSequencial}
                        variant="standard"
                      />
                    </Box>
                    <FormControl size="small">
                      <Select
                        value={comissao}
                        onChange={handleChange}
                        displayEmpty
                        inputProps={{ "aria-label": "Without label" }}
                      >
                        <MenuItem value={comissao} disabled>
                          {texts.modalAddPropostaPauta.comissao}
                        </MenuItem>
                        {listaComissoes?.map((e, index) => (
                          <MenuItem key={index} value={e} title={e.nomeForum}>
                            {e.siglaForum}
                          </MenuItem>
                        ))}
                      </Select>
                    </FormControl>
                  </Box>
                </Paper>
              )}
            </Box>

            <Divider
              sx={{
                marginTop: "2%",
                width: "80%",
                borderColor: "tertiary.main",
              }}
            />

            {/* Parte de baixo do componente, com a opção de selecionar se é uma pauta publicada ou não publicada, 
                        assim como opção de criar nova pauta ou adicionar em alguma pauta */}
            <Box sx={parteCheck}>
              <Typography
                fontWeight={650}
                fontSize={FontConfig.veryBig}
                color={"primary.main"}
              >
                {texts.modalAddPropostaPauta.adicionarComoProposta}
              </Typography>
              <Box>
                <FormGroup>
                  <Box
                    sx={{
                      display: "flex",
                      justifyContent: "center",
                      padding: "4px",
                    }}
                  >
                    <FormControlLabel
                      checked={check[0]}
                      onChange={mudarCheck1}
                      control={<Checkbox />}
                      label={texts.modalAddPropostaPauta.publicada}
                    />
                    <FormControlLabel
                      checked={check[1]}
                      onChange={mudarCheck2}
                      control={<Checkbox />}
                      label={texts.modalAddPropostaPauta.naoPublicada}
                    />
                  </Box>
                </FormGroup>
              </Box>
              <Box
                sx={{
                  width: "90%",
                  display: "flex",
                  justifyContent: "space-between",
                  marginTop: "3%",
                }}
              >
                <Button
                  sx={botaoCriar}
                  disableElevation
                  onClick={addPauta}
                  disabled={novaPauta}
                >
                  <Typography fontSize={FontConfig.default}>
                    {texts.modalAddPropostaPauta.novaPauta}
                  </Typography>
                </Button>
                <Button
                  sx={botaoDesabilitado}
                  disableElevation
                  disabled={
                    indexPautaSelecionada == null && !novaPautaSelecionada
                  }
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
