import React, { useState, useContext, useEffect } from "react";
import { TableRow, Box, Tooltip, Input, InputAdornment } from "@mui/material";

import DeleteOutlineOutlinedIcon from "@mui/icons-material/DeleteOutlineOutlined";

import ColorModeContext from "../../service/TemaContext";

import FontContext from "../../service/FontContext";
import TextLanguage from "../../service/TextLanguage";
import TextLanguageContext from "../../service/TextLanguageContext";

const LinhaTabelaCCs = (props) => {
  // Context que contém os textos do sistema
  const { texts, setTexts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // UseState para alterar a cor do fundo textArea
  const [corFundoTextArea, setCorFundoTextArea] = useState("#FFFF");

  // Variável para alterar o tema
  const { mode } = useContext(ColorModeContext);

  // UseEffect para alterar a cor de fundo do textArea
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
          <Tooltip title={texts.LinhaTabelaCCs.titleExcluirLinha}>
            <DeleteOutlineOutlinedIcon
              fontSize="medium"
              className="mt-1 delay-120 hover:scale-110 duration-300"
              sx={{ color: "icon.main", cursor: "pointer" }}
              onClick={() =>
                props.deletarLinhaCCs(props.dados.ccs[props.index].id, props.index)
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
            placeholder={texts.LinhaTabelaCCs.digiteCodigo}
            size="small"
            style={{
              width: "70%",
              height: "2.4rem",
              backgroundColor: corFundoTextArea,
              paddingLeft: "10px",
            }}
            value={props.dados.ccs[props.index].codigo || ""}
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
            value={props.dados.ccs[props.index].porcentagem || ""}
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
