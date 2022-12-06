import React from "react";

import { Box, Paper, Typography } from "@mui/material";

import DeleteOutlineOutlinedIcon from "@mui/icons-material/DeleteOutlineOutlined";

import FontConfig from "../../service/FontConfig";

const Pautas = (props) => {
  return (
    <Paper
      className="flex flex-col border-t-4 pt-2 pb-3 px-6"
      sx={{ borderColor: "primary.main", minWidth: "729px" }}
    >
      <Box className="flex w-full justify-between">
        <Typography fontSize={FontConfig.big} fontWeight="600">
          #Número Sequencial
        </Typography>
        <Box className="flex items-center">
          <Typography
            fontSize={FontConfig.big}
            fontWeight="600"
            sx={{ color: "text.secondary" }}
          >
            Data
          </Typography>
          <DeleteOutlineOutlinedIcon
            className="delay-120 hover:scale-110 duration-300 ml-5"
            sx={{
              color: "icon.main",
              cursor: "pointer",
              fontSize: "30px",
            }}
          />
        </Box>
      </Box>
      <Box className="flex w-full justify-between">
        <Box className="flex">
          <Typography fontSize={FontConfig.big} fontWeight="600">
            Comissão:{" "}
          </Typography>
          <Typography fontSize={FontConfig.big} fontWeight="600">
            Nome da comissao{" "}
          </Typography>
        </Box>
      </Box>
    </Paper>
  );
};

export default Pautas;
