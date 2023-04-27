import React, { useState, useContext, useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import {
  Box,
  Typography,
  Button,
  Divider,
  Tooltip,
  IconButton,
} from "@mui/material";

import SaveAltOutlinedIcon from "@mui/icons-material/SaveAltOutlined";
import OtherHousesIcon from "@mui/icons-material/OtherHouses";
import DensitySmallIcon from "@mui/icons-material/DensitySmall";
import DeleteIcon from "@mui/icons-material/Delete";
import PostAddOutlinedIcon from "@mui/icons-material/PostAddOutlined";

import Feedback from "../../components/Feedback/Feedback";
import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Caminho from "../../components/Caminho/Caminho";
import DetalhesProposta from "../../components/DetalhesProposta/DetalhesProposta";

import { keyframes } from "@emotion/react";

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

// Página para mostrar os detalhes da pauta selecionada, com opção de download para pdf
const DetalhesPauta = (props) => {
  // Context para alterar a linguagem do sistema
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

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
    if (indexProposta == listaProposta.length - 1) {
      setBotaoProximo(false);
    } else {
      setProposta(false);
      setDadosProposta(pauta.propostas[indexProposta + 1]);
      setIndexProposta(indexProposta + 1);
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

  // Função para deletar uma proposta da pauta, atualizando a pauta logo em seguida
  const deletePropostaFromPauta = () => {
    const indexProposta = pauta.propostas.findIndex(
      (proposta) => proposta.id == dadosProposta.id
    );

    const propostasDeleted = pauta.propostas.splice(indexProposta, 1);
    const propostaDeleted = propostasDeleted[0];

    // Anulando os campos da proposta que não devem ter valor após ser tirada da pauta
    propostaDeleted.publicada = null;
    propostaDeleted.status = "ASSESSMENT_APROVACAO";
    propostaDeleted.parecerInformacao = null;
    propostaDeleted.parecerComissao = null;
    propostaDeleted.parecerDG = null;

    PautaService.put(pauta).then((newPauta) => {
      setFeedbackPropostaDeletada(true);
      PropostaService.putWithoutArquivos(
        propostaDeleted,
        propostaDeleted.id
      ).then((newProposta) => {});
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
      for (let proposta of ata.propostas) {
        proposta.status = "ASSESSMENT_DG";
        proposta.emAta = true;
      }

      updatePropostas(pauta.propostas);

      AtaService.post(ata).then((response) => {
        PautaService.delete(pauta.id).then((response) => {
          feedbackAta();
        });
      });
    }
  };

  const handlePautaWithNoApprovedProposals = () => {
    for (let proposta of pauta.propostas) {
      switch (proposta.parecerComissao) {
        case "REPROVADO":
          proposta.status = "CANCELLED";
          break;
        case "MAIS_INFORMACOES":
          proposta.status = "ASSESSMENT_EDICAO";
          break;
        case "BUSINESSCASE":
          proposta.status = "ASSESSMENT_EDICAO";
          break;
      }

      PropostaService.putWithoutArquivos(proposta, proposta.id);
    }

    PautaService.delete(pauta.id).then((response) => {
      console.log(
        "Pauta deletada com sucesso e propostas atualizadas! ",
        response
      );
      feedbackPropostasAtualizadas(); // Caso não tenha propostas aprovadas, atualiza as propostas
    });
  };

  // Atualiza a lista de propostas passada por parâmetro
  const updatePropostas = (listaPropostasToUpdate = []) => {
    for (let proposta of listaPropostasToUpdate) {
      PropostaService.putWithoutArquivos(proposta, proposta.id).then(
        (response) => {
          console.log("Proposta atualizada com sucesso! ", response);
        }
      );
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

  return (
    <FundoComHeader>
      <ModalCriarAta
        open={openModalCriarAta}
        setOpen={setOpenModalCriarAta}
        criarAta={criarAta}
      />
      {/* Feedback proposta deletada da pauta */}
      <Feedback
        open={feedbackPropostaDeletada}
        handleClose={() => {
          setFeedbackPropostaDeletada(false);
        }}
        status={"erro"}
        mensagem={texts.detalhesPauta.feedbacks.feedback1}
      />

      {/* Feedback campos faltantes */}
      <Feedback
        open={feedbackCamposFaltantes}
        handleClose={() => {
          setFeedbackCamposFaltantes(false);
        }}
        status={"erro"}
        mensagem={texts.detalhesPauta.feedbacks.feedback2}
      />
      <ModalConfirmacao
        open={modal}
        setOpen={setModal}
        textoModal={"tirarPropostaDePauta"}
        textoBotao={"sim"}
        onConfirmClick={deletePropostaFromPauta}
        onCancelClick={() => {}}
      />
      <Box className="p-2" sx={{ minWidth: "60rem" }}>
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
              >
                {texts.detalhesPauta.pauta}
              </Typography>
              {/* Número sequencial */}
              <Typography sx={informacoesAta}>
                {texts.detalhesPauta.numeroSequencial}: {pauta.numeroSequencial}
              </Typography>
              {/* Comissão */}
              <Typography sx={informacoesAta}>
                {texts.detalhesPauta.comissao}: {pauta.comissao.siglaForum} -{" "}
                {pauta.comissao.nomeForum}
              </Typography>
              {/* Data da reunião da comissão */}
              <Typography sx={informacoesAta}>
                {texts.detalhesPauta.reuniaoDoForum}:{" "}
                {DateService.getFullDateUSFormat(
                  DateService.getDateByMySQLFormat(pauta?.dataReuniao)
                )}
              </Typography>
              {/* Data da reunião da DG */}
              <Typography sx={informacoesAta}>
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
                >
                  {texts.detalhesPauta.sumario}
                </Typography>
                <Box className="flex flex-col items-center justify-center">
                  {isSummaryVisible ? (
                    pauta.propostas?.map((proposta, index) => {
                      return (
                        <Box
                          className="w-full border-solid border border-l-4 px-4 drop-shadow-sm rounded flex items-center mt-4"
                          sx={{
                            height: "2.5rem",
                            borderLeftColor: "primary.main",
                            backgroundColor: "background.default",
                            fontWeight: "300",
                            cursor: "pointer",
                            '&:hover': {
                              backgroundColor: 'component.main',
                            }
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
                    <Typography fontSize={FontConfig.medium}>
                      {texts.detalhesPauta.nenhumaPropostaAdicionada}
                    </Typography>
                  )}
                </Box>
                {/* <Box
                  sx={{
                    display: "grid",
                    textAlign: "center",
                    marginTop: "2%",
                    gap: "1rem",
                    gridTemplateColumns: "repeat(auto-fit, minmax(30%, 1fr))",
                  }}
                > */}
                {/* Lista utilizada para mostrar os títulos no sumário */}
                {/* {isSummaryVisible ? (
                    pauta.propostas?.map((proposta, index) => {
                      return (
                        <Typography
                          fontSize={FontConfig.big}
                          sx={tituloProposta}
                          key={index}
                          onClick={() => onClickProposta(index)}
                        >
                          {index + 1} - {proposta.titulo}
                        </Typography>
                      );
                    })
                  ) : (
                    <Typography fontSize={FontConfig.medium}>
                      {texts.detalhesPauta.nenhumaPropostaAdicionada}
                    </Typography>
                  )}
                </Box> */}
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
                  proposta={dadosProposta}
                  setProposta={setDadosProposta}
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
                    onClick={() => voltar()}
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
