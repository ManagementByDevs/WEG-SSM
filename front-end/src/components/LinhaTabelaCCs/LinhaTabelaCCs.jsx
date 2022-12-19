import React, { useState, useContext, useEffect } from "react";
import {
  TableRow,
  Box,
  Tooltip,
  TextField,
  FormControl,
  Input,
  InputAdornment,
} from "@mui/material";

import FontConfig from "../../service/FontConfig";

import DeleteOutlineOutlinedIcon from "@mui/icons-material/DeleteOutlineOutlined";

import ColorModeContext from "../../service/TemaContext";

const LinhaTabelaCCs = (props) => {
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
    <TableRow>
      <td align="center" className="pb-5 relative">
        <Box className="flex w-full justify-end absolute" sx={{ width: "98%" }}>
          <Tooltip title="Excluir linha">
            <DeleteOutlineOutlinedIcon
              fontSize="medium"
              className="mt-1 delay-120 hover:scale-110 duration-300"
              sx={{ color: "icon.main", cursor: "pointer" }}
            />
          </Tooltip>
        </Box>
        <Box
          className="flex w-full justify-around pr-10 pl-4"
          sx={{ marginTop: "2rem" }}
        >
          <TextField
            id="outlined-basic"
            label="Digite o código"
            variant="outlined"
            size="small"
            style={{
              width: "70%",
              backgroundColor: corFundoTextArea,
            }}
            fontSize={FontConfig.medium}
            className="flex outline-none border-solid border drop-shadow-sm rounded"
            placeholder="Digite o código..."
            value={props.dados.ccs[props.index].codigo}
            onChange={(e) => {
              let aux = [...props.custos];
              aux[props.indexCusto].ccs[props.index].codigo = e.target.value;
              props.setCustos(aux);
            }}
          />
          <Input
            className="border rounded drop-shadow-sm outline-none"
            id="standard-adornment-weight"
            endAdornment={<InputAdornment position="end">%</InputAdornment>}
            aria-describedby="standard-weight-helper-text"
            inputProps={{
              "aria-label": "weight"
            }}
            fontSize={FontConfig.medium}
            size="small"
            style={{
              width: "18%",
              backgroundColor: corFundoTextArea,
              padding: "0 10px",
            }}
            value={props.dados.ccs[props.index].porcentagem}
            onChange={(e) => {
              let aux = [...props.custos];
              aux[props.indexCusto].ccs[props.index].porcentagem = e.target.value;
              props.setCustos(aux);
            }}
          />
        </Box>
      </td>
    </TableRow>
  );
};

export default LinhaTabelaCCs;
