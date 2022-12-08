import React, { useState } from "react";
import { Box, FormControl, Select, MenuItem, Typography } from "@mui/material";

import FontConfig from "../../service/FontConfig";

const FormularioCustosProposta = () => {
  return (
    <Box className="flex flex-col" sx={{ height: "45rem" }}>
      <Box className="mt-10">
        <Box className="flex w-full justify-between">
          <Box>
            <Box className="flex mb-2">
              <Typography sx={{ fontSize: FontConfig.big }}>
                Período de execução:
              </Typography>
              <Typography
                sx={{
                  fontSize: FontConfig.big,
                  color: "red",
                  marginLeft: "5px",
                }}
              >
                *
              </Typography>
            </Box>
            <Box className="flex">
              <Box className="mr-5">INPUT DAATA</Box>
              <Box>
                <Typography sx={{ fontSize: FontConfig.big }}>à</Typography>
              </Box>
              <Box className="ml-5">INPUT DATA</Box>
            </Box>
          </Box>
          <Box>
            <Box className="flex mb-2">
              <Typography sx={{ fontSize: FontConfig.big }}>
                Payback simples:
              </Typography>
              <Typography
                sx={{
                  fontSize: FontConfig.big,
                  color: "red",
                  marginLeft: "5px",
                }}
              >
                *
              </Typography>
            </Box>
            <Box className="flex">
              <Box
                fontSize={FontConfig.medium}
                color="text.primary"
                className="flex outline-none border-solid border px-1 py-1.5 drop-shadow-sm rounded text-center"
                sx={{
                  width: "20%;",
                  height: "30px",
                  backgroundColor: "background.default",
                }}
                component="input"
                placeholder="Qtd"
              />
              <FormControl
                variant="standard"
                sx={{ marginLeft: "20px", minWidth: 100 }}
              >
                <Select
                  labelId="demo-simple-select-standard-label"
                  id="demo-simple-select-standard"
                >
                  <MenuItem value={"Real"}>Dias</MenuItem>
                  <MenuItem value={"Potencial"}>Semanas</MenuItem>
                  <MenuItem value={"Qualitativo"}>Meses</MenuItem>
                </Select>
              </FormControl>
            </Box>
          </Box>
          <Box>
            <Box className="flex mb-2">
              <Typography sx={{ fontSize: FontConfig.big }}>
                Código CLEITOM:
              </Typography>
              <Typography
                sx={{
                  fontSize: FontConfig.big,
                  color: "red",
                  marginLeft: "5px",
                }}
              >
                *
              </Typography>
            </Box>
            <Box className="flex">
              <Box
                fontSize={FontConfig.medium}
                color="text.primary"
                className="flex outline-none border-solid border px-1 py-1.5 drop-shadow-sm rounded text-center"
                sx={{
                  width: "100%;",
                  height: "30px",
                  backgroundColor: "background.default",
                }}
                component="input"
                placeholder="Qtd"
              />
            </Box>
          </Box>
        </Box>
        <Box></Box>
        <Box></Box>
      </Box>
    </Box>
  );
};

export default FormularioCustosProposta;
