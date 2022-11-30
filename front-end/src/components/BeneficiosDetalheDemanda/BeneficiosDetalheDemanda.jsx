import * as React from "react";

import { styled } from "@mui/material/styles";
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

import DeleteOutlineOutlinedIcon from "@mui/icons-material/DeleteOutlineOutlined";

import FontConfig from "../../service/FontConfig";

const BeneficiosDetalheDemanda = (props) => {
  const StyledTableRow = styled(TableRow)(({ theme }) => ({
    "&:nth-of-type(odd)": {
      backgroundColor: theme.palette.action.hover,
    },
    // hide last border
    "&:last-child td, &:last-child th": {
      border: 0,
    },
  }));

  function createData(tipo, valorMensal, moeda, memoriaCalculo) {
    return { tipo, valorMensal, moeda, memoriaCalculo };
  }

  const rows = [
    createData(
      "Real",
      "500,00",
      "BR",
      "Escrevendo alguma coisa que realmente seja grande para testar a responsividade do bagulho aqui"
    ),
  ];

  return (
    <Box className="flex items-center">
      {props.editavel ? (
        <>
          <DeleteOutlineOutlinedIcon
            fontSize="large"
            className="delay-120 hover:scale-110 duration-300 mr-2"
            sx={{ color: "icon.main", cursor: "pointer" }}
          />
          <TableContainer component={Paper}>
            <Table sx={{ minWidth: 750 }} aria-label="customized table">
              <TableHead sx={{ backgroundColor: "primary.main" }}>
                <TableRow>
                  <th align="center" className="p-4 w-0" style={{width: '10%'}}>
                    <Typography
                      fontSize={FontConfig.big}
                      fontWeight="800"
                      color="text.white"
                    >
                      Tipo
                    </Typography>
                  </th>
                  <th align="center" className="p-4 w-0" style={{width: '15%'}}>
                    <Typography
                      fontSize={FontConfig.big}
                      fontWeight="800"
                      color="text.white"
                    >
                      Valor Mensal
                    </Typography>
                  </th>
                  <th align="center" className="p-4 w-0" style={{width: '10%'}}>
                    <Typography
                      fontSize={FontConfig.big}
                      fontWeight="800"
                      color="text.white"
                    >
                      Moeda
                    </Typography>
                  </th>
                  <th align="center" className="p-4 w-0" style={{width: '30%'}}>
                    <Typography
                      fontSize={FontConfig.big}
                      fontWeight="800"
                      color="text.white"
                    >
                      Memória de Cálculo
                    </Typography>
                  </th>
                </TableRow>
              </TableHead>
              <TableBody>
                {rows.map((row) => (
                  <StyledTableRow key={row.tipo} className="flex">
                    <td align="center">
                      <Box
                        value={row.tipo}
                        fontSize={FontConfig.medium}
                        color="text.primary"
                        className="flex outline-none border-solid border px-1 py-1.5 drop-shadow-sm rounded text-center"
                        sx={{
                          width: "80%;",
                          height: "30px",
                          backgroundColor: "background.default",
                        }}
                        component="input"
                        placeholder="Digite o tipo do benefício..."
                      />
                    </td>
                    <td align="center">
                      <Box
                        value={row.valorMensal}
                        fontSize={FontConfig.medium}
                        color="text.primary"
                        className="flex outline-none border-solid border px-1 py-1.5 drop-shadow-sm rounded text-center"
                        sx={{
                          width: "80%;",
                          height: "30px",
                          backgroundColor: "background.default",
                        }}
                        component="input"
                        placeholder="Digite o valor mensal..."
                      />
                    </td>
                    <td align="center">
                      <Box
                        value={row.moeda}
                        fontSize={FontConfig.medium}
                        color="text.primary"
                        className="flex outline-none border-solid border px-1 py-1.5 drop-shadow-sm rounded text-center"
                        sx={{
                          width: "80%;",
                          height: "30px",
                          backgroundColor: "background.default",
                        }}
                        component="input"
                        placeholder="Digite o tipo da moeda..."
                      />
                    </td>
                    <td
                      align="center"
                      className="p-3 pl-5 pr-5 flex justify-center"
                    >
                      <TextareaAutosize
                        style={{
                          width: "100%",
                          resize: "none",
                          backgroundColor: "background.default",
                        }}
                        value={row.memoriaCalculo}
                        fontSize={FontConfig.medium}
                        className="flex outline-none border-solid border px-1 py-1.5 drop-shadow-sm rounded"
                        placeholder="Digite a memória de cálculo..."
                      />
                    </td>
                  </StyledTableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>
        </>
      ) : (
        <TableContainer component={Paper}>
          <Table sx={{ minWidth: 750 }} aria-label="customized table">
            <TableHead sx={{ backgroundColor: "primary.main" }}>
              <TableRow>
                <th align="center" className="p-4 w-0" style={{width: '10%'}}>
                  <Typography
                    fontSize={FontConfig.big}
                    fontWeight="800"
                    color="text.white"
                  >
                    Tipo
                  </Typography>
                </th>
                <th align="center" className="p-4 w-0" style={{width: '15%'}}>
                  <Typography
                    fontSize={FontConfig.big}
                    fontWeight="800"
                    color="text.white"
                  >
                    Valor Mensal
                  </Typography>
                </th>
                <th align="center" className="p-4 w-0" style={{width: '10%'}}>
                  <Typography
                    fontSize={FontConfig.big}
                    fontWeight="800"
                    color="text.white"
                  >
                    Moeda
                  </Typography>
                </th>
                <th align="center" className="p-4 w-0" style={{width: '30%'}}>
                  <Typography
                    fontSize={FontConfig.big}
                    fontWeight="800"
                    color="text.white"
                  >
                    Memória de Cálculo
                  </Typography>
                </th>
              </TableRow>
            </TableHead>
            <TableBody>
              {rows.map((row) => (
                <StyledTableRow key={row.tipo} className="flex">
                  <td align="center">
                    <Typography
                      fontSize={FontConfig.medium}
                      color="text.primary"
                    >
                      {row.tipo}
                    </Typography>
                  </td>
                  <td align="center">
                    <Typography
                      fontSize={FontConfig.medium}
                      color="text.primary"
                    >
                      {row.valorMensal}
                    </Typography>
                  </td>
                  <td align="center">
                    <Typography
                      fontSize={FontConfig.medium}
                      color="text.primary"
                    >
                      {row.moeda}
                    </Typography>
                  </td>
                  <td
                    align="center"
                    className="p-3 pl-5 pr-5 flex justify-center"
                  >
                    <Typography
                      className="text-justify"
                      fontSize={FontConfig.medium}
                      color="text.primary"
                      sx={{ width: "100%" }}
                    >
                      {row.memoriaCalculo}
                    </Typography>
                  </td>
                </StyledTableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      )}
    </Box>
  );
};

export default BeneficiosDetalheDemanda;
