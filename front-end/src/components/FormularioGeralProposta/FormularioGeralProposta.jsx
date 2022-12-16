import React, { useState, useContext, useRef } from "react";
import {
  Box,
  FormControl,
  Select,
  MenuItem,
  Typography,
  Divider,
  IconButton,
  Paper,
} from "@mui/material";

import ColorModeContext from "../../service/TemaContext";

import FontConfig from "../../service/FontConfig";

import ResponsavelNegocio from "../ResponsavelNegocio/ResponsavelNegocio";

import AddCircleOutlineOutlinedIcon from "@mui/icons-material/AddCircleOutlineOutlined";
import CloseIcon from "@mui/icons-material/Close";

const FormularioGeralProposta = (props) => {
  const { mode } = useContext(ColorModeContext);
  const [responsavelNegocio, setResponsavelNegocio] = useState([
    { nome: "", area: "", visible: true },
  ]);
  const inputFile = useRef(null);

  const deleteResponsavel = (indexResponsavel) => {
    let aux = props.responsaveisNegocio.map((responsavel) => {
      return {
        nome: responsavel.nome,
        area: responsavel.area,
        visible: responsavel.visible,
      };
    });
    aux[indexResponsavel].visible = false;
    props.setResponsaveisNegocio(aux);
  };

  // Aciona o input de anexos ao clicar no add anexos
  const onAddAnexoButtonClick = () => {
    inputFile.current.click();
  };

  // Coloca o arquivo selecionado no input no state de anexos
  const onFilesSelect = () => {
    for (let file of inputFile.current.files) {
      props.setGerais({
        ...props.gerais,
        anexos: [...props.gerais.anexos, file],
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
                Período de execução:
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
                  placeholder="Digite o código..."
                  value={props.gerais.periodoExecucacaoInicio}
                  onChange={(e) =>
                    props.setGerais({
                      ...props.gerais,
                      periodoExecucacaoInicio: e.target.value,
                    })
                  }
                />
              </Box>
              <Box>
                <Typography sx={{ fontSize: FontConfig.big }}>à</Typography>
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
                  placeholder="Digite o código..."
                  value={props.gerais.periodoExecucacaoFim}
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
                Payback simples:
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
                placeholder="Qtd"
                value={props.gerais.qtdPaybackSimples}
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
                  value={props.gerais.unidadePaybackSimples}
                  onChange={(e) => props.setGerais({...props.gerais, unidadePaybackSimples: e.target.value})}
                >
                  <MenuItem value={"DIAS"}>Dias</MenuItem>
                  <MenuItem value={"SEMANAS"}>Semanas</MenuItem>
                  <MenuItem value={"MESES"}>Meses</MenuItem>
                </Select>
              </FormControl>
            </Box>
          </Box>
          <Box>
            <Box className="flex mb-2">
              <Typography sx={{ fontSize: FontConfig.big, fontWeight: "600" }}>
                Código PPM:
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
                placeholder="Digite o código..."
                value={props.gerais.ppm}
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
              Link do jira:
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
              placeholder="Insira o link do jira..."
              value={props.gerais.linkJira}
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
              Responsável/Responsáveis:
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
            <AddCircleOutlineOutlinedIcon
              className="delay-120 hover:scale-110 duration-300"
              sx={{ color: "primary.main", cursor: "pointer" }}
              onClick={() => {
                props.setResponsaveisNegocio([
                  ...props.responsaveisNegocio,
                  {
                    nome: "",
                    area: "",
                    visible: true,
                  },
                ]);
              }}
            />
          </Box>
          {props.responsaveisNegocio?.map((item, index) => {
            if (item.visible) {
              return (
                <ResponsavelNegocio
                  dados={item}
                  index={index}
                  deleteResponsavel={deleteResponsavel}
                />
              );
            }
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
                Anexos:
                <AddCircleOutlineOutlinedIcon
                  className="ml-1 delay-120 hover:scale-110 duration-300"
                  sx={{ color: "icon.main", cursor: "pointer" }}
                  onClick={onAddAnexoButtonClick}
                />
              </Typography>
              <input
                onChange={onFilesSelect}
                ref={inputFile}
                type="file"
                multiple
                hidden
              />
              {props.gerais.anexos.length > 0 ? (
                <Box className="flex flex-col gap-2">
                  {props.gerais.anexos.map((anexo, index) => (
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
                        {anexo.name}
                      </Typography>
                      <IconButton
                        onClick={() =>
                          props.setGerais({
                            ...props.gerais,
                            anexos: props.gerais.anexos.filter(
                              (anexo, i) => i !== index
                            ),
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
                  Nenhum anexo adicionado
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
