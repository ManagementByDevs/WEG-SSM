import React, { useEffect, useContext } from "react";

import { Box, Typography, Tooltip, Input } from "@mui/material";

import MicNoneOutlinedIcon from "@mui/icons-material/MicNoneOutlined";
import MicOutlinedIcon from "@mui/icons-material/MicOutlined";

import CurrencyInput from 'react-currency-input-field';

import TextLanguageContext from "../../service/TextLanguageContext";
import SpeechSynthesisContext from "../../service/SpeechSynthesisContext";
import { SpeechRecognitionContext } from "../../service/SpeechRecognitionService";
import { typography } from "@mui/system";

/** Input usado para valores em dinheiro */
const InputDinheiro = (props) => {

    // Contexto para trocar a linguagem
    const { texts } = useContext(TextLanguageContext);

    /** Context para ler o texto da tela */
    const { lerTexto } = useContext(SpeechSynthesisContext);

    /** Context para obter a função de leitura de texto */
    const { startRecognition, escutar, palavrasJuntas, localClique } = useContext(
        SpeechRecognitionContext
    );

    /** Função para salvar o valor do props recebido para o input (mudando também o valor do próprio input) */
    const save = (e) => {
        const valor = e.nativeEvent?.data;
        if (!parseInt(valor) && valor != "0" && valor != null) return;

        let valorInicial = "";
        if (valor) {
            valorInicial = props.texto + valor;
        } else {
            valorInicial = props.texto.substring(0, props.texto.length - 1);
        }

        valorInicial = valorInicial.replace(",", "")
        while (valorInicial.charAt(0) == "0") {
            valorInicial = valorInicial.substring(1, valorInicial.length)
        }

        let casaInteiraFinal = valorInicial.substring(0, valorInicial.length - 2);
        if (!casaInteiraFinal) {
            casaInteiraFinal = "0";
        }

        let casaDecimalFinal = valorInicial.substring(valorInicial.length - 2, valorInicial.length);
        if (casaDecimalFinal.length == 1) {
            casaDecimalFinal = "0" + casaDecimalFinal
        }
        if (casaDecimalFinal.length == 0) {
            casaDecimalFinal = "00";
        }

        let valorFinal = casaInteiraFinal + "," + casaDecimalFinal;

        props.saveInputValue(valorFinal);
    };

    /** useEffect utilizado para ouvir áudio */
    useEffect(() => {
        if (escutar && localClique == props.label) {
            props.saveInputValue(palavrasJuntas);
        }
    }, [palavrasJuntas]);

    useEffect(() => {
        if (props.smartRecomendation) {
        }
    }, []);

    return (
        <Box sx={{ width: "100%" }}>
            <Box className="flex">
                {/* Label acima do input */}
                <Typography
                    fontSize={props.fontConfig}
                    sx={{ fontWeight: "600", cursor: "default" }}
                    gutterBottom
                    onClick={() => {
                        lerTexto(props.label);
                    }}
                >
                    {props.label}
                </Typography>
                <Typography
                    fontSize={props.fontConfig}
                    sx={{ fontWeight: "800", cursor: "default", margin: "0 .2% .2% .2%" }}
                    className="text-red-600"
                    gutterBottom
                >
                    *
                </Typography>
            </Box>
            <Box
                className="flex items-center justify-between border-solid border border-l-4 px-1 py-1.5 drop-shadow-sm rounded"
                sx={{
                    width: "100%",
                    height: "40px",
                    backgroundColor: "background.default",
                    borderLeftColor: "primary.main",
                }}
            >
                <Input
                    value={props.texto}
                    onChange={(e) => {
                        save(e);
                    }}
                    fontSize={props.fontConfig}
                    className="outline-none no-underline"
                    sx={{
                        width: "95%",
                        fontWeight: "300",
                    }}
                    placeholder={props.placeholder}
                    inputProps={{
                        
                    }}
                />
                <Tooltip
                    className="hover:cursor-pointer"
                    title={texts.homeGerencia.gravarAudio}
                    onClick={() => {
                        startRecognition(props.label);
                    }}
                >
                    {escutar && localClique == props.label ? (
                        <MicOutlinedIcon
                            sx={{
                                cursor: "pointer",
                                color: "primary.main",
                                fontSize: "1.3rem",
                            }}
                        />
                    ) : (
                        <MicNoneOutlinedIcon
                            sx={{
                                cursor: "pointer",
                                color: "text.secondary",
                                fontSize: "1.3rem",
                            }}
                        />
                    )}
                </Tooltip>
            </Box>
        </Box>
    );
};

export default InputDinheiro;
