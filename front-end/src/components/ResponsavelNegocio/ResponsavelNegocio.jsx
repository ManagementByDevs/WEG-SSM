import React from "react";
import { Box, Typography } from "@mui/material";

import FontConfig from "../../service/FontConfig";

const ResponsavelNegocio = () => {
  return (
    <Box className="flex w-full mt-5 items-center">
      <Box className="flex flex-col">
        <Box className="flex mb-2">
          <Typography sx={{ fontSize: FontConfig.big, fontWeight: "600" }}>
            Responsável do negócio:
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
        <Box sx={{ width: "30rem" }}>
          <Box
            fontSize={FontConfig.medium}
            color="text.primary"
            className="flex outline-none border-solid border px-1 py-1.5 drop-shadow-sm rounded border-l-4"
            sx={{
              width: "100%;",
              height: "30px",
              backgroundColor: "background.default",
              borderLeftColor: "primary.main",
            }}
            component="input"
            placeholder="Insira o responsável pelo negócio..."
          />
        </Box>
      </Box>
      <Box className="flex flex-col ml-10">
        <Box className="flex mb-2">
          <Typography sx={{ fontSize: FontConfig.big, fontWeight: "600" }}>
            Área:
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
        <Box sx={{ width: "20rem" }}>
          <Box
            fontSize={FontConfig.medium}
            color="text.primary"
            className="flex outline-none border-solid border px-1 py-1.5 drop-shadow-sm rounded border-l-4"
            sx={{
              width: "100%;",
              height: "30px",
              backgroundColor: "background.default",
              borderLeftColor: "primary.main",
            }}
            component="input"
            placeholder="Insira a área do responsável..."
          />
        </Box>
      </Box>
    </Box>
  );
};

export default ResponsavelNegocio;
