import React, { useRef } from "react";

import { Box, Paper, Typography } from "@mui/material";

import DeleteIcon from "@mui/icons-material/Delete";

import FontConfig from "../../service/FontConfig";

const Escopo = (props) => {
  const deleteIcon = useRef(null);

  const handleClick = (number) => {
    // if (e.target.id)
    // console.log(e.target == deleteIcon.current);
    if (number == 1) {
      props.onclick(props.index);
    } else {
      props.handleDelete(props.index)
    }
  };

  return (
    <Paper
      className="flex flex-col gap-1 border-t-4 pt-2 pb-3 px-6 cursor-pointer"
      sx={{ borderColor: "primary.main" }}
      
    >
      {/* Container titulo e progressao */}
      <Box className="flex w-full gap-4 items-center">
        <Typography
          className="w-3/4 overflow-hidden text-ellipsis whitespace-nowrap"
          fontSize={FontConfig.veryBig}
          fontWeight="600"
          onClick={(e) => handleClick(1)}
        >
          {props.escopo.titulo}
        </Typography>

        {/* Progressão do escopo */}
        <Box className="flex w-1/4 h-full gap-2 items-center">
          <Box
            className="w-11/12 h-4 flex rounded"
            sx={{ backgroundColor: "divider.main" }}
          >
            <Box
              className="w-full rounded"
              sx={{
                backgroundColor: "primary.main",
                width: props.escopo.porcentagem,
              }}
            />
          </Box>
          <Typography fontSize={FontConfig.medium}>
            {props.escopo.porcentagem}
          </Typography>
        </Box>
      </Box>

      {/* Proposta (dados do escopo) */}
      <Box className="relative">
        <Box className="h-16">
          <Typography
            className="w-11/12 h-full overflow-hidden text-ellipsis whitespace-pre-wrap"
            fontSize={FontConfig.default}
          >
            {props.escopo.proposta}
          </Typography>
        </Box>
        <DeleteIcon
          className="absolute bottom-0 hover:cursor-pointer"
          ref={deleteIcon}
          sx={{ right: "-10px" }}
          color="primary"
          onClick={(e) => handleClick(2)}
        />
      </Box>
    </Paper>
  );
};

export default Escopo;
