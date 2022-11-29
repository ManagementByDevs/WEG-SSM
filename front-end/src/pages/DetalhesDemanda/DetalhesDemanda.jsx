import React from "react";

import { Box, Typography, Button, Divider } from "@mui/material";

import SaveAltOutlinedIcon from "@mui/icons-material/SaveAltOutlined";

import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Caminho from "../../components/Caminho/Caminho";
import BeneficiosDetalheDemanda from "../../components/BeneficiosDetalheDemanda/BeneficiosDetalheDemanda";

import FontConfig from "../../service/FontConfig";

const DetalhesDemanda = () => {
  return (
    <FundoComHeader>
      <Box className="p-2">
        <Box className="flex w-full relative">
          <Caminho/>
          <Box className=" absolute" sx={{top: '10px' ,right: '20px', cursor: 'pointer'}}>
            <SaveAltOutlinedIcon fontSize="large" className="delay-120 hover:scale-110 duration-300" sx={{color:'icon.main'}}/>
          </Box>
        </Box>
        <Box className="flex justify-center relative items-center mt-3">
          <Box
            className="flex flex-col gap-5 border rounded p-10"
            sx={{ width: "55rem", height: "70rem" }}
          >
            <Box className="flex justify-center">
              <Typography
                fontSize={FontConfig.title}
                sx={{ fontWeight: "600", cursor: "default" }}
                color="primary.main"
              >
                Aqui tem o título da demanda
              </Typography>
            </Box>
            <Divider />
            <Box>
              <Typography
                fontSize={FontConfig.veryBig}
                fontWeight="600"
                color="text.primary"
              >
                Problema:
              </Typography>
              <Typography
                fontSize={FontConfig.medium}
                className="text-justify"
                color="text.secondary"
                sx={{ marginLeft: "30px" }}
              >
                Lorem Ipsum is simply dummy text of the printing and typesetting
                industry. Lorem Ipsum has been the industry's standard dummy
                text ever since the 1500s, when an unknown printer took a galley
                of type and scrambled it to make a type specimen book. It has
                survived not only five centuries is simply dummy text of the
                printing and typesetting industry. Lorem Ipsum has been the
                industry's standard dummy text ever since the 1500s, when an
                unknown printer took a galley of type and scrambled it to make a
                type specimen book. It has survived not only five centuries
              </Typography>
            </Box>
            <Box>
              <Typography
                fontSize={FontConfig.veryBig}
                fontWeight="600"
                color="text.primary"
              >
                Proposta:
              </Typography>
              <Typography
                fontSize={FontConfig.medium}
                className="text-justify"
                color="text.secondary"
                sx={{ marginLeft: "30px" }}
              >
                Lorem Ipsum is simply dummy text of the printing and typesetting
                industry. Lorem Ipsum has been the industry's standard dummy
                text ever since the 1500s, when an unknown printer took a galley
                of type and scrambled it to make a type specimen book. It has
                survived not only five centuries is simply dummy text of the
                printing and typesetting industry. Lorem Ipsum has been the
                industry's standard dummy text ever since the 1500s, when an
                unknown printer took a galley of type and scrambled it to make a
                type specimen book. It has survived not only five centuries is
                simply dummy text of the printing and typesetting industry.
                Lorem Ipsum has been the industry's standard dummy text ever
                since the 1500s, when an unknown printer took a galley of type
                and scrambled it to make a type specimen book. It has survived
                not only five centuries
              </Typography>
            </Box>
            <Box>
              <Typography
                fontSize={FontConfig.veryBig}
                fontWeight="600"
                color="text.primary"
              >
                Beneficios:
              </Typography>
              <Box className="ml-7 mt-2">
                <BeneficiosDetalheDemanda />
              </Box>
            </Box>
            <Box>
              <Typography
                fontSize={FontConfig.veryBig}
                fontWeight="600"
                color="text.primary"
              >
                Frequência de uso:
              </Typography>
              <Typography
                fontSize={FontConfig.medium}
                className="text-justify"
                color="text.secondary"
                sx={{ marginLeft: "30px" }}
              >
                Lorem Ipsum is simply dummy text of the printing and
              </Typography>
            </Box>
            <Box>
              <Typography
                fontSize={FontConfig.veryBig}
                fontWeight="600"
                color="text.primary"
              >
                Anexos:
              </Typography>
              <Box>AQUI JÁS ANEXOS</Box>
            </Box>
          </Box>
          <Box
            className="flex fixed justify-around"
            sx={{ width: "20rem", bottom: "20px", right: "20px" }}
          >
            <Button
              sx={{
                backgroundColor: "primary.main",
                color: "text.white",
                fontSize: FontConfig.default,
              }}
              variant="contained"
            >
              Recusar
            </Button>
            <Button
              sx={{
                backgroundColor: "primary.main",
                color: "text.white",
                fontSize: FontConfig.default,
              }}
              variant="contained"
            >
              Devolver
            </Button>
            <Button
              sx={{
                backgroundColor: "primary.main",
                color: "text.white",
                fontSize: FontConfig.default,
              }}
              variant="contained"
            >
              Aceitar
            </Button>
          </Box>
        </Box>
      </Box>
    </FundoComHeader>
  );
};

export default DetalhesDemanda;
