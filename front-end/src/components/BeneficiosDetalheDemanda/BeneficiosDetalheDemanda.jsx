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
} from "@mui/material";
import TableCell, { tableCellClasses } from "@mui/material/TableCell";

import FontConfig from "../../service/FontConfig";

const BeneficiosDetalheDemanda = () => {

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
      "Escrevendo alguma coisa que realmente seja grande para testar a responsividade do bagulho"
    ),
  ];

  return (
    <TableContainer component={Paper}>
      <Table sx={{ minWidth: 750 }} aria-label="customized table">
        <TableHead sx={{ backgroundColor: "primary.main" }}>
          <TableRow>
            <th align="center" className="p-4 w-0">
              <Typography
                fontSize={FontConfig.big}
                fontWeight="800"
                color="text.white"
                sx={{width: '40%'}}
              >
                Tipo
              </Typography>
            </th>
            <th align="center" className="p-4 w-0">
              <Typography
                fontSize={FontConfig.big}
                fontWeight="800"
                color="text.white"
                sx={{width: '40%'}}
              >
                Valor Mensal
              </Typography>
            </th>
            <th align="center" className="p-4 w-0">
              <Typography
                fontSize={FontConfig.big}
                fontWeight="800"
                color="text.white"
                sx={{width: '40%'}}
              >
                Moeda
              </Typography>
            </th>
            <th align="center" className="p-4 w-0">
              <Typography
                fontSize={FontConfig.big}
                fontWeight="800"
                color="text.white"
                sx={{width: '40%'}}
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
                <Typography fontSize={FontConfig.medium} color="text.primary">
                  {row.tipo}
                </Typography>
              </td>
              <td align="center">
                <Typography fontSize={FontConfig.medium} color="text.primary">
                  {row.valorMensal}
                </Typography>
              </td>
              <td align="center">
                <Typography fontSize={FontConfig.medium} color="text.primary">
                  {row.moeda}
                </Typography>
              </td>
              <td align="center" className="p-3 pl-5 pr-5 flex justify-center">
                <Typography className="text-justify" fontSize={FontConfig.medium} color="text.primary" sx={{width: '100%'}}>
                  {row.memoriaCalculo}
                </Typography>
              </td>
            </StyledTableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
};

export default BeneficiosDetalheDemanda;
