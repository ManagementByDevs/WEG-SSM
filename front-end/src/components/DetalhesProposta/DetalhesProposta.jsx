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

import ClipLoader from "react-spinners/ClipLoader";

import LogoWEG from "../../assets/logo-weg.png";

import DownloadIcon from "@mui/icons-material/Download";
import EditIcon from "@mui/icons-material/Edit";
import EditOffIcon from "@mui/icons-material/EditOff";

import ModalConfirmacao from "../Modais/Modal-confirmacao/ModalConfirmacao";
import CaixaTextoQuill from "../CaixaTextoQuill/CaixaTextoQuill";
import DetalhesPropostaEditMode from "../DetalhesPropostaEditMode/DetalhesPropostaEditMode";
import Feedback from "../Feedback/Feedback";
import ModalRecusarDemanda from "../Modais/Modal-recusarDemanda/ModalRecusarDemanda";

import FontContext from "../../service/FontContext";
import DateService from "../../service/dateService";
import TextLanguageContext from "../../service/TextLanguageContext";
import EntitiesObjectService from "../../service/entitiesObjectService";
import PropostaService from "../../service/propostaService";
import UsuarioService from "../../service/usuarioService";
import CookieService from "../../service/cookieService";
import ExportPdfService from "../../service/exportPdfService";
import NotificacaoService from "../../service/notificacaoService";
import { WebSocketContext } from "../../service/WebSocketService";
import SpeechSynthesisContext from "../../service/SpeechSynthesisContext";

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
  setDadosProposta = () => { },
  setFeedbackEditSuccess = () => { },
  onlyView = false,
}) => {
  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  // Context para ler o texto da tela
  const { lerTexto } = useContext(SpeechSynthesisContext);

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

  /** Função passada para o componente detalhes proposta edit mode */
  const setPropostaNewData = (proposta = EntitiesObjectService.proposta()) => {
    setFeedbackEditSuccess(true);
    setProposta({ ...proposta, beneficios: receberBeneficios(proposta.beneficios) });
  };

  /** Função acionada quando o usúario clica no ícone de editar */
  const handleOnEditClick = () => {
    setIsEditing(!isEditing);
  };

  /** Função da edição de status da proposta  */
  const editProposta = (proposal) => {
    setFeedbackEditSuccess(true);
    setProposta(proposal);
  };

  // Verificação para ver se o escopo está vazio
  const isEscopoVazio = () => {
    return (
      proposta.escopo == "" ||
      proposta.escopo == null ||
      proposta.escopo == "<p><br></p>" ||
      proposta.escopo == "<p><br/></p>"
    );
  };

  // useEffect para ver se a proposta está sendo alterada ou foi alterada
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
  }, [proposta, isEditing]);

  useEffect(() => {
    // Buscando os dados da proposta usando o propostaId
    PropostaService.getById(propostaId).then((proposal) => {
      // Arrumando alguns textos
      setProposta({ ...proposal, beneficios: receberBeneficios(proposal.beneficios) });
      setIsLoading(false);
    });
  }, []);

  /** Função para receber os benefícios do banco de dados, adicionando o atributo "visible" neles */
  const receberBeneficios = (listaBeneficios) => {
    let listaNova = [];
    for (let beneficio of listaBeneficios) {
      listaNova.push({ ...beneficio, visible: true, valor_mensal: formatarValorMensal(beneficio.valor_mensal) });
    }
    return listaNova;
  };

  /** Função para formatar o valor mensal de um benefício ao recebê-lo */
  const formatarValorMensal = (valor_mensal) => {
    if (!valor_mensal) return;
    let valorMensalString = valor_mensal.toString();
    let arrayValor = valorMensalString.split(".");

    if (arrayValor.length == 1) {
      return valor_mensal + ",00"
    } else if (arrayValor[1].length == 1) {
      if (arrayValor[1].charAt(0)) {
        return valor_mensal + "0"
      } else {
        return arrayValor[0] + ",0" + arrayValor[1]
      }
    } else {
      return arrayValor[0] + "," + arrayValor[1]
    }
  }

  // se a proposta estiver vazia, irá retornar um componente vazio
  if (Object.values(proposta).some((value) => value === undefined))
    return <></>;

  // se estiver carregando (buscando os dados), irá retornar o loader
  if (isLoading)
    return (
      <Box className="flex justify-center">
        <Box
          className="flex justify-center border rounded px-10 py-4 border-t-6 relative"
          sx={{ width: "60rem", borderTopColor: "primary.main" }}
        >
          <ClipLoader className="mt-2" color="#00579D" size={110} />
        </Box>
      </Box>
    );

  // se estiver no modo edição, irá retornar o componente de edição
  if (isEditing) {
    return (
      <Box className="flex justify-center">
        <Box
          className="border rounded px-10 py-4 border-t-6 relative"
          sx={{ width: "60rem", borderTopColor: "primary.main" }}
        >
          <DetalhesPropostaEditMode
            propostaData={proposta}
            setPropostaData={setPropostaNewData}
            setIsEditing={setIsEditing}
            emAprovacao={emAprovacao}
          />
        </Box>
      </Box>
    );
  }

  return (
    <Box className="flex justify-center">
      <Box
        className="border rounded px-10 py-4 border-t-6 relative"
        sx={{ width: "60rem", borderTopColor: "primary.main" }}
        id="primeiro"
      >
        {/* Componente da bandeirinha do status da proposta */}
        {!onlyView && (
          <StatusProposta
            proposta={proposta}
            setProposta={editProposta}
            getCorStatus={getCorStatus}
          />
        )}
        {/* Box header */}
        <Box className="w-full flex justify-between">
          <Box className="flex gap-4">
            {/* Codigo PPM */}
            <Typography
              color="primary"
              fontWeight="bold"
              fontSize={FontConfig.big}
              onClick={() =>
                lerTexto(texts.detalhesProposta.ppm + " " + proposta.codigoPPM)
              }
            >
              {texts.detalhesProposta.ppm} {proposta.codigoPPM}{" "}
            </Typography>
            {/* Data */}
            <Typography
              color="primary"
              fontWeight="bold"
              fontSize={FontConfig.big}
              onClick={() =>
                lerTexto(
                  texts.detalhesProposta.data +
                  " " +
                  DateService.getTodaysDateUSFormat(
                    DateService.getDateByMySQLFormat(proposta.data),
                    texts.linguagem
                  )
                )
              }
            >
              {texts.detalhesProposta.data}{" "}
              {DateService.getTodaysDateUSFormat(
                DateService.getDateByMySQLFormat(proposta.data),
                texts.linguagem
              )}
            </Typography>
            {/* Proposta publicada ou não publicada */}
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
          {/* Logo WEG */}
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
            {!onlyView &&
              proposta.status != "CANCELLED" &&
              proposta.status != "DONE" &&
              proposta.presenteEm != "Ata" && (
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
                    lerTexto(propostaText.current.innerHTML);
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
                {texts.detalhesProposta.problema}:
              </Typography>
              <Box className="mx-4">
                <Typography
                  fontSize={FontConfig.medium}
                  ref={problemaText}
                  onClick={() => {
                    lerTexto(problemaText.current.innerHTML);
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
                    lerTexto(textoEscopo.current.innerHTML);
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
                {proposta.tabelaCustos.length > 0 ? (
                  proposta.tabelaCustos?.map((tabela, index) => {
                    return <TabelaCustos key={index} dados={tabela} />;
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
                    {texts.detalhesProposta.semTabelasDeCusto}
                  </Typography>
                )}
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
              {/* Se não tiver nenhuma BU beneficiada irá aparecer "Sem BU Beneficiada" */}
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
                sx={{ color: "primary.main", textDecoration: "underline" }}
              >
                <a target="_blank" href={proposta.linkJira}>
                  {proposta.linkJira}
                </a>
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
                    DateService.getOnlyDate(
                      DateService.getDateByMySQLFormat(proposta.inicioExecucao),
                      texts.linguagem
                    ) +
                    " " +
                    texts.detalhesProposta.ate +
                    " " +
                    DateService.getOnlyDate(
                      DateService.getDateByMySQLFormat(proposta.fimExecucao),
                      texts.linguagem
                    )
                  )
                }
              >
                {DateService.getOnlyDate(
                  DateService.getDateByMySQLFormat(proposta.inicioExecucao),
                  texts.linguagem
                )}{" "}
                {texts.detalhesProposta.ate}{" "}
                {DateService.getOnlyDate(
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
            {/* Divisor */}
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
                {proposta.motivoRecusa && proposta.status == "CANCELLED" ? (
                  <Box>
                    <Box className="flex items-center mt-4">
                      {/* Label */}
                      <Typography
                        fontSize={FontConfig.medium}
                        onClick={() =>
                          lerTexto(texts.detalhesProposta.semComissao)
                        }
                      >
                        {texts.detalhesProposta.semComissao}
                      </Typography>
                    </Box>
                    {/* Comporta o texto do parecer da comissão */}
                    <Box
                      className="mt-2 mx-4 border-l-2 px-2"
                      sx={{ borderColor: "primary.main" }}
                      onClick={() => lerTexto(proposta.motivoRecusa)}
                    >
                      <Typography fontSize={FontConfig.medium}>
                        {proposta.motivoRecusa}
                      </Typography>
                    </Box>
                  </Box>
                ) : null}

                {/* Parecer da Comissão */}
                <ParecerComissao
                  proposta={proposta}
                  setProposta={setProposta}
                  setDadosProposta={setDadosProposta}
                  parecerComissao={parecerComissao}
                  parecerInformacao={parecerInformacao}
                  emAprovacao={emAprovacao}
                />

                {/* Parecer da Diretoria */}
                {!(proposta.publicada == false) ? (
                  <ParecerDG
                    proposta={proposta}
                    setProposta={setProposta}
                    setDadosProposta={setDadosProposta}
                    parecerDG={parecerDG}
                    parecerInformacaoDG={parecerInformacaoDG}
                    emAprovacao={emAprovacao}
                  />
                ) : null}
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
}) => {
  // Context para obter as configurações de fontes do sistema
  const { FontConfig } = useContext(FontContext);

  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  // Context para ler o texto da tela
  const { lerTexto } = useContext(SpeechSynthesisContext);

  return (
    <Paper className="w-full mt-2 mb-6" square>
      <Table className="table-fixed w-full">
        <TableHead>
          <TableRow sx={{ backgroundColor: "primary.main" }}>
            {/* Tipo da despesa */}
            <th className="text-white p-1">
              <Typography
                fontWeight="bold"
                fontSize={FontConfig.default}
                onClick={() => lerTexto(texts.detalhesProposta.tipoDaDespesa)}
              >
                {texts.detalhesProposta.tipoDaDespesa}
              </Typography>
            </th>
            {/* perfil da despesa */}
            <th className="text-white p-1">
              <Typography
                fontWeight="bold"
                fontSize={FontConfig.default}
                onClick={() => lerTexto(texts.detalhesProposta.perfilDaDespesa)}
              >
                {texts.detalhesProposta.perfilDaDespesa}
              </Typography>
            </th>
            {/* Período de execução */}
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
            {/* Horas */}
            <th className="text-white p-1">
              <Typography
                fontWeight="bold"
                fontSize={FontConfig.default}
                onClick={() => lerTexto(texts.detalhesProposta.horas)}
              >
                {texts.detalhesProposta.horas}
              </Typography>
            </th>
            {/* Valor da hora */}
            <th className="text-white p-1">
              <Typography
                fontWeight="bold"
                fontSize={FontConfig.default}
                onClick={() => lerTexto(texts.detalhesProposta.valorHora)}
              >
                {texts.detalhesProposta.valorHora}
              </Typography>
            </th>
            {/* Total */}
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
        {/* Linha de cada custo */}
        <TableBody>
          {dados.custos.map((custo, index) => {
            return <CustosRow key={index} custo={custo} despesa={dados} />;
          })}
        </TableBody>
      </Table>
      <Box className="mt-2">
        <Table className="table-fixed w-full">
          <TableHead>
            <TableRow sx={{ backgroundColor: "primary.main" }}>
              {/* CCs */}
              <th className="text-white p-1">
                <Typography
                  fontSize={FontConfig.medium}
                  onClick={() => lerTexto(texts.detalhesProposta.ccs)}
                >
                  {texts.detalhesProposta.ccs}
                </Typography>
              </th>
              {/* Porcentagem */}
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
            {/* O que irá aparecer para cada cc */}
            {dados.ccs.map((cc, index) => {
              return (
                <TableRow key={index} className="w-full border rounded">
                  {/* Codigo do CC */}
                  <td className="text-center p-1">
                    <Typography
                      fontSize={FontConfig.medium}
                      onClick={() => lerTexto(cc.codigo)}
                    >
                      {cc.codigo}
                    </Typography>
                  </td>
                  {/* Porcentagem do CC */}
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
}) => {
  // Context para obter as configurações de fonte do sistema
  const { FontConfig } = useContext(FontContext);

  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  // Context para ler o texto da tela
  const { lerTexto } = useContext(SpeechSynthesisContext);

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
      {/* Tipo de despesa */}
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
      {/* Perfil de despesa */}
      <td className="p-2 text-center">
        <Typography
          fontSize={FontConfig.default}
          onClick={() => lerTexto(custo.perfilDespesa)}
        >
          {custo.perfilDespesa}
        </Typography>
      </td>
      {/* Período de execução */}
      <td className="p-2 text-center">
        <Typography
          fontSize={FontConfig.default}
          onClick={() => lerTexto(custo.periodoExecucao)}
        >
          {custo.periodoExecucao}
        </Typography>
      </td>
      {/* Horas */}
      <td className="p-2 text-center">
        <Typography
          fontSize={FontConfig.default}
          onClick={() => lerTexto(custo.horas)}
        >
          {custo.horas}
        </Typography>
      </td>
      {/* Valor da hora */}
      <td className="p-2 text-center">
        <Typography
          fontSize={FontConfig.default}
          onClick={() => lerTexto(getValorFormatted(custo.valorHora))}
        >
          {getValorFormatted(custo.valorHora)}
        </Typography>
      </td>
      {/* Valor total */}
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
const Beneficio = ({ beneficio = EntitiesObjectService.beneficio() }) => {
  // Context para obter as configurações de fonte do sistema
  const { FontConfig } = useContext(FontContext);

  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  // Context para ler o texto da tela
  const { lerTexto } = useContext(SpeechSynthesisContext);

  // Estado se é um beneficio com tipo qualitativo
  const [isQualitativo, setIsQualitativo] = useState(false);

  // Variável utilizada para armazenar a memória de cálculo em html
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

  // Verifica se o benefício tem id, se não retorna null
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
            {/* Tipo do beneficio */}
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
            {/* Se o benefício for qualitativo, não mostrar os campos de valor mensal e moeda */}
            {!isQualitativo && (
              <>
                {/* Valor mensal */}
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
                {/* Moeda */}
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
            {/* Memoria de calculo */}
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
            {/* Tipo do beneficio */}
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
            {/* Se o benefício for qualitativo, não mostrar os campos de valor mensal e moeda */}
            {!isQualitativo && (
              <>
                {/* valor mensal */}
                <td className="text-center p-1">
                  {beneficio.moeda == "Real" ? (
                    <Typography
                      fontSize={FontConfig.medium}
                      color="text.primary"
                      onClick={() => {
                        lerTexto(beneficio.valor_mensal);
                      }}
                    >
                      R$ {beneficio.valor_mensal}
                    </Typography>
                  ) : beneficio.moeda == "Dolar" ? (
                    <Typography
                      fontSize={FontConfig.medium}
                      color="text.primary"
                      onClick={() => {
                        lerTexto(beneficio.valor_mensal);
                      }}
                    >
                      $ {beneficio.valor_mensal}
                    </Typography>
                  ) : beneficio.moeda == "Euro" ? (
                    <Typography
                      fontSize={FontConfig.medium}
                      color="text.primary"
                      onClick={() => {
                        lerTexto(beneficio.valor_mensal);
                      }}
                    >
                      {beneficio.valor_mensal} €
                    </Typography>
                  ) : null}
                </td>

                {/* Moeda */}
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
            {/* Memoria de calculo */}
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
  setProposta = () => { },
  setDadosProposta = () => { },
  parecerComissao = "",
  parecerInformacao = "",
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
      />
    );
  return <ParecerComissaoOnlyRead proposta={proposta} />;
};

// Chamar o parecer da DG
const ParecerDG = ({
  proposta = propostaExample,
  setProposta = () => { },
  setDadosProposta = () => { },
  parecerDG = "",
  parecerInformacaoDG = "",
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
      />
    );
  return <ParecerDGOnlyRead proposta={proposta} />;
};

// Escrever o parecer da comissão
const ParecerComissaoInsertText = ({
  proposta = propostaExample,
  setProposta = () => { },
  setDadosProposta = () => { },
  parecerComissao = "",
  parecerInformacao = "",
}) => {
  // Context para obter as configurações de fontes do sistema
  const { FontConfig } = useContext(FontContext);

  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  // Context para ler o texto da tela
  const { lerTexto } = useContext(SpeechSynthesisContext);

  // Assim que é dado o parecer da comissao é setado na proposta
  const handleOnParecerComissaoChange = (texto) => {
    setProposta({ ...proposta, parecerInformacao: texto });
    setDadosProposta({ ...proposta, parecerInformacao: texto });
  };

  return (
    <Box>
      <Box className="flex">
        <Box className="flex items-center mt-4">
          {/* Nome do forum */}
          <Typography
            fontSize={FontConfig.medium}
            onClick={() =>
              lerTexto(
                texts.detalhesProposta.comissao +
                ": " +
                proposta.forum.nomeForum
              )
            }
          >
            {texts.detalhesProposta.comissao}: {proposta.forum.nomeForum}
            :&nbsp;
          </Typography>
        </Box>
        {/* Parecer da comissão */}
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
        </TextField>
      </Box>
      <Box className="mt-4">
        <CaixaTextoQuill
          texto={parecerInformacao}
          onChange={handleOnParecerComissaoChange}
          label="parecerComissao"
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

  // Context para ler o texto da tela
  const { lerTexto } = useContext(SpeechSynthesisContext);

  // Variável para armazenar as informações do parecer da comissão
  const parecerComissaoInformacoesBox = useRef(null);

  // useEffect para atualizar o texto do parecer da comissão
  useEffect(() => {
    if (parecerComissaoInformacoesBox.current) {
      parecerComissaoInformacoesBox.current.innerHTML =
        proposta.parecerInformacao == null ||
          proposta.parecerInformacao == "null"
          ? texts.detalhesProposta.semInformacoesAdicionais
          : proposta.parecerInformacao;
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
    }
  };

  return (
    <Box>
      <Box className="flex">
        <Box className="flex items-center mt-4">
          {/* nome do forum */}
          <Typography
            fontSize={FontConfig.medium}
            onClick={() =>
              lerTexto(
                texts.detalhesProposta.comissao +
                ": " +
                proposta.forum.nomeForum
              )
            }
          >
            {texts.detalhesProposta.comissao} : {proposta.forum.nomeForum}
            :&nbsp;
          </Typography>
          {/* Parecer da comissao */}
          <Typography
            fontSize={FontConfig.medium}
            fontWeight="bold"
            onClick={() =>
              lerTexto(getParecerComissaoFomartted(proposta.parecerComissao))
            }
          >
            {getParecerComissaoFomartted(proposta.parecerComissao)}
          </Typography>
        </Box>
      </Box>
      {/* Comporta o texto do parecer da comissão */}
      <Box
        ref={parecerComissaoInformacoesBox}
        className="mt-2 mx-4 border-l-2 px-2"
        sx={{ borderColor: "primary.main" }}
        onClick={() =>
          lerTexto(parecerComissaoInformacoesBox.current.innerText)
        }
      />
    </Box>
  );
};

// Escrever o parecer da DG
const ParecerDGInsertText = ({
  proposta = propostaExample,
  setProposta = () => { },
  setDadosProposta = () => { },
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
        {/* DG */}
        <Box className="flex items-center mt-4">
          <Typography>{texts.detalhesProposta.direcaoGeral}: &nbsp;</Typography>
        </Box>
        {/* Parecer da DG */}
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
          label="parecerDG"
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

  // Context para ler o texto da tela
  const { lerTexto } = useContext(SpeechSynthesisContext);

  // Variável para armazenar o parecer da DG
  const parecerDGInformacoesBox = useRef(null);

  // UseEffect utilizado para armazenar o valor do parecer da dg
  useEffect(() => {
    if (parecerDGInformacoesBox.current) {
      parecerDGInformacoesBox.current.innerHTML =
        proposta.parecerInformacaoDG == null ||
          proposta.parecerInformacaoDG == "null"
          ? texts.detalhesProposta.semInformacoesAdicionais
          : proposta.parecerInformacaoDG;
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
          {/* DG */}
          <Typography
            fontSize={FontConfig.medium}
            onClick={() => lerTexto(texts.detalhesProposta.direcaoGeral)}
          >
            {texts.detalhesProposta.direcaoGeral}:&nbsp;
          </Typography>
          {/* Parecer da DG */}
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
        onClick={() => lerTexto(parecerDGInformacoesBox.current.innerText)}
      />
    </Box>
  );
};

// Status da Proposta
const StatusProposta = ({
  proposta = propostaExample,
  setProposta = () => { },
  getCorStatus = () => { },
}) => {
  /**  Context do WebSocket */
  const { enviar } = useContext(WebSocketContext);

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

  // Estado do modal de informar motivo de recusa da proposta, caso seja selecionado o status "Cancelled"
  const [modalRecusarProposta, setModalRecusarProposta] = useState(false);

  // Variável armazenando o motivo da recusa caso a proposta seja reprovada
  const [motivoRecusa, setMotivoRecusa] = useState("");

  // Estado do novo status
  const [newStatus, setNewStatus] = useState("");

  // Estado para a controlar o feedback de erro de autoridade
  const [feedbackErrorAuthority, setFeedbackErrorAuthority] = useState(false);

  // Estado para controlar o feedback de erro de mesmo status selecionado
  const [feedbackStatusError, setFeedbackStatusError] = useState(false);

  // Abre o modal para alterar o status da proposta
  const handleOpenModalStatus = () => {
    if (proposta.status != "CANCELLED") {
      setModalStatus(true);
    }
  };

  // Fecha o modal para alterar o status da proposta
  const handleCloseModalStataus = () => {
    setModalStatus(false);
  };

  // Abre o menu de alterar status
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

  /** Função para criar e retornar um objeto de histórico para salvamento */
  const retornaObjetoHistorico = (acaoRealizada) => {
    const historico = {
      data: new Date(),
      acaoRealizada: acaoRealizada,
      autor: { id: CookieService.getUser().id },
      informacaoAdicional: motivoRecusa && newStatus == "CANCELLED" ? motivoRecusa : null
    };
    return historico;
  };

  // Função para editar o status da proposta
  const editarStatus = () => {
    if (newStatus == "") {
      return;
    }

    if (!userHasAuthority()) {
      setFeedbackErrorAuthority(true);
      return;
    }

    setConfirmEditStatus(false);

    // Requisição para atualizar a proposta com o novo status
    PropostaService.atualizarStatus(proposta.id, newStatus, motivoRecusa).then(
      (response) => {
        setProposta({
          ...proposta,
          status: response.status,
          motivoRecusa: motivoRecusa,
        });

        // Criar notificação
        sendNotification(
          JSON.parse(JSON.stringify({ ...proposta, status: response.status }))
        );

        // Salvamento de histórico
        ExportPdfService.exportProposta(response.id).then((file) => {
          let arquivo = new Blob([file], { type: "application/pdf" });
          PropostaService.addHistorico(
            response.id,
            retornaObjetoHistorico("Status Editado para " + getStatusFormatted(newStatus)),
            arquivo,
          ).then(() => { });
        });
      }
    );
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
      {/* Feedback de erro Authority */}
      <Feedback
        open={feedbackErrorAuthority}
        handleClose={() => setFeedbackErrorAuthority(false)}
        status={"erro"}
        mensagem={texts.detalhesProposta.feedbackErrorAuthority}
      />
      {/* Feedback de erro mesmo status */}
      <Feedback
        open={feedbackStatusError}
        handleClose={() => setFeedbackStatusError(false)}
        status={"erro"}
        mensagem={texts.detalhesProposta.mesmoStatus}
      />
      {/* Modal de confirmação de alteração de status */}
      <ModalConfirmacao
        open={confirmEditStatus}
        setOpen={setConfirmEditStatus}
        textoModal={"alterarStatusProposta"}
        textoBotao={"sim"}
        onConfirmClick={editarStatus}
        onCancelClick={() => { }}
      />
      <ModalRecusarDemanda
        open={modalRecusarProposta}
        setOpen={setModalRecusarProposta}
        handleClose={() => {
          setModalRecusarProposta(false);
          setMotivoRecusa("");
        }}
        confirmRecusarDemanda={() => {
          setModalRecusarProposta(false);
          confirmSelectStatus("CANCELLED");
        }}
        motivo={motivoRecusa}
        setMotivo={setMotivoRecusa}
      />
      {/* Menu para a alteração de status */}
      <Menu
        id="basic-menu"
        anchorEl={anchorElModalStatus}
        open={modalStatus}
        onClose={handleCloseModalStataus}
        MenuListProps={{
          "aria-labelledby": "basic-button",
        }}
      >
        {/* Status de ASSESSMENT */}
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
        {/* Status de BUSINESS CASE */}
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
        {/* Status de CANCELLED */}
        <MenuItem
          className="gap-2"
          onClick={() => setModalRecusarProposta(true)}
        >
          <Box
            className="w-4 h-4 rounded"
            sx={{ backgroundColor: getCorStatus("CANCELLED") }}
          />
          <Typography fontSize={FontConfig.default}>
            {texts.detalhesProposta.statusText.cancelled}
          </Typography>
        </MenuItem>
        {/* Status de DONE */}
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
