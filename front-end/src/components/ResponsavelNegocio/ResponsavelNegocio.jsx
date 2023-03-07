import React, { useContext } from "react";
import { Box, Typography } from "@mui/material";

import DeleteOutlineOutlinedIcon from "@mui/icons-material/DeleteOutlineOutlined";

import FontContext from "../../service/FontContext";

const ResponsavelNegocio = (props) => {
  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

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
            value={props.dados.nome}
            onChange={(e) =>
              props.setDados({ ...props.dados, nome: e.target.value })
            }
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
            value={props.dados.area}
            onChange={(e) =>
              props.setDados({ ...props.dados, area: e.target.value })
            }
          />
        </Box>
      </Box>
      {props.index !== 0 && (
        <Box className="flex ml-3">
          <DeleteOutlineOutlinedIcon
            className="delay-120 hover:scale-110 duration-300"
            fontSize="large"
            sx={{ color: "icon.main", cursor: "pointer" }}
            onClick={() => props.deleteResponsavel(props.index)}
          />
        </Box>
      )}
    </Box>
  );
};

export default ResponsavelNegocio;
