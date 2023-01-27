import React from "react";

import { Box, Typography } from "@mui/material";

import FontContext from "../../service/FontContext";

const InputComLabel = (props) => {
  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // Função para salvar o valor do props recebido para o input (mudando também o valor do próprio input)
  const save = (e) => {
    props.saveInputValue(e.target.value);
  };

  return (
    <Box sx={{ width: "100%" }}>
      <Box className="flex">
        <Typography
          fontSize={props.fontConfig}
          sx={{ fontWeight: "600", cursor: "default" }}
          gutterBottom
        >
          {props.label}
        </Typography>
        <Typography
          fontSize={props.fontConfig}
          sx={{ fontWeight: "800", cursor: "default", margin: "0 .2% .2% .2%" }}
          className="text-red-600"
          gutterBottom
        >
          *
        </Typography>
      </Box>
      {props.component === "input" ? (
        <Box
          value={props.texto}
          onChange={(e) => {
            save(e);
          }}
          fontSize={props.fontConfig}
          className="outline-none border-solid border border-l-4 px-1 py-1.5 drop-shadow-sm rounded"
          sx={{
            borderLeftColor: "primary.main",
            width: "100%;",
            backgroundColor: "background.default",
            fontWeight: "300",
          }}
          component="input"
          placeholder={props.placeholder}
        />
      ) : (
        <Box
          value={props.texto}
          onChange={(e) => {
            save(e);
          }}
          fontSize={props.fontConfig}
          className="outline-none border-solid border border-l-4 px-1 py-1.5 drop-shadow-sm rounded"
          sx={{
            borderLeftColor: "primary.main",
            width: "100%;",
            backgroundColor: "background.default",
            fontWeight: "300",
            resize: "none",
          }}
          component="textarea"
          placeholder={props.placeholder}
          rows={props.rows}
        />
      )}
    </Box>
  );
};

export default InputComLabel;
