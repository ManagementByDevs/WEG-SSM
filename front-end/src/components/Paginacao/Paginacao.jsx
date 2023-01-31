import React, { useState, useContext } from "react";

import {
  FormControl,
  MenuItem,
  Select,
  Pagination,
  Box,
  Typography,
} from "@mui/material";

import FontConfig from "../../service/FontConfig";

import FontContext from "../../service/FontContext";

const Paginacao = (props) => {
  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  const handleChange = (event) => {
    props.setTamanho(event.target.value);
  };

  const mudarPagina = (event, page) => {
    props.setPaginaAtual(page - 1);
  }

  return (
    <Box className="flex items-end" sx={{marginBottom: "30px"}}>
      <Typography fontSize={FontConfig.medium} sx={{ marginRight: "15px" }}>
        Itens por página:
      </Typography>
      <FormControl
        variant="standard"
        sx={{ marginRight: "10px", minWidth: 50 }}
      >
        <Select
          labelId="demo-simple-select-standard-label"
          id="demo-simple-select-standard"
          value={props.tamanhoPagina}
          onChange={handleChange}
        >
          <MenuItem value={20}>20</MenuItem>
          <MenuItem value={40}>40</MenuItem>
          <MenuItem value={60}>60</MenuItem>
          <MenuItem value={80}>80</MenuItem>
        </Select>
      </FormControl>
      <Pagination onChange={(e, page) => mudarPagina(e, page)} count={props.totalPaginas} shape="rounded" />
    </Box>
  );
};

export default Paginacao;
