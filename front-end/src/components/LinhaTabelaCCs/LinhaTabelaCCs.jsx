import React, { useState, useContext, useEffect } from "react";
import {
  TableRow,
  Box,
  Tooltip,
  TextField,
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
    <TableRow className="border-b">
      <td align="center" className="pb-5 relative">
        <Box className="flex w-full justify-end absolute" sx={{ width: "98%" }}>
          <Tooltip title="Excluir linha">
            <DeleteOutlineOutlinedIcon
              fontSize="medium"
              className="mt-1 delay-120 hover:scale-110 duration-300"
              sx={{ color: "icon.main", cursor: "pointer" }}
              onClick={() =>
                props.deletarLinhaCCs(props.index, props.indexCusto)
              }
            />
          </Tooltip>
        </Box>
        <Box
          className="flex w-full justify-around pr-10 pl-4"
          sx={{ marginTop: "2.8rem" }}
        >
          <Input
            className="border rounded drop-shadow-sm outline-none"
            fontSize={FontConfig.medium}
            placeholder="Digite o código..."
            size="small"
            style={{
              width: "70%",
              height: "2.4rem",
              backgroundColor: corFundoTextArea,
              paddingLeft: "10px",
            }}
            value={props.dados.ccs[props.index].codigo}
            onChange={(e) => {
              let aux = [...props.custos];
              aux[props.indexCusto].ccs[props.index].codigo = e.target.value;
              props.setCustos(aux);
            }}
          />
          <Input
            className="border rounded drop-shadow-sm outline-none"
            endAdornment={
              <InputAdornment className="mb-1" position="end">
                %
              </InputAdornment>
            }
            aria-describedby="standard-weight-helper-text"
            fontSize={FontConfig.medium}
            size="small"
            style={{
              width: "22%",
              backgroundColor: corFundoTextArea,
              padding: "0 10px",
            }}
            value={props.dados.ccs[props.index].porcentagem}
            onChange={(e) => {
              let aux = [...props.custos];
              aux[props.indexCusto].ccs[props.index].porcentagem =
                e.target.value;
              props.setCustos(aux);
            }}
          />
        </Box>
      </td>
    </TableRow>
  );
};

export default LinhaTabelaCCs;
