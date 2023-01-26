import React from "react";
import { Box, Typography, FormControlLabel } from "@mui/material";

import FontConfig from "../../service/FontConfig";

import HighlightOffOutlinedIcon from "@mui/icons-material/HighlightOffOutlined";

const Notificacao = () => {
  // Container da natificacao
  return (
    <Box
      className="flex items-center border rounded px-2 py-1 mb-2"
      sx={{ width: "100%"}}
    >
      <HighlightOffOutlinedIcon sx={{ fontSize: "35px", marginRight: "5px" }} />
      <Box className="flex flex-col w-32">
        <Typography
          className="overflow-hidden text-ellipsis whitespace-nowrap"
          fontSize={FontConfig.default}
          color={"text.primary"}
          sx={{ fontWeight: 600 }}
        >
          Aqui é o titulo da notificação que aparecerá!
        </Typography>
        <Typography
          className="text-end "
          fontSize={FontConfig.small}
          color={"text.secondary"}
          sx={{ fontWeight: 600 }}
        >
          à 2 dias
        </Typography>
      </Box>
    </Box>
  );
};

export default Notificacao;
