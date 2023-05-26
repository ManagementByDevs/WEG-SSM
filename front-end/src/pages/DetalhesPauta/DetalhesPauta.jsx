import React, { useState, useContext, useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom";

import VLibras from "@djpfs/react-vlibras";

import {
  Box,
  Typography,
  Button,
  Divider,
  Tooltip,
  IconButton,
} from "@mui/material";

import { keyframes } from "@emotion/react";

import SaveAltOutlinedIcon from "@mui/icons-material/SaveAltOutlined";
import OtherHousesIcon from "@mui/icons-material/OtherHouses";
import DensitySmallIcon from "@mui/icons-material/DensitySmall";
import DeleteIcon from "@mui/icons-material/Delete";
import PostAddOutlinedIcon from "@mui/icons-material/PostAddOutlined";

import Feedback from "../../components/Feedback/Feedback";
import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Caminho from "../../components/Caminho/Caminho";
import DetalhesProposta from "../../components/DetalhesProposta/DetalhesProposta";

import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";
import DateService from "../../service/dateService";
import PautaService from "../../service/pautaService";
import ModalConfirmacao from "../../components/ModalConfirmacao/ModalConfirmacao";
import PropostaService from "../../service/propostaService";
import ExportPdfService from "../../service/exportPdfService";
import AtaService from "../../service/ataService";
import EntitiesObjectService from "../../service/entitiesObjectService";
import ModalCriarAta from "../../components/ModalCriarAta/ModalCriarAta";

import CookieService from "../../service/cookieService";
import DemandaService from "../../service/demandaService";

// Página para mostrar os detalhes da pauta selecionada, com opção de download para pdf
const DetalhesPauta = (props) => {
  // Context para alterar a linguagem do sistema
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  // Navigate utilizado para navegar para uma determianda página
  const navigate = useNavigate();

  // Variável do react-router-dom que guarda as informações da rota atual
  const location = useLocation();

  // Variáveis de estilo para o componente
  const informacoesAta = {
    fontWeight: "600",
    cursor: "default",
    marginTop: "1%",
  };

  // UseState para guardar a pauta atual
  const [pauta, setPauta] = useState(EntitiesObjectService.pauta());

  // Lista provisória de propostas para preencher a tela
  const [listaProposta, setListaProposta] = useState([
    EntitiesObjectService.proposta(),
  ]);

  // Variável de verificação utilizada para mostrar o sumário ou uma proposta
  const [proposta, setProposta] = useState(false);

  // Variável utilizada para passar para a próxima proposta
  const [botaoProximo, setBotaoProximo] = useState(true);

  // Variável utilizada para mostrar os dados de uma proposta
  const [dadosProposta, setDadosProposta] = useState(listaProposta[0]);

  // Index de uma proposta, utilizado para mostrar os dados de uma porposta específica
  const [indexProposta, setIndexProposta] = useState(-1);

  // Variável utilizada para minimizar os botões da página
  const [minimizar, setMinimizar] = useState(true);

  // Estado para mostrar o modal de confirmação
  const [modal, setModal] = useState(false);

  // Estado para mostrar o sumário ou não, usado também para atualizar a página com as novas propostas
  const [isSummaryVisible, setIsSummaryVisible] = useState(true);

  // Função para selecionar uma proposta do sumário
  const onClickProposta = (index) => {
    setIndexProposta(index);
    setDadosProposta(pauta.propostas[index]);
    setProposta(true);
  };

  // Função para passar para a próxima proposta
  const proximo = () => {
    if (!props.lendo) {
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

  useEffect(() => {
    if (indexProposta != -1) {
      setProposta(true);
    }
  }, [indexProposta]);

  // Função para voltar para uma proposta
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

  // Função para voltar ao sumário da pauta
  const voltarSumario = () => {
    setBotaoProximo(true);
    setIndexProposta(-1);
    setProposta(false);
  };

  const [fecharMenu, setFecharMenu] = useState(true);

  // Função para fechar os botões da página
  const funcaoFecharMenu = () => {
    setFecharMenu(!fecharMenu);
  };

  // Feedback de ata criada com sucesso
  const feedbackAta = () => {
    navigate("/", { state: { feedback: "ata-criada" } });
  };

  // Feedback de propostas atualizadas caso não tenha proposta aprovada
  const feedbackPropostasAtualizadas = () => {
    navigate("/", { state: { feedback: "propostas-atualizadas" } });
  };

  // Fazer uma animação para os notões de navegação
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

  const [girarIcon, setGirarIcon] = useState(false);

  const [aparecerSumir, setAparecerSumir] = useState(false);

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

  const [display, setDisplay] = useState("hidden");

  // Função acionada quando é clicado no botão de delete de alguma proposta
  const onDeletePropostaClick = () => {
    setModal(true);
  };

  // Feedback para quando o usuário deletar uma proposta da pauta
  const [feedbackPropostaDeletada, setFeedbackPropostaDeletada] =
    useState(false);

  // Feedback para quando o usuário não preencher todos os campos obrigatórios
  const [feedbackCamposFaltantes, setFeedbackCamposFaltantes] = useState(false);

  // Feedback para quando da erro de incompatibilidade com o navegador
  const [
    feedbackErroNavegadorIncompativel,
    setFeedbackErroNavegadorIncompativel,
  ] = useState(false);

  // Feedback para quando da erro no reconhecimento de voz
  const [feedbackErroReconhecimentoVoz, setFeedbackErroReconhecimentoVoz] =
    useState(false);

  // Função para deletar uma proposta da pauta, atualizando a pauta logo em seguida
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

  useEffect(() => {
    setPauta(location.state.pauta);
    setListaProposta(location.state.pauta.propostas);
  }, []);

  // Função para baixar o arquivo pdf da respectiva pauta
  const baixarPDF = () => {
    ExportPdfService.exportPauta(pauta.id).then((response) => {
      let blob = new Blob([response], { type: "application/pdf" });
      let url = URL.createObjectURL(blob);
      let link = document.createElement("a");
      link.href = url;
      link.download = "pdf_pauta.pdf";
      link.click();
    });
  };

  // Verifica se todos os campos necessários para criação de uma ata foram preenchidos
  const isAllFieldsFilled = () => {
    // Verifica se os pareceres das propostas foram preenchidos
    let isFilled = pauta.propostas.every((proposta) => {
      return (
        proposta.parecerComissao != null &&
        proposta.parecerInformacao != null && // Essa variável sempre começa como null
        proposta.parecerInformacao != "<p><br></p>" // Necessário para o editor de texto, pois ele insere esse código quando o campo está vazio
      );
    });

    return isFilled;
  };

  const abrirModalCriarAta = () => {
    if (!isAllFieldsFilled()) {
      setFeedbackCamposFaltantes(true);
      return;
    }
    setOpenModalCriarAta(true);
  };

  /** Função para formatar uma lista de objetos, retornando somente o id de cada objeto presente, com a lista sendo recebida como parâmetro */
  const retornarIdsObjetos = (listaObjetos) => {
    let listaNova = [];
    for (let objeto of listaObjetos) {
      listaNova.push({ id: objeto.id });
    }

    return listaNova;
  };

  // Função que cria uma ata
  const criarAta = (numeroSequencial, dataReuniao) => {
    // Criação do obj ata
    let ata = {
      ...pauta,
      numeroSequencial: numeroSequencial,
      dataReuniao: dataReuniao,
    };

    // Se o parecere da comissão não for aprovado, a proposta não é adicionada na ata
    ata.propostas = ata.propostas.filter((proposta) => {
      return proposta.parecerComissao == "APROVADO";
    });

    // Caso não haja propostas aprovadas, não cria a ata
    if (ata.propostas.length == 0) {
      handlePautaWithNoApprovedProposals();
      return;
    }

    // Cria a ata caso tenha propostas aprovadas
    if (ata.propostas.length > 0) {
      ata.propostas = retornarIdsObjetos(ata.propostas);

      AtaService.post(ata).then((response) => {
        updatePropostas(pauta.propostas, response.numeroSequencial);
        PautaService.delete(pauta.id).then((response) => {
          feedbackAta();
        });
      });
    }
  };

  const handlePautaWithNoApprovedProposals = () => {
    for (let proposta of pauta.propostas) {
      PropostaService.atualizacaoAta(
        proposta.id,
        proposta.parecerComissao,
        proposta.parecerInformacao
      ).then((response) => {
        //Salvamento de histórico e atualização da demanda
        ExportPdfService.exportProposta(response.id).then((file) => {
          let arquivo = new Blob([file], { type: "application/pdf" });

          switch (response.parecerComissao) {
            case "REPROVADO":
              PropostaService.addHistorico(
                response.id,
                "Proposta Reprovada",
                arquivo,
                CookieService.getUser().id
              ).then(() => {});
              DemandaService.atualizarStatus(
                response.demanda.id,
                "CANCELLED"
              ).then(() => {});
              break;
            case "MAIS_INFORMACOES":
              PropostaService.addHistorico(
                response.id,
                "Enviada para Edição",
                arquivo,
                CookieService.getUser().id
              ).then(() => {});
              break;
            case "BUSINESS_CASE":
              PropostaService.addHistorico(
                response.id,
                "Enviada para Business Case",
                arquivo,
                CookieService.getUser().id
              ).then(() => {});
              break;
          }
        });
      });
    }

    PautaService.delete(pauta.id).then((response) => {
      feedbackPropostasAtualizadas(); // Caso não tenha propostas aprovadas, atualiza as propostas
    });
  };

  /** Função para salvar o histórico da proposta após criação da ata, seguindo um texto diferente dependendo do parecer da comissão */
  const salvarHistoricoAprovacao = (proposta, idAta) => {
    //Salvamento do histórico e atualização da demanda
    ExportPdfService.exportProposta(proposta.id).then((file) => {
      let arquivo = new Blob([file], { type: "application/pdf" });

      switch (proposta.parecerComissao) {
        case "REPROVADO":
          PropostaService.addHistorico(
            proposta.id,
            "Proposta Reprovada",
            arquivo,
            CookieService.getUser().id
          ).then(() => {});
          DemandaService.atualizarStatus(proposta.demanda.id, "CANCELLED").then(
            () => {}
          );
          break;
        case "MAIS_INFORMACOES":
          PropostaService.addHistorico(
            proposta.id,
            "Enviada para Edição",
            arquivo,
            CookieService.getUser().id
          ).then(() => {});
          break;
        case "BUSINESS_CASE":
          PropostaService.addHistorico(
            proposta.id,
            "Entrada em Business Case",
            arquivo,
            CookieService.getUser().id
          ).then(() => {});
          break;
        case "APROVADO":
          PropostaService.addHistorico(
            proposta.id,
            "Adicionada na Ata #" + idAta,
            arquivo,
            CookieService.getUser().id
          ).then(() => {});
          break;
      }
    });
  };

  // Atualiza a lista de propostas passada por parâmetro
  const updatePropostas = (listaPropostasToUpdate = [], idAta) => {
    for (let proposta of listaPropostasToUpdate) {
      PropostaService.atualizacaoAta(
        proposta.id,
        proposta.parecerComissao,
        proposta.parecerInformacao
      ).then((response) => {
        salvarHistoricoAprovacao(response, idAta);
      });
    }
  };

  // Função que atualiza as propostas da pauta sempre que uma proposta é atualizada
  useEffect(() => {
    if (indexProposta > -1 && dadosProposta != null) {
      let aux = [...listaProposta];
      aux[indexProposta] = { ...dadosProposta };
      setListaProposta(aux);
      setPauta({ ...pauta, propostas: aux });
    }
  }, [dadosProposta]);

  useEffect(() => {
    setIsSummaryVisible(true);
  }, [dadosProposta]);

  // useState utilizado para abrir e fechar o modal de adicionar a pauta
  const [openModalCriarAta, setOpenModalCriarAta] = useState(false);

  // Função que irá setar o texto que será "lido" pela a API
  const lerTexto = (texto) => {
    if (props.lendo) {
      props.setTexto(texto);
    }
  };

  // Função que irá "ouvir" o texto que será "lido" pela a API
  useEffect(() => {
    let countFala = 0;
    const synthesis = window.speechSynthesis;
    const utterance = new SpeechSynthesisUtterance(props.texto);
    if (props.lendo && props.texto != "" && countFala == 0) {
      if ("speechSynthesis" in window) {
        synthesis.speak(utterance);
        countFala++;
      }
      props.setTexto("");
    } else if (!props.lendo || countFala > 0) {
      if ("speechSynthesis" in window) {
        synthesis.cancel();
      }
    }
  }, [props.texto, props.lendo]);

  return (
    <FundoComHeader
      lendo={props.lendo}
      texto={props.texto}
      setTexto={props.setTexto}
    >
      <VLibras forceOnload />
      <ModalCriarAta
        open={openModalCriarAta}
        setOpen={setOpenModalCriarAta}
        criarAta={criarAta}
        setFeedbackErroNavegadorIncompativel={
          setFeedbackErroNavegadorIncompativel
        }
        setFeedbackErroReconhecimentoVoz={setFeedbackErroReconhecimentoVoz}
        setFeedbackCamposFaltantes={setFeedbackCamposFaltantes}
        lendo={props.lendo}
        texto={props.texto}
        setTexto={props.setTexto}
      />
      {/* Feedback Erro reconhecimento de voz */}
      <Feedback
        open={feedbackErroReconhecimentoVoz}
        handleClose={() => {
          setFeedbackErroReconhecimentoVoz(false);
        }}
        status={"erro"}
        mensagem={texts.homeGerencia.feedback.feedback12}
        lendo={props.lendo}
        texto={props.texto}
        setTexto={props.setTexto}
      />
      {/* Feedback Não navegador incompativel */}
      <Feedback
        open={feedbackErroNavegadorIncompativel}
        handleClose={() => {
          setFeedbackErroNavegadorIncompativel(false);
        }}
        status={"erro"}
        mensagem={texts.homeGerencia.feedback.feedback13}
        lendo={props.lendo}
        texto={props.texto}
        setTexto={props.setTexto}
      />
      {/* Feedback campos faltantes */}
      <Feedback
        open={feedbackCamposFaltantes}
        handleClose={() => {
          setFeedbackCamposFaltantes(false);
        }}
        status={"erro"}
        mensagem={texts.modalCriarAta.feedback}
        lendo={props.lendo}
        texto={props.texto}
        setTexto={props.setTexto}
      />
      {/* Feedback proposta deletada da pauta */}
      <Feedback
        open={feedbackPropostaDeletada}
        handleClose={() => {
          setFeedbackPropostaDeletada(false);
        }}
        status={"sucesso"}
        mensagem={texts.detalhesPauta.feedbacks.feedback1}
        lendo={props.lendo}
        texto={props.texto}
        setTexto={props.setTexto}
      />
      <ModalConfirmacao
        open={modal}
        setOpen={setModal}
        textoModal={"tirarPropostaDePauta"}
        textoBotao={"sim"}
        onConfirmClick={deletePropostaFromPauta}
        onCancelClick={() => {}}
        lendo={props.lendo}
        texto={props.texto}
        setTexto={props.setTexto}
      />
      <Box className="p-2" sx={{ minWidth: "60rem" }}>
        <Box className="flex w-full relative">
          <Caminho
            lendo={props.lendo}
            texto={props.texto}
            setTexto={props.setTexto}
          />
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
                sx={informacoesAta}
                onClick={() => {
                  lerTexto(texts.detalhesProposta.numeroSequencial);
                }}
              >
                {texts.detalhesPauta.numeroSequencial}: {pauta.numeroSequencial}
              </Typography>
              {/* Comissão */}
              <Typography
                sx={informacoesAta}
                onClick={() => {
                  lerTexto(texts.detalhesPauta.comissao);
                }}
              >
                {texts.detalhesPauta.comissao}: {pauta.comissao.siglaForum} -{" "}
                {pauta.comissao.nomeForum}
              </Typography>
              {/* Data da reunião da comissão */}
              <Typography
                sx={informacoesAta}
                onClick={() => {
                  lerTexto(texts.detalhesPauta.reuniaoDoForum);
                }}
              >
                {texts.detalhesPauta.reuniaoDoForum}:{" "}
                {DateService.getFullDateUSFormat(
                  DateService.getDateByMySQLFormat(pauta?.dataReuniao)
                )}
              </Typography>
              {/* Data da reunião da DG */}
              <Typography
                sx={informacoesAta}
                onClick={() => {
                  lerTexto(texts.detalhesPauta.analistaResponsavel);
                }}
              >
                {texts.detalhesPauta.analistaResponsavel}:{" "}
                {pauta.analistaResponsavel.nome}
              </Typography>

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
                          onClick={() => onClickProposta(index)}
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
            <Box className="flex justify-end">
              <Box
                className={`w-full ${display} items-center mr-1`}
                sx={{ animation: `${aparecerSumir} 1.2s forwards` }}
              >
                <Box className="flex justify-around w-full">
                  <Button
                    sx={{
                      backgroundColor: "primary.main",
                      color: "text.white",
                      fontSize: FontConfig.default,
                      maxHeight: "2.5rem",
                    }}
                    variant="contained"
                    onClick={() => {
                      if (props.lendo) {
                        voltar();
                      } else {
                        lerTexto(texts.detalhesPauta.voltar);
                      }
                    }}
                  >
                    {texts.detalhesPauta.voltar}
                  </Button>
                  <Button
                    sx={{
                      backgroundColor: "primary.main",
                      color: "text.white",
                      fontSize: FontConfig.default,
                      maxHeight: "2.5rem",
                    }}
                    variant="contained"
                    onClick={voltarSumario}
                  >
                    <OtherHousesIcon />
                  </Button>
                  <Button
                    sx={{
                      backgroundColor: "primary.main",
                      color: "text.white",
                      fontSize: FontConfig.default,
                      maxHeight: "2.5rem",
                    }}
                    variant="contained"
                    onClick={proximo}
                    fdsfds
                  >
                    <Typography>{texts.detalhesPauta.proximo}</Typography>
                  </Button>
                </Box>
              </Box>
              <Tooltip title={texts.detalhesPauta.navegacao}>
                <Box
                  className="flex justify-center items-center w-12 h-12 rounded-full cursor-pointer delay-120 hover:scale-110 duration-300"
                  sx={{
                    backgroundColor: "primary.main",
                    color: "text.white",
                    fontSize: FontConfig.default,
                  }}
                  onClick={() => {
                    animarBotoes();
                    setMinimizar(!minimizar);
                  }}
                >
                  <DensitySmallIcon
                    sx={{
                      rotate: "90deg",
                      animation: `${girarIcon} 1.2s forwards`,
                    }}
                  ></DensitySmallIcon>
                </Box>
              </Tooltip>
            </Box>
            <Tooltip title={texts.detalhesPauta.criarAta}>
              <Box
                // onClick={feedbackAta}
                onClick={abrirModalCriarAta}
                className="flex justify-center items-center w-12 h-12 rounded-full cursor-pointer delay-120 hover:scale-110 duration-300"
                sx={{
                  backgroundColor: "primary.main",
                  fontSize: FontConfig.default,
                  marginLeft: "1rem",
                  color: "#FFFF",
                }}
              >
                <PostAddOutlinedIcon fontSize="large" />
              </Box>
            </Tooltip>
          </Box>
        </Box>
      </Box>
    </FundoComHeader>
  );
};

export default DetalhesPauta;
