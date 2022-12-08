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
  FormControl,
  Select,
  MenuItem,
} from "@mui/material";

import FontConfig from "../../service/FontConfig";

import ColorModeContext from "../../service/TemaContext";

import DeleteOutlineOutlinedIcon from "@mui/icons-material/DeleteOutlineOutlined";

const Custos = () => {
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
      <DeleteOutlineOutlinedIcon
        fontSize="large"
        className="mr-2 delay-120 hover:scale-110 duration-300"
        sx={{ color: "icon.main", cursor: "pointer" }}
      />
      <Paper className="w-full">
        <TableContainer component={Paper}>
          <Table sx={{ minWidth: 750 }} aria-label="customized table">
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
                <td align="center" className="pt-5 pb-5">
                  <FormControl
                    variant="standard"
                    sx={{ marginRight: "10px", minWidth: 130 }}
                  >
                    <Select
                      labelId="demo-simple-select-standard-label"
                      id="demo-simple-select-standard"
                    >
                      <MenuItem value={"Real"}>Tipo 1</MenuItem>
                      <MenuItem value={"Potencial"}>Tipo 2</MenuItem>
                      <MenuItem value={"Qualitativo"}>Tipo 3</MenuItem>
                    </Select>
                  </FormControl>
                </td>
                <td align="center" className="pt-5 pb-5">
                  <FormControl
                    variant="standard"
                    sx={{ marginRight: "10px", minWidth: 130 }}
                  >
                    <Select
                      labelId="demo-simple-select-standard-label"
                      id="demo-simple-select-standard"
                    >
                      <MenuItem value={"Real"}>Perfil 1</MenuItem>
                      <MenuItem value={"Potencial"}>Perfil 2</MenuItem>
                      <MenuItem value={"Qualitativo"}>Perfil 3</MenuItem>
                    </Select>
                  </FormControl>
                </td>
                <td align="center" className="pt-5 pb-5">
                  <TextareaAutosize
                    style={{
                      width: "95%",
                      resize: "none",
                      textAlign: "center",
                      backgroundColor: corFundoTextArea,
                    }}
                    fontSize={FontConfig.medium}
                    className="flex outline-none border-solid border px-1 py-1.5 drop-shadow-sm rounded"
                    placeholder="Digite o período..."
                  />
                </td>
                <td align="center" className="pt-5 pb-5">
                  <TextareaAutosize
                    style={{
                      width: "90%",
                      resize: "none",
                      textAlign: "center",
                      backgroundColor: corFundoTextArea,
                    }}
                    fontSize={FontConfig.medium}
                    className="flex outline-none border-solid border px-1 py-1.5 drop-shadow-sm rounded"
                    placeholder="Digite as horas..."
                  />
                </td>
                <td align="center" className="pt-5 pb-5">
                  <TextareaAutosize
                    style={{
                      width: "95%",
                      resize: "none",
                      textAlign: "center",
                      backgroundColor: corFundoTextArea,
                    }}
                    fontSize={FontConfig.medium}
                    className="flex outline-none border-solid border px-1 py-1.5 drop-shadow-sm rounded"
                    placeholder="Digite o valor..."
                  />
                </td>
                <td align="center" className="pt-5 pb-5">
                  <TextareaAutosize
                    style={{
                      width: "95%",
                      resize: "none",
                      textAlign: "center",
                      backgroundColor: corFundoTextArea,
                    }}
                    fontSize={FontConfig.medium}
                    className="flex outline-none border-solid border px-1 py-1.5 drop-shadow-sm rounded"
                    placeholder="Digite o total..."
                  />
                </td>
                <td align="center" className="pt-5 pb-5">
                  <TextareaAutosize
                    style={{
                      width: "95%",
                      marginRight: "10px",
                      resize: "none",
                      textAlign: "center",
                      backgroundColor: corFundoTextArea,
                    }}
                    fontSize={FontConfig.medium}
                    className="flex outline-none border-solid border px-1 py-1.5 drop-shadow-sm rounded"
                    placeholder="Digite os CCs..."
                  />
                </td>
              </TableRow>
            </TableBody>
          </Table>
        </TableContainer>
      </Paper>
    </Box>
  );
};

export default Custos;
