import React, { useContext, useRef, useEffect } from "react";

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

import ResponsavelNegocio from "../../ResponsavelNegocio/ResponsavelNegocio";

import AddCircleOutlineOutlinedIcon from "@mui/icons-material/AddCircleOutlineOutlined";
import CloseIcon from "@mui/icons-material/Close";
import MicNoneOutlinedIcon from "@mui/icons-material/MicNoneOutlined";
import MicOutlinedIcon from "@mui/icons-material/MicOutlined";

import ColorModeContext from "../../../service/TemaContext";
import FontContext from "../../../service/FontContext";
import TextLanguageContext from "../../../service/TextLanguageContext";
import SpeechSynthesisContext from "../../../service/SpeechSynthesisContext";
import { SpeechRecognitionContext } from "../../../service/SpeechRecognitionService";
import ResponsavelNegocioService from "../../../service/responsavelNegocioService";
import AnexoService from "../../../service/anexoService";

// Componente utilizado para adicionar os dados gerais da proposta
const FormularioGeralProposta = (props) => {
  // Contexto para trocar a linguagem
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  // Variável para alterar o tema
  const { mode } = useContext(ColorModeContext);

  /** Context para ler o texto da tela */
  const { lerTexto } = useContext(SpeechSynthesisContext);

  /** Context para obter a função de leitura de texto */
  const { startRecognition, escutar, localClique, palavrasJuntas } = useContext(
    SpeechRecognitionContext
  );

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

  /** Função para remover um responsável */
  const deleteResponsavel = (indexResponsavel) => {
    ResponsavelNegocioService.delete(
      props.gerais.responsaveisNegocio[indexResponsavel].id
    ).then((response) => {
      let listaNova = [...props.gerais.responsaveisNegocio];
      listaNova.splice(indexResponsavel, 1);
      props.setGerais({ ...props.gerais, responsaveisNegocio: [...listaNova] });
    });
  };

  /** Aciona o input de anexos ao clicar no add anexos */
  const onAddAnexoButtonClick = () => {
    inputFile.current.click();
  };

  /** Coloca o arquivo selecionado no input no state de anexos */
  const onFilesSelect = async () => {
    const responses = await Promise.all(
      Array.from(inputFile.current.files).map((file) => AnexoService.save(file))
    );

    props.setDados({
      ...props.dados,
      anexo: [...props.dados.anexo, ...responses],
    });
  };

  /** useEffect utilizado para leitura de voz no local clicado */
  useEffect(() => {
    switch (localClique) {
      case "qtdPaybackSimples":
        props.setGerais({
          ...props.gerais,
          qtdPaybackSimples: palavrasJuntas,
        });
        break;
      case "ppm":
        props.setGerais({
          ...props.gerais,
          ppm: palavrasJuntas,
        });
        break;
      case "linkJira":
        props.setGerais({
          ...props.gerais,
          linkJira: palavrasJuntas,
        });
        break;
      default:
        break;
    }
  }, [palavrasJuntas]);

  return (
    <Box className="flex flex-col">
      <Box className="mt-12" sx={{ minWidth: "55rem" }}>
        <Box
          className="flex flex-row mb-8 "
          sx={{ marginLeft: "6.1rem", marginTop: "2%" }}
        >
          {/* Box geral do formulário */}
          <Box sx={{ width: "50%" }}>
            {/* Período Execução */}
            <Box className="flex mb-2 w-1/2">
              <Typography
                sx={{ fontSize: FontConfig.big, fontWeight: "600" }}
                onClick={() => {
                  lerTexto(texts.formularioGeralProposta.periodoDeExecucao);
                }}
              >
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
            <Box className="flex w-full">
              <Box className="mr-5" sx={{ width: "30%" }}>
                <Box
                  fontSize={FontConfig.medium}
                  color="text.primary"
                  className="flex outline-none border-solid border px-1 py-1.5 border-l-4"
                  sx={{
                    width: "100%;",
                    height: "40px",
                    backgroundColor: "background.default",
                    borderLeftColor: "primary.main",
                    colorScheme: mode,
                  }}
                  component="input"
                  type="date"
                  placeholder={texts.formularioGeralProposta.digiteCodigo}
                  value={props.gerais.periodoExecucacaoInicio}
                  onChange={(e) => {
                    props.setGerais({
                      ...props.gerais,
                      periodoExecucacaoInicio: e.target.value,
                    });
                  }}
                />
              </Box>
              <Box sx={{ display: "flex", alignItems: "center" }}>
                <Typography
                  sx={{ fontSize: FontConfig.big }}
                  onClick={() => {
                    lerTexto(texts.formularioGeralProposta.a);
                  }}
                >
                  {texts.formularioGeralProposta.a}
                </Typography>
              </Box>
              <Box className="ml-5" sx={{ width: "30%" }}>
                <Box
                  fontSize={FontConfig.medium}
                  color="text.primary"
                  className="flex outline-none border-solid border px-1 py-1.5 border-l-4"
                  sx={{
                    width: "100%;",
                    height: "40px",
                    backgroundColor: "background.default",
                    borderLeftColor: "primary.main",
                    colorScheme: mode,
                  }}
                  component="input"
                  type="date"
                  placeholder={texts.formularioGeralProposta.digiteCodigo}
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

          <Box sx={{ width: "50%" }}>
            {/* Payback */}
            <Box className="flex mb-2">
              <Typography
                sx={{ fontSize: FontConfig.big, fontWeight: "600" }}
                onClick={() => {
                  lerTexto(texts.formularioGeralProposta.paybackSimples);
                }}
              >
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
                className="flex items-center justify-between border-solid border px-1 py-1.5 border-l-4"
                sx={{
                  backgroundColor: "background.default",
                  width: "20%",
                  height: "40px",
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
              <FormControl
                variant="standard"
                sx={{ marginLeft: "30px", minWidth: 110 }}
              >
                <Select
                  className="w-4/5 border-solid border-t border-r pl-1 text-center"
                  sx={{
                    borderLeft: "4px solid #00579d",
                    height: "40px",
                  }}
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
        </Box>

        {/* Link Jira */}
        <Box className="flex flex-row mb-8 " sx={{ marginLeft: "6.1rem" }}>
          <Box sx={{ width: "50%" }}>
            <Box className="flex mb-2">
              <Typography
                sx={{ fontSize: FontConfig.big, fontWeight: "600" }}
                onClick={() => {
                  lerTexto(texts.formularioGeralProposta.linkDoJira);
                }}
              >
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
            <Box sx={{ width: "67%" }}>
              <Box
                className="flex items-center justify-between border-solid border px-1 py-1.5 border-l-4"
                sx={{
                  width: "100%;",
                  height: "40px",
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
          </Box>

          <Box sx={{ width: "50%" }}>
            {/* Código PPM */}
            <Box className="flex mb-2">
              <Typography
                sx={{ fontSize: FontConfig.big, fontWeight: "600" }}
                onClick={() => {
                  lerTexto(texts.formularioGeralProposta.codigoPpm);
                }}
              >
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
                className="flex items-center justify-between border-solid border px-1 py-1.5 border-l-4"
                sx={{
                  width: "40%;",
                  height: "40px",
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
          </Box>
        </Box>

        <Divider />

        {/* Responsável negócio */}
        <Box className="flex flex-col mb-8" sx={{ marginLeft: "6.1rem" }}>
          <Box className="flex mt-8 items-center">
            <Typography
              sx={{
                fontSize: FontConfig.veryBig,
                fontWeight: "600",
                marginRight: "5px",
                color: "primary.main",
              }}
              onClick={() => {
                lerTexto(texts.formularioGeralProposta.responsavelResponsaveis);
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
                onClick={() => {
                  lerTexto(texts.formularioGeralProposta.anexos);
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
              {/* Adicionar anexos */}
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
                      elevation={0}
                      className="flex justify-between items-center mt-2 px-2 border border-l-4"
                      sx={{ borderLeftColor: "primary.main" }}
                      square
                    >
                      <Typography
                        sx={{
                          color: "text.primary",
                          fontSize: FontConfig.default,
                        }}
                        onClick={() => {
                          lerTexto(anexo.name || anexo.nome);
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
                  onClick={() => {
                    lerTexto(
                      texts.formularioGeralProposta.nenhumAnexoAdicionado
                    );
                  }}
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
