import React, { useState, useContext, useEffect } from "react";

import { TableRow, Box, TextareaAutosize, FormControl, Select, MenuItem, Tooltip, InputLabel } from "@mui/material";

import DeleteOutlineOutlinedIcon from "@mui/icons-material/DeleteOutlineOutlined";

import ColorModeContext from "../../service/TemaContext";
import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";

// Componente para criar uma linha na tabela de custos
const LinhaTabelaCustos = (props) => {

  // Context que contém os textos do sistema
  const { texts, setTexts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // UseState para alterar a cor do textArea
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


  // UseEffect para calcular o total de custo
  useEffect(() => {
    let aux = [...props.custos];
    aux[props.indexCusto].custos[props.index].total = (
      aux[props.indexCusto].custos[props.index].horas *
      1 *
      (aux[props.indexCusto].custos[props.index].valorHora * 1)
    ).toFixed(2);
    props.setCustos(aux);
  }, [
    props.custos[props.indexCusto].custos[props.index].horas,
    props.custos[props.indexCusto].custos[props.index].valorHora,
  ]);

  return (
    <TableRow className="border-b">
      <td align="center" className="pt-5 pb-5">
        <TextareaAutosize
          style={{
            width: "80%",
            resize: "none",
            textAlign: "center",
            backgroundColor: corFundoTextArea,
            marginTop: "0.8rem",
            overflow: "hidden",
            textOverflow: "ellipsis",
            whiteSpace: "nowrap",
          }}
          fontSize={FontConfig.medium}
          className="flex outline-none border-solid border px-1 py-1.5 drop-shadow-sm rounded"
          placeholder={texts.linhaTabelaCustos.digiteTipo}
          value={props.dados.custos[props.index].tipoDespesa || ""}
          onChange={(e) => {
            let aux = [...props.custos];
            aux[props.indexCusto].custos[props.index].tipoDespesa =
              e.target.value;
            props.setCustos(aux);
          }}
        />
      </td>
      <td align="center" className="pt-5 pb-5">
        <TextareaAutosize
          style={{
            width: "80%",
            resize: "none",
            textAlign: "center",
            backgroundColor: corFundoTextArea,
            marginTop: "0.8rem",
          }}
          fontSize={FontConfig.medium}
          className="flex outline-none border-solid border px-1 py-1.5 drop-shadow-sm rounded"
          placeholder={texts.linhaTabelaCustos.digitePerfil}
          value={props.dados.custos[props.index].perfilDespesa || ""}
          onChange={(e) => {
            let aux = [...props.custos];
            aux[props.indexCusto].custos[props.index].perfilDespesa =
              e.target.value;
            props.setCustos(aux);
          }}
        />
      </td>
      <td align="center" className="pt-5 pb-5">
        <TextareaAutosize
          style={{
            width: "95%",
            minWidth: "155px",
            resize: "none",
            textAlign: "center",
            backgroundColor: corFundoTextArea,
            marginTop: "0.8rem",
          }}
          fontSize={FontConfig.medium}
          className="flex outline-none border-solid border px-1 py-1.5 drop-shadow-sm rounded"
          placeholder={texts.linhaTabelaCustos.digitePeriodo}
          value={props.dados.custos[props.index].periodoExecucao || ""}
          onChange={(e) => {
            let aux = [...props.custos];
            aux[props.indexCusto].custos[props.index].periodoExecucao =
              e.target.value;
            props.setCustos(aux);
          }}
        />
      </td>
      <td align="center" className="pt-5 pb-5">
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
          placeholder={texts.linhaTabelaCustos.digiteHoras}
          value={props.dados.custos[props.index].horas || ""}
          onChange={(e) => {
            let aux = [...props.custos];
            aux[props.indexCusto].custos[props.index].horas = e.target.value;
            props.setCustos(aux);
          }}
        />
      </td>
      <td align="center" className="pt-5 pb-5">
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
          placeholder={texts.linhaTabelaCustos.digiteValor}
          value={props.dados.custos[props.index].valorHora || ""}
          onChange={(e) => {
            let aux = [...props.custos];
            aux[props.indexCusto].custos[props.index].valorHora =
              e.target.value;
            props.setCustos(aux);
          }}
        />
      </td>
      <td className="relative">
        <Box className="flex w-full justify-end absolute" sx={{ width: "98%", top: 0 }}>
          <Tooltip title={texts.linhaTabelaCustos.titleExcluirLinha}>
            <DeleteOutlineOutlinedIcon
              fontSize="medium"
              className="mt-1 delay-120 hover:scale-110 duration-300"
              sx={{ color: "icon.main", cursor: "pointer" }}
              onClick={() =>
                props.deletarLinhaCustos(props.dados.custos[props.index].id, props.index)
              }
            />
          </Tooltip>
        </Box>
        <Box className="flex justify-center mt-5">{props.dados.custos[props.index].total || ""}</Box>
      </td>
    </TableRow>
  );
};

export default LinhaTabelaCustos;