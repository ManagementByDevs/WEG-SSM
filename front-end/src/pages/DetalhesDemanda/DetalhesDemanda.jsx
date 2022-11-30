import React, { useState } from "react";

import { Box, Typography, Button, Divider, TextareaAutosize } from "@mui/material";

import SaveAltOutlinedIcon from "@mui/icons-material/SaveAltOutlined";
import ModeEditOutlineOutlinedIcon from "@mui/icons-material/ModeEditOutlineOutlined";
import EditOffOutlinedIcon from "@mui/icons-material/EditOffOutlined";

import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Caminho from "../../components/Caminho/Caminho";
import BeneficiosDetalheDemanda from "../../components/BeneficiosDetalheDemanda/BeneficiosDetalheDemanda";

import FontConfig from "../../service/FontConfig";

const DetalhesDemanda = () => {

  const [editar, setEditar] = useState(false);

  function editarDemanda() {
    if (editar) {
      console.log("Certeza que deseja cancelar as alterações?");
    }
    setEditar(!editar);
  }

  const [dados, setDados] = useState({
    titulo: "",
    problema: "",
    proposta: "",
    frequencia: "",
  });

  const salvarTitulo = (texto) => {
    setDados({ ...dados, titulo: texto });
  };

  const salvarProblema = (texto) => {
    setDados({ ...dados, problema: texto });
  };

  const salvarProposta = (texto) => {
    setDados({ ...dados, proposta: texto });
  };

  const salvarFrequencia = (texto) => {
    setDados({ ...dados, frequencia: texto });
  };

  const [tituloDemanda, setTituloDemanda] = useState(
    "Sistema de Gestão de Demandas"
  );
  const [problema, setProblema] = useState(
    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries"
  );
  const [proposta, setProposta] = useState(
    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen  book. It has survived not only five centuries is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since  the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries"
  );
  const [frequencia, setFrequencia] = useState("Lorem Ipsum is simply dummy text of the printing and");

  let textoAux = "";

  const save = (e, input) => {
    if (input === "titulo") {
      setTituloDemanda(e.target.value);
      textoAux += e.target.value;
      salvarTitulo(textoAux);
    } else if (input === "problema") {
      setProblema(e.target.value);
      textoAux += e.target.value;
      salvarProblema(textoAux);
    } else if (input === "proposta") {
      setProposta(e.target.value);
      textoAux += e.target.value;
      salvarProposta(textoAux);
    } else if (input === "frequencia") {
      setFrequencia(e.target.value);
      textoAux += e.target.value;
      salvarFrequencia(textoAux);
    }
  };
  return (
    <FundoComHeader>
      <Box className="p-2">
        <Box className="flex w-full relative">
          <Caminho />
          <Box
            className=" absolute"
            sx={{ top: "10px", right: "20px", cursor: "pointer" }}
          >
            <SaveAltOutlinedIcon
              fontSize="large"
              className="delay-120 hover:scale-110 duration-300"
              sx={{ color: "icon.main" }}
            />
          </Box>
        </Box>
        <Box className="flex justify-center relative items-center mt-3">
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
                    sx={{ fontWeight: "600", cursor: "default" }}
                    color="primary.main"
                  >
                    {tituloDemanda}
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
                    {problema}
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
                    Lorem Ipsum is simply dummy text of the printing and
                    typesetting industry. Lorem Ipsum has been the industry's
                    standard dummy text ever since the 1500s, when an unknown
                    printer took a galley of type and scrambled it to make a
                    type specimen book. It has survived not only five centuries
                    is simply dummy text of the printing and typesetting
                    industry. Lorem Ipsum has been the industry's standard dummy
                    text ever since the 1500s, when an unknown printer took a
                    galley of type and scrambled it to make a type specimen
                    book. It has survived not only five centuries is simply
                    dummy text of the printing and typesetting industry. Lorem
                    Ipsum has been the industry's standard dummy text ever since
                    the 1500s, when an unknown printer took a galley of type and
                    scrambled it to make a type specimen book. It has survived
                    not only five centuries
                  </Typography>
                </Box>
                <Box>
                  <Typography
                    fontSize={FontConfig.veryBig}
                    fontWeight="600"
                    color="text.primary"
                  >
                    Beneficios:
                  </Typography>
                  <Box className="ml-7 mt-2">
                    <BeneficiosDetalheDemanda />
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
                    Lorem Ipsum is simply dummy text of the printing and
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
                      save(e, "titulo");
                    }}
                    fontSize={FontConfig.title}
                    color="primary.main"
                    className="flex outline-none border-solid border px-1 py-1.5 drop-shadow-sm rounded text-center"
                    sx={{
                      width: "90%;",
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
                    style={{ width: 775, marginLeft: "26px", resize: "none",backgroundColor: "background.default"}}
                    value={problema}
                    fontSize={FontConfig.medium}
                    onChange={(e) => {
                      save(e, "problema");
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
                    style={{ width: 775, marginLeft: "26px", resize: "none",backgroundColor: "background.default"}}
                    value={proposta}
                    fontSize={FontConfig.medium}
                    onChange={(e) => {
                      save(e, "proposta");
                    }}
                    className="flex outline-none border-solid border px-1 py-1.5 drop-shadow-sm rounded text-center text-justify"
                    placeholder="Digite a proposta..."
                  />
                </Box>
                <Box>
                  <Typography
                    fontSize={FontConfig.veryBig}
                    fontWeight="600"
                    color="text.primary"
                  >
                    Beneficios:
                  </Typography>
                  <Box className="ml-7 mt-2">
                    <BeneficiosDetalheDemanda />
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
                      save(e, "frequencia");
                    }}
                    fontSize={FontConfig.medium}
                    className="outline-none border-solid border px-1 py-1.5 drop-shadow-sm rounded"
                    sx={{
                      width: "90%;",
                      backgroundColor: "background.default",
                      marginLeft: "30px",
                    }}
                    component="input"
                    placeholder="Digite a frequência..."
                  />
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
            )}
          </Box>
          <Box
            className="flex fixed justify-around"
            sx={{ width: "20rem", bottom: "20px", right: "20px" }}
          >
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
            >
              Aceitar
            </Button>
          </Box>
        </Box>
      </Box>
    </FundoComHeader>
  );
};

export default DetalhesDemanda;
