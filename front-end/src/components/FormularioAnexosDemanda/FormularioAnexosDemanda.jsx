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

const FormularioAnexosDemanda = (props) => {
  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);
  
  // Variáveis para armazenar os arquivos 
  const dragArea = useRef(null);
  const inputFile = useRef(null);

  // UseState para receber os arquivos e mapear na tabela
  const [mapAbleFileList, setMapAbleFileList] = useState([]);

  // UseState para alterar o texto do drag and drop
  const [dragText, setDragText] = useState("Arraste & Solte para Adicionar um Arquivo");

  // Função para abrir o input file
  const fileClick = () => {
    inputFile.current.click();
  };

  // Função para adicionar os arquivos no array
  const onFilesSelect = () => {
    for (let file of inputFile.current.files) {
      props.setDados([...props.dados, file]);
    }
  };

  // UseEffect para atualizar o array de arquivos
  useEffect(() => {
    setMapAbleFileList(Array.from(props.dados));
    props.salvarEscopo();
  }, [props.dados]);

  // Função para o drag and drop
  const drag = (event) => {
    event.preventDefault();
    setDragText("Solte para Adicionar um Arquivo");
  };

  // Função para alterar o texto do drag and drop
  const onDragLeaveHandle = (event) => {
    event.preventDefault();
    setDragText("Arraste & Solte para Adicionar um Arquivo");
  };

  // Função para adicionar os arquivos no array
  const onDropFile = (event) => {
    event.preventDefault();
    event.stopPropagation();
    let files = event.dataTransfer.files;
    let fileArrayAux = Array.from(files);
    for (let file of fileArrayAux) {
      props.setDados([...props.dados, file]);
    }
  };

  // Função para remover um arquivo do array
  const deleteFile = (desiredIndex) => {
    setMapAbleFileList(
      mapAbleFileList.filter((_, index) => index !== desiredIndex)
    );
    props.setDados(props.dados.filter((_, index) => index !== desiredIndex));
  };

  return (
    <Box className="flex justify-center items-center" sx={{ height: "45rem" }}>
      <Box
        ref={dragArea}
        onDragOver={drag}
        onDragLeave={onDragLeaveHandle}
        onDrop={onDropFile}
        className="flex justify-center items-center flex-col rounded border-2"
        sx={{ width: "85%", height: "85%" }}
      >
        <input
          onChange={onFilesSelect}
          ref={inputFile}
          type="file"
          multiple
          hidden
        />
        {props.dados?.length === 0 ? (
          <>
            <Typography
              fontSize={FontConfig.veryBig}
              color="text.secondary"
              sx={{
                fontWeight: "600",
                cursor: "default",
                marginBottom: "1rem",
              }}
            >
              {dragText}
            </Typography>
            <Typography
              fontSize={FontConfig.veryBig}
              color="text.secondary"
              sx={{
                fontWeight: "600",
                cursor: "default",
                marginBottom: "1rem",
              }}
            >
              OU
            </Typography>
            <Button onClick={fileClick} variant="contained" disableElevation>
              <Typography fontSize={FontConfig.medium}>
                Pesquisar Arquivos
              </Typography>
            </Button>
          </>
        ) : (
          <Box className="w-full h-full px-2 py-4 relative">
            <Box className="absolute bottom-2 right-2">
              <IconButton onClick={fileClick} aria-label="adicionar">
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
                  {mapAbleFileList?.map((file, index) => {
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
