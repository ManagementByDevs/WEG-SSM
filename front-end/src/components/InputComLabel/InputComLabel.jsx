import React, { useState } from "react";

import { Box, Typography } from "@mui/material";
import { useEffect } from "react";

const InputComLabel = (props) => {
  const [texto, setTexto] = useState("");

  useEffect(() => {
    console.log(props.texto);
    if(props.texto) {
      setTexto(props.texto);
    }
  }, [])

  const save = (e) => {
    setTexto(e.target.value);
    props.saveInputValue(texto);
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
          value={texto}
          onChange={(e) => {
            save(e);
          }}
          fontSize={props.fontConfig}
          className="outline-none border-solid border border-l-4 px-1 py-1.5 drop-shadow-sm rounded

"
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
          value={texto}
          onChange={(e) => {
            save(e);
          }}
          fontSize={props.fontConfig}
          className="outline-none border-solid border border-l-4 px-1 py-1.5 drop-shadow-sm rounded

            "
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
