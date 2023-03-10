import React, { useRef, useContext } from "react";

import JoditEditor from "jodit-react";

import TextLanguageContext from "../../service/TextLanguageContext";

// Componente para criar um editor de textos utilizados em outros componentes
const TextEditor = (props) => {
  // Context para alterar idioma
  const { texts, setTexts } = useContext(TextLanguageContext);
  // Variável para o editor
  const editor = useRef(null);

  // Variável que contem as configurações do editor
  const config = {
    readonly: false, // Todas opções encontradas em https://xdsoft.net/jodit/docs/
    height: "700",
    placeholder: texts.textEditor.digiteAquiEscopoDaProposta,
    allowResizeX: false,
    allowResizeY: false,
  };

  // Função para atualizar o escopo
  const handleUpdate = (newContent) => {
    props.setEscopo(newContent);
  };

  return (
    <div className="App">
      <JoditEditor
        ref={editor}
        value={props.escopo}
        config={config}
        onBlur={handleUpdate}
      />
    </div>
  );
};

export default TextEditor;
