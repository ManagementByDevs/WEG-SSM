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

import ColorModeContext from "../../service/TemaContext";
import BeneficioService from "../../service/beneficioService";
import DemandaService from "../../service/demandaService";
import AnexoService from "../../service/anexoService";
import NotificacaoService from "../../service/notificacaoService";

import FontContext from "../../service/FontContext";

const DetalhesDemanda = (props) => {
  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // Variável hexadecimal para alterar a cor de certos componentes ao mudar o tema da página
  const [corFundoTextArea, setCorFundoTextArea] = useState("#FFFF");

  // Variável armazenando o tema da página (light / dark)
  const { temaPagina } = useContext(ColorModeContext);
  const inputFile = useRef(null);

  const [tituloDemanda, setTituloDemanda] = useState(props.dados.titulo);
  const [problema, setProblema] = useState(props.dados.problema);
  const [proposta, setProposta] = useState(props.dados.proposta);
  const [frequencia, setFrequencia] = useState(props.dados.frequencia);

  const [beneficios, setBeneficios] = useState(null);
  const [beneficiosNovos, setBeneficiosNovos] = useState([]);
  const [beneficiosExcluidos, setBeneficiosExcluidos] = useState([]);

  const [demandaEmEdicao, setDemandaEmEdicao] = useState(false);
  const [anexos, setAnexos] = useState(props.dados.anexo);

  // UseState do modal de aceitar demanda
  const [openModalAceitarDemanda, setOpenModalAceitarDemanda] = useState(false);
  const [openModalRecusa, setOpenModalRecusa] = useState(false);

  // modal para a confirmação da aprovação da demanda
  const [modalAprovarDemanda, setModalAprovarDemanda] = useState(false);

  const [motivoRecusaDemanda, setMotivoRecusaDemanda] = useState("");
  const [modoModalRecusa, setModoModalRecusa] = useState("recusa");

  const [editar, setEditar] = useState(false);

  const [openModal, setOpenModal] = useState(false);

  // Feedback caso o usuário coloque um nome de anexo com mesmo nome de outro anexo
  const [feedbackComAnexoMesmoNome, setFeedbackComAnexoMesmoNome] =
    useState(false);

  // Feedback caso o usuário tente salvar a demanda sem ter feito nenhuma alteração
  const [feedbackFacaAlteracao, setFeedbackFacaAlteracao] = useState(false);

  const navigate = useNavigate();

  // UseEffect para atualizar a variável "corFundoTextArea" quando o tema da página for modificado
  useEffect(() => {
    if (temaPagina === "dark") {
      setCorFundoTextArea("#212121");
    } else {
      setCorFundoTextArea("#FFFF");
    }
  }, [temaPagina]);

  useEffect(() => {
    setTituloDemanda(props.dados.titulo);
    setProblema(props.dados.problema);
    setProposta(props.dados.proposta);
    setFrequencia(props.dados.frequencia);
    setBeneficios(formatarBeneficios(props.dados.beneficios));
    setAnexos(props.dados.anexo);
  }, [props.dados]);

  // ----------------------------------------------------------------------------------------------------------------------------
  // Funções de edição da demanda

  useEffect(() => {
    if (!props.edicao) {
      setEditar(false);
    }
  }, [props.edicao]);

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
    setAnexos(props.dados.anexo);
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
              ?.toLowerCase() || "Real",
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
        setAnexos([...anexos, file]);
      } else {
        // feedback de anexo já existente
        setFeedbackComAnexoMesmoNome(true);
      }
    }
  };

  const existsInAnexos = (anexo) => {
    return (
      anexos.filter((anexoItem) => {
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
    removeAnexosNovos(anexos[index]);
    let aux = [...anexos];
    aux.splice(index, 1);
    setAnexos(aux);
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
      BeneficioService.delete(beneficio.id).then((response) => {});
    }
    setBeneficiosExcluidos([]);
  };

  // Função para excluir todos os benefícios adicionados em uma edição caso ela seja cancelada
  const excluirBeneficiosAdicionados = () => {
    for (let beneficio of beneficiosNovos) {
      BeneficioService.delete(beneficio.id).then((response) => {});
    }
    setBeneficiosNovos([]);
  };

  // Função inicial da edição da demanda, atualizando os benefícios dela
  const salvarEdicao = () => {
    if (!canSave()) {
      setFeedbackFacaAlteracao(true);
      return null;
    }
    let listaBeneficiosFinal = formatarBeneficiosRequisicao(beneficios);
    let contagem = 0;

    if (listaBeneficiosFinal.length > 0) {
      for (let beneficio of formatarBeneficiosRequisicao(beneficios)) {
        BeneficioService.put(beneficio).then((response) => {});
        contagem++;

        if (contagem == listaBeneficiosFinal.length) {
          setDemandaEmEdicao(true);
        }
      }
    } else {
      setDemandaEmEdicao(true);
    }
  };

  const checkIfBeneficiosChanged = () => {
    // Se foi Adicionado um novo benefício
    if (beneficiosNovos.length > 0 || beneficiosExcluidos.length > 0) return true;

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

  // Função que determina se o usuário pode salvar a demanda ou não, se baseando se ele editou alguma coisa
  const canSave = () => {
    console.log(
      checkIfBeneficiosChanged(),
      tituloDemanda != props.dados.titulo,
      problema != props.dados.problema,
      proposta != props.dados.proposta,
      frequencia != props.dados.frequencia
    );
    if (
      checkIfBeneficiosChanged() ||
      tituloDemanda != props.dados.titulo ||
      problema != props.dados.problema ||
      proposta != props.dados.proposta ||
      frequencia != props.dados.frequencia
    ) {
      console.log("oi?");
      return true;
    }
    return false;
  };

  // UseEffect ativado quando os benefícios da demanda são atualizados no banco, salvando os outros dados da demanda
  useEffect(() => {
    console.log(beneficios, props.dados.beneficios);
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
      };

      const anexosVelhos = [];
      for (let anexo of anexos) {
        if (anexo.id) {
          anexosVelhos.push(
            new File([base64ToArrayBuffer(anexo.dados)], anexo.nome, {
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
          for (let anexo of anexos) {
            AnexoService.deleteById(anexo.id);
          }
          // atualizar demanda salva no location

          setEditar(false);
          excluirBeneficiosRemovidos();
          setDemandaEmEdicao(false);
          props.setDados(response);
        });
      } else {
        DemandaService.putSemAnexos(demandaAtualizada).then((response) => {
          // atualizar demanda salva no location

          setEditar(false);
          excluirBeneficiosRemovidos();
          setDemandaEmEdicao(false);
          props.setDados(response);
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
      textoFinal += bu.nome + ", ";
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

  const aprovarDemanda = () => {
    setModalAprovarDemanda(true);
  };

  const abrirRecusaDemanda = (modo) => {
    setModoModalRecusa(modo);
    setOpenModalRecusa(true);
  };

  // Devolver a demanda para o analista
  const aprovarDemandaGerencia = () => {
    DemandaService.atualizarStatus(props.dados.id, "ASSESSMENT").then(
      (response) => {
        navegarHome(1);
      }
    );
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
    };

    DemandaService.put(demandaAtualizada, dados.anexos).then((response) => {
      navigate("/");
    });
    NotificacaoService.post(
      NotificacaoService.createNotificationObject(
        NotificacaoService.aprovado,
        props.dados
      )
    );
  };

  // Função acionada quando o usuário clica em "Aceitar" no modal de confirmação
  const confirmRecusaDemanda = () => {
    if (motivoRecusaDemanda != "") {
      setOpenModalRecusa(false);
      if (modoModalRecusa == "devolucao") {
        DemandaService.putSemAnexos({
          ...props.dados,
          motivoRecusa: motivoRecusaDemanda,
          status: "BACKLOG_EDICAO",
        }).then((response) => {
          navegarHome(2);
        });
      } else {
        DemandaService.put(
          {
            ...props.dados,
            motivoRecusa: motivoRecusaDemanda,
            status: "CANCELLED",
          },
          []
        ).then((response) => {
          navegarHome(3);
        });
      }

      NotificacaoService.post(
        NotificacaoService.createNotificationObject(
          modoModalRecusa == "devolucao"
            ? NotificacaoService.maisInformacoes
            : NotificacaoService.reprovado,
          props.dados
        )
      );
    }
  };

  function base64ToArrayBuffer(base64) {
    const binaryString = window.atob(base64);
    const bytes = new Uint8Array(binaryString.length);
    return bytes.map((byte, i) => binaryString.charCodeAt(i));
  }

  const baixarAnexo = (index) => {
    const file = anexos[index];
    let blob;
    let fileName;

    if (anexos[index] instanceof File) {
      blob = file;
      fileName = file.name;
    } else {
      blob = new Blob([base64ToArrayBuffer(file.dados)]);
      fileName = `${file.nome}`;
    }

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

  // Lógica para anexos ------------------------------------------------------
  // Novos anexos que serão adicionados na demanda
  const [novosAnexos, setNovosAnexos] = useState([]);

  // Anexos que já estavam na demanda e que serão removidos
  const [anexosRemovidos, setAnexosRemovidos] = useState([]);

  // Irá atualizar a lista para que contenha os anexos que foram removidos da demanda e que já estavam salvos no banco de dados
  const updateAnexosRemovidos = (indexAnexo) => {
    let anexo = anexos[indexAnexo];

    setAnexosRemovidos([
      ...anexosRemovidos,
      ...anexos.filter(
        // anexo.id - se tiver, quer dizer que é um anexo que já estava na demanda (salvo no banco de dados)
        (anexoItem) => anexo.id && anexoItem.id == anexo.id
      ),
    ]);
  };
  //lembrar de resetar essas variáveis depois de salvar e/ou sair

  // Irá atualizar a lista para que contenha os anexos que foram adicionados na demanda (somente novos anexos)
  const updateAnexosNovos = (anexo) => {
    if (!existsInArray(novosAnexos, anexo)) {
      setNovosAnexos([...novosAnexos, anexo]);
    }
  };

  // Função que verifica se um determinado anexo já existe na lista provida
  const existsInArray = (array, anexo) => {
    return (
      array.filter((anexoItem) => {
        return anexoItem.name == anexo.name;
      }).length > 0
    );
  };

  // Função que remove um anexo da lista anexosNovos caso o usuário o remova, só é removido anexos que não estavam salvos no banco de dados
  const removeAnexosNovos = (anexo) => {
    setNovosAnexos(
      novosAnexos.filter((anexoItem) => {
        return anexoItem.name != anexo.name && !anexoItem.id;
      })
    );
  };

  // Aparecer o feedback sobre a demanda

  const navegarHome = (tipoFeedback) => {
    localStorage.removeItem("tipoFeedback");

    switch (tipoFeedback) {
      case 1:
        localStorage.setItem("tipoFeedback", "2");
        navigate("/");
        break;
      case 2:
        localStorage.setItem("tipoFeedback", "3");
        navigate("/");
        break;
      case 3:
        localStorage.setItem("tipoFeedback", "4");
        navigate("/");
        break;
    }
  };

  return (
    <Box className="flex flex-col justify-center relative items-center mt-10">
      <Feedback
        open={feedbackFacaAlteracao}
        handleClose={() => setFeedbackFacaAlteracao(false)}
        status={"erro"}
        mensagem={"Faça alguma alteração para poder salvar!"}
      />
      <Feedback
        open={feedbackComAnexoMesmoNome}
        handleClose={() => setFeedbackComAnexoMesmoNome(false)}
        status={"erro"}
        mensagem={"Já há um anexo com esse nome!"}
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
                Problema:
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
                Proposta:
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
                  Beneficios:
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
                Frequência de uso:
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
                    Tamanho:
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
                    Seção de TI:
                  </Typography>
                  <Typography
                    fontSize={FontConfig.medium}
                    className="text-justify"
                    color="text.secondary"
                    sx={{ marginLeft: "10px", marginRight: "15px" }}
                  >
                    {props.dados.secaoTI}
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
                    BU Solicitante:
                  </Typography>
                  <Typography
                    fontSize={FontConfig.medium}
                    className="text-justify"
                    color="text.secondary"
                    sx={{ marginLeft: "10px" }}
                  >
                    {props.dados.buSolicitante.nome}
                  </Typography>
                </Box>
                <Box className="flex items-center">
                  <Typography
                    fontSize={FontConfig.veryBig}
                    fontWeight="600"
                    color="text.primary"
                  >
                    BUs Beneficiadas:
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
                  Fórum:
                </Typography>
                <Typography
                  fontSize={FontConfig.medium}
                  className="text-justify"
                  color="text.secondary"
                  sx={{ marginLeft: "10px" }}
                >
                  {props.dados.forum.nome}
                </Typography>
              </Box>
            )}
            <Box>
              <Typography
                fontSize={FontConfig.veryBig}
                fontWeight="600"
                color="text.primary"
              >
                Anexos:
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
                      <Tooltip title="Baixar">
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
                  Nenhum anexo adicionado
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
                placeholder="Digite o título da demanda..."
              />
            </Box>
            <Divider />
            <Box>
              <Typography
                fontSize={FontConfig.veryBig}
                fontWeight="600"
                color="text.primary"
              >
                Problema:
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
                placeholder="Digite o problema..."
              />
            </Box>
            <Box>
              <Typography
                fontSize={FontConfig.veryBig}
                fontWeight="600"
                color="text.primary"
              >
                Proposta:
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
                placeholder="Digite a proposta..."
              />
            </Box>
            <Box>
              <Box className="flex items-center">
                <Typography
                  fontSize={FontConfig.veryBig}
                  fontWeight="600"
                  color="text.primary"
                >
                  Beneficios:
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
                Frequência de uso:
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
                placeholder="Digite a frequência..."
              />
            </Box>
            <Box>
              <Box className="flex items-center">
                <Typography
                  fontSize={FontConfig.veryBig}
                  fontWeight="600"
                  color="text.primary"
                >
                  Anexos:
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
              {anexos.length > 0 ? (
                <Box className="flex flex-col gap-2">
                  {anexos?.map((anexo, index) => (
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
                        <Tooltip title="Baixar">
                          <IconButton
                            onClick={() => {
                              baixarAnexo(index);
                            }}
                          >
                            <DownloadIcon sx={{ color: "text.primary" }} />
                          </IconButton>
                        </Tooltip>
                        <Tooltip title="Remover">
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
                  Nenhum anexo adicionado
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
                Recusar
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
                Devolver
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
                Aceitar
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
                Recusar
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
                Aceitar
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
            Salvar
          </Button>
        )}
      </Box>
    </Box>
  );
};

export default DetalhesDemanda;
