import React, { useState, useContext, useEffect } from "react";
import { useLocation } from "react-router-dom";

import {
  Box,
  Typography,
  Button,
  Divider,
  TextareaAutosize,
} from "@mui/material";

import ModeEditOutlineOutlinedIcon from "@mui/icons-material/ModeEditOutlineOutlined";
import EditOffOutlinedIcon from "@mui/icons-material/EditOffOutlined";
import AddCircleOutlineOutlinedIcon from "@mui/icons-material/AddCircleOutlineOutlined";

import BeneficiosDetalheDemanda from "../../components/BeneficiosDetalheDemanda/BeneficiosDetalheDemanda";
import ModalConfirmacao from "../../components/ModalConfirmacao/ModalConfirmacao";
import ModalAceitarDemanda from "../../components/ModalAceitarDemanda/ModalAceitarDemanda";

import FontConfig from "../../service/FontConfig";

import ColorModeContext from "../../service/TemaContext";

const DetalhesDemanda = (props) => {
  const [corFundoTextArea, setCorFundoTextArea] = useState("#FFFF");
  const { mode } = useContext(ColorModeContext);

  const location = useLocation();

  useEffect(() => {
    if (mode === "dark") {
      setCorFundoTextArea("#212121");
    } else {
      setCorFundoTextArea("#FFFF");
    }
  }, [mode]);

  // useEffect(() => {
  //   location.state.beneficios.map((beneficio) => {
  //     beneficio.visible = true;
  //   });
  //   console.log(location.state);
  //   setDados(location.state);
  // }, []);

  const [editar, setEditar] = useState(false);

  const [openModal, setOpenModal] = useState(false);

  function editarDemanda() {
    if (editar) {
      setOpenModal(true);
    } else {
      setEditar(true);
    }
  }

  function resetarTextoInput() {
    setEditar(false);
    setTituloDemanda(dados.titulo);
    setProblema(dados.problema);
    setProposta(dados.proposta);
    setFrequencia(dados.frequencia);
    setBeneficios(dados.beneficios);
  }

  const [dados, setDados] = useState({
    titulo: "Sistema de Gestão de Demandas",
    problema:
      "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries",
    proposta:
      "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen  book. It has survived not only five centuries is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since  the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries",
    frequencia: "Lorem Ipsum is simply dummy text of the printing and",
    beneficios: [
      {
        tipoBeneficio: "Real",
        valor_mensal: "300,00",
        moeda: "BR",
        memoriaCalculo:
          "aqui vai a memória de cálculo, onde conterá as informações necessárias dele",
        visible: true,
      },
    ],
  });

  useEffect(() => {
    setTituloDemanda(dados.titulo);
    setProblema(dados.problema);
    setProposta(dados.proposta);
    setFrequencia(dados.frequencia);
    setBeneficios(dados.beneficios);

    const aux = dados.beneficios.map((beneficio) => {
      return {
        tipoBeneficio: beneficio.tipoBeneficio,
        valor_mensal: beneficio.valor_mensal,
        moeda: beneficio.moeda,
        memoriaCalculo: beneficio.memoriaCalculo,
        visible: beneficio.visible,
      };
    });
    setBeneficios(aux);
  }, [dados]);

  const save = () => {
    setDados({
      titulo: tituloDemanda,
      problema: problema,
      proposta: proposta,
      frequencia: frequencia,
      beneficios: beneficios,
    });
  };

  const [tituloDemanda, setTituloDemanda] = useState(dados.titulo);
  const [problema, setProblema] = useState(dados.problema);
  const [proposta, setProposta] = useState(dados.proposta);
  const [frequencia, setFrequencia] = useState(dados.frequencia);
  const [beneficios, setBeneficios] = useState(null);

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

  const alterarTextoBeneficio = (beneficio, index) => {
    let aux = dados.beneficios.map((beneficio) => {
      return {
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
    console.log(dados);
    let aux = dados.beneficios.map((beneficio) => {
      return {
        tipoBeneficio: beneficio.tipoBeneficio,
        valor_mensal: beneficio.valor_mensal,
        moeda: beneficio.moeda,
        memoriaCalculo: beneficio.memoriaCalculo,
        visible: beneficio.visible,
      };
    });
    aux[indexBeneficio].visible = false;
    setBeneficios(aux);
  };

  // Código do modal de aceitar demanda
  const [openModalAceitarDemanda, setOpenModalAceitarDemanda] = useState(false);

  const handleClickOpenModalAceitarDemanda = () => {
    setOpenModalAceitarDemanda(true);
  };

  const handleCloseModalAceitarDemanda = () => {
    setOpenModalAceitarDemanda(false);
  };

  const aceitarDemanda = () => {
    console.log("aceitar demanda");
    handleClickOpenModalAceitarDemanda();
  };

  return (
    <Box className="flex flex-col justify-center relative items-center mt-10">
      <ModalAceitarDemanda
        open={openModalAceitarDemanda}
        setOpen={setOpenModalAceitarDemanda}
        handleClose={handleCloseModalAceitarDemanda}
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
                {dados.titulo}
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
                {dados.problema}
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
                {dados.proposta}
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
                {dados.beneficios.map((beneficio, index) => {
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
                {dados.frequencia}
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
              <Box>AQUI JAZ ANEXOS</Box>
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
                    setBeneficios([
                      ...beneficios,
                      {
                        tipo: "",
                        valor: "",
                        moeda: "",
                        memoriaCalculo: "",
                        visible: true,
                      },
                    ]);
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
                />
              </Box>
              <Box>AQUI JAZ ANEXOS</Box>
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
              save();
              setEditar(false);
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