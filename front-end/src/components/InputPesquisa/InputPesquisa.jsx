import React, { useContext, useState, useEffect } from "react";
import {
  Box,
  Button,
  IconButton,
  Tab,
  Tooltip,
  useAutocomplete,
  Autocomplete,
  TextField,
} from "@mui/material";

import { styled } from "@mui/system";

import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";

import SwapVertIcon from "@mui/icons-material/SwapVert";

const Label = styled("label")({
  display: "block",
});

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

export default function UseAutocomplete(props) {
  // Context que contém os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  const [textoPesquisa, setTextoPesquisa] = useState("");

  useEffect(() => {}, [textoPesquisa]);

  const {
    getRootProps,
    getInputLabelProps,
    getInputProps,
    getListboxProps,
    getOptionProps,
    groupedOptions,
  } = useAutocomplete({
    id: "use-autocomplete-demo",
    options: top100Films,
    getOptionLabel: (option) => option.title,
    // blurOnSelect: true,
    // onChange: (value) => {
    //   props.salvarPesquisa(value);
    //   props.pesquisaTitulo();
    // },
  });

  return (
    <div>
      <div {...getRootProps()}>
        <Input
          placeholder={texts.homeGerencia.pesquisarPorTitulo}
          {...getInputProps()}
          onKeyDown={(e) => {
            console.log(getInputProps().value);
            props.eventoTeclado(e);
            props.salvarPesquisa(e);
          }}
        />
      </div>
      {groupedOptions.length > 0 ? (
        <Listbox
          onChange={(e) => {
            console.log("getInputProps().value");
            props.eventoTeclado(e);
            props.salvarPesquisa(e);
          }}
          {...getListboxProps()}
        >
          {groupedOptions.map((option, index) => (
            <li {...getOptionProps({ option, index })}>{option.title}</li>
          ))}
        </Listbox>
      ) : null}
    </div>
  );
}

const top100Films = [
  { title: "The Shawshank Redemption", year: 1994 },
  { title: "The Godfather", year: 1972 },
  { title: "The Godfather: Part II", year: 1974 },
  { title: "The Dark Knight", year: 2008 },
  { title: "12 Angry Men", year: 1957 },
];
