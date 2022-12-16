import React, { useRef } from "react";

import JoditEditor from "jodit-react";

const TextEditor = (props) => {
  const editor = useRef(null);

  const config = {
    readonly: false, // Todas opções encontradas em https://xdsoft.net/jodit/doc/
    height: "700",
    placeholder: "Digite aqui o escopo da proposta...",
    allowResizeX: false,
    allowResizeY: false,
  };

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
