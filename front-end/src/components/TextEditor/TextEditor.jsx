import React, { useRef } from "react";

import JoditEditor from "jodit-react";

const TextEditor = (props) => {
  // Variável para o editor
  const editor = useRef(null);

  // Variável que contem as configurações do editor
  const config = {
    readonly: false, // Todas opções encontradas em https://xdsoft.net/jodit/docs/
    height: "700",
    placeholder: "Digite aqui o escopo da proposta...",
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
