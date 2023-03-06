import React, { useContext, useRef } from "react";
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

import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";

import ResponsavelNegocioService from "../../service/responsavelNegocioService";

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
      props.setDados({
        titulo: props.dados.titulo,
        status: props.dados.status,
        problema: props.dados.problema,
        proposta: props.dados.proposta,
        beneficios: props.dados.beneficios,
        frequencia: props.dados.frequencia,
        anexo: [...props.dados.anexo, file],
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
      });
    }
  };

  return (
    <Box className="flex flex-col">
      <Box className="mt-12">
        <Box className="flex w-full justify-around mb-5 mt-10">
          <Box>
            <Box className="flex mb-2">
              <Typography sx={{ fontSize: FontConfig.big, fontWeight: "600" }}>
                {texts.formularioGeralProposta.periodoExecucacao}:
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
                <Typography sx={{ fontSize: FontConfig.big }}>{texts.formularioGeralProposta.a}</Typography>
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
                fontSize={FontConfig.medium}
                color="text.primary"
                className="flex outline-none border-solid border px-1 py-1.5 drop-shadow-sm rounded border-l-4"
                sx={{
                  width: "20%;",
                  height: "30px",
                  backgroundColor: "background.default",
                  borderLeftColor: "primary.main",
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
                  <MenuItem value={"DIAS"}>{texts.formularioGeralProposta.dias}</MenuItem>
                  <MenuItem value={"SEMANAS"}>{texts.formularioGeralProposta.semanas}</MenuItem>
                  <MenuItem value={"MESES"}>{texts.formularioGeralProposta.meses}</MenuItem>
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
                fontSize={FontConfig.medium}
                color="text.primary"
                className="flex outline-none border-solid border px-1 py-1.5 drop-shadow-sm rounded border-l-4"
                sx={{
                  width: "100%;",
                  height: "30px",
                  backgroundColor: "background.default",
                  borderLeftColor: "primary.main",
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
              fontSize={FontConfig.medium}
              color="text.primary"
              className="flex outline-none border-solid border px-1 py-1.5 drop-shadow-sm rounded border-l-4"
              sx={{
                width: "100%;",
                height: "30px",
                backgroundColor: "background.default",
                borderLeftColor: "primary.main",
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
            <Tooltip title={texts.formularioGeralProposta.titleAdicionarNovoResponsavel}>
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
                <Tooltip title={texts.formularioGeralProposta.adicionarNovoAnexo}>
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
