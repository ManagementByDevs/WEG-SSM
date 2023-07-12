import React, { useContext, useState } from "react";

import { Box } from "@mui/material";

import Tour from "reactour";

import Ajuda from "../../components/Ajuda/Ajuda";
import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Caminho from "../../components/Caminho/Caminho";
import CriarPautaContent from "../../components/CriarPautaContent/CriarPautaContent";

import TextLanguageContext from "../../service/TextLanguageContext";

/** Página utilizada para criação de uma pauta */
const CriarPauta = () => {

  /** Context para obter os textos do sistema */
  const { texts } = useContext(TextLanguageContext);

  /** useState para abrir e fechar o tour */
  const [isTourOpen, setIsTourOpen] = useState(false);

  const stepsTour = [
    {
      selector: "#primeiro",
      content: texts.criarPauta.tour.tour1,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#segundo",
      content: texts.criarPauta.tour.tour2,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#terceiro",
      content: texts.criarPauta.tour.tour3,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#setimo",
      content: texts.criarPauta.tour.tour7,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#oitavo",
      content: texts.criarPauta.tour.tour8,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#nono",
      content: texts.criarPauta.tour.tour9,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#quinto",
      content: texts.criarPauta.tour.tour5,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#quarto",
      content: texts.criarPauta.tour.tour4,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#sexto",
      content: texts.criarPauta.tour.tour6,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#decimo",
      content: texts.criarPauta.tour.tour10,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
  ];

  return (
    <FundoComHeader>
      {/* Tour de ajuda para a criação da pauta*/}
      <Tour
        steps={stepsTour}
        isOpen={isTourOpen}
        onRequestClose={() => setIsTourOpen(false)}
        accentColor="#00579D"
        rounded={10}
        showCloseButton={false}
      />

      <Ajuda onClick={() => setIsTourOpen(true)} />
      <Box className="p-2">
        <Caminho feedback={true} />
        <Box className="w-full flex justify-center mt-4 mb-6">
          <Box className="w-5/6 relative">
            <CriarPautaContent />
          </Box>
        </Box>
      </Box>
    </FundoComHeader>
  );
};

export default CriarPauta;
