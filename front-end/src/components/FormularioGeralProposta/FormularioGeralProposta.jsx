import React, { useState } from "react";
import {
  Box,
  FormControl,
  Select,
  MenuItem,
  Typography,
  Divider,
} from "@mui/material";

import FontConfig from "../../service/FontConfig";

import ResponsavelNegocio from "../ResponsavelNegocio/ResponsavelNegocio";

import AddCircleOutlineOutlinedIcon from "@mui/icons-material/AddCircleOutlineOutlined";

const FormularioCustosProposta = () => {
  const [responsavelNegocio, setResponsavelNegocio] = useState([
    { nome: "", area: "", visible: true },
  ]);

  const deleteResponsavel = (indexResponsavel) => {
    let aux = responsavelNegocio.map((responsavel) => {
      return {
        nome: responsavel.nome,
        area: responsavel.area,
        visible: responsavel.visible,
      };
    });
    aux[indexResponsavel].visible = false;
    setResponsavelNegocio(aux);
  };

  return (
    <Box className="flex flex-col" sx={{ height: "45rem" }}>
      <Box className="mt-12">
        <Box className="flex w-full justify-around mb-5 mt-10">
          <Box>
            <Box className="flex mb-2">
              <Typography sx={{ fontSize: FontConfig.big, fontWeight: "600" }}>
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
              <Box className="mr-5">
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
                  type="date"
                  placeholder="Digite o código..."
                />
              </Box>
              <Box>
                <Typography sx={{ fontSize: FontConfig.big }}>à</Typography>
              </Box>
              <Box className="ml-5">
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
                  type="date"
                  placeholder="Digite o código..."
                />
              </Box>
            </Box>
          </Box>
          <Box>
            <Box className="flex mb-2">
              <Typography sx={{ fontSize: FontConfig.big, fontWeight: "600" }}>
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
                className="flex outline-none border-solid border px-1 py-1.5 drop-shadow-sm rounded border-l-4"
                sx={{
                  width: "20%;",
                  height: "30px",
                  backgroundColor: "background.default",
                  borderLeftColor: "primary.main",
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
              <Typography sx={{ fontSize: FontConfig.big, fontWeight: "600" }}>
                Código PPM:
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
                className="flex outline-none border-solid border px-1 py-1.5 drop-shadow-sm rounded border-l-4"
                sx={{
                  width: "100%;",
                  height: "30px",
                  backgroundColor: "background.default",
                  borderLeftColor: "primary.main",
                }}
                component="input"
                placeholder="Digite o código..."
              />
            </Box>
          </Box>
        </Box>
        <Box className="flex flex-col mb-8" sx={{ marginLeft: "6.1rem" }}>
          <Box className="flex mb-2">
            <Typography sx={{ fontSize: FontConfig.big, fontWeight: "600" }}>
              Link do jira:
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
              placeholder="Insira o link do jira..."
            />
          </Box>
        </Box>
        <Divider />
        <Box className="flex flex-col mb-8" sx={{ marginLeft: "6.1rem" }}>
          <Box className="flex mt-8 items-center">
            <Typography
              sx={{
                fontSize: FontConfig.veryBig,
                fontWeight: "600",
                marginRight: "5px",
                color: "primary.main",
              }}
            >
              Responsável/Responsáveis:
            </Typography>
            <Typography
              sx={{
                fontSize: FontConfig.veryBig,
                fontWeight: "600",
                marginRight: "8px",
                color: "red",
              }}
            >
              *
            </Typography>
            <AddCircleOutlineOutlinedIcon
              className="delay-120 hover:scale-110 duration-300"
              sx={{ color: "primary.main", cursor: "pointer" }}
              onClick={() => {
                setResponsavelNegocio([
                  ...responsavelNegocio,
                  {
                    responsavelNegocio: "",
                    cargo: "",
                    email: "",
                    telefone: "",
                  },
                ]);
              }}
            />
          </Box>
          {responsavelNegocio?.map((item, index) => (
            <ResponsavelNegocio dados={item} index={index} deleteResponsavel={deleteResponsavel}/>
          ))}
        </Box>
        <Divider />
        <Box sx={{ marginLeft: "6.1rem" }}>
          <Box className="flex items-center mt-8">
            <Typography
              sx={{
                fontSize: FontConfig.big,
                fontWeight: "600",
                marginRight: "5px",
              }}
            >
              Anexos:
            </Typography>
            <AddCircleOutlineOutlinedIcon
              className="delay-120 hover:scale-110 duration-300"
              sx={{ color: "icon.main", cursor: "pointer" }}
            />
          </Box>
        </Box>
      </Box>
    </Box>
  );
};

export default FormularioCustosProposta;
