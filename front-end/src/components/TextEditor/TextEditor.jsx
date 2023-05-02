import JoditEditor from "jodit-react";
import React, { useRef, useContext, useMemo } from "react";

import TextLanguageContext from "../../service/TextLanguageContext";

/** Componente para criar um editor de texto utilizado para o campo de escopo da proposta */
const TextEditor = (props) => {

  // Context para alterar idioma
  const { texts, setTexts } = useContext(TextLanguageContext);

  /** Variável de referência para o editor */
  const editor = useRef(JoditEditor);

  /** Variável que contém as configurações do editor */
  const config = {
    readonly: false, // Todas opções encontradas em https://xdsoft.net/jodit/docs/
    height: "700",
    placeholder: texts.textEditor.digiteAquiEscopoDaProposta,
    allowResizeX: false,
    allowResizeY: false,
  };

  return useMemo(() => (
    <div className="App">
      <JoditEditor
        ref={editor}
        value={props.escopo}
        config={config}
        onChange={valor => props.setEscopo(valor)}
      />
    </div>
  ), [])
};

export default TextEditor;
