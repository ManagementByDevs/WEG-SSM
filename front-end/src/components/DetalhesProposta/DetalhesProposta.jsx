import React, { useContext, useState, useRef, useEffect } from "react";

import {
  Box,
  Divider,
  IconButton,
  Menu,
  MenuItem,
  Paper,
  Table,
  TableBody,
  TableHead,
  TableRow,
  TextField,
  Tooltip,
  Typography,
} from "@mui/material";

import LogoWEG from "../../assets/logo-weg.png";

import DownloadIcon from "@mui/icons-material/Download";
import EditIcon from "@mui/icons-material/Edit";
import EditOffIcon from "@mui/icons-material/EditOff";

import ModalConfirmacao from "../ModalConfirmacao/ModalConfirmacao";
import CaixaTextoQuill from "../CaixaTextoQuill/CaixaTextoQuill";
import DetalhesPropostaEditMode from "../DetalhesPropostaEditMode/DetalhesPropostaEditMode";

import FontContext from "../../service/FontContext";
import DateService from "../../service/dateService";
import TextLanguageContext from "../../service/TextLanguageContext";
import EntitiesObjectService from "../../service/entitiesObjectService";
import PropostaService from "../../service/propostaService";
import UsuarioService from "../../service/usuarioService";

import ClipLoader from "react-spinners/ClipLoader";
import Feedback from "../Feedback/Feedback";

// Exemplo de proposta a ser seguido
const propostaExample = EntitiesObjectService.proposta();

// Componente  para mostrar os detalhes de uma proposta e suas respectivas funções
const DetalhesProposta = ({
  propostaId = 0,
  emAprovacao = false,
  setDadosProposta = () => {},
  parecerComissao = "",
  parecerInformacao = "",
  parecerDG = "",
  parecerInformacaoDG = "",
  lendo,
  setTexto,
  texto,
}) => {
  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  // Estado da proposta
  const [proposta, setProposta] = useState(propostaExample);

  // Estado para mostrar o carregamento enquanto é feito a requisição para pegar os dados da prospota
  const [isLoading, setIsLoading] = useState(true);

  // Estado para saber qual estado da proposta mostrar, a estática ou editável
  const [isEditing, setIsEditing] = useState(false);

  // Estado para feedback de edição feita com sucesso
  const [feedbackEditSuccess, setFeedbackEditSuccess] = useState(false);

  // Referência para o texto do problema
  const problemaText = useRef(null);

  // Referência para o texto da proposta
  const propostaText = useRef(null);

  /** Referência para o texto de escopo */
  const textoEscopo = useRef(null);

  /** Função para baixar um anexo */
  const downloadAnexo = (anexo = EntitiesObjectService.anexo()) => {
    const file = anexo;
    let blob = new Blob([base64ToArrayBuffer(file.dados)]);
    let fileName = `${file.nome}`;

    if (navigator.msSaveBlob) {
      navigator.msSaveBlob(blob, fileName);
    } else {
      const link = document.createElement("a");
      if (link.download !== undefined) {
        const url = URL.createObjectURL(blob);
        link.setAttribute("href", url);
        link.setAttribute("download", fileName);
        link.style.visibility = "hidden";
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
        URL.revokeObjectURL(url);
      }
    }
  };

  /** Retorna o texto do status da proposta */
  const getStatusFormatted = () => {
    switch (proposta.status) {
      case "ASSESSMENT_APROVACAO": //#F7DC6F
        return texts.detalhesProposta.status.assessmentAprovacao;
      case "ASSESSMENT_EDICAO": //#F7DC6F
        return texts.detalhesProposta.status.assessmentAprovacao;
      case "ASSESSMENT_COMISSAO": //#F7DC6F
        return texts.detalhesProposta.status.assessmentAprovacao;
      case "ASSESSMENT_DG": //#F7DC6F
        return texts.detalhesProposta.status.assessmentAprovacao;
      case "BUSINESS_CASE": // #C8CA5F
        return texts.detalhesProposta.statusRejeitada;
      case "CANCELLED": //#DA0303
        return texts.detalhesProposta.statusCancelada;
      case "DONE": //#62A265
        return texts.detalhesProposta.statusEmAndamento;
      default:
        return "";
    }
  };

  /** Retorna a cor hexadecimal do status da proposta */
  const getCorStatus = (status) => {
    switch (status) {
      case "ASSESSMENT_APROVACAO":
        return "#8862A2";
      case "ASSESSMENT_EDICAO":
        return "#8862A2";
      case "ASSESSMENT_COMISSAO":
        return "#8862A2";
      case "ASSESSMENT_DG":
        return "#8862A2";
      case "BUSINESS_CASE":
        return "#C8CA5F";
      case "CANCELLED":
        return "#DA0303";
      case "DONE":
        return "#62A265";
      default:
        return "";
    }
  };

  /** Função para transformar uma string em base64 para um ArrayBuffer */
  const base64ToArrayBuffer = (base64) => {
    const binaryString = window.atob(base64);
    const bytes = new Uint8Array(binaryString.length);
    return bytes.map((byte, i) => binaryString.charCodeAt(i));
  };

  /** Função para transformar um base64 em uma string */
  const convertByteArrayToString = (byteArray = []) => {
    return window.atob(byteArray).toString("utf-8");
  };

  /** Função passada para o componente detalhes proposta edit mode */
  const setPropostaNewData = (proposta = EntitiesObjectService.proposta()) => {
    proposta.proposta = convertByteArrayToString(proposta.proposta);
    proposta.problema = convertByteArrayToString(proposta.problema);
    proposta.escopo = convertByteArrayToString(proposta.escopo);

    if (proposta.beneficios.length > 0)
      for (let beneficio of proposta.beneficios) {
        beneficio.memoriaCalculo = convertByteArrayToString(
          beneficio.memoriaCalculo
        );
      }

    setFeedbackEditSuccess(true);
    setProposta(JSON.parse(JSON.stringify(proposta)));
  };

  /** Função acionada quando o usúario clica no ícone de editar */
  const handleOnEditClick = () => {
    setIsEditing(!isEditing);
  };

  /** Formata o objeto proposta pego do banco de dados */
  const formatData = (proposal = EntitiesObjectService.proposta()) => {
    proposal.problema = convertByteArrayToString(proposal.problema);
    proposal.proposta = convertByteArrayToString(proposal.proposta);
    proposal.escopo = convertByteArrayToString(proposal.escopo);

    for (let beneficio of proposal.beneficios) {
      beneficio.memoriaCalculo = convertByteArrayToString(
        beneficio.memoriaCalculo
      );
    }
    console.log("Depois: ", JSON.parse(JSON.stringify(proposal)));
  };

  const editProposta = (proposal) => {
    setFeedbackEditSuccess(true);
    setProposta(proposal);
  };

  useEffect(() => {
    if (problemaText.current) {
      problemaText.current.innerHTML = proposta.problema;
    }

    if (propostaText.current) {
      propostaText.current.innerHTML = proposta.proposta;
    }

    if (textoEscopo.current) {
      textoEscopo.current.innerHTML = proposta.escopo;
    }
  }, [proposta, isEditing]);

  useEffect(() => {
    // Buscando os dados da proposta usando o propostaId
    PropostaService.getById(propostaId).then((proposal) => {
      console.log("Antes: ", JSON.parse(JSON.stringify(proposal)));
      // Arrumando alguns textos
      formatData(proposal);
      setProposta(proposal);
      setIsLoading(false);
    });
  }, []);

  // Função que irá setar o texto que será "lido" pela a API
  const lerTexto = (escrita) => {
    if (lendo) {
      setTexto(escrita);
    }
  };

  // Função que irá "ouvir" o texto que será "lido" pela a API
  useEffect(() => {
    const synthesis = window.speechSynthesis;
    const utterance = new SpeechSynthesisUtterance(texto);

    const finalizarLeitura = () => {
      if ("speechSynthesis" in window) {
        synthesis.cancel();
      }
    };

    if (lendo && texto !== "") {
      if ("speechSynthesis" in window) {
        synthesis.speak(utterance);
      }
    } else if (!lendo) {
      finalizarLeitura();
    }

    return () => {
      finalizarLeitura();
    };
  }, [texto, lendo]);

  if (Object.values(proposta).some((value) => value === undefined))
    return <></>;

  if (isLoading)
    return (
      <Box className="flex justify-center">
        <Box
          className="flex justify-center border rounded px-10 py-4 border-t-6 relative"
          sx={{ width: "55rem", borderTopColor: "primary.main" }}
        >
          <ClipLoader className="mt-2" color="#00579D" size={110} />
        </Box>
      </Box>
    );

  if (isEditing) {
    return (
      <Box className="flex justify-center">
        <Box
          className="border rounded px-10 py-4 border-t-6 relative"
          sx={{ width: "55rem", borderTopColor: "primary.main" }}
        >
          <StatusProposta
            proposta={proposta}
            setProposta={editProposta}
            getCorStatus={getCorStatus}
            getStatusFormatted={getStatusFormatted}
            lendo={lendo}
            texto={texto}
            setTexto={setTexto}
          />
          <DetalhesPropostaEditMode
            propostaData={proposta}
            setPropostaData={setPropostaNewData}
            setIsEditing={setIsEditing}
            emAprovacao={emAprovacao}
            lendo={lendo}
            texto={texto}
            setTexto={setTexto}
          />
        </Box>
      </Box>
    );
  }

  return (
    <Box className="flex justify-center">
      <Feedback
        open={feedbackEditSuccess}
        handleClose={() => setFeedbackEditSuccess(false)}
        status={"sucesso"}
        mensagem={texts.detalhesProposta.editadoComSucesso}
        lendo={lendo}
        texto={texto}
        setTexto={setTexto}
      />
      <Box
        className="border rounded px-10 py-4 border-t-6 relative"
        sx={{ width: "55rem", borderTopColor: "primary.main" }}
      >
        <StatusProposta
          proposta={proposta}
          setProposta={editProposta}
          getCorStatus={getCorStatus}
          getStatusFormatted={getStatusFormatted}
          lendo={lendo}
          texto={texto}
          setTexto={setTexto}
        />
        {/* Box header */}
        <Box className="w-full flex justify-between">
          <Box className="flex gap-4">
            <Typography
              color="primary"
              fontWeight="bold"
              fontSize={FontConfig.big}
              onClick={() => lerTexto(texts.detalhesProposta.ppm)}
            >
              {texts.detalhesProposta.ppm} {proposta.codigoPPM}{" "}
            </Typography>
            <Typography
              color="primary"
              fontWeight="bold"
              fontSize={FontConfig.big}
              onClick={() => lerTexto(texts.detalhesProposta.data)}
            >
              {texts.detalhesProposta.data}{" "}
              {DateService.getTodaysDateUSFormat(
                DateService.getDateByMySQLFormat(proposta.data)
              )}
            </Typography>
            <Typography
              color="primary"
              fontWeight="bold"
              fontSize={FontConfig.big}
              onClick={() => {
                if (proposta.publicada != null && proposta.publicada) {
                  lerTexto(texts.detalhesProposta.publicada.toUpperCase());
                } else {
                  lerTexto(texts.detalhesProposta.naoPublicada.toUpperCase());
                }
              }}
            >
              {proposta.publicada != null
                ? proposta.publicada
                  ? texts.detalhesProposta.publicada.toUpperCase()
                  : texts.detalhesProposta.naoPublicada.toUpperCase()
                : ""}
            </Typography>
          </Box>
          <Box className="flex w-16">
            <img src={LogoWEG} className="w-16 h-11" alt="Logo WEG" />
          </Box>
        </Box>

        {/* Box Conteudo */}
        <Box className="w-full">
          {/* Titulo */}
          <Box>
            <Typography
              color={"primary.main"}
              fontSize={FontConfig.smallTitle}
              onClick={() => lerTexto(proposta.titulo)}
            >
              {proposta.titulo}
            </Typography>
          </Box>
          <Divider />

          {/* Box Informações gerais */}
          <Box className="relative">
            {proposta.status != "CANCELLED" &&
              proposta.status != "DONE" &&
              proposta.presenteEm != "Pauta" && (
                <Tooltip title={texts.detalhesProposta.editar}>
                  <Box className="absolute -right-8 -top-2">
                    <IconButton
                      sx={{ color: "primary.main" }}
                      onClick={handleOnEditClick}
                    >
                      {!isEditing ? <EditIcon /> : <EditOffIcon />}
                    </IconButton>
                  </Box>
                </Tooltip>
              )}
            {/* Solicitante */}
            <Box className="flex mt-4">
              <Typography
                fontSize={FontConfig.medium}
                fontWeight="bold"
                onClick={() => lerTexto(texts.detalhesProposta.solicitante)}
              >
                {texts.detalhesProposta.solicitante}:&nbsp;
              </Typography>
              <Typography
                fontSize={FontConfig.medium}
                onClick={() =>
                  lerTexto(
                    proposta.solicitante?.nome +
                      " - " +
                      proposta.solicitante?.departamento?.nome
                  )
                }
              >
                {proposta.solicitante?.nome} -{" "}
                {proposta.solicitante?.departamento?.nome}
              </Typography>
            </Box>

            {/* Bu solicitante */}
            <Box className="flex mt-4">
              <Typography
                fontSize={FontConfig.medium}
                fontWeight="bold"
                onClick={() => lerTexto(texts.detalhesProposta.buSolicitante)}
              >
                {texts.detalhesProposta.buSolicitante}:&nbsp;
              </Typography>
              <Typography
                fontSize={FontConfig.medium}
                onClick={() =>
                  lerTexto(
                    proposta.buSolicitante?.siglaBu +
                      " - " +
                      proposta.buSolicitante?.nomeBu
                  )
                }
              >
                {proposta.buSolicitante?.siglaBu} -{" "}
                {proposta.buSolicitante?.nomeBu}
              </Typography>
            </Box>

            {/* Gerente */}
            <Box className="flex mt-4">
              <Typography
                fontSize={FontConfig.medium}
                fontWeight="bold"
                onClick={() => lerTexto(texts.detalhesProposta.gerente)}
              >
                {texts.detalhesProposta.gerente}:&nbsp;
              </Typography>
              <Typography
                fontSize={FontConfig.medium}
                onClick={() =>
                  lerTexto(
                    proposta.gerente?.nome +
                      " - " +
                      proposta.gerente?.departamento?.nome
                  )
                }
              >
                {proposta.gerente?.nome} -{" "}
                {proposta.gerente?.departamento?.nome}
              </Typography>
            </Box>

            {/* Fórum e Tamanho*/}
            <Box className="flex w-full justify-between mt-4">
              {/* Fórum */}
              <Box className="flex flex-row w-3/4">
                <Typography
                  fontSize={FontConfig.medium}
                  fontWeight="bold"
                  onClick={() => lerTexto(texts.detalhesProposta.forum)}
                >
                  {texts.detalhesProposta.forum}:&nbsp;
                </Typography>

                <Typography
                  fontSize={FontConfig.medium}
                  onClick={() =>
                    lerTexto(
                      proposta.forum?.siglaForum +
                        " - " +
                        proposta.forum?.nomeForum
                    )
                  }
                >
                  {proposta.forum?.siglaForum} - {proposta.forum?.nomeForum}
                </Typography>
              </Box>
              {/* Tamanho */}
              <Box className="flex flex-row">
                <Typography
                  fontSize={FontConfig.medium}
                  fontWeight="bold"
                  onClick={() => lerTexto(texts.detalhesProposta.tamanho)}
                >
                  {texts.detalhesProposta.tamanho}:&nbsp;
                </Typography>

                <Typography
                  fontSize={FontConfig.medium}
                  onClick={() => lerTexto(proposta.tamanho)}
                >
                  {proposta.tamanho}
                </Typography>
              </Box>
            </Box>

            {/* Secao TI */}
            <Box className="flex mt-4">
              <Typography
                fontSize={FontConfig.medium}
                fontWeight="bold"
                onClick={() => lerTexto(texts.detalhesProposta.secaoTi)}
              >
                {texts.detalhesProposta.secaoTi}:&nbsp;
              </Typography>
              <Typography
                fontSize={FontConfig.medium}
                onClick={() =>
                  lerTexto(
                    proposta.secaoTI.siglaSecao +
                      " - " +
                      proposta.secaoTI.nomeSecao
                  )
                }
              >
                {proposta.secaoTI.siglaSecao} - {proposta.secaoTI.nomeSecao}
              </Typography>
            </Box>

            {/* Proposta / Objetivo */}
            <Box className="mt-4">
              <Typography
                fontSize={FontConfig.medium}
                fontWeight="bold"
                onClick={() => lerTexto(texts.detalhesProposta.proposta)}
              >
                {texts.detalhesProposta.proposta}:&nbsp;
              </Typography>
              <Box className="mx-4">
                <Typography
                  fontSize={FontConfig.medium}
                  ref={propostaText}
                  onClick={() => {
                    lerTexto(propostaText);
                  }}
                />
              </Box>
            </Box>

            {/* Problema / Situação atual */}
            <Box className="mt-4">
              <Typography
                fontSize={FontConfig.medium}
                fontWeight="bold"
                onClick={() => lerTexto(texts.detalhesProposta.problema)}
              >
                {texts.detalhesProposta.problema}:&nbsp;
              </Typography>
              <Box className="mx-4">
                <Typography
                  fontSize={FontConfig.medium}
                  ref={problemaText}
                  onClick={() => {
                    lerTexto(problemaText);
                  }}
                />
              </Box>
            </Box>

            {/* Escopo da proposta */}
            <Box className="mt-4">
              <Typography
                fontSize={FontConfig.medium}
                fontWeight="bold"
                onClick={() =>
                  lerTexto(texts.detalhesProposta.escopoDaProposta)
                }
              >
                {texts.detalhesProposta.escopoDaProposta}:&nbsp;
              </Typography>
              <Box className="mx-4">
                <Typography
                  fontSize={FontConfig.medium}
                  ref={textoEscopo}
                  onClick={() => {
                    lerTexto(textoEscopo);
                  }}
                />
              </Box>
            </Box>

            {/* Frequência */}
            <Box className="flex mt-4">
              <Typography
                fontSize={FontConfig.medium}
                fontWeight="bold"
                onClick={() => lerTexto(texts.detalhesProposta.frequencia)}
              >
                {texts.detalhesProposta.frequencia}:&nbsp;
              </Typography>
              <Typography
                fontSize={FontConfig.medium}
                onClick={() => lerTexto(proposta.frequencia)}
              >
                {proposta.frequencia}
              </Typography>
            </Box>

            {/* Tabela de custos */}
            <Box className="mt-4">
              <Typography
                fontSize={FontConfig.medium}
                fontWeight="bold"
                onClick={() => lerTexto(texts.detalhesProposta.tabelaDeCustos)}
              >
                {texts.detalhesProposta.tabelaDeCustos}:&nbsp;
              </Typography>
              <Box className="mx-4">
                {proposta.tabelaCustos?.map((tabela, index) => {
                  return <TabelaCustos key={index} dados={tabela} />;
                })}
              </Box>
            </Box>

            {/* Benefícios */}
            <Box className="mt-4">
              <Typography
                fontSize={FontConfig.medium}
                fontWeight="bold"
                onClick={() => lerTexto(texts.detalhesProposta.beneficios)}
              >
                {texts.detalhesProposta.beneficios}:&nbsp;
              </Typography>
              <Box className="mx-4">
                {proposta.beneficios.length > 0 ? (
                  proposta.beneficios.map((beneficio, index) => {
                    return <Beneficio key={index} beneficio={beneficio} />;
                  })
                ) : (
                  <Typography
                    className="text-center"
                    fontSize={FontConfig.medium}
                    color="text.secondary"
                    onClick={() =>
                      lerTexto(texts.detalhesProposta.semBeneficios)
                    }
                  >
                    {texts.detalhesProposta.semBeneficios}
                  </Typography>
                )}
              </Box>
            </Box>

            {/* BUs beneficiadas */}
            <Box className="mt-4">
              <Typography
                fontSize={FontConfig.medium}
                fontWeight="bold"
                onClick={() => lerTexto(texts.detalhesProposta.busBeneficiadas)}
              >
                {texts.detalhesProposta.busBeneficiadas}:&nbsp;
              </Typography>
              <Box className="mx-8">
                {proposta.busBeneficiadas.length > 0 ? (
                  <ol className="list-disc">
                    {proposta.busBeneficiadas.map((bu, index) => {
                      return (
                        <li
                          key={index}
                          onClick={() => {
                            lerTexto(bu.nomeBu);
                          }}
                        >
                          {bu.siglaBu} - {bu.nomeBu}
                        </li>
                      );
                    })}
                  </ol>
                ) : (
                  <Typography
                    className="text-center"
                    fontSize={FontConfig.medium}
                    color="text.secondary"
                    onClick={() =>
                      lerTexto(texts.detalhesProposta.semBuBeneficiada)
                    }
                  >
                    {texts.detalhesProposta.semBuBeneficiada}
                  </Typography>
                )}
              </Box>
            </Box>

            {/* Link do Jira */}
            <Box className="flex mt-4">
              <Typography
                fontSize={FontConfig.medium}
                fontWeight="bold"
                onClick={() => lerTexto(texts.detalhesProposta.linkJira)}
              >
                {texts.detalhesProposta.linkJira}:&nbsp;
              </Typography>
              <Typography
                fontSize={FontConfig.medium}
                onClick={() => lerTexto(proposta.linkJira)}
              >
                {proposta.linkJira}
              </Typography>
            </Box>

            {/* Período de execução */}
            <Box className="flex flex-row mt-4">
              <Typography
                fontSize={FontConfig.medium}
                fontWeight="bold"
                onClick={() =>
                  lerTexto(texts.detalhesProposta.periodoDeExecucao)
                }
              >
                {texts.detalhesProposta.periodoDeExecucao}:&nbsp;
              </Typography>

              <Typography
                fontSize={FontConfig.medium}
                onClick={() =>
                  lerTexto(
                    DateService.getTodaysDateUSFormat(
                      DateService.getDateByMySQLFormat(proposta.inicioExecucao)
                    ) +
                      " " +
                      texts.detalhesProposta.ate +
                      " " +
                      DateService.getTodaysDateUSFormat(
                        DateService.getDateByMySQLFormat(proposta.fimExecucao)
                      )
                  )
                }
              >
                {DateService.getTodaysDateUSFormat(
                  DateService.getDateByMySQLFormat(proposta.inicioExecucao)
                )}{" "}
                {texts.detalhesProposta.ate}{" "}
                {DateService.getTodaysDateUSFormat(
                  DateService.getDateByMySQLFormat(proposta.fimExecucao)
                )}
              </Typography>
            </Box>

            {/* Payback */}
            <Box className="flex mt-4">
              <Typography
                fontSize={FontConfig.medium}
                fontWeight="bold"
                onClick={() => lerTexto(texts.detalhesProposta.payback)}
              >
                {texts.detalhesProposta.payback}:&nbsp;
              </Typography>
              <Box>
                <Typography
                  fontSize={FontConfig.medium}
                  onClick={() =>
                    lerTexto(
                      proposta.paybackValor +
                        " " +
                        proposta.paybackTipo.toLowerCase()
                    )
                  }
                >
                  {proposta.paybackValor} {proposta.paybackTipo.toLowerCase()}
                </Typography>
              </Box>
            </Box>

            {/* Anexos */}
            <Box className="mt-4">
              <Typography
                fontSize={FontConfig.medium}
                fontWeight="bold"
                onClick={() => lerTexto(texts.detalhesProposta.anexos)}
              >
                {texts.detalhesProposta.anexos}:&nbsp;
              </Typography>
              <Box className="mx-4">
                {proposta.anexo.length > 0 ? (
                  proposta.anexo.map((anexo, index) => {
                    return (
                      <Paper
                        key={index}
                        elevation={0}
                        className="flex justify-between items-center mt-2 px-2 border border-l-4"
                        sx={{ borderLeftColor: "primary.main" }}
                        square
                      >
                        <Typography
                          fontSize={FontConfig.medium}
                          onClick={() =>
                            lerTexto(anexo.nome + " - " + anexo.tipo)
                          }
                        >
                          {anexo.nome} - {anexo.tipo}
                        </Typography>
                        <Tooltip title={texts.detalhesProposta.download}>
                          <IconButton onClick={() => downloadAnexo(anexo)}>
                            <DownloadIcon />
                          </IconButton>
                        </Tooltip>
                      </Paper>
                    );
                  })
                ) : (
                  <Typography
                    className="text-center"
                    fontSize={FontConfig.medium}
                    color="text.secondary"
                    onClick={() => lerTexto(texts.detalhesProposta.semAnexos)}
                  >
                    {texts.detalhesProposta.semAnexos}
                  </Typography>
                )}
              </Box>
            </Box>

            {/* Responsáveis do negócio */}
            <Box className="mt-6 mb-4 text-center">
              {proposta.responsavelNegocio.map((responsavel, index) => {
                return (
                  <Typography
                    key={index}
                    fontSize={FontConfig.medium}
                    onClick={() =>
                      lerTexto(responsavel.nome + " - " + responsavel.area)
                    }
                  >
                    {responsavel.nome} - {responsavel.area}
                  </Typography>
                );
              })}
              <Typography
                fontSize={FontConfig.medium}
                fontWeight="bold"
                onClick={() =>
                  lerTexto(texts.detalhesProposta.reponsaveisPeloNegocio)
                }
              >
                {texts.detalhesProposta.reponsaveisPeloNegocio}
              </Typography>
            </Box>

            {!(proposta.status == "ASSESSMENT_APROVACAO") && (
              <>
                <Divider />
                {/* Pareceres */}
                <Box className="mt-3">
                  <Typography
                    fontSize={FontConfig.big}
                    fontWeight="bold"
                    onClick={() => lerTexto(texts.detalhesProposta.pareceres)}
                  >
                    {texts.detalhesProposta.pareceres}:&nbsp;
                  </Typography>
                  <Box className="mx-4">
                    {/* Parecer da Comissão */}
                    <ParecerComissao
                      proposta={proposta}
                      setProposta={setProposta}
                      setDadosProposta={setDadosProposta}
                      parecerComissao={parecerComissao}
                      parecerInformacao={parecerInformacao}
                    />

                    {/* Parecer da Diretoria */}
                    {[
                      "ASSESSMENT_DG",
                      "DONE",
                      "ASSESSMENT_EDICAO",
                      "CANCELLED",
                    ].includes(proposta.status) && (
                      <ParecerDG
                        proposta={proposta}
                        setProposta={setProposta}
                        setDadosProposta={setDadosProposta}
                        parecerDG={parecerDG}
                        parecerInformacaoDG={parecerInformacaoDG}
                      />
                    )}
                  </Box>
                </Box>
              </>
            )}
          </Box>
        </Box>
      </Box>
    </Box>
  );
};

// Mostrar a tabela de custos
const TabelaCustos = ({
  dados = {
    id: 0,
    custos: [
      {
        id: 0,
        tipoDespesa: "",
        perfilDespesa: "",
        periodoExecucao: 0,
        horas: 0,
        valorHora: 0,
      },
    ],
    ccs: [{ id: 0, codigo: 0, porcentagem: 0.0 }],
  },
}) => {
  // Context para obter as configurações de fontes do sistema
  const { FontConfig } = useContext(FontContext);

  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  return (
    <Paper className="w-full mt-2 mb-6" square>
      <Table className="table-fixed w-full">
        <TableHead>
          <TableRow sx={{ backgroundColor: "primary.main" }}>
            <th className="text-white p-1">
              <Typography fontWeight="bold" fontSize={FontConfig.default}>
                {texts.detalhesProposta.tipoDaDespesa}
              </Typography>
            </th>
            <th className="text-white p-1">
              <Typography fontWeight="bold" fontSize={FontConfig.default}>
                {texts.detalhesProposta.perfilDaDespesa}
              </Typography>
            </th>
            <th className="text-white p-1">
              <Typography fontWeight="bold" fontSize={FontConfig.default}>
                {texts.detalhesProposta.periodoDeExecucaoTabela}
              </Typography>
            </th>
            <th className="text-white p-1">
              <Typography fontWeight="bold" fontSize={FontConfig.default}>
                {texts.detalhesProposta.horas}
              </Typography>
            </th>
            <th className="text-white p-1">
              <Typography fontWeight="bold" fontSize={FontConfig.default}>
                {texts.detalhesProposta.valorHora}
              </Typography>
            </th>
            <th className="text-white p-1">
              <Typography fontWeight="bold" fontSize={FontConfig.default}>
                {texts.detalhesProposta.total}
              </Typography>
            </th>
          </TableRow>
        </TableHead>
        <TableBody>
          {dados.custos.map((custo, index) => {
            return <CustosRow key={index} custo={custo} />;
          })}
        </TableBody>
      </Table>
      <Box className="mt-2">
        <Table className="table-fixed w-full">
          <TableHead>
            <TableRow sx={{ backgroundColor: "primary.main" }}>
              <th className="text-white p-1">
                <Typography fontSize={FontConfig.medium}>
                  {texts.detalhesProposta.ccs}
                </Typography>
              </th>
              <th className="text-white p-1">
                <Typography fontSize={FontConfig.medium}>
                  {texts.detalhesProposta.porcentagem}
                </Typography>
              </th>
            </TableRow>
          </TableHead>
          <TableBody>
            {dados.ccs.map((cc, index) => {
              return (
                <TableRow key={index} className="w-full border rounded">
                  <td className="text-center p-1">
                    <Typography fontSize={FontConfig.medium}>
                      {cc.codigo}
                    </Typography>
                  </td>
                  <td className="text-center p-1">
                    <Typography fontSize={FontConfig.medium}>
                      {cc.porcentagem}%
                    </Typography>
                  </td>
                </TableRow>
              );
            })}
          </TableBody>
        </Table>
      </Box>
    </Paper>
  );
};

// Mostrar os custos na proposta
const CustosRow = ({
  custo = {
    id: 0,
    tipoDespesa: "",
    perfilDespesa: "",
    periodoExecucao: 0,
    horas: 0,
    valorHora: 0,
  },
}) => {
  // Context para obter as configurações de fonte do sistema
  const { FontConfig } = useContext(FontContext);

  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  // Formatando o tipo da moeda de acordo com o local do usuário
  const getValorFormatted = (valor) => {
    let local = "pt-BR";
    let tipoMoeda = "BRL";

    switch (texts.linguagem) {
      case "pt":
        local = "pt-BR";
        tipoMoeda = "BRL";
        break;
      case "en":
        local = "en-US";
        tipoMoeda = "USD";
        break;
      case "ch":
        local = "zh-CN";
        tipoMoeda = "CNY";
        break;
      default:
        local = "pt-BR";
        tipoMoeda = "BRL";
        break;
    }

    return valor
      ? valor.toLocaleString(local, {
          style: "currency",
          currency: tipoMoeda,
        })
      : 0.0;
  };

  return (
    <TableRow>
      <td className="p-2 text-center">
        <Typography fontSize={FontConfig.default}>
          {custo.tipoDespesa}
        </Typography>
      </td>
      <td className="p-2 text-center">
        <Typography fontSize={FontConfig.default}>
          {custo.perfilDespesa}
        </Typography>
      </td>
      <td className="p-2 text-center">
        <Typography fontSize={FontConfig.default}>
          {custo.periodoExecucao}
        </Typography>
      </td>
      <td className="p-2 text-center">
        <Typography fontSize={FontConfig.default}>{custo.horas}</Typography>
      </td>
      <td className="p-2 text-center">
        <Typography fontSize={FontConfig.default}>
          {getValorFormatted(custo.valorHora)}
        </Typography>
      </td>
      <td className="p-2 text-center">
        <Typography fontSize={FontConfig.default}>
          {getValorFormatted(custo.horas * custo.valorHora)}
        </Typography>
      </td>
    </TableRow>
  );
};

// Mostrar os benefícios da proposta
const Beneficio = ({ beneficio = EntitiesObjectService.beneficio() }) => {
  // Context para obter as configurações de fonte do sistema
  const { FontConfig } = useContext(FontContext);

  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  // Estado se é um beneficio com tipo qualitativo
  const [isQualitativo, setIsQualitativo] = useState(false);

  const memoriaCalculoText = useRef(null);

  // Verifica se o benefício é do tipo qualitativo
  useEffect(() => {
    if (beneficio.tipoBeneficio == "QUALITATIVO") {
      setIsQualitativo(true);
    }

    if (memoriaCalculoText.current) {
      memoriaCalculoText.current.innerHTML = beneficio.memoriaCalculo;
    }
  }, []);

  if (beneficio.id === 0) return null;

  return (
    <Paper
      className="flex justify-between items-center mt-2 border-t-4"
      sx={{ borderTopColor: "primary.main" }}
      square
    >
      <Table>
        <TableBody>
          <TableRow>
            <th className="p-1">
              <Typography
                color="primary"
                fontWeight="bold"
                fontSize={FontConfig.medium}
              >
                {texts.detalhesProposta.tipoBeneficio}
              </Typography>
            </th>
            {!isQualitativo && (
              <>
                <th className="p-1">
                  <Typography
                    color="primary"
                    fontWeight="bold"
                    fontSize={FontConfig.medium}
                  >
                    {texts.detalhesProposta.valorMensal}
                  </Typography>
                </th>
                <th className="p-1">
                  <Typography
                    color="primary"
                    fontWeight="bold"
                    fontSize={FontConfig.medium}
                  >
                    {texts.detalhesProposta.moeda}
                  </Typography>
                </th>
              </>
            )}
            <th className="p-1">
              <Typography
                color="primary"
                fontWeight="bold"
                fontSize={FontConfig.medium}
              >
                {texts.detalhesProposta.memoriaCalculo}
              </Typography>
            </th>
          </TableRow>
        </TableBody>
        <TableBody className="border-t">
          <TableRow>
            <td className="text-center p-1">
              <Typography fontSize={FontConfig.default}>
                {beneficio.tipoBeneficio[0].toUpperCase() +
                  beneficio.tipoBeneficio.substring(1).toLowerCase()}
              </Typography>
            </td>
            {!isQualitativo && (
              <>
                <td className="text-center p-1">
                  <Typography fontSize={FontConfig.default}>
                    {beneficio.valor_mensal}
                  </Typography>
                </td>
                <td className="text-center p-1">
                  <Typography fontSize={FontConfig.default}>
                    {beneficio.moeda}
                  </Typography>
                </td>
              </>
            )}
            <td className="text-center p-1">
              <Typography
                ref={memoriaCalculoText}
                fontSize={FontConfig.default}
              >
                {beneficio.memoriaCalculo}
              </Typography>
            </td>
          </TableRow>
        </TableBody>
      </Table>
    </Paper>
  );
};

// Chamar o parecer da comissão
const ParecerComissao = ({
  proposta = propostaExample,
  setProposta = () => {},
  setDadosProposta = () => {},
  parecerComissao = "",
  parecerInformacao = "",
}) => {
  if (proposta.status == "ASSESSMENT_COMISSAO")
    return (
      <ParecerComissaoInsertText
        proposta={proposta}
        setProposta={setProposta}
        setDadosProposta={setDadosProposta}
        parecerComissao={parecerComissao}
        parecerInformacao={parecerInformacao}
      />
    );
  return <ParecerComissaoOnlyRead proposta={proposta} />;
};

// Chamar o parecer da DG
const ParecerDG = ({
  proposta = propostaExample,
  setProposta = () => {},
  setDadosProposta = () => {},
  parecerDG = "",
  parecerInformacaoDG = "",
}) => {
  if (proposta.status == "ASSESSMENT_DG")
    return (
      <ParecerDGInsertText
        proposta={proposta}
        setProposta={setProposta}
        setDadosProposta={setDadosProposta}
        parecerDG={parecerDG}
        parecerInformacaoDG={parecerInformacaoDG}
      />
    );
  return <ParecerDGOnlyRead proposta={proposta} />;
};

// Escrever o parecer da comissão
const ParecerComissaoInsertText = ({
  proposta = propostaExample,
  setProposta = () => {},
  setDadosProposta = () => {},
  parecerComissao = "",
  parecerInformacao = "",
}) => {
  // Context para obter as configurações de fontes do sistema
  const { FontConfig } = useContext(FontContext);

  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  return (
    <Box>
      <Box className="flex">
        <Box className="flex items-center mt-4">
          <Typography fontSize={FontConfig.medium}>
            {texts.detalhesProposta.comissao} {proposta.forum.nome}:&nbsp;
          </Typography>
        </Box>
        <TextField
          select
          label={texts.detalhesProposta.parecer}
          value={parecerComissao}
          onChange={(event) => {
            setProposta({ ...proposta, parecerComissao: event.target.value });
            setDadosProposta({
              ...proposta,
              parecerComissao: event.target.value,
            });
          }}
          variant="standard"
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
          <MenuItem key={"Business Case"} value={"BUSINESS_CASE"}>
            <Typography fontSize={FontConfig.medium}>
              {texts.detalhesProposta.businessCase}
            </Typography>
          </MenuItem>
        </TextField>
      </Box>
      <Box className="mt-4">
        <CaixaTextoQuill
          texto={parecerInformacao}
          onChange={(e) => {
            setProposta({ ...proposta, parecerInformacao: e });
            setDadosProposta({ ...proposta, parecerInformacao: e });
          }}
        />
      </Box>
    </Box>
  );
};

// Visualizar o parecer da comissão
const ParecerComissaoOnlyRead = ({ proposta = propostaExample }) => {
  // Context para obter as configurações das fontes do sistema
  const { FontConfig } = useContext(FontContext);

  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  // Variável para armazenar as informações do parecer da comissão
  const parecerComissaoInformacoesBox = useRef(null);

  // useEffect para atualizar o texto do parecer da comissão
  useEffect(() => {
    if (parecerComissaoInformacoesBox.current) {
      parecerComissaoInformacoesBox.current.innerHTML =
        proposta.parecerInformacao
          ? proposta.parecerInformacao
          : texts.detalhesProposta.semInformacoesAdicionais;
    }
  }, []);

  // Função para formatar o parecer
  const getParecerComissaoFomartted = (parecer) => {
    if (!parecer) {
      return texts.detalhesProposta.semParecer;
    }

    switch (parecer) {
      case "APROVADO":
        return texts.detalhesProposta.aprovado;
      case "REPROVADO":
        return texts.detalhesProposta.reprovado;
      case "MAIS_INFORMACOES":
        return texts.detalhesProposta.devolvido;
      case "BUSINESS_CASE":
        return texts.detalhesProposta.businessCase;
    }
  };

  return (
    <Box>
      <Box className="flex">
        <Box className="flex items-center mt-4">
          <Typography fontSize={FontConfig.medium}>
            {texts.detalhesProposta.comissao} {proposta.forum.nome}:&nbsp;
          </Typography>
          <Typography fontSize={FontConfig.medium} fontWeight="bold">
            {getParecerComissaoFomartted(proposta.parecerComissao)}
          </Typography>
        </Box>
      </Box>
      {/* Comporta o texto do parecer da comissão */}
      <Box
        ref={parecerComissaoInformacoesBox}
        className="mt-2 mx-4 border-l-2 px-2"
        sx={{ borderColor: "primary.main" }}
      />
    </Box>
  );
};

// Escrever o parecer da DG
const ParecerDGInsertText = ({
  proposta = propostaExample,
  setProposta = () => {},
  setDadosProposta = () => {},
  parecerDG = "",
  parecerInformacaoDG = "",
}) => {
  // Context para obter as configurações das fontes do sistema
  const { FontConfig } = useContext(FontContext);

  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  return (
    <Box className="mt-4">
      <Box className="flex">
        <Box className="flex items-center mt-4">
          <Typography>{texts.detalhesProposta.direcaoGeral}: &nbsp;</Typography>
        </Box>
        <TextField
          select
          label={texts.detalhesProposta.parecer}
          value={parecerDG || ""}
          onChange={(event) => {
            setProposta({ ...proposta, parecerDG: event.target.value });
            setDadosProposta({ ...proposta, parecerDG: event.target.value });
          }}
          variant="standard"
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
        </TextField>
      </Box>
      <Box className="mt-4">
        <CaixaTextoQuill
          texto={parecerInformacaoDG || ""}
          onChange={(e) => {
            setProposta({ ...proposta, parecerInformacaoDG: e });
            setDadosProposta({ ...proposta, parecerInformacaoDG: e });
          }}
        />
      </Box>
    </Box>
  );
};

// Visualizar o parecer da DG
const ParecerDGOnlyRead = ({ proposta = propostaExample }) => {
  // Context para obter as configurações das fontes do sistema
  const { FontConfig } = useContext(FontContext);

  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  // Variável para armazenar o parecer da DG
  const parecerDGInformacoesBox = useRef(null);

  // UseEffect utilizado para armazenar o valor do parecer da dg
  useEffect(() => {
    if (parecerDGInformacoesBox.current) {
      parecerDGInformacoesBox.current.innerHTML = proposta.parecerInformacaoDG
        ? proposta.parecerInformacaoDG
        : texts.detalhesProposta.semInformacoesAdicionais;
    }
  }, []);

  // Função para formatar o parecer da DG
  const getParecerDGFomartted = (parecer) => {
    return parecer
      ? parecer[0].toUpperCase() + parecer.substring(1).toLowerCase()
      : texts.detalhesProposta.semParecer;
  };

  return (
    <Box>
      <Box className="flex">
        <Box className="flex items-center mt-4">
          <Typography fontSize={FontConfig.medium}>
            {texts.detalhesProposta.direcaoGeral}:&nbsp;
          </Typography>
          <Typography fontSize={FontConfig.medium} fontWeight="bold">
            {getParecerDGFomartted(proposta.parecerDG)}
          </Typography>
        </Box>
      </Box>
      {/* Comporta o texto do parecer da comissão */}
      <Box
        ref={parecerDGInformacoesBox}
        className="mt-2 mx-4 border-l-2 px-2"
        sx={{ borderColor: "primary.main" }}
      />
    </Box>
  );
};

const StatusProposta = ({
  proposta = propostaExample,
  setProposta = () => {},
  getCorStatus = () => {},
  getStatusFormatted = () => {},
  lendo = false,
  texto = "",
  setTexto = () => {},
}) => {
  // Context para obter as configurações das fontes do sistema
  const { FontConfig } = useContext(FontContext);

  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  // UseState para poder visualizar e alterar o menu do idioma
  const [anchorElModalStatus, setAnchorElModalStatus] = useState(null);

  // Referência do elemento de status
  const statusElement = useRef(null);

  // Estado do modal de trocar status
  const [modalStatus, setModalStatus] = useState(false);

  // Estado do modal de confirmação de troca de status
  const [confirmEditStatus, setConfirmEditStatus] = useState(false);

  // Estado do novo status
  const [newStatus, setNewStatus] = useState("");

  // Estado para a controlar o feedback de erro de autoridade
  const [feedbackErrorAuthority, setFeedbackErrorAuthority] = useState(false);

  // Estado para controlar o feedback de erro de mesmo status selecionado
  const [feedbackStatusError, setFeedbackStatusError] = useState(false);

  // Abre o modal para alterar o status da proposta
  const handleOpenModalStatus = () => {
    setModalStatus(true);
  };

  // Fecha o modal para alterar o status da proposta
  const handleCloseModalStataus = () => {
    setModalStatus(false);
  };

  const confirmSelectStatus = (status) => {
    if (isSameStatus(status)) {
      setFeedbackStatusError(true);
      return;
    }

    setModalStatus(false);
    setNewStatus(status);
    setConfirmEditStatus(true);
  };

  // Verifica se o status selecionado é o mesmo da proposta
  const isSameStatus = (status) => {
    switch (status) {
      case "ASSESSMENT_APROVACAO":
        return proposta.status.startsWith("ASSESSMENT");
      case "BUSINESS_CASE":
        return proposta.status == "BUSINESS_CASE";
      case "CANCELLED":
        return proposta.status == "CANCELLED";
      case "DONE":
        return proposta.status == "DONE";
    }

    return false;
  };

  // Formata o HTML em casos como a falta de fechamentos em tags "<br>"
  const formatarHtml = (texto) => {
    texto = texto.replace(/<br>/g, "<br/>");
    return texto;
  };

  // Formata alguns dados da proposta para outro tipo de dado
  const arrangeData = (propostaObj = EntitiesObjectService.proposta()) => {
    propostaObj.problema = btoa(formatarHtml(propostaObj.problema));
    propostaObj.proposta = btoa(formatarHtml(propostaObj.proposta));
    propostaObj.escopo = btoa(formatarHtml(propostaObj.escopo));

    for (let beneficio of propostaObj.beneficios) {
      beneficio.memoriaCalculo = btoa(formatarHtml(beneficio.memoriaCalculo));
    }
  };

  // Função para transformar um base64 em uma string
  const convertByteArrayToString = (byteArray = []) => {
    return window.atob(byteArray).toString("utf-8");
  };

  const userHasAuthority = () => {
    let userCookie = UsuarioService.getUserCookies();
    let user = userCookie.usuario;
    if (proposta.analista.id == user.id) return true;

    return false;
  };

  // Função para editar o status da proposta
  const editarStatus = () => {
    if (newStatus == "") {
      console.log("Status não pode ser vazio!");
      return;
    }

    setConfirmEditStatus(false);

    // Requisição para atualizar a proposta com o novo status
    PropostaService.atualizarStatus(proposta.id, newStatus).then((response) => {
      setProposta({ ...proposta, status: response.status });
    });
  };

  useEffect(() => {
    // Adiciona o elemento do status para poder configurar a posição do modal
    if (statusElement.current) {
      setAnchorElModalStatus(statusElement.current);
    }
  }, []);

  return (
    <>
      <Feedback
        open={feedbackErrorAuthority}
        handleClose={() => setFeedbackErrorAuthority(false)}
        status={"erro"}
        mensagem={texts.detalhesProposta.feedbackErrorAuthority}
        lendo={lendo}
        texto={texto}
        setTexto={setTexto}
      />
      <Feedback
        open={feedbackStatusError}
        handleClose={() => setFeedbackStatusError(false)}
        status={"erro"}
        mensagem={texts.detalhesProposta.mesmoStatus}
        lendo={lendo}
        texto={texto}
        setTexto={setTexto}
      />
      <ModalConfirmacao
        open={confirmEditStatus}
        setOpen={setConfirmEditStatus}
        textoModal={"alterarStatusProposta"}
        textoBotao={"sim"}
        onConfirmClick={editarStatus}
        onCancelClick={() => {}}
        lendo={lendo}
        texto={texto}
        setTexto={setTexto}
      />
      <Menu
        id="basic-menu"
        anchorEl={anchorElModalStatus}
        open={modalStatus}
        onClose={handleCloseModalStataus}
        MenuListProps={{
          "aria-labelledby": "basic-button",
        }}
      >
        <MenuItem
          className="gap-2"
          onClick={() => confirmSelectStatus("ASSESSMENT_APROVACAO")}
        >
          <Box
            className="w-4 h-4 rounded"
            sx={{ backgroundColor: getCorStatus("ASSESSMENT_APROVACAO") }}
          />
          <Typography fontSize={FontConfig.default}>
            {texts.detalhesProposta.statusText.assessment}
          </Typography>
        </MenuItem>
        <MenuItem
          className="gap-2"
          onClick={() => confirmSelectStatus("BUSINESS_CASE")}
        >
          <Box
            className="w-4 h-4 rounded"
            sx={{ backgroundColor: getCorStatus("BUSINESS_CASE") }}
          />
          <Typography fontSize={FontConfig.default}>
            {texts.detalhesProposta.statusText.bussinessCase}
          </Typography>
        </MenuItem>
        <MenuItem
          className="gap-2"
          onClick={() => confirmSelectStatus("CANCELLED")}
        >
          <Box
            className="w-4 h-4 rounded"
            sx={{ backgroundColor: getCorStatus("CANCELLED") }}
          />
          <Typography fontSize={FontConfig.default}>
            {texts.detalhesProposta.statusText.cancelled}
          </Typography>
        </MenuItem>
        <MenuItem className="gap-2" onClick={() => confirmSelectStatus("DONE")}>
          <Box
            className="w-4 h-4 rounded"
            sx={{ backgroundColor: getCorStatus("DONE") }}
          />
          <Typography fontSize={FontConfig.default}>
            {texts.detalhesProposta.statusText.done}
          </Typography>
        </MenuItem>
      </Menu>

      <Tooltip title={getStatusFormatted()}>
        <Box
          className="flex absolute right-2 top-0 cursor-pointer"
          onClick={handleOpenModalStatus}
          ref={statusElement}
        >
          <Box
            className="w-0 h-0 relative left-4"
            sx={{
              borderTop: `1.8rem solid ${getCorStatus(proposta.status)}`,
              borderRight: "1.1rem solid transparent",
              borderLeft: "0px solid transparent",
            }}
          />
          <Box
            className="w-0 h-0 relative"
            sx={{
              borderTop: `1.8rem solid ${getCorStatus(proposta.status)}`,
              borderRight: "0rem solid transparent",
              borderLeft: "1.1rem solid transparent",
            }}
          />
        </Box>
      </Tooltip>
    </>
  );
};

export default DetalhesProposta;
