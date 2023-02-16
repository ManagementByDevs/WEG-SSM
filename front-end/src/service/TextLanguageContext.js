import { createContext } from "react";

const TextLanguageContext = createContext({
  texts: {},
  setTexts: () => {},
});

export default TextLanguageContext;
