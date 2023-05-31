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
import CookieService from "../../service/cookieService";
import ExportPdfService from "../../service/exportPdfService";
import NotificacaoService from "../../service/notificacaoService";

import ClipLoader from "react-spinners/ClipLoader";
import Feedback from "../Feedback/Feedback";

// Exemplo de proposta a ser seguido
const propostaExample = EntitiesObjectService.proposta();

// Componente  para mostrar os detalhes de uma proposta e suas respectivas funções
const DetalhesProposta = ({
  propostaId = 0,
  emAprovacao = false,
  parecerComissao = "",
  parecerInformacao = "",
  parecerDG = "",
  parecerInformacaoDG = "",
  setDadosProposta = () => {},
  setFeedbackEditSuccess = () => {},
  lendo,
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
  };

  /** Função da edição de status da proposta  */
  const editProposta = (proposal) => {
    setFeedbackEditSuccess(true);
    setProposta(proposal);
  };

  const isEscopoVazio = () => {
    return (
      proposta.escopo == "" ||
      proposta.escopo == null ||
      proposta.escopo == "<p><br/></p>"
    );
  };

  useEffect(() => {
    if (problemaText.current) {
      problemaText.current.innerHTML = proposta.problema;
    }

    if (propostaText.current) {
      propostaText.current.innerHTML = proposta.proposta;
    }

    if (textoEscopo.current) {
      textoEscopo.current.innerHTML = !isEscopoVazio()
        ? proposta.escopo
        : texts.detalhesProposta.escopoVazio;
    }

    console.log(proposta);
  }, [proposta, isEditing]);

  useEffect(() => {
    // Buscando os dados da proposta usando o propostaId
    PropostaService.getById(propostaId).then((proposal) => {
      // Arrumando alguns textos
      formatData(proposal);
      setProposta(proposal);
      setIsLoading(false);
    });
  }, []);

  // Função que irá setar o texto que será "lido" pela a API
  const lerTexto = (escrita) => {
    if (lendo) {
      const synthesis = window.speechSynthesis;
      const utterance = new SpeechSynthesisUtterance(escrita);

      const finalizarLeitura = () => {
        if ("speechSynthesis" in window) {
          synthesis.cancel();
        }
      };

      if (lendo && escrita !== "") {
        if ("speechSynthesis" in window) {
          synthesis.speak(utterance);
        }
      } else {
        finalizarLeitura();
      }

      return () => {
        finalizarLeitura();
      };
    }
  };

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
          <DetalhesPropostaEditMode
            propostaData={proposta}
            setPropostaData={setPropostaNewData}
            setIsEditing={setIsEditing}
            emAprovacao={emAprovacao}
            lendo={lendo}
          />
        </Box>
      </Box>
    );
  }

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
          lendo={lendo}
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
                DateService.getDateByMySQLFormat(proposta.data),
                texts.linguagem
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
                  return (
                    <TabelaCustos key={index} dados={tabela} lendo={lendo} />
                  );
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
                    return (
                      <Beneficio
                        key={index}
                        beneficio={beneficio}
                        lendo={lendo}
                      />
                    );
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
                      DateService.getDateByMySQLFormat(proposta.inicioExecucao),
                      texts.linguagem
                    ) +
                      " " +
                      texts.detalhesProposta.ate +
                      " " +
                      DateService.getTodaysDateUSFormat(
                        DateService.getDateByMySQLFormat(proposta.fimExecucao),
                        texts.linguagem
                      )
                  )
                }
              >
                {DateService.getTodaysDateUSFormat(
                  DateService.getDateByMySQLFormat(proposta.inicioExecucao),
                  texts.linguagem
                )}{" "}
                {texts.detalhesProposta.ate}{" "}
                {DateService.getTodaysDateUSFormat(
                  DateService.getDateByMySQLFormat(proposta.fimExecucao),
                  texts.linguagem
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
                  lendo={lendo}
                  emAprovacao={emAprovacao}
                />

                {/* Parecer da Diretoria */}
                <ParecerDG
                  proposta={proposta}
                  setProposta={setProposta}
                  setDadosProposta={setDadosProposta}
                  parecerDG={parecerDG}
                  parecerInformacaoDG={parecerInformacaoDG}
                  lendo={lendo}
                  emAprovacao={emAprovacao}
                />
              </Box>
            </Box>
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
    tipoDespesa: "",
    custos: [
      {
        id: 0,
        perfilDespesa: "",
        periodoExecucao: 0,
        horas: 0,
        valorHora: 0,
      },
    ],
    ccs: [{ id: 0, codigo: 0, porcentagem: 0.0 }],
  },
  lendo = false,
}) => {
  // Context para obter as configurações de fontes do sistema
  const { FontConfig } = useContext(FontContext);

  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  // Função que irá setar o texto que será "lido" pela a API
  const lerTexto = (escrita) => {
    if (lendo) {
      const synthesis = window.speechSynthesis;
      const utterance = new SpeechSynthesisUtterance(escrita);

      const finalizarLeitura = () => {
        if ("speechSynthesis" in window) {
          synthesis.cancel();
        }
      };

      if (lendo && escrita !== "") {
        if ("speechSynthesis" in window) {
          synthesis.speak(utterance);
        }
      } else {
        finalizarLeitura();
      }

      return () => {
        finalizarLeitura();
      };
    }
  };

  return (
    <Paper className="w-full mt-2 mb-6" square>
      <Table className="table-fixed w-full">
        <TableHead>
          <TableRow sx={{ backgroundColor: "primary.main" }}>
            <th className="text-white p-1">
              <Typography
                fontWeight="bold"
                fontSize={FontConfig.default}
                onClick={() => lerTexto(texts.detalhesProposta.tipoDaDespesa)}
              >
                {texts.detalhesProposta.tipoDaDespesa}
              </Typography>
            </th>
            <th className="text-white p-1">
              <Typography
                fontWeight="bold"
                fontSize={FontConfig.default}
                onClick={() => lerTexto(texts.detalhesProposta.perfilDaDespesa)}
              >
                {texts.detalhesProposta.perfilDaDespesa}
              </Typography>
            </th>
            <th className="text-white p-1">
              <Typography
                fontWeight="bold"
                fontSize={FontConfig.default}
                onClick={() =>
                  lerTexto(texts.detalhesProposta.periodoDeExecucao)
                }
              >
                {texts.detalhesProposta.periodoDeExecucaoTabela}
              </Typography>
            </th>
            <th className="text-white p-1">
              <Typography
                fontWeight="bold"
                fontSize={FontConfig.default}
                onClick={() => lerTexto(texts.detalhesProposta.horas)}
              >
                {texts.detalhesProposta.horas}
              </Typography>
            </th>
            <th className="text-white p-1">
              <Typography
                fontWeight="bold"
                fontSize={FontConfig.default}
                onClick={() => lerTexto(texts.detalhesProposta.valorHora)}
              >
                {texts.detalhesProposta.valorHora}
              </Typography>
            </th>
            <th className="text-white p-1">
              <Typography
                fontWeight="bold"
                fontSize={FontConfig.default}
                onClick={() => lerTexto(texts.detalhesProposta.total)}
              >
                {texts.detalhesProposta.total}
              </Typography>
            </th>
          </TableRow>
        </TableHead>
        <TableBody>
          {dados.custos.map((custo, index) => {
            return (
              <CustosRow
                key={index}
                custo={custo}
                lendo={lendo}
                despesa={dados}
              />
            );
          })}
        </TableBody>
      </Table>
      <Box className="mt-2">
        <Table className="table-fixed w-full">
          <TableHead>
            <TableRow sx={{ backgroundColor: "primary.main" }}>
              <th className="text-white p-1">
                <Typography
                  fontSize={FontConfig.medium}
                  onClick={() => lerTexto(texts.detalhesProposta.ccs)}
                >
                  {texts.detalhesProposta.ccs}
                </Typography>
              </th>
              <th className="text-white p-1">
                <Typography
                  fontSize={FontConfig.medium}
                  onClick={() => lerTexto(texts.detalhesProposta.porcentagem)}
                >
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
                    <Typography
                      fontSize={FontConfig.medium}
                      onClick={() => lerTexto(cc.codigo)}
                    >
                      {cc.codigo}
                    </Typography>
                  </td>
                  <td className="text-center p-1">
                    <Typography
                      fontSize={FontConfig.medium}
                      onClick={() => lerTexto(cc.porcentagem + "%")}
                    >
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
  despesa = { tipoDespesa: "" },
  custo = {
    id: 0,
    tipoDespesa: "",
    perfilDespesa: "",
    periodoExecucao: 0,
    horas: 0,
    valorHora: 0,
  },
  lendo = false,
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

  // Função que irá setar o texto que será "lido" pela a API
  const lerTexto = (escrita) => {
    if (lendo) {
      const synthesis = window.speechSynthesis;
      const utterance = new SpeechSynthesisUtterance(escrita);

      const finalizarLeitura = () => {
        if ("speechSynthesis" in window) {
          synthesis.cancel();
        }
      };

      if (lendo && escrita !== "") {
        if ("speechSynthesis" in window) {
          synthesis.speak(utterance);
        }
      } else {
        finalizarLeitura();
      }

      return () => {
        finalizarLeitura();
      };
    }
  };

  return (
    <TableRow>
      <td className="p-2 text-center">
        <Typography fontSize={FontConfig.default}>
          {despesa.tipoDespesa}
        </Typography>
        <Typography
          fontSize={FontConfig.default}
          onClick={() => lerTexto(custo.tipoDespesa)}
        >
          {custo.tipoDespesa}
        </Typography>
      </td>
      <td className="p-2 text-center">
        <Typography
          fontSize={FontConfig.default}
          onClick={() => lerTexto(custo.perfilDespesa)}
        >
          {custo.perfilDespesa}
        </Typography>
      </td>
      <td className="p-2 text-center">
        <Typography
          fontSize={FontConfig.default}
          onClick={() => lerTexto(custo.periodoExecucao)}
        >
          {custo.periodoExecucao}
        </Typography>
      </td>
      <td className="p-2 text-center">
        <Typography
          fontSize={FontConfig.default}
          onClick={() => lerTexto(custo.horas)}
        >
          {custo.horas}
        </Typography>
      </td>
      <td className="p-2 text-center">
        <Typography
          fontSize={FontConfig.default}
          onClick={() => lerTexto(getValorFormatted(custo.valorHora))}
        >
          {getValorFormatted(custo.valorHora)}
        </Typography>
      </td>
      <td className="p-2 text-center">
        <Typography
          fontSize={FontConfig.default}
          onClick={() =>
            lerTexto(getValorFormatted(custo.horas * custo.valorHora))
          }
        >
          {getValorFormatted(custo.horas * custo.valorHora)}
        </Typography>
      </td>
    </TableRow>
  );
};

// Mostrar os benefícios da proposta
const Beneficio = ({
  beneficio = EntitiesObjectService.beneficio(),
  lendo = false,
}) => {
  // Context para obter as configurações de fonte do sistema
  const { FontConfig } = useContext(FontContext);

  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  // Estado se é um beneficio com tipo qualitativo
  const [isQualitativo, setIsQualitativo] = useState(false);

  const memoriaCalculoText = useRef(null);

  // Função que irá setar o texto que será "lido" pela a API
  const lerTexto = (escrita) => {
    if (lendo) {
      const synthesis = window.speechSynthesis;
      const utterance = new SpeechSynthesisUtterance(escrita);

      const finalizarLeitura = () => {
        if ("speechSynthesis" in window) {
          synthesis.cancel();
        }
      };

      if (lendo && escrita !== "") {
        if ("speechSynthesis" in window) {
          synthesis.speak(utterance);
        }
      } else {
        finalizarLeitura();
      }

      return () => {
        finalizarLeitura();
      };
    }
  };

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
                onClick={() => lerTexto(texts.detalhesProposta.tipoBeneficio)}
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
                    onClick={() => lerTexto(texts.detalhesProposta.valorMensal)}
                  >
                    {texts.detalhesProposta.valorMensal}
                  </Typography>
                </th>
                <th className="p-1">
                  <Typography
                    color="primary"
                    fontWeight="bold"
                    fontSize={FontConfig.medium}
                    onClick={() => lerTexto(texts.detalhesProposta.moeda)}
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
                onClick={() => lerTexto(texts.detalhesProposta.memoriaCalculo)}
              >
                {texts.detalhesProposta.memoriaCalculo}
              </Typography>
            </th>
          </TableRow>
        </TableBody>
        <TableBody className="border-t">
          <TableRow>
            <td className="text-center p-1">
              <Typography
                fontSize={FontConfig.default}
                onClick={() =>
                  lerTexto(
                    beneficio.tipoBeneficio[0].toUpperCase() +
                      beneficio.tipoBeneficio.substring(1).toLowerCase()
                  )
                }
              >
                {beneficio.tipoBeneficio[0].toUpperCase() +
                  beneficio.tipoBeneficio.substring(1).toLowerCase()}
              </Typography>
            </td>
            {!isQualitativo && (
              <>
                <td className="text-center p-1">
                  <Typography
                    fontSize={FontConfig.default}
                    onClick={() => lerTexto(beneficio.valor_mensal)}
                  >
                    {beneficio.valor_mensal}
                  </Typography>
                </td>
                <td className="text-center p-1">
                  <Typography
                    fontSize={FontConfig.default}
                    onClick={() => lerTexto(beneficio.moeda)}
                  >
                    {beneficio.moeda}
                  </Typography>
                </td>
              </>
            )}
            <td className="text-center p-1">
              <Typography
                ref={memoriaCalculoText}
                fontSize={FontConfig.default}
                onClick={() => lerTexto(beneficio.memoriaCalculo)}
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
  lendo = false,
  emAprovacao = false,
}) => {
  if (proposta.status == "ASSESSMENT_COMISSAO" && emAprovacao)
    return (
      <ParecerComissaoInsertText
        proposta={proposta}
        setProposta={setProposta}
        setDadosProposta={setDadosProposta}
        parecerComissao={parecerComissao}
        parecerInformacao={parecerInformacao}
        lendo={lendo}
      />
    );
  return <ParecerComissaoOnlyRead proposta={proposta} lendo={lendo} />;
};

// Chamar o parecer da DG
const ParecerDG = ({
  proposta = propostaExample,
  setProposta = () => {},
  setDadosProposta = () => {},
  parecerDG = "",
  parecerInformacaoDG = "",
  lendo = false,
  emAprovacao = false,
}) => {
  if (proposta.status == "ASSESSMENT_DG" && emAprovacao)
    return (
      <ParecerDGInsertText
        proposta={proposta}
        setProposta={setProposta}
        setDadosProposta={setDadosProposta}
        parecerDG={parecerDG}
        parecerInformacaoDG={parecerInformacaoDG}
        lendo={lendo}
      />
    );
  return <ParecerDGOnlyRead proposta={proposta} lendo={lendo} />;
};

// Escrever o parecer da comissão
const ParecerComissaoInsertText = ({
  proposta = propostaExample,
  setProposta = () => {},
  setDadosProposta = () => {},
  parecerComissao = "",
  parecerInformacao = "",
  lendo = false,
}) => {
  // Context para obter as configurações de fontes do sistema
  const { FontConfig } = useContext(FontContext);

  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  // Função que irá setar o texto que será "lido" pela a API
  const lerTexto = (escrita) => {
    if (lendo) {
      const synthesis = window.speechSynthesis;
      const utterance = new SpeechSynthesisUtterance(escrita);

      const finalizarLeitura = () => {
        if ("speechSynthesis" in window) {
          synthesis.cancel();
        }
      };

      if (lendo && escrita !== "") {
        if ("speechSynthesis" in window) {
          synthesis.speak(utterance);
        }
      } else {
        finalizarLeitura();
      }

      return () => {
        finalizarLeitura();
      };
    }
  };

  return (
    <Box>
      <Box className="flex">
        <Box className="flex items-center mt-4">
          <Typography
            fontSize={FontConfig.medium}
            onClick={() =>
              lerTexto(
                texts.detalhesProposta.comissao + " " + proposta.forum.nome
              )
            }
          >
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
          lendo={lendo}
        />
      </Box>
    </Box>
  );
};

// Visualizar o parecer da comissão
const ParecerComissaoOnlyRead = ({
  proposta = propostaExample,
  lendo = false,
}) => {
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

  // Função que irá setar o texto que será "lido" pela a API
  const lerTexto = (escrita) => {
    if (lendo) {
      const synthesis = window.speechSynthesis;
      const utterance = new SpeechSynthesisUtterance(escrita);

      const finalizarLeitura = () => {
        if ("speechSynthesis" in window) {
          synthesis.cancel();
        }
      };

      if (lendo && escrita !== "") {
        if ("speechSynthesis" in window) {
          synthesis.speak(utterance);
        }
      } else {
        finalizarLeitura();
      }

      return () => {
        finalizarLeitura();
      };
    }
  };

  return (
    <Box>
      <Box className="flex">
        <Box className="flex items-center mt-4">
          <Typography
            fontSize={FontConfig.medium}
            onClick={() =>
              lerTexto(
                texts.detalhesProposta.comissao + " " + proposta.forum.nome
              )
            }
          >
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
  lendo = false,
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
          lendo={lendo}
        />
      </Box>
    </Box>
  );
};

// Visualizar o parecer da DG
const ParecerDGOnlyRead = ({ proposta = propostaExample, lendo = false }) => {
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

  // Função que irá setar o texto que será "lido" pela a API
  const lerTexto = (escrita) => {
    if (lendo) {
      const synthesis = window.speechSynthesis;
      const utterance = new SpeechSynthesisUtterance(escrita);

      const finalizarLeitura = () => {
        if ("speechSynthesis" in window) {
          synthesis.cancel();
        }
      };

      if (lendo && escrita !== "") {
        if ("speechSynthesis" in window) {
          synthesis.speak(utterance);
        }
      } else {
        finalizarLeitura();
      }

      return () => {
        finalizarLeitura();
      };
    }
  };

  return (
    <Box>
      <Box className="flex">
        <Box className="flex items-center mt-4">
          <Typography
            fontSize={FontConfig.medium}
            onClick={() => lerTexto(texts.detalhesProposta.direcaoGeral)}
          >
            {texts.detalhesProposta.direcaoGeral}:&nbsp;
          </Typography>
          <Typography
            fontSize={FontConfig.medium}
            fontWeight="bold"
            onClick={() => lerTexto(getParecerDGFomartted(proposta.parecerDG))}
          >
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
  lendo = false,
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

  /** Verifica se o usuário logado pode alterar o status da proposta */
  const userHasAuthority = () => {
    let userCookie = UsuarioService.getUserCookies();
    let user = userCookie.usuario;
    if (proposta.analista.id == user.id) return true;

    return false;
  };

  /** Cria a notificacao da demanda */
  const sendNotification = (propostaAux) => {
    let tipoNotificacao;

    switch (propostaAux.status) {
      case "ASSESSMENT_APROVACAO":
        tipoNotificacao = NotificacaoService.assessmentAnalista;
        break;
      case "BUSINESS_CASE":
        tipoNotificacao = NotificacaoService.businessCaseAnalista;
        break;
      case "CANCELLED":
        tipoNotificacao = NotificacaoService.cancelledAnalista;
        break;
      case "DONE":
        tipoNotificacao = NotificacaoService.doneAnalista;
        break;
      default:
        tipoNotificacao = NotificacaoService.assessmentAnalista;
        break;
    }

    // Criar notificação
    NotificacaoService.post(
      NotificacaoService.createNotificationObject(
        tipoNotificacao,
        JSON.parse(JSON.stringify(propostaAux.demanda)),
        CookieService.getUser().id
      )
    ).catch((error) => {});
  };

  // Função para editar o status da proposta
  const editarStatus = () => {
    if (newStatus == "") {
      // Em teoria isso nunca vai acontecer já que o status é um select box
      console.log("Status não pode ser vazio!");
      return;
    }

    if (!userHasAuthority()) {
      setFeedbackErrorAuthority(true);
      return;
    }

    setConfirmEditStatus(false);

    // Requisição para atualizar a proposta com o novo status
    PropostaService.atualizarStatus(proposta.id, newStatus).then((response) => {
      setProposta({ ...proposta, status: response.status });

      // Criar notificação
      sendNotification(
        JSON.parse(JSON.stringify({ ...proposta, status: response.status }))
      );

      // Salvamento de histórico
      ExportPdfService.exportProposta(response.id).then((file) => {
        let arquivo = new Blob([file], { type: "application/pdf" });
        PropostaService.addHistorico(
          response.id,
          "Status Editado para " + getStatusFormatted(newStatus),
          arquivo,
          CookieService.getUser().id
        ).then(() => {});
      });
    });
  };

  /** Retorna o texto do status recebido */
  const getStatusFormatted = (status) => {
    switch (status) {
      case "ASSESSMENT_APROVACAO": //#F7DC6F
        return texts.detalhesProposta.status.assessmentAprovacao;
      case "ASSESSMENT_EDICAO": //#F7DC6F
        return texts.detalhesProposta.status.assessmentAprovacao;
      case "ASSESSMENT_COMISSAO": //#F7DC6F
        return texts.detalhesProposta.status.assessmentAprovacao;
      case "ASSESSMENT_DG": //#F7DC6F
        return texts.detalhesProposta.status.assessmentAprovacao;
      case "BUSINESS_CASE": // #C8CA5F
        return texts.detalhesProposta.status.businessCase;
      case "CANCELLED": //#DA0303
        return texts.detalhesProposta.status.cancelled;
      case "DONE": //#62A265
        return texts.detalhesProposta.status.done;
      default:
        return "";
    }
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
      />
      <Feedback
        open={feedbackStatusError}
        handleClose={() => setFeedbackStatusError(false)}
        status={"erro"}
        mensagem={texts.detalhesProposta.mesmoStatus}
        lendo={lendo}
      />
      <ModalConfirmacao
        open={confirmEditStatus}
        setOpen={setConfirmEditStatus}
        textoModal={"alterarStatusProposta"}
        textoBotao={"sim"}
        onConfirmClick={editarStatus}
        onCancelClick={() => {}}
        lendo={lendo}
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

      <Tooltip title={getStatusFormatted(proposta.status)}>
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
