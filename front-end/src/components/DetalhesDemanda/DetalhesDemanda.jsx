import React, { useState, useContext, useEffect, useRef } from "react";
import { useNavigate } from "react-router-dom";

import {
  Box,
  Typography,
  Button,
  Divider,
  TextareaAutosize,
  Paper,
  IconButton,
  Tooltip,
} from "@mui/material";

import ModeEditOutlineOutlinedIcon from "@mui/icons-material/ModeEditOutlineOutlined";
import EditOffOutlinedIcon from "@mui/icons-material/EditOffOutlined";
import AddCircleOutlineOutlinedIcon from "@mui/icons-material/AddCircleOutlineOutlined";
import CloseIcon from "@mui/icons-material/Close";
import DownloadIcon from "@mui/icons-material/Download";

import BeneficiosDetalheDemanda from "../../components/BeneficiosDetalheDemanda/BeneficiosDetalheDemanda";
import ModalConfirmacao from "../../components/ModalConfirmacao/ModalConfirmacao";
import ModalAceitarDemanda from "../../components/ModalAceitarDemanda/ModalAceitarDemanda";
import ModalRecusarDemanda from "../ModalRecusarDemanda/ModalRecusarDemanda";
import Feedback from "../Feedback/Feedback";

import TextLanguageContext from "../../service/TextLanguageContext";
import ColorModeContext from "../../service/TemaContext";
import BeneficioService from "../../service/beneficioService";
import DemandaService from "../../service/demandaService";
import AnexoService from "../../service/anexoService";
import NotificacaoService from "../../service/notificacaoService";
import ExportPdfService from "../../service/exportPdfService";
import FontContext from "../../service/FontContext";

// Componente para mostrar os detalhes de uma demanda e suas respectivas funções
const DetalhesDemanda = (props) => {
  // Contexto para trocar a linguagem
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // Navigate utilizado para navegar para outras páginas
  const navigate = useNavigate();

  // Variável hexadecimal para alterar a cor de certos componentes ao mudar o tema da página
  const [corFundoTextArea, setCorFundoTextArea] = useState("#FFFF");

  // Variável armazenando o tema da página (light / dark)
  const { temaPagina } = useContext(ColorModeContext);
  const inputFile = useRef(null);

  // UseStates para armazenar os dados de demanda
  const [tituloDemanda, setTituloDemanda] = useState(props.dados.titulo);
  const [problema, setProblema] = useState(props.dados.problema);
  const [proposta, setProposta] = useState(props.dados.proposta);
  const [frequencia, setFrequencia] = useState(props.dados.frequencia);
  const [beneficios, setBeneficios] = useState(null);
  const [beneficiosNovos, setBeneficiosNovos] = useState([]);
  const [beneficiosExcluidos, setBeneficiosExcluidos] = useState([]);
  const [anexosDemanda, setAnexosDemanda] = useState(props.dados.anexo);

  // Verificar se a demanda está em modo de edição
  const [demandaEmEdicao, setDemandaEmEdicao] = useState(false);

  // UseState do modal de aceitar demanda
  const [openModalAceitarDemanda, setOpenModalAceitarDemanda] = useState(false);
  const [openModalRecusa, setOpenModalRecusa] = useState(false);

  // modal para a confirmação da aprovação da demanda
  const [modalAprovarDemanda, setModalAprovarDemanda] = useState(false);

  // UseState para armazenar o motivo da recusa da demanda
  const [motivoRecusaDemanda, setMotivoRecusaDemanda] = useState("");

  // UseState para exibir o modal de recusa da demanda
  const [modoModalRecusa, setModoModalRecusa] = useState("recusa");

  // UseState para permitir edição na demanda
  const [editar, setEditar] = useState(false);

  // UseState para exibir o modal de confirmação de edição da demanda
  const [openModal, setOpenModal] = useState(false);

  // Feedback caso o usuário coloque um nome de anexo com mesmo nome de outro anexo
  const [feedbackComAnexoMesmoNome, setFeedbackComAnexoMesmoNome] =
    useState(false);

  // Feedback caso o usuário tente salvar a demanda sem ter feito nenhuma alteração
  const [feedbackFacaAlteracao, setFeedbackFacaAlteracao] = useState(false);

  // UseEffect para atualizar a variável "corFundoTextArea" quando o tema da página for modificado
  useEffect(() => {
    temaPagina === "dark"
      ? setCorFundoTextArea("#212121")
      : setCorFundoTextArea("#FFFFFF");
  }, [temaPagina]);

  // UseEffect para atualizar os dados da demanda quando o componente for montado
  useEffect(() => {
    setTituloDemanda(props.dados.titulo);
    setProblema(props.dados.problema);
    setProposta(props.dados.proposta);
    setFrequencia(props.dados.frequencia);
    setBeneficios(formatarBeneficios(props.dados.beneficios));
    setAnexosDemanda(props.dados.anexo);
  }, [props.dados]);

  // ----------------------------------------------------------------------------------------------------------------------------
  // Funções de edição da demanda

  // UseEffect para setar a variável "editar" para false quando o usuário sair da página de edição da demanda
  useEffect(() => {
    if (!props.edicao) setEditar(false);
  }, [props.edicao]);

  // Função para editar a demanda
  function editarDemanda() {
    if (editar) {
      setOpenModal(true);
    } else {
      setEditar(true);
      if (props.setEdicao) {
        props.setEdicao(true);
      }
    }
  }

  // Função de cancelamento da edição da demanda, retornando os dados ao salvamento anterior
  function resetarTextoInput() {
    excluirBeneficiosAdicionados();
    setEditar(false);
    setTituloDemanda(props.dados.titulo);
    setProblema(props.dados.problema);
    setProposta(props.dados.proposta);
    setFrequencia(props.dados.frequencia);
    setBeneficios(formatarBeneficios(props.dados.beneficios));
    setAnexosDemanda(props.dados.anexo);
  }

  // Função para formatar os benefícios recebidos do banco para a lista de benefícios na página
  const formatarBeneficios = (listaBeneficios) => {
    const aux = listaBeneficios.map((beneficio) => {
      return {
        id: beneficio.id,
        tipoBeneficio:
          beneficio.tipoBeneficio?.charAt(0) +
          beneficio.tipoBeneficio
            ?.substring(1, beneficio.tipoBeneficio?.length)
            ?.toLowerCase() || texts.DetalhesDemanda.real,
        valor_mensal: beneficio.valor_mensal,
        moeda: beneficio.moeda,
        memoriaCalculo: beneficio.memoriaCalculo,
        visible: true,
      };
    });
    return aux;
  };

  // Função para formatar os benefícios do front-end para enviá-los ao salvamento da demanda
  const formatarBeneficiosRequisicao = (listaBeneficios) => {
    let listaNova = [];
    for (let beneficio of listaBeneficios) {
      if (beneficio.visible) {
        listaNova.push({
          id: beneficio.id,
          memoriaCalculo: beneficio.memoriaCalculo,
          moeda: beneficio.moeda,
          valor_mensal: beneficio.valor_mensal,
          tipoBeneficio: beneficio.tipoBeneficio.toUpperCase(),
        });
      }
    }
    return listaNova;
  };

  // Função para alterar o texto de algum dos campos da demanda
  const alterarTexto = (e, input) => {
    if (input === "titulo") {
      setTituloDemanda(e.target.value);
    } else if (input === "problema") {
      setProblema(e.target.value);
    } else if (input === "proposta") {
      setProposta(e.target.value);
    } else if (input === "frequencia") {
      setFrequencia(e.target.value);
    }
  };

  // Aciona o input de anexos ao clicar no add anexos
  const onAddAnexoButtonClick = () => {
    inputFile.current.click();
  };

  // Coloca o arquivo selecionado no input no state de anexos
  const onFilesSelect = () => {
    for (let file of inputFile.current.files) {
      if (!existsInAnexos(file)) {
        updateAnexosNovos(file);
        setAnexosDemanda([...anexosDemanda, file]);
      } else {
        // feedback de anexo já existente
        setFeedbackComAnexoMesmoNome(true);
      }
    }
  };

  // Função para verificar se um anexo já existe na lista de anexos
  const existsInAnexos = (anexo) => {
    return (
      anexosDemanda.filter((anexoItem) => {
        return (
          anexoItem.nome == anexo.name ||
          (anexo.id && anexoItem.nome == anexo.nome)
        );
      }).length > 0
    );
  };

  // Função para remover um anexo da lista de anexos
  const removerAnexo = (index) => {
    updateAnexosRemovidos(index);
    removeAnexosNovos(anexosDemanda[index]);
    let aux = [...anexosDemanda];
    aux.splice(index, 1);
    setAnexosDemanda(aux);
  };

  // Função que cria um benefício no banco e usa o id nele em um objeto novo na lista da página
  const adicionarBeneficio = () => {
    BeneficioService.post({
      tipoBeneficio: "",
      valor_mensal: "",
      moeda: "",
      memoriaCalculo: "",
    }).then((response) => {
      let beneficioNovo = {
        id: response.id,
        tipoBeneficio: "",
        valor_mensal: "",
        moeda: "",
        memoriaCalculo: "",
        visible: true,
      };
      setBeneficiosNovos([...beneficiosNovos, beneficioNovo]);
      setBeneficios([...beneficios, beneficioNovo]);
    });
  };

  // Função para atualizar o benefício quando ele recebe alguma alteração
  const alterarTextoBeneficio = (beneficio, index) => {
    let aux = beneficios.map((beneficio) => {
      return {
        id: beneficio.id,
        tipoBeneficio: beneficio.tipoBeneficio,
        valor_mensal: beneficio.valor_mensal,
        moeda: beneficio.moeda,
        memoriaCalculo: beneficio.memoriaCalculo,
        visible: beneficio.visible,
      };
    });
    aux[index] = beneficio;
    setBeneficios(aux);
  };

  // Função para excluir um benefício da lista
  const deleteBeneficio = (indexBeneficio) => {
    let aux = beneficios.map((beneficio) => {
      return {
        id: beneficio.id,
        tipoBeneficio: beneficio.tipoBeneficio,
        valor_mensal: beneficio.valor_mensal,
        moeda: beneficio.moeda,
        memoriaCalculo: beneficio.memoriaCalculo,
        visible: beneficio.visible,
      };
    });
    aux[indexBeneficio].visible = false;
    setBeneficiosExcluidos([...beneficiosExcluidos, aux[indexBeneficio]]);
    setBeneficios(aux);
  };

  // Função para excluir os benefícios que foram criados no banco, porém excluídos da demanda
  const excluirBeneficiosRemovidos = () => {
    for (let beneficio of beneficiosExcluidos) {
      BeneficioService.delete(beneficio.id).then(() => { });
    }
    setBeneficiosExcluidos([]);
  };

  // Função para excluir todos os benefícios adicionados em uma edição caso ela seja cancelada
  const excluirBeneficiosAdicionados = () => {
    for (let beneficio of beneficiosNovos) {
      BeneficioService.delete(beneficio.id).then(() => { });
    }
    setBeneficiosNovos([]);
  };

  // Função inicial da edição da demanda, atualizando os benefícios dela
  const salvarEdicao = () => {
    if (!podeSalvar()) {
      setFeedbackFacaAlteracao(true);
      return null;
    }

    let listaBeneficiosFinal = formatarBeneficiosRequisicao(beneficios);
    let contagem = 0;

    if (listaBeneficiosFinal.length > 0) {
      for (let beneficio of formatarBeneficiosRequisicao(beneficios)) {
        BeneficioService.put(beneficio).then((response) => { });
        contagem++;

        if (contagem == listaBeneficiosFinal.length) {
          setDemandaEmEdicao(true);
        }
      }
    } else {
      setDemandaEmEdicao(true);
    }
  };

  // Função que verifica se o usuário adicionou algum benefício
  const checkIfBeneficiosChanged = () => {
    // Se foi Adicionado um novo benefício
    if (beneficiosNovos.length > 0 || beneficiosExcluidos.length > 0)
      return true;

    return !beneficios.every((e, index) => {
      return (
        e.tipoBeneficio.toLowerCase() ==
        props.dados.beneficios[index].tipoBeneficio.toLowerCase() &&
        e.valor_mensal == props.dados.beneficios[index].valor_mensal &&
        e.moeda.toLowerCase() ==
        props.dados.beneficios[index].moeda.toLowerCase() &&
        e.memoriaCalculo.toLowerCase() ==
        props.dados.beneficios[index].memoriaCalculo.toLowerCase()
      );
    });
  };

  /** Função que determina se o usuário pode salvar a demanda ou não, se baseando se ele editou alguma coisa */
  const podeSalvar = () => {
    return (
      checkIfBeneficiosChanged() ||
      tituloDemanda != props.dados.titulo ||
      problema != props.dados.problema ||
      proposta != props.dados.proposta ||
      frequencia != props.dados.frequencia
    );
  };

  // UseEffect ativado quando os benefícios da demanda são atualizados no banco, salvando os outros dados da demanda
  useEffect(() => {
    if (demandaEmEdicao) {
      const demandaAtualizada = {
        id: props.dados.id,
        titulo: tituloDemanda,
        problema: problema,
        proposta: proposta,
        frequencia: frequencia,
        beneficios: formatarBeneficiosRequisicao(beneficios),
        data: props.dados.data,
        status: "BACKLOG_REVISAO",
        solicitante: props.dados.solicitante,
        gerente: props.dados.gerente,
        anexo: props.dados.anexo,
        departamento: props.dados?.departamento,
        analista: props.dados?.analista,
        historicoDemanda: props.dados?.historicoDemanda,
      };

      const anexosVelhos = [];
      for (let anexo of anexosDemanda) {
        if (anexo.id) {
          anexosVelhos.push(
            new File([converterBase64(anexo.dados)], anexo.nome, {
              type: anexo.tipo,
            })
          );
        }
      }

      if (novosAnexos.length > 0) {
        DemandaService.put(demandaAtualizada, [
          ...anexosVelhos,
          ...novosAnexos,
        ]).then((response) => {
          for (let anexo of anexosDemanda) {
            AnexoService.deleteById(anexo.id);
          }
          // atualizar demanda salva no location

          setEditar(false);
          excluirBeneficiosRemovidos();
          setDemandaEmEdicao(false);
          props.setDados(response);
          salvarHistorico("Demanda Editada");
        });
      } else {
        DemandaService.putSemAnexos(demandaAtualizada).then((response) => {
          // atualizar demanda salva no location

          setEditar(false);
          excluirBeneficiosRemovidos();
          setDemandaEmEdicao(false);
          props.setDados(response);
          salvarHistorico("Demanda Editada");
        });
      }
    }

    if (props.dados.id) {
      DemandaService.getById(props.dados.id).then((res) => {
        props.updateDemandaProps(res);
      });
    }

    if (anexosRemovidos.length > 0) {
      for (let anexoRemovido of anexosRemovidos) {
        AnexoService.deleteById(anexoRemovido.id);
      }
    }
  }, [demandaEmEdicao]);

  // Const para retornar uma lista das BUs beneficiadas em forma de texto
  const retornarBUsBeneficiadas = () => {
    let textoFinal = "";
    for (const bu of props.dados.busBeneficiadas) {
      textoFinal += bu.siglaBu + ", ";
    }
    textoFinal = textoFinal.substring(0, textoFinal.length - 2);
    return textoFinal;
  };

  // -----------------------------------------------------------------------------------------------------------------------------------
  // Funções de aceite/recusa do analista/gerente

  // Função para fechar o modal de confirmação
  const handleCloseModalAceitarDemanda = () => {
    setOpenModalAceitarDemanda(false);
  };

  // Função para fechar o modal de recusa
  const handleCloseModalRecusar = () => {
    setMotivoRecusaDemanda("");
    setOpenModalRecusa(false);
  };

  // Acionado quando o usuário clicar em "Aceitar" na demanda
  const aceitarDemanda = () => {
    setOpenModalAceitarDemanda(true);
  };

  // Acionado quando o usuário clicar em "Aprovar" na demanda
  const aprovarDemanda = () => {
    setModalAprovarDemanda(true);
  };

  // Função para abrir o modal de recusa
  const abrirRecusaDemanda = (modo) => {
    setModoModalRecusa(modo);
    setOpenModalRecusa(true);
  };

  /** Função para aceitar a demanda como gerente, enviando-a para a criação de proposta */
  const aprovarDemandaGerencia = () => {
    DemandaService.atualizarStatus(props.dados.id, "ASSESSMENT").then(() => {
      salvarHistorico("Demanda Aprovada");
      navegarHome(1);
    });
  };

  // Função acionada quando o usuário clica em "Aceitar" no modal de confirmação
  const confirmAceitarDemanda = (dados) => {
    const demandaAtualizada = {
      id: props.dados.id,
      titulo: props.dados.titulo,
      problema: props.dados.problema,
      proposta: props.dados.proposta,
      frequencia: props.dados.frequencia,
      beneficios: props.dados.beneficios,
      data: props.dados.data,
      status: "BACKLOG_APROVACAO",
      solicitante: props.dados.solicitante,
      tamanho: dados.tamanho,
      secaoTI: dados.secaoTI,
      busBeneficiadas: dados.busBeneficiadas,
      buSolicitante: dados.buSolicitante,
      forum: dados.forum,
      analista: props.usuario,
      gerente: props.dados.gerente,
      departamento: props.dados.departamento,
      historicoDemanda: props.dados.historicoDemanda,
    };

    DemandaService.put(demandaAtualizada, dados.anexos).then(() => {
      salvarHistorico("Demanda Aprovada");
      navegarHome(1);
    });
    NotificacaoService.post(
      NotificacaoService.createNotificationObject(
        NotificacaoService.aprovado,
        props.dados
      )
    );
  };

  /** Função acionada quando o analista recusa uma demanda, tanto devolução quanto reprovação */
  const confirmRecusaDemanda = () => {
    if (!motivoRecusaDemanda) return;
    setOpenModalRecusa(false);
    const status =
      modoModalRecusa === "devolucao" ? "BACKLOG_EDICAO" : "CANCELLED";

    DemandaService.put(
      { ...props.dados, motivoRecusa: motivoRecusaDemanda, status: status },
      []
    ).then(() => {
      const tipoNotificacao =
        modoModalRecusa === "devolucao"
          ? NotificacaoService.maisInformacoes
          : NotificacaoService.reprovado;
      NotificacaoService.post(
        NotificacaoService.createNotificationObject(
          tipoNotificacao,
          props.dados
        )
      );
      salvarHistorico(
        modoModalRecusa === "devolucao"
          ? "Demanda Devolvida"
          : "Demanda Reprovada"
      );
      navegarHome(modoModalRecusa === "devolucao" ? 2 : 3);
    });
  };

  /** Função usada para atualizar o histórico da demanda quando ela for atualizada no banco, recebendo um texto da ação realizada */
  const salvarHistorico = (texto) => {
    ExportPdfService.exportDemanda(props.dados.id).then((file) => {
      let arquivo = new Blob([file], { type: "application/pdf" });
      DemandaService.addHistorico(
        props.dados.id,
        texto,
        arquivo,
        parseInt(localStorage.getItem("usuarioId"))
      );
    });
  };

  /** Função para transformar uma string em base64 para um ArrayBuffer, usada para baixar anexos */
  function converterBase64(base64) {
    const textoBinario = window.atob(base64);
    const bytes = new Uint8Array(textoBinario.length);
    return bytes.map((byte, i) => textoBinario.charCodeAt(i));
  }

  /** Função usada para baixar um anexo */
  const baixarAnexo = (index) => {
    const arquivo = anexosDemanda[index];
    let blob =
      arquivo instanceof File
        ? arquivo
        : new Blob([converterBase64(arquivo.dados)]);
    let nomeArquivo =
      arquivo instanceof File ? arquivo.name : `${arquivo.nome}`;

    if (navigator.msSaveBlob) {
      navigator.msSaveBlob(blob, nomeArquivo);
    } else {
      const link = document.createElement("a");
      if (link.download !== undefined) {
        const url = URL.createObjectURL(blob);
        link.setAttribute("href", url);
        link.setAttribute("download", nomeArquivo);
        link.style.visibility = "hidden";
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
        URL.revokeObjectURL(url);
      }
    }
  };

  /** Novos anexos que serão adicionados na edição da demanda */
  const [novosAnexos, setNovosAnexos] = useState([]);

  /** Anexos que já estavam na demanda e que serão removidos na edição da demanda */
  const [anexosRemovidos, setAnexosRemovidos] = useState([]);

  /** Irá atualizar a lista para que contenha os anexos que foram removidos da demanda e que já estavam salvos no banco de dados */
  const updateAnexosRemovidos = (indexAnexo) => {
    const anexo = anexosDemanda[indexAnexo];
    const anexosFiltrados = anexosDemanda.filter(
      (anexoItem) => anexo.id && anexoItem.id == anexo.id
    );
    setAnexosRemovidos([...anexosRemovidos, ...anexosFiltrados]);
  };

  /** Função que verifica se um determinado anexo já existe na lista provida */
  const existsInArray = (array, anexo) => {
    return array.some((anexoItem) => anexoItem.name === anexo.name);
  };

  /** Irá atualizar a lista para que contenha os anexos que foram adicionados na demanda (somente novos anexos) */
  const updateAnexosNovos = (anexo) => {
    if (existsInArray(novosAnexos, anexo)) return;
    setNovosAnexos([...novosAnexos, anexo]);
  };

  /** Função que remove um anexo da lista anexosNovos caso o usuário o remova, 
  só é removido anexos que não estavam salvos no banco de dados */
  const removeAnexosNovos = (anexo) => {
    setNovosAnexos(
      novosAnexos.filter((anexoItem) => {
        return anexoItem.name != anexo.name && !anexoItem.id;
      })
    );
  };

  /** Função usada para navegar à home salvando um valor de feedback no localStorage */
  const navegarHome = (tipoFeedback) => {
    switch (tipoFeedback) {
      case 1:
        localStorage.setItem("tipoFeedback", "2");
        break;
      case 2:
        localStorage.setItem("tipoFeedback", "3");
        break;
      case 3:
        localStorage.setItem("tipoFeedback", "4");
        break;
    }
    navigate("/");
  };

  return (
    <Box className="flex flex-col justify-center relative items-center mt-10">
      <Feedback
        open={feedbackFacaAlteracao}
        handleClose={() => setFeedbackFacaAlteracao(false)}
        status={"erro"}
        mensagem={texts.DetalhesDemanda.facaAlgumaAlteracaoParaPoderSalvar}
      />
      <Feedback
        open={feedbackComAnexoMesmoNome}
        handleClose={() => setFeedbackComAnexoMesmoNome(false)}
        status={"erro"}
        mensagem={texts.DetalhesDemanda.jaHaUmAnexoComEsseNome}
      />
      <ModalAceitarDemanda
        open={openModalAceitarDemanda}
        setOpen={setOpenModalAceitarDemanda}
        handleClose={handleCloseModalAceitarDemanda}
        confirmAceitarDemanda={confirmAceitarDemanda}
      />
      <ModalRecusarDemanda
        open={openModalRecusa}
        setOpen={setOpenModalRecusa}
        handleClose={handleCloseModalRecusar}
        confirmRecusarDemanda={confirmRecusaDemanda}
        motivo={motivoRecusaDemanda}
        setMotivo={setMotivoRecusaDemanda}
      />
      <ModalConfirmacao
        open={openModal}
        setOpen={setOpenModal}
        onConfirmClick={resetarTextoInput}
        onCancelClick={setEditar}
        textoModal="cancelarEdicao"
        textoBotao="sim"
      />
      <ModalConfirmacao
        open={modalAprovarDemanda}
        setOpen={setModalAprovarDemanda}
        onConfirmClick={aprovarDemandaGerencia}
        textoModal="aceitarDemanda"
        textoBotao="aceitar"
      />
      <Box
        id="primeiro"
        className="flex flex-col gap-5 border rounded relative p-10 drop-shadow-lg"
        sx={{ width: "55rem" }}
      >
        <Box
          className="absolute cursor-pointer"
          sx={{ top: "10px", right: "10px" }}
          onClick={editarDemanda}
        >
          {props.usuario?.id == props.dados.solicitante?.id &&
            props.dados.status == "BACKLOG_EDICAO" &&
            !editar ? (
            <ModeEditOutlineOutlinedIcon
              id="terceiro"
              fontSize="large"
              className="delay-120 hover:scale-110 duration-300"
              sx={{ color: "icon.main" }}
            />
          ) : null}
          {props.usuario?.id == props.dados.solicitante?.id &&
            props.dados.status == "BACKLOG_EDICAO" &&
            editar ? (
            <EditOffOutlinedIcon
              fontSize="large"
              className="delay-120 hover:scale-110 duration-300"
              sx={{ color: "icon.main" }}
            />
          ) : null}
        </Box>
        {!editar ? (
          <>
            <Box className="flex justify-center">
              <Typography
                fontSize={FontConfig.title}
                sx={{
                  fontWeight: "600",
                  cursor: "default",
                  inlineSize: "800px",
                  overflowWrap: "break-word",
                }}
                color="primary.main"
              >
                {props.dados.titulo}
              </Typography>
            </Box>
            <Divider />
            <Box>
              <Typography
                fontSize={FontConfig.veryBig}
                fontWeight="600"
                color="text.primary"
              >
                {texts.DetalhesDemanda.problema}:
              </Typography>
              <Typography
                fontSize={FontConfig.medium}
                className="text-justify"
                color="text.secondary"
                sx={{ marginLeft: "30px" }}
              >
                {props.dados.problema}
              </Typography>
            </Box>
            <Box>
              <Typography
                fontSize={FontConfig.veryBig}
                fontWeight="600"
                color="text.primary"
              >
                {texts.DetalhesDemanda.proposta}:
              </Typography>
              <Typography
                fontSize={FontConfig.medium}
                className="text-justify"
                color="text.secondary"
                sx={{ marginLeft: "30px" }}
              >
                {props.dados.proposta}
              </Typography>
            </Box>
            <Box>
              <Box>
                <Typography
                  fontSize={FontConfig.veryBig}
                  fontWeight="600"
                  color="text.primary"
                >
                  {texts.DetalhesDemanda.beneficios}:
                </Typography>
              </Box>
              <Box className="mt-2 flex flex-col gap-5">
                {beneficios?.map((beneficio, index) => {
                  if (beneficio.visible) {
                    return (
                      <BeneficiosDetalheDemanda
                        editavel={false}
                        key={index}
                        index={index}
                        beneficio={beneficio}
                      />
                    );
                  }
                })}
              </Box>
            </Box>
            <Box>
              <Typography
                fontSize={FontConfig.veryBig}
                fontWeight="600"
                color="text.primary"
              >
                {texts.DetalhesDemanda.frequenciaDeUso}:
              </Typography>
              <Typography
                fontSize={FontConfig.medium}
                className="text-justify"
                color="text.secondary"
                sx={{ marginLeft: "30px" }}
              >
                {props.dados.frequencia}
              </Typography>
            </Box>
            {props.dados.tamanho && props.dados.secaoTI && (
              <Box className="flex justify-between items-center">
                <Box className="flex items-center">
                  <Typography
                    fontSize={FontConfig.veryBig}
                    fontWeight="600"
                    color="text.primary"
                  >
                    {texts.DetalhesDemanda.tamanho}:
                  </Typography>
                  <Typography
                    fontSize={FontConfig.medium}
                    className="text-justify"
                    color="text.secondary"
                    sx={{ marginLeft: "10px" }}
                  >
                    {props.dados.tamanho}
                  </Typography>
                </Box>
                <Box className="flex items-center">
                  <Typography
                    fontSize={FontConfig.veryBig}
                    fontWeight="600"
                    color="text.primary"
                  >
                    {texts.DetalhesDemanda.secaoDeTi}:
                  </Typography>
                  <Typography
                    fontSize={FontConfig.medium}
                    className="text-justify"
                    color="text.secondary"
                    sx={{ marginLeft: "10px", marginRight: "15px" }}
                  >
                    {props.dados.secaoTI.siglaSecao}
                  </Typography>
                </Box>
              </Box>
            )}
            {props.dados.buSolicitante && (
              <Box className="flex justify-between items-center">
                <Box className="flex items-center">
                  <Typography
                    fontSize={FontConfig.veryBig}
                    fontWeight="600"
                    color="text.primary"
                  >
                    {texts.DetalhesDemanda.buSolicitante}:
                  </Typography>
                  <Typography
                    fontSize={FontConfig.medium}
                    className="text-justify"
                    color="text.secondary"
                    sx={{ marginLeft: "10px" }}
                  >
                    {props.dados.buSolicitante.siglaBu}
                  </Typography>
                </Box>
                <Box className="flex items-center">
                  <Typography
                    fontSize={FontConfig.veryBig}
                    fontWeight="600"
                    color="text.primary"
                  >
                    {texts.DetalhesDemanda.busBeneficiadas}:
                  </Typography>
                  <Typography
                    fontSize={FontConfig.medium}
                    className="text-justify"
                    color="text.secondary"
                    sx={{ marginLeft: "10px", marginRight: "15px" }}
                  >
                    {retornarBUsBeneficiadas()}
                  </Typography>
                </Box>
              </Box>
            )}
            {props.dados.forum && (
              <Box className="flex items-center">
                <Typography
                  fontSize={FontConfig.veryBig}
                  fontWeight="600"
                  color="text.primary"
                >
                  {texts.DetalhesDemanda.forum}:
                </Typography>
                <Typography
                  fontSize={FontConfig.medium}
                  className="text-justify"
                  color="text.secondary"
                  sx={{ marginLeft: "10px" }}
                >
                  {props.dados.forum.siglaForum} - {props.dados.forum.nomeForum}
                </Typography>
              </Box>
            )}
            <Box>
              <Typography
                fontSize={FontConfig.veryBig}
                fontWeight="600"
                color="text.primary"
              >
                {texts.DetalhesDemanda.anexos}:
              </Typography>
              {props.dados.anexo != null && props.dados.anexo.length > 0 ? (
                <Box className="flex flex-col gap-2">
                  {props.dados.anexo.map((anexo, index) => (
                    <Paper
                      key={index}
                      className="flex justify-between items-center"
                      sx={{
                        borderLeftWidth: "4px",
                        borderLeftColor: "primary.main",
                        borderLeftStyle: "solid",
                        backgroundColor: "background.default",
                        padding: "0.2rem 1rem",
                      }}
                      elevation={0}
                    >
                      <Typography
                        sx={{
                          color: "text.primary",
                          fontSize: FontConfig.default,
                        }}
                      >
                        {anexo.nome ? anexo.nome : anexo.name}
                      </Typography>
                      <Tooltip title={texts.DetalhesDemanda.baixar}>
                        <IconButton
                          onClick={() => {
                            baixarAnexo(index);
                          }}
                        >
                          <DownloadIcon sx={{ color: "text.primary" }} />
                        </IconButton>
                      </Tooltip>
                    </Paper>
                  ))}
                </Box>
              ) : (
                <Typography
                  textAlign="center"
                  sx={{ color: "text.primary", fontSize: FontConfig.default }}
                >
                  {texts.DetalhesDemanda.nenhumAnexoAdicionado}
                </Typography>
              )}
            </Box>
          </>
        ) : (
          <>
            <Box className="flex justify-center">
              <Box
                value={tituloDemanda}
                onChange={(e) => {
                  alterarTexto(e, "titulo");
                }}
                fontSize={FontConfig.title}
                color="primary.main"
                className="flex outline-none border-solid border px-1 py-1.5 drop-shadow-sm rounded"
                sx={{
                  width: "100%;",
                  height: "54px",
                  backgroundColor: "background.default",
                  fontWeight: "600",
                }}
                component="input"
                placeholder={texts.DetalhesDemanda.digiteTituloDaDemanda}
              />
            </Box>
            <Divider />
            <Box>
              <Typography
                fontSize={FontConfig.veryBig}
                fontWeight="600"
                color="text.primary"
              >
                {texts.DetalhesDemanda.problema}:
              </Typography>
              <TextareaAutosize
                style={{
                  width: 775,
                  marginLeft: "26px",
                  resize: "none",
                  backgroundColor: corFundoTextArea,
                }}
                value={problema}
                fontSize={FontConfig.medium}
                onChange={(e) => {
                  alterarTexto(e, "problema");
                }}
                className="flex outline-none border-solid border px-1 py-1.5 drop-shadow-sm rounded text-center text-justify"
                placeholder={texts.DetalhesDemanda.digiteProblema}
              />
            </Box>
            <Box>
              <Typography
                fontSize={FontConfig.veryBig}
                fontWeight="600"
                color="text.primary"
              >
                {texts.DetalhesDemanda.proposta}:
              </Typography>
              <TextareaAutosize
                style={{
                  width: 775,
                  marginLeft: "26px",
                  resize: "none",
                  backgroundColor: corFundoTextArea,
                }}
                value={proposta}
                fontSize={FontConfig.medium}
                onChange={(e) => {
                  alterarTexto(e, "proposta");
                }}
                className="flex outline-none border-solid border px-1 py-1.5 drop-shadow-sm rounded text-center text-justify"
                placeholder={texts.DetalhesDemanda.digiteProposta}
              />
            </Box>
            <Box>
              <Box className="flex items-center">
                <Typography
                  fontSize={FontConfig.veryBig}
                  fontWeight="600"
                  color="text.primary"
                >
                  {texts.DetalhesDemanda.beneficios}:
                </Typography>
                <AddCircleOutlineOutlinedIcon
                  className="delay-120 hover:scale-110 duration-300 ml-1"
                  onClick={() => {
                    adicionarBeneficio();
                  }}
                  sx={{ color: "primary.main", cursor: "pointer" }}
                />
              </Box>
              <Box className="mt-2 flex flex-col gap-5">
                {beneficios?.map((beneficio, index) => {
                  if (beneficio.visible) {
                    return (
                      <BeneficiosDetalheDemanda
                        editavel={true}
                        key={index}
                        index={index}
                        delete={deleteBeneficio}
                        beneficio={beneficio}
                        setBeneficio={alterarTextoBeneficio}
                      />
                    );
                  }
                })}
              </Box>
            </Box>
            <Box>
              <Typography
                fontSize={FontConfig.veryBig}
                fontWeight="600"
                color="text.primary"
              >
                {texts.DetalhesDemanda.frequenciaDeUso}:
              </Typography>
              <Box
                value={frequencia}
                onChange={(e) => {
                  alterarTexto(e, "frequencia");
                }}
                fontSize={FontConfig.medium}
                className="outline-none border-solid border px-1 py-1.5 drop-shadow-sm rounded"
                sx={{
                  width: "90%;",
                  backgroundColor: corFundoTextArea,
                  marginLeft: "30px",
                }}
                component="input"
                placeholder={texts.DetalhesDemanda.digiteFrequenciaDeUso}
              />
            </Box>
            <Box>
              <Box className="flex items-center">
                <Typography
                  fontSize={FontConfig.veryBig}
                  fontWeight="600"
                  color="text.primary"
                >
                  {texts.DetalhesDemanda.anexos}:
                </Typography>
                <AddCircleOutlineOutlinedIcon
                  className="delay-120 hover:scale-110 duration-300 ml-1"
                  sx={{ color: "primary.main", cursor: "pointer" }}
                  onClick={onAddAnexoButtonClick}
                />
                <input
                  onChange={onFilesSelect}
                  ref={inputFile}
                  type="file"
                  multiple
                  hidden
                />
              </Box>
              {anexosDemanda.length > 0 ? (
                <Box className="flex flex-col gap-2">
                  {anexosDemanda?.map((anexo, index) => (
                    <Paper
                      key={index}
                      className="flex justify-between items-center"
                      sx={{
                        borderLeftWidth: "4px",
                        borderLeftColor: "primary.main",
                        borderLeftStyle: "solid",
                        backgroundColor: "background.default",
                        padding: "0.2rem 1rem",
                      }}
                      elevation={0}
                    >
                      <Typography
                        sx={{
                          color: "text.primary",
                          fontSize: FontConfig.default,
                        }}
                      >
                        {anexo.nome ? anexo.nome : anexo.name}
                      </Typography>
                      <Box className="flex gap-2">
                        <Tooltip title={texts.DetalhesDemanda.baixar}>
                          <IconButton
                            onClick={() => {
                              baixarAnexo(index);
                            }}
                          >
                            <DownloadIcon sx={{ color: "text.primary" }} />
                          </IconButton>
                        </Tooltip>
                        <Tooltip title={texts.DetalhesDemanda.remover}>
                          <IconButton
                            onClick={() => {
                              removerAnexo(index);
                            }}
                          >
                            <CloseIcon sx={{ color: "text.primary" }} />
                          </IconButton>
                        </Tooltip>
                      </Box>
                    </Paper>
                  ))}
                </Box>
              ) : (
                <Typography
                  textAlign="center"
                  sx={{ color: "text.primary", fontSize: FontConfig.default }}
                >
                  {texts.DetalhesDemanda.nenhumAnexoAdicionado}
                </Typography>
              )}
            </Box>
          </>
        )}
      </Box>
      <Box className="w-full p-10"></Box>
      <Box
        className="flex fixed justify-end"
        sx={{ width: "20rem", bottom: "20px", right: "20px" }}
      >
        {props.usuario?.tipoUsuario == "ANALISTA" &&
          props.botao == "sim" &&
          !editar && props.dados.status == "BACKLOG_REVISAO" && (
            <Box className="flex justify-around w-full">
              <Button
                sx={{
                  backgroundColor: "primary.main",
                  color: "text.white",
                  fontSize: FontConfig.default,
                }}
                variant="contained"
                onClick={() => {
                  abrirRecusaDemanda("recusa");
                }}
              >
                {texts.DetalhesDemanda.botaoRecusar}
              </Button>

              <Button
                sx={{
                  backgroundColor: "primary.main",
                  color: "text.white",
                  fontSize: FontConfig.default,
                }}
                variant="contained"
                onClick={() => {
                  abrirRecusaDemanda("devolucao");
                }}
              >
                {texts.DetalhesDemanda.botaoDevolver}
              </Button>
              <Button
                sx={{
                  backgroundColor: "primary.main",
                  color: "text.white",
                  fontSize: FontConfig.default,
                }}
                variant="contained"
                onClick={aceitarDemanda}
              >
                {texts.DetalhesDemanda.botaoAceitar}
              </Button>
            </Box>
          )}

        {/* caso o usuário seja um gerente */}
        {props.usuario?.tipoUsuario == "GERENTE" &&
          props.botao == "sim" &&
          !editar && (
            <Box className="flex justify-around w-full">
              <Button
                sx={{
                  backgroundColor: "primary.main",
                  color: "text.white",
                  fontSize: FontConfig.default,
                }}
                variant="contained"
                onClick={() => {
                  abrirRecusaDemanda("recusa");
                }}
              >
                {texts.DetalhesDemanda.botaoRecusar}
              </Button>
              <Button
                sx={{
                  backgroundColor: "primary.main",
                  color: "text.white",
                  fontSize: FontConfig.default,
                }}
                variant="contained"
                onClick={aprovarDemanda}
              >
                {texts.DetalhesDemanda.botaoAceitar}
              </Button>
            </Box>
          )}

        {editar && props.salvar && (
          <Button
            sx={{
              backgroundColor: "primary.main",
              color: "text.white",
              fontSize: FontConfig.default,
            }}
            variant="contained"
            onClick={() => {
              salvarEdicao();
            }}
          >
            {texts.DetalhesDemanda.botaoSalvar}
          </Button>
        )}
      </Box>
    </Box>
  );
};

export default DetalhesDemanda;
