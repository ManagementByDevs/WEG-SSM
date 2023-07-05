import { createContext } from "react";

const SpeechSynthesisContext = createContext({
  lendoTexto: false,
  librasAtivo: false,
  setLibrasAtivo: () => {},
  setLendoTexto: () => {},
  lerTexto: (texto) => {},
});

export default SpeechSynthesisContext;
