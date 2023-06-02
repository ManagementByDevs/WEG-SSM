import React, { createContext, useState } from "react";

export const SpeechSynthesisContext = createContext({
  lendoTexto: false,
  setLendoTexto: () => {},
  lerTexto: () => {},
});

export const SpeechSynthesisService = ({ children }) => {
  const [lendoTexto, setLendoTexto] = useState(false);

  // Função que irá setar o texto que será "lido" pela a API
  const lerTexto = (escrita) => {
    if (lendoTexto) {
      const synthesis = window.speechSynthesis;
      const utterance = new SpeechSynthesisUtterance(escrita);

      const finalizarLeitura = () => {
        if ("speechSynthesis" in window) {
          synthesis.cancel();
        }
      };

      if (lendoTexto && escrita !== "") {
        if ("speechSynthesis" in window) {
          synthesis.speak(utterance);
        }
      } else {
        finalizarLeitura();
      }

      return () => {
        finalizarLeitura();
      };
    }
  };

  return (
    <SpeechSynthesisContext.Provider
      value={{
        lendoTexto,
        setLendoTexto,
        lerTexto,
      }}
    >
      {children}
    </SpeechSynthesisContext.Provider>
  );
};
