import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

import {
  Box,
  Stepper,
  Step,
  StepLabel,
  Typography,
  Button,
} from "@mui/material";

import FormularioDadosDemanda from "../FormularioDadosDemanda/FormularioDadosDemanda";
import FormularioBeneficiosDemanda from "../FormularioBeneficiosDemanda/FormularioBeneficiosDemanda";
import FormularioAnexosDemanda from "../FormularioAnexosDemanda/FormularioAnexosDemanda";
import Feedback from "../Feedback/Feedback";
import ModalConfirmacao from "../ModalConfirmacao/ModalConfirmacao"

import DemandaService from '../../service/demandaService';
import EscopoService from "../../service/escopoService";

const BarraProgressaoDemanda = (props) => {
  const [activeStep, setActiveStep] = useState(0);
  const [skipped, setSkipped] = useState(new Set());
  const steps = props.steps;
  const [ultimoEscopo, setUltimoEscopo] = useState(null);
  var idEscopo = null;

  // Dados da página inicial da criação de demanda
  const [paginaDados, setPaginaDados] = useState({
    titulo: "",
    problema: "",
    proposta: "",
    frequencia: "",
  });

  // Lista de benefícios definidos na segunda página da criação de demanda
  const [paginaBeneficios, setPaginaBeneficios] = useState([]);

  // Lista de anexos definidos na terceira página da criação de demanda
  const [paginaArquivos, setPaginaArquivos] = useState([]);

  const navigate = useNavigate();

  useEffect(() => {
    if (!idEscopo) {
      idEscopo = 1;
      EscopoService.postNew(parseInt(localStorage.getItem("usuarioId"))).then((response) => {
        idEscopo = response.id;
        setUltimoEscopo({ id: idEscopo });
      })
    }
  }, [])

  useEffect(() => {
    if (ultimoEscopo) {
      setTimeout(() => {salvarEscopo(ultimoEscopo.id)}, 10000);
    }
  }, [ultimoEscopo]);

  const salvarEscopo = (id) => {
    setUltimoEscopo({
      id: id,
      titulo: paginaDados.titulo,
      problema: paginaDados.problema,
      proposta: paginaDados.proposta,
      frequencia: paginaDados.frequencia,
      beneficios: paginaBeneficios
    })

    EscopoService.salvarDados(ultimoEscopo).then((response) => {
      console.log(response);
    })
  }

  const isStepOptional = (step) => {
    return false;
  };

  const isStepSkipped = (step) => {
    return skipped.has(step);
  };

  const handleNext = () => {
    if (activeStep === steps.length - 1) {
      criarDemanda();
    }
    let newSkipped = skipped;
    if (isStepSkipped(activeStep)) {
      newSkipped = new Set(newSkipped.values());
      newSkipped.delete(activeStep);
    }
    setActiveStep((prevActiveStep) => prevActiveStep + 1);
    setSkipped(newSkipped);
  };

  const handleBack = () => {
    setActiveStep((prevActiveStep) => prevActiveStep - 1);
  };

  const handleSkip = () => {
    if (!isStepOptional(activeStep)) {
      throw new Error("Você não pode pular um passo que não é opcional!");
    }

    setActiveStep((prevActiveStep) => prevActiveStep + 1);
    setSkipped((prevSkipped) => {
      const newSkipped = new Set(prevSkipped.values());
      newSkipped.add(activeStep);
      return newSkipped;
    });
  };

  const handleReset = () => {
    setActiveStep(0);
  };

  const [demandaCriada, setDemandaCriada] = React.useState(false);

  const criarDemanda = () => {
    handleClick(true);
  };

  const [state, setState] = React.useState({
    open: false,
  });
  const { open } = state;

  const handleClick = (newState) => () => {
    setState({ open: true, ...newState });
  };

  const [modalConfirmacao, setOpenConfirmacao] = useState(false);

  const abrirModalConfirmacao = () => {
    setOpenConfirmacao(true);
  }

  // Função para formatar os benefícios recebidos da página de benefícios para serem adicionados ao banco na criação da demanda
  const formatarBeneficios = () => {
    let listaNova = [];
    for (let beneficio of paginaBeneficios) {
      delete beneficio.id;
      delete beneficio.visible;
      listaNova.push({ ...beneficio, tipoBeneficio: beneficio.tipoBeneficio.toUpperCase() });
    }
    return listaNova;
  }

  // UseEffect para criar a demanda usando os dados recebidos das páginas
  useEffect(() => {
    if (open) {

      if (paginaDados.titulo != "" && paginaDados.problema && paginaDados.proposta && paginaDados.frequencia) {
        const demandaFinal = {
          titulo: paginaDados.titulo,
          problema: paginaDados.problema,
          proposta: paginaDados.proposta,
          frequencia: paginaDados.frequencia,
          beneficios: formatarBeneficios(),
          status: "BACKLOG"
        }

        DemandaService.post(demandaFinal, paginaArquivos, parseInt(localStorage.getItem("usuarioId"))).then((e) => {
          navigate("/");
        })
      } else {
        // Fazer feedback de campos obrigatórios faltantes
      }
    }
  }, [open]);

  const handleClose = () => {
    setState({ ...state, open: false });
  };

  return (
    <>
      {/* <Feedback
        open={open}
        handleClose={handleClose}
        status="sucesso"
        mensagem="Demanda criada com sucesso!"
      /> */}
      <Stepper activeStep={activeStep}>
        {steps.map((label, index) => {
          const stepProps = {};
          const labelProps = {};
          if (isStepOptional(index)) {
            labelProps.optional = (
              <Typography variant="caption">Optional</Typography>
            );
          }
          if (isStepSkipped(index)) {
            stepProps.completed = false;
          }
          return (
            <Step key={label} {...stepProps}>
              <StepLabel {...labelProps}>{label}</StepLabel>
            </Step>
          );
        })}
      </Stepper>
      {activeStep == 0 && <FormularioDadosDemanda dados={paginaDados} setDados={setPaginaDados} />}
      {activeStep == 1 && <FormularioBeneficiosDemanda dados={paginaBeneficios} setDados={setPaginaBeneficios} />}
      {activeStep == 2 && <FormularioAnexosDemanda dados={paginaArquivos} setDados={setPaginaArquivos} />}
      <Box sx={{ display: "flex", flexDirection: "row", pt: 2 }}>
        <Button
          variant="outlined"
          color="tertiary"
          disabled={activeStep === 0}
          onClick={handleBack}
          sx={{ mr: 1 }}
          disableElevation
        >
          Voltar
        </Button>
        <Box sx={{ flex: "1 1 auto" }} />
        {isStepOptional(activeStep) && (
          <Button color="inherit" onClick={handleSkip} sx={{ mr: 1 }}>
            Pular
          </Button>
        )}

        {activeStep === steps.length - 1 ? (
          <Button
            color="primary"
            variant="contained"
            onClick={handleClick()}
            disableElevation
          >
            Criar
          </Button>
        ) : (
          <Button
            color="primary"
            variant="contained"
            onClick={handleNext}
            disableElevation
          >
            Próximo
          </Button>
        )}
        {/* {modalConfirmacao && <ModalConfirmacao open={modalConfirmacao} setOpen={setOpenConfirmacao} textoModal={"enviarDemanda"} textoBotao={"enviar"} />} */}
      </Box>
    </>
  );
};

export default BarraProgressaoDemanda;
