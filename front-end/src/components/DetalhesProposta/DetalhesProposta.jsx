import React, { useContext } from "react";

import { Box, Divider, Typography } from "@mui/material";

import LogoWEG from "../../assets/logo-weg.png";
import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";

const DetalhesProposta = ({
  proposta = {
    analista: {},
    anexo: [],
    beneficios: [],
    buSolicitante: 0,
    busBeneficiadas: [],
    codigoPPM: 0,
    data: "",
    demanda: 0,
    departamento: 0,
    escopo: 0,
    fimExecucao: "",
    forum: 0,
    frequencia: "",
    gerente: 0,
    historicoProposta: [],
    id: 0,
    inicioExecucao: "",
    linkJira: "",
    naoPublicada: true,
    parecerComissao: 0,
    parecerDG: 0,
    parecerInformacao: "",
    paybackTipo: "",
    paybackValor: 0,
    problema: "",
    proposta: "",
    publicada: false,
    responsavelNegocio: [],
    secaoTI: "",
    solicitante: {},
    status: "",
    tabelaCustos: [],
    tamanho: "",
    titulo: "",
    visibilidade: true,
  },
}) => {
  const { FontConfig } = useContext(FontContext);
  const { texts } = useContext(TextLanguageContext);

  return (
    <Box className="mt-10 flex justify-center">
      <Box
        className="border rounded px-10 py-4 border-t-6"
        sx={{ width: "55rem", borderTopColor: "primary.main" }}
      >
        {/* Box header */}
        <Box className="w-full flex justify-between">
          <Typography color="primary" fontWeight="bold" fontSize={FontConfig.big}>{texts.detalhesProposta.ppm} {proposta.codigoPPM}</Typography>
          <Box className="w-16">
            <img src={LogoWEG} alt="Logo WEG" />
          </Box>
        </Box>

        {/* Box Conteudo */}
        <Box className="w-full">
          {/* Titulo */}
          <Box>
            <Typography fontSize={FontConfig.smallTitle}>
              {proposta.titulo}
            </Typography>
          </Box>
          <Divider />

          {/* Box Informações gerais */}
          <Box>
            <Box className="flex">

            <Typography fontSize={FontConfig.medium} fontWeight="bold">Solicitante: </Typography>
            <Typography fontSize={FontConfig.medium}>{proposta.solicitante.nome} </Typography>
            </Box>
          </Box>
        </Box>
      </Box>
    </Box>
  );
};

export default DetalhesProposta;
