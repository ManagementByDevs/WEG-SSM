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

  const [horasTotais, setHorasTotais] = useState("00:00");

  const [valorTotal, setValorTotal] = useState("00,00");

  const [porcentagemTotal, setPorcentagemTotal] = useState(0);

  return (
    <Box className="flex w-full mt-5">
      <Box className="flex items-top mr-2">
        {props.index > 0 ? (
          <Box className="h-full flex items-center">
            <Tooltip title="Excluir tabela de custos">
              <DeleteOutlineOutlinedIcon
                fontSize="large"
                className="mr-2 delay-120 hover:scale-110 duration-300"
                sx={{ color: "icon.main", cursor: "pointer" }}
                onClick={() => props.deletarCustos(props.index)}
              />
            </Tooltip>
          </Box>
        ) : (
          <Box className="flex mr-2 p-4"></Box>
        )}
        <Box>
          <Paper className="w-full mr-3 pb-1">
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
                        Per??odo de Execu????o (Meses)
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
            <Box className="w-full flex justify-between items-center m-2">
              <Box className="flex w-full">
                <Typography fontSize={FontConfig.medium} sx={{ marginRight: "8px" }}>Total: </Typography>
                <Typography fontSize={FontConfig.medium} sx={{ marginRight: "15px" }}>
                  {horasTotais}h
                </Typography>
                <Typography fontSize={FontConfig.medium} sx={{ marginRight: "15px" }}>-</Typography>
                <Typography fontSize={FontConfig.medium} sx={{ marginRight: "8px" }}>
                  R${valorTotal}
                </Typography>
              </Box>
              <Tooltip title="Adicionar nova linha">
                <AddCircleOutlineOutlinedIcon
                  fontSize="medium"
                  className="mr-3 delay-120 hover:scale-110 duration-300"
                  sx={{ color: "icon.main", cursor: "pointer" }}
                  onClick={() => props.setDespesas(props.index)}
                />
              </Tooltip>
            </Box>
          </Paper>
        </Box>
      </Box>
      <Paper className="h-full pb-1" sx={{ width: "25%" }}>
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
                      deletarLinhaCCs={props.deletarLinhaCCs}
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
        <Box className="w-full flex justify-between items-center m-2">
          <Box className="flex">
            <Typography fontSize={FontConfig.medium} sx={{ marginRight: "8px" }}>Total:</Typography>
            <Typography fontSize={FontConfig.medium}>{porcentagemTotal}% </Typography>
          </Box>
          <Tooltip title="Adicionar nova linha">
            <AddCircleOutlineOutlinedIcon
              fontSize="medium"
              className="mr-3 delay-120 hover:scale-110 duration-300"
              sx={{ color: "icon.main", cursor: "pointer" }}
              onClick={() => props.setCcs(props.index)}
            />
          </Tooltip>
        </Box>
      </Paper>
    </Box>
  );
};

export default Custos;
