import React, { useState, useContext, useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom";

import {
  Box,
  Typography,
  Divider,
  Tooltip,
  IconButton,
  Input,
  TextField,
  MenuItem,
} from "@mui/material";

import RateReviewOutlinedIcon from "@mui/icons-material/RateReviewOutlined";
import SaveAltOutlinedIcon from "@mui/icons-material/SaveAltOutlined";
import OtherHousesIcon from "@mui/icons-material/OtherHouses";
import DensitySmallIcon from "@mui/icons-material/DensitySmall";
import DeleteIcon from "@mui/icons-material/Delete";
import ArrowForwardIosIcon from "@mui/icons-material/ArrowForwardIos";
import ArrowBackIosNewIcon from "@mui/icons-material/ArrowBackIosNew";
import AddHomeOutlinedIcon from "@mui/icons-material/AddHomeOutlined";
import TextFieldsIcon from "@mui/icons-material/TextFields";
import CloseIcon from "@mui/icons-material/Close";
import UndoIcon from "@mui/icons-material/Undo";

import SpeedDial from "@mui/material/SpeedDial";
import SpeedDialAction from "@mui/material/SpeedDialAction";

import Feedback from "../../components/Feedback/Feedback";
import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Caminho from "../../components/Caminho/Caminho";
import DetalhesProposta from "../../components/DetalhesProposta/DetalhesProposta";
import ModalConfirmacao from "../../components/Modais/Modal-confirmacao/ModalConfirmacao";
import Ajuda from "../../components/Ajuda/Ajuda";
import CaixaTextoQuill from "../../components/CaixaTextoQuill/CaixaTextoQuill";

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

import Tour from "reactour";

/** Página para mostrar os detalhes da pauta selecionada, com opção de download para pdf */
const DetalhesPauta = (props) => {
  /** Context para alterar a linguagem do sistema */
  const { texts } = useContext(TextLanguageContext);

  /** Context para alterar o tamanho da fonte */
  const { FontConfig } = useContext(FontContext);

  /** Context para ler o texto da tela */
  const { lendoTexto, lerTexto, librasAtivo } = useContext(
    SpeechSynthesisContext
  );

  /** Navigate utilizado para navegar para uma determianda página */
  const navigate = useNavigate();

  /** Variável do react-router-dom que guarda as informações da rota atual */
  const location = useLocation();

  /** UseState para guardar a pauta atual */
  const [pauta, setPauta] = useState(EntitiesObjectService.pauta());

  /** Lista provisória de propostas para preencher a tela */
  const [listaProposta, setListaProposta] = useState([
    EntitiesObjectService.proposta(),
  ]);

  /** Variável de verificação utilizada para mostrar o sumário ou uma proposta */
  const [proposta, setProposta] = useState(false);

  /** Proposta a ser adicionado o parecer da comissão */
  const [propostaParecer, setPropostaParecer] = useState(null);

  /** Variável utilizada para passar para a próxima proposta */
  const [botaoProximo, setBotaoProximo] = useState(true);

  /** Variável utilizada para mostrar os dados de uma proposta */
  const [dadosProposta, setDadosProposta] = useState(listaProposta[0]);

  /** Index de uma proposta, utilizado para mostrar os dados de uma porposta específica */
  const [indexProposta, setIndexProposta] = useState(-1);

  /** Estado para mostrar o modal de confirmação */
  const [modal, setModal] = useState(false);

  /** UseState para definir se o modal de confirmação para a criação de ata está aberto */
  const [modalCriarAta, setModalCriarAta] = useState(false);

  /** Estado para mostrar o sumário ou não, usado também para atualizar a página com as novas propostas */
  const [isSummaryVisible, setIsSummaryVisible] = useState(true);

  /** Context do WebSocket */
  const { enviar } = useContext(WebSocketContext);

  /** Feedback para quando a pauta não possuir propostas */
  const [feedbackSemPropostas, setFeedbackSemPropostas] = useState(false);

  /** Feedback para quando o usuário deletar uma proposta da pauta */
  const [feedbackPropostaDeletada, setFeedbackPropostaDeletada] =
    useState(false);

  /** Feedback para quando o usuário não preencher todos os campos obrigatórios */
  const [feedbackCamposFaltantes, setFeedbackCamposFaltantes] = useState(false);

  /** useState utilizado para armazenar o número sequencial da ata */
  const [numeroSequencialAta, setNumeroSequencialAta] = useState();

  /** useState para abrir e fechar o tour */
  const [isTourOpen, setIsTourOpen] = useState(false);

  const stepsTour = [
    {
      selector: "#primeiro",
      content: texts.detalhesPauta.tour.tour1,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#segundo",
      content: texts.detalhesPauta.tour.tour2,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#terceiro",
      content: texts.detalhesPauta.tour.tour3,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#quarto",
      content: texts.detalhesPauta.tour.tour4,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
  ];

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
    setPropostaParecer(null);
    setProposta(true);
  };

  /** Função para passar para a próxima proposta */
  const proximo = () => {
    if (!lendoTexto && !librasAtivo) {
      if (indexProposta == listaProposta.length - 1) {
        setBotaoProximo(false);
      } else {
        setProposta(false);
        setDadosProposta(pauta.propostas[indexProposta + 1]);
        setIndexProposta(indexProposta + 1);
        setPropostaParecer(null);
      }
    } else if (librasAtivo) {
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
      setPropostaParecer(null);
    }
  };

  /** Função para voltar ao sumário da pauta */
  const voltarSumario = () => {
    setBotaoProximo(true);
    setIndexProposta(-1);
    setProposta(false);
  };

  // Variável utilizada para mostrar os botões de navegação da página
  const actions = [
    { icon: <ArrowForwardIosIcon />, name: "Próximo", onClick: proximo },
    { icon: <OtherHousesIcon />, name: "Início", onClick: voltarSumario },
    { icon: <ArrowBackIosNewIcon />, name: "Voltar", onClick: voltar },
  ];

  /** Feedback de ata criada com sucesso */
  const feedbackAta = () => {
    navigate("/", { state: { feedback: "ata-criada" } });
  };

  /** Função acionada quando é clicado no botão de delete de alguma proposta */
  const onDeletePropostaClick = () => {
    setModal(true);
  };

  /** Função para criar e retornar um objeto de histórico para salvamento */
  const retornaObjetoHistorico = (acaoRealizada, informacaoAdicional) => {
    const historico = {
      data: new Date(),
      acaoRealizada: acaoRealizada,
      autor: { id: CookieService.getUser().id },
      informacaoAdicional: informacaoAdicional,
    };
    return historico;
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
              retornaObjetoHistorico(
                "Removida da Pauta #" + newPauta.numeroSequencial,
                null
              ),
              arquivo
            ).then(() => {});
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
    if (texto) {
      texto = texto.replace(/<br>/g, "<br/>");
    }
    return texto;
  };

  /** Função que inicia a criação das duas atas */
  const criarAtas = () => {
    let propostasAprovadas = pauta.propostas.filter((proposta) => {
      return (
        proposta.parecerComissao == "APROVADO" ||
        proposta.parecerComissao == "REPROVADO"
      );
    });

    let propostasReprovadas = pauta.propostas.filter((proposta) => {
      return (
        proposta.parecerComissao != "APROVADO" &&
        proposta.parecerComissao != "REPROVADO"
      );
    });

    let ataPublicada = {
      ...pauta,
      numeroSequencial: numeroSequencialAta,
      publicadaDg: false,
      publicada: true,
    };
    let ataNaoPublicada = {
      ...pauta,
      numeroSequencial: numeroSequencialAta,
      publicadaDg: true,
      publicada: false,
    };

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
    });
  };

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
      PropostaService.atualizacaoAta(
        proposta.id,
        proposta.parecerComissao,
        formatarHtml(proposta.parecerInformacao)
      ).then((response) => {
        //Salvamento de histórico e atualização da demanda
        ExportPdfService.exportProposta(response.id).then((file) => {
          let arquivo = new Blob([file], { type: "application/pdf" });

          switch (response.parecerComissao) {
            case "APROVADO":
              PropostaService.addHistorico(
                response.id,
                retornaObjetoHistorico(
                  "Adicionada na Ata #" + numeroSequencialAta,
                  formatarHtml(proposta.parecerInformacao)
                ),
                arquivo
              ).then(() => {});
              break;
            case "REPROVADO":
              PropostaService.addHistorico(
                response.id,
                retornaObjetoHistorico(
                  "Proposta Reprovada",
                  formatarHtml(proposta.parecerInformacao)
                ),
                arquivo
              ).then(() => {});
              DemandaService.atualizarStatus(
                response.demanda.id,
                "CANCELLED"
              ).then(() => {});
              break;
            case "MAIS_INFORMACOES":
              PropostaService.addHistorico(
                response.id,
                retornaObjetoHistorico(
                  "Enviada para Edição",
                  formatarHtml(proposta.parecerInformacao)
                ),
                arquivo
              ).then(() => {});
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
  };

  /** Mostrar campos para inserir parecer da proposta */
  const abrirParecerProposta = (event, proposta) => {
    event.stopPropagation();
    setPropostaParecer(proposta);
  };

  return (
    <FundoComHeader>
      {/* Tour de ajuda para a criação da pauta*/}
      <Tour
        steps={stepsTour}
        isOpen={isTourOpen}
        onRequestClose={() => setIsTourOpen(false)}
        accentColor="#00579D"
        rounded={10}
        showCloseButton={false}
      />

      <Ajuda onClick={() => setIsTourOpen(true)} />

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
        onCancelClick={() => {}}
      />

      {/* Modal de confirmação para criar uma ata */}
      <ModalConfirmacao
        open={modalCriarAta}
        setOpen={setModalCriarAta}
        textoModal={"criarAta"}
        textoBotao={"sim"}
        onConfirmClick={criarAtas}
        onCancelClick={() => {}}
      />

      <Box className="p-2 mb-16" sx={{ minWidth: "58rem" }}>
        <Box className="flex w-full relative">
          <Caminho />
          <Box
            className=" absolute"
            sx={{ top: "10px", right: "20px", cursor: "pointer" }}
            id="segundo"
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
            className="flex flex-col gap-5 border rounded p-10 pt-6 border-t-6 relative"
            sx={{ width: "60rem", borderTopColor: "primary.main" }}
            id="primeiro"
          >
            {/* Informações do header da pauta */}
            <Box className="flex justify-center flex-col">
              <Box className="w-full flex justify-between items-center">
                <Typography
                  fontSize={FontConfig.title}
                  sx={{
                    fontWeight: "600",
                    cursor: "default",
                    overflowWrap: "break-word",
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
                  component="span"
                  className="whitespace-nowrap"
                  sx={{ fontWeight: "600", cursor: "default" }}
                  fontSize={FontConfig.medium}
                  onClick={() => {
                    lerTexto(
                      texts.detalhesPauta.numeroSequencial +
                        ": " +
                        pauta.numeroSequencial
                    );
                  }}
                >
                  {texts.detalhesPauta.numeroSequencial}:{" "}
                  {pauta.numeroSequencial}
                </Typography>
              </Box>

              <Divider />

              {/* Comissão */}
              <Typography
                sx={{ fontWeight: "600", cursor: "default", marginTop: "2%" }}
                fontSize={FontConfig.medium}
                onClick={() => {
                  lerTexto(
                    texts.detalhesPauta.comissao +
                      ": " +
                      pauta.comissao.nomeForum
                  );
                }}
              >
                {texts.detalhesPauta.comissao}:{" "}
                <Typography component="span" fontSize={FontConfig.default}>
                  {pauta.comissao.siglaForum} - {pauta.comissao.nomeForum}
                </Typography>
              </Typography>

              {/* Data da reunião da comissão */}
              <Typography
                sx={{ fontWeight: "600", cursor: "default", marginTop: "1%" }}
                fontSize={FontConfig.medium}
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
                <Typography component="span" fontSize={FontConfig.default}>
                  {DateService.getFullDateUSFormat(
                    DateService.getDateByMySQLFormat(pauta?.dataReuniao),
                    texts.linguagem
                  )}
                </Typography>
              </Typography>

              {/* Analista responsável */}
              <Typography
                sx={{ fontWeight: "600", cursor: "default", marginTop: "1%" }}
                fontSize={FontConfig.medium}
                onClick={() => {
                  lerTexto(
                    texts.detalhesPauta.analistaResponsavel +
                      ": " +
                      pauta.analistaResponsavel.nome
                  );
                }}
              >
                {texts.detalhesPauta.analistaResponsavel}:{" "}
                <Typography component="span" fontSize={FontConfig.default}>
                  {pauta.analistaResponsavel.nome}
                </Typography>
              </Typography>

              {/* Input para informar o número sequencial da ata */}
              <Box
                className="flex"
                sx={{
                  marginBottom: "1%",
                  width: "80%",
                  height: "5%",
                }}
              >
                <Typography
                  sx={{ fontWeight: "600", cursor: "default", marginTop: "1%" }}
                  fontSize={FontConfig.medium}
                  onClick={() => {
                    lerTexto(texts.detalhesPauta.numeroSequencialAta);
                  }}
                >
                  {texts.detalhesPauta.numeroSequencialAta}:
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
              // Mostra o sumário com os títulos das propostas
              <Box>
                <Box className="border-b">
                  <Typography
                    fontSize={FontConfig.smallTitle}
                    sx={{
                      fontWeight: "600",
                      cursor: "default",
                      overflowWrap: "break-word",
                    }}
                    color="primary.main"
                    onClick={() => {
                      lerTexto(texts.detalhesPauta.sumario);
                    }}
                    textAlign="center"
                  >
                    {texts.detalhesPauta.sumario}
                  </Typography>
                </Box>
                <Box className="flex flex-col items-center justify-center">
                  {isSummaryVisible ? (
                    <>
                      {pauta.propostas?.map((proposta, index) => {
                        return (
                          <Box key={index} className="w-full">
                            <Box
                              className="w-full flex items-center border-solid border border-l-4 px-4 py-2 mt-4 cursor-pointer"
                              sx={{
                                borderLeftColor: "primary.main",
                                backgroundColor: "background.default",
                                fontWeight: "300",
                                "&:hover": {
                                  backgroundColor: "component.main",
                                },
                              }}
                              onClick={() => {
                                if (lendoTexto) {
                                  lerTexto(proposta.titulo);
                                } else if (librasAtivo) {
                                } else {
                                  onClickProposta(index);
                                }
                              }}
                            >
                              <Box className="w-full flex justify-between items-center">
                                <Typography
                                  fontSize={FontConfig.medium}
                                  className="truncate"
                                  color="primary"
                                >
                                  {index + 1} - {proposta.titulo}
                                </Typography>

                                <Tooltip title={texts.detalhesPauta.addParecer}>
                                  <IconButton
                                    size="small"
                                    onClick={(event) =>
                                      abrirParecerProposta(event, proposta)
                                    }
                                  >
                                    <RateReviewOutlinedIcon />
                                  </IconButton>
                                </Tooltip>
                              </Box>
                            </Box>
                            {!!propostaParecer &&
                              propostaParecer.id == proposta.id && (
                                <InserirParecer
                                  proposta={propostaParecer}
                                  setProposta={setPropostaParecer}
                                  setPauta={setPauta}
                                />
                              )}
                          </Box>
                        );
                      })}
                    </>
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
              // Mostra uma proposta e seus dados
              <Box className="w-full">
                <Box className="flex items-center justify-center relative border-b mb-4">
                  <Typography
                    className="w-full text-center"
                    fontSize={FontConfig.smallTitle}
                    fontWeight={500}
                    onClick={() => {
                      lerTexto(texts.detalhesPauta.proposta);
                    }}
                  >
                    {texts.detalhesPauta.proposta} {indexProposta + 1}
                  </Typography>
                  <Box className="w-full flex justify-between items-center absolute">
                    <Tooltip title="Voltar ao sumário">
                      <IconButton color="primary" onClick={voltarSumario}>
                        <UndoIcon />
                      </IconButton>
                    </Tooltip>
                    <Tooltip title={texts.DetalhesDemanda.remover}>
                      <IconButton
                        onClick={onDeletePropostaClick}
                        color="primary"
                      >
                        <DeleteIcon
                          sx={{
                            width: "28px",
                            height: "28px",
                          }}
                        />
                      </IconButton>
                    </Tooltip>
                  </Box>
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
          sx={{ width: "22rem", bottom: "20px", right: "80px", gap: "1rem" }}
        >
          <Box>
            <SpeedDial
              ariaLabel="SpeedDial playground example"
              icon={<DensitySmallIcon />}
              direction="left"
              id="terceiro"
            >
              {actions.map((action) => (
                <SpeedDialAction
                  key={action.name}
                  icon={action.icon}
                  tooltipTitle={action.name}
                  onClick={action.onClick}
                />
              ))}
            </SpeedDial>
          </Box>
          <Box>
            <Tooltip title={texts.detalhesPauta.criarAta}>
              <Box
                id="quarto"
                onClick={() => {
                  if (!isAllFieldsFilled()) {
                    setFeedbackCamposFaltantes(true);
                  } else {
                    setModalCriarAta(true);
                  }
                }}
                className="flex justify-center items-center rounded-full cursor-pointer delay-120 hover:scale-110 duration-300"
                sx={{
                  backgroundColor: "primary.main",
                  fontSize: FontConfig.default,
                  marginLeft: "1rem",
                  color: "#FFFF",
                  width: "3.5rem",
                  height: "3.5rem",
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
    </FundoComHeader>
  );
};

const InserirParecer = ({
  proposta = EntitiesObjectService.proposta(),
  setProposta = () => {},
  setPauta = () => {},
}) => {
  /** Context para alterar a linguagem do sistema */
  const { texts } = useContext(TextLanguageContext);

  /** Context para alterar o tamanho da fonte */
  const { FontConfig } = useContext(FontContext);

  /** Fechar inserir o parecer */
  const handleOnCloseParecerClick = () => {
    setProposta(null);
  };

  /** Adiciona o novo parecer da comissão na proposta */
  const handleOnParecerComissaoChange = (event) => {
    setProposta({ ...proposta, parecerComissao: event.target.value });
  };

  /** Adiciona a nova informação do parecer da comissão na proposta */
  const handleOnInformacaoComissaoChange = (event) => {
    setProposta({ ...proposta, parecerInformacao: event });
  };

  /** Salva a pauta com as novas informações da proposta */
  const handleChangePauta = () => {
    setPauta((prevState) => {
      let listAux = [];
      for (let propostaAux of prevState.propostas) {
        if (propostaAux.id === proposta.id) {
          listAux = [...listAux, proposta];
        } else {
          listAux = [...listAux, propostaAux];
        }
      }
      return { ...prevState, propostas: listAux };
    });
  };

  /** Salva a pauta toda vez que a proposta for atualizada */
  useEffect(() => {
    const delayDebounceFn = setTimeout(() => {
      handleChangePauta();
    }, 500);

    return () => clearTimeout(delayDebounceFn);
  }, [proposta]);

  /** Context para ler o texto da tela */
  const { lerTexto } = useContext(SpeechSynthesisContext);

  return (
    <Box className="mt-1">
      <Box className="w-full flex justify-between items-center">
        <Typography
          fontSize={FontConfig.veryBig}
          color="primary"
          fontWeight={600}
          onClick={() => {
            lerTexto(texts.detalhesPauta.inserirParecer);
          }}
        >
          {texts.detalhesPauta.inserirParecer}
        </Typography>
        <Tooltip title={texts.detalhesPauta.fechar}>
          <IconButton color="primary" onClick={handleOnCloseParecerClick}>
            <CloseIcon />
          </IconButton>
        </Tooltip>
      </Box>
      <Box>
        <Box className="flex gap-2">
          <Typography
            color="primary"
            component="span"
            fontWeight={600}
            fontSize={FontConfig.default}
            onClick={() => {
              lerTexto(texts.detalhesProposta.ppm);
            }}
          >
            {texts.detalhesProposta.ppm}
          </Typography>
          <Typography
            fontSize={FontConfig.default}
            onClick={() => {
              lerTexto(proposta.codigoPPM);
            }}
          >
            {proposta.codigoPPM}
          </Typography>
        </Box>
      </Box>

      <Box className="flex items-end gap-2">
        {/* Parecer da comissão */}
        <Typography
          onClick={() => {
            lerTexto(texts.detalhesPauta.comissao);
          }}
        >
          {texts.detalhesPauta.comissao}:{" "}
        </Typography>
        <TextField
          select
          label={texts.detalhesProposta.parecer}
          value={proposta.parecerComissao ? proposta.parecerComissao : ""}
          onChange={handleOnParecerComissaoChange}
          variant="standard"
          size="small"
          sx={{ width: "10rem", marginLeft: "0.5rem" }}
        >
          <MenuItem key={"Aprovado"} value={"APROVADO"}>
            <Typography fontSize={FontConfig.medium}>
              {texts.detalhesProposta.aprovado}
            </Typography>
          </MenuItem>
          <MenuItem key={"Reprovado"} value={"REPROVADO"}>
            <Typography fontSize={FontConfig.medium}>
              {texts.detalhesProposta.reprovado}
            </Typography>
          </MenuItem>
          <MenuItem key={"Mais informações"} value={"MAIS_INFORMACOES"}>
            <Typography fontSize={FontConfig.medium}>
              {texts.detalhesProposta.devolvido}
            </Typography>
          </MenuItem>
        </TextField>
      </Box>
      <Box className="mt-4">
        <CaixaTextoQuill
          texto={proposta.parecerInformacao}
          onChange={handleOnInformacaoComissaoChange}
          label="parecerComissao"
        />
      </Box>
    </Box>
  );
};

export default DetalhesPauta;
