import React, { useState, useContext, useEffect, useRef } from "react";

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

import BeneficiosDetalheDemanda from "../../components/BeneficiosDetalheDemanda/BeneficiosDetalheDemanda";
import ModalConfirmacao from "../../components/ModalConfirmacao/ModalConfirmacao";
import ModalAceitarDemanda from "../../components/ModalAceitarDemanda/ModalAceitarDemanda";

import FontConfig from "../../service/FontConfig";

import ColorModeContext from "../../service/TemaContext";
import BeneficioService from '../../service/beneficioService';
import DemandaService from '../../service/demandaService';
import AnexoService from "../../service/anexoService";

const DetalhesDemanda = (props) => {
  const [corFundoTextArea, setCorFundoTextArea] = useState("#FFFF");
  const { mode } = useContext(ColorModeContext);
  const inputFile = useRef(null);

  useEffect(() => {
    if (mode === "dark") {
      setCorFundoTextArea("#212121");
    } else {
      setCorFundoTextArea("#FFFF");
    }
  }, [mode]);

  const [editar, setEditar] = useState(false);

  const [openModal, setOpenModal] = useState(false);

  function editarDemanda() {
    if (editar) {
      setOpenModal(true);
    } else {
      setEditar(true);
      if (props.setEdicao) {
        props.setEdicao(true)
      }
    }
  }

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
  }

  useEffect(() => {
    setTituloDemanda(props.dados.titulo);
    setProblema(props.dados.problema);
    setProposta(props.dados.proposta);
    setFrequencia(props.dados.frequencia);
    setBeneficios(formatarBeneficios(props.dados.beneficios));
    setAnexos(props.dados.anexo);
  }, [props.dados]);

  useEffect(() => {
    if (!props.edicao) {
      setEditar(false);
    }
  }, [props.edicao]);

  const [tituloDemanda, setTituloDemanda] = useState(props.dados.titulo);
  const [problema, setProblema] = useState(props.dados.problema);
  const [proposta, setProposta] = useState(props.dados.proposta);
  const [frequencia, setFrequencia] = useState(props.dados.frequencia);

  const [beneficios, setBeneficios] = useState(null);
  const [beneficiosNovos, setBeneficiosNovos] = useState([]);
  const [beneficiosExcluidos, setBeneficiosExcluidos] = useState([]);
  const [demandaEmEdicao, setDemandaEmEdicao] = useState(false);

  const [anexosExcluidos, setAnexosExcluidos] = useState([]);

  const [anexos, setAnexos] = useState(props.dados.anexo);

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

  // UseState do modal de aceitar demanda
  const [openModalAceitarDemanda, setOpenModalAceitarDemanda] = useState(false);

  // Função para fechar o modal de confirmação
  const handleCloseModalAceitarDemanda = () => {
    setOpenModalAceitarDemanda(false);
  };

  // Acionado quando o usuário clicar em "Aceitar" na demanda
  const aceitarDemanda = () => {
    setOpenModalAceitarDemanda(true);
  };

  // Função acionada quando o usuário clica em "Aceitar" no modal de confirmação
  const confirmAceitarDemanda = (dados) => {
    console.log(dados);
  };

  // Coloca o arquivo selecionado no input no state de anexos
  const onFilesSelect = () => {
    for (let file of inputFile.current.files) {
      setAnexos([...anexos, file]);
    }
  };

  const removerAnexo = (index) => {
    setAnexosExcluidos([...anexosExcluidos, anexos[index]]);
    let listaNova = [];
    for (let anexo of anexos) {
      if (anexo.id != anexos[index].id) {
        listaNova.push(anexo);
      }
    }
    setAnexos([...listaNova]);
  }

  // Aciona o input de anexos ao clicar no add anexos
  const onAddAnexoButtonClick = () => {
    inputFile.current.click();
  };

  // useEffect(() => {
  //   setMapAbleAnexos(Array.from(props.dados.anexo));
  // }, [props.dados]);

  const adicionarBeneficio = () => {
    BeneficioService.post({ tipoBeneficio: '', valor_mensal: '', moeda: '', memoriaCalculo: '' }).then((response) => {
      let beneficioNovo = { id: response.id, tipoBeneficio: '', valor_mensal: '', moeda: '', memoriaCalculo: '', visible: true };
      setBeneficiosNovos([...beneficiosNovos, beneficioNovo]);
      setBeneficios([...beneficios, beneficioNovo])
    })
  }

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

  const excluirBeneficiosRemovidos = () => {
    for (let beneficio of beneficiosExcluidos) {
      BeneficioService.delete(beneficio.id).then((response) => { })
    }
    setBeneficiosExcluidos([]);
  }

  const excluirBeneficiosAdicionados = () => {
    for (let beneficio of beneficiosNovos) {
      BeneficioService.delete(beneficio.id).then((response) => { })
    }
    setBeneficiosNovos([]);
  }

  const salvarEdicao = () => {

    let listaBeneficiosFinal = formatarBeneficiosRequisicao(beneficios);
    let contagem = 0;

    for (let beneficio of formatarBeneficiosRequisicao(beneficios)) {
      BeneficioService.put(beneficio).then((response) => { })
      contagem++;

      if (contagem == listaBeneficiosFinal.length) {
        setDemandaEmEdicao(true);
      }
    }
  }

  useEffect(() => {
    if (demandaEmEdicao) {
      const demandaAtualizada = { id: props.dados.id, titulo: tituloDemanda, problema: problema, proposta: proposta, frequencia: frequencia, beneficios: formatarBeneficiosRequisicao(beneficios), data: props.dados.data, status: props.dados.status, solicitante: props.dados.solicitante };
      DemandaService.put(demandaAtualizada, anexos).then((response) => {
        setEditar(false);
        excluirBeneficiosRemovidos();
        setDemandaEmEdicao(false);
        props.setDados(response);
      })
    }
  }, [demandaEmEdicao])

  return (
    <Box className="flex flex-col justify-center relative items-center mt-10">
      <ModalAceitarDemanda
        open={openModalAceitarDemanda}
        setOpen={setOpenModalAceitarDemanda}
        handleClose={handleCloseModalAceitarDemanda}
        confirmAceitarDemanda={confirmAceitarDemanda}
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
          {!editar ? (
            <ModeEditOutlineOutlinedIcon
              fontSize="large"
              className="delay-120 hover:scale-110 duration-300"
              sx={{ color: "icon.main" }}
            />
          ) : (
            <EditOffOutlinedIcon
              fontSize="large"
              className="delay-120 hover:scale-110 duration-300"
              sx={{ color: "icon.main" }}
            />
          )}
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
                        padding: "0.5rem 1rem",
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
                  onClick={() => { adicionarBeneficio() }}
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
                      <IconButton
                        onClick={() => { removerAnexo(index) }}
                      >
                        <CloseIcon sx={{ color: "text.primary" }} />
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
        )}
      </Box>
      <Box className="w-full p-10"></Box>
      <Box
        className="flex fixed justify-end"
        sx={{ width: "20rem", bottom: "20px", right: "20px" }}
      >
        {props.botao == "sim" && !editar && (
          <Box className="flex justify-around w-full">
            <Button
              sx={{
                backgroundColor: "primary.main",
                color: "text.white",
                fontSize: FontConfig.default,
              }}
              variant="contained"
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
              salvarEdicao()
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
