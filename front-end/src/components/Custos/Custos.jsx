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
  Tooltip,
} from "@mui/material";

import FontConfig from "../../service/FontConfig";

import DeleteOutlineOutlinedIcon from "@mui/icons-material/DeleteOutlineOutlined";
import AddCircleOutlineOutlinedIcon from "@mui/icons-material/AddCircleOutlineOutlined";

import LinhaTabelaCustos from "../LinhaTabelaCustos/LinhaTabelaCustos";
import LinhaTabelaCCs from "../LinhaTabelaCCs/LinhaTabelaCCs";

const Custos = (props) => {
  return (
    <Box className="flex w-full mt-5">
      <Box className="flex items-center">
        {props.index > 0 ? (
          <Tooltip title="Excluir tabela de custos">
            <DeleteOutlineOutlinedIcon
              fontSize="large"
              className="mr-2 delay-120 hover:scale-110 duration-300"
              sx={{ color: "icon.main", cursor: "pointer" }}
              onClick={() => props.deletarCustos(props.index)}
            />
          </Tooltip>
        ) : (
          <Box className="flex mr-2 p-4"></Box>
        )}
        <Paper className="w-full mr-3">
          <TableContainer component={Paper}>
            <Table sx={{ minWidth: "90%" }} aria-label="customized table">
              <TableHead sx={{ backgroundColor: "primary.main" }}>
                <TableRow>
                  <th
                    align="center"
                    className="p-4 w-0"
                    style={{ width: "5%" }}
                  >
                    <Typography
                      fontSize={FontConfig.big}
                      fontWeight="800"
                      color="text.white"
                    >
                      Tipo da Despesa
                    </Typography>
                  </th>
                  <th
                    align="center"
                    className="p-4 w-0"
                    style={{ width: "5%" }}
                  >
                    <Typography
                      fontSize={FontConfig.big}
                      fontWeight="800"
                      color="text.white"
                    >
                      Perfil da Despesa
                    </Typography>
                  </th>
                  <th
                    align="center"
                    className="p-4 w-0"
                    style={{ width: "10%" }}
                  >
                    <Typography
                      fontSize={FontConfig.big}
                      fontWeight="800"
                      color="text.white"
                    >
                      Período de Execução (Meses)
                    </Typography>
                  </th>
                  <th
                    align="center"
                    className="p-4 w-0"
                    style={{ width: "7%" }}
                  >
                    <Typography
                      fontSize={FontConfig.big}
                      fontWeight="800"
                      color="text.white"
                    >
                      Horas
                    </Typography>
                  </th>
                  <th
                    align="center"
                    className="p-4 w-0"
                    style={{ width: "8%" }}
                  >
                    <Typography
                      fontSize={FontConfig.big}
                      fontWeight="800"
                      color="text.white"
                    >
                      Valor Hora
                    </Typography>
                  </th>
                  <th
                    align="center"
                    className="p-4 w-0"
                    style={{ width: "10%" }}
                  >
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
                        dados={props.dados}
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
            <Tooltip title="Adicionar nova linha">
              <AddCircleOutlineOutlinedIcon
                fontSize="medium"
                className="m-1 mr-3 delay-120 hover:scale-110 duration-300"
                sx={{ color: "icon.main", cursor: "pointer" }}
                onClick={() => props.setDespesas(props.index)}
              />
            </Tooltip>
          </Box>
        </Paper>
      </Box>
      <Paper sx={{ width: "30%", height: "100%" }}>
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
              {props.dados.ccs?.map((cc, index) => {
                return (
                  cc.visible && (
                    <LinhaTabelaCCs
                      key={index}
                      dados={props.dados}
                      index={index}
                      // deletarLinhaCcs={props.deletarLinhaCcs}
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
          <Tooltip title="Adicionar nova linha">
            <AddCircleOutlineOutlinedIcon
              fontSize="medium"
              className="m-1 mr-3 delay-120 hover:scale-110 duration-300"
              sx={{ color: "icon.main", cursor: "pointer" }}
            />
          </Tooltip>
        </Box>
      </Paper>
    </Box>
  );
};

export default Custos;
