import React from "react";
import { Box, Typography } from "@mui/material";

import FontConfig from "../../service/FontConfig";

import DeleteOutlineOutlinedIcon from "@mui/icons-material/DeleteOutlineOutlined";

const ResponsavelNegocio = (props) => {
  return (
    <Box className="flex w-full mt-5 items-end">
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
      {props.index !== 0 && (
        <Box className="flex ml-3">
          <DeleteOutlineOutlinedIcon
            className="delay-120 hover:scale-110 duration-300"
            fontSize="large"
            sx={{ color: "icon.main", cursor: "pointer" }}
            onClick={() => props.deleteResponsavel(props.dados)}
          />
        </Box>
      )}
    </Box>
  );
};

export default ResponsavelNegocio;
