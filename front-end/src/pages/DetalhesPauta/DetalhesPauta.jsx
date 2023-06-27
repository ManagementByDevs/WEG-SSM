import React, { useState, useContext, useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom";

import { keyframes } from "@emotion/react";
import VLibras from "@djpfs/react-vlibras";

import { Box, Typography, Divider, Tooltip, IconButton, ButtonBase, Input } from "@mui/material";

import SaveAltOutlinedIcon from "@mui/icons-material/SaveAltOutlined";
import OtherHousesIcon from "@mui/icons-material/OtherHouses";
import DensitySmallIcon from "@mui/icons-material/DensitySmall";
import DeleteIcon from "@mui/icons-material/Delete";
import AddHomeOutlinedIcon from "@mui/icons-material/AddHomeOutlined";
import ArrowForwardIosIcon from "@mui/icons-material/ArrowForwardIos";
import ArrowBackIosNewIcon from "@mui/icons-material/ArrowBackIosNew";

import Feedback from "../../components/Feedback/Feedback";
import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Caminho from "../../components/Caminho/Caminho";
import DetalhesProposta from "../../components/DetalhesProposta/DetalhesProposta";
import ModalConfirmacao from "../../components/ModalConfirmacao/ModalConfirmacao";

import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";
import DateService from "../../service/dateService";
import PautaService from "../../service/pautaService";
import PropostaService from "../../service/propostaService";
import ExportPdfService from "../../service/exportPdfService";
import AtaService from "../../service/ataService";
import EntitiesObjectService from "../../service/entitiesObjectService";
import CookieService from "../../service/cookieService";
import DemandaService from "../../service/demandaService";
import NotificacaoService from "../../service/notificacaoService";
import { WebSocketContext } from "../../service/WebSocketService";
import SpeechSynthesisContext from "../../service/SpeechSynthesisContext";

/** Página para mostrar os detalhes da pauta selecionada, com opção de download para pdf */
const DetalhesPauta = (props) => {

  /** Context para alterar a linguagem do sistema */
  const { texts } = useContext(TextLanguageContext);

  /** Context para alterar o tamanho da fonte */
  const { FontConfig } = useContext(FontContext);

  /** Context para ler o texto da tela */
  const { lerTexto, lendoTexto } = useContext(SpeechSynthesisContext);

  /** Navigate utilizado para navegar para uma determianda página */
  const navigate = useNavigate();

  /** Variável do react-router-dom que guarda as informações da rota atual */
  const location = useLocation();

  /** UseState para guardar a pauta atual */
  const [pauta, setPauta] = useState(EntitiesObjectService.pauta());

  /** Lista provisória de propostas para preencher a tela */
  const [listaProposta, setListaProposta] = useState([EntitiesObjectService.proposta(),]);

  /** Variável de verificação utilizada para mostrar o sumário ou uma proposta */
  const [proposta, setProposta] = useState(false);

  /** Variável utilizada para passar para a próxima proposta */
  const [botaoProximo, setBotaoProximo] = useState(true);

  /** Variável utilizada para mostrar os dados de uma proposta */
  const [dadosProposta, setDadosProposta] = useState(listaProposta[0]);

  /** Index de uma proposta, utilizado para mostrar os dados de uma porposta específica */
  const [indexProposta, setIndexProposta] = useState(-1);

  /** Variável utilizada para minimizar os botões da página */
  const [minimizar, setMinimizar] = useState(true);

  /** Estado para mostrar o modal de confirmação */
  const [modal, setModal] = useState(false);

  /** UseState para definir se o modal de confirmação para a criação de ata está aberto */
  const [modalCriarAta, setModalCriarAta] = useState(false);

  /** Estado para mostrar o sumário ou não, usado também para atualizar a página com as novas propostas */
  const [isSummaryVisible, setIsSummaryVisible] = useState(true);

  /** useState utilizado para abrir ou fechar o menu de ícones */
  const [fecharMenu, setFecharMenu] = useState(true);

  // useState utilizado para colocar efeito no ícone
  const [girarIcon, setGirarIcon] = useState(false);

  // useState utilizado para tirar os ícones de navegação
  const [aparecerSumir, setAparecerSumir] = useState(false);

  // useState de estilo para desaparecer os botões de navegação
  const [display, setDisplay] = useState("hidden");

  /** Context do WebSocket */
  const { enviar } = useContext(WebSocketContext);

  /** Feedback para quando a pauta não possuir propostas */
  const [feedbackSemPropostas, setFeedbackSemPropostas] = useState(false);

  /** Feedback para quando o usuário deletar uma proposta da pauta */
  const [feedbackPropostaDeletada, setFeedbackPropostaDeletada] = useState(false);

  /** Feedback para quando o usuário não preencher todos os campos obrigatórios */
  const [feedbackCamposFaltantes, setFeedbackCamposFaltantes] = useState(false);

  /** useState utilizado para armazenar o número sequencial da ata */
  const [numeroSequencialAta, setNumeroSequencialAta] = useState();

  /** useEffect utilizado para mostrar uma proposta */
  useEffect(() => {
    if (indexProposta != -1) {
      setProposta(true);
    }
  }, [indexProposta]);

  /** useEffect utilizado para setar a pauta que está sendo visualizada */
  useEffect(() => {
    setPauta(location.state.pauta);
    setListaProposta(location.state.pauta.propostas);
  }, []);

  /** Função que atualiza as propostas da pauta sempre que uma proposta é atualizada */
  useEffect(() => {
    if (indexProposta > -1 && dadosProposta != null) {
      let aux = [...listaProposta];
      aux[indexProposta] = { ...dadosProposta };
      setListaProposta(aux);
      setPauta({ ...pauta, propostas: aux });
    }
  }, [dadosProposta]);

  /** useEffect utilizado para ver o sumário da pauta */
  useEffect(() => {
    setIsSummaryVisible(true);
  }, [dadosProposta]);

  /** Função para selecionar uma proposta do sumário */
  const onClickProposta = (index) => {
    setIndexProposta(index);
    setDadosProposta(pauta.propostas[index]);
    setProposta(true);
  };

  /** Função para passar para a próxima proposta */
  const proximo = () => {
    if (!lendoTexto) {
      if (indexProposta == listaProposta.length - 1) {
        setBotaoProximo(false);
      } else {
        setProposta(false);
        setDadosProposta(pauta.propostas[indexProposta + 1]);
        setIndexProposta(indexProposta + 1);
      }
    } else {
      lerTexto(texts.detalhesPauta.proximo);
    }
  };

  /** Função para voltar para uma proposta */
  const voltar = () => {
    if (indexProposta <= 0) {
      setProposta(false);
      setIndexProposta(-1);
    } else {
      setBotaoProximo(true);
      setProposta(false);
      setDadosProposta(pauta.propostas[indexProposta - 1]);
      setIndexProposta(indexProposta - 1);
    }
  };

  /** Função para voltar ao sumário da pauta */
  const voltarSumario = () => {
    setBotaoProximo(true);
    setIndexProposta(-1);
    setProposta(false);
  };

  /** Função para fechar os botões da página */
  const funcaoFecharMenu = () => {
    setFecharMenu(!fecharMenu);
  };

  /** Feedback de ata criada com sucesso */
  const feedbackAta = () => {
    navigate("/", { state: { feedback: "ata-criada" } });
  };

  /** Feedback de propostas atualizadas caso não tenha proposta aprovada */
  const feedbackPropostasAtualizadas = () => {
    navigate("/", { state: { feedback: "propostas-atualizadas" } });
  };

  /** Funções para animação dos botões de navegação */
  const girar = keyframes({
    from: { rotate: "90deg" },
    to: { rotate: "0deg" },
  });

  const girar2 = keyframes({
    from: { rotate: "0deg" },
    to: { rotate: "90deg" },
  });

  const aparecer = keyframes({
    "0%": { width: "10rem", opacity: "0" },
    "100%": { width: "15.5rem", opacity: "1" },
  });

  const sumir = keyframes({
    "0%": { width: "15.5rem", opacity: "1" },
    "100%": { width: "8rem", opacity: "0" },
  });

  /** Função para animar os botões de acordo com o click */
  const animarBotoes = () => {
    if (minimizar) {
      setGirarIcon(girar);
      setDisplay("flex");
      setAparecerSumir(aparecer);
    } else {
      setGirarIcon(girar2);
      setTimeout(() => {
        setDisplay("hidden");
      }, 1000);
      setAparecerSumir(sumir);
    }
  };

  /** Função acionada quando é clicado no botão de delete de alguma proposta */
  const onDeletePropostaClick = () => {
    setModal(true);
  };

  /** Função para deletar uma proposta da pauta, atualizando a pauta logo em seguida */
  const deletePropostaFromPauta = () => {
    pauta.propostas = retornarIdsObjetos(pauta.propostas);
    const indexProposta = pauta.propostas.findIndex(
      (proposta) => proposta.id == dadosProposta.id
    );

    const propostasDeleted = pauta.propostas.splice(indexProposta, 1);

    PautaService.put(pauta).then((newPauta) => {
      setFeedbackPropostaDeletada(true);
      PropostaService.removerPresenca(propostasDeleted[0].id).then(
        (response) => {
          // Salvamento de histórico
          ExportPdfService.exportProposta(response.id).then((file) => {
            let arquivo = new Blob([file], { type: "application/pdf" });
            PropostaService.addHistorico(
              response.id,
              "Removida da Pauta #" + newPauta.numeroSequencial,
              arquivo,
              CookieService.getUser().id
            ).then(() => { });
          });
        }
      );

      location.state = { pauta: newPauta }; // Atualizando a pauta na página
      setPauta(newPauta); // Atualizando a pauta na variável do front
      setProposta(false); // Anulando a proposta que estava sendo exibida
      setListaProposta(newPauta.propostas); // Atualizando a lista de propostas
      setDadosProposta(null); // Anulando os dados da proposta que estava sendo exibido
    });
  };

  /** Função para baixar o arquivo pdf da respectiva pauta */
  const baixarPDF = () => {
    ExportPdfService.exportPauta(pauta.id).then((response) => {
      let blob = new Blob([response], { type: "application/pdf" });
      let url = URL.createObjectURL(blob);
      let link = document.createElement("a");
      link.href = url;
      link.download = "Pauta - " + pauta.numeroSequencial + ".pdf";
      link.click();
    });
  };

  /** Verifica se todos os campos necessários para criação de uma ata foram preenchidos */
  const isAllFieldsFilled = () => {
    // Verifica se os pareceres das propostas foram preenchidos assim como o número sequencial da ata
    let isFilled = pauta.propostas.every((proposta) => {
      if (proposta.parecerComissao == "APROVADO") {
        return (
          proposta.parecerComissao != null &&
          numeroSequencialAta != null &&
          numeroSequencialAta != undefined &&
          numeroSequencialAta != ""
        );
      } else {
        return (
          proposta.parecerComissao != null &&
          proposta.parecerInformacao != null && // Essa variável sempre começa como null
          proposta.parecerInformacao != "<p><br></p>" && // Necessário para o editor de texto, pois ele insere esse código quando o campo está vazio
          numeroSequencialAta != null &&
          numeroSequencialAta != undefined &&
          numeroSequencialAta != ""
        );
      }
    });

    return isFilled;
  };

  /** Função para formatar uma lista de objetos, retornando somente o id de cada objeto presente, com a lista sendo recebida como parâmetro */
  const retornarIdsObjetos = (listaObjetos) => {
    let listaNova = [];
    for (let objeto of listaObjetos) {
      listaNova.push({ id: objeto.id });
    }

    return listaNova;
  };

  /** Função para formatar o HTML em casos como a falta de fechamentos em tags "<br>" */
  const formatarHtml = (texto) => {
    texto = texto.replace(/<br>/g, "<br/>");
    return texto;
  };

  /** Função que inicia a criação das duas atas */
  const criarAtas = () => {

    let propostasAprovadas = pauta.propostas.filter((proposta) => {
      return (proposta.parecerComissao == "APROVADO" || proposta.parecerComissao == "REPROVADO");
    });

    let propostasReprovadas = pauta.propostas.filter((proposta) => {
      return (proposta.parecerComissao != "APROVADO" && proposta.parecerComissao != "REPROVADO");
    });

    let ataPublicada = { ...pauta, numeroSequencial: numeroSequencialAta, publicadaDg: false, publicada: true }
    let ataNaoPublicada = { ...pauta, numeroSequencial: numeroSequencialAta, publicadaDg: true, publicada: false }

    ataPublicada.propostas = propostasAprovadas.filter((proposta) => {
      return proposta.publicada;
    });

    ataNaoPublicada.propostas = propostasAprovadas.filter((proposta) => {
      return !proposta.publicada;
    });

    atualizarPropostas(propostasReprovadas, null);

    if (ataPublicada.propostas.length > 0) {
      criarAta(ataPublicada);
    }
    if (ataNaoPublicada.propostas.length > 0) {
      criarAta(ataNaoPublicada);
    }

    PautaService.delete(pauta.id).then((response) => {
      feedbackAta();
    });
  };

  /** Função para criar somente uma ata, recebendo seu objeto como parâmetro */
  const criarAta = (ata) => {
    let propostasAta = [...ata.propostas];
    ata.propostas = retornarIdsObjetos(ata.propostas);

    AtaService.post(ata).then((response) => {
      atualizarPropostas(propostasAta, response.numeroSequencial);
    })
  }

  /** Cria a notificacao da demanda */
  const sendNotification = (propostaAux) => {
    let tipoNotificacao;

    switch (propostaAux.parecerComissao) {
      case "APROVADO":
        tipoNotificacao = NotificacaoService.aprovadoComissao;
        break;
      case "REPROVADO":
        tipoNotificacao = NotificacaoService.reprovadoComissao;
        break;
      case "MAIS_INFORMACOES":
        tipoNotificacao = NotificacaoService.maisInformacoesComissao;
        break;
      default:
        tipoNotificacao = NotificacaoService.reprovadoComissao;
        break;
    }

    const demandaNotificacao = JSON.parse(JSON.stringify(propostaAux.demanda));
    const notificacao = NotificacaoService.createNotificationObject(
      tipoNotificacao,
      demandaNotificacao,
      CookieService.getUser().id
    );
    enviar(
      `/app/weg_ssm/notificacao/${demandaNotificacao.solicitante.id}`,
      JSON.stringify(notificacao)
    );
  };

  /** Função para atualizar o status das propostas e adicionar históricos na criação de uma ata */
  const atualizarPropostas = (propostas, numeroSequencialAta) => {
    for (let proposta of propostas) {
      PropostaService.atualizacaoAta(proposta.id, proposta.parecerComissao, formatarHtml(proposta.parecerInformacao)).then((response) => {

        //Salvamento de histórico e atualização da demanda
        ExportPdfService.exportProposta(response.id).then((file) => {
          let arquivo = new Blob([file], { type: "application/pdf" });

          switch (response.parecerComissao) {
            case "APROVADO":
              PropostaService.addHistorico(response.id, "Adicionada na Ata #" + numeroSequencialAta, arquivo, CookieService.getUser().id).then(() => { });
              break;
            case "REPROVADO":
              PropostaService.addHistorico(response.id, "Proposta Reprovada", arquivo, CookieService.getUser().id).then(() => { });
              DemandaService.atualizarStatus(response.demanda.id, "CANCELLED").then(() => { });
              break;
            case "MAIS_INFORMACOES":
              PropostaService.addHistorico(response.id, "Enviada para Edição", arquivo, CookieService.getUser().id).then(() => { });
              break;
          }
        });

        sendNotification(JSON.parse(JSON.stringify(proposta)));
      });
    }
  };

  /** Função para salvar o numero sequencial da ata digitado no input */
  const salvarNumeroSequencial = (event) => {
    setNumeroSequencialAta(event.target.value);
  }

  return (
    <FundoComHeader>
      {/* Tradução para libras */}
      <VLibras forceOnload />

      {/* Feedback campos faltantes */}
      <Feedback
        open={feedbackCamposFaltantes}
        handleClose={() => {
          setFeedbackCamposFaltantes(false);
        }}
        status={"erro"}
        mensagem={texts.modalCriarAta.feedback}
      />
      {/* Feedback proposta deletada da pauta */}
      <Feedback
        open={feedbackPropostaDeletada}
        handleClose={() => {
          setFeedbackPropostaDeletada(false);
        }}
        status={"sucesso"}
        mensagem={texts.detalhesPauta.feedbacks.feedback1}
      />
      {/* Feedback pauta sem propostas */}
      <Feedback
        open={feedbackSemPropostas}
        handleClose={() => {
          setFeedbackSemPropostas(false);
        }}
        status={"erro"}
        mensagem={texts.detalhesPauta.feedbacks.feedback3}
      />
      {/* Modal de confirmação para tirar proposta de pauta */}
      <ModalConfirmacao
        open={modal}
        setOpen={setModal}
        textoModal={"tirarPropostaDePauta"}
        textoBotao={"sim"}
        onConfirmClick={deletePropostaFromPauta}
        onCancelClick={() => { }}
      />

      {/* Modal de confirmação para criar uma ata */}
      <ModalConfirmacao
        open={modalCriarAta}
        setOpen={setModalCriarAta}
        textoModal={"criarAta"}
        textoBotao={"sim"}
        onConfirmClick={criarAtas}
        onCancelClick={() => { }}
      />

      <Box className="p-2 mb-16" sx={{ minWidth: "58rem" }}>
        <Box className="flex w-full relative">
          <Caminho />
          <Box
            className=" absolute"
            sx={{ top: "10px", right: "20px", cursor: "pointer" }}
          >
            <Tooltip title={texts.detalhesPauta.baixarPDF}>
              <SaveAltOutlinedIcon
                fontSize="large"
                className="delay-120 hover:scale-110 duration-300"
                sx={{ color: "icon.main" }}
                onClick={baixarPDF}
              />
            </Tooltip>
          </Box>
        </Box>
        {/* Corpo da pauta */}
        <Box className="flex flex-col justify-center relative items-center mt-5">
          <Box
            className="flex flex-col gap-5 border rounded relative p-10 drop-shadow-lg"
            sx={{ width: "55rem" }}
          >
            {/* Informações do header da pauta */}
            <Box className="flex justify-center flex-col">
              <Typography
                fontSize={FontConfig.title}
                sx={{
                  fontWeight: "600",
                  cursor: "default",
                  inlineSize: "800px",
                  overflowWrap: "break-word",
                  textAlign: "center",
                  color: "primary.main",
                }}
                onClick={() => {
                  lerTexto(texts.detalhesPauta.pauta);
                }}
              >
                {texts.detalhesPauta.pauta}
              </Typography>
              {/* Número sequencial */}
              <Typography
                sx={{ fontWeight: "600", cursor: "default", marginTop: "1%" }}
                onClick={() => {
                  lerTexto(
                    texts.detalhesPauta.numeroSequencial +
                    ": " +
                    pauta.numeroSequencial
                  );
                }}
              >
                {texts.detalhesPauta.numeroSequencial}: {pauta.numeroSequencial}
              </Typography>
              {/* Comissão */}
              <Typography
                sx={{ fontWeight: "600", cursor: "default", marginTop: "1%" }}
                onClick={() => {
                  lerTexto(
                    texts.detalhesPauta.comissao +
                    ": " +
                    pauta.comissao.nomeForum
                  );
                }}
              >
                {texts.detalhesPauta.comissao}: {pauta.comissao.siglaForum} -{" "}
                {pauta.comissao.nomeForum}
              </Typography>
              {/* Data da reunião da comissão */}
              <Typography
                sx={{ fontWeight: "600", cursor: "default", marginTop: "1%" }}
                onClick={() => {
                  lerTexto(
                    texts.detalhesPauta.reuniaoDoForum +
                    ": " +
                    DateService.getFullDateUSFormat(
                      DateService.getDateByMySQLFormat(pauta?.dataReuniao),
                      texts.linguagem
                    )
                  );
                }}
              >
                {texts.detalhesPauta.reuniaoDoForum}:{" "}
                {DateService.getFullDateUSFormat(
                  DateService.getDateByMySQLFormat(pauta?.dataReuniao),
                  texts.linguagem
                )}
              </Typography>
              {/* Data da reunião da DG */}
              <Typography
                sx={{ fontWeight: "600", cursor: "default", marginTop: "1%" }}
                onClick={() => {
                  lerTexto(
                    texts.detalhesPauta.analistaResponsavel +
                    ": " +
                    pauta.analistaResponsavel.nome
                  );
                }}
              >
                {texts.detalhesPauta.analistaResponsavel}:{" "}
                {pauta.analistaResponsavel.nome}
              </Typography>

              {/* Input para informar o número sequencial da ata */}
              <Box sx={{ marginBottom: "1%", width: "80%", height: "5%", display: "flex", flexDirection: "row" }}>
                <Typography sx={{ fontWeight: "600", cursor: "default", marginTop: "1%" }}>
                  Número Sequencial da Ata:
                </Typography>
                <Typography
                  fontSize={props.fontConfig}
                  sx={{ fontWeight: "800", cursor: "default" }}
                  className="text-red-600"
                  gutterBottom
                >
                  *
                </Typography>

                <Input
                  sx={{ width: "5rem", marginLeft: "2%" }}
                  onChange={salvarNumeroSequencial}
                />
              </Box>

              <Divider sx={{ marginTop: "1%" }} />
            </Box>

            {/* Verificação para mostrar o sumário da pauta ou uma proposta */}
            {!proposta ? (
              // Mostrar o sumário com os títulos das propostas
              <Box>
                <Typography
                  fontSize={FontConfig.title}
                  sx={{
                    fontWeight: "600",
                    cursor: "default",
                    inlineSize: "800px",
                    overflowWrap: "break-word",
                    textAlign: "center",
                  }}
                  color="primary.main"
                  onClick={() => {
                    lerTexto(texts.detalhesPauta.sumario);
                  }}
                >
                  {texts.detalhesPauta.sumario}
                </Typography>
                <Box className="flex flex-col items-center justify-center">
                  {isSummaryVisible ? (
                    pauta.propostas?.map((proposta, index) => {
                      return (
                        <Box
                          key={index}
                          className="w-full border-solid border border-l-4 px-4 drop-shadow-sm rounded flex items-center mt-4"
                          sx={{
                            height: "2.5rem",
                            borderLeftColor: "primary.main",
                            backgroundColor: "background.default",
                            fontWeight: "300",
                            cursor: "pointer",
                            "&:hover": { backgroundColor: "component.main" },
                          }}
                          onClick={() => {
                            if (lendoTexto) {
                              lerTexto(proposta.titulo);
                            } else {
                              onClickProposta(index);
                            }
                          }}
                        >
                          <Typography
                            fontSize={FontConfig.medium}
                            sx={{
                              color: "primary.main",
                              overflow: "hidden",
                              whiteSpace: "nowrap",
                              textOverflow: "ellipsis",
                            }}
                          >
                            {index + 1} - {proposta.titulo}
                          </Typography>
                        </Box>
                      );
                    })
                  ) : (
                    <Typography
                      fontSize={FontConfig.medium}
                      onClick={() => {
                        lerTexto(texts.detalhesPauta.nenhumaPropostaAdicionada);
                      }}
                    >
                      {texts.detalhesPauta.nenhumaPropostaAdicionada}
                    </Typography>
                  )}
                </Box>
              </Box>
            ) : (
              // Mostrar uma proposta e seus dados
              <Box>
                <Box
                  sx={{
                    display: "flex",
                    alignItems: "center",
                    justifyContent: "center",
                  }}
                >
                  <Typography
                    sx={{
                      marginBottom: "2%",
                      display: "flex",
                      justifyContent: "center",
                    }}
                    fontSize={FontConfig.title}
                    fontWeight={650}
                    onClick={() => {
                      lerTexto(texts.detalhesPauta.proposta);
                    }}
                  >
                    {texts.detalhesPauta.proposta} {indexProposta + 1}
                  </Typography>
                  <IconButton
                    sx={{ position: "absolute", left: "90%" }}
                    onClick={onDeletePropostaClick}
                  >
                    <DeleteIcon
                      sx={{
                        width: "32px",
                        height: "32px",
                        color: "primary.main",
                      }}
                    />
                  </IconButton>
                </Box>
                <DetalhesProposta
                  setDadosProposta={setDadosProposta}
                  parecerComissao={dadosProposta.parecerComissao || ""}
                  parecerInformacao={dadosProposta.parecerInformacao || ""}
                  emAprovacao={true}
                  propostaId={dadosProposta.id}
                />
              </Box>
            )}

          </Box>
        </Box>

        {/* Botões da pauta (voltar, próximo...) */}
        <Box
          className="flex fixed justify-end items-center"
          sx={{ width: "30rem", bottom: "20px", right: "20px" }}
        >
          <Box className="flex justify-center">
            <Box
              className="flex fixed justify-end items-center"
              sx={{ width: "22rem", bottom: "20px", right: "20px" }}
            >
              <Box className="flex justify-center gap-3">
                <Box className="flex justify-end">
                  <Box
                    className={`${display} items-center mr-1 justify-end`}
                    sx={{ animation: `${aparecerSumir} 1.2s forwards` }}
                  >
                    <Box className="flex gap-2">
                      <Tooltip title={texts.detalhesAta.voltar}>
                        <IconButton
                          color="primary"
                          size="large"
                          onClick={voltar}
                        >
                          <ArrowBackIosNewIcon />
                        </IconButton>
                      </Tooltip>
                      <Tooltip>
                        <IconButton
                          color="primary"
                          size="large"
                          onClick={voltarSumario}
                        >
                          <OtherHousesIcon />
                        </IconButton>
                      </Tooltip>
                      <Tooltip title={texts.detalhesAta.proximo}>
                        <IconButton
                          color="primary"
                          size="large"
                          onClick={proximo}
                        >
                          <ArrowForwardIosIcon />
                        </IconButton>
                      </Tooltip>
                    </Box>
                  </Box>
                  <Tooltip title={texts.detalhesAta.navegacao}>
                    <ButtonBase
                      variant="contained"
                      color="primary"
                      className="!rounded-full !p-3 hover:!outline-none"
                      sx={{
                        backgroundColor: "primary.main",
                        "&:hover": {
                          boxShadow:
                            "0px 2px 4px -1px rgba(0,0,0,0.2), 0px 4px 5px 0px rgba(0,0,0,0.14), 0px 1px 10px 0px rgba(0,0,0,0.12)",
                          backgroundColor: "rgb(0, 60, 109)",
                        },
                      }}
                      onClick={() => {
                        animarBotoes();
                        setMinimizar(!minimizar);
                      }}
                    >
                      <DensitySmallIcon
                        sx={{
                          animation: `${girarIcon} 1.2s forwards`,
                          color: "white",
                        }}
                      />
                    </ButtonBase>
                  </Tooltip>
                </Box>
                <Tooltip title={texts.detalhesPauta.criarAta}>
                  <Box
                    onClick={() => {
                      if (!isAllFieldsFilled()) {
                        setFeedbackCamposFaltantes(true);
                      } else {
                        setModalCriarAta(true)
                      }
                    }}
                    className="flex justify-center items-center w-12 h-12 rounded-full cursor-pointer delay-120 hover:scale-110 duration-300"
                    sx={{
                      backgroundColor: "primary.main",
                      fontSize: FontConfig.default,
                      marginLeft: "1rem",
                      color: "#FFFF",
                    }}
                  >
                    <AddHomeOutlinedIcon
                      sx={{
                        transform: "rotate(180deg) scaleX(-1)",
                        fontSize: "32px",
                      }}
                    />
                  </Box>
                </Tooltip>
              </Box>
            </Box>
          </Box>
        </Box>
      </Box>
    </FundoComHeader>
  );
};

export default DetalhesPauta;
