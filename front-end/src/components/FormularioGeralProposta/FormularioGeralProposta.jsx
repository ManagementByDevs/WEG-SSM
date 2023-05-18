import React, { useContext, useRef, useEffect, useState } from "react";
import {
  Box,
  FormControl,
  Select,
  MenuItem,
  Typography,
  Divider,
  IconButton,
  Paper,
  Tooltip,
} from "@mui/material";

import ColorModeContext from "../../service/TemaContext";

import ResponsavelNegocio from "../ResponsavelNegocio/ResponsavelNegocio";

import AddCircleOutlineOutlinedIcon from "@mui/icons-material/AddCircleOutlineOutlined";
import CloseIcon from "@mui/icons-material/Close";
import MicNoneOutlinedIcon from "@mui/icons-material/MicNoneOutlined";
import MicOutlinedIcon from "@mui/icons-material/MicOutlined";

import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";

import ResponsavelNegocioService from "../../service/responsavelNegocioService";
import AnexoService from "../../service/anexoService";

const FormularioGeralProposta = (props) => {
  // Contexto para trocar a linguagem
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // Variável para alterar o tema
  const { mode } = useContext(ColorModeContext);

  // Variável para o input de anexos
  const inputFile = useRef(null);

  /** Função para criar um responsável no banco e receber o id para adicionar na lista */
  const adicionarResponsavel = () => {
    ResponsavelNegocioService.post({ nome: "", area: "" }).then((response) => {
      props.setGerais({
        ...props.gerais,
        responsaveisNegocio: [
          ...props.gerais.responsaveisNegocio,
          {
            id: response.id,
            nome: "",
            area: "",
          },
        ],
      });
    });
  };

  // Função para remover um responsável
  const deleteResponsavel = (indexResponsavel) => {
    ResponsavelNegocioService.delete(
      props.gerais.responsaveisNegocio[indexResponsavel].id
    ).then((response) => {
      let listaNova = [...props.gerais.responsaveisNegocio];
      listaNova.splice(indexResponsavel, 1);
      props.setGerais({ ...props.gerais, responsaveisNegocio: [...listaNova] });
    });
  };

  // Aciona o input de anexos ao clicar no add anexos
  const onAddAnexoButtonClick = () => {
    inputFile.current.click();
  };

  // Coloca o arquivo selecionado no input no state de anexos
  const onFilesSelect = () => {
    for (let file of inputFile.current.files) {
      AnexoService.save(file).then((response) => {
        props.setDados({
          ...props.dados,
          anexo: [...props.dados.anexo, response],
        });
      });
    }
  };

  // // ********************************************** Gravar audio **********************************************

  const recognitionRef = useRef(null);

  const [escutar, setEscutar] = useState(false);

  const [localClique, setLocalClique] = useState("");

  const ouvirAudio = () => {
    // Verifica se a API é suportada pelo navegador
    if ("webkitSpeechRecognition" in window) {
      const recognition = new window.webkitSpeechRecognition();
      recognition.continuous = true;
      switch (texts.linguagem) {
        case "pt":
          recognition.lang = "pt-BR";
          break;
        case "en":
          recognition.lang = "en-US";
          break;
        case "es":
          recognition.lang = "es-ES";
          break;
        case "ch":
          recognition.lang = "cmn-Hans-CN";
          break;
        default:
          recognition.lang = "pt-BR";
          break;
      }

      recognition.onstart = () => {
        // console.log("Reconhecimento de fala iniciado. Fale algo...");
      };

      recognition.onresult = (event) => {
        const transcript =
          event.results[event.results.length - 1][0].transcript;
        switch (localClique) {
          case "qtdPaybackSimples":
            props.setGerais({
              ...props.gerais,
              qtdPaybackSimples: transcript,
            });
            break;
          case "ppm":
            props.setGerais({
              ...props.gerais,
              ppm: transcript,
            });
            break;
          case "linkJira":
            props.setGerais({
              ...props.gerais,
              linkJira: transcript,
            });
            break;
          default:
            break;
        }
      };

      recognition.onerror = (event) => {
        props.setFeedbackErroReconhecimentoVoz(true);
        setEscutar(false);
      };

      recognitionRef.current = recognition;
      recognition.start();
    } else {
      props.setFeedbackErroNavegadorIncompativel(true);
      setEscutar(false);
    }
  };

  const stopRecognition = () => {
    if (recognitionRef.current) {
      recognitionRef.current.stop();
      // console.log("Reconhecimento de fala interrompido.");
    }
  };

  const startRecognition = (ondeClicou) => {
    setEscutar(!escutar);
    setLocalClique(ondeClicou);
  };

  useEffect(() => {
    if (escutar) {
      ouvirAudio();
    } else {
      stopRecognition();
    }
  }, [escutar]);

  // // ********************************************** Fim Gravar audio **********************************************

  return (
    <Box className="flex flex-col">
      <Box className="mt-12" sx={{ minWidth: "55rem" }}>
        <Box
          className="flex justify-around mb-5 mt-10 "
          sx={{
            marginLeft: "6.1rem",
            display: "grid",
            gap: "1rem",
            gridTemplateColumns: "repeat(auto-fit, minmax(380px, 1fr))",
          }}
        >
          <Box>
            <Box className="flex mb-2">
              <Typography sx={{ fontSize: FontConfig.big, fontWeight: "600" }}>
                {texts.formularioGeralProposta.periodoDeExecucao}:
              </Typography>
              <Typography
                sx={{
                  fontSize: FontConfig.big,
                  color: "red",
                  marginLeft: "5px",
                }}
              >
                *
              </Typography>
            </Box>
            <Box className="flex">
              <Box className="mr-5">
                <Box
                  fontSize={FontConfig.medium}
                  color="text.primary"
                  className="flex outline-none border-solid border px-1 py-1.5 drop-shadow-sm rounded border-l-4"
                  sx={{
                    width: "100%;",
                    height: "30px",
                    backgroundColor: "background.default",
                    borderLeftColor: "primary.main",
                    colorScheme: mode,
                  }}
                  component="input"
                  type="date"
                  placeholder={texts.formularioGeralProposta.digiteCodigo}
                  value={props.gerais.periodoExecucacaoInicio || ""}
                  onChange={(e) =>
                    props.setGerais({
                      ...props.gerais,
                      periodoExecucacaoInicio: e.target.value,
                    })
                  }
                />
              </Box>
              <Box>
                <Typography sx={{ fontSize: FontConfig.big }}>
                  {texts.formularioGeralProposta.a}
                </Typography>
              </Box>
              <Box className="ml-5">
                <Box
                  fontSize={FontConfig.medium}
                  color="text.primary"
                  className="flex outline-none border-solid border px-1 py-1.5 drop-shadow-sm rounded border-l-4"
                  sx={{
                    width: "100%;",
                    height: "30px",
                    backgroundColor: "background.default",
                    borderLeftColor: "primary.main",
                    colorScheme: mode,
                  }}
                  component="input"
                  type="date"
                  placeholder={texts.formularioGeralProposta.digiteCodigo}
                  value={props.gerais.periodoExecucacaoFim || ""}
                  onChange={(e) =>
                    props.setGerais({
                      ...props.gerais,
                      periodoExecucacaoFim: e.target.value,
                    })
                  }
                />
              </Box>
            </Box>
          </Box>
          <Box>
            <Box className="flex mb-2">
              <Typography sx={{ fontSize: FontConfig.big, fontWeight: "600" }}>
                {texts.formularioGeralProposta.paybackSimples}:
              </Typography>
              <Typography
                sx={{
                  fontSize: FontConfig.big,
                  color: "red",
                  marginLeft: "5px",
                }}
              >
                *
              </Typography>
            </Box>
            <Box className="flex">
              <Box
                className="flex items-center justify-between border-solid border px-1 py-1.5 drop-shadow-sm rounded border-l-4"
                sx={{
                  backgroundColor: "background.default",
                  width: "17%",
                  height: "30px",
                  borderLeftColor: "primary.main",
                }}
              >
                <Box
                  fontSize={FontConfig.medium}
                  color="text.primary"
                  className="flex outline-none"
                  sx={{
                    width: "90%",
                    backgroundColor: "trasnparent",
                  }}
                  component="input"
                  placeholder={texts.formularioGeralProposta.quantidade}
                  value={props.gerais.qtdPaybackSimples || ""}
                  onChange={(e) =>
                    props.setGerais({
                      ...props.gerais,
                      qtdPaybackSimples: e.target.value,
                    })
                  }
                />
                <Tooltip
                  className="hover:cursor-pointer"
                  title={texts.homeGerencia.gravarAudio}
                  onClick={() => {
                    startRecognition("qtdPaybackSimples");
                  }}
                >
                  {escutar && localClique == "qtdPaybackSimples" ? (
                    <MicOutlinedIcon
                      sx={{ color: "primary.main", fontSize: "1.3rem" }}
                    />
                  ) : (
                    <MicNoneOutlinedIcon
                      sx={{ color: "text.secondary", fontSize: "1.3rem" }}
                    />
                  )}
                </Tooltip>
              </Box>
              <FormControl
                variant="standard"
                sx={{ marginLeft: "20px", minWidth: 100 }}
              >
                <Select
                  labelId="demo-simple-select-standard-label"
                  id="demo-simple-select-standard"
                  value={props.gerais.unidadePaybackSimples || ""}
                  onChange={(e) =>
                    props.setGerais({
                      ...props.gerais,
                      unidadePaybackSimples: e.target.value,
                    })
                  }
                >
                  <MenuItem value={"DIAS"}>
                    {texts.formularioGeralProposta.dias}
                  </MenuItem>
                  <MenuItem value={"SEMANAS"}>
                    {texts.formularioGeralProposta.semanas}
                  </MenuItem>
                  <MenuItem value={"MESES"}>
                    {texts.formularioGeralProposta.meses}
                  </MenuItem>
                </Select>
              </FormControl>
            </Box>
          </Box>
          <Box>
            <Box className="flex mb-2">
              <Typography sx={{ fontSize: FontConfig.big, fontWeight: "600" }}>
                {texts.formularioGeralProposta.codigoPpm}:
              </Typography>
              <Typography
                sx={{
                  fontSize: FontConfig.big,
                  color: "red",
                  marginLeft: "5px",
                }}
              >
                *
              </Typography>
            </Box>
            <Box className="flex">
              <Box
                className="flex items-center justify-between border-solid border px-1 py-1.5 drop-shadow-sm rounded border-l-4"
                sx={{
                  width: "90%;",
                  height: "30px",
                  backgroundColor: "background.default",
                  borderLeftColor: "primary.main",
                }}
              >
                <Box
                  fontSize={FontConfig.medium}
                  color="text.primary"
                  className="flex outline-none"
                  sx={{
                    width: "95%;",
                    backgroundColor: "transparent",
                  }}
                  component="input"
                  placeholder={texts.formularioGeralProposta.digiteCodigo}
                  value={props.gerais.ppm || ""}
                  onChange={(e) =>
                    props.setGerais({
                      ...props.gerais,
                      ppm: e.target.value,
                    })
                  }
                />
                <Tooltip
                  className="hover:cursor-pointer"
                  title={texts.homeGerencia.gravarAudio}
                  onClick={() => {
                    startRecognition("ppm");
                  }}
                >
                  {escutar && localClique == "ppm" ? (
                    <MicOutlinedIcon
                      sx={{ color: "primary.main", fontSize: "1.3rem" }}
                    />
                  ) : (
                    <MicNoneOutlinedIcon
                      sx={{ color: "text.secondary", fontSize: "1.3rem" }}
                    />
                  )}
                </Tooltip>
              </Box>
            </Box>
          </Box>
        </Box>
        <Box className="flex flex-col mb-8" sx={{ marginLeft: "6.1rem" }}>
          <Box className="flex mb-2">
            <Typography sx={{ fontSize: FontConfig.big, fontWeight: "600" }}>
              {texts.formularioGeralProposta.linkDoJira}:
            </Typography>
            <Typography
              sx={{
                fontSize: FontConfig.big,
                color: "red",
                marginLeft: "5px",
              }}
            >
              *
            </Typography>
          </Box>
          <Box sx={{ width: "30rem" }}>
            <Box
              className="flex items-center justify-between border-solid border px-1 py-1.5 drop-shadow-sm rounded border-l-4"
              sx={{
                width: "100%;",
                height: "30px",
                backgroundColor: "background.default",
                borderLeftColor: "primary.main",
              }}
            >
              <Box
                fontSize={FontConfig.medium}
                color="text.primary"
                className="flex outline-none"
                sx={{
                  width: "95%;",
                  backgroundColor: "transparent",
                }}
                component="input"
                placeholder={texts.formularioGeralProposta.insiraLinkDoJira}
                value={props.gerais.linkJira || ""}
                onChange={(e) =>
                  props.setGerais({
                    ...props.gerais,
                    linkJira: e.target.value,
                  })
                }
              />
              <Tooltip
                className="hover:cursor-pointer"
                title={texts.homeGerencia.gravarAudio}
                onClick={() => {
                  startRecognition("linkJira");
                }}
              >
                {escutar && localClique == "linkJira" ? (
                  <MicOutlinedIcon
                    sx={{ color: "primary.main", fontSize: "1.3rem" }}
                  />
                ) : (
                  <MicNoneOutlinedIcon
                    sx={{ color: "text.secondary", fontSize: "1.3rem" }}
                  />
                )}
              </Tooltip>
            </Box>
          </Box>
        </Box>
        <Divider />
        <Box className="flex flex-col mb-8" sx={{ marginLeft: "6.1rem" }}>
          <Box className="flex mt-8 items-center">
            <Typography
              sx={{
                fontSize: FontConfig.veryBig,
                fontWeight: "600",
                marginRight: "5px",
                color: "primary.main",
              }}
            >
              {texts.formularioGeralProposta.responsavelResponsaveis}:
            </Typography>
            <Typography
              sx={{
                fontSize: FontConfig.veryBig,
                fontWeight: "600",
                marginRight: "8px",
                color: "red",
              }}
            >
              *
            </Typography>
            <Tooltip
              title={
                texts.formularioGeralProposta.titleAdicionarNovoResponsavel
              }
            >
              <AddCircleOutlineOutlinedIcon
                className="delay-120 hover:scale-110 duration-300"
                sx={{ color: "primary.main", cursor: "pointer" }}
                onClick={() => {
                  adicionarResponsavel();
                }}
              />
            </Tooltip>
          </Box>
          {props.gerais.responsaveisNegocio?.map((item, index) => {
            return (
              <ResponsavelNegocio
                dados={item}
                setDados={(event) => {
                  let aux = [...props.gerais.responsaveisNegocio];
                  aux[index] = event;
                  props.setGerais({
                    ...props.gerais,
                    responsaveisNegocio: [...aux],
                  });
                }}
                index={index}
                deleteResponsavel={deleteResponsavel}
                key={index}
                setFeedbackErroReconhecimentoVoz={props.setFeedbackErroReconhecimentoVoz}
                setFeedbackErroNavegadorIncompativel={props.setFeedbackErroNavegadorIncompativel}
              />
            );
          })}
        </Box>
        <Divider />
        <Box sx={{ marginLeft: "6.1rem" }}>
          <Box className="flex items-center mt-8">
            <Box className="w-full">
              <Typography
                sx={{
                  fontSize: FontConfig.big,
                  fontWeight: "600",
                  marginRight: "5px",
                }}
              >
                {texts.formularioGeralProposta.anexos}:
                <Tooltip
                  title={texts.formularioGeralProposta.adicionarNovoAnexo}
                >
                  <AddCircleOutlineOutlinedIcon
                    className="ml-1 delay-120 hover:scale-110 duration-300"
                    sx={{ color: "icon.main", cursor: "pointer" }}
                    onClick={onAddAnexoButtonClick}
                  />
                </Tooltip>
              </Typography>
              <input
                onChange={onFilesSelect}
                ref={inputFile}
                type="file"
                multiple
                hidden
              />
              {props.dados.anexo.length > 0 ? (
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
                    >
                      <Typography
                        sx={{
                          color: "text.primary",
                          fontSize: FontConfig.default,
                        }}
                      >
                        {anexo.name || anexo.nome}
                      </Typography>
                      <IconButton
                        onClick={() =>
                          props.setDados({
                            titulo: props.dados.titulo,
                            status: props.dados.status,
                            problema: props.dados.problema,
                            proposta: props.dados.proposta,
                            beneficios: props.dados.beneficios,
                            frequencia: props.dados.frequencia,
                            anexo: props.dados.anexo.filter(
                              (anexo, i) => i !== index
                            ),
                            solicitante: props.dados.solicitante,
                            analista: props.dados.analista,
                            gerente: props.dados.gerente,
                            buSolicitante: props.dados.buSolicitante,
                            busBeneficiadas: props.dados.busBeneficiadas,
                            data: props.dados.data,
                            departamento: props.dados.departamento,
                            forum: props.dados.forum,
                            secaoTI: props.dados.secaoTI,
                            tamanho: props.dados.tamanho,
                          })
                        }
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
                  {texts.formularioGeralProposta.nenhumAnexoAdicionado}
                </Typography>
              )}
            </Box>
          </Box>
        </Box>
      </Box>
    </Box>
  );
};

export default FormularioGeralProposta;
