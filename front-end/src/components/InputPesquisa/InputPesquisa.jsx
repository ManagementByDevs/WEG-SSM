import React, { useContext, } from "react";
import { useAutocomplete } from "@mui/material";

import { styled } from "@mui/system";

import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";
import debounce from "lodash/debounce";

// Variável para estilizar o componente
const Label = styled("label")({
  display: "block",
});

// Variável para estilizar o componente
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

// Variável para estilizar o componente
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

  // Variável utilizada para lógica do autoComplete
  const {
    getRootProps,
    getInputLabelProps,
    getInputProps,
    getListboxProps,
    getOptionProps,
    groupedOptions,
  } = useAutocomplete({
    id: "use-autocomplete-demo",
    options: props.listaAutocomplete,
    getOptionLabel: (option) => option.titulo,
  });

  // HandleChange utilizado para pegar o valor do input
  const handleChange = (event) => {
    props.setValorPesquisa(event.target.value);
    handleInputChangeDebounced(event.target.value); // chamando a função debounce
  };

  // HandleInputChange utilizado para o debounce do input
  const handleInputChange = (valorInput) => {
  };

  // Variável utilizada para o debounce do input
  const handleInputChangeDebounced = debounce(handleInputChange, 5000);

  return (
    <div>
      <div {...getRootProps()}>
        <Input
          {...getInputProps()}
          placeholder={texts.homeGerencia.pesquisarPorTitulo}
          sx={{ fontSize: FontConfig.medium }}
          value={props.valorPesquisa}
          onChange={(e) => {
            handleChange(e);
          }}
          onKeyDown={(e) => {
            props.eventoTeclado(e);
          }}
        />
      </div>
      {groupedOptions.length > 0 ? (
        <Listbox {...getListboxProps()}>
          {groupedOptions.map((option, index) => {
            return (
              option.titulo.includes(props.valorPesquisa) && (
                <li
                  {...getOptionProps({ option, index })}
                  onClick={() => {
                    props.setValorPesquisa(option.titulo);
                  }}
                >
                  {option.titulo}
                </li>
              )
            );
          })}
        </Listbox>
      ) : null}
    </div>
  );
}