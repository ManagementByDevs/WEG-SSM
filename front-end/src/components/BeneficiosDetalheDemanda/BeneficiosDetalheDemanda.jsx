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
} from "@mui/material";

import DeleteOutlineOutlinedIcon from "@mui/icons-material/DeleteOutlineOutlined";

import FontConfig from "../../service/FontConfig";

import ColorModeContext from "../../service/TemaContext";

const BeneficiosDetalheDemanda = (props) => {
  const [corFundoTextArea, setCorFundoTextArea] = useState("#FFFF");
  const { mode } = useContext(ColorModeContext);

  const [beneficio, setBeneficio] = useState({
    tipo: props.beneficio.tipo,
    valorMensal: props.beneficio.valorMensal,
    moeda: props.beneficio.moeda,
    memoriaCalculo: props.beneficio.memoriaCalculo,
    visible: true,
  });

  const [teste, setTeste] = useState(props.beneficio.tipo);

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
    // hide last border
    "&:last-child td, &:last-child th": {
      border: 0,
    },
  }));

  const save = () => {
    props.save(beneficio, props.index);
  };

  const handleTipoChange = (e) => {
    console.log("aaa");
    setTeste(e.target.value);
    // setBeneficio({ ...beneficio, tipo: e.target.value });
  };

  useEffect(() => {
    save();
  }, [beneficio]);

  return (
    <Box className="flex items-center">
      {props.editavel ? (
        <>
          <DeleteOutlineOutlinedIcon
            fontSize="large"
            className="delay-120 hover:scale-110 duration-300 mr-2"
            sx={{ color: "icon.main", cursor: "pointer" }}
            onClick={() => {
              props.delete(props.index);
            }}
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
                      Memória de Cálculo
                    </Typography>
                  </th>
                </TableRow>
              </TableHead>
              <TableBody>
                <TableRow>
                  <td align="center">
                    <Box
                      component="input"
                      value={beneficio.tipo}
                      fontSize={FontConfig.medium}
                      onChange={(e) =>
                        setBeneficio({ ...beneficio, tipo: e.target.value })
                      }
                      color="text.primary"
                      className="flex outline-none border-solid border px-1 py-1.5 drop-shadow-sm rounded text-center"
                      sx={{
                        width: "80%;",
                        height: "30px",
                        backgroundColor: "background.default",
                      }}
                      placeholder="Digite o tipo do benefício..."
                    />
                  </td>
                  <td align="center">
                    <Box
                      value={beneficio.valorMensal}
                      fontSize={FontConfig.medium}
                      onChange={(e) => {
                        setBeneficio({
                          ...beneficio,
                          valorMensal: e.target.value,
                        });
                        save();
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
                    <Box
                      value={beneficio.moeda}
                      fontSize={FontConfig.medium}
                      onChange={(e) => {
                        setBeneficio({ ...beneficio, moeda: e.target.value });
                        save();
                      }}
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
                        textAlign: "center",
                        backgroundColor: corFundoTextArea,
                      }}
                      value={beneficio.memoriaCalculo}
                      fontSize={FontConfig.medium}
                      onChange={(e) => {
                        setBeneficio({
                          ...beneficio,
                          memoriaCalculo: e.target.value,
                        });
                        save();
                      }}
                      className="flex outline-none border-solid border px-1 py-1.5 drop-shadow-sm rounded"
                      placeholder="Digite a memória de cálculo..."
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
                    Memória de Cálculo
                  </Typography>
                </th>
              </TableRow>
            </TableHead>
            <TableBody>
              <StyledTableRow className="flex">
                <td align="center">
                  <Typography fontSize={FontConfig.medium} color="text.primary">
                    {beneficio.tipo}
                  </Typography>
                </td>
                <td align="center">
                  <Typography fontSize={FontConfig.medium} color="text.primary">
                    {beneficio.valorMensal}
                  </Typography>
                </td>
                <td align="center">
                  <Typography fontSize={FontConfig.medium} color="text.primary">
                    {beneficio.moeda}
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
                    {beneficio.memoriaCalculo}
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
