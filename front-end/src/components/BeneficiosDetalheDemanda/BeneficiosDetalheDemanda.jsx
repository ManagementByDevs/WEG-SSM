import * as React from "react";

import { styled } from "@mui/material/styles";
import {
  TableContainer,
  Table,
  TableHead,
  TableRow,
  TableBody,
  Paper,
} from "@mui/material";
import TableCell, { tableCellClasses } from "@mui/material/TableCell";

import FontConfig from "../../service/FontConfig";

const BeneficiosDetalheDemanda = () => {
  const StyledTableCell = styled(TableCell)(({ theme }) => ({
    [`&.${tableCellClasses.head}`]: {
      backgroundColor: theme.palette.common.black,
      color: theme.palette.common.white,
    },
    [`&.${tableCellClasses.body}`]: {
      fontSize: 14,
    },
  }));

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
    createData("Real", "500,00", "BR", "Alguma coisa provavelmente grande (ou não) vem aqui, como não sei, vou escrever algo grande")
  ];

  return (
    <TableContainer component={Paper}>
      <Table sx={{ minWidth: 750 }} aria-label="customized table">
        <TableHead sx={{backgroundColor: '#00579D'}}>
          <TableRow>
            <th>Tipo</th>
            <th>Valor Mensal</th>
            <th>Moeda</th>
            <th>Memória de cálculo</th>
          </TableRow>
        </TableHead>
        <TableBody>
          {rows.map((row) => (
            <StyledTableRow key={row.tipo} className="flex">
              <td className="justify-center">{row.tipo}</td>
              <td className="justify-center">{row.valorMensal}</td>
              <td className="justify-center">{row.moeda}</td>
              <td className="flex justify-center">{row.memoriaCalculo}</td>
            </StyledTableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
};

export default BeneficiosDetalheDemanda;
