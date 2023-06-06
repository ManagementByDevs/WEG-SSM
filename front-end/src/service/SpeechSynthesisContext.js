import { createContext } from "react";

const SpeechSynthesisContext = createContext({
  lendoTexto: false,
  setLendoTexto: () => {},
  lerTexto: (texto) => {},
});

export default SpeechSynthesisContext;
