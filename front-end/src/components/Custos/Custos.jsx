import React, { useState, useContext, useEffect } from "react";
import {
  TableContainer,
  Table,
  TableHead,
  TableRow,
  TableBody,
  Paper,
  Typography,
  Box,
  TextareaAutosize,
} from "@mui/material";

import FontConfig from "../../service/FontConfig";

import ColorModeContext from "../../service/TemaContext";

import DeleteOutlineOutlinedIcon from "@mui/icons-material/DeleteOutlineOutlined";
import AddCircleOutlineOutlinedIcon from "@mui/icons-material/AddCircleOutlineOutlined";

import LinhaTabelaCustos from "../LinhaTabelaCustos/LinhaTabelaCustos";

const Custos = (props) => {
  const [corFundoTextArea, setCorFundoTextArea] = useState("#FFFF");
  const { mode } = useContext(ColorModeContext);

  useEffect(() => {
    if (mode === "dark") {
      setCorFundoTextArea("#212121");
    } else {
      setCorFundoTextArea("#FFFF");
    }
  }, [mode]);

  return (
    <Box className="flex w-full mt-5 items-center">
      {props.index > 0 ? (
        <DeleteOutlineOutlinedIcon
          fontSize="large"
          className="mr-2 delay-120 hover:scale-110 duration-300"
          sx={{ color: "icon.main", cursor: "pointer" }}
          onClick={() => props.deletarCustos(props.index)}
        />
      ) : (
        <Box className="flex mr-2 p-4"></Box>
      )}
      <Paper className="w-full mr-3">
        <TableContainer component={Paper}>
          <Table sx={{ minWidth: "90%" }} aria-label="customized table">
            <TableHead sx={{ backgroundColor: "primary.main" }}>
              <TableRow>
                <th align="center" className="p-4 w-0" style={{ width: "5%" }}>
                  <Typography
                    fontSize={FontConfig.big}
                    fontWeight="800"
                    color="text.white"
                  >
                    Tipo da Despesa
                  </Typography>
                </th>
                <th align="center" className="p-4 w-0" style={{ width: "5%" }}>
                  <Typography
                    fontSize={FontConfig.big}
                    fontWeight="800"
                    color="text.white"
                  >
                    Perfil da Despesa
                  </Typography>
                </th>
                <th align="center" className="p-4 w-0" style={{ width: "10%" }}>
                  <Typography
                    fontSize={FontConfig.big}
                    fontWeight="800"
                    color="text.white"
                  >
                    Período de Execução (Meses)
                  </Typography>
                </th>
                <th align="center" className="p-4 w-0" style={{ width: "7%" }}>
                  <Typography
                    fontSize={FontConfig.big}
                    fontWeight="800"
                    color="text.white"
                  >
                    Horas
                  </Typography>
                </th>
                <th align="center" className="p-4 w-0" style={{ width: "8%" }}>
                  <Typography
                    fontSize={FontConfig.big}
                    fontWeight="800"
                    color="text.white"
                  >
                    Valor Hora
                  </Typography>
                </th>
                <th align="center" className="p-4 w-0" style={{ width: "10%" }}>
                  <Typography
                    fontSize={FontConfig.big}
                    fontWeight="800"
                    color="text.white"
                  >
                    Total
                  </Typography>
                </th>
              </TableRow>
            </TableHead>
            <TableBody>
              {props.dados.despesas?.map((despesa, index) => {
                return (
                  despesa.visible && (
                    <LinhaTabelaCustos
                      key={index}
                      dados={despesa}
                      index={index}
                      deletarLinhaCustos={props.deletarLinhaCustos}
                      indexCusto={props.index}
                      setCustos={props.setCustos}
                      custos={props.custos}
                    />
                  )
                );
              })}
            </TableBody>
          </Table>
        </TableContainer>
        <Box className="w-full flex justify-end">
          <AddCircleOutlineOutlinedIcon
            fontSize="medium"
            className="m-1 mr-3 delay-120 hover:scale-110 duration-300"
            titleAccess="Adicionar nova linha"
            sx={{ color: "icon.main", cursor: "pointer" }}
            onClick={() => props.setDespesas(props.index)}
          />
        </Box>
      </Paper>
      <Paper sx={{ width: "20%", height: "100%" }}>
        <TableContainer component={Paper}>
          <Table sx={{ minWidth: "100%" }} aria-label="customized table">
            <TableHead
              sx={{ backgroundColor: "primary.main", height: "5.4rem" }}
            >
              <TableRow>
                <th align="center" className="p-4 w-0" style={{ width: "10%" }}>
                  <Typography
                    fontSize={FontConfig.big}
                    fontWeight="800"
                    color="text.white"
                  >
                    CCs
                  </Typography>
                </th>
              </TableRow>
            </TableHead>
            <TableBody>
              <TableRow>
                <td align="center" className="pb-5 relative">
                  <Box
                    className="flex w-full justify-end absolute"
                    sx={{ width: "98%" }}
                  >
                    <DeleteOutlineOutlinedIcon
                      fontSize="medium"
                      className="mt-1 delay-120 hover:scale-110 duration-300"
                      titleAccess="Excluir linha"
                      sx={{ color: "icon.main", cursor: "pointer" }}
                    />
                  </Box>
                  <TextareaAutosize
                    style={{
                      width: "95%",
                      resize: "none",
                      textAlign: "center",
                      backgroundColor: corFundoTextArea,
                      marginTop: "2rem",
                    }}
                    fontSize={FontConfig.medium}
                    className="flex outline-none border-solid border px-1 py-1.5 drop-shadow-sm rounded"
                    placeholder="Digite os CCs..."
                    value={props.dados.ccs}
                    onChange={(e) => {
                      props.setCustos(
                        props.custos.map((custo, index) => {
                          if (index === props.index) {
                            return {
                              ...custo,
                              ccs: e.target.value,
                            };
                          }
                          return custo;
                        })
                      );
                    }}
                  />
                </td>
              </TableRow>
            </TableBody>
          </Table>
        </TableContainer>
        <Box className="w-full flex justify-end">
          <AddCircleOutlineOutlinedIcon
            fontSize="medium"
            className="m-1 mr-3 delay-120 hover:scale-110 duration-300"
            titleAccess="Adicionar nova linha"
            sx={{ color: "icon.main", cursor: "pointer" }}
          />
        </Box>
      </Paper>
    </Box>
  );
};

export default Custos;
