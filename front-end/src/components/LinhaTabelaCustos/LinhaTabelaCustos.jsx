import React, { useState, useContext, useEffect } from "react";

import {
  TableRow,
  Box,
  TextareaAutosize,
  FormControl,
  Select,
  MenuItem,
  Tooltip,
  InputLabel
} from "@mui/material";

import FontConfig from "../../service/FontConfig";
import ColorModeContext from "../../service/TemaContext";

import DeleteOutlineOutlinedIcon from "@mui/icons-material/DeleteOutlineOutlined";

const LinhaTabelaCustos = (props) => {
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
    <TableRow className="border-b">
      <td align="center" className="pt-5 pb-5">
        <FormControl
          variant="standard"
          sx={{ marginRight: "10px", minWidth: 130, marginTop: "0.8rem" }}
        >
          <InputLabel id="demo-simple-select-label">Tipo</InputLabel>
          <Select
            labelId="demo-simple-select-standard-label"
            id="demo-simple-select-standard"
            value={props.dados.despesas[props.index].tipoDespesa}
            onChange={(e) => {
              let aux = [...props.custos];
              aux[props.indexCusto].despesas[props.index].tipoDespesa =
                e.target.value;
              props.setCustos(aux);
            }}
          >
            <MenuItem value={"1"}>Tipo 1</MenuItem>
            <MenuItem value={"2"}>Tipo 2</MenuItem>
            <MenuItem value={"3"}>Tipo 3</MenuItem>
          </Select>
        </FormControl>
      </td>
      <td align="center" className="pt-5 pb-5">
        <FormControl
          variant="standard"
          sx={{ marginRight: "10px", minWidth: 130, marginTop: "0.8rem" }}
        >
          <InputLabel id="demo-simple-select-label">Perfil</InputLabel>
          <Select
            labelId="demo-simple-select-standard-label"
            id="demo-simple-select-standard"
            value={props.dados.despesas[props.index].perfilDespesa}
            onChange={(e) => {
              let aux = [...props.custos];
              aux[props.indexCusto].despesas[props.index].perfilDespesa =
                e.target.value;
              props.setCustos(aux);
            }}
          >
            <MenuItem value={"1"}>Perfil 1</MenuItem>
            <MenuItem value={"2"}>Perfil 2</MenuItem>
            <MenuItem value={"3"}>Perfil 3</MenuItem>
          </Select>
        </FormControl>
      </td>
      <td align="center" className="pt-8 pb-5">
        <TextareaAutosize
          style={{
            width: "95%",
            resize: "none",
            textAlign: "center",
            backgroundColor: corFundoTextArea,
            marginTop: "0.8rem",
          }}
          fontSize={FontConfig.medium}
          className="flex outline-none border-solid border px-1 py-1.5 drop-shadow-sm rounded"
          placeholder="Digite o período..."
          value={props.dados.despesas[props.index].periodoExecucao}
          onChange={(e) => {
            let aux = [...props.custos];
            aux[props.indexCusto].despesas[props.index].periodoExecucao =
              e.target.value;
            props.setCustos(aux);
          }}
        />
      </td>
      <td align="center" className="pt-8 pb-5">
        <TextareaAutosize
          style={{
            width: "90%",
            resize: "none",
            textAlign: "center",
            backgroundColor: corFundoTextArea,
            marginTop: "0.8rem",
          }}
          fontSize={FontConfig.medium}
          className="flex outline-none border-solid border px-1 py-1.5 drop-shadow-sm rounded"
          placeholder="Digite as horas..."
          value={props.dados.despesas[props.index].horas}
          onChange={(e) => {
            let aux = [...props.custos];
            aux[props.indexCusto].despesas[props.index].horas = e.target.value;
            props.setCustos(aux);
          }}
        />
      </td>
      <td align="center" className="pt-8 pb-5">
        <TextareaAutosize
          style={{
            width: "95%",
            resize: "none",
            textAlign: "center",
            backgroundColor: corFundoTextArea,
            marginTop: "0.8rem",
          }}
          fontSize={FontConfig.medium}
          className="flex outline-none border-solid border px-1 py-1.5 drop-shadow-sm rounded"
          placeholder="Digite o valor..."
          value={props.dados.despesas[props.index].valorHora}
          onChange={(e) => {
            let aux = [...props.custos];
            aux[props.indexCusto].despesas[props.index].valorHora =
              e.target.value;
            props.setCustos(aux);
          }}
        />
      </td>
      <td align="center" className="pb-5 relative">
        <Box className="flex w-full justify-end absolute" sx={{ width: "98%" }}>
          <Tooltip title="Excluir linha">
            <DeleteOutlineOutlinedIcon
              fontSize="medium"
              className="mt-1 delay-120 hover:scale-110 duration-300"
              sx={{ color: "icon.main", cursor: "pointer" }}
              onClick={() =>
                props.deletarLinhaCustos(props.index, props.indexCusto)
              }
            />
          </Tooltip>
        </Box>
        <TextareaAutosize
          style={{
            width: "95%",
            resize: "none",
            textAlign: "center",
            backgroundColor: corFundoTextArea,
            marginTop: "2.8rem",
          }}
          fontSize={FontConfig.medium}
          className="flex outline-none border-solid border px-1 py-1.5 drop-shadow-sm rounded"
          placeholder="Digite o total..."
          value={props.dados.despesas[props.index].total}
          onChange={(e) => {
            let aux = [...props.custos];
            aux[props.indexCusto].despesas[props.index].total = e.target.value;
            props.setCustos(aux);
          }}
        />
      </td>
    </TableRow>
  );
};

export default LinhaTabelaCustos;
