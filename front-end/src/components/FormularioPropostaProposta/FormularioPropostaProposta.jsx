import React, { useState, useContext, useEffect, useRef } from "react";

import {
  Box,
  Typography,
  Divider,
  TextareaAutosize,
  Paper,
  IconButton,
  Tooltip,
} from "@mui/material";

import AddCircleOutlineOutlinedIcon from "@mui/icons-material/AddCircleOutlineOutlined";
import CloseIcon from "@mui/icons-material/Close";
import DownloadIcon from "@mui/icons-material/Download";
import BeneficiosDetalheDemanda from "../../components/BeneficiosDetalheDemanda/BeneficiosDetalheDemanda";

import ColorModeContext from "../../service/TemaContext";
import BeneficioService from "../../service/beneficioService";
import FontContext from "../../service/FontContext";

const FormularioPropostaProposta = (props) => {
  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  const [corFundoTextArea, setCorFundoTextArea] = useState("#FFFF");
  const { mode } = useContext(ColorModeContext);
  const inputFile = useRef(null);

  useEffect(() => {
    if (mode === "dark") {
      setCorFundoTextArea("#212121");
    } else {
      setCorFundoTextArea("#FFFF");
    }
  }, [mode]);

  // ----------------------------------------------------------------------------------------------------------------------------
  // Funções de edição da demanda

  const alterarTexto = (e, input) => {
    if (input === "titulo") {
      props.setDadosDemanda({ titulo: e.target.value, status: props.dados.status, problema: props.dados.problema, proposta: props.dados.proposta, beneficios: props.dados.beneficios, frequencia: props.dados.frequencia, anexo: props.dados.anexo, solicitante: props.dados.solicitante, analista: props.dados.analista, gerente: props.dados.gerente, buSolicitante: props.dados.buSolicitante, busBeneficiadas: props.dados.busBeneficiadas, data: props.dados.data, departamento: props.dados.departamento, forum: props.dados.forum, secaoTI: props.dados.secaoTI });
    } else if (input === "problema") {
      props.setDadosDemanda({ titulo: props.dados.titulo, status: props.dados.status, problema: e.target.value, proposta: props.dados.proposta, beneficios: props.dados.beneficios, frequencia: props.dados.frequencia, anexo: props.dados.anexo, solicitante: props.dados.solicitante, analista: props.dados.analista, gerente: props.dados.gerente, buSolicitante: props.dados.buSolicitante, busBeneficiadas: props.dados.busBeneficiadas, data: props.dados.data, departamento: props.dados.departamento, forum: props.dados.forum, secaoTI: props.dados.secaoTI });
    } else if (input === "proposta") {
      props.setDadosDemanda({ titulo: props.dados.titulo, status: props.dados.status, problema: props.dados.problema, proposta: e.target.value, beneficios: props.dados.beneficios, frequencia: props.dados.frequencia, anexo: props.dados.anexo, solicitante: props.dados.solicitante, analista: props.dados.analista, gerente: props.dados.gerente, buSolicitante: props.dados.buSolicitante, busBeneficiadas: props.dados.busBeneficiadas, data: props.dados.data, departamento: props.dados.departamento, forum: props.dados.forum, secaoTI: props.dados.secaoTI });
    } else if (input === "frequencia") {
      props.setDadosDemanda({ titulo: props.dados.titulo, status: props.dados.status, problema: props.dados.problema, proposta: props.dados.proposta, beneficios: props.dados.beneficios, frequencia: e.target.value, anexo: props.dados.anexo, solicitante: props.dados.solicitante, analista: props.dados.analista, gerente: props.dados.gerente, buSolicitante: props.dados.buSolicitante, busBeneficiadas: props.dados.busBeneficiadas, data: props.dados.data, departamento: props.dados.departamento, forum: props.dados.forum, secaoTI: props.dados.secaoTI });
    }
  };

  // Aciona o input de anexos ao clicar no add anexos
  const onAddAnexoButtonClick = () => {
    inputFile.current.click();
  };

  // Coloca o arquivo selecionado no input no state de anexos
  const onFilesSelect = () => {
    for (let file of inputFile.current.files) {
      if (!existsInAnexos(file)) {
        updateAnexosNovos(file);
        props.setDadosDemanda({ titulo: props.dados.titulo, status: props.dados.status, problema: props.dados.problema, proposta: props.dados.proposta, beneficios: props.dados.beneficios, frequencia: props.dados.frequencia, anexo: [...props.dados.anexo, file], solicitante: props.dados.solicitante, analista: props.dados.analista, gerente: props.dados.gerente, buSolicitante: props.dados.buSolicitante, busBeneficiadas: props.dados.busBeneficiadas, data: props.dados.data, departamento: props.dados.departamento, forum: props.dados.forum, secaoTI: props.dados.secaoTI });
      } else {
        // feedback de anexo já existente
        console.log("já há um anexo com esse nome");
      }
    }
  };

  const existsInAnexos = (anexo) => {
    return (
      props.dados.anexo.filter((anexoItem) => {
        return (
          anexoItem.nome == anexo.name ||
          (anexo.id && anexoItem.nome == anexo.nome)
        );
      }).length > 0
    );
  };

  // Função para remover um anexo da lista de anexos
  const removerAnexo = (index) => {
    updateAnexosRemovidos(index);
    removeAnexosNovos(props.dados.anexo[index]);
    let aux = [...props.dados.anexo];
    aux.splice(index, 1);
    props.setDadosDemanda({ titulo: props.dados.titulo, status: props.dados.status, problema: props.dados.problema, proposta: props.dados.proposta, beneficios: props.dados.beneficios, frequencia: props.dados.frequencia, anexo: aux, solicitante: props.dados.solicitante, analista: props.dados.analista, gerente: props.dados.gerente, buSolicitante: props.dados.buSolicitante, busBeneficiadas: props.dados.busBeneficiadas, data: props.dados.data, departamento: props.dados.departamento, forum: props.dados.forum, secaoTI: props.dados.secaoTI });
  };

  // Função que cria um benefício no banco e usa o id nele em um objeto novo na lista da página
  const adicionarBeneficio = () => {
    BeneficioService.post({
      tipoBeneficio: "",
      valor_mensal: "",
      moeda: "",
      memoriaCalculo: "",
    }).then((response) => {
      let beneficioNovo = {
        id: response.id,
        tipoBeneficio: "",
        valor_mensal: "",
        moeda: "",
        memoriaCalculo: "",
        visible: true,
      };
      props.setBeneficios([...props.beneficios, beneficioNovo]);
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

  // Função para excluir um benefício da lista
  const deleteBeneficio = (indexBeneficio) => {
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
    aux[indexBeneficio].visible = false;
    props.setBeneficiosExcluidos([...props.beneficiosExcluidos, aux[indexBeneficio]]);
    props.setBeneficios(aux);
  };

  function base64ToArrayBuffer(base64) {
    const binaryString = window.atob(base64);
    const bytes = new Uint8Array(binaryString.length);
    return bytes.map((byte, i) => binaryString.charCodeAt(i));
  }

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

  // Lógica para anexos ------------------------------------------------------
  // Novos anexos que serão adicionados na demanda
  const [novosAnexos, setNovosAnexos] = useState([]);

  // Anexos que já estavam na demanda e que serão removidos
  const [anexosRemovidos, setAnexosRemovidos] = useState([]);

  // Irá atualizar a lista para que contenha os anexos que foram removidos da demanda e que já estavam salvos no banco de dados
  const updateAnexosRemovidos = (indexAnexo) => {
    let anexo = props.dados.anexo[indexAnexo];

    setAnexosRemovidos([
      ...anexosRemovidos,
      ...props.dados.anexo.filter(
        // anexo.id - se tiver, quer dizer que é um anexo que já estava na demanda (salvo no banco de dados)
        (anexoItem) => anexo.id && anexoItem.id == anexo.id
      ),
    ]);
  };
  //lembrar de resetar essas variáveis depois de salvar e/ou sair

  // Irá atualizar a lista para que contenha os anexos que foram adicionados na demanda (somente novos anexos)
  const updateAnexosNovos = (anexo) => {
    if (!existsInArray(novosAnexos, anexo)) {
      setNovosAnexos([...novosAnexos, anexo]);
    }
  };

  // Função que verifica se um determinado anexo já existe na lista provida
  const existsInArray = (array, anexo) => {
    return (
      array.filter((anexoItem) => {
        return anexoItem.name == anexo.name;
      }).length > 0
    );
  };

  // Função que remove um anexo da lista anexosNovos caso o usuário o remova, só é removido anexos que não estavam salvos no banco de dados
  const removeAnexosNovos = (anexo) => {
    setNovosAnexos(
      novosAnexos.filter((anexoItem) => {
        return anexoItem.name != anexo.name && !anexoItem.id;
      })
    );
  };

  return (
    <Box className="flex flex-col justify-center relative items-center mt-10">
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
              placeholder="Digite a proposta..."
            />
          </Box>
          <Box>
            <Box className="flex items-center">
              <Typography
                fontSize={FontConfig.veryBig}
                fontWeight="600"
                color="text.primary"
              >
                Beneficios:
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
                if (beneficio.visible) {
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
                }
              })}
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
              placeholder="Digite a frequência..."
            />
          </Box>
          <Box>
            <Box className="flex items-center">
              <Typography
                fontSize={FontConfig.veryBig}
                fontWeight="600"
                color="text.primary"
              >
                Anexos:
              </Typography>
              <AddCircleOutlineOutlinedIcon
                className="delay-120 hover:scale-110 duration-300 ml-1"
                sx={{ color: "primary.main", cursor: "pointer" }}
                onClick={onAddAnexoButtonClick}
              />
              <input
                onChange={onFilesSelect}
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
                      <Tooltip title="Baixar">
                        <IconButton
                          onClick={() => {
                            baixarAnexo(index);
                          }}
                        >
                          <DownloadIcon sx={{ color: "text.primary" }} />
                        </IconButton>
                      </Tooltip>
                      <Tooltip title="Remover">
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
                Nenhum anexo adicionado
              </Typography>
            )}
          </Box>
        </>
      </Box>
    </Box>
  );
};

export default FormularioPropostaProposta;
