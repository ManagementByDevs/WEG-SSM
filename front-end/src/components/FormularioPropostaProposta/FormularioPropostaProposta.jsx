import React, { useState, useContext, useEffect, useRef } from "react";
import { Box, Typography, Divider, TextareaAutosize, Paper, IconButton, Tooltip, MenuItem, TextField, Autocomplete, Checkbox } from "@mui/material";

import AddCircleOutlineOutlinedIcon from "@mui/icons-material/AddCircleOutlineOutlined";
import CloseIcon from "@mui/icons-material/Close";
import DownloadIcon from "@mui/icons-material/Download";
import CheckBoxOutlineBlankIcon from "@mui/icons-material/CheckBoxOutlineBlank";
import CheckBoxIcon from "@mui/icons-material/CheckBox";

import BeneficiosDetalheDemanda from "../../components/BeneficiosDetalheDemanda/BeneficiosDetalheDemanda";
import Feedback from "../../components/Feedback/Feedback";

import ColorModeContext from "../../service/TemaContext";
import BeneficioService from "../../service/beneficioService";
import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";

import AnexoService from "../../service/anexoService";

import ClipLoader from 'react-spinners/ClipLoader';

// Ícone selecionado e não selecionado, para o checkbox
const icon = <CheckBoxOutlineBlankIcon fontSize="small" />;
const checkedIcon = <CheckBoxIcon fontSize="small" />;

/** Fase de criação de proposta em que os dados da demanda poderão ser editados */
const FormularioPropostaProposta = (props) => {

  // Contexto para trocar a linguagem
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // UseState para mudar a cor do textArea
  const [corFundoTextArea, setCorFundoTextArea] = useState("#FFFF");

  // Variável para alterar o tema
  const { mode } = useContext(ColorModeContext);

  /** Variável booleana para determinar se o feedback de anexo já existente está ativo */
  const [feedbackAnexoExistente, setFeedbackAnexoExistente] = useState(false);

  /** Variável de referência para ativar a escolha de anexos */
  const inputFile = useRef(null);

  // UseEffect para alterar a cor do textArea (modo escuro / claro)
  useEffect(() => {
    if (mode === "dark") {
      setCorFundoTextArea("#212121");
    } else {
      setCorFundoTextArea("#FFFF");
    }
  }, [mode]);

  /** Função para atualizar os dados da demanda quando algum input for modificado */
  const alterarTexto = (e, input) => {
    if (input === "titulo") {
      props.setDadosDemanda({ ...props.dados, titulo: e.target.value });
    } else if (input === "problema") {
      props.setDadosDemanda({ ...props.dados, problema: e.target.value });
    } else if (input === "proposta") {
      props.setDadosDemanda({ ...props.dados, proposta: e.target.value });
    } else if (input === "frequencia") {
      props.setDadosDemanda({ ...props.dados, frequencia: e.target.value });
    } else if (input === "tamanho") {
      props.setDadosDemanda({ ...props.dados, tamanho: e.target?.value });
    } else if (input == "secao") {
      props.setDadosDemanda({ ...props.dados, secaoTI: e });
    } else if (input == "buSolicitante") {
      props.setDadosDemanda({ ...props.dados, buSolicitante: e });
    } else if (input == "busBeneficiadas") {
      props.setDadosDemanda({ ...props.dados, busBeneficiadas: e });
    } else if (input == "forum") {
      props.setDadosDemanda({ ...props.dados, forum: e });
    }
  };

  /** Função para acionar o botão do tipo file */
  const clickAdicionarAnexo = () => {
    inputFile.current.click();
  };

  /** Função para ver se um novo anexo recebido já existe na lista de anexos, ativando o feedback caso positivo */
  const verificaAnexoExistente = (anexo) => {
    return (
      props.dados.anexo.filter((anexoItem) => {
        return (
          anexoItem.nome == anexo.name ||
          (anexo.id && anexoItem.nome == anexo.nome)
        );
      }).length > 0
    );
  };

  /** Função acionada quando o usuário adiciona algum anexo, salvando ele no banco de dados e na lista da demanda */
  const salvarAnexo = () => {
    for (let file of inputFile.current.files) {
      if (!verificaAnexoExistente(file)) {
        AnexoService.save(file).then((response) => {
          props.setDadosDemanda({ ...props.dados, anexo: [...props.dados.anexo, response] });
        })
      } else {
        setFeedbackAnexoExistente(true);
      }
    }
  };

  /** Função para remover um anexo da lista de anexos e excluí-lo do banco de dados */
  const removerAnexo = (index) => {
    const arquivo = props.dados.anexo[index];
    AnexoService.deleteById(arquivo.id).then((response) => {
      let listaNova = [...props.dados.anexo];
      listaNova.splice(index, 1);
      props.setDadosDemanda({ ...props.dados, anexo: listaNova })
    })
  };

  // Função que cria um benefício no banco e usa o id nele em um objeto novo na lista da página
  const adicionarBeneficio = () => {
    BeneficioService.post({
      tipoBeneficio: "REAL",
      valor_mensal: "",
      moeda: "",
      memoriaCalculo: "",
    }).then((response) => {
      let beneficioNovo = {
        id: response.id,
        tipoBeneficio: "REAL",
        valor_mensal: "",
        moeda: "",
        memoriaCalculo: "",
        visible: true,
      };

      try {
        props.setBeneficios([...props.beneficios, beneficioNovo]);
      } catch (error) {
        props.setBeneficios([beneficioNovo]);
      }
    });
  };

  // Função para atualizar o benefício quando ele recebe alguma alteração
  const alterarTextoBeneficio = (beneficio, index) => {
    let aux = props.beneficios.map((beneficio) => {
      return {
        id: beneficio.id,
        tipoBeneficio: beneficio.tipoBeneficio,
        valor_mensal: beneficio.valor_mensal,
        moeda: beneficio.moeda,
        memoriaCalculo: beneficio.memoriaCalculo,
        visible: beneficio.visible,
      };
    });
    aux[index] = beneficio;
    props.setBeneficios(aux);
  };

  /** Função para excluir um benefício da lista e do banco de dados, recebendo seu index na lista */
  const deleteBeneficio = (indexBeneficio) => {
    let listaNova = [...props.beneficios];
    let beneficioExcluido = (listaNova.splice(indexBeneficio, 1))[0];

    BeneficioService.delete(beneficioExcluido.id).then((response) => {
      props.setBeneficiosExcluidos([
        ...props.beneficiosExcluidos,
        beneficioExcluido
      ]);
      props.setBeneficios(listaNova);
    });
  };

  // Função para converter uma string em base64 para um ArrayBuffer
  function base64ToArrayBuffer(base64) {
    const binaryString = window.atob(base64);
    const bytes = new Uint8Array(binaryString.length);
    return bytes.map((byte, i) => binaryString.charCodeAt(i));
  }

  // Função para baixar um anexo
  const baixarAnexo = (index) => {
    const file = props.dados.anexo[index];
    let blob;
    let fileName;

    if (props.dados.anexo[index] instanceof File) {
      blob = file;
      fileName = file.name;
    } else {
      blob = new Blob([base64ToArrayBuffer(file.dados)]);
      fileName = `${file.nome}`;
    }

    if (navigator.msSaveBlob) {
      navigator.msSaveBlob(blob, fileName);
    } else {
      const link = document.createElement("a");
      if (link.download !== undefined) {
        const url = URL.createObjectURL(blob);
        link.setAttribute("href", url);
        link.setAttribute("download", fileName);
        link.style.visibility = "hidden";
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
        URL.revokeObjectURL(url);
      }
    }
  };

  return (
    <>
      {/* Feedback anexos existente */}
      <Feedback
        open={feedbackAnexoExistente}
        handleClose={() => {
          setFeedbackAnexoExistente(false);
        }}
        status={"erro"}
        mensagem={texts.formularioPropostaProposta.feedbacks.feedback1}
      />
      <Box
        className="flex flex-col justify-center relative items-center"
        sx={{ minWidth: "60rem", marginTop: "4rem", marginBottom: "3rem" }}
      >
        {props.carregamento ? (
          <Box className="mt-6 w-full h-full flex justify-center items-center">
            <ClipLoader color="#00579D" size={110} />
          </Box>
        ) : (
          <Box
            className="flex flex-col gap-5 border rounded relative p-10 drop-shadow-lg"
            sx={{ width: "55rem" }}
          >
            <>
              <Box className="flex justify-center">
                <Box
                  value={props.dados.titulo}
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
                  placeholder={
                    texts.formularioPropostaProposta.digiteTituloDaDemanda
                  }
                />
              </Box>
              <Divider />
              <Box>
                <Typography
                  fontSize={FontConfig.veryBig}
                  fontWeight="600"
                  color="text.primary"
                >
                  {texts.formularioPropostaProposta.problema}:
                </Typography>
                <TextareaAutosize
                  style={{
                    width: 775,
                    marginLeft: "26px",
                    resize: "none",
                    backgroundColor: corFundoTextArea,
                  }}
                  value={props.dados.problema}
                  fontSize={FontConfig.medium}
                  onChange={(e) => {
                    alterarTexto(e, "problema");
                  }}
                  className="flex outline-none border-solid border px-1 py-1.5 drop-shadow-sm rounded text-center text-justify"
                  placeholder={texts.formularioPropostaProposta.digiteProblema}
                />
              </Box>
              <Box>
                <Typography
                  fontSize={FontConfig.veryBig}
                  fontWeight="600"
                  color="text.primary"
                >
                  {texts.formularioPropostaProposta.proposta}:
                </Typography>
                <TextareaAutosize
                  style={{
                    width: 775,
                    marginLeft: "26px",
                    resize: "none",
                    backgroundColor: corFundoTextArea,
                  }}
                  value={props.dados.proposta}
                  fontSize={FontConfig.medium}
                  onChange={(e) => {
                    alterarTexto(e, "proposta");
                  }}
                  className="flex outline-none border-solid border px-1 py-1.5 drop-shadow-sm rounded text-center text-justify"
                  placeholder={texts.formularioPropostaProposta.digiteProposta}
                />
              </Box>
              <Box>
                <Box className="flex items-center">
                  <Typography
                    fontSize={FontConfig.veryBig}
                    fontWeight="600"
                    color="text.primary"
                  >
                    {texts.formularioPropostaProposta.beneficios}:
                  </Typography>
                  <AddCircleOutlineOutlinedIcon
                    className="delay-120 hover:scale-110 duration-300 ml-1"
                    onClick={() => {
                      adicionarBeneficio();
                    }}
                    sx={{ color: "primary.main", cursor: "pointer" }}
                  />
                </Box>
                <Box className="mt-2 flex flex-col gap-5">
                  {props.beneficios?.map((beneficio, index) => {
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
                  })}
                </Box>
              </Box>
              <Box>
                <Typography
                  fontSize={FontConfig.veryBig}
                  fontWeight="600"
                  color="text.primary"
                >
                  {texts.formularioPropostaProposta.frequenciaDeUso}:
                </Typography>
                <Box
                  value={props.dados.frequencia}
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
                  placeholder={texts.formularioPropostaProposta.digiteFrequencia}
                />
              </Box>
              <Box
                className="flex justify-evenly"
                sx={{ marginTop: "15px", marginBottom: "10px" }}
              >
                <TextField
                  sx={{ width: "45%" }}
                  select
                  label={texts.formularioPropostaProposta.labelTamanho}
                  value={props.dados.tamanho}
                  onChange={(e) => alterarTexto(e, "tamanho")}
                  variant="standard"
                  fullWidth
                >
                  <MenuItem key={"Muito Pequeno"} value={"Muito Pequeno"}>
                    {texts.formularioPropostaProposta.muitoPequeno}
                  </MenuItem>
                  <MenuItem key={"Pequeno"} value={"Pequeno"}>
                    {texts.formularioPropostaProposta.pequeno}
                  </MenuItem>
                  <MenuItem key={"Médio"} value={"Médio"}>
                    {texts.formularioPropostaProposta.medio}
                  </MenuItem>
                  <MenuItem key={"Grande"} value={"Grande"}>
                    {texts.formularioPropostaProposta.grande}
                  </MenuItem>
                  <MenuItem key={"Muito Grande"} value={"Muito Grande"}>
                    {texts.formularioPropostaProposta.muitoGrande}
                  </MenuItem>
                </TextField>

                <Autocomplete
                  sx={{ width: "45%" }}
                  disablePortal
                  options={props.listaSecoesTI}
                  value={props.dados.secaoTI}
                  onChange={(event, value) => {
                    alterarTexto(value, "secao");
                  }}
                  getOptionLabel={(option) => {
                    return option.siglaSecao || "";
                  }}
                  isOptionEqualToValue={(option, value) => {
                    return option?.idSecao == value?.idSecao;
                  }}
                  fullWidth
                  noOptionsText={
                    texts.formularioPropostaProposta.nenhumaSecaoEncontrada
                  }
                  renderInput={(params) => (
                    <TextField
                      variant="standard"
                      {...params}
                      label={texts.formularioPropostaProposta.labelSecaoTi}
                    />
                  )}
                />
              </Box>
              <Box
                className="flex justify-evenly"
                sx={{ marginTop: "15px", marginBottom: "10px" }}
              >
                <Autocomplete
                  sx={{ width: "45%" }}
                  disablePortal
                  options={props.listaBU}
                  value={props.dados.buSolicitante}
                  onChange={(event, value) => {
                    alterarTexto(value, "buSolicitante");
                  }}
                  getOptionLabel={(option) => {
                    return option?.siglaBu || "";
                  }}
                  isOptionEqualToValue={(option, value) => {
                    return option?.idBu == value?.idBu;
                  }}
                  fullWidth
                  noOptionsText={
                    texts.formularioPropostaProposta.nenhumaBuEncontrada
                  }
                  renderInput={(params) => (
                    <TextField
                      variant="standard"
                      {...params}
                      label={texts.formularioPropostaProposta.buSolicitante}
                    />
                  )}
                />

                <Autocomplete
                  sx={{ width: "45%" }}
                  multiple
                  value={props.dados.busBeneficiadas}
                  options={props.listaBU}
                  disableCloseOnSelect
                  onChange={(event, newValue) => {
                    alterarTexto(newValue, "busBeneficiadas");
                  }}
                  getOptionLabel={(option) => option.siglaBu}
                  renderOption={(props, option, { selected }) => (
                    <li {...props}>
                      <Checkbox
                        icon={icon}
                        checkedIcon={checkedIcon}
                        style={{ marginRight: 8 }}
                        checked={selected}
                      />
                      {option.siglaBu}
                    </li>
                  )}
                  isOptionEqualToValue={(option, value) => {
                    return option?.idBu == value?.idBu;
                  }}
                  noOptionsText={
                    texts.formularioPropostaProposta.nenhumaBuEncontrada
                  }
                  renderInput={(params) => (
                    <TextField
                      {...params}
                      label={texts.formularioPropostaProposta.labelBusBeneficiadas}
                      variant="standard"
                      placeholder={
                        texts.formularioPropostaProposta.selecioneUmaOuMaisBus
                      }
                    />
                  )}
                />
              </Box>
              <Box
                className="flex"
                sx={{ marginTop: "15px", marginBottom: "10px" }}
              >
                <Autocomplete
                  sx={{ width: "45%", marginLeft: "3%" }}
                  disablePortal
                  options={props.listaForuns}
                  value={props.dados.forum}
                  onChange={(event, value) => {
                    alterarTexto(value, "forum");
                  }}
                  getOptionLabel={(option) => {
                    return option?.siglaForum || "";
                  }}
                  isOptionEqualToValue={(option, value) => {
                    return option?.id == value?.id;
                  }}
                  fullWidth
                  noOptionsText={
                    texts.formularioPropostaProposta.nenhumForumEncontrado
                  }
                  renderInput={(params) => (
                    <TextField
                      variant="standard"
                      {...params}
                      label={texts.formularioPropostaProposta.labelForum}
                    />
                  )}
                />
              </Box>
              <Box>
                <Box className="flex items-center">
                  <Typography
                    fontSize={FontConfig.veryBig}
                    fontWeight="600"
                    color="text.primary"
                  >
                    {texts.formularioPropostaProposta.anexos}:
                  </Typography>
                  <AddCircleOutlineOutlinedIcon
                    className="delay-120 hover:scale-110 duration-300 ml-1"
                    sx={{ color: "primary.main", cursor: "pointer" }}
                    onClick={clickAdicionarAnexo}
                  />
                  <input
                    onChange={salvarAnexo}
                    ref={inputFile}
                    type="file"
                    multiple
                    hidden
                  />
                </Box>
                {props.dados.anexo.length > 0 ? (
                  <Box className="flex flex-col gap-2">
                    {props.dados.anexo?.map((anexo, index) => (
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
                        elevation={0}
                      >
                        <Typography
                          sx={{
                            color: "text.primary",
                            fontSize: FontConfig.default,
                          }}
                        >
                          {anexo.nome ? anexo.nome : anexo.name}
                        </Typography>
                        <Box className="flex gap-2">
                          <Tooltip
                            title={texts.formularioPropostaProposta.titleBaixar}
                          >
                            <IconButton
                              onClick={() => {
                                baixarAnexo(index);
                              }}
                            >
                              <DownloadIcon sx={{ color: "text.primary" }} />
                            </IconButton>
                          </Tooltip>
                          <Tooltip
                            title={texts.formularioPropostaProposta.titleRemover}
                          >
                            <IconButton
                              onClick={() => {
                                removerAnexo(index);
                              }}
                            >
                              <CloseIcon sx={{ color: "text.primary" }} />
                            </IconButton>
                          </Tooltip>
                        </Box>
                      </Paper>
                    ))}
                  </Box>
                ) : (
                  <Typography
                    textAlign="center"
                    sx={{ color: "text.primary", fontSize: FontConfig.default }}
                  >
                    {texts.formularioPropostaProposta.nenhumAnexo}
                  </Typography>
                )}
              </Box>
            </>
          </Box>
        )}
      </Box>
    </>
  );
};

export default FormularioPropostaProposta;
