import React, { useRef, useContext } from "react";

import JoditEditor from "jodit-react";

import FontContext from "../../service/FontContext";

const TextEditor = (props) => {
  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // Variável para o editor
  const editor = useRef(null);

  // Variável que contem as configurações do editor
  const config = {
    readonly: false, // Todas opções encontradas em https://xdsoft.net/jodit/doc/
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
