import React, { useState, useContext, useEffect } from "react";

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
  Button,
  FormControl,
  Select,
  MenuItem,
} from "@mui/material";

import DeleteOutlineOutlinedIcon from "@mui/icons-material/DeleteOutlineOutlined";

import FontConfig from "../../service/FontConfig";

import ColorModeContext from "../../service/TemaContext";

const BeneficiosDetalheDemanda = (props) => {
  const [corFundoTextArea, setCorFundoTextArea] = useState("#FFFF");
  const { mode } = useContext(ColorModeContext);

  useEffect(() => {
    if (mode === "dark") {
      setCorFundoTextArea("#212121");
    } else {
      setCorFundoTextArea("#FFFF");
    }
  }, [mode]);

  const StyledTableRow = styled(TableRow)(({ theme }) => ({
    "&:nth-of-type(odd)": {
      backgroundColor: theme.palette.action.hover,
    },
    "&:last-child td, &:last-child th": {
      border: 0,
    },
  }));

  const deleteBeneficio = () => {
    props.delete(props.index);
  };

  return (
    <Box className="flex items-center">
      {props.editavel ? (
        <>
          <DeleteOutlineOutlinedIcon
            fontSize="large"
            className="delay-120 hover:scale-110 duration-300 mr-2"
            sx={{ color: "icon.main", cursor: "pointer" }}
            onClick={deleteBeneficio}
          />
          <TableContainer component={Paper}>
            <Table sx={{ minWidth: 750 }} aria-label="customized table">
              <TableHead sx={{ backgroundColor: "primary.main" }}>
                <TableRow>
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
                      Tipo
                    </Typography>
                  </th>
                  <th
                    align="center"
                    className="p-4 w-0"
                    style={{ width: "15%" }}
                  >
                    <Typography
                      fontSize={FontConfig.big}
                      fontWeight="800"
                      color="text.white"
                    >
                      Valor Mensal
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
                      Moeda
                    </Typography>
                  </th>
                  <th
                    align="center"
                    className="p-4 w-0"
                    style={{ width: "30%" }}
                  >
                    <Typography
                      fontSize={FontConfig.big}
                      fontWeight="800"
                      color="text.white"
                    >
                      Mem??ria de C??lculo
                    </Typography>
                  </th>
                </TableRow>
              </TableHead>
              <TableBody>
                <TableRow>
                  <td align="center">
                    <FormControl
                      variant="standard"
                      sx={{ marginRight: "10px", minWidth: 90 }}
                    >
                      <Select
                        labelId="demo-simple-select-standard-label"
                        id="demo-simple-select-standard"
                        value={props.beneficio.tipoBeneficio}
                        onChange={(e) => {
                          props.setBeneficio(
                            { ...props.beneficio, tipoBeneficio: e.target.value },
                            props.index
                          );
                        }}
                      >
                        <MenuItem value={"Real"}>Real</MenuItem>
                        <MenuItem value={"Potencial"}>Potencial</MenuItem>
                        <MenuItem value={"Qualitativo"}>Qualitativo</MenuItem>
                      </Select>
                    </FormControl>
                  </td>
                  <td align="center">
                    <Box
                      value={props.beneficio.valor_mensal}
                      fontSize={FontConfig.medium}
                      onChange={(e) => {
                        props.setBeneficio(
                          { ...props.beneficio, valor_mensal: e.target.value },
                          props.index
                        );
                      }}
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
                    <FormControl
                      variant="standard"
                      sx={{ marginRight: "10px", minWidth: 90 }}
                    >
                      <Select
                        labelId="demo-simple-select-standard-label"
                        id="demo-simple-select-standard"
                        value={props.beneficio.moeda}
                        onChange={(e) => {
                          props.setBeneficio(
                            { ...props.beneficio, moeda: e.target.value },
                            props.index
                          );
                        }}
                      >
                        <MenuItem value={"Real"}>BR</MenuItem>
                        <MenuItem value={"Dolar"}>UR</MenuItem>
                        <MenuItem value={"Euro"}>EUR</MenuItem>
                      </Select>
                    </FormControl>
                  </td>
                  <td
                    align="center"
                    className="p-3 pl-5 pr-5 flex justify-center"
                  >
                    <TextareaAutosize
                      style={{
                        width: "100%",
                        resize: "none",
                        textAlign: "center",
                        backgroundColor: corFundoTextArea,
                      }}
                      value={props.beneficio.memoriaCalculo}
                      fontSize={FontConfig.medium}
                      onChange={(e) => {
                        props.setBeneficio(
                          {
                            ...props.beneficio,
                            memoriaCalculo: e.target.value,
                          },
                          props.index
                        );
                      }}
                      className="flex outline-none border-solid border px-1 py-1.5 drop-shadow-sm rounded"
                      placeholder="Digite a mem??ria de c??lculo..."
                    />
                  </td>
                </TableRow>
              </TableBody>
            </Table>
          </TableContainer>
        </>
      ) : (
        <TableContainer component={Paper}>
          <Table sx={{ minWidth: 750 }} aria-label="customized table">
            <TableHead sx={{ backgroundColor: "primary.main" }}>
              <TableRow>
                <th align="center" className="p-4 w-0" style={{ width: "10%" }}>
                  <Typography
                    fontSize={FontConfig.big}
                    fontWeight="800"
                    color="text.white"
                  >
                    Tipo
                  </Typography>
                </th>
                <th align="center" className="p-4 w-0" style={{ width: "15%" }}>
                  <Typography
                    fontSize={FontConfig.big}
                    fontWeight="800"
                    color="text.white"
                  >
                    Valor Mensal
                  </Typography>
                </th>
                <th align="center" className="p-4 w-0" style={{ width: "10%" }}>
                  <Typography
                    fontSize={FontConfig.big}
                    fontWeight="800"
                    color="text.white"
                  >
                    Moeda
                  </Typography>
                </th>
                <th align="center" className="p-4 w-0" style={{ width: "30%" }}>
                  <Typography
                    fontSize={FontConfig.big}
                    fontWeight="800"
                    color="text.white"
                  >
                    Mem??ria de C??lculo
                  </Typography>
                </th>
              </TableRow>
            </TableHead>
            <TableBody>
              <StyledTableRow className="flex">
                <td align="center">
                  <Typography fontSize={FontConfig.medium} color="text.primary">
                    {props.beneficio.tipoBeneficio}
                  </Typography>
                </td>
                <td align="center">
                  <Typography fontSize={FontConfig.medium} color="text.primary">
                    {props.beneficio.valor_mensal}
                  </Typography>
                </td>
                <td align="center">
                  <Typography fontSize={FontConfig.medium} color="text.primary">
                    {props.beneficio.moeda}
                  </Typography>
                </td>
                <td
                  align="center"
                  className="p-3 pl-5 pr-5 flex justify-center"
                >
                  <Typography
                    className="text-center"
                    fontSize={FontConfig.medium}
                    color="text.primary"
                    sx={{ width: "100%" }}
                  >
                    {props.beneficio.memoriaCalculo}
                  </Typography>
                </td>
              </StyledTableRow>
            </TableBody>
          </Table>
        </TableContainer>
      )}
    </Box>
  );
};

export default BeneficiosDetalheDemanda;
