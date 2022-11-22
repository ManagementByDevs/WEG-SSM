import React from 'react'

import { Box, Stepper, Step, StepLabel, Typography, Button } from "@mui/material";

import FormularioDadosDemanda from '../FormularioDadosDemanda/FormularioDadosDemanda';
import FormularioBeneficiosDemanda from '../FormularioBeneficiosDemanda/FormularioBeneficiosDemanda';
import FormularioAnexosDemanda from '../FormularioAnexosDemanda/FormularioAnexosDemanda';

const BarraProgressao = (props) => {
    const [activeStep, setActiveStep] = React.useState(0);
    const [skipped, setSkipped] = React.useState(new Set());
    const steps = props.steps;


    const isStepOptional = (step) => {
        return false;
    };

    const isStepSkipped = (step) => {
        return skipped.has(step);
    };

    const handleNext = () => {
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

    return (
        <>
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
            {activeStep == 0 && <FormularioDadosDemanda />}
            {activeStep == 1 && <FormularioBeneficiosDemanda />}
            {activeStep == 2 && <FormularioAnexosDemanda />}
            <Box sx={{ display: 'flex', flexDirection: 'row', pt: 2 }}>
                <Button variant='outlined'
                    color="tertiary"
                    disabled={activeStep === 0}
                    onClick={handleBack}
                    sx={{ mr: 1 }}
                    disableElevation
                >
                    Voltar
                </Button>
                <Box sx={{ flex: '1 1 auto' }} />
                {isStepOptional(activeStep) && (
                    <Button color="inherit" onClick={handleSkip} sx={{ mr: 1 }}>
                        Pular
                    </Button>
                )}

                <Button color='primary' variant='contained' onClick={handleNext} disableElevation>
                    {activeStep === steps.length - 1 ? 'Criar' : 'Próximo'}
                </Button>
            </Box>
        </>
    )
}

export default BarraProgressao