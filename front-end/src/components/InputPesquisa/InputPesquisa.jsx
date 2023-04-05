import React, { useContext, useState, useEffect } from "react";
import { useAutocomplete } from "@mui/material";

import { styled } from "@mui/system";

import DemandaService from "../../service/demandaService";
import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";

import SwapVertIcon from "@mui/icons-material/SwapVert";

const Label = styled("label")({
  display: "block",
});

const Listbox = styled("ul")(({ theme }) => ({
  width: "28rem",
  margin: 0,
  border: "1.5px solid",
  borderColor: "input.main",
  borderRadius: "0 0 4px 4px",
  zIndex: 1,
  position: "absolute",
  listStyle: "none",
  backgroundColor: theme.palette.mode === "light" ? "#FFFFFF" : "#22252C",
  overflow: "auto",
  maxHeight: 200,
  border: "1px solid rgba(0,0,0,.25)",
  "& li": {
    padding: "0.1rem 0.5rem",
  },
  "& li.Mui-focused": {
    backgroundColor: theme.palette.mode === "light" ? "#BDBEC0" : "#535353",
    cursor: "pointer",
  },
  "& li:active": {
    backgroundColor: theme.palette.mode === "light" ? "#BDBEC0" : "#535353",
  },
}));

const Input = styled("input")(({ theme }) => ({
  width: "28rem",
  height: "2.2rem",
  padding: "0.5rem 0.5rem",
  backgroundColor: "transparent",
  color: "input.main",
  border: "1.5px solid",
  borderColor: "#BDBEC0",
  borderRadius: 4,
  outline: "none",
}));

export default function UseAutocomplete(props) {
  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // Context que contém os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  const {
    getRootProps,
    getInputLabelProps,
    getInputProps,
    getListboxProps,
    getOptionProps,
    groupedOptions,
  } = useAutocomplete({
    id: "use-autocomplete-demo",
    options: opcoes,
    getOptionLabel: (option) => option.titulo,
  });

  return (
    <div>
      <div {...getRootProps()}>
        <Input
          {...getInputProps()}
          placeholder={texts.homeGerencia.pesquisarPorTitulo}
          sx={{ fontSize: FontConfig.medium }}
          value={props.valorPesquisa}
          onChange={(e) => {
            props.setValorPesquisa(e.target.value);
          }}
          onKeyDown={(e) => {
            props.eventoTeclado(e);
          }}
        />
      </div>
      {groupedOptions.length > 0 ? (
        <Listbox
          {...getListboxProps()}
          onClick={() => {
          }}
        >
          {groupedOptions.map((option, index) => {
            return (
              option.titulo.includes(props.valorPesquisa) && (
                <li {...getOptionProps({ option, index })}>{option.titulo}</li>
              )
            );
          })}
        </Listbox>
      ) : null}
    </div>
  );
}
