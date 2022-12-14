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

import FontConfig from "../../service/FontConfig";

import ColorModeContext from "../../service/TemaContext";
import BeneficioService from "../../service/beneficioService";
import DemandaService from "../../service/demandaService";
import AnexoService from "../../service/anexoService";

const DetalhesDemanda = (props) => {
  const [corFundoTextArea, setCorFundoTextArea] = useState("#FFFF");
  const { mode } = useContext(ColorModeContext);
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

  const [motivoRecusaDemanda, setMotivoRecusaDemanda] = useState("");
  const [modoModalRecusa, setModoModalRecusa] = useState("recusa");

  useEffect(() => {
    if (mode === "dark") {
      setCorFundoTextArea("#212121");
    } else {
      setCorFundoTextArea("#FFFF");
    }
  }, [mode]);

  const [editar, setEditar] = useState(false);

  const [openModal, setOpenModal] = useState(false);

  const navigate = useNavigate();

  useEffect(() => {
    setTituloDemanda(props.dados.titulo);
    setProblema(props.dados.problema);
    setProposta(props.dados.proposta);
    setFrequencia(props.dados.frequencia);
    setBeneficios(formatarBeneficios(props.dados.beneficios));
    setAnexos(props.dados.anexo);
  }, [props.dados]);

  // ----------------------------------------------------------------------------------------------------------------------------
  // Fun????es de edi????o da demanda

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

  // Fun????o de cancelamento da edi????o da demanda, retornando os dados ao salvamento anterior
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

  // Fun????o para formatar os benef??cios recebidos do banco para a lista de benef??cios na p??gina
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

  // Fun????o para formatar os benef??cios do front-end para envi??-los ao salvamento da demanda
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
        // feedback de anexo j?? existente
        console.log("j?? h?? um anexo com esse nome");
      }
    }
  };

  const existsInAnexos = (anexo) => {
    return (
      anexos.filter((anexoItem) => {
        console.log(!anexo.id && anexoItem.name == anexo.nome);
        return (
          (anexoItem.nome == anexo.name) ||
          (anexo.id && anexoItem.nome == anexo.nome)
        );
      }).length > 0
    );
  };

  // Fun????o para remover um anexo da lista de anexos
  const removerAnexo = (index) => {
    updateAnexosRemovidos(index);
    removeAnexosNovos(anexos[index]);
    let aux = [...anexos];
    aux.splice(index, 1);
    setAnexos(aux);
  };

  // Fun????o que cria um benef??cio no banco e usa o id nele em um objeto novo na lista da p??gina
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

  // Fun????o para atualizar o benef??cio quando ele recebe alguma altera????o
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

  // Fun????o para excluir um benef??cio da lista
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

  // Fun????o para excluir os benef??cios que foram criados no banco, por??m exclu??dos da demanda
  const excluirBeneficiosRemovidos = () => {
    for (let beneficio of beneficiosExcluidos) {
      BeneficioService.delete(beneficio.id).then((response) => {});
    }
    setBeneficiosExcluidos([]);
  };

  // Fun????o para excluir todos os benef??cios adicionados em uma edi????o caso ela seja cancelada
  const excluirBeneficiosAdicionados = () => {
    for (let beneficio of beneficiosNovos) {
      BeneficioService.delete(beneficio.id).then((response) => {});
    }
    setBeneficiosNovos([]);
  };

  // Fun????o inicial da edi????o da demanda, atualizando os benef??cios dela
  const salvarEdicao = () => {
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

  // UseEffect ativado quando os benef??cios da demanda s??o atualizados no banco, salvando os outros dados da demanda
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
      };

      console.log("demanda: ", demandaAtualizada);
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
        console.log("entrou novos aenxos lenght > 0");
        DemandaService.put(demandaAtualizada, [
          ...anexosVelhos,
          ...novosAnexos,
        ]).then((response) => {
          // atualizar demanda salva no location

          setEditar(false);
          excluirBeneficiosRemovidos();
          setDemandaEmEdicao(false);
          console.log("Response: ", response);
          props.setDados(response);
        });
      } else {
        console.log("entrou novos aenxos lenght > 0 else");
        DemandaService.putSemAnexos(demandaAtualizada).then((response) => {
          // atualizar demanda salva no location

          setEditar(false);
          excluirBeneficiosRemovidos();
          setDemandaEmEdicao(false);
          console.log("Response: ", response);
          props.setDados(response);
        });
      }
    }

    if (anexosRemovidos.length > 0) {
      console.log("entrou anexos removidos");
      for (let anexoRemovido of anexosRemovidos) {
        AnexoService.deleteById(anexoRemovido.id);
      }
    }
  }, [demandaEmEdicao]);

  // -----------------------------------------------------------------------------------------------------------------------------------
  // Fun????es de aceite/recusa do analista/gerente

  // Fun????o para fechar o modal de confirma????o
  const handleCloseModalAceitarDemanda = () => {
    setOpenModalAceitarDemanda(false);
  };

  // Fun????o para fechar o modal de recusa
  const handleCloseModalRecusar = () => {
    setMotivoRecusaDemanda("");
    setOpenModalRecusa(false);
  };

  // Acionado quando o usu??rio clicar em "Aceitar" na demanda
  const aceitarDemanda = () => {
    setOpenModalAceitarDemanda(true);
  };

  const abrirRecusaDemanda = (modo) => {
    setModoModalRecusa(modo);
    setOpenModalRecusa(true);
  };

  // Fun????o acionada quando o usu??rio clica em "Aceitar" no modal de confirma????o
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
      gerente: props.dados.gerente
    };

    DemandaService.put(demandaAtualizada, []).then((response) => {
      navigate('/');
    });
  };

  // Fun????o acionada quando o usu??rio clica em "Aceitar" no modal de confirma????o
  const confirmRecusaDemanda = () => {
    if (motivoRecusaDemanda != "") {
      setOpenModalRecusa(false);

      if (modoModalRecusa == "devolucao") {
        DemandaService.put(
          {
            ...props.dados,
            motivoRecusa: motivoRecusaDemanda,
            status: "BACKLOG_EDICAO",
          },
          []
        ).then((response) => {
          navigate("/");
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
          navigate("/");
        });
      }
    }
  };

  const aceitarDemandaGerente = () => {
    DemandaService.atualizarStatus(props.dados.id, "ASSESSMENT").then((response) => {
      navigate('/');
    });
  }

  function base64ToArrayBuffer(base64) {
    const binaryString = window.atob(base64);
    const bytes = new Uint8Array(binaryString.length);
    return bytes.map((byte, i) => binaryString.charCodeAt(i));
  }

  const baixarAnexo = (index) => {
    const file = anexos[index];
    const blob = new Blob([base64ToArrayBuffer(file.dados)]);
    const fileName = `${file.nome}`;

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

  // L??gica para anexos ------------------------------------------------------
  // Novos anexos que ser??o adicionados na demanda
  const [novosAnexos, setNovosAnexos] = useState([]);

  // Anexos que j?? estavam na demanda e que ser??o removidos
  const [anexosRemovidos, setAnexosRemovidos] = useState([]);

  // Ir?? atualizar a lista para que contenha os anexos que foram removidos da demanda e que j?? estavam salvos no banco de dados
  const updateAnexosRemovidos = (indexAnexo) => {
    let anexo = anexos[indexAnexo];

    setAnexosRemovidos(
      ...anexosRemovidos,
      props.dados.anexo.filter(
        // anexo.id - se tiver, quer dizer que ?? um anexo que j?? estava na demanda (salvo no banco de dados)
        (anexoItem) => {
          return anexo.id && anexoItem.id == anexo.id;
        }
      )
    );
  };
  //lembrar de resetar essas vari??veis depois de salvar e/ou sair

  // Ir?? atualizar a lista para que contenha os anexos que foram adicionados na demanda (somente novos anexos)
  const updateAnexosNovos = (anexo) => {
    if (!existsInArray(novosAnexos, anexo)) {
      setNovosAnexos([...novosAnexos, anexo]);
    }
  };

  // Fun????o que verifica se um determinado anexo j?? existe na lista provida
  const existsInArray = (array, anexo) => {
    console.log(anexo);
    return (
      array.filter((anexoItem) => {
        console.log(anexoItem.nome == anexo.nome);
        return anexoItem.name == anexo.name;
      }).length > 0
    );
  };

  // Fun????o que remove um anexo da lista anexosNovos caso o usu??rio o remova, s?? ?? removido anexos que n??o estavam salvos no banco de dados
  const removeAnexosNovos = (anexo) => {
    setNovosAnexos(
      novosAnexos.filter((anexoItem) => {
        return anexoItem.name != anexo.name && !anexoItem.id;
      })
    );
  };

  useEffect(() => {
    console.log("anexos", anexos);
    console.log("anexosRemovidos", anexosRemovidos);
    console.log("novosAnexos", novosAnexos);
  }, [anexos, anexosRemovidos, novosAnexos]);

  return (
    <Box className="flex flex-col justify-center relative items-center mt-10">
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
      <Box
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
                Frequ??ncia de uso:
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
                      <IconButton
                        onClick={() => {
                          baixarAnexo(index);
                        }}
                      >
                        <DownloadIcon sx={{ color: "text.primary" }} />
                      </IconButton>
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
                placeholder="Digite o t??tulo da demanda..."
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
                Frequ??ncia de uso:
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
                placeholder="Digite a frequ??ncia..."
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
                        <IconButton
                          onClick={() => {
                            removerAnexo(index);
                          }}
                        >
                          <CloseIcon sx={{ color: "text.primary" }} />
                        </IconButton>
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
