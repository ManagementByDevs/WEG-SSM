import React from "react";

import { Box, Typography, Button } from "@mui/material";

import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Caminho from "../../components/Caminho/Caminho";
import BeneficiosDetalheDemanda from "../../components/BeneficiosDetalheDemanda/BeneficiosDetalheDemanda";

import FontConfig from "../../service/FontConfig";

const DetalhesDemanda = () => {
  return (
    <FundoComHeader>
      <Box className="p-2">
        <Caminho />
        <Box className="flex justify-center items-center mt-3">
          <Box
            className="flex flex-col gap-5 border rounded p-10"
            sx={{ width: "55rem", height: "50rem" }}
          >
            <Box className="flex justify-center" sx={{ marginBottom: "20px" }}>
              <Typography
                fontSize={FontConfig.title}
                sx={{ fontWeight: "600", cursor: "default" }}
                color="primary.main"
              >
                Detalhes da Demanda
              </Typography>
            </Box>
            <Box>
              <Typography
                fontSize={FontConfig.veryBig}
                fontWeight="600"
                color="text.primary"
              >
                Título da demanda:
              </Typography>
              <Typography
                fontSize={FontConfig.default}
                className="text-justify"
                color="text.secondary"
                sx={{ marginLeft: "30px" }}
              >
                Lorem Ipsum is simply dummy text of the prinpe and scrambled it
                to make a type specimen book.
              </Typography>
            </Box>
            <Box>
              <Typography
                fontSize={FontConfig.veryBig}
                fontWeight="600"
                color="text.primary"
              >
                Problema:
              </Typography>
              <Typography
                fontSize={FontConfig.default}
                className="text-justify"
                color="text.secondary"
                sx={{ marginLeft: "30px" }}
              >
                Lorem Ipsum is simply dummy text of the printing and typesetting
                industry. Lorem Ipsum has been the industry's standard dummy
                text ever since the 1500s, when an unknown printer took a galley
                of type and scrambled it to make a type specimen book. It has
                survived not only five centuries
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
                fontSize={FontConfig.default}
                className="text-justify"
                color="text.secondary"
                sx={{ marginLeft: "30px" }}
              >
                Lorem Ipsum is simply dummy text of the printing and typesetting
                industry. Lorem Ipsum has been the industry's standard dummy
                text ever since the 1500s, when an unknown printer took a galley
                of type and scrambled it to make a type specimen book. It has
                survived not only five centuries
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
                fontSize={FontConfig.default}
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
          <Box className="flex absolute" sx={{bottom: '10px', right: '10px'}}>
          <Button
              sx={{
                backgroundColor: "primary.main",
                color: "text.white",
                fontSize: FontConfig.default,
              }}
              variant="contained"
              disableElevation
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
              disableElevation
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
              disableElevation
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