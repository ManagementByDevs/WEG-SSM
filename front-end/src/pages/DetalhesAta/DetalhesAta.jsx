import { React, useState, useEffect, useContext } from "react";
import { useNavigate, useLocation } from "react-router-dom";

import VLibras from "@djpfs/react-vlibras";

import { keyframes } from "@emotion/react";
import {
  Box,
  Typography,
  Divider,
  Tooltip,
  IconButton,
  ButtonBase,
} from "@mui/material";

import SaveAltOutlinedIcon from "@mui/icons-material/SaveAltOutlined";
import OtherHousesIcon from "@mui/icons-material/OtherHouses";
import DensitySmallIcon from "@mui/icons-material/DensitySmall";
import DoneOutlinedIcon from "@mui/icons-material/DoneOutlined";
import ArrowForwardIosIcon from "@mui/icons-material/ArrowForwardIos";
import ArrowBackIosNewIcon from "@mui/icons-material/ArrowBackIosNew";

import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Caminho from "../../components/Caminho/Caminho";
import Feedback from "../../components/Feedback/Feedback";
import DetalhesProposta from "../../components/DetalhesProposta/DetalhesProposta";

import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";
import EntitiesObjectService from "../../service/entitiesObjectService";
import ExportPdfService from "../../service/exportPdfService";
import PropostaService from "../../service/propostaService";
import AtaService from "../../service/ataService";
import DateService from "../../service/dateService";
import CookieService from "../../service/cookieService";
import DemandaService from "../../service/demandaService";
import NotificacaoService from "../../service/notificacaoService";
import ModalConfirmacao from "../../components/ModalConfirmacao/ModalConfirmacao";
import { WebSocketContext } from "../../service/WebSocketService";
import SpeechSynthesisContext from "../../service/SpeechSynthesisContext";

/** Página para mostrar os detalhes da ata selecionada, com opçao de download para pdf */
const DetalhesAta = (props) => {
  /** Context para trocar a liguagem */
  const { texts } = useContext(TextLanguageContext);

  /** Context para alterar o tamanho da fonte */
  const { FontConfig } = useContext(FontContext);

  /** Context para ler o texto da tela */
  const { lerTexto, lendoTexto } = useContext(SpeechSynthesisContext);

  /** Navigate e location utilizados para verificar state passado por parametro para determinada verificação */
  const navigate = useNavigate();

  /** Location utilizado para saber a localização atual do usuário */
  const location = useLocation();

  /** Lista de propostas provisória apenas para visualização na tela */
  const listaProposta = [EntitiesObjectService.proposta()];

  /** Variável de verificação utilizada para mostrar o sumário ou uma proposta */
  const [proposta, setProposta] = useState(false);

  /** Variável utilizada para mostrar os dados de uma proposta */
  const [dadosProposta, setDadosProposta] = useState(null);

  /** Index de uma proposta, utilizado para mostrar os dados de uma porposta específica */
  const [indexProposta, setIndexProposta] = useState(-1);

  /** Variável utilizada para passar para a próxima proposta */
  const [botaoProximo, setBotaoProximo] = useState(true);

  /** Variável utilizada para minimizar os botões da página */
  const [minimizar, setMinimizar] = useState(false);

  /** Variável para armazenar o objeto da ata */
  const [ata, setAta] = useState(EntitiesObjectService.ata());

  /** Variável para ativar os botões de visualização */
  const [actionButtons, setActionButtons] = useState(true);

  /** feedback para ata criada com sucesso */
  const [feedbackAta, setFeedbackAta] = useState(false);

  /** Feedback para quando o usuário não preencher todos os campos obrigatórios */
  const [feedbackCamposFaltantes, setFeedbackCamposFaltantes] = useState(false);

  /** Estado para feedback de edição feita com sucesso */
  const [feedbackEditSuccess, setFeedbackEditSuccess] = useState(false);

  /** Modal de confirmação para a publicação de uma ata */
  const [modalConfirmacaoPublicacao, setModalConfirmacaoPublicacao] =
    useState(false);

  /**  Context do WebSocket */
  const { enviar } = useContext(WebSocketContext);

  /** useStates utilizados para animar os botões de navegação na ata */
  const [girarIcon, setGirarIcon] = useState(true);

  /** useState para aparecer/desapareceber o botão de navegação da ata */
  const [aparecerSumir, setAparecerSumir] = useState(true);

  /** useState utilizado para setar o display de visualização */
  const [display, setDisplay] = useState("flex");

  /** useEffect usado para feedback de ata criada */
  useEffect(() => {
    if (location.state?.feedback) {
      setFeedbackAta(true);
    }
  }, [location.state?.feedback]);

  /** useEffect utilizado para mostrar uma proposta pelo seu index */
  useEffect(() => {
    if (indexProposta != -1) {
      setProposta(true);
    }
  }, [indexProposta]);

  /** Função que atualiza as propostas da pauta sempre que uma proposta é atualizada */
  useEffect(() => {
    if (indexProposta > -1 && dadosProposta != null) {
      let aux = [...ata.propostas];
      aux[indexProposta] = { ...dadosProposta };
      setAta({ ...ata, propostas: aux });
    }
  }, [dadosProposta]);

  /** useEffect utilizado para desaparecer os botões de navegação */
  useEffect(() => {
    if (location.state?.ata) {
      let aux = location.state.ata;
      setAta(aux);
      if (aux.propostas[0].parecerDG != null) {
        setActionButtons(false);
      }
    }
  }, []);

  /** Função para voltar ao sumário da ata */
  const voltarSumario = () => {
    setBotaoProximo(true);
    setIndexProposta(-1);
    setProposta(false);
  };

  /** Função para selecionar uma proposta do sumário */
  const onClickProposta = (index) => {
    setIndexProposta(index);
    setDadosProposta(ata.propostas[index]);
    setProposta(true);
  };

  /** Função para voltar para uma proposta */
  const voltar = () => {
    if (!lendoTexto) {
      if (indexProposta <= 0) {
        setProposta(false);
        setIndexProposta(-1);
      } else {
        setBotaoProximo(true);
        setProposta(false);
        setDadosProposta(ata.propostas[indexProposta - 1]);
        setIndexProposta(indexProposta - 1);
      }
    } else {
      lerTexto(texts.detalhesAta.voltar);
    }
  };

  /** Função para passar para a próxima proposta */
  const proximo = () => {
    if (!lendoTexto) {
      if (indexProposta == ata.propostas.length - 1) {
        setBotaoProximo(false);
      } else {
        setProposta(false);
        setDadosProposta(ata.propostas[indexProposta + 1]);
        setIndexProposta(indexProposta + 1);
      }
    } else {
      lerTexto(texts.detalhesAta.proximo);
    }
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

  /** Função para baixar o arquivo pdf da respectiva ata */
  const baixarPDF = () => {
    ExportPdfService.exportAta(ata.id).then((response) => {
      let blob = new Blob([response], { type: "application/pdf" });
      let url = URL.createObjectURL(blob);
      let link = document.createElement("a");
      link.href = url;
      link.download = "pdf_ata.pdf";
      link.click();
    });
  };

  /** Função para verificar se todos os campos estão preenchidos */
  const isAllFieldsFilled = () => {
    // Verifica se os pareceres das propostas foram preenchidos
    let isFilled = ata.propostas.every((proposta) => {
      return (
        proposta.parecerDG != null &&
        proposta.parecerInformacaoDG != null && // Essa variável sempre começa como null
        proposta.parecerInformacaoDG != "<p><br></p>" // Necessário para o editor de texto, pois ele insere esse código quando o campo está vazio
      );
    });

    return isFilled;
  };

  /** Cria a notificacao da demanda */
  const sendNotification = (propostaAux) => {
    let tipoNotificacao;

    switch (propostaAux.parecerDG) {
      case "APROVADO":
        tipoNotificacao = NotificacaoService.aprovadoDG;
        break;
      case "REPROVADO":
        tipoNotificacao = NotificacaoService.reprovadoDG;
        break;
      default:
        tipoNotificacao = NotificacaoService.reprovadoDG;
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

  /** Atualiza a lista de propostas passada por parâmetro */
  const updatePropostas = (
    listaPropostasToUpdate = [EntitiesObjectService.proposta()]
  ) => {
    for (let proposta of listaPropostasToUpdate) {
      PropostaService.atualizacaoDg(
        proposta.id,
        proposta.parecerDG,
        proposta.parecerInformacaoDG
      ).then((response) => {
        // Salvamento de histórico e atualização da demanda
        ExportPdfService.exportProposta(response.id).then((file) => {
          let arquivo = new Blob([file], { type: "application/pdf" });
          switch (response.parecerDG) {
            case "REPROVADO":
              PropostaService.addHistorico(
                response.id,
                "Reprovada pela DG",
                arquivo,
                CookieService.getUser().id
              ).then(() => {});
              DemandaService.atualizarStatus(
                proposta.demanda.id,
                "CANCELLED"
              ).then(() => {});
              break;
            case "APROVADO":
              PropostaService.addHistorico(
                response.id,
                "Aprovada pela DG",
                arquivo,
                CookieService.getUser().id
              ).then(() => {});
              DemandaService.atualizarStatus(proposta.demanda.id, "DONE").then(
                () => {}
              );
              break;
          }
        });

        sendNotification(JSON.parse(JSON.stringify(proposta)));
      });
    }
  };

  /** Função para formatar uma lista de objetos, retornando somente o id de cada objeto presente, com a lista sendo recebida como parâmetro */
  const retornarIdsObjetos = (listaObjetos) => {
    let listaNova = [];
    for (let objeto of listaObjetos) {
      listaNova.push({ id: objeto.id });
    }
    return listaNova;
  };

  /** Função para abrir o modal de publicação de ata */
  const verificarPublicarAta = () => {
    if (!isAllFieldsFilled()) {
      setFeedbackCamposFaltantes(true);
      return;
    }

    setModalConfirmacaoPublicacao(true);
  };

  /** Função de criar ata e enviar feedback */
  const publicarAta = () => {
    // Criação do objeto da ata publicada
    let ataPublished = { ...ata };
    ataPublished.publicadaDg = true;

    updatePropostas(ataPublished.propostas);
    ataPublished.propostas = retornarIdsObjetos(ataPublished.propostas);
    AtaService.put(ataPublished, ataPublished.id).then((response) => {});

    navigate("/", { state: { feedback: true } });
  };

  /** Função para buscar a hora */
  const trazerHoraData = (data) => {
    let dataHora = new Date(data);
    let hora = dataHora.getHours();
    let minuto = dataHora.getMinutes();

    return hora + ":" + minuto;
  };

  return (
    // Começo com o header da página
    <FundoComHeader>
      <VLibras forceOnload />
      {/* Feedback campos faltantes */}
      <Feedback
        open={feedbackCamposFaltantes}
        handleClose={() => {
          setFeedbackCamposFaltantes(false);
        }}
        status={"erro"}
        mensagem={texts.detalhesPauta.feedbacks.feedback2}
      />
      {/* Feedback edição bem sucedida */}
      <Feedback
        open={feedbackEditSuccess}
        handleClose={() => setFeedbackEditSuccess(false)}
        status={"sucesso"}
        mensagem={texts.detalhesProposta.editadoComSucesso}
      />

      {/* Modal de confirmação de publicar a ata */}
      {modalConfirmacaoPublicacao && (
        <ModalConfirmacao
          open={true}
          setOpen={setModalConfirmacaoPublicacao}
          textoModal={"publicarAta"}
          textoBotao={"sim"}
          onConfirmClick={publicarAta}
        />
      )}

      <Box className="p-2 mb-16">
        {/* caminho da página */}
        <Box className="flex w-full relative">
          <Caminho />
          <Box
            className=" absolute"
            sx={{ top: "10px", right: "20px", cursor: "pointer" }}
          >
            <SaveAltOutlinedIcon
              fontSize="large"
              className="delay-120 hover:scale-110 duration-300"
              sx={{ color: "icon.main" }}
              onClick={baixarPDF}
            />
          </Box>
        </Box>

        {/* container geral da tela */}
        <Box className="flex flex-col justify-center relative items-center mt-5">
          {/* container da folha */}
          <Box
            className="flex flex-col gap-5 border rounded relative p-10 drop-shadow-lg"
            sx={{ width: "55rem" }}
          >
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
                  lerTexto(texts.detalhesAta.ata);
                }}
              >
                {texts.detalhesAta.ata}
              </Typography>
              <Typography
                className="cursor-default mt-2"
                fontWeight={600}
                onClick={() => {
                  lerTexto(
                    texts.detalhesAta.numeroSequencial +
                      ": " +
                      ata.numeroSequencial
                  );
                }}
              >
                {texts.detalhesAta.numeroSequencial}: {ata.numeroSequencial}
              </Typography>
              <Typography
                className="cursor-default mt-2"
                fontWeight={600}
                onClick={() => {
                  lerTexto(
                    texts.detalhesAta.dataReuniao +
                      ": " +
                      DateService.getTodaysDateUSFormat(
                        ata.dataReuniao,
                        texts.linguagem
                      )
                  );
                }}
              >
                {/* {data reunião} */}
                {texts.detalhesAta.dataReuniao}:{" "}
                {DateService.getTodaysDateUSFormat(
                  ata.dataReuniao,
                  texts.linguagem
                )}
              </Typography>
              <Typography
                className="cursor-default mt-2"
                fontWeight={600}
                onClick={() => {
                  lerTexto(
                    texts.detalhesAta.horaReuniao +
                      ": " +
                      trazerHoraData(ata.dataReuniao)
                  );
                }}
              >
                {/* {Hora reunião} */}
                {texts.detalhesAta.horaReuniao}:{" "}
                {trazerHoraData(ata.dataReuniao)}
              </Typography>
              <Typography
                className="cursor-default mt-2"
                fontWeight={600}
                onClick={() => {
                  lerTexto(
                    texts.detalhesAta.analistaResponsavel +
                      ": " +
                      ata.analistaResponsavel.nome
                  );
                }}
              >
                {/* {analista responsavel} */}
                {texts.detalhesAta.analistaResponsavel}:{" "}
                {ata.analistaResponsavel.nome}
              </Typography>
              <Typography
                className="cursor-default mt-2"
                fontWeight={600}
                onClick={() => {
                  lerTexto(
                    texts.detalhesAta.comissao + ": " + ata.comissao.nomeForum
                  );
                }}
              >
                {/* {props.inicio} */}
                {texts.detalhesAta.comissao}: {ata.comissao.siglaForum} -{" "}
                {ata.comissao.nomeForum}
              </Typography>
              <Divider sx={{ marginTop: "1%" }} />
            </Box>

            {/* Verificação para mostrar o sumário ou mostrar o componente da proposta */}
            {!proposta ? (
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
                    lerTexto(texts.detalhesAta.sumario);
                  }}
                >
                  {texts.detalhesAta.sumario}
                </Typography>
                <Box
                  sx={{
                    display: "grid",
                    textAlign: "center",
                    marginTop: "2%",
                    gap: "1rem",
                    gridTemplateColumns: "repeat(auto-fit, minmax(30%, 1fr))",
                  }}
                >
                  {ata.propostas?.map((proposta, index) => {
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
                  })}
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
                      lerTexto(texts.detalhesAta.proposta);
                    }}
                  >
                    {texts.detalhesPauta.proposta} {indexProposta + 1}
                  </Typography>
                </Box>
                <DetalhesProposta
                  emAprovacao={true}
                  propostaId={dadosProposta.id}
                  texto={props.texto}
                  setFeedbackEditSuccess={setFeedbackEditSuccess}
                  parecerDG={dadosProposta.parecerDG || ""}
                  parecerInformacaoDG={dadosProposta.parecerInformacaoDG || ""}
                  setDadosProposta={setDadosProposta}
                />
              </Box>
            )}
          </Box>

          {/* Botões de navegação entre as propostas da ata */}
          {actionButtons && (
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
                <Tooltip title={texts.detalhesAta.publicarAta}>
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
                    onClick={verificarPublicarAta}
                  >
                    <DoneOutlinedIcon sx={{ color: "white " }} />
                  </ButtonBase>
                </Tooltip>
              </Box>
            </Box>
          )}
        </Box>
      </Box>
    </FundoComHeader>
  );
};

export default DetalhesAta;
