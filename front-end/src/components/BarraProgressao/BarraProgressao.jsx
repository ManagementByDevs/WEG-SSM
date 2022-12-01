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

const BarraProgressao = (props) => {
  const [activeStep, setActiveStep] = useState(0);
  const [skipped, setSkipped] = useState(new Set());
  const steps = props.steps;

  const [paginaDados, setPaginaDados] = useState({
    titulo: "",
    problema: "",
    proposta: "",
    frequencia: "",
  });
  const [paginaBeneficios, setPaginaBeneficios] = useState([]);
  const [paginaArquivos, setPaginaArquivos] = useState([]);

  const navigate = useNavigate();

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

    useEffect(() => {
      if (open) {
        navigate("/");
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
        {activeStep == 0 && <FormularioDadosDemanda dados={paginaDados} setDados={setPaginaDados}/>}
        {activeStep == 1 && <FormularioBeneficiosDemanda />}
        {activeStep == 2 && <FormularioAnexosDemanda />}
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
        </Box>
      </>
    );
};

export default BarraProgressao;
