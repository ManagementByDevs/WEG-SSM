import React from "react";

import { Box, Typography } from "@mui/material";

import FontConfig from "../../service/FontConfig";

const BeneficiosDetalheDemanda = () => {
  return (
    <Box className="flex border rounded p-2 pt-4" sx={{ width: "100%", height: "7rem", minWidth: '620px' }}>
      <Box className="flex flex-col gap-2 ml-3" sx={{width: '20%'}}>
        <Box className="flex">
          <Typography fontSize={FontConfig.medium} fontWeight='600' color="text.primary">Tipo:</Typography>
          <Typography fontSize={FontConfig.default} color="text.secondary" sx={{ marginLeft: "10px" }}>Potencial</Typography>
        </Box>
        <Box className="flex flex-col">
          <Typography fontSize={FontConfig.medium} fontWeight='600' color="text.primary">Valor Mensal:</Typography>
          <Typography fontSize={FontConfig.default} color="text.secondary" sx={{ marginLeft: "10px" }}>1000,00 BR</Typography>
        </Box>
      </Box>
      <Box className="flex flex-col" sx={{width: '80%'}}>
          <Typography fontSize={FontConfig.medium} fontWeight='600' color="text.primary">Memória de cálculo:</Typography>
          <Typography fontSize={FontConfig.default} color="text.secondary" sx={{ marginLeft: "10px" }}>Lorem Ipsum is simply dummy text of the prinpe and scrambled it to make a type specimen book.</Typography>
      </Box>
    </Box>
  );
};

export default BeneficiosDetalheDemanda;