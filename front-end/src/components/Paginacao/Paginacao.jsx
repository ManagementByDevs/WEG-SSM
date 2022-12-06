import React, { useState } from "react";

import {
  FormControl,
  MenuItem,
  Select,
  Pagination,
  Box,
  Typography,
} from "@mui/material";

import FontConfig from "../../service/FontConfig";

const Paginacao = () => {
  const [valor, setValor] = React.useState(18);

  const handleChange = (event) => {
    setValor(event.target.value);
  };

  return (
    <Box className="flex items-end">
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
          value={valor}
          onChange={handleChange}
        >
          <MenuItem value={18}>18</MenuItem>
          <MenuItem value={36}>36</MenuItem>
          <MenuItem value={54}>54</MenuItem>
          <MenuItem value={72}>72</MenuItem>
        </Select>
      </FormControl>
      <Pagination count={10} shape="rounded" />
    </Box>
  );
};

export default Paginacao;
