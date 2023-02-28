import React, { useRef, useState, useEffect, useContext } from "react";
import { Box, Button, Typography, IconButton } from "@mui/material";

import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";

import DeleteOutlineOutlinedIcon from "@mui/icons-material/DeleteOutlineOutlined";
import AddCircleIcon from "@mui/icons-material/AddCircle";

import FontContext from "../../service/FontContext";

/** Terceira e última etapa da criação de demanda, com espaço para adicionar anexos numa lista */
const FormularioAnexosDemanda = (props) => {
  // props.dados => Lista de arquivos recebidos

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  /** Variável usada para referência da área de "soltar" arquivos */
  const areaArquivos = useRef(null);

  /** Variável usada para referência do input do tipo "file" para selecionar os arquivos */
  const inputArquivos = useRef(null);

  /** Variável usada como texto da caixa de seleção de arquivos, modificado ao arrastar arquivos na caixa */
  const [textoCaixa, setTextoCaixa] = useState("Arraste & Solte para Adicionar um Arquivo");

  // UseEffect para salvar o escopo da demanda atual quando algum arquivo for adicionado / removido
  useEffect(() => {
    props.salvarEscopo();
  }, [props.dados]);

  /** Função para acionar o input de arquivos */
  const clickInputArquivos = () => {
    inputArquivos.current.click();
  };

  /** Função para adicionar os arquivos no array após seleção pelo input */
  const salvarArquivos = () => {
    props.setDados([...props.dados, ...inputArquivos.current.files]);
  };

  /** Função para mudar o "textoCaixa" quando o usuário arrastar um arquivo para a caixa de seleção */
  const textoSoltarArquivo = (event) => {
    event.preventDefault();
    setTextoCaixa("Solte para Adicionar um Arquivo");
  };

  /** Função para mudar o "textoCaixa" quando o usuário arrastar um arquivo para fora da caixa de seleção */
  const textoArrastarArquivo = (event) => {
    event.preventDefault();
    setTextoCaixa("Arraste & Solte para Adicionar um Arquivo");
  };

  /** Função para salvar os arquivos que forem arrastados para a caixa de seleção */
  const onDropFile = (event) => {
    event.preventDefault();
    event.stopPropagation();
    let files = event.dataTransfer.files;
    let fileArrayAux = Array.from(files);
    props.setDados([...props.dados, ...fileArrayAux]);
  };

  /** Função para excluir um arquivo do array */
  const deleteFile = (desiredIndex) => {
    props.setDados(props.dados.filter((_, index) => index !== desiredIndex));
  };

  return (
    <Box className="flex justify-center items-center" sx={{ height: "45rem" }}>

      {/* Caixa de seleção */}
      <Box
        ref={areaArquivos}
        onDragOver={textoSoltarArquivo}
        onDragLeave={textoArrastarArquivo}
        onDrop={onDropFile}
        className="flex justify-center items-center flex-col rounded border-2"
        sx={{ width: "85%", height: "85%" }}
      >
        <input
          onChange={salvarArquivos}
          ref={inputArquivos}
          type="file"
          multiple
          hidden
        />
        {props.dados?.length === 0 ? (

          // Texto de arrastar arquivos caso não possua nenhum arquivo na lista
          <>
            <Typography
              fontSize={FontConfig.veryBig}
              color="text.secondary"
              sx={{ fontWeight: "600", cursor: "default", marginBottom: "1rem", }}
            >
              {textoCaixa}
            </Typography>
            <Typography
              fontSize={FontConfig.veryBig}
              color="text.secondary"
              sx={{ fontWeight: "600", cursor: "default", marginBottom: "1rem", }}
            >
              OU
            </Typography>
            <Button onClick={clickInputArquivos} variant="contained" disableElevation>
              <Typography fontSize={FontConfig.medium}>
                Pesquisar Arquivos
              </Typography>
            </Button>
          </>
        ) : (

          // Tabela de arquivos da lista
          <Box className="w-full h-full px-2 py-4 relative">
            <Box className="absolute bottom-2 right-2">
              <IconButton onClick={clickInputArquivos} aria-label="adicionar">
                <AddCircleIcon color="primary" fontSize="large" />
              </IconButton>
            </Box>
            <TableContainer className="h-full w-full">
              <Table sx={{ minWidth: 650 }} aria-label="simple table">
                <TableHead className="border-b">
                  <TableRow>
                    <th>
                      <Typography
                        fontSize={FontConfig.big}
                        color="text.primary"
                        sx={{ fontWeight: "700", cursor: "default" }}
                      >
                        Nome
                      </Typography>
                    </th>
                    <th>
                      <Typography
                        fontSize={FontConfig.big}
                        color="text.primary"
                        sx={{ fontWeight: "700", cursor: "default" }}
                      >
                        Tipo
                      </Typography>
                    </th>
                    <th>
                      <Typography
                        fontSize={FontConfig.big}
                        color="text.primary"
                        sx={{ fontWeight: "700", cursor: "default" }}
                      >
                        Remover
                      </Typography>
                    </th>
                  </TableRow>
                </TableHead>
                <TableBody>

                  {/* Lista de arquivos */}
                  {props.dados?.map((file, index) => {
                    return (
                      <TableRow key={index} className="border-b">
                        <td className="text-center">
                          <Typography
                            fontSize={FontConfig.medium}
                            color="text.secondary"
                            sx={{ fontWeight: "500", cursor: "default" }}
                          >
                            {file.name}
                          </Typography>
                        </td>
                        <td className="text-center">
                          <Typography
                            fontSize={FontConfig.medium}
                            color="text.secondary"
                            sx={{ fontWeight: "500", cursor: "default" }}
                          >
                            {file.type}
                          </Typography>
                        </td>
                        <td className="text-center">
                          <IconButton onClick={() => deleteFile(index)}>
                            <DeleteOutlineOutlinedIcon color="primary" />
                          </IconButton>
                        </td>
                      </TableRow>
                    );
                  })}
                </TableBody>
              </Table>
            </TableContainer>
          </Box>
        )}
      </Box>
    </Box>
  );
};

export default FormularioAnexosDemanda;
